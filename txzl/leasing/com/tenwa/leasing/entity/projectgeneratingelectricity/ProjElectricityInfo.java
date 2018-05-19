package com.tenwa.leasing.entity.projectgeneratingelectricity;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.GenericGenerator;

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author cyw
 * @date 2013-3-4下午09:33:10
 * @info 项目发电量电费收入信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "项目发电量电费收入信息")
@Table(name="Proj_Electricity_Info")

public class ProjElectricityInfo {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	@FieldName(name="项目编号")
	private ProjInfo projid;
	
	@FieldName(name="时间")
	@Column(name="TIME",length=50)
	private String time;
	
	@FieldName(name="上网电量(千瓦时)")
	@Column(name="ONGRID_ENERGY", length=50)
	private String ongridenergy;
	
	@Column(name = "INTERNET_TARIFF")
	@FieldName(name="上网电价（元/度）")
	private String internettariff;
	
	@Column(name = "THERMAL_PRICE")
	@FieldName(name="火电标杆电价（元/度）")
	private String thermalprice;
	
	@FieldName(name = "标准发电小时")
	@Column(name = "STANDARD_HOURS")
	private String standardhours;
	
	@FieldName(name="折合标准发电小时数")
	@Column(name="CONVERT_STANDARD_HOURS", length=50)
	private String convertstandardhours;
	
	@FieldName(name="应收税后电费(元)")
	@Column(name="BILL_AFTER_RECEIVABLE",length=50)
	private String billafterreceivable;
	
	@FieldName(name="应收电费(元)")
	@Column(name="BILL_RECEIVABLE",length=50)
	private String billreceivable;
	
	@FieldName(name="实收电费(元)")
	@Column(name="CASH_RECEIVED",length=50)
	private String cashreceived;
	
	@FieldName(name="结算电价(元)")
	@Column(name="SETTLEMENT_PRICE",length=50)
	private String settlementprice;
	
	@FieldName(name="应收税后标杆电费(元)")
	@Column(name="BENCHMARK_AFTER_TARIFF",length=50)
	private String benchmarkaftertariff;
	
	@FieldName(name="应收标杆电费(元)")
	@Column(name="BENCHMARK_TARIFF",length=50)
	private String benchmarktariff;
	
	
	@FieldName(name="不含补贴收入(元)")
	@Column(name="NOSUBSIDY_INCOME")
	private String nosubsidyincome;
	
	
	@FieldName(name="应收不含补贴电费(元)")
	@Column(name="EXCLUDING_NOSUBSIDY",length=50)
	private String excludingnosubsidy;
	
	
	@FieldName(name="实收补贴(元)")
	@Column(name="ELECTRICITY_SUBSIDY")
	private String electricitysubsidy;
	
	@FieldName(name="补贴电价(元)")
	@Column(name="AFTER_BENCHMARK_PRICE",length=50)
	private String afterbenchmarkprice;
	
	@FieldName(name="未收到补贴电费收入(元)")
	@Column(name="RECEIVE_NOSUBSIDY",length=50)
	private String receivenosubsidy;
	
	@FieldName(name="未收到电费补贴(元)")
	@Column(name="NOELECTRICITY_RECEIVED",length=50)
	private String noelectricityreceived;
	
	@FieldName(name="限电比例")
	@Column(name="POWER_RATIONING",length=50)
	private String powerrationing;
	
	@FieldName(name="单月限电比例")
	@Column(name="MONTH_POWER_RATIONING",length=50)
	private String monthpowerrationing;
	
	@FieldName(name="限电量")
	@Column(name="POWER_LIMIT",length=50)
	private String powerlimit;
	
	@FieldName(name="备注")
	@Column(name="NOTE",length=200)
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


	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public ProjInfo getProjid() {
		return projid;
	}

	public void setProjid(ProjInfo projid) {
		this.projid = projid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOngridenergy() {
		return ongridenergy;
	}

	public void setOngridenergy(String ongridenergy) {
		this.ongridenergy = ongridenergy;
	}

	public String getStandardhours() {
		return standardhours;
	}

	public void setStandardhours(String standardhours) {
		this.standardhours = standardhours;
	}

	public String getConvertstandardhours() {
		return convertstandardhours;
	}

	public void setConvertstandardhours(String convertstandardhours) {
		this.convertstandardhours = convertstandardhours;
	}

	public String getBillafterreceivable() {
		return billafterreceivable;
	}

	public void setBillafterreceivable(String billafterreceivable) {
		this.billafterreceivable = billafterreceivable;
	}

	public String getBillreceivable() {
		return billreceivable;
	}

	public void setBillreceivable(String billreceivable) {
		this.billreceivable = billreceivable;
	}

	public String getCashreceived() {
		return cashreceived;
	}

	public void setCashreceived(String cashreceived) {
		this.cashreceived = cashreceived;
	}

	public String getSettlementprice() {
		return settlementprice;
	}

	public void setSettlementprice(String settlementprice) {
		this.settlementprice = settlementprice;
	}

	public String getBenchmarkaftertariff() {
		return benchmarkaftertariff;
	}

	public void setBenchmarkaftertariff(String benchmarkaftertariff) {
		this.benchmarkaftertariff = benchmarkaftertariff;
	}

	public String getBenchmarktariff() {
		return benchmarktariff;
	}

	public void setBenchmarktariff(String benchmarktariff) {
		this.benchmarktariff = benchmarktariff;
	}

	public String getNosubsidyincome() {
		return nosubsidyincome;
	}

	public void setNosubsidyincome(String nosubsidyincome) {
		this.nosubsidyincome = nosubsidyincome;
	}

	public String getExcludingnosubsidy() {
		return excludingnosubsidy;
	}

	public void setExcludingnosubsidy(String excludingnosubsidy) {
		this.excludingnosubsidy = excludingnosubsidy;
	}

	public String getElectricitysubsidy() {
		return electricitysubsidy;
	}

	public void setElectricitysubsidy(String electricitysubsidy) {
		this.electricitysubsidy = electricitysubsidy;
	}

	public String getAfterbenchmarkprice() {
		return afterbenchmarkprice;
	}

	public void setAfterbenchmarkprice(String afterbenchmarkprice) {
		this.afterbenchmarkprice = afterbenchmarkprice;
	}

	public String getReceivenosubsidy() {
		return receivenosubsidy;
	}

	public void setReceivenosubsidy(String receivenosubsidy) {
		this.receivenosubsidy = receivenosubsidy;
	}

	public String getNoelectricityreceived() {
		return noelectricityreceived;
	}

	public void setNoelectricityreceived(String noelectricityreceived) {
		this.noelectricityreceived = noelectricityreceived;
	}

	public String getPowerrationing() {
		return powerrationing;
	}

	public void setPowerrationing(String powerrationing) {
		this.powerrationing = powerrationing;
	}

	public String getMonthpowerrationing() {
		return monthpowerrationing;
	}

	public void setMonthpowerrationing(String monthpowerrationing) {
		this.monthpowerrationing = monthpowerrationing;
	}

	public String getPowerlimit() {
		return powerlimit;
	}

	public void setPowerlimit(String powerlimit) {
		this.powerlimit = powerlimit;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getInternettariff() {
		return internettariff;
	}

	public void setInternettariff(String internettariff) {
		this.internettariff = internettariff;
	}

	public String getThermalprice() {
		return thermalprice;
	}

	public void setThermalprice(String thermalprice) {
		this.thermalprice = thermalprice;
	}
	
}
