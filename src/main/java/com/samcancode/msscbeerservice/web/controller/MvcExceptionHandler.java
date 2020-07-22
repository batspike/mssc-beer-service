package com.samcancode.msscbeerservice.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //this indicates the class is common for all controllers
public class MvcExceptionHandler {
	//Validation Exception handling
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException e) {
		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
		
		e.getConstraintViolations()
			.forEach( 
					  constraintViolation -> errors.add( constraintViolation.getPropertyPath() +
														 " : " + 
														 constraintViolation.getMessage())
					);
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<List<String>> handleBindException(BindException e) {
		List<String> errors = new ArrayList<>(e.getAllErrors().size());
		
		e.getAllErrors().forEach(err -> errors.add(err.getObjectName() + " : "+ err.getDefaultMessage()));
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
