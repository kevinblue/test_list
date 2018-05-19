package com.tenwa.leasing.entity.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name = "T_BASEFILE_ATTACHMENTINFO")
public class BaseFile_AttachmentInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@Column(name="UPLOAD_ID")
	@FieldName(name="上传或是生成的ID")
	private String uploadid;
	
	@Column(name="ATTACHMENT_ID")
	@FieldName(name="附件一览的ID")
	private String attachmentid;
	
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUploadid() {
		return uploadid;
	}

	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}

	public String getAttachmentid() {
		return attachmentid;
	}

	public void setAttachmentid(String attachmentid) {
		this.attachmentid = attachmentid;
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
