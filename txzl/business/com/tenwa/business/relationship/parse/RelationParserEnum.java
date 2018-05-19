package com.tenwa.business.relationship.parse;


public enum RelationParserEnum {
	ADDRESS_PARSER(AddressRelationParser.class);
	
	private Class<? extends RelationParser> parserClass;
	
	private RelationParserEnum(Class<? extends RelationParser> parserClass){
		this.setParserClass(parserClass);
	}

	public Class<? extends RelationParser> getParserClass() {
		return parserClass;
	}

	public void setParserClass(Class<? extends RelationParser> parserClass) {
		this.parserClass = parserClass;
	}
}
