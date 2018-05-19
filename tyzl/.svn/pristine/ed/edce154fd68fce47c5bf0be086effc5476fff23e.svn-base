package com.tenwa.leasing.entity.invoice;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;

/**
 * @author ROVINE
 *	2014-12-1
 *  租金发票信息
 *  
 */
@Entity
@FieldName(name = "租金发票开票信息")
@Table(name="RENT_INVOICE_INFO")
public class RentInvoiceInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号:只在本金一次性开票存入")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="Contract_Fund_Rent_Plan表的主键ID")
	@ManyToOne(targetEntity=ContractFundRentPlan.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CFRP_ID")
	private ContractFundRentPlan cfrpId;
	
	@FieldName(name="Contract_Fund_Rent_Income表的主键ID")
	@ManyToOne(targetEntity=ContractFundRentInCome.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CFRI_ID")
	private ContractFundRentInCome cfriId;
	
	@FieldName(name="开票类型（corpusone:本金一次性开票||corpus：本金 ||rent:租金||interest:利息）")
	@Column(name="TAX_TYPE",length=20)
	private String  taxType;
	
	@FieldName(name="发票状态 (null||0)：未申请||1：已退回||2：已申请")
	@Column(name="INVOICE_STATUS")
	private Integer invoiceStatus;
	
	@FieldName(name="发票种类 invoice：发票||receipt:收据")
	@Column(name="BILL_TYPE")
	private String billType;
	
	@FieldName(name="金额")
	@Column(name="MONEY",precision=22,scale=2)
	private BigDecimal Money;
	
	@FieldName(name = "租金发票收据导出数据")
	@OneToMany(mappedBy = "rentInvoiceId",cascade={CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private Set<RentInvoiceDownloadInfo> RentInvoiceDownloadInfos = new HashSet<RentInvoiceDownloadInfo>();
	
	
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

	public ContractFundRentPlan getCfrpId() {
		return cfrpId;
	}

	public void setCfrpId(ContractFundRentPlan cfrpId) {
		this.cfrpId = cfrpId;
	}

	public ContractFundRentInCome getCfriId() {
		return cfriId;
	}

	public void setCfriId(ContractFundRentInCome cfriId) {
		this.cfriId = cfriId;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
	

	public BigDecimal getMoney() {
		return Money;
	}

	public void setMoney(BigDecimal money) {
		Money = money;
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public Set<RentInvoiceDownloadInfo> getRentInvoiceDownloadInfos() {
		return RentInvoiceDownloadInfos;
	}

	public void setRentInvoiceDownloadInfos(
			Set<RentInvoiceDownloadInfo> rentInvoiceDownloadInfos) {
		RentInvoiceDownloadInfos = rentInvoiceDownloadInfos;
	}
	
}
