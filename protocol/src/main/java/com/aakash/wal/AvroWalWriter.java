package com.aakash.wal;

import com.aakash.wal.record.WALRecord;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.hadoop.conf.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

/**
 * An {@link WALWriter} implementation to support avro based wal.
 */
public class AvroWalWriter implements WALWriter {
    public static final String WAL_FILE_DIR = "wal.file.dir";
    public static final String WAL_ROLLOVER_WINDOW_IN_MILLISECONDS = "wal.rollover.window.in.milliseconds";
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final AtomicLong numRecords = new AtomicLong(0l);
    private String dir;
    private DataFileWriter<WALRecord> curWriter;
    private long rolloverWindow;
    private Object lock = new Object();

    @Override
    public void init(Configuration configuration) {
        String currentDirectory = System.getProperty("user.dir");
        this.dir = configuration.get(WAL_FILE_DIR, currentDirectory);
        this.rolloverWindow = configuration.getLong(WAL_ROLLOVER_WINDOW_IN_MILLISECONDS, TimeUnit.MINUTES.toMillis(10));
        this.executorService.scheduleAtFixedRate(() -> {
            synchronized (lock) {
                if (curWriter != null && numRecords.get() > 0) {
                    try {
                        curWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    curWriter = null;
                    numRecords.set(0l);
                }

            }
        }, this.rolloverWindow, this.rolloverWindow, TimeUnit.MILLISECONDS);
    }

    @Override
    public void write(long trxId, Operation operation, byte[] reqData) throws IOException {
        synchronized (lock) {
            if (this.curWriter == null) {
                SpecificDatumWriter<WALRecord> datumWriter = new SpecificDatumWriter<>(WALRecord.getClassSchema());
                this.curWriter = new DataFileWriter<>(datumWriter);
                this.curWriter.create(WALRecord.getClassSchema(), new File(this.dir, new FilenameProvider().apply(trxId)));
                this.curWriter.setFlushOnEveryBlock(true);
                this.curWriter.setSyncInterval(32);
            }

            ByteBuffer byteBuffer = ByteBuffer.wrap(reqData);
            WALRecord walRecord = WALRecord.newBuilder().setTrxId(trxId).setOperation(operation.name())
                    .setReqData(byteBuffer).build();
            this.curWriter.append(walRecord);
            this.numRecords.incrementAndGet();
        }
    }

    /**
     * Helper class to generate and parse wal file name
     */
    public static class FilenameProvider implements Function<Long, String> {

        public static final String WAL_PREFIX = "wal";
        public static final String SEPARATOR = "-";

        public static Long getTrxId(String filename) throws IOException {
            String[] split = filename.split(SEPARATOR);
            if (split.length >= 3 && split[0].equals(WAL_PREFIX)) {
                return Long.valueOf(split[1]);
            }
            throw new IOException("filename :" + filename + " is not a wal file");
        }

        public static boolean assertWalFile(String filename) {
            String[] split = filename.split(SEPARATOR);
            if (split.length < 3) {
                return false;
            }

            if (!split[0].equals(WAL_PREFIX)) {
                return false;
            }

            try {
                Long.valueOf(split[1]);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        public String apply(Long offset) {
            return WAL_PREFIX + SEPARATOR + offset + SEPARATOR + System.currentTimeMillis();
        }
    }
}
