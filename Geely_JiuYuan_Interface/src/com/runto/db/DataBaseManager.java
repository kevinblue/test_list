package com.runto.db;

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
	private String serviceurl;
	/** webservice IP地址端口号 */
	private String postpone="";  //定时任务，在当前的日期推迟几天执行,
	private String time="";      //时
	private String minute="";	 //分
	private String interval="";		//定时执行间隔
	



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
			conn = DriverManager.getConnection(url, user, password);
			log.info("数据库连接成功");
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
		this.serviceurl=pro.getProperty("serviceurl");
		this.postpone=pro.getProperty("postpone");
		this.time=pro.getProperty("time");
		this.minute=pro.getProperty("minute");
		this.interval=pro.getProperty("interval");
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
		
		DataBaseManager da = new DataBaseManager();
		da.getConnection();
		//da.getConnection();
		//da.serviceurl;
		
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

	public String getServiceurl() {
		return serviceurl;
	}

	public void setServiceurl(String serviceurl) {
		this.serviceurl = serviceurl;
	}
	public String getPostpone() {
		return postpone;
	}

	public void setPostpone(String postpone) {
		postpone = postpone;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}
}
