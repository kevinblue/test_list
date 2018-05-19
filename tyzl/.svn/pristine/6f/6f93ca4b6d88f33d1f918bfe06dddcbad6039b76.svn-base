package com.tenwa.leasing.entity.contract;

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



/**
 * 
 * @author 
 * @date 2013-3-4下午04:31:01
 * @info 组后巡视基本信息
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合同租后巡视表")
@Table(name = "CONTRACT_PATROL_INFO")
public class ContractPatrolInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name = "标识符")
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="巡视重点")
	@Column(name="PATROL_POINT", length=20)
	private String patrolPoint;
	
	@FieldName(name="巡视时间")
	@Column(name="PATROL_DATE", length=20)	
	private String patrolDate;
	
	@FieldName(name="市场估价")
	@Column(name="MARKET_VALUATION", length=20)
	private String marketValuation;
	
	@FieldName(name="创建时间")
	@Column(name="REGISTERED_AMT", length=22)	
	BigDecimal  registeredAmt;
	
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

	public BigDecimal getRegisteredAmt() {
		return registeredAmt;
	}

	public void setRegisteredAmt(BigDecimal registeredAmt) {
		this.registeredAmt = registeredAmt;
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

	public String getPatrolPoint() {
		return patrolPoint;
	}

	public void setPatrolPoint(String patrolPoint) {
		this.patrolPoint = patrolPoint;
	}

	public String getPatrolDate() {
		return patrolDate;
	}

	public void setPatrolDate(String patrolDate) {
		this.patrolDate = patrolDate;
	}

	public String getMarketValuation() {
		return marketValuation;
	}

	public void setMarketValuation(String marketValuation) {
		this.marketValuation = marketValuation;
	}
	
}
