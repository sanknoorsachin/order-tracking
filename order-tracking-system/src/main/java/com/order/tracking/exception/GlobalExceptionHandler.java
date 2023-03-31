package com.order.tracking.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.order.tracking.OrderTrackingConstants;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(OrderTrackingException.class)
	public ResponseEntity<?> handleOrderTrackingException(OrderTrackingException exception, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), exception.getMessage(), exception.getStatus());
		return new ResponseEntity(error, error.getStatus());

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
		ErrorResponse error = new ErrorResponse(new Date(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> validationList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
		ErrorResponse error = new ErrorResponse(new Date(), OrderTrackingConstants.VALIDATION_FALIURE_MESSAGE,
				HttpStatus.BAD_REQUEST, validationList);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
}
