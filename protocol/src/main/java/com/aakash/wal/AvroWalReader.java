package com.aakash.wal;

import com.aakash.wal.record.WALRecord;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An {@link WalReader} implementation to read avro based wal.
 */
public class AvroWalReader implements WalReader {
    private final File walFile;
    private final DataFileReader<WALRecord> dataFileReader;

    public AvroWalReader(String walFilePath) throws IOException {
        this.walFile = new File(walFilePath);
        if(!this.walFile.exists()) {
            throw new FileNotFoundException(walFilePath+" is not found");
        }
        //verifies that file is a wal file
        if (!AvroWalWriter.FilenameProvider.assertWalFile(this.walFile.getName())) {
            throw new IOException(walFilePath + " is not a wal file");
        }
        this.dataFileReader = new DataFileReader<>(this.walFile, new SpecificDatumReader<WALRecord>(WALRecord.SCHEMA$));
    }

    @Override
    public Iterable<WALRecord> getWalRecords() {
        return this.dataFileReader;
    }

    @Override
    public void close() throws IOException {
        this.dataFileReader.close();
    }
}
