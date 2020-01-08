package com.aakash.cloudfs1.mapper;

import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public interface CfsPathToVendorPathMapper {
    Path map(String namespace, Path cfsPath, String bucketName, String vendorURI) throws IOException;

    default URI newUri(String namespace, Path cfsPath, String scheme, String authority, String filename) throws IOException {
        try {
            return new URI(scheme, authority, "/" + namespace + cfsPath.toUri().getPath() + "/" + filename, null, null);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    default URI getURI(String vendorURI) throws IOException {
        try {
            return new URI(vendorURI);
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    class IdentityPathMapper implements CfsPathToVendorPathMapper {

        @Override
        public Path map(String namespace, Path cfsPath, String bucketName, String vendorURIStr) throws IOException {

            String resolvedVendorUriStr = vendorURIStr;
            URI vendorUri = getURI(vendorURIStr);
            if(vendorUri.getAuthority() == null){
                resolvedVendorUriStr += bucketName;
            }
            Path vendorUriPath = new Path(resolvedVendorUriStr);

            String filePath = cfsPath.toUri().getPath();

            if(cfsPath.isAbsolute()){
                filePath = namespace+filePath;
            } else {
                filePath = namespace +"/"+filePath;
            }

            Path resultPath = new Path(vendorUriPath,filePath);
            return resultPath;
        }
    }

    class UniquePathMapper implements CfsPathToVendorPathMapper {

        @Override
        public Path map(String namespace, Path cfsPath, String bucketName, String vendorUriStr) throws IOException {
            URI vendorURI = getURI(vendorUriStr);

            Path qualifiedCfsPath = cfsPath.makeQualified(cfsPath.toUri(), new Path("/"));
            final String unqiueFilename = unqiueFilename(qualifiedCfsPath.getName());
            if (vendorURI.getAuthority().contains(bucketName)) {
                URI pathURI = newUri(namespace, qualifiedCfsPath.getParent(), vendorURI.getScheme(), vendorURI.getAuthority(), unqiueFilename);
                return new Path(pathURI).makeQualified(vendorURI, new Path("/", namespace));
            }

            URI pathURI = newUri(namespace, qualifiedCfsPath.getParent(), vendorURI.getScheme(), bucketName, unqiueFilename);
            return new Path(pathURI).makeQualified(vendorURI, new Path("/", namespace));
        }

        private String unqiueFilename(String name) {
            final String extension = FilenameUtils.getExtension(name);
            final String baseName = FilenameUtils.getBaseName(name);
            return baseName + "-" + UUID.randomUUID().toString() + "." + extension;
        }


    }
}
