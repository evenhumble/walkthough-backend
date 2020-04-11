package io.qdriven.plugins.redis.exceptions;

/**
 * Created by patrick on 16/8/12.
 */
public class RedisCommandException extends RuntimeException {
    public RedisCommandException() {
    }

    public RedisCommandException(String message) {
        super(message);
    }

    public RedisCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisCommandException(Throwable cause) {
        super(cause);
    }

    public RedisCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
