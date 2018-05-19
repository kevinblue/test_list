package com.tenwa.leasing.entity.base;

import java.math.BigDecimal;
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
@Table(name = "FUND_EBANK_DATA")
public class FundEbankData {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "客户名称")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUST_ID")
	private CustInfo custId;

	@FieldName(name = "网银编号")
	@Column(name = "EBDATA_ID", length = 50, nullable = true)
	private String ebdataId;
	
	@FieldName(name = "流水号")
	@Column(name = "SN", length = 100)
	private String sn;
	
	@FieldName(name = "到账金额类型")
	@Column(name = "MONEY_TYPE")
	private String moneyType;
	
	@FieldName(name = "到账金额")
	@Column(name = "FACT_MONEY", precision = 22, scale = 2)
	private BigDecimal factMoney;
	
	@FieldName(name = "到账时间")
	@Column(name = "FACT_DATE", length = 20)
	private String factDate;
	
	@FieldName(name = "非业务金额[与系统无关金额]")
	@Column(name = "NO_WITH_MONEY", precision = 22, scale = 2)
	private BigDecimal noWithMoney;
	
	@FieldName(name = "币种")
	@Column(name = "COIN")
	private String coin;
	
	@FieldName(name = "汇率")
	@Column(name = "RATE", precision = 22, scale = 8)
	private BigDecimal rate;
	
	
	@FieldName(name = "付款人")
	@Column(name = "CLIENT_NAME", length = 200)
	private String clientName;
	
	@FieldName(name = "本方银行")
	@Column(name = "OWN_BANK", length = 200)
	private String ownBank;

	@FieldName(name = "本方账户")
	@Column(name = "OWN_ACCOUNT", length = 200)
	private String ownAccount;

	@FieldName(name = "本方账号")
	@Column(name = "OWN_ACC_NUMBER", length = 200)
	private String ownAccNumber;

	@FieldName(name = "对方银行")
	@Column(name = "CLIENT_BANK", length = 200)
	private String clientBank;

	@FieldName(name = "对方账户")
	@Column(name = "CLIENT_ACCOUNT", length = 200)
	private String clientAccount;

	@FieldName(name = "对方账号")
	@Column(name = "CLIENT_ACC_NUMBER", length = 200)
	private String clientAccNumber;


	@FieldName(name = "已核销金额")
	@Column(name = "HAD_MONEY", precision = 22, scale = 2)
	private BigDecimal hadMoney;

	@FieldName(name = "可核销金额")
	@Column(name = "MAY_OPEMONEY", precision = 22, scale = 2)
	private BigDecimal mayOpeMoney;

	@FieldName(name = "收款金额")
	@Column(name = "FUND_MONEY", precision = 22, scale = 2)
	private BigDecimal fundMoney;

	@Transient
	@FieldName(name = "核销合同租金金额")
	@Column(name = "RENT_MONEY", precision = 22, scale = 2)
	private BigDecimal rentMoney;

	@Transient
	@FieldName(name = "核销过程中的金额")
	@Column(name = "FUND_P_MONEY", precision = 22, scale = 2)
	private BigDecimal fundPMoney;

	
	@FieldName(name = "合同主键id【手工生成虚拟网银用到】")
	@Column(name = "contract_id", length = 50, nullable = true)
	private String contractId;
	

	@FieldName(name = "对应实收合同号")
	@Column(name = "contract_id_income")
	private String contracIdIncome;

	@FieldName(name = "是否暂收款")
	@Column(name = "NO_CONTRACT")
	private Integer noContract;
	
	@FieldName(name = "对应实收期项")
	@Column(name = "rent_list_income")
	private String rentListIncome;
	
	
	@FieldName(name="是否作废(伪删除)[是/否][S]")
	@Column(name="INVALID_",length=2)
	private String invalid;
	
	@FieldName(name = "资金收款信息")
	@OneToMany(mappedBy = "ebankNumber", fetch = FetchType.LAZY)
	private Set<ContractFundFundCharge> fundFundCharges = new HashSet<ContractFundFundCharge>();

	@FieldName(name = "租金实收表")
	@OneToMany(mappedBy = "ebankNumber", fetch = FetchType.LAZY)
	private Set<ContractFundRentInCome> contractFundRentInComes = new HashSet<ContractFundRentInCome>();
	
	@FieldName(name = "网银在进行流程")
	@OneToMany(mappedBy = "ebdataId")
	private Set<FundEbankProcess> fundEbankProcesses = new HashSet<FundEbankProcess>();

	@FieldName(name = "备注")
	@Column(name = "SUMMARY", length = 2000)
	private String summary;

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

	
	public void initFundEbank() {
		BigDecimal fundMoney = BigDecimal.ZERO;
		BigDecimal rentMoney = BigDecimal.ZERO;
		BigDecimal fundGMoney=BigDecimal.ZERO;
		BigDecimal fundPMoney=BigDecimal.ZERO;
		for (ContractFundFundCharge fc : fundFundCharges) {
			fundMoney = fundMoney.add(fc.getFactMoney());
		}
		for (ContractFundRentInCome fc : contractFundRentInComes) {
			rentMoney = rentMoney .add(fc.getRent()).add(fc.getPenalty());
		}
	    
		for (FundEbankProcess fc : fundEbankProcesses) {
			if(fc.getWork_flag().endsWith("0")){
				fundPMoney = fundPMoney.add( fc.getProcessAmount());
			}
		}
		if(null==this.noWithMoney){
			this.setNoWithMoney(BigDecimal.ZERO);
		}
		this.setFundMoney(fundMoney);  //非租金实收（不包含经销商保证金）
		this.setFundPMoney(fundPMoney); //核销过程中表
		this.setRentMoney(rentMoney);//租金实收
		BigDecimal hadMoney=new BigDecimal(0.00);
		this.setHadMoney(hadMoney.add(fundMoney).add(rentMoney).add(fundGMoney).add(fundPMoney).setScale(2, BigDecimal.ROUND_HALF_UP));
		BigDecimal opeMoney=this.factMoney;
		//opeMoney.subtract(this.noWithMoney).subtract(fundMoney).subtract(rentMoney).subtract(fundGMoney).subtract(fundPMoney);
		this.setMayOpeMoney(opeMoney.subtract(this.noWithMoney).subtract(fundMoney).subtract(rentMoney).subtract(fundGMoney).subtract(fundPMoney).setScale(2, BigDecimal.ROUND_HALF_UP));
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getEbdataId() {
		return ebdataId;
	}




	public void setEbdataId(String ebdataId) {
		this.ebdataId = ebdataId;
	}




	public String getSn() {
		return sn;
	}




	public void setSn(String sn) {
		this.sn = sn;
	}




	public BigDecimal getFactMoney() {
		return factMoney;
	}




	public void setFactMoney(BigDecimal factMoney) {
		this.factMoney = factMoney;
	}




	public String getFactDate() {
		return factDate;
	}




	public void setFactDate(String factDate) {
		this.factDate = factDate;
	}




	public BigDecimal getNoWithMoney() {
		return noWithMoney;
	}




	public void setNoWithMoney(BigDecimal noWithMoney) {
		this.noWithMoney = noWithMoney;
	}




	public BigDecimal getHadMoney() {
		return hadMoney;
	}




	public void setHadMoney(BigDecimal hadMoney) {
		this.hadMoney = hadMoney;
	}




	public BigDecimal getMayOpeMoney() {
		return mayOpeMoney;
	}




	public void setMayOpeMoney(BigDecimal mayOpeMoney) {
		this.mayOpeMoney = mayOpeMoney;
	}




	public BigDecimal getFundMoney() {
		return fundMoney;
	}




	public void setFundMoney(BigDecimal fundMoney) {
		this.fundMoney = fundMoney;
	}




	public BigDecimal getRentMoney() {
		return rentMoney;
	}




	public void setRentMoney(BigDecimal rentMoney) {
		this.rentMoney = rentMoney;
	}




	public BigDecimal getFundPMoney() {
		return fundPMoney;
	}




	public void setFundPMoney(BigDecimal fundPMoney) {
		this.fundPMoney = fundPMoney;
	}




	public String getContractId() {
		return contractId;
	}




	public void setContractId(String contractId) {
		this.contractId = contractId;
	}




	public String getOwnBank() {
		return ownBank;
	}




	public void setOwnBank(String ownBank) {
		this.ownBank = ownBank;
	}




	public String getOwnAccount() {
		return ownAccount;
	}




	public void setOwnAccount(String ownAccount) {
		this.ownAccount = ownAccount;
	}




	public String getOwnAccNumber() {
		return ownAccNumber;
	}




	public void setOwnAccNumber(String ownAccNumber) {
		this.ownAccNumber = ownAccNumber;
	}




	public String getClientBank() {
		return clientBank;
	}




	public void setClientBank(String clientBank) {
		this.clientBank = clientBank;
	}




	public String getClientAccount() {
		return clientAccount;
	}




	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}




	public String getClientAccNumber() {
		return clientAccNumber;
	}




	public void setClientAccNumber(String clientAccNumber) {
		this.clientAccNumber = clientAccNumber;
	}




	public String getClientName() {
		return clientName;
	}




	public void setClientName(String clientName) {
		this.clientName = clientName;
	}




	public CustInfo getCustId() {
		return custId;
	}




	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}




	public String getMoneyType() {
		return moneyType;
	}




	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}




	public String getContracIdIncome() {
		return contracIdIncome;
	}




	public void setContracIdIncome(String contracIdIncome) {
		this.contracIdIncome = contracIdIncome;
	}




	public Integer getNoContract() {
		return noContract;
	}




	public void setNoContract(Integer noContract) {
		this.noContract = noContract;
	}




	public String getRentListIncome() {
		return rentListIncome;
	}




	public void setRentListIncome(String rentListIncome) {
		this.rentListIncome = rentListIncome;
	}




	public String getCoin() {
		return coin;
	}




	public void setCoin(String coin) {
		this.coin = coin;
	}




	public BigDecimal getRate() {
		return rate;
	}




	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}






	public String getInvalid() {
		return invalid;
	}




	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}




	public Set<ContractFundFundCharge> getFundFundCharges() {
		return fundFundCharges;
	}




	public void setFundFundCharges(Set<ContractFundFundCharge> fundFundCharges) {
		this.fundFundCharges = fundFundCharges;
	}




	public Set<ContractFundRentInCome> getContractFundRentInComes() {
		return contractFundRentInComes;
	}




	public void setContractFundRentInComes(
			Set<ContractFundRentInCome> contractFundRentInComes) {
		this.contractFundRentInComes = contractFundRentInComes;
	}




	public Set<FundEbankProcess> getFundEbankProcesses() {
		return fundEbankProcesses;
	}




	public void setFundEbankProcesses(Set<FundEbankProcess> fundEbankProcesses) {
		this.fundEbankProcesses = fundEbankProcesses;
	}




	public String getSummary() {
		return summary;
	}




	public void setSummary(String summary) {
		this.summary = summary;
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