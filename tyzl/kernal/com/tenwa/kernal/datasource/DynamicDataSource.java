package com.tenwa.kernal.datasource;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
@SuppressWarnings("unchecked")
public class DynamicDataSource extends AbstractRoutingDataSource
{   
	    @Override  
	    public void setTargetDataSources(Map targetDataSources) 
	    {   
	        super.setTargetDataSources(targetDataSources);   
	    }   
	       
	    @Override  
	    public Object unwrap(Class iface) throws SQLException 
	    {   
	        return null;   
	    }   
	  
	    @Override  
	    public boolean isWrapperFor(Class iface) throws SQLException 
	    {   
	        return false;   
	    }   
	  
	    @Override  
	    protected Object determineCurrentLookupKey() 
	    {   
	        String dataSourceName = DynamicDataSourceHolder.getDataSourceName();   
	        return dataSourceName;   
	    } 
}