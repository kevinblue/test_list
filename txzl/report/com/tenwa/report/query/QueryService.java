package com.tenwa.report.query;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.MutablePair;

import com.tenwa.business.entity.User;
import com.tenwa.report.entity.ReportColumn;
import com.tenwa.report.entity.ReportDataSource;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.enums.QueryType;

public interface QueryService {
	
	public static final String _TENWA_ROWNUMBER = "_TENWA_ROWNUMBER_";	
	public static final String _TENWA_ROW_COLOR_ = "_TENWA_ROW_COLOR_";
	public static final String _TENWA_CELL_COLOR_ = "_TENWA_CELL_COLOR_";

	public void initQuery(String contentId, ReportDataSource rds, String sql, QueryType queryType, Set<ReportColumn> columns, User user);
	
	public void addRestrction(QueryRestriction key,Boolean value);

	public List<Map<String, CellData>> getTableData(List<MutablePair<ReportFilter, String>> params, Integer start, Integer limit,ReportTable ... table)
			throws Exception;

	public List<Map<String, String>> getTableMetadata(List<MutablePair<String, String>> params) throws Exception;

	public int getTotalCount(List<MutablePair<ReportFilter, String>> params,ReportTable ... table) throws Exception;

	public List<Map<String, CellData>> getTotal(List<MutablePair<ReportFilter, String>> params,ReportTable ... table) throws Exception;

	public void setExternOrder(String externOrder);

	public List<Map<String, CellData>> getChartData(List<MutablePair<ReportFilter, String>> params) throws Exception;
	
	public boolean isQueryStart(Integer start);

}
