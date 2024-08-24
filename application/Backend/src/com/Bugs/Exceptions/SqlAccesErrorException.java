package com.Bugs.Exceptions;

public class SqlAccesErrorException extends Exception{
    public SqlAccesErrorException() {
    }

    public SqlAccesErrorException(String message) {
        super(message);
    }

    public SqlAccesErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlAccesErrorException(Throwable cause) {
        super(cause);
    }

    public SqlAccesErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
