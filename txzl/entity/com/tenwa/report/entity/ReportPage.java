package com.tenwa.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Report_Page")
@XmlRootElement(name = "page")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportPage implements ReportContent {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	@XmlID
	private String id;
	
	@Column(name = "NAME_")
	private String name;
	
	@Column(name = "ENNAME_")
	private String enname;
	
	@Column(name = "URL_")
	private String url;
	

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toString(){
		return ReflectionToStringBuilder.toStringExclude(this);
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}
	
	@Override
	public ReportDataSource getReportDataSource() {
		// TODO Auto-generated method stub
		return null;
	}	
}
