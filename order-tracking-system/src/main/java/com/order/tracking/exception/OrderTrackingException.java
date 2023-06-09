package com.order.tracking.exception;

import org.springframework.http.HttpStatus;

public class OrderTrackingException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;

	public OrderTrackingException(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
