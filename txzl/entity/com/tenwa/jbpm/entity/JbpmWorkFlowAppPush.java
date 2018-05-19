package com.tenwa.jbpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.tenwa.kernal.annotation.FieldName;

/**
 * 
* @ClassName: JbpmWorkFlowAppPush 
* @Description: app推送
* @author zhangc
* @date 2015年6月26日 下午2:39:28 
*
 */
@Entity
@Table(name="T_JBPM_APP_PUSH")
public class JbpmWorkFlowAppPush {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@Column(name="TASK_ID_")
	@Index(name="INX_JBPM_APP_PUSH")
	private String taskId;
	
	@Column(name="IS_PUSH")
	private Boolean isPush;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Boolean getIsPush() {
		return isPush;
	}

	public void setIsPush(Boolean isPush) {
		this.isPush = isPush;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	
}
