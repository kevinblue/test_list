
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
  * 
  * @author 孙传良
  * @date 2013-8-29上午09:38:58
  * @info   号码废弃池
  * @Copyright 
  * Tenwa
  */
@Entity
@Table(name="T_SERIAL_DISCARD_NUMBER")
@FieldName(name = "号码废弃池")
public class SerialDiscardNumber implements Comparable<SerialDiscardNumber> 
{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(name="ID_",length=32)
    private String id ;
	
	@Column(name="TYPE_",length=100,unique=false,nullable=false)
	@FieldName(name = "类型")
	private String type;
	  
	@Column(name="YEAR_",length=100,nullable=false)
	@FieldName(name = "年")
	private int year;
	@Column(name="MONTH_",length=100,nullable=true)
	@FieldName(name = "月")
	private Integer month;
	
	@Column(name="DISCARD_NUMBER_",length=100,nullable=false)
	@FieldName(name = "号码废弃池")
	private int discardNumber;
	
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

	
	 /**
	 * @param year the year to set
	 **/
	
	public void setYear(int year) {
		this.year = year;
	}

	
	 /**
	 * year
	 * @return the year
	 * @since 1.0.0
	 **/
	
	public int getYear() {
		return year;
	}

	public int getDiscardNumber() {
		return discardNumber;
	}

	public void setDiscardNumber(int discardNumber) {
		this.discardNumber = discardNumber;
	}

	@Override
	public int compareTo(SerialDiscardNumber o) {
		if( this.getDiscardNumber()<o.getDiscardNumber()){
			return -1;
		}else if (this.getDiscardNumber()>o.getDiscardNumber()) {
			return 1;
		}else{
			return 0;
		}
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getMonth() {
		return month;
	}

}
