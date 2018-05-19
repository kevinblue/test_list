package com.tenwa.report.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TenwaReport")
@XmlAccessorType(XmlAccessType.FIELD)
public class TenwaReport {
	@XmlElementWrapper(name = "datasources")
	@XmlElement(name = "datasource")
	private List<ReportDataSource> datasources = new ArrayList<ReportDataSource>();

	@XmlElementWrapper(name = "reports")
	@XmlElement(name = "report")
	private List<Report> reports = new ArrayList<Report>();

	@XmlElementWrapper(name = "tables")
	@XmlElement(name = "table")
	private List<ReportTable> tables = new ArrayList<ReportTable>();

	@XmlElementWrapper(name = "charts")
	@XmlElement(name = "chart")
	private List<ReportChart> charts = new ArrayList<ReportChart>();

	@XmlElementWrapper(name = "pages")
	@XmlElement(name = "page")
	private List<ReportPage> pages = new ArrayList<ReportPage>();
	
	@XmlElementWrapper(name="layouts")
	@XmlElement(name="layout")
	private List<ReportLayout> layouts = new ArrayList<ReportLayout>();

	public void addDataSource(ReportDataSource ds) {
		this.datasources.add(ds);
	}

	public void addDataSource(Collection<ReportDataSource> dss) {
		this.datasources.addAll(dss);
	}

	public void addReport(Report report) {
		this.reports.add(report);
	}

	public void addReport(Collection<Report> reports) {
		this.reports.addAll(reports);
	}

	public void addChart(ReportChart chart) {
		this.charts.add(chart);
	}

	public void addChart(Collection<ReportChart> charts) {
		this.charts.addAll(charts);
	}

	public void addTable(ReportTable table) {
		this.tables.add(table);
	}

	public void addTable(Collection<ReportTable> tables) {
		this.tables.addAll(tables);
	}

	public void addPage(ReportPage page) {
		this.pages.add(page);
	}

	public void addPage(Collection<ReportPage> pages) {
		this.pages.addAll(pages);
	}
	
	public void addLayout(ReportLayout layout){
		this.layouts.add(layout);
	}
	
	public void addLayout(Collection<ReportLayout> layouts){
		this.layouts.addAll(layouts);
	}

	public List<ReportLayout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<ReportLayout> layouts) {
		this.layouts = layouts;
	}

	public List<ReportDataSource> getDatasources() {
		return datasources;
	}

	public void setDatasources(List<ReportDataSource> datasources) {
		this.datasources = datasources;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public List<ReportTable> getTables() {
		return tables;
	}

	public void setTables(List<ReportTable> tables) {
		this.tables = tables;
	}

	public List<ReportChart> getCharts() {
		return charts;
	}

	public void setCharts(List<ReportChart> charts) {
		this.charts = charts;
	}

	public List<ReportPage> getPages() {
		return pages;
	}

	public void setPages(List<ReportPage> pages) {
		this.pages = pages;
	}
	
	
}
