package com.tenwa.leasing.entity.finacial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author zyh
 * @date 2016-9-23下午09:33:10
 * @info 资金预实表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "资金预实计划表")
@Table(name="DEPOSIT_INTEREST_DETAIL")
public class DepositInterestDetail {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="排序号")
	@Column(name="FUND_PLAN_NO", length=50)
	private String fundPlanNo;//BEFORE_DEPOSIT_INTEREST_DETAIL_INSERT

	@ManyToOne
	@JoinColumn(name = "DI_ID")
	@FieldName(name="资金预实表id")
	private DepositInterest diid;
	

	@FieldName(name = "时间")
	@Column(name="PLAN_TIME", length=200)
	private String planTime;
	
	@FieldName(name="内部账户转款(转入)")
	@Column(name="INNER_ACCOUNT")	
	private String innerAccount;
	
	@FieldName(name="内部账户转款(转入)名称")
	@Column(name="INNER_ACCOUNT_NAME")	
	private String innerAccountName;
	

	@FieldName(name="内部账户转款(转入)单位")
	@Column(name="INNER_ACCOUNT_UNIT")	
	private String innerAccountUnit;
	
	@ManyToOne
	@FieldName(name="内部账户转款(转入)款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="INNER_ACCOUNT_TYPE")
	private DictionaryData innerAccountType;
	
	@FieldName(name="内部账户转款(转入)备注")
	@Column(name="INNER_ACCOUNT_NOTE")	
	private String innerAccountNote;
	
	@FieldName(name="回款")
	@Column(name="RETURN_AMOUNT")	
	private String returnAmount;
	
	@FieldName(name="回款名称")
	@Column(name="RETURN_AMOUNT_NAME")	
	private String returnAmountName;
	

	@FieldName(name="回款单位")
	@Column(name="RETURN_AMOUNT_UNIT")	
	private String returnAmountUnit;
	
	@ManyToOne
	@FieldName(name="回款款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="RETURN_AMOUNT_TYPE")
	private DictionaryData returnAmountType;
	
	@FieldName(name="回款备注")
	@Column(name="RETURN_AMOUNT_NOTE")	
	private String returnAmountNote;
	
	@FieldName(name="资金下拨")
	@Column(name="FUND_ALLOCATED")	
	private String fundAllocated;
	
	@FieldName(name="资金下拨名称")
	@Column(name="FUND_ALLOCATED_NAME")	
	private String fundAllocatedName;
	

	@FieldName(name="资金下拨单位")
	@Column(name="FUND_ALLOCATED_UNIT")	
	private String fundAllocatedUnit;
	
	@ManyToOne
	@FieldName(name="资金下拨款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="FUND_ALLOCATED_TYPE")
	private DictionaryData fundAllocatedType;
	
	@FieldName(name="资金下拨备注")
	@Column(name="FUND_ALLOCATED_NOTE")	
	private String fundAllocatedNote;
	
	@FieldName(name="内部借款")
	@Column(name="INNER_BORROW")	
	private String innerBorrow;
	
	@FieldName(name="内部借款名称")
	@Column(name="INNER_BORROW_NAME")	
	private String innerBorrowName;
	

	@FieldName(name="内部借款单位")
	@Column(name="INNER_BORROW_UNIT")	
	private String innerBorrowUnit;
	
	@ManyToOne
	@FieldName(name="内部借款款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="INNER_BORROW_TYPE")
	private DictionaryData innerBorrowType;
	
	@FieldName(name="内部借款备注")
	@Column(name="INNER_BORROW_NOTE")	
	private String innerBorrowNote;
	
	@FieldName(name="外部借款")
	@Column(name="OUTTER_BORROW")	
	private String outterBorrow;
	
	@FieldName(name="外部借款名称")
	@Column(name="OUTTER_BORROW_NAME")	
	private String outterBorrowName;
	

	@FieldName(name="外部借款单位")
	@Column(name="OUTTER_BORROW_UNIT")	
	private String outterBorrowUnit;
	
	@ManyToOne
	@FieldName(name="外部借款款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="OUTTER_BORROW_TYPE")
	private DictionaryData outterBorrowType;
	
	@FieldName(name="外部借款备注")
	@Column(name="OUTTER_BORROW_NOTE")	
	private String outterBorrowNote;

	
	@FieldName(name="付款")
	@Column(name="PAYMENT")	
	private String payment;
	
	@FieldName(name="付款名称")
	@Column(name="PAYMENT_NAME")	
	private String paymentName;
	

	@FieldName(name="付款供应商")
	@Column(name="PAYMENT_UNIT")	
	private String paymentUnit;
	
	@ManyToOne
	@FieldName(name="付款款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="PAYMENT_TYPE")
	private DictionaryData paymentType;
	
	@FieldName(name="付款备注")
	@Column(name="PAYMENT_NOTE")	
	private String paymentNote;
	
	@FieldName(name="付款银承")
	@Column(name="PAYMENT_SILVER")	
	private String paymentSilver;
	
	@Column(name="FUND_DEPOSIT")	
@FieldName(name="资金上存")
	private String fundDeposit;
	
	@FieldName(name="资金上存名称")
	@Column(name="FUND_DEPOSIT_NAME")	
	private String fundDepositName;
	

	@FieldName(name="资金上存供应商")
	@Column(name="FUND_DEPOSIT_UNIT")	
	private String fundDepositUnit;
	
	@ManyToOne
	@FieldName(name="资金上存款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="FUND_DEPOSIT_TYPE")
	private DictionaryData fundDepositType;
	
	@FieldName(name="资金上存备注")
	@Column(name="FUND_DEPOSIT_NOTE")	
	private String fundDepositNote;
	
	@FieldName(name="资金上存银承")
	@Column(name="FUND_DEPOSIT_SILVER")	
	private String fundDepositSilver;
	
	@Column(name="INNER_TRAN")	
@FieldName(name="内部账户转款(转出)")
	private String innerTran;
	
	@FieldName(name="内部账户转款(转出)名称")
	@Column(name="INNER_TRAN_NAME")	
	private String innerTranName;
	

	@FieldName(name="内部账户转款(转出)供应商")
	@Column(name="INNER_TRAN_UNIT")	
	private String innerTranUnit;
	
	@ManyToOne
	@FieldName(name="内部账户转款(转出)款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="INNER_TRAN_TYPE")
	private DictionaryData innerTranType;
	
	@FieldName(name="内部账户转款(转出)备注")
	@Column(name="INNER_TRAN_NOTE")	
	private String innerTranNote;
	
	@FieldName(name="内部账户转款(转出)银乘")
	@Column(name="INNER_TRAN_SILVER")	
	private String innerTranSilver;
	
	@Column(name="OUTTER_RETURN")	
@FieldName(name="外部还款")
	private String outterReturn;
	
	@FieldName(name="外部还款名称")
	@Column(name="OUTTER_RETURN_NAME")	
	private String outterReturnName;
	

	@FieldName(name="外部还款供应商")
	@Column(name="OUTTER_RETURN_UNIT")	
	private String outterReturnUnit;
	
	@ManyToOne
	@FieldName(name="外部还款款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="OUTTER_RETURN_TYPE")
	private DictionaryData outterReturnType;
	
	@FieldName(name="外部还款备注")
	@Column(name="OUTTER_RETURN_NOTE")	
	private String outterReturnNote;
	
	@FieldName(name="外部还款银乘")
	@Column(name="OUTTER_RETURN_SILVER")	
	private String outterReturnSilver;

	@Column(name="GROUP_RETURN")	
@FieldName(name="还集团借款")
	private String groupReturn;
	
	@FieldName(name="还集团借款名称")
	@Column(name="GROUP_RETURN_NAME")	
	private String groupReturnName;
	

	@FieldName(name="还集团借款供应商")
	@Column(name="GROUP_RETURN_UNIT")	
	private String groupReturnUnit;
	
	@ManyToOne
	@FieldName(name="还集团借款款项类型")//资金计划表收付类型..现金流类型
	@JoinColumn(name="GROUP_RETURN_TYPE")
	private DictionaryData groupReturnType;
	
	@FieldName(name="还集团借款备注")
	@Column(name="GROUP_RETURN_NOTE")	
	private String groupReturnNote;
	
	@FieldName(name="还集团借款银乘")
	@Column(name="GROUP_RETURN_SILVER")	
	private String groupReturnSilver;
	
	
	
	public String getFundPlanNo() {
		return fundPlanNo;
	}

	public void setFundPlanNo(String fundPlanNo) {
		this.fundPlanNo = fundPlanNo;
	}

	public DepositInterest getDiid() {
		return diid;
	}

	public void setDiid(DepositInterest diid) {
		this.diid = diid;
	}

	public String getInnerAccount() {
		return innerAccount;
	}

	public void setInnerAccount(String innerAccount) {
		this.innerAccount = innerAccount;
	}

	public String getInnerAccountName() {
		return innerAccountName;
	}

	public void setInnerAccountName(String innerAccountName) {
		this.innerAccountName = innerAccountName;
	}

	public String getInnerAccountUnit() {
		return innerAccountUnit;
	}

	public void setInnerAccountUnit(String innerAccountUnit) {
		this.innerAccountUnit = innerAccountUnit;
	}

	public DictionaryData getInnerAccountType() {
		return innerAccountType;
	}

	public void setInnerAccountType(DictionaryData innerAccountType) {
		this.innerAccountType = innerAccountType;
	}

	public String getInnerAccountNote() {
		return innerAccountNote;
	}

	public void setInnerAccountNote(String innerAccountNote) {
		this.innerAccountNote = innerAccountNote;
	}

	public String getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}

	public String getReturnAmountName() {
		return returnAmountName;
	}

	public void setReturnAmountName(String returnAmountName) {
		this.returnAmountName = returnAmountName;
	}

	public String getReturnAmountUnit() {
		return returnAmountUnit;
	}

	public void setReturnAmountUnit(String returnAmountUnit) {
		this.returnAmountUnit = returnAmountUnit;
	}

	public DictionaryData getReturnAmountType() {
		return returnAmountType;
	}

	public void setReturnAmountType(DictionaryData returnAmountType) {
		this.returnAmountType = returnAmountType;
	}

	public String getReturnAmountNote() {
		return returnAmountNote;
	}

	public void setReturnAmountNote(String returnAmountNote) {
		this.returnAmountNote = returnAmountNote;
	}

	public String getFundAllocated() {
		return fundAllocated;
	}

	public void setFundAllocated(String fundAllocated) {
		this.fundAllocated = fundAllocated;
	}

	public String getFundAllocatedName() {
		return fundAllocatedName;
	}

	public void setFundAllocatedName(String fundAllocatedName) {
		this.fundAllocatedName = fundAllocatedName;
	}

	public String getFundAllocatedUnit() {
		return fundAllocatedUnit;
	}

	public void setFundAllocatedUnit(String fundAllocatedUnit) {
		this.fundAllocatedUnit = fundAllocatedUnit;
	}

	public DictionaryData getFundAllocatedType() {
		return fundAllocatedType;
	}

	public void setFundAllocatedType(DictionaryData fundAllocatedType) {
		this.fundAllocatedType = fundAllocatedType;
	}

	public String getFundAllocatedNote() {
		return fundAllocatedNote;
	}

	public void setFundAllocatedNote(String fundAllocatedNote) {
		this.fundAllocatedNote = fundAllocatedNote;
	}

	public String getInnerBorrow() {
		return innerBorrow;
	}

	public void setInnerBorrow(String innerBorrow) {
		this.innerBorrow = innerBorrow;
	}

	public String getInnerBorrowName() {
		return innerBorrowName;
	}

	public void setInnerBorrowName(String innerBorrowName) {
		this.innerBorrowName = innerBorrowName;
	}

	public String getInnerBorrowUnit() {
		return innerBorrowUnit;
	}

	public void setInnerBorrowUnit(String innerBorrowUnit) {
		this.innerBorrowUnit = innerBorrowUnit;
	}

	public DictionaryData getInnerBorrowType() {
		return innerBorrowType;
	}

	public void setInnerBorrowType(DictionaryData innerBorrowType) {
		this.innerBorrowType = innerBorrowType;
	}

	public String getInnerBorrowNote() {
		return innerBorrowNote;
	}

	public void setInnerBorrowNote(String innerBorrowNote) {
		this.innerBorrowNote = innerBorrowNote;
	}

	public String getOutterBorrow() {
		return outterBorrow;
	}

	public void setOutterBorrow(String outterBorrow) {
		this.outterBorrow = outterBorrow;
	}

	public String getOutterBorrowName() {
		return outterBorrowName;
	}

	public void setOutterBorrowName(String outterBorrowName) {
		this.outterBorrowName = outterBorrowName;
	}

	public String getOutterBorrowUnit() {
		return outterBorrowUnit;
	}

	public void setOutterBorrowUnit(String outterBorrowUnit) {
		this.outterBorrowUnit = outterBorrowUnit;
	}

	public DictionaryData getOutterBorrowType() {
		return outterBorrowType;
	}

	public void setOutterBorrowType(DictionaryData outterBorrowType) {
		this.outterBorrowType = outterBorrowType;
	}

	public String getOutterBorrowNote() {
		return outterBorrowNote;
	}

	public void setOutterBorrowNote(String outterBorrowNote) {
		this.outterBorrowNote = outterBorrowNote;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPaymentUnit() {
		return paymentUnit;
	}

	public void setPaymentUnit(String paymentUnit) {
		this.paymentUnit = paymentUnit;
	}

	public DictionaryData getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(DictionaryData paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public String getPaymentSilver() {
		return paymentSilver;
	}

	public void setPaymentSilver(String paymentSilver) {
		this.paymentSilver = paymentSilver;
	}

	public String getFundDeposit() {
		return fundDeposit;
	}

	public void setFundDeposit(String fundDeposit) {
		this.fundDeposit = fundDeposit;
	}

	public String getFundDepositName() {
		return fundDepositName;
	}

	public void setFundDepositName(String fundDepositName) {
		this.fundDepositName = fundDepositName;
	}

	public String getFundDepositUnit() {
		return fundDepositUnit;
	}

	public void setFundDepositUnit(String fundDepositUnit) {
		this.fundDepositUnit = fundDepositUnit;
	}

	public DictionaryData getFundDepositType() {
		return fundDepositType;
	}

	public void setFundDepositType(DictionaryData fundDepositType) {
		this.fundDepositType = fundDepositType;
	}

	public String getFundDepositNote() {
		return fundDepositNote;
	}

	public void setFundDepositNote(String fundDepositNote) {
		this.fundDepositNote = fundDepositNote;
	}

	public String getFundDepositSilver() {
		return fundDepositSilver;
	}

	public void setFundDepositSilver(String fundDepositSilver) {
		this.fundDepositSilver = fundDepositSilver;
	}

	public String getInnerTran() {
		return innerTran;
	}

	public void setInnerTran(String innerTran) {
		this.innerTran = innerTran;
	}

	public String getInnerTranName() {
		return innerTranName;
	}

	public void setInnerTranName(String innerTranName) {
		this.innerTranName = innerTranName;
	}

	public String getInnerTranUnit() {
		return innerTranUnit;
	}

	public void setInnerTranUnit(String innerTranUnit) {
		this.innerTranUnit = innerTranUnit;
	}

	public DictionaryData getInnerTranType() {
		return innerTranType;
	}

	public void setInnerTranType(DictionaryData innerTranType) {
		this.innerTranType = innerTranType;
	}

	public String getInnerTranNote() {
		return innerTranNote;
	}

	public void setInnerTranNote(String innerTranNote) {
		this.innerTranNote = innerTranNote;
	}

	public String getInnerTranSilver() {
		return innerTranSilver;
	}

	public void setInnerTranSilver(String innerTranSilver) {
		this.innerTranSilver = innerTranSilver;
	}

	public String getOutterReturn() {
		return outterReturn;
	}

	public void setOutterReturn(String outterReturn) {
		this.outterReturn = outterReturn;
	}

	public String getOutterReturnName() {
		return outterReturnName;
	}

	public void setOutterReturnName(String outterReturnName) {
		this.outterReturnName = outterReturnName;
	}

	public String getOutterReturnUnit() {
		return outterReturnUnit;
	}

	public void setOutterReturnUnit(String outterReturnUnit) {
		this.outterReturnUnit = outterReturnUnit;
	}

	public DictionaryData getOutterReturnType() {
		return outterReturnType;
	}

	public void setOutterReturnType(DictionaryData outterReturnType) {
		this.outterReturnType = outterReturnType;
	}

	public String getOutterReturnNote() {
		return outterReturnNote;
	}

	public void setOutterReturnNote(String outterReturnNote) {
		this.outterReturnNote = outterReturnNote;
	}

	public String getOutterReturnSilver() {
		return outterReturnSilver;
	}

	public void setOutterReturnSilver(String outterReturnSilver) {
		this.outterReturnSilver = outterReturnSilver;
	}

	public String getGroupReturn() {
		return groupReturn;
	}

	public void setGroupReturn(String groupReturn) {
		this.groupReturn = groupReturn;
	}

	public String getGroupReturnName() {
		return groupReturnName;
	}

	public void setGroupReturnName(String groupReturnName) {
		this.groupReturnName = groupReturnName;
	}

	public String getGroupReturnUnit() {
		return groupReturnUnit;
	}

	public void setGroupReturnUnit(String groupReturnUnit) {
		this.groupReturnUnit = groupReturnUnit;
	}

	public DictionaryData getGroupReturnType() {
		return groupReturnType;
	}

	public void setGroupReturnType(DictionaryData groupReturnType) {
		this.groupReturnType = groupReturnType;
	}

	public String getGroupReturnNote() {
		return groupReturnNote;
	}

	public void setGroupReturnNote(String groupReturnNote) {
		this.groupReturnNote = groupReturnNote;
	}

	public String getGroupReturnSilver() {
		return groupReturnSilver;
	}

	public void setGroupReturnSilver(String groupReturnSilver) {
		this.groupReturnSilver = groupReturnSilver;
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

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	
		
}
