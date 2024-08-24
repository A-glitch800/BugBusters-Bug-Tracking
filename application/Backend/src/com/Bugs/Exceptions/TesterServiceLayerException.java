package com.Bugs.Exceptions;

public class TesterServiceLayerException extends Exception{
    public TesterServiceLayerException() {
    }

    public TesterServiceLayerException(String message) {
        super(message);
    }

    public TesterServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TesterServiceLayerException(Throwable cause) {
        super(cause);
    }

    public TesterServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
