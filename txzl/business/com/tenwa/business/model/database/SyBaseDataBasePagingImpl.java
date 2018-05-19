package com.tenwa.business.model.database;

public class SyBaseDataBasePagingImpl implements DataBasePaging {

	@Override
	public String getPagingSql(String sqlNoPaging, int start, int limit,String ... sortSql) {
		return sqlNoPaging;
	}
}
