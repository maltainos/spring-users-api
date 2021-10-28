package com.tccfinal.app.ws.service.exception;

public class UserServiceException extends RuntimeException{

	private static final long serialVersionUID = -799298558048118352L;
	
	public UserServiceException(String message) {
		super(message);
	}
}
