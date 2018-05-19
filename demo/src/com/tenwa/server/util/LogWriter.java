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
 * ��־����API
 * 
 * @author JavaJeffe
 * 
 * date ---- 5:23:33 PM
 */
public final class LogWriter {

	/**
	 * �������̨��Ϣ��java������
	 * 
	 * @param info
	 */
	public static void logDebug(String info) {
		// debug��Ϣ
		if ("open".equals(ConfigReader.getResultByKey("outlog"))) {
			System.out.println(" Debug��" + info);
		}
	}

	/**
	 * �������̨��Ϣ��java������
	 * 
	 * @param info
	 */
	public static void logError(String info) {
		// debug��Ϣ
//		if ("open".equals(ConfigReader.getResultByKey("errorlog"))) {
//			System.out.println(" Error��" + info);
//		}
	}

	/**
	 * ���ִ�е�Sql
	 * 
	 * @param msg
	 * @param sqlStr
	 */
	public static void logSqlStr(String msg, String sqlStr) {
//		if ("open".equals(ConfigReader.getResultByKey("sqllog"))) {
//			System.out.println("ģ�飺" + msg + " ==============ִ��Sql���========\n"
//					+ sqlStr);
//		}
	}

	/**
	 * ������־ filereader
	 * 
	 * @param request
	 * @param info
	 * @param sql
	 */
	public static void operationLog(String info) {
		String date = new SimpleDateFormat("yyyy��MM��dd��").format(new Date());

		File file = new File((String) ConfigReader
				.getResultByKey("configOperPath")
				+ "/" + date + "����ͬ����־.txt");// ������־�ļ�
		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// д������־��Ϣ
		pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date())
				+ "ʱ�䣬ͬ������Info��" + info);

		// �ر���Դ
		pw.close();
	}

	/**
	 * �ļ���־ ����ӿ�
	 * 
	 * @param info
	 */
	public static void operationFILog(String info,String msg, String sqlStr) {
		String date = new SimpleDateFormat("yyyy��MM��dd��").format(new Date());

		File file = new File((String) ConfigReader
				.getResultByKey("configOracleOperPath")
				+ "/" + date + "����ͬ����־.txt");// ������־�ļ�

		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// д������־��Ϣ
		pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date())
				+ "ʱ�䣬ͬ������Info��" + info+"ģ�飺" + msg + " ==============ִ��Sql���========\n"
				+ sqlStr);

		// �ر���Դ
		pw.close();
	}

	/**
	 * �����ļ���־ SMS���Žӿ�
	 * 
	 * @param string
	 */
	public static void operationSMSLog(String info) {
		String date = new SimpleDateFormat("yyyy��MM��dd��").format(new Date());

		File file = new File((String) ConfigReader
				.getResultByKey("configSMSOperPath")
				+ "/" + date + "����ͬ����־.txt");// ������־�ļ�

		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// д������־��Ϣ
		pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date())
				+ "ʱ�䣬ͬ������Info��" + info);

		// �ر���Դ
		pw.close();
	}
	public static void operationLogPrint(String insAmount,String updAmount) {
		String date = new SimpleDateFormat("yyyy��MM��dd��").format(new Date());

		File file = new File((String) ConfigReader
				.getResultByKey("configOperPath")
				+ "/" + date + "����ͬ����־.txt");// ������־�ļ�
		PrintWriter pw = null;// pw
		try {
			pw = new PrintWriter(new FileWriter(file, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// д������־��Ϣ
		pw.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date())
				+ "ʱ�䣬ͬ������Info��" + insAmount+","+updAmount);
       
		// �ر���Դ
		pw.close();
	}
}
