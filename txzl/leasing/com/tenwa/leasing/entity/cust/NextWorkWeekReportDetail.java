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
@Table(name="T_NEXT_WORK_WEEK_REPORT_DETAIL")
public class 	NextWorkWeekReportDetail{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name = "编号")
	@Column(name="NEXT_WEEK_ID", length=200)
	private String nextweekid;
	
	@FieldName(name = "下周一")
	@Column(name="NEXT_WEEK_ONE", length=200)
	private String nextweekone;
	
	@FieldName(name = "下周二")
	@Column(name="NEXT_WEEK_TWO", length=200)
	private String nextweektwo;
	
	@FieldName(name = "下周三")
	@Column(name="NEXT_WEEK_THREE", length=200)
	private String nextweekthree;
	
	@FieldName(name = "下周四")
	@Column(name="NEXT_WEEK_four", length=200)
	private String nextweekfour;
	
	@FieldName(name = "下周五")
	@Column(name="NEXT_WEEK_FIVE", length=200)
	private String nextweekfive;
	
	@FieldName(name = "下周六")
	@Column(name="NEXT_WEEK_SIX", length=200)
	private String nextweeksix;
	
	@FieldName(name = "下周日")
	@Column(name="NEXT_WEEK_SEVEN", length=200)
	private String nextweekseven;
	
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

	public WorkWeekReport getWorkweekid() {
		return workweekid;
	}

	public void setWorkweekid(WorkWeekReport workweekid) {
		this.workweekid = workweekid;
	}

	public String getNextweekone() {
		return nextweekone;
	}

	public void setNextweekone(String nextweekone) {
		this.nextweekone = nextweekone;
	}

	public String getNextweektwo() {
		return nextweektwo;
	}

	public void setNextweektwo(String nextweektwo) {
		this.nextweektwo = nextweektwo;
	}

	public String getNextweekthree() {
		return nextweekthree;
	}

	public void setNextweekthree(String nextweekthree) {
		this.nextweekthree = nextweekthree;
	}

	public String getNextweekfour() {
		return nextweekfour;
	}

	public void setNextweekfour(String nextweekfour) {
		this.nextweekfour = nextweekfour;
	}

	public String getNextweekfive() {
		return nextweekfive;
	}

	public void setNextweekfive(String nextweekfive) {
		this.nextweekfive = nextweekfive;
	}

	public String getNextweekid() {
		return nextweekid;
	}

	public void setNextweekid(String nextweekid) {
		this.nextweekid = nextweekid;
	}

	public String getNextweeksix() {
		return nextweeksix;
	}

	public void setNextweeksix(String nextweeksix) {
		this.nextweeksix = nextweeksix;
	}

	public String getNextweekseven() {
		return nextweekseven;
	}

	public void setNextweekseven(String nextweekseven) {
		this.nextweekseven = nextweekseven;
	}
	
}
