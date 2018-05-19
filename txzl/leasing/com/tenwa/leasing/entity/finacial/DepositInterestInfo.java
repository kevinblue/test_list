package com.tenwa.leasing.entity.finacial;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author zyh
 * @date 2016-9-23下午09:33:10
 * @info 资金预实明细表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "资金预实明细表")
@Table(name="DEPOSIT_INTEREST_INFO")
public class DepositInterestInfo {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name="月份")
	@JoinColumn(name="DATE_ID")
	private DepositInterest dateId;	
	
	@FieldName(name="序号")
	@Column(name="SERIAL_ID", length=50)
	private String serialId;//
	
	@FieldName(name="项目")
	@Column(name="ITEM",length=50)
	private String item;
	
	@FieldName(name="月度预算")
	@Column(name="MONTHLY_BUDGET",length=50)
	private String  monthlyBudget;
	
	@FieldName(name="实际金额")
	@Column(name="ACTUAL_AMOUNT",length=50)
	private String   actualAmount;
	
	@FieldName(name="差异数")
	@Column(name="VARIANCE",length=50)
	private String  variance;
	
	@FieldName(name="执行率")
	@Column(name="IMP_RATE",length=50)
	private String impRate;
		
	@FieldName(name="备注")
	@Column(name="NOTE_",length=200)
	private String note;
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
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

	public DepositInterest getDateId() {
		return dateId;
	}

	public void setDateId(DepositInterest dateId) {
		this.dateId = dateId;
	}

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	

	public String getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(String monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getVariance() {
		return variance;
	}

	public void setVariance(String variance) {
		this.variance = variance;
	}

	public String getImpRate() {
		return impRate;
	}

	public void setImpRate(String impRate) {
		this.impRate = impRate;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	
}
