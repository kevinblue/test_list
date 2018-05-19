package com.tenwa.leasing.entity.windprojectapproval;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;

/**
 * 
 * @author ZYH
 * @date 2016-9-29上午09:33:10
 * @info 风电项目核准计划
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "风电项目核准计划")
@Table(name="WIND_PROJECT_APPROVAL")
public class WindProjectApproval {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	

	@FieldName(name="项目名称")
	@Column(name="PRO_NAME", length=500)
	private String proName;
	
	
	@FieldName(name="核准批次")
	@Column(name="APPROVAL_BATCH", length=200)
	private String approvalBatch;//
	
	@FieldName(name="规模（万千瓦）")
	@Column(name="SCALE",length=200)
	private Double scale;
	
	@FieldName(name="项目单位")
	@Column(name="PRO_UNIT",length=500)
	private String proUnit;
	
	@FieldName(name="所在省市")
	@Column(name="PROVINCES",length=200)
	private String  provinces;
	
	@FieldName(name="项目地址")
	@Column(name="PRO_ADD",length=500)
	private String  proAdd;
	
	@FieldName(name="计划核准时间")
	@Column(name="PLAN_APP_DATE",length=20)
	private String planAppDate;
	
	@FieldName(name="计划投产时间")
	@Column(name="PLAN_PROD_DATE",length=20)
	private String planProdDate;
	
	
	@FieldName(name="备注")
	@Column(name="NOTE",length=2000)
	private String note;

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
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getApprovalBatch() {
		return approvalBatch;
	}

	public void setApprovalBatch(String approvalBatch) {
		this.approvalBatch = approvalBatch;
	}

	

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public String getProUnit() {
		return proUnit;
	}

	public void setProUnit(String proUnit) {
		this.proUnit = proUnit;
	}

	public String getProvinces() {
		return provinces;
	}

	public void setProvinces(String provinces) {
		this.provinces = provinces;
	}

	public String getProAdd() {
		return proAdd;
	}

	public void setProAdd(String proAdd) {
		this.proAdd = proAdd;
	}

	public String getPlanAppDate() {
		return planAppDate;
	}

	public void setPlanAppDate(String planAppDate) {
		Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(planAppDate);
        if (m.find()) {
        	int a = Integer.parseInt(planAppDate);
    		Calendar c = new GregorianCalendar(1900,0,-1); 
    		 Date d = c.getTime();  
            Date _d = DateUtils.addDays(d, a);  
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy年MM月 " );
            String str = sdf.format(_d);
    		this.planAppDate =str;
        }else{
        	this.planAppDate = planAppDate;
        }
	}

	public String getPlanProdDate() {
		return planProdDate;
	}

	public void setPlanProdDate(String planProdDate) {
		Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(planProdDate);
        if (m.find()) {
        	int a = Integer.parseInt(planProdDate);
    		Calendar c = new GregorianCalendar(1900,0,-1); 
    		 Date d = c.getTime();  
            Date _d = DateUtils.addDays(d, a);  
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy年MM月 " );
            String str = sdf.format(_d);
    		this.planProdDate =str;
        }else{
        	this.planProdDate = planProdDate;
        }
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}
	
}
