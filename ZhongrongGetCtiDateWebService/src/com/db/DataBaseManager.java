package com.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


import org.apache.log4j.*;


public class DataBaseManager {
	private static final Logger log = Logger.getLogger(DataBaseManager.class);
	private static final String DBFILEPATH = "/db.cfg";
	
	private String driver;
	/** jdbc驱动程序类路径 */
	private String url;
	/** 数据库连接URL */
	private String user;
	/** 用户名 */
	private String password;
	/** 密码 */
	
	private String host_directory;
	private String sftp_port;
	private String sftp_host;
	private String sftp_user;
	private String sftp_password;
	private String sftp_directory;

	private static final DataBaseManager dm = new DataBaseManager();

	private DataBaseManager() {
		init();
	}

	public static DataBaseManager getInstance() {
		return dm;
	}

	/**
	 * 获取一个数据库连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager
					.getConnection(url,user,password);
			log.info("数据库加载成功");
			//log.info("加载数据库驱动");
		} catch (ClassNotFoundException ce) {
			log.error("----加载数据库驱动程序失败----:", ce);
		} catch (SQLException se) {
			log.error("加载数据库驱动程序:", se);
		}
		return conn;
	}

	/**
	 * 读取db.cfg配置文件并初始化
	 */
	private void init() {
		InputStream is = this.getClass().getResourceAsStream(DBFILEPATH);
		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (IOException e) {
			log.error("没有找到db.cfg配置文件:", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				is = null;
			}
		}
		this.driver = pro.getProperty("driver");
		this.url = pro.getProperty("url");
		this.user = pro.getProperty("user");
		this.password = pro.getProperty("password");
		this.host_directory=pro.getProperty("host_directory");
		this.sftp_port=pro.getProperty("sftp_port");
		this.sftp_host=pro.getProperty("sftp_host");
		this.sftp_user=pro.getProperty("sftp_user");
		this.sftp_password=pro.getProperty("sftp_password");
		this.sftp_directory=pro.getProperty("sftp_directory");
		
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public void CloseConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}

	/**
	 * 关闭statement
	 * 
	 * @param stat
	 */
	public void CloseStatement(Statement stat) {
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stat = null;
			}
		}
	}

	/**
	 * 关闭statement
	 * 
	 * @param stat
	 */
	public void ClosePreparStatement(PreparedStatement stat) {
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stat = null;
			}
		}
	}

	/**
	 * 关闭结果集
	 * 
	 * @param rs
	 */
	public void CloseResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
	}
	
	public static void main(String[] str){
		
		 
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getHost_directory() {
		return host_directory;
	}

	public void setHost_directory(String hostDirectory) {
		host_directory = hostDirectory;
	}

	public String getSftp_port() {
		return sftp_port;
	}

	public void setSftp_port(String sftpPort) {
		sftp_port = sftpPort;
	}

	public String getSftp_host() {
		return sftp_host;
	}

	public void setSftp_host(String sftpHost) {
		sftp_host = sftpHost;
	}

	public String getSftp_user() {
		return sftp_user;
	}

	public void setSftp_user(String sftpUser) {
		sftp_user = sftpUser;
	}

	public String getSftp_password() {
		return sftp_password;
	}

	public void setSftp_password(String sftpPassword) {
		sftp_password = sftpPassword;
	}

	public String getSftp_directory() {
		return sftp_directory;
	}

	public void setSftp_directory(String sftpDirectory) {
		sftp_directory = sftpDirectory;
	}

	
}
