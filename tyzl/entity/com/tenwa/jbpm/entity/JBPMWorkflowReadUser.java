package com.tenwa.jbpm.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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

import com.tenwa.business.entity.User;

@Entity
@Table(name="T_JBPM_READ_USERS")
public class JBPMWorkflowReadUser implements Serializable 
{
	private static final long serialVersionUID = -6297218341440605242L;
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id;
	
	@ManyToOne(targetEntity=JBPMWorkflowHistoryInfo.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="JBPMWORKFLOWHISTORYINFO_ID_",nullable=false)
    private JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo;
	
	@ManyToOne(targetEntity=User.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="PLAN_ACTOR_USER_ID_",nullable=false)
    private User planActor;
	
	@ManyToOne(targetEntity=User.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="READ_ACTOR_USER_ID_",nullable=false)
    private User readActor;
	
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="READED_ACTOR_USER_ID_",nullable=true)
	private User readedActor;
	
    
	@Column(name="PROCESSED_ADVISE_",length=1000)
	private String processedAdvise;
	@Column(name="READ_ADVISE_",length=1000)
	private String readAdvise;
	@Column(name="READED_ADVISE_",length=1000)
	private String readedAdvise;
	
	@Column(name="START_TIME_",length=20)
	private String startTime;
	
	@Column(name="END_TIME_",length=20)
	private String endTime;
	 /**
	 * @param jbpmWorkflowHistoryInfo the jbpmWorkflowHistoryInfo to set
	 **/
	
	public void setJbpmWorkflowHistoryInfo(JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) {
		this.jbpmWorkflowHistoryInfo = jbpmWorkflowHistoryInfo;
	}


	
	 /**
	 * jbpmWorkflowHistoryInfo
	 * @return the jbpmWorkflowHistoryInfo
	 * @since 1.0.0
	 **/
	
	public JBPMWorkflowHistoryInfo getJbpmWorkflowHistoryInfo() {
		return jbpmWorkflowHistoryInfo;
	}



	
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
	 * @param processedAdvise the processedAdvise to set
	 **/
	
	public void setProcessedAdvise(String processedAdvise) {
		this.processedAdvise = processedAdvise;
	}



	
	 /**
	 * processedAdvise
	 * @return the processedAdvise
	 * @since 1.0.0
	 **/
	
	public String getProcessedAdvise() {
		return processedAdvise;
	}

	
	 /**
	 * @param startTime the startTime to set
	 **/
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	
	 /**
	 * startTime
	 * @return the startTime
	 * @since 1.0.0
	 **/
	
	public String getStartTime() {
		return startTime;
	}



	
	 /**
	 * @param endTime the endTime to set
	 **/
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	
	 /**
	 * endTime
	 * @return the endTime
	 * @since 1.0.0
	 **/
	
	public String getEndTime() {
		return endTime;
	}



	
	 /**
	 * @param readActor the readActor to set
	 **/
	
	public void setReadActor(User readActor) {
		this.readActor = readActor;
	}



	
	 /**
	 * readActor
	 * @return the readActor
	 * @since 1.0.0
	 **/
	
	public User getReadActor() {
		return readActor;
	}



	
	 /**
	 * @param readedActor the readedActor to set
	 **/
	
	public void setReadedActor(User readedActor) {
		this.readedActor = readedActor;
	}



	
	 /**
	 * readedActor
	 * @return the readedActor
	 * @since 1.0.0
	 **/
	
	public User getReadedActor() {
		return readedActor;
	}



	
	 /**
	 * @param readAdvise the readAdvise to set
	 **/
	
	public void setReadAdvise(String readAdvise) {
		this.readAdvise = readAdvise;
	}



	
	 /**
	 * readAdvise
	 * @return the readAdvise
	 * @since 1.0.0
	 **/
	
	public String getReadAdvise() {
		return readAdvise;
	}



	
	 /**
	 * @param readedAdvise the readedAdvise to set
	 **/
	
	public void setReadedAdvise(String readedAdvise) {
		this.readedAdvise = readedAdvise;
	}



	
	 /**
	 * readedAdvise
	 * @return the readedAdvise
	 * @since 1.0.0
	 **/
	
	public String getReadedAdvise() {
		return readedAdvise;
	}



	
	 /**
	 * @param planActor the planActor to set
	 **/
	
	public void setPlanActor(User planActor) {
		this.planActor = planActor;
	}



	
	 /**
	 * planActor
	 * @return the planActor
	 * @since 1.0.0
	 **/
	
	public User getPlanActor() {
		return planActor;
	}

}
