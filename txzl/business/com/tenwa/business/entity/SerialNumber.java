
 /**
 * 项目名称：    系统名称
 * 包名：              com.business.entity
 * 文件名：         SerialNumber.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-26-上午10:44:52
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;



 /**
 * 类名称：     SerialNumber
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-12-26 上午10:44:52
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_SERIAL_NUMBER")
@FieldName(name = "序列号")
public class SerialNumber 
{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(name="ID_",length=32)
    private String id ;
	@FieldName(name = "序列前缀")
	@Column(name="TYPE_",length=100,unique=false,nullable=false)
	private String type;
	@Column(name="YEAR_",length=100,nullable=false)
	@FieldName(name = "年")
	private Integer year;
	@Column(name="MONTH_",length=100,nullable=true)
	@FieldName(name = "月")
	private Integer month;
	@Column(name="ORDER_NUMBER_",length=100,nullable=false)
	@FieldName(name = "序号")
	private int orderNumber;
	
	 /**
	 * @param id the id to set
	 **/
	
	public void setId(String id) {
		this.id = id;
	}
	
	 /**
	 * id
	 * @return the id
	 * @since 1.0.0
	 **/
	
	public String getId() {
		return id;
	}

	
	 /**
	 * @param type the type to set
	 **/
	
	public void setType(String type) {
		this.type = type;
	}

	
	 /**
	 * type
	 * @return the type
	 * @since 1.0.0
	 **/
	
	public String getType() {
		return type;
	}

	
    public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 **/
	
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	
	 /**
	 * orderNumber
	 * @return the orderNumber
	 * @since 1.0.0
	 **/
	
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getMonth() {
		return month;
	}
}
