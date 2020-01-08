package com.aakash.server.impl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MetadataGrpcServer {

    private final Server server;

    public MetadataGrpcServer(int port) {
        this.server = ServerBuilder.forPort(port).addService(new MetadataGrpcService()).build();
    }

    public void start() throws IOException {
        this.server.start();
    }

}
