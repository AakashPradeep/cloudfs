# cloudfs
An HDFS compatible FileSystem to store data in different cloud object storage like S3, GCS. In my view, a block storage system for a local Hard disk/SSD or different cloud vendor's object storage requires a hierarchical File System layer to make it easy to use it for processing and analyzing the data. The current implementation is based on that idea and it also tries to separate the actual data storage from the metadata storage so that either of them can be scaled independently. I also believe that the current set of big data processing tools at our disposals like Spark and MapReduce are heavily based on HDFS compatible Filesystem and require a layer like the current File System to work with cloud object storage in an optimal and less complicated way. It would allow using 2 phase commit to make sure either customer gets all the data or nothing after a processing step by allowing an atomic rename operation. 

# Features 
* Supports off-heap and on heap metadata storage based on configuration per instance. This feature allows overcoming the JVM GC pause of the Server node.
* It provides versioned storage for the metadata which essentially implements Snapshot Isolation to provide better concurrency control and also allow to capture frequent Snapshot in parallel to all read, write and delete operation. It also allows for avoiding any locking while reading metadata. 
* If underlining Cloud storage provides read after write consistency (like S3) then this File System implementation will support strong consistency and allow a very consistent and parallel read-write. 
* It supports transactions and makes sure read calls only read committed data i.e. there is no lazy read and lazy writes. 
* It also supports the atomic rename which will allow us to use the 1st version of Hadoop's commit algorithm and avoid the need of any complicated committers. 
* It can be used to implement a quota system like HDFS.
* It also provides separate lock service to minimize the footprint for Java Locks Objects.
* It supports Unix like ACL to control permission for a file besides underlining the IAM model. Besides this, it provides a plugin-based mechanism to implement custom logic to control access. Which will allow the customer to control access to a file based on whatever custom access management they have like an active directory or third-party services like Okta, which I believe would be very helpful for implementing compliance like GDPR and HIPPA.
* It supports namespace and allows to host multiple namespaces at a single server or have 1:1 relationship for Server and namespace. Each namespace can independently support different cloud vendor object storage. 
* Currently it uses Grpc for the client to server communication so if required it can support a very secure communication channel and all metadata on the wire can be encrypted. The client to server communication protocol also developed as a plugin so if required customers can implement it using their favorite tools like Apache Thrift. I selected Grpc because of ease in development, features, and popularity.
* It would also allow migrating a part of metadata from one namespace to a new namespace, which would be very helpful in case of one namespace grows too big to be handled by a single server or has a lot of traffic. 
* It has been designed to support federated metadata server, customer can implement a new plugin to find the mapping between namespace and server. 
* Currently it has been implemented in Java to be easily compatible with other processing system and I hope it will allow getting a lot of community support. 

# How to use: an example
```Java
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

            //create a file
            Path filePath = new Path(path, "c.txt");
            FSDataOutputStream outputStream = fileSystem.create(filePath);
            try (final PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(outputStream))) {
                IntStream.range(0, 10000).forEach(i ->
                        printWriter.write("Hello how are you doing times:" + i + "\n"));
            }

            Assert.assertTrue(fileSystem.exists(filePath));

            
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
```
# License 
**TBD**

# Authors
Aakash Pradeep (email2aakash@gmail.com)
