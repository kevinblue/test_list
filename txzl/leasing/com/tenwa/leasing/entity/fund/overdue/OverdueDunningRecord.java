package com.tenwa.leasing.entity.fund.overdue;


import java.math.BigDecimal;

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

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustRelatedPerson;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;


/**
 * 催收记录
 * @Title: OverdueDunningRecord.java
 * @package: com.tenwa.leasing.entity.fund.overdue
 * @author: tpf
 * @date 2014年11月24日 上午10:13:37 
 * @version V5.1
 */
@Entity
@FieldName(name = "催收记录")
@Table(name="OVERDUE_DUNNING_RECORD")
public class OverdueDunningRecord {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "联系日期")
	@Column(name="CONTACT_DATE", length=20)
	private String contactDate;
	
	@ManyToOne
	@FieldName(name = "联系方式")
	@JoinColumn(name="CONTACT_WAY")
	private DictionaryData contactWay;
	
	@FieldName(name = "联系人")
	@Column(name="CONTACT_PERSON",length=20)
	private String contactPerson;
	
	@FieldName(name="承诺还款金额")
	@Column(name="COMMITMENT_MONEY", precision = 22)
	private BigDecimal commitmentMoney;
	
	@FieldName(name = "联系还款日")
	@Column(name="COMMITMENT_DATE", length=20)
	private String commitmentDate;
	
	@FieldName(name = "下次联系日期")
	@Column(name="NEXT_COMMITMENT_DATE", length=20)	
	private String nextCommitmentDate;
	
	@FieldName(name = "联系情况")
	@Column(name="COMMITMENT_INFO", length=2000)
	private String commitmentInfo;
	
	@FieldName(name = "说明")
	@Column(name="INTRODUCE", length = 2000)
	private String introduce;
	
	@ManyToOne
	@FieldName(name = "逾期原因")
	@JoinColumn(name="OVERDUE_REASON")
	private DictionaryData overduereasons;
	
	@ManyToOne
	@FieldName(name = "催款员")
	@JoinColumn(name="DUNNING")
	private User dunning;
	
	@FieldName(name = "租金计划")
	@ManyToOne(targetEntity = ContractFundRentPlan.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID")
	private ContractFundRentPlan planid;
	
	@FieldName(name = "重要个人")
	@ManyToOne(targetEntity = CustRelatedPerson.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	private CustRelatedPerson personid;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getContactDate() {
		return contactDate;
	}

	public void setContactDate(String contactDate) {
		this.contactDate = contactDate;
	}

	public DictionaryData getContactWay() {
		return contactWay;
	}

	public void setContactWay(DictionaryData contactWay) {
		this.contactWay = contactWay;
	}

	public BigDecimal getCommitmentMoney() {
		return commitmentMoney;
	}

	public void setCommitmentMoney(BigDecimal commitmentMoney) {
		this.commitmentMoney = commitmentMoney;
	}

	public String getCommitmentDate() {
		return commitmentDate;
	}

	public void setCommitmentDate(String commitmentDate) {
		this.commitmentDate = commitmentDate;
	}

	public String getNextCommitmentDate() {
		return nextCommitmentDate;
	}

	public void setNextCommitmentDate(String nextCommitmentDate) {
		this.nextCommitmentDate = nextCommitmentDate;
	}

	public String getCommitmentInfo() {
		return commitmentInfo;
	}

	public void setCommitmentInfo(String commitmentInfo) {
		this.commitmentInfo = commitmentInfo;
	}

	public DictionaryData getOverduereasons() {
		return overduereasons;
	}

	public void setOverduereasons(DictionaryData overduereasons) {
		this.overduereasons = overduereasons;
	}

	public User getDunning() {
		return dunning;
	}

	public void setDunning(User dunning) {
		this.dunning = dunning;
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

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	


	public ContractFundRentPlan getPlanid() {
		return planid;
	}

	public void setPlanid(ContractFundRentPlan planid) {
		this.planid = planid;
	}

	public CustRelatedPerson getPersonid() {
		return personid;
	}

	public void setPersonid(CustRelatedPerson personid) {
		this.personid = personid;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
	
}