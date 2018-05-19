package com.tenwa.report.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

public enum FusionChart {
	Column2D(ChartType.Column, "MSColumn2D"),
	Column3D(ChartType.Column, "MSColumn3D"),
	StackedColumn2D(ChartType.Column, "StackedColumn2D"),
	StackedColumn3D(ChartType.Column, "StackedColumn3D"),
	ScrollColumn2D(ChartType.Column, "ScrollColumn2D"),
	ScrollStackedColumn2D(ChartType.Column, "ScrollStackedColumn2D"),
	Line2D(ChartType.Line, "MSLine"),
	ZomeLine(ChartType.Line, "ZoomLine"),
	ScrollLine2D(ChartType.Line, "ScrollLine2D"),
	Bar2D(ChartType.Bar, "MSBar2D"),
	Bar3D(ChartType.Bar, "MSBar3D"),
	StackedBar2D(ChartType.Bar, "StackedColumn2D"),
	StackedBar3D(ChartType.Bar, "StackedColumn3D"),
	Combi2D(ChartType.Combi, "MSCombi2D"),
	Combi3D(ChartType.Combi, "MSCombi3D"),
	CombiDY2D(ChartType.Combi,"MSCombiDY2D",true,true),
	ScrollCombi2D(ChartType.Combi, "ScrollCombi2D"),
	Area2D(ChartType.Area, "MSArea"),
	ScrollArea2D(ChartType.Area, "ScrollArea2D"),
	Pie2D(ChartType.Pie, "Pie2D", true),
	Pie3D(ChartType.Pie, "Pie3D", true),
	

	;
	private ChartType chartType;
	private String swfName;
	private boolean isSingleSerial;
	private boolean isDualY;

	private FusionChart(ChartType chartType, String swfName) {
		this.chartType = chartType;
		this.swfName = swfName;
		this.isSingleSerial = false;
	}

	private FusionChart(ChartType chartType, String swfName, boolean isSingleSerial) {
		this.chartType = chartType;
		this.swfName = swfName;
		this.isSingleSerial = isSingleSerial;
	}
	
	private FusionChart(ChartType chartType,String swfName,boolean isSingleSerial,boolean isDualY){
		this.chartType= chartType;
		this.swfName = swfName;
		this.isSingleSerial = false;
		this.isDualY = isDualY;
	}

	public ChartType getChartType() {
		return this.chartType;
	}

	public String getSwfName() {
		return this.swfName;
	}

	public boolean isSingleSerial() {
		return this.isSingleSerial;
	}
	
	public static List<FusionChart> findChartsByType(ChartType chartType){
		List<FusionChart> allCharts = EnumUtils.getEnumList(FusionChart.class);
		List<FusionChart> typedCharts = new ArrayList<FusionChart>();
		for(FusionChart chart : allCharts){
			if(chart.getChartType() == chartType){
				typedCharts.add(chart);
			}
		}
		return typedCharts;
		
	}

	public boolean isDualY() {
		return isDualY;
	}

}
