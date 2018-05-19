package com.tenwa.report.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Report_Test")
public class TestData {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	private String stringData;
	private int intData;
	private float floatData;
	private double doubleData;
	private long longData;
	private BigDecimal decimalData;
	@Temporal(value=TemporalType.DATE)
	private Date dateData;
	
	private boolean booleanData;
	private Calendar calendarData;
	private Currency currencyData;

	public boolean isBooleanData() {
		return booleanData;
	}

	public void setBooleanData(boolean booleanData) {
		this.booleanData = booleanData;
	}

	public Calendar getCalendarData() {
		return calendarData;
	}

	public void setCalendarData(Calendar calendarData) {
		this.calendarData = calendarData;
	}

	public Currency getCurrencyData() {
		return currencyData;
	}

	public void setCurrencyData(Currency currencyData) {
		this.currencyData = currencyData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStringData() {
		return stringData;
	}

	public void setStringData(String stringData) {
		this.stringData = stringData;
	}

	public int getIntData() {
		return intData;
	}

	public void setIntData(int intData) {
		this.intData = intData;
	}

	public float getFloatData() {
		return floatData;
	}

	public void setFloatData(float floatData) {
		this.floatData = floatData;
	}

	public double getDoubleData() {
		return doubleData;
	}

	public void setDoubleData(double doubleData) {
		this.doubleData = doubleData;
	}

	public long getLongData() {
		return longData;
	}

	public void setLongData(long longData) {
		this.longData = longData;
	}

	public BigDecimal getDecimalData() {
		return decimalData;
	}

	public void setDecimalData(BigDecimal decimalData) {
		this.decimalData = decimalData;
	}

	public Date getDateData() {
		return dateData;
	}

	public void setDateData(Date dateData) {
		this.dateData = dateData;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
