package com.tenwa.report.filter.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tenwa.report.entity.ReportContent;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.filter.ReportCombobox;

@Service("localCombobox")
public class LocalComboBox implements ReportCombobox {
	private static final Logger log = LoggerFactory.getLogger(LocalComboBox.class);

	@Override
	public JSONArray getJson(ReportContent table, ReportFilter filter) {
		JSONArray jsArray = new JSONArray();
		String localValues = filter.getComboBoxDataSource();
		String[] values = null;
		if (localValues.contains("|")) {
			values = localValues.split("\\|");
		} else {
			values = new String[] { localValues };
		}

		for (String v : values) {
			JSONObject jo = new JSONObject();
			if (v.contains(":")) {
				try {
					jo.put("text", v.substring(0, v.indexOf(":")));
					jo.put("value", v.substring(v.indexOf(":") + 1, v.length()));
				} catch (JSONException e) {
					log.error("", e);
				}

			} else {
				// 简略格式：A|B|C
				try {
					jo.put(filter.getComboBoxValueField() != null && filter.getComboBoxValueField().length()>0 ?filter.getComboBoxValueField():"value", v);
					jo.put(filter.getComboBoxNameField()!= null && filter.getComboBoxNameField().length()>0 ? filter.getComboBoxNameField(): "text", v);
				} catch (JSONException e) {
					log.error("", e);
				}

			}
			jsArray.put(jo);
		}
		return jsArray;
	}

}
