package com.aakash.cloudfs.mapper;

import com.aakash.cloudfs1.mapper.CfsPathToVendorPathMapper;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for {@link CfsPathToVendorPathMapper.IdentityPathMapper}
 */
public class IdentityPathMapperTest {
    @Test
    public void testPathMap() throws IOException {
        Path cfsPath = new Path("/test/a/b/c");
        Path vendorPath = new CfsPathToVendorPathMapper.IdentityPathMapper().map("com.aakash.ns1", cfsPath, "testBucket",
                "gs://testBucket/");

        Path expectedParentPath = new Path("gs://testBucket/com.aakash.ns1/test/a/b/c");
        Assert.assertEquals(vendorPath.getName() + " is not as expected", vendorPath, expectedParentPath);
    }

    @Test
    public void testPathMapWithRelativePath() throws IOException {
        Path cfsPath = new Path("test/a/b/c");
        Path vendorPath = new CfsPathToVendorPathMapper.IdentityPathMapper().map("com.aakash.ns1", cfsPath, "testBucket",
                "gs://testBucket/");

        Path expectedParentPath = new Path("gs://testBucket/com.aakash.ns1/test/a/b/c");
        Assert.assertEquals(vendorPath.getName() + " is not as expected", vendorPath, expectedParentPath);
    }
}
