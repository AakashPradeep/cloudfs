package com.aakash.cloudfs1.mapper;

import com.aakash.cloudfs.protocol.proto.generated.stubs.Attribute;
import com.aakash.cloudfs.protocol.proto.generated.stubs.CFSFileStatus;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

import java.util.function.Function;

public class FileStatusMapper implements Function<CFSFileStatus, FileStatus> {

    @Override
    public FileStatus apply(CFSFileStatus cfsFileStatus) {
        Attribute attribute = cfsFileStatus.getAttribute();
        FileStatus fileStatus = new FileStatus(attribute.getFileSize(), !attribute.getIsFile(), attribute.getReplication(),
                attribute.getBlockSize(), attribute.getLastModifiedTime(), attribute.getLastAccessedTime(),
                FsPermission.createImmutable((short) attribute.getPermission()), attribute.getOwner(), attribute.getGroup(),
                new Path(cfsFileStatus.getCfsPath()));

        return fileStatus;
    }
}