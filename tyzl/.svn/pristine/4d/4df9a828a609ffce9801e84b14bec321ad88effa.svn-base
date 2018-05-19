
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         JdbcModel.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-21-下午01:45:44
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.jbpm.model;

import java.text.MessageFormat;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;


 /**
 * 类名称：     JdbcModel
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-5-21 下午01:45:44
 * 修改备注：
 * 
 * @version 1.0.0
 **/

public class JdbcModel 
{
	private final JdbcTemplate jdbcTemplate;
	public JdbcModel(String type,String host, String port, String dbname, String user,String password) throws Exception 
	{
		type = type.toLowerCase().trim();
		String driver = "";
		String connectionString = "";
		if("oracle".equals(type)){
			driver  = "oracle.jdbc.driver.OracleDriver";
			connectionString = "jdbc:oracle:thin:@{0}:{1}:{2}";
		}
		else if("sqlserver".equals(type)){
			driver  = "net.sourceforge.jtds.jdbc.Driver";
			connectionString = "jdbc:jtds:sqlserver://{0}:{1}/{2};tds=8.0;lastupdatecount=true;autoReconnect=true;SelectMethod=Cursor";
		}else{
			driver  = "oracle.jdbc.driver.OracleDriver";
		}
		connectionString = MessageFormat.format(connectionString, host,port,dbname);
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(connectionString);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	public JdbcTemplate getJdbcTemplate()
	{
		return this.jdbcTemplate;
	}
}
