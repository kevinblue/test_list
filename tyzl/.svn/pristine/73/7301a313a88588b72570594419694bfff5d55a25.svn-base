package com.tenwa.business.model.database;

public class MySqlDataBasePagingImpl implements DataBasePaging {

	@Override
	public String getPagingSql(String sqlNoPaging, int start, int limit,String ... sortSql) {
		String sortSqlStr = "";   
		if(null != sortSql && 0 < sortSql.length){
			sortSqlStr = sortSql[0];
		}  
		String sqlPaging = sqlNoPaging + sortSqlStr + " limit "+start+","+limit;
		return sqlPaging;
	}
}
