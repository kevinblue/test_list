package com.tenwa.leasing.entity.excel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "Sheet中表格配置")
@Table(name = "EXCEL_TABLE_CONFIG")
public class ExcelTableConfig {
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
	
	@Column(name = "TABLE_NAME", length = 50)
	@FieldName(name="表格名字")
	private String tableName;
	
	@Column(name = "TABLE_CODE", length = 50)
	@FieldName(name="表格编号")
	private String tableCode;

	@Column(name = "TABLE_WIDTH", length = 50)
	@FieldName(name="表格宽度")
	private String tableWidth; 
	
	@Column(name = "START_ROW_NUM")
	@FieldName(name="表格开始行")
	private Integer startRowNum;
	
	@Column(name = "END_ROW_NUM")
	@FieldName(name="表格结束行")
	private Integer endRowNum;
	
	@Column(name = "START_COLUMN_NUM")
	@FieldName(name="表格开始列")
	private Integer startColumnNum;
	
	@Column(name = "END_COLUMN_NUM")
	@FieldName(name="表格结束列")
	private Integer endColumnNum;
	
	@Column(name = "COLUMNS_INDEX", length = 2000)
	@FieldName(name = "列索引配置【针对某一行中的列合并单元格，不适用行合并，普通单元格直接取列索引，合并单元格取第一列索引，以逗号隔开，行中无合并单元格请勿填写】")
	private String columnsIndex;
	
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(name = "COLUMNS_CONFIG")
	@FieldName(name="表格列配置")
	private String columnsConfig;
	
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
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public String getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(String tableWidth) {
		this.tableWidth = tableWidth;
	}

	public Integer getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(Integer startRowNum) {
		this.startRowNum = startRowNum;
	}

	public Integer getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(Integer endRowNum) {
		this.endRowNum = endRowNum;
	}

	public Integer getStartColumnNum() {
		return startColumnNum;
	}

	public void setStartColumnNum(Integer startColumnNum) {
		this.startColumnNum = startColumnNum;
	}

	public Integer getEndColumnNum() {
		return endColumnNum;
	}

	public void setEndColumnNum(Integer endColumnNum) {
		this.endColumnNum = endColumnNum;
	}

	public String getColumnsIndex() {
		return columnsIndex;
	}

	public void setColumnsIndex(String columnsIndex) {
		this.columnsIndex = columnsIndex;
	}

	public String getColumnsConfig() {
		return columnsConfig;
	}

	public void setColumnsConfig(String columnsConfig) {
		this.columnsConfig = columnsConfig;
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
