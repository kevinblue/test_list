package com.tenwa.business.model;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;

public class JsonReturnResult {
	private JsonReturnResultTypeEnum returnType;
	private String                   content;
	public JsonReturnResult(JsonReturnResultTypeEnum returnType, String content) {
		super();
		this.returnType = returnType;
		this.content = content;
	}
	public JsonReturnResultTypeEnum getReturnType() {
		return returnType;
	}
	public void setReturnType(JsonReturnResultTypeEnum returnType) {
		this.returnType = returnType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
