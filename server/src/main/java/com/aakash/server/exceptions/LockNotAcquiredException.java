package com.aakash.server.exceptions;

import java.io.IOException;

public class LockNotAcquiredException extends IOException {
    public LockNotAcquiredException(String message) {
        super(message);
    }
}
