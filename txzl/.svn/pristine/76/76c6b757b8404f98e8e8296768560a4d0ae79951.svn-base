package com.tenwa.leasing.entity.chattelmortgage;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author cyw
 * @date 2013-3-4下午09:33:10
 * @info 动产抵押登记信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "动产抵押登记信息")
@Table(name="CHATTEL_MORTGAGE")
public class ChattelMortgage {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name="项目名称")
	@JoinColumn(name="PROJ_NAME_ID")
	private ProjInfo projNameId;
	
	
	@FieldName(name="抵押人名称")
	@Column(name="MORTGAGOR", length=50)
	private String mortgagor;
	
	@FieldName(name="登记单位")
	@Column(name="REGISTER_COMPANY",length=50)
	private String registerCompany;
	
	@FieldName(name="登记编号")
	@Column(name="REGISTER_NUMBER",length=50)
	private String registerNumber;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_ID")
	@FieldName(name="种类")
	private DictionaryData typeId;
	
	@FieldName(name="数额")
	@Column(name="AMOUNT",length=50)
	private String amount;
	
	@FieldName(name="抵押登记期限（起）")
	@Column(name="MORTGAGE_BEGIN",length=50)
	private String mortgageBegin;
	
	@FieldName(name="抵押登记期限（止）")
	@Column(name="MORTGAGE_END",length=50)
	private String mortgageEnd;
	
	@FieldName(name="债务履行期限（起）")
	@Column(name="DEBT_BEGIN",length=50)
	private String debtBegin;
	
	@FieldName(name="债务履行期限（止）")
	@Column(name="DEBT_END",length=50)
	private String debtEnd;
		
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

	public ProjInfo getProjNameId() {
		return projNameId;
	}

	public void setProjNameId(ProjInfo projNameId) {
		this.projNameId = projNameId;
	}

	public String getMortgagor() {
		return mortgagor;
	}

	public void setMortgagor(String mortgagor) {
		this.mortgagor = mortgagor;
	}

	public String getRegisterCompany() {
		return registerCompany;
	}

	public void setRegisterCompany(String registerCompany) {
		this.registerCompany = registerCompany;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	

	public DictionaryData getTypeId() {
		return typeId;
	}

	public void setTypeId(DictionaryData typeId) {
		this.typeId = typeId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMortgageBegin() {
		return mortgageBegin;
	}

	public void setMortgageBegin(String mortgageBegin) {
		this.mortgageBegin = mortgageBegin;
	}

	public String getMortgageEnd() {
		return mortgageEnd;
	}

	public void setMortgageEnd(String mortgageEnd) {
		this.mortgageEnd = mortgageEnd;
	}

	public String getDebtBegin() {
		return debtBegin;
	}

	public void setDebtBegin(String debtBegin) {
		this.debtBegin = debtBegin;
	}

	public String getDebtEnd() {
		return debtEnd;
	}

	public void setDebtEnd(String debtEnd) {
		this.debtEnd = debtEnd;
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
