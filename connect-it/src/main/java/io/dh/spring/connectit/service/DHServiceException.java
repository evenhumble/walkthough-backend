package io.dh.spring.connectit.service;

public class DHServiceException extends RuntimeException{
    public DHServiceException() {
    }

    public DHServiceException(String message) {
        super(message);
    }

    public DHServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DHServiceException(Throwable cause) {
        super(cause);
    }

    public DHServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
