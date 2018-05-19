package com.tenwa.leasing.entity.proj;

import java.io.Serializable;

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
import com.tenwa.leasing.entity.cust.CustInfo;

@Entity
@FieldName(name = "1.4项目担保信息")
@Table(name = "PROJ_GUARANTEE_METHOD")
public class ProjGuaranteeMethod implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	@FieldName(name="项目信息")
	private ProjInfo projInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSUROR")
	@FieldName(name="担保人")
	private CustInfo assuror;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSURE_METHOD")
	@FieldName(name="担保类型")
	private DictionaryData assureMethod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assure_nature")
	@FieldName(name="担保单位性质")
	private DictionaryData assurenature;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSURE_RELATION")
	@FieldName(name="关系")
	private DictionaryData assureRelation;

	@Column(name = "CGMNOTE", length = 510)
	@FieldName(name="备注")
	private String cgmnote;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR")
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
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjInfo getProjInfo() {
		return this.projInfo;
	}

	public void setProjInfo(ProjInfo projInfo) {
		this.projInfo = projInfo;
	}

	public CustInfo getAssuror() {
		return assuror;
	}

	public void setAssuror(CustInfo assuror) {
		this.assuror = assuror;
	}

	public DictionaryData getAssureMethod() {
		return assureMethod;
	}

	public void setAssureMethod(DictionaryData assureMethod) {
		this.assureMethod = assureMethod;
	}

	public DictionaryData getAssureRelation() {
		return assureRelation;
	}

	public void setAssureRelation(DictionaryData assureRelation) {
		this.assureRelation = assureRelation;
	}

	public String getCgmnote() {
		return cgmnote;
	}

	public void setCgmnote(String cgmnote) {
		this.cgmnote = cgmnote;
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

	public DictionaryData getAssurenature() {
		return assurenature;
	}

	public void setAssurenature(DictionaryData assurenature) {
		this.assurenature = assurenature;
	}

	
}