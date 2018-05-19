package com.tenwa.report.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name = "Report_Power_Control")
public class ReportPowerControl {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	@ManyToOne(targetEntity = ReportTable.class, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "report_id")
	@FieldName(name = "报表id")
	private ReportTable reportId;
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "POWER_PERSON")
	private User PowerPerson;
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ReportTable getReportId() {
		return reportId;
	}

	public void setReportId(ReportTable reportId) {
		this.reportId = reportId;
	}

	public User getPowerPerson() {
		return PowerPerson;
	}

	public void setPowerPerson(User powerPerson) {
		PowerPerson = powerPerson;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
	
	
}
