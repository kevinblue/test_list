package com.tenwa.report.service;

import org.json.JSONArray;

public interface ComboboxService {
	public JSONArray getComboboxJson(String tableId,String filterId) throws Exception;	
}
