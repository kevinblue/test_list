package com.tenwa.leasing.entity.voucher;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;


/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-11-20 下午3:31:35
 * 类说明
 */
@Entity
@FieldName(name="客户编码配置表")
@Table(name="VOUCHERASS_CUST_CONFIG")
public class VoucherassCustConfig {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="客户编码")
	@ManyToOne(targetEntity=CustInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CUST_NUMBER")
	private CustInfo custNumber;
	
	@FieldName(name="客户科目编码")
	@Column(name="FINANCIAL_CODE")
	private String financialCode;
	
	@FieldName(name="供应商科目编码")
	@Column(name="FINANCIAL_CODE_VENDOR")
	private String financialCode_vendor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustInfo getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(CustInfo custNumber) {
		this.custNumber = custNumber;
	}

	public String getFinancialCode() {
		return financialCode;
	}

	public void setFinancialCode(String financialCode) {
		this.financialCode = financialCode;
	}

	public String getFinancialCode_vendor() {
		return financialCode_vendor;
	}

	public void setFinancialCode_vendor(String financialCode_vendor) {
		this.financialCode_vendor = financialCode_vendor;
	}
	
}
