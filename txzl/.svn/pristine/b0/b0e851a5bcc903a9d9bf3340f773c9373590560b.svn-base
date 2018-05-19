package com.reckon.bean;

import java.math.BigDecimal;

import com.tenwa.business.entity.User;

public class AdjustBean {

	private String id;
	private String docId;//流程文档号
	private String contractId;//合同号
	private String contractInfoId;//合同信息表UUID
	private String onHireId;                            
	
	private int startList;//调整开始期项
	private String paydayAdjust;//合同中途终止情况下，该字段表示约定终止日；变更情况下，该字段表示变更开始日期
	private String payMoneyday;//合同中途终止情况下，该字段表示结算偿还金支付日
	private Double discussrent;//合同中途终止情况下，该字段表示商定租金
	private String incomeNumberYear;
	private Double handlingCharge;//表示变更或者提前终止所收取的手续费，没有默认为0
	private Integer adjustList;//租金变更情况下，该字段表示变更后总期项
	private BigDecimal yearRate;//合同中途终止情况下，该字段表示折现年利率；变更情况下，该字段表示变更年利率；
	private Double interestHandlingCharge;
	private Double corpusOverage;//合同中途终止情况下，该字段表示未到期业务本金
	private Double finacorpusOverage;//合同中途终止情况下，该字段表示未到期财务本金
	private String fundputstr;//合同中途终止情况下，该字段表示页面投放json字符串
	
	private String creator;
	private String createDate;
	private String modificator;
	private String modifyDate;
	
	private Double dunRent;
	private Double dunPenalty;
	private Double agreedPenalty;//商定罚息
	private Double agreed_interest;//商定利息
	
	private Double otherOut;//其它支出
	private Double otherIn;//其它收入
	
	private String assetOwnership;
	private Double contractTotal;
	
	private String adjustType;//调整类型

	public AdjustBean() {
		
	}

	public AdjustBean(String docId) {
		this.docId = docId;
	}

	@Override
	public String toString() {
		String toStr = "AdjustBean的值为:";
		toStr += "\nPk_clum_v1:" + this.getContractId();
		toStr += "\nPk_clum_v2:" + this.getDocId();
		toStr += "\nid:" + this.getId();
		toStr += "\ndocId:" + this.getDocId();
		toStr += "\nonHireId:" + this.getOnHireId();
		toStr += "\ncontractId:" + this.getContractId();
		toStr += "\nadjustType:" + this.getAdjustType();
		toStr += "\nstartList:" + this.getStartList();
		toStr += "\npaydayAdjust:" + this.getPaydayAdjust();
		toStr += "\npayMoneyday:" + this.getPayMoneyday();
		toStr += "\ndiscussrent:" + this.getDiscussrent();
		toStr += "\nhandlingCharge:" + this.getHandlingCharge();
		toStr += "\nadjustList:" + this.getAdjustList();
		toStr += "\nyearRate:" + this.getYearRate();
		toStr += "\ninterestHandlingCharge:" + this.getInterestHandlingCharge();
		toStr += "\ncorpusOverage:" + this.getCorpusOverage();
		toStr += "\nfinacorpusOverage:" + this.getFinacorpusOverage();//合同终止:未到期财务本金
		toStr += "\ndunRent:" + this.getDunRent();
		toStr += "\ndunPenalty:" + this.getDunPenalty();
		toStr += "\nagreedPenalty:" + this.getAgreedPenalty();
		toStr += "\notherOut:" + this.getOtherOut();
		toStr += "\notherIn:" + this.getOtherIn();
		toStr += "\nassetOwnership:" + this.getAssetOwnership();
		toStr += "\ncontractTotal:" + this.getContractTotal();
		toStr += "\ncreateDate:" + this.getCreateDate();
		toStr += "\nmodificator:" + this.getModificator();
		toStr += "\nmodifyDate:" + this.getModifyDate();
		return toStr;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocId() {
		return this.docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public int getStartList() {
		return this.startList;
	}

	public void setStartList(int startList) {
		this.startList = startList;
	}

	public String getPaydayAdjust() {
		return this.paydayAdjust;
	}

	public void setPaydayAdjust(String paydayAdjust) {
		this.paydayAdjust = paydayAdjust;
	}

	public Double getHandlingCharge() {
		return this.handlingCharge;
	}

	public void setHandlingCharge(Double handlingCharge) {
		this.handlingCharge = handlingCharge;
	}

	public Integer getAdjustList() {
		return this.adjustList;
	}

	public void setAdjustList(Integer adjustList) {
		this.adjustList = adjustList;
	}
	/**
	  * <p>GET合同中途终止情况下，该字段表示折现年利率。</p>
	  * <p>GET租金计划变更情况下，该字段表示变更的新年利率。</p>
	  * <p>GET其它情况请在这里添加注释说明。</p>
	  * @author sea
	  * @return
	 */
	public BigDecimal getYearRate() {
		return this.yearRate;
	}

	/**
	 * <p>SET合同中途终止情况下，该字段表示折现年利率。</p>
	 * <p>SET租金计划变更情况下，该字段表示变更的新年利率。</p>
	 * <p>SET其它情况请在这里添加注释说明。</p>
	 * @author sea
	 * @return
	 */
	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public Double getInterestHandlingCharge() {
		return this.interestHandlingCharge;
	}

	public void setInterestHandlingCharge(Double interestHandlingCharge) {
		this.interestHandlingCharge = interestHandlingCharge;
	}

	public Double getCorpusOverage() {
		return this.corpusOverage;
	}

	public void setCorpusOverage(Double corpusOverage) {
		this.corpusOverage = corpusOverage;
	}

	public String getIncomeNumberYear() {
		return incomeNumberYear;
	}

	public void setIncomeNumberYear(String incomeNumberYear) {
		this.incomeNumberYear = incomeNumberYear;
	}

	public Double getDunRent() {
		return dunRent;
	}

	public void setDunRent(Double dunRent) {
		this.dunRent = dunRent;
	}

	public Double getDunPenalty() {
		return dunPenalty;
	}

	public void setDunPenalty(Double dunPenalty) {
		this.dunPenalty = dunPenalty;
	}

	public Double getAgreedPenalty() {
		return agreedPenalty;
	}

	public void setAgreedPenalty(Double agreedPenalty) {
		this.agreedPenalty = agreedPenalty;
	}

	public Double getOtherOut() {
		return otherOut;
	}

	public void setOtherOut(Double otherOut) {
		this.otherOut = otherOut;
	}

	public Double getOtherIn() {
		return otherIn;
	}

	public void setOtherIn(Double otherIn) {
		this.otherIn = otherIn;
	}

	public String getAssetOwnership() {
		return assetOwnership;
	}

	public void setAssetOwnership(String assetOwnership) {
		this.assetOwnership = assetOwnership;
	}

	public Double getContractTotal() {
		return contractTotal;
	}

	public void setContractTotal(Double contractTotal) {
		this.contractTotal = contractTotal;
	}

	public String getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	public Double getAgreedInterest() {
		return agreed_interest;
	}

	public void setAgreedInterest(Double agreed_interest) {
		this.agreed_interest = agreed_interest;
	}

	public String getOnHireId() {
		return onHireId;
	}

	public void setOnHireId(String onHireId) {
		this.onHireId = onHireId;
	}

	public void setCreator(User user) {
		creator = user.getId();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String create_date) {
		this.createDate = create_date;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setModificator(User user) {
		modificator = user.getId();
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

	public void setModifyDate(String modify_date) {
		this.modifyDate = modify_date;
	}

	public String getContractInfoId() {
		if(contractInfoId != null){
			return contractInfoId;
		} else {
			return contractId;
		}
	}

	public void setContractInfoId(String contractInfoId) {
		this.contractInfoId = contractInfoId;
	}
	/**
	  * <p>GET合同中途终止情况下，该字段表示结算偿还金支付日。</p>
	  * @author sea
	  * @return
	 */
	public String getPayMoneyday() {
		return payMoneyday;
	}

	/**
	 * <p>SET合同中途终止情况下，该字段表示结算偿还金支付日。</p>
	 * @author sea
	 * @return
	 */
	public void setPayMoneyday(String payMoneyday) {
		this.payMoneyday = payMoneyday;
	}
	/**
	  * <p>GET合同中途终止情况下，该字段表示商定租金。</p>
	  * @author sea
	  * @return
	 */
	public Double getDiscussrent() {
		return discussrent;
	}

	/**
	 * <p>SET合同中途终止情况下，该字段表示商定租金。</p>
	 * @author sea
	 * @return
	 */
	public void setDiscussrent(Double discussrent) {
		this.discussrent = discussrent;
	}
	
	/**
	  * <p>GET合同中途终止情况下，该字段表示未到期财务本金。</p>
	  * @author sea
	  * @return
	 */
	public Double getFinacorpusOverage() {
		return finacorpusOverage;
	}

	/**
	 * <p>SET合同中途终止情况下，该字段表示未到期财务本金。</p>
	 * @author sea
	 * @return
	 */
	public void setFinacorpusOverage(Double finacorpusOverage) {
		this.finacorpusOverage = finacorpusOverage;
	}

	public String getFundputstr() {
		return fundputstr;
	}

	public void setFundputstr(String fundputstr) {
		this.fundputstr = fundputstr;
	}

	public Double getAgreed_interest() {
		return agreed_interest;
	}

	public void setAgreed_interest(Double agreed_interest) {
		this.agreed_interest = agreed_interest;
	}
	
	
}
