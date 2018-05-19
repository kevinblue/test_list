package com.tenwa.report.filter;

import org.json.JSONArray;

import com.tenwa.report.dao.ReportDao;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;

public interface ReportCombobox {

	public JSONArray getJson(ReportTable table, ReportFilter filter,ReportDao reportDao) throws Exception;
	

}
