package com.reckon.entity.contract.reckon.cash;

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

import com.reckon.bean.CashDetail;
import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;

/**
 * 
 * @author 孙传良
 * @date 2013-3-5下午04:03:34
 * @info 合同会计现金流明细表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同会计现金流明细表")
@Table(name="CONTRACT_FINA_CASH_DETAIL")
public class ContractFinaCashDetail implements CashDetail, Comparable<CashDetail> {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="报价编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name="客户初始报价测算编号")
	@Column(name="QUOT_ID")
	@Deprecated
	private String quotId;
	
	@FieldName(name="多次起租测算编号")
	@Column(name="ONHIRE_ID")
	@Deprecated
	private String onhireId;
	
	@FieldName(name="现金流日期")
	@Column(name="PLAN_DATE", length=20)
	private String   planDate;
	
	@FieldName(name="流入量")
	@Column(name="FUND_IN",precision=22,scale=2)
	private BigDecimal fundIn;

	@FieldName(name="流入量清单")
	@Column(name="FUND_IN_DETAILS",length=200)
	private String   fundInDetails;

	@FieldName(name="流出量")
	@Column(name="FUND_OUT",precision=22,scale=2)
	private BigDecimal fundOut;

	@FieldName(name="流出量清单单")
	@Column(name="FUND_OUT_DETAILS",length=200)
	private String   fundOutDetails;

	@FieldName(name="净流量")
	@Column(name="NET_FLOW",precision=22,scale=2)
	private BigDecimal netFlow;
	
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

	@Override
	public boolean equals(Object obj) {
		ContractFinaCashDetail cd=(ContractFinaCashDetail)obj;
		if(this.getPlanDate().equals(cd.getPlanDate())){
			return true;
		}else{
			return false;
		}
	}
	
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

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getQuotId() {
		return quotId;
	}

	public void setQuotId(String quotId) {
		this.quotId = quotId;
	}

	public String getOnhireId() {
		return onhireId;
	}

	public void setOnhireId(String onhireId) {
		this.onhireId = onhireId;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public BigDecimal getFundIn() {
		return fundIn;
	}

	public void setFundIn(BigDecimal fundIn) {
		this.fundIn = fundIn;
	}

	public String getFundInDetails() {
		return fundInDetails;
	}

	public void setFundInDetails(String fundInDetails) {
		this.fundInDetails = fundInDetails;
	}

	public BigDecimal getFundOut() {
		return fundOut;
	}

	public void setFundOut(BigDecimal fundOut) {
		this.fundOut = fundOut;
	}

	public String getFundOutDetails() {
		return fundOutDetails;
	}

	public void setFundOutDetails(String fundOutDetails) {
		this.fundOutDetails = fundOutDetails;
	}

	public BigDecimal getNetFlow() {
		return netFlow;
	}

	public void setNetFlow(BigDecimal netFlow) {
		this.netFlow = netFlow;
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
	
	@Override
	public int compareTo(CashDetail o) {
		long dateDiff = DateTools.getDateDiff(this.getPlanDate(), o.getPlanDate());
		int netFlowDiff = this.getNetFlow().compareTo(o.getNetFlow());
		if(dateDiff == 0){
			return netFlowDiff;
		} else{
			return (int)dateDiff;
		}
	}
}
