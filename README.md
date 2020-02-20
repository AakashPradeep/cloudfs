# cloudfs
An HDFS compatible FileSystem to store data in different cloud object storage like S3, GCS. In my view, a block storage like system whether it is for local Hard disk or SSD or cloud vendor's object storage both required a heirarchical layer like a File System to make it easy to use for processig and analyzing the data. The current implementation is based on that idea and it try to keep the storage of actual data separate from the metadata and let them scale independently. I also beleive that the current set of big data processing tools at our disposal like Spark and MapReduce are based on HDFS compatible Filesystem for accessing data and supporting 2 phase commit to make sure either customer get all the data or nothing after a processing step and since its not supported with object storage we need a layer to store and scale the metadata and support it. 

# Features 
* Supports off heap and on heap metadata storage based on configuration per instance. This feature allow to overcome the JVM GC pause of Server node.
* It provides versioninized storage for the metadata which essentially implements Snapshot Isolation to provide better concurrency control and also allow to capture frequent Snapshot in parallel to all read, write and delete operation. It also allow to avoid any locking while reading metadata. 
* If underlining Cloud storage provides read after write consistency (like S3) then this File System implementation will support strong consistency and allow a very consistent and parallel read write. 
* It supports transactions and make sure read calls only read committed data i.e. there is no lazy read and lazy writes. 
* It also supports atomic rename which will allow to use 1st version of Hadoop's commit algorithm and avoid need of any complicated committers. 
* It also provide separate lock service to minimize the footprint for Java Locks Objects.
* It support Unix like ACL to control permission for a file besides underlining IAM model. Besides this it provides a plugin based mechanism to implement custom logic to control access. Which will allow customer to control access to a file based on whatever custom access management they have like active directory or third party service like Okta, which I believe would be very helpful for implementing compliance like GDPR and HIPPA.
* It supports namespace and allow to host multiple namespace at a single server or have 1:1 relationship for Server and namespace. Each namespace can independently supports different cloud vendor object storage. 
* Curently it uses Grpc for client to server communication so if required it can support very secure communication channel and all metadata on wire can be encrypted. The client to server communication protocol also developed as plugin so if required customer can implement it using their favorite tools like Apache Thrift. I selected Grpc because of ease in development, features and popularity.
* It would also allow to migrate a part of metadata from one namespace to a new namespace, which would be very helpful in case of one namespace grows too big to be handled by a single server or has lot of traffic. 
* It has been designed to support fedarated metadata server, customer can implement a new plugin to find the mapping between namespace and server. 
* Currently it has been implemented in Java to be easily compatible with other processing system and I hope it will allow to get lot of community support. 

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
