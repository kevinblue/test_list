
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         NoticeAttachment.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-11-21-下午07:26:20
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


 /**
 * 类名称：     NoticeAttachment
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-11-21 下午07:26:20
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_NOTICE_ATTACHMENT")
public class NoticeAttachment {
	private static final long serialVersionUID = 2969449842305751022L;
	@Id
     @GeneratedValue(generator = "paymentableGenerator")     
     @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
     @Column(length=32,name="ID_")
     private String id ;
	 //文件信息
	 @Column(name="CHINESE_FILE_NAME_",nullable=false)
	 private String chineseFileName;
	 @Column(name="ENCODE_FILE_NAME_",nullable=false)
	 private String encodeFileName;
	 @Column(name="FILE_TYPE",nullable=true)
	 private String fileType;
	 @Column(name="FILE_SIZE_",nullable=false)
	 private long    fileSize;
	 @Column(name="REMARK_",nullable=true)
	 private String remark;
	 @ManyToOne(targetEntity=User.class,cascade = 
	 {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	 ,fetch=FetchType.LAZY) 
	 @JoinColumn(name="UPLOAD_USER_ID_",nullable=false)
	 private User uploadUser;
		 
	 @Column(length=32,name="UPLOAD_TIME_",nullable=false)
	 private String uploadTime;

	public String getId() {
		return id;
	}

	public String getChineseFileName() {
		return chineseFileName;
	}

	public String getEncodeFileName() {
		return encodeFileName;
	}

	public String getFileType() {
		return fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getRemark() {
		return remark;
	}

	public User getUploadUser() {
		return uploadUser;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setChineseFileName(String chineseFileName) {
		this.chineseFileName = chineseFileName;
	}

	public void setEncodeFileName(String encodeFileName) {
		this.encodeFileName = encodeFileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setUploadUser(User uploadUser) {
		this.uploadUser = uploadUser;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}	 
}
