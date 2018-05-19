package com.tenwa.leasing.entity.contract;

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

/**
 * 
 * @author 徐云龙
 * @date 2013-3-6上午12:05:35
 * @info
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同供应商信息")
@Table(name="CONTRACT_SUPPLIER_INFO")
public class ContractSupplierInfo {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="供应商名称")
	@Column(name="SELLER")
	private String seller;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "类型（供应商/代理商）")
	@JoinColumn(name = "CUST_TYPE")
	private DictionaryData CustType;
	
	@FieldName(name="法定代表人（卖方）")
	@Column(name="SELLER_LEGAL_PERSON")
	private String sellerLegalPerson;

	@FieldName(name="联系人（卖方）")
	@Column(name="SELLER_LINKMAN")
	private String sellerLinkman;

	@FieldName(name="注册地址")
	@Column(name="SELLER_REGISTER_ADDR")
	private String sellerRegisterAddr;

	@FieldName(name="通讯地址")
	@Column(name="SELLER_ADDR")
	private String sellerAddr;

	@FieldName(name="邮政编码")
	@Column(name="SELLER_POSTCODE")
	private String sellerPostcode;

	@FieldName(name="电话（卖方）")
	@Column(name="SELLER_TEL",length=100)
	private String sellerTel;

	@FieldName(name="传真（卖方）")
	@Column(name="SELLER_FAX",length=100)
	private String sellerFax;
	
	@FieldName(name="电子邮件（卖方）")
	@Column(name="SELLER_EMAIL")
	private String sellerEmail;

	@FieldName(name="开户账号")
	@Column(name="SELLER_ACC_NUMBER")
	private String sellerAccNumber;

	@FieldName(name="开户银行")
	@Column(name="SELLER_ACC_BANK")
	private String sellerAccBank;

	@FieldName(name="开户户名")
	@Column(name="SELLER_ACC_NAME")
	private String sellerAccName;

	@FieldName(name="附属条款")
	@Column(name="ATTACH_CONDTION", length=2000)
	private String  attachCondtion;
	
	public DictionaryData getCustType() {
		return CustType;
	}

	public void setCustType(DictionaryData custType) {
		CustType = custType;
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getSellerLegalPerson() {
		return sellerLegalPerson;
	}

	public void setSellerLegalPerson(String sellerLegalPerson) {
		this.sellerLegalPerson = sellerLegalPerson;
	}

	public String getSellerLinkman() {
		return sellerLinkman;
	}

	public void setSellerLinkman(String sellerLinkman) {
		this.sellerLinkman = sellerLinkman;
	}

	public String getSellerRegisterAddr() {
		return sellerRegisterAddr;
	}

	public void setSellerRegisterAddr(String sellerRegisterAddr) {
		this.sellerRegisterAddr = sellerRegisterAddr;
	}

	public String getSellerAddr() {
		return sellerAddr;
	}

	public void setSellerAddr(String sellerAddr) {
		this.sellerAddr = sellerAddr;
	}

	public String getSellerPostcode() {
		return sellerPostcode;
	}

	public void setSellerPostcode(String sellerPostcode) {
		this.sellerPostcode = sellerPostcode;
	}

	public String getSellerTel() {
		return sellerTel;
	}

	public void setSellerTel(String sellerTel) {
		this.sellerTel = sellerTel;
	}

	public String getSellerFax() {
		return sellerFax;
	}

	public void setSellerFax(String sellerFax) {
		this.sellerFax = sellerFax;
	}

	public String getSellerAccNumber() {
		return sellerAccNumber;
	}

	public void setSellerAccNumber(String sellerAccNumber) {
		this.sellerAccNumber = sellerAccNumber;
	}

	public String getSellerAccBank() {
		return sellerAccBank;
	}

	public void setSellerAccBank(String sellerAccBank) {
		this.sellerAccBank = sellerAccBank;
	}

	public String getSellerAccName() {
		return sellerAccName;
	}

	public void setSellerAccName(String sellerAccName) {
		this.sellerAccName = sellerAccName;
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

	public String getAttachCondtion() {
		return attachCondtion;
	}

	public void setAttachCondtion(String attachCondtion) {
		this.attachCondtion = attachCondtion;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	
}
