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
import org.jbpm.pvm.internal.repository.DeploymentImpl;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name="T_JBPM_WORKFLOW_INFOS_USERS")
public class JBPMWorkflowHistoryInfoUser implements Serializable 
{
	private static final long serialVersionUID = -6297218341440605242L;
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id;
	

	//国药APP提交流程标识字段
	@FieldName(name = "APP提交")
	@Column(name = "SUBMIT_ON_APP", columnDefinition="INT DEFAULT 0", length = 1)
	private Boolean submitOnApp;
	
	@ManyToOne(targetEntity=JBPMWorkflowHistoryInfo.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="JBPMWORKFLOWHISTORYINFO_ID_",nullable=false)
    private JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo;
	
	@ManyToOne(targetEntity=DeploymentImpl.class,fetch=FetchType.LAZY)
	@JoinColumn(name="JBPM4_DEPLOYMENT_DBID_",nullable=false)
	private DeploymentImpl deploymentImpl;
	
	@ManyToOne(targetEntity=User.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="PLAN_ACTOR_USER_ID_",nullable=false)
    private User planActor;
	
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="ACTUAL_ACTOR_USER_ID_",nullable=true)
	private User actualActor;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="ASSIGN_ACTOR_USER_ID_",nullable=true)
	private User assignActor;
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="ASSIGNED_ACTOR_USER_ID_",nullable=true)
	private User assignedActor;
    
	@Column(name="PROCESSED_ADVISE_",length=1000)
	private String processedAdvise;
	
	@ManyToOne
	@FieldName(name="审批结论")
	@JoinColumn(name="PROCESSED_RESULT_")
	private DictionaryData processedResult;
	
	
	@Column(name = "IS_READED_")
	@FieldName(name="已阅？")
	private Boolean isReaded;
	
	@Column(name="ASSIGN_ADVISE_",length=1000)
	private String assignAdvise;
	@Column(name="ASSIGNED_ADVISE_",length=1000)
	private String assignedAdvise;
	
	
	@Column(name="START_TIME_",length=20,nullable=false)
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



	
	 /**
	 * @param actualActor the actualActor to set
	 **/
	
	public void setActualActor(User actualActor) {
		this.actualActor = actualActor;
	}



	
	 /**
	 * actualActor
	 * @return the actualActor
	 * @since 1.0.0
	 **/
	
	public User getActualActor() {
		return actualActor;
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
	 * @param assignedActor the assignedActor to set
	 **/
	
	public void setAssignedActor(User assignedActor) {
		this.assignedActor = assignedActor;
	}



	
	 /**
	 * assignedActor
	 * @return the assignedActor
	 * @since 1.0.0
	 **/
	
	public User getAssignedActor() {
		return assignedActor;
	}



	
	 /**
	 * @param assignActor the assignActor to set
	 **/
	
	public void setAssignActor(User assignActor) {
		this.assignActor = assignActor;
	}



	
	 /**
	 * assignActor
	 * @return the assignActor
	 * @since 1.0.0
	 **/
	
	public User getAssignActor() {
		return assignActor;
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
	 * @param assignAdvise the assignAdvise to set
	 **/
	
	public void setAssignAdvise(String assignAdvise) {
		this.assignAdvise = assignAdvise;
	}



	
	 /**
	 * assignAdvise
	 * @return the assignAdvise
	 * @since 1.0.0
	 **/
	
	public String getAssignAdvise() {
		return assignAdvise;
	}



	
	 /**
	 * @param assignedAdvise the assignedAdvise to set
	 **/
	
	public void setAssignedAdvise(String assignedAdvise) {
		this.assignedAdvise = assignedAdvise;
	}



	
	 /**
	 * assignedAdvise
	 * @return the assignedAdvise
	 * @since 1.0.0
	 **/
	
	public String getAssignedAdvise() {
		return assignedAdvise;
	}



	
	 /**
	 * @param deploymentImpl the deploymentImpl to set
	 **/
	
	public void setDeploymentImpl(DeploymentImpl deploymentImpl) {
		this.deploymentImpl = deploymentImpl;
	}



	
	 /**
	 * deploymentImpl
	 * @return the deploymentImpl
	 * @since 1.0.0
	 **/
	
	public DeploymentImpl getDeploymentImpl() {
		return deploymentImpl;
	}



	public DictionaryData getProcessedResult() {
		return processedResult;
	}



	public void setProcessedResult(DictionaryData processedResult) {
		this.processedResult = processedResult;
	}



	public Boolean getIsReaded() {
		return isReaded;
	}



	public void setIsReaded(Boolean isReaded) {
		this.isReaded = isReaded;
	}



	public Boolean getSubmitOnApp() {
		return submitOnApp;
	}



	public void setSubmitOnApp(Boolean submitOnApp) {
		this.submitOnApp = submitOnApp;
	}
	
	

}
