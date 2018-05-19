package com.tenwa.leasing.entity.finacial;

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
import com.tenwa.leasing.entity.file.BaseFile;

/**
 * 
 * @author zyh
 * @date 2016-9-23下午09:33:10
 * @info 利息计提明细表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "利息计提明细表")
@Table(name="INTEREST_PROVISION_INFO")
public class InterestProvisionInfo {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name="月份")
	@JoinColumn(name="DATE_ID")
	private InterestProvision dateId;	
	
	@FieldName(name="业务编号")
	@Column(name="SERVICE_NUMBER", length=50)
	private String servicenumber;
	
	@FieldName(name="合同号")
	@Column(name="CONTRACT_NO",length=50)
	private String contractno;
	
	@FieldName(name="企业客户")
	@Column(name="ENTERPRISE_CUSTOMER",length=50)
	private String enterprisecustomer;
	
	@FieldName(name="企业客户名称")
	@Column(name="ENTERPRISE_CUSTOMER_NAME",length=200)
	private String enterprisecustomername;
	
	@FieldName(name="币种")
	@Column(name="CURRENCY",length=50)
	private String currency;
	
	@FieldName(name="利率")
	@Column(name="INTEREST_RATE",length=50)
	private String interestrate;
		
	@FieldName(name="余额")
	@Column(name="BALANCE",length=100)
	private String balance;
	
	@FieldName(name="利息")
	@Column(name="INTEREST",length=100)
	private String interest;
	
	@FieldName(name="起息日")
	@Column(name="DATE_OF_INTEREST",length=100)
	private String dateofinterest;
	
	@FieldName(name="止息日")
	@Column(name="CEASE_DATE",length=100)
	private String ceasedate;
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InterestProvision getDateId() {
		return dateId;
	}

	public void setDateId(InterestProvision dateId) {
		this.dateId = dateId;
	}

	public String getServicenumber() {
		return servicenumber;
	}

	public void setServicenumber(String servicenumber) {
		this.servicenumber = servicenumber;
	}

	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public String getEnterprisecustomer() {
		return enterprisecustomer;
	}

	public void setEnterprisecustomer(String enterprisecustomer) {
		this.enterprisecustomer = enterprisecustomer;
	}

	public String getEnterprisecustomername() {
		return enterprisecustomername;
	}

	public void setEnterprisecustomername(String enterprisecustomername) {
		this.enterprisecustomername = enterprisecustomername;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getDateofinterest() {
		return dateofinterest;
	}

	public void setDateofinterest(String dateofinterest) {
		this.dateofinterest = dateofinterest;
	}

	public String getCeasedate() {
		return ceasedate;
	}

	public void setCeasedate(String ceasedate) {
		this.ceasedate = ceasedate;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}
	
	

}
