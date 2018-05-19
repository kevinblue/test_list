package com.tenwa.report.formatter;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tenwa.report.entity.ReportChart;
import com.tenwa.report.query.CellData;

public interface ChartFormatter {
		
	public JSONObject generateJson() throws Exception;

	public void setDataSet(List<Map<String, CellData>> datas);

	public void setChart(ReportChart chart);
}
