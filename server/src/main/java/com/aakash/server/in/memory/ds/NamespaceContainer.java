package com.aakash.server.in.memory.ds;

import com.aakash.bean.NamespaceInfo;
import com.aakash.server.exceptions.NamespaceDoesNotExist;
import com.aakash.server.exceptions.NamsepaceAlreadyExistException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class NamespaceContainer {
    private final Map<String, ContainerEntry> namespaceEntryMap = new ConcurrentHashMap<>();

    public Set<String> listOfNamespaceNames() {
        return this.namespaceEntryMap.keySet();
    }

    public Set<NamespaceInfo> listOfNamespace() {
        return this.namespaceEntryMap.values().stream().map(e -> e.namespaceInfo).collect(Collectors.toSet());
    }

    public void add(String namespaceName, NamespaceInfo namespaceInfo, NodeAttribute rootNodeAttribute) throws NamsepaceAlreadyExistException {
        if (exists(namespaceName)) {
            throw new NamsepaceAlreadyExistException("namespace :" + namespaceName + " already exist");
        }
        namespaceEntryMap.putIfAbsent(namespaceName, new ContainerEntry(namespaceInfo, new DirContainer(rootNodeAttribute)));
    }

    public NamespaceInfo getNamespaceInfo(String namespaceName) throws NamespaceDoesNotExist {
        if (!exists(namespaceName)) {
            throw new NamespaceDoesNotExist("namspace:" + namespaceName + " does not exist");
        }
        return this.namespaceEntryMap.get(namespaceName).namespaceInfo;

    }

    public DirContainer getRootDirOfNamespace(String namespaceName) throws NamespaceDoesNotExist {
        if (!exists(namespaceName)) {
            throw new NamespaceDoesNotExist("namspace:" + namespaceName + " does not exist");
        }
        return this.namespaceEntryMap.get(namespaceName).dirContainer;
    }

    public TrashQueue getTrashQueue(String namespaceName) throws NamespaceDoesNotExist {
        if (!exists(namespaceName)) {
            throw new NamespaceDoesNotExist("namspace:" + namespaceName + " does not exist");
        }
        return this.namespaceEntryMap.get(namespaceName).trashQueue;
    }

    public boolean exists(String namespace) {
        return this.namespaceEntryMap.containsKey(namespace);
    }

    public static class ContainerEntry {
        private final NamespaceInfo namespaceInfo;
        private final DirContainer dirContainer;
        private final TrashQueue trashQueue = new TrashQueue();

        public ContainerEntry(NamespaceInfo namespaceInfo, DirContainer dirContainer) {
            this.namespaceInfo = namespaceInfo;
            this.dirContainer = dirContainer;
        }
    }


}
