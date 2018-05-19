package com.tenwa.leasing.entity.base;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "节假日分类")
@Table(name="HOLIDAY_CLASSIFY")
public class HolidayClassify {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="编号")
	@Column(name="HOLIDAY_NUMBER",length=50)
	private String holidaynumber;

	@FieldName(name="种类")
	@Column(name="HOLIDAY_TYPE",length=50)
	private String holidaytype;

	@FieldName(name="年份")
	@Column(name="HOLIDAY_YEAR",length=50)
	private String holidayyear;
	
	@FieldName(name="状态")
	@Column(name="STATE_",length=2)
	private String state;
	
	@FieldName(name = "节假日登记")
	@OneToMany(mappedBy = "holidayclassifyid", fetch = FetchType.LAZY)
	private Set<HolidayInfo> holidayinfo = new HashSet<HolidayInfo>();

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

	public String getHolidaynumber() {
		return holidaynumber;
	}

	public void setHolidaynumber(String holidaynumber) {
		this.holidaynumber = holidaynumber;
	}

	public String getHolidaytype() {
		return holidaytype;
	}

	public void setHolidaytype(String holidaytype) {
		this.holidaytype = holidaytype;
	}

	public String getHolidayyear() {
		return holidayyear;
	}

	public void setHolidayyear(String holidayyear) {
		this.holidayyear = holidayyear;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Set<HolidayInfo> getHolidayinfo() {
		return holidayinfo;
	}

	public void setHolidayinfo(Set<HolidayInfo> holidayinfo) {
		this.holidayinfo = holidayinfo;
	}





}