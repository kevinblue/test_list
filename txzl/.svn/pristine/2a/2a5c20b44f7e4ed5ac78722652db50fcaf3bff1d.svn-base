
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         JbpmCommonAdvise.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-3-上午10:28:05
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


 /**
 * 类名称：     JbpmCommonAdvise
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-3 上午10:28:05
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_JBPM_COMMON_ADVISE")
public class JbpmCommonAdvise 
{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	@Column(name="NAME_",unique=true,nullable=false)
	private String name;
	@Column(name="CODE_",unique=true,nullable=false)
	private String code;
	@Column(name="SORT_",unique=true,nullable=false)
	private Integer sort;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
