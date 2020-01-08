package com.aakash.cloudfs.mappers;

import com.aakash.cloudfs.bean.HostWithPort;
import org.apache.hadoop.conf.Configuration;

public interface NamespaceToServerMapperProxy {
    void initialize(Configuration configuration);
    HostWithPort getMetadataServerHostsWithPort(String namespace);
}
