package com.brooklyn.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.brooklyn.payload.ErrorDetail;

@ControllerAdvice
public class GlobalExceptionHandler {
	// handle exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException exception, 
			WebRequest request){
		ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDetail> handleBlogApiException(BlogApiException exception, 
			WebRequest request){
		ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetail> handleGlobalException(Exception exception, 
			WebRequest request){
		ErrorDetail detail = new ErrorDetail(new Date(), exception.getMessage(),request.getDescription(false));
		
		return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
