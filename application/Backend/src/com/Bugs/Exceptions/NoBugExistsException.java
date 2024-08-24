package com.Bugs.Exceptions;

public class NoBugExistsException extends Exception {
    public NoBugExistsException() {
    }

    public NoBugExistsException(String message) {
        super(message);
    }

    public NoBugExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBugExistsException(Throwable cause) {
        super(cause);
    }

    public NoBugExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
