package com.aakash.server.exceptions;

import java.io.IOException;

public class OperationNotPermittedException extends IOException {
    public OperationNotPermittedException(String message) {
        super(message);
    }
}
