package com.tenwa.report.query;

import static com.tenwa.kernal.utils.ResourceUtil.getConfigValue;
import static com.tenwa.kernal.utils.StringUtil.nullToString;
import static java.lang.Integer.parseInt;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.WebUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tenwa.report.entity.ReportDataSource;
import com.tenwa.report.enums.DataSourceType;

public class DataSourceFactory {
	private static final Logger log = LoggerFactory.getLogger(DataSourceFactory.class);

	private Map<String, DataSource> allDataSources = new HashMap<String, DataSource>();

	private static DataSourceFactory dsf = new DataSourceFactory();

	private DataSourceFactory() {
		// do nothing
	}

	public static Connection getConnection(ReportDataSource rds) throws BusinessException, SQLException, NamingException {
		return getConnection(rds, true);
	}

	/**
	 * 仅用于数据源测试时
	 * 
	 * @param rds
	 * @param isCachable
	 * @return
	 * @throws BusinessException 
	 * @throws SQLException 
	 * @throws PropertyVetoException 
	 * @throws NamingException 
	 * @throws Exception
	 */
	public static Connection getConnection(ReportDataSource rds, boolean isCachable) throws BusinessException, SQLException, NamingException   {
		Connection conn = null;
		if (rds == null)
			throw new BusinessException("Non exists Report DataSource");
		log.debug("ReportDataSource:{}", rds);

		isCachable = (rds.getDataSourceType() == DataSourceType.JDBC) ? true && isCachable : false;
		if (dsf.allDataSources.containsKey(rds.getId()) && isCachable) {
			log.debug("Get DataSource [" + rds.getDataSourceName() + "] from cache");
			DataSource ds = dsf.allDataSources.get(rds.getId());
			
				conn = ds.getConnection();
			
		}
		if (conn == null) {
			DataSource ds = null;
			if (rds.getDataSourceType() == null)
				throw new BusinessException("Incorrect Datasource Type");
			switch (rds.getDataSourceType()) {
			case JDBC:
				if (isCachable) {
					try {
						ds = dsf.createC3P0DataSource(rds);
					} catch (PropertyVetoException e) {
						throw new BusinessException(e);
					}
				} else {
					ds = dsf.createDirectJDBC(rds);
				}
				break;
			case JNDI:
				ds = dsf.lookupJNDIDataSource(rds);
				break;
			case SPRING:
				ds = dsf.lookupSpringBeanDataSource(rds);
				break;
			default:
				throw new BusinessException("Incorrect Datasource Type");
			}
			if (ds != null) {
				conn = ds.getConnection();
				conn.setAutoCommit(false);
				log.debug("DataSource [" + rds.getDataSourceName() + "] initialized");
				if (isCachable)
					dsf.allDataSources.put(rds.getId(), ds);
			} else
				throw new BusinessException("DataSource [" + rds.getDataSourceName() + "] can't obtained");

		}
		return conn;
	}

	private DataSource createDirectJDBC(ReportDataSource rds)  {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(rds.getDriverName());
		ds.setUrl(rds.getUrl());
		ds.setUsername(rds.getUsername());
		ds.setPassword(rds.getPassword());
		
//		ds.setDriverClass(rds.getDriverName());
//		ds.setUser(rds.getUrl());
//		ds.setPassword(rds.getPassword());
//		ds.setJdbcUrl(rds.getUrl());
		log.debug("{}",ds.toString());
		return ds;
	}

	private DataSource lookupSpringBeanDataSource(ReportDataSource rds) {
		return WebUtil.getApplicationContext().getBean(rds.getJndi(), DataSource.class);
	}

	private DataSource lookupJNDIDataSource(ReportDataSource rds) throws NamingException{
		InitialContext ctx = new InitialContext();
		return (DataSource) ctx.lookup(rds.getJndi());
	}

	private DataSource createC3P0DataSource(ReportDataSource rds) throws PropertyVetoException  {
		ComboPooledDataSource ds = new ComboPooledDataSource();

		ds.resetPoolManager();
		ds.setUser(rds.getUsername());
		ds.setPassword(rds.getPassword());
		ds.setDriverClass(rds.getDriverName());
		ds.setJdbcUrl(rds.getUrl());
		ds.setDataSourceName(rds.getId());
		ds.setAcquireRetryAttempts(2);
		ds.setAutoCommitOnClose(false);
		ds.setBreakAfterAcquireFailure(true);
		ds.setPropertyCycle(300);
		ds.setInitialPoolSize(parseInt(nullToString(getConfigValue("c3p0.initialPoolSize"), "5")));
		ds.setMinPoolSize(parseInt(nullToString(getConfigValue("c3p0.minPoolSize"), "3")));
		ds.setMaxPoolSize(parseInt(nullToString(getConfigValue("c3p0.maxPoolSize"), "50")));
		ds.setMaxIdleTime(parseInt(nullToString(getConfigValue("3p0.maxIdleTime"), "60")));

		return ds;
	}

	public static void close(Connection conn) {
		close(conn, null, null);
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {

		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			log.error("", e);
		} finally {
			rs = null;
			stmt = null;
			conn = null;
		}
	}
}
