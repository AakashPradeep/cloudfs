package com.aakash.server.in.memory.ds;

import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class NodeIdProvider {
    public static final NodeIdProvider INSTANCE;

    static {
        try {
            INSTANCE = new NodeIdProvider();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private final String seed;
    private final AtomicLong atomicLong = new AtomicLong(1423);

    private NodeIdProvider() throws UnknownHostException {
        String unqiueVal = Inet6Address.getLocalHost().getHostAddress() + "-" + System.currentTimeMillis() + "-"
                + new Random().nextInt();
        Checksum checksum = new CRC32();
        byte[] bytes = unqiueVal.getBytes();
        checksum.update(bytes, 0, bytes.length);
        this.seed = "Nid"+Long.toString(checksum.getValue(), 32);
    }

    public char[] getNewUniqueId() {
        return (this.seed + Long.toString(atomicLong.incrementAndGet(), 32)).toCharArray();
    }
}
