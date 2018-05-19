package com.tenwa.business.dao;

import com.tenwa.business.model.Table;

public interface TableDao  extends BaseDao
{
	
	
	 /**
	 * @method getTableInfo(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param table
	 * @throws Exception
	 * @returnType void
	 * @exception  
	 * @since      1.0.0
	 **/
	public void getTableInfo(final Table table,final boolean isLoadTreeData) throws Exception;
	
	 /**
	 * @method getTableExportExcelInfo(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param table
	 * @throws Exception
	 * @returnType void
	 * @exception  
	 * @since      1.0.0
	 **/
	public void getTableExportExcelInfo(final Table table,final boolean isLoadTreeData) throws Exception;
}
