package com.tenwa.business.entity.base;  
  
//这个地方可以事先定义好需要的类  
import javax.persistence.*;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.StringUtil;

import java.io.Serializable;

import com.tenwa.business.entity.User;
import com.tenwa.business.entity.base.BaseRole;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.business.entity.base.BaseRoleBlock;
import com.tenwa.business.model.RoleCalEnum;
import com.tenwa.freemaker.enums.EntityColumTypeEnum;

@Entity
@FieldName(name = "树转表评分规则") 
@Table(name = "BASE_ROLE")
public class BaseRole implements Serializable{  

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name="ID_", nullable = false, length = 32)
	@FieldName(name="主键")
    private String id;  
	
	@FieldName(name="资料code")
	@Column(name="CODE_",nullable=false,unique=true)
	private String code;

	@Column(name = "NAME_" ,length = 40)
	@FieldName(name="规则名称")
    private String name;  

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID_" )
	@FieldName(name="父节点")
    private BaseRole pid;  

	@Column(name = "MAX_VALUE")
	@FieldName(name="最大分值")
    private Double maxValue;  

	@Column(name = "MIN_VALUE")
	@FieldName(name="最小分值")
    private Double minValue;  

	@Column(name = "VALUE_SCALE")
	@FieldName(name="分值精度")
    private Integer valueScale;  

	@Column(name = "ENABLED_" ,nullable=false)
	@FieldName(name="是否有效")
    private Boolean enabled;  
	
	@Enumerated(EnumType.STRING)
	@FieldName(name = "列类型")
	@Column(name="ROLE_CAL_TYPE", length=100)	
	private RoleCalEnum roleCalType;

	@OneToMany(mappedBy="pid" ,fetch = FetchType.LAZY)
	@OrderBy(value = "position ASC")
	@Where(clause="ENABLED_ = 1")
    private Set<BaseRole> childenRoles;  

	@OneToMany(mappedBy="pid" ,fetch = FetchType.LAZY)
	@FieldName(name="对应块")
	@OrderBy(value = "position ASC")
	@Where(clause="ENABLED_ = 1")
    private Set<BaseRoleBlock> blocks; 
	
	@Column(name = "POSITION_")
	@FieldName(name="位置")
    private Integer position;
	
	@FieldName(name="资料描述")
	@Column(name="DESCRIPTION_")
	private String description; 

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_" )
	@FieldName(name="创建人")
    private User creator;  

	@Column(name = "CREATE_DATE_" ,length = 40)
	@FieldName(name="创建时间")
    private String createDate;  

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_" )
	@FieldName(name="修改人")
    private User modificator;  

	@Column(name = "MODIFY_DATE_" ,length = 40)
	@FieldName(name="修改时间")
    private String modifyDate;  
      
    public void setId(String id){  
        this.id=id;  
    }  
      
    public String getId(){  
        return this.id;  
    }  
      
    public void setName(String name){  
        this.name=name;  
    }  
      
    public String getName(){  
        return this.name;  
    }  
      
    public void setPid(BaseRole pid){  
        this.pid=pid;  
    }  
      
    public BaseRole getPid(){  
        return this.pid;  
    }  
      
    public void setMaxValue(Double maxValue){  
        this.maxValue=maxValue;  
    }  
      
    public Double getMaxValue(){  
        return this.maxValue;  
    }  
      
    public void setMinValue(Double minValue){  
        this.minValue=minValue;  
    }  
      
    public Double getMinValue(){  
        return this.minValue;  
    }  
      
    public void setValueScale(Integer valueScale){  
        this.valueScale=valueScale;  
    }  
      
    public Integer getValueScale(){  
        return this.valueScale;  
    }  
      
    public void setEnabled(Boolean enabled){  
        this.enabled=enabled;  
    }  
      
    public Boolean getEnabled(){  
        return this.enabled;  
    }  
      
    public void setChildenRoles(Set<BaseRole> childenRoles){  
        this.childenRoles=childenRoles;  
    }  
      
    public Set<BaseRole> getChildenRoles(){  
        return this.childenRoles;  
    }  
      
    public void setBlocks(Set<BaseRoleBlock> blocks){  
        this.blocks=blocks;  
    }  
      
    public Set<BaseRoleBlock> getBlocks(){  
        return this.blocks;  
    }  
      
    public void setCreator(User creator){  
        this.creator=creator;  
    }  
      
    public User getCreator(){  
        return this.creator;  
    }  
      
    public void setCreateDate(String createDate){  
        this.createDate=createDate;  
    }  
      
    public String getCreateDate(){  
        return this.createDate;  
    }  
      
    public void setModificator(User modificator){  
        this.modificator=modificator;  
    }  
      
    public User getModificator(){  
        return this.modificator;  
    }  
      
  

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}  
      
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean isHasChildren()
	{
		return this.childenRoles.size()>=1;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public JSONArray getChildrenJsonArray(boolean isInit) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectBaseRole(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenJsonArray(!isInit));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(BaseRole baseRole : this.getChildenRoles()){
				String state = baseRole.isHasChildren() ? "closed" : "opened";
				JSONObject deptJsonObj = this.getJsonObjectBaseRole(baseRole,isInit,state);
				jsonArray.put(deptJsonObj);
			}
		}
		return jsonArray;
	}
	@Transient
	public JSONObject getJsonObjectBaseRole(BaseRole baseRole,boolean isInit,String state) throws Exception{
		String currentState = isInit ? "opened" : state;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", baseRole.getId());
		jsonObj.put("text", baseRole.getName()+" [ "+baseRole.getCode()+" ] ");
		String iconCls = "icon-home";
		if(!isInit){
			iconCls = state.equals("closed") ? "icon-box" :"icon-box-w";
		}
		jsonObj.put("iconCls", iconCls);
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject(this.getArributesJsonString(baseRole));
		atrributesJsonObj.put("name", baseRole.getName());
		atrributesJsonObj.put("code", baseRole.getCode());
		atrributesJsonObj.put("description", baseRole.getDescription());
		atrributesJsonObj.put("position", ("0".equals(baseRole.getId())) ? -1 :(baseRole.getPosition()));
		jsonObj.put("attributes",atrributesJsonObj);
		return jsonObj;
	}
	
	public String getArributesJsonString(BaseRole node)
	{
		Map<String,String> nodeAttributes = node.getAttributes();
		StringBuffer  str_sb = new StringBuffer("{");
		int index= 0;
		if(null != nodeAttributes)
		{
			for(String key:nodeAttributes.keySet())
			{
				String value = StringUtil.nullToString(nodeAttributes.get(key));
				if( 1 != ++index)
				{
					str_sb.append(",");
				}
				str_sb.append("\"")
				      .append(key)
					  .append("\":\"")
					  .append(StringUtil.getJsonString(value))
					  .append("\"");
				
			}
		}
		str_sb.append("}");
		return str_sb.toString();
	}
	
	@Transient
	private Map<String,String> attributes = null;
	
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	@Transient
	public Map<String, String> getAttributes() {
		Map<String,String> nodeAttributes = new HashMap<String,String>();
		BaseRole parentRole = this.getPid();
		String pid = parentRole == null?"-1":parentRole.getId();
		nodeAttributes.put("pid", pid);
		String parentOrgRealName = parentRole == null? "":parentRole.getName();
		nodeAttributes.put("parentDictionaryRealName", parentOrgRealName);
		nodeAttributes.put("code", this.getCode());
		nodeAttributes.put("position", StringUtil.nullToString(this.getPosition()));
		nodeAttributes.put("description", StringUtil.getJsonString(this.getDescription()));
		nodeAttributes.put("enabled", this.getEnabled().toString());
		nodeAttributes.put("type", "baseRole");
		nodeAttributes.put("maxvalue", this.getMaxValue()== null ? "" : this.getMaxValue().toString());
		nodeAttributes.put("minvalue", this.getMinValue()== null ? "" : this.getMinValue().toString());
		nodeAttributes.put("valuescale",this.getValueScale()== null ? "" : this.getValueScale().toString() );
		nodeAttributes.put("rolecaltype",this.getRoleCalType()== null ? "" : this.getRoleCalType().toString() );
		if(null != this.attributes){
			nodeAttributes.putAll(this.attributes);
		}
		return nodeAttributes;
	}

	public RoleCalEnum getRoleCalType() {
		return roleCalType;
	}

	public void setRoleCalType(RoleCalEnum roleCalType) {
		this.roleCalType = roleCalType;
	}

	
	
    
}  