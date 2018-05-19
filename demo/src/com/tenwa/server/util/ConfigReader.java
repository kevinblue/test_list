/**
 * cn.tenwa.util.ConfigReader
 * create by JavaJeffe.
 * date Jun 21, 2010
 */
package com.tenwa.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author JavaJeffe
 * 
 * date ---- 3:21:11 PM
 */
public final class ConfigReader {
	private static Map<String, Object> configMap = new HashMap<String, Object>(
			0);// Config Map

	public ConfigReader() {
	}

	static {
		// ��ȡservice�����ļ�
		Properties properties = loadProperties("sysConfig.properties");
		// ���������ݻ���
		Set<Map.Entry<Object, Object>> entrys = properties.entrySet();
		for (Map.Entry<Object, Object> entry : entrys) {
			String name = (String) entry.getKey();
			String result = (String) entry.getValue();
			// ����beanMap��
			configMap.put(name, result);
		}
	}

	/**
	 * ����config�����ļ�
	 * 
	 * @param name -
	 *            �ļ�ȫ����
	 * @return
	 */
	public static Properties loadProperties(String name) {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = ConfigReader.class.getClassLoader().getResourceAsStream(name);
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException("�ر�config����Դ�����쳣", e);
				}
			}
		}
		return properties;
	}

	/**
	 * �õ�ָ��key��ֵ
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static Object getResultByKey(String key) {
	   return configMap.get(key);
	}

}
