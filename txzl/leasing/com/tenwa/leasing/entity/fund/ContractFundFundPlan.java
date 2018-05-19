package com.tenwa.leasing.entity.fund;

import java.math.BigDecimal;
import java.util.HashMap;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.reckon.bean.FundPlan;
import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.paymentinterface.PaymentLog;


/**
 * 
 * @author 孙传良
 * @date 2013-3-7下午12:05:49
 * @info 合同资金计划表
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合同资金计划表")
@Table(name = "CONTRACT_FUND_FUND_PLAN")
public class ContractFundFundPlan implements FundPlan {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "收付编号")
	@Column(name = "PAYMENT_ID", length = 32)
	private String paymentId;

	@FieldName(name = "流程编号")
	@Column(name = "DOC_ID")
	private String docId;

	@FieldName(name = "合同编号")
	@ManyToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;

	
	@ManyToOne
	@FieldName(name = "设备名称")
	@JoinColumn(name = "DEVICE_NAME")
	private DictionaryData devicename;
	
	@ManyToOne
	@FieldName(name = "费用类型")
	@JoinColumn(name = "FEE_TYPE")
	private DictionaryData feeType;
	
	@ManyToOne
	@FieldName(name="设备款类型")
	@JoinColumn(name="FUND_TYPE")
	private DictionaryData fundType;

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
	@FieldName(name = "收付对象")
	@JoinColumn(name = "PAY_CUST")
	private CustInfo payCust;

	@ManyToOne
	@FieldName(name="结算方式[D]")
	@JoinColumn(name="SETTLE_METHOD")
	private DictionaryData settleMethod;
	
	@ManyToOne
	@FieldName(name="支付条件")
	@JoinColumn(name="PAY_CONDITION")
	private DictionaryData payCondition;
	
	@FieldName(name="是否抵扣")
	@Column(name="WHETHER_DEDUCT")
	private String   whetherDeduct;
	
	@FieldName(name = "是否预付款")
	@Column(name = "IS_PREPAY")
	private String isPrePay;
	
	
	@ManyToOne
	@FieldName(name = "收付类型")
	@JoinColumn(name = "PAY_TYPE")
	private DictionaryData payType;

	@Transient
	@FieldName(name = "状态")
	private DictionaryData planMoneyStatus;

	@Transient
	@FieldName(name = "已收款")
	private BigDecimal receiverMoney;

	@Transient
	@FieldName(name = "未收款")
	private BigDecimal planbalance;
	
	@Transient
	@FieldName(name="调整")
	private BigDecimal adjustMoney;
	
	@FieldName(name="备注")
	@Column(name="FPNOTE")
	private String   fpnote;
	
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
   
	@FieldName(name = "流程状态")
	@Column(name = "WORK_FLAG", length = 2, columnDefinition = "INT DEFAULT 0")
	private Integer workFlag;
	
	//设置作废字段,默认为0-不作废,1-作废
	@FieldName(name="是否作废")
	@Column(name="INVALID",length=2)
	private String invalid="0";
	
	
	@FieldName(name = "资金收款信息")
	@OneToMany(mappedBy = "fundFundChargePlan", fetch = FetchType.LAZY)
	private Set<ContractFundFundCharge> fundFundCharges = new HashSet<ContractFundFundCharge>();
	
	@FieldName(name = "付款日志")
	@OneToOne(mappedBy = "paymentid", fetch = FetchType.LAZY)
	private PaymentLog paymentLog;

	public PaymentLog getPaymentLog() {
		return paymentLog;
	}

	public void setPaymentLog(PaymentLog paymentLog) {
		this.paymentLog = paymentLog;
	}

	@Override
	public boolean equals(Object obj) {
		ContractFundFundPlan cp=(ContractFundFundPlan)obj;
		if(this.getPaymentId().equals(cp.getPaymentId())&&this.getFeeType().equals(cp.getFeeType())&&this.getPayType().equals(cp.getPayType())){
			return true;
		}else{
			return false;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DictionaryData getDevicename() {
		return devicename;
	}

	public void setDevicename(DictionaryData devicename) {
		this.devicename = devicename;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public Set<ContractFundFundCharge> getFundFundCharges() {
		return fundFundCharges;
	}

	public void setFundFundCharges(Set<ContractFundFundCharge> fundFundCharges) {
		this.fundFundCharges = fundFundCharges;
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

	/**
	 * 返回资金收款状态
	 * @return
	 */
	public DictionaryData getPlanMoneyStatus() {
		return planMoneyStatus;
	}

	/**
	 * 获取当条的已收资金
	 * @return 已收资金
	 */
	public BigDecimal getReceiverMoney() {
		return receiverMoney;
	}

	public void setPlanMoneyStatus(DictionaryData planMoneyStatus) {
		this.planMoneyStatus = planMoneyStatus;
	}

	public void setReceiverMoney(BigDecimal receiverMoney) {
		this.receiverMoney = receiverMoney;
	}

	/**
	 * 
	 * @date 2013-4-19 xuyunlong 计算已回笼金额并改变状态
	 * @param payType
	 */
	public void receiverAllMoney(HashMap<String, DictionaryData> payType) {
		// 调整金额也算是实收，2013-8-20 edit by jason

		this.setReceiverMoney(BigDecimal.ZERO);// 已收款
		this.setAdjustMoney(BigDecimal.ZERO);// 调整
		this.setPlanbalance(BigDecimal.ZERO);// 未收款
		BigDecimal palnMoney = this.getPlanMoney();// 计划金额

		BigDecimal fundMoney = BigDecimal.ZERO;// 资金实收款
		BigDecimal adjustMoney = BigDecimal.ZERO;// 资金实调整
		BigDecimal allMoney = BigDecimal.ZERO;// 资金全部收款

		if (this.fundFundCharges.size() > 0) {
			for (ContractFundFundCharge fc : fundFundCharges) {
				fundMoney = fundMoney.add(fc.getFactMoney());
				adjustMoney = adjustMoney.add(fc.getFeeAdjust()==null?BigDecimal.ZERO:fc.getFeeAdjust());
			}
			allMoney = fundMoney.add(adjustMoney);
		}

		this.setReceiverMoney(fundMoney);
		this.setAdjustMoney(adjustMoney);
		this.setPlanbalance(palnMoney.subtract(fundMoney).subtract(adjustMoney));
        String pfix="";
        if(this.getPayType().getCode().equals("pay_type_out")){
        	pfix="P";
        }
		if (allMoney.compareTo(BigDecimal.ZERO) == 0) {
			this.setPlanMoneyStatus(payType.get(("PLANMANYSTATUSNO"+pfix)));// 未回笼
		} else if (allMoney.compareTo(palnMoney) < 0) {
			this.setPlanMoneyStatus(payType.get("PLANMANYSTATUSPART"+pfix));// 部份回笼
		} else if (allMoney.compareTo(palnMoney) == 0) {
			this.setPlanMoneyStatus(payType.get("PLANMANYSTATUSALL")); // 已回笼
		} else {
			this.setPlanMoneyStatus(payType.get("PLANMANYSTATUSOVER")); // 超额回笼
		}

	/*	this.setPlanbalance(palnMoney.subtract(fundMoney));
		this.setReceiverMoney(fundMoney);
		this.setAdjustMoney(adjustMoney);
		this.setPlanMoneyStatus(payType.get("PLANMANYSTATUSNO"));// 未回笼
*/	}

	public Integer getWorkFlag() {
		return workFlag;
	}

	public void setWorkFlag(Integer workFlag) {
		this.workFlag = workFlag;
	}

	/**
	 * 获取当条未收资金
	 * @return 未收资金
	 */
	public BigDecimal getPlanbalance() {
		return planbalance;
	}

	/**
	 * 获取当条已调整值
	 * @return 已调整值
	 */
	public BigDecimal getAdjustMoney() {
		return adjustMoney;
	}

	public void setPlanbalance(BigDecimal planbalance) {
		this.planbalance = planbalance;
	}

	public void setAdjustMoney(BigDecimal adjustMoney) {
		this.adjustMoney = adjustMoney;
	}

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}
	
	public DictionaryData getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(DictionaryData settleMethod) {
		this.settleMethod = settleMethod;
	}

	public String getIsPrePay() {
		return isPrePay;
	}

	public void setIsPrePay(String isPrePay) {
		this.isPrePay = isPrePay;
	}

	public CustInfo getPayCust() {
		return payCust;
	}

	public void setPayCust(CustInfo payCust) {
		this.payCust = payCust;
	}

	public DictionaryData getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(DictionaryData payCondition) {
		this.payCondition = payCondition;
	}

	public String getWhetherDeduct() {
		return whetherDeduct;
	}

	public void setWhetherDeduct(String whetherDeduct) {
		this.whetherDeduct = whetherDeduct;
	}

	
	
	public DictionaryData getFundType() {
		return fundType;
	}

	public void setFundType(DictionaryData fundType) {
		this.fundType = fundType;
	}

	@Override
	public int compareTo(FundPlan o) {
		long dateDiff = DateTools.getDateDiff(this.getPlanDate(), o.getPlanDate());
		int netFlowDiff = this.getPlanMoney().compareTo(o.getPlanMoney());
		if(dateDiff == 0){
			return netFlowDiff;
		} else{
			return (int)dateDiff;
		}
	}
}
 
