
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         PermissionCache.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-8-31-下午01:24:58
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.enums.PermissionEnum;


 /**
 * 类名称：     PermissionCache
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-8-31 下午01:24:58
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_PERMISSIONS_CACHES")
public class PermissionCache 
{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@FieldName(name = "关键字检索")
	@Column(name="KEY_", nullable=false)
	private String key;
	
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="PERMISSION_CACHED_JSON_", nullable=false)
	private String  permissionCachedJson;
	
	@FieldName(name = "权限缓存类别")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "TYPE_")
	private PermissionEnum type;
	

	public String getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getPermissionCachedJson() {
		return permissionCachedJson;
	}

	public PermissionEnum getType() {
		return type;
	}


	public void setId(String id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setPermissionCachedJson(String permissionCachedJson) {
		this.permissionCachedJson = permissionCachedJson;
	}

	public void setType(PermissionEnum type) {
		this.type = type;
	}
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
	private String modifyDate;


	public User getModificator() {
		return modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
