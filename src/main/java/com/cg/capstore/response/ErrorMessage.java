package com.cg.capstore.response;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

	private HttpStatus header;
	private String message;
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(HttpStatus header, String message) {
		this.header = header;
		this.message = message;
	}
	public HttpStatus getHeader() {
		return header;
	}
	public void setHeader(HttpStatus header) {
		this.header = header;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
