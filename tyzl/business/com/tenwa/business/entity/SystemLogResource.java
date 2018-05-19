package com.tenwa.business.entity;   

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-7-11 下午12:04:23
 * 类说明
 */
@Entity
@Table(name = "T_SYSTEM_LOG_RESOURCE")
@FieldName(name = "资源URL")
public class SystemLogResource {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;
	
	@FieldName(name = "资源名称")
	@Column(name="NAME_", length=254)
	private String name;
	
	@FieldName(name = "资源地址")
	@Column(name="URL_", length=254)
	private String url;
	
	@FieldName(name = "描述")
	@Column(name="DESCRIBE_", length=254)
	private String describe;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getDescribe() {
		return describe;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
  
