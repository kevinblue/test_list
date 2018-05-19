﻿package com.tenwa.report.serviceImpl;

import static com.tenwa.kernal.utils.StringUtil.booleanToString;
import static com.tenwa.kernal.utils.StringUtil.nullToString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.tuple.MutablePair;

import com.fasterxml.jackson.core.type.TypeReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.Group;
import com.tenwa.business.entity.Menu;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.service.AclService;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.EnumUtil;
import com.tenwa.kernal.utils.PermissionUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.SM.MachineMainData;
import com.tenwa.leasing.entity.SM.PickUpMainData;
import com.tenwa.leasing.entity.SM.ProjMainData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.global.AnnualOperation;
import com.tenwa.leasing.entity.global.GlobalBaseMessageFan;
import com.tenwa.leasing.entity.global.GlobalContractMessage;
import com.tenwa.leasing.utils.BSDataBaseManager;
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
import com.tenwa.report.util.RestUtils;

@Service("reportService")
public class ReportServiceImpl extends AbstractBaseServiceImpl implements ReportService {
	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

	private static Map<String, Queue<String>> uploadMessage = new LinkedHashMap<String, Queue<String>>();

	@Resource(name = "tableService")
	private TableService tableService;

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
			this.getBussinessDao().getJdbcTemplate().execute(
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
		if (WebUtil.isEnglish()) {
			nodeJson.put("text", null == report.getEnname() || 0 > report.getEnname().length() ? report.getName()
					: report.getEnname());
		} else {
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
		attributesJson.put("name", report.getName());
		attributesJson.put("enname", report.getEnname());
		attributesJson.put("code", report.getCode());
		attributesJson.put("position", report.getPosition());
		attributesJson.put("description", report.getDescription());
		attributesJson.put("reportType", nullToString(report.getReportType()));
		attributesJson.put("parentReport",
				(report.getParentReport() == null) ? "-1" : report.getParentReport().getId());
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

	private <T extends ReportContent> JSONObject getReportContentNodeAsJson(T t, ReportLayout layout,
			ContentType contentType) throws Exception {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("id", t.getId());
		if (WebUtil.isEnglish()) {
			jsonObject.put("text", null == t.getEnname() || 0 > t.getEnname().length() ? t.getName()
					: t.getEnname() + "[" + contentType.name() + "]");
		} else {
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
						if (WebUtil.isEnglish()) {
							columnJson.put("text",
									null == column.getEnLabel() || 0 < column.getEnLabel().length()
											? column.getName() + "[" + column.getName() + "]"
											: column.getName() + "[" + column.getEnLabel() + "]");
						} else {
							columnJson.put("text", column.getName() + "[" + column.getLabel() + "]");
						}

						columnJson.put("checked", "true");
						columnJson.put("iconCls", "icon-db");
						JSONObject attributes = new JSONObject();
						attributes.put("table_columnId", column.getId());
						attributes.put("table_columnName", nullToString(column.getName()));
						attributes.put("table_columnEnLabel",
								(null == column.getEnLabel() || 0 >= column.getEnLabel().length()) ? column.getName()
										: column.getEnLabel());
						attributes.put("table_columnLabel", nullToString(column.getLabel()));
						attributes.put("position", String.valueOf(position++));
						attributes.put("table_columnType",
								nullToString(column.getColumnDataType(), ColumnDataType.STRING.name()));
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
						attributes.put("chart_columnType",
								nullToString(column.getColumnDataType(), ColumnDataType.STRING.name()));
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
						attributes.put("chart_columnUsageType",
								nullToString(column.getUsageType(), UsageType.DATA.name()));
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
				if (WebUtil.isEnglish()) {
					f.setName(f.getName() + "[group search]");
				} else {
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
				if (WebUtil.isEnglish()) {
					f.setName(f.getName() + "[group search]");
				} else {
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

	private JSONObject getFilterAsTreeNode(ReportFilter filter, String prefix, boolean bracketLabel)
			throws JSONException {
		JSONObject nodeJson = new JSONObject();
		nodeJson.put("id", filter.getId());
		String label = nullToString(filter.getName());
		if (bracketLabel) {
			if (WebUtil.isEnglish()) {
				label += "[" + (nullToString(filter.getEnname()).equalsIgnoreCase("") ? nullToString(filter.getName())
						: nullToString(filter.getEnname()).equalsIgnoreCase("")) + "]";
			} else {
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
		if (WebUtil.isEnglish()) {
			f.setName("Control");
		} else {
			f.setName("权限控制");
		}
		f.setPosition(0);
		JSONObject searchJson = getControlAsTreeNode(f, "table", false);
		if (null != table) {
			Set<ReportControl> controls = table.getControls();
			log.info("" + controls.size());
			JSONArray childNodes = new JSONArray();
			for (ReportControl control : controls) {
				childNodes.put(this.getControlAsTreeNode(control, "table", true));
			}
			searchJson.put("children", childNodes);
		}
		treeJson.put(searchJson);
		log.info(treeJson.toString());
		return treeJson;
	}

	private JSONObject getControlAsTreeNode(ReportControl control, String prefix, boolean bracketLabel)
			throws JSONException {
		JSONObject nodeJson = new JSONObject();
		nodeJson.put("id", control.getId());
		String label = nullToString(control.getName());
		if (bracketLabel) {
			label += "[" + nullToString(control.getLabel()) + "]";
		}
		nodeJson.put("text", label);

		JSONObject attributesJson = new JSONObject();
		attributesJson.put(prefix + "_controlId", control.getId());
		attributesJson.put("position", nullToString(control.getPosition(), "0"));
		attributesJson.put(prefix + "_controlName", control.getName());
		attributesJson.put(prefix + "_controlLabel", control.getLabel());
		attributesJson.put(prefix + "_controlMatch", control.getMatch() == null ? "" : control.getMatch().getId());
		attributesJson.put(prefix + "_controlGroup", control.getGroupId() == null ? "" : control.getGroupId());
		nodeJson.put("attributes", attributesJson);
		return nodeJson;
	}

	@Override
	public String validateSQL(String datasourceId, String sql, String queryType,
			List<MutablePair<String, String>> params) throws Exception {

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
				ReportDataSource rds = (ReportDataSource) this.findEntityByID(ReportDataSource.class,
						model.get("table_datasource"));
				if (rds != null)
					table.setReportDataSource(rds);
			}
			table.setSql(model.get("table_sql"));
			table.setShowRowNumber(toBoolean(model.get("table_showRowNumber"), "1"));
			table.setShowTotalTitle(toBoolean(model.get("table_showTotalTitle"), "1"));
			table.setIsScale(toBoolean(model.get("table_isScale"), "1"));
			table.setIsCache(toBoolean((null == model.get("table_isCache") || "".equals(model.get("table_isCache")))
					? "0" : model.get("table_isCache"), "1"));
			table.setIsExcel(toBoolean((null == model.get("table_isExcel") || "".equals(model.get("table_isExcel")))
					? "0" : model.get("table_isExcel"), "1"));
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
					column.setActionType(
							EnumUtils.getEnum(ContentType.class, getValueFromJsonObject(jo, "table_columnActionType")));
					column.setActionParamters(getValueFromJsonObject(jo, "table_columnActionParameter"));
					column.setAlignType(
							EnumUtils.getEnum(AlignType.class, getValueFromJsonObject(jo, "table_columnAlign")));
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
					filter = (ReportFilter) this.copyAndOverrideExistedValueFromStringMap(filterModel, filter, null,
							false);
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

			// add Controls
			Set<ReportControl> controls = table.getControls();
			json = nullToString(model.get("table_selectedControls"));
			List<String> removeControlsId = new ArrayList<String>();
			if (null != controls && controls.size() > 0) {
				for (ReportControl control : controls) {
					removeControlsId.add(control.getId());
				}
			}
			log.info(json);
			if (null != json && 0 < (json.trim().length())) {
				JSONArray jsonStrs = new JSONArray(json);
				for (int i = 0; i < jsonStrs.length(); i++) {
					JSONObject jsonStr = jsonStrs.getJSONObject(i);
					ReportControl control = new ReportControl();
					String controlId = getValueFromJsonObject(jsonStr, "table_controlId");
					if (null != controlId && 0 < controlId.trim().length()) {
						control = (ReportControl) this.findEntityByID(ReportControl.class, controlId);
						controls.remove(control);
						removeControlsId.remove(controlId);
					}

					// TODO
					control = changeJSONMapToControl(jsonStr, control);
					log.info(control.getLabel());
					log.info("" + control.getPosition());
					if (control.getPosition() == 0) {
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
			if (null != controls && controls.size() > 0) {
				table.setControls(controls);
			}
			this.saveOrUpdateEntity(table);
			columns = null;

			// remove old columns
			for (String id : removeColumnsId) {
				this.removeEntityById(ReportColumn.class, id);
			}
			// remove old control
			for (String id : removeControlsId) {
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
			// 保存报表导出权限
			String controlPersons = model.get("table_controlPerson");
			if (null != controlPersons && 0 < controlPersons.length()) {
				this.aclService.removeAllEntites(
						this.aclService.findResultsByHSQL("from ReportPowerControl rc where rc.reportId = ?", table));
				List<ReportPowerControl> powers = new ArrayList<ReportPowerControl>();
				String[] usersIds = controlPersons.split(",");
				for (String id : usersIds) {
					User user = new User();
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

	private ReportControl changeJSONMapToControl(JSONObject json, ReportControl control) throws Exception {
		control.setId(json.getString("table_controlId"));
		control.setName(json.getString("table_controlName"));
		control.setLabel(json.getString("table_controlLabel"));
		String matchId = json.getString("table_controlMatch");
		String groupId = json.getString("table_controlGroup");
		if (null != matchId && 0 < matchId.length()) {
			ReportControlMatch match = new ReportControlMatch();
			match.setId(matchId);
			control.setMatch(match);
		}
		if (null != groupId && 0 < groupId.length()) {
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
			chart.setChartType(
					Enum.valueOf(ChartType.class, nullToString(model.get("chart_chartType"), ChartType.Column.name())));
			chart.setFusionChart(Enum.valueOf(FusionChart.class,
					nullToString(model.get("chart_chartType2"), FusionChart.Column2D.name())));
			chart.setQuery(model.get("chart_sql"));
			chart.setQueryParamValue(model.get("chart_sqlParamValue"));
			chart.setQueryType(Enum.valueOf(QueryType.class,
					nullToString(model.get("chart_sqlType"), QueryType.SIMPLESQL.name())));
			chart.setxAxisName(nullToString(model.get("chart_xAxisName")));
			chart.setyAxisName(nullToString(model.get("chart_yAxisName")));
			chart.setsAxisName(nullToString(model.get("chart_sAxisName")));
			String datasource_id = nullToString(model.get("chart_datasource"));
			if (!"".equals(datasource_id)) {
				try {
					ReportDataSource rds = this.reportDao.findEntityByID(ReportDataSource.class, datasource_id);
					chart.setReportDataSource(rds);
				} catch (Exception e) {
					chart.setReportDataSource(null);
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
					column.setActionType(
							EnumUtils.getEnum(ContentType.class, getValueFromJsonObject(jo, "chart_columnActionType")));
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

					filter = (ReportFilter) this.copyAndOverrideExistedValueFromStringMap(filterModel, filter, null,
							false);
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

	private Map<String, String> copyJsonToMap(JSONObject jsonObj, String additionKey, boolean isAdd, boolean isPrefix)
			throws JSONException {
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

	private void getAllSubReport(Report report, List<Report> reports, List<ReportTable> tables,
			List<ReportChart> charts, List<ReportPage> pages, List<ReportLayout> layouts, List<String> ids) {
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

		List<Report> flReports = new ArrayList<Report>(); // find first level
															// report
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

	private void importReport(List<Report> reports, Map<String, ReportTable> importedTables,
			Map<String, ReportChart> importedCharts, Map<String, ReportPage> importedPages, Report desParentReport,
			Queue<String> message) {
		if (reports == null) {
			return;
		}

		Set<Report> flReportsInDb = desParentReport.getChildrenReport();

		for (Report srcReport : reports) {
			try {
				boolean reportFound = false;
				Report newReport = null;
				for (Report desReport : flReportsInDb) {
					if (srcReport.getName().equalsIgnoreCase(desReport.getName())
							&& srcReport.getReportType() == desReport.getReportType()) {
						newReport = desReport;
						reportFound = true;
						break;
					}
				}
				if (newReport == null) {
					newReport = new Report();
				}
				BeanUtils.copyProperties(srcReport, newReport,
						new String[] { "id", "menuId", "parentReport", "childrenReport", "layout" });
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
				this.getBussinessDao().getJdbcTemplate()
						.execute("INSERT INTO REPORT_REPORT(ID_, NAME_, CODE_, DESCRIPTION_, POSITION_, REPORTTYPE) "
								+ "VALUES ('0', '报表', 'RPROOT', '报表', '0', 'FOLDER')");
				rootReport = this.reportDao.findEntityByID(Report.class, "0");
				this.saveToMenu(rootReport, rootMenuId);
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return rootReport;
	}

	private Map<String, ReportPage> importReportPage(List<ReportPage> pages,
			Map<String, ReportDataSource> importedDatasource, Queue<String> message) {
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

	private Map<String, ReportChart> importReportChart(List<ReportChart> charts,
			Map<String, ReportDataSource> importedDatasource, Queue<String> message) throws Exception {
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
					BeanUtils.copyProperties(srcChart, foundDesChart,
							new String[] { "id", "filter", "search", "dataSource" });

					foundDesChart.setReportColumns(null);
					foundDesChart.setFilter(null);
					foundDesChart.setSearch(null);
					foundDesChart.setReportDataSource(null);

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
					if (srcChart.getReportDataSource() != null) {
						String oldDsId = srcChart.getReportDataSource().getId();
						if (importedDatasource.containsKey(oldDsId)) {
							foundDesChart.setReportDataSource(importedDatasource.get(oldDsId));
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

	private Map<String, ReportTable> importReportTable(List<ReportTable> tables,
			Map<String, ReportDataSource> importedDatasource, Queue<String> message) {
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
					BeanUtils.copyProperties(srcTable, foundDesTable,
							new String[] { "id", "filter", "search", "reportColumns", "reportDataSource" });
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
	public void updateLayout(String reportId, String layoutId, String contentId, String contentType, String action)
			throws Exception {
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
	public void updatePosition(String entityClass, String id, String pid, String parentField, Integer position)
			throws Exception {
		String hql = "update " + entityClass + " t set t.position = t.position + 1 where t.position >= ? and t."
				+ parentField + ".id=?";
		this.reportDao.updateByHSQL(hql, position, pid);
		hql = "update " + entityClass + " t set t.position=?, t." + parentField + ".id=? where t.id=?";
		this.reportDao.updateByHSQL(hql, position, pid, id);

		// 更新Report
		if (entityClass.equalsIgnoreCase(Report.class.getSimpleName())) {
			Report report = this.reportDao.findEntityByID(Report.class, id);
			Menu menu = null;
			/*
			 * if (null == report.getMenuId() || "".equals(report.getMenuId()))
			 * { saveToMenu(report, rootMenuId); }
			 */
			menu = this.aclService.findEntityByID(Menu.class, report.getMenuId());
			Menu parentMenu = null;
			if (pid == null || "0".equals(pid)) {
				// parentMenu = this.aclService.findEntityByID(Menu.class,
				// rootMenuId);
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
	public List getReportDataSourceByPage(ReportDataSource reportDataSource, int start, int end, String sortField,
			String sortOrder) throws Exception {
		String sql = " select * from report_datasource where id in (select sid from (select sid ,rownum rn from (select id sid from report_datasource )where rownum <"
				+ end + " )where rn >" + start + ")";
		System.out.println(sql + "====");
		List list = this.getBussinessDao().getJdbcTemplate().queryForList(sql);
		System.out.println(list.toString());
		return list;
	}

	@Override
	public String getDataSourceAsTableJson() throws Exception {
		String query = "from ReportDataSource ds order by ds.dataSourceName";
		List<ReportDataSource> rds = this.reportDao.findResultsByHSQL(query);
		JSONArray json = new JSONArray();
		if (rds != null) {
			for (ReportDataSource ds : rds) {
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
		if (rds != null) {
			for (ReportControlMatch ds : rds) {
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
		List<ReportTable> tables = reportDao.findResultsByHSQL(hql, true);

		for (ReportTable table : tables) {
			// 1.首先去判断是否存在该报表所对应的缓存表
			String isExitHql = "from ReportTableLazy where reportId = ?";
			List<ReportTableLazy> lazys = reportDao.findResultsByHSQL(isExitHql, table);
			String excuteSql = "";
			if (null != lazys && 0 < lazys.size()) {
				String tableName = lazys.get(0).getLazyTableName();
				String deleteSql = "truncate table " + tableName;
				reportDao.getJdbcTemplate().execute(deleteSql);
				excuteSql = "INSERT INTO " + tableName + " (";
				excuteSql += getColumnStr(table);
				excuteSql += "CREATE_DATE)";
				excuteSql += "SELECT " + getColumnStr(table);
				excuteSql += "'" + DateUtil.getSystemDateTime() + "'";
				excuteSql += " FROM (" + table.getSql() + ")t";
			} else {
				String serial = WorkflowUtil.getReportLazySerialNumber("报表懒加载序列号", null,
						reportDao.getHibernateTemplate(), reportDao.getJdbcTemplate());
				String tableName = "REPORT_LAZY_" + serial;
				String currentTime = DateUtil.getSystemDateTime();
				ReportTableLazy lazy = new ReportTableLazy();
				lazy.setLazyTableName(tableName);
				lazy.setReportId(table);
				lazy.setCreateDate(currentTime);
				reportDao.saveEntity(lazy);

				excuteSql = "CREATE TABLE " + tableName + " (";
				excuteSql += getColumnStr(table);
				excuteSql += "CREATE_DATE) AS ";
				excuteSql += "SELECT " + getColumnStr(table);
				excuteSql += "'" + currentTime + "'";
				excuteSql += " FROM (" + table.getSql() + ")t";
			}
			reportDao.getJdbcTemplate().execute(excuteSql);
		}
	}

	private String getColumnStr(ReportTable table) {
		String columnSql = "";
		Set<ReportColumn> columns = table.getReportColumns();
		for (Iterator<ReportColumn> ite = columns.iterator(); ite.hasNext();) {
			ReportColumn column = ite.next();
			columnSql += column.getName() + ",";
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

	private JSONObject getChildrenDeptTree(Department dept) throws Exception {
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
	public void runOwnershipTransfersZZ() throws Exception {

		String bm_sql = "insert into BASE_MESSAGE" + "  (ID," + "   MSG_TITLE," + "   SUBJECT_," + "   SEND_DATE,"
				+ "   CREATE_DATE," + "   MSG_TYPE," + "   FROMUSER," + "   CREATOR_)" + "  select sys_guid(),"
				+ "         '回租设备款投放完毕',"
				+ "               '您好：回租合同编号为'||ci.contract_number||'的'||ci.project_name||'的最后一笔设备款投放流程结束',"
				+ "         to_char(SYSTIMESTAMP, 'yyyy-mm-dd'),"
				+ "         to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss')," + "         'msgtype.1',"
				+ "         'Administrator'," + "         'Administrator'"
				+ "    from (select sum(cp.plan_money) planmoney," + "                 cc.contract_id contractid,"
				+ "                 sum(cc.fact_money) factmoney" + "            from contract_fund_fund_charge cc"
				+ "            left join contract_fund_fund_plan cp" + "              on cc.payment_id = cp.id"
				+ "           where cc.fee_type = 'feetype10'" + "           group by cc.contract_id) a"
				+ " left join contract_info ci on a.contractid=ci.id   "
				+ "   where a.planmoney - a.factmoney = 0 and    not exists"
				+ "   (select 1 from BASE_MESSAGE bm  where instr(to_char(bm.subject_),'您好：回租合同编号为'||ci.contract_number)>0 )";
		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER"
				+ "  (ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)" + "  select sys_guid(),"
				+ "         '1'," + "         bm.create_date," + "         bm.id,"
				+ "         '402880ad58f1f5ad0158f20acc920032'," + "         bm.creator_" + "    from BASE_MESSAGE bm"
				+ "   where not exists" + "     (select 1 from BASE_MESSAGE_TO_USER b where b.msg_id = bm.id) ";
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);

	}

	@Override
	public void saveGlobal(String reportdateold) throws Exception {
		List<Map<String, Object>> jsonMaplist = null;
		String wfid = "";
		String wfidListsql = "From GlobalContractMessage";
		List<GlobalContractMessage> globalData = reportDao.findResultsByHSQL(wfidListsql);
		for (GlobalContractMessage globalContractMessage : globalData) {
			wfid = globalContractMessage.getWfid();
			String reportdate = reportdateold;
			for (int j = 2; j < 4; j++) {
				if (j == 3) {
					if (reportdate.length() > 6) {
						String aString = reportdate.substring(5, 7);
						if (!aString.equals("12")) {
							break;
						} else {
							reportdate = reportdate.substring(0, 4);
						}
					}
				}
				try {
					jsonMaplist = RestUtils.doGet("http://54.223.190.36:8081/api/WeChat/WfDataReport/WF/" + wfid + "/"
							+ j + "/" + reportdate + "/List", "", new TypeReference<List<Map<String, Object>>>() {
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (jsonMaplist == null) {
					continue;
				}
				if (jsonMaplist.size() == 0) {
					continue;
				}
				for (int i = 0; i < jsonMaplist.size(); i++) {
					AnnualOperation annualOperation = new AnnualOperation();
					Set<Entry<String, Object>> jsonSet = jsonMaplist.get(i).entrySet();
					for (Entry<String, Object> entry : jsonSet) {
						if (entry.getKey().equals("reportdate")) {
							if (entry.getValue() == null || !entry.getValue().equals(reportdate)) {
								break;
							}
							annualOperation.setReportDate(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("windspeed")) {
							annualOperation.setWindSpeed(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("temperature")) {
							annualOperation.setTempeRature(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("electricdate")) {
							annualOperation
									.setElectricDate(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("dateavailability")) {
							annualOperation
									.setDateavailAbility(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("faultnums")) {
							annualOperation.setFaultNums(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("faultdate")) {
							annualOperation.setFaultDate(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("mtbf")) {
							annualOperation.setMtbf(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("mttr")) {
							annualOperation.setMttr(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("maintainloss")) {
							annualOperation
									.setMaintainLoss(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("limitloss")) {
							annualOperation.setLimitLoss(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("faultloss")) {
							annualOperation.setFaultLoss(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("totalloss")) {
							annualOperation.setTotalLoss(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("power")) {
							annualOperation.setPower(entry.getValue() == null ? "" : entry.getValue().toString());
						}
					}
					if (annualOperation != null) {
						annualOperation.setOpenId(reportdate);
						annualOperation.setWfId(wfid);
						annualOperation.setReporttype(j + "");
						annualOperation.setCreateDate(DateUtil.getSystemDateTime());
					}
					if (annualOperation.getReportDate() != null) {
						tableService.saveOrUpdateEntity(annualOperation);
					}
				}
			}
		}
	}

	@Override
	public void saveGlobalFan(String reportdateold) throws Exception {
		List<Map<String, Object>> jsonMaplist = null;
		String wfid = "";
		String wfidListsql = "From GlobalContractMessage";
		List<GlobalContractMessage> globalData = reportDao.findResultsByHSQL(wfidListsql);

		for (GlobalContractMessage globalContractMessage : globalData) {
			wfid = globalContractMessage.getWfid();
			String reportdate = reportdateold;
			for (int j = 2; j < 4; j++) {
				if (j == 3) {
					if (reportdate.length() > 6) {
						String aString = reportdate.substring(5, 7);
						if (!aString.equals("12")) {
							break;
						} else {
							reportdate = reportdate.substring(0, 4);
						}
					}
				}
				try {
					jsonMaplist = RestUtils.doGet("http://54.223.190.36:8081/api/WeChat/WfDataReport/WT/" + wfid + "/"
							+ j + "/" + reportdate + "/" + reportdate + "/List", "",
							new TypeReference<List<Map<String, Object>>>() {
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (jsonMaplist == null) {
					continue;
				}
				if (jsonMaplist.size() == 0) {
					continue;
				}
				for (int i = 0; i < jsonMaplist.size(); i++) {
					GlobalBaseMessageFan globalBaseMessageFan = new GlobalBaseMessageFan();
					Set<Entry<String, Object>> jsonSet = jsonMaplist.get(i).entrySet();
					for (Entry<String, Object> entry : jsonSet) {
						if (entry.getKey().equals("wfwtid")) {// 1风机wfwtid
							globalBaseMessageFan.setWfwtId(entry.getValue() == null ? "" : entry.getValue().toString());
							// annualOperation.setReportDate(entry.getValue().toString());
						}
						if (entry.getKey().equals("wtname")) {// 1风机编号
							globalBaseMessageFan
									.setFanNumber(entry.getValue() == null ? "" : entry.getValue().toString());
							// annualOperation.setReportDate(entry.getValue().toString());
						}
						if (entry.getKey().equals("wttype")) {// 2机型
							globalBaseMessageFan
									.setFanType(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("windspeed")) {// 3平均风速
							globalBaseMessageFan
									.setWindSpeed(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("temperature")) {// 4发电量（统计时段内）
							globalBaseMessageFan
									.setTempeRature(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("electricDate")) {// 5等效利用小时数
							globalBaseMessageFan
									.setElectricDate(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("dateavailability")) {// 6机组可利用率
							globalBaseMessageFan
									.setDateavailAbility(entry.getValue() == null ? "" : entry.getValue().toString());

						}
						if (entry.getKey().equals("faultnums")) {// 7故障次数
							globalBaseMessageFan
									.setFaultNums(entry.getValue() == null ? "" : entry.getValue().toString());

						}
						if (entry.getKey().equals("faultdate")) {// 8故障停机总时长
							globalBaseMessageFan
									.setFaultDate(entry.getValue() == null ? "" : entry.getValue().toString());

						}
						if (entry.getKey().equals("mtbf")) {// 9平均无故障时间
							globalBaseMessageFan.setMtbf(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("mttr")) {// 10故障排除时间
							globalBaseMessageFan.setMttr(entry.getValue() == null ? "" : entry.getValue().toString());

						}
						if (entry.getKey().equals("maintainloss")) {// 11维护损失电量
							globalBaseMessageFan
									.setMaintainLoss(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("limitloss")) {// 12限功率损失电量v
							globalBaseMessageFan
									.setLimitLoss(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("faultloss")) {// 13故障损失电量
							globalBaseMessageFan
									.setFaultLoss(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("totalloss")) {// 14损失电量合计
							globalBaseMessageFan
									.setTotalLoss(entry.getValue() == null ? "" : entry.getValue().toString());
						}
						if (entry.getKey().equals("power")) {// 15备注
							globalBaseMessageFan.setPower(entry.getValue() == null ? "" : entry.getValue().toString());
						}
					}
					if (globalBaseMessageFan != null) {
						globalBaseMessageFan.setOpenId(reportdate);
						globalBaseMessageFan.setWfId(wfid);
						globalBaseMessageFan.setReporttype(j + "");
						globalBaseMessageFan.setCreateDate(DateUtil.getSystemDateTime());
					}
					tableService.saveOrUpdateEntity(globalBaseMessageFan);

				}
			}
		}
	}

	// 保存风机合同对应编号
	@Override
	public void saveGlobalContract() throws Exception {
		List<Map<String, Object>> jsonMaplist = null;
		try {
			jsonMaplist = RestUtils.doGet(
					"http://54.223.190.36:8081/api/WeChat/WfDataReport/WF/WfList/TianXin/KpiSystem", "",
					new TypeReference<List<Map<String, Object>>>() {
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		String isHave = "From GlobalContractMessage g where g.wfid=?";
		for (int i = 0; i < jsonMaplist.size(); i++) {
			GlobalContractMessage globalContractMessage = new GlobalContractMessage();
			Set<Entry<String, Object>> jsonSet = jsonMaplist.get(i).entrySet();
			for (Entry<String, Object> entry : jsonSet) {

				if (entry.getKey().equals("wfid")) {// 风场id
					List<GlobalContractMessage> globalData = reportDao.findResultsByHSQL(isHave,
							entry.getValue() == null ? "" : entry.getValue().toString());
					if (globalData.size() > 0) {
						break;
					}
					globalContractMessage.setWfid(entry.getValue() == null ? "" : entry.getValue().toString());
				}
				if (entry.getKey().equals("scadawfid")) {// 2scadawfid
					globalContractMessage.setScadawfid(entry.getValue() == null ? "" : entry.getValue().toString());
				}
				if (entry.getKey().equals("oraproid")) {// 3风机采购合同id
					globalContractMessage.setOraproid(entry.getValue() == null ? "" : entry.getValue().toString());
				}
				if (entry.getKey().equals("wfx")) {// 风场
					globalContractMessage.setWfX(entry.getValue() == null ? "" : entry.getValue().toString());
				}
				if (entry.getKey().equals("wfy")) {// 风场纬度
					globalContractMessage.setWfY(entry.getValue() == null ? "" : entry.getValue().toString());
				}
				if (entry.getKey().equals("wfname")) {// 3风机采购合同id
					globalContractMessage.setWfName(entry.getValue() == null ? "" : entry.getValue().toString());
				}
			}
			if (globalContractMessage != null) {
				globalContractMessage.setCreateDate(DateUtil.getSystemDateTime());
			}
			if (globalContractMessage.getWfid() != null)
				tableService.saveOrUpdateEntity(globalContractMessage);
		}
	}

	// 项目主数据
	public String updateProjMainData() throws Exception {
		String sqlstr = "select  distinct ci.wind_machine,nvl(tpm.id,'0') tpm_id " + "from contract_info ci "
				+ "left join T_PROJ_MAIN_DATA tpm" + " on ci.wind_machine=tpm.项目编号 "
				+ "where ci.wind_machine is not null ";
		List<Map<String, Object>> contractidlist = new ArrayList<Map<String, Object>>();// 用来存放风机号不为空的list
		try {
			contractidlist = this.tableService.queryListBySql(sqlstr, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ProjMainData> listSaveData = new ArrayList<ProjMainData>();
		List<ProjMainData> listupdateData = new ArrayList<ProjMainData>();
		BSDataBaseManager dbm = BSDataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dbm.getConnection();
			if (conn == null) {
				return "失败！";
			}
			conn.setAutoCommit(false);// 将自动提交设置为手动提交
			boolean flag = false;
			if (contractidlist.size() > 0) {
				for (Map<String, Object> restlt : contractidlist) {
					String selectSql = "select * from gwsmprod.project_last_view where 项目编号 ='"
							+ restlt.get("wind_machine") + "'";
					ps = conn.prepareStatement(selectSql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						String id = restlt.get("tpm_id").toString();
						ProjMainData pmd = new ProjMainData();
						if (!"0".equals(id)) {// update
							pmd = this.tableService.findEntityByID(ProjMainData.class, id);
							flag = true;
						}
						pmd.setProjNum(rs.getString("项目编号"));
						pmd.setProjStatus(rs.getString("项目状态"));
						pmd.setProjStage(rs.getString("项目阶段"));
						pmd.setProjCapacity(rs.getString("项目容量"));
						pmd.setSetsNum(rs.getString("机组数量"));
						pmd.setLatitudeType(rs.getString("维度类型"));
						pmd.setLatitude(rs.getString("纬度"));
						pmd.setLongitudeType(rs.getString("经度类型"));
						pmd.setLongitude(rs.getString("经度"));
						pmd.setDistrict(rs.getString("片区"));
						pmd.setProjName(rs.getString("项目名称"));
						pmd.setProjManager(rs.getString("项目经理姓名"));
						pmd.setOwnerUnit(rs.getString("业主单位"));
						pmd.setContractSubject(rs.getString("合同主体"));
						pmd.setContractSigningDate(rs.getString("合同签订日期"));
						pmd.setWarrantyStarttime(rs.getString("质保期开始时间"));
						pmd.setWarrantyDuration(rs.getString("质保期时长"));
						pmd.setWindFieldAvailability(rs.getString("风场可用率"));
						pmd.setUnitAvailability(rs.getString("机组可用率"));
						pmd.setSinglePowerCurve(rs.getString("单台功率曲线"));
						pmd.setWindPowerCurve(rs.getString("风场功率曲线"));
						pmd.setPowerCurveDescription(rs.getString("功率曲线说明"));
						if (flag) {// update
							listupdateData.add(pmd);
						} else {// save
							listSaveData.add(pmd);
						}
					}
				}
			}
			if (listSaveData.size() > 0) {
				this.tableService.saveAllEntities(listSaveData);
			}
			if (listupdateData.size() > 0) {
				this.tableService.updateAllEntities(listupdateData);
			}
			conn.commit();
			return "成功";
		} catch (SQLException e) {// 回滚事物
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return "失败";
			}
			e.printStackTrace();
			return "失败";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}

		}
	}

	// 机组主数据
	public String updateMachineMainData() throws Exception {
		String sqlstr = " select distinct ci.wind_machine,nvl(tpm.id,'0') tpm_id,nvl(tpm.unit_num,'0') unitnum from "
				+ "contract_info ci left join T_MACHINE_MAIN_DATA tpm "
				+ "on ci.wind_machine=tpm.WIND_FIELD_NUM where ci.wind_machine is not null";
		List<Map<String, Object>> contractidlist = new ArrayList<Map<String, Object>>();// 用来存放风机号不为空的list
		try {
			contractidlist = this.tableService.queryListBySql(sqlstr, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<MachineMainData> listsaveData = new ArrayList<MachineMainData>();
		List<MachineMainData> listupdateData = new ArrayList<MachineMainData>();
		// 连接数据库
		BSDataBaseManager dbm = BSDataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			conn = dbm.getConnection();
			if (conn == null) {
				return "失败！";
			}
			conn.setAutoCommit(false);// 将自动提交设置为手动提交
			if (contractidlist.size() > 0) {
				for (Map<String, Object> restlt : contractidlist) {
					String selectSql = "select * from gwsmprod.turbineinfor where 风场编号 ='" + restlt.get("wind_machine")
							+ "'";
					ps = conn.prepareStatement(selectSql);
					String id = restlt.get("tpm_id").toString();
					String unitnum = restlt.get("unitnum").toString();
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						MachineMainData mmd = new MachineMainData();
						String jznumber = rs.getString("机组编号");
						if (!"0".equals(id)) {
							if (jznumber != null && jznumber.equals(unitnum)) {
								mmd = this.tableService.findEntityByID(MachineMainData.class, id);
								System.out.println(jznumber + "========" + unitnum);
								flag = true;
							} else {
								continue;
							}
						} else {
							flag = false;
						}
						System.out.println(unitnum + "----------->");
						mmd.setUnitNum(rs.getString("机组编号"));
						mmd.setWindFieldNum(rs.getString("风场编号"));
						mmd.setWindFieldName(rs.getString("风场名称"));
						mmd.setArea(rs.getString("片区"));
						mmd.setProjDept(rs.getString("项目部"));
						mmd.setProjManager(rs.getString("项目经理"));
						mmd.setMachineStatus(rs.getString("机组状态"));
						mmd.setMachineNum(rs.getString("运行机位号"));
						mmd.setMachineNumSCA(rs.getString("SCADA风机编号"));
						mmd.setFieldNumSCA(rs.getString("SCADA风场编号"));
						mmd.setUnitCapacity(rs.getString("机组容量"));
						mmd.setModel(rs.getString("机型"));
						mmd.setWheelHeight(rs.getString("轮毂高度"));
						mmd.setAddressOfIP(rs.getString("IP地址"));
						mmd.setSubnetMask(rs.getString("子网掩码"));
						mmd.setExcavationTime(rs.getString("基础开挖时间"));
						mmd.setCastingTime(rs.getString("基础浇筑时间"));
						mmd.setBoxInstallationTime(rs.getString("箱变安装时间"));
						mmd.setPickUpStartTime(rs.getString("接货开始时间"));
						mmd.setPickUpEndTime(rs.getString("接货结束时间"));
						mmd.setLiftingStartTime(rs.getString("吊装开始时间"));
						mmd.setLiftingEndTime(rs.getString("吊装结束时间"));
						mmd.setPowerOnTime(rs.getString("上电时间"));
						mmd.setStaticStartTime(rs.getString("静调开始时间"));
						mmd.setStaticEndTime(rs.getString("静调结束时间"));
						mmd.setMoveStartTime(rs.getString("动调开始时间"));
						mmd.setMoveEndTime(rs.getString("动调结束时间"));
						mmd.setCommissioningStime(rs.getString("试运行开始时间"));
						mmd.setCommissioningEtime(rs.getString("试运行结束时间"));
						mmd.setPreacceptanceTime(rs.getString("预验收通过时间"));
						mmd.setTurnUnderWarranty(rs.getString("在建转质保时间"));
						mmd.setMainProgramVnum(rs.getString("主控程序版本号"));
						mmd.setTransformerVnum(rs.getString("变流版本号"));
						mmd.setIsAnalyzedVnum(rs.getString("变桨版本号"));
						mmd.setInitFileVnum(rs.getString("初始化文件版本号"));
						mmd.setPropellerType(rs.getString("变桨类型"));
						mmd.setConverterType(rs.getString("变流类型"));
						mmd.setCold(rs.getString("冷却"));
						mmd.setLeafBlade(rs.getString("叶片"));
						mmd.setBusType(rs.getString("总线类型"));
						mmd.setTowerDrum(rs.getString("塔筒"));
						mmd.setBasicType(rs.getString("基础形式"));
						mmd.setTowerConnection(rs.getString("塔架连接形式"));
						mmd.setTowerDrawing(rs.getString("塔架图号"));
						mmd.setExpectWarrantyTime(rs.getString("预计出质保时间"));
						mmd.setFinHandEndTime(rs.getString("最终交接结束时间"));
						mmd.setHoistingStime(rs.getString("吊装过程验收开始时间"));
						mmd.setHoistingEtime(rs.getString("吊装过程验收结束时间"));
						mmd.setTorqueStime(rs.getString("力矩验收开始时间"));
						mmd.setTorqueEtime(rs.getString("力矩验收结束时间"));
						mmd.setWiringStime(rs.getString("接线开始时间"));
						mmd.setWiringEtime(rs.getString("接线结束时间"));
						mmd.setAllacceptanceStime(rs.getString("整体验收开始时间"));
						mmd.setAllacceptanceEtime(rs.getString("整体验收结束时间"));
						mmd.setInacceptanceStime(rs.getString("内部验收开始时间"));
						mmd.setInacceptanceEtime(rs.getString("内部验收结束时间"));
						mmd.setWillacceptanceStime(rs.getString("预验收开始时间"));
						mmd.setLasthandoverStime(rs.getString("最终交接开始时间"));
						mmd.setLastacceptanceStime(rs.getString("最终交接验收开始时间"));
						mmd.setLastacceptanceEtime(rs.getString("最终交接验收结束时间"));
						mmd.setLastWarrantyTime(rs.getString("最终出质保时间"));
						mmd.setSystCreateTime(rs.getString("系统创建时间"));
						if (flag) {// update
							listupdateData.add(mmd);
						} else {// save
							listsaveData.add(mmd);
						}

					}
				}
			}
			if (listsaveData.size() > 0) {
				this.tableService.saveAllEntities(listsaveData);
			}
			if (listupdateData.size() > 0) {
				this.tableService.updateAllEntities(listupdateData);
			}
			conn.commit();
			return "成功";
		} catch (SQLException e) {// 回滚事物
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return "失败";
			}
			e.printStackTrace();
			return "失败";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
		}
	}

	// 交接主数据
	public String updatePickupMainData() throws Exception {
		String sqlstr = "select distinct ci.wind_machine,nvl(tpm.id_,'0') tpm_id_,nvl(tpm.id,'0') tpm_idstring"
				+ " from contract_info ci left join T_PICKUP_MAIN_DATA tpm on ci.wind_machine=tpm.风电场编号 "
				+ "where ci.wind_machine is not null";
		List<Map<String, Object>> contractidlist = new ArrayList<Map<String, Object>>();// 用来存放风机号不为空的list
		try {
			contractidlist = this.tableService.queryListBySql(sqlstr, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<PickUpMainData> listsaveData = new ArrayList<PickUpMainData>();
		List<PickUpMainData> listupdateData = new ArrayList<PickUpMainData>();
		// 连接数据库
		BSDataBaseManager dbm = BSDataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;
		try {
			conn = dbm.getConnection();
			if (conn == null) {
				return "失败！";
			}
			conn.setAutoCommit(false);
			if (contractidlist.size() > 0) {
				for (Map<String, Object> restlt : contractidlist) {
					String selectSql = "select * from gwsmprod.goodreceive_view_Receiving where 风电场编号 ='"
							+ restlt.get("wind_machine") + "'";
					ps = conn.prepareStatement(selectSql);
					String id = restlt.get("tpm_id_").toString();
					String id_string = restlt.get("tpm_idstring").toString();
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						PickUpMainData pmd = new PickUpMainData();
						String NID = rs.getString("ID");
						if (!"0".equals(id)) {
							if (NID != null && NID.equals(id_string)) {
								pmd = this.tableService.findEntityByID(PickUpMainData.class, id);
								System.out.println(NID + "========" + id_string);
								flag = true;
							} else {
								continue;
							}
						} else {
							flag = false;
						}
						pmd.setId(rs.getString("ID"));
						pmd.setSysmodtime(rs.getString("SYSMODTIME"));
						pmd.setReservation(rs.getString("存放机位"));
						pmd.setLocation(rs.getString("存放位置"));
						pmd.setMaterialCode(rs.getString("物料编码"));
						pmd.setMaterialName(rs.getString("物料名称"));
						pmd.setBatchNum(rs.getString("批次号"));
						pmd.setS_id(rs.getString("序列号"));
						pmd.setAmount(rs.getString("数量"));
						pmd.setUnit(rs.getString("单位"));
						pmd.setReceivingNum(rs.getString("接货工单号"));
						pmd.setArea(rs.getString("片区"));
						pmd.setWindFieldNum(rs.getString("风电场编号"));
						pmd.setPickUpStartTime(rs.getString("接货开始时间"));
						pmd.setPickUpEndTime(rs.getString("接货结束时间"));
						if (flag) {// update
							listupdateData.add(pmd);
						} else {// save
							listsaveData.add(pmd);
						}

					}
				}
			}
			if (listsaveData.size() > 0) {
				this.tableService.saveAllEntities(listsaveData);
			}
			if (listupdateData.size() > 0) {
				this.tableService.updateAllEntities(listupdateData);
			}
			conn.commit();
			return "成功";
		} catch (SQLException e) {// 回滚事物
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return "失败";
			}
			e.printStackTrace();
			return "失败";
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
		}
	}

	@Override
	public void runOnhireRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE,CREATE_DATE,MSG_TYPE,FROMUSER, CREATOR_)"
				+ "select sys_guid(),'起租日提醒',"
				+ "'合同编号为'||ci.contract_number||'的'||ci.project_name||'的'||nvl(t.name_,'租金')||'将于'||ff.plan_date||'进行收款，请及时催收', "
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd'),to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1','Administrator','Administrator'"
				+ "from (select ffp.plan_date ,ffp.contract_id cd,ffp.fee_type ft from contract_fund_fund_plan ffp union all "
				+ "select frp.plan_date,frp.contract_id cd,'租金' ft from contract_fund_rent_plan frp )ff left join contract_info ci on ci.id = ff.cd "
				+ "LEFT JOIN T_DICTS_DATAS t on t.id_ = ff.ft where   not exists "
				+ "(select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),'合同编号为'||ci.contract_number||'的'||ci.project_name||'的'||nvl(t.name_,'租金'))>0)"
				+ "   and   to_char(sysdate+14,'yyyy-mm-dd') = ff.plan_date";
		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm"
				+ "  (ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)"
				+ "select sys_guid(),'1',bm.create_date,bm.id,dd.user_id_,bm.creator_ from  (select bb.* from BASE_MESSAGE bb left join contract_info ci  on "
				+ "instr(to_char(bb.subject_),'合同编号为'||ci.contract_number||'的'||ci.project_name)>0"
				+ "          where bb.msg_title = '起租日提醒'"
				+ "and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm "
				+ "left join ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_ where t.name_ = '资产管理部') dd   on   1=1";
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);

	}

	// 租赁登记提醒runRentNotice
	@Override
	public void runLeaseRegister() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE " + "  (ID," + "   MSG_TITLE," + "   SUBJECT_," + "   SEND_DATE,"
				+ "   CREATE_DATE," + "   MSG_TYPE," + "   FROMUSER," + "   CREATOR_)" + "  select sys_guid(),"
				+ "         '租赁登记提醒',"
				+ "      '融资租赁合同编号为'||ci.contract_number||'的'||ci.project_name||'合同签约流程已结束，请及时进行租赁登记，并将登记文件扫描件上传系统',"
				+ "       to_char(SYSTIMESTAMP, 'yyyy-mm-dd'),"
				+ "         to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss')," + "         'msgtype.1', "
				+ "         'Administrator'," + "         'Administrator'" + "    from contract_info ci"
				+ "    where   not exists"
				+ "   (select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),'融资租赁合同编号为'||ci.contract_number)>0)"
				+ "   and  ci.contract_status =21";
		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm"
				+ " (ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)"
				+ "select  sys_guid(), '1', bm.create_date, bm.id,dd.user_id_,bm.creator_ from  (select bb.* from BASE_MESSAGE bb "
				+ "          left join contract_info ci on instr(to_char(bb.subject_),'融资租赁合同编号为'||ci.contract_number)>0 "
				+ "         where bb.msg_title = '租赁登记提醒' "
				+ "          and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm  "
				+ "   left join  ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_ where t.name_ = '风险控制部') dd on   1=1";
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	// 租金支付通知书提醒--待续
	@Override
	public void runRentNotice() throws Exception {

	}

	@Override
	public void runOwnerShipTransfer() throws Exception {

		// TODO Auto-generated method stub
		String bm_sql = "insert into BASE_MESSAGE" + "  (ID," + "   MSG_TITLE," + "   SUBJECT_," + "   SEND_DATE,"
				+ "   CREATE_DATE," + "   MSG_TYPE," + "   FROMUSER," + "   CREATOR_)" + "  select sys_guid(),"
				+ "         '回租设备款投放完毕',"
				+ "      '您好：回租合同编号为'||ci.contract_number||'的'||ci.project_name||'的最后一笔设备款投放流程结束',"
				+ "       to_char(SYSTIMESTAMP, 'yyyy-mm-dd'),"
				+ "         to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss')," + "         'msgtype.1', "
				+ "         'Administrator'," + "         'Administrator'"
				+ "    from (select sum(cp.plan_money) planmoney," + "                 cc.contract_id contractid,"
				+ "                 sum(cc.fact_money) factmoney" + "            from contract_fund_fund_charge cc"
				+ "            left join contract_fund_fund_plan cp" + "              on cc.payment_id = cp.id"
				+ "           where cc.fee_type = 'feetype10'" + "           group by cc.contract_id) a"
				+ "            left join contract_info ci on a.contractid=ci.id   "
				+ "   where a.planmoney - a.factmoney = 0";
		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER"
				+ "  (ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)" + "  select sys_guid(),"
				+ "         '1'," + "         bm.create_date," + "         bm.id,"
				+ "         '402880ad58f1f5ad0158f20acc920032'," + "         bm.creator_" + "    from BASE_MESSAGE bm"
				+ "   where not exists" + "   (select 1 from BASE_MESSAGE_TO_USER b where b.msg_id = bm.id)";
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	// 收款日提醒
	@Override
	public void runReceiveDate() throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmReceiveDate.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserReceiveDate.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	// 合同履行时关联客户、项目、合同提醒
	@Override
	public void runRelationCustProjRemind() throws Exception {

		String bm_sql = "insert into BASE_MESSAGE (ID, MSG_TITLE,SUBJECT_,SEND_DATE,CREATE_DATE,MSG_TYPE,FROMUSER,CREATOR_)"
				+ " select sys_guid(), '并网发电后制作相关协议提醒',"
				+ "'客户名称为'||ff.CUST_NAME||'的'||ff.proj_name||'已经并网发电，请制作电费收费账户资金监管协议、应收账款质押协议！', "
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd'), to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1','Administrator','Administrator'"
				+ " from (select * from PROJ_DEVELOPE_INFO pdi left join  cust_info ci on  ci.id=pdi.cust_id ) ff"
				+ " where ff.project_phase='project_phase.06' and not exists"
				+ "  (select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),'客户名称为'||ff.CUST_NAME||'的'||ff.proj_name||'已经并网发电')>0)";

		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)"
				+ "select  sys_guid(), '1',bm.create_date,bm.id,dd.user_id_,bm.creator_ "
				+ "from  (select bb.* from BASE_MESSAGE bb "
				+ "    left join PROJ_DEVELOPE_INFO pdi on instr(to_char(bb.subject_), pdi.proj_name||'已经并网发电')>0"
				+ "    where bb.msg_title = '并网发电后制作相关协议提醒'"
				+ "    and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm left join  "
				+ "( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_ where t.name_ = '风险控制部') dd   on   1=1";
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);

	}

	@Override
	public void runPledgeAskingRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE, SUBJECT_,SEND_DATE,CREATE_DATE,MSG_TYPE,FROMUSER, CREATOR_)"
				+ "select sys_guid(),'股权质押的咨询','股权质押合同编号为'||cns.contract_number||'的'||ci.project_name||'需要进行股权质押的咨询',"
				+ " to_char(SYSTIMESTAMP, 'yyyy-mm-dd'),to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1', 'Administrator','Administrator'"
				+ "from contract_info ci left join contract_number_setting  cns on ci.id =cns.contract_id"
				+ " where cns.contract_type='CONTRACT_TYPE.04'and  not exists"
				+ "  (select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),'股权质押合同编号为'||cns.contract_number||'的'||ci.project_name||'需要进行股权质押的咨询')>0)"
				+ "   and ci.contract_status=21";
		String bmtouser_sql = " insert into BASE_MESSAGE_TO_USER bm (ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)"
				+ " select sys_guid(), '1',bm.create_date,bm.id,dd.user_id_,bm.creator_ from  (select bb.* from BASE_MESSAGE bb "
				+ "left join contract_info ci on instr(to_char(bb.subject_),'的'||ci.project_name||'需要进行股权质押的咨询')>0 "
				+ "where bb.msg_title = '股权质押的咨询'"
				+ "and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm "
				+ " left join  ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_ where t.name_ = '风险控制部') dd    on   1=1";

		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);

	}

	@Override
	public void runPledgeRegisterRemind() throws Exception {
		// TODO Auto-generated method stub
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE, CREATE_DATE, MSG_TYPE,FROMUSER,CREATOR_)"
				+ "select sys_guid(), '抵质押登记提醒','合同编号为'||ff.contract_number||'的'||ff.project_name||'需要重新办理抵制押登记', "
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd'), to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1', 'Administrator', "
				+ "'Administrator'from( select * from  pledge pl left join  contract_info  ci on ci.id =pl.contract_id) ff "
				+ " where  to_char(add_months(sysdate,1),'yyyy-mm-dd')=ff.PLEDGE_END   and  "
				+ " not exists (select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_), '合同编号为'||ff.contract_number||'的'||ff.project_name||'需要重新办理抵制押登记')>0)";
		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)"
				+ "select sys_guid(), '1',bm.create_date,bm.id,dd.user_id_,bm.creator_ from  (select bb.* from BASE_MESSAGE bb "
				+ "left join contract_info ci  on instr(to_char(bb.subject_),'合同编号为'||ci.contract_number||'的'||ci.project_name||'需要重新办理抵制押登记')>0"
				+ "where bb.msg_title = '抵质押登记提醒'and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm "
				+ "left join   ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_ where t.name_ = '风险控制部') dd   on   1=1";
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	@Override
	public void runProjFinish() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE,CREATE_DATE,MSG_TYPE,FROMUSER,CREATOR_)"
				+ " select sys_guid(), '项目竣工后提醒', '合同编号为'||ff.contract_id||'的'||ff.project_name||'的竣工，请制作抵押合同！',"
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd'),to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1','Administrator','Administrator'"
				+ "from (select cf.cust_name,ci.project_name,cp.* from CONTRACT_PATROL_INFO cp left join contract_info ci on ci.id=cp.contract_id "
				+ "left join cust_info cf on cf.id=cp.cust_id) ff where sysdate >=to_date(ff.completion_time,'yyyy-mm-dd') and not exists"
				+ "   (select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),'合同编号为'||ff.contract_id||'的'||ff.project_name||'的竣工，请制作抵押合同！')>0)";

		String bmourser_sql = "insert into BASE_MESSAGE_TO_USER bm (ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)"
				+ "select sys_guid(), '1', bm.create_date, bm.id,dd.user_id_,bm.creator_"
				+ "from  (select bb.* from BASE_MESSAGE bb "
				+ "left join contract_info ci  on instr(to_char(bb.subject_),'合同编号为'||ci.contract_id||'的'||ci.project_name||'的竣工，请制作抵押合同！')>0"
				+ "where bb.msg_title = '项目竣工后提醒'"
				+ "and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm left join "
				+ "( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_ where t.name_ = '风险控制部') dd   on   1=1";
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmourser_sql);

	}

	@Override
	public void runLandCertificateRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE,CREATE_DATE,MSG_TYPE, FROMUSER,CREATOR_)"
				+ " select sys_guid(),'土地证提醒制作抵押合同',"
				+ " '合同编号为'||ff.contract_id||'的'||ff.project_name||'有土地证，请制作抵押合同！', "
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd'),to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),"
				+ "'msgtype.1',  'Administrator', 'Administrator' from ("
				+ "select cf.cust_name,ci.project_name,cp.* from CONTRACT_PATROL_INFO cp "
				+ "left join contract_info ci on ci.id=cp.contract_id left join cust_info cf on cf.id=cp.cust_id ) ff where "
				+ "ff.if_have='是' and not exists"
				+ "(select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),'合同编号为'||ff.contract_id||'的'||ff.project_name||'有土地证，请制作抵押合同！')>0)";

		String bmourser_sql = "insert into BASE_MESSAGE_TO_USER bm"
				+ "(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)"
				+ "select  sys_guid(),'1',bm.create_date,bm.id,dd.user_id_, bm.creator_ from  (select bb.* from BASE_MESSAGE bb "
				+ "left join contract_info ci  on instr(to_char(bb.subject_),'合同编号为'||ci.contract_id||'的'||ci.project_name||'有土地证，请制作抵押合同！')>0"
				+ "where bb.msg_title = '土地证提醒制作抵押合同'and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm "
				+ "left join      ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_ where t.name_ = '风险控制部') dd  on   1=1";
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmourser_sql);

	}

	@Override
	public void runMortgageContractRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE, CREATE_DATE, MSG_TYPE,FROMUSER,CREATOR_)"
				+ "select sys_guid(),'抵押合同制作提醒','客户名称为'||vi.custname||'的'||vi.projname||'的项目'||'需要制作抵押合同',to_char(SYSTIMESTAMP, 'yyyy-mm-dd'), "
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1', 'Administrator', 'Administrator' from "
				+ "(select * from  VI_PROJ_DEVELOPE_INFO where projectphase='project_phase.03') vi "
				+ "where not exists (select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_), "
				+ "'客户名称为'||vi.custname||'的'||vi.projname||'的项目'||'需要制作抵押合同')>0)";

		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_)"
				+ "select sys_guid(), '1',bm.create_date,bm.id,dd.user_id_,bm.creator_ from  "
				+ "(select bb.* from BASE_MESSAGE bb where bb.msg_title = '抵押合同制作提醒'and bb.id not in "
				+ "(select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm left join  "
				+ " ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_ where t.name_ = '风险控制部') dd   on   1=1";

		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	@Override
	public void runJieXiRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE, CREATE_DATE, MSG_TYPE,FROMUSER,CREATOR_) "
				+ "select sys_guid(),'结息提醒','贷款合同号'||ff.loan_contract_id||'摘要为'||ff.plan_list||'起息日为'||ff.plan_date||'的这笔需要结息',"
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd'), to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1', 'Administrator','Administrator' from "
				+ "(select la.loan_contract_id,lrp.plan_list,lrp.plan_date,lrp.ypay_date from LOAN_RENT_Plan_TO_INTEREST lrp left join loan_account la on la.id=lrp.loan_id "
				+ "where to_char(sysdate+7,'yyyy-mm-dd')=lrp.ypay_date) ffwhere 1=1 and not exists (select 1 from BASE_MESSAGE b "
				+ "where  instr(to_char(b.subject_),'贷款合同号'||ff.loan_contract_id||'摘要为'||ff.plan_list||'起息日为'||ff.plan_date||'的这笔需要结息' )>0)";

		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_) "
				+ "select sys_guid(), '1',bm.create_date,bm.id,dd.user_id_,bm.creator_ from  (select bb.* from BASE_MESSAGE bb where bb.msg_title = '结息提醒' "
				+ "and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm left join   ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_"
				+ " where t.name_ = '金融部') dd   on   1=1";

		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	@Override
	public void runLoanInvoicedRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE, CREATE_DATE, MSG_TYPE,FROMUSER,CREATOR_) "
				+ "select sys_guid(),'借款利息发票开具提醒','贷款合同号'||ff.loan_contract_id||'摘要为'||ff.plan_list||'止息日为'||ff.ypay_date||'的这笔需要结息',"
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd'), to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1', 'Administrator','Administrator' from "
				+ "(select la.loan_contract_id,lrp.plan_list,lrp.plan_date,lrp.ypay_date from LOAN_RENT_Plan_TO_INTEREST lrp left join loan_account la on la.id=lrp.loan_id "
				+ "where to_char(sysdate+7,'yyyy-mm-dd')=lrp.ypay_date) ff where 1=1 and not exists (select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),"
				+ "'贷款合同号'||ff.loan_contract_id||'摘要为'||ff.plan_list||'止息日为'||ff.ypay_date||'的这笔需要结息' )>0)";

		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_) "
				+ "select sys_guid(), '1',bm.create_date,bm.id,dd.user_id_,bm.creator_ from  (select bb.* from BASE_MESSAGE bb where bb.msg_title = '借款利息发票开具提醒' "
				+ "and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm left join   ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_"
				+ " where t.name_ = '金融部') dd   on   1=1";

		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	@Override
	public void runRentPatrolRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE, CREATE_DATE, MSG_TYPE,FROMUSER,CREATOR_) "
				+ "select sys_guid(),'租后巡视提醒','本季度项目需要进行租后巡视'||'',to_char(SYSTIMESTAMP, 'yyyy-mm-dd'), to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),"
				+ "'msgtype.1', 'Administrator','Administrator'from dual     where   not exists (select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),"
				+ "'本季度项目需要进行租后巡视' )>0)";

		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_) select sys_guid(), '1',"
				+ "bm.create_date,bm.id,dd.user_id_,bm.creator_ from  (select bb.* from BASE_MESSAGE bb where bb.msg_title = '租后巡视提醒'and bb.id not in "
				+ "(select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm left join   ( select d.user_id_ from t_users_depts d left join t_depts t on d.dept_id_ = t.id_"
				+ " where t.name_ = '资产管理部') dd   on   1=1";

		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	@Override
	public void runCollectionDateRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE, CREATE_DATE, MSG_TYPE,FROMUSER,CREATOR_) select sys_guid(),'收款日提醒'，'合同号为'||ff.contract_id||'的'||ff.project_name||'需要收款'||PLANMONEY||'('||ff.FEETYPENAME||')',"
				+ "to_char(SYSTIMESTAMP, 'yyyy-mm-dd'), to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1',ff.proj_manage,'Administrator' from (select frp.id,frp.FEETYPENAME,frp.PLANMONEY,ci.project_name,ci.proj_manage,ci.contract_id,"
				+ "ci.proj_id,hi.holiday_,hi.workday_，nvl2(hi.holiday_,hi.workday_,frp.plandate) as reminddate,frp.plandate from vi_contract_fund_fund_plan  frp left join  HOLIDAY_INFO hi  on hi.holiday_ = frp.plandateleft join contract_info ci "
				+ "on ci.id=frp.contractid where frp.paytype='pay_type_in' union all select frp.id,'租金' ,frp.rent,ci.project_name,ci.proj_manage,ci.contract_id,ci.proj_id,hi.holiday_,hi.workday_，nvl2(hi.holiday_,hi.workday_,frp.plandate) as reminddate,"
				+ "frp.plandate from vi_contract_fund_rent_plan  frp left join  HOLIDAY_INFO hi  on hi.holiday_ = frp.plandateleft join contract_info ci on ci.id=frp.contractid ) ff where  to_char(sysdate+2,'yyyy-mm-dd')=ff.reminddate   and   not exists "
				+ "(select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_),'收款日提醒'，'合同号为'||ff.contract_id||'的'||ff.project_name||'需要收款'||PLANMONEY||'('||ff.FEETYPENAME||')' )>0)";

		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_) "
				+ "select sys_guid(), '1',bm.create_date,bm.id,bm.fromuser,bm.creator_ from  (select bb.* from BASE_MESSAGE bb where bb.msg_title = '收款日提醒' "
				+ "and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu) and (select id from t_users) =bb.fromuser) bm ";

		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	@Override
	public void runRegistrationChangeRemind() throws Exception {
		String bm_sql = "insert into BASE_MESSAGE (ID,MSG_TITLE,SUBJECT_,SEND_DATE, CREATE_DATE, MSG_TYPE,FROMUSER,CREATOR_) "
				+ "select sys_guid(),'注册信息变更提醒','合同号为'||ff.contract_id||'的'||ff.project_name||'的客户注册信息变更',to_char(SYSTIMESTAMP, 'yyyy-mm-dd'),"
				+ " to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'),'msgtype.1', 'Administrator', 'Administrator'from(select ci.project_name,ci.contract_id "
				+ "from CONTRACT_PATROL_INFO cpi left join contract_info ci on ci.id=cpi.contract_id where cpi.if_change='1' ) ff where not exists "
				+ "(select 1 from BASE_MESSAGE b where  instr(to_char(b.subject_), '合同号为'||ff.contract_id||'的'||ff.project_name||'的客户注册信息变更')>0)";

		String bmtouser_sql = "insert into BASE_MESSAGE_TO_USER bm(ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_) "
				+ "select sys_guid(), '1',bm.create_date,bm.id,FROMUSER,bm.creator_ from  (select bb.* from BASE_MESSAGE bb where bb.msg_title = '注册信息变更提醒' "
				+ "and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm left join ( select d.user_id_ from t_users_depts d left join t_depts t "
				+ "on d.dept_id_ = t.id_ where t.name_ = '集团审计法务部') dd   on   1=1";

		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	@Override
	public void runZuhouXunShiRemind() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmZuHouXunShi.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserZuHouXunShi.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);

	}

	// 投保提醒
	@Override
	public void runTouBao() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmTouBao.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserTouBao.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	// 保险到期提醒
	@Override
	public void runBaoXiDaoQi() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBaoXiDaoQi.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBaoXiDaoQi.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	// 租前息盖章提醒
	@Override
	public void runZuQianXiGaiZhang() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmZuQianXiGaiZhang.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserZuQianXiGaiZhang.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	// 保函到期提醒
	@Override
	public void runBaoHanDaoQi() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBaoHanDaoQi.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBaoHanDaoQi.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	// 面签提醒发送资料清单
	@Override
	public void runMianQian() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmMianQian.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserMianQian.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
	}

	@Override
	public void runBuLiangZiChan() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong1.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong1.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong2() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong2.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong2.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong3() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong3.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong3.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong4() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong4.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong4.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong5() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong5.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong5.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong6() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong6.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong6.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong7() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong7.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong7.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong8() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong8.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong8.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong9() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong9.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong9.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong10() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong10.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong10.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong11() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong11.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong11.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong12() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong12.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong12.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong13() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong13.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong13.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong14() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong14.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong14.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}

	@Override
	public void runBeiYong15() throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String bm_sql = "";
		String bmtouser_sql = "";
		bm_sql = this.tableService.getTableXMLJsonData("quartz/bmBeiYong15.xml", model);
		bmtouser_sql = this.tableService.getTableXMLJsonData("quartz/bmtouserBeiYong15.xml", model);
		reportDao.getJdbcTemplate().execute(bm_sql);
		reportDao.getJdbcTemplate().execute(bmtouser_sql);
		
	}
	
}
