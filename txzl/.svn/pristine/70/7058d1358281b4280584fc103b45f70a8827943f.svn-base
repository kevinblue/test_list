package com.tenwa.leasing.entity.proj;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.kernal.annotation.FieldName;

/**
 * ProjInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "项目信审流程数据")
@Table(name = "PROJ_CREDIT_WORKFLOW_INFO")
public class ProjCreditWorkFlowInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@FieldName(name = "项目")
	@ManyToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name = "流程编号")
	@Column(name = "DOC_ID")
	private String docId;
	
	@FieldName(name="信审报告json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="CREDIT_REPORT_JSON_",nullable=false)
	private String creditReportJson;
	
	@FieldName(name="文件清单json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="DOC_LIST_JSON_",nullable=false)
	private String docListJson;
	
	@FieldName(name="树状迭代json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="CREDIT_GRADE_JSON_",nullable=false)
	private String creditGradeJson;

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public String getCreditReportJson() {
		return creditReportJson;
	}

	public void setCreditReportJson(String creditReportJson) {
		this.creditReportJson = creditReportJson;
	}

	public String getDocListJson() {
		return docListJson;
	}

	public void setDocListJson(String docListJson) {
		this.docListJson = docListJson;
	}

	public String getCreditGradeJson() {
		return creditGradeJson;
	}

	public void setCreditGradeJson(String creditGradeJson) {
		this.creditGradeJson = creditGradeJson;
	}
	
	
	
}