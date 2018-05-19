package com.tenwa.report.filter;

import org.json.JSONArray;

import com.tenwa.report.entity.ReportContent;
import com.tenwa.report.entity.ReportFilter;

public interface ReportCombobox {

	public JSONArray getJson(ReportContent table, ReportFilter filter);
	

}
