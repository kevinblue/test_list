package com.tenwa.leasing.entity.dun;

import java.math.BigDecimal;

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
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;


/**
 * 
 * @author 孙传良
 * @date 2013-3-6下午04:15:55
 * @info 催款员催收记录
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "催款员催收记录")
@Table(name="DUNNING_RECORD")
public class DunningRecord {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@ManyToOne
	@FieldName(name="客户编号")
	@JoinColumn(name="CUST_ID")
	private CustInfo custId;
	@ManyToOne
	@FieldName(name="合同号")
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	@FieldName(name="联系日期")
	@Column(name="LIAISON_DATE", length=20)	
	private String liaisonDate;

	@ManyToOne
	@FieldName(name="联系方式")
	@JoinColumn(name="LIAISON_WAY")
	private DictionaryData liaisonWay;

	@FieldName(name="联系情况")
	@Column(name="LIAISON_INFO")	
	private String liaisonInfo;

	@FieldName(name="承诺还款日")
	@Column(name="PAY_DATE", length=20)	
	private String payDate;

	@FieldName(name="承诺还款金额")
	@Column(name="PAY_MONEY",precision=22,scale=2)	
	private BigDecimal payMoney;

	@FieldName(name="下次联系日期")
	@Column(name="NEXTLIAISON_DATE", length=20)	
	private String nextliaisonDate;

	@FieldName(name="催款员")
	@Column(name="LIAISONER")
	private String liaisoner;
	
	public String getLiaisoner() {
		return liaisoner;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setLiaisoner(String liaisoner) {
		this.liaisoner = liaisoner;
	}

	@ManyToOne
	@FieldName(name="上传ID")
	@JoinColumn(name="UP_ID")
	private BaseFile upId;
	
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

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getLiaisonDate() {
		return liaisonDate;
	}

	public void setLiaisonDate(String liaisonDate) {
		this.liaisonDate = liaisonDate;
	}

	public DictionaryData getLiaisonWay() {
		return liaisonWay;
	}

	public void setLiaisonWay(DictionaryData liaisonWay) {
		this.liaisonWay = liaisonWay;
	}

	public String getLiaisonInfo() {
		return liaisonInfo;
	}

	public void setLiaisonInfo(String liaisonInfo) {
		this.liaisonInfo = liaisonInfo;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public String getNextliaisonDate() {
		return nextliaisonDate;
	}

	public void setNextliaisonDate(String nextliaisonDate) {
		this.nextliaisonDate = nextliaisonDate;
	}

	/*public User getLiaisoner() {
		return liaisoner;
	}

	public void setLiaisoner(User liaisoner) {
		this.liaisoner = liaisoner;
	}*/

	public BaseFile getUpId() {
		return upId;
	}

	public void setUpId(BaseFile upId) {
		this.upId = upId;
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
}
