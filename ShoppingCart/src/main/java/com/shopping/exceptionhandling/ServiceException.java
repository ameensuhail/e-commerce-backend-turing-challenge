package com.shopping.exceptionhandling;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -9079454849611061074L;

	public ServiceException() {
		super();
	}

	public ServiceException(final String message) {
		super(message);
	}
}
