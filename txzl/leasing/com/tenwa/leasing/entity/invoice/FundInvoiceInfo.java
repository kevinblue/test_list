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
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.proj.ProjGuaranteeEquip;



/**
 * @author Toybaby
 *	2013-10-11
 *  资计发票信息
 *  
 */
@Entity
@FieldName(name = "资金发票开票信息")
@Table(name="FUND_INVOICE_INFO")
public class FundInvoiceInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="Contract_Fund_Fund_Plan表的主键ID")
	@ManyToOne(targetEntity=ContractFundFundPlan.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CFFP_ID")
	private ContractFundFundPlan cffpId;
	
	@FieldName(name="Contract_Fund_Fund_Charge表的主键ID")
	@ManyToOne(targetEntity=ContractFundFundCharge.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CFFC_ID")
	private ContractFundFundCharge cffcId;
	
	@FieldName(name="发票状态 (null||0)：未申请||1：已退回||2：已申请")
	@Column(name="INVOICE_STATUS")
	private Integer invoiceStatus;
	
	@FieldName(name="发票种类 invoice：发票||receipt:收据")
	@Column(name="BILL_TYPE")
	private String billType;
	
	@FieldName(name="金额")
	@Column(name="MONEY",precision=22,scale=2)
	private BigDecimal Money;
	
	@FieldName(name = "资金发票收据导出数据")
	@OneToMany(mappedBy = "fundInvoiceId",cascade={CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private Set<FundInvoiceDownloadInfo> FundInvoiceDownloadInfos = new HashSet<FundInvoiceDownloadInfo>();
	
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

	public ContractFundFundPlan getCffpId() {
		return cffpId;
	}

	public void setCffpId(ContractFundFundPlan cffpId) {
		this.cffpId = cffpId;
	}

	public ContractFundFundCharge getCffcId() {
		return cffcId;
	}

	public void setCffcId(ContractFundFundCharge cffcId) {
		this.cffcId = cffcId;
	}

	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
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

	public Set<FundInvoiceDownloadInfo> getFundInvoiceDownloadInfos() {
		return FundInvoiceDownloadInfos;
	}

	public void setFundInvoiceDownloadInfos(
			Set<FundInvoiceDownloadInfo> fundInvoiceDownloadInfos) {
		FundInvoiceDownloadInfos = fundInvoiceDownloadInfos;
	}
	

}
