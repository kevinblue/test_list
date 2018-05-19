package com.reckon.entity.contract.reckon.fund;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;

@Entity
@FieldName(name = "合同付款前提表")
@Table(name = "ConPAYMENT_PREMISE_CONDITION")
public class ContractPaymentPremiseCondition {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "资金收付计划编号")
	@Column(name = "PAYMENT_ID", length = 32)
	private String paymentid;
	
	@FieldName(name = "项目编号")
	@Column(name = "PROJ_NUMBER", length = 32)
	private String projnumber;

	@FieldName(name = "付款节点")
	@Column(name = "PAYMENT_NODE", length = 64)
	private String paymentnode;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@ManyToOne
	@FieldName(name = "费用类型")
	@JoinColumn(name = "FEE_TYPE")
	private DictionaryData feetype;

	@FieldName(name = "前提条件")
	@Column(name = "CONDITION_NAME", length = 200)
	private String conditionname;
		
	@ManyToOne
	@FieldName(name = "设备名称")
	@JoinColumn(name = "DEVICE_NAME")
	private DictionaryData devicename;
	
	
	@FieldName(name = "是否满足")
	@Column(name = "ISMEET", length = 200)
	private String isMeet;
	
	
	@FieldName(name = "条件说明")
	@Column(name = "CONDITIONSTUB", length = 200)
	private String conditionStub;

	@FieldName(name="备注")
	@Column(name="REMARK")
	private String   remark;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
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

	
	
	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}


	public String getProjnumber() {
		return projnumber;
	}

	public void setProjnumber(String projnumber) {
		this.projnumber = projnumber;
	}

	public String getPaymentnode() {
		return paymentnode;
	}

	public void setPaymentnode(String paymentnode) {
		this.paymentnode = paymentnode;
	}



	public DictionaryData getFeetype() {
		return feetype;
	}

	public void setFeetype(DictionaryData feetype) {
		this.feetype = feetype;
	}

	public String getConditionname() {
		return conditionname;
	}

	public void setConditionname(String conditionname) {
		this.conditionname = conditionname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	public String getIsMeet() {
		return isMeet;
	}

	public void setIsMeet(String isMeet) {
		this.isMeet = isMeet;
	}

	public String getConditionStub() {
		return conditionStub;
	}

	public void setConditionStub(String conditionStub) {
		this.conditionStub = conditionStub;
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

	public DictionaryData getDevicename() {
		return devicename;
	}

	public void setDevicename(DictionaryData devicename) {
		this.devicename = devicename;
	}
	
	
}
