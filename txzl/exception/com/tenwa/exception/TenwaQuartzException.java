package com.tenwa.exception;

public class TenwaQuartzException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public TenwaQuartzException(String msg) {
		super(msg);
	}

	public TenwaQuartzException(Exception e) {
		super(e);
	}

}