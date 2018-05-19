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


/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:23:41
 * @info 部门建设信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "部门建设信息")
@Table(name="DEPARTMENT_BUILD_DETAIL")
public class 	DepartmentBuildDetail{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	
	@FieldName(name = "内容")
	@Column(name="CONTENT", length=200)
	private String content;
	
	@FieldName(name = "区域")
	@Column(name="AREA", length=200)
	private String area;
	
	@FieldName(name = "内容详情")
	@Column(name="CONTENTS", length=200)
	private String contents;

	@ManyToOne(targetEntity = SaleWeekReport.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "SALEWEEK_ID")
	@FieldName(name="工作周报编号")
	private SaleWeekReport saleweekid;
	
	@FieldName(name = "项目阶段")
	@Column(name="PROJ_STATUS", length=200)
	private String projstatus;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public SaleWeekReport getSaleweekid() {
		return saleweekid;
	}

	public void setSaleweekid(SaleWeekReport saleweekid) {
		this.saleweekid = saleweekid;
	}

	public String getProjstatus() {
		return projstatus;
	}

	public void setProjstatus(String projstatus) {
		this.projstatus = projstatus;
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
