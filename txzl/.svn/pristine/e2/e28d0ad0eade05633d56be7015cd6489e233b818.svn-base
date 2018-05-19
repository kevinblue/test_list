package com.tenwa.leasing.entity.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


@Entity
@FieldName(name = "承兑汇票信息")
@Table(name="ACCEPTANCE_BILL")
public class AcceptanceBill {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="票据类型")
	@Column(name="BILL_TYPE",length=50)
	private String billType;
	
	@FieldName(name="票据编号")
	@Column(name="BILL_NUM",length=50)
	private String billNum;

	@FieldName(name="新票据编号")
	@Column(name="NEW_BILL_NUM",length=50)
	private String newBillNum;
	
	@FieldName(name="旧票据编号")
	@Column(name="OLD_BILL_NUM",length=50)
	private String oldBillNum;
	
	@FieldName(name="出票日期")
	@Column(name="ISSUE_DATE", length=20)	
	private String issueDate;
	
	@FieldName(name="出款人全称")
	@Column(name="DRAWEE_NAME",length=50)
	private String Draweename;
	
	@FieldName(name="收款人全称")
	@Column(name="PAYEE_NAME",length=50)
	private String Payeename;
	
	@FieldName(name="到期日期")
	@Column(name="DUE_DATE", length=20)	
	private String dueDate;
	
	@FieldName(name="承兑人账号")
	@Column(name="ACCEPTOR_NUMBER",length=50)
	private String acceptorNumber;

	@FieldName(name="承兑人开户银行")
	@Column(name="ACCEPTOR_BANK",length=50)
	private String acceptorBank;

	@FieldName(name="承兑人名称")
	@Column(name="ACCEPTOR_NAME",length=50)
	private String acceptorName;
	
	@FieldName(name = "票面金额")
	@Column(name = "FACE_AMOUNT", precision = 22, scale = 2)
	private BigDecimal faceAmount;
	
	@FieldName(name="票据来源")
	@Column(name="BILL_RESOURCE",length=50)
	private String billResource;
	
	@FieldName(name="买入日期")
	@Column(name="BUY_DATE", length=20)	
	private String buyDate;
	
	@FieldName(name="当前持票人")
	@Column(name="CURRENT_HOLDER",length=50)
	private String currentHolder;
	
	@FieldName(name="上一前手")
	@Column(name="PREVIOUS_HOLDER",length=50)
	private String previousHolder;
	
	@FieldName(name="前手")
	@Column(name="REMOTE_HOLDER",length=50)
	private String remoteHolder;
	
	@FieldName(name="买入时的合同号")
	@Column(name="BUY_CONTRACT_NUM",length=50)
	private String buyContractNum;
	
	@FieldName(name="买入时的通知单号")
	@Column(name="BUY_NOTICE_NUM",length=50)
	private String buyNoticeNum;
	
	@FieldName(name="买入时的在途天数")
	@Column(name="BUY_TRANSIT_DAYS",length=50)
	private Integer buyTransitDays;

	@FieldName(name = "买入时的贴现率")
	@Column(name = "BUY_DISCOUNT_RATE", precision = 22, scale = 2)
	private BigDecimal buyDiscountRate;
	
	@FieldName(name = "买入时的贴现利息")
	@Column(name = "BUY_DISCOUNT_INTEREST", precision = 22, scale = 2)
	private BigDecimal buyDiscountInterest;
	
	@FieldName(name="备注")
	@Column(name="MEMO",length=2000)
	private String memo;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getNewBillNum() {
		return newBillNum;
	}

	public void setNewBillNum(String newBillNum) {
		this.newBillNum = newBillNum;
	}

	public String getOldBillNum() {
		return oldBillNum;
	}

	public void setOldBillNum(String oldBillNum) {
		this.oldBillNum = oldBillNum;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getAcceptorNumber() {
		return acceptorNumber;
	}

	public void setAcceptorNumber(String acceptorNumber) {
		this.acceptorNumber = acceptorNumber;
	}

	public String getAcceptorBank() {
		return acceptorBank;
	}

	public void setAcceptorBank(String acceptorBank) {
		this.acceptorBank = acceptorBank;
	}

	public String getAcceptorName() {
		return acceptorName;
	}

	public void setAcceptorName(String acceptorName) {
		this.acceptorName = acceptorName;
	}

	public BigDecimal getFaceAmount() {
		return faceAmount;
	}

	public void setFaceAmount(BigDecimal faceAmount) {
		this.faceAmount = faceAmount;
	}

	public String getBillResource() {
		return billResource;
	}

	public void setBillResource(String billResource) {
		this.billResource = billResource;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getCurrentHolder() {
		return currentHolder;
	}

	public void setCurrentHolder(String currentHolder) {
		this.currentHolder = currentHolder;
	}

	public String getPreviousHolder() {
		return previousHolder;
	}

	public void setPreviousHolder(String previousHolder) {
		this.previousHolder = previousHolder;
	}

	public String getRemoteHolder() {
		return remoteHolder;
	}

	public void setRemoteHolder(String remoteHolder) {
		this.remoteHolder = remoteHolder;
	}

	public String getBuyContractNum() {
		return buyContractNum;
	}

	public void setBuyContractNum(String buyContractNum) {
		this.buyContractNum = buyContractNum;
	}

	public String getBuyNoticeNum() {
		return buyNoticeNum;
	}

	public void setBuyNoticeNum(String buyNoticeNum) {
		this.buyNoticeNum = buyNoticeNum;
	}

	public Integer getBuyTransitDays() {
		return buyTransitDays;
	}

	public void setBuyTransitDays(Integer buyTransitDays) {
		this.buyTransitDays = buyTransitDays;
	}

	public BigDecimal getBuyDiscountRate() {
		return buyDiscountRate;
	}

	public void setBuyDiscountRate(BigDecimal buyDiscountRate) {
		this.buyDiscountRate = buyDiscountRate;
	}

	public BigDecimal getBuyDiscountInterest() {
		return buyDiscountInterest;
	}

	public void setBuyDiscountInterest(BigDecimal buyDiscountInterest) {
		this.buyDiscountInterest = buyDiscountInterest;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getDraweename() {
		return Draweename;
	}

	public void setDraweename(String draweename) {
		Draweename = draweename;
	}

	public String getPayeename() {
		return Payeename;
	}

	public void setPayeename(String payeename) {
		Payeename = payeename;
	}
    
}
