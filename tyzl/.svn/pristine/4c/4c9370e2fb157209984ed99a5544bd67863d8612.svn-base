package com.tenwa.leasing.entity.finance;

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
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethod;

@Entity
@FieldName(name = "财务表")
@Table(name = "Financial_Table")
public class FinancialTable {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name = "标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@Column(name = "TITLE_NAME", length = 100)
	@FieldName(name="标题")
	private String titlename;
	
	@Column(name = "SHEET_NAME", length = 100)
	@FieldName(name="sheet名")
	private String sheetname;

	@Column(name = "TITLE_INDEX")
	@FieldName(name="排序位置")
	private Integer titleIndex;
	
	@Column(name = "key_ROW_INDEX")
	@FieldName(name="年所在行")
	private Integer keyRowIndex; 
	
	@Column(name = "KEY_CELL_INDEX")
	@FieldName(name="数据开始列")
	private Integer keyCellIndex; 
	
	@Column(name = "SKIP_CELL")
	@FieldName(name="跳过列")
	private Integer skipcell;
	

	@Column(name = "IS_SAVE_SKIPDATA", length =10)
	@FieldName(name="是否保存调过的行的数据")
	private String isSaveSkipData;
	
	@FieldName(name = "对应的科目")
	@OneToMany(mappedBy = "financialTable", fetch = FetchType.LAZY)
	private Set<FinancialSubjects> financialSubjects = new HashSet<FinancialSubjects>();

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

	public String getTitlename() {
		return titlename;
	}

	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}

	public String getSheetname() {
		return sheetname;
	}

	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}

	public Integer getTitleIndex() {
		return titleIndex;
	}

	public void setTitleIndex(Integer titleIndex) {
		this.titleIndex = titleIndex;
	}

	
	public Integer getKeyRowIndex() {
		return keyRowIndex;
	}

	public void setKeyRowIndex(Integer keyRowIndex) {
		this.keyRowIndex = keyRowIndex;
	}

	public Integer getKeyCellIndex() {
		return keyCellIndex;
	}

	public void setKeyCellIndex(Integer keyCellIndex) {
		this.keyCellIndex = keyCellIndex;
	}

	public Integer getSkipcell() {
		return skipcell;
	}

	public void setSkipcell(Integer skipcell) {
		this.skipcell = skipcell;
	}

	public Set<FinancialSubjects> getFinancialSubjects() {
		return financialSubjects;
	}

	public void setFinancialSubjects(Set<FinancialSubjects> financialSubjects) {
		this.financialSubjects = financialSubjects;
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

	public String getIsSaveSkipData() {
		return isSaveSkipData;
	}

	public void setIsSaveSkipData(String isSaveSkipData) {
		this.isSaveSkipData = isSaveSkipData;
	}
	
}
