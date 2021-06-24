package com.art.orion.model.service;

public class OrionDatabaseException extends RuntimeException {
    public OrionDatabaseException() {
    }

    public OrionDatabaseException(String message) {
        super(message);
    }

    public OrionDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrionDatabaseException(Throwable cause) {
        super(cause);
    }
}
