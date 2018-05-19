package com.tenwa.leasing.entity.cust;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;


/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:23:41
 * @info 工作周报表详情信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "工作周报表详情")
@Table(name="T_SALE_WEEK_REPORT_DETAIL")
public class 	SaleWeekReportDetail{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name = "区域")
	@Column(name="AREA", length=200)
	private String area;
	
	
	@FieldName(name = "合同编号")
	@ManyToOne
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	


	@FieldName(name = "项目名称")
	@Column(name="PROJ_NAME", length=200)
	private String projname;
	
	@FieldName(name = "项目金额")
	@Column(name="PROJ_MONEYE", length=200)
	private String projmoney;
	
	@FieldName(name = "项目编号")
	@Column(name="PROJ_NUMBER", length=200)
	private String projnumber;
	
	@FieldName(name = "项目阶段")
	@Column(name="PROJ_STATUS", length=200)
	private String projstatus;
	
	@FieldName(name = "项目时间")
	@Column(name="PROJ_DATE", length=200)
	private String projdate;
	

	@ManyToOne(targetEntity = SaleWeekReport.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "SALEWEEK_ID")
	@FieldName(name="工作周报编号")
	private SaleWeekReport saleweekid;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getProjname() {
		return projname;
	}

	public void setProjname(String projname) {
		this.projname = projname;
	}

	public String getProjmoney() {
		return projmoney;
	}

	public void setProjmoney(String projmoney) {
		this.projmoney = projmoney;
	}

	public String getProjnumber() {
		return projnumber;
	}

	public void setProjnumber(String projnumber) {
		this.projnumber = projnumber;
	}

	public String getProjstatus() {
		return projstatus;
	}

	public void setProjstatus(String projstatus) {
		this.projstatus = projstatus;
	}

	public String getProjdate() {
		return projdate;
	}

	public void setProjdate(String projdate) {
		this.projdate = projdate;
	}

	public SaleWeekReport getSaleweekid() {
		return saleweekid;
	}

	public void setSaleweekid(SaleWeekReport saleweekid) {
		this.saleweekid = saleweekid;
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
