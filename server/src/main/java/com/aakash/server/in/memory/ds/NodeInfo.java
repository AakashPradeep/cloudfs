package com.aakash.server.in.memory.ds;

import org.apache.hadoop.fs.Path;

public interface NodeInfo {
    NodeAttribute getAttribute();

    Path getVendorCloudPath();

    default void free(){}
}
