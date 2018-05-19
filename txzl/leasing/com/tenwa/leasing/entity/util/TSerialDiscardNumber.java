package com.tenwa.leasing.entity.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

/**
 * TSerialDiscardNumber entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "0.废弃序号明细表")
@Table(name="T_SERIAL_DISCARD_NUMBER")
public class TSerialDiscardNumber implements java.io.Serializable {

	// Fields

	private String id;
	private String type; 
	private String typeId;
	private Integer year;
	private Integer month;
	private Integer discardNumber;

	// Constructors

	/** default constructor */
	public TSerialDiscardNumber() {
	}

	/** minimal constructor */
	public TSerialDiscardNumber(String type, Integer year, Integer discardNumber) {
		this.type = type;
		this.year = year;
		this.discardNumber = discardNumber;
	}

	/** full constructor */
	public TSerialDiscardNumber(String type, String typeId, Integer year,
			Integer month, Integer discardNumber) {
		this.type = type;
		this.typeId = typeId;
		this.year = year;
		this.month = month;
		this.discardNumber = discardNumber;
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

	@Column(name = "TYPE_ID", length = 200)
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
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

	@Column(name = "DISCARD_NUMBER_", nullable = false, precision = 10, scale = 0)
	public Integer getDiscardNumber() {
		return this.discardNumber;
	}

	public void setDiscardNumber(Integer discardNumber) {
		this.discardNumber = discardNumber;
	}

}