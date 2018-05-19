package com.tenwa.leasing.entity.voucher;   

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.kernal.annotation.FieldName;


/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-11-20 下午3:31:35
 * 类说明
 */
@Entity
@FieldName(name="科目辅助账类型配置表")
@Table(name="VOUCHERASS_STACTS_CONFIG")
public class VoucherassStactsConfig {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="科目名称")
	@Column(name="SUBJECTS_NAME", length=1000)	
	private String subjectsName; 
	
	@FieldName(name="公司段")
	@Column(name="COMPANY_CODE")	
	private String companyCode; 
	
	@FieldName(name="公司段名称")
	@Column(name="COMPANY_NAME", length=200)	
	private String companyName; 
	
	@FieldName(name="科目编号")
	@Column(name="SUBJECTS_CODE")	
	private String subjectsCode; 
	
	@FieldName(name="科目段名称")
	@Column(name="SUBJECTS_ONENAME", length=200)	
	private String subjectsOneName; 
	
	
	public String getSubjectsOneName() {
		return subjectsOneName;
	}

	public void setSubjectsOneName(String subjectsOneName) {
		this.subjectsOneName = subjectsOneName;
	}

	@FieldName(name="子科目段")
	@Column(name="SONSUBJECT_CODE", length=200)	
	private String sonSubjectCode; 
	
	
	@FieldName(name="子科目段名称")
	@Column(name="SONSUBJECT_NAME", length=200)	
	private String sonSubjectName; 
	
	
	@FieldName(name="责任中心段")
	@Column(name="RESPON_CENTER")	
	private String responCenter;
	
	@FieldName(name="责任中心段名称")
	@Column(name="RESPON_CENTER_NAME", length=200)	
	private String responCenterName;
	
	@FieldName(name="产品段")
	@Column(name="PRODUCT")	
	private String product;
	
	@FieldName(name="产品段名称")
	@Column(name="PRODUCT_NAME", length=200)	
	private String productName;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSonSubjectName() {
		return sonSubjectName;
	}

	public void setSonSubjectName(String sonSubjectName) {
		this.sonSubjectName = sonSubjectName;
	}

	public String getResponCenterName() {
		return responCenterName;
	}

	public void setResponCenterName(String responCenterName) {
		this.responCenterName = responCenterName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getIntrabranchName() {
		return intrabranchName;
	}

	public void setIntrabranchName(String intrabranchName) {
		this.intrabranchName = intrabranchName;
	}

	public String getStandbyName() {
		return standbyName;
	}

	public void setStandbyName(String standbyName) {
		this.standbyName = standbyName;
	}

	@FieldName(name="属性段")
	@Column(name="ATTRIBUTE")	
	private String attribute;
	
	@FieldName(name="属性段名称")
	@Column(name="ATTRIBUTE_NAME", length=200)	
	private String attributeName;
	
	@FieldName(name="内部往来段")
	@Column(name="INTRABRANCH")	
	private String intrabranch;
	
	@FieldName(name="内部往来段名称")
	@Column(name="INTRABRANCH_NAME", length=200)	
	private String intrabranchName;
	
	@FieldName(name="备用段")
	@Column(name="STANBY")	
	private String standby;
	
	@FieldName(name="备用段名称")
	@Column(name="STANBY_NAME", length=200)	
	private String standbyName;
	
	@FieldName(name = "客户01")
	@Column(name="ASSTACTTYPE_CUST", length = 5,columnDefinition="INT default 0")
	private int asstActTypeCust;//1是启用； 0为禁用
	
	@FieldName(name = "供应商02")
	@Column(name="ASSTACTTYPE_VENDOR", length = 5,columnDefinition="INT default 0")
	private int asstActTypeVendor;//1是启用； 0为禁用
	
	@FieldName(name = "银行账户03")
	@Column(name="ASSTACTTYPE_BANK", length = 5,columnDefinition="INT default 0")
	private int asstActTypeBank;//1是启用； 0为禁用
	
	@FieldName(name = "预留04")
	@Column(name="ASSTACTTYPE_FOUR", length = 5,columnDefinition="INT default 0")
	private int asstActTypeFour;//1是启用； 0为禁用
	
	@FieldName(name = "职员05")
	@Column(name="ASSTACTTYPE_EMPLOYEE", length = 5,columnDefinition="INT default 0")
	private int asstActTypeEmployee;//1是启用； 0为禁用
	
	@FieldName(name = "预留06")
	@Column(name="ASSTACTTYPE_SIX", length = 5,columnDefinition="INT default 0")
	private int asstActTypeSix;//1是启用； 0为禁用
	
	@FieldName(name = "行政组织07")
	@Column(name="ASSTACTTYPE_ORGANIZATION", length = 5,columnDefinition="INT default 0")
	private int asstActTypeOrganization;//1是启用； 0为禁用
	
	@FieldName(name = "科目编码ID(用于多分公司同一科目不同科目编码)")
	@Column(name="SUBJECTS_ID")
	private String subjectsId;
	
	@ManyToOne
	@FieldName(name="所属区域(科目对应的所属区域，存入的是数据字典的UUID)")//，实际取数据字典中的DataKey1值 
	@JoinColumn(name="SUBJECTS_OWNER")//select * from T_DICTS_DATAS where  id_ = 'ContractBelong1';
	private DictionaryData subjectsOwner;
	
	@FieldName(name = "是否有效")
	@Column(name="status", length = 5,columnDefinition="INT default 0")
	private int status;//0是有效； 1为作废
	
	@JsonIgnore
	@FieldName(name = "辅助账类别[客户/项目等][D]")
	@OneToMany(mappedBy="configid",cascade={CascadeType.REMOVE},fetch=FetchType.LAZY)
	private Set<VoucherConnection> voucherTypes = new HashSet<VoucherConnection>();
	
	public String getId() {
		return id;
	}

	public String getSubjectsName() {
		return subjectsName;
	}

	public String getSubjectsCode() {
		return subjectsCode;
	}

	public int getAsstActTypeCust() {
		return asstActTypeCust;
	}

	public int getAsstActTypeVendor() {
		return asstActTypeVendor;
	}

	public int getAsstActTypeBank() {
		return asstActTypeBank;
	}

	public int getAsstActTypeFour() {
		return asstActTypeFour;
	}

	public int getAsstActTypeEmployee() {
		return asstActTypeEmployee;
	}

	public int getAsstActTypeSix() {
		return asstActTypeSix;
	}

	public int getAsstActTypeOrganization() {
		return asstActTypeOrganization;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSubjectsName(String subjectsName) {
		this.subjectsName = subjectsName;
	}

	public void setSubjectsCode(String subjectsCode) {
		this.subjectsCode = subjectsCode;
	}

	public void setAsstActTypeCust(int asstActTypeCust) {
		this.asstActTypeCust = asstActTypeCust;
	}

	public void setAsstActTypeVendor(int asstActTypeVendor) {
		this.asstActTypeVendor = asstActTypeVendor;
	}

	public void setAsstActTypeBank(int asstActTypeBank) {
		this.asstActTypeBank = asstActTypeBank;
	}

	public void setAsstActTypeFour(int asstActTypeFour) {
		this.asstActTypeFour = asstActTypeFour;
	}

	public void setAsstActTypeEmployee(int asstActTypeEmployee) {
		this.asstActTypeEmployee = asstActTypeEmployee;
	}

	public void setAsstActTypeSix(int asstActTypeSix) {
		this.asstActTypeSix = asstActTypeSix;
	}

	public void setAsstActTypeOrganization(int asstActTypeOrganization) {
		this.asstActTypeOrganization = asstActTypeOrganization;
	}

	public String getSubjectsId() {
		return subjectsId;
	}

	public void setSubjectsId(String subjectsId) {
		this.subjectsId = subjectsId;
	}

	public DictionaryData getSubjectsOwner() {
		return subjectsOwner;
	}

	public void setSubjectsOwner(DictionaryData subjectsOwner) {
		this.subjectsOwner = subjectsOwner;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<VoucherConnection> getVoucherTypes() {
		return voucherTypes;
	}

	public void setVoucherTypes(Set<VoucherConnection> voucherTypes) {
		this.voucherTypes = voucherTypes;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getSonSubjectCode() {
		return sonSubjectCode;
	}

	public void setSonSubjectCode(String sonSubjectCode) {
		this.sonSubjectCode = sonSubjectCode;
	}

	public String getResponCenter() {
		return responCenter;
	}

	public void setResponCenter(String responCenter) {
		this.responCenter = responCenter;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getIntrabranch() {
		return intrabranch;
	}

	public void setIntrabranch(String intrabranch) {
		this.intrabranch = intrabranch;
	}

	public String getStandby() {
		return standby;
	}

	public void setStandby(String standby) {
		this.standby = standby;
	}

	
}
  
