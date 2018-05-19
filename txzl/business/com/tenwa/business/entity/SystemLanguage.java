
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         LoginInfo.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-9-下午07:07:47
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.i18n.LocaleEnum;
import com.tenwa.report.enums.ColumnDataType;


 /**
 * 类名称：     LoginInfo
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-9-9 下午07:07:47
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name = "T_SYSTEM_LANGUAGE")
public class SystemLanguage 
{
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
    private String id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, name = "SYSTEM_LOCAL")
	private LocaleEnum systemLocal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocaleEnum getSystemLocal() {
		return systemLocal;
	}

	public void setSystemLocal(LocaleEnum systemLocal) {
		this.systemLocal = systemLocal;
	}
	
	
}
