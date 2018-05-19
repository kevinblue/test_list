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
 * @date 2013-3-4下午04:31:01
 * @info 合同变更信息
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合同信息表")
@Table(name = "CONTRACT_CHANGE_INFO")
public class ContractChangeInfo {

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
	
	@FieldName(name = "变更时间/终止时间")
	@Column(name = "CHANGE_DATE", length = 200)
	private String changeDate;
	
	@FieldName(name = "变更前内容/终止原因")
	@Column(name = "BEFORE_CHANGE_CONTENT", length = 2000)
	private String beforeChangeContent;
	
	@FieldName(name = "变更后内容")
	@Column(name = "AFTER_CHANGE_CONTENT", length = 2000)
	private String afterChangeContent; 

	@FieldName(name = "变更说明/备注")
	@Column(name = "CHANGE_INSTRUCTION", length = 2000)
	private String changeInstruction;
	

	@FieldName(name = "流程的ID")
	@Column(name = "FLOW_UNID", length = 200)
	private String flowUnid;
	
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

	public String getFlowUnid() {
		return flowUnid;
	}

	public void setFlowUnid(String flowUnid) {
		this.flowUnid = flowUnid;
	}

	
	
}
