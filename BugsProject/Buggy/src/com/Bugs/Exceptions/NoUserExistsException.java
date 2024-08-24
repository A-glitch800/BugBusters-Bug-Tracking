package com.Bugs.Exceptions;

public class NoUserExistsException extends Exception {
    public NoUserExistsException() {    }

    public NoUserExistsException(String message) {
        super(message);
    }

    public NoUserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUserExistsException(Throwable cause) {
        super(cause);
    }

    public NoUserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
