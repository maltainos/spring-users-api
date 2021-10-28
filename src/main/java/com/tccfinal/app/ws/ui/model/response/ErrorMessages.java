package com.tccfinal.app.ws.ui.model.response;

public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("Missing requied field. Please check documention for required fields"),
	RECORDE_ALREADY_EXISTS("Recorde already exists"), INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with provided id is not found"), AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"), COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADDRES_NOT_VERIFIED("Email address could not be verified");

	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
