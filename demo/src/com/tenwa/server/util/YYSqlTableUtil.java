package com.tenwa.server.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 
 * @author luojian
 * @date 2016-8-11
 * @info YYSqlTableUtil
 * @Copyright Tenwa
 */

public class YYSqlTableUtil {
	/*
	 * private static final YYSqlTableUtil s=new YYSqlTableUtil();
	 * 
	 * private YYSqlTableUtil(){
	 * 
	 * }
	 * 
	 * public static YYSqlTableUtil getInstance(){ return s;
	 * 
	 * }
	 */
	/**
	 * ��̬����insertSQL
	 * 
	 * @throws SQLException
	 * */
	public static String getAllFiledInsertSQL(Object obj, String tablename)
			throws SQLException {
		YongYouDataSource single = new YongYouDataSource();
		String sqltablecolumns = "SELECT c.COLUMN_NAME as columnname FROM USER_TAB_COLUMNS c  where c.TABLE_NAME='"
				+ tablename + "' order by c.COLUMN_ID asc ";
		ResultSet rs = single.executeQuery(sqltablecolumns);
		StringBuffer sbkey = new StringBuffer("insert into " + tablename + "(");
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer sbvalue = new StringBuffer();
		while (rs.next()) {
			String columnname = rs.getString("columnname");
			String key = columnname.replace("_", "").toUpperCase();
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String value = fieldName.replace("_", "").toUpperCase();
				if (key.equals(value)) {
					sbkey.append(columnname).append(",");
					sbvalue.append(
							"'" + getFieldValueByName(fields[i].getName(), obj))
							.append("',");
					break;
				}
			}

		}
		sbkey.deleteCharAt(sbkey.length() - 1);
		sbvalue.deleteCharAt(sbvalue.length() - 1);
		String insertsql = sbkey.toString() + ")values(" + sbvalue.toString()
				+ ")";
		single.close();
		return insertsql;
	}

	/**
	 * ������������ȡ����ֵ
	 * */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			if (value == null) {
				value = "";
			}
			return value;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	/**
	 *  sql service��̬���ݶ��󷵻ؽ����
	 * @param <T>
	 * @return 
	 * @return 
	 * 
	 * @throws SQLException
	 * */
	public static <T> T getResultSetObj(T entity, ResultSet rs,String tablename) throws SQLException {
		// 1��ȡSQL service����
		Method method = null;
		try {
			ERPDataSource erpDataSource = new ERPDataSource();
			String sqltablecolumns = "select name from sys.columns where object_id = object_id('"
					+ tablename + "')  order by column_id asc ";
			ResultSet rscolumns = erpDataSource.executeQuery(sqltablecolumns);
			Field[] fields = entity.getClass().getDeclaredFields();
			
			while (rscolumns.next()) {
				String columnname = rscolumns.getString("name");// ����
				String key = columnname.replace("_", "").toUpperCase();
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i].getName();
					String value = fieldName.replace("_", "").toUpperCase();
					 String type = fields[i].getGenericType().toString(); // ��ȡ���Ե�����
					if (key.equals(value)) {
						String firstLetter = fieldName.substring(0, 1).toUpperCase();
						String setter = "set" +firstLetter+fieldName.substring(1);
						System.out.println(key+"11"+value+"22"+fieldName+"333"+ fieldName+"44");
						 if("class java.lang.String".equals(type)){
						  method = entity.getClass().getMethod(setter,String.class);
						  method.invoke(entity,rs.getString(columnname));
						 }else if("class java.lang.Integer".equals(type)){
						  method = entity.getClass().getMethod(setter,Integer.class);
						  System.out.println(setter+"---"+rs.getString(columnname)+"==="+type);
						  method.invoke(entity,("".equals(rs.getString(columnname))||null==rs.getString(columnname) ) ?null:Integer.parseInt(rs.getString(columnname)));
						 }else if("class java.math.BigDecimal".equals(type)){
							 System.out.println(setter+"---"+rs.getString(columnname)+"==="+type);
						  method = entity.getClass().getMethod(setter,BigDecimal.class);
						  method.invoke(entity,("".equals(rs.getString(columnname))||null==rs.getString(columnname) ) ?null:new BigDecimal(rs.getString(columnname))); 
						 }else{
							 System.out.println(type+"4444"+rs.getString(columnname)+"000"+columnname);	 
							 
						 }
						
					
					   break;
					}
				}

			}
			erpDataSource.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entity;

	}
	/**
	 *  ��ȡERP���е� ����  ��̬���ݶ��󷵻�
	 * @param <T>
	 * @return 
	 * @return 
	 * 
	 * @throws SQLException
	 * */
	
	public static <T> T getERPResultSetObj(T entity, ResultSet rs,String tablename) throws SQLException {
		// 1��ȡSQL service����
		Method method = null;
		YongYouDataSource  erpDataSource =new YongYouDataSource();
		try {
			String sqltablecolumns = "SELECT c.COLUMN_NAME as name FROM USER_TAB_COLUMNS c  where c.TABLE_NAME='"
					+ tablename + "' order by c.COLUMN_ID asc ";
			ResultSet rscolumns = erpDataSource.executeQuery(sqltablecolumns);
			Field[] fields = entity.getClass().getDeclaredFields();
			while (rscolumns.next()) {
				String columnname = rscolumns.getString("name");// ����
				String key = columnname.replace("_", "").toUpperCase();
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i].getName();
					String value = fieldName.replace("_", "").toUpperCase();
					 String type = fields[i].getGenericType().toString(); // ��ȡ���Ե�����
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
						  method.invoke(entity,new BigDecimal(rs.getString(columnname))); 
						 }else{
							 
						 }
						
					
					   break;
					}
				}

			}
			erpDataSource.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entity;

	}
	
	/**
	 *  sql service��̬���ݶ��󷵻�SQL
	 * @return 
	 * @return 
	 * 
	 * @throws SQLException
	 * */
	public static String getAllobject(Object obj,String tablename,int count) throws SQLException  {
		ERPDataSource erpDataSource = new ERPDataSource();
		String sqltablecolumns = "select name from sys.columns where object_id = object_id('"
				+ tablename + "')  order by column_id asc ";
		ResultSet rs = erpDataSource.executeQuery(sqltablecolumns);
		StringBuffer sbkey = new StringBuffer();
		if(count==0){
		sbkey.append("insert into " + tablename + "(");
	
		}else{
	   sbkey.append("update  " + tablename + " set ");	
			
		}
		
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuffer sbvalue = new StringBuffer();
		while (rs.next()) {
			String columnname = rs.getString("name");
			String key = columnname.replace("_", "").toUpperCase();
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String value = fieldName.replace("_", "").toUpperCase();
				if (key.equals(value)&&!key.equals("ID")) {
					  if(count==0){
						 sbkey.append(columnname).append(",");
							sbvalue.append(
									"'" + getFieldValueByName(fields[i].getName(), obj))
									.append("',");  
						  
					  }else{
						 sbkey.append(columnname).append(" = '"+getFieldValueByName(fields[i].getName(), obj)+"',");
						 sbvalue.append(" ");
					  }
					
					break;
				}
			}

		}
		sbkey.deleteCharAt(sbkey.length() - 1);
		sbvalue.deleteCharAt(sbvalue.length() - 1);
		String insertorupdatesql="";
		if(count==0){
			insertorupdatesql = sbkey.toString() + ")values(" + sbvalue.toString()
					+ ")";
			}else{
			
		insertorupdatesql=sbkey.toString();
			}
	
				
		erpDataSource.close();
		return insertorupdatesql;
	}
	public static int getSelectcount(String tableName,String columnname,String fieldName) throws SQLException{
	  int cou=0;
	  ERPDataSource erpDataSource = new ERPDataSource();
	  String sql="select count(1) cou from "+tableName+" where "+columnname+"='"+fieldName+"'";
	  ResultSet rscolumns = erpDataSource.executeQuery(sql);
	  System.out.println(sql);
	  while (rscolumns.next()) {
		  cou=rscolumns.getInt("cou");
		  
	  }
	  System.out.println(cou);
	  return cou;	
		
	}
	public static int getSelectFactoringBankcount(String tableName,String columnname,String fieldName,int rentlist) throws SQLException{
		  int cou=0;
		  ERPDataSource erpDataSource = new ERPDataSource();
		  String sql="select count(1) cou from "+tableName+" where "+columnname+"='"+fieldName+"' and rent_list="+rentlist;
		  ResultSet rscolumns = erpDataSource.executeQuery(sql);
		  System.out.println(sql);
		  while (rscolumns.next()) {
			  cou=rscolumns.getInt("cou");
			  
		  }
		  System.out.println(cou);
		  return cou;	
			
		}
}
