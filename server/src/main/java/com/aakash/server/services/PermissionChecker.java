package com.aakash.server.services;

import com.amazonaws.services.simpleworkflow.model.OperationNotPermittedException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.permission.FsAction;

import java.util.List;

public interface PermissionChecker {
    default void init(Configuration configuration){}

    boolean check(short permission, String owner, List<String> groups, boolean isFile, FsAction fsAction) throws OperationNotPermittedException;

    class DefaultPermissionCheckerImpl implements PermissionChecker{

        @Override
        public boolean check(short permission, String owner, List<String> groups, boolean isFile, FsAction fsAction) throws OperationNotPermittedException {
             return true;
        }
    }

    class DoNothingPermissionCheckerImpl implements PermissionChecker {

        @Override
        public boolean check(short permission, String owner, List<String> groups, boolean isFile, FsAction fsAction) throws OperationNotPermittedException {
            return true;
        }
    }
}
