
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         ActivitityDetail.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-9-27-上午10:21:20
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jbpm.pvm.internal.repository.DeploymentImpl;
import org.json.JSONObject;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


 /**
 * 类名称：     ActivitityDetail
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-9-27 上午10:21:20
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_JBPM_ACTIVITY_DETAIL")
public class ActivityDetail {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@ManyToOne(targetEntity=DeploymentImpl.class)
	@JoinColumn(name="JBPM4_DEPLOYMENT_DBID_")
	private DeploymentImpl deploymentImpl;
	@Column(name="ACTIVITY_NAME_",nullable=false)
	private String activityName;
	@Column(name="ACTIVITY_TYPE_",nullable=false)
	private String activityType;
	@Column(name="ACTIVITY_ACTION_")
	private String activityAction;
	@Column(name="ATTACHMENT_TYPE_")
	private String attachmentType;
	@Column(name="ATTACHMENT_TYPE_IDS_")
	private String attachmentTypeIds;
	@Column(name="ACTOR_TYPE_")
	private String actorType;
	@Column(name="ACTOR_VALUE_",length=2000)
	private String actorValue;
	@Column(name="ACTOR_VALUE_DISPLAY_",length=2000)
	private String displayActorValue;
	@Column(name="FORM_PATH_")
	private String formPath;
	@Column(name="FORM_TITLE_")
	private String formTitle;
	@Column(name="OPERATION_BUTTONS_")
	private String operationButtons;
	@Column(name="IS_NEED_ADVISE_",length=5)
	private String isNeedAdvise;
	@Column(name="IS_AUTO_ACTIVITY_",length=5)
	private String isAutoActivity;
	@Column(name="BACK_ACTIVITIES_")
	private String backActivities;
	@Column(name="IS_SHOW_ON_APP",length=5)
	private String isShowOnApp;
	
	@Column(name="EXC_MES_ACTIVITIES")
	private String excludeMessageActivities;
	@Column(name="EXC_MES_ACTIVITIES_DESC")
	private String excludeMessageActivitiesDesc;
	//处理方式   1.单人处理,2.多人全部,3.多人任一,4.N人处理
	/**
	 *  { name:'单人处理',value:'Single'},
		{ name:'多人全部',value:'AllPassed'},
		{ name:'多人任一',value:'OnePassed'},
		{ name:'N人处理',value:'NPassed'}
	 * ***/
	@Column(name="DEAL_METHOD_")
	private String dealMethod;
	@Column(name="PASSED_COUNT_")
	private String passedCount;
    //自动传阅
	@Column(name="AUTO_READ_ACTOR_TYPE_")
	private String autoReadActorType;
	@Column(name="AUTO_READ_ACTOR_VALUE_",length=2000)
	private String autoReadActorValue;
	@Column(name="AUTO_READ_DISPLAY_",length=2000)
	private String autoReadDisplayActorValue;
	//自动会签
	@Column(name="AUTO_SIGNATURE_ACTOR_TYPE_")
	private String autoSignatureActorType;
	@Column(name="AUTO_SIGNATURE_ACTOR_VALUE_",length=2000)
	private String autoSignatureActorValue;
	@Column(name="AUTO_SIGNATURE_DISPLAY_",length=2000)
	private String autoSignatureDisplayActorValue;
	//是否检查附件完整性
	@Column(columnDefinition="INT DEFAULT 0",name="IS_ATTACHMENT_CHECKED_",length=1,nullable=false)
	private Boolean isAttachmentChecked;
	
	//是否意见互斥
	@Column(columnDefinition="INT DEFAULT 0",name="IS_NEED_ADVISE_EXCLUDE_",length=1,nullable=false)
	private Boolean isNeedAdviseExclude;
	//当前流程节点互斥文件类型
	
	//配置条件路由
	@Column(name="CONDITION_ROUTE_TYPE_")
	private String conditionRouteType;
	@Column(name="CONDITION_ROUTE_VALUE_",length=3000)
	private String conditionRouteValue;
	/*
	   PAGECALLBACK("pageCallBack","页面回调函数"),//默认 workflowNextRouteCallBack
	   FIELD("field","表单域"),
	   EXPRESSION("expression","表达式"),
	   SQL("sql","自定义");
	*/
	//节点相关其他属性
	@Column(name="ACTIVITY_KEY_")
	private String activityKey; 
	@Column(name="ACTIVITY_POSITION_")
	private Integer activityPosition;
	//信息提醒
	@Column(name="MESSAGE_TYPES_")
	private String messageTypes;
	@Column(name="MESSAGE_CONTENT_")
	private String messageContent;
	//传阅信息提醒
	@Column(name="READ_MESSAGE_TYPES_")
	private String readMessageTypes;
	@Column(name="READ_MESSAGE_CONTENT_")
	private String readMessageContent;
	//会签信息提醒
	@Column(name="SIGNATURE_MESSAGE_TYPES_")
	private String signatureMessageTypes;
	@Column(name="SIGNATURE_MESSAGE_CONTENT_")
	private String signatureMessageContent;

	//是否有效
	@Column(name="ENABLED_",columnDefinition="INT DEFAULT 1")
	private Integer enabled;
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
	
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getMessageTypes() {
		return messageTypes;
	}
	public void setMessageTypes(String messageTypes) {
		this.messageTypes = messageTypes;
	}
	public String getDisplayActorValue() {
		return displayActorValue;
	}
	public String getAutoReadDisplayActorValue() {
		return autoReadDisplayActorValue;
	}
	public String getAutoSignatureDisplayActorValue() {
		return autoSignatureDisplayActorValue;
	}
	public void setDisplayActorValue(String displayActorValue) {
		this.displayActorValue = displayActorValue;
	}
	public void setAutoReadDisplayActorValue(String autoReadDisplayActorValue) {
		this.autoReadDisplayActorValue = autoReadDisplayActorValue;
	}
	public void setAutoSignatureDisplayActorValue(
			String autoSignatureDisplayActorValue) {
		this.autoSignatureDisplayActorValue = autoSignatureDisplayActorValue;
	}
	
	public User getCreator() {
		return creator;
	}
	public String getCreateDate() {
		return createDate;
	}
	public User getModificator() {
		return modificator;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setModificator(User modificator) {
		this.modificator = modificator;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getAutoReadActorType() {
		return autoReadActorType;
	}
	public void setAutoReadActorType(String autoReadActorType) {
		this.autoReadActorType = autoReadActorType;
	}
	public String getAutoReadActorValue() {
		return autoReadActorValue;
	}
	public void setAutoReadActorValue(String autoReadActorValue) {
		this.autoReadActorValue = autoReadActorValue;
	}
	public String getAutoSignatureActorType() {
		return autoSignatureActorType;
	}
	public void setAutoSignatureActorType(String autoSignatureActorType) {
		this.autoSignatureActorType = autoSignatureActorType;
	}
	public String getAutoSignatureActorValue() {
		return autoSignatureActorValue;
	}
	public void setAutoSignatureActorValue(String autoSignatureActorValue) {
		this.autoSignatureActorValue = autoSignatureActorValue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DeploymentImpl getDeploymentImpl() {
		return deploymentImpl;
	}
	public void setDeploymentImpl(DeploymentImpl deploymentImpl) {
		this.deploymentImpl = deploymentImpl;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getActorType() {
		return actorType;
	}
	public void setActorType(String actorType) {
		this.actorType = actorType;
	}
	public String getActorValue() {
		return actorValue;
	}
	public void setActorValue(String actorValue) {
		this.actorValue = actorValue;
	}
	public String getFormPath() {
		return formPath;
	}
	public void setFormPath(String formPath) {
		this.formPath = formPath;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public String getOperationButtons() {
		return operationButtons;
	}
	public void setOperationButtons(String operationButtons) {
		this.operationButtons = operationButtons;
	}
	public String getIsNeedAdvise() {
		return isNeedAdvise;
	}
	public void setIsNeedAdvise(String isNeedAdvise) {
		this.isNeedAdvise = isNeedAdvise;
	}
	
	 /**
	 * @param dealMethod the dealMethod to set
	 **/
	
	public void setDealMethod(String dealMethod) {
		this.dealMethod = dealMethod;
	}
	
	 /**
	 * dealMethod
	 * @return the dealMethod
	 * @since 1.0.0
	 **/
	
	public String getDealMethod() {
		return dealMethod;
	}
	/**
	 * dealMethod
	 * @return the dealMethod
	 * @since 1.0.0
	 **/
	@Transient
	public String getDealMethodChineseName() 
	{
		String dealMethodChineseName = "";
		if("Single".equals(dealMethod)){dealMethodChineseName="单人处理";};
		if("AllPassed".equals(dealMethod)){dealMethodChineseName="多人全部";};
		if("OnePassed".equals(dealMethod)){dealMethodChineseName="多人任一";};
		if("NPassed".equals(dealMethod)){dealMethodChineseName="N人处理";};
		return dealMethodChineseName;
	}
	
	 /**
	 * @param backActivities the backActivities to set
	 **/
	
	public void setBackActivities(String backActivities) {
		this.backActivities = backActivities;
	}
	
	 /**
	 * backActivities
	 * @return the backActivities
	 * @since 1.0.0
	 **/
	
	public String getBackActivities() {
		return backActivities;
	}
	
	 /**
	 * @param activityAction the activityAction to set
	 **/
	
	public void setActivityAction(String activityAction) {
		this.activityAction = activityAction;
	}
	
	 public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	/**
	 * activityAction
	 * @return the activityAction
	 * @since 1.0.0
	 **/
	
	public String getActivityAction() {
		return activityAction;
	}
	
	 /**
	 * @param passedCount the passedCount to set
	 **/
	
	public void setPassedCount(String passedCount) {
		this.passedCount = passedCount;
	}
	
	 /**
	 * passedCount
	 * @return the passedCount
	 * @since 1.0.0
	 **/
	
	public String getPassedCount() {
		return passedCount;
	}
	
	 /**
	 * @param isAutoActivity the isAutoActivity to set
	 **/
	
	public void setIsAutoActivity(String isAutoActivity) {
		this.isAutoActivity = isAutoActivity;
	}
	
	 /**
	 * isAutoActivity
	 * @return the isAutoActivity
	 * @since 1.0.0
	 **/
	
	public String getIsAutoActivity() {
		return isAutoActivity;
	}
	
	 /**
	 * @param enabled the enabled to set
	 **/
	
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	public String getAttachmentTypeIds() {
		return attachmentTypeIds;
	}
	public void setAttachmentTypeIds(String attachmentTypeIds) {
		this.attachmentTypeIds = attachmentTypeIds;
	}
	
	public String getConditionRouteType() {
		return conditionRouteType;
	}
	public void setConditionRouteType(String conditionRouteType) {
		this.conditionRouteType = conditionRouteType;
	}
	public String getConditionRouteValue() {
		return conditionRouteValue;
	}
	public void setConditionRouteValue(String conditionRouteValue) {
		this.conditionRouteValue = conditionRouteValue;
	}
	@Transient
	public JSONObject getJsonObjectActivityDetail(ActivityDetail activityDetail) throws Exception{
		String currentState =  "opened";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", activityDetail.getId());
		jsonObj.put("text", activityDetail.getActivityName()+"（"+activityDetail.getActivityType()+"）");
		jsonObj.put("iconCls", "icon-home");
		jsonObj.put("state", currentState);
		return jsonObj;
	}
	public void setIsAttachmentChecked(Boolean isAttachmentChecked) {
		this.isAttachmentChecked = isAttachmentChecked;
	}
	public Boolean getIsAttachmentChecked() {
		return isAttachmentChecked;
	}
	public void setActivityPosition(Integer activityPosition) {
		this.activityPosition = activityPosition;
	}
	public Integer getActivityPosition() {
		return activityPosition;
	}
	public void setActivityKey(String activityKey) {
		this.activityKey = activityKey;
	}
	public String getActivityKey() {
		return activityKey;
	}
	public String getReadMessageTypes() {
		return readMessageTypes;
	}
	public void setReadMessageTypes(String readMessageTypes) {
		this.readMessageTypes = readMessageTypes;
	}
	public String getReadMessageContent() {
		return readMessageContent;
	}
	public void setReadMessageContent(String readMessageContent) {
		this.readMessageContent = readMessageContent;
	}
	public String getSignatureMessageTypes() {
		return signatureMessageTypes;
	}
	public void setSignatureMessageTypes(String signatureMessageTypes) {
		this.signatureMessageTypes = signatureMessageTypes;
	}
	public String getSignatureMessageContent() {
		return signatureMessageContent;
	}
	public void setSignatureMessageContent(String signatureMessageContent) {
		this.signatureMessageContent = signatureMessageContent;
	}
	public String getExcludeMessageActivities() {
		return excludeMessageActivities;
	}
	public void setExcludeMessageActivities(String excludeMessageActivities) {
		this.excludeMessageActivities = excludeMessageActivities;
	}
	public String getExcludeMessageActivitiesDesc() {
		return excludeMessageActivitiesDesc;
	}
	public void setExcludeMessageActivitiesDesc(String excludeMessageActivitiesDesc) {
		this.excludeMessageActivitiesDesc = excludeMessageActivitiesDesc;
	}
	public Boolean getIsNeedAdviseExclude() {
		return isNeedAdviseExclude;
	}
	public void setIsNeedAdviseExclude(Boolean isNeedAdviseExclude) {
		this.isNeedAdviseExclude = isNeedAdviseExclude;
	}
	public String getIsShowOnApp() {
		return isShowOnApp;
	}
	public void setIsShowOnApp(String isShowOnApp) {
		this.isShowOnApp = isShowOnApp;
	}
	
}
