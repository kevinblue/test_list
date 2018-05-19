package com.tenwa.leasing.utils;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.EncodeFilter;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;



/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc ( 字符串处理工具类)
 */
public class StrTools {
	/**
	 * 替换字符串
	 * 
	 * @param from
	 *            String 原始字符串
	 * @param to
	 *            String 目标字符串
	 * @param source
	 *            String 母字符串
	 * @return String 替换后的字符串
	 */
	public static String replace(String from, String to, String source) {
		if (source == null || from == null || to == null)
			return null;
		StringBuffer bf = new StringBuffer("");
		int index = -1;
		while ((index = source.indexOf(from)) != -1) {
			bf.append(source.substring(0, index) + to);
			source = source.substring(index + from.length());
			index = source.indexOf(from);
		}
		bf.append(source);
		return bf.toString();
	}

	/**
	 * 
	 * @param str
	 *            查找的字符串
	 * @param splitsign
	 *            分隔符
	 * @return 分隔后的字符串数组
	 */
	public static String[] split(String str, String splitsign) {
		int index;
		if (str == null || splitsign == null)
			return null;
		ArrayList<String> al = new ArrayList<String>();
		while ((index = str.indexOf(splitsign)) != -1) {
			al.add(str.substring(0, index));
			str = str.substring(index + splitsign.length());
		}
		al.add(str);
		return (String[]) al.toArray(new String[0]);
	}

	/**
	 * request字符串中文处理
	 * 
	 * @param str
	 *            待处理字符串
	 * @return
	 */
	public String getStr(String str) {
		try {
			String temp_p = str;
			byte[] temp_t = temp_p.getBytes("ISO8859-1");
			String temp = new String(temp_t);
			return temp;
		} catch (Exception e) {

		}
		return "";
	}

	/**
	 * DB字符串取出处理
	 * 
	 * @param str1
	 * @return
	 */
	public static String getDBStr(String str1) {
		try {
			String temp_n = str1;
			if ((temp_n == null) || (temp_n.equals("")) || (temp_n.equals("null"))) {
				temp_n = "";
			} else {
			}
			return temp_n;
		} catch (Exception e) {

		}
		return "";
	}

	/**
	 * 
	 * @param sign
	 *            分隔符
	 * @param sourceString
	 *            待处理字符串
	 * @return 处理完的集合
	 */
	public Vector<String> splitString(String sign, String sourceString) {
		Vector<String> splitArrays = new Vector<String>();
		int i = 0;
		int j = 0;
		if (sourceString.length() == 0) {
			return splitArrays;
		}
		while (i <= sourceString.length()) {
			j = sourceString.indexOf(sign, i);
			if (j < 0) {
				j = sourceString.length();
			}
			splitArrays.addElement(sourceString.substring(i, j));
			i = j + 1;
		}
		return splitArrays;
	}

	/**
	 * 数字格式化，xx,xxx,xxx.xx
	 * 
	 * @param numstr
	 *            要格式化的数字字符串
	 * @param style
	 *            要格式化成的形式
	 * @return
	 */
	public String formatNumberStr(String numstr, String style) {
		try {
			String temp_num = numstr;
			if ((temp_num == null) || (temp_num.equals(""))) {
				temp_num = "";
			} else {
				DecimalFormat ft = new DecimalFormat(style);
				BigDecimal bd = new BigDecimal(temp_num);
				temp_num = ft.format(bd);
			}
			return temp_num;
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 
	 * ( 大字符串截取前10加...返回)
	 * 
	 * @param str
	 * @return
	 */
	public static String getTenLength(String str) {
		if (str != null && !str.equals("")) {

			if (str.length() > 10) {
				return str.substring(0, 11) + "...";

			} else {
				return str;
			}
		} else {
			return "";
		}

	}

	/**
	 * 
	 * (替换sql的条件)
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String,Object> setSqlCondtion(String sql, Map<String, String> condtion) {
		String tempSql = sql;
		Pattern p = Pattern.compile("(\\{(.*?)\\})");
		Matcher m = p.matcher(sql);
		Map returnMap=new HashMap();
		List valueList=new ArrayList();
		List valueType=new ArrayList();
		while (m.find()) {
			String key = m.group(1).toString();
			key = key.replace("{", "");
			key = key.replace("}", "");
			if (condtion.containsKey(key)) {
				tempSql = tempSql.replace(m.group(1).toString(), "?");
				valueList.add(StringUtil.nullToString(condtion.get(key)));
				valueType.add(Types.VARCHAR);
			} else {
				System.out.println("sql=" + sql + "没有条件参数据" + m.group(1));
			}
		}
		returnMap.put("sql", tempSql);
		returnMap.put("value", valueList.toArray());
		returnMap.put("valueType", valueType.toArray());
		return returnMap;		
	}
	public static String fileNameCheck(String filename) throws BusinessException{
		
		String webpath=FileUtil.getFilePathString(WebUtil.getWebContextRealPath());
		String attacheFile=FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath());
		//检查上一级的目录
		if(filename.indexOf("../")>0 ){
			filename=filename.replace("../", "");
		}
		if(filename.indexOf("./")>0 ){
			filename=filename.replace("./", "");
		}
		//检查文件的全路径
		if(filename.indexOf("/")>0){
			if(filename.indexOf(webpath)>=0||filename.indexOf(attacheFile)>=0){						
			}else{
				throw new BusinessException("路径名无效"+filename);
			}
		}
		if(filename.indexOf(".")>0){
			Pattern p = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf|xml|doc|docx|xls|xlsm|xlsx|zip|rar|mpp|ppt|pptx))$)");
			Matcher m = p.matcher(filename);
			if(!m.find()){
				throw new BusinessException("上传或生成文件类型无效"+filename);
			}
		}
		return filename;
	}
	public static String outPrintCheck(String message){
		if(message.startsWith("<script")&&message.endsWith("script>")){
			return message;
		}else{
			return EncodeFilter.encode(message);
		}
		
	}
	public static String logCheck(String message){
		return message;
	}
	public static String sqlCheck(String message){
		return message;
	}
	
}