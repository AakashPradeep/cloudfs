package com.aakash.server.exceptions;

import java.io.IOException;

public class NamespaceDoesNotExist extends IOException {
    public NamespaceDoesNotExist(String message) {
        super(message);
    }
}
