package com.hummersoft.eira.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EiraException extends RuntimeException{

    private final int statusCode;

    private String errorMessage;

    public EiraException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public EiraException(int statusCode) {
        this.statusCode = statusCode;
    }

    public EiraException(String errorMessage) {
        this.errorMessage = errorMessage;
        this.statusCode = -1;
    }

    public EiraException(Exception e) {
        super(e);
        this.statusCode = -1;
        this.errorMessage = null;
    }

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
