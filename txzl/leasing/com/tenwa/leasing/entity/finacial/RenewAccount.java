package com.tenwa.leasing.entity.finacial;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author LYF
 * @date 2016-9-23下午09:33:10
 * @info 续借台帐
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "续借台帐")
@Table(name="Renew_Account")
public class RenewAccount {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="续借日期")
	@Column(name="RENEW_DATE", length=50)	
	private String renewdate;

	@FieldName(name="续借到期日")
	@Column(name="EXPIRATION_DATE", length=50)	
	private String expirationdate;
	
	@FieldName(name="执行利率")
	@Column(name="NOW_RATE", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal nowRate;
	
	@ManyToOne
	@FieldName(name="借款台账ID")
	@JoinColumn(name="LOANACCOUNT_ID")
	private LoanAccount loanaccountid;
	
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

	public String getRenewdate() {
		return renewdate;
	}

	public void setRenewdate(String renewdate) {
		this.renewdate = renewdate;
	}

	public String getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(String expirationdate) {
		this.expirationdate = expirationdate;
	}

	public BigDecimal getNowRate() {
		return nowRate;
	}

	public void setNowRate(BigDecimal nowRate) {
		this.nowRate = nowRate;
	}

	public LoanAccount getLoanaccountid() {
		return loanaccountid;
	}

	public void setLoanaccountid(LoanAccount loanaccountid) {
		this.loanaccountid = loanaccountid;
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
