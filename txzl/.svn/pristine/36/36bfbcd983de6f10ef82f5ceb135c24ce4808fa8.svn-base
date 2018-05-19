package com.tenwa.leasing.entity.officialsealregistration;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author zyh
 * @date 2016-10-31下午09:33:10
 * @info 公章使用登记
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "公章使用登记")
@Table(name="OFFICIAL_SEAL_REGISTRATION")
public class OfficialSealRegistration implements java.io.Serializable{
	
	private static final long serialVersionUID = -8332265650799921120L;
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	/*
	@ManyToOne
	@FieldName(name="项目编号")
	@JoinColumn(name="PROJ_ID")
	private ProjInfo projid;
	*/
	@FieldName(name = "项目名称")
	@Column(name = "PROJECT_NAME", length = 200)
	private String projectName;
	
	@FieldName(name="盖章文件名称")
	@Column(name="SEAL_FILE",length=1000)
	private String sealfile;
	
	@FieldName(name="流程编号编号/名称")
	@Column(name="FLOW_NUMBER",length=50)
	private String flownumber;
	
	@FieldName(name="报送部门")
	@Column(name="SUBMIT_DEPARTMENT",length=50)
	private String submitdepartment;
	
	@FieldName(name="文件份数")
	@Column(name="DOCUMENT_NUMBER",length=50)
	private String documentnumber;
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_MANAGE")
	@FieldName(name="项目经理")
	private User projManage;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_DEPT")
	@FieldName(name="出单部门")
	private Department projdept;
	
	
	@Column(name = "SEAL_TYPE",length=50)
	@FieldName(name="公章种类")
	private String sealtype;
	
	@FieldName(name="登记日期")
	@Column(name="REGISTRATION_DATE",length=50)
	private String registrationdate;
	
	@FieldName(name="备注")
	@Column(name="REMARKS",length=2000)
	private String remarks;
	
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSealfile() {
		return sealfile;
	}

	public void setSealfile(String sealfile) {
		this.sealfile = sealfile;
	}

	public String getFlownumber() {
		return flownumber;
	}

	public void setFlownumber(String flownumber) {
		this.flownumber = flownumber;
	}

	public String getSubmitdepartment() {
		return submitdepartment;
	}

	public void setSubmitdepartment(String submitdepartment) {
		this.submitdepartment = submitdepartment;
	}

	public String getDocumentnumber() {
		return documentnumber;
	}

	public void setDocumentnumber(String documentnumber) {
		this.documentnumber = documentnumber;
	}

	public User getProjManage() {
		return projManage;
	}

	public void setProjManage(User projManage) {
		this.projManage = projManage;
	}

	public Department getProjdept() {
		return projdept;
	}

	public void setProjdept(Department projdept) {
		this.projdept = projdept;
	}

	public String getSealtype() {
		return sealtype;
	}

	public void setSealtype(String sealtype) {
		this.sealtype = sealtype;
	}

	public String getRegistrationdate() {
		return registrationdate;
	}

	public void setRegistrationdate(String registrationdate) {
		this.registrationdate = registrationdate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
				
}
