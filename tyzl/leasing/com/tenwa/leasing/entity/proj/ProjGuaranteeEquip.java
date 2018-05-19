package com.tenwa.leasing.entity.proj;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;

@Entity
@FieldName(name = "1.3项目抵押物信息")
@Table(name = "PROJ_GUARANTEE_EQUIP")
public class ProjGuaranteeEquip implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	@FieldName(name="项目")
	private ProjInfo projInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GUARANTOR")
	@FieldName(name="抵押人")
	private CustInfo guarantor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EQUIP_GUARANTEE_TYPE")
	@FieldName(name="抵押方式")
	private DictionaryData equipGuaranteeType;

	@Column(name = "EQUIP_NAME", length = 510)
	@FieldName(name="抵押物名称")
	private String equipName;
	
	@Column(name = "EQUIP_INVOICE", length = 510)
	@FieldName(name="发票号码")
	private String equipInvoice;
	
	@Column(name = "TOTAL_PRICE", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="发票金额")
	private BigDecimal totalPrice;
	
	@Column(name = "PRESENT_VALUE", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="现值")
	private BigDecimal presentValue;
	
	@Column(name = "GUARANTYVALUE", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="抵质押物价值")
	private BigDecimal guarantyValue;

	@Column(name = "NOTARY_FLAG", length =10)
	@FieldName(name="是否抵押公正")
	private String notaryFlag;
	
	@Column(name = "PURCHASE_LIFE")
	@FieldName(name="购买年限")
	private String purchaseLife;

	@Column(name = "RECORD_MECH", length = 510)
	@FieldName(name="登记机关")
	private String recordMech;

	@Column(name = "CGENOTE", length = 510)
	@FieldName(name="备注")
	private String cgenote;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;
	
	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;
	

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getModificator() {
		return this.modificator;
	}

	public void setModificator(User Modificator) {
		this.modificator = Modificator;
	}

	public ProjInfo getProjInfo() {
		return this.projInfo;
	}

	public void setProjInfo(ProjInfo projInfo) {
		this.projInfo = projInfo;
	}


	public CustInfo getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(CustInfo guarantor) {
		this.guarantor = guarantor;
	}

	public DictionaryData getEquipGuaranteeType() {
		return equipGuaranteeType;
	}

	public void setEquipGuaranteeType(DictionaryData equipGuaranteeType) {
		this.equipGuaranteeType = equipGuaranteeType;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getEquipInvoice() {
		return equipInvoice;
	}

	public void setEquipInvoice(String equipInvoice) {
		this.equipInvoice = equipInvoice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPresentValue() {
		return presentValue;
	}

	public void setPresentValue(BigDecimal presentValue) {
		this.presentValue = presentValue;
	}

	public String getNotaryFlag() {
		return notaryFlag;
	}

	public void setNotaryFlag(String notaryFlag) {
		this.notaryFlag = notaryFlag;
	}
 

	public String getPurchaseLife() {
		return purchaseLife;
	}

	public void setPurchaseLife(String purchaseLife) {
		this.purchaseLife = purchaseLife;
	}

	public String getRecordMech() {
		return recordMech;
	}

	public void setRecordMech(String recordMech) {
		this.recordMech = recordMech;
	}

	public String getCgenote() {
		return cgenote;
	}

	public void setCgenote(String cgenote) {
		this.cgenote = cgenote;
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

	public BigDecimal getGuarantyValue() {
		return guarantyValue;
	}

	public void setGuarantyValue(BigDecimal guarantyValue) {
		this.guarantyValue = guarantyValue;
	}
	

	
}