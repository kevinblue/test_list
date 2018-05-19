package com.tenwa.report.enums;

public enum ReportType {
	FOLDER(1),
	REPORT(2);	
	
	private int type;
	
	private ReportType(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}	
}
