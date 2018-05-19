package com.tenwa.leasing.entity.voucher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 孙传良
 * @date 2013-9-13下午05:11:11
 * @info 财务关帐日表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "财务关帐日表")
@Table(name="INTER_FINANCE_CLOSEDAY")
public class InterFinanceCloseDay {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id ;
	
	@FieldName(name="年份")
	@Column(name="CLOSE_YEAR")	
	private Integer closeYear;
	
	@FieldName(name="月份")
	@Column(name="CLOSE_MONTH")	
	private Integer closeMonth;
	
	@FieldName(name="关账日")
	@Column(name="CLOSE_DAY",length=20)	
	private String closeDay;
	
	@ManyToOne
	@FieldName(name="关帐人")
	@JoinColumn(name="CLOSE_STAFF")
	private User closeStaff;
	
	@FieldName(name="状态:有效/无效")
	@Column(name="STATUS_")	
	private String status;
	
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

	public Integer getCloseYear() {
		return closeYear;
	}

	public void setCloseYear(Integer closeYear) {
		this.closeYear = closeYear;
	}

	public Integer getCloseMonth() {
		return closeMonth;
	}

	public void setCloseMonth(Integer closeMonth) {
		this.closeMonth = closeMonth;
	}

	public String getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(String closeDay) {
		this.closeDay = closeDay;
	}

	public User getCloseStaff() {
		return closeStaff;
	}

	public void setCloseStaff(User closeStaff) {
		this.closeStaff = closeStaff;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
}
