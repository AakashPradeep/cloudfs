package com.aakash.server.services;

import com.aakash.bean.NamespaceInfo;
import com.aakash.in.memory.ds.LockService;
import com.aakash.server.exceptions.NamespaceCannotBeCreated;
import com.aakash.server.exceptions.NamespaceDoesNotExist;
import com.aakash.server.exceptions.NamsepaceAlreadyExistException;
import com.aakash.server.in.memory.ds.DirContainer;
import com.aakash.server.ds.NamespaceContainer;
import com.aakash.server.in.memory.ds.InMemoryNodeAttribute;
import com.aakash.server.in.memory.ds.NodeAttribute;
import com.aakash.server.in.memory.ds.TrashQueue;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class NamespaceContainerService {
    private final NamespaceContainer namespaceContainer = new NamespaceContainer();
    private final UTCTimeProvider utcTimeProvider = new UTCTimeProvider();

    NamespaceContainerService() {
    }
    //TODO update namespace
    //TODO delete namespace

    /**
     * It allows only one request thread to modify the namespace metadata but multiple can readCommitted it.
     *
     * @param owner          name of the owner creating the namespace
     * @param groupName      name of the group owner belongs to
     * @param namespace      name of the namespace
     * @param cloudVendorURI class name of cloud specific {@link org.apache.hadoop.fs.FileSystem}
     * @param bucketName     name of the bucket if any
     * @param additionalInfo any additional info
     * @throws NamespaceCannotBeCreated
     * @throws NamsepaceAlreadyExistException
     */
    public synchronized void registerNewNamespace(String owner, String groupName, String namespace, String cloudVendorURI, String bucketName, Map<String, String> additionalInfo) throws NamespaceCannotBeCreated, NamsepaceAlreadyExistException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(namespace), "namespace cannot be null or empty");
        Preconditions.checkArgument(StringUtils.isNotEmpty(cloudVendorURI), "cloudVendorURI cannot be null or empty");

        checkVendorFSClassPresence(cloudVendorURI);
        if (additionalInfo == null) {
            additionalInfo = Collections.emptyMap();
        }

        NamespaceInfo namespaceInfo = new NamespaceInfo(namespace, cloudVendorURI, bucketName, additionalInfo);
        NodeAttribute dirInMemoryNodeAttribute = InMemoryNodeAttribute.createDirNodeAttribute((short) 770, owner, groupName, utcTimeProvider.currentEpochTime());
        this.namespaceContainer.add(namespace, namespaceInfo, dirInMemoryNodeAttribute);
        return;
    }

    public Set<NamespaceInfo> listNamespace() {
        return this.namespaceContainer.listOfNamespace();
    }

    public Set<String> listOfNamespaceName() {
        return this.namespaceContainer.listOfNamespaceNames();
    }

    public NamespaceInfo getNamespaceInfo(String namespace) throws NamespaceDoesNotExist {
        return this.namespaceContainer.getNamespaceInfo(namespace);
    }

    DirContainer getDirContainer(String namespace) throws NamespaceDoesNotExist {
        return this.namespaceContainer.getRootDirOfNamespace(namespace);
    }

    LockService getLockService(String namespace) throws NamespaceDoesNotExist {
        return this.namespaceContainer.getLockService(namespace);
    }

    TrashQueue getTrashQueue(String namespace) throws NamespaceDoesNotExist {
        return this.namespaceContainer.getTrashQueue(namespace);
    }

    private void checkVendorFSClassPresence(String vendorUri) throws NamespaceCannotBeCreated {
        try {
            FileSystem.get(new URI(vendorUri), new Configuration());
        } catch (IOException | URISyntaxException e) {
            throw new NamespaceCannotBeCreated(e);
        }
    }

}
