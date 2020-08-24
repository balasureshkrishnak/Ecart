package com.hcl.ecart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(
			InsufficientBalanceException insufficientBalanceException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(insufficientBalanceException.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(
			CartNotFoundException cartNotFoundException, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(cartNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	

}
