package com.tenwa.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.report.enums.AlignType;
import com.tenwa.report.enums.ChartType;
import com.tenwa.report.enums.ColumnDataType;
import com.tenwa.report.enums.ContentType;
import com.tenwa.report.enums.UsageType;

@Entity
@Table(name = "Report_Column")
@XmlRootElement(name = "column")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportColumn {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	@XmlID
	private String id;

	@Column(name = "NAME_")
	private String name;

	@Enumerated(EnumType.STRING)
	private ColumnDataType columnDataType;

	@Enumerated(EnumType.STRING)
	private UsageType usageType; // for chart

	private String formater; // 类型的格式化串

	private String label;
	
	private String enLabel;

	private Integer width;

	private Integer position;

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isVisible;

	private String color;

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isCountTotal;

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isCountSubTotal;

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isGroupby;

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isMerge;

	@Enumerated(EnumType.STRING)
	private ContentType actionType;

	@Enumerated(EnumType.STRING)
	private AlignType alignType;

	private String action;

	private String actionParamters;

	private String actionCondition;

	// 以下字段图表
	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean showValue; // 用于图表，是否显示数值

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isRightY; // 字段显示用于右Y轴

	@Enumerated(EnumType.STRING)
	private ChartType combiType; // 用于组合图时，该列生成的图形 Area,Line,Column

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ColumnDataType getColumnDataType() {
		return columnDataType;
	}

	public void setColumnDataType(ColumnDataType columnDataType) {
		this.columnDataType = columnDataType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/*
	 * public ReportTable getReportTable() {
	 * return reportTable;
	 * }
	 * public void setReportTable(ReportTable reportTable) {
	 * this.reportTable = reportTable;
	 * }
	 */
	public int getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		if (position == null) {
			this.position = position;
		} else {
			this.position = position;
		}
	}

	public String getFormater() {
		return formater;
	}

	public void setFormater(String formater) {
		this.formater = formater;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		if (width == null) {
			this.width = 100;
		} else {
			this.width = width;
		}
	}

	public Boolean getIsCountTotal() {
		if (isCountTotal == null)
			return false;
		return isCountTotal;
	}

	public void setIsCountTotal(Boolean isCountTotal) {
		if (isCountTotal == null) {
			this.isCountSubTotal = false;
		} else {
			this.isCountTotal = isCountTotal;
		}
	}

	public Boolean getIsCountSubTotal() {
		if (isCountSubTotal == null) {
			return false;
		}
		return isCountSubTotal;
	}

	public void setIsCountSubTotal(Boolean isCountSubTotal) {
		if (isCountSubTotal == null) {
			this.isCountSubTotal = false;
		} else {
			this.isCountSubTotal = isCountSubTotal;
		}
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		if (isVisible == null) {
			this.isVisible = true;
		} else {
			this.isVisible = isVisible;
		}
	}

	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "reportTable");
	}

	public Boolean getIsGroupby() {
		return isGroupby;
	}

	public void setIsGroupby(Boolean isGroupby) {
		if (isGroupby == null) {
			this.isGroupby = false;
		} else {
			this.isGroupby = isGroupby;
		}
	}

	public ContentType getActionType() {
		return actionType;
	}

	public void setActionType(ContentType actionType) {
		this.actionType = actionType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionParamters() {
		return actionParamters;
	}

	public void setActionParamters(String actionParamters) {
		this.actionParamters = actionParamters;
	}

	public String getActionCondition() {
		return actionCondition;
	}

	public void setActionCondition(String actionCondition) {
		this.actionCondition = actionCondition;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public UsageType getUsageType() {
		return usageType;
	}

	public void setUsageType(UsageType usageType) {
		this.usageType = usageType;
	}

	public Boolean getShowValue() {
		if (showValue == null)
			return false;
		return showValue;
	}

	public void setShowValue(Boolean showValue) {
		if (showValue == null) {
			this.showValue = false;
		}
		this.showValue = showValue;
	}

	public Boolean getIsRightY() {
		if (this.isRightY == null) {
			return false;
		}
		return isRightY;
	}

	public void setIsRightY(Boolean isRightY) {
		if (isRightY == null) {
			this.isRightY = false;
		}
		this.isRightY = isRightY;
	}

	public ChartType getCombiType() {
		return combiType;
	}

	public void setCombiType(ChartType combiType) {
		this.combiType = combiType;
	}

	public AlignType getAlignType() {
		return alignType;
	}

	public void setAlignType(AlignType alignType) {
		this.alignType = alignType;
	}

	public Boolean getIsMerge() {
		if (this.isMerge == null) {
			return false;
		}
		return isMerge;
	}

	public void setIsMerge(Boolean isMerge) {
		if (isMerge == null) {
			this.isMerge = false;
		} else {
			this.isMerge = isMerge;
		}
	}

	public String getEnLabel() {
		return enLabel;
	}

	public void setEnLabel(String enLabel) {
		this.enLabel = enLabel;
	}

	
}
