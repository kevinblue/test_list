package com.tenwa.report.filter.impl;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.CommService;
import com.tenwa.report.entity.ReportContent;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.filter.ReportCombobox;


@Service("dictCombobox")
public class DictComboBox implements ReportCombobox{
	private static final Logger log = LoggerFactory.getLogger(DictComboBox.class);
	@Resource(name="commService")
	private CommService commService;
	@Override
	public JSONArray getJson(ReportContent table, ReportFilter filter) {
		String dictName = filter.getComboBoxDataSource();
		
		JSONArray jsArray = new JSONArray();
		
		try {
			List<DictionaryData> dictDatas = this.commService.findDictValue(dictName);
			if(dictDatas == null){
				return jsArray;
			}
			for(DictionaryData data : dictDatas){
				JSONObject jsObj = new JSONObject();
				jsObj.put(filter.getComboBoxValueField() != null && filter.getComboBoxValueField().length()>0 ?filter.getComboBoxValueField():"value" , data.getCode());
				jsObj.put(filter.getComboBoxNameField()!= null && filter.getComboBoxNameField().length()>0 ? filter.getComboBoxNameField(): "text", data.getName());
				jsArray.put(jsObj);
			}	
			
		} catch (Exception e) {
			log.error("",e);
		}
		return jsArray;
	}
	 

}
