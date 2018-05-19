package com.tenwa.business.model;

public class EasyUIRequestParam {
	private int page;
	private int rows;
	private String sorter;
	private Boolean order = true;
	private Object[] values;
	public int getPage() {
		return page;
	}
	public int getRows() {
		return rows;
	}
	public String getSorter() {
		return sorter;
	}
	public Boolean getOrder() {
		return order;
	}
	public Object[] getValues() {
		return values;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setSorter(String sorter) {
		this.sorter = sorter;
	}
	public void setOrder(Boolean order) {
		this.order = order;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	
	
}
