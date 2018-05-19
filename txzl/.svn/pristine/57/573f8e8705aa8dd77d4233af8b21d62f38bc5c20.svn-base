package com.tenwa.freemaker.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.freemaker.enums.ColumnFetchEnum;
import com.tenwa.freemaker.enums.EntityColumTypeEnum;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "freemaker实体类Column配置表") 
@Table(name = "T_FREEMAKER_ENTITY_COLUMN")
public class FreeMakerEntityColumn implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID_", nullable = false, length = 32)
	private String id;
	
	@FieldName(name="实体属性名")
	@Column(name="FIELD_NAME", length=100)	
	private String fieldName;
	
	@FieldName(name="实体属性类型")
	@Column(name="FIELD_TYPE", length=100)	
	private String fieldType;
	
	@FieldName(name="实体属性类型")
	@Column(name="FIELD_TYPE_FULL_NAME", length=100)	
	private String fieldTypeFullName;
	
	@FieldName(name="字段是否是索引字段")
	@Column(name="TABLE_IS_INDEX")	
	private Boolean tableIsIndex;
	
	@FieldName(name="索引名称")
	@Column(name="TABLE_INDEX_NAME", length=100)	
	private String tableIndexName;
	
	@FieldName(name="数据库列名")
	@Column(name="TABLE_COLUMN_NAME", length=100)	
	private String tableColumnName;
	
	@Enumerated(EnumType.STRING)
	@FieldName(name = "列类型")
	@Column(name="TABLE_COLUMN_TYPE", length=100)	
	private EntityColumTypeEnum tableColumnType;
	
	@FieldName(name="列注释")
	@Column(name="TABLE_COLUMN_DESC", length=100)	
	private String tableColumnDesc;
	
	@FieldName(name="其他属性")
	@Column(name="TABLE_COLUMN_DEFINITION", length=100)	
	private String tableColumnDefinition;
	
	@FieldName(name="长度")
	@Column(name="TABLE_COLUMN_LENGTH")	
	private Integer tableColumnLength;
	
	@FieldName(name="精度")
	@Column(name="TABLE_COLUMN_PRECISION")	
	private Integer tableColumnPrecision;
	
	@FieldName(name="小数点后位数")
	@Column(name="TABLE_COLUMN_SCALE")	
	private Integer tableColumnScale;
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "PID_")
	@FieldName(name = "关联freemakerEntity")
	private FreeMakerEntity entity;
	
	@FieldName(name="是否非空约束")
	@Column(name="TABLE_IS_NOTNULL")	
	private Boolean tableIsNotnull;
	
	@FieldName(name="是否唯一性约束")
	@Column(name="TABLE_IS_UNIQUE")	
	private Boolean tableIsUnique;
	
	@FieldName(name="实体排序")
	@Column(name="COLUMN_ORDER_BY", length=100)	
	private String columnOrderBy;
	
	@Enumerated(EnumType.STRING)
	@FieldName(name = "加载类型")
	@Column(name="TABLE_FETCH_TYPE", length=100)	
	private ColumnFetchEnum fetchType;
	
	@Column(nullable = false, name = "POSITION_",columnDefinition="INT DEFAULT 0")
	@FieldName(name = "菜单顺序")
	private Integer position = 0;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=40)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=40)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getTableColumnName() {
		return tableColumnName;
	}

	public void setTableColumnName(String tableColumnName) {
		this.tableColumnName = tableColumnName;
	}

	public EntityColumTypeEnum getTableColumnType() {
		return tableColumnType;
	}

	public void setTableColumnType(EntityColumTypeEnum tableColumnType) {
		this.tableColumnType = tableColumnType;
	}

	public String getTableColumnDesc() {
		return tableColumnDesc;
	}

	public void setTableColumnDesc(String tableColumnDesc) {
		this.tableColumnDesc = tableColumnDesc;
	}

	public FreeMakerEntity getEntity() {
		return entity;
	}

	public void setEntity(FreeMakerEntity entity) {
		this.entity = entity;
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

	public Boolean getTableIsIndex() {
		return tableIsIndex;
	}

	public void setTableIsIndex(Boolean tableIsIndex) {
		this.tableIsIndex = tableIsIndex;
	}

	public Integer getTableColumnLength() {
		return tableColumnLength;
	}

	public void setTableColumnLength(Integer tableColumnLength) {
		this.tableColumnLength = tableColumnLength;
	}

	public String getTableIndexName() {
		return tableIndexName;
	}

	public void setTableIndexName(String tableIndexName) {
		this.tableIndexName = tableIndexName;
	}

	public Integer getTableColumnPrecision() {
		return tableColumnPrecision;
	}

	public void setTableColumnPrecision(Integer tableColumnPrecision) {
		this.tableColumnPrecision = tableColumnPrecision;
	}

	public Integer getTableColumnScale() {
		return tableColumnScale;
	}

	public void setTableColumnScale(Integer tableColumnScale) {
		this.tableColumnScale = tableColumnScale;
	}

	public Boolean getTableIsNotnull() {
		return tableIsNotnull;
	}

	public void setTableIsNotnull(Boolean tableIsNotnull) {
		this.tableIsNotnull = tableIsNotnull;
	}

	public Boolean getTableIsUnique() {
		return tableIsUnique;
	}

	public void setTableIsUnique(Boolean tableIsUnique) {
		this.tableIsUnique = tableIsUnique;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getFieldTypeFullName() {
		return fieldTypeFullName;
	}

	public void setFieldTypeFullName(String fieldTypeFullName) {
		this.fieldTypeFullName = fieldTypeFullName;
	}

	public String getTableColumnDefinition() {
		return tableColumnDefinition;
	}

	public void setTableColumnDefinition(String tableColumnDefinition) {
		this.tableColumnDefinition = tableColumnDefinition;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getColumnOrderBy() {
		return columnOrderBy;
	}

	public void setColumnOrderBy(String columnOrderBy) {
		this.columnOrderBy = columnOrderBy;
	}

	public ColumnFetchEnum getFetchType() {
		return fetchType;
	}

	public void setFetchType(ColumnFetchEnum fetchType) {
		this.fetchType = fetchType;
	}
	
	
}
