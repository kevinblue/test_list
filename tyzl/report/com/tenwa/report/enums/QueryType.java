package com.tenwa.report.enums;

public enum QueryType {
	SIMPLESQL(1), 
	COMPLEXSQL(2),
	PROCEDURE(3);
	/*
	 * //TODO: 未完成
	 * JAVACLASS(4),
	 * SPRINGBEAN(5),
	 * HQL(6);
	 */
	private final int type;

	private QueryType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
