package com.aakash.server.services;

import com.aakash.in.memory.ds.LockService;
import com.aakash.server.ds.TransactionIdProvider;
import org.apache.hadoop.conf.Configuration;

public class ServiceProvider {

    private static final ServiceProvider INSTANCE = new ServiceProvider();
    private final NamespaceContainerService ncs = new NamespaceContainerService();
    private final LockService lockService = new LockService();
    private FileOperationService fileOperationService;
    private StorageService storageService;
    private TransactionService transactionService;

    private ServiceProvider() {
    }

    public static ServiceProvider getSingeltonInstance() {
        return INSTANCE;
    }

    public NamespaceContainerService getNamespaceContainerService() {
        return ncs;
    }

    public synchronized FileOperationService getFileOperationService() throws IllegalAccessException, InstantiationException {
        if (this.fileOperationService == null) {
            this.fileOperationService = new FileOperationService(getNamespaceContainerService(), getStorageService(), getConfiguration());
        }
        return fileOperationService;
    }

    public synchronized StorageService getStorageService() {
        if (this.storageService == null) {
            Configuration configuration = getConfiguration();
            boolean isOffHeapStorageEnabled = configuration.getBoolean(StorageService.CLOUDS_OFF_HEAP_STORAGE_ENABLED,
                    true);
            this.storageService = new StorageService(isOffHeapStorageEnabled, getTransactionService());
            this.storageService.startCleaningService();
        }
        return this.storageService;
    }

    public synchronized TransactionService getTransactionService(){
        if(this.transactionService == null){
            this.transactionService = new TransactionService(new TransactionIdProvider());
        }
        return this.transactionService;
    }

    protected Configuration getConfiguration() {
        return new Configuration();
    }
}
