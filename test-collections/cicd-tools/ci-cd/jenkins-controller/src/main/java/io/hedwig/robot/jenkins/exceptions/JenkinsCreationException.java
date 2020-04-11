package io.hedwig.robot.jenkins.exceptions;

/**
 * Created by patrick on 16/3/28.
 */
public class JenkinsCreationException extends RuntimeException {


    public JenkinsCreationException() {
    }

    public JenkinsCreationException(String message) {
        super(message);
    }

    public JenkinsCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JenkinsCreationException(Throwable cause) {
        super(cause);
    }

    public JenkinsCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
