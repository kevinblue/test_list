package com.tenwa.leasing.entity.cust;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
/**
 * @author 姚俊喜
 * @date 2014-11-14下午05:19:51
 * @email toybaby@tenwa.com.cn
 * @function 
 */
@Entity
@FieldName(name = "进货厂商")
@Table(name = "CUST_PURCHASE_MANUFACTURERS")
public class CustPurchaseManufacturers {
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;

	@ManyToOne
	@FieldName(name="客户ID")
	@JoinColumn(name = "CUST_ID")
	private CustInfo custId;
	
	@FieldName(name="公司名称")
	@Column(name="COMPANY_NAME", length=200)
	private String companyName;

	@FieldName(name="公司统编")
	@Column(name="COMPNAY_CODE", length=100)
	private String companyCode;
	
	@FieldName(name="月平均往来金额")
	@Column(name="AVERAGE_MONTHLY_MONEY", length=100)
	private String averageMonthlyMoney;
	
	@FieldName(name="付款方式")
	@Column(name="PAYMENT_TYPE", length=100)
	private String paymentType;
	
	@FieldName(name="进货项目")
	@Column(name="PURCHASE_PROJECT", length=2000)
	private String purchaseProject;

	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;

	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;

	@FieldName(name = "更新时间")
	@Column(name="MODIFY_DATE", length = 20)
	private String modifyDate;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getAverageMonthlyMoney() {
		return averageMonthlyMoney;
	}

	public void setAverageMonthlyMoney(String averageMonthlyMoney) {
		this.averageMonthlyMoney = averageMonthlyMoney;
	}


	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPurchaseProject() {
		return purchaseProject;
	}

	public void setPurchaseProject(String purchaseProject) {
		this.purchaseProject = purchaseProject;
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