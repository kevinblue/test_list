package com.tenwa.report.query.dialect;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tenwa.report.annotation.DialectName;
@DialectName(name="Oracle")
public class OracleDialect extends AbstractSQLDialect {
	@Override
	public String buildPagingQuery(String sql, int start, int limit) {
		start = start + 1;
		return new StringBuffer("SELECT * FROM (SELECT t_.*,ROWNUM row_num_ FROM (")
					.append(sql)
					.append(") t_ where ROWNUM <")
					.append("?")
					.append(" ) WHERE row_num_ >= ")
					.append("?").toString();
	}

	

	@Override
	protected void registerSQLKeyword() {
	}

	@Override
	public ResultSet executeProcdure(String proc) {
		return null;
	}
	
	/**
	 * 
	 * @param t
	 * @param hasApostrophe 是否需要单引号
	 * @return
	 */
	public String to_date(String t,boolean hasApostrophe){
		String apostrophe = "'";
		if(!hasApostrophe)
			apostrophe = "";
		return "to_date(" + apostrophe + t + apostrophe + ",'yyyy-mm-dd hh24:mi:ss')";
	}

	@Override
	protected List<String> appendOrder(String sql) {
		String extractstartSql = " ";
		String extractendSql = " ";
		Pattern p = Pattern.compile("<EXTRACTSTART>(.*)<EXTRACTSTART>");
		Matcher matcher =  p.matcher(sql);
		if(matcher.find()){
			extractstartSql =   matcher.group(1);
			sql = p.matcher(sql).replaceAll("");
		}
		
		p = Pattern.compile("<EXTRACTEND>(.*)<EXTRACTEND>");
		matcher =  p.matcher(sql);
		if(matcher.find()){
			extractendSql =   matcher.group(1);
			sql = p.matcher(sql).replaceAll("");
		}
		
	   List<String>returnSql = new ArrayList<String>();
	   returnSql.add(extractstartSql);
	   returnSql.add(sql);
	   returnSql.add(extractendSql);
	   return  returnSql ;
	}
}
