package com.tenwa.kernal.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBeanUtil {
	
	public static <T> T getResultSetObj(T entity, ResultSet rs,String tablename) throws SQLException {
		Method method = null;
		PreparedStatement ps = null;
		Connection conn=null;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:10.1.3.107:1521:txzldev", "txzl", "123");
		String sqltablecolumns = "SELECT case when c.COLUMN_NAME='ID' then '9999' else c.COLUMN_NAME end  name  FROM USER_TAB_COLUMNS c where c.TABLE_NAME = '"
				+ tablename + "'order by c.COLUMN_ID asc";
		ps=conn.prepareStatement(sqltablecolumns);
		ResultSet rscolumns = ps.executeQuery();
		Field[] fields = entity.getClass().getDeclaredFields();
		while (rscolumns.next()) {
			String columnname = rscolumns.getString("name");// 列名
			String key = columnname.replace("_", "").toUpperCase();
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String value = fieldName.replace("_", "").toUpperCase();
				 String type = fields[i].getGenericType().toString(); // 获取属性的类型
				if (key.equals(value)) {
					String firstLetter = fieldName.substring(0, 1).toUpperCase();
					String setter = "set" +firstLetter+fieldName.substring(1);
					 if("class java.lang.String".equals(type)){
					  method = entity.getClass().getMethod(setter,String.class);
					  method.invoke(entity,rs.getString(columnname));
					 }else if("class java.lang.Integer".equals(type)){
					  method = entity.getClass().getMethod(setter,Integer.class);
					  method.invoke(entity,("".equals(rs.getString(columnname))||null==rs.getString(columnname) ) ?null:Integer.parseInt(rs.getString(columnname)));
					 }else if("class java.math.BigDecimal".equals(type)){
					  method = entity.getClass().getMethod(setter,BigDecimal.class);
					  method.invoke(entity,("".equals(rs.getString(columnname))||null==rs.getString(columnname) ) ?null:new BigDecimal(rs.getString(columnname))); 
					 }else{
					 }
				}
			}
		}
		conn.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	return entity;

  }
	public static String getAllFiledInsertSQL(ResultSet rs, String tablename,String str){
		PreparedStatement ps = null;
		Connection conn=null;
		String sql="";
		String sqltablecolumns = "SELECT c.COLUMN_NAME as columnname FROM USER_TAB_COLUMNS c  where c.TABLE_NAME='"
				+ tablename + "' order by c.COLUMN_ID asc ";
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:10.1.3.107:1521:txzldev", "txzl", "123");
		ps=conn.prepareStatement(sqltablecolumns);
		ResultSet rscolumns = ps.executeQuery();
		StringBuffer sbkey = new StringBuffer();
		sbkey.append("insert into " + tablename + "(");
		while (rs.next()) {
			
			
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sql;
	}

}
