package com.tenwa.report.enums;


public enum ColumnDataType {
	STRING(0),
	NUMBER(1),
	DATE(2),
	DICT(3);
	
	private final int type;
	
	private ColumnDataType(int type){
		this.type = type;
	}

	public int getColumnDataTeyp(){
		return type;
	}
}
