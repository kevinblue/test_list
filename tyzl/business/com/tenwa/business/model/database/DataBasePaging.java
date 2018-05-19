package com.tenwa.business.model.database;

public interface DataBasePaging 
{
	public abstract String getPagingSql(String sqlNoPaging,int start,int limit,String...sortSql);
}
