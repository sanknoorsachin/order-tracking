package com.order.tracking.consumer.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(OrderConsumerException.class)
	public ResponseEntity<?> handleOrderTrackingException(OrderConsumerException exception, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), exception.getMessage(), exception.getStatus());
		return new ResponseEntity(error, error.getStatus());

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	
}
