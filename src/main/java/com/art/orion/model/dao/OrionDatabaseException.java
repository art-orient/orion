package com.art.orion.model.dao;

public class OrionDatabaseException extends Exception {

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
