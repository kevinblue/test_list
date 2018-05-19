package com.tenwa.report.filter.impl;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.CommService;
import com.tenwa.report.entity.ReportContent;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.filter.ReportCombobox;


@Service("jsonCombobox")
public class JsonComboBox implements ReportCombobox{
	private static final Logger log = LoggerFactory.getLogger(DictComboBox.class);
	@Resource(name="commService")
	private CommService commService;
	@Override
	public JSONArray getJson(ReportContent table, ReportFilter filter) {
		
		JSONArray jsArray = new JSONArray();
		try {
			jsArray = new JSONArray(filter.getComboBoxDataSource()) ;
		} catch (Exception e) {
			log.info("json格式不正确！");
			return jsArray;
		}
		return jsArray;
	}
	 

}
