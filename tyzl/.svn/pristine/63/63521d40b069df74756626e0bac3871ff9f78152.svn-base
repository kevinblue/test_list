package com.tenwa.report.formatter;

import static com.tenwa.kernal.utils.StringUtil.nullToString;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import org.apache.commons.lang3.tuple.MutablePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.CommService;
import com.tenwa.kernal.utils.EhcacheUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.entity.ReportColumn;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.enums.ColumnDataType;
import com.tenwa.report.query.CellData;

public class DefaultTableFormatter implements TableFormatter {
	private static final Logger log = LoggerFactory.getLogger(DefaultTableFormatter.class);
	
	protected static final String cacheName = "reportCache";
	private Map<String, MutablePair<ColumnDataType, String>> registedFormater = new HashMap<String, MutablePair<ColumnDataType, String>>();
	private boolean generateHtml = true;	
	private Integer totalCount = 0;
	

	private ReportTable table;
	private List<Map<String, CellData>> datas;

	@Override
	public JSONObject generateJson() throws Exception {
		registerDataFormater();
		formatData();
		JSONObject queryResult = new JSONObject();
		queryResult.put("datas", covertToJsonArray(datas));
		queryResult.put("totalCount", totalCount);
		queryResult.put("code", "");
		return queryResult;
	}

	@Override
	public void setDataSet(List<Map<String, CellData>> datas) {
		this.datas = datas;
	}

	@Override
	public void setTable(ReportTable table) {
		this.table = table;
	}
	@Override
	public void setGenerateHtml(boolean generateHtml) {
		this.generateHtml = generateHtml;
	}

	@Override
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	private void formatData(){
		int dataLen = datas.size()-1;
		for(int i = 0 ; i < this.datas.size() ;i++){
			Map<String,CellData> rowDatas = datas.get(i);
			for(CellData cellData:rowDatas.values()){
				cellData = format(cellData);
				if(this.generateHtml){
					cellData = formatSpecialChar(cellData);
				}
			}
			if(this.generateHtml){
				if(i == dataLen){
					rowDatas = formatColorAndLink(rowDatas,true);
				}else{
					rowDatas = formatColorAndLink(rowDatas);
				}
			}
		}
		
	}

	protected CellData format(CellData cellData) {
		String columnName = cellData.getColumnName();
		String columnValue = cellData.getRawValue();
		if (columnValue == null || columnName == null) {
			return cellData;
		}
		if (this.registedFormater.containsKey(columnName.toUpperCase())) {

			MutablePair<ColumnDataType, String> formater = this.registedFormater.get(columnName.toUpperCase());
			ColumnDataType cdt = formater.getLeft();
			switch (cdt) {
			case NUMBER:
				try {
					DecimalFormatSymbols symbols = new DecimalFormatSymbols(WebUtil.getSessionLocale());
//					log.debug("columnValue:{}",columnValue);
					String temp = columnValue.replaceAll(String.valueOf(symbols.getGroupingSeparator()), "");

					Double value = Double.parseDouble(temp);
					DecimalFormat f = new DecimalFormat(formater.getRight());
//					log.debug("formatted value:{}",f.format(value));
					cellData.setDisplayValue(f.format(value));

				} catch (Exception e) {
					log.warn("数值为空或者数值格式不正确！");
				}
				break;
			case DATE:
				try {
					SimpleDateFormat f = new SimpleDateFormat(formater.getRight());
					cellData.setDisplayValue(f.format(f.parse(columnValue)));
				} catch (Exception e) {
					log.warn("日期为空或者日期格式不正确");
				}
				break;
			case DICT: {
				String dictName = formater.getRight();
				if (dictName == null || "".equals(dictName.trim())) {
					break;
				} else {
					Serializable cacheValue = EhcacheUtil.getCachedValue(cacheName, columnValue.toUpperCase());
					if (cacheValue != null) {
						cellData.setDisplayValue(cacheValue.toString());
						columnValue = cacheValue.toString();
					} else {
						CommService service = (CommService) WebUtil.getApplicationContext().getBean("commService");
						try {
							List<DictionaryData> dictDatas = service.findDictValue(dictName.trim());
							if (dictDatas != null) {
								for (DictionaryData data : dictDatas) {
									EhcacheUtil.setCachedValue(cacheName, data.getCode().toUpperCase(), data.getName());
									if (data.getCode().equalsIgnoreCase(columnValue.trim())) {
										cellData.setDisplayValue(data.getName());
									}
								}
							}
						} catch (Exception e) {
							log.error("", e);
						}
					}
				}

				break;
			}
			case STRING:
			default:
				break;
			}

		}
		return cellData;
	}

	private void formatColor(ReportColumn column, Map<String, CellData> datas, CellData cellData) {
		String colorExpression = column.getColor();
		if (colorExpression.indexOf(":") < 0) {
			colorExpression.replaceAll("\"", "");
			cellData.setDisplayValue("<span style='color:" + colorExpression + "'>" + cellData.getDisplayValue() + "</span>");

		} else {
			if (!colorExpression.startsWith("{")) {
				colorExpression = "{" + colorExpression;
			}
			if (!colorExpression.endsWith("}")) {
				colorExpression = colorExpression + "}";
			}
			try {
				JSONObject colorJson = new JSONObject(colorExpression);

				@SuppressWarnings("unchecked")
				Iterator<String> colors = colorJson.keys();
				while (colors.hasNext()) {
					String color = colors.next();
					String expression = colorJson.getString(color);
					Object value = FormatterUtil.evaluate(expression, datas);
					if (value == null) {
						continue;
					}
					if ((Boolean) value) {
						cellData.setDisplayValue("<span style='color:" + color + "'>" + cellData.getDisplayValue() + "</span>");
					}
				}
			} catch (JSONException e) {
				log.error("", e);
			}
		}

	}

	protected void formatLink(ReportColumn column, Map<String, CellData> datas, CellData cellData,Boolean ... isLast) {
		String condition = column.getActionCondition();
		String paramString = "";
		boolean hasLink = false;
		if (condition != null && !"".equals(condition.trim())) {
			Object isTrue = FormatterUtil.evaluate(condition, datas);
			if (isTrue == null) {
				return;
			} else if ((Boolean) isTrue) {
				paramString = StringUtil.nullToString(column.getActionParamters());
				hasLink = true;
			} else {
				return;
			}

		} else {
			hasLink = true;
		}

		if (!hasLink) {
			return;
		}

		if (!"".equals(paramString)) {
			Matcher m = null;
			while ((m = FormatterUtil.parameter_pattern.matcher(paramString)).find()) {
				CellData tempData = datas.get(paramString.substring(m.start() + 1, m.end() - 1).toLowerCase());
				String paramValue = StringUtil.nullToString(tempData.getRawValue());
				paramString = m.replaceFirst(paramValue);
			}
		}
		String  actionParams =  column.getActionParamters() ; 
		String paramsValues = ""; 
		if(null != actionParams){
			String [] params =  actionParams.split("\\|");
			for(String param : params){
				for(String key : datas.keySet()){
					CellData data = datas.get(key);
					if(data.getColumnName().equalsIgnoreCase(param)){
						paramsValues += data.getDisplayValue()+"|";
						break;
					}
				}
			}
			if(paramsValues.length() > 0 ){
				paramsValues = paramsValues.substring(0,paramsValues.length()-1);
			}
		}
		if(!(null != isLast && isLast.length > 0 && isLast[0])){
			cellData.setDisplayValue("<a href=\"javascript:void(0)\" onclick=\"report_openLink('" + WebUtil.getWebContextPath() + "','"
					+ StringUtil.nullToString(column.getActionType()) + "','" + column.getAction() + "','"+column.getName()+"','" + cellData.getDisplayValue()+ "','"+actionParams+ "','"+paramsValues+"')\">" + cellData.getDisplayValue() + "</a>");
		}
	}

	protected CellData formatSpecialChar(CellData cellData) {
		String columnValue = cellData.getDisplayValue();
		if (columnValue == null)
			return cellData;
		columnValue = columnValue.trim();
		if ("".equals(columnValue)) {
			cellData.setDisplayValue("&nbsp;");
		}
		if ("-".equals(columnValue)) {
			cellData.setDisplayValue("<div style='text-align:center;width:100%'>" + columnValue + "</div>");
		}
		return cellData;
	}

	protected Map<String, CellData> formatColorAndLink(Map<String, CellData> datas,Boolean ... isLast) {
		Set<ReportColumn> columnSettings = this.getColumns();
		Boolean flag = false;
		for(ReportColumn column : columnSettings){
			if(column.getIsCountSubTotal() || column.getIsCountTotal()){
				flag = true;
				break;
			}
		}
		for (ReportColumn column : columnSettings) {
			
			CellData cellData = datas.get(column.getName().toLowerCase());
			if (column.getColor() != null && !"".equals(column.getColor().trim())) {
				formatColor(column, datas, cellData);

			}
			if (column.getAction() != null && !"".equals(column.getAction().trim())) {
				if(flag && isLast.length > 0 &&  isLast[0]){
					isLast[0] = true;
					formatLink(column, datas, cellData,isLast);
				}else{
					formatLink(column, datas, cellData);
				}
			}

		}
		return datas;
	}

	protected void registerDataFormater() {
		Set<ReportColumn> columns = getColumns();
		if (columns == null)
			return;
		for (ReportColumn column : columns) {
			if (!"".equals(nullToString(column.getFormater()))) {
				MutablePair<ColumnDataType, String> p = new MutablePair<ColumnDataType, String>();
				p.setLeft(column.getColumnDataType());
				p.setRight(column.getFormater());
				this.registedFormater.put(column.getName().toUpperCase(), p);
			}
		}
	}

	private Set<ReportColumn> getColumns() {
		return this.table.getReportColumns();
	}


	
	private JSONArray covertToJsonArray(List<Map<String, CellData>> datas) throws JSONException {
		JSONArray jsonDatas = new JSONArray();
		if (datas == null)
			return jsonDatas;
		for (Map<String, CellData> data : datas) {
			JSONObject jsonData = new JSONObject();
			for (String key : data.keySet()) {
				CellData cellData = data.get(key);
				jsonData.put(cellData.getColumnName(), cellData.getDisplayValue());
			}
			jsonDatas.put(jsonData);
		}
		return jsonDatas;

	}

	
}
