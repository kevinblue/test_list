package com.reckon.entity.proj;

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

import com.reckon.bean.FundPlan;
import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author 孙传良
 * @date 2013-3-7下午12:05:49
 * @info 合同资金计划表
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "立项资金计划表")
@Table(name = "PROJ_FUND_FUND_PLAN")
public class ProjFundFundPlan implements FundPlan {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "收付编号")
	@Column(name = "PAYMENT_ID", length = 32)
	private String paymentId;

	@FieldName(name = "项目编号")
	@ManyToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;
	
	//立项阶段没有资金实收
	/*@FieldName(name = "资金收款信息")
	@OneToMany(mappedBy = "fundFundChargePlan", fetch = FetchType.LAZY)
	private Set<FundFundCharge> fundFundCharges = new HashSet<FundFundCharge>();*/

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
	@FieldName(name = "收付对象")
	@JoinColumn(name = "PAY_CUST")
	private CustInfo payCust;
    
	@ManyToOne
	@FieldName(name="结算方式[D]")
	@JoinColumn(name="SETTLE_METHOD")
	private DictionaryData settleMethod;
	
	
	@FieldName(name = "是否预付款")
	@Column(name = "IS_PREPAY")
	private String isPrePay;
	
	@ManyToOne
	@FieldName(name = "收付类型")
	@JoinColumn(name = "PAY_TYPE")
	private DictionaryData payType;
	
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

	public String getId() {
		return id;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public DictionaryData getFeeType() {
		return feeType;
	}

	public String getPlanDate() {
		return planDate;
	}

	public BigDecimal getPlanMoney() {
		return planMoney;
	}

	public String getPayObj() {
		return payObj;
	}

	public DictionaryData getPayType() {
		return payType;
	}


	public String getFpnote() {
		return fpnote;
	}

	public User getCreator() {
		return creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public Integer getWorkFlag() {
		return workFlag;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public void setFeeType(DictionaryData feeType) {
		this.feeType = feeType;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
	}

	public void setPayObj(String payObj) {
		this.payObj = payObj;
	}

	public void setPayType(DictionaryData payType) {
		this.payType = payType;
	}


	public void setFpnote(String fpnote) {
		this.fpnote = fpnote;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setWorkFlag(Integer workFlag) {
		this.workFlag = workFlag;
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

	@Override
	public boolean equals(Object obj) {
		ProjFundFundPlan cp=(ProjFundFundPlan)obj;
		if(this.getPaymentId().equals(cp.getPaymentId())&&this.getFeeType().equals(cp.getFeeType())&&this.getPayType().equals(cp.getPayType())){
			return true;
		}else{
			return false;
		}
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
 
