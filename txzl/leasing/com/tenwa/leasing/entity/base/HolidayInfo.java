package com.tenwa.leasing.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "节假日信息")
@Table(name="HOLIDAY_INFO")
public class HolidayInfo {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="节假日")
	@Column(name="HOLIDAY_",length=50)
	private String holiday;

	@FieldName(name="是否节日当天")
	@Column(name="ISSAMEDAY",length=10)
	private String issameday;
	
	@FieldName(name="工作日")
	@Column(name="WORKDAY_",length=50)
	private String workday;

	@FieldName(name="备注")
	@Column(name="MEMO_",length=50)
	private String memo;
	
	@FieldName(name="状态")
	@Column(name="STATUS_",length=50)
	private String status;
	
	@FieldName(name="节假日分类编号")
	@OneToOne(targetEntity=HolidayClassify.class,fetch=FetchType.LAZY)
	@JoinColumn(name="HOLIDAYCLASSIFY_ID")
	private HolidayClassify holidayclassifyid;
	
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

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getWorkday() {
		return workday;
	}

	public void setWorkday(String workday) {
		this.workday = workday;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HolidayClassify getHolidayclassifyid() {
		return holidayclassifyid;
	}

	public void setHolidayclassifyid(HolidayClassify holidayclassifyid) {
		this.holidayclassifyid = holidayclassifyid;
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

	public String getIssameday() {
		return issameday;
	}

	public void setIssameday(String issameday) {
		this.issameday = issameday;
	}
	

}