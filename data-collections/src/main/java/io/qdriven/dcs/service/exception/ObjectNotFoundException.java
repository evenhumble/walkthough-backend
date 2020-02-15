package io.qdriven.dcs.service.exception;

/**
 * @author: patrick on 2019/11/8
 * @Description:
 */
public class ObjectNotFoundException extends RuntimeException {


	public ObjectNotFoundException() {
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundException(Throwable cause) {
		super(cause);
	}

	public ObjectNotFoundException(String message, Throwable cause, boolean enableSuppression,
								   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
