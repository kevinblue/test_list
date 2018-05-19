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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;

import com.tenwa.jbpm.entity.ActivityDetail;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;

/**
 * 类名称： AttachmentFileUploadInfo 类描述： 创建人： tracywindy 修改人： tracywindy
 * 修改时间：2012-11-9 上午09:58:44 修改备注：
 * 
 * @version 1.0.0
 **/
@Entity
@Table(name = "T_ATTACHMENT_INFO_DETAIL")
public class AttachmentFileUploadInfoDetail implements Serializable {

	private static final long serialVersionUID = 2969449842305751022L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	// 文件信息
	@Column(name = "CHINESE_FILE_NAME_", nullable = false)
	private String chineseFileName;

	@Column(name = "ENCODE_FILE_NAME_", nullable = false)
	private String encodeFileName;

	@Column(name = "FILE_TYPE", nullable = true)
	private String fileType;

	@Column(name = "FILE_SIZE_", nullable = false)
	private long fileSize;

	@Column(name = "REMARK_", nullable = true)
	private String remark;

	// 如果图片的话，对应T_FILE_IMAGES中的ID
	@Column(name = "FILE_IMAGES_ID", nullable = true)
	private String fileImagesId;

	@ManyToOne(targetEntity = AttachmentFileUploadInfo.class, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ATTACHMENT_INFO_ID_", nullable = false)
	private AttachmentFileUploadInfo attachmentFileUploadInfo;

	/** 与文档下载的一对多关系 **/
	@OneToMany(mappedBy = "attachmentFileUploadInfoDetail", fetch = FetchType.LAZY)
	private Set<AttachmentFileUploadInfoDetailDownload> attachmentFileUploadInfoDetailDownloads = new HashSet<AttachmentFileUploadInfoDetailDownload>();

	@ManyToOne(targetEntity = User.class, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "UPLOAD_USER_ID_", nullable = false)
	private User uploadUser;

	@Column(length = 32, name = "UPLOAD_TIME_", nullable = false)
	private String uploadTime;

	@ManyToOne(targetEntity = JBPMWorkflowHistoryInfo.class, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "JBPMWORKFLOWHISTORYINFO_ID_")
	private JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "HISTORYPROCESSINSTANCE_ID_")
	private HistoryProcessInstanceImpl historyProcessInstanceImpl;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITYDETAIL_ID_")
	private ActivityDetail activityDetail;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "JBPMWORKFLOWDESIGNER_ID_")
	private JbpmWorkflowDesigner jbpmWorkflowDesigner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChineseFileName() {
		return chineseFileName;
	}

	public void setChineseFileName(String chineseFileName) {
		this.chineseFileName = chineseFileName;
	}

	public String getEncodeFileName() {
		return encodeFileName;
	}

	public void setEncodeFileName(String encodeFileName) {
		this.encodeFileName = encodeFileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public AttachmentFileUploadInfo getAttachmentFileUploadInfo() {
		return attachmentFileUploadInfo;
	}

	public void setAttachmentFileUploadInfo(AttachmentFileUploadInfo attachmentFileUploadInfo) {
		this.attachmentFileUploadInfo = attachmentFileUploadInfo;
	}

	public Set<AttachmentFileUploadInfoDetailDownload> getAttachmentFileUploadInfoDetailDownloads() {
		return attachmentFileUploadInfoDetailDownloads;
	}

	public void setAttachmentFileUploadInfoDetailDownloads(Set<AttachmentFileUploadInfoDetailDownload> attachmentFileUploadInfoDetailDownloads) {
		this.attachmentFileUploadInfoDetailDownloads = attachmentFileUploadInfoDetailDownloads;
	}

	public User getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(User uploadUser) {
		this.uploadUser = uploadUser;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * @param jbpmWorkflowHistoryInfo
	 *            the jbpmWorkflowHistoryInfo to set
	 **/

	public void setJbpmWorkflowHistoryInfo(JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) {
		this.jbpmWorkflowHistoryInfo = jbpmWorkflowHistoryInfo;
	}

	/**
	 * jbpmWorkflowHistoryInfo
	 * 
	 * @return the jbpmWorkflowHistoryInfo
	 * @since 1.0.0
	 **/

	public JBPMWorkflowHistoryInfo getJbpmWorkflowHistoryInfo() {
		return jbpmWorkflowHistoryInfo;
	}

	public HistoryProcessInstanceImpl getHistoryProcessInstanceImpl() {
		return historyProcessInstanceImpl;
	}

	public JbpmWorkflowDesigner getJbpmWorkflowDesigner() {
		return jbpmWorkflowDesigner;
	}

	public void setHistoryProcessInstanceImpl(HistoryProcessInstanceImpl historyProcessInstanceImpl) {
		this.historyProcessInstanceImpl = historyProcessInstanceImpl;
	}

	public void setJbpmWorkflowDesigner(JbpmWorkflowDesigner jbpmWorkflowDesigner) {
		this.jbpmWorkflowDesigner = jbpmWorkflowDesigner;
	}

	public ActivityDetail getActivityDetail() {
		return activityDetail;
	}

	public void setActivityDetail(ActivityDetail activityDetail) {
		this.activityDetail = activityDetail;
	}

	public String getFileImagesId() {
		return fileImagesId;
	}

	public void setFileImagesId(String fileImagesId) {
		this.fileImagesId = fileImagesId;
	}
}
