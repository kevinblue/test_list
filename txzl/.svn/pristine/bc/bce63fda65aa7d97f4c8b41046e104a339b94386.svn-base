
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity.message
 * 文件名：         MessageBase.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-2-下午01:49:29
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity.message;


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
 * 类名称：     MessageBase
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-12-2 下午01:49:29
 * 修改备注：
 * @version 1.0.0
 **/

@FieldName(name="短信配置")
@Entity
@Table(name="MSG_CONFIG")
public class MsgConfig {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@FieldName(name="短信类型")
    @Column(length=40,name="MSG_TYPE",nullable=true)
    private String msgType ;
	
	@FieldName(name="短信内容")
	@Column(name="MSG_CONTENT",nullable=true,length=500)
	private String  msgContent;
	
	@FieldName(name="发送时间类型")
	@Column(name="MSG_OBJECT",nullable=true,length=32)
	private String msgObject;
	
	@FieldName(name="延迟发送天数")
	@Column(name="DELAY_DAY")
	private String delayDay;
	
	public String getDelayDay() {
		return delayDay;
	}
	public void setDelayDay(String delayDay) {
		this.delayDay = delayDay;
	}
	@FieldName(name="状态")
	@Column(name="MSG_STATUS",nullable=true,length=32)
	private String msgStatus;
	
	@FieldName(name="触发方式")
	@Column(name="TRIGGER_MODE",nullable=true,length=32)
	private String triggerMode;
	
	@FieldName(name="SQL")
	@Column(name="MSG_SQL",nullable=true,length=1000)
	private String msgSql ;
	
	@FieldName(name="发送记录关联关键字")
	@Column(name="MSG_SQL_PARAM",length=100)
	private String msgSqlParam;
	
	@FieldName(name="调度持续时间(天)")
	@Column(name="DURABLEDAYS")
	private Integer durabledays;
	
	public Integer getDurabledays() {
		return durabledays;
	}
	public void setDurabledays(Integer durabledays) {
		this.durabledays = durabledays;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPreviousFireTime() {
		return previousFireTime;
	}
	public void setPreviousFireTime(String previousFireTime) {
		this.previousFireTime = previousFireTime;
	}
	public String getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public String getJobname() {
		return jobname;
	}
	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	public String getTriggername() {
		return triggername;
	}
	public void setTriggername(String triggername) {
		this.triggername = triggername;
	}
	@FieldName(name="调度开始时间")
	@Column(name="START_DATE")
	private String startDate;
	
	@FieldName(name="调度结束时间")
	@Column(name="END_DATE")
	private String endDate;
	
	@FieldName(name="调度上次执行时间")
	@Column(name="PREVIOUS_FIRE_TIME")
	private String previousFireTime;
	
	@FieldName(name="调度下次执行时间")
	@Column(name="NEXT_FIRE_TIME")
	private String nextFireTime;
	
	
	@FieldName(name="定时任务名")
	@Column(name="JOBNAME",length=64)
	private String jobname;
	
	@FieldName(name="定时任务时间名")
	@Column(name="TRIGGERNAME",length=64)
	private String triggername;
	
	@FieldName(name="短信发送时间")
	@Column(name="MSG_SEND_TIME",length=32)
	private String msgSendTime;
	
	@FieldName(name="定时时间表达式")
	@Column(name="CRON_EXPRESSION",length=32,nullable=true)
	private String cronExpression;
	
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getIsOverdateExecute() {
		return isOverdateExecute;
	}
	public void setIsOverdateExecute(String isOverdateExecute) {
		this.isOverdateExecute = isOverdateExecute;
	}
	@FieldName(name="是否过期执行")
	@Column(name="IS_OVERDATE_EXECUTE",length=32,nullable=true)
	private String isOverdateExecute;
	
	public String getMsgSendTime() {
		return msgSendTime;
	}
	public void setMsgSendTime(String msgSendTime) {
		this.msgSendTime = msgSendTime;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgObject() {
		return msgObject;
	}
	public void setMsgObject(String msgObject) {
		this.msgObject = msgObject;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	public String getTriggerMode() {
		return triggerMode;
	}
	public void setTriggerMode(String triggerMode) {
		this.triggerMode = triggerMode;
	}
	public String getMsgSql() {
		return msgSql;
	}
	public void setMsgSql(String msgSql) {
		this.msgSql = msgSql;
	}
	public String getMsgSqlParam() {
		return msgSqlParam;
	}
	public void setMsgSqlParam(String msgSqlParam) {
		this.msgSqlParam = msgSqlParam;
	}
	public User getModificator() {
		return modificator;
	}
	public void setModificator(User modificator) {
		this.modificator = modificator;
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
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
}
