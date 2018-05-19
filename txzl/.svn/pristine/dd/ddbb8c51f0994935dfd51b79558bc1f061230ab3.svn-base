package com.tenwa.leasing.entity.cust;

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


/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:23:41
 * @info 工作周报信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "工作周报")
@Table(name="T_SALE_WEEK_REPORT")
public class SaleWeekReport{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;

	@FieldName(name = "流水号")
	@Column(name="SERIAL_ID", length=200)
	private String serialid;
	
	@FieldName(name = "开始时间")
	@Column(name="START_DATE", length=200)
	private String startDate;
	
	@FieldName(name = "结束时间")
	@Column(name="END_DATE", length=200)
	private String endDate;
	
	@FieldName(name = "周报状态")
	@Column(name="WEEK_STYLE", length=200)
	private String weekstyle;
	
	@ManyToOne
	@FieldName(name = "周报登记人")
	@JoinColumn(name="REGISTER_ID")
	private User registerid;
		
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
	
	@FieldName(name="周报详情")
	@OneToMany(mappedBy="saleweekid", fetch=FetchType.LAZY)
	private Set<SaleWeekReportDetail>  saleweekreportid=new HashSet<SaleWeekReportDetail>();	
	
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

	public String getSerialid() {
		return serialid;
	}

	public void setSerialid(String serialid) {
		this.serialid = serialid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public User getRegisterid() {
		return registerid;
	}

	public void setRegisterid(User registerid) {
		this.registerid = registerid;
	}

	public String getWeekstyle() {
		return weekstyle;
	}

	public void setWeekstyle(String weekstyle) {
		this.weekstyle = weekstyle;
	}

	public Set<SaleWeekReportDetail> getSaleweekreportid() {
		return saleweekreportid;
	}

	public void setSaleweekreportid(Set<SaleWeekReportDetail> saleweekreportid) {
		this.saleweekreportid = saleweekreportid;
	}

	

	
}
