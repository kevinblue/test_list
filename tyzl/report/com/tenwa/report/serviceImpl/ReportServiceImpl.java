﻿package com.tenwa.report.serviceImpl;

import static com.tenwa.kernal.utils.StringUtil.booleanToString;
import static com.tenwa.kernal.utils.StringUtil.nullToString;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.poi.ss.formula.functions.Replace;
import org.apache.tools.ant.filters.TokenFilter.Trim;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.Group;
import com.tenwa.business.entity.Menu;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.service.AclService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.EnumUtil;
import com.tenwa.kernal.utils.PermissionUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.utils.WorkflowUtil;
import com.tenwa.report.dao.ReportDao;
import com.tenwa.report.entity.Report;
import com.tenwa.report.entity.ReportChart;
import com.tenwa.report.entity.ReportColumn;
import com.tenwa.report.entity.ReportContent;
import com.tenwa.report.entity.ReportControl;
import com.tenwa.report.entity.ReportControlMatch;
import com.tenwa.report.entity.ReportDataSource;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportLayout;
import com.tenwa.report.entity.ReportPage;
import com.tenwa.report.entity.ReportPowerControl;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.entity.ReportTableLazy;
import com.tenwa.report.entity.TenwaReport;
import com.tenwa.report.enums.AlignType;
import com.tenwa.report.enums.ChartType;
import com.tenwa.report.enums.ColumnDataType;
import com.tenwa.report.enums.ContentType;
import com.tenwa.report.enums.FilterType;
import com.tenwa.report.enums.FusionChart;
import com.tenwa.report.enums.QueryType;
import com.tenwa.report.enums.ReportType;
import com.tenwa.report.enums.UsageType;
import com.tenwa.report.query.QueryFactory;
import com.tenwa.report.query.QueryService;
import com.tenwa.report.service.ReportService;

@Service("reportService")
public class ReportServiceImpl extends AbstractBaseServiceImpl implements ReportService {
	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

	private static Map<String, Queue<String>> uploadMessage = new LinkedHashMap<String, Queue<String>>();

	@Resource(name = "reportDao")
	private ReportDao reportDao;

	@Resource(name = "queryFactory")
	private QueryFactory queryFactory;

	@Resource(name = "aclService")
	private AclService aclService;

	@Override
	public BaseDao getBussinessDao() throws Exception {
		return this.reportDao;
	}

	@Override
	public String updateAndQueryReportTree(String parent) throws Exception {
		// log.debug("",local);
		boolean isShowHidden = false;
		Report report = (Report) this.findEntityByID(Report.class, parent);
		// 初始化Report根结点
		if ("0".equals(parent) && report == null) {
			this.getBussinessDao()
					.getJdbcTemplate()
					.execute(
							"INSERT INTO REPORT_REPORT(ID_, NAME_, ENNAME_ ,CODE_, DESCRIPTION_, POSITION_, REPORTTYPE) "
									+ "VALUES ('0', '报表', 'REPORT' ,'RPROOT', '报表', '0', 'FOLDER')");
			report = (Report) this.findEntityByID(Report.class, parent);
		}
		JSONObject reportTreeJson = this.getReportTreeAsJson(report, true, isShowHidden);
		JSONArray treeJson = new JSONArray();
		treeJson.put(reportTreeJson);
		return treeJson.toString();
	}

	@Override
	public JSONObject getReportTreeAsJson(Report report, Boolean isRoot, Boolean showHidden) throws Exception {
		if (report == null)
			throw new Exception("Not Found Report Entity is NULL");

		JSONObject nodeJson = this.getReportTreeNodeAsJson(report);
		if (report.getChildrenReport() != null && report.getChildrenReport().size() > 0) {
			JSONArray childJsons = new JSONArray();
			for (Report childReport : report.getChildrenReport()) {
				JSONObject childNodeJson = getReportTreeAsJson(childReport, false, showHidden);
				childJsons.put(childNodeJson);
			}
			nodeJson.put("children", childJsons);
		}

		return nodeJson;

	}

	private JSONObject getReportTreeNodeAsJson(Report report) throws Exception {
		String currentState = "open";
		JSONObject nodeJson = new JSONObject();
		nodeJson.put("id", report.getId());
		if(WebUtil.isEnglish()){
			nodeJson.put("text", null == report.getEnname() || 0 > report.getEnname().length() ? report.getName() : report.getEnname());
		}else{
			nodeJson.put("text", report.getName());
		}
		String icon;
		switch (report.getReportType()) {
		case FOLDER:
			icon = "icon-home";
			break;
		case REPORT:
			icon = "icon-chart_bar";
			break;

		default:
			icon = "icon-home";
		}
		nodeJson.put("iconCls", icon);
		nodeJson.put("state", currentState);
		JSONObject attributesJson = new JSONObject();
		attributesJson.put("id", report.getId());
		//attributesJson.put("tablename", report.getTableName());
		attributesJson.put("reportortype", report.getReportOrType());
		attributesJson.put("reportclassify", report.getReportClassify());
		attributesJson.put("name", report.getName());
		attributesJson.put("enname", report.getEnname());
		attributesJson.put("code", report.getCode());
		attributesJson.put("position", report.getPosition());
		attributesJson.put("description", report.getDescription());
		attributesJson.put("reportType", nullToString(report.getReportType()));
		attributesJson.put("parentReport", (report.getParentReport() == null) ? "-1" : report.getParentReport().getId());
		nodeJson.put("attributes", attributesJson);
		return nodeJson;
	}

	@Override
	public JSONArray getReportContentTreeAsJson(String reportId) throws Exception {
		List<String> selectedTable = new ArrayList<String>();
		boolean hasChild = false;
		Report report = (Report) this.findEntityByID(Report.class, reportId);
		JSONArray jsonArray = new JSONArray();
		JSONObject reportJson = getReportTreeNodeAsJson(report);
		if (report.getLayout() != null && report.getLayout().size() > 0) {

			JSONArray jsonLayoutArray = new JSONArray();
			for (ReportLayout layout : report.getLayout()) {

				String contentId = layout.getContentId();
				if (contentId != null && !contentId.equals("")) {
					hasChild = true;
					JSONObject layoutJson = new JSONObject();
					String realContentId = contentId.substring(layout.getContentType().name().length() + 1);
					switch (layout.getContentType()) {
					case TABLE: {
						ReportTable table = this.reportDao.findEntityByID(ReportTable.class, realContentId);
						selectedTable.add(layout.getContentType().name() + "_" + table.getId());
						layoutJson = getReportContentNodeAsJson(table, layout, ContentType.TABLE);
					}
						break;
					case CHART:
						ReportChart chart = this.reportDao.findEntityByID(ReportChart.class, realContentId);
						selectedTable.add(layout.getContentType().name() + "_" + chart.getId());
						layoutJson = getReportContentNodeAsJson(chart, layout, ContentType.CHART);
						break;
					case PAGE:
						ReportPage page = this.reportDao.findEntityByID(ReportPage.class, realContentId);
						selectedTable.add(layout.getContentType().name() + "_" + page.getId());
						layoutJson = getReportContentNodeAsJson(page, layout, ContentType.PAGE);
						break;
					default:
						break;
					}
					if (hasChild) {
						jsonLayoutArray.put(layoutJson);
					} else {
						jsonArray.put(layoutJson);
					}
				}

			}
			if (hasChild) {
				reportJson.put("children", jsonLayoutArray);
				jsonArray.put(reportJson);
			}
		}

		// jsonArray.put(this.getSeparator("separator_1", 10));

		// List<ReportTable> tables = this.findEntities(ReportTable.class);
		List<ReportTable> tables = this.findResultsByHSQL("from ReportTable r order by r.name");
		if (tables != null && tables.size() > 0) {
			for (ReportTable table : tables) {
				if (!selectedTable.contains(ContentType.TABLE.name() + "_" + table.getId())) {
					jsonArray.put(getReportContentNodeAsJson(table, null, ContentType.TABLE));
				}
			}
		}

		// List<ReportChart> charts = this.findEntities(ReportChart.class);
		List<ReportChart> charts = this.findResultsByHSQL("from ReportChart r order by r.name");
		if (charts != null && charts.size() > 0) {
			for (ReportChart chart : charts) {
				if (!selectedTable.contains(ContentType.CHART.name() + "_" + chart.getId())) {
					jsonArray.put(getReportContentNodeAsJson(chart, null, ContentType.CHART));
				}
			}
		}

		List<ReportPage> pages = this.findResultsByHSQL("from ReportPage r order by r.name");
		if (pages != null && pages.size() > 0) {
			for (ReportPage page : pages) {
				if (!selectedTable.contains(ContentType.PAGE.name() + "_" + page.getId())) {
					jsonArray.put(getReportContentNodeAsJson(page, null, ContentType.PAGE));
				}
			}
		}
		return jsonArray;
	}

	private <T extends ReportContent> JSONObject getReportContentNodeAsJson(T t, ReportLayout layout, ContentType contentType)
			throws Exception {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("id", t.getId());
		if(WebUtil.isEnglish()){
			jsonObject.put("text", null == t.getEnname() || 0 > t.getEnname().length() ? t.getName() : t.getEnname()+ "[" + contentType.name() + "]");
		}else{
			jsonObject.put("text", t.getName() + "[" + contentType.name() + "]");
		}
		switch (contentType) {
		case TABLE:
			jsonObject.put("iconCls", "icon-bubble");
			break;
		case CHART:
			jsonObject.put("iconCls", "icon-chart_line");
			break;
		case PAGE:
			jsonObject.put("iconCls", "icon-department");
		default:
			break;
		}

		jsonObject.put("state", "open");

		JSONObject attributesJson = new JSONObject();
		if (layout != null) {
			jsonObject.put("checked", "true");
			attributesJson.put("layoutid", layout.getId());
			attributesJson.put("divHeight", nullToString(layout.getDivHeight(), "0"));
			attributesJson.put("divWidth", nullToString(layout.getDivWidth(), "0"));
			attributesJson.put("position", layout.getPosition());
		}
		attributesJson.put("contentId", t.getId());
		attributesJson.put("contentType", contentType);
		jsonObject.put("attributes", attributesJson);
		return jsonObject;
	}

	@Override
	public JSONArray getTableColumnsAsTreeJson(String tableId, String datasource, String sql, String queryType,
			List<MutablePair<String, String>> params) {
		JSONArray columnsJson = new JSONArray();
		ReportTable table = null;
		int position = 0;
		try {
			
			Map<String, JSONObject> dbColumnJsons = new LinkedHashMap<String, JSONObject>();
			// 先获取SQL语句中的表结构
			if (datasource != null && !"".equals(datasource.trim()) && sql != null && !"".equals(sql.trim())) {
				ReportDataSource rds;

				rds = (ReportDataSource) this.reportDao.findEntityByID(ReportDataSource.class, datasource);
				QueryType qt = EnumUtils.getEnum(QueryType.class, queryType);

				QueryService queryService = this.queryFactory.getService(EnumUtils.getEnum(QueryType.class, queryType));
				queryService.initQuery("", rds, sql, qt, null, null);

				List<Map<String, String>> metadata = queryService.getTableMetadata(params);

				for (Map<String, String> rowData : metadata) {
					JSONObject columnJson = new JSONObject();
					columnJson.put("id", rowData.get("columnLabel"));
					columnJson.put("text", rowData.get("columnLabel"));
					JSONObject attributes = new JSONObject();
					attributes.put("table_columnId", "");
					attributes.put("table_columnName", rowData.get("columnLabel"));
					attributes.put("table_columnLabel", rowData.get("columnLabel"));
					attributes.put("table_columnType", rowData.get("columnDbType"));
					attributes.put("table_columnRealName", rowData.get("columnRealName"));
					attributes.put("table_columnEnLabel", rowData.get("columnLabel"));
					attributes.put("position", String.valueOf(position++));
					attributes.put("table_columnWidth", "100");
					attributes.put("table_columnFormater", "");
					attributes.put("table_columnAlign", "left");
					columnJson.put("attributes", attributes);
					dbColumnJsons.put(rowData.get("columnLabel").toLowerCase(), columnJson);
				}
			}

			if (tableId != null && !"".equals(tableId)) {
				table = (ReportTable) this.findEntityByID(ReportTable.class, tableId);
				if (table != null && table.getReportColumns() != null && table.getReportColumns().size() > 0) {
					for (ReportColumn column : table.getReportColumns()) {

						// String iconCls = "icon-db";

						JSONObject columnJson = new JSONObject();
						columnJson.put("id", column.getName());
						if(WebUtil.isEnglish()){
							columnJson.put("text", null == column.getEnLabel() || 0  < column.getEnLabel().length() ? column.getName() + "[" + column.getName() + "]" : column.getName() + "[" + column.getEnLabel() + "]"  );
						}else{
							columnJson.put("text", column.getName() + "[" + column.getLabel() + "]");
						}
						
						columnJson.put("checked", "true");
						columnJson.put("iconCls", "icon-db");
						JSONObject attributes = new JSONObject();
						attributes.put("table_columnId", column.getId());
						attributes.put("table_columnName", nullToString(column.getName()));
						attributes.put("table_columnEnLabel",(null ==  column.getEnLabel() || 0 >= column.getEnLabel().length()) ? column.getName() : column.getEnLabel() );
						attributes.put("table_columnLabel", nullToString(column.getLabel()));
						attributes.put("position", String.valueOf(position++));
						attributes.put("table_columnType", nullToString(column.getColumnDataType(), ColumnDataType.STRING.name()));
						attributes.put("table_columnVisible", booleanToString(column.getIsVisible(), "1", "0"));
						attributes.put("table_columnGroupby", booleanToString(column.getIsGroupby(), "1", "0"));
						attributes.put("table_columnTotal", booleanToString(column.getIsCountTotal(), "1", "0"));
						attributes.put("table_columnSubTotal", booleanToString(column.getIsCountSubTotal(), "1", "0"));
						attributes.put("table_columnMerge", booleanToString(column.getIsMerge(), "1", "0"));
						attributes.put("table_columnWidth", nullToString(column.getWidth(), "100"));
						attributes.put("table_columnFormater", nullToString(column.getFormater()));
						attributes.put("table_columnActionType", nullToString(column.getActionType()));
						if (column.getActionType() == ContentType.PAGE) {
							attributes.put("table_columnActionUrl", "");
							attributes.put("table_columnActionUrl2", nullToString(column.getAction()));
						} else {
							attributes.put("table_columnActionUrl", nullToString(column.getAction()));
							attributes.put("table_columnActionUrl2", "");
						}
						attributes.put("table_columnActionParameter", nullToString(column.getActionParamters()));
						attributes.put("table_columnColor", nullToString(column.getColor()));
						attributes.put("table_columnActionCondition", nullToString(column.getActionCondition()));
						attributes.put("table_columnAlign", nullToString(column.getAlignType()));
						columnJson.put("attributes", attributes);
						// 判断已定义的列是否存在表中
						if (dbColumnJsons.containsKey(column.getName().toLowerCase())) {
							dbColumnJsons.remove(column.getName().toLowerCase());
						} else {
							columnJson.put("iconCls", "icon-attention");
						}

						columnsJson.put(columnJson);
					}
				}
			}

			// 添加剩下的表中列名
			for (String key : dbColumnJsons.keySet()) {
				columnsJson.put(dbColumnJsons.get(key));
			}

		} catch (Exception e) {
			log.error("", e);
		}
		return columnsJson;
	}

	@Override
	public JSONArray getChartColumnsAsTreeJson(String id, String datasource, String sql, String queryType,
			List<MutablePair<String, String>> params) {
		JSONArray columnsJson = new JSONArray();
		ReportChart chart = null;
		int position = 0;
		try {

			Map<String, JSONObject> dbColumnJsons = new LinkedHashMap<String, JSONObject>();
			// 先获取SQL语句中的表结构
			if (datasource != null && !"".equals(datasource.trim()) && sql != null && !"".equals(sql.trim())) {
				ReportDataSource rds;

				rds = (ReportDataSource) this.reportDao.findEntityByID(ReportDataSource.class, datasource);
				QueryType qt = EnumUtils.getEnum(QueryType.class, queryType);

				QueryService queryService = this.queryFactory.getService(EnumUtils.getEnum(QueryType.class, queryType));
				queryService.initQuery("", rds, sql, qt, null, null);

				List<Map<String, String>> metadata = queryService.getTableMetadata(params);

				for (Map<String, String> rowData : metadata) {
					JSONObject columnJson = new JSONObject();
					columnJson.put("id", rowData.get("columnLabel"));
					columnJson.put("text", rowData.get("columnLabel"));
					JSONObject attributes = new JSONObject();
					attributes.put("chart_columnId", "");
					attributes.put("chart_columnName", rowData.get("columnLabel"));
					attributes.put("chart_columnLabel", rowData.get("columnLabel"));
					attributes.put("chart_columnType", rowData.get("columnDbType"));
					attributes.put("position", String.valueOf(position++));
					attributes.put("chart_columnFormater", "");

					attributes.put("chart_columnUsageType", UsageType.DATA);
					columnJson.put("attributes", attributes);
					dbColumnJsons.put(rowData.get("columnLabel").toLowerCase(), columnJson);
				}
			}

			if (id != null && !"".equals(id)) {
				chart = this.findEntityByID(ReportChart.class, id);
				if (chart != null && chart.getReportColumns() != null && chart.getReportColumns().size() > 0) {
					for (ReportColumn column : chart.getReportColumns()) {

						// String iconCls = "icon-db";

						JSONObject columnJson = new JSONObject();
						columnJson.put("id", column.getName());
						columnJson.put("text", column.getName() + "[" + column.getLabel() + "]");
						columnJson.put("checked", "true");
						columnJson.put("iconCls", "icon-db");
						JSONObject attributes = new JSONObject();
						attributes.put("chart_columnId", column.getId());
						attributes.put("chart_columnName", nullToString(column.getName()));
						attributes.put("chart_columnLabel", nullToString(column.getLabel()));
						attributes.put("position", String.valueOf(position++));
						attributes.put("chart_columnType", nullToString(column.getColumnDataType(), ColumnDataType.STRING.name()));
						attributes.put("chart_columnFormater", nullToString(column.getFormater()));
						attributes.put("chart_columnActionType", nullToString(column.getActionType()));
						if (column.getActionType() == ContentType.PAGE) {
							attributes.put("chart_columnActionUrl", "");
							attributes.put("chart_columnActionUrl2", nullToString(column.getAction()));
						} else {
							attributes.put("chart_columnActionUrl", nullToString(column.getAction()));
							attributes.put("chart_columnActionUrl2", "");
						}

						attributes.put("chart_columnActionParameter", nullToString(column.getActionParamters()));
						attributes.put("chart_columnColor", nullToString(column.getColor()));
						attributes.put("chart_columnActionCondition", nullToString(column.getActionCondition()));
						attributes.put("chart_columnUsageType", nullToString(column.getUsageType(), UsageType.DATA.name()));
						attributes.put("chart_columnShowValue", booleanToString(column.getShowValue(), "1", "0"));
						attributes.put("chart_columnRightY", booleanToString(column.getIsRightY(), "1", "0"));
						attributes.put("chart_columnCombi", nullToString(column.getCombiType()));
						columnJson.put("attributes", attributes);
						// 判断已定义的列是否存在表中
						if (dbColumnJsons.containsKey(column.getName().toLowerCase())) {
							dbColumnJsons.remove(column.getName().toLowerCase());
						} else {
							columnJson.put("iconCls", "icon-attention");
						}

						columnsJson.put(columnJson);
					}
				}
			}

			// 添加剩下的表中列名
			for (String key : dbColumnJsons.keySet()) {
				columnsJson.put(dbColumnJsons.get(key));
			}

		} catch (Exception e) {
			log.error("", e);
		}
		return columnsJson;

	}

	@Override
	public JSONArray getTableFiltersAsTreeJson(String tableId) throws Exception {
		ReportTable table = null;
		if (tableId != null && !"".equals(tableId)) {
			table = (ReportTable) this.reportDao.findEntityByID(ReportTable.class, tableId);
		}
		JSONArray treeJson = new JSONArray();

		// 预查询
		ReportFilter f = new ReportFilter();
		f.setId("SEARCH_0");
		f.setName(EnumUtil.getEnumMessage(FilterType.SEARCH, "预查询"));
		f.setFilterType(FilterType.SEARCH);
		f.setPosition(0);
		if (table != null) {
			if (!"".equals(nullToString(table.getSearchExpress()))) {
				if(WebUtil.isEnglish()){
					f.setName(f.getName() + "[group search]");
				}else{
					f.setName(f.getName() + "[组合查询]");
				}
			}
			f.setExpress(nullToString(table.getSearchExpress()));

		}
		JSONObject searchJson = getFilterAsTreeNode(f, "table", false);

		if (table != null) {
			Set<ReportFilter> search = table.getSearch();
			JSONArray childrenJson = new JSONArray();
			for (ReportFilter sf : search) {
				childrenJson.put(getFilterAsTreeNode(sf, "table", true));
			}
			searchJson.put("children", childrenJson);
		}
		treeJson.put(searchJson);
		// 过滤
		f = new ReportFilter();
		f.setId("FILTER_0");
		f.setName(EnumUtil.getEnumMessage(FilterType.FILTER, "过滤"));
		f.setFilterType(FilterType.FILTER);
		f.setPosition(1);
		if (table != null) {
			if (!"".equals(nullToString(table.getFilterExpress()))) {
				if(WebUtil.isEnglish()){
					f.setName(f.getName() + "[group search]");
				}else{
					f.setName(f.getName() + "[组合查询]");
				}
			}
			f.setExpress(nullToString(table.getFilterExpress()));
		}
		JSONObject filterJson = getFilterAsTreeNode(f, "table", false);

		if (table != null) {
			Set<ReportFilter> filter = table.getFilter();
			JSONArray childrenJson = new JSONArray();
			for (ReportFilter sf : filter) {
				childrenJson.put(getFilterAsTreeNode(sf, "table", true));
			}
			filterJson.put("children", childrenJson);
		}

		treeJson.put(filterJson);

		return treeJson;
	}

	private JSONObject getFilterAsTreeNode(ReportFilter filter, String prefix, boolean bracketLabel) throws JSONException {
		JSONObject nodeJson = new JSONObject();
		nodeJson.put("id", filter.getId());
		String label = nullToString(filter.getName());
		if (bracketLabel){
			if(WebUtil.isEnglish()){
				label += "[" + (nullToString(filter.getEnname()).equalsIgnoreCase("") ? nullToString(filter.getName()) : nullToString(filter.getEnname()).equalsIgnoreCase("")) + "]";
			}else{
				label += "[" + nullToString(filter.getLabel()) + "]";
			}
		}
		nodeJson.put("text", label);
		JSONObject attributesJson = new JSONObject();
		attributesJson.put(prefix + "_filterId", filter.getId());
		attributesJson.put(prefix + "_filterFilterType", nullToString(filter.getFilterType()));
		attributesJson.put(prefix + "_filterName", nullToString(filter.getName()));
		attributesJson.put(prefix + "_filterEnname", nullToString(filter.getEnname()));
		attributesJson.put(prefix + "_filterLabel", nullToString(filter.getLabel()));
		attributesJson.put(prefix + "_filterDbType", nullToString(filter.getDbType()));
		attributesJson.put(prefix + "_filterHtmlType", nullToString(filter.getHtmlType()));
		attributesJson.put(prefix + "_filterExpress", nullToString(filter.getExpress()));
		attributesJson.put(prefix + "_filterDefaultValue", nullToString(filter.getDefaultValue()));
		attributesJson.put(prefix + "_filterFilterDataRequestType", nullToString(filter.getFilterDataRequestType()));
		attributesJson.put(prefix + "_filterComboboxDataSource", nullToString(filter.getComboBoxDataSource()));
		attributesJson.put(prefix + "_filterComboboxNameField", nullToString(filter.getComboBoxNameField()));
		attributesJson.put(prefix + "_filterComboboxValueField", nullToString(filter.getComboBoxValueField()));
		attributesJson.put("position", nullToString(filter.getPosition(), "0"));
		nodeJson.put("attributes", attributesJson);
		return nodeJson;
	}
	
	@Override
	public JSONArray getTableControlsAsTreeJson(String tableId) throws Exception {
		ReportTable table = null;
		if (tableId != null && !"".equals(tableId)) {
			table = (ReportTable) this.reportDao.findEntityByID(ReportTable.class, tableId);
		}
		JSONArray treeJson = new JSONArray();

		// 预查询
		ReportControl f = new ReportControl();
		f.setId("CONTROL_0");
		if(WebUtil.isEnglish()){
			f.setName("Control");
		}else{
			f.setName("权限控制");
		}
		f.setPosition(0);
		JSONObject searchJson = getControlAsTreeNode(f, "table", false);
		if(null != table){
			Set<ReportControl> controls = table.getControls();
			log.info(""+controls.size());
			JSONArray childNodes = new JSONArray();
			for(ReportControl control : controls){
				childNodes.put(this.getControlAsTreeNode(control, "table", true));
			}
			searchJson.put("children", childNodes);
		}
		treeJson.put(searchJson);
		log.info(treeJson.toString());
		return treeJson;
	}
	
	private JSONObject getControlAsTreeNode(ReportControl control,String prefix, boolean bracketLabel)throws JSONException{
		JSONObject nodeJson = new JSONObject();
		nodeJson.put("id", control.getId());
		String label = nullToString(control.getName());
		if(bracketLabel){
			label += "["+nullToString(control.getLabel())+"]";
		}
		nodeJson.put("text", label);
		
		JSONObject attributesJson = new JSONObject();
		attributesJson.put(prefix + "_controlId", control.getId());
		attributesJson.put("position", nullToString(control.getPosition(), "0"));
		attributesJson.put(prefix + "_controlName", control.getName());
		attributesJson.put(prefix + "_controlLabel", control.getLabel());
		attributesJson.put(prefix + "_controlMatch", control.getMatch() == null ?  "" :  control.getMatch().getId());
		attributesJson.put(prefix + "_controlGroup", control.getGroupId()== null ? "" : control.getGroupId());
		nodeJson.put("attributes", attributesJson);
		return nodeJson;
	}
	
	@Override
	public String validateSQL(String datasourceId, String sql, String queryType, List<MutablePair<String, String>> params) throws Exception {

		ReportDataSource rds;

		rds = (ReportDataSource) this.reportDao.findEntityByID(ReportDataSource.class, datasourceId);
		QueryType qt = EnumUtils.getEnum(QueryType.class, queryType);

		QueryService queryService = this.queryFactory.getService(EnumUtils.getEnum(QueryType.class, queryType));
		queryService.initQuery("", rds, sql, qt, null, null);

		queryService.getTableMetadata(params);
		return "TRUE";

	}

	@Override
	@Transactional
	public void removeReport(String reportId) throws DataAccessException, Exception {
		Report report = this.reportDao.findEntityByID(Report.class, reportId);
		this.aclService.removeMenu(report.getMenuId());
		this.reportDao.updateByHSQL("delete from ReportLayout l where l.report.id=?", report.getId());
		report.setLayout(null);

		this.removeEntity(report);

	}

	@Override
	public String saveTable(Map<String, String> model) {
		JSONObject returnMsg = new JSONObject();
		try {
			String tableId = model.get("table_id");

			ReportTable table;
			if (tableId == null || "".equals(tableId)) {
				table = new ReportTable();
			} else {
				table = (ReportTable) this.findEntityByID(ReportTable.class, tableId);
			}
			table.setName(model.get("table_name"));
			table.setEnname(model.get("table_enname"));
			if (!"".equals(nullToString(model.get("table_datasource")))) {
				ReportDataSource rds = (ReportDataSource) this.findEntityByID(ReportDataSource.class, model.get("table_datasource"));
				if (rds != null)
					table.setReportDataSource(rds);
			}
			table.setSql(model.get("table_sql"));
			table.setShowRowNumber(toBoolean(model.get("table_showRowNumber"), "1"));
			table.setShowTotalTitle(toBoolean(model.get("table_showTotalTitle"), "1"));
			table.setIsScale(toBoolean(model.get("table_isScale"), "1"));
			table.setIsCache(toBoolean(( null ==  model.get("table_isCache") || "".equals(model.get("table_isCache")) ) ? "0" : model.get("table_isCache") , "1") );
			table.setIsExcel(toBoolean(( null ==  model.get("table_isExcel") || "".equals(model.get("table_isExcel")) ) ? "0" : model.get("table_isExcel") , "1") );
			table.setPageSize(toInteger(nullToString(model.get("table_pageSize"))));
			table.setSqlParamValue(nullToString(model.get("table_sqlParamValue")));
			table.setQueryType(Enum.valueOf(QueryType.class, nullToString(model.get("table_sqlType"), "SIMPLESQL")));

			// Save Columns
			Set<ReportColumn> columns = table.getReportColumns();
			if (columns == null) {
				columns = new HashSet<ReportColumn>();
			}
			Set<ReportColumn> newColumns = new HashSet<ReportColumn>();
			List<String> removeColumnsId = new ArrayList<String>();
			for (ReportColumn c : columns) {
				removeColumnsId.add(c.getId());
			}
			String json = nullToString(model.get("table_selectedColumns"));
			if (!"".equals(json)) {

				JSONArray ja = new JSONArray(json);

				for (int i = 0; i < ja.length(); i++) {

					JSONObject jo = ja.getJSONObject(i);
					ReportColumn column;
					String columnId = getValueFromJsonObject(jo, "table_columnId");
					if ("".equals(columnId)) {
						column = new ReportColumn();
					} else {
						column = (ReportColumn) this.findEntityByID(ReportColumn.class, columnId);
						removeColumnsId.remove(column.getId());
					}
					column.setName(getValueFromJsonObject(jo, "table_columnName"));
					column.setLabel(getValueFromJsonObject(jo, "table_columnLabel"));
					column.setEnLabel(getValueFromJsonObject(jo, "table_columnEnLabel"));
					column.setColumnDataType(Enum.valueOf(ColumnDataType.class,
							getValueFromJsonObject(jo, "table_columnType", ColumnDataType.STRING.name())));
					column.setPosition(Integer.parseInt(getValueFromJsonObject(jo, "position", "0", "0")));
					column.setIsGroupby(toBoolean(getValueFromJsonObject(jo, "table_columnGroupby"), "1"));
					column.setIsCountTotal(toBoolean(getValueFromJsonObject(jo, "table_columnTotal"), "1"));
					column.setIsCountSubTotal(toBoolean(getValueFromJsonObject(jo, "table_columnSubTotal"), "1"));
					column.setIsMerge(toBoolean(getValueFromJsonObject(jo, "table_columnMerge"), "1"));
					column.setIsVisible(toBoolean(getValueFromJsonObject(jo, "table_columnVisible"), "1"));
					column.setWidth(toInteger(getValueFromJsonObject(jo, "table_columnWidth", "100", "100")));
					column.setFormater(getValueFromJsonObject(jo, "table_columnFormater"));

					column.setColor(getValueFromJsonObject(jo, "table_columnColor"));
					column.setActionCondition(getValueFromJsonObject(jo, "table_columnActionCondition"));
					column.setActionType(EnumUtils.getEnum(ContentType.class, getValueFromJsonObject(jo, "table_columnActionType")));
					column.setActionParamters(getValueFromJsonObject(jo, "table_columnActionParameter"));
					column.setAlignType(EnumUtils.getEnum(AlignType.class, getValueFromJsonObject(jo, "table_columnAlign")));
					if (column.getActionType() == ContentType.PAGE) {
						column.setAction(getValueFromJsonObject(jo, "table_columnActionUrl2"));
					} else {
						column.setAction(getValueFromJsonObject(jo, "table_columnActionUrl"));
					}
					// column.setReportTable(table);
					log.debug("{}", column);
					newColumns.add(column);
				}
			}
			table.setReportColumns(newColumns);

			// add Filters
			Set<ReportFilter> filters = table.getFilter();
			Set<ReportFilter> searchs = table.getSearch();
			List<String> removeFiltersId = new ArrayList<String>();
			if (filters != null) {
				for (ReportFilter f : filters) {
					removeFiltersId.add(f.getId());
				}
			}
			if (searchs != null) {
				for (ReportFilter f : searchs) {
					removeFiltersId.add(f.getId());
				}
			}
			json = nullToString(model.get("table_selectedFilters"));
			if (!"".equals(json)) {
				JSONArray jsonArray = new JSONArray(json);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					ReportFilter filter;
					String filterId = getValueFromJsonObject(jsonObject, "table_filterId");
					if ("".equals(filterId)) {
						filter = new ReportFilter();
					} else {
						filter = (ReportFilter) this.findEntityByID(ReportFilter.class, filterId);
						removeFiltersId.remove(filter.getId());
					}
					Map<String, String> filterModel = this.copyJsonToMap(jsonObject, "table_filter", false, true);
					filter = (ReportFilter) this.copyAndOverrideExistedValueFromStringMap(filterModel, filter, null, false);
					if (filter.getPosition() == 0)
						filter.setPosition(i);
					// filter.setReportTable(table);
					this.saveEntity(filter);
					switch (filter.getFilterType()) {
					case FILTER:
						filters.add(filter);
						break;
					case SEARCH:
						searchs.add(filter);
						break;
					}

				}
			}
			
			//add Controls
			Set<ReportControl> controls = table.getControls();
			json = nullToString(model.get("table_selectedControls"));
			List<String> removeControlsId = new ArrayList<String>();
			if(null != controls && controls.size() > 0 ){
				for(ReportControl control : controls){
					removeControlsId.add(control.getId());
				}
			}			
			log.info(json);
			if(null != json && 0< (json.trim().length())){
				JSONArray jsonStrs = new JSONArray(json);
				for(int i = 0 ; i<jsonStrs.length() ; i++){
					JSONObject jsonStr = jsonStrs.getJSONObject(i);
					ReportControl control = new ReportControl();
					String controlId = getValueFromJsonObject(jsonStr, "table_controlId");
					if(null != controlId && 0 < controlId.trim().length()){
						control = (ReportControl) this.findEntityByID(ReportControl.class, controlId);
						controls.remove(control);
						removeControlsId.remove(controlId);
					}
					
					//TODO
					control = changeJSONMapToControl(jsonStr,control);
					log.info(control.getLabel());
					log.info(""+control.getPosition());
					if (control.getPosition() == 0){
						control.setPosition(i);
					}
					// filter.setReportTable(table);
					this.saveEntity(control);
					controls.add(control);
				}
			}
			
			
			table.setSearchExpress(nullToString(model.get("combine_express_search")));
			table.setFilterExpress(nullToString(model.get("combine_express_filter")));
			table.setFilter(filters);
			table.setSearch(searchs);
			if(null!=controls&&controls.size() > 0 ){
				table.setControls(controls);
			}
			this.saveOrUpdateEntity(table);
			columns = null;

			// remove old columns
			for (String id : removeColumnsId) {
				this.removeEntityById(ReportColumn.class, id);
			}
			//remove old control
			for(String id : removeControlsId){
				this.removeEntityById(ReportControl.class, id);
			}
			// remove old filters

			String layoutId = model.get("layoutId");
			if (null != layoutId && !"".equals(layoutId)) {
				ReportLayout layout = (ReportLayout) this.findEntityByID(ReportLayout.class, layoutId);
				layout.setDivHeight(toInteger(nullToString(model.get("layout_height"), "0")));
				layout.setDivWidth(toInteger(nullToString(model.get("layout_width"), "0")));
				this.saveEntity(layout);
			}
			//保存报表导出权限
			String controlPersons = model.get("table_controlPerson");
			if(null != controlPersons && 0 < controlPersons.length() ){
				this.aclService.removeAllEntites(this.aclService.findResultsByHSQL("from ReportPowerControl rc where rc.reportId = ?", table));
				List<ReportPowerControl> powers = new ArrayList<ReportPowerControl>();
				String[] usersIds = controlPersons.split(",");
				for(String id : usersIds){
					User user =new User();
					user.setId(id);
					ReportPowerControl power = new ReportPowerControl();
					power.setReportId(table);
					power.setPowerPerson(user);
					power.setCreator(SecurityUtil.getPrincipal());
					power.setCreateDate(DateUtil.getSystemDateTime());
					powers.add(power);
				}
				this.saveAllEntities(powers);
			}
			returnMsg.put("message", "TRUE");
			returnMsg.put("result", table.getId());
		} catch (Exception e) {
			log.error("", e);

			try {
				returnMsg.put("message", "ERROR");
				returnMsg.put("result", e.getMessage());
			} catch (JSONException e1) {
				log.error("", e1);
			}
		}
		return returnMsg.toString();
	}

	private ReportControl changeJSONMapToControl(JSONObject json,ReportControl control) throws Exception{
		control.setId(json.getString("table_controlId"));
		control.setName(json.getString("table_controlName"));
		control.setLabel(json.getString("table_controlLabel"));
		String matchId = json.getString("table_controlMatch");
		String groupId = json.getString("table_controlGroup");
		if(null != matchId  && 0< matchId.length()){
			ReportControlMatch match = new ReportControlMatch();
			match.setId(matchId);
			control.setMatch(match);
		}
		if(null != groupId  && 0< groupId.length()){
			Group group = new Group();
			group.setId(groupId);
			control.setGroupId(groupId);
		}
		return control;	
	}
	
	@Override
	public String saveChart(Map<String, String> model) {
		JSONObject returnMsg = new JSONObject();
		try {
			String chartId = nullToString(model.get("chart_id"));
			ReportChart chart = null;
			if (chartId != "") {
				chart = this.reportDao.findEntityByID(ReportChart.class, chartId);
			} else {
				chart = new ReportChart();
			}
			chart.setName(model.get("chart_name"));
			chart.setCaption(model.get("chart_caption"));
			chart.setSubCaption(model.get("chart_subcaption"));
			chart.setChartType(Enum.valueOf(ChartType.class, nullToString(model.get("chart_chartType"), ChartType.Column.name())));
			chart.setFusionChart(Enum.valueOf(FusionChart.class, nullToString(model.get("chart_chartType2"), FusionChart.Column2D.name())));
			chart.setQuery(model.get("chart_sql"));
			chart.setQueryParamValue(model.get("chart_sqlParamValue"));
			chart.setQueryType(Enum.valueOf(QueryType.class, nullToString(model.get("chart_sqlType"), QueryType.SIMPLESQL.name())));
			chart.setxAxisName(nullToString(model.get("chart_xAxisName")));
			chart.setyAxisName(nullToString(model.get("chart_yAxisName")));
			chart.setsAxisName(nullToString(model.get("chart_sAxisName")));
			String datasource_id = nullToString(model.get("chart_datasource"));
			if (!"".equals(datasource_id)) {
				try {
					ReportDataSource rds = this.reportDao.findEntityByID(ReportDataSource.class, datasource_id);
					chart.setDataSource(rds);
				} catch (Exception e) {
					chart.setDataSource(null);
				}
			}

			// Save Columns
			Set<ReportColumn> columns = chart.getReportColumns();
			if (columns == null) {
				columns = new HashSet<ReportColumn>();
			}
			Set<ReportColumn> newColumns = new HashSet<ReportColumn>();
			List<String> removeColumnsId = new ArrayList<String>();
			for (ReportColumn c : columns) {
				removeColumnsId.add(c.getId());
			}
			String json = nullToString(model.get("chart_selectedColumns"));
			if (!"".equals(json)) {

				JSONArray ja = new JSONArray(json);

				for (int i = 0; i < ja.length(); i++) {

					JSONObject jo = ja.getJSONObject(i);
					ReportColumn column;
					String columnId = getValueFromJsonObject(jo, "chart_columnId");
					if ("".equals(columnId)) {
						column = new ReportColumn();
					} else {
						column = (ReportColumn) this.findEntityByID(ReportColumn.class, columnId);
						removeColumnsId.remove(column.getId());
					}
					column.setName(getValueFromJsonObject(jo, "chart_columnName"));
					column.setLabel(getValueFromJsonObject(jo, "chart_columnLabel"));
					column.setColumnDataType(Enum.valueOf(ColumnDataType.class,
							getValueFromJsonObject(jo, "chart_columnType", ColumnDataType.STRING.name())));
					column.setPosition(Integer.parseInt(getValueFromJsonObject(jo, "position", "0", "0")));
					column.setUsageType(Enum.valueOf(UsageType.class,
							getValueFromJsonObject(jo, "chart_columnUsageType", UsageType.DATA.name())));
					column.setFormater(getValueFromJsonObject(jo, "chart_columnFormater"));

					column.setColor(getValueFromJsonObject(jo, "chart_columnColor"));
					column.setActionCondition(getValueFromJsonObject(jo, "chart_columnActionCondition"));
					column.setActionType(EnumUtils.getEnum(ContentType.class, getValueFromJsonObject(jo, "chart_columnActionType")));
					column.setActionParamters(getValueFromJsonObject(jo, "chart_columnActionParameter"));
					column.setShowValue(toBoolean(getValueFromJsonObject(jo, "chart_columnShowValue"), "1"));
					if (chart.getChartType() != null && chart.getChartType() == ChartType.Combi) {
						column.setCombiType(EnumUtils.getEnum(ChartType.class,
								getValueFromJsonObject(jo, "chart_columnCombi", ChartType.Column.name())));
					} else {
						column.setCombiType(null);
					}
					if (column.getActionType() == ContentType.PAGE) {
						column.setAction(getValueFromJsonObject(jo, "chart_columnActionUrl2"));
					} else {
						column.setAction(getValueFromJsonObject(jo, "chart_columnActionUrl"));
					}
					column.setIsRightY(toBoolean(getValueFromJsonObject(jo, "chart_columnRightY"), "1"));

					newColumns.add(column);
				}
			}
			chart.setReportColumns(newColumns);
			// save Filters
			Set<ReportFilter> filters = chart.getFilter();
			Set<ReportFilter> searchs = chart.getSearch();
			List<String> removeFiltersId = new ArrayList<String>();
			if (filters != null) {
				for (ReportFilter f : filters) {
					removeFiltersId.add(f.getId());
				}
			}
			if (searchs != null) {
				for (ReportFilter f : searchs) {
					removeFiltersId.add(f.getId());
				}
			}
			json = nullToString(model.get("chart_selectedFilters"));
			if (!"".equals(json)) {
				JSONArray jsonArray = new JSONArray(json);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					ReportFilter filter;
					String filterId = getValueFromJsonObject(jsonObject, "chart_filterId");
					if ("".equals(filterId)) {
						filter = new ReportFilter();
					} else {
						filter = (ReportFilter) this.findEntityByID(ReportFilter.class, filterId);
						removeFiltersId.remove(filter.getId());
					}

					Map<String, String> filterModel = this.copyJsonToMap(jsonObject, "chart_filter", false, true);

					filter = (ReportFilter) this.copyAndOverrideExistedValueFromStringMap(filterModel, filter, null, false);
					if (filter.getPosition() == 0)
						filter.setPosition(i);
					// filter.setReportTable(table);
					this.saveEntity(filter);
					switch (filter.getFilterType()) {
					case FILTER:
						filters.add(filter);
						break;
					case SEARCH:
						searchs.add(filter);
						break;
					}

				}
			}
			chart.setSearchExpress(nullToString(model.get("combine_express_search")));
			chart.setFilterExpress(nullToString(model.get("combine_express_filter")));
			chart.setFilter(filters);
			chart.setSearch(searchs);
			this.reportDao.saveEntity(chart);
			returnMsg.putOpt("message", "TRUE");
			returnMsg.putOpt("result", chart.getId());
		} catch (Exception e) {
			log.error("", e);

			try {
				returnMsg.putOpt("message", "ERROR");
				returnMsg.putOpt("result", e.getMessage());
			} catch (JSONException e1) {
				log.error("", e1);
			}
		}
		return returnMsg.toString();
	}

	private String getValueFromJsonObject(JSONObject o, String key, String... defaultValue) {
		String result;
		try {
			result = o.getString(key);
		} catch (Exception e) {
			result = null;
		}
		int c = defaultValue.length;
		if (result == null) {
			if (c > 0)
				return defaultValue[0];
			else
				result = "";
		}

		if ("".equals(result) && c > 1)
			return defaultValue[1];

		return result;
	}

	private Map<String, String> copyJsonToMap(JSONObject jsonObj, String additionKey, boolean isAdd, boolean isPrefix) throws JSONException {
		String key_add = "";
		if (additionKey != null && !"".equals(additionKey)) {
			key_add = additionKey;
		}
		Iterator<?> keys = jsonObj.keys();
		Map<String, String> toMap = new HashMap<String, String>();
		while (keys.hasNext()) {
			Object key = keys.next();
			String mapKey = key.toString();
			if (!"".equals(key_add))
				if (isAdd) {
					if (isPrefix)
						mapKey = key_add + mapKey;
					else
						mapKey = mapKey + key_add;
				} else {
					if (mapKey.length() > key_add.length()) {
						if (isPrefix && mapKey.toLowerCase().startsWith(key_add.toLowerCase())) {
							mapKey = mapKey.substring(key_add.length());
						} else if (mapKey.toLowerCase().endsWith(key_add.toLowerCase())) {
							mapKey = mapKey.substring(0, mapKey.length() - key_add.length());
						}
					}
				}
			toMap.put(mapKey, jsonObj.getString(key.toString()));
		}
		return toMap;

	}

	private Integer toInteger(String value) {
		if (value == null || "".equals(value))
			return null;
		Integer i = null;
		try {
			i = Integer.parseInt(value);
		} catch (Exception e) {
			log.warn("", e);
			i = null;
		}
		return i;
	}

	private Boolean toBoolean(String value, String trueValue) {
		if (value == null || "".equals(value))
			return null;
		if (value.equalsIgnoreCase(trueValue))
			return true;
		else
			return false;
	}

	@Override
	public TenwaReport exportAll(String startId) throws Exception {
		TenwaReport tp = new TenwaReport();
		List<ReportDataSource> ds = this.reportDao.findEntities(ReportDataSource.class);
		tp.addDataSource(ds);
		if ("0".equals(startId)) {
			List<ReportLayout> layouts = this.reportDao.findEntities(ReportLayout.class);
			tp.addLayout(layouts);

			List<Report> reports = this.reportDao.findEntities(Report.class);
			tp.addReport(reports);

			List<ReportTable> tables = this.reportDao.findEntities(ReportTable.class);
			tp.addTable(tables);

			List<ReportChart> charts = this.reportDao.findEntities(ReportChart.class);
			tp.addChart(charts);

			List<ReportPage> pages = this.reportDao.findEntities(ReportPage.class);
			tp.addPage(pages);
		} else {
			Report report = this.reportDao.findEntityByID(Report.class, startId);
			List<Report> reports = new ArrayList<Report>();
			List<ReportTable> tables = new ArrayList<ReportTable>();
			List<ReportChart> charts = new ArrayList<ReportChart>();
			List<ReportPage> pages = new ArrayList<ReportPage>();
			List<ReportLayout> layouts = new ArrayList<ReportLayout>();
			List<String> ids = new ArrayList<String>();
			report.setParentReport(this.reportDao.findEntityByID(Report.class, "0"));
			getAllSubReport(report, reports, tables, charts, pages, layouts, ids);
			tp.addReport(reports);
			tp.addLayout(layouts);
			tp.addTable(tables);
			tp.addChart(charts);
			tp.addPage(pages);

		}
		return tp;

	}

	private void getAllSubReport(Report report, List<Report> reports, List<ReportTable> tables, List<ReportChart> charts,
			List<ReportPage> pages, List<ReportLayout> layouts, List<String> ids) {
		if (report != null && !ids.contains(report.getId())) {
			ids.add(report.getId());
			reports.add(report);
		}
		if (report.getReportType() == ReportType.FOLDER) {
			Set<Report> childrenReports = report.getChildrenReport();
			if (childrenReports != null && childrenReports.size() > 0) {
				for (Report childReport : childrenReports) {
					getAllSubReport(childReport, reports, tables, charts, pages, layouts, ids);
				}
			}

		} else if (report.getReportType() == ReportType.REPORT) {
			Set<ReportLayout> curLayouts = report.getLayout();
			for (ReportLayout layout : curLayouts) {
				if (!ids.contains(layout.getContentId())) {
					ids.add(layout.getContentId());
					switch (layout.getContentType()) {
					case TABLE:
						try {
							tables.add(this.reportDao.findEntityByID(ReportTable.class,
									layout.getContentId().substring(ContentType.TABLE.name().length() + 1)));
						} catch (Exception e) {
							log.error("", e);
						}
						break;
					case CHART:
						try {
							charts.add(this.reportDao.findEntityByID(ReportChart.class,
									layout.getContentId().substring(ContentType.CHART.name().length() + 1)));
						} catch (Exception e) {
							log.error("", e);
						}
						break;
					case PAGE:
						try {
							pages.add(this.reportDao.findEntityByID(ReportPage.class,
									layout.getContentId().substring(ContentType.PAGE.name().length() + 1)));
						} catch (Exception e) {
							log.error("", e);
						}
						break;
					}
				}
				if (!ids.contains(layout.getId())) {
					ids.add(layout.getId());
					layouts.add(layout);
				}
			}
		}
	}

	@Override
	public void uploadReport(TenwaReport tp, String rootMenuId, String parentId, String timeStamp) throws Exception {
		log.info("Start Report Module Import!");
		Queue<String> message = new LinkedBlockingQueue<String>();
		uploadMessage.put(timeStamp, message);
		log.debug("Have timestamp {} ?{}", timeStamp, uploadMessage.containsKey(timeStamp));

		if (tp == null) {
			message.offer("用于导入的文件为空或格式错误");
			log.info("Import File is EMPTY OR FORMAT NOT CORRECT");
			message.offer("导入完成");
			message.offer("TRUE");
			return;
		}
		Report parentReport = null;
		if (!"".equals(nullToString(parentId))) {
			if (!"0".equals(parentId)) {
				parentReport = this.reportDao.findEntityByID(Report.class, parentId);
			}
		}

		if (parentReport == null) {
			parentReport = getRootReport(rootMenuId);
		}

		if (parentReport == null || parentReport.getReportType() != ReportType.FOLDER) {
			message.add("选择的目标节点不是文件夹类型，导入失败！");
			message.offer("TRUE");
			return;
		}

		Map<String, ReportDataSource> importedDatasource = importDatasource(tp.getDatasources(), message);
		log.info("{} datasources imported", importedDatasource.size());

		Map<String, ReportTable> importedTables = importReportTable(tp.getTables(), importedDatasource, message);
		log.info("{} tables imported", importedTables.size());

		Map<String, ReportChart> importedCharts = importReportChart(tp.getCharts(), importedDatasource, message);
		log.info("{} charts imported", importedCharts.size());

		Map<String, ReportPage> importedPages = importReportPage(tp.getPages(), importedDatasource, message);
		log.info("{} pages imported", importedPages.size());

		List<Report> flReports = new ArrayList<Report>(); // find first level report
		for (Report report : tp.getReports()) {
			if (report.getParentReport() == null || "0".equals(report.getParentReport().getId())) {
				flReports.add(report);
			}
		}
		importReport(flReports, importedTables, importedCharts, importedPages, parentReport, message);

		log.info("Report Module Import Finish!");
		message.offer("导入完成");
		message.offer("TRUE");

	}

	private void importReport(List<Report> reports, Map<String, ReportTable> importedTables, Map<String, ReportChart> importedCharts,
			Map<String, ReportPage> importedPages, Report desParentReport, Queue<String> message) {
		if (reports == null) {
			return;
		}

		Set<Report> flReportsInDb = desParentReport.getChildrenReport();

		for (Report srcReport : reports) {
			try {
				boolean reportFound = false;
				Report newReport = null;
				for (Report desReport : flReportsInDb) {
					if (srcReport.getName().equalsIgnoreCase(desReport.getName()) && srcReport.getReportType() == desReport.getReportType()) {
						newReport = desReport;
						reportFound = true;
						break;
					}
				}
				if (newReport == null) {
					newReport = new Report();
				}
				BeanUtils.copyProperties(srcReport, newReport, new String[] { "id", "menuId", "parentReport", "childrenReport", "layout" });
				newReport.setParentReport(desParentReport);
				if (newReport.getCode() != null) {
					newReport.setCode(null);
				}
				this.reportDao.saveOrUpdateEntity(newReport);
				this.saveToMenu(newReport, desParentReport.getMenuId());
				if (newReport.getReportType() == ReportType.FOLDER) {
					List<Report> srcChildrenReports = new ArrayList<Report>(srcReport.getChildrenReport());
					importReport(srcChildrenReports, importedTables, importedCharts, importedPages, newReport, message);
				} else if (newReport.getReportType() == ReportType.REPORT) {
					if (newReport.getLayout() != null) {
						if (newReport.getLayout() != null) {
							this.reportDao.removeAllEntites(newReport.getLayout());
						}
						newReport.setLayout(null);
						this.reportDao.saveOrUpdateEntity(newReport);
					}
					for (ReportLayout srcLayout : srcReport.getLayout()) {
						ContentType contentType = srcLayout.getContentType();
						String contentId = srcLayout.getContentId().substring(contentType.name().length() + 1);
						Set<ReportLayout> newLayouts = new HashSet<ReportLayout>();
						String newContentId = "";
						switch (contentType) {
						case TABLE:
							if (importedTables.containsKey(contentId)) {
								newContentId = contentType.name() + "#" + importedTables.get(contentId).getId();
							}
							break;
						case CHART:
							if (importedCharts.containsKey(contentId)) {
								newContentId = contentType.name() + "#" + importedCharts.get(contentId).getId();
							}
							break;
						case PAGE:
							if (importedPages.containsKey(contentId)) {
								newContentId = contentType.name() + "#" + importedPages.get(contentId).getId();
							}
						}
						if (!"".equals(newContentId)) {
							ReportLayout newLayout = new ReportLayout();
							newLayout.setContentId(newContentId);
							newLayout.setContentType(contentType);
							newLayout.setReport(newReport);
							newLayout.setDivHeight(srcLayout.getDivHeight());
							newLayout.setDivWidth(srcLayout.getDivWidth());
							newLayouts.add(newLayout);
						}
						newReport.setLayout(newLayouts);
						this.reportDao.saveOrUpdateEntity(newReport);
					}
				}
				log.info("报表[" + newReport.getName() + "]" + (reportFound ? "更新" : "导入") + "成功");
				message.offer("报表[" + newReport.getName() + "]" + (reportFound ? "更新" : "导入") + "成功");
			} catch (Exception e) {
				log.error("导入报表[" + srcReport.getName() + "]失败", e);
				message.add("导入报表[" + srcReport.getName() + "]失败");
			}
		}

	}

	private Report getRootReport(String rootMenuId) {
		Report rootReport = null;
		try {
			rootReport = this.reportDao.findEntityByID(Report.class, "0");
			if (rootReport == null) {
				this.getBussinessDao()
						.getJdbcTemplate()
						.execute(
								"INSERT INTO REPORT_REPORT(ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, REPORTTYPE) "
										+ "VALUES ('0', '报表', 'RPROOT', '报表', '0', 'FOLDER')");
				rootReport = this.reportDao.findEntityByID(Report.class, "0");
				this.saveToMenu(rootReport, rootMenuId);
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return rootReport;
	}

	private Map<String, ReportPage> importReportPage(List<ReportPage> pages, Map<String, ReportDataSource> importedDatasource,
			Queue<String> message) {
		Map<String, ReportPage> importedPages = new HashMap<String, ReportPage>();
		if (pages == null || pages.isEmpty()) {
			return importedPages;
		}
		List<ReportPage> pagesInDb = new ArrayList<ReportPage>();
		try {
			pagesInDb = this.reportDao.findEntities(ReportPage.class);
		} catch (Exception e) {
			log.error("", e);
			message.offer("获取当前数据库中的页面出现错误:" + e.getMessage());
		}
		for (ReportPage srcPage : pages) {
			boolean found = false;
			ReportPage foundDesPage = null;
			for (ReportPage desPage : pagesInDb) {
				if (srcPage.getName().equalsIgnoreCase(desPage.getName())) {
					found = true;
					foundDesPage = desPage;
					break;
				}
			}
			if (!found) {
				foundDesPage = new ReportPage();
			}
			if (foundDesPage != null) {
				BeanUtils.copyProperties(srcPage, foundDesPage, new String[] { "id" });
				try {
					this.reportDao.saveEntity(foundDesPage);
					importedPages.put(srcPage.getId(), foundDesPage);

					message.offer("页面[" + foundDesPage.getName() + "]" + (found ? "更新" : "导入") + "成功");

					log.info("Page [{}] imported", foundDesPage.getName());
				} catch (Exception e) {
					log.error("", e);
					message.offer("页面[" + foundDesPage.getName() + "]导入失败:" + e.getMessage());
				}
			}
		}

		return importedPages;
	}

	private Map<String, ReportChart> importReportChart(List<ReportChart> charts, Map<String, ReportDataSource> importedDatasource,
			Queue<String> message) throws Exception {
		Map<String, ReportChart> importedCharts = new HashMap<String, ReportChart>();
		if (charts == null || charts.isEmpty()) {
			return importedCharts;
		}

		List<ReportChart> chartsInDb = new ArrayList<ReportChart>();
		try {
			chartsInDb = this.reportDao.findEntities(ReportChart.class);
		} catch (Exception e) {
			message.offer("无法获取当前数据库中的图表:" + e.getMessage());
			log.error("", e);
		}
		for (ReportChart srcChart : charts) {
			boolean found = false;
			ReportChart foundDesChart = null;
			for (ReportChart desChart : chartsInDb) {
				if (srcChart.getName().equalsIgnoreCase(desChart.getName())) {
					found = true;
					foundDesChart = desChart;
					break;
				}
			}

			if (!found) {
				foundDesChart = new ReportChart();
			}

			if (foundDesChart != null) {
				try {
					if (foundDesChart.getFilter() != null) {
						this.reportDao.removeAllEntites(foundDesChart.getFilter());
					}
					if (foundDesChart.getSearch() != null) {
						this.reportDao.removeAllEntites(foundDesChart.getSearch());
					}
					if (foundDesChart.getReportColumns() != null) {
						this.reportDao.removeAllEntites(foundDesChart.getReportColumns());
					}
					BeanUtils.copyProperties(srcChart, foundDesChart, new String[] { "id", "filter", "search", "dataSource" });

					foundDesChart.setReportColumns(null);
					foundDesChart.setFilter(null);
					foundDesChart.setSearch(null);
					foundDesChart.setDataSource(null);

					this.reportDao.saveOrUpdateEntity(foundDesChart);
					this.reportDao.updateFlush();

					if (srcChart.getReportColumns() != null) {
						for (ReportColumn column : srcChart.getReportColumns()) {
							column.setId(null);
						}
						foundDesChart.setReportColumns(srcChart.getReportColumns());
					}

					if (srcChart.getFilter() != null) {
						for (ReportFilter filter : srcChart.getFilter()) {
							filter.setId(null);
						}
						foundDesChart.setFilter(srcChart.getFilter());
					}
					if (srcChart.getSearch() != null) {
						for (ReportFilter filter : srcChart.getSearch()) {
							filter.setId(null);
						}
						foundDesChart.setSearch(srcChart.getSearch());
					}
					if (srcChart.getDataSource() != null) {
						String oldDsId = srcChart.getDataSource().getId();
						if (importedDatasource.containsKey(oldDsId)) {
							foundDesChart.setDataSource(importedDatasource.get(oldDsId));
						}
					}
					this.reportDao.saveOrUpdateEntity(foundDesChart);

					importedCharts.put(srcChart.getId(), foundDesChart);
					message.offer("图表[" + foundDesChart.getName() + "]" + (found ? "更新" : "导入") + "成功");
					log.info("Chart [{}] imported", foundDesChart.getName());
				} catch (Exception e) {
					log.error("", e);
					message.offer("图表[" + foundDesChart.getName() + "]导入失败:" + e.getMessage());
				}
			}
		}
		return importedCharts;
	}

	private Map<String, ReportTable> importReportTable(List<ReportTable> tables, Map<String, ReportDataSource> importedDatasource,
			Queue<String> message) {
		Map<String, ReportTable> importedTables = new HashMap<String, ReportTable>();
		if (tables == null || tables.isEmpty()) {
			return importedTables;
		}

		List<ReportTable> tablesInDb = new ArrayList<ReportTable>();
		try {
			tablesInDb = this.reportDao.findEntities(ReportTable.class);
		} catch (Exception e) {
			log.error("", e);
			message.offer("获取所有表格失败:" + e.getMessage());
		}
		if (tablesInDb == null) {
			tablesInDb = new ArrayList<ReportTable>();
		}
		for (ReportTable srcTable : tables) {
			boolean found = false;
			ReportTable foundDesTable = null;
			for (ReportTable desTable : tablesInDb) {
				if (srcTable.getName().equalsIgnoreCase(desTable.getName())) {
					foundDesTable = desTable;
					importedTables.put(srcTable.getId(), desTable);
					found = true;
					break;
				}
			}
			if (!found) {
				foundDesTable = new ReportTable();
			}

			if (foundDesTable != null) {

				try {
					if (foundDesTable.getFilter() != null) {
						this.reportDao.removeAllEntites(foundDesTable.getFilter());
					}
					if (foundDesTable.getSearch() != null) {
						this.reportDao.removeAllEntites(foundDesTable.getSearch());
					}
					if (foundDesTable.getReportColumns() != null) {
						this.reportDao.removeAllEntites(foundDesTable.getReportColumns());
					}
					BeanUtils.copyProperties(srcTable, foundDesTable, new String[] { "id", "filter", "search", "reportColumns",
							"reportDataSource" });
					foundDesTable.setFilter(null);
					foundDesTable.setSearch(null);
					foundDesTable.setReportColumns(null);
					foundDesTable.setReportDataSource(null);
					this.reportDao.saveOrUpdateEntity(foundDesTable);

					this.reportDao.getHibernateTemplate().flush();
					if (srcTable.getReportColumns() != null) {
						for (ReportColumn column : srcTable.getReportColumns()) {
							column.setId(null);
						}
						foundDesTable.setReportColumns(srcTable.getReportColumns());
					}
					if (srcTable.getFilter() != null) {
						for (ReportFilter filter : srcTable.getFilter()) {
							filter.setId(null);
						}
						foundDesTable.setFilter(srcTable.getFilter());
					}
					if (srcTable.getSearch() != null) {
						for (ReportFilter filter : srcTable.getSearch()) {
							filter.setId(null);
						}
						foundDesTable.setSearch(srcTable.getSearch());
					}
					if (srcTable.getReportDataSource() != null) {
						String oldDsId = srcTable.getReportDataSource().getId();
						if (importedDatasource.containsKey(oldDsId)) {
							foundDesTable.setReportDataSource(importedDatasource.get(oldDsId));
						}
					}
					this.reportDao.saveOrUpdateEntity(foundDesTable);
					importedTables.put(srcTable.getId(), foundDesTable);
					log.info("Table [{}] imported", foundDesTable.getName());
					message.offer("表格[" + foundDesTable.getName() + "]" + (found ? "更新" : "导入") + "成功");
				} catch (Exception e) {
					log.error("", e);
					message.offer("表格[" + foundDesTable.getName() + "]导入失败:" + e.getMessage());
				}
			}
		}

		return importedTables;
	}

	private Map<String, ReportDataSource> importDatasource(List<ReportDataSource> datasources, Queue<String> message) {
		Map<String, ReportDataSource> dataSource = new HashMap<String, ReportDataSource>();
		if (datasources == null || datasources.isEmpty()) {
			return dataSource;
		}

		List<ReportDataSource> dsInDb = new ArrayList<ReportDataSource>();
		try {
			dsInDb = this.reportDao.findEntities(ReportDataSource.class);
		} catch (Exception e) {
			log.error("", e);
			message.offer("获取已存在的数据源失败");
		}
		for (ReportDataSource srcDS : datasources) {
			boolean found = false;
			for (ReportDataSource desDS : dsInDb) {
				if (srcDS.getDataSourceName().equalsIgnoreCase(desDS.getDataSourceName())) {
					found = true;
					dataSource.put(srcDS.getId(), desDS);
					message.offer("同名数据源[" + desDS.getDataSourceName() + "]已存在,将会更新相关设置以使用现有数据源");
					break;
				}
			}
			if (!found) {
				String oldId = srcDS.getId();
				srcDS.setId(null);
				try {
					this.reportDao.saveEntity(srcDS);
				} catch (Exception e) {
					message.offer("保存数据源[" + srcDS.getDataSourceName() + "]失败");
					log.error("", e);
				}
				dataSource.put(oldId, srcDS);
				log.info("Datasource [{}] imported", srcDS.getDataSourceName());
				message.offer("数据源[" + srcDS.getDataSourceName() + "]导入成功");
			}
		}
		return dataSource;
	}

	@Override
	public void updateLayout(String reportId, String layoutId, String contentId, String contentType, String action) throws Exception {
		if ("remove".equalsIgnoreCase(action)) {
			Report report = this.reportDao.findEntityByID(Report.class, reportId);
			ReportLayout layout = this.reportDao.findEntityByID(ReportLayout.class, layoutId);
			report.getLayout().remove(layout);
			this.reportDao.removeEntity(layout);
		}
		if ("add".equalsIgnoreCase(action)) {
			ReportLayout layout = new ReportLayout();
			ContentType ct = Enum.valueOf(ContentType.class, contentType);
			layout.setContentType(ct);
			layout.setDivHeight(null);
			layout.setDivWidth(null);
			layout.setReport((Report) this.reportDao.findEntityByID(Report.class, reportId));
			layout.setContentId(ct.name() + "#" + contentId);

			this.reportDao.saveEntity(layout);

		}
	}

	@Override
	public Report saveReport(Report report, String parentMenuId) throws Exception {
		this.reportDao.saveEntity(report);
		try {
			report.setActived(true);
			saveToMenu(report, parentMenuId);
		} catch (Exception e) {
			log.error("", e);
		}

		return report;
	}

	private void saveToMenu(Report report, String parentReportMenuId) throws Exception {
		if (null == report)
			return;

		if ("".equals(nullToString(report.getId())) || "".equals(nullToString(parentReportMenuId))
				|| "".equals(nullToString(report.getName())))
			return;
		Menu menu;
		if ("".equals(nullToString(report.getMenuId()))) {
			if (!report.isActived())
				return;
			menu = new Menu();
		} else {
			// FIXME: 是否可见
			if (!report.isActived()) {
				this.reportDao.removeEntityById(Menu.class, report.getMenuId());
				report.setMenuId("");
				this.reportDao.saveOrUpdateEntity(report);
				PermissionUtil.cachedAllUserTreeMenus(true);
				return;
			} else {
				menu = (Menu) this.reportDao.findEntityByID(Menu.class, report.getMenuId());
			}
		}
		if (menu == null) {
			menu = new Menu();
		}
		menu.setName(report.getName());
		menu.setPosition(report.getPosition());
		menu.setCode(("".equals(nullToString(report.getCode()))) ? report.getId() : report.getCode());
		Menu parentMenu;
		if (report.getParentReport() == null || "".equals(nullToString(report.getParentReport().getMenuId()))) {
			parentMenu = (Menu) this.reportDao.findEntityByID(Menu.class, parentReportMenuId);
		} else {
			parentMenu = (Menu) this.reportDao.findEntityByID(Menu.class, report.getParentReport().getMenuId());
		}
		menu.setParentMenu(parentMenu);
		menu.setIsRelativedPath(true);
		menu.setUrl("/report/showReport.action?reportId=" + report.getId());
		switch (report.getReportType()) {
		case FOLDER:
			menu.setIcon("home.png");
			break;
		case REPORT:
			menu.setIcon("chart_bar.png");
			break;
		default:
			menu.setIcon("home.png");
			break;
		}

		this.reportDao.saveOrUpdateEntity(menu);
		report.setMenuId(menu.getId());
		this.reportDao.saveOrUpdateEntity(report);
		// PermissionUtil.cachedAllUserTreeMenus(true);
	}

	@Override
	public void updatePosition(String entityClass, String id, String pid, String parentField, Integer position, String rootMenuId)
			throws Exception {
		String hql = "update " + entityClass + " t set t.position = t.position + 1 where t.position >= ? and t." + parentField + ".id=?";
		this.reportDao.updateByHSQL(hql, position, pid);
		hql = "update " + entityClass + " t set t.position=?, t." + parentField + ".id=? where t.id=?";
		this.reportDao.updateByHSQL(hql, position, pid, id);

		// 更新Report
		if (entityClass.equalsIgnoreCase(Report.class.getSimpleName())) {
			Report report = this.reportDao.findEntityByID(Report.class, id);
			Menu menu = null;
			if (null == report.getMenuId() || "".equals(report.getMenuId())) {
				saveToMenu(report, rootMenuId);
			}
			menu = this.aclService.findEntityByID(Menu.class, report.getMenuId());
			Menu parentMenu = null;
			if (pid == null || "0".equals(pid)) {
				parentMenu = this.aclService.findEntityByID(Menu.class, rootMenuId);
			} else {
				parentMenu = this.aclService.findEntityByID(Menu.class, report.getParentReport().getMenuId());
			}
			menu.setParentMenu(parentMenu);
			this.aclService.saveOrUpdateMenuWithPosition(menu, parentMenu.getId(), String.valueOf(position));
		}
	}

	public Queue<String> getUploadMessage(String timeStamp) {
		if (uploadMessage.containsKey(timeStamp)) {
			return uploadMessage.get(timeStamp);
		} else {
			log.debug("No timestamp {}", timeStamp);
		}
		return null;
	}

	public void removeUploadMessage(String timeStamp) {
		if (uploadMessage.containsKey(timeStamp)) {
			uploadMessage.remove(timeStamp);
		}
	}

	@Override
	public JSONArray getChartFiltersAsTreeJson(String chartId) throws Exception {

		ReportChart chart = null;
		if (chartId != null && !"".equals(chartId)) {
			chart = this.reportDao.findEntityByID(ReportChart.class, chartId);
		}
		JSONArray treeJson = new JSONArray();

		// 预查询
		ReportFilter f = new ReportFilter();
		f.setId("SEARCH_0");
		f.setName(EnumUtil.getEnumMessage(FilterType.SEARCH, "预查询"));
		f.setFilterType(FilterType.SEARCH);
		f.setPosition(0);
		if (chart != null) {
			if (!"".equals(nullToString(chart.getSearchExpress()))) {
				f.setName(f.getName() + "[组合查询]");
			}
			f.setExpress(nullToString(chart.getSearchExpress()));

		}
		JSONObject searchJson = getFilterAsTreeNode(f, "chart", false);

		if (chart != null) {
			Set<ReportFilter> search = chart.getSearch();
			JSONArray childrenJson = new JSONArray();
			for (ReportFilter sf : search) {
				childrenJson.put(getFilterAsTreeNode(sf, "chart", true));
			}
			searchJson.put("children", childrenJson);
		}
		treeJson.put(searchJson);
		// 过滤
		f = new ReportFilter();
		f.setId("FILTER_0");
		f.setName(EnumUtil.getEnumMessage(FilterType.FILTER, "过滤"));
		f.setFilterType(FilterType.FILTER);
		f.setPosition(1);
		if (chart != null) {
			if (!"".equals(nullToString(chart.getFilterExpress()))) {
				f.setName(f.getName() + "[组合查询]");
			}
			f.setExpress(nullToString(chart.getFilterExpress()));
		}
		JSONObject filterJson = getFilterAsTreeNode(f, "chart", false);

		if (chart != null) {
			Set<ReportFilter> filter = chart.getFilter();
			JSONArray childrenJson = new JSONArray();
			for (ReportFilter sf : filter) {
				childrenJson.put(getFilterAsTreeNode(sf, "chart", true));
			}
			filterJson.put("children", childrenJson);
		}

		treeJson.put(filterJson);

		return treeJson;
	}

	@Override
	public String savePage(Map<String, String> model) throws JSONException {
		JSONObject returnJson = new JSONObject();
		String id = nullToString(model.get("page_id"));
		ReportPage page = null;
		try {
			if ("".equals(id)) {
				page = new ReportPage();
			} else {
				page = this.reportDao.findEntityByID(ReportPage.class, id);
			}
			page.setName(nullToString(model.get("page_name")));
			page.setEnname(nullToString(model.get("page_enname")));
			page.setUrl(nullToString(model.get("page_url")));
			this.reportDao.saveOrUpdateEntity(page);
			String layoutId = nullToString(model.get("layoutId"));
			if (!"".equals(layoutId)) {
				ReportLayout layout = this.reportDao.findEntityByID(ReportLayout.class, layoutId);
				layout.setDivHeight(Integer.parseInt(nullToString(model.get("layout_height"), "0")));
				layout.setDivWidth(Integer.parseInt(nullToString(model.get("layout_width"), "0")));
				this.reportDao.saveOrUpdateEntity(layout);
			}
			returnJson.put("message", "TRUE");
			returnJson.put("result", page.getId());
		} catch (Exception e) {
			log.error("", e);
			returnJson.put("message", "ERROR");
			returnJson.put("result", e.getMessage());
		}
		return returnJson.toString();
	}

	/**
	 * 分页查询数据源列表
	 */
	@Override
	public List getReportDataSourceByPage(ReportDataSource reportDataSource, int start, int end, String sortField, String sortOrder)
			throws Exception {
		String sql = " select * from report_datasource where id in (select sid from (select sid ,rownum rn from (select id sid from report_datasource )where rownum <? )where rn >?)";
		System.out.println(sql + "====");
		List list = this.getBussinessDao().getJdbcTemplate().queryForList(sql,end,start);
		System.out.println(list.toString());
		return list;
	}

	@Override
	public String getDataSourceAsTableJson() throws Exception {
		String query = "from ReportDataSource ds order by ds.dataSourceName";
		List<ReportDataSource> rds = this.reportDao.findResultsByHSQL(query);
		JSONArray json = new JSONArray();
		if(rds != null){
			for(ReportDataSource ds : rds){
				JSONObject jo = new JSONObject();
				jo.put("dataSourceName", ds.getDataSourceName());
				jo.put("dataSourceType", ds.getDataSourceType().name());
				jo.put("dialect", ds.getDialect());
				jo.put("dialectName", ds.getDialectName());
				jo.put("id", ds.getId());
				jo.put("driverName", ds.getDriverName());
				jo.put("jndi", ds.getJndi());
				jo.put("url", ds.getUrl());
				jo.put("username", ds.getUsername());
				jo.put("password", ds.getPassword());
				json.put(jo);
			}
		}
		return json.toString();
	}
	
	@Override
	public String getMatchTableAsTableJson() throws Exception {
		String query = "from ReportControlMatch ds order by ds.name";
		List<ReportControlMatch> rds = this.reportDao.findResultsByHSQL(query);
		JSONArray json = new JSONArray();
		if(rds != null){
			for(ReportControlMatch ds : rds){
				JSONObject jo = new JSONObject();
				jo.put("matchName", ds.getName());
				jo.put("matchSql", ds.getMathchSQL());
				jo.put("id", ds.getId());
				json.put(jo);
			}
		}
		return json.toString();
	}
	
	@Override
	public void runReportLazy() throws Exception {
		String hql = "From  com.tenwa.report.entity.ReportTable where isCache = ?";
		List<ReportTable> tables =  reportDao.findResultsByHSQL(hql, true);
		for(ReportTable table : tables){
			final String talbeid=table.getId();
			 try {		    	
			    	String returnValue = (String)this.reportDao.getJdbcTemplate().execute(  
			    		     new CallableStatementCreator() {  
			    		        public CallableStatement createCallableStatement(Connection con) throws SQLException {  
			    		           String storedProc = "{call proc_lazy_report_table (?,?)}";// 调用的sql  		    		           
			    		           CallableStatement cs = con.prepareCall(storedProc);  
			    		           cs.setString(1, talbeid);// 设置输入参数的值  
			    		           cs.registerOutParameter(2,OracleTypes.VARCHAR);// 注册输出参数的类型  
			    		           return cs;  
			    		        }  
			    		     }, new CallableStatementCallback() {  
			    		         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {  
			    		           cs.execute();  
			    		           return cs.getString(2);// 获取输出参数的值  
			    		     }  
			    	});  
			    	if(null!=returnValue&&(!"".endsWith(returnValue))){
			    		throw new BusinessException(returnValue);
			    	}
			    } catch (Exception e) {
						throw new BusinessException("懒加载表:"+table.getName()+"出错  内容:"+e.getMessage());
			    }
		} 
	}
	
	private String getColumnStr(ReportTable table ){
		String columnSql = "";
		Set<ReportColumn> columns =  table.getReportColumns();
		for(Iterator<ReportColumn> ite = columns.iterator();ite.hasNext();){
			ReportColumn column = ite.next();
			columnSql += column.getName()+",";
		}
		return columnSql;
	}
	@Override
	public String getUserTree() throws Exception {
		Department rootDept = this.reportDao.findEntityByID(Department.class, "0");
		JSONArray treeJson = new JSONArray();
		treeJson.put(getChildrenDeptTree(rootDept));
		return treeJson.toString();
	}
	
	private JSONObject getChildrenDeptTree(Department dept) throws Exception{
		JSONObject deptJson = dept.getJsonObjectDept(dept, false, null);
		JSONArray childrenJson = new JSONArray();

		for (Department subDept : dept.getChildrenDepts()) {
			JSONObject subDeptJson = getChildrenDeptTree(subDept);
			childrenJson.put(subDeptJson);
		}

		// Add User
		for (UserDepartment userDept : dept.getUserDepts()) {
			JSONObject userJsonObj = dept.getJsonObjectUser(userDept);
			childrenJson.put(userJsonObj);
		}
		deptJson.put("children", childrenJson);
		return deptJson;
	}
	@Override
	public void saveReportTimedTask() throws Exception {
			String now_time=DateUtil.getSystemDate();//当前时间
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 0);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String exe_time= df.format(calendar.getTime());
			if(now_time.equals(exe_time)){//判断每月的月底
			int month=Integer.parseInt((now_time.substring(5,7)));//根据月份进行判断
			switch (month){
			case 3:
				saveReportHis("0");
				saveReportHis("1");
				break;
			case 6:
				saveReportHis("0");
				saveReportHis("1");
				saveReportHis("2");
				break;
			case 9:
				saveReportHis("0");
				saveReportHis("1");
				break;
			case 12:
				saveReportHis("1");
				saveReportHis("0");
			
				saveReportHis("2");
				break;
			default :
				saveReportHis("0");
			}
		}
	}
	
	@Override
	public void saveReportHis(String reportClassify) throws Exception {
		String Reporthql = "From  Report r where r.reportOrType = ? and r.reportClassify=?";
		List<Report> tables =  reportDao.findResultsByHSQL(Reporthql, "1",reportClassify);
		for (Report report : tables) {
			String istablenamesql="select count(1) cou from dba_tables where owner = 'TYZL' and table_name = ?";
			List<Map<String, Object>> istablenamelist=this.getBussinessDao().getJdbcTemplate().queryForList(istablenamesql,report.getTableName().toUpperCase());
			String reportLayoutHql="From ReportLayout rl where rl.report=?";
			List<ReportLayout> reportLayoutList =  reportDao.findResultsByHSQL(reportLayoutHql, report);
			  if(reportLayoutList.size()>0){
			ReportLayout rl=reportLayoutList.get(0);//针对report_report表和report_table的1对1关系创建动态表，
			String contentId=rl.getContentId().replace(rl.getContentType()+"#", "");
			//通过ReportLayout表的contentId查找reportTable表
			String reportTable="From ReportTable ft where ft.id=?";
			List<ReportTable> reportTableList =  reportDao.findResultsByHSQL(reportTable, contentId);
			String exesql = "";
			if(reportTableList.size()>0){//説明ReportTable和ReportLayout有关联关系，可根据ReportTable检索出reportcolumn对象
				if(istablenamelist.size()>0){
					String cou=istablenamelist.get(0).get("cou").toString();//大于0表存在,就写插入语句
					  if(toInteger(cou)>0){
							for(ReportTable restr:reportTableList){
					    		exesql+="insert into "+report.getTableName()+"("+getColumnStr(restr)+"create_date)select "+getColumnStr(restr)+"sysdate from("+restr.getSql()+")t";
					    	}
					  }else{//反之创建动态表名并且执行插入数据操作
							for(ReportTable restr:reportTableList){
					    		exesql+="CREATE TABLE  "+report.getTableName()+"("+getColumnStr(restr)+"create_date) as select "+getColumnStr(restr)+"sysdate  from("+restr.getSql()+")t";
					    	}
					  }
					
				}
			}
			System.out.println("111===="+exesql);
			reportDao.getJdbcTemplate().execute(exesql);
			}
		}
		
	}
	public Report getreport(Report report,Map<String, String> model)  {
		try {
		  String random = createCalNum();
		  String report_ortype=model.get("reportortype");//报表类型
	      String report_classify=model.get("reportclassify");//报表分类 reportType
		if("1".equals(report_ortype)){//1104报表
			switch(report_classify){
			case "0"://月报
		    report.setTableName("t_monthly_"+random);
				break;
			case "1"://季报
			report.setTableName("t_season_"+random);
				break;
			case "2"://半年报
			report.setTableName("t_year_"+random);	
				break;	
			default:
			}
		 }		
	   } catch (Exception e) {
			e.printStackTrace();
		}

		return report;
	}
	public synchronized String createCalNum() throws Exception {
		try{
			String year=DateUtil.getSystemDate().substring(0, 4);
			String month=DateUtil.getSystemDate().substring(5, 7);
			List<Map<String,Object>> numberlist=this.reportDao.queryListBySql("select * from T_SERIAL_NUMBER where type_='报表系列' ");
			if(numberlist!=null&&numberlist.size()>0){
				if(new BigDecimal(year).compareTo(new BigDecimal(numberlist.get(0).get("year_").toString()))!=0||new BigDecimal(month).compareTo(new BigDecimal(numberlist.get(0).get("month_").toString()))!=0){
					this.reportDao.updateBySql("update T_SERIAL_NUMBER set year_=?, month_=?, order_number_=? where  type_='报表系列' ", Integer.parseInt(year),Integer.parseInt(month),0001);
					return "report"+"0001";
				}else{
					BigDecimal number=new BigDecimal(numberlist.get(0).get("order_number_").toString()).add(BigDecimal.ONE) ;
					String format=new DecimalFormat("0000").format(number);
					this.reportDao.updateBySql("update T_SERIAL_NUMBER set  order_number_=? where  type_='报表系列' ", Integer.parseInt(format));
					return "report"+format;
				}
			}else{
				this.reportDao.updateBySql("insert into T_SERIAL_NUMBER ( id_,type_,year_,month_,order_number_) values(sys_guid(),'报表系列',?,?,?)", Integer.parseInt(year),Integer.parseInt(month),0001);
				return "report"+"0001";
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public String queryReportHisData(Map<String, String> model) throws Exception {
		String implement=model.get("implement");//实施查询动态选中的SQL
		String reportID=model.get("reportname");//Report主键ID
		Report report=reportDao.findEntityByID(Report.class, reportID);
		String result = ""; 
		StringBuffer  sthtml=new StringBuffer();
			String reportyear=model.get("reportyear");//年份
			String reporttype=model.get("reporttype");//月份
		    StringBuffer sb =new StringBuffer(" where 1=1");
		    if(reportyear!=null&&!"".equals(reportyear)){
		    	sb.append("&dateyear=").append(reportyear);
		    }
		    if(reporttype!=null&&!"".equals(reporttype)){
		    	sb.append("&datemonthly=").append(reporttype);
		    }
			String tablename=report.getTableName();
			String istablenamesql="select count(1) cou from dba_tables where owner = 'TYZL' and table_name = ?";
			List<Map<String, Object>> istablenamelist=this.getBussinessDao().getJdbcTemplate().queryForList(istablenamesql,tablename.toUpperCase());
			if(!"implement".equals(implement)){
				if(istablenamelist.size()>0){
					String cou=istablenamelist.get(0).get("cou").toString();//大于0表存在,就写插入语句
					  if(toInteger(cou)==0){
						  throw new BusinessException("当前没有可查询的数据！");
					  }
				}
			}
	    String sqlxml="select * from "+tablename+sb.toString();
		if(null!=report){
			String reportLayoutHql="From ReportLayout rl where rl.report=?";
			List<ReportLayout> reportLayoutList =  reportDao.findResultsByHSQL(reportLayoutHql, report);
			ReportLayout rl=reportLayoutList.get(0);
			String contentId=rl.getContentId().replace(rl.getContentType()+"#", "");
			String reportTablesql="From ReportTable ft where ft.id=?";
			List<ReportTable> reportTableList =  reportDao.findResultsByHSQL(reportTablesql, contentId);
			if(reportTableList.size()>0){
				ReportTable  reportTableh=reportTableList.get(0);
				if("implement".equals(implement)){
					String reportTableid=reportTableh.getId();
					sthtml.append("<div id='datagrid1' class='mini-datagrid' style='width:100%;height:100%;' "
							+ "url='/tyzl/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=/eleasing/jsp/report/trends_hisreport.xml&reportTableid="+reportTableid+"'"
							+ "idField='id'  sizeList='[5,10,20,50]'  allowAlternating='true' pageSize='20'"
							+ " dataField='datas' showColumnsMenu='true' ><div property='columns' ><div type='indexcolumn' width='35'></div>");
				}else{
					sthtml.append("<div id='datagrid1' class='mini-datagrid' style='width:100%;height:100%;' "
							+ "url='/tyzl/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=/eleasing/jsp/report/trends_hisreport.xml&tablename="+sqlxml+"'"
							+ "idField='id'  sizeList='[5,10,20,50]'  allowAlternating='true' pageSize='20'"
							+ " dataField='datas' showColumnsMenu='true' ><div property='columns' ><div type='indexcolumn' width='35'></div>");
				}
				result =sthtml.toString()+getColumnlabelHtml(reportTableh);
			}
		}
		return result;
	}
	private String getColumnlabelHtml(ReportTable table ){
		StringBuffer  sbhtml=new StringBuffer();
		Set<ReportColumn> columns =  table.getReportColumns();
		for(Iterator<ReportColumn> ite = columns.iterator();ite.hasNext();){
			ReportColumn column = ite.next();
			sbhtml.append("<div field='").append(column.getName().toLowerCase()).append("'width='200' headerAlign='center' allowSort='true'>").append(column.getLabel()).append("</div>");
		}
		return sbhtml.toString()+"</div></div>";
	}
	
}
