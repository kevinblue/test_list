/**
 * cn.tenwa.log.LogWriter
 * create by JavaJeffe.
 * date Jun 18, 2010
 */
package com.tenwa.server.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志处理API
 * 
 * @author JavaJeffe
 * 
 * date ---- 5:23:33 PM
 */
public final class LogWriter {

	/**
	 * 输出控制台信息，java代码中
	 * 
	 * @param info
	 */
	public static void logDebug(String info) {
		// debug信息
		if ("open".equals(ConfigReader.getResultByKey("outlog"))) {
			System.out.println(" Debug：" + info);
		}
	}

	/**
	 * 输出控制台信息，java代码中
	 * 
	 * @param info
	 */
	public static void logError(String info) {
		// debug信息
//		if ("open".equals(ConfigReader.getResultByKey("errorlog"))) {
//			System.out.println(" Error：" + info);
//		}
	}

	/**
	 * 输出执行的Sql
	 * 
	 * @param msg
	 * @param sqlStr
	 */
	public static void logSqlStr(String msg, String sqlStr) {
//		if ("open".equals(ConfigReader.getResultByKey("sqllog"))) {
//			System.out.println("模块：" + msg + " ==============执行Sql语句========\n"
//					+ sqlStr);
//		}
	}

	/**
	 * 操作日志 filereader
	 * 
	 * @param request
	 * @param info
	 * @param sql
	 */
	public static void operationLog(String info) {
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());

		File file = new File((String) ConfigReader
				.getResultByKey("configOperPath")
				+ "/" + date + "数据同步日志.txt");// 创建日志文件
		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 写操作日志信息
		pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date())
				+ "时间，同步数据Info：" + info);

		// 关闭资源
		pw.close();
	}

	/**
	 * 文件日志 财务接口
	 * 
	 * @param info
	 */
	public static void operationFILog(String info,String msg, String sqlStr) {
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());

		File file = new File((String) ConfigReader
				.getResultByKey("configOracleOperPath")
				+ "/" + date + "数据同步日志.txt");// 创建日志文件

		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 写操作日志信息
		pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date())
				+ "时间，同步数据Info：" + info+"模块：" + msg + " ==============执行Sql语句========\n"
				+ sqlStr);

		// 关闭资源
		pw.close();
	}

	/**
	 * 操作文件日志 SMS短信接口
	 * 
	 * @param string
	 */
	public static void operationSMSLog(String info) {
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());

		File file = new File((String) ConfigReader
				.getResultByKey("configSMSOperPath")
				+ "/" + date + "数据同步日志.txt");// 创建日志文件

		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 写操作日志信息
		pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date())
				+ "时间，同步数据Info：" + info);

		// 关闭资源
		pw.close();
	}
	public static void operationLogPrint(String insAmount,String updAmount) {
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());

		File file = new File((String) ConfigReader
				.getResultByKey("configOperPath")
				+ "/" + date + "数据同步日志.txt");// 创建日志文件
		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 写操作日志信息
		pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date())
				+ "时间，同步数据Info：" + insAmount+","+updAmount);
       
		// 关闭资源
		pw.close();
	}
}
