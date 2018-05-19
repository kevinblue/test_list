package com.tenwa.report.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceCloseUtil {
	public static void safeCloseStatement(Statement stmt) {
		if (stmt != null) {
		try {
		stmt.close();
		} catch (SQLException e) {
		
		}
		}
	}
	public static void safeCloseConnection(Connection stmt) {
		if (stmt != null) {
		try {
		stmt.close();
		} catch (SQLException e) {
		
		}
		}
	}
	public static void safeCloseResultSet(ResultSet stmt) {
		if (stmt != null) {
		try {
		stmt.close();
		} catch (SQLException e) {
		
		}
		}
	}
}
