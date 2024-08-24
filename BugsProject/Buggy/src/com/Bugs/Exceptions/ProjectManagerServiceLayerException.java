package com.Bugs.Exceptions;

public class ProjectManagerServiceLayerException extends Exception {
    public ProjectManagerServiceLayerException() {
    }

    public ProjectManagerServiceLayerException(String message) {
        super(message);
    }

    public ProjectManagerServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectManagerServiceLayerException(Throwable cause) {
        super(cause);
    }

    public ProjectManagerServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
