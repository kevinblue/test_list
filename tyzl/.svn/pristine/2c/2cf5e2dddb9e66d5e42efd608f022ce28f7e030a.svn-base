package com.tenwa.report.query;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CellData {
	private String columnName;

	private String displayValue;
	private String rawValue;
	private Map<String, String> attributes;

	public CellData() {
	}

	public CellData(String columnName, String rawValue, String displayValue) {
		this.columnName = columnName;
		this.setValue(rawValue, displayValue);
	}

	public CellData(String columnName, String value) {
		this.columnName = columnName;
		this.setValue(value, value);
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getRawValue() {
		return rawValue;
	}

	public void setRawValue(String rawValue) {
		this.rawValue = rawValue;
	}

	public void setValue(String rawValue, String displayValue) {
		this.rawValue = rawValue;
		this.displayValue = displayValue;
	}

	public void setValue(String value) {
		this.setValue(value, value);
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void addAttribute(String key, String value) {
		if (key == null || value == null || "".equals(key.trim()))
			return;

		if (this.attributes == null) {
			this.attributes = new HashMap<String, String>();
		}
		attributes.put(key, value);
	}

	public boolean equals(Object o) {
		if (!(o instanceof CellData)) {
			return false;
		}
		return this.getRawValue().equals(((CellData) o).getRawValue());
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
