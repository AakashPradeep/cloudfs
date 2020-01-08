package com.aakash.server.services;

import org.apache.hadoop.conf.Configuration;

public class ServiceProvider {

    private static final ServiceProvider INSTANCE = new ServiceProvider();
    private ServiceProvider(){}


    private final NamespaceContainerService ncs = new NamespaceContainerService();
    private  FileOperationService fileOperationService;

    public NamespaceContainerService getNamespaceContainerService() {
        return ncs;
    }

    public synchronized FileOperationService getFileOperationService() throws IllegalAccessException, InstantiationException {
        if(this.fileOperationService == null){
            this.fileOperationService = new FileOperationService(ncs, getConfiguration());
        }
        return fileOperationService;
    }

    protected Configuration getConfiguration(){
        return new Configuration();
    }

    public static ServiceProvider getSingeltonInstance(){
        return INSTANCE;
    }
}
