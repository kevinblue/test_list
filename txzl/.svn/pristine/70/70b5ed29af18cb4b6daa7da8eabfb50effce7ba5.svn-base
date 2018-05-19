package com.tenwa.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.tenwa.report.enums.ColumnDataType;
import com.tenwa.report.enums.FilterDataRequestType;
import com.tenwa.report.enums.FilterType;
import com.tenwa.report.enums.HtmlType;

@Entity
@Table(name = "Report_Filter")
@XmlRootElement(name="report_filter")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportFilter {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@XmlID
	private String id;

	@Column(name = "NAME_")
	private String name;
	
	@Column(name = "ENNAME_")
	private String enname;

	private String label;

	@Enumerated(EnumType.STRING)
	private FilterType filterType;
	
	@Enumerated(EnumType.STRING)
	private ColumnDataType dbType;

	@Enumerated(EnumType.STRING)
	private HtmlType htmlType;

	@Enumerated(EnumType.STRING)
	private FilterDataRequestType filterDataRequestType;

	private String comboBoxDataSource; // 下拉列表数据来源 SQL或URL,XML

	private String comboBoxNameField; // 用于表示下拉列表的Name字段

	private String comboBoxValueField; // 用于表示下拉列表的Value字段
	
	private String express;
	
	private String defaultValue;

	private int position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public FilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(FilterType filterType) {
		this.filterType = filterType;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

//	public ReportTable getReportTable() {
//		return reportTable;
//	}
//
//	public void setReportTable(ReportTable reportTable) {
//		this.reportTable = reportTable;
//	}

	public HtmlType getHtmlType() {
		return htmlType;
	}

	public void setHtmlType(HtmlType htmlType) {
		this.htmlType = htmlType;
	}

	public String getComboBoxDataSource() {
		return comboBoxDataSource;
	}

	public void setComboBoxDataSource(String comboBoxDataSource) {
		this.comboBoxDataSource = comboBoxDataSource;
	}

	public String getComboBoxNameField() {
		return comboBoxNameField;
	}

	public void setComboBoxNameField(String comboBoxNameField) {
		this.comboBoxNameField = comboBoxNameField;
	}

	public String getComboBoxValueField() {
		return comboBoxValueField;
	}

	public void setComboBoxValueField(String comboBoxValueField) {
		this.comboBoxValueField = comboBoxValueField;
	}

	public FilterDataRequestType getFilterDataRequestType() {
		return filterDataRequestType;
	}

	public void setFilterDataRequestType(FilterDataRequestType filterDataRequestType) {
		this.filterDataRequestType = filterDataRequestType;
	}
	
	public String toString(){
		return ReflectionToStringBuilder.toStringExclude(this, "reportTable");
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public ColumnDataType getDbType() {
		return dbType;
	}

	public void setDbType(ColumnDataType dbType) {
		this.dbType = dbType;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	

}
