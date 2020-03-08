package com.aakash.server.services;

import com.aakash.wal.AvroWalReader;
import com.aakash.wal.AvroWalWriter;
import com.aakash.wal.WALWriter;
import com.aakash.wal.WalReader;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;

public class WALService {
    private final Configuration configuration;
    private final WALWriter walWriter;

    public WALService(Configuration configuration) {
        this.configuration = configuration;
        this.walWriter = new AvroWalWriter();
        this.walWriter.init(configuration);
    }

    public WALWriter getWalWriter() {
        return walWriter;
    }

    public WalReader getWalReader(String walFilePath) throws IOException {
        return new AvroWalReader(walFilePath);
    }
}


