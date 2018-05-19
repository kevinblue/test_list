package com.tenwa.leasing.entity.equitypledge;

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
 * @info 股权出质设立登记
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "股权出质设立登记")
@Table(name="EQUITY_PLEDGE")
public class EquityPledge {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name="项目名称")
	@JoinColumn(name="PROJ_NAME_ID")
	private ProjInfo projNameId;
	
	
	@FieldName(name="融资租赁合同编号")
	@Column(name="FINANCIAL_NUMBER", length=50)
	private String financialNumber ;
	
	@FieldName(name="股权质押合同编号")
	@Column(name="REGISTER_COMPANY",length=50)
	private String equityNumber ;
	
	@FieldName(name="登记时间")
	@Column(name="REGISTER_TIME",length=50)
	private String registerTime;
		
	@FieldName(name="质权登记编号")
	@Column(name="PLEDGE_REG_NUM",length=50)
	private String pledgeRegNum;
	
	@FieldName(name="出质股权所在公司")
	@Column(name="PLEDGE_EQU_COM",length=50)
	private String pledgeEquCom;
	
	@FieldName(name="出质人")
	@Column(name="PLEDGOR",length=50)
	private String pledgor;
	
	@FieldName(name="出质股权数额")
	@Column(name="PLEDGED_EQU_AMOUNT",length=50)
	private String pledgedEquAmount;
	
	@FieldName(name="质押期限/备注")
	@Column(name="PLEDGE_TERM_NOTE",length=50)
	private String pledgeTermNote;
		
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

	public String getFinancialNumber() {
		return financialNumber;
	}

	public void setFinancialNumber(String financialNumber) {
		this.financialNumber = financialNumber;
	}

	public String getEquityNumber() {
		return equityNumber;
	}

	public void setEquityNumber(String equityNumber) {
		this.equityNumber = equityNumber;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getPledgeRegNum() {
		return pledgeRegNum;
	}

	public void setPledgeRegNum(String pledgeRegNum) {
		this.pledgeRegNum = pledgeRegNum;
	}

	public String getPledgeEquCom() {
		return pledgeEquCom;
	}

	public void setPledgeEquCom(String pledgeEquCom) {
		this.pledgeEquCom = pledgeEquCom;
	}

	public String getPledgor() {
		return pledgor;
	}

	public void setPledgor(String pledgor) {
		this.pledgor = pledgor;
	}

	public String getPledgedEquAmount() {
		return pledgedEquAmount;
	}

	public void setPledgedEquAmount(String pledgedEquAmount) {
		this.pledgedEquAmount = pledgedEquAmount;
	}

	public String getPledgeTermNote() {
		return pledgeTermNote;
	}

	public void setPledgeTermNote(String pledgeTermNote) {
		this.pledgeTermNote = pledgeTermNote;
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
