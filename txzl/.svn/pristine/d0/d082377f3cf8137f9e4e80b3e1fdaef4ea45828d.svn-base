package com.tenwa.leasing.entity.finance;

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
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
@Entity
@FieldName(name = "财务数据表")
@Table(name = "Financial_DATA")
public class FinancialData {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;	
    
	@Column(name = "Financial_Date", length = 100)
	@FieldName(name="财务数据对应的日期")
	private String FinancialDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_ID")
	@FieldName(name="承租人")
	private CustInfo custInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Fina_sub_id")
	@FieldName(name="财务科目")
	private FinancialSubjects FinancialSubjectsid;
	
   @Column(name = "SUBJECT_DATA", length = 100)
   @FieldName(name="财务数据")
   private String subjectData;
   
   @Column(name = "SUBJECT_OTHER_DATA", length = 1000)
   @FieldName(name="财务其他数据")
   private String subjectOtherData;
  
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "CREATOR_")
   @FieldName(name="创建人")
    private User creator;
	
	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name = "MODIFY_DATE", length = 40)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfo custInfo) {
		this.custInfo = custInfo;
	}

	public String getSubjectData() {
		return subjectData;
	}

	public void setSubjectData(String subjectData) {
		this.subjectData = subjectData;
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

	public FinancialSubjects getFinancialSubjectsid() {
		return FinancialSubjectsid;
	}

	public void setFinancialSubjectsid(FinancialSubjects financialSubjectsid) {
		FinancialSubjectsid = financialSubjectsid;
	}

	public String getFinancialDate() {
		return FinancialDate;
	}

	public void setFinancialDate(String financialDate) {
		FinancialDate = financialDate;
	}

	public String getSubjectOtherData() {
		return subjectOtherData;
	}

	public void setSubjectOtherData(String subjectOtherData) {
		this.subjectOtherData = subjectOtherData;
	}
}
