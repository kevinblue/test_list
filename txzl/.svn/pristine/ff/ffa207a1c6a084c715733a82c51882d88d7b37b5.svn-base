package com.tenwa.business.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.StringUtil;

public class Table 
{
	private String showSql ;
    private String dataSourceName;
    private String sourceSql;
    private String isPrepared;
    private String predefinedSql;
    private String orderColumn;
    private List<String> replaceKeyList = new ArrayList<String>();
    private List<String> replaceValueList = new ArrayList<String>();
    private String[] summaryFields        = null;
    private String excelSourceSql;
    private Map<String,String> columnTypesMapping = new LinkedHashMap<String,String>();
    private List<Map<String,String>>  datas;
    private JSONArray columnsJsonArray;
    private final Map<String,String> headers = new LinkedHashMap<String,String>();
    public Map<String, String> getHeaders() {
		return headers;
	}
	private String tableXmlLoadingDirectoryFilePath;
    private String tableXmlTableFileNameOrPath;
    private Map<String,String> model;
    private int start = -1;
	private int pageSize = -1;
	private int total = 0;
	private String extractstartSql ;
	private String extractendSql ;
	private String dataAuthority;
   
	
	public String getExtractstartSql() {
		String extractstartSqlTemp = "";
		if(null != extractstartSql ){
			extractstartSqlTemp = this.extractstartSql;
			Map<String,Object> returnMap = QueryUtil.getQueryStringWithSqlFilter(model, extractstartSqlTemp, Boolean.parseBoolean(this.getIsPrepared()));
			extractstartSqlTemp = returnMap.get("transferedText").toString();
		}
		return extractstartSqlTemp;
	}
	public void setExtractstartSql(String extractstartSql) {
		this.extractstartSql = extractstartSql;
	}
	public String getExtractendSql() {
		String extractendSqlTemp = "";
		if(null != extractendSql ){
			extractendSqlTemp = this.extractendSql;
			Map<String,Object> returnMap = QueryUtil.getQueryStringWithSqlFilter(model, extractendSqlTemp, Boolean.parseBoolean(this.getIsPrepared()));
			extractendSqlTemp = returnMap.get("transferedText").toString();
		}
		return extractendSqlTemp;
	}
	public void setExtractendSql(String extractendSql) {
		this.extractendSql = extractendSql;
	}
	public String getSourceSql() {
		return sourceSql;
	}
	public void setSourceSql(String sourceSql) {
		this.sourceSql = sourceSql;
	}
	
	public List<String> getReplaceKeyList() {
		return replaceKeyList;
	}
	public void setReplaceKeyList(List<String> replaceKeyList) {
		this.replaceKeyList = replaceKeyList;
	}
	public List<String> getReplaceValueList() {
		return replaceValueList;
	}
	public void setReplaceValueList(List<String> replaceValueList) {
		this.replaceValueList = replaceValueList;
	}
	@SuppressWarnings("unchecked")
	public String getTargetSummarySql() 
	{
		try 
		{
			if("SQLSERVER".equalsIgnoreCase(ResourceUtil.getDBType())){
				   String noSpaceSql = this.sourceSql.toUpperCase().replaceAll("\\s", "");
				   if(!noSpaceSql.startsWith("SELECTTOP100PERCENT"))
				   {
					   this.sourceSql = "SELECT TOP 100 PERCENT "+this.sourceSql.trim().substring(6);
					   //System.out.println("$$$$$$$$$$$$$EEEE:"+this.sourceSql );
				   }
			}
			//String topSql = ("SQLSERVER".equalsIgnoreCase(ResourceUtil.getDBType()))?" TOP 100 PERCENT " : ""; 
			//String text    = "SELECT "+topSql+"TT_SORT_OUTER.* FROM("+this.sourceSql+")TT_SORT_OUTER /~queryInputRawValue: where   {queryInputRawValueField} like '%{queryInputRawValue}%'~/ /~TableRemoteSortField: ORDER BY  {TableRemoteSortField} {TableRemoteSortDir}~/";
			StringBuffer summarySql = new StringBuffer();
			int index = 0;
			for(String summaryField : this.summaryFields){
				if(++index > 1){
					summarySql.append(",");
				}
				summarySql.append(" NVL(SUM(TT_SORT_OUTER."+summaryField+"),0) AS "+summaryField);
			}
			String text    = "SELECT "+summarySql.toString()+" FROM("+this.sourceSql+")TT_SORT_OUTER /~queryInputRawValue: where   {queryInputRawValueField} like '%{queryInputRawValue}%'~/";
			Map<String,Object> returnMap = QueryUtil.getQueryStringWithSqlFilter(model, text, Boolean.parseBoolean(this.getIsPrepared()));
			String targetSql = returnMap.get("transferedText").toString();
			@SuppressWarnings("rawtypes")
			List[] transferedKeyValueListArr = (List[])returnMap.get("transferedKeyValueListArr");
			this.setReplaceKeyList((List<String>)transferedKeyValueListArr[0]);
			this.setReplaceValueList((List<String>)transferedKeyValueListArr[1]);
			return targetSql;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "";
	}
	@SuppressWarnings("unchecked")
	public String getTargetSql(Boolean... isShow) 
	{
		try 
		{
			String extractstartSql = null;
			String extractendSql = null;
			Map<String, String> results =  removeSqlStartEnd(this.sourceSql,extractstartSql,extractendSql);
			this.sourceSql = results.get("sql");
			extractstartSql = results.get("startSql");
			extractendSql = results.get("endSql");
			if(null != extractstartSql){
				this.extractstartSql = extractstartSql+" ";
			}
			if(null != model.get("TableRemoteSortField")  && 0< model.get("TableRemoteSortField").trim().length()){
				this.extractendSql  = "";
				/*if(extractendSql != null && extractendSql.replaceAll("\\s", "").toLowerCase().indexOf("order")>=0){
					this.extractendSql += " ,"+model.get("TableRemoteSortField") +" "+ ((null == model.get("TableRemoteSortDir") ||  0 >= model.get("TableRemoteSortDir").trim().length()) ? "" : model.get("TableRemoteSortDir")); 
				}else{*/
				this.extractendSql += " ORDER BY "+model.get("TableRemoteSortField") +" "+ ((null == model.get("TableRemoteSortDir") ||  0 >= model.get("TableRemoteSortDir").trim().length()) ? "" : model.get("TableRemoteSortDir"));
				/*}*/
			}
			
			
			if(extractendSql!=null && null == this.extractendSql){
				this.extractendSql = extractendSql + (null == this.extractendSql ? "" : this.extractendSql);
			}
		
		
			if("SQLSERVER".equalsIgnoreCase(ResourceUtil.getDBType())){
				   String noSpaceSql = this.sourceSql.toUpperCase().replaceAll("\\s", "");
				  
				   if(!noSpaceSql.startsWith("SELECTTOP100PERCENT"))
				   {
					   this.sourceSql = "SELECT TOP 100 PERCENT "+this.sourceSql.trim().substring(6);
					   //System.out.println("$$$$$$$$$$$$$EEEE:"+this.sourceSql );
				   }
			}
			
			String topSql = ("SQLSERVER".equalsIgnoreCase(ResourceUtil.getDBType()))?" TOP 100 PERCENT " : "";
			
			String text    = "SELECT "+topSql+"TT_SORT_OUTER.* FROM("+this.sourceSql+")TT_SORT_OUTER /~queryInputRawValue: where   <<queryInputRawValueField>> like '%{queryInputRawValue}%'~/ ";
			Boolean isPrepared = Boolean.parseBoolean(this.getIsPrepared());
			if(null != isShow && 0< isShow.length){
				if(isShow[0]){
					isPrepared = false;
				}
			}
			Map<String,Object> returnMap = QueryUtil.getQueryStringWithSqlFilter(model, text, isPrepared );
			String targetSql = returnMap.get("transferedText").toString();
			
			@SuppressWarnings("rawtypes")
			List[] transferedKeyValueListArr = (List[])returnMap.get("transferedKeyValueListArr");
			this.setReplaceKeyList((List<String>)transferedKeyValueListArr[0]);
			this.setReplaceValueList((List<String>)transferedKeyValueListArr[1]);
			if(null != isShow && 0< isShow.length){
				if(isShow[0]){
					targetSql = (null == this.extractstartSql  ? " " : this.extractstartSql) + targetSql+ " " +(null == this.extractendSql ? " " : this.extractendSql );
				}
			}
			return targetSql;
			//return QueryUtil.getQueryString(model, "SELECT "+topSql+"TT_SORT_OUTER.* FROM("+this.sourceSql+")TT_SORT_OUTER /~TableRemoteSortField: ORDER BY  {TableRemoteSortField} {TableRemoteSortDir}~/");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "";
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	public String getExcelTargetSql() 
	{
		/*try 
		{
			return QueryUtil.getQueryString(model, this.excelSourceSql);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "";*/
		try 
		{
			Map<String, String> results =  removeSqlStartEnd(this.excelSourceSql,null,null);
			this.excelSourceSql = results.get("sql");
			this.sourceSql = results.get("sql");
			Map<String,Object> returnMap = QueryUtil.getQueryStringWithSqlFilter(model, this.excelSourceSql,Boolean.parseBoolean(this.getIsPrepared()) );
			String targetSql = returnMap.get("transferedText").toString();
			List[] transferedKeyValueListArr = (List[])returnMap.get("transferedKeyValueListArr");
			this.setReplaceKeyList((List<String>)transferedKeyValueListArr[0]);
			this.setReplaceValueList((List<String>)transferedKeyValueListArr[1]);
			return targetSql;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "";
	}
	public List<Map<String,String>> getDatas() 
	{
		return datas;
	}
	public void setDatas(List<Map<String,String>> datas) 
	{
		this.datas = datas;
	}
	public void setDataSourceName(String dataSourceName) 
	{
		this.dataSourceName = dataSourceName;
	}
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setModel(Map<String,String> model) {
		this.model = model;
	}
	public Map<String,String> getModel() {
		return model;
	}
	public String getTableXmlLoadingDirectoryFilePath() {
			return tableXmlLoadingDirectoryFilePath;
	}
	public void setTableXmlLoadingDirectoryFilePath(
				String tableXmlLoadingDirectoryFilePath) {
			this.tableXmlLoadingDirectoryFilePath = tableXmlLoadingDirectoryFilePath;
	}
	public String getTableXmlTableFileNameOrPath() {
			return tableXmlTableFileNameOrPath;
	}
	public void setTableXmlTableFileNameOrPath(String tableXmlTableFileNameOrPath) {
			this.tableXmlTableFileNameOrPath = tableXmlTableFileNameOrPath;
	}
	public String getXmlTableFileFullPathWithoutFileSeparator()
	{
		return (this.tableXmlLoadingDirectoryFilePath+this.tableXmlTableFileNameOrPath).replaceAll("(\\\\)|(/)|(%5C)","").replaceAll("\\s","");
	}
    public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setExcelSourceSql(String excelSourceSql) {
		this.excelSourceSql = excelSourceSql;
	}
	public String getExcelSourceSql() {
		return excelSourceSql;
	}
	public JSONArray getTableJsonArrayData() throws Exception
	{
		JSONArray  jsonArrayData = new JSONArray();
		for(int i=0;i<this.datas.size();i++ )
		{
			JSONObject jsonObj = new JSONObject();
			for(String nameMapping : this.headers.keySet()){
				String jsonKey = StringUtil.getJsonString(nameMapping).toLowerCase();
				String jsonValue = StringUtil.getJsonString(this.datas.get(i).get(nameMapping));
				if("iconcls".equalsIgnoreCase(jsonKey))
				{
					jsonKey = "iconCls";
				}
				else if(jsonKey.startsWith("rawvalue_")){
					jsonKey = jsonKey.replace("rawvalue_","rawValue_");
				}
				
				jsonObj.put(jsonKey, jsonValue);
			}
			jsonArrayData.put(jsonObj);
		}
		return jsonArrayData;
	}
	public String getTableJsonArrayStringDatas() throws Exception
	{
		StringBuffer jsonArrayString = new StringBuffer("[");
		for(int i=0 ; i<this.datas.size(); i++ )
		{
			if(0 < i){
				jsonArrayString.append(",");
			}
			jsonArrayString.append("{");
			//JSONObject jsonObj = new JSONObject();
			int j = 0;
			for(String nameMapping : this.headers.keySet()){
				String jsonKey = StringUtil.getJsonString(nameMapping).toLowerCase();
				//moditify by zhangc 2015/11/19 将'替换成转义符
				String jsonValue = StringUtil.getJsonString(this.datas.get(i).get(nameMapping));
				if("iconcls".equalsIgnoreCase(jsonKey))
				{
					jsonKey = "iconCls";
				}
				else if(jsonKey.startsWith("rawvalue_")){
					jsonKey = jsonKey.replace("rawvalue_","rawValue_");
				}
				if(0 < j++){
					jsonArrayString.append(",");
				}
				jsonArrayString.append("\""+jsonKey+"\":\""+jsonValue+"\"");
				//jsonObj.put(jsonKey, jsonValue);
			}
			jsonArrayString.append("}");
			//jsonArrayData.put(jsonObj);
		}
		jsonArrayString.append("]");
		return jsonArrayString.toString();
	}
	public void setShowSql(String showSql) {
		this.showSql = showSql;
	}
	
	
	public String getShowSql() {
		return showSql;
	}
	public boolean isShowSql()
	{
		return "true".equalsIgnoreCase(this.showSql);
	}
	
	public void setColumnsJsonArray(JSONArray columnsJsonArray) throws Exception {
		headers.clear();
		for(int i=0;i<columnsJsonArray.length();i++){
			JSONObject columnObj = columnsJsonArray.getJSONObject(i);
			headers.put(columnObj.getString("mapping"), columnObj.getString("header"));
		}
		this.columnsJsonArray = columnsJsonArray;
	}
	public JSONArray getColumnsJsonArray() {
		return columnsJsonArray;
	}
	public void setColumnTypesMapping(Map<String,String> columnTypesMapping) {
		this.columnTypesMapping = columnTypesMapping;
	}
	public Map<String,String> getColumnTypesMapping() {
		return columnTypesMapping;
	}
	public String[] getSummaryFields() {
		return summaryFields;
	}
	public void setSummaryFields(String[] summaryFields) {
		this.summaryFields = summaryFields;
	}
	public String getPredefinedSql() {
		
		String targetSql = "";
		if(null!=this.predefinedSql&&(!"".equals(this.predefinedSql))){
		String text=this.predefinedSql;	
		Map<String,Object> returnMap = QueryUtil.getQueryStringWithSqlFilter(model, text, Boolean.parseBoolean(this.getIsPrepared()));
		targetSql = returnMap.get("transferedText").toString();
		}
		return targetSql;
	}
	public void setPredefinedSql(String predefinedSql) {
		this.predefinedSql = predefinedSql;
	}
	public String getOrderColumn() {
		String targetSql = "";
		if(null!=this.orderColumn&&(!"".equals(this.orderColumn))){
			String text=this.orderColumn;	
			Map<String,Object> returnMap = QueryUtil.getQueryStringWithSqlFilter(model, text, Boolean.parseBoolean(this.getIsPrepared()));
			targetSql = "   "+returnMap.get("transferedText").toString();
		}
		return targetSql;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getDataAuthority() {
		return dataAuthority;
	}
	public void setDataAuthority(String dataAuthority) {
		this.dataAuthority = dataAuthority;
	}
	
	private Map<String, String> removeSqlStartEnd(String sourceSql,String extractstartSql,String extractendSql){
		Map<String, String> returnInfo = new HashMap<String, String>();
		Pattern p = Pattern.compile("<EXTRACTSTART>(.*)<EXTRACTSTART>");
		Matcher matcher =  p.matcher(sourceSql);
		if(matcher.find()){
			extractstartSql =   matcher.group(1);
			sourceSql = p.matcher(sourceSql).replaceAll("");
		}
		
		p = Pattern.compile("<EXTRACTEND>(.*)<EXTRACTEND>");
		matcher =  p.matcher(sourceSql);
		if(matcher.find()){
			extractendSql =   matcher.group(1);
			sourceSql = p.matcher(sourceSql).replaceAll("");
		}
		returnInfo.put("sql", sourceSql);
		returnInfo.put("startSql", extractstartSql);
		returnInfo.put("endSql", extractendSql);
		return returnInfo;
	}
	public String getIsPrepared() {
		return isPrepared;
	}
	public void setIsPrepared(String isPrepared) {
		this.isPrepared = isPrepared;
	}
	
	
}

