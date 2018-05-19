package com.tenwa.report.filter.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tenwa.exception.BusinessException;
import com.tenwa.report.dao.ReportDao;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.filter.ReportCombobox;
import com.tenwa.report.query.DataSourceCloseUtil;
import com.tenwa.report.query.DataSourceFactory;

@Service("sqlCombobox")
public class SQLComboBox implements ReportCombobox {
	private static final Logger log = LoggerFactory.getLogger(SQLComboBox.class);

	@Override
	public JSONArray getJson(ReportTable table, ReportFilter filter,ReportDao reportDao) throws Exception {
		JSONArray jsArray = new JSONArray();
			List<Map<String, String>> rs=null;;
			try {
				rs = reportDao.findResultsByHSQL(filter.getComboBoxDataSource());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(null!=rs&&rs.size()<=0){
				return new JSONArray();
			}else{				
				for(int i=0;i<rs.size();i++){
					JSONObject jsObj = new JSONObject();
					String value=filter.getComboBoxValueField() != null && filter.getComboBoxValueField().length()>0 ?filter.getComboBoxValueField():"value";
					String text=filter.getComboBoxNameField()!= null && filter.getComboBoxNameField().length()>0 ? filter.getComboBoxNameField(): "text";
					jsObj.put(value, rs.get(i).get(value));
					jsObj.put(text, rs.get(i).get(text));
					jsArray.put(jsObj);
				}
				return jsArray;
			}

	}
}
