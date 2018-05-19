package com.tenwa.report.query.dialect;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tenwa.report.annotation.DialectName;

@DialectName(name="SQL Server 2008")
public class SQLServer2008Dialect extends AbstractSQLDialect {
	@Override
	protected String buildPagingQuery(String sql, int start, int limit) {
		
		List<String>returnSql =  appendOrder(sql);
		String extractstartSql = returnSql.get(0);
		sql = returnSql.get(1);
		String extractendSql = returnSql.get(2);
		String noSpaceSql = sql.toUpperCase().replaceAll("\\s", "");
	   if(!noSpaceSql.startsWith("SELECTTOP100PERCENT"))
	   {
		   sql = "SELECT TOP 100 PERCENT "+sql.trim().substring(6);
	   }
	   String orderColumn = "ORDER BY GETDATE()";
	   if(extractendSql.trim().length() > 0 ){
		   orderColumn = extractendSql;
	   }
	    StringBuffer pageSql_sb = new StringBuffer("");
	    
		pageSql_sb.append(extractstartSql)
		.append(" select OUTER_PAGE_RS.* from(")
        .append("    select ROW_NUMBER() OVER("+orderColumn+") as rn_column,INNER_PAGE_RS.* from(")
        .append("          "+sql)
        .append("    )INNER_PAGE_RS ")
        .append(" )OUTER_PAGE_RS " )
        .append(" WHERE OUTER_PAGE_RS.rn_column > " +start)
        .append(" AND   OUTER_PAGE_RS.rn_column <= "+(start+limit))
		.append(extractendSql);
		
		return pageSql_sb.toString();
	}

	@Override
	public ResultSet executeProcdure(String proc) {
		return null;
	}

	@Override
	protected void registerSQLKeyword() {
		
	}
	
	
	public String to_date(String t,boolean hasApostrophe){
		String apostrophe = "'";
		if(!hasApostrophe)
			apostrophe = "";
		return "convert(datetime," + apostrophe + t + apostrophe + ",120)";
	}
	
	@Override
	protected List<String> appendOrder(String sql){
		String extractstartSql = " ";
		String extractendSql = " ";
		Pattern p = Pattern.compile("<EXTRACTSTART>(.*)<EXTRACTSTART>");
		Matcher matcher =  p.matcher(sql);
		if(matcher.find()){
			extractstartSql =   matcher.group(1)+" ";
			sql = p.matcher(sql).replaceAll("");
		}
		
		p = Pattern.compile("<EXTRACTEND>(.*)<EXTRACTEND>");
		matcher =  p.matcher(sql);
		if(matcher.find()){
			extractendSql =   " "+matcher.group(1);
			sql = p.matcher(sql).replaceAll("");
		}
		
	   List<String>returnSql = new ArrayList<String>();
	   returnSql.add(extractstartSql);
	   returnSql.add(sql);
	   returnSql.add(extractendSql);
	   return  returnSql ;
	}

}
