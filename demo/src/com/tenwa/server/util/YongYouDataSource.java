/**
 * com.tenwa.datasync.util
 */
package com.tenwa.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author Jaffe
 * 
 * Date:Mar 16, 2012 11:03:31 AM Email:JaffeHe@hotmail.com
 */
public class YongYouDataSource {
/*	// ����
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String dataSource;*/

	/**
	 * ��ȡ����
	 */
/*	public YongYouDataSource() {
		conn = null;
		stmt = null;
		rs = null;
		dataSource = "jdbc/yongyou";
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(dataSource);
			System.out.println("====>Oracle DataSource Object Create<=====");
			conn = ds.getConnection();
			stmt = conn.createStatement(1005, 1008);
		} catch (Exception e) {
			System.err.println("!!!!����Oracle DataSourceError: "
					+ e.getMessage());
		}
	}
*/
	
	private static String DRIVER = "oracle.jdbc.driver.OracleDriver";// 
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";// 
	private static String USER = "ncmd";// 
	private static String PWD = "123";// 

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	static {// ��̬�����ִ��һ��
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public YongYouDataSource() {
		conn = null;
		stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PWD);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
/*	public Connection getConnection() {
	
		return conn;
	}
*/	/**
	 * ִ�в�ѯ
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public ResultSet executeQuery(String sql) throws SQLException {
		return stmt.executeQuery(sql);
	}

	/**
	 * ִ���޸�
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int executeUpdate(String sql) throws SQLException {
		return stmt.executeUpdate(sql);
	}

	/**
	 * �ر���Դ
	 */
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
			System.out.println("====>!!Oracle DataSource Object close!!<=====");
		} catch (Exception e) {
			System.err.println("YongYou DataSource �ر��쳣��" + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		YongYouDataSource s= new YongYouDataSource();
		System.out.println(s.toString());
		
	}

}
