package com.tenwa.leasing.entity.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;

/**
 * FundEbankProcess entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "网银在进行流程信息")
@Table(name="FUND_EBANK_PROCESS")
public class FundEbankProcess {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="网银编号")
	@ManyToOne(targetEntity=FundEbankData.class,fetch=FetchType.LAZY)
	@JoinColumn(name="EBDATA_ID")
	private FundEbankData ebdataId ;

	@FieldName(name="合同编号")
	@Column(name="CONTRACT_ID")
	private String contractId ;

	@FieldName(name="备用主键1")
	@Column(name="key_One")
	private String keyOne ;
	
	@FieldName(name = "流程实例ID")
	@Column(name="FLOW_UNID")
	private String flowUnid;

	@FieldName(name="占用流程")
	@Column(name="PROCESS_NAME")
	private String   processName;

	@FieldName(name="流程开始日期")
	@Column(name="START_DATE_",length=50)
	private String   startDate;

	@FieldName(name="涉及网银金额")
	@Column(name="PROCESS_AMOUNT",precision=22,scale=2)
	private BigDecimal processAmount;
	
	@FieldName(name="流程状态(1为已使用，-1为红冲,初始化过程表workflag 为3)")
	@Column(name="work_flag")
	private String work_flag;
	
	@FieldName(name="Contract_Fund_Rent_Plan表的主键ID")
	@ManyToOne(targetEntity=ContractFundRentPlan.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PLAN_ID")
	private ContractFundRentPlan planId;
	
	@FieldName(name = "租金或罚息占用[0 or null:租金;1:罚息]")
	@Column(name = "RENT_OR_PENALTY", length = 10)
	private String RentOrPenalty;
	
	@FieldName(name = "本次退还金额")
	@Column(name = "REFUND_AMOUNT", precision = 22, scale = 2)
	private BigDecimal RefundAmount;
	
	@FieldName(name = "本次退还备注")
	@Column(name = "REFUND_SUMMARY", length = 2000)
	private String RefundSummary;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FundEbankData getEbdataId() {
		return ebdataId;
	}

	public void setEbdataId(FundEbankData ebdataId) {
		this.ebdataId = ebdataId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getKeyOne() {
		return keyOne;
	}

	public void setKeyOne(String keyOne) {
		this.keyOne = keyOne;
	}

	public String getFlowUnid() {
		return flowUnid;
	}

	public void setFlowUnid(String flowUnid) {
		this.flowUnid = flowUnid;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getProcessAmount() {
		return processAmount;
	}

	public void setProcessAmount(BigDecimal processAmount) {
		this.processAmount = processAmount;
	}

	public String getWork_flag() {
		return work_flag;
	}

	public void setWork_flag(String workFlag) {
		work_flag = workFlag;
	}

	public BigDecimal getRefundAmount() {
		return RefundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		RefundAmount = refundAmount;
	}

	public String getRefundSummary() {
		return RefundSummary;
	}

	public void setRefundSummary(String refundSummary) {
		RefundSummary = refundSummary;
	}

	public ContractFundRentPlan getPlanId() {
		return planId;
	}

	public void setPlanId(ContractFundRentPlan planId) {
		this.planId = planId;
	}

	public String getRentOrPenalty() {
		return RentOrPenalty;
	}

	public void setRentOrPenalty(String rentOrPenalty) {
		RentOrPenalty = rentOrPenalty;
	}

}