package com.aakash.cloudfs.bean;

import com.google.common.base.Preconditions;

import java.util.Objects;


public class HostWithPort {
    private final String hostname;
    private final int port;

    public HostWithPort(String hostname, int port) {
        Preconditions.checkNotNull(hostname, "hostname cannot be null");
        Preconditions.checkArgument(port >= 0, "port cannot be < 0 ");
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HostWithPort that = (HostWithPort) o;
        return port == that.port &&
                Objects.equals(hostname, that.hostname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostname, port);
    }

    public String map() {
        return hostname + ":" + port;
    }

    @Override
    public String toString() {
        return "HostWithPort{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                '}';
    }
}
