package com.aakash.cloudfs;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class CfsOutputStream extends OutputStream {
    private final AtomicLong bytesWritten = new AtomicLong();
    private final OutputStream backupStream;
    private final long blockSize;
    private final short replication;
    private final MetadataClientService service;
    private final Path cfsPath;
    private final Path vendorPath;
    private final String owner;
    private final List<String> groups;
    private final String namespace;
    private final FileSystem vendorFS;
    private AtomicBoolean isClosed = new AtomicBoolean(false);

    public CfsOutputStream(OutputStream backupStream, long blockSize, short replication, MetadataClientService service, Path cfsPath, Path vendorPath, String owner, List<String> groups, String namespace, FileSystem vendorFS) {
        this.backupStream = backupStream;
        this.blockSize = blockSize;
        this.replication = replication;
        this.service = service;
        this.cfsPath = cfsPath;
        this.vendorPath = vendorPath;
        this.owner = owner;
        this.groups = groups;
        this.namespace = namespace;
        this.vendorFS = vendorFS;
    }

    @Override
    public void write(int b) throws IOException {
        if (isClosed.get()) {
            throw new IOException("file is already closed");
        }
        this.backupStream.write(b);
        this.bytesWritten.incrementAndGet();
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (isClosed.get()) {
            throw new IOException("file is already closed");
        }
        this.backupStream.write(b, off, len);
        this.bytesWritten.addAndGet(len);
    }

    @Override
    public void write(byte[] b) throws IOException {
        if (isClosed.get()) {
            throw new IOException("file is already closed");
        }
        this.backupStream.write(b);
        this.bytesWritten.addAndGet(b.length);
    }

    @Override
    public void flush() throws IOException {
        this.backupStream.flush();
    }

    @Override
    public void close() throws IOException {
        if (isClosed.get()) {
            return;
        }
        isClosed.compareAndSet(false, true);
        this.backupStream.close();

        try {
            service.createOnUploadCompletion(this.namespace, this.cfsPath, this.vendorPath, this.owner, this.groups,
                    this.replication, this.blockSize, this.bytesWritten.get());
        } catch (Exception e) {
            this.vendorFS.delete(vendorPath, true);

            throw e;
        }
    }
}
