package com.aakash.cloudfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.stream.IntStream;

public class CloudFileSystemTest {

    @Test
    public void testDirOperations() throws IOException {

        Configuration configuration = new Configuration();
        configuration.set(Constants.FS_CFS_HA_METADATA_CLIENT_SERVICE_PROVIDER_CLASS, HAMetadataClientServiceProvider.LocalInMemoryMetadataClientStoreServiceProvider.class.getName());

        String namespace = "test.ns1";
        MetadataClientService metadataClientService = new HAMetadataClientServiceProvider.LocalInMemoryMetadataClientStoreServiceProvider().provide(namespace, configuration);
        metadataClientService.registerNewNamespace("test1", "grp1", namespace, "/", "tmp", Collections.emptyMap());

        Path path = new Path("cfs://" + namespace + "/a/b");
        final FileSystem fileSystem = path.getFileSystem(configuration);
        try {
            fileSystem.mkdirs(path);
            Path pathForb1 = new Path(path.getParent(), "b1");
            fileSystem.mkdirs(pathForb1);
            fileSystem.mkdirs(new Path(path.getParent(), "b2"));

            boolean exists = fileSystem.exists(path);
            Assert.assertTrue(exists);

            RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(path, true);
            Assert.assertFalse(locatedFileStatusRemoteIterator.hasNext());

            FileStatus[] fileStatuses = fileSystem.listStatus(path.getParent());
            Assert.assertEquals(fileStatuses.length, 3);

            ContentSummary contentSummary = fileSystem.getContentSummary(path.getParent());
            Assert.assertEquals(contentSummary.getDirectoryCount(), 4);
            Assert.assertEquals(contentSummary.getFileCount(), 0);

            fileSystem.delete(path, false);
            Assert.assertFalse(fileSystem.exists(path));

            try {
                Assert.assertFalse(fileSystem.delete(path.getParent(), false));
            } catch (IOException e) {
                //expected ignore
            }

            fileSystem.delete(pathForb1);
            Assert.assertFalse(fileSystem.exists(pathForb1));
        } finally {
            if (fileSystem.exists(path.getParent())) {
                fileSystem.delete(path.getParent(), true);
            }
        }
    }

    @Test
    public void testCreateFile() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set(Constants.FS_CFS_HA_METADATA_CLIENT_SERVICE_PROVIDER_CLASS, HAMetadataClientServiceProvider.LocalInMemoryMetadataClientStoreServiceProvider.class.getName());

        String namespace = "test.create.file.ns1";
        MetadataClientService metadataClientService = new HAMetadataClientServiceProvider.LocalInMemoryMetadataClientStoreServiceProvider().provide(namespace, configuration);
        metadataClientService.registerNewNamespace("test1", "grp1", namespace, "/", "tmp", Collections.emptyMap());

        Path path = new Path("cfs://" + namespace + "/a/b");
        final FileSystem fileSystem = path.getFileSystem(configuration);

        try {
            fileSystem.mkdirs(path);

            Path filePath = new Path(path, "c.txt");
            FSDataOutputStream outputStream = fileSystem.create(filePath);
            try (final PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(outputStream))) {
                IntStream.range(0, 10000).forEach(i ->
                        printWriter.write("Hello how are you doing times:" + i + "\n"));
            }

            Assert.assertTrue(fileSystem.exists(filePath));

            ContentSummary contentSummary = fileSystem.getContentSummary(path);
            Assert.assertEquals("file count is not as expected", contentSummary.getFileCount(), 1);
            Assert.assertEquals("dir count is not as expected", contentSummary.getDirectoryCount(), 1);
            Assert.assertEquals("dir size is not as expected", contentSummary.getLength(), 348890);
            Assert.assertEquals("dir space consumed is not as expected", contentSummary.getSpaceConsumed(), 348890);

            RemoteIterator<LocatedFileStatus> locatedFileStatusRemoteIterator = fileSystem.listFiles(path, true);
            Assert.assertTrue(locatedFileStatusRemoteIterator.hasNext());
            LocatedFileStatus next = locatedFileStatusRemoteIterator.next();
            Assert.assertEquals(next.getPath(), filePath);
            Assert.assertTrue(next.getLen() > 0);
            Assert.assertFalse(locatedFileStatusRemoteIterator.hasNext());

        } finally {
            if (fileSystem.exists(path)) {
                fileSystem.delete(path, true);
            }
        }
    }

    @Test
    public void testCreateEmptyFile() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set(Constants.FS_CFS_HA_METADATA_CLIENT_SERVICE_PROVIDER_CLASS, HAMetadataClientServiceProvider.LocalInMemoryMetadataClientStoreServiceProvider.class.getName());

        String namespace = "test.create.empty.file.ns1";
        MetadataClientService metadataClientService = new HAMetadataClientServiceProvider.LocalInMemoryMetadataClientStoreServiceProvider().provide(namespace, configuration);
        metadataClientService.registerNewNamespace("test1", "grp1", namespace, "/", "tmp", Collections.emptyMap());

        Path path = new Path("cfs://" + namespace + "/a/b");
        final FileSystem fileSystem = path.getFileSystem(configuration);

        try {
            fileSystem.mkdirs(path);

            Path filePath = new Path(path, "c.txt");
            try (FSDataOutputStream outputStream = fileSystem.create(filePath)) {
                outputStream.close();
            }
            Assert.assertTrue(fileSystem.exists(filePath));

            ContentSummary contentSummary = fileSystem.getContentSummary(path);
            Assert.assertEquals("file count is not as expected", contentSummary.getFileCount(), 1);
            Assert.assertEquals("dir count is not as expected", contentSummary.getDirectoryCount(), 1);
            Assert.assertEquals("dir size is not as expected", contentSummary.getLength(), 0);
            Assert.assertEquals("dir space consumed is not as expected", contentSummary.getSpaceConsumed(), 0);

        } finally {
            if (fileSystem.exists(path)) {
                fileSystem.delete(path, true);
            }
        }
    }

    @Test
    public void testReCreateEmptyFile() throws IOException {
        Configuration configuration = new Configuration();
        configuration.set(Constants.FS_CFS_HA_METADATA_CLIENT_SERVICE_PROVIDER_CLASS, HAMetadataClientServiceProvider.LocalInMemoryMetadataClientStoreServiceProvider.class.getName());

        String namespace = "test.recreate.file.ns1";
        MetadataClientService metadataClientService = new HAMetadataClientServiceProvider.LocalInMemoryMetadataClientStoreServiceProvider().provide(namespace, configuration);
        metadataClientService.registerNewNamespace("test1", "grp1", namespace, "/", "tmp", Collections.emptyMap());

        Path path = new Path("cfs://" + namespace + "/a/b");
        final FileSystem fileSystem = path.getFileSystem(configuration);

        try {
            fileSystem.mkdirs(path);

            Path filePath = new Path(path, "c.txt");
            try (FSDataOutputStream outputStream = fileSystem.create(filePath)) {
                outputStream.close();
            }
            Assert.assertTrue(fileSystem.exists(filePath));

            //attempting to recreate the same file which should result in an an exception: FileAlreadyExistsException.
            try {
                try (FSDataOutputStream outputStream = fileSystem.create(filePath)) {
                    outputStream.close();
                }

                Assert.fail("file creation should have failed as " + filePath + " already created");
            } catch (FileAlreadyExistsException fe) {
                //expected
            }

            ContentSummary contentSummary = fileSystem.getContentSummary(path);
            Assert.assertEquals("file count is not as expected", contentSummary.getFileCount(), 1);
            Assert.assertEquals("dir count is not as expected", contentSummary.getDirectoryCount(), 1);
            Assert.assertEquals("dir size is not as expected", contentSummary.getLength(), 0);
            Assert.assertEquals("dir space consumed is not as expected", contentSummary.getSpaceConsumed(), 0);

        } finally {
            if (fileSystem.exists(path)) {
                fileSystem.delete(path, true);
            }
        }
    }
}
