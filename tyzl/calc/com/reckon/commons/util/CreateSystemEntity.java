package com.reckon.commons.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.leasing.utils.StrTools;

public class CreateSystemEntity {

	/**
	 * @author 作者 E-mail: 生成系统中的实体类的说明的
	 * @version 创建时间：2013-12-13 下午01:44:36 类说明
	 */
	public static void main(String[] args) {
		OutputStreamWriter fw =null;
		try {
			File directory = new File("");// 参数为空
			String courseFile = directory.getCanonicalPath();
			String dirpath = courseFile + "\\leasing";
			File dir = new File(StrTools.fileNameCheck(dirpath));
			File file = new File("d:\\系统实体类.html");
			 fw = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
			File[] files = dir.listFiles();
			
			fw.write("\n<html>");
			fw.write("\n<head>");
			fw.write("\n<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
			fw.write("\n<style type='text/css'>");
			fw.write("\ntable{border-collapse:collapse;margin-bottom:50}");
			fw.write("\ntable,th, td{border: 1px solid blue;}");
			fw.write("\n</style>");
			fw.write("\n</head>");
			fw.write("\n<body>");
			fw.write("\n<table>");
			fw.write("\n<tr><td>表中文名</td>");
			fw.write("\n<td>表名</td>");
			fw.write("\n<td>实体类</td></tr>");
			
			for (File f : files) {
				if (f.isDirectory() && (!f.getName().equals(".svn"))) {
					readFileName(f, fw, 0, dirpath);
				}
			}
			fw.write("\n</table>");
			for (File f : files) {
				if (f.isDirectory() && (!f.getName().equals(".svn"))) {
					readFileName(f, fw, 1, dirpath);
				}
			}
			fw.write("\n</body>");
			fw.write("\n</html>");
			fw.close();
		} catch (Exception e) {

		}finally{
			if(null!=fw){
			FileUtil.safeCloseOutputStreamWriter(fw);
			}
		}
	}

	public static void readFileName(File f, OutputStreamWriter fw, int i, String dirpath) {

		String fileName = "";
		if (f.isFile() && (!f.isDirectory())) {
			fileName = f.getAbsolutePath();
			if (fileName.indexOf(".svn") < 0) {
				fileName = fileName.replace(dirpath + "\\", "");
				fileName = fileName.replaceAll("\\\\", ".");
				if (fileName.matches("*com.tenwa.*.entity.*")) {
					fileName = fileName.replace(".java", "");
					readEntityPropotype(fileName, fw, i);
				}
			}
		} else {
			File[] files = f.listFiles();
			for (File fd : files) {
				readFileName(fd, fw, i, dirpath);
			}
		}
	}

	public static void readEntityPropotype(String fileName, OutputStreamWriter fw, int j) {
		String className = fileName;
		Class<?> demo1 = null;
		try {
			demo1 = Class.forName(className);
			Table ctable = (Table) demo1.getAnnotation(Table.class);
			FieldName tableName = (FieldName) demo1.getAnnotation(FieldName.class);
			if (j == 0) {
				fw.write("\n<tr><td>" + tableName.name() + "</td>");
				fw.write("\n<td>" + (null == ctable ? "" : ctable.name()) + "</td>");
				fw.write("\n<td><a href='#" + demo1.getName() + "'>" + demo1.getName() + "</a></td></tr>");

			} else {
				fw.write("\n<table>");
				fw.write("\n<tr><td colspan='4'>表名:" + (null == ctable ? "" : ctable.name()) + "</td></tr>");
				fw.write("\n<tr><td colspan='4'>实体类:<a name='" + demo1.getName() + "' id='" + demo1.getName() + "'>" + demo1.getName() + "</a></td></tr>");
				fw.write("\n<tr><td colspan='4'>表中文名：" + tableName.name() + "</td></tr>");
				Field[] field = demo1.getDeclaredFields();
				fw.write("\n<tr>");
				fw.write("\n<td>类属性</td>");
				fw.write("\n<td>类属性类型</td>");
				fw.write("\n<td>表字段</td>");
				fw.write("\n<td>说明</td></tr>");
				for (int i = 0; i < field.length; i++) {
					fw.write("\n<tr>");
					Field fd = field[i];
					fw.write("\n<td>" + fd.getName().toLowerCase() + "</td>");
					fw.write("\n<td>" + fd.getType().getName() + "</td>");

					Column anC = (Column) fd.getAnnotation(Column.class);
					if (null != anC) {
						fw.write("\n<td>" + anC.name() + "</td>");
					}
					JoinColumn anD = (JoinColumn) fd.getAnnotation(JoinColumn.class);
					if (null != anD) {
						fw.write("\n<td>" + anD.name() + "</td>");
					}
					if (null == anC && null == anD) {
						fw.write("\n<td></td>");
					}
					FieldName anF = (FieldName) fd.getAnnotation(FieldName.class);
					if (null != anF) {
						fw.write("\n<td>" + anF.name() + "</td>");
					} else {
						fw.write("\n<td>id</td>");
					}
					fw.write("\n</tr>");
				}
				fw.write("\n</table>");
			}
		} catch (Exception e) {
			System.out.println(fileName);
		}
	}

}
