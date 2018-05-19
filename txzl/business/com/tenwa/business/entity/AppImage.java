package com.tenwa.business.entity;

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
import org.hibernate.annotations.Index;

import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name = "T_FILE_IMAGES")
public class AppImage implements Serializable {

	private static final long serialVersionUID = 987190043840421550L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID_", length = 32)
	private String id;

	@FieldName(name = "流程编号")
	@Column(name = "DOC_ID")
	private String docId;

	@FieldName(name = "客户号")
	@Column(name = "CUST_ID")
	@Index(name = "I_FILE_IMAGES_CUSTID")
	private String custId;

	@FieldName(name = "项目号")
	@Column(name = "PROJ_ID")
	@Index(name = "I_FILE_IMAGES_PROJID")
	private String projId;

	@FieldName(name = "合同号")
	@Column(name = "CONTRACT_ID")
	@Index(name = "I_FILE_IMAGES_CONTRACTID")
	private String contractId;

	@FieldName(name = "设备号")
	@Column(name = "EQUIP_ID")
	@Index(name = "I_FILE_IMAGES_EQUIPID")
	private String equipId;

	@FieldName(name = "商务报价ID")
	@Column(name = "CONDITION_ID")
	@Index(name = "I_FILE_IMAGES_CONDITIONID")
	private String conditionId;

	@FieldName(name = "文件标题")
	@Column(name = "TITLE_", length = 512)
	private String title;

	@FieldName(name = "图片相对路径")
	@Column(name = "IMAGE_PATH", length = 512)
	private String imagePath;

	@FieldName(name = "图片名称")
	@Column(name = "FILE_NAME", length = 128)
	private String fileName;

	@FieldName(name = "缓存图片相对路径")
	@Column(name = "THUMB_IMAGE_PATH", length = 512)
	private String thumbImagePath;

	@FieldName(name = "图片宽度")
	@Column(name = "IMAGE_WIDTH")
	private Integer imageWidth;

	@FieldName(name = "图片高度")
	@Column(name = "IMAGE_HEIGHT")
	private Integer imageHeight;

	@FieldName(name = "缩略图宽度")
	@Column(name = "THUMB_IMAGE_WIDTH")
	private Integer thumbImageWidth;

	@FieldName(name = "缩略图高度")
	@Column(name = "THUMB_IMAGE_HEIGHT")
	private Integer thumbImageHeight;

	@FieldName(name = "图片类型")
	@Column(name = "IMAGE_TYPE", length = 20)
	private String imageType;

	@FieldName(name = "图片大小")
	@Column(name = "IMAGE_SIZE")
	private Long imageSize;
	
	@FieldName(name = "图片大小")
	@Column(name = "REMARK_", length = 1024)
	private String remark;

	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	@ManyToOne(fetch = FetchType.LAZY)
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	@ManyToOne(fetch = FetchType.LAZY)
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;
	
	@Column(name = "PHONE_UPLOAD", columnDefinition="NUMBER(1) DEFAULT 0", length = 1)
	private Boolean phoneUpload;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getThumbImagePath() {
		return thumbImagePath;
	}

	public void setThumbImagePath(String thumbImagePath) {
		this.thumbImagePath = thumbImagePath;
	}

	public Integer getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

	public Integer getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Integer getThumbImageWidth() {
		return thumbImageWidth;
	}

	public void setThumbImageWidth(Integer thumbImageWidth) {
		this.thumbImageWidth = thumbImageWidth;
	}

	public Integer getThumbImageHeight() {
		return thumbImageHeight;
	}

	public void setThumbImageHeight(Integer thumbImageHeight) {
		this.thumbImageHeight = thumbImageHeight;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public Long getImageSize() {
		return imageSize;
	}

	public void setImageSize(Long imageSize) {
		this.imageSize = imageSize;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getPhoneUpload() {
		return phoneUpload;
	}

	public void setPhoneUpload(Boolean phoneUpload) {
		this.phoneUpload = phoneUpload;
	}
	
}
