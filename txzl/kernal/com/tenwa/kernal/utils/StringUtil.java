package com.tenwa.kernal.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author tracywindy 2012-3-28 上午09:40:17 email:tracywindy@126.com
 * 
 */
public class StringUtil {
	public static String nullToString(final Object obj, String... defaultValue) {

		if (obj != null) {
			if (obj.getClass().isEnum()) {
				return ((Enum<?>) obj).name();
			} else {
				return obj.toString();
			}
		}
		for (String v : defaultValue) {
			return v;
		}
		return "";

	}
	public static String nullToArray(final Object obj, String... defaultValue) {
		
		if (obj != null) {
			if (obj.getClass().isEnum()) {
				return ((Enum<?>) obj).name();
			} else {
				return obj.toString();
			}
		}
		for (String v : defaultValue) {
			return v;
		}
		return "[]";
		
	}

	public static String booleanToString(final Boolean b, String... defaultValue) {
		Boolean temp = b;
		if (b == null)
			temp = false;
		
		if (defaultValue != null) {
			int c = defaultValue.length;
			if (c > 0 && temp)
				return defaultValue[0];
			if (c > 1 && !temp)
				return defaultValue[1];
		}
		return temp.toString();
	}

	public static String lTrim(String str) {
		return str.replaceAll("^\\s*", "");
	}

	public static String rTrim(String str) {
		return str.replaceAll("\\s*$", "");
	}

	@SuppressWarnings("unchecked")
	public static String join(Object items, String splitChar) {
		StringBuffer sb = new StringBuffer();
		if (items instanceof Collection) {
			Collection collectionItems = (Collection) items;
			int index = 0;
			for (Object collectionItem : collectionItems) {
				if (++index > 1) {
					sb.append(splitChar);
				}
				sb.append(StringUtil.nullToString(collectionItem));
			}
		} else {
			Object[] arrayItems = (Object[]) items;
			for (int i = 0; i < arrayItems.length; i++) {
				if (i > 0) {
					sb.append(splitChar);
				}
				sb.append(StringUtil.nullToString(arrayItems[i]));
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static String join(Object items, String splitChar, String itemPrefix, String itemSuffix) {
		StringBuffer sb = new StringBuffer();
		if (items instanceof Collection) {
			Collection collectionItems = (Collection) items;
			int index = 0;
			for (Object collectionItem : collectionItems) {
				if (++index > 1) {
					sb.append(splitChar);
				}
				sb.append(itemPrefix + StringUtil.nullToString(collectionItem) + itemSuffix);
			}
		} else {
			Object[] arrayItems = (Object[]) items;
			for (int i = 0; i < arrayItems.length; i++) {
				if (i > 0) {
					sb.append(splitChar);
				}
				sb.append(StringUtil.nullToString(arrayItems[i]));
			}
		}
		return sb.toString();
	}

	public static String toSqlJoinString(String joinString, String splitChar) {
		if (null == joinString)
			return "''";
		String[] arr = joinString.split(splitChar);
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (String str : arr) {
			if (index++ > 0) {
				sb.append(",");
			}
			sb.append("'" + str + "'");
		}
		return sb.toString();
	}

	public static String empty2Other(Object obj, String replaceValue) {
		String value = StringUtil.nullToString(obj);
		if (value.trim().isEmpty()) {
			return replaceValue;
		}
		return value;
	}

	public static String getJsonString(Object str) {

		return nullToString(str).replaceAll("\"", "'").replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n").replaceAll("\\\\", "\\\\\\\\");
	}

	public static String traceExceptionMessage(Object source, Throwable e) {
		String newLine = System.getProperty("line.separator");
		StringBuffer exceptionInfo_sb = new StringBuffer(source.toString());
		exceptionInfo_sb.append(newLine + e.toString());
		StackTraceElement[] trace = e.getStackTrace();
		for (int i = 0; i < trace.length; i++) {
			exceptionInfo_sb.append(newLine + "\t " + trace[i]);
		}
		return exceptionInfo_sb.toString();
	}

	public static String getClearWhereSQL(String table_sql_temp) {
		/*
		 * Pattern p = Pattern.compile("\\s*where\\s*1\\s*=\\s*1\\s*and?", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		 * table_sql_temp = p.matcher(table_sql_temp).replaceAll(" _W_H_E_R_E_ ");
		 * p = Pattern.compile("\\s*where\\s*1\\s*=\\s*1\\s*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		 * table_sql_temp = p.matcher(table_sql_temp).replaceAll(" ");
		 * p = Pattern.compile(" _W_H_E_R_E_ ", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		 * table_sql_temp = p.matcher(table_sql_temp).replaceAll(" WHERE ");
		 */
		return table_sql_temp;
	}

	public static boolean isExistElement(Object[] arr, Object findValue) {
		boolean isFound = false;
		for (Object value : arr) {
			if (findValue.equals(value)) {
				isFound = true;
			}
			break;
		}
		return isFound;
	}

	public static Collection<String> getCollectionByString(String collectionType, String sourceString, String splitString) {
		Collection<String> collection = null;
		if ("set".equalsIgnoreCase(collectionType)) {
			collection = new HashSet<String>();
		} else if ("list".equals(collectionType)) {
			collection = new ArrayList<String>();
		}
		if (null == sourceString) {
			return collection;
		}
		for (String str : sourceString.split(splitString)) {
			collection.add(str);
		}
		return collection;
	}

	public static Collection<Long> getLongCollectionByString(String collectionType, String sourceString, String splitString) {
		Collection<Long> collection = null;
		if ("set".equalsIgnoreCase(collectionType)) {
			collection = new HashSet<Long>();
		} else if ("list".equals(collectionType)) {
			collection = new ArrayList<Long>();
		}
		for (String str : sourceString.split(splitString)) {
			collection.add(Long.valueOf(str));
		}
		return collection;
	}
}
