package com.tenwa.business.entity;

import com.tenwa.kernal.annotation.FieldName;


@FieldName(name="列信息")
public class BaseColumnInfo {

	 public String columnName; //前台对应的字段
	 public String columnSqlName;//对应数据库中的字段
     public String fileName; //中文标题
     
     
	public BaseColumnInfo(String columnName, String columnSqlName,
			String fileName) {
		this.columnName = columnName;
		this.columnSqlName = columnSqlName;
		this.fileName = fileName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnSqlName() {
		return columnSqlName;
	}
	public void setColumnSqlName(String columnSqlName) {
		this.columnSqlName = columnSqlName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
     
}
