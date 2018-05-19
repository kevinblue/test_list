/**
 * cn.tenwa.log.LogWriter
 * create by JavaJeffe.
 * date Jun 18, 2010
 */
package com.tenwa.leasing.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tools.ant.taskdefs.Mkdir;

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
	public static String logError(String errorInfo,String info) {
		
		String date1 = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		String date2 = new SimpleDateFormat("HH时mm分ss秒").format(new Date());
		String path="D:/Error/" + date1;
		File file = new File(path);// 创建日志文件
		if(!file.exists()){
			file.mkdirs();
		}
		String fullPath=path+"/错误信息"+date2+".txt";
		File file2=new File(fullPath);
		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file2, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 写操作日志信息
		pw.println("错误原因："+errorInfo+"@需要保存的数据@"+info);

		// 关闭资源
		pw.close();
		return fullPath;
	}

	
	/**
	 * 操作日志 filereader
	 * 
	 * @param request
	 * @param info
	 * @param sql
	 */
	public static String operationLog(String info) {
		String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		String date2 = new SimpleDateFormat("HH时mm分SS秒").format(new Date());
		String path="D:/Payment/" + date;
		File file = new File(path);// 创建日志文件
		if(!file.exists()){
			file.mkdirs();
		}
		String fullPath=path+"/同步数据"+date2+".txt";
		File file2=new File(fullPath);
		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file2, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 写操作日志信息
		pw.println(info);

		// 关闭资源
		pw.close();
		return fullPath;
	}

	/**
	 * 文件日志 财务接口
	 * 
	 * @param info
	 */
}
