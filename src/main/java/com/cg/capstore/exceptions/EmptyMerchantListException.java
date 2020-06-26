package com.cg.capstore.exceptions;

public class EmptyMerchantListException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public EmptyMerchantListException(String message) {
		super(message);
	}
}
