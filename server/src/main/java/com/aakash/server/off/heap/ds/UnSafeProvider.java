package com.aakash.server.off.heap.ds;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;

public class UnSafeProvider {

    private static final Unsafe unsafe;

    static {
        Constructor<Unsafe> unsafeConstructor = null;
        try {
            unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        unsafeConstructor.setAccessible(true);
        try {
            unsafe = unsafeConstructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
