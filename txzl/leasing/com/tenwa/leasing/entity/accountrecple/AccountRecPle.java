package com.tenwa.leasing.entity.accountrecple;

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
 * @info 应收账款质押
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "应收账款质押")
@Table(name="ACCOUNT_REC_PLE")
public class AccountRecPle {
	
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
	@Column(name="FINANCIAL_CON_NUM", length=50)
	private String financialConNum;
	
	@FieldName(name="应收账款质押登记合同编号")
	@Column(name="ACCOUNTS_REC_NUM",length=50)
	private String accountsRecNum;
	
	@FieldName(name="填表人归档号")
	@Column(name="PREPARER_NUM",length=50)
	private String preparerNum;
		
	
	@FieldName(name="合同金额/租金总额")
	@Column(name="AMOUNT",length=50)
	private String amount;
	
	@FieldName(name="财产描述")
	@Column(name="PROPERTY_DES",length=50)
	private String propertyDes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REGIS_TYPE_ID")
	@FieldName(name="登记类型")
	private DictionaryData regisTypeId;
	
	@FieldName(name="登记证明编号")
	@Column(name="REGIS_NUM",length=50)
	private String regisNum;
	
	@FieldName(name="登记时间")
	@Column(name="REGIS_TIME",length=50)
	private String regisTime;
	
	@FieldName(name="填表人")
	@Column(name="PREPARER",length=50)
	private String preparer;
	
	@FieldName(name="债务履行期限（起）")
	@Column(name="DEBT_START",length=50)
	private String debtStart;
	
	@FieldName(name="债务履行期限（止）")
	@Column(name="DEBT_END",length=50)
	private String debtEnd;
	
	@FieldName(name="登记到期日")
	@Column(name="REGIST_DATE",length=50)
	private String  registDate;
		
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

	public String getFinancialConNum() {
		return financialConNum;
	}

	public void setFinancialConNum(String financialConNum) {
		this.financialConNum = financialConNum;
	}

	

	public String getAccountsRecNum() {
		return accountsRecNum;
	}

	public void setAccountsRecNum(String accountsRecNum) {
		this.accountsRecNum = accountsRecNum;
	}

	public String getPreparerNum() {
		return preparerNum;
	}

	public void setPreparerNum(String preparerNum) {
		this.preparerNum = preparerNum;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPropertyDes() {
		return propertyDes;
	}

	public void setPropertyDes(String propertyDes) {
		this.propertyDes = propertyDes;
	}

	public DictionaryData getRegisTypeId() {
		return regisTypeId;
	}

	public void setRegisTypeId(DictionaryData regisTypeId) {
		this.regisTypeId = regisTypeId;
	}

	public String getRegisNum() {
		return regisNum;
	}

	public void setRegisNum(String regisNum) {
		this.regisNum = regisNum;
	}

	public String getRegisTime() {
		return regisTime;
	}

	public void setRegisTime(String regisTime) {
		this.regisTime = regisTime;
	}

	public String getPreparer() {
		return preparer;
	}

	public void setPreparer(String preparer) {
		this.preparer = preparer;
	}

	public String getDebtStart() {
		return debtStart;
	}

	public void setDebtStart(String debtStart) {
		this.debtStart = debtStart;
	}

	public String getDebtEnd() {
		return debtEnd;
	}

	public void setDebtEnd(String debtEnd) {
		this.debtEnd = debtEnd;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
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
