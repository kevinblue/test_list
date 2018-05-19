package com.tenwa.report.enums;

public enum AlignType {
	LEFT("left"),
	RIGHT("right"),
	CENTER("center")
	;
	private String align;
	
	private AlignType(String align){
		this.align = align;
	}

	public String getAlign() {
		return align;
	}

}
