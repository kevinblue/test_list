package com.tenwa.report.query.dialect;


public class ProcedureParameter {
	
	public enum Direction{
		IN,
		OUT,
		INOUT
	}
	private String name;
	private int position;
	private Direction direction;
	
	
}
