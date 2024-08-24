package com.Bugs.Exceptions;

public class DeveloperServiceLayerException extends Exception{
    public DeveloperServiceLayerException() {
    }

    public DeveloperServiceLayerException(String message) {
        super(message);
    }

    public DeveloperServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeveloperServiceLayerException(Throwable cause) {
        super(cause);
    }

    public DeveloperServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
