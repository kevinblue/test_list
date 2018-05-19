package com.tenwa.business.model;

import java.util.List;

public class TableColum {
	private String id = null;
	private String name = null;
	private String attribute = null;
	private String type = null;
	private String columnsStyle = null;
	private String columnValidation = null;
	private int sort = -1;
	private List<TableColum> colums = null;
	
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAttribute() {
		return attribute;
	}
	public String getType() {
		return type;
	}
	public int getSort() {
		return sort;
	}
	public List<TableColum> getColums() {
		return colums;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public void setColums(List<TableColum> colums) {
		this.colums = colums;
	}
	@Override
	public String toString() {
		return "TableColum {id : " + id + ", name : " + name + ", attribute : " + attribute + ", type : " + type + ", sort : " + sort + ", colums : " + colums + "}";
	}
	public String getColumnsStyle() {
		return columnsStyle;
	}
	public String getColumnValidation() {
		return columnValidation;
	}
	public void setColumnsStyle(String columnsStyle) {
		this.columnsStyle = columnsStyle;
	}
	public void setColumnValidation(String columnValidation) {
		this.columnValidation = columnValidation;
	}
	

}
