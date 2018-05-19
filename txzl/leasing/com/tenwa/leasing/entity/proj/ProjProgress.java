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

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;

@Entity
@FieldName(name = "项目建设进度")
@Table(name = "PROJ_PROGRESS")
public class ProjProgress implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@FieldName(name="任务名称")
	@JoinColumn(name="TASKNAME")
	@ManyToOne
	private DictionaryData taskName;
	
	@FieldName(name="开始时间")
	@Column(name="BEGIN_TIME", length=20)	
	private String beginTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="项目id号")
	@JoinColumn(name = "PROJ_id")
	private ProjDevelopInfo projId;
	
	@FieldName(name="结束时间")
	@Column(name="END_TIME", length=20)	
	private String endTime;
	
	@FieldName(name="持续时间")
	@Column(name="DURATION_TIME", length=20)	
	private String durationTime;
	
	@FieldName(name="完工进度")
	@Column(name="SCHEDULE", length=255)	
	private String schedule;
	
	@FieldName(name="已完工项目情况说明")
	@Column(name="COMPLETEDPROJECTCASE", length=2000)	
	private String completedProjectCase;
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
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

	public DictionaryData getTaskName() {
		return taskName;
	}

	public void setTaskName(DictionaryData taskName) {
		this.taskName = taskName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public ProjDevelopInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjDevelopInfo projId) {
		this.projId = projId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
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

	public String getCompletedProjectCase() {
		return completedProjectCase;
	}

	public void setCompletedProjectCase(String completedProjectCase) {
		this.completedProjectCase = completedProjectCase;
	}

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	
	
}
