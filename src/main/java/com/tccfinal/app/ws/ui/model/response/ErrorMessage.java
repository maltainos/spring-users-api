package com.tccfinal.app.ws.ui.model.response;

import java.util.Date;

public class ErrorMessage {
	
	private int status;
	private Date timestamp;
	private String message;

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(int status, Date timestamp, String message) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;

	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
