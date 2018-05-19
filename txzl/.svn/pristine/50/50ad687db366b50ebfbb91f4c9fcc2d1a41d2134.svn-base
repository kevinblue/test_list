package com.tenwa.leasing.entity.excel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethod;

@Entity
@FieldName(name = "sheet中的单元格配置")
@Table(name = "EXCEL_CELL_CONFIG")
public class ExcelCellConfig {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name = "标识符")
	@Column(name = "ID", nullable = false, length = 32)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SHEET_CONFIG_ID")
	@FieldName(name="Sheet Config Id")
	private ExcelSheetConfig sheetConfigId;
	
	@Column(name = "CELL_NAME", length = 50)
	@FieldName(name="cell名")
	private String cellName;

	@Column(name = "CELL_CODE", length = 50)
	@FieldName(name="cell编号")
	private String cellCode;
	
	@Column(name = "CELL_DISPLAY_FORMAT", length = 50)
	@FieldName(name="cell显示格式")
	private String cellDisplayFormat;
	
	@Column(name = "ROW_INDEX")
	@FieldName(name="行坐标")
	private Integer rowIndex;
	
	@Column(name = "COLUMN_INDEX")
	@FieldName(name="列坐标")
	private Integer columnIndex;
	
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

	public ExcelSheetConfig getSheetConfigId() {
		return sheetConfigId;
	}

	public void setSheetConfigId(ExcelSheetConfig sheetConfigId) {
		this.sheetConfigId = sheetConfigId;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getCellCode() {
		return cellCode;
	}

	public void setCellCode(String cellCode) {
		this.cellCode = cellCode;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public Integer getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(Integer columnIndex) {
		this.columnIndex = columnIndex;
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

	public String getCellDisplayFormat() {
		return cellDisplayFormat;
	}

	public void setCellDisplayFormat(String cellDisplayFormat) {
		this.cellDisplayFormat = cellDisplayFormat;
	}
	
}
