package com.Bugs.Exceptions;

public class NoprojectExistsException extends Exception {
    public NoprojectExistsException() {
    }

    public NoprojectExistsException(String message) {
        super(message);
    }

    public NoprojectExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoprojectExistsException(Throwable cause) {
        super(cause);
    }

    public NoprojectExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
