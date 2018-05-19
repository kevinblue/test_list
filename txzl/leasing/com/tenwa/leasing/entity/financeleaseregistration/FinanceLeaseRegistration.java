package com.tenwa.leasing.entity.financeleaseregistration;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


import org.hibernate.annotations.Index;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author zyh
 * @date 2016-10-31下午09:33:10
 * @info 抵质押登记
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "融资租赁登记")
@Table(name="FINANCE_LEASE_REGISTRATION")
public class FinanceLeaseRegistration {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@ManyToOne
	@FieldName(name="租赁合同编号")
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractid;
	
	@FieldName(name = "项目名称")
	@Column(name = "PROJECT_NAME", length = 200)
	private String projectName;
	
	@FieldName(name="填表人")
	@Column(name="PREPARER",length=50)
	private String preparer;
	
	@FieldName(name="登记证明编号")
	@Column(name="SERIAL_NUMBER",length=50)
	private String serialnumber;
	
	@FieldName(name="登记日期")
	@Column(name="RECORD_DATE",length=50)
	private String recorddate;
	
	@FieldName(name="登记期限")
	@Column(name="DEAD_LINE",length=50)
	private String deadline;
			
	@FieldName(name="登记到期日")
	@Column(name="DATE_OF_REGISTRATION",length=50)
	private String dateofregistration;
		
	@FieldName(name="归档号")
	@Column(name="ARCHIVE_NUMBER",length=50)
	private String archivenumber;
	
	@FieldName(name="租金金额")
	@Column(name="RENT_AMOUNT",length=50)
	private String rentamount;
	
	@FieldName(name="租赁财产描述")
	@Column(name="PROPERTY_DESCRIPTION",length=2000)
	private String propertydescription;
	
	@FieldName(name="租赁物清单")
	@Column(name="LEASE_LIST",length=100)
	private String leaselist;
	
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

	public ContractInfo getContractid() {
		return contractid;
	}

	public void setContractid(ContractInfo contractid) {
		this.contractid = contractid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPreparer() {
		return preparer;
	}

	public void setPreparer(String preparer) {
		this.preparer = preparer;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getRecorddate() {
		return recorddate;
	}

	public void setRecorddate(String recorddate) {
		this.recorddate = recorddate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getDateofregistration() {
		return dateofregistration;
	}

	public void setDateofregistration(String dateofregistration) {
		this.dateofregistration = dateofregistration;
	}

	public String getArchivenumber() {
		return archivenumber;
	}

	public void setArchivenumber(String archivenumber) {
		this.archivenumber = archivenumber;
	}

	public String getRentamount() {
		return rentamount;
	}

	public void setRentamount(String rentamount) {
		this.rentamount = rentamount;
	}

	public String getPropertydescription() {
		return propertydescription;
	}

	public void setPropertydescription(String propertydescription) {
		this.propertydescription = propertydescription;
	}

	public String getLeaselist() {
		return leaselist;
	}

	public void setLeaselist(String leaselist) {
		this.leaselist = leaselist;
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
