package com.tenwa.leasing.entity.cust;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;


/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:23:41
 * @info 周报表详情信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "周报表详情")
@Table(name="T_WORK_WEEK_REPORT_DETAIL")
public class 	WorkWeekReportDetail{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name = "编号")
	@Column(name="WEEK_ID", length=200)
	private String weekid;
	
	@FieldName(name = "周一")
	@Column(name="WEEK_ONE", length=200)
	private String weekone;
	
	@FieldName(name = "周二")
	@Column(name="WEEK_TWO", length=200)
	private String weektwo;
	
	@FieldName(name = "周三")
	@Column(name="WEEK_THREE", length=200)
	private String weekthree;
	
	@FieldName(name = "周四")
	@Column(name="WEEK_four", length=200)
	private String weekfour;
	
	@FieldName(name = "周五")
	@Column(name="WEEK_FIVE", length=200)
	private String weekfive;
	
	@FieldName(name = "周六")
	@Column(name="WEEK_SIX", length=200)
	private String weeksix;
	
	@FieldName(name = "周日")
	@Column(name="WEEK_SEVEN", length=200)
	private String weekseven;
	
	@ManyToOne(targetEntity = WorkWeekReport.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKWEEK_ID")
	@FieldName(name="周报表编号")
	private WorkWeekReport workweekid;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getWeekone() {
		return weekone;
	}

	public void setWeekone(String weekone) {
		this.weekone = weekone;
	}

	public String getWeektwo() {
		return weektwo;
	}

	public void setWeektwo(String weektwo) {
		this.weektwo = weektwo;
	}

	public String getWeekthree() {
		return weekthree;
	}

	public void setWeekthree(String weekthree) {
		this.weekthree = weekthree;
	}

	public String getWeekfour() {
		return weekfour;
	}

	public void setWeekfour(String weekfour) {
		this.weekfour = weekfour;
	}

	public String getWeekfive() {
		return weekfive;
	}

	public void setWeekfive(String weekfive) {
		this.weekfive = weekfive;
	}

	public WorkWeekReport getWorkweekid() {
		return workweekid;
	}

	public void setWorkweekid(WorkWeekReport workweekid) {
		this.workweekid = workweekid;
	}

	public String getWeekid() {
		return weekid;
	}

	public void setWeekid(String weekid) {
		this.weekid = weekid;
	}

	public String getWeeksix() {
		return weeksix;
	}

	public void setWeeksix(String weeksix) {
		this.weeksix = weeksix;
	}

	public String getWeekseven() {
		return weekseven;
	}

	public void setWeekseven(String weekseven) {
		this.weekseven = weekseven;
	}

	
}
