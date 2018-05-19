package com.tenwa.leasing.entity.finacial;

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
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
@Entity
@FieldName(name = "保证金折现")
@Table(name="CASH_DEPOSIT")
public class CashDeposit {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	

	@FieldName(name = "合同编号")
	@ManyToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="保证金折现日期")
	@Column(name="CASH_DEPOSIT_DATE",length=50)
	private String cashDepositDate;
	
	@FieldName(name="保证金折现值")
	@Column(name="CASH_DEPOSIT_AMOUNT",length=50)
	private String cashDepositAmount;
	
	@FieldName(name="保证金利息")
	@Column(name="CASH_DEPOSIT_INTEREST",length=50)
	private String cashDepositInterest;
	
	@FieldName(name="保证金利息年份")
	@Column(name="CASH_DEPOSIT_INTEREST_DATE",length=50)
	private String cashDepositInterestDate;
	
	public String getCashDepositInterestDate() {
		return cashDepositInterestDate;
	}

	public void setCashDepositInterestDate(String cashDepositInterestDate) {
		this.cashDepositInterestDate = cashDepositInterestDate;
	}

	@FieldName(name = "资金实收表")
	@ManyToOne(targetEntity = CashDepositBase.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHARGE_ID")
	private CashDepositBase chargeId;
	
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

	public String getCashDepositDate() {
		return cashDepositDate;
	}

	public void setCashDepositDate(String cashDepositDate) {
		this.cashDepositDate = cashDepositDate;
	}

	public String getCashDepositAmount() {
		return cashDepositAmount;
	}

	public void setCashDepositAmount(String cashDepositAmount) {
		this.cashDepositAmount = cashDepositAmount;
	}

	public CashDepositBase getChargeId() {
		return chargeId;
	}

	public void setChargeId(CashDepositBase chargeId) {
		this.chargeId = chargeId;
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

	public String getCashDepositInterest() {
		return cashDepositInterest;
	}

	public void setCashDepositInterest(String cashDepositInterest) {
		this.cashDepositInterest = cashDepositInterest;
	}

	
	
}
