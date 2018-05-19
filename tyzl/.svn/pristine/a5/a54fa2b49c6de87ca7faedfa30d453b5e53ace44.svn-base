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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Report_CONTROL")
@XmlRootElement(name = "control")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportControl {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@XmlID
	private String id;

	@Column(name = "NAME_",length=100)
	private String name;
	
	@Column(name = "LABEL",length=100)
	private String label;
	
	@Column(name = "GROUP_ID_",length=100)
	private String groupId;
	
	@ManyToOne(targetEntity=ReportControlMatch.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.EAGER) 
	@JoinColumn(name="MATCH_ID_",nullable=true)
	@XmlIDREF
	private ReportControlMatch match;
	
	@JoinColumn(name="position",nullable=true)
	private int position;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ReportControlMatch getMatch() {
		return match;
	}

	public void setMatch(ReportControlMatch match) {
		this.match = match;
	}

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


	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	
	
}
