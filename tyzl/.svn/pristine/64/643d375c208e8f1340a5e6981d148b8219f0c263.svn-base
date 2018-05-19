package com.tenwa.business.entity;


import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name="T_DICTS_DATAS")
public class DictionaryData implements Serializable
{
	private static final long serialVersionUID = -6634782176482661906L;
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(name="ID_",length=200)
    private String id ;
	@Column(name="NAME_",nullable=false,length=100)
	private String name;
	@Column(name="EN_NAME_",length=100)
	private String enName;
	@Column(name="CODE_",nullable=false,unique=true,length=50)
	private String code;
	@Column(name="ENABLED_",length=1,nullable=false)
	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean enabled;
	@Column(name="DESCRIPTION_",length=1000)
	private String description; 
	@Column(name="POSITION_",nullable=false)
	private Integer position; 
	@Column(name="CHARACTER_")
	private String character; 
	@Column(name="GRADE_LEVEL_")
	private String gradeLevel; 
	@Column(columnDefinition="INT DEFAULT 0",name="IS_MUST_",length=1,nullable=false)
	private Boolean isMust;
	
	@Column(name="PROP_ONE_")
	private String propOne; 
	@Column(name="PROP_TWO_")
	private String propTwo; 
	@Column(name="PROP_THREE_")
	private String propThree; 
	@Column(name="PROP_FOUR_")
	private String propFour; 
	@Column(name="PROP_Five_")
	private String propFive; 
	
	public DictionaryData(){
		
	}
	
	public DictionaryData(String id){
		this.id = id;
	}
	
	public String getPropOne() {
		return propOne;
	}

	public void setPropOne(String propOne) {
		this.propOne = propOne;
	}

	public String getPropTwo() {
		return propTwo;
	}

	public void setPropTwo(String propTwo) {
		this.propTwo = propTwo;
	}

	public String getPropThree() {
		return propThree;
	}

	public void setPropThree(String propThree) {
		this.propThree = propThree;
	}

	public String getPropFour() {
		return propFour;
	}

	public void setPropFour(String propFour) {
		this.propFour = propFour;
	}

	public String getPropFive() {
		return propFive;
	}

	public void setPropFive(String propFive) {
		this.propFive = propFive;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	@JsonBackReference
	@ManyToOne(targetEntity=Dictionary.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.EAGER) 
	@JoinColumn(name="PID_",nullable=false)
	private Dictionary belongDictionary ;
	
	/**与上传info的一对多关系**/
	@OneToMany(mappedBy="attachmentFileDict",fetch = FetchType.LAZY)
	private Set<AttachmentFileUploadInfo> attachmentFileUploadInfos = new HashSet<AttachmentFileUploadInfo>();
	/**与上传jbpmWorkflowDesigner的一对多关系**/
	@OneToMany(mappedBy="type",fetch = FetchType.LAZY)
	@OrderBy("position")
	private Set<JbpmWorkflowDesigner> jbpmWorkflowDesigners = new HashSet<JbpmWorkflowDesigner>();
	 
	public Set<JbpmWorkflowDesigner> getJbpmWorkflowDesigners() {
		return jbpmWorkflowDesigners;
	}
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
	
	@Transient
	private Map<String,String> attributes = new HashMap<String,String>();
	@Transient
	private String unionKey  ;
	@Override
	public boolean equals(Object obj) 
	{
		return this.id.equals(((DictionaryData)obj).id);
	}

	@Override
	public int hashCode() 
	{
		return this.id.hashCode();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	 /**
	 * @param belongDictionary the belongDictionary to set
	 **/
	
	public void setBelongDictionary(Dictionary belongDictionary) {
		this.belongDictionary = belongDictionary;
	}

	
	 /**
	 * belongDictionary
	 * @return the belongDictionary
	 * @since 1.0.0
	 **/
	
	public Dictionary getBelongDictionary() {
		return belongDictionary;
	}

	
	 /**
	 * @param description the description to set
	 **/
	
	public void setDescription(String description) {
		this.description = description;
	}

	
	 /**
	 * description
	 * @return the description
	 * @since 1.0.0
	 **/
	
	public String getDescription() {
		return description;
	}

	
	 /**
	 * @param position the position to set
	 **/
	
	public void setPosition(Integer position) {
		this.position = position;
	}

	
	 /**
	 * position
	 * @return the position
	 * @since 1.0.0
	 **/
	
	public Integer getPosition() {
		return position;
	}

	
	 /**
	 * @param enabled the enabled to set
	 **/
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	 /**
	 * enabled
	 * @return the enabled
	 * @since 1.0.0
	 **/
	
	public Boolean getEnabled() {
		return enabled;
	}

	
	 /**
	 * @param code the code to set
	 **/
	
	public void setCode(String code) {
		this.code = code;
	}

	
	 /**
	 * code
	 * @return the code
	 * @since 1.0.0
	 **/
	
	public String getCode() {
		return code;
	}

	
	 /**
	 * @param name the name to set
	 **/
	
	public void setName(String name) {
		this.name = name;
	}

	
	 /**
	 * name
	 * @return the name
	 * @since 1.0.0
	 **/
	
	public String getName() {
		return name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Set<AttachmentFileUploadInfo> getAttachmentFileUploadInfos() {
		return attachmentFileUploadInfos;
	}

	public void setAttachmentFileUploadInfos(
			Set<AttachmentFileUploadInfo> attachmentFileUploadInfos) {
		this.attachmentFileUploadInfos = attachmentFileUploadInfos;
	}

	public void setJbpmWorkflowDesigners(Set<JbpmWorkflowDesigner> jbpmWorkflowDesigners) {
		this.jbpmWorkflowDesigners = jbpmWorkflowDesigners;
	}
	@Transient
	public JSONObject getJsonObjectDictionaryData(DictionaryData dictionaryData) throws Exception{
		String currentState =  "closed";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", dictionaryData.getId());
		jsonObj.put("text", dictionaryData.getName()+"（"+dictionaryData.getCode()+"）");
		jsonObj.put("iconCls", "icon-home");
		jsonObj.put("state", currentState);
		return jsonObj;
	}
	@Transient
	public JSONArray getJbpmWorkflowDesignersJsonArray() throws Exception {
		JSONArray jsonArr  = new JSONArray();
		for(JbpmWorkflowDesigner jbpmWorkflowDesigner : jbpmWorkflowDesigners){
			JSONObject jsonObj = jbpmWorkflowDesigner.getJsonObjectJbpmWorkFlowDesigner(jbpmWorkflowDesigner);
			jsonArr.put(jsonObj);
		}
		return jsonArr;
	}
	@Transient
	public void setAttributes(Map<String,String> attributes) {
		this.attributes = attributes;
	}
	@Transient
	public Map<String,String> getAttributes() {
		return attributes;
	}
	@Transient
	public void setUnionKey(String unionKey) {
		this.unionKey = unionKey;
	}
	@Transient
	public String getUnionKey() {
		return unionKey;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setIsMust(Boolean isMust) {
		this.isMust = isMust;
	}

	public Boolean getIsMust() {
		return isMust;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	
}
