package com.tenwa.report.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import com.tenwa.report.enums.ContentType;

@Entity
@Table(name = "Report_Layout")
@XmlRootElement(name="layout")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportLayout {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	@XmlID
	private String id;

	@Column(name = "divHeight")
	private Integer divHeight;

	@Column(name = "divWidth")
	private Integer divWidth;

	@Column(name = "layoutOrder")
	private Integer position = 0;

	@Column
	private String contentId;
	
		
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = false)
	@JoinColumn(name = "report_id")
	@XmlIDREF
	private Report report;

		
	@Enumerated(EnumType.STRING)
	private ContentType contentType;

	@Transient
	private String realId;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDivHeight() {
		return divHeight;
	}

	public void setDivHeight(Integer divHeight) {
		if (divHeight == null) {
			this.divHeight = 0;
		} else {
			this.divHeight = divHeight;
		}
	}

	public Integer getDivWidth() {
		return divWidth;
	}

	public void setDivWidth(Integer divWidth) {
		if (divWidth == null) {
			this.divWidth = 0;
		} else {
			this.divWidth = divWidth;
		}
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		if (position == null) {
			this.position = 0;
		} else {
			this.position = position;
		}
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	/*
	public ReportTable getReportTable() {
		return reportTable;
	}

	public void setReportTable(ReportTable reportTable) {
		this.reportTable = reportTable;
	}

	public ReportChart getReportChart() {
		return reportChart;
	}

	public void setReportChart(ReportChart reportChart) {
		this.reportChart = reportChart;
	}
	
	public ReportPage getReportPage() {
		return reportPage;
	}

	public void setReportPage(ReportPage reportPage) {
		this.reportPage = reportPage;
	}
	*/
	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	

	/* When in Hibernate Session Mode, this function not called */
	@PrePersist
	@PreUpdate
	public void prePersist() {
		if (this.getDivHeight() == null)
			this.setDivHeight(0);
		if (this.getDivWidth() == null)
			this.setDivWidth(0);
		if (this.getPosition() == null)
			this.setPosition(0);
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getRealId() {
		return this.contentId.substring(this.contentType.name().length()+1);
	}
	
	public String toString(){
		return ReflectionToStringBuilder.toStringExclude(this);
	}
}
