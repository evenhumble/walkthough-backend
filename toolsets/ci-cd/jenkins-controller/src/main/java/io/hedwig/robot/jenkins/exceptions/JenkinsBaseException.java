package io.hedwig.robot.jenkins.exceptions;

/**
 * Created by patrick on 16/3/28.
 */
public class JenkinsBaseException extends RuntimeException {


    public JenkinsBaseException() {
    }

    public JenkinsBaseException(String message) {
        super(message);
    }

    public JenkinsBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JenkinsBaseException(Throwable cause) {
        super(cause);
    }

    public JenkinsBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
