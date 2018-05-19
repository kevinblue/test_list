package com.tenwa.business.model.database;

public class DB2DataBasePagingImpl implements DataBasePaging {

	@Override
	public String getPagingSql(String sqlNoPaging, int start, int limit,String ... sortSql) {
		String sqlPaging = sqlNoPaging + " limit "+start+","+limit;
		return sqlPaging;
	}
}
