package com.tenwa.leasing.entity.file;

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

/**
 * BaseFileRecorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BASE_FILE_RECORDER")
public class BaseFileRecorder implements java.io.Serializable {

	// Fields
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 128)
	@FieldName(name="标识符")
	private String id;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BASE_FILE")
	@FieldName(name="文件对象")
	private BaseFile baseFile;
	 
	@Column(name = "OPERATOR_TYPE", length = 200)
	@FieldName(name="操作类型")
	private String operatorType;
	
	@ManyToOne
	@JoinColumn(name="CREATOR_")
	@FieldName(name="创建人")
	private User creator;
	
	 
	@Column(name="CREATE_DATE", length=20)	
	@FieldName(name="创建时间")
	private String createDate;
	
	@ManyToOne
	 
	@JoinColumn(name="MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;
	
	
	 
	@Column(name="MODIFY_DATE", length=20)	
	@FieldName(name="修改时间")
	private String modifyDate;

	// Constructors

	/** default constructor */
	public BaseFileRecorder() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BaseFile getBaseFile() {
		return baseFile;
	}

	public void setBaseFile(BaseFile baseFile) {
		this.baseFile = baseFile;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
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