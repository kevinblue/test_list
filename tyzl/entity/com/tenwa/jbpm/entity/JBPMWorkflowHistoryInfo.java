
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         JBPMWorkflowHistoryInfo.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-10-23-上午09:28:41
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.entity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
import org.jbpm.pvm.internal.repository.DeploymentImpl;

import com.tenwa.business.entity.AttachmentFileUploadInfoDetail;
import com.tenwa.business.entity.User;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.annotation.FieldName;


 /**
 * 类名称：     JBPMWorkflowHistoryInfo
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-10-23 上午09:28:41
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_JBPM_WORKFLOW_INFO")
public class JBPMWorkflowHistoryInfo implements Comparable<JBPMWorkflowHistoryInfo>
{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	@OneToOne(targetEntity=HistoryTaskInstanceImpl.class,fetch=FetchType.LAZY)
	@JoinColumn(name="JBPM4_HIST_ACTINST_DBID_")
	private HistoryTaskInstanceImpl historyTaskInstanceImpl ;
	@ManyToOne(targetEntity=HistoryTaskInstanceImpl.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PREV_JBPM4_HIST_ACTINST_DBID_")
	private HistoryTaskInstanceImpl previousHistoryTaskInstanceImpl ;
	@ManyToOne(targetEntity=HistoryProcessInstanceImpl.class,fetch=FetchType.LAZY)
	@JoinColumn(name="JBPM4_HIST_PROCINST_DBID_",referencedColumnName="DBID_",nullable=false)
	private HistoryProcessInstanceImpl historyProcessInstanceImpl;
	@ManyToOne(targetEntity=DeploymentImpl.class,fetch=FetchType.LAZY)
	@JoinColumn(name="JBPM4_DEPLOYMENT_DBID_",nullable=false)
	private DeploymentImpl deploymentImpl;
	/***key info***/
	@Column(name="KEY_ONE_")
	private String keyOne;
	@Column(name="KEY_TWO_")
	private String keyTwo;
	@Column(name="KEY_THREE_")
	private String keyThree;
	@Column(name="KEY_FOUR_")
	private String keyFour;
	@Column(name="KEY_FIVE_")
	private String keyFive;
	@Column(name="KEY_SIX_")
	private String keySix;
	@Column(name="KEY_SEVEN_")
	private String keySeven;
	@Column(name="KEY_EIGHT_")
	private String keyEight;
	@Column(name="KEY_NINE_")
	private String keyNine;
	@Column(name="KEY_TEN_")
	private String keyTen;
	
	@Column(name="PROCESS_FORM_PATH_")
	private String processFormPath;
	@Column(name="PROCESS_FORM_TITLE_")
	private String processFormTitle;
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="PROCESSED_FORM_VARIABLES_", nullable=false)
	private String  processedFormVariables;	
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="PROCESSED_FORM_VALUES_", nullable=false)
	private String  processedFormValues;
	@Column(name="WORKFLOW_NAME_",nullable=false)
    private String workflowName;
	@Column(name="WORKFLOW_DISPLAY_NAME_",nullable=false)
	private String workflowDisplayName;
	@Column(name="DRAFT_UUID_")
	private String draftUUID;
	@Column(name="PROCESSINSTANCE_STATE_",length=50)
	private String processInstanceState;
	@Column(name="PROCESSDEFINITION_ID_",nullable=false)
	private String processDefinitionId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REQUEST_INITIATOR_ID_")
	private User requestInitiatorUser;
	
	@Column(name="REQUEST_INITIATOR_",nullable=false)
	private String requestInitiator;
	@Column(name="REQUEST_INITIATOR_REAL_NAME_",nullable=false)
	private String requestInitiatorRealName;
	/*@Column(name="PARALLEL_ACTIVITY_DBIDS_")
	private String parallelActivityDBIDs; */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACTIVITY_DETAIL_ID_")
	private ActivityDetail activityDetail;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BACKED_HISTORY_INFO_")
	private JBPMWorkflowHistoryInfo backedHistoryInfo;
	
	@OneToMany(mappedBy="backedHistoryInfo",fetch = FetchType.LAZY)
	private Set<JBPMWorkflowHistoryInfo> backHistoryInfos = new HashSet<JBPMWorkflowHistoryInfo>();
	
	@Column(name="FROM_BUTTONTEXT_TO_COME_")
	private String fromButtonTextToCome;
	
	@Column(name="HISTORY_ACTIVITY_NAME_")
	private String historyActivityName;
	
	@Column(name="IS_REMOVED_DRAFT_")
	private Boolean isRemovedDraft;
	@Column(name="IS_READED_")
	private Boolean isReaded;
	
	@FieldName(name="流程退回模式")
	@Column(name="BACK_MODEL")
	private String backModel;
	
	public Boolean getIsRemovedDraft() {
		return isRemovedDraft;
	}
	public void setIsRemovedDraft(Boolean isRemovedDraft) {
		this.isRemovedDraft = isRemovedDraft;
	}
	public Boolean getIsReaded() {
		return isReaded;
	}
	public void setIsReaded(Boolean isReaded) {
		this.isReaded = isReaded;
	}
	//@Column(name="START_TASK_NAMES_")
	//存放当前活动节点
	@OneToMany(mappedBy="firstedProcessInstanceHistoryInfo",fetch = FetchType.LAZY)
	private Set<HistoryTaskInstanceImpl> firstHistoryTaskInstanceImpls = new HashSet<HistoryTaskInstanceImpl>();
	//存放当前活动节点
	@OneToMany(mappedBy="activedProcessInstanceHistoryInfo",fetch = FetchType.LAZY)
	private Set<HistoryTaskInstanceImpl> activeHistoryTaskInstanceImpls = new HashSet<HistoryTaskInstanceImpl>();
	
	//存放所有实例节点
	@OneToMany(mappedBy="processInstanceHistoryInfo",fetch = FetchType.LAZY)
	private Set<HistoryTaskInstanceImpl> historyTaskInstanceImpls = new HashSet<HistoryTaskInstanceImpl>();
	
	//存放结束节点信息
	@ManyToOne(targetEntity=HistoryTaskInstanceImpl.class,fetch=FetchType.LAZY)
	@JoinColumn(name="JBPM4_HIST_ACTINST_LAST_DBID_")
	@Cascade(CascadeType.ALL)
	private HistoryTaskInstanceImpl lastHistoryTaskInstanceImpl ;
	
    /*与JBPMWorkflowHistoryInfoUser的一对多关系*/
	@OneToMany(mappedBy="jbpmWorkflowHistoryInfo",fetch=FetchType.LAZY)
    private Set<JBPMWorkflowHistoryInfoUser> jbpmWorkflowHistoryInfoUsers = new HashSet<JBPMWorkflowHistoryInfoUser>();
	
	/*与JBPMWorkflowSignatureUser的一对多关系*/
	@OneToMany(mappedBy="jbpmWorkflowHistoryInfo",fetch=FetchType.LAZY)
	private Set<JBPMWorkflowSignatureUser> jbpmWorkflowSignatureUsers = new HashSet<JBPMWorkflowSignatureUser>();
	/*与JBPMWorkflowReadUser的一对多关系*/
	@OneToMany(mappedBy="jbpmWorkflowHistoryInfo",fetch=FetchType.LAZY)
	private Set<JBPMWorkflowReadUser> jbpmWorkflowReadUsers = new HashSet<JBPMWorkflowReadUser>();
	/*与AttachmentFileUploadInfoDetail的一对多关系*/
	@OneToMany(mappedBy="jbpmWorkflowHistoryInfo",fetch=FetchType.LAZY)
	@OrderBy("uploadTime DESC")
	private Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails = new HashSet<AttachmentFileUploadInfoDetail>();
	
	@Transient
	private boolean exclusionMessageFlag;
	
	@Transient
	public Map<String,String> getDetailDataMap()
	{
		Map<String,String> detailDataMap = JBPMUtil.getVaribalesMapByAllString(this.getProcessedFormVariables(), this.getProcessedFormValues());
		return detailDataMap;
	}
	
	
	
	public boolean isExclusionMessageFlag() {
		return exclusionMessageFlag;
	}
	public void setExclusionMessageFlag(boolean exclusionMessageFlag) {
		this.exclusionMessageFlag = exclusionMessageFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcessFormPath() {
		return processFormPath;
	}
	public void setProcessFormPath(String processFormPath) {
		this.processFormPath = processFormPath;
	}
	public String  getProcessedFormVariables() {
		return processedFormVariables;
	}
	public void setProcessedFormVariables(String  processedFormVariables) {
		this.processedFormVariables = processedFormVariables;
	}
	public String  getProcessedFormValues() {
		return processedFormValues;
	}
	public void setProcessedFormValues(String  processedFormValues) {
		this.processedFormValues = processedFormValues;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public String getDraftUUID() {
		return draftUUID;
	}
	public void setDraftUUID(String draftUUID) {
		this.draftUUID = draftUUID;
	}
	public String getProcessInstanceState() {
		return processInstanceState;
	}
	public void setProcessInstanceState(String processInstanceState) {
		this.processInstanceState = processInstanceState;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getRequestInitiator() {
		return requestInitiator;
	}
	public void setRequestInitiator(String requestInitiator) {
		this.requestInitiator = requestInitiator;
	}
	public String getRequestInitiatorRealName() {
		return requestInitiatorRealName;
	}
	public void setRequestInitiatorRealName(String requestInitiatorRealName) {
		this.requestInitiatorRealName = requestInitiatorRealName;
	}
	
	public HistoryTaskInstanceImpl getHistoryTaskInstanceImpl() {
		return historyTaskInstanceImpl;
	}
	public void setHistoryTaskInstanceImpl(
			HistoryTaskInstanceImpl historyTaskInstanceImpl) {
		this.historyTaskInstanceImpl = historyTaskInstanceImpl;
	}
	public HistoryProcessInstanceImpl getHistoryProcessInstanceImpl() {
		return historyProcessInstanceImpl;
	}
	public void setHistoryProcessInstanceImpl(
			HistoryProcessInstanceImpl historyProcessInstanceImpl) {
		this.historyProcessInstanceImpl = historyProcessInstanceImpl;
	}
	public DeploymentImpl getDeploymentImpl() {
		return deploymentImpl;
	}
	public void setDeploymentImpl(DeploymentImpl deploymentImpl) {
		this.deploymentImpl = deploymentImpl;
	}
	
	
	 /**
	 * @param processFormTitle the processFormTitle to set
	 **/
	
	public void setProcessFormTitle(String processFormTitle) {
		this.processFormTitle = processFormTitle;
	}
	
	 /**
	 * processFormTitle
	 * @return the processFormTitle
	 * @since 1.0.0
	 **/
	
	public String getProcessFormTitle() {
		return processFormTitle;
	}
	
	 /**
	 * @param activityDetail the activityDetail to set
	 **/
	
	public void setActivityDetail(ActivityDetail activityDetail) {
		this.activityDetail = activityDetail;
	}
	
	 /**
	 * activityDetail
	 * @return the activityDetail
	 * @since 1.0.0
	 **/
	
	public ActivityDetail getActivityDetail() {
		return activityDetail;
	}
	
	 /**
	 * @param lastHistoryTaskInstanceImpl the lastHistoryTaskInstanceImpl to set
	 **/
	
	public void setLastHistoryTaskInstanceImpl(
			HistoryTaskInstanceImpl lastHistoryTaskInstanceImpl) {
		this.lastHistoryTaskInstanceImpl = lastHistoryTaskInstanceImpl;
	}
	
	 /**
	 * lastHistoryTaskInstanceImpl
	 * @return the lastHistoryTaskInstanceImpl
	 * @since 1.0.0
	 **/
	
	public HistoryTaskInstanceImpl getLastHistoryTaskInstanceImpl() {
		return lastHistoryTaskInstanceImpl;
	}
	
	 /**
	 * @param jbpmWorkflowHistoryInfoUsers the jbpmWorkflowHistoryInfoUsers to set
	 **/
	
	public void setJbpmWorkflowHistoryInfoUsers(
			Set<JBPMWorkflowHistoryInfoUser> jbpmWorkflowHistoryInfoUsers) {
		this.jbpmWorkflowHistoryInfoUsers = jbpmWorkflowHistoryInfoUsers;
	}
	
	 /**
	 * jbpmWorkflowHistoryInfoUsers
	 * @return the jbpmWorkflowHistoryInfoUsers
	 * @since 1.0.0
	 **/
	
	public Set<JBPMWorkflowHistoryInfoUser> getJbpmWorkflowHistoryInfoUsers() {
		return jbpmWorkflowHistoryInfoUsers;
	}
	
	 /**
	 * @param fromButtonTextToCome the fromButtonTextToCome to set
	 **/
	
	public void setFromButtonTextToCome(String fromButtonTextToCome) {
		this.fromButtonTextToCome = fromButtonTextToCome;
	}
	//yyyy 年 MM 月 dd 日  HH 时 mm 分 ss 秒
	 /**
	 * fromButtonTextToCome
	 * @return the fromButtonTextToCome
	 * @since 1.0.0
	 **/
	
	public String getFromButtonTextToCome() {
		return fromButtonTextToCome;
	}
	
	 /**
	 * @param activedHistoryTaskInstanceImpls the activedHistoryTaskInstanceImpls to set
	 **/
	
	public void setActivedHistoryTaskInstanceImpls(
			Set<HistoryTaskInstanceImpl> activeHistoryTaskInstanceImpls) {
		this.activeHistoryTaskInstanceImpls = activeHistoryTaskInstanceImpls;
	}
	
	 /**
	 * activedHistoryTaskInstanceImpls
	 * @return the activedHistoryTaskInstanceImpls
	 * @since 1.0.0
	 **/
	
	public Set<HistoryTaskInstanceImpl> getActiveHistoryTaskInstanceImpls() {
		return activeHistoryTaskInstanceImpls;
	}
	
	 /**
	 * @param previousHistoryTaskInstanceImpl the previousHistoryTaskInstanceImpl to set
	 **/
	
	public void setPreviousHistoryTaskInstanceImpl(
			HistoryTaskInstanceImpl previousHistoryTaskInstanceImpl) {
		this.previousHistoryTaskInstanceImpl = previousHistoryTaskInstanceImpl;
	}
	
	 /**
	 * previousHistoryTaskInstanceImpl
	 * @return the previousHistoryTaskInstanceImpl
	 * @since 1.0.0
	 **/
	
	public HistoryTaskInstanceImpl getPreviousHistoryTaskInstanceImpl() {
		return previousHistoryTaskInstanceImpl;
	}
	
	 /**
	 * @param jbpmWorkflowSignatureUsers the jbpmWorkflowSignatureUsers to set
	 **/
	
	public void setJbpmWorkflowSignatureUsers(
			Set<JBPMWorkflowSignatureUser> jbpmWorkflowSignatureUsers) {
		this.jbpmWorkflowSignatureUsers = jbpmWorkflowSignatureUsers;
	}
	
	 /**
	 * jbpmWorkflowSignatureUsers
	 * @return the jbpmWorkflowSignatureUsers
	 * @since 1.0.0
	 **/
	
	public Set<JBPMWorkflowSignatureUser> getJbpmWorkflowSignatureUsers() {
		return jbpmWorkflowSignatureUsers;
	}
	
	 /**
	 * @param jbpmWorkflowReadUsers the jbpmWorkflowReadUsers to set
	 **/
	
	public void setJbpmWorkflowReadUsers(Set<JBPMWorkflowReadUser> jbpmWorkflowReadUsers) {
		this.jbpmWorkflowReadUsers = jbpmWorkflowReadUsers;
	}
	
	 /**
	 * jbpmWorkflowReadUsers
	 * @return the jbpmWorkflowReadUsers
	 * @since 1.0.0
	 **/
	
	public Set<JBPMWorkflowReadUser> getJbpmWorkflowReadUsers() {
		return jbpmWorkflowReadUsers;
	}
	
	 /**
	 * @param attachmentFileUploadInfoDetails the attachmentFileUploadInfoDetails to set
	 **/
	
	public void setAttachmentFileUploadInfoDetails(
			Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails) {
		this.attachmentFileUploadInfoDetails = attachmentFileUploadInfoDetails;
	}
	
	 /**
	 * attachmentFileUploadInfoDetails
	 * @return the attachmentFileUploadInfoDetails
	 * @since 1.0.0
	 **/
	
	public Set<AttachmentFileUploadInfoDetail> getAttachmentFileUploadInfoDetails() {
		return attachmentFileUploadInfoDetails;
	}
	
	 /**
	 * @param firstHistoryTaskInstanceImpls the firstHistoryTaskInstanceImpls to set
	 **/
	
	public void setFirstHistoryTaskInstanceImpls(
			Set<HistoryTaskInstanceImpl> firstHistoryTaskInstanceImpls) {
		this.firstHistoryTaskInstanceImpls = firstHistoryTaskInstanceImpls;
	}
	
	 /**
	 * firstHistoryTaskInstanceImpls
	 * @return the firstHistoryTaskInstanceImpls
	 * @since 1.0.0
	 **/
	
	public Set<HistoryTaskInstanceImpl> getFirstHistoryTaskInstanceImpls() {
		return firstHistoryTaskInstanceImpls;
	}
	
	 /**
	 * @param keyOne the keyOne to set
	 **/
	
	public void setKeyOne(String keyOne) {
		this.keyOne = keyOne;
	}
	
	 /**
	 * keyOne
	 * @return the keyOne
	 * @since 1.0.0
	 **/
	
	public String getKeyOne() {
		return keyOne;
	}
	
	 /**
	 * @param keyTwo the keyTwo to set
	 **/
	
	public void setKeyTwo(String keyTwo) {
		this.keyTwo = keyTwo;
	}
	
	 /**
	 * keyTwo
	 * @return the keyTwo
	 * @since 1.0.0
	 **/
	
	public String getKeyTwo() {
		return keyTwo;
	}
	
	 /**
	 * @param keyThree the keyThree to set
	 **/
	
	public void setKeyThree(String keyThree) {
		this.keyThree = keyThree;
	}
	
	 /**
	 * keyThree
	 * @return the keyThree
	 * @since 1.0.0
	 **/
	
	public String getKeyThree() {
		return keyThree;
	}
	
	 /**
	 * @param keyFour the keyFour to set
	 **/
	
	public void setKeyFour(String keyFour) {
		this.keyFour = keyFour;
	}
	
	 /**
	 * keyFour
	 * @return the keyFour
	 * @since 1.0.0
	 **/
	
	public String getKeyFour() {
		return keyFour;
	}
	
	 /**
	 * @param requestInitiatorUser the requestInitiatorUser to set
	 **/
	
	public void setRequestInitiatorUser(User requestInitiatorUser) {
		this.requestInitiatorUser = requestInitiatorUser;
	}
	
	 public String getKeyFive() {
		return keyFive;
	}
	public String getKeySix() {
		return keySix;
	}
	public String getKeySeven() {
		return keySeven;
	}
	public String getKeyEight() {
		return keyEight;
	}
	public String getKeyNine() {
		return keyNine;
	}
	public String getKeyTen() {
		return keyTen;
	}
	public void setKeyFive(String keyFive) {
		this.keyFive = keyFive;
	}
	public void setKeySix(String keySix) {
		this.keySix = keySix;
	}
	public void setKeySeven(String keySeven) {
		this.keySeven = keySeven;
	}
	public void setKeyEight(String keyEight) {
		this.keyEight = keyEight;
	}
	public void setKeyNine(String keyNine) {
		this.keyNine = keyNine;
	}
	public void setKeyTen(String keyTen) {
		this.keyTen = keyTen;
	}
	
	public void setActiveHistoryTaskInstanceImpls(
			Set<HistoryTaskInstanceImpl> activeHistoryTaskInstanceImpls) {
		this.activeHistoryTaskInstanceImpls = activeHistoryTaskInstanceImpls;
	}
	/**
	 * requestInitiatorUser
	 * @return the requestInitiatorUser
	 * @since 1.0.0
	 **/
	
	public User getRequestInitiatorUser() {
		return requestInitiatorUser;
	}
	
	
	@Override
	public int compareTo(JBPMWorkflowHistoryInfo o) {
		return this.id.compareTo(o.getId());
	}
	
	 /**
	 * @param historyTaskInstanceImpls the historyTaskInstanceImpls to set
	 **/
	
	public void setHistoryTaskInstanceImpls(Set<HistoryTaskInstanceImpl> historyTaskInstanceImpls) {
		this.historyTaskInstanceImpls = historyTaskInstanceImpls;
	}
	
	 /**
	 * historyTaskInstanceImpls
	 * @return the historyTaskInstanceImpls
	 * @since 1.0.0
	 **/
	public Set<HistoryTaskInstanceImpl> getHistoryTaskInstanceImpls() {
		return historyTaskInstanceImpls;
	}
	public void setWorkflowDisplayName(String workflowDisplayName) {
		this.workflowDisplayName = workflowDisplayName;
	}
	public String getWorkflowDisplayName() {
		return workflowDisplayName;
	}
	/*@Transient
	public ActivityDetail getStartActivityDetail() {
		this.getDeploymentImpl().
	}*/
	public void setHistoryActivityName(String historyActivityName) {
		this.historyActivityName = historyActivityName;
	}
	public String getHistoryActivityName() {
		return historyActivityName;
	}
	public JBPMWorkflowHistoryInfo getBackedHistoryInfo() {
		return backedHistoryInfo;
	}
	public void setBackedHistoryInfo(JBPMWorkflowHistoryInfo backedHistoryInfo) {
		this.backedHistoryInfo = backedHistoryInfo;
	}
	public Set<JBPMWorkflowHistoryInfo> getBackHistoryInfos() {
		return backHistoryInfos;
	}
	public void setBackHistoryInfos(Set<JBPMWorkflowHistoryInfo> backHistoryInfos) {
		this.backHistoryInfos = backHistoryInfos;
	}
	public String getBackModel() {
		return backModel;
	}
	public void setBackModel(String backModel) {
		this.backModel = backModel;
	}
	
}
