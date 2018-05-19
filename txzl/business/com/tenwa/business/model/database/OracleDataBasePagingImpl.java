package com.tenwa.business.model.database;


public class OracleDataBasePagingImpl implements DataBasePaging 
{

	@Override
	public String getPagingSql(String sqlNoPaging, int start, int limit,String ...sortSql) 
	{
		String sortSqlStr = "";   
		if(null != sortSql && 0 < sortSql.length){
			sortSqlStr = sortSql[0];
		}  
		String DataAuthorityCondtion="";
		if(null != sortSql && sortSql.length>0 && null != sortSql[1] ){
			DataAuthorityCondtion = sortSql[1];
		} 
		StringBuffer pageSql_sb = new StringBuffer("");
		pageSql_sb.append(" select OUTER_PAGE_RS.* from(")
		          .append("    select rownum as rn_column,INNER_PAGE_RS.* from(")
		          .append("          "+sqlNoPaging + sortSqlStr)
		          .append("    )INNER_PAGE_RS "+DataAuthorityCondtion)
		          .append(" )OUTER_PAGE_RS " )
		          .append(" WHERE OUTER_PAGE_RS.rn_column > " +start)
		          .append(" AND   OUTER_PAGE_RS.rn_column <= "+(start+limit));
		return pageSql_sb.toString();
	}

}
