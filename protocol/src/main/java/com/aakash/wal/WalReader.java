package com.aakash.wal;

import com.aakash.wal.record.WALRecord;

import java.io.IOException;

public interface WalReader {

    Iterable<WALRecord> getWalRecords();

    void close() throws IOException;
}
