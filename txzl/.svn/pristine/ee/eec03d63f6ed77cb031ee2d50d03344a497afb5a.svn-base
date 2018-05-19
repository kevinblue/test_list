package com.tenwa.leasing.entity.cust;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author xaj
 * @date 2016/11/02
 * @info 客户评级
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "客户评级")
@Table(name="CUST_GRADE")
public class CustGrade {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;

	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE")
	private String createDate;
	
	@FieldName(name = "评级时间")
	@Column(name="GRADE_DATE")
	private String gradeDate;
	
	public String getGradeDate() {
		return gradeDate;
	}

	public void setGradeDate(String gradeDate) {
		this.gradeDate = gradeDate;
	}

	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;
	
	@FieldName(name="客户ID")
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="CUST_ID")
	private CustInfo custId;
	
	@Column(name="ALL_SCORE")
	@FieldName(name="总得分")
	private String allScore ;	
	
	@FieldName(name = "客户信用优势")
	@Column(name="ADVANTAGE", length=3000)
	private String advantage;

	@FieldName(name = "客户信用劣势")
	@Column(name="INFERIORITY", length=3000)
	private String inferiority;
	
	@FieldName(name = "客户盈利资产项目的实施情况")
	@Column(name="IMPLEMENTATION", length=3000)
	private String implementation;
	
	@FieldName(name = "客户未来信用等级预测")
	@Column(name="FORECAST", length=3000)
	private String forecast;
	
	@FieldName(name = "客户评级结论")
	@Column(name="CONCLUSION", length=3000)
	private String conclusion;
	
	@Column(name="GRADE_RESULT")
	@FieldName(name="评级结果")
	private String gradeResult;

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

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getAllScore() {
		return allScore;
	}

	public void setAllScore(String allScore) {
		this.allScore = allScore;
	}

	public String getGradeResult() {
		return gradeResult;
	}

	public void setGradeResult(String gradeResult) {
		this.gradeResult = gradeResult;
	}	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getInferiority() {
		return inferiority;
	}

	public void setInferiority(String inferiority) {
		this.inferiority = inferiority;
	}

	public String getImplementation() {
		return implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	
}
