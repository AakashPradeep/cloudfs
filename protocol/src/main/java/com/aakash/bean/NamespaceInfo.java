package com.aakash.bean;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class NamespaceInfo {
    private final String namespace;
    private final String cloudVendorURI;
    private final String bucketName;
    private final Map<String, String> additionalInfo;
    private long trashTTLInMillis = TimeUnit.DAYS.toMillis(1);

    public NamespaceInfo(String namespace, String cloudVendorURI, String bucketName, Map<String, String> additionalInfo) {
        this.namespace = namespace;
        this.cloudVendorURI = cloudVendorURI;
        this.bucketName = bucketName;
        this.additionalInfo = Collections.unmodifiableMap(additionalInfo);
    }

    public synchronized void setNewTTL(long trashTTLInMillis) {
        Preconditions.checkArgument(trashTTLInMillis > TimeUnit.MINUTES.toMillis(10)
                , "Trash TTL cannot be less than 10 minutes");
        this.trashTTLInMillis = trashTTLInMillis;
    }

    public String getNamespace() {
        return namespace;
    }

    /**
     * @return a class which represents an impl of {@link org.apache.hadoop.fs.FileSystem}
     */
    public String getCloudVendorURI() {
        return cloudVendorURI;
    }

    /**
     * @return an immutable map.
     */
    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }


    public String getBucketName() {
        return bucketName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamespaceInfo that = (NamespaceInfo) o;
        return Objects.equals(namespace, that.namespace) &&
                Objects.equals(cloudVendorURI, that.cloudVendorURI) &&
                Objects.equals(bucketName, that.bucketName) &&
                Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(namespace, cloudVendorURI, bucketName, additionalInfo);
    }

    @Override
    public String toString() {
        return "NamespaceInfo{" +
                "namespace='" + namespace + '\'' +
                ", cloudVendorURI='" + cloudVendorURI + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", additionalInfo=" + additionalInfo +
                '}';
    }

    public long getTrashTTLInMillis() {
        return trashTTLInMillis;
    }
}
