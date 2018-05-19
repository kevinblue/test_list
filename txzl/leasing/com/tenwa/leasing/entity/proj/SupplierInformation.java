package com.tenwa.leasing.entity.proj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "供应商信息")
@Table(name = "SUPPLIER_INFORMATION")
public class SupplierInformation implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="项目id号")
	@JoinColumn(name = "PROJ_id")
	private ProjDevelopInfo projId;
	
	@FieldName(name="供应商编号")
	@Column(name="Supplier_Id", length=20)	
	private String supplierId;
	
	@FieldName(name="供应商名称")
	@Column(name="SUPPLIER_NAME", length=20)	
	private String supplierName;
	
	@FieldName(name="企业工商注册编码")
	@Column(name="IC_REGIST_CODE", length=20)	
	private String iCRegistCode;
	
	@FieldName(name="供应商大类")
	@JoinColumn(name="SUPPLIER_B_CATEGORIES")
	@ManyToOne(fetch=FetchType.LAZY)
	private SupplierInformationType supplierBCategories;
	
	@FieldName(name="供应商小类")
	@JoinColumn(name="SUPPLIER_S_CATEGORIES")
	@ManyToOne(fetch=FetchType.LAZY)
	private SupplierInformationType  supplierSCategories;
	
	@FieldName(name="货物或劳务名称")
	@JoinColumn(name="NAME_OF_GOODS_OR_SERVICES")
	@ManyToOne(fetch=FetchType.LAZY)
	private SupplierInformationType  nameOfGoodsOrServices;
	
	@FieldName(name="是否合格供应商")
	@Column(name="IS_QUALIFIED_SUPPLIER", length=20)	
	private String isQualifiedSupplier;
	
	@FieldName(name="相关资质")
	@Column(name="RELEVANT_QUALIFICATION", length=20)	
	private String relevantQualification;
	
	@FieldName(name="项目经验")
	@Column(name="PROJECT_EXPERIENCE", length=20)	
	private String projectExperience;
	
	@FieldName(name="联系人")
	@Column(name="CONTACTS", length=20)	
	private String contacts;
	
	@FieldName(name="联系方式")
	@Column(name="CONTACT_WAY", length=20)	
	private String contactWay;
	
	@FieldName(name="备注")
	@Column(name="REMARKS", length=20)	
	private String remarks;
	
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
	private String modifyDate;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjDevelopInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjDevelopInfo projId) {
		this.projId = projId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getiCRegistCode() {
		return iCRegistCode;
	}

	public void setiCRegistCode(String iCRegistCode) {
		this.iCRegistCode = iCRegistCode;
	}

	

	public SupplierInformationType getSupplierBCategories() {
		return supplierBCategories;
	}

	public void setSupplierBCategories(SupplierInformationType supplierBCategories) {
		this.supplierBCategories = supplierBCategories;
	}

	public SupplierInformationType getSupplierSCategories() {
		return supplierSCategories;
	}

	public void setSupplierSCategories(SupplierInformationType supplierSCategories) {
		this.supplierSCategories = supplierSCategories;
	}

	public SupplierInformationType getNameOfGoodsOrServices() {
		return nameOfGoodsOrServices;
	}

	public void setNameOfGoodsOrServices(SupplierInformationType nameOfGoodsOrServices) {
		this.nameOfGoodsOrServices = nameOfGoodsOrServices;
	}

	public String getIsQualifiedSupplier() {
		return isQualifiedSupplier;
	}

	public void setIsQualifiedSupplier(String isQualifiedSupplier) {
		this.isQualifiedSupplier = isQualifiedSupplier;
	}

	public String getRelevantQualification() {
		return relevantQualification;
	}

	public void setRelevantQualification(String relevantQualification) {
		this.relevantQualification = relevantQualification;
	}

	public String getProjectExperience() {
		return projectExperience;
	}

	public void setProjectExperience(String projectExperience) {
		this.projectExperience = projectExperience;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
