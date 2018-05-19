package com.tenwa.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;

@Entity
@Table(name = "T_APP_VERSION")
public class AppVersion implements Serializable {

	private static final long serialVersionUID = 987190043840421550L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID_", length = 32)
	private String id;
	
	@OneToOne(targetEntity=BaseFile.class)
	@JoinColumn(name="BASE_FILE_ID")
	private BaseFile baseFileId;

	@FieldName(name = "版本号")
	@Column(name = "VERSION_ID")
	private String versionId;
	
	@FieldName(name = "版本下载码")
	@Column(name = "VERSION_CODE")
	private String versionCode;
	
	@FieldName(name = "版本名称")
	@Column(name = "VERSION_NAME")
	private String versionName;
	
	@FieldName(name = "发布时间")
	@Column(name = "UP_DATE", length = 20)
	private String upDates;

	@FieldName(name = "图片相对路径")
	@Column(name = "IMAGE_PATH", length = 512)
	private String imagePath;
	
	@FieldName(name = "是否最新版本")
	@Column(name = "IS_NEW", length = 10)
	private String isNew;
	
	@FieldName(name = "是否强制更新")
	@Column(name = "MUST_UPDATE", length = 10)
	private String mustUpdate;
	
	@FieldName(name = "手机类型")
	@Column(name = "PHONE_TYPE", length = 10)
	private String phoneType;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getUpDates() {
		return upDates;
	}

	public void setUpDates(String upDates) {
		this.upDates = upDates;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BaseFile getBaseFileId() {
		return baseFileId;
	}

	public void setBaseFileId(BaseFile baseFileId) {
		this.baseFileId = baseFileId;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getMustUpdate() {
		return mustUpdate;
	}

	public void setMustUpdate(String mustUpdate) {
		this.mustUpdate = mustUpdate;
	}
	
}
