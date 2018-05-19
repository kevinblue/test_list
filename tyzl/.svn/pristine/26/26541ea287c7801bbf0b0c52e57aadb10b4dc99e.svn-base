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
@Table(name="T_JBPM_SIGNATURE_USERS")
public class JBPMWorkflowSignatureUser implements Serializable 
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
    @JoinColumn(name="SIGNATURE_ACTOR_USER_ID_",nullable=false)
    private User signatureActor;
	
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="SIGNATURED_ACTOR_USER_ID_",nullable=true)
	private User signaturedActor;
	
    
	@Column(name="PROCESSED_ADVISE_",length=1000)
	private String processedAdvise;
	@Column(name="SIGNATURE_ADVISE_",length=1000)
	private String signatureAdvise;
	@Column(name="SIGNATURED_ADVISE_",length=1000)
	private String signaturedAdvise;
	
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
	 * @param signatureActor the signatureActor to set
	 **/
	
	public void setSignatureActor(User signatureActor) {
		this.signatureActor = signatureActor;
	}



	
	 /**
	 * signatureActor
	 * @return the signatureActor
	 * @since 1.0.0
	 **/
	
	public User getSignatureActor() {
		return signatureActor;
	}



	
	 /**
	 * @param signaturedActor the signaturedActor to set
	 **/
	
	public void setSignaturedActor(User signaturedActor) {
		this.signaturedActor = signaturedActor;
	}



	
	 /**
	 * signaturedActor
	 * @return the signaturedActor
	 * @since 1.0.0
	 **/
	
	public User getSignaturedActor() {
		return signaturedActor;
	}



	
	 /**
	 * @param signatureAdvise the signatureAdvise to set
	 **/
	
	public void setSignatureAdvise(String signatureAdvise) {
		this.signatureAdvise = signatureAdvise;
	}



	
	 /**
	 * signatureAdvise
	 * @return the signatureAdvise
	 * @since 1.0.0
	 **/
	
	public String getSignatureAdvise() {
		return signatureAdvise;
	}



	
	 /**
	 * @param signaturedAdvise the signaturedAdvise to set
	 **/
	
	public void setSignaturedAdvise(String signaturedAdvise) {
		this.signaturedAdvise = signaturedAdvise;
	}



	
	 /**
	 * signaturedAdvise
	 * @return the signaturedAdvise
	 * @since 1.0.0
	 **/
	
	public String getSignaturedAdvise() {
		return signaturedAdvise;
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
