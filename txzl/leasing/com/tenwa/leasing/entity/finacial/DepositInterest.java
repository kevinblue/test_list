package com.tenwa.leasing.entity.finacial;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author zyh
 * @date 2016-9-23下午09:33:10
 * @info 资金预实表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "资金预实表")
@Table(name="DEPOSIT_INTEREST")
public class DepositInterest {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="流水号")
	@Column(name="SN", length=50)
	private String sN;//
	
	@FieldName(name="日期")
	@Column(name="DIDATE",length=50)
	private String didate;
	
	@FieldName(name = "资金月报表状态")
	@Column(name="REPORT_STYLE", length=200)
	private String reportStyle;
	
	@FieldName(name = "月初账户余额")
	@Column(name="ACCOUNT_BALANCE", length=200)
	private String accountBalance;
	
	@FieldName(name = "月初存款余额")
	@Column(name="BALANCE_DEPOSIT", length=200)
	private String balanceDeposit;
		
	@FieldName(name="备注")
	@Column(name="NOTE_",length=50)
	private String note;
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

	public String getsN() {
		return sN;
	}

	public void setsN(String sN) {
		this.sN = sN;
	}



	public String getDidate() {
		return didate;
	}

	public void setDidate(String didate) {
		this.didate = didate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getReportStyle() {
		return reportStyle;
	}

	public void setReportStyle(String reportStyle) {
		this.reportStyle = reportStyle;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getBalanceDeposit() {
		return balanceDeposit;
	}

	public void setBalanceDeposit(String balanceDeposit) {
		this.balanceDeposit = balanceDeposit;
	}
		
}
