package com.cg.capstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.capstore.exceptions.EmptyMerchantListException;
import com.cg.capstore.exceptions.NoFeedbacksException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends RuntimeException{
	private static final long serialVersionUID = 1L;
	@ExceptionHandler(value= {NoFeedbacksException.class,EmptyMerchantListException.class})
	public final ResponseEntity<String> exceptionHandler(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}

