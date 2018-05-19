package com.tenwa.leasing.entity.proj;

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
 * ProjInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "项目评审会议记录")
@Table(name = "PROJ_MEETING_INFO")
public class ProjMeetingInfo  {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@FieldName(name = "项目")
	@ManyToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name = "流程编号")
	@Column(name = "DOC_ID")
	private String docId;
	
	@Column(name = "MEETING_DATE", length = 40)
	@FieldName(name="上会时间")
	private String meetingDate;
	
	@Column(name = "MEETING_PLACE", length = 400)
	@FieldName(name="上会地点")
	private String meetingPlace;
	
	@Column(name = "MEETING_CHAIRMAN", length = 40)
	@FieldName(name="主持人")
	private String meetingChairman;
	
	@Column(name = "MEETING_TYPE", length = 40)
	@FieldName(name="会议方式")
	private String meetingType;
	
	@Column(name = "APPROVAL_CONTENT", length = 500)
	@FieldName(name="审批内容")
	private String approvalContent;
	
	@Column(name = "APPROVAL_PERSON", length = 500)
	@FieldName(name="参加审批人员")
	private String approvalPerson;
	
	@Column(name = "ATTENDANCE", length = 500)
	@FieldName(name="列席会议人员")
	private String attendance;
	
	@Column(name = "ABSENT", length = 500)
	@FieldName(name="缺席会议人员")
	private String absent;
	
	@Column(name = "RECORD", length = 500)
	@FieldName(name="会议记录")
	private String record;
	
	@Column(name = "CONCLUSION", length = 500)
	@FieldName(name="会议结论")
	private String conclusion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;
	
	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name = "MODIFY_DATE", length = 40)
	private String modifyDate;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingPlace() {
		return meetingPlace;
	}

	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}

	public String getMeetingChairman() {
		return meetingChairman;
	}

	public void setMeetingChairman(String meetingChairman) {
		this.meetingChairman = meetingChairman;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public String getApprovalContent() {
		return approvalContent;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	public String getApprovalPerson() {
		return approvalPerson;
	}

	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public String getAbsent() {
		return absent;
	}

	public void setAbsent(String absent) {
		this.absent = absent;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
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