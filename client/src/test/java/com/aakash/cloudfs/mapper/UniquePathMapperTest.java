package com.aakash.cloudfs.mapper;

import com.aakash.cloudfs1.mapper.CfsPathToVendorPathMapper;
import com.aakash.cloudfs1.mapper.CfsPathToVendorPathMapper.UniquePathMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for {@link CfsPathToVendorPathMapper}
 */
public class UniquePathMapperTest {

    @Test
    public void testPathMap() throws IOException {
        Path cfsPath = new Path("/test/a/b/c");
        Path vendorPath = new UniquePathMapper().map("com.aakash.ns1", cfsPath, "testBucket",
                "gs://testBucket/");

        Path expectedParentPath = new Path("gs://testBucket/com.aakash.ns1/test/a/b");
        Assert.assertEquals("parent path not as expected", expectedParentPath, vendorPath.getParent());
        Assert.assertTrue("no extension expected", StringUtils.isEmpty(FilenameUtils.getExtension(vendorPath.getName())));
        Assert.assertTrue(vendorPath.getName() + " is not as expected", vendorPath.getName().startsWith(cfsPath.getName()));
    }

    @Test
    public void testPathMapWithRelativePath() throws IOException {
        Path cfsPath = new Path("test/a/b/c");
        Path vendorPath = new UniquePathMapper().map("com.aakash.ns1", cfsPath, "testBucket",
                "gs://testBucket/");

        Path expectedParentPath = new Path("gs://testBucket/com.aakash.ns1/test/a/b");
        Assert.assertEquals("parent path not as expected", expectedParentPath, vendorPath.getParent());
        Assert.assertTrue("no extension expected", StringUtils.isEmpty(FilenameUtils.getExtension(vendorPath.getName())));
        Assert.assertTrue(vendorPath.getName() + " is not as expected", vendorPath.getName().startsWith(cfsPath.getName()));
    }

    @Test
    public void testPathMapWithExtentsion() throws IOException {
        Path cfsPath = new Path("/test/a/b/json-format.json");
        Path vendorPath = new UniquePathMapper().map("com.aakash.ns1", cfsPath, "testBucket",
                "gs://testBucket/");

        Path expectedParentPath = new Path("gs://testBucket/com.aakash.ns1/test/a/b");
        Assert.assertEquals("parent path not as expected", expectedParentPath, vendorPath.getParent());
        Assert.assertEquals("extension is not as expected", FilenameUtils.getExtension(vendorPath.getName()), "json");
        Assert.assertTrue(vendorPath.getName() + " is not as expected", vendorPath.getName()
                .startsWith(FilenameUtils.getBaseName(cfsPath.getName())));
    }
}
