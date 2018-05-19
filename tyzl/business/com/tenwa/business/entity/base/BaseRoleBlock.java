package com.tenwa.business.entity.base;  
  
//这个地方可以事先定义好需要的类  
import javax.persistence.*;

import com.tenwa.kernal.annotation.FieldName;

import java.io.Serializable;

import com.tenwa.business.entity.User;
import com.tenwa.business.entity.base.BaseRole;

import org.hibernate.annotations.GenericGenerator;

@Entity
@FieldName(name = "规则块") 
@Table(name = "BASE_ROLE_BLOCK")
public class BaseRoleBlock implements Serializable{  

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name="ID_", nullable = false, length = 32)
	@FieldName(name="主键")
    private String id;  
	
	@FieldName(name="编号")
	@Column(name="CODE_",nullable=false,unique=true)
	private String code;

	@Column(name = "NAME_" ,length = 100)
	@FieldName(name="规则块名")
    private String name;  

	@Column(name = "MAX_FIELD_VALUE")
	@FieldName(name="最大区间值")
    private Double maxFieldValue;  

	@Column(name = "MAX_OPEN_OR_CLOSE")
	@FieldName(name="最大区间值是否有界(0.开,1闭)")
    private Boolean maxOpenOrClose;  

	@Column(name = "MIN_FIELD_VALUE")
	@FieldName(name="最小区间值")
    private Double minFieldValue;  

	@Column(name = "MIN_OPEN_OR_CLOSE")
	@FieldName(name="最小区间值是否开闭(0.开,1.闭)")
    private Boolean minOpenOrClose;  

	@Column(name = "BASE_FIELD_VALUE")
	@FieldName(name="基准区间值")
    private Double baseFieldValue;  

	@Column(name = "BASE_VALUE")
	@FieldName(name="基准分值")
    private Double baseValue;  

	@Column(name = "FIELD_INCREMENT")
	@FieldName(name="区间增量")
    private Double fieldIncrement;  

	@Column(name = "VALUE_INCREMENT")
	@FieldName(name="增量")
    private Double valueIncrement;  

	@Column(name = "POSITION_")
	@FieldName(name="位置")
    private Integer position;  
	
	@FieldName(name="资料描述")
	@Column(name="DESCRIPTION_")
	private String description; 

	@Column(name = "ENABLED_")
	@FieldName(name="是否有效")
    private Boolean enabled;  

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID_" )
	@FieldName(name="对应规则")
    private BaseRole pid;  

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
      
    public void setMaxFieldValue(Double maxFieldValue){  
        this.maxFieldValue=maxFieldValue;  
    }  
      
    public Double getMaxFieldValue(){  
        return this.maxFieldValue;  
    }  
      
    public void setMaxOpenOrClose(Boolean maxOpenOrClose){  
        this.maxOpenOrClose=maxOpenOrClose;  
    }  
      
    public Boolean getMaxOpenOrClose(){  
        return this.maxOpenOrClose;  
    }  
      
    public void setMinFieldValue(Double minFieldValue){  
        this.minFieldValue=minFieldValue;  
    }  
      
    public Double getMinFieldValue(){  
        return this.minFieldValue;  
    }  
      
    public void setMinOpenOrClose(Boolean minOpenOrClose){  
        this.minOpenOrClose=minOpenOrClose;  
    }  
      
    public Boolean getMinOpenOrClose(){  
        return this.minOpenOrClose;  
    }  
      
    public void setBaseFieldValue(Double baseFieldValue){  
        this.baseFieldValue=baseFieldValue;  
    }  
      
    public Double getBaseFieldValue(){  
        return this.baseFieldValue;  
    }  
      
    public void setBaseValue(Double baseValue){  
        this.baseValue=baseValue;  
    }  
      
    public Double getBaseValue(){  
        return this.baseValue;  
    }  
      
    public void setFieldIncrement(Double fieldIncrement){  
        this.fieldIncrement=fieldIncrement;  
    }  
      
    public Double getFieldIncrement(){  
        return this.fieldIncrement;  
    }  
      
    public void setValueIncrement(Double valueIncrement){  
        this.valueIncrement=valueIncrement;  
    }  
      
    public Double getValueIncrement(){  
        return this.valueIncrement;  
    }  
      
    public void setPosition(Integer position){  
        this.position=position;  
    }  
      
    public Integer getPosition(){  
        return this.position;  
    }  
      
    public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public BaseRole getPid() {
		return pid;
	}

	public void setPid(BaseRole pid) {
		this.pid = pid;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}  
    
    
      
}  