package com.tenwa.report.formatter;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.query.CellData;

public interface TableFormatter {
	public JSONObject generateJson() throws Exception;

	public void setDataSet(List<Map<String, CellData>> datas);

	public void setTable(ReportTable table);

	public void setGenerateHtml(boolean b);

	public void setTotalCount(Integer totalCount);
	
	
}
