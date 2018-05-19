package com.tenwa.leasing.entity.excel;

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
@FieldName(name = "Excel Sheet 配置")
@Table(name = "EXCEL_SHEET_CONFIG")
public class ExcelSheetConfig {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name = "标识符")
	@Column(name = "ID", nullable = false, length = 32)
	private String id;
	
	@Column(name = "IMPORT_TYPE", length = 50)
	@FieldName(name="导入类型")
	private String importType;
	
	@Column(name = "SHEET_NAME", length = 50)
	@FieldName(name="sheet名")
	private String sheetName;

	@Column(name = "SHEET_CODE", length = 50)
	@FieldName(name="sheet编号")
	private String sheetCode;
	
	@Column(name = "ORDER_INDEX")
	@FieldName(name="排序位置")
	private Integer orderIndex;
		
	@FieldName(name = "对应的sheet中的表配置")
	@OneToMany(mappedBy = "sheetConfigId", fetch = FetchType.LAZY)
	private Set<ExcelTableConfig> excelTableConfigs = new HashSet<ExcelTableConfig>();

	@FieldName(name = "对应的sheet中的单元格配置")
	@OneToMany(mappedBy = "sheetConfigId", fetch = FetchType.LAZY)
	private Set<ExcelCellConfig> excelCellConfigs = new HashSet<ExcelCellConfig>();
	
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

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getSheetCode() {
		return sheetCode;
	}

	public void setSheetCode(String sheetCode) {
		this.sheetCode = sheetCode;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Set<ExcelTableConfig> getExcelTableConfigs() {
		return excelTableConfigs;
	}

	public void setExcelTableConfigs(Set<ExcelTableConfig> excelTableConfigs) {
		this.excelTableConfigs = excelTableConfigs;
	}

	public Set<ExcelCellConfig> getExcelCellConfigs() {
		return excelCellConfigs;
	}

	public void setExcelCellConfigs(Set<ExcelCellConfig> excelCellConfigs) {
		this.excelCellConfigs = excelCellConfigs;
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
