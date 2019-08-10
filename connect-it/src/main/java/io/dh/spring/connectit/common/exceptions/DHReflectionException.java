package io.dh.spring.connectit.common.exceptions;

public class DHReflectionException extends RuntimeException {

    public DHReflectionException() {
    }

    public DHReflectionException(String message) {
        super(message);
    }

    public DHReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DHReflectionException(Throwable cause) {
        super(cause);
    }

    public DHReflectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
