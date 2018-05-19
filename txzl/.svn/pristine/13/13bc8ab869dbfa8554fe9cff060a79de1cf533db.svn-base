package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.kernal.annotation.FieldName;



/**
 * 
* @ClassName: I18nDictionary 
* @Description: 国际化数据字典
* @author zhangc
* @date 2015年4月17日 下午5:52:33 
*
 */
 
@Entity
@FieldName(name = "国际化数据字典")
@Table(name="T_I18N_DATAS")
public class I18nDictionary {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="名称")
	@Column(name="NAME_", length=3000)
	private String name;
	
	@FieldName(name="code")
	@Column(name="CODE_", length=200,nullable=false,unique=true)	
	private String code;
	
	@FieldName(name="中文名称")
	@Column(name="ZH_NAME_", length=3000)
	private String zhName;
	
	@FieldName(name="英文名称")
	@Column(name="EN_NAME_", length=3000)
	private String enName;
	
	@FieldName(name="台湾名称")
	@Column(name="TW_NAME_", length=3000)
	private String twName;

	@FieldName(name="台湾名称")
	@Column(name="JN_NAME_", length=3000)
	private String jnName;
	
	
	@FieldName(name="是否有效")
	@Column(name="ENABLED_",length=1,nullable=false)
	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean enabled;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getZhName() {
		return zhName;
	}

	public void setZhName(String zhName) {
		this.zhName = zhName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getTwName() {
		return twName;
	}

	public void setTwName(String twName) {
		this.twName = twName;
	}

	public String getJnName() {
		return jnName;
	}

	public void setJnName(String jnName) {
		this.jnName = jnName;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	
}
