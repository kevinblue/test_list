package com.tenwa.business.model.database;

public class SqlServerDataBasePagingImpl implements DataBasePaging {

	@Override
	public String getPagingSql(String sqlNoPaging, int start, int limit,String ... sortSql) {
		   String noSpaceSql = sqlNoPaging.toUpperCase().replaceAll("\\s", "");
		   if(!noSpaceSql.startsWith("SELECTTOP100PERCENT"))
		   {
			   sqlNoPaging = "SELECT TOP 100 PERCENT "+sqlNoPaging.trim().substring(6);
		   }
		String sortSqlStr = "ORDER BY GETDATE()";   
		if(null != sortSql && 0 < sortSql.length && null != sortSql[0] && sortSql[0].length() > 0){
			sortSqlStr = sortSql[0];
		} 
		String DataAuthorityCondtion="";
		if(null != sortSql && sortSql.length>0 && null != sortSql[1] ){
			DataAuthorityCondtion = sortSql[1];
		} 
		StringBuffer pageSql_sb = new StringBuffer("");
		pageSql_sb.append(" select OUTER_PAGE_RS.* from(")
		          .append("    select ROW_NUMBER() OVER("+sortSqlStr+") as rn_column,INNER_PAGE_RS.* from(")
		          .append("          "+sqlNoPaging)
		          .append("    )INNER_PAGE_RS "+DataAuthorityCondtion)
		          .append(" )OUTER_PAGE_RS " )
		          .append(" WHERE OUTER_PAGE_RS.rn_column > " +start)
		          .append(" AND   OUTER_PAGE_RS.rn_column <= "+(start+limit));
		return pageSql_sb.toString();
	}
}
