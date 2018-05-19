package com.tenwa.kernal.datasource;

public class DynamicDataSourceHolder 
{   
	  
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();   
	public static void putDataSourceName(String dataSourceBeanId)
	{   
	   holder.set(dataSourceBeanId);   
	}   
	public static String getDataSourceName()
	{   
	   return holder.get();   
	}   
	public static void setCurrentUseDataSource(String dataSourceBeanId)
	{
		putDataSourceName(dataSourceBeanId);
	}
	public static String getCurrentUseDataSource()
	{
		return holder.get();  
	}
}  
