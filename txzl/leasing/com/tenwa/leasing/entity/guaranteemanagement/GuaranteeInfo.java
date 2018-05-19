package com.tenwa.leasing.entity.guaranteemanagement;


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
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author cyw
 * @date 2013-3-4下午09:33:10
 * @info 保函管理信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "保函管理信息")
@Table(name="GUARANTEE_INFO")
public class GuaranteeInfo {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name="项目名称")
	@JoinColumn(name="PROJ_NAME_ID")
	private ProjInfo projNameId;
	
	/**
	 * 新增字段
	 */
	@FieldName(name="采购合同名称")
	@Column(name="CONTRACT_NAME", length=50)
	private String contractName;
	
	/**
	 * 新增字段
	 */
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@FieldName(name = "采购合同编号")
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="保函编号")
	@Column(name="GUARANTEE_ID", length=50)
	private String guaranteeId;//
	
	@FieldName(name="保函类型")
	@Column(name="GUARANTEE_TYPE",length=50)
	private String guaranteeType;
	
	@FieldName(name="金额(元)")
	@Column(name="MONEY_",length=50)
	private String money;
	
	@FieldName(name="保函开立银行")
	@Column(name="OPEN_BANK",length=50)
	private String openBank;
	
	@FieldName(name="相关设备")
	@Column(name="RELATED_EQUIPMENT",length=50)
	private String relatedEquipment;
	
	@FieldName(name="生效日期")
	@Column(name="EFFECTIVE_DATE",length=50)
	private String effectiveDate;
	
	@FieldName(name="失效日期")
	@Column(name="EXPIRY_DATE",length=50)
	private String expiryDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="保函状态")
	@JoinColumn(name="GUARANTEE_STATE_ID")
	private DictionaryData guaranteeStateId;
	
	
	@FieldName(name="是否申请退还")
	@Column(name="APPLY_REFUND",length=50)
	private String applyRefund;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="保函名称")
	@JoinColumn(name="GUARANTEE_NAME_ID")
	private DictionaryData guaranteeNameId;
	
	@FieldName(name="供应商名称")
	@Column(name="SUPPLIIER_NAME",length=50)
	private String supplierName;
	
	@FieldName(name="相关合同")
	@Column(name="RELEVANT_CONTRACT",length=50)
	private String relevantContract;
	
	@FieldName(name="页数")
	@Column(name="PAGES_",length=50)
	private String page;
	
	@FieldName(name="保函位置")
	@Column(name="GUARANTEE_POSITION",length=50)
	private String guaranteePosition;
	
	@FieldName(name="备注")
	@Column(name="NOTE_",length=50)
	private String note;
	
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

	
	public DictionaryData getGuaranteeNameId() {
		return guaranteeNameId;
	}

	public void setGuaranteeNameId(DictionaryData guaranteeNameId) {
		this.guaranteeNameId = guaranteeNameId;
	}

	public String getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getRelatedEquipment() {
		return relatedEquipment;
	}

	public void setRelatedEquipment(String relatedEquipment) {
		this.relatedEquipment = relatedEquipment;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	

	public String getApplyRefund() {
		return applyRefund;
	}

	public void setApplyRefund(String applyRefund) {
		this.applyRefund = applyRefund;
	}

	

	public ProjInfo getProjNameId() {
		return projNameId;
	}

	public void setProjNameId(ProjInfo projNameId) {
		this.projNameId = projNameId;
	}

	public DictionaryData getGuaranteeStateId() {
		return guaranteeStateId;
	}

	public void setGuaranteeStateId(DictionaryData guaranteeStateId) {
		this.guaranteeStateId = guaranteeStateId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getRelevantContract() {
		return relevantContract;
	}

	public void setRelevantContract(String relevantContract) {
		this.relevantContract = relevantContract;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getGuaranteePosition() {
		return guaranteePosition;
	}

	public void setGuaranteePosition(String guaranteePosition) {
		this.guaranteePosition = guaranteePosition;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}


		
}
