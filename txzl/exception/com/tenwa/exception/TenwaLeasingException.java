package com.tenwa.exception;

public class TenwaLeasingException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public TenwaLeasingException(String msg) {
		super(msg);
	}

	public TenwaLeasingException(Exception e) {
		super(e);
	}

}