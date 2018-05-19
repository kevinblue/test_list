package com.tenwa.leasing.entity.cust;

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


/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:23:41
 * @info 问题及建议信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "问题及建议信息")
@Table(name="PROBLEM_ADVICE_DETAIL")
public class 	ProblemAdviceDetail{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	
	@FieldName(name = "问题")
	@Column(name="PROBLEM", length=2000)
	private String problem;
	
	@FieldName(name = "建议")
	@Column(name="ADVICE", length=2000)
	private String advice;
	
	@FieldName(name = "备注")
	@Column(name="REMARKS", length=2000)
	private String remarks;

	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;

	@ManyToOne(targetEntity = SaleWeekReport.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "SALEWEEK_ID")
	@FieldName(name="工作周报编号")
	private SaleWeekReport saleweekid;
	
	@FieldName(name = "项目阶段")
	@Column(name="PROJ_STATUS", length=200)
	private String projstatus;

	public String getProjstatus() {
		return projstatus;
	}

	public void setProjstatus(String projstatus) {
		this.projstatus = projstatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public SaleWeekReport getSaleweekid() {
		return saleweekid;
	}

	public void setSaleweekid(SaleWeekReport saleweekid) {
		this.saleweekid = saleweekid;
	}
	
	
}
