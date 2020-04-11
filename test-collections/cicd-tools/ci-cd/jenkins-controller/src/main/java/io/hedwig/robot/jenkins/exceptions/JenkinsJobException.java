package io.hedwig.robot.jenkins.exceptions;

/**
 * Created by patrick on 16/3/28.
 */
public class JenkinsJobException extends RuntimeException {


    public JenkinsJobException() {
    }

    public JenkinsJobException(String message) {
        super(message);
    }

    public JenkinsJobException(String message, Throwable cause) {
        super(message, cause);
    }

    public JenkinsJobException(Throwable cause) {
        super(cause);
    }

    public JenkinsJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
