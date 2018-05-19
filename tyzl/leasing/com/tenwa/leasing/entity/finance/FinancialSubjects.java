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

@Entity
@FieldName(name = "财务科目表")
@Table(name = "Financial_SUBJECTS")
public class FinancialSubjects {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name = "标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FINANCIAL_Table")
	@FieldName(name="科目主表")
	private FinancialTable financialTable;
	
	@Column(name = "SUBJECT_NAME", length = 500)
	@FieldName(name = "科目名称")
	private String subjectName;

	@Column(name = "SUBJECT_CODE", length = 50)
	@FieldName(name = "科目编号")
	private String subjectCode;

	@Column(name = "SUBJECT_INDEX", length = 50)
	@FieldName(name = "对应EXCEL位置")
	private String subjectIndex;

	@Column(name = "formula", length = 1000)
	@FieldName(name = "对应的公式")
	private String formula;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name = "创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name = "创建时间")
	private String createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 40)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectIndex() {
		return subjectIndex;
	}

	public void setSubjectIndex(String subjectIndex) {
		this.subjectIndex = subjectIndex;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
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

	public FinancialTable getFinancialTable() {
		return financialTable;
	}

	public void setFinancialTable(FinancialTable financialTable) {
		this.financialTable = financialTable;
	}

}
