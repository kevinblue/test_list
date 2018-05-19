package com.tenwa.leasing.entity.contract;

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

/**
 * 
 * @author Toybaby
 * @date 2014-12-02下午18:56:22
 * @info 合同结束信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同结束信息")
@Table(name="CONTRACT_END_INFO")
public class ContractEndInfo {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;

	
	@FieldName(name="合同结束接收时间")
	@Column(name="ACTUAl_END_DATE",length=20)
	private String actualENdDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "合同结束类型")
	@JoinColumn(name = "END_TYPE")
	private DictionaryData endType;
	
	@FieldName(name="结束收益率")
	@Column(name="END_IRR", precision = 22)
	private BigDecimal end_irr; 
	
	@FieldName(name="合同结束备注")
	@Column(name="END_MEMO",length=2000)
	private String endMemo;
	
	
	@FieldName(name="合同结束转移价格")
	@Column(name="END_PRICE",precision=22,scale=2)
	private BigDecimal endPrice;
	
	@FieldName(name="合同结束接收方名称")
	@Column(name="END_CUST",length=100)
	private String endCust;
	
	@FieldName(name="合同结束接收时间")
	@Column(name="END_DATE",length=20)
	private String endDate;
	
	@FieldName(name="安全性总结")
	@Column(name="SAFETY_MEMO",length=2000)
	private String safetyMemo;
	
	@FieldName(name="收益性总结")
	@Column(name="INCOME_MEMO",length=2000)
	private String incomeMemo;
	
	@FieldName(name="流动性总结")
	@Column(name="FLOW_MEMO",length=2000)
	private String flowMemo;
	
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndMemo() {
		return endMemo;
	}

	public void setEndMemo(String endMemo) {
		this.endMemo = endMemo;
	}

	
	public String getActualENdDate() {
		return actualENdDate;
	}

	public void setActualENdDate(String actualENdDate) {
		this.actualENdDate = actualENdDate;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public String getEndCust() {
		return endCust;
	}

	public void setEndCust(String endCust) {
		this.endCust = endCust;
	}

	public String getSafetyMemo() {
		return safetyMemo;
	}

	public void setSafetyMemo(String safetyMemo) {
		this.safetyMemo = safetyMemo;
	}
	
	

	public BigDecimal getEnd_irr() {
		return end_irr;
	}

	public void setEnd_irr(BigDecimal endIrr) {
		end_irr = endIrr;
	}

	public String getIncomeMemo() {
		return incomeMemo;
	}

	public void setIncomeMemo(String incomeMemo) {
		this.incomeMemo = incomeMemo;
	}

	public String getFlowMemo() {
		return flowMemo;
	}

	public void setFlowMemo(String flowMemo) {
		this.flowMemo = flowMemo;
	}

	
	
	public DictionaryData getEndType() {
		return endType;
	}

	public void setEndType(DictionaryData endType) {
		this.endType = endType;
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
