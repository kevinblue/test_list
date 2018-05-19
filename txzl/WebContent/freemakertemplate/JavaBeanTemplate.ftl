package ${package};  
  
//这个地方可以事先定义好需要的类  
import javax.persistence.*;
import com.tenwa.kernal.annotation.FieldName;
import java.io.Serializable;
<#list importclasses as importclass> 
import ${importclass};
</#list> 

@Entity
@FieldName(name = "${entityDesc}") 
@Table(name = "${entityName}")
public class ${className} implements Serializable{  
<#list properties as pro>  

	<#if (pro.columnType?? && pro.columnType == 'PRIMARY')>
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	<#if (pro.columnName??)>
	@Column(name="${pro.columnName}", nullable = false, length = 32)
	<#else>
	@Column(name = "ID", nullable = false, length = 32)
	</#if>
	<#elseif (pro.columnType?? && pro.columnType == 'FOREIGNKEY')>
	@ManyToOne<#if (pro.fetchType??)>(fetch = FetchType.${pro.fetchType})</#if>
	@JoinColumn(name = "${pro.columnName}" <#if (pro.notnull?? && pro.notnull=='false')> ,nullable=false</#if>)
	<#elseif (pro.columnType?? && pro.columnType == 'ONETOONE')>
	@OneToOne<#if (pro.fetchType??)>(fetch = FetchType.${pro.fetchType})</#if>)
	@JoinColumn(name="${pro.columnName}"  <#if (pro.notnull?? && pro.notnull=='false')> ,nullable=false</#if>)
	<#elseif (pro.columnType?? && pro.columnType == 'ONETOMANY')>
	@OneToMany(mappedBy="${pro.columnName}"<#if (pro.fetchType??)> ,fetch = FetchType.${pro.fetchType}</#if>)
	<#elseif (pro.columnType?? && pro.columnType == 'CLOB')>
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	<#if (pro.columnName??)>
	@Column(name="${pro.columnName}"<#if (pro.notnull?? && pro.notnull=='false')> ,nullable=false</#if>)
	</#if>
	<#elseif (pro.columnType?? && pro.columnType == 'BLOB')>
	@Lob 
	@Type(type = "org.hibernate.type.BlobType")
	@Basic(fetch=FetchType.LAZY)
	<#if (pro.columnName??)>
	@Column(name="${pro.columnName}"<#if (pro.notnull?? && pro.notnull=='false')> ,nullable=false</#if>)
	</#if>
	<#elseif (pro.columnType?? && pro.columnType == 'TRANSIENT')>
	@Transient
	<#elseif (pro.columnType?? && pro.columnType == 'ENUM')>
	@Enumerated(EnumType.STRING)
	<#if (pro.columnName??)>
	@Column(name="${pro.columnName}"<#if (pro.notnull?? && pro.notnull=='false')> ,nullable=false</#if>)
	</#if>
	<#else>
	<#if (pro.columnName??)>
	@Column(name = "${pro.columnName}"<#if (pro.columnLength??)> ,length = ${pro.columnLength}</#if><#if (pro.precision??)> ,precision = ${pro.precision}</#if><#if (pro.scale??)> ,scale = ${pro.scale}</#if><#if (pro.notnull?? && pro.notnull=='false')> ,nullable=false</#if><#if (pro.unique?? && pro.unique=='true')> ,unique=true</#if>)
	</#if>
	</#if>
	<#if (pro.proDesc??)>
	@FieldName(name="${pro.proDesc}")
	</#if>
	<#if (pro.isindex?? && pro.isindex=='true')>
	@Index(name="${pro.indexname}")
	</#if>
	<#if (pro.orderBy??)>
	@OrderBy(value = "${pro.orderBy}")
	</#if>
    private ${pro.proType} ${pro.proName};  
</#list>  
      
<#list properties as pro>  
    public void set<@upperFC>${pro.proName}</@upperFC>(${pro.proType} ${pro.proName}){  
        this.${pro.proName}=${pro.proName};  
    }  
      
    public ${pro.proType} get<@upperFC>${pro.proName}</@upperFC>(){  
        return this.${pro.proName};  
    }  
      
</#list>  
}  