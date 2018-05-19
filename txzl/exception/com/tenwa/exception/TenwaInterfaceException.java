package com.tenwa.exception;

public class TenwaInterfaceException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public TenwaInterfaceException(String msg) {
		super(msg);
	}

	public TenwaInterfaceException(Exception e) {
		super(e);
	}

}