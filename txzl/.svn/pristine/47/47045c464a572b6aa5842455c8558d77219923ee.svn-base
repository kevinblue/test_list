package com.reckon.entity.contract.reckon.fund;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author zhangc
 * @ClassName: FundFundTemp 
 * @date 2014年12月16日 下午7:58:41 
 * @Description: 测算，资金计划存储的临时表，方便进行现金流的封装
 */
@Entity
@FieldName(name = "资金计划临时表(临时)")
@Table(name="FUND_FUND_PLAN_TEMP")
public class FundFundPlanTemp {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="报价编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name = "收付编号")
	@Column(name = "PAYMENT_ID", length = 32)
	private String paymentId;
	
	@FieldName(name = "合同号")
	@Column(name = "CONTRACT_ID")
	private String contractId;
	
	@FieldName(name = "项目编号")//临时表中不需要针对项目做隐射关联关系，因为这时可能项目信息还没入库了！
	@Column(name = "PROJ_ID")
	private String projId;
	
	@ManyToOne
	@FieldName(name = "费用类型")
	@JoinColumn(name = "FEE_TYPE")
	private DictionaryData feeType;

	@FieldName(name = "计划收付日期")
	@Column(name = "PLAN_DATE", length = 20)
	private String planDate;

	@FieldName(name = "金额")
	@Column(name = "PLAN_MONEY", precision = 22, scale = 2)
	private BigDecimal planMoney;

	@FieldName(name = "付款对象")
	@Column(name = "PAY_OBJ")
	private String payObj;
    
	@ManyToOne
	@FieldName(name="结算方式[D]")
	@JoinColumn(name="SETTLE_METHOD")
	private DictionaryData settleMethod;
	
	@ManyToOne
	@FieldName(name = "收付类型")
	@JoinColumn(name = "PAY_TYPE")
	private DictionaryData payType;
	
	@FieldName(name="备注")
	@Column(name="FPNOTE")
	private String   fpnote;
	
	@FieldName(name = "收付对象")
	@Column(name = "PAY_CUST")
	private String payCust ;
	
	@FieldName(name = "付款对象显示值")
	@Column(name = "PAY_CUST_NAME")
	private String payCustName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public DictionaryData getFeeType() {
		return feeType;
	}

	public void setFeeType(DictionaryData feeType) {
		this.feeType = feeType;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public BigDecimal getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
	}

	public String getPayObj() {
		return payObj;
	}

	public void setPayObj(String payObj) {
		this.payObj = payObj;
	}

	public DictionaryData getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(DictionaryData settleMethod) {
		this.settleMethod = settleMethod;
	}

	public DictionaryData getPayType() {
		return payType;
	}

	public void setPayType(DictionaryData payType) {
		this.payType = payType;
	}

	public String getFpnote() {
		return fpnote;
	}

	public void setFpnote(String fpnote) {
		this.fpnote = fpnote;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getPayCust() {
		return payCust;
	}

	public void setPayCust(String payCust) {
		this.payCust = payCust;
	}

	public String getPayCustName() {
		return payCustName;
	}

	public void setPayCustName(String payCustName) {
		this.payCustName = payCustName;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	
}
