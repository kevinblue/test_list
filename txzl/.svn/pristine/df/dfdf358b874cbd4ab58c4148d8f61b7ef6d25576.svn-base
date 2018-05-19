package com.tenwa.leasing.entity.other;


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

@Entity
@Table(name = "ACCIDENT_INFO")
@FieldName(name="出险信息表")
public class AccidentInfo implements java.io.Serializable {

	private static final long serialVersionUID = -6755954533682227582L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	
	@Column(name = "dnum",length = 100)
	@FieldName(name="唯一的编号")
	private String dnum;
	
	@Column(name = "CONTRACT_ID",length = 100)
	@FieldName(name="合同表主键id")
	private String contractId;
	 
	@Column(name = "guarantee",length = 200)
	@FieldName(name="保证人")
	private String guarantee;
	
	@Column(name = "ACCIDENT_RES",length =  4000)
	@FieldName(name="预警/出险原因")
	private String accidentres;
	
	@Column(name = "FACTOR",length =  4000)
	@FieldName(name="风险因素分析")
	private String factor;
	
	@Column(name = "EQUIPINFO",length = 4000)
	@FieldName(name="租赁物件")
	private String equipinfo;
	
	@Column(name = "DY_INFO",length = 4000)
	@FieldName(name="抵押物")
	private String dyinfo;
	
	@Column(name = "other_info",length = 4000)
	@FieldName(name="其他")
	private String otherinfo;
	
	@Column(name = "PLAN_ADVICE",length = 4000)
	@FieldName(name="处理预案/建议")
	private String planadvice;
	
	@Column(name = "THING_HISCONTENT",length = 4000)
	@FieldName(name="处理预案/建议")
	private String thinghiscontent;
	
	@Column(name = "THING_HISDATE",length = 4000)
	@FieldName(name="历史重大事项时间")
	private String thinghisdate;
	
	@Column(name = "THING_OTHER",length = 4000)
	@FieldName(name="其他说明事项")
	private String thingother;
	
	@Column(name = "ASSET_PROJ",length = 200)
	@FieldName(name="资产经理")
	private String assetproj;
	
	@Column(name = "REPORT_DATE",length = 200)
	@FieldName(name="填报时间")
	private String reportdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDnum() {
		return dnum;
	}

	public void setDnum(String dnum) {
		this.dnum = dnum;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public String getAccidentres() {
		return accidentres;
	}

	public void setAccidentres(String accidentres) {
		this.accidentres = accidentres;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public String getEquipinfo() {
		return equipinfo;
	}

	public void setEquipinfo(String equipinfo) {
		this.equipinfo = equipinfo;
	}

	public String getDyinfo() {
		return dyinfo;
	}

	public void setDyinfo(String dyinfo) {
		this.dyinfo = dyinfo;
	}

	public String getOtherinfo() {
		return otherinfo;
	}

	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}

	public String getPlanadvice() {
		return planadvice;
	}

	public void setPlanadvice(String planadvice) {
		this.planadvice = planadvice;
	}

	public String getThinghiscontent() {
		return thinghiscontent;
	}

	public void setThinghiscontent(String thinghiscontent) {
		this.thinghiscontent = thinghiscontent;
	}

	public String getThinghisdate() {
		return thinghisdate;
	}

	public void setThinghisdate(String thinghisdate) {
		this.thinghisdate = thinghisdate;
	}

	public String getThingother() {
		return thingother;
	}

	public void setThingother(String thingother) {
		this.thingother = thingother;
	}

	public String getAssetproj() {
		return assetproj;
	}

	public void setAssetproj(String assetproj) {
		this.assetproj = assetproj;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
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

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
							
}