package com.tenwa.report.formatter;

import static com.tenwa.kernal.utils.StringUtil.nullToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.entity.ReportChart;
import com.tenwa.report.entity.ReportColumn;
import com.tenwa.report.enums.ChartType;
import com.tenwa.report.enums.UsageType;
import com.tenwa.report.query.CellData;

public class FunsionChartFormatter implements ChartFormatter {

	private ReportChart chart;

	private List<Map<String, CellData>> datas = new ArrayList<Map<String, CellData>>();

	public void setChart(ReportChart chart) {
		this.chart = chart;
	}

	@Override
	public void setDataSet(List<Map<String, CellData>> datas) {
		this.datas = datas;
	}

	@Override
	public JSONObject generateJson() throws Exception {
		JSONObject chartJson = new JSONObject();
		if (chart.getFusionChart().isSingleSerial()) {
			makeSingleSerialJson(chartJson);
		} else {
			makeMultiSerialJson(chartJson);
		}
		return chartJson;
	}

	private void makeSingleSerialJson(JSONObject chartJson) throws Exception {

		JSONArray dataArray = new JSONArray();
		for (Map<String, CellData> rowData : datas) {
			JSONObject dataJson = new JSONObject();
			String dataColumnName = "";
			for (ReportColumn c : chart.getReportColumns()) {
				CellData d = rowData.get(c.getName().toLowerCase());
				switch (c.getUsageType()) {
				case LABEL:
					dataJson.put("label", d.getRawValue());
					break;
				case DATA:
					if (dataColumnName.equals("")) {
						dataColumnName = d.getColumnName();
					}
					if (dataColumnName.equals(d.getColumnName())) {
						dataJson.put("value", d.getRawValue());
						if (c.getShowValue()) {
							dataJson.put("showValue", "1");
						}
						String link = makeLink(c, rowData, d);
						if (null != link) {
							dataJson.put("link", link);
						}
					}
					break;
				}
			}
			dataArray.put(dataJson);
		}
		chartJson.put("chart", createTitleJson(this.chart));
		chartJson.put("data", dataArray);
	}

	private String makeLink(ReportColumn c, Map<String, CellData> rowData, CellData cellData) throws JSONException {
		if (!"".equals(StringUtil.nullToString(c.getActionType()))) {
			return formatLink(c, rowData, cellData);
		}
		return null;
	}

	private String makeLink(String key, Map<String, CellData> rowData, CellData cellData) throws JSONException {
		for (ReportColumn c : this.chart.getReportColumns()) {
			if (c.getName().equalsIgnoreCase(key)) {
				return makeLink(c, rowData, cellData);
			}
		}
		return null;
	}

	protected String formatLink(ReportColumn column, Map<String, CellData> datas, CellData cellData) throws JSONException {
		String condition = column.getActionCondition();
		String paramString = "";
		boolean hasLink = false;
		if (condition != null && !"".equals(condition.trim())) {
			Object isTrue = FormatterUtil.evaluate(condition, datas);
			if (isTrue == null) {
				return null;
			} else if ((Boolean) isTrue) {
				paramString = StringUtil.nullToString(column.getActionParamters());
				hasLink = true;
			} else {
				return null;
			}

		} else {
			hasLink = true;
		}

		if (!hasLink) {
			return null;
		}

		if (!"".equals(paramString)) {
			Matcher m = null;
			while ((m = FormatterUtil.parameter_pattern.matcher(paramString)).find()) {
				CellData tempData = datas.get(paramString.substring(m.start() + 1, m.end() - 1).toLowerCase());
				String paramValue = StringUtil.nullToString(tempData.getRawValue());
				paramString = m.replaceFirst(paramValue);
			}
		}
		StringBuffer link = new StringBuffer();
		JSONObject actionJson = new JSONObject();

		actionJson.put("contextPath", WebUtil.getWebContextPath());
		actionJson.put("type", StringUtil.nullToString(column.getActionType()));
		actionJson.put("url", column.getAction());
		actionJson.put("parameters", paramString);

		link.append("J-").append("openChartLink-").append(actionJson.toString());
		/*
		 * cellData.setDisplayValue("<a href=\"javascript:void(0)\" onclick=\"report_openLink('" + WebUtil.getWebContextPath() + "','"
		 * + StringUtil.nullToString(column.getActionType()) + "','" + column.getAction() + "','" + paramString + "')\">" + cellData.getDisplayValue() + "</a>");
		 */
		return link.toString();
	}

	private void makeMultiSerialJson(JSONObject chartJson) throws Exception {
		Map<String, JSONArray> dataJsons = new HashMap<String, JSONArray>();

		String labelKey = "";

		for (ReportColumn c : chart.getReportColumns()) {
			switch (c.getUsageType()) {
			case LABEL:
				labelKey = c.getName().toLowerCase();
				break;
			case DATA: {
				dataJsons.put(c.getName().toLowerCase(), new JSONArray());
				break;
			}
			}
		}

		JSONArray categoryJson = new JSONArray();
		for (Map<String, CellData> rowData : datas) {
			JSONObject labelJson = new JSONObject();
			labelJson.put("label", rowData.get(labelKey).getRawValue());
			categoryJson.put(labelJson);
			for (String key : dataJsons.keySet()) {
				String value = rowData.get(key).getRawValue();
				JSONObject valueJson = new JSONObject();
				JSONArray valueJsons = dataJsons.get(key);
				valueJson.put("value", value);
				String link = makeLink(key, rowData, rowData.get(key));
				if (null != link) {
					valueJson.put("link", link);
				}
				valueJsons.put(valueJson);
			}

		}

		JSONArray categoriesJson = new JSONArray();

		categoriesJson.put((new JSONObject()).put("category", categoryJson));
		chartJson.put("categories", categoriesJson);
		JSONArray datasetJson = new JSONArray();
		for (ReportColumn column : chart.getReportColumns()) {
			if (column.getUsageType() == UsageType.DATA) {
				JSONObject dataJson = new JSONObject();
				dataJson.put("seriesname", column.getLabel());
				dataJson.put("data", dataJsons.get(column.getName().toLowerCase()));
				if (column.getShowValue()) {
					dataJson.put("showValues", "1");
				}
				if (!"".equals(nullToString(column.getColor()))) {
					dataJson.put("color", column.getColor());
				}

				// 组合图
				if (chart.getChartType() == ChartType.Combi) {
					if (column.getCombiType() != null && column.getCombiType() != ChartType.Column) {
						dataJson.put("renderas", column.getCombiType().name());
					}
					if (chart.getFusionChart().isDualY()) {
						if (column.getIsRightY()) {
							dataJson.put("parentyaxis", "S");
						} else {
							dataJson.put("parentyaxis", "P");
						}
					}
				}
				datasetJson.put(dataJson);
			}
		}
		chartJson.put("chart", createTitleJson(this.chart));
		chartJson.put("dataset", datasetJson);
	}

	private JSONObject createTitleJson(ReportChart chart2) throws JSONException {
		JSONObject titleJson = new JSONObject();
		titleJson.put("caption", nullToString(chart.getCaption(), chart.getName()));
		if (!"".equals(nullToString(chart.getSubCaption()))) {
			titleJson.put("subcaption", nullToString(chart.getSubCaption()));
		}
		titleJson.put("xaxisname", nullToString(chart.getxAxisName()));

		if (chart.getFusionChart().isDualY()) {
			titleJson.put("pyaxisname", nullToString(chart.getyAxisName()));
			titleJson.put("syaxisname", nullToString(chart.getsAxisName()));
		} else {
			titleJson.put("yaxisname", nullToString(chart.getyAxisName()));
		}
		titleJson.put("showValues", "0");
		titleJson.put("labelDisplay", "wrap");
		return titleJson;
	}
}
