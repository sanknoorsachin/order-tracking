package com.order.tracking.consumer.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private Date timestamp;
	private String message;
	private HttpStatus status;
	private List<String> errors;

	public ErrorResponse(Date timestamp, String message, HttpStatus status) {

		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
	}

	public ErrorResponse(Date timestamp,String message, HttpStatus status, List<String> errors) {
		this.timestamp = timestamp;
		this.message=message;
		this.status = status;
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ErrorResponse(List<String> errors, HttpStatus status) {
		this.status = status;
		this.errors = errors;

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

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
