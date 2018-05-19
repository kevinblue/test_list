package com.tenwa.leasing.entity.renewableenergy;

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
import com.tenwa.leasing.entity.file.BaseFile;

/**
 * 
 * @author ZYH
 * @date 2016-9-29上午09:33:10
 * @info 可再生能源补助目录
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "可再生能源补助目录")
@Table(name="RENEWABLE_ENERGY")
public class RenewableEnergy {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	

	@FieldName(name="项目类别")
	@Column(name="PRO_TYPE", length=200)
	private String proType;
	
	
	@FieldName(name="所在省市")
	@Column(name="PROVINCES", length=200)
	private String provinces;
	
	@FieldName(name="项目名称")
	@Column(name="PRO_NAME",length=500)
	private String proName;
	
	@FieldName(name="项目公司")
	@Column(name="PRO_COM",length=200)
	private String  proCom;
	
	@FieldName(name="项目容量（MW）")
	@Column(name="PRO_CAPACITY",length=200)
	private Double  proCapacity;
	
	@FieldName(name="批次")
	@Column(name="BATCH",length=200)
	private String batch;
	
	@FieldName(name="文件下发时间")
	@Column(name="DOC_ISSUE_DATE",length=20)
	private String docIssueDate;
	
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
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProCom() {
		return proCom;
	}

	public void setProCom(String proCom) {
		this.proCom = proCom;
	}

	public Double getProCapacity() {
		return proCapacity;
	}

	public void setProCapacity(Double proCapacity) {
		this.proCapacity = proCapacity;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
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

	public String getDocIssueDate() {
		return docIssueDate;
	}

	public void setDocIssueDate(String docIssueDate) {
		this.docIssueDate = docIssueDate;
	}

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
}
