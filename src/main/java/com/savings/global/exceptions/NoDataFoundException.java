package com.savings.global.exceptions;

public class NoDataFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	String message;
	
	public NoDataFoundException(String message) {
		super(message);
		this.message = message;
	}

}
