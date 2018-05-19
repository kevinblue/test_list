package com.tenwa.business.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 孙传良
 * @date 2013-8-8下午04:02:26
 * @info 合同状态基本数据表 配合常量类 AppStaticUtil 一起使用
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同状态基本数据表")
@Table(name="BASE_CONTRACT_STATUS")
public class BaseContractStatus {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="状态编码")
	@Column(name="STATUS_CODE")
	private Integer statusCode;
	
	@FieldName(name="状态名称")
	@Column(name="STATUS_NAME",length=20)
	private String statusName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
