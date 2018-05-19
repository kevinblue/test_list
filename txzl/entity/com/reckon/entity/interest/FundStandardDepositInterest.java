package com.reckon.entity.interest;

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

import org.hibernate.annotations.GenericGenerator;

import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author 孙传良
 * @date 2013-3-7上午10:45:07
 * @info 央行基准利率调整记录
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "央行基准存款利率调整记录")
@Table(name="FUND_STANDARD_DEPOSIT_INTEREST")
public class FundStandardDepositInterest {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="存款利率开始执行日期")
	@Column(name="START_DATE_",length=20)
	private String   startDate;

	

	@FieldName(name="存款利率央行基准_1年")
	@Column(name="BASE_RATE_DEPOSIT_ONE",precision=22,scale=6)
	private BigDecimal baseRateDepositOne;

	
	
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getBaseRateDepositOne() {
		return baseRateDepositOne;
	}

	public void setBaseRateDepositOne(BigDecimal baseRateDepositOne) {
		this.baseRateDepositOne = baseRateDepositOne;
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
