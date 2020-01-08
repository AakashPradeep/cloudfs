package com.aakash.server.exceptions;

import java.io.IOException;

public class NamespaceCannotBeCreated extends IOException {
    public NamespaceCannotBeCreated(String message) {
        super(message);
    }

    public NamespaceCannotBeCreated(String message, Throwable cause) {
        super(message, cause);
    }

    public NamespaceCannotBeCreated(Throwable cause) {
        super(cause);
    }
}
