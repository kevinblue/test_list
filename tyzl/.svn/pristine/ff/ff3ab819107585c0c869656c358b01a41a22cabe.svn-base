package com.tenwa.business.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name="T_ROLES")
public class Role implements Serializable 
{
	private static final long serialVersionUID = -6297218341440605242L;
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	@Column(name="CODE_",nullable=false,unique=true)
	private String code ;
	@Column(name="NAME_",nullable=false)
	private String name;
	@Column(name="DESCRIPTION_")
	private String description;
	@Column(name="ENABLED_",nullable=false,length=1)
	private Boolean enabled;
	
	@OneToMany(mappedBy="role",fetch=FetchType.LAZY) 
	private Set<DepartmentRole> deptRoles = new HashSet<DepartmentRole>();
	
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
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescription() {
		return description;
	}

	public Set<DepartmentRole> getDeptRoles() {
		return deptRoles;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDeptRoles(Set<DepartmentRole> deptRoles) {
		this.deptRoles = deptRoles;
	}

	@Override
	public boolean equals(Object obj) 
	{
		return this.id.equals(((Role)obj).id);
	}

	@Override
	public int hashCode() 
	{
		return this.id.hashCode();
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
