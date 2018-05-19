package com.reckon.commons.helper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.reckon.commons.base.RentPlan;
import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;

public class ObjectConvertUtils {

	public static void main(String[] args) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rentList", "3");
			map.put("rent", "3");
			map.put("startDate", "2013-12-06");
			RentPlan rp = convertMapToBean(RentPlan.class, map);
			System.out.println(rp.getRentList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将一个对象数组转化为另一个对象数组
	 */
	public static <T, K> List<K> convertObjectList(Class<K> type, List<T> objectList) throws Exception {
		List<K> result = new ArrayList<K>();
		for (T rp : objectList) {
			Map<String, Object> map = ObjectConvertUtils.convertBeanToMap(rp);
			K temp = ObjectConvertUtils.convertMapToBean(type, map);
			result.add(temp);
		}
		return result;
	}
	
	/**
	 * 将json字符串转化为一个对象数组
	 */
	public static <T, K> List<K> convertObjectByJson(Class<K> type, String jsonstr) throws Exception {
		List<K> result = new ArrayList<K>();
		if(null==jsonstr||"".equals(jsonstr))return result;
		ObjectMapper objectMapper	= new ObjectMapper();
		JSONArray jsonarray=new JSONArray(jsonstr);
		for(int i=0;i<jsonarray.length();i++){
			JSONObject obj=jsonarray.optJSONObject(i);
			Map<String,Object> objMap = objectMapper.readValue(obj.toString(),Map.class);//转成map  
			K k = ObjectConvertUtils.convertMapToBean(type, objMap);
		    result.add(k);
		}
		return result;
	}

	/**
	 * 对象转换，同名属性对转
	 * 
	 * @param type
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T, K> K convertObject(Class<K> type, T obj) throws Exception {
		Map<String, Object> map = ObjectConvertUtils.convertBeanToMap(obj);
		K temp = ObjectConvertUtils.convertMapToBean(type, map);
		return temp;
	}

	
	/**
	 * 将一个Map转化为一个JavaBean对象
	 */
	public static <T> T convertMapToBean(Class<T> type, Map<String, Object> map) throws Exception {
		Map<String, String> mapParams = new HashMap<String, String>();
		for (Object key : map.keySet()) {
			String keyValue = "";
			if(key instanceof String){
				keyValue = (String)key;
			}else{
				keyValue = key+"";
			}
			mapParams.put(keyValue.replace("_", "").toLowerCase(), map.get(keyValue) + "");
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		T obj = type.newInstance();
		for (int i = 0; i < properties.length; i++) {
			PropertyDescriptor descriptor = properties[i];
			String propertyName = descriptor.getName();
			String value = mapParams.get(propertyName.replace("_", "").toLowerCase());
			if (value != null) {
				Class<?>[] parameterType = descriptor.getWriteMethod().getParameterTypes();
				String first = parameterType[0].getSimpleName();
				if (!"null".equals(value) && value != null && value.length() > 0) {
					System.out.println(value);
					if ("int".equals(first) || "Integer".equals(first)) {
						descriptor.getWriteMethod().invoke(obj, Integer.valueOf(value));
					} else if ("Long".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Long.valueOf(value));
					} else if ("Double".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Double.valueOf(value));
					} else if ("Float".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Float.valueOf(value));
					} else if ("Boolean".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Boolean.valueOf(value));
					} else if ("Short".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Short.valueOf(value));
					} else if ("Byte".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Byte.valueOf(value));
					} else if ("BigDecimal".equals(first)) {
						descriptor.getWriteMethod().invoke(obj, new BigDecimal(value.replace(",", "")));
					} else if ("Date".equals(first)) {
						descriptor.getWriteMethod().invoke(obj, DateTools.parseToDate(value));
					} else if ("DictionaryData".equals(first)) {
						DictionaryData temp = new DictionaryData();
						temp.setId(value);
						temp.setCode(value);
						temp.setName(mapParams.get(propertyName.replace("_", "").toLowerCase()+"name"));
						descriptor.getWriteMethod().invoke(obj, temp);
					} else if ("User".equals(first)) {
						User temp = new User();
						temp.setId(value);
						descriptor.getWriteMethod().invoke(obj, temp);
					} else if ("String".equals(first)) {
						descriptor.getWriteMethod().invoke(obj, value);
					}
				}
			}
		}
		return obj;
	}
	public static <T> T convertMapToBeanEBFIL(Class<T> type, Map<String, String> map) throws Exception {
		Map<String, String> mapParams = new HashMap<String, String>();
		for (Object key : map.keySet()) {
			String keyValue = "";
			if(key instanceof String){
				keyValue = (String)key;
			}else{
				keyValue = key+"";
			}
			mapParams.put(keyValue.replace("_", "").toLowerCase(), map.get(keyValue) + "");
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		T obj = type.newInstance();
		for (int i = 0; i < properties.length; i++) {
			PropertyDescriptor descriptor = properties[i];
			String propertyName = descriptor.getName();
			String value = mapParams.get(propertyName.replace("_", "").toLowerCase());
			if (value != null) {
				Class<?>[] parameterType = descriptor.getWriteMethod().getParameterTypes();
				String first = parameterType[0].getSimpleName();
				if (value != null && value.length() > 0) {
					if ("int".equals(first) || "Integer".equals(first)) {
						descriptor.getWriteMethod().invoke(obj, Integer.valueOf(value));
					} else if ("Long".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Long.valueOf(value));
					} else if ("Double".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Double.valueOf(value));
					} else if ("Float".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Float.valueOf(value));
					} else if ("Boolean".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Boolean.valueOf(value));
					} else if ("Short".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Short.valueOf(value));
					} else if ("Byte".equalsIgnoreCase(first)) {
						descriptor.getWriteMethod().invoke(obj, Byte.valueOf(value));
					} else if ("BigDecimal".equals(first)) {
						descriptor.getWriteMethod().invoke(obj, new BigDecimal(value));
					} else if ("Date".equals(first)) {
						descriptor.getWriteMethod().invoke(obj, DateTools.parseToDate(value));
					} else if ("DictionaryData".equals(first)) {
						DictionaryData temp = new DictionaryData();
						temp.setId(value);
						temp.setCode(value);
						descriptor.getWriteMethod().invoke(obj, temp);
					} else if ("User".equals(first)) {
						User temp = new User();
						temp.setId(value);
						descriptor.getWriteMethod().invoke(obj, temp);
					} else if ("String".equals(first)) {
						descriptor.getWriteMethod().invoke(obj, value);
					}
				}
			}
		}
		return obj;
	}
	
	public static<T,K> T convertBeanToBean(K bean,T newBean) throws Exception{
		Map<String, Object> returnMap = ObjectConvertUtils.convertBeanToMap(bean);
		return (T)ObjectConvertUtils.convertMapToBean(newBean.getClass(), returnMap);
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 */
	public static <T> Map<String, Object> convertBeanToMap(T bean) throws Exception {
		Class<?> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < properties.length; i++) {
			PropertyDescriptor descriptor = properties[i];
			String propertyName = descriptor.getName().toLowerCase();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				if (readMethod != null) {
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						if (result instanceof Date) {
							String values = DateTools.formatToDate((Date) result);
							returnMap.put(propertyName, values);
						} else if (result instanceof User) {
							String values = ((User) result).getId();
							returnMap.put(propertyName, values);
						} else if (result instanceof DictionaryData) {
							String values = ((DictionaryData) result).getCode();
							returnMap.put(propertyName, values);
						} else {
							returnMap.put(propertyName, result.toString());
						}
					}
				}
			}
		}
		return returnMap;
	}
}