
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         AttachmentFileUploadInfo.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-9-上午09:58:44
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.business.entity;

import java.io.Serializable;

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
 * 类名称：     AttachmentFileUploadInfo
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-9 上午09:58:44
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_ATTACHMENT_DOWNLOAD")
public class AttachmentFileUploadInfoDetailDownload implements Serializable
{
	private static final long serialVersionUID = -7096890509104447725L;

	@Id
     @GeneratedValue(generator = "paymentableGenerator")     
     @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
     @Column(length=32,name="ID_")
     private String id ;
	 
	 @ManyToOne(targetEntity=AttachmentFileUploadInfoDetail.class,cascade = 
	 {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	 ,fetch=FetchType.LAZY)
	 @JoinColumn(name="ATTACHMENT_INFO_DETAIL_ID_",nullable=false)
	 private AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail;
	 
	 @ManyToOne(targetEntity=User.class,cascade = 
	 {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	 ,fetch=FetchType.LAZY) 
	 @JoinColumn(name="DOWNLOAD_USER_ID_",nullable=false)
	 private User downloadUser;
	 
	 @Column(length=32,name="DOWNLOAD_TIME_",nullable=false)
	 private String downloadTime;

	 /**
	 * @param id the id to set
	 **/
	
	public void setId(String id) {
		this.id = id;
	}

	
	 /**
	 * id
	 * @return the id
	 * @since 1.0.0
	 **/
	
	public String getId() {
		return id;
	}


	
	 /**
	 * @param attachmentFileUploadInfoDetail the attachmentFileUploadInfoDetail to set
	 **/
	
	public void setAttachmentFileUploadInfoDetail(
			AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail) {
		this.attachmentFileUploadInfoDetail = attachmentFileUploadInfoDetail;
	}


	
	 /**
	 * attachmentFileUploadInfoDetail
	 * @return the attachmentFileUploadInfoDetail
	 * @since 1.0.0
	 **/
	
	public AttachmentFileUploadInfoDetail getAttachmentFileUploadInfoDetail() {
		return attachmentFileUploadInfoDetail;
	}


	
	 /**
	 * @param downloadUser the downloadUser to set
	 **/
	
	public void setDownloadUser(User downloadUser) {
		this.downloadUser = downloadUser;
	}


	
	 /**
	 * downloadUser
	 * @return the downloadUser
	 * @since 1.0.0
	 **/
	
	public User getDownloadUser() {
		return downloadUser;
	}


	
	 /**
	 * @param downloadTime the downloadTime to set
	 **/
	
	public void setDownloadTime(String downloadTime) {
		this.downloadTime = downloadTime;
	}


	
	 /**
	 * downloadTime
	 * @return the downloadTime
	 * @since 1.0.0
	 **/
	
	public String getDownloadTime() {
		return downloadTime;
	}
}
