
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         JbpmWorkflowDesigner.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-5-下午10:40:37
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.entity;

import java.util.HashSet;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.jbpm.pvm.internal.repository.DeploymentImpl;
import org.json.JSONObject;

import com.tenwa.business.entity.AttachmentFileUploadInfoDetail;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


 /**
 * 类名称：     JbpmWorkflowDesigner
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-5 下午10:40:37
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_JBPM_DESIGNER_TRANSFER")
public class JbpmWorkflowDesigner implements Comparable<JbpmWorkflowDesigner>
{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	@Column(name="DISPLAY_NAME_",nullable=false)
	private String displayName;
	@Column(name="WORKFLOW_NAME_",nullable=false)
	private String workflowName;
	@Column(name="KEY_",nullable=false)
	private String key;
	
	@JoinColumn(name="TYPE_",nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	private DictionaryData type;
	@Column(name="CODE_",nullable=false)
	private String code;
	@Column(nullable=false,name="POSITION_")
	private int position;
	
	public DictionaryData getType() {
		return type;
	}
	public String getCode() {
		return code;
	}
	public int getPosition() {
		return position;
	}
	public void setType(DictionaryData type) {
		this.type = type;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setPosition(int position) {
		this.position = position;
	}
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
	
	@Column(name="VERSION_",nullable=false)
	private int version;
	@Column(name="WORKFLOW_DEFINITION_",nullable=false,unique=true)
	private String workflowDefinition;
	@Column(name="DESCRIPTION_")
	private String description;
	@Column(name="JPDL_VERSION_",nullable=false)//4.3或者4.4
	private String jpdlVersion;
	@Column(name="CREATE_TIME_",nullable=false)
	private String createTime;
	@Column(name="UPDATE_TIME_")
	private String updateTime;
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="DESIGNER_WORKFLOW_JSON_", nullable=false)
	private String  designerWorkflowJson;
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="TRANSFERED_JPDL_XML_", nullable=false)
	private String  transferedJpdlXml;
	@Column(name="DEPLOYED_TIME_")
	private String deployedTime;
	@Column(name="MAX_DOT_X_")
	private Integer maxDotX;
	@Column(name="MAX_DOT_Y_")
	private Integer maxDotY;
	
	
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
	
	
	public Integer getMaxDotX() {
		return maxDotX;
	}
	public Integer getMaxDotY() {
		return maxDotY;
	}
	public void setMaxDotX(Integer maxDotX) {
		this.maxDotX = maxDotX;
	}
	public void setMaxDotY(Integer maxDotY) {
		this.maxDotY = maxDotY;
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
	@OneToMany(mappedBy="jbpmWorkflowDesigner",fetch=FetchType.LAZY)
    private Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails = new HashSet<AttachmentFileUploadInfoDetail>();
	
	@OneToMany(mappedBy="rejectJbpmWorkflowDesigner",fetch=FetchType.LAZY)
	private Set<WorkflowDesignerReject> sourceWorkflowDesignerRejects = new HashSet<WorkflowDesignerReject>();
	@OneToMany(mappedBy="sourceJbpmWorkflowDesigner",fetch=FetchType.LAZY)
	private Set<WorkflowDesignerReject> rejectWorkflowDesignerRejects = new HashSet<WorkflowDesignerReject>();
	
	@OneToMany(mappedBy="workflowStart",fetch=FetchType.LAZY) 
	private Set<WorkflowStartDepartment> workflowStartDepts = new HashSet<WorkflowStartDepartment>();
	@OneToMany(mappedBy="workflowStart",fetch=FetchType.LAZY) 
	private Set<WorkflowStartDepartmentRole> workflowStartDeptRoles = new HashSet<WorkflowStartDepartmentRole>();
	@OneToMany(mappedBy="workflowStart",fetch=FetchType.LAZY) 
	private Set<WorkflowStartGroup> workflowStartGroups = new HashSet<WorkflowStartGroup>();
	@OneToMany(mappedBy="workflowStart",fetch=FetchType.LAZY) 
	private Set<UserWorkflowStart> userWorkflowStarts = new HashSet<UserWorkflowStart>();
	
	@OneToMany(mappedBy="workflowView",fetch=FetchType.LAZY) 
	private Set<WorkflowViewDepartment> workflowViewDepts = new HashSet<WorkflowViewDepartment>();
	@OneToMany(mappedBy="workflowView",fetch=FetchType.LAZY) 
	private Set<WorkflowViewDepartmentRole> workflowViewDeptRoles = new HashSet<WorkflowViewDepartmentRole>();
	@OneToMany(mappedBy="workflowView",fetch=FetchType.LAZY) 
	private Set<WorkflowViewGroup> workflowViewGroups = new HashSet<WorkflowViewGroup>();
	@OneToMany(mappedBy="workflowView",fetch=FetchType.LAZY) 
	private Set<UserWorkflowView> userWorkflowViews = new HashSet<UserWorkflowView>();
	
	
	public Set<WorkflowStartDepartment> getWorkflowStartDepts() {
		return workflowStartDepts;
	}
	public Set<WorkflowStartDepartmentRole> getWorkflowStartDeptRoles() {
		return workflowStartDeptRoles;
	}
	public Set<WorkflowStartGroup> getWorkflowStartGroups() {
		return workflowStartGroups;
	}
	public Set<UserWorkflowStart> getUserWorkflowStarts() {
		return userWorkflowStarts;
	}
	public Set<WorkflowViewDepartment> getWorkflowViewDepts() {
		return workflowViewDepts;
	}
	public Set<WorkflowViewDepartmentRole> getWorkflowViewDeptRoles() {
		return workflowViewDeptRoles;
	}
	public Set<WorkflowViewGroup> getWorkflowViewGroups() {
		return workflowViewGroups;
	}
	public Set<UserWorkflowView> getUserWorkflowViews() {
		return userWorkflowViews;
	}
	public void setWorkflowStartDepts(
			Set<WorkflowStartDepartment> workflowStartDepts) {
		this.workflowStartDepts = workflowStartDepts;
	}
	public void setWorkflowStartDeptRoles(
			Set<WorkflowStartDepartmentRole> workflowStartDeptRoles) {
		this.workflowStartDeptRoles = workflowStartDeptRoles;
	}
	public void setWorkflowStartGroups(Set<WorkflowStartGroup> workflowStartGroups) {
		this.workflowStartGroups = workflowStartGroups;
	}
	public void setUserWorkflowStarts(Set<UserWorkflowStart> userWorkflowStarts) {
		this.userWorkflowStarts = userWorkflowStarts;
	}
	public void setWorkflowViewDepts(Set<WorkflowViewDepartment> workflowViewDepts) {
		this.workflowViewDepts = workflowViewDepts;
	}
	public void setWorkflowViewDeptRoles(
			Set<WorkflowViewDepartmentRole> workflowViewDeptRoles) {
		this.workflowViewDeptRoles = workflowViewDeptRoles;
	}
	public void setWorkflowViewGroups(Set<WorkflowViewGroup> workflowViewGroups) {
		this.workflowViewGroups = workflowViewGroups;
	}
	public void setUserWorkflowViews(Set<UserWorkflowView> userWorkflowViews) {
		this.userWorkflowViews = userWorkflowViews;
	}
	public Set<WorkflowDesignerReject> getSourceWorkflowDesignerRejects() {
		return sourceWorkflowDesignerRejects;
	}
	public Set<WorkflowDesignerReject> getRejectWorkflowDesignerRejects() {
		return rejectWorkflowDesignerRejects;
	}
	public void setSourceWorkflowDesignerRejects(
			Set<WorkflowDesignerReject> sourceWorkflowDesignerRejects) {
		this.sourceWorkflowDesignerRejects = sourceWorkflowDesignerRejects;
	}
	public void setRejectWorkflowDesignerRejects(
			Set<WorkflowDesignerReject> rejectWorkflowDesignerRejects) {
		this.rejectWorkflowDesignerRejects = rejectWorkflowDesignerRejects;
	}
	@Transient
	public String getDesignerWorkflowJsonToString()
	{
		return new String(this.designerWorkflowJson);
	}
	@Transient
	public String getTransferedJpdlXmlToString()
	{
		return this.transferedJpdlXml;
	}
	@OneToOne(mappedBy="jbpmWorkflowDesigner",fetch=FetchType.LAZY)
	private DeploymentImpl deploymentImpl;
	
	public DeploymentImpl getDeploymentImpl() {
		return deploymentImpl;
	}
	public void setDeploymentImpl(DeploymentImpl deploymentImpl) {
		this.deploymentImpl = deploymentImpl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDesignerWorkflowJson() {
		return designerWorkflowJson;
	}
	public void setDesignerWorkflowJson(String designerWorkflowJson) {
		this.designerWorkflowJson = designerWorkflowJson;
	}
	public String getTransferedJpdlXml() {
		return transferedJpdlXml;
	}
	public void setTransferedJpdlXml(String transferedJpdlXml) {
		this.transferedJpdlXml = transferedJpdlXml;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJpdlVersion() {
		return jpdlVersion;
	}
	public void setJpdlVersion(String jpdlVersion) {
		this.jpdlVersion = jpdlVersion;
	}
	
	 /**
	 * @param deployedTime the deployedTime to set
	 **/
	
	public void setDeployedTime(String deployedTime) {
		this.deployedTime = deployedTime;
	}
	
	 /**
	 * deployedTime
	 * @return the deployedTime
	 * @since 1.0.0
	 **/
	
	public String getDeployedTime() {
		return deployedTime;
	}
	
	 /**
	 * @param workflowDefinition the workflowDefinition to set
	 **/
	
	public void setWorkflowDefinition(String workflowDefinition) {
		this.workflowDefinition = workflowDefinition;
	}
	
	 /**
	 * workflowDefinition
	 * @return the workflowDefinition
	 * @since 1.0.0
	 **/
	
	public String getWorkflowDefinition() {
		return workflowDefinition;
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
	
	public void setKeyThree(String keyThree) {
		this.keyThree = keyThree;
	}
	
	public String getKeyThree() {
		return keyThree;
	}
	
	public void setKeyFour(String keyFour) {
		this.keyFour = keyFour;
	}
	
	public String getKeyFour() {
		return keyFour;
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
	public Set<AttachmentFileUploadInfoDetail> getAttachmentFileUploadInfoDetails() {
		return attachmentFileUploadInfoDetails;
	}
	public void setAttachmentFileUploadInfoDetails(
			Set<AttachmentFileUploadInfoDetail> attachmentFileUploadInfoDetails) {
		this.attachmentFileUploadInfoDetails = attachmentFileUploadInfoDetails;
	}
	
	@Transient
	public JSONObject getJsonObjectJbpmWorkFlowDesigner(JbpmWorkflowDesigner jbpmWorkFlowDesigner) throws Exception{
		String currentState =  "opened";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", jbpmWorkFlowDesigner.getId());
		jsonObj.put("text", jbpmWorkFlowDesigner.getDisplayName()+" - "+jbpmWorkFlowDesigner.getVersion());
		jsonObj.put("iconCls", "icon-contact");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject();
		DictionaryData workflowType = jbpmWorkFlowDesigner.getType();
		atrributesJsonObj.put("typeId",workflowType.getId());
		atrributesJsonObj.put("typeName",workflowType.getName());
		atrributesJsonObj.put("rejectEntityClassName","WorkflowDesignerReject");
		atrributesJsonObj.put("displayName", jbpmWorkFlowDesigner.getDisplayName());
		atrributesJsonObj.put("workflowDefinition", jbpmWorkFlowDesigner.getWorkflowDefinition());
		atrributesJsonObj.put("key", jbpmWorkFlowDesigner.getKey());
		atrributesJsonObj.put("version", jbpmWorkFlowDesigner.getVersion());
		atrributesJsonObj.put("position", jbpmWorkFlowDesigner.getPosition());
		jsonObj.put("attributes", atrributesJsonObj);
		return jsonObj;
	}
	@Override
	public int compareTo(JbpmWorkflowDesigner jbpmWorkflowDesigner) {
		return this.id.compareTo(jbpmWorkflowDesigner.getId());
	}

}
