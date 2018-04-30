package com.alnura.greco.client.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;





@ControllerAdvice(assignableTypes = ClientController.class)
public class UsersControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
	 protected ResponseEntity<Object> handleNotFoundConflict(RuntimeException ex, WebRequest request) {
	        String bodyOfResponse = ((HttpClientErrorException)ex).getResponseBodyAsString();
	        
	        return handleExceptionInternal(ex, bodyOfResponse, 
	          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
	 
}
