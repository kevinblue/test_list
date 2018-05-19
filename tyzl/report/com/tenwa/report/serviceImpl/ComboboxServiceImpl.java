package com.tenwa.report.serviceImpl;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.tenwa.report.dao.ReportDao;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.filter.ReportCombobox;
import com.tenwa.report.service.ComboboxService;

@Service(value = "comboboxService")
public class ComboboxServiceImpl implements ComboboxService {

	@Resource
	private ReportDao reportDao;
	
	@Resource(name="localCombobox")
	private ReportCombobox localCombobox;
	
	@Resource(name="sqlCombobox")
	private ReportCombobox sqlCombobox;
	
	@Resource(name="dictCombobox")
	private ReportCombobox dictCombobox;

	@Resource(name="jsonCombobox")
	private ReportCombobox jsonCombobox;
	
	@Override
	public JSONArray getComboboxJson(String tableId, String filterId) throws Exception {
		ReportFilter filter = this.reportDao.findEntityByID(ReportFilter.class, filterId);
		ReportTable table = this.reportDao.findEntityByID(ReportTable.class, tableId);
		JSONArray jsArray = null;
		if (filter != null) {
			ReportCombobox comboBox = null;
			switch (filter.getFilterDataRequestType()) {
			case LOCAL:
				comboBox = localCombobox;
				break;
    
			case SQL:
				comboBox = sqlCombobox;
				break;
				
			case DICT:
				comboBox = dictCombobox;
				break;
			case JSON:
				comboBox = jsonCombobox;
				break;
			default:
				return new JSONArray();
			}

			jsArray = comboBox.getJson(table, filter,this.reportDao);
		}

		if (jsArray == null) {
			jsArray = new JSONArray();
		}
		return jsArray;
	}

}
