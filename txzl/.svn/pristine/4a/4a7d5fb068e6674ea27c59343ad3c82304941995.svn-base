package com.tenwa.test.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 测试的最大连接数
 * oracle的默认连接数为135
 * Mysql的默认连接数为100
 * @author kxbin
 * 
 */
class testMaxConn {
	int count = 0; // 连接数
	Connection[] conn = new Connection[1000];
	Statement[] stmt = new Statement[1000];
	ResultSet[] rs = new ResultSet[1000];
	/**
	* 数据库连接方法（oracle）
	*/
	public void testOracleMaxConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@localhost:1521:compare";
			for (count = 0; count < 135; count++) {
				conn[count] = DriverManager.getConnection(url,"nxcompare", "64compare");
				stmt[count] = conn[count].createStatement();
				rs[count] = stmt[count].executeQuery("SELECT * FROM users");
				System.out.print(count + "\t");
			}
		} catch (SQLException ex1) {
			System.out.println("\n" + ex1.toString());
		} catch (InstantiationException ex2) {
			System.out.println("\n" + ex2.toString());
		} catch (ClassNotFoundException ex3) {
			System.out.println("\n" + ex3.toString());
		} catch (IllegalAccessException ex4) {
			System.out.println("\n" + ex4.toString());
		} finally {
			try {
				System.out.println("\n系统打开的连接数"+ count--+ " oracle connections.\n请按回车按钮，进行关闭");
				System.in.read();
				System.out.println("\n闭关连接:");
				for (; count >= 0; count--) {
					rs[count].close();
					stmt[count].close();
					conn[count].close();
					System.out.print(count + "\t");
				}
			} catch (SQLException ex) {
				System.out.println("\n 关闭连接出现SQLException："+ ex.toString());
			} catch (IOException io_ex) {
				System.out.println("\n 关闭连接出现IOException："+ io_ex.toString());
			}
		}
	}
	
	/**
	* 数据库连接方法（Mysql）
	*/
	public void testMysqlMaxConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://192.168.1.254:3306/framework?useUnicode=true&characterEncoding=UTF-8";
			for (count = 0; count < 2000; count++) {
				conn[count] = DriverManager.getConnection(url,"test", "test");
				stmt[count] = conn[count].createStatement();
				rs[count] = stmt[count].executeQuery("SELECT 1 FROM dual");
				//System.out.print(count + "\t");
			}
		} catch (SQLException ex1) {
			System.out.println("\n" + ex1.toString());
		} catch (InstantiationException ex2) {
			System.out.println("\n" + ex2.toString());
		} catch (ClassNotFoundException ex3) {
			System.out.println("\n" + ex3.toString());
		} catch (IllegalAccessException ex4) {
			System.out.println("\n" + ex4.toString());
		} finally {
			try {
				System.out.println("\n系统打开的连接数"+ count--+ " MYSQL connections.\n请按回车按钮，进行关闭");
				System.in.read();
				System.out.println("\n闭关连接:");
				for (; count >= 0; count--) {
					rs[count].close();
					stmt[count].close();
					conn[count].close();
					System.out.print(count + "\t");
				}
			} catch (SQLException ex) {
				System.out.println("\n 关闭连接出现SQLException："+ ex.toString());
			} catch (IOException io_ex) {
				System.out.println("\n 关闭连接出现IOException："+ io_ex.toString());
			}
		}
	}
	/**
	* 测试方法
	* 
	* @param args
	* @throws Exception
	*/
	public static void main(String args[]) throws Exception {
//		new testMaxConn().testOracleMaxConn(); //测试Oracle默认最大连接数135
		//new testMaxConn().testMysqlMaxConn(); //测试MYSQL，默认最大连接数100
		//System.out.println(1<<3);
		//System.out.println(System.getProperty("file.encoding"));
		System.out.println("D:\\/\\eclipse_svnProjects/framework\\WebContent\\/test.doc"
				.replaceAll("/+", "\\\\").replaceAll("(\\\\)+","\\\\"));
	}
}

