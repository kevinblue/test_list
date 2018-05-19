package com.tenwa.report.controller;

import static com.tenwa.kernal.utils.StringUtil.nullToString;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.MutablePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tenwa.business.entity.TableExportLog;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.TableExportLog.ExportType;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.entity.Report;
import com.tenwa.report.entity.ReportChart;
import com.tenwa.report.entity.ReportColumn;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportLayout;
import com.tenwa.report.entity.ReportPage;
import com.tenwa.report.entity.ReportPowerControl;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.entity.ReportTableLazy;
import com.tenwa.report.enums.FilterType;
import com.tenwa.report.enums.HtmlType;
import com.tenwa.report.export.ExcelExportService;
import com.tenwa.report.export.ExportService;
import com.tenwa.report.formatter.ChartFormatter;
import com.tenwa.report.formatter.DefaultTableFormatter;
import com.tenwa.report.formatter.FunsionChartFormatter;
import com.tenwa.report.formatter.TableFormatter;
import com.tenwa.report.query.CellData;
import com.tenwa.report.query.QueryFactory;
import com.tenwa.report.query.QueryRestriction;
import com.tenwa.report.query.QueryService;
import com.tenwa.report.service.ComboboxService;
import com.tenwa.report.service.ReportService;
import com.tenwa.report.util.DES;

@Controller("reportController")
@RequestMapping(value = "/**/report")
public class ReportController {
	private static final Logger log = LoggerFactory.getLogger(ReportController.class);

	@Resource(name = "queryFactory")
	private QueryFactory queryFactory;

	@Resource(name = "reportService")
	private ReportService reportService;

	@Resource(name = "comboboxService")
	private ComboboxService comboboxService;

	@Resource(name = "tableService")
	private TableService tableService;

	@RequestMapping(value = "/showReport.action")
	public ModelAndView showReport(HttpServletRequest request, @RequestParam String reportId) throws Exception {
		Report report = (Report) this.reportService.findEntityByID(Report.class, reportId);
		
		ModelAndView mv = new ModelAndView("/solutions/report/report.jsp");
		
		Set<ReportLayout> layout = report.getLayout();

		mv.addObject("layouts", layout);

		mv.addObject("reportName", report.getName());

		return mv;
	}

	@RequestMapping("/showTable.action")
	public ModelAndView showTable(HttpServletRequest request, @RequestParam String id, @RequestParam(required = false) String needjs,
			@RequestParam(required = false) String divWidth, @RequestParam(required = false) String divHeight, @RequestParam(required = false) String divCount) throws Exception {

		
		ModelAndView mv = new ModelAndView("/solutions/report/table.jsp");
		mv.addObject("height", Integer.parseInt(nullToString(divHeight, "0")));
		mv.addObject("width", Integer.parseInt(nullToString(divWidth, "0")));
		mv.addObject("divCount", Integer.parseInt(nullToString(divCount, "1")));
		if (needjs == null || "".equals(needjs))
			needjs = "1";
		mv.addObject("needjs", needjs);
		mv.addObject("id", id);

		ReportTable table = (ReportTable) this.reportService.findEntityByID(ReportTable.class, id);

		mv.addObject("isExcel", "true");
		if(table.getIsExcel()){
			mv.addObject("isExcel", "false");
			User currentUser = SecurityUtil.getPrincipal();
			String queryControlHql = "From com.tenwa.report.entity.ReportPowerControl where reportId = ? and PowerPerson = ?";
			List<ReportPowerControl> powers =  this.tableService.findResultsByHSQL(queryControlHql, table,currentUser);
			if(null != powers && 0 < powers.size()){
				mv.addObject("isExcel", "true");
			}
		}
		mv.addObject("pageSize", table.getPageSize());
		mv.addObject("title", WebUtil.isEnglish() ? table.getEnname() : table.getName());
		mv.addObject("searchs", table.getSearch());
		mv.addObject("isScale", table.getIsScale());

		mv.addObject("filters", table.getFilter());

		return mv;
	}

	@RequestMapping("/showPage.action")
	public ModelAndView showPage(HttpServletRequest request, @RequestParam String id, @RequestParam(required = false) String needjs,
			@RequestParam(required = false) String divWidth, @RequestParam(required = false) String divHeight, @RequestParam(required = false) String divCount, @RequestParam(required = false) String actionParametersName, @RequestParam(required = false) String actionParameters) throws Exception {

		ModelAndView mv = new ModelAndView("/solutions/report/page.jsp");
		mv.addObject("height", Integer.parseInt(nullToString(divHeight, "0")));
		mv.addObject("width", Integer.parseInt(nullToString(divWidth, "0")));
		mv.addObject("divCount", Integer.parseInt(nullToString(divCount, "1")));
		if (needjs == null || "".equals(needjs))
			needjs = "1";
		mv.addObject("needjs", needjs);
		mv.addObject("id", id);

		ReportPage page = this.reportService.findEntityByID(ReportPage.class, id);
		mv.addObject("pageUrl",page.getUrl());
		
		mv.addObject("actionParametersName",actionParametersName);
		mv.addObject("actionParameters",actionParameters);
		mv.addObject("title",page.getName());
		return mv;
	}

	@RequestMapping("/getHeader.action")
	@ResponseBody
	public String getHeader(@RequestParam String id, @RequestParam Integer pageWidth) throws Exception {
		ReportTable table = (ReportTable) this.reportService.findEntityByID(ReportTable.class, id);
		// JSONObject jsResult = new JSONObject();

		Set<ReportColumn> columns = table.getReportColumns();
		JSONArray columnJson = new JSONArray();

		int totalWidth = 0;
		if (table.getIsScale()) {
			for (ReportColumn column : columns) {
				totalWidth += Integer.parseInt(nullToString(column.getWidth(), "100"));
			}
		}

		if (table.getShowRowNumber() || table.getShowTotalTitle()) {
			totalWidth -= 30;
			JSONObject columnInfo = new JSONObject();
			columnInfo.put("header", "&nbsp;");
			columnInfo.put("name", QueryService._TENWA_ROWNUMBER);
			columnInfo.put("field", QueryService._TENWA_ROWNUMBER);
			if (table.getIsScale()) {
				columnInfo.put("width", String.valueOf((30.0 / pageWidth) * 100.0) + "%");
			} else {
				columnInfo.put("width", 30);
			}
			columnInfo.put("hidden", false);
			columnJson.put(columnInfo);
		}

		for (ReportColumn column : columns) {
			JSONObject columnInfo = new JSONObject();
			if(WebUtil.isEnglish()){
				columnInfo.put("header", nullToString(column.getEnLabel()).equals("") ? nullToString(column.getName()) : nullToString(column.getEnLabel()));
			}else{
				columnInfo.put("header", nullToString(column.getLabel()));
			}
			columnInfo.put("name", nullToString(column.getName()));
			columnInfo.put("field", nullToString(column.getName()));
			int width = Integer.parseInt(nullToString(column.getWidth(), "100"));
			if (table.getIsScale()) {
				columnInfo.put("width", String.valueOf(((width * 100) / totalWidth)) + "%");
			} else {
				columnInfo.put("width", width);
			}
			//add  by zhanc
			columnInfo.put("visible", column.getIsVisible());
			if(column.getAlignType() != null){
				columnInfo.put("align", column.getAlignType().getAlign());
			}
			
			switch (column.getColumnDataType()) {
			case DATE:
				//dateFormat : "yyyy-MM-dd HH:mm:ss",
				columnInfo.put("dateFormat", column.getFormater());	
				columnInfo.put("autoEscape", false);	
				break;

			default:
				break;
			}
			columnJson.put(columnInfo);
		}
		// jsResult.put("columns", columnJson);
		return columnJson.toString();
	}

	@RequestMapping("/getCombobox.action")
	@ResponseBody
	public String getCombobox(@RequestParam String tableId, @RequestParam String filterId) throws Exception {
		return this.comboboxService.getComboboxJson(tableId, filterId).toString();
	}

	@RequestMapping("/loadTableData2.action")
	@ResponseBody
	public String loadTableData2(@RequestParam String id, @RequestParam(required = false) Integer start, @RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) Integer totalCount, @RequestParam(required = false) String paramValue, @RequestParam(required = false) String TableRemoteSortField)
			throws JSONException, BusinessException {
		Integer limit = pageSize;
		JSONObject queryResult = new JSONObject();
		QueryService queryService = null;
		try {
			ReportTable table = (ReportTable) this.reportService.findEntityByID(ReportTable.class, id);
			String sqlCache = table.getSql(); 
			if(null != table.getIsCache() && table.getIsCache()){
				//缓存表是否存在
				String Hql = "from ReportTableLazy where reportId = ?";
				List<ReportTableLazy> lazys = this.tableService.findResultsByHSQL(Hql, table);
				if(null != lazys && 0 < lazys.size()){
					table.setSql("select * from "+lazys.get(0).getLazyTableName());
				}
			}

			queryService = this.queryFactory.getService(table.getQueryType());

			queryService.initQuery(table.getId(), table.getReportDataSource(), table.getSql(), table.getQueryType(), table.getReportColumns(), SecurityUtil.getPrincipal());

			queryService.addRestrction(QueryRestriction.PACKAGE_NOT_GENERATE_HTML, false);
			queryService.addRestrction(QueryRestriction.PACKAGE_NOT_SHOW_ROW_NUMBER, false);
			queryService.addRestrction(QueryRestriction.TABLE_SHOW_ROW_NUMBER, table.getShowRowNumber());
			queryService.addRestrction(QueryRestriction.TABLE_SHOW_TOTAL_TITLE, table.getShowTotalTitle());
			queryService.addRestrction(QueryRestriction.NO_SUM, false);

			if (start == null)
				start = 0;
			if (limit == null || limit == 0)
				limit = 20;

			start = start * limit;
			queryService.setExternOrder(TableRemoteSortField);

			List<MutablePair<ReportFilter, String>> params = this.getParams(paramValue);
			List<Map<String, CellData>> datas = queryService.getTableData(params, start, limit,table);

			if (queryService.isQueryStart(start) || totalCount == null) {
				totalCount = queryService.getTotalCount(params,table);
			}

			TableFormatter formatter = new DefaultTableFormatter();
			formatter.setDataSet(datas);
			if(null != table.getIsCache() && table.getIsCache()){
				table.setSql(sqlCache);
			}
			formatter.setTable(table);
			formatter.setGenerateHtml(true);
			formatter.setTotalCount(totalCount);
			queryResult = formatter.generateJson();
			
		} catch (Exception e) {
			log.error("{}", e);
			queryResult.put("code", e.getMessage());
			throw new BusinessException(e);
		}
		return queryResult.toString();
	}

	@RequestMapping("/loadChartData.action")
	@ResponseBody
	public String loadChartData(HttpServletRequest request,HttpServletResponse response) throws JSONException, BusinessException {

		JSONObject jsonChart = new JSONObject();
		QueryService queryService = null;
		try {
			String id = request.getParameter("id");
			String paramValue = request.getParameter("paramValue") == null ? "" : request.getParameter("paramValue");
			paramValue = DES.decrypt(paramValue ,"4860B1E27","7464DB1C804E","426A2405196");
			
			ReportChart chart = this.reportService.findEntityByID(ReportChart.class, id);

			queryService = this.queryFactory.getService(chart.getQueryType());

			queryService.initQuery(chart.getId(), chart.getReportDataSource(), chart.getQuery(), chart.getQueryType(), chart.getReportColumns(), SecurityUtil.getPrincipal());

			queryService.addRestrction(QueryRestriction.PACKAGE_NOT_GENERATE_HTML, true);
			queryService.addRestrction(QueryRestriction.PACKAGE_NOT_SHOW_ROW_NUMBER, true);
			queryService.addRestrction(QueryRestriction.TABLE_SHOW_ROW_NUMBER, false);
			queryService.addRestrction(QueryRestriction.TABLE_SHOW_TOTAL_TITLE, false);
			queryService.addRestrction(QueryRestriction.NO_SUM, true);
			List<MutablePair<ReportFilter, String>> params = this.getParams( URLDecoder.decode(paramValue, "UTF-8"));

			List<Map<String, CellData>> datas = queryService.getChartData(params);// queryService.getTableData(params, start, limit);

			ChartFormatter formatter = new FunsionChartFormatter();
			formatter.setChart(chart);
			formatter.setDataSet(datas);

			jsonChart = formatter.generateJson();

		} catch (Exception e) {
			log.error("{}", e);
			jsonChart.put("code", e.getMessage());
			throw new BusinessException(e);
		}

		return jsonChart.toString();
	}

	@RequestMapping(value = "/getXmlCombo.action")
	@ResponseBody
	public String getXmlCombo(HttpServletRequest request, HttpServletResponse response) {

		try {
			Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
			String xmlFileNameOrPath = model.get("xmlFileName");
			String jsonData = this.tableService.getTableJsonData(xmlFileNameOrPath, model);
			JSONObject jsonObject = new JSONObject(jsonData);
			JSONArray jsonDatas = jsonObject.getJSONArray("datas");

			return jsonDatas.toString();
		} catch (JSONException e) {
			log.error("", e);
		} catch (Exception e) {
			log.error("", e);
		}

		return "";
	}

	@RequestMapping("/showChart.action")
	public ModelAndView showChart(HttpServletRequest request, @RequestParam String id, @RequestParam(required = false) String needjs,
			@RequestParam(required = false) String divWidth, @RequestParam(required = false) String divHeight, @RequestParam(required = false) String divCount) throws Exception {

		ModelAndView mv = new ModelAndView("/solutions/report/chart.jsp");
		mv.addObject("height", Integer.parseInt(nullToString(divHeight, "0")));
		mv.addObject("width", Integer.parseInt(nullToString(divWidth, "0")));
		mv.addObject("divCount", Integer.parseInt(nullToString(divCount, "1")));
		if (needjs == null || "".equals(needjs))
			needjs = "1";
		mv.addObject("needjs", needjs);
		mv.addObject("id", id);

		ReportChart chart = this.reportService.findEntityByID(ReportChart.class, id);
		mv.addObject("title", chart.getName());
		mv.addObject("searchs", chart.getSearch());
		mv.addObject("filters", chart.getFilter());
		mv.addObject("swfName", chart.getFusionChart().getSwfName());
		return mv;
	}

	/**
	 * 加入用户信息，用于权限过滤
	 * 
	 * @param params
	 * @return
	 */
	private List<MutablePair<ReportFilter, String>> addUserInformation(List<MutablePair<ReportFilter, String>> params) {
		User user = SecurityUtil.getPrincipal();
		ReportFilter filter = new ReportFilter();
		filter.setComboBoxDataSource(null);
		filter.setComboBoxNameField(null);
		filter.setFilterDataRequestType(null);
		filter.setFilterType(FilterType.SEARCH);
		filter.setHtmlType(HtmlType.TEXT);
		filter.setName("_TENWA_USERID");
		MutablePair<ReportFilter, String> userIdInfo = new MutablePair<ReportFilter, String>();
		userIdInfo.setLeft(filter);
		userIdInfo.setRight(user.getId());

		params.add(userIdInfo);
		return params;
	}

	private List<MutablePair<ReportFilter, String>> getParams(String paramValue) throws Exception {

		List<MutablePair<ReportFilter, String>> params = new ArrayList<MutablePair<ReportFilter, String>>();

		if (!"".equals(nullToString(paramValue))) {
			JSONArray jsonParams = new JSONArray(paramValue);
			for (int i = 0; i < jsonParams.length(); i++) {
				JSONObject jsonParam = (JSONObject) jsonParams.get(i);

				if (!"".equals(jsonParam.getString("value")) && !"|".equals(jsonParam.getString("value"))) {
					MutablePair<ReportFilter, String> pair = new MutablePair<ReportFilter, String>();
					ReportFilter filter = (ReportFilter) this.reportService.findEntityByID(ReportFilter.class, jsonParam.getString("id"));
					pair.setLeft(filter);
					pair.setRight(jsonParam.getString("value"));
					params.add(pair);
				}
			}
		}

		params = addUserInformation(params);
		return params;
	}

	@RequestMapping(value = "/exportExcel.action")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String reportName, @RequestParam String exportParams,
			@RequestParam String browser) throws Exception {
		log.info(DateUtil.getSystemDateTime());
		TableExportLog logExport = new TableExportLog();
		Long startTime =  System.currentTimeMillis();
		String logContent = "";
		
		JSONArray exportParamJson = new JSONArray(exportParams);
		ExportService exportService = new ExcelExportService();
		String fileName = reportName;
		logContent = "导出模块："+fileName;
		for (int k = 0; k < exportParamJson.length(); k++) {

			JSONObject paramObject = exportParamJson.getJSONObject(k);
			String id = nullToString(paramObject.getString("id"));
			if ("".equals(id))
				continue;
			String query = "";
			try {
				query = paramObject.getString("queryParams");
				query = query.replaceAll("\\\"", "\"");
			} catch (JSONException e) {
				query = "";
			}

			ReportTable table = (ReportTable) this.reportService.findEntityByID(ReportTable.class, id);
			List<MutablePair<ReportFilter, String>> params = getParams(query);

			QueryService queryService = null;
			queryService = this.queryFactory.getService(table.getQueryType());

			queryService.initQuery(table.getId(), table.getReportDataSource(), table.getSql(), table.getQueryType(), table.getReportColumns(), SecurityUtil.getPrincipal());

			queryService.addRestrction(QueryRestriction.PACKAGE_NOT_GENERATE_HTML, true);
			queryService.addRestrction(QueryRestriction.PACKAGE_NOT_SHOW_ROW_NUMBER, true);
			queryService.addRestrction(QueryRestriction.TABLE_SHOW_ROW_NUMBER, table.getShowRowNumber());
			queryService.addRestrction(QueryRestriction.TABLE_SHOW_TOTAL_TITLE, table.getShowTotalTitle());
			queryService.addRestrction(QueryRestriction.NO_SUM, false);

			int limit = queryService.getTotalCount(params);
			List<Map<String, CellData>> datas = queryService.getTableData(params, 0, limit,table);
			exportService.addTableData(table, datas);
			if (fileName == null || "".equals(fileName)) {
				fileName = table.getName();
			}
			logContent += ";导出sql:"+table.getSql();
			logExport.setExportDataSize(Long.parseLong(datas.size()+"") );
		}

		fileName = QueryUtil.getFilenameAssociateBrowser(browser, fileName);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		fileName += ("_" + f.format(Calendar.getInstance().getTime()));
		// fileName +=
		exportService.doExport(response, fileName);
		Long endTime =  System.currentTimeMillis();
		logExport.setExportTime(endTime - startTime);
		logExport.setExportType(ExportType.REPORT);
		logExport.setContent(logContent);
		logExport.setOperateUser(SecurityUtil.getPrincipal());
		logExport.setTime(DateUtil.getSystemDateTime());
		String isEntityLog =  ResourceUtil.getConfigValue("system.framework.entitylog");
		if(isEntityLog != null && isEntityLog.equals("true")){
			this.tableService.saveEntity(logExport);
		}
		log.info(DateUtil.getSystemDateTime());
	}

}
