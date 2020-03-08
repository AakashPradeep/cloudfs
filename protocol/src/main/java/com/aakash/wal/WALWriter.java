package com.aakash.wal;

import org.apache.hadoop.conf.Configuration;

import java.io.IOException;

public interface WALWriter {

    void init(Configuration configuration);

    void write(long trxId, Operation operation, byte[] reqData) throws IOException;

    enum Operation {
        MKDIR, DELETE, RENAME, CREATE_ZERO_BYTE_FILE, CREATE_ON_FILE_UPLOAD_COMPLETION
    }

}
