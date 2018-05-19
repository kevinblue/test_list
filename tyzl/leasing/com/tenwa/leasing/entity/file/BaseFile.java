package com.tenwa.leasing.entity.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * BaseFile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BASE_FILE")
public class BaseFile implements java.io.Serializable {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@ManyToOne(targetEntity=BaseFileTemplate.class,fetch=FetchType.LAZY)
	@JoinColumn(name="FILE_TEMPLATE")
	@FieldName(name="对应模板")
    private BaseFileTemplate baseFileTemplate;
    
	 
	@Column(name="FLOW_UNID")
	@FieldName(name="标识流程ID")
	private String flowUnid;
	
	@Column(name="MODEL_NAME")
	@FieldName(name="模块名")
	private String modelName;
	
	@Column(name="FILE_KEY")
	@FieldName(name="文件关键字，客户Number,合同号,项目编号")
	private String filekey;
	
	@Column(name="FILE_NAME")
	@FieldName(name="文件名称")
	private String fileName;
	
	@Column(name="FILE_SIZE")
	@FieldName(name="文件大小")
	private Long fileSize;
	 	 
	@Column(name="FILE_ADDRESS")
	@FieldName(name="文件地址")
	private String fileAddress;
	
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
	   
	@Column(name="MEMO")	
	@FieldName(name="备注")
	private String memo;
	
	@Column(name="INVALID_",length=2)
	@FieldName(name="是否有效")
	private String invalid;

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BaseFileTemplate getBaseFileTemplate() {
		return baseFileTemplate;
	}

	public void setBaseFileTemplate(BaseFileTemplate baseFileTemplate) {
		this.baseFileTemplate = baseFileTemplate;
	}

	public String getFlowUnid() {
		return flowUnid;
	}

	public void setFlowUnid(String flowUnid) {
		this.flowUnid = flowUnid;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}

	public String getFilekey() {
		return filekey;
	}

	public void setFilekey(String filekey) {
		this.filekey = filekey;
	}
	
}