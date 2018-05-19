package com.tenwa.business.entity;

import com.tenwa.kernal.annotation.FieldName;


@FieldName(name = "财务导出工具体")
public class FinanceReportBean {
	@FieldName(name="数据对应sheet的cell的行") 
	public String index;
	@FieldName(name="数据对应标题") 
	public String FieldName;
	@FieldName(name="数据对应的字段") 
	public String Field;

	public FinanceReportBean(String index, String fieldName, String field) {
		super();
		this.index = index;
		FieldName = fieldName.replaceAll("[\\s\\p{Zs}]+", "");
		Field = field;
	}
    
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}

	public String getField() {
		return Field;
	}

	public void setField(String field) {
		Field = field;
	}

}
