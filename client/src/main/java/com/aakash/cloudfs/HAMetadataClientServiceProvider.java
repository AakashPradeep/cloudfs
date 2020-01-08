package com.aakash.cloudfs;

import com.aakash.cloudfs.bean.HostWithPort;
import com.aakash.cloudfs.mappers.NamespaceToServerMapperProxy;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.util.Optional;

public interface HAMetadataClientServiceProvider {
    MetadataClientService provide(String namespace, Configuration configuration) throws IOException;

    default HostWithPort getMetadataServerHostWithPort(String namespace, org.apache.hadoop.conf.Configuration conf) throws IOException {
        Optional<? extends Class<?>> optionalMetadataServerProxyKlass = Optional.ofNullable(conf.getClassByNameOrNull(Constants.FS_CFS_NAMESPACE_TO_METADATA_SERVER_MAPPER_PROXY_CLASS));
        Class<?> metadataServerProxyKlass = optionalMetadataServerProxyKlass.orElseThrow(() -> new IOException(Constants.FS_CFS_NAMESPACE_TO_METADATA_SERVER_MAPPER_PROXY_CLASS + " is not defined"));
        try {
            NamespaceToServerMapperProxy namespaceToServerMapperProxy = (NamespaceToServerMapperProxy) metadataServerProxyKlass.newInstance();
            namespaceToServerMapperProxy.initialize(conf);
            return namespaceToServerMapperProxy.getMetadataServerHostsWithPort(namespace);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IOException(e);
        }
    }

    class SimpleMetadataClientServiceProvider implements HAMetadataClientServiceProvider {
        private HostWithPort hostWithPort = null;
        private MetadataClientService metadataClientService = null;

        @Override
        public synchronized MetadataClientService provide(String namespace, Configuration configuration) throws IOException {
            final HostWithPort newHostWithPort = getMetadataServerHostWithPort(namespace, configuration);
            if (newHostWithPort.equals(hostWithPort)) {
                this.hostWithPort = newHostWithPort;
                this.metadataClientService = new GrpcBasedMetadataClientServiceImpl(this.hostWithPort);
            }
            return this.metadataClientService;
        }
    }

    class LocalInMemoryMetadataClientStoreServiceProvider implements HAMetadataClientServiceProvider {

        private static final MetadataClientService service = new LocalInMemoryMetadataClientStoreService();

        @Override
        public MetadataClientService provide(String namespace, Configuration configuration) throws IOException {
            return service;
        }
    }
}
