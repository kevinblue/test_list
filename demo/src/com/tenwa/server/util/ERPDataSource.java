/**
 * com.tenwa.datasync.util
 */
package com.tenwa.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


/**
 * @author Jaffe
 * 
 * Date:Mar 15, 2012 4:00:02 PM Email:JaffeHe@hotmail.com
 */
public class ERPDataSource {

/*	 // ����
	 private Connection conn;
	 private Statement stmt;
	 private ResultSet rs;
	 private String dataSource;
	
	 *//**
	 * ��ȡ����
	 *//*
	 public ERPDataSource() {

		    this.conn = null;
		    this.stmt = null;
		    this.rs = null;
		    //this.dataSource = "java:comp/env/jdbc/culcleasing";
		    this.dataSource = "jdbc/culcleasing";
		    try
		    {
		      Context ctx = new InitialContext();
		      System.out.println("====>ERP DataSource Object Create<=====");
		      DataSource ds = (DataSource)ctx.lookup(this.dataSource);
		      this.conn = ds.getConnection();
		      this.stmt = this.conn.createStatement(1005, 1008);
		    }
		    catch (Exception e)
		    {
		      System.err.println(e.getMessage());
		    }
	 }*/

	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// 
	private static String URL = "jdbc:sqlserver://localhost:1433;databasename=CulcLeasing";// 
	private static String USER = "sa";// 
	private static String PWD = "123456";// 

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement inspstmt = null;
	static {// ��̬�����ִ��һ��
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ERPDataSource() {
		conn = null;
		stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PWD);
			stmt = conn.createStatement();
			System.out.println("====>!!ERP DataSource Object open!!<=====");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
public static void main(String[] args) {
	ERPDataSource s= new ERPDataSource();
	System.out.println(s.toString());
	
}
/*public Connection getConnection() {
	Connection con = null;
	try {
		con = DriverManager.getConnection(URL, USER, PWD);
	
	} catch (SQLException se) {

		se.printStackTrace();

	}
	return con;
}*/


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
    
	public ResultSet executeQuery(String sql) throws SQLException {
		return stmt.executeQuery(sql);
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
			System.out.println("====>!!ERP DataSource Object close!!<=====");
		} catch (Exception e) {
			System.err.println("ERP DataSource �ر��쳣��" + e.getMessage());
		}
	}
}
