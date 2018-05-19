package com.tenwa.report.filter.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tenwa.exception.BusinessException;
import com.tenwa.report.entity.ReportContent;
import com.tenwa.report.entity.ReportFilter;
import com.tenwa.report.entity.ReportTable;
import com.tenwa.report.filter.ReportCombobox;
import com.tenwa.report.query.DataSourceFactory;

@Service("sqlCombobox")
public class SQLComboBox implements ReportCombobox {
	private static final Logger log = LoggerFactory.getLogger(SQLComboBox.class);

	@Override
	public JSONArray getJson(ReportContent table, ReportFilter filter) {
		Connection conn = null;
		JSONArray jsArray = new JSONArray();
		
		try {
			conn = DataSourceFactory.getConnection(table.getReportDataSource());
			PreparedStatement stmt = conn.prepareStatement(filter.getComboBoxDataSource());
			ResultSet rs = stmt.executeQuery();

			int keyIndex = 1;
			int valueIndex = 2;
			if (rs.getMetaData().getColumnCount() < 1) {
				return new JSONArray();
			} else if (rs.getMetaData().getColumnCount() == 1) {
				valueIndex = 1;
			}
			
			while (rs.next()) {
				JSONObject jsObj = new JSONObject();
				try {
					jsObj.put(filter.getComboBoxValueField() != null && filter.getComboBoxValueField().length()>0 ?filter.getComboBoxValueField():"value", rs.getString(valueIndex));
					jsObj.put(filter.getComboBoxNameField()!= null && filter.getComboBoxNameField().length()>0 ? filter.getComboBoxNameField(): "text", rs.getString(keyIndex));
					jsArray.put(jsObj);
				} catch (JSONException e) {
					log.error("", e);
				}
			}
		} catch (BusinessException e) {
			log.error("", e);
		} catch (SQLException e) {
			log.error("", e);
		} catch (NamingException e) {
			log.error("", e);
		} finally {
			DataSourceFactory.close(conn);
		}

		return jsArray;
	}

}
