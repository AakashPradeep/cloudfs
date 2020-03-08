package com.aakash.wal;

import com.aakash.wal.record.WALRecord;
import org.apache.hadoop.conf.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class AvroWalWriterTest {

    @Test
    public void testAvroWalWriting() throws IOException, InterruptedException {
        File testAvroWalWriting = new File("testAvroWalWriting");
        testAvroWalWriting.mkdir();

        try {
            Configuration configuration = new Configuration();
            configuration.set(AvroWalWriter.WAL_FILE_DIR, testAvroWalWriting.getAbsolutePath());
            configuration.set(AvroWalWriter.WAL_ROLLOVER_WINDOW_IN_MILLISECONDS, String.valueOf(TimeUnit.MILLISECONDS.toMillis(50)));

            AvroWalWriter avroWalWriter = new AvroWalWriter();
            avroWalWriter.init(configuration);

            HashSet<Long> trxIds = new HashSet<>();
            int i = 0;
            for (; i < 50; i++) {
                avroWalWriter.write(Long.valueOf(i), WALWriter.Operation.MKDIR, String.valueOf("hello-" + i).getBytes());
                trxIds.add(Long.valueOf(i));
            }

            Thread.sleep(50l);

            for (; i < 100; i++) {
                avroWalWriter.write(Long.valueOf(i), WALWriter.Operation.MKDIR, String.valueOf("hello-" + i).getBytes());
                trxIds.add(Long.valueOf(i));
            }

            Stream<Path> list = Files.list(testAvroWalWriting.toPath());

            Assert.assertTrue(list.count() >= 2);


            Files.list(testAvroWalWriting.toPath()).forEach(p -> {
                try {
                    AvroWalReader avroWalReader = new AvroWalReader(p.toFile().getAbsolutePath());
                    for (WALRecord walRecord : avroWalReader.getWalRecords()) {
                        long trxId = walRecord.getTrxId();
                        Assert.assertTrue(trxIds.contains(trxId));
                        trxIds.remove(trxId);
                        Assert.assertEquals(WALWriter.Operation.MKDIR.name().toString(), walRecord.getOperation().toString());
                        byte[] bytes = walRecord.getReqData().array();
                        Assert.assertEquals("hello-" + trxId, new String(bytes));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } finally {
            for (File file : testAvroWalWriting.listFiles()) {
                file.delete();
            }
            testAvroWalWriting.delete();
        }
    }
}
