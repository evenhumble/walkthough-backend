package io.hedwig.robot.jenkins.exceptions;

/**
 * Created by patrick on 16/3/28.
 */
public class JenkinsViewException extends RuntimeException{
    public JenkinsViewException() {
    }

    public JenkinsViewException(String message) {
        super(message);
    }

    public JenkinsViewException(String message, Throwable cause) {
        super(message, cause);
    }

    public JenkinsViewException(Throwable cause) {
        super(cause);
    }

    public JenkinsViewException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
