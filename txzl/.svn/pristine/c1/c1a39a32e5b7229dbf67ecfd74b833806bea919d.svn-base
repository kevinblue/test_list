
package com.tenwa.leasing.entity.lawmng;


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
@Table(name = "LAW_COST")
@FieldName(name="法务费用申请流程信息 【url: workflow/forms/law_mng/law_cost/law_cost_open_list.bi】")
public class LawCost implements java.io.Serializable {

	private static final long serialVersionUID = 3141494191853495745L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	
	@FieldName(name = "合同编号")
	@ManyToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "法务申请信息")
	@ManyToOne
	@JoinColumn(name = "LAW_ID")
	private LawApproval lawId;
	
	@Column(name = "LAWNUM", length =100)
	@FieldName(name="法务编号")
	private String lawnum;
	
	@Column(name = "COSTTYPE", length = 50)
	@FieldName(name="费用类型")
	private String costtype;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "费用类型")
	@JoinColumn(name = "FEETYPE")
	private DictionaryData feeType;
	
	@Column(name = "LAWDATE",length = 100)
	@FieldName(name="支付日期")
	private String lawdate;

	@Column(name = "LAWMONEY",length = 100)
	@FieldName(name="金额")
	private String lawmoney;
	
	@Column(name = "IS_TRASFER",length = 100)
	@FieldName(name="是否票据移交")
	private String isTrasfer;
	
	@FieldName(name="票据移交日期")
	@Column(name = "TRANSFER_DATE",length = 100)
	private String transferDate;
	
	@FieldName(name="我司承担")
	@Column(name = "MINE_AFFORD",length = 100)
	private String mineAfford;
	
	@FieldName(name="对方承担")
	@Column(name = "OPPOSITE_AFFORD",length = 100)
	private String oppositeAfford;
	
	@FieldName(name="对方实付")
	@Column(name = "OPPOSITE_PAYED",length = 100)
	private String oppositePayed;
	
	@FieldName(name="法院退费日")
	@Column(name = "COURT_BACK_DATE",length = 100)
	private String courtBackDate;
	
	@FieldName(name="退费金额")
	@Column(name = "COURT_BACK_AMT",length = 100)
	private String courtBackAmt;
	
	@FieldName(name="法院应退")
	@Column(name = "COURT_BACK_PLAN",length = 100)
	private String courtBackPlan;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "支付方式")
	@JoinColumn(name = "PAY_TYPE")
	private DictionaryData payType;
	
	@Column(name = "MEMO",length = 4000)
	@FieldName(name="备注")
	private String memo;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
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

	public String getCosttype() {
		return costtype;
	}

	public void setCosttype(String costtype) {
		this.costtype = costtype;
	}

	public String getLawdate() {
		return lawdate;
	}

	public void setLawdate(String lawdate) {
		this.lawdate = lawdate;
	}

	public String getLawmoney() {
		return lawmoney;
	}

	public void setLawmoney(String lawmoney) {
		this.lawmoney = lawmoney;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
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

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLawnum() {
		return lawnum;
	}

	public void setLawnum(String lawnum) {
		this.lawnum = lawnum;
	}

	public LawApproval getLawId() {
		return lawId;
	}

	public void setLawId(LawApproval lawId) {
		this.lawId = lawId;
	}

	public String getIsTrasfer() {
		return isTrasfer;
	}

	public void setIsTrasfer(String isTrasfer) {
		this.isTrasfer = isTrasfer;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	public String getMineAfford() {
		return mineAfford;
	}

	public void setMineAfford(String mineAfford) {
		this.mineAfford = mineAfford;
	}

	public String getOppositeAfford() {
		return oppositeAfford;
	}

	public void setOppositeAfford(String oppositeAfford) {
		this.oppositeAfford = oppositeAfford;
	}

	public String getOppositePayed() {
		return oppositePayed;
	}

	public void setOppositePayed(String oppositePayed) {
		this.oppositePayed = oppositePayed;
	}

	public String getCourtBackDate() {
		return courtBackDate;
	}

	public void setCourtBackDate(String courtBackDate) {
		this.courtBackDate = courtBackDate;
	}

	public String getCourtBackAmt() {
		return courtBackAmt;
	}

	public void setCourtBackAmt(String courtBackAmt) {
		this.courtBackAmt = courtBackAmt;
	}

	public String getCourtBackPlan() {
		return courtBackPlan;
	}

	public void setCourtBackPlan(String courtBackPlan) {
		this.courtBackPlan = courtBackPlan;
	}

	public DictionaryData getPayType() {
		return payType;
	}

	public void setPayType(DictionaryData payType) {
		this.payType = payType;
	}

	public DictionaryData getFeeType() {
		return feeType;
	}

	public void setFeeType(DictionaryData feeType) {
		this.feeType = feeType;
	}

}