package com.aakash.server.ds;

import java.util.concurrent.atomic.AtomicLong;

/**
 * It provides API to get the current transaction Id and get a new transaction Id for update operations.
 * It always provide incrementing transaction number.
 */
public class TransactionIdProvider {
    private final AtomicLong sequenceId = new AtomicLong(System.currentTimeMillis());

    private static final TransactionIdProvider INSTANCE = new TransactionIdProvider();

    /**
     * It should be used for all the write/update/delete operation.
     */
    public Long getNewTrxId(){
        return sequenceId.incrementAndGet();
    }

    /**
     * It should be used for readCommitted operation to get the most updated committed value
     */
    public long getCurrentTrxId() {
        return sequenceId.get();
    }
}
