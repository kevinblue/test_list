package com.tenwa.leasing.entity.base;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;


@Entity
@FieldName(name = "网银数据")
@Table(name = "FUND_EBANK_DATA_TX")
public class FundEbankDataTX {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	

	@FieldName(name = "交易日期时间")
	@Column(name = "TRANSACTION_DATE", length = 50)
	private String transactionDate;
	
	@FieldName(name = "起息日期")
	@Column(name = "PAYOUT_DATE", length = 100)
	private String payoutDate;
	
	@FieldName(name = "账户名称")
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	
	@FieldName(name = "银行类型")
	@Column(name = "BANK_TYPE", length=20)
	private String bankType;
	
	@FieldName(name = "账号")
	@Column(name = "ACCOUNT", length = 200)
	private String account;
	
	@FieldName(name = "借")
	@Column(name = "BORROW", precision = 22, scale = 2)
	private BigDecimal borrow;
	

	@FieldName(name = "贷")
	@Column(name = "CREDIT", precision = 22, scale = 2)
	private BigDecimal credit;
	
	@FieldName(name = "账户余额")
	@Column(name = "ACCOUNT_BALANCE", precision = 22, scale = 2)
	private BigDecimal accountBalance;
	
	@FieldName(name = "摘要")
	@Column(name = "DIGEST",length=20)
	private String digest;
	
	@FieldName(name = "用途")
	@Column(name = "PURPOSE",length=20)
	private String purpose;
	
	@FieldName(name = "对方户名")
	@Column(name = "OTHER_ACCOUNT",length=30)
	private String otherAccount;
	
	@FieldName(name = "对方账号")
	@Column(name = "CLIENT_ACC_NUMBER", length = 200)
	private String clientAccNumber;
	
	@FieldName(name = "对方开户行")
	@Column(name = "OTHER_BANK", length = 200)
	private String otherBank;
	
	@FieldName(name = "交易类型")
	@Column(name = "TRANSACTION_TYPE", length = 20)
	private String transactionType;
	
	@FieldName(name = "结算类型")
	@Column(name = "SETTLEMENT_TYPE", length = 20)
	private String settlementType;
	
	@FieldName(name = "币种")
	@Column(name = "CURRENCY", length = 20)
	private String currency;
	
	@FieldName(name = "关联客户号")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUST_ID")
	private CustInfo custId;
	
	@FieldName(name = "银行流水号")
	@Column(name = "SN", length = 100)
	private String sn;
	
	@FieldName(name = "明细来源")
	@Column(name = "DETAILED_SOURCE", length = 100)
	private String detailedSource;
	
	@FieldName(name = "维护用户")
	@Column(name = "MAINTENANCE_USER", length = 10)
	private String maintenanceUser;
	
	
	@FieldName(name = "金额")
	@Column(name = "AMOUNT ", precision = 22, scale = 8)
	private BigDecimal amount ;
	
	
	@FieldName(name = "备注")
	@Column(name = "NOTE", length = 200)
	private String note;
	
	@FieldName(name = "母/子公司账号")
	@Column(name = "PAT_SUB_ACCOUNTS", length = 200)
	private String parSubAccounts;

	@FieldName(name = "母/子公司账户名")
	@Column(name = "PAT_SUB_ACCNAME", length = 200)
	private String parSubAccname;

	
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
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
	
	@FieldName(name = "上传时间")
	@Column(name = "UPLOAD_DATE", length = 50)
	private String uploaddate;
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPayoutDate() {
		return payoutDate;
	}

	public void setPayoutDate(String payoutDate) {
		this.payoutDate = payoutDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getBorrow() {
		return borrow;
	}

	public void setBorrow(BigDecimal borrow) {
		this.borrow = borrow;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}

	public String getClientAccNumber() {
		return clientAccNumber;
	}

	public void setClientAccNumber(String clientAccNumber) {
		this.clientAccNumber = clientAccNumber;
	}

	public String getOtherBank() {
		return otherBank;
	}

	public void setOtherBank(String otherBank) {
		this.otherBank = otherBank;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getDetailedSource() {
		return detailedSource;
	}

	public void setDetailedSource(String detailedSource) {
		this.detailedSource = detailedSource;
	}

	public String getMaintenanceUser() {
		return maintenanceUser;
	}

	public void setMaintenanceUser(String maintenanceUser) {
		this.maintenanceUser = maintenanceUser;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getParSubAccounts() {
		return parSubAccounts;
	}

	public void setParSubAccounts(String parSubAccounts) {
		this.parSubAccounts = parSubAccounts;
	}

	public String getParSubAccname() {
		return parSubAccname;
	}

	public void setParSubAccname(String parSubAccname) {
		this.parSubAccname = parSubAccname;
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

	public String getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}


}