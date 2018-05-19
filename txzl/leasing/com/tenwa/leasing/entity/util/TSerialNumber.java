package com.tenwa.leasing.entity.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

/**
 * TSerialNumber entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "0.序号明细表")
@Table(name="T_SERIAL_NUMBER")
public class TSerialNumber implements java.io.Serializable {

	// Fields

	private String id;
	private String type;
	private Integer year;
	private Integer month;
	private Integer orderNumber;

	// Constructors

	/** default constructor */
	public TSerialNumber() {
	}

	/** minimal constructor */
	public TSerialNumber(String type, Integer year, Integer orderNumber) {
		this.type = type;
		this.year = year;
		this.orderNumber = orderNumber;
	}

	/** full constructor */
	public TSerialNumber(String type, Integer year, Integer month, Integer orderNumber) {
		this.type = type;
		this.year = year;
		this.month = month;
		this.orderNumber = orderNumber;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID_",nullable = false, length = 64)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TYPE_", nullable = false, length = 200)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "YEAR_", nullable = false, precision = 10, scale = 0)
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "MONTH_", precision = 10, scale = 0)
	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Column(name = "ORDER_NUMBER_", nullable = false, precision = 10, scale = 0)
	public Integer getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

}