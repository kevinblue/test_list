package com.tenwa.business.model;

public class UIColumns implements Comparable <UIColumns> {
	private String field;
	private String title;
	private String attribute;
	private String type;
	private String columnsStyle = null;
	private String columnValidation = null;
	private int sort;

	public String getField() {
		return field;
	}

	public String getTitle() {
		return title;
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

	public void setField(String field) {
		this.field = field;
	}

	public void setTitle(String title) {
		this.title = title;
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

	

	@Override
	public int compareTo(UIColumns o) {
		if (sort > o.sort)
			return 1;
		else if (sort == o.sort)
			return 0;
		else
			return -1;
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

	@Override
	public String toString() {
		return "UIColumns [field=" + field + ", title=" + title + ", attribute=" + attribute + ", type=" + type  + ", columnsStyle=" + columnsStyle + ", columnValidation="
				+ columnValidation + ", sort=" + sort + "]";
	}
	
	

}
