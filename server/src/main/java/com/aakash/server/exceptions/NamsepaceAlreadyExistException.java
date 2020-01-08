package com.aakash.server.exceptions;

import java.io.IOException;

public class NamsepaceAlreadyExistException extends IOException {
    public NamsepaceAlreadyExistException(String message) {
        super(message);
    }
}
