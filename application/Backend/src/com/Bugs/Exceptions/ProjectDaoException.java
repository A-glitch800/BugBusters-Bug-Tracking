package com.Bugs.Exceptions;

public class ProjectDaoException extends Exception {
    public ProjectDaoException() {
    }

    public ProjectDaoException(String message) {
        super(message);
    }

    public ProjectDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectDaoException(Throwable cause) {
        super(cause);
    }

    public ProjectDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
