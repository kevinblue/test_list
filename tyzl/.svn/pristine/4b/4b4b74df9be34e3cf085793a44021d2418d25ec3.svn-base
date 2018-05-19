package com.reckon.bean;

import com.reckon.commons.util.DateTools;



/**
 * 租金测算时有一个叫做资金收付计划的东东，就是这个了
 * 
 * @author mhy(海洋)
 *
 */
public class FundPlanBean implements Comparable<FundPlanBean>{
	
	private String id;
	private String docId;
	private String contractId;
	private String paymentId;
	private String planDate;
	private String planMoney;
	private String feeTypeName;
	private String feeType;
	private String payTypeName;
	private String payType;
	private String payObj;
	private String settleMethod;
	private String settleMethodName;
	private String creator;
	private String createDate;
	private String modificator;
	private String modifyDate;
	private String payCust ;
	private String payCustName;
	private String fieldName;//对应的资金类别的名称
	//sea 新增字段
	private String isPrePay;//是否预付款 

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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(String planMoney) {
		this.planMoney = planMoney;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getFeeTypeName() {
		return feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getPayObj() {
		return payObj;
	}

	public void setPayObj(String payObj) {
		this.payObj = payObj;
	}

	public String getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(String settleMethod) {
		this.settleMethod = settleMethod;
	}

	public String getSettleMethodName() {
		return settleMethodName;
	}

	public void setSettleMethodName(String settleMethodName) {
		this.settleMethodName = settleMethodName;
	}
	
	/**
	  * <p>GET是否预付款。</p>
	  * @author sea
	  * @return
	 */
	public String getIsPrePay() {
		return isPrePay;
	}

	/**
	 * <p>SET是否预付款。</p>
	 * @author sea
	 * @return
	 */
	public void setIsPrePay(String isPrePay) {
		this.isPrePay = isPrePay;
	}
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModificator() {
		return modificator;
	}

	public void setModificator(String modificator) {
		this.modificator = modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
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

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public int compareTo(FundPlanBean o) {
		try {
			long dateDiff = DateTools.getDateDiff(o.getPlanDate(),this.getPlanDate());
			if(dateDiff > 0){
				return 1;
			} else if(dateDiff < 0){
				return -1;
			} else {
				if(this.getPayType().equals(o.getPayType())){
					return (int)(Double.parseDouble(planMoney) - Double.parseDouble(o.getPlanMoney()));
				} else if("pay_type_in".equals(this.getPayType())){
					return -1;
				} else if("pay_type_out".equals(this.getPayType())){
					return 1;
				} else {
					return 0;
				}
			}
		} catch (Exception e) {
			return 0;
		}
	}
}
