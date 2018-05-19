package com.tenwa.leasing.entity.fund;

import java.math.BigDecimal;
import java.util.HashMap;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.reckon.bean.FundPlan;
import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.paymentinterface.PaymentLog;


/**
 * 
 * @author 孙传良
 * @date 2013-3-7下午12:05:49
 * @info 合同资金计划表
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "采购合同关联资金计划表")
@Table(name = "T_CONTRACT_NUM_SET_FUND_PLAN")
public class PurchaseContractFundFundPlan {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "租金计划表id")
	@Column(name = "FUND_PLAN_ID", length = 32)
	private String fundPlanId;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 30)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 30)
	private String modifyDate;
   
	
	@FieldName(name = "采购合同id")
	@Column(name = "PURCHASE_CONTRACT_ID", length = 32)
	private String purchaseContractid;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public String getPurchaseContractid() {
		return purchaseContractid;
	}

	public void setPurchaseContractid(String purchaseContractid) {
		this.purchaseContractid = purchaseContractid;
	}

	public String getFundPlanId() {
		return fundPlanId;
	}

	public void setFundPlanId(String fundPlanId) {
		this.fundPlanId = fundPlanId;
	}
	
}
 
