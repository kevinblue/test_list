package com.tenwa.report.entity;

import java.util.Set;

import static com.tenwa.kernal.utils.StringUtil.booleanToString;
import static com.tenwa.kernal.utils.StringUtil.nullToString;

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
import org.hibernate.annotations.Where;
import org.json.JSONException;
import org.json.JSONObject;

import com.tenwa.report.enums.ChartType;
import com.tenwa.report.enums.FusionChart;
import com.tenwa.report.enums.QueryType;

@Entity
@Table(name = "Report_Chart")
@XmlRootElement(name = "chart")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportChart implements ReportContent {
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	@XmlID
	private String id;

	@Column(name = "name_", unique = true)
	private String name;
	
	@Column(name = "ENNAME_")
	private String enname;

	private String caption;

	private String subCaption;

	private String xAxisName;

	private String yAxisName;

	private String sAxisName; // 右侧纵坐标

	/*
	 * private Boolean is3D;
	 * 
	 * private Boolean isMultiSerial;
	 */
	private Boolean showValue; // 是否显示数值

	@Enumerated(EnumType.STRING)
	private ChartType chartType;

	@Enumerated(EnumType.STRING)
	private FusionChart fusionChart;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "datasource_id")
	@XmlIDREF
	private ReportDataSource reportDataSource;

	@Enumerated(EnumType.STRING)
	private QueryType queryType;

	@Lob
	private String query;

	private String queryParamValue;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("position asc")
	@JoinColumn(name = "REPORT_CHART_ID")
	@XmlElementWrapper(name = "columns")
	@XmlElement(name = "column")
	private Set<ReportColumn> reportColumns;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_CHART_ID")
	@OrderBy("position asc")
	@Where(clause = "filterType='SEARCH'")
	@XmlElementWrapper(name = "searchs")
	@XmlElement(name = "search")
	private Set<ReportFilter> search;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "REPORT_CHART_ID")
	@OrderBy("position asc")
	@Where(clause = "filterType='FILTER'")
	@XmlElementWrapper(name = "filters")
	@XmlElement(name = "filter")
	private Set<ReportFilter> filter;

	private String filterExpress;

	private String searchExpress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubCaption() {
		return subCaption;
	}

	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}

	public String getxAxisName() {
		return xAxisName;
	}

	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	public String getyAxisName() {
		return yAxisName;
	}

	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}

	public Boolean getShowValue() {
		return showValue;
	}

	public void setShowValue(Boolean showValue) {
		this.showValue = showValue;
	}

	public ChartType getChartType() {
		return chartType;
	}

	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
	}

	public ReportDataSource getReportDataSource() {
		return reportDataSource;
	}

	public void setReportDataSource(ReportDataSource reportDataSource) {
		this.reportDataSource = reportDataSource;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQueryParamValue() {
		return queryParamValue;
	}

	public void setQueryParamValue(String queryParamValue) {
		this.queryParamValue = queryParamValue;
	}

	public JSONObject toJson() throws JSONException {
		JSONObject json = new JSONObject();
		json.putOpt("id", nullToString(this.getId()));
		json.putOpt("caption", nullToString(this.getCaption()));
		json.putOpt("subCaption", nullToString(this.getSubCaption()));
		json.putOpt("xAxisName", nullToString(this.getxAxisName()));
		json.putOpt("yAxisName", nullToString(this.getyAxisName()));
		json.putOpt("sAxisName", nullToString(this.getsAxisName()));
		json.putOpt("showValue", booleanToString(this.getShowValue()));
		return json;
	}

	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this);
	}

	public Set<ReportColumn> getReportColumns() {
		return reportColumns;
	}

	public void setReportColumns(Set<ReportColumn> reportColumns) {
		this.reportColumns = reportColumns;
	}

	public String getFilterExpress() {
		return filterExpress;
	}

	public void setFilterExpress(String filterExpress) {
		this.filterExpress = filterExpress;
	}

	public String getSearchExpress() {
		return searchExpress;
	}

	public void setSearchExpress(String searchExpress) {
		this.searchExpress = searchExpress;
	}

	public Set<ReportFilter> getFilter() {
		return filter;
	}

	public void setFilter(Set<ReportFilter> filter) {
		this.filter = filter;
	}

	public Set<ReportFilter> getSearch() {
		return search;
	}

	public void setSearch(Set<ReportFilter> search) {
		this.search = search;
	}

	public FusionChart getFusionChart() {
		return fusionChart;
	}

	public void setFusionChart(FusionChart fusionChart) {
		this.fusionChart = fusionChart;
	}

	public String getsAxisName() {
		return sAxisName;
	}

	public void setsAxisName(String sAxisName) {
		this.sAxisName = sAxisName;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	
}
