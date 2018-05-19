package com.tenwa.leasing.entity.cust;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author rovine
 * @date 2014-8-13下午03:26:53
 * @info 关联企业及分支机构情况
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name="关联企业及分支机构情况")
@Table(name="CUST_SHARE_COMPANY")
public class CustShareCompany {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;

	@ManyToOne
	@FieldName(name="客户ID")
	@JoinColumn(name="CUST_ID")
	private CustInfo custId;
	
	@FieldName(name="关联企业名称")
	@Column(name="STOCKHOLDER_NAME", length=100)
	private String stockholderName;
	
	@ManyToOne
	@FieldName(name="法人/个人[D]")
	@JoinColumn(name="STOCKHOLDER_TYPE")
	private DictionaryData stockholderType;
	
	@FieldName(name="身份证/组织机构代码")
	@Column(name="ORG_CODE", length=20)
	private String orgCode;
	
	@FieldName(name="注册资本")
	@Column(name="REG_CAPITAL", length=200)
	private String regCapital;
	
	@FieldName(name="持股比例")
	@Column(name="SHAREHOLDING", precision=22,scale=6)
	private BigDecimal shareholding;
	
	@FieldName(name="法人代表")
	@Column(name="LEGAL_REPRESENTATIVE", length=100)
	private String legalRepresentative;
	
	@ManyToOne
	@FieldName(name="关联关系[D]")
	@JoinColumn(name="RELATIONSHIP")
	private DictionaryData relationship;
	
	@FieldName(name="地址")
	@Column(name="ADDR_", length=200)
	private String addr;
	
	@FieldName(name="主营业务")
	@Column(name="BIZ_SCOPE_PRIMARY", length=2000)
	private String bizScopePrimary;
	
	@ManyToOne
	@FieldName(name="登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="登记时间")
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

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getStockholderName() {
		return stockholderName;
	}

	public void setStockholderName(String stockholderName) {
		this.stockholderName = stockholderName;
	}

	public DictionaryData getStockholderType() {
		return stockholderType;
	}

	public void setStockholderType(DictionaryData stockholderType) {
		this.stockholderType = stockholderType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public BigDecimal getShareholding() {
		return shareholding;
	}

	public void setShareholding(BigDecimal shareholding) {
		this.shareholding = shareholding;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public DictionaryData getRelationship() {
		return relationship;
	}

	public void setRelationship(DictionaryData relationship) {
		this.relationship = relationship;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBizScopePrimary() {
		return bizScopePrimary;
	}

	public void setBizScopePrimary(String bizScopePrimary) {
		this.bizScopePrimary = bizScopePrimary;
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
}
