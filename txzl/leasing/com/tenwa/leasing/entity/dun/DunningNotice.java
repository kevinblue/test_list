package com.tenwa.leasing.entity.dun;

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
import com.tenwa.leasing.entity.cust.CustInfo;

/**
 * 
 * @author 孙传良
 * @date 2013-3-6下午03:59:55
 * @info 催款函发送记录
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "催款函发送记录")
@Table(name="DUNNING_NOTICE")
public class DunningNotice {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="客户编号")
	@JoinColumn(name="CUST_ID")
	private CustInfo custId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="合同号")
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;

	@FieldName(name="发送时间")
	@Column(name="SEND_DATE", length=20)	
	private String sendDate;
	
	@FieldName(name="承租人导出标记")
	@Column(name="CUST_IS_PRINT")	
	private Integer custIsPrint;

	@FieldName(name="承租人导出时间")
	@Column(name="CUST_PRINT_DATE", length=20)	
	private String custPrintDate;

	@FieldName(name="担保人导出标记")
	@Column(name="ASSUROR_IS_PRINT")	
	private Integer assurorIsPrint;

	@FieldName(name="担保人导出时间")
	@Column(name="ASSUROR_PRINT_DATE", length=20)	
	private String assurorPrintDate;
	
	@FieldName(name="逾期期数")
	@Column(name="OVERNUM",precision=22,scale=6)	
	private BigDecimal overnum;

	@FieldName(name="逾期租金")
	@Column(name="OVERRENT",precision=22,scale=2)	
	private BigDecimal overrent;

	@FieldName(name="逾期罚息")
	@Column(name="PENALTY",precision=22,scale=2)	
	private BigDecimal penalty;

	@FieldName(name="导出标记")
	@Column(name="FLAG",length=2)	
	private String flag;

	@FieldName(name="备注")
	@Column(name="DNMEMO")	
	private String dnmemo;

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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getCustIsPrint() {
		return custIsPrint;
	}

	public void setCustIsPrint(Integer custIsPrint) {
		this.custIsPrint = custIsPrint;
	}

	public String getCustPrintDate() {
		return custPrintDate;
	}

	public void setCustPrintDate(String custPrintDate) {
		this.custPrintDate = custPrintDate;
	}

	public Integer getAssurorIsPrint() {
		return assurorIsPrint;
	}

	public void setAssurorIsPrint(Integer assurorIsPrint) {
		this.assurorIsPrint = assurorIsPrint;
	}

	public String getAssurorPrintDate() {
		return assurorPrintDate;
	}

	public void setAssurorPrintDate(String assurorPrintDate) {
		this.assurorPrintDate = assurorPrintDate;
	}

	public BigDecimal getOvernum() {
		return overnum;
	}

	public void setOvernum(BigDecimal overnum) {
		this.overnum = overnum;
	}

	public BigDecimal getOverrent() {
		return overrent;
	}

	public void setOverrent(BigDecimal overrent) {
		this.overrent = overrent;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDnmemo() {
		return dnmemo;
	}

	public void setDnmemo(String dnmemo) {
		this.dnmemo = dnmemo;
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
