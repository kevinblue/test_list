package com.tenwa.business.serviceImpl;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.reckon.commons.util.DateTools;
import com.tenwa.business.callback.EntityBeanCallBack;
import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.dao.TableDao;
import com.tenwa.business.entity.TableExportLog;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.TableExportLog.ExportType;
import com.tenwa.business.entity.base.BaseDocumentConfig;
import com.tenwa.business.entity.base.BaseDocumentConfigData;
import com.tenwa.business.entity.base.BaseDocumentSavedData;
import com.tenwa.business.entity.base.BaseDocumentSavedInfo;
import com.tenwa.business.entity.base.BaseRole;
import com.tenwa.business.entity.base.BaseRoleBlock;
import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.model.Table;
import com.tenwa.business.model.database.DataSourceCloseUtil;
import com.tenwa.business.service.BeforeTableParamsLoadService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.ApConvertor;
import com.tenwa.kernal.utils.BeanFieldUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.PoiExcelUtil;
import com.tenwa.kernal.utils.ReflectionUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.kernal.utils.XMLDataUtil;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.file.BaseFileRecorder;
import com.tenwa.leasing.service.fileTemplate.FileExcelService;
import com.tenwa.leasing.serviceImpl.fileTemplate.DataStringValidator;

@Service(value = "tableService")
public class TableServiceImpl extends AbstractBaseServiceImpl implements TableService {
	
	@Resource(name = "tableDao")
	private TableDao tableDao;

	@Resource(name = "fileExcelService")
	private FileExcelService fileExcelService;
	
	@Override
	public String getSummaryTableJsonData(String xmlFileNameOrPath, Map<String, String> model) throws Exception {
		String[] summaryFields = model.get("summaryFields").split(",");
		String beforeTableParamsLoadServiceName = model.get("beforeTableParamsLoadServiceName");
		if (null != beforeTableParamsLoadServiceName) {
			BeforeTableParamsLoadService beforeTableParamsLoadService = (BeforeTableParamsLoadService) WebUtil.getApplicationContext().getBean(beforeTableParamsLoadServiceName);
			beforeTableParamsLoadService.beforeTableParamsLoad(model);
		}
		Table table = new Table();
		table.setTableXmlLoadingDirectoryFilePath(ResourceUtil.getTablesDataSourceDirectoryPath());
		table.setTableXmlTableFileNameOrPath(xmlFileNameOrPath);
		table.setModel(model);
		table.setSummaryFields(summaryFields);
		this.readTabletXmlInfo(table, false);
		// if(ResourceUtil.isDebug())
		{
			// if(table.isShowSql())
			{
				System.out.println("###tableXml:" + table.getTableXmlTableFileNameOrPath());
				System.out.println("###tableSql:" + table.getTargetSummarySql());
			}
		}
		return this.tableDao.getJdbcTemplate().query(table.getTargetSummarySql(), new RowMapper<JSONObject>() {

			@Override
			public JSONObject mapRow(ResultSet rs, int index) throws SQLException {
				JSONObject obj = new JSONObject();
				ResultSetMetaData rsMetaData = rs.getMetaData();
				int count = rsMetaData.getColumnCount();
				for (int i = 1; i <= count; i++) {
					String key = StringUtil.nullToString(rsMetaData.getColumnLabel(i), rsMetaData.getColumnName(i));
					Object value = rs.getObject(i);
					try {
						obj.put(key.toLowerCase(), value);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				return obj;
			}

		}).get(0).toString();
	}

	@Override
	public String getTableXMLJsonData(String xmlFileNameOrPath, Map<String, String> model) throws Exception {
		String beforeTableParamsLoadServiceName = model.get("beforeTableParamsLoadServiceName");
		if (null != beforeTableParamsLoadServiceName) {
			BeforeTableParamsLoadService beforeTableParamsLoadService = (BeforeTableParamsLoadService) WebUtil.getApplicationContext().getBean(beforeTableParamsLoadServiceName);
			beforeTableParamsLoadService.beforeTableParamsLoad(model);
		}
		Table table = new Table();
		table.setTableXmlLoadingDirectoryFilePath(ResourceUtil.getTablesDataSourceDirectoryPath());
		table.setTableXmlTableFileNameOrPath(xmlFileNameOrPath);
		table.setModel(model);
		String temp = this.readRequiredXmlInfo(table, false);
		return temp;
		// return jsonRoot.toString();
	}
	
	@Override
	public String getTableJsonData(String xmlFileNameOrPath, Map<String, String> model) throws Exception {
		String beforeTableParamsLoadServiceName = model.get("beforeTableParamsLoadServiceName");
		if (null != beforeTableParamsLoadServiceName) {
			BeforeTableParamsLoadService beforeTableParamsLoadService = (BeforeTableParamsLoadService) WebUtil.getApplicationContext().getBean(beforeTableParamsLoadServiceName);
			beforeTableParamsLoadService.beforeTableParamsLoad(model);
		}
		Table table = new Table();
		table.setTableXmlLoadingDirectoryFilePath(ResourceUtil.getTablesDataSourceDirectoryPath());
		table.setTableXmlTableFileNameOrPath(xmlFileNameOrPath);
		table.setModel(model);
		this.readTabletXmlInfo(table, false);
		if (ResourceUtil.isDebug()) {
			if (table.isShowSql()) {
				System.out.println("###tableXml:" + table.getTableXmlTableFileNameOrPath());
				if(null!=table.getPredefinedSql()&&(!"".equals(table.getPredefinedSql()))){
				   System.out.println("###PredefinedSql:" + table.getPredefinedSql());
				}
				System.out.println("###tableSql:" + table.getTargetSql(true));
			}
		}

		int start = 0;
		int pageSize = Integer.MAX_VALUE;
		String startStr = StringUtil.nullToString(model.get("start"));
		String pageSizeStr = StringUtil.nullToString(model.get("pageSize"));
		if (!startStr.isEmpty()) {
			start = Integer.parseInt(startStr);
		}
		if ((!"99999999".equals(pageSize)) && (!pageSizeStr.isEmpty())) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		table.setStart(start);
		table.setPageSize(pageSize);

		if (StringUtil.nullToString(table.getSourceSql()).isEmpty()) {
			System.out.println("tracywindy导出异常，异常sql：【" + table.getSourceSql() + "】");
			System.out.println("traycwindyExcel导出异常,excelXML：" + table.getTableXmlTableFileNameOrPath());
			System.out.println("请求参数列表：" + table.getModel());
		}
		this.tableDao.getTableInfo(table, false);

		// Map<String,String> headers = table.getHeaders();
		int totalCount = table.getTotal();
		/*
		 * JSONArray tableJsonDatas = table.getTableJsonArrayData();
		 * 
		 * JSONObject jsonRoot = new JSONObject(); String noRecordFlag =
		 * "false"; if((totalCount <= 0)||(0==tableJsonDatas.length())){
		 * totalCount = 0; noRecordFlag="true"; JSONObject headerObj = new
		 * JSONObject(); tableJsonDatas.put(headerObj); for(String key :
		 * headers.keySet()) {
		 * headerObj.put(StringUtil.getJsonString(key).toLowerCase() ,""); } }
		 * jsonRoot.put("totalCount", totalCount) .put("norecord" ,
		 * noRecordFlag) .put("datas" , tableJsonDatas) ;
		 */
		String data_json = table.getTableJsonArrayStringDatas();// table.getTableJsonArrayData().toString();
		StringBuffer sb = new StringBuffer();
		if ((totalCount <= 0) || ("[]".equals(data_json))) {
			// int tempIndex = 0;
			sb.append("{sumRowData:{},total:0,norecord:\"true\",datas:");
			StringBuffer tempDataJsonString = new StringBuffer();
			/*
			 * tempDataJsonString.append("[{"); for(String key :
			 * headers.keySet()) { String value = ""; if(0 < tempIndex) {
			 * tempDataJsonString.append(","); } tempDataJsonString.append("\"")
			 * .append(StringUtil.getJsonString(key).toLowerCase() )
			 * .append("\":") .append("\"") .append(value) .append("\"");
			 * tempIndex++; } tempDataJsonString.append("}]");
			 */
			tempDataJsonString.append("[]");
			sb.append(tempDataJsonString);
		} else {
			sb.append("{total:" + totalCount + ",norecord:\"false\",datas:");
			sb.append(data_json);
			sb.append(",sumRowData:");
			String sumRowData = "{}";
			String summaryFieldsArr = model.get("summaryFields");
			if (null != summaryFieldsArr) {
				String[] summaryFields = summaryFieldsArr.split(",");
				table.setSummaryFields(summaryFields);
				final Table finalTable = table;
				sumRowData = this.tableDao.getJdbcTemplate().execute(new ConnectionCallback<JSONArray>() {
					
					@Override
					public JSONArray doInConnection(Connection conn) throws SQLException,
							DataAccessException {
						PreparedStatement ps = conn.prepareStatement(finalTable.getTargetSummarySql(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
						for(int index = 0;index<finalTable.getReplaceValueList().size();index++){
							ps.setString((index+1),finalTable.getReplaceValueList().get(index));
						}
						ResultSet rs = ps.executeQuery();
						try{
							ResultSetMetaData rsMetaData = rs.getMetaData();
							int count = rsMetaData.getColumnCount();
							JSONArray jsonArray = new JSONArray();
							while(rs.next()){
								
								for (int i = 1; i <= count; i++) {
									String key = StringUtil.nullToString(rsMetaData.getColumnLabel(i), rsMetaData.getColumnName(i));
									Object value = rs.getObject(key);
									JSONObject obj = new JSONObject();
									try {
										obj.put(key.toLowerCase(), value);
										jsonArray.put(obj);
									} catch (JSONException e) {
										e.printStackTrace();
									}
								}
							}
							
							return jsonArray;
						}catch(Exception e){
							e.printStackTrace();
							throw new SQLException("异常"+finalTable.getTargetSummarySql());
						}finally{
							if(null!=rs)
							 {
								DataSourceCloseUtil.safeCloseResultSet(rs); 
							 }
							 if(null!=ps)DataSourceCloseUtil.safeCloseStatement(ps);
						}
					}
				}).get(0).toString();
				/*sumRowData = this.tableDao.getJdbcTemplate().query(table.getTargetSummarySql(), new RowMapper<JSONObject>() {

					@Override
					public JSONObject mapRow(ResultSet rs, int index) throws SQLException {
						JSONObject obj = new JSONObject();
						ResultSetMetaData rsMetaData = rs.getMetaData();
						int count = rsMetaData.getColumnCount();
						for (int i = 1; i <= count; i++) {
							String key = StringUtil.nullToString(rsMetaData.getColumnLabel(i), rsMetaData.getColumnName(i));
							Object value = rs.getObject(i);
							try {
								obj.put(key.toLowerCase(), value);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						return obj;
					}

				}).get(0).toString();*/
			}
			sb.append(sumRowData);
		}
		sb.append("}");
		return sb.toString();
		// return jsonRoot.toString();
	}
	private static Logger logger = Logger.getLogger(TableServiceImpl.class);
	@Override
	public void readTabletXmlInfo(Table table, boolean isOnlyExportByXmlFile) throws Exception {
		String tableXmlLoadingDirectoryFilePath = table.getTableXmlLoadingDirectoryFilePath();
		String tableXmlTableFileNameOrPath = table.getTableXmlTableFileNameOrPath();
		Map<String, String> tableInfoFromXmlFile = null;
		if (ResourceUtil.isDebug() || isOnlyExportByXmlFile) {
			String tableXmlfileNameFullPath = tableXmlLoadingDirectoryFilePath + "/" + tableXmlTableFileNameOrPath;
			tableXmlfileNameFullPath = FileUtil.getFilePathString(tableXmlfileNameFullPath);
			logger.info(tableXmlfileNameFullPath);
			tableInfoFromXmlFile = XMLDataUtil.readTableInfoFromXmlFile(tableXmlfileNameFullPath);
		} else {
			logger.info(table.getXmlTableFileFullPathWithoutFileSeparator());
			tableInfoFromXmlFile = WebUtil.getAllXMLTable().get(table.getXmlTableFileFullPathWithoutFileSeparator());
		}
		String show_sql = tableInfoFromXmlFile.get("show_sql");
		//默认进行防sql注入
		String isPrepared = tableInfoFromXmlFile.get("isPrepared");
		String table_sql = tableInfoFromXmlFile.get("table_sql");
		String excel_sql = tableInfoFromXmlFile.get("excel_sql");
		String dataSource = tableInfoFromXmlFile.get("dataSource");
	    String predefined_sql= tableInfoFromXmlFile.get("predefined_sql");
        String order_column=tableInfoFromXmlFile.get("order_column");
        String Data_Authority=tableInfoFromXmlFile.get("Data_Authority");
		table.setShowSql(show_sql);
		table.setSourceSql(table_sql);
		table.setExcelSourceSql(excel_sql);
		table.setDataSourceName(dataSource);
		table.setPredefinedSql(predefined_sql);
		table.setOrderColumn(order_column);
		table.setIsPrepared(isPrepared);
		if(!"false".equals(Data_Authority)){
			table.setDataAuthority(this.getDataAuthorityCondtion(Data_Authority));
		}else{
			table.setDataAuthority("");
		}
		
	}
	
	@Override
	public String readRequiredXmlInfo(Table table, boolean isOnlyExportByXmlFile) throws Exception {
		String tableXmlLoadingDirectoryFilePath = table.getTableXmlLoadingDirectoryFilePath();
		String tableXmlTableFileNameOrPath = table.getTableXmlTableFileNameOrPath();
		Map<String, String> tableInfoFromXmlFile = null;
		if (ResourceUtil.isDebug() || isOnlyExportByXmlFile) {
			String tableXmlfileNameFullPath = tableXmlLoadingDirectoryFilePath + "/" + tableXmlTableFileNameOrPath;
			tableXmlfileNameFullPath = FileUtil.getFilePathString(tableXmlfileNameFullPath);
			tableInfoFromXmlFile = XMLDataUtil.readTableInfoFromXmlFile(tableXmlfileNameFullPath);
		} else {
			tableInfoFromXmlFile = WebUtil.getAllXMLTable().get(table.getXmlTableFileFullPathWithoutFileSeparator());
		}
		String table_sql = tableInfoFromXmlFile.get("table_sql");
		return table_sql;
	}
	
	@Override
	public void doExportExcel(String xmlFileNameOrPath, Map<String, String> model, boolean isExportTitle, OutputStream os, boolean isOnlyExportByXmlFile) throws Exception {
		TableExportLog log = new TableExportLog();
		Long startTime =  System.currentTimeMillis();
		String beforeTableParamsLoadServiceName = model.get("beforeTableParamsLoadServiceName");
		if (null != beforeTableParamsLoadServiceName) {
			BeforeTableParamsLoadService beforeTableParamsLoadService = (BeforeTableParamsLoadService) WebUtil.getApplicationContext().getBean(beforeTableParamsLoadServiceName);
			beforeTableParamsLoadService.beforeTableParamsLoad(model);
		}
		String logContent = "";
		try {
			String fileTitleName = model.get("excelTitleName");
			logContent = "导出模块："+fileTitleName;
			fileTitleName = URLDecoder.decode(fileTitleName, "utf-8").trim();
			String complexHeadersStr = StringUtil.nullToString(model.get("complexHeadersStr"));
			String columnsStr = StringUtil.nullToString(model.get("columnsStr"));
			String summaryFields = StringUtil.nullToString(model.get("summaryFields"));
			// String forceExportExcelIndexs =
			// StringUtil.nullToString(model.get("forceExportExcelIndexs"));
			ExcelVersionEnum excelVersionEnum = ExcelVersionEnum.VERSION2007;
			String exportExcelVersion = StringUtil.nullToString(model.get("exportExcelVersion"), "2007");
			if (exportExcelVersion.trim().equals("2003")) {
				excelVersionEnum = ExcelVersionEnum.VERSION2003;
			}
			
			if ("local".equals(model.get("loadMode"))) {
				logContent += ";导出模式：local";
				JSONArray columnsJsonArray = new JSONArray(columnsStr);
				List<String> columns_mapping = new ArrayList<String>();
				Map<String, String> columnTypesMapping = new HashMap<String, String>();
				
				for (int i = 0; i < columnsJsonArray.length(); i++) {
					JSONObject columnJson = columnsJsonArray.getJSONObject(i);
					String mapping = columnJson.getString("mapping");
					columns_mapping.add(mapping);
					String exportType = "varchar";
					if (columnJson.has("exportType")) {
						exportType = columnJson.getString("exportType").toLowerCase();
					} else if (columnJson.has("type")) {
						exportType = columnJson.getString("type").toLowerCase();
					}
					columnTypesMapping.put(mapping, exportType);
				}
				List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
				String datasStr = model.get("datasStr");
				JSONArray datasJsonArray = new JSONArray(datasStr);
				for (int i = 0; i < datasJsonArray.length(); i++) {
					JSONObject dataJson = datasJsonArray.getJSONObject(i);
					Map<String, String> rowData = new HashMap<String, String>();
					for (String columnName : columns_mapping) {
						String columnData = "";
						if (dataJson.has(columnName)) {
							columnData = StringUtil.nullToString(dataJson.getString(columnName));
						}
						rowData.put(columnName, columnData);
					}
					datas.add(rowData);
				}
				this.dealExcelDatas(excelVersionEnum, complexHeadersStr, columnsStr, summaryFields, datas, columnTypesMapping, fileTitleName, isExportTitle, os);
				log.setExportDataSize(Long.parseLong(datasJsonArray.length()+""));
			} else {
				Table table = new Table();
				table.setTableXmlLoadingDirectoryFilePath(ResourceUtil.getTablesDataSourceDirectoryPath());
				table.setTableXmlTableFileNameOrPath(xmlFileNameOrPath);
				table.setModel(model);
				this.readTabletXmlInfo(table, isOnlyExportByXmlFile);
				int start = 0;
				int pageSize = Integer.MAX_VALUE;
				table.setStart(start);
				table.setPageSize(pageSize);
				this.tableDao.getTableExportExcelInfo(table, false);
				if (!table.getExcelSourceSql().equals(table.getSourceSql())) {
					columnsStr = table.getColumnsJsonArray().toString();
				}
				this.dealExcelDatas(excelVersionEnum, complexHeadersStr, columnsStr, summaryFields, table.getDatas(), table.getColumnTypesMapping(), fileTitleName, isExportTitle, os);
				String exportSql = table.getTargetSql(false);
				logContent += ";导出sql:"+exportSql;
				log.setExportDataSize(Long.parseLong(table.getDatas().size()+"") );
			}
		} finally {
			String isEntityLog =  ResourceUtil.getConfigValue("system.framework.entitylog");
			if(isEntityLog != null && isEntityLog.equals("true")){
				Long endTime =  System.currentTimeMillis();
				Long costTime = endTime - startTime;
				log.setExportTime(costTime);
				log.setExportType(ExportType.TABLE);
				log.setContent(logContent);
				log.setOperateUser(SecurityUtil.getPrincipal());
				log.setTime(DateUtil.getSystemDateTime());
				this.tableDao.saveEntity(log);
			}
			if (null != os) {
				os.flush();
				FileUtil.safeCloseOutStream(os);
			}
		}
	}

	@Override
	public void dealExcelDatas(ExcelVersionEnum excelVersionEnum, String complexHeadersStr, String columnsStr, String summaryFields, List<Map<String, String>> datas, Map<String, String> dataTypesMapping, String fileTitleName, boolean isExportTitle, OutputStream os) throws Exception {
		String exportDate = DateUtil.getSystemDateTime();
		Workbook workbook = PoiExcelUtil.createExcelWorkbook(excelVersionEnum);
		CellStyle fileTitleStyle = PoiExcelUtil.getExportFileNameStyle(workbook);
		CellStyle fileDateStyle = PoiExcelUtil.getExportDateStyle(workbook);
		CellStyle dateCotentStyle = PoiExcelUtil.getExportContentDateStyle(workbook);
		CellStyle complexHeaderTitleStyle = PoiExcelUtil.getExportComplexHeaderTitleStyle(workbook);
		CellStyle contentTitleStyle = PoiExcelUtil.getExportContentTitleStyle(workbook);
		CellStyle cotentStyle = PoiExcelUtil.getExportContentStyle(workbook);
		
		// 保存自适应宽度
		Map<String, Integer> savedWidthMap = new HashMap<String, Integer>();
		
		// 创建标题行准备
		int len = 1;// 编号行
		JSONArray columns = new JSONArray(columnsStr);
		for (int i = 0; i < columns.length(); i++) {
			if (!columns.getJSONObject(i).getBoolean("hidden")) {
				len++;
			}
		}
		
		// 行号游标
		int rowIndex = 0;
		Sheet sheet = PoiExcelUtil.createExcelSheet(workbook, null);
		
		// excel标题行
		Row excelTitleRow = PoiExcelUtil.createExcelRow(sheet, rowIndex++);
		Cell excelTitleCell = PoiExcelUtil.createLabelExcelCell(excelTitleRow, 0, fileTitleStyle, (fileTitleName), CellStyle.ALIGN_LEFT);
		PoiExcelUtil.createMergeRegion(sheet, excelTitleCell, 1, len);// 加上序号列
		
		// 日期行
		Row excelDateRow = PoiExcelUtil.createExcelRow(sheet, rowIndex++);
		Cell excelDateCell = PoiExcelUtil.createLabelExcelCell(excelDateRow, 0, fileDateStyle, exportDate, CellStyle.ALIGN_LEFT);
		PoiExcelUtil.createMergeRegion(sheet, excelDateCell, 1, len);// 加上序号列
		
		// 创建复杂表头
		if (complexHeadersStr != null && complexHeadersStr.matches("^\\[(.|\\W)*\\]$")) {
			JSONArray complexHeadersArray = new JSONArray(complexHeadersStr);
			rowIndex += complexHeadersArray.length();
			for (int i = 0; i < (rowIndex - 2); i++) {
				Row complexHeaderRow = PoiExcelUtil.createExcelRow(sheet, i + 2);
				JSONArray complexHeaderRowData = (JSONArray) complexHeadersArray.get(i);
				int currentColumnRealIndex = 0;
				int beforeCurrentColumnRealIndex = 0;
				int disperRealIndex = 0;
				for (int j = 0; j < complexHeaderRowData.length(); j++) {
					int rowspan = 1;
					int colspan = 1;

					JSONObject complexHeaderRowDataColumn = (JSONObject) complexHeaderRowData.get(j);
					String columnHeader = StringUtil.nullToString(complexHeaderRowDataColumn.get("header"));
					// String tempColumnHeaderAlign = StringUtil.nullToString(complexHeaderRowDataColumn.get("headerAlign"));

					String rowspanStr = complexHeaderRowDataColumn.isNull("rowspan") ? "" : StringUtil.nullToString(complexHeaderRowDataColumn.get("rowspan"));
					String colspanStr = complexHeaderRowDataColumn.isNull("colspan") ? "" : StringUtil.nullToString(complexHeaderRowDataColumn.get("colspan"));

					beforeCurrentColumnRealIndex = currentColumnRealIndex;
					if (!rowspanStr.isEmpty()) {
						rowspan = Integer.parseInt(rowspanStr);
					}
					if (!colspanStr.isEmpty()) {
						colspan = Integer.parseInt(colspanStr);
					}
					int realComplexColIndex = beforeCurrentColumnRealIndex - disperRealIndex;
					if (complexHeaderRowDataColumn.has("startColNum")) {
						realComplexColIndex = complexHeaderRowDataColumn.getInt("startColNum") - 1;
						currentColumnRealIndex = realComplexColIndex;
					}

					Cell complexHeaderCell = PoiExcelUtil.createLabelExcelCell(complexHeaderRow, realComplexColIndex, complexHeaderTitleStyle, columnHeader, CellStyle.ALIGN_CENTER);

					currentColumnRealIndex += colspan;
					for (int colConfigIndex = beforeCurrentColumnRealIndex; colConfigIndex < currentColumnRealIndex; colConfigIndex++) {
						if (columns.getJSONObject(colConfigIndex).getBoolean("hidden")) {
							colspan--;
							disperRealIndex++;
						}
					}
					PoiExcelUtil.createMergeRegion(sheet, complexHeaderCell, rowspan, colspan);
				}
			}
		}
		
		// 创建内容标题行
		int realTitleColIndex = 0;
		Row contentTitleRow = PoiExcelUtil.createExcelRow(sheet, rowIndex++);
		PoiExcelUtil.createLabelExcelCell(contentTitleRow, realTitleColIndex++, contentTitleStyle, WebUtil.isEnglish() ? "index" : "编号", CellStyle.ALIGN_LEFT);
		for (int i = 0; i < columns.length(); i++) {
			JSONObject columnObject = columns.getJSONObject(i);
			if (columnObject.getBoolean("hidden")) {
				continue;
			}
			String headerValue = columnObject.getString("header");
			if (columnObject.has("required")) {
				if ("true".equals(columnObject.getString("required"))) {
					headerValue = "*" + headerValue;
				}
			}
			Integer currentSavedColWidth = savedWidthMap.get("col_" + realTitleColIndex);
			Integer currentColWidth = headerValue.getBytes().length;
			if (null != currentSavedColWidth) {
				currentColWidth = Integer.valueOf(Math.max(currentColWidth, currentSavedColWidth.intValue()));
			}
			savedWidthMap.put("col_" + realTitleColIndex, currentColWidth);
			if(headerValue.indexOf("*")>=0){
				CellStyle contentTitleStyletemp = PoiExcelUtil.getExportContentTitleStyle(workbook);
				contentTitleStyletemp.setFillForegroundColor(HSSFColor.RED.index);
				Cell cell=PoiExcelUtil.createLabelExcelCell(contentTitleRow, realTitleColIndex, contentTitleStyletemp, headerValue, CellStyle.ALIGN_LEFT);		
			}else{
				Cell cell=PoiExcelUtil.createLabelExcelCell(contentTitleRow, realTitleColIndex, contentTitleStyle, headerValue, CellStyle.ALIGN_LEFT);			
			}
			realTitleColIndex++;
		}
		
		// 创建数据行
		summaryFields=","+summaryFields+",";
		Map<String, Integer> totalColIndex = new HashMap<String, Integer>();
		Map<String, BigDecimal> totalValue = new HashMap<String, BigDecimal>();
		for (int i = 0; i < datas.size(); i++) {
			int realDataColIndex = 0;
			Map<String, String> dataRow = datas.get(i);
			Row contentRow = PoiExcelUtil.createExcelRow(sheet, rowIndex++);
			PoiExcelUtil.createLabelExcelCell(contentRow, realDataColIndex++, cotentStyle, i + 1, CellStyle.ALIGN_LEFT);// 序号列
			
			for (int index = 0; index < columns.length(); index++) {
				if (columns.getJSONObject(index).getBoolean("hidden")) {
					continue;
				}
				String mappingName = null;
				try {
					mappingName = StringUtil.nullToString(columns.getJSONObject(index).getString("mapping"));
				} catch (Exception e) {
					mappingName = "";
				}

				String value = "-";
				if (mappingName != null) {
					value = StringUtil.empty2Other(dataRow.get(mappingName), "-");
				}
				Integer currentSavedColWidth = savedWidthMap.get("col_" + realDataColIndex);
				Integer currentColWidth = value.getBytes().length;
				if (null != currentSavedColWidth) {
					currentColWidth = Integer.valueOf(Math.max(currentColWidth, currentSavedColWidth.intValue()));
				}
				savedWidthMap.put("col_" + realDataColIndex, currentColWidth);
				
				//通过数据库类型判断 单元格类型
				String typeName = null;
				try{
					typeName = StringUtil.nullToString(dataTypesMapping.get(mappingName)).toLowerCase();
				}catch(Exception e){
					typeName = "VARCHAR2".toLowerCase();
				}
				
				boolean isNumber = false;
				if ((typeName.indexOf("int") > -1)) {
					isNumber = true;
				} else if ((typeName.indexOf("float") > -1)) {
					isNumber = true;
				} else if ((typeName.indexOf("double") > -1)) {
					isNumber = true;
				} else if (typeName.indexOf("decimal") > -1) {
					isNumber = true;
				} else if (typeName.indexOf("number") > -1) {
					isNumber = true;
				}
				
				// 合计NUMBER的值
				if(!totalValue.containsKey(mappingName)){
					totalValue.put(mappingName, null);
				}
				
				if(isNumber){// 是数字列
					if(value != null && value.matches("^([-]){0,1}([0-9]){1,}([.]){0,1}([0-9]){0,}$")){// 值是数字
						if(summaryFields != null && summaryFields.indexOf(","+mappingName+",") > -1){// 值需要合计
							BigDecimal vv = totalValue.get(mappingName);
							vv = new BigDecimal(value).add(vv != null ? vv : BigDecimal.ZERO);
							totalValue.put(mappingName, vv);
						}
					}
					PoiExcelUtil.createNumberExcelCell(contentRow, realDataColIndex, cotentStyle, value, 20);
				} else {
					if(DateTools.isStringDate(value)){
						Date vD = DateTools.parseToDate(value);
						Cell c = PoiExcelUtil.createLabelExcelCell(contentRow, realDataColIndex, dateCotentStyle, value, CellStyle.ALIGN_LEFT);
						c.setCellValue(vD);
					} else {
						PoiExcelUtil.createLabelExcelCell(contentRow, realDataColIndex, cotentStyle, value, CellStyle.ALIGN_LEFT);
					}
				}
				
				totalColIndex.put(mappingName, realDataColIndex);
				realDataColIndex++;
			}
		}		
		// 创建合计行
		try {
			if(datas.size()>0){
			Row rr = PoiExcelUtil.createExcelRow(sheet, rowIndex++);
			PoiExcelUtil.createLabelExcelCell(rr, 0, cotentStyle, "合计：", CellStyle.ALIGN_LEFT);
			for (String key : totalValue.keySet()) {
				int cc = totalColIndex.get(key).intValue();
				BigDecimal vv = totalValue.get(key);
				PoiExcelUtil.createLabelExcelCell(rr, cc, cotentStyle, vv == null ? "-" : vv.toString(), CellStyle.ALIGN_LEFT);
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		PoiExcelUtil.setMergeRegionsCellStyle(sheet, cotentStyle);
		//PoiExcelUtil.setAutoColumnWidth(sheet,true);
		//PoiExcelUtil.setAutoColumnWidthByMap(sheet, savedWidthMap);
		// 生成excel
		PoiExcelUtil.writeWorkBook(workbook, os);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tenwa.business.service.TableService#getTreeJsonData(java.lang.String,
	 *      java.util.Map)
	 **/
	@Override
	public String getTreeJsonData(String xmlFileNameOrPath, Map<String, String> model) throws Exception {
		return this.getJsonArrayData(xmlFileNameOrPath, model).toString();
	}

	@Override
	public JSONArray getJsonArrayData(String xmlFileNameOrPath, Map<String, String> model) throws Exception {
		String beforeTableParamsLoadServiceName = model.get("beforeTableParamsLoadServiceName");
		if (null != beforeTableParamsLoadServiceName) {
			BeforeTableParamsLoadService beforeTableParamsLoadService = (BeforeTableParamsLoadService) WebUtil.getApplicationContext().getBean(beforeTableParamsLoadServiceName);
			beforeTableParamsLoadService.beforeTableParamsLoad(model);
		}
		Table table = new Table();
		table.setTableXmlLoadingDirectoryFilePath(ResourceUtil.getTablesDataSourceDirectoryPath());
		table.setTableXmlTableFileNameOrPath(xmlFileNameOrPath);
		table.setModel(model);
		this.readTabletXmlInfo(table, false);
		int start = 0;
		int pageSize = Integer.MAX_VALUE;
		String startStr = StringUtil.nullToString(model.get("start"));
		String pageSizeStr = StringUtil.nullToString(model.get("pageSize"));
		if (!startStr.isEmpty()) {
			start = Integer.parseInt(startStr);
		}
		if ((!"99999999".equals(pageSize)) && (!pageSizeStr.isEmpty())) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		table.setStart(start);
		table.setPageSize(pageSize);
		if (ResourceUtil.isDebug()) {
			if (table.isShowSql()) {
				System.out.println("###tableXml:" + table.getTableXmlTableFileNameOrPath());
				System.out.println("###tableSql:" + table.getTargetSql());
			}
		}
		if (StringUtil.nullToString(table.getSourceSql()).isEmpty()) {
			System.out.println("异常sql：【" + table.getSourceSql() + "】");
			System.out.println("请求参数列表：" + table.getModel());
		}
		this.tableDao.getTableInfo(table, true);

		return table.getTableJsonArrayData();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tenwa.business.serviceImpl.AbstractBaseServiceImpl#getBussinessDao()
	 **/

	@Override
	public BaseDao getBussinessDao() throws Exception {
		return this.tableDao;
	}

	
	@Override
	public String importExcel(MultipartFile multipartFile, Map<String, String> modelData) throws Exception {
		InputStream is = null;
		JSONArray resultJsonArray;
		List targetList=new ArrayList();
		String StrtargetClass="";
		String StrCallBackMethod="";
		//mapping列对应类的属性名
		//hiddenName列对应的隐藏属性名
		//entityClassName列对应的类名
		//entityHeaderFieldName列对应的类的属性名
		//type数据类型
		//header列名
		List exceptionMessage=new ArrayList();
		try {
			String importExcelColumnStr = modelData.get("importExcelColumnStr");
			JSONArray importExcelColumnJsonArray = new JSONArray(importExcelColumnStr);
			Map<String, String> mappingMap = new HashMap<String, String>();
			Map<String, String> hiddenNameMap = new HashMap<String, String>();
			Map<String, String> entityClassNameMap = new HashMap<String, String>();
			Map<String, String> entityHeaderFieldNameMap = new HashMap<String, String>();
			Map<String, String> entityHeaderFieldTypeNameMap = new HashMap<String, String>();
			Map<String,String>classFieldMap=new HashMap<String,String>();
			Map<String, String> findCallBackMap = new HashMap<String, String>();			
			Class<?> targetObject =null; 
			BaseFile bf = null;
			//导入对应的类
			if(modelData.containsKey("importTargetClass")){
				StrtargetClass=StringUtil.empty2Other(modelData.get("importTargetClass"),"");
				if(!"".equals(StrtargetClass)){
				   targetObject=Class.forName(StrtargetClass);
				   bf = saveUpFiletoService(multipartFile, modelData);// 记录上传操作
				}
			}
			Method callBackMethod = null;
			//导入对应的回调方法
			if(modelData.containsKey("importOrExPortCallBack")){
				StrCallBackMethod=StringUtil.empty2Other(modelData.get("importOrExPortCallBack"),"");
				if(!"".equals(StrCallBackMethod)){
					try {
						callBackMethod = fileExcelService.getClass().getMethod(StrCallBackMethod, targetObject, Map.class, Integer.class);
					} catch (Exception e) {
						throw new BusinessException("fileExcelService中没有自定义回调方法" + StrCallBackMethod);
					}
				}
			}
			//前台传入的参数据
			String value = "";
			for (int i = 0; i < importExcelColumnJsonArray.length(); i++) {
				JSONObject jsonColumn = importExcelColumnJsonArray.getJSONObject(i);
				String header = "";
				String name = jsonColumn.getString("mapping");
				String hiddenName = null;
				String entityClassName = null;
				String entityHeaderFieldName = null;
				String entityHeaderFieldTypeName = null;
				String findCallBack="";
				if (jsonColumn.has("hiddenName")) {
					hiddenName = StringUtil.empty2Other(jsonColumn.getString("hiddenName"), null);
				}
				if (jsonColumn.has("entityClassName")) {// DictionaryData
					entityClassName = StringUtil.empty2Other(jsonColumn.getString("entityClassName"), null);
				}
				if (jsonColumn.has("entityHeaderFieldName")) {// name
					entityHeaderFieldName = StringUtil.empty2Other(jsonColumn.getString("entityHeaderFieldName"), null);
				} else {
					entityHeaderFieldName = name;
				}
				if (jsonColumn.has("type")) {
					entityHeaderFieldTypeName = jsonColumn.getString("type").toLowerCase();
				} else {
					entityHeaderFieldTypeName = "VARCHAR2".toLowerCase();
				}
				if(jsonColumn.has("findCallBack")){
					findCallBack=jsonColumn.getString("findCallBack").toLowerCase();
				}else{
					findCallBack="";
				}
				if (jsonColumn.has("header")) {
					header = jsonColumn.getString("header");
				} else {
					header = name;
				}
				mappingMap.put(header, name);
				hiddenNameMap.put(header, hiddenName);
				entityClassNameMap.put(header, entityClassName);
				entityHeaderFieldNameMap.put(header, entityHeaderFieldName);
				entityHeaderFieldTypeNameMap.put(header, entityHeaderFieldTypeName);
				findCallBackMap.put(header, findCallBack);
				if(null!=entityClassName&&(!"".equals(entityClassName))){
					classFieldMap.put(entityClassName.substring(entityClassName.lastIndexOf(".")+1), "id");
				}
			}
			
			
			is = multipartFile.getInputStream();
			resultJsonArray = new JSONArray();
			Workbook wb = null;
			String importFileName = multipartFile.getOriginalFilename().toLowerCase();
			if (importFileName.endsWith("xlsx")) {
				wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2007);
			} else {
				wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2003);
			}

			Sheet sheet = wb.getSheetAt(0);

			String strheaderRowindex = StringUtil.nullToString(modelData.get("importHeaderIndex"), "1");
			String strDataRowindex = StringUtil.nullToString(modelData.get("importDataIndex"), "2");
			int headerRowindex = Integer.parseInt(strheaderRowindex);
			int DataRowindex=Integer.parseInt(strDataRowindex);
			
			String[] headerIndexs = strheaderRowindex.split(",");
			List<Row> headerRows = new ArrayList<Row>();
			// 标题行
			for (int i = 0; i < headerIndexs.length; i++) {
				headerRows.add(sheet.getRow(Integer.parseInt(headerIndexs[i]) - 1));
			}
			int rowCount = sheet.getPhysicalNumberOfRows();
			int cellnumber=0;
			for (int rowIndex =( DataRowindex-1); rowIndex < rowCount; rowIndex++) {
				JSONObject jsonObj = new JSONObject();
				Row row = (Row) sheet.getRow(rowIndex);
				cellnumber=0;
				//判断这一行是不是都为空字符
				boolean isemptyrow=false;
				if(null==row){continue;}//如果这个这一行为空则调过这一行
				int cellnumbers=row.getPhysicalNumberOfCells();
				if(cellnumbers<=0){continue;}//如果这一行的单元格数据为O则进调过这一行
				Iterator tempIt = row.cellIterator();
				if(tempIt.hasNext()){
				  Cell cellt = (Cell) tempIt.next();
				  value = PoiExcelUtil.getCellValue(cellt);
				  if(value.length()==0){continue;}
				  if(value.indexOf("合计")>=0){continue;}//如果这一行为合计则跳过这一行。
				}
				for (Iterator cit = row.cellIterator(); cit.hasNext();) {
					Cell cell = (Cell) cit.next();
					
					value = PoiExcelUtil.getCellValue(cell);
					value = value.trim();
					if(value.length()>0){isemptyrow=true;}
				}
				if(isemptyrow==false){continue;}//如果这一行都为空则跳过这一行。
				for (Iterator cit = row.cellIterator(); cit.hasNext();) {
					cellnumber=cellnumber+1;
					value="";
					Cell cell = (Cell) cit.next();
					value = PoiExcelUtil.getCellValue(cell);
					value = value.trim();
					int colIndex = cell.getColumnIndex();
					String header="";
					String temp="";
					//查找这个单位格对应的标题
					for (Row r : headerRows) {
						if(null!=r.getCell(colIndex)){
							String headername = r.getCell(colIndex).getStringCellValue().trim();	
							temp=headername;
							if(temp.indexOf("*")>=0){
								temp=headername.replace("*", "");	
							}
							if(mappingMap.containsKey(temp)){
								header=headername;
							}
				        }
					}	
					String tempheader=header;
					if(tempheader.indexOf("*")>=0){
						tempheader=header.replace("*", "");
					}
					String name = mappingMap.get(tempheader);
					String hiddenName = hiddenNameMap.get(tempheader);
					String entityClassName = entityClassNameMap.get(tempheader);
					String entityHeaderFieldName = entityHeaderFieldNameMap.get(tempheader);
					String entityHeaderFieldTypeName = entityHeaderFieldTypeNameMap.get(tempheader);
					//检查必填
					if(header.indexOf("*")>=0){
						if("".equals(value)){
							exceptionMessage.add("Excel中的"+header+"("+(rowIndex+1)+"行"+cellnumber+"列)不能为空<br>");
							//throw new BusinessException("Excel中的"+header+"("+(rowIndex+1)+"行"+cellnumber+"列)不能为空");
						}
					}
					if("".equals(value)){continue;}
					if(entityHeaderFieldTypeName=="currency"){
						value=value.replaceAll(",", "");
						value=value.replaceAll("￥", "");
					}					
					//检查数据类型
					if(!DataStringValidator.checkStringData(value, entityHeaderFieldTypeName)){
						exceptionMessage.add("Excel中的第"+(rowIndex+1)+"行"+header+"["+value+"]"+"和系统中设定数据格式不对<br>");
						//throw new BusinessException(value+":"+entityHeaderFieldTypeName+"在Excel中的第"+(rowIndex+1)+"行"+header+"和系统中设定数据格式不对");	
					}else{
					   if (null != name) {
						  jsonObj.put(name, value);
					   }
					}
					//查找对象属性
					if(null!=value && value!=""){
					if ((null != hiddenName) && (null != entityClassName) && (null != entityHeaderFieldName)) {
						Class<?> entityClass = Class.forName(entityClassName);
						Map<String, Object> filterMap = new HashMap<String, Object>();
						// 通过数据库类型判断 单元格类型
						Object filterValue = value;
							if ((entityHeaderFieldTypeName.indexOf("int") > -1)) {
								filterValue = new Integer(value);
							} else if ((entityHeaderFieldTypeName.indexOf("float") > -1)) {
								filterValue = new Float(value);
							} else if ((entityHeaderFieldTypeName.indexOf("double") > -1)) {
								filterValue = new Double(value);
							} else if (entityHeaderFieldTypeName.indexOf("decimal") > -1) {
								filterValue = new BigDecimal(value);
							} else if (entityHeaderFieldTypeName.indexOf("number") > -1) {
								filterValue = new Integer(value);
							} else if (entityHeaderFieldTypeName.indexOf("boolean") > -1) {
								filterValue = new Boolean(value);
							}
						filterMap.put(entityHeaderFieldName, filterValue);
						String hiddenValue = null;
						List objList = this.findEntityByProperties(entityClass, filterMap);
						if (objList.size() > 0) {
							hiddenValue = (entityClass.getMethod("getId").invoke(objList.get(0))).toString();
						}else{
							Method findCallBackMethod = null;
							String findCallBack="";
							
							findCallBack=findCallBackMap.get(tempheader).toString();
							if(null!=findCallBack&&findCallBack.length()>0){
								try {
									findCallBackMethod = fileExcelService.getClass().getMethod(findCallBack, String.class);
								} catch (Exception e) {
									throw new BusinessException("fileExcelService中没有自定义回调方法" + findCallBack);
								}
								try {
									
									hiddenValue = findCallBackMethod.invoke(fileExcelService, value).toString();
								} catch (Exception e) {
										InvocationTargetException targetEx = (InvocationTargetException) e;
										Throwable t = targetEx.getTargetException();
										exceptionMessage.add(t.getMessage());
										e.printStackTrace();
								}
							}else{
							    //检查对应的类有没有
								exceptionMessage.add("Excel中的("+(rowIndex+1)+"行"+cellnumber+"列)"+value+"在系统没有对应的数据"+header+"<br>");
								//throw new BusinessException("Excel中的("+(rowIndex+1)+"行"+cellnumber+"列)"+value+"在系统没有对应的数据"+header);
							}
						}
						if(null!=hiddenValue){
						  jsonObj.put(hiddenName, hiddenValue);
						}
					}}
				}
				
				if(jsonObj.length()>0){
				   if(!"".equals(StrtargetClass)){
					   Object sourectObject = targetObject.newInstance();
					   this.copyAndOverrideExistedValueFromJSONObject(jsonObj, sourectObject, classFieldMap);
					   try {
							BeanUtils.getPropertyDescriptor(sourectObject.getClass(), "upLoadId").getWriteMethod().invoke(sourectObject, bf);
						} catch (Exception e1) {
							throw new BusinessException("实体中没有upLoadId字雄段<br>");
						}
					   //判断是否重复导入
					   if(null!=sourectObject){//返回空不插数据库
						   for(int i=0;i<targetList.size();i++){
							  if(targetList.get(i).equals(sourectObject)){
								throw new BusinessException("Excel中的第"+(rowIndex)+"行与前面重复<br>");
							  }
						}
						//每一行数据回调
						if (!"".equals(StrCallBackMethod) && null != callBackMethod) {
						    try {
							  sourectObject = callBackMethod.invoke(fileExcelService, sourectObject, modelData, (Integer) (rowIndex+1));
							 } catch (Exception e) {
								InvocationTargetException targetEx = (InvocationTargetException) e;
								Throwable t = targetEx.getTargetException();
								//throw new BusinessException("Excel中的第"+(rowIndex+1)+"行调用回调方法"+StrCallBackMethod+"时出错"+t.getMessage());
								//实际运营中不需要过多的代码提示
								//throw new BusinessException(t.getMessage());
								exceptionMessage.add(t.getMessage());
								e.printStackTrace();
							}
						}   
					    targetList.add(sourectObject);
				   }
					 
				}
				   resultJsonArray.put(jsonObj); 
				}
			}
		} finally {
			if (null != is) {
				FileUtil.safeCloseInputStream(is);
			}
		}
		//如果出错信息大于0则抛出一个异常
		if(exceptionMessage.size()>0){
			throw new BusinessException("导入数据出错：<br>"+exceptionMessage.toString());
		}
		if(targetList.size()>0){
			this.saveOrUpdateAllEntities(targetList);
			return "{message:\"成功导入"+targetList.size()+"条\",tabledate:\"\"}";
		}else{
			return "{message:\"成功导入"+resultJsonArray.length()+"条\",tabledate:"+resultJsonArray.toString()+"}";
		}
	}

	@Override
	public String createTree2TableJsonString(String savedDataKey, String dictTableName, String dictTableDataName, String rootDictId, boolean isLoadDictData, Map<String, String> savedDataKeyMap) throws Exception {
		String treeTableJson = "";
		String treeDataMapping = "";
		int maxTreeLevel = 0;
		int maxTreeDataCount = 0;
		String valuesMapping = "";
		String scoreMapping = null;

		savedDataKeyMap.put("savedDataKey", savedDataKey);
		savedDataKeyMap.put("dictTableName", dictTableName);
		savedDataKeyMap.put("dictTableDataName", dictTableDataName);
		savedDataKeyMap.put("rootDictId", rootDictId);

		JSONArray treeTableInfoArray = this.getJsonArrayData("tree2table/queryTreeTableInfo.xml", savedDataKeyMap);
		if (0 < treeTableInfoArray.length()) {
			JSONObject treeTableInfoJson = treeTableInfoArray.getJSONObject(0);
			treeTableJson = treeTableInfoJson.getString("tree_table_json_");
			treeDataMapping = treeTableInfoJson.getString("tree_data_mapping_");
			maxTreeLevel = treeTableInfoJson.getInt("max_tree_level_");
			maxTreeDataCount = treeTableInfoJson.getInt("max_tree_data_count_");
			valuesMapping = treeTableInfoJson.getString("values_mapping_");
			scoreMapping = treeTableInfoJson.getString("score_mapping_");
		} else {
			// 各个级别对应的节点
			// 各个级别对应的子节点数量
			JSONArray allLevelTreeJsonArray = this.getJsonArrayData("tree2table/queryAllLevelTree.xml", savedDataKeyMap);
			JSONArray allLevelTreeCountJsonArray = this.getJsonArrayData("tree2table/queryAllLevelTreeCount.xml", savedDataKeyMap);
			Map<String, Integer> levelDataCountMap = new HashMap<String, Integer>();
			for (int i = 0; i < allLevelTreeCountJsonArray.length(); i++) {
				JSONObject jsonObj = allLevelTreeCountJsonArray.getJSONObject(i);
				int childrenCount = jsonObj.getInt("children_count_");
				String itemId = jsonObj.getString("item_id_");
				levelDataCountMap.put(itemId, childrenCount);
			}
			// 最大级别
			JSONArray maxLevelJsonArray = this.getJsonArrayData("tree2table/queryMaxLevel.xml", savedDataKeyMap);
			int maxLevel = 0;
			// 获取maxTreeLevel
			for (int i = 0; i < maxLevelJsonArray.length(); i++) {
				JSONObject jsonObj = maxLevelJsonArray.getJSONObject(i);
				maxLevel = jsonObj.getInt("max_item_level_");
			}
			// 数据明细
			JSONArray allLevelColumnDetailJsonArray = this.getJsonArrayData("tree2table/queryAllLevelColumnDetail.xml", savedDataKeyMap);
			// 数据明细最大列数
			JSONArray allLevelColumnDetailMaxCountJsonArray = this.getJsonArrayData("tree2table/queryAllLevelColumnDetailMaxCount.xml", savedDataKeyMap);
			// 已经保存的数值
			JSONArray valuesMappingJsonArray = this.getJsonArrayData("tree2table/queryValuesMapping.xml", savedDataKeyMap);

			// 迭代treeTableJson
			JSONObject jsonRootObj = null;
			Map<String, JSONObject> remainJSONObjectMap = new HashMap<String, JSONObject>();
			int currentRowIndex = 0;
			for (int index = 0; index < allLevelTreeJsonArray.length(); index++) {
				JSONObject levelDataMap = allLevelTreeJsonArray.getJSONObject(index);
				JSONObject cellJsonObj = new JSONObject();
				String itemPid = StringUtil.nullToString(levelDataMap.get("item_pid_"));
				String itemId = StringUtil.nullToString(levelDataMap.get("item_id_"));
				String itemName = StringUtil.nullToString(levelDataMap.get("item_name_"));
				String itemCode = StringUtil.nullToString(levelDataMap.get("item_code_"));
				String itemValue = StringUtil.nullToString(levelDataMap.get("item_value_"));
				String itemType = StringUtil.nullToString(levelDataMap.get("item_type_"));
				String itemIsSelected = StringUtil.nullToString(levelDataMap.get("item_is_selected"));
				String itemIsRequire = StringUtil.nullToString(levelDataMap.get("item_is_require"));
				String itemDescription = StringUtil.nullToString(levelDataMap.get("item_description_"));
				String itemPosition = StringUtil.nullToString(levelDataMap.get("item_position_"));
				String itemIsStatistic = StringUtil.nullToString(levelDataMap.get("item_is_statistic_"));
				String itemEnabled = StringUtil.nullToString(levelDataMap.get("item_enabled_"));
				String itemWidth = StringUtil.nullToString(levelDataMap.get("item_width_"));
				String itemHeight = StringUtil.nullToString(levelDataMap.get("item_height_"));
				
				 
				boolean isLeaf = Boolean.parseBoolean(StringUtil.nullToString(levelDataMap.get("is_leaf_")));
				int itemLevel = Integer.parseInt(StringUtil.nullToString(levelDataMap.get("item_level_")));
				int rowSpan = levelDataCountMap.get(itemId).intValue();
				cellJsonObj.put("pid", itemPid);
				cellJsonObj.put("id", itemId);
				cellJsonObj.put("itemIsStatistic", itemIsStatistic);
				cellJsonObj.put("itemIsRequire", itemIsRequire);
				cellJsonObj.put("name", itemName);
				cellJsonObj.put("code", itemCode);
				cellJsonObj.put("value", itemValue);
				cellJsonObj.put("type", itemType);
				cellJsonObj.put("isSelected", itemIsSelected);
				cellJsonObj.put("description", itemDescription);
				cellJsonObj.put("width", itemWidth);
				cellJsonObj.put("height", itemHeight);
				cellJsonObj.put("position", itemPosition);
				cellJsonObj.put("enabled", itemEnabled);
				cellJsonObj.put("level", itemLevel);
				cellJsonObj.put("leaf", isLeaf);
				cellJsonObj.put("rowSpan", rowSpan);
				cellJsonObj.put("colSpan", isLeaf ? (maxLevel - itemLevel + 1) : 1);
			 
				if (1 == itemLevel) {
					cellJsonObj.put("trs", new JSONArray());
					jsonRootObj = cellJsonObj;
				} else {
					JSONArray jsonArray = jsonRootObj.getJSONArray("trs");
					JSONObject rowJsonObj = remainJSONObjectMap.get("tr-" + currentRowIndex);
					if (null == rowJsonObj) {
						rowJsonObj = new JSONObject();
						rowJsonObj.put("tds", new JSONArray());
						jsonArray.put(rowJsonObj);
						remainJSONObjectMap.put("tr-" + currentRowIndex, rowJsonObj);
					}
					JSONArray cellsArray = rowJsonObj.getJSONArray("tds");
					cellsArray.put(cellJsonObj);
				}
				if (isLeaf) {
					++currentRowIndex;
				}
			}
			// 迭代treeDataMapping
			int maxDataDetailCount = 0;
			Map<String, List<Map<String, String>>> dataDetailListMap = new HashMap<String, List<Map<String, String>>>();
			if (isLoadDictData) {
				for (int i = 0; i < allLevelColumnDetailJsonArray.length(); i++) {
					JSONObject jsonObj = allLevelColumnDetailJsonArray.getJSONObject(i);
					String itemPid = StringUtil.nullToString(jsonObj.getString("item_pid_"));
					String itemIsStatistic = StringUtil.nullToString(jsonObj.getString("item_is_statistic_"));
					String itemId = StringUtil.nullToString(jsonObj.getString("item_id_"));
					String itemName = StringUtil.nullToString(jsonObj.getString("item_name_"));
					String itemCode = StringUtil.nullToString(jsonObj.getString("item_code_"));
					String itemValue = StringUtil.nullToString(jsonObj.getString("item_value_"));
					String itemType = StringUtil.nullToString(jsonObj.getString("item_type_"));
					String itemIsSelected = StringUtil.nullToString(jsonObj.getString("item_is_selected"));
					String itemIsRequire = StringUtil.nullToString(jsonObj.get("item_is_require"));
					String itemWidth = StringUtil.nullToString(jsonObj.getString("item_width_"));
					String itemHeight = StringUtil.nullToString(jsonObj.getString("item_height_"));
					String itemDescription = StringUtil.nullToString(jsonObj.getString("item_description_"));
					String itemPosition = StringUtil.nullToString(jsonObj.getString("item_position_"));
					String itemEnabled = StringUtil.nullToString(jsonObj.getString("item_enabled_"));
					String itemautocomputer= StringUtil.nullToString(jsonObj.get("auto_computer_"));
					//String role = StringUtil.nullToString(jsonObj.get("role"));
					Map<String, String> dataMap = new HashMap<String, String>();
					dataMap.put("pid", itemPid);
					dataMap.put("id", itemId);
					dataMap.put("name", itemName);
					dataMap.put("code", itemCode);
					dataMap.put("value", itemValue);
					dataMap.put("type", itemType);
					dataMap.put("isSelected", itemIsSelected);
					dataMap.put("description", itemDescription);
					dataMap.put("width", itemWidth);
					dataMap.put("height", itemHeight);
					dataMap.put("position", itemPosition);
					dataMap.put("itemIsStatistic", itemIsStatistic);
					dataMap.put("enabled", itemEnabled);
					dataMap.put("itemIsRequire", itemIsRequire);
					dataMap.put("autocomputer",itemautocomputer);
					dataMap.put("defaultValue", itemValue);
					//dataMap.put("role", role);
					//如果是下拉框
					/*if(itemType.equals("documentColumnType.combox")){
						String hql = "from BaseRoleBlock  where pid.id = ? order by position asc";
						List<BaseRoleBlock> blocks = this.findResultsByHSQL(hql, role);
						JSONArray blocksJsonStr = new JSONArray();
						for(BaseRoleBlock block : blocks){
							JSONObject blockJson = new JSONObject();
							blockJson.put("blockName", block.getName());
							blockJson.put("blockValue", block.getBaseValue());
							blocksJsonStr.put(blockJson);
						}
						System.out.println(blocksJsonStr.toString());
						dataMap.put("blocksJsonStr", blocksJsonStr.toString());
					} */
					List<Map<String, String>> dataDetailList = dataDetailListMap.get(itemPid);
					if (null == dataDetailList) {
						dataDetailList = new ArrayList<Map<String, String>>();
						dataDetailListMap.put(itemPid, dataDetailList);
					}
					dataDetailList.add(dataMap);
				}
				// 获取maxTreeDataCount
				for (int i = 0; i < allLevelColumnDetailMaxCountJsonArray.length(); i++) {
					JSONObject jsonObj = allLevelColumnDetailMaxCountJsonArray.getJSONObject(i);
					maxDataDetailCount = jsonObj.getInt("max_item_count_");
				}
			}
			// 迭代valuesMapping
			JSONObject valuesMappingJson = new JSONObject();
			for (int i = 0; i < valuesMappingJsonArray.length(); i++) {
				JSONObject jsonObj = valuesMappingJsonArray.getJSONObject(i);
				String dictId = jsonObj.getString("dict_id_");
				String columnId = jsonObj.getString("column_id_");
				JSONArray valuesMappingJsonColumns = null;
				String savedData = jsonObj.getString("saved_data_");
				try {
					if (valuesMappingJson.has(dictId)) {
						valuesMappingJsonColumns = valuesMappingJson.getJSONArray(dictId);
					} else {
						valuesMappingJsonColumns = new JSONArray();
						valuesMappingJson.put(dictId, valuesMappingJsonColumns);
					}
					JSONObject valueJson = new JSONObject();
					valueJson.put("columnId", columnId);
					valueJson.put("savedData", savedData);
				} catch (JSONException e) {
					throw new SQLException("grant value exception");
				}
			}
			// 返回结果
			ObjectMapper mapper = new ObjectMapper();
			treeTableJson = jsonRootObj.toString();
			treeDataMapping = mapper.writeValueAsString(dataDetailListMap);
			maxTreeLevel = maxLevel;
			maxTreeDataCount = maxDataDetailCount;
			valuesMapping = valuesMappingJson.toString();
		}

		StringBuffer returnJsonString = new StringBuffer();
		if(scoreMapping != null){
			returnJsonString.append("{").append("\"treeTableJson\":" + treeTableJson).append(",").append("\"treeDataMapping\":" + treeDataMapping).append(",").append("\"maxTreeLevel\":" + maxTreeLevel).append(",").append("\"maxTreeDataCount\":" + maxTreeDataCount).append(",").append("\"valuesMapping\":" + valuesMapping).append(",").append("\"scoreMapping\":" + scoreMapping).append("}");
		}else{
			returnJsonString.append("{").append("\"treeTableJson\":" + treeTableJson).append(",").append("\"treeDataMapping\":" + treeDataMapping).append(",").append("\"maxTreeLevel\":" + maxTreeLevel).append(",").append("\"maxTreeDataCount\":" + maxTreeDataCount).append(",").append("\"valuesMapping\":" + valuesMapping).append("}");
		}
		return returnJsonString.toString();
	}


	@Override
	public String savedTree2TableData(String savedDataKey, String savedDataDetailJson, Map<String, String> savedDataKeyMap) throws Exception {

		Map<String, Object> propertiesMap = new HashMap<String, Object>();

		for (String key : savedDataKeyMap.keySet()) {
			if (key.startsWith("savedDataKey")) {
				String value = savedDataKeyMap.get(key);
				if (null != value) {
					propertiesMap.put(key, value);
				}
			}
		}
		BaseDocumentSavedInfo baseDocumentSavedInfo = (BaseDocumentSavedInfo) this.tableDao.getNewOrUpdateObject(BaseDocumentSavedInfo.class, propertiesMap);
		this.tableDao.copyAndOverrideExistedValueFromStringMap(savedDataKeyMap, baseDocumentSavedInfo, null);
		this.tableDao.saveOrUpdateEntity(baseDocumentSavedInfo);
		// this.tableDao.getHibernateTemplate().clear();
		this.tableDao.updateByHSQL("delete from BaseDocumentSavedData bdsd where bdsd.savedInfo.id= ?", baseDocumentSavedInfo.getId());
		JSONObject savedDataJsonObj = new JSONObject(savedDataDetailJson);
		Iterator iter = savedDataJsonObj.keys();
		// int index = 0;
		while (iter.hasNext()) {
			String dictId = iter.next().toString();
			BaseDocumentConfig dict = (BaseDocumentConfig) this.tableDao.findEntityByID(BaseDocumentConfig.class, dictId);
			JSONArray savedDataJSONArray = savedDataJsonObj.getJSONArray(dictId);
			for (int i = 0; i < savedDataJSONArray.length(); i++) {
				JSONObject data = savedDataJSONArray.getJSONObject(i);
				String columnId = data.getString("columnId");
				String savedData = data.getString("savedData");
				BaseDocumentSavedData baseDocumentSavedData = new BaseDocumentSavedData();
				baseDocumentSavedData.setSavedInfo(baseDocumentSavedInfo);
				baseDocumentSavedData.setDict(dict);
				baseDocumentSavedData.setSavedData(savedData);
				BaseDocumentConfigData column = (BaseDocumentConfigData) this.tableDao.findEntityByID(BaseDocumentConfigData.class, columnId);
				;
				baseDocumentSavedData.setColumn(column);
				this.tableDao.saveEntity(baseDocumentSavedData);
				// if((++index % 50)==0 )
				{
					this.tableDao.updateFlush();
					this.tableDao.getHibernateTemplate().evict(baseDocumentSavedData);
				}
			}
		}
		return null;
	}

	@Override
	public String getCollectionJsonString(Collection<?> collection, HashMap<String, String> mappingField) throws Exception {
		Iterator<?> it = collection.iterator();
		JSONArray collectionJsonArray = new JSONArray();
		while (it.hasNext()) {
			Object entityObject = (Object) it.next();
			Class<?> entityClass = entityObject.getClass();
			PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(entityClass);
			JSONObject jsonObject = new JSONObject();

			for (PropertyDescriptor pd : pds) {
				Method readMethod = pd.getReadMethod();
				Class<?> readMethodClass = readMethod.getReturnType();
				if (!Collection.class.isAssignableFrom(readMethodClass)) {
					Object valueObject = readMethod.invoke(entityObject);
					if (valueObject == null) {
						continue;
					}

					if (BeanFieldUtil.isWrapClass(readMethodClass)) {// 基础类型
						jsonObject.putOpt(pd.getName().toLowerCase(), valueObject);
					} else {// 引用类型
						String readMethodName = readMethodClass.getSimpleName();
						Set<String> mappingNameSet = mappingField.keySet();
						if (mappingField.get(readMethodName) == null) {// 如果匹配字段集合中不包含引用类型，则默认添加ID
							Class<?> citeClass = valueObject.getClass();

							PropertyDescriptor citePd = BeanUtils.getPropertyDescriptor(citeClass, "id");
							Method citeMethod = citePd.getReadMethod();
							Object value = citeMethod.invoke(valueObject);
							jsonObject.putOpt(pd.getName().toLowerCase(), value);
						}

						for (String valueName : mappingNameSet) {
							if (valueName.toLowerCase().startsWith(readMethodName.toLowerCase())) {
								// String perfixName = valueName;
								String suffixName = "";
								if (valueName.indexOf(".") != -1) {
									// perfixName = valueName.substring(0,
									// valueName.indexOf("."));
									suffixName = valueName.substring(valueName.indexOf("."));
								}

								String citeFieldName = mappingField.get(valueName);// 引用类的引用字段
								if (citeFieldName != null) {
									Class<?> citeClass = valueObject.getClass();

									PropertyDescriptor citePd = BeanUtils.getPropertyDescriptor(citeClass, citeFieldName);
									Method citeMethod = citePd.getReadMethod();
									Object value = citeMethod.invoke(valueObject);

									jsonObject.putOpt(pd.getName().toLowerCase() + suffixName, value);

									// 对数据字典的特殊处理
									if (valueName.equals("DictionaryData")) {
										PropertyDescriptor namePd = BeanUtils.getPropertyDescriptor(citeClass, "name");
										Method nameMethod = namePd.getReadMethod();
										String rawValue = (String) nameMethod.invoke(valueObject);

										jsonObject.putOpt("rawValue_" + pd.getName().toLowerCase() + suffixName, rawValue);

									}

								}
							}
						}
					}
				} else {
					continue;
				}
			}
			if (jsonObject != null) {
				collectionJsonArray.put(jsonObject);
			}

		}
		return collectionJsonArray.toString();
	}

	/**
	 * 
	 * @param variablesMap
	 * @param projInfo
	 *            当前合同实例
	 * @param processDefinitionId
	 *            流程实例id
	 * @throws Exception
	 */
	@Override
	public void getAttachment(Map<String, String> variablesMap, String project_id, String processDefinitionId) throws Exception {
		// 将项目立项里面上传的附件带过来
		List<Long> dbid = this.tableDao.findResultsByHSQL("select jhi.historyProcessInstanceImpl.dbid  from JBPMWorkflowHistoryInfo jhi  where  jhi.processDefinitionId=? and jhi.keyNine = ? and jhi.historyTaskInstanceImpl is  null", processDefinitionId, project_id);
		if (!dbid.isEmpty()) {
			Long projApproveProcessInstanceDBID = dbid.get(0);
			variablesMap.put("projApproveProcessInstanceDBID", projApproveProcessInstanceDBID.toString());
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateRemoteOper(Map<String, String> model, OperTypeEnum operType) throws Exception {

		String entityClassName = model.get("entityClassName");
		Class beanClazz = Class.forName(entityClassName);

		List entityBeanList = new ArrayList();
		List entityIdList = new ArrayList<String>();

		String entityBeanCallBackClassName = model.get("entityBeanCallBackClassName");
		EntityBeanCallBack entityBeanCallBack = null;

		if (!StringUtils.isBlank(entityBeanCallBackClassName)) {
			entityBeanCallBack = (EntityBeanCallBack) Class.forName(entityBeanCallBackClassName).newInstance();
		}
		switch (operType) {
			case ADD:
			case COPY: {
				ApConvertor.text2Html(model);
				Object entityBean = ApConvertor.getBeanByMap(this, model, beanClazz);
				entityBeanList.add(entityBean);
				break;
			}
			case EDIT: {
				String idStr = model.get("id");
				entityIdList.add(idStr);
				Object entityBean = this.findEntityByID(beanClazz, idStr);
				entityBeanList.add(entityBean);
				ApConvertor.text2Html(model);
				ApConvertor.copyPropertiesByMap(this, model, entityBean);
				break;
			}
			case REMOVE: {
				String[] idsArr = model.get("id").split(",");
				int idsLen = idsArr.length;
				for (int i = 0; i < idsLen; i++) {
					String idStr = idsArr[i];
					entityIdList.add(idStr);
				}
				entityBeanList = this.findEntityByIDArray(beanClazz, idsArr);
				break;
			}
		}
		if (null != entityBeanCallBack) {// before persistent
			entityBeanCallBack.beforePersistent(this, entityBeanList, entityIdList, model, operType);
		}
		switch (operType) {
		case ADD:
		case COPY: {
			this.saveAllEntities(entityBeanList);
			for (Object entityBean : entityBeanList) {
				entityIdList.add((String) ReflectionUtil.invokeGetterMethod(entityBean, "id"));
			}
			break;
		}
		case EDIT: {
			this.updateAllEntities(entityBeanList);
			break;
		}

		case REMOVE: {
			this.removeAllEntites(entityBeanList);
			break;
		}
		}
		if (null != entityBeanCallBack) {// after persistent
			entityBeanCallBack.afterPersistent(this, entityBeanList, entityIdList, model, operType);
		}
	}
	public BaseFile saveUpFiletoService(MultipartFile multipartFile, Map<String, String> modelData) throws Exception {
		// TODO Auto-generated method stub
		BaseFile bf = new BaseFile();
		if(modelData.containsKey("id")){
			modelData.remove("id");
		}
		String SystemDate = DateUtil.getSystemDate();
		Map<String, String> classFieldMapping = new HashMap<String, String>();
		this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(modelData, bf, classFieldMapping);
		bf.setFileName(multipartFile.getOriginalFilename());
		InputStream source =null;
		try {
			 source = multipartFile.getInputStream();
			String uploadDirPath = getuploadFileAllDir(modelData.get("currentTableId"));
			String dirpath = getuploadFileDir(modelData.get("currentTableId"));
			String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			String fileName = UUID.randomUUID() + suffix;
			String uploadedFileFullPath = uploadDirPath + fileName;
			bf.setFileAddress(dirpath + fileName);
			ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, source, null);
			bf.setId("");
			bf.setInvalid("是");
			this.getBussinessDao().saveEntity(bf);
			this.addlogFileOper(bf, "上传");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e.getMessage());
		}finally{
			if(null!=source){
				FileUtil.safeCloseInputStream(source);
			}
		}
		return bf;
	}
	@Override
	public String getuploadFileAllDir(String key) throws Exception {
		// TODO Auto-generated method stub
		String SystemDate = DateUtil.getSystemDate();
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + ResourceUtil.getWordFilesRelativeStorePath() + "/";
		String dirpath = key + "/" + SystemDate.replaceAll("-", "/") + "/";
		return uploadDirPath + dirpath;
	}

	@Override
	public String getuploadFileDir(String key) throws Exception {

		String SystemDate = DateUtil.getSystemDate();
		String dirpath = key + "/" + SystemDate.replaceAll("-", "/") + "/";
		return dirpath;
	}
	public void addlogFileOper(BaseFile bf, String operType) throws Exception {
		// TODO Auto-generated method stub
		BaseFileRecorder bfr = new BaseFileRecorder();
		bfr.setBaseFile(bf);
		bfr.setOperatorType(operType);
		boolean isWithUser = true;
		User user = null;
		try {
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			isWithUser = false;
		}
		if (isWithUser) {
			String systemDate = DateUtil.getSystemDateTime();
			bfr.setCreator(user);
			bfr.setCreateDate(systemDate);
		}
		this.getBussinessDao().saveEntity(bfr);

	}

	@Override
	public String getDataAuthorityCondtion(String dataAuthority) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		String condtion ="";
		try {
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new BusinessException("取查询条件时没有获得当前用户");
		}
		final String userid=user.getId();
		final String str_dataAuthority=dataAuthority;
		try {		    	
	    	 condtion = (String) this.getBussinessDao().getJdbcTemplate().execute(  
	    		     new CallableStatementCreator() {  
	    		        public CallableStatement createCallableStatement(Connection con) throws SQLException {  
	    		           String storedProc = "{call proc_get_authority_condition(?,?,?)}";// 调用的sql  		    		           
	    		           CallableStatement cs = con.prepareCall(storedProc);  
	    		           cs.setString(1,userid );// 设置输入参数的值  
	    		           cs.setString(2, str_dataAuthority);
	    		           cs.registerOutParameter(3,OracleTypes.VARCHAR);// 注册输出参数的类型  
	    		           return cs;  
	    		        }  
	    		     }, new CallableStatementCallback() {  
	    		         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {  
	    		           cs.execute();  
	    		           return cs.getString(3); 
	    		     }  
	    	});  
	    } catch (Exception e) {
			condtion="";
		}
		if(null==condtion){condtion="";}
		if(!"".equals(condtion)){
			condtion=condtion.replaceFirst("or", "and");
			condtion="  where 1=1  "+condtion;
		}
		System.out.println(condtion);
		return condtion;	
	}
	
	
	@Override
	public String getRuleScore(String fieldValue, String ruleId)
			throws Exception {
		BaseRole role = this.findEntityByID(BaseRole.class, ruleId);
		Set<BaseRoleBlock> blocks =  role.getBlocks();
		Double field = Double.parseDouble(fieldValue);
		BaseRoleBlock existBlock = null;
		for(BaseRoleBlock block : blocks){
			//判断fieldValue 处于哪个规则块中
			Double maxFieldValue =  block.getMaxFieldValue() == null ? Double.MAX_VALUE : block.getMaxFieldValue();
			Double minFieldValue = block.getMinFieldValue() == null ? Double.MIN_VALUE : block.getMinFieldValue();
			Boolean upperFlag = block.getMaxOpenOrClose() ? field <= maxFieldValue : field < maxFieldValue;
			Boolean lowerFlag = block.getMinOpenOrClose() ? field >= minFieldValue : field > minFieldValue;
			if(upperFlag && lowerFlag){
				existBlock = block;
				break;
			}
		}
		if(existBlock != null){
			int scale = role.getValueScale() == null ? 0 : role.getValueScale();
			BigDecimal baseFieldValue =  existBlock.getBaseFieldValue() == null ? null : new BigDecimal(existBlock.getBaseFieldValue());
			BigDecimal baseValue = existBlock.getBaseValue() == null ? null : new BigDecimal(existBlock.getBaseValue());
			BigDecimal incrementValue =  existBlock.getValueIncrement() == null ? null : new BigDecimal(existBlock.getValueIncrement());
			BigDecimal incrementField = existBlock.getFieldIncrement() == null ? null : new BigDecimal( existBlock.getFieldIncrement());
			if(incrementField != null && incrementField.compareTo(BigDecimal.ZERO) != 0 && incrementValue != null && incrementValue.compareTo(BigDecimal.ZERO) != 0 ){
				BigDecimal value =  (new BigDecimal(field).subtract(baseFieldValue)).multiply(incrementValue).divide(incrementField, scale + 1, BigDecimal.ROUND_FLOOR).add(baseValue);
				switch (role.getRoleCalType()) {
				case ROUND:
					value = value.setScale(scale, BigDecimal.ROUND_HALF_UP);
					break;
				case CEIL:
					value = value.setScale(scale, BigDecimal.ROUND_CEILING);
					break;
				case FLOOR:
					value = value.setScale(scale, BigDecimal.ROUND_FLOOR);
					break;	
				default:
					break;
				}
				if(value.compareTo(new BigDecimal(role.getMaxValue() == null ? Double.MAX_VALUE : role.getMaxValue())) >0 ){
					return new BigDecimal(role.getMaxValue()).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
				}else if(value.compareTo(new BigDecimal(role.getMinValue() == null ? Double.MIN_VALUE : role.getMinValue())) < 0){
					return new BigDecimal(role.getMinValue()).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
				}else{
					return value.toString();
				}
			}else{
				return existBlock.getBaseValue() == null ? "0" : existBlock.getBaseValue().toString();
			}
		}else{
			return role.getMinValue() == null ? "0" : role.getMinValue().toString();
		}
	}
	
	
}