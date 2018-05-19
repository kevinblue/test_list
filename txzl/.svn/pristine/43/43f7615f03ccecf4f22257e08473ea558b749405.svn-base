package com.tenwa.leasing.entity.cust.other;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;


/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:24:06
 * @info 供应商资料
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "供应商资料")
@Table(name="CUST_MANUFACTURERS_INFO")
public class CustManufacturersInfo {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;

	@ManyToOne
	@FieldName(name="客户ID")
	@JoinColumn(name = "CUST_ID")
	private CustInfo custId;
	
	@FieldName(name="公司统编")
	@Column(name="COMPANY_NUMBER", length=100)
	private String companyNumber;
	
	@FieldName(name="公司名称")
	@Column(name="COMPANY_NAME", length=100)
	private String companyName;
	
	@FieldName(name="项目名称")
	@Column(name="PROJECT_NAME", length=100)
	private String projectName;
	
	@FieldName(name = "月平均往来金额--合同金额")
	@Column(name="MONTH_AVG_MONEY", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal monthAvgMoney;
	
	@FieldName(name="付款方式")
	@Column(name="PAYMENT_WAY", length=100)
	private String paymentWay;
	
	@ManyToOne
	@FieldName(name="质量评级")
	@JoinColumn(name="QUALITY_RATE")
	private DictionaryData qualityrate;
	
	@ManyToOne
	@FieldName(name="天信客户信用评级")
	@JoinColumn(name="CREDIT_RATE")
	private DictionaryData creditrate;
	
	
	@FieldName(name="进货项目")
	@Column(name="PURCHASE_PROJECT", length=500)
	private String purchaseProject;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;

	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length = 20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;

	@FieldName(name = "更新时间")
	@Column(name="MODIFY_DATE", length = 20)
	private String modifyDate;

	
	
	public DictionaryData getQualityrate() {
		return qualityrate;
	}

	public void setQualityrate(DictionaryData qualityrate) {
		this.qualityrate = qualityrate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getMonthAvgMoney() {
		return monthAvgMoney;
	}

	public void setMonthAvgMoney(BigDecimal monthAvgMoney) {
		this.monthAvgMoney = monthAvgMoney;
	}

	public String getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getPurchaseProject() {
		return purchaseProject;
	}

	public void setPurchaseProject(String purchaseProject) {
		this.purchaseProject = purchaseProject;
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

	public DictionaryData getCreditrate() {
		return creditrate;
	}

	public void setCreditrate(DictionaryData creditrate) {
		this.creditrate = creditrate;
	}
	
	
	
}