package com.tenwa.leasing.entity.contract;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;

import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetail;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlan;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanBefore;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanBefore;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.proj.ProjInfo;



/**
 * 
 * @author 李超杰
 * @date 2014-11-25下午04:31:01
 * @info 合同变更信息
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合同信息表")
@Table(name = "ONHIRE_CONTRACT_CHANGE_INFO")
public class OnhireContractChangeInfo {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="ID")
	private String id;

	@FieldName(name = "合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "变更时间")
	@Column(name = "CHANGE_DATE", length = 20)
	private String changeDate;
	
	@FieldName(name = "变更前业务合同号")
	@Column(name = "BEFORE_CONTRACT_NUMBER", length = 50)
	private String beforeContractNumber;
	
	@FieldName(name = "变更后业务合同号")
	@Column(name = "AFTER_CONTRACT_NUMBER", length = 50)
	private String afterContractNumber;
	
	@ManyToOne
	@FieldName(name = "变更前承租人")
	@JoinColumn(name = "BEFORE_CUST_ID")
	private CustInfo beforeCustId;
	
	@ManyToOne
	@FieldName(name = "变更后承租人")
	@JoinColumn(name = "AFTER_CUST_ID")
	private CustInfo afterCustId;
	
	@FieldName(name = "变更前内容")
	@Column(name = "BEFORE_CHANGE_CONTENT", length = 2000)
	private String beforeChangeContent;
	
	@FieldName(name = "变更后内容")
	@Column(name = "AFTER_CHANGE_CONTENT", length = 2000)
	private String afterChangeContent; 

	@FieldName(name = "变更说明")
	@Column(name = "CHANGE_INSTRUCTION", length = 2000)
	private String changeInstruction;
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;

	@FieldName(name = "租赁物件历史")
	@OneToMany(mappedBy = "onhireContractChangeId", fetch = FetchType.LAZY)
	private Set<ContractEquipHis> contractEquipsHis = new HashSet<ContractEquipHis>();
	
	@FieldName(name = "担保人信息历史")
	@OneToMany(mappedBy = "onhireContractChangeId", fetch = FetchType.LAZY)
	private Set<ContractGuaranteeMethodHis> contractGuaranteeMethodsHis = new HashSet<ContractGuaranteeMethodHis>();
	
	@FieldName(name = "抵押物信息历史")
	@OneToMany(mappedBy = "onhireContractChangeId", fetch = FetchType.LAZY)
	private Set<ContractGuaranteeEquipHis> contractGuaranteeEquipsHis = new HashSet<ContractGuaranteeEquipHis>();
	
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

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public String getBeforeContractNumber() {
		return beforeContractNumber;
	}

	public void setBeforeContractNumber(String beforeContractNumber) {
		this.beforeContractNumber = beforeContractNumber;
	}

	public String getAfterContractNumber() {
		return afterContractNumber;
	}

	public void setAfterContractNumber(String afterContractNumber) {
		this.afterContractNumber = afterContractNumber;
	}

	public CustInfo getBeforeCustId() {
		return beforeCustId;
	}

	public void setBeforeCustId(CustInfo beforeCustId) {
		this.beforeCustId = beforeCustId;
	}

	public CustInfo getAfterCustId() {
		return afterCustId;
	}

	public void setAfterCustId(CustInfo afterCustId) {
		this.afterCustId = afterCustId;
	}
	
	public String getBeforeChangeContent() {
		return beforeChangeContent;
	}

	public void setBeforeChangeContent(String beforeChangeContent) {
		this.beforeChangeContent = beforeChangeContent;
	}

	public String getAfterChangeContent() {
		return afterChangeContent;
	}

	public void setAfterChangeContent(String afterChangeContent) {
		this.afterChangeContent = afterChangeContent;
	}

	public String getChangeInstruction() {
		return changeInstruction;
	}

	public void setChangeInstruction(String changeInstruction) {
		this.changeInstruction = changeInstruction;
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

	public Set<ContractEquipHis> getContractEquipsHis() {
		return contractEquipsHis;
	}

	public void setContractEquipsHis(Set<ContractEquipHis> contractEquipsHis) {
		this.contractEquipsHis = contractEquipsHis;
	}

	public Set<ContractGuaranteeMethodHis> getContractGuaranteeMethodsHis() {
		return contractGuaranteeMethodsHis;
	}

	public void setContractGuaranteeMethodsHis(
			Set<ContractGuaranteeMethodHis> contractGuaranteeMethodsHis) {
		this.contractGuaranteeMethodsHis = contractGuaranteeMethodsHis;
	}

	public Set<ContractGuaranteeEquipHis> getContractGuaranteeEquipsHis() {
		return contractGuaranteeEquipsHis;
	}

	public void setContractGuaranteeEquipsHis(
			Set<ContractGuaranteeEquipHis> contractGuaranteeEquipsHis) {
		this.contractGuaranteeEquipsHis = contractGuaranteeEquipsHis;
	}
}
