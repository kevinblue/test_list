package com.tenwa.report.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name = "Report_CONTROL_MATHCH")
@XmlRootElement(name = "match")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportControlMatch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 219904718444421054L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@XmlID
	private String id;

	@FieldName(name="名称")
	@Column(name = "NAME_",length=20)
	private String name;
	
	@FieldName(name="名称")
	@Column(name = "MATHCH_SQL",length=500)
	private String mathchSQL;

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

	public String getMathchSQL() {
		return mathchSQL;
	}

	public void setMathchSQL(String mathchSQL) {
		this.mathchSQL = mathchSQL;
	}
	
}
