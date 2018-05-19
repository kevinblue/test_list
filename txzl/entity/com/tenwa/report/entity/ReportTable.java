package com.tenwa.report.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import com.tenwa.report.enums.QueryType;

@Entity
@Table(name = "Report_Table")
@XmlRootElement(name = "table")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportTable implements ReportContent {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	@XmlID
	private String id;

	@Column(name = "NAME_", nullable = false)
	private String name;
	
	@Column(name = "ENNAME_")
	private String enname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "datasource_ID")
	@XmlIDREF
	private ReportDataSource reportDataSource;

	@Enumerated(EnumType.STRING)
	@Column(name = "SQLTYPE", nullable = false)
	private QueryType queryType;

	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	//mysql sql为关键字，不能作为字段名
	//@Column(name="SQL_",nullable=false)
	private String sql;

	private String sqlParamValue;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("position asc")
	@JoinColumn(name = "REPORT_TABLE_ID")
	@XmlElementWrapper(name = "columns")
	@XmlElement(name = "column")
	private Set<ReportColumn> reportColumns;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("position asc")
	@JoinColumn(name = "REPORT_TABLE_ID")
	@XmlElementWrapper(name = "controls")
	@XmlElement(name = "control")
	private Set<ReportControl> controls;
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_TABLE_ID")
	@OrderBy("position asc")
	@Where(clause = "filterType='SEARCH'")
	@XmlElementWrapper(name = "searchs")
	@XmlElement(name = "search")
	private Set<ReportFilter> search;

	private String searchExpress;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_TABLE_ID")
	@OrderBy("position asc")
	@Where(clause = "filterType='FILTER'")
	@XmlElementWrapper(name = "filters")
	@XmlElement(name = "filter")
	private Set<ReportFilter> filter;
	

	private String filterExpress;

	private Integer pageSize;

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean showRowNumber;

	//@Type(type = "org.hibernate.type.YesNoType")
	private Boolean showTotalTitle;

	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isScale; // 列是否按比例扩展至全屏幕
	
	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isCache; // 是否缓存
	
	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isExcel; //是否具有报表导出控制 

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

	public ReportDataSource getReportDataSource() {
		return reportDataSource;
	}

	public void setReportDataSource(ReportDataSource reportDataSource) {
		this.reportDataSource = reportDataSource;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Set<ReportColumn> getReportColumns() {
		return reportColumns;
	}

	public void setReportColumns(Set<ReportColumn> reportColumns) {
		this.reportColumns = reportColumns;
	}

	public Set<ReportFilter> getSearch() {
		return search;
	}

	public void setSearch(Set<ReportFilter> search) {
		this.search = search;
	}

	public Set<ReportFilter> getFilter() {
		return filter;
	}

	public void setFilter(Set<ReportFilter> filter) {
		this.filter = filter;
	}

	public String getSearchExpress() {
		return searchExpress;
	}

	public void setSearchExpress(String searchExpress) {
		this.searchExpress = searchExpress;
	}

	public String getFilterExpress() {
		return filterExpress;
	}

	public void setFilterExpress(String filterExpress) {
		this.filterExpress = filterExpress;
	}

	public String getSqlParamValue() {
		return sqlParamValue;
	}

	public void setSqlParamValue(String sqlParamValue) {
		this.sqlParamValue = sqlParamValue;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize == 0)
			pageSize = 20;
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null || pageSize == 0) {
			this.pageSize = 20;
		} else {
			this.pageSize = pageSize;
		}
	}

	public Boolean getShowRowNumber() {

		return showRowNumber;
	}

	public void setShowRowNumber(Boolean showRowNumber) {
		if (showRowNumber == null) {
			this.showRowNumber = false;
		} else {
			this.showRowNumber = showRowNumber;
		}
	}

	public Boolean getShowTotalTitle() {
		return showTotalTitle;
	}

	public void setShowTotalTitle(Boolean showTotalTitle) {
		if (showTotalTitle == null) {
			this.showTotalTitle = false;
		} else {
			this.showTotalTitle = showTotalTitle;
		}
	}

	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this);
	}

	public Boolean getIsScale() {
		if (isScale == null) {
			isScale = false;
		}

		return isScale;
	}


	public Boolean getIsCache() {
		if (isCache == null) {
			isCache = false;
		}

		return isCache;
	}

	public void setIsCache(Boolean isCache) {
		this.isCache = isCache;
	}


	public void setIsScale(Boolean isScale) {
		if (isScale == null) {
			this.isScale = false;
		} else {
			this.isScale = isScale;
		}
	}

	public Set<ReportControl> getControls() {
		return controls;
	}

	public void setControls(Set<ReportControl> controls) {
		this.controls = controls;
	}

	public Boolean getIsExcel() {
		if (isExcel == null) {
			isExcel = false;
		}
		return isExcel;
	}

	public void setIsExcel(Boolean isExcel) {
		this.isExcel = isExcel;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}


	
	
}
