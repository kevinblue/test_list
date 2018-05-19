package com.tenwa.freemaker.entity;  
  
//这个地方可以事先定义好需要的类  
import javax.persistence.*;
import com.tenwa.kernal.annotation.FieldName;
import java.io.Serializable;
import com.tenwa.business.entity.User;
import com.tenwa.freemaker.entity.FreeMakerEntity;
import java.util.Set;
import com.tenwa.freemaker.enums.EntityTypeEnum;
import org.hibernate.annotations.GenericGenerator;
import com.tenwa.freemaker.entity.FreeMakerEntityColumn;

@Entity
@FieldName(name = "freemaker创建实体类") 
@Table(name = "T_FREEMAKER_ENTITY")
public class FreeMakerEntity implements Serializable{  

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name="ID", nullable = false, length = 32)
    private String id;  

	@Column(name = "ENTITY_NAME" ,length = 200)
	@FieldName(name="实体名称")
    private String entityName;  

	@Column(name = "SOURCEFOLDER_NAME" ,length = 20)
	@FieldName(name="对应的包名")
    private String sourcefolderName;  

	@Column(name = "TABLE_NAME" ,length = 200)
	@FieldName(name="实体映射表名")
    private String tableName;  

	@Column(name = "TABLE_DESC" ,length = 200)
	@FieldName(name="实体映射表说明")
    private String tableDesc;  

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CREATOR_" )
	@FieldName(name="创建人")
    private User creator;  

	@Column(name = "CREATE_DATE" ,length = 20)
	@FieldName(name="创建时间")
    private String createDate;  

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MODIFICATOR_" )
	@FieldName(name="修改人")
    private User modificator;  

	@Column(name = "MODIFY_DATE" ,length = 20)
	@FieldName(name="修改时间")
    private String modifyDate;  

	@Enumerated(EnumType.STRING)
	@Column(name="ENTITY_TYPE")
	@FieldName(name="类型")
    private EntityTypeEnum EntityType;  

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PID_" )
	@FieldName(name="父结点")
    private FreeMakerEntity parent;  

	@OneToMany(mappedBy="parent" ,fetch = FetchType.EAGER)
	@FieldName(name="子节点")
	@OrderBy(value = "position ASC,entityName")
    private Set<FreeMakerEntity> children;  

	@Column(name = "POSITION_" ,length = 255 ,nullable=false)
	@FieldName(name="菜单顺序")
    private int position;  

	@OneToMany(mappedBy="entity" ,fetch = FetchType.EAGER)
	@FieldName(name="子节点")
	@OrderBy(value = "position ASC,fieldName")
    private Set<FreeMakerEntityColumn> fields;  
      
    public void setId(String id){  
        this.id=id;  
    }  
      
    public String getId(){  
        return this.id;  
    }  
      
    public void setEntityName(String entityName){  
        this.entityName=entityName;  
    }  
      
    public String getEntityName(){  
        return this.entityName;  
    }  
      
    public void setSourcefolderName(String sourcefolderName){  
        this.sourcefolderName=sourcefolderName;  
    }  
      
    public String getSourcefolderName(){  
        return this.sourcefolderName;  
    }  
      
    public void setTableName(String tableName){  
        this.tableName=tableName;  
    }  
      
    public String getTableName(){  
        return this.tableName;  
    }  
      
    public void setTableDesc(String tableDesc){  
        this.tableDesc=tableDesc;  
    }  
      
    public String getTableDesc(){  
        return this.tableDesc;  
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
      
    public void setModifyDate(String modifyDate){  
        this.modifyDate=modifyDate;  
    }  
      
    public String getModifyDate(){  
        return this.modifyDate;  
    }  
      
    public void setEntityType(EntityTypeEnum EntityType){  
        this.EntityType=EntityType;  
    }  
      
    public EntityTypeEnum getEntityType(){  
        return this.EntityType;  
    }  
      
    public void setParent(FreeMakerEntity parent){  
        this.parent=parent;  
    }  
      
    public FreeMakerEntity getParent(){  
        return this.parent;  
    }  
      
    public void setChildren(Set<FreeMakerEntity> children){  
        this.children=children;  
    }  
      
    public Set<FreeMakerEntity> getChildren(){  
        return this.children;  
    }  
      
    public void setPosition(int position){  
        this.position=position;  
    }  
      
    public int getPosition(){  
        return this.position;  
    }  
      
    public void setFields(Set<FreeMakerEntityColumn> fields){  
        this.fields=fields;  
    }  
      
    public Set<FreeMakerEntityColumn> getFields(){  
        return this.fields;  
    }  
      
}  