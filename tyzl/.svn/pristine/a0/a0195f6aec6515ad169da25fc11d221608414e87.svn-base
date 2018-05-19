package com.tenwa.kernal.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.formula.functions.T;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.tenwa.kernal.annotation.FieldName;

/**
 * @author Administrator 获取字段注释
 */
public class BeanFieldUtil {
	private static final Logger log = LoggerFactory.getLogger(BeanFieldUtil.class);
	/**
	 * @param clazz
	 *            需要获取的类
	 * @return 所有字段的中文名称
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 */
	public static Map<String, String> parseFieldNames(Class clazz) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException, InstantiationException {

		// List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (Field field : clazz.getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}

			// 字段
			FieldName fieldName = field.getAnnotation(FieldName.class);
			// JsonManagedReference jsonManagedReference = field.getAnnotation(JsonManagedReference.class);
			// JsonIgnore jsonIgnore = field.getAnnotation(JsonIgnore.class);
			// get方法

			// JsonManagedReference getMethodJsonManagedReference = getMethod.getAnnotation(JsonManagedReference.class);
			// JsonIgnore getMethodjsonIgnore = getMethod.getAnnotation(JsonIgnore.class);
			// set方法

			// JsonManagedReference setMethodJsonManagedReference = setMethod.getAnnotation(JsonManagedReference.class);
			// JsonIgnore setMethodJsonIgnore = setMethod.getAnnotation(JsonIgnore.class);

			/*
			 * if (jsonManagedReference != null || getMethodJsonManagedReference != null || setMethodJsonManagedReference != null || jsonIgnore != null || getMethodjsonIgnore !=
			 * null || setMethodJsonIgnore != null) { continue; }
			 */

			// map.put("field", field.getName());
			String showName = "";
			if (fieldName != null) {
				showName = fieldName.name();
			} else {
				Method getMethod = BeanFieldUtil.getGetMethod(clazz, field.getName());
				Method setMethod = BeanFieldUtil.getSetMethod(clazz, field.getName());
				if (getMethod != null) {
					FieldName getMethodFieldName = getMethod.getAnnotation(FieldName.class);
					if (getMethodFieldName != null) {
						showName = getMethodFieldName.name();
					} else {
						showName = field.getName();
					}

				} else if (setMethod != null) {
					FieldName setMethodFieldName = setMethod.getAnnotation(FieldName.class);
					if (setMethodFieldName != null) {
						showName = setMethodFieldName.name();
					} else {
						showName = field.getName();
					}
				} else {
					showName = field.getName();
				}
			}
			map.put(field.getName(), showName);
		}

		return map;
	}

	public static String parseClassName(Class clazz) {

		FieldName name = (FieldName) clazz.getAnnotation(FieldName.class);
		String className = "";
		if (null != name) {
			className = name.name();
		}
		return className;
	}

	/**
	 * java反射bean的get方法
	 * 
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static Method getGetMethod(Class objectClass, String fieldName) {
		try {
			return new PropertyDescriptor(fieldName, objectClass).getReadMethod();

		} catch (IntrospectionException e1) {
			// e1.printStackTrace();
			return null;
		}
		/*
		 * StringBuffer sb = new StringBuffer(); sb.append("get"); sb.append(fieldName.substring(0, 1).toUpperCase()); sb.append(fieldName.substring(1)); try { return
		 * objectClass.getMethod(sb.toString()); } catch (Exception e) { } return null;
		 */
	}

	/**
	 * java反射bean的set方法
	 * 
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */
	public static Method getSetMethod(Class objectClass, String fieldName) {
		try {
			return new PropertyDescriptor(fieldName, objectClass).getWriteMethod();
		} catch (IntrospectionException e) {
			return null;
			// e.printStackTrace();
		}
		/*
		 * try { Class[] parameterTypes = new Class[1]; Field field = objectClass.getDeclaredField(fieldName); parameterTypes[0] = field.getType(); StringBuffer sb = new
		 * StringBuffer(); sb.append("set"); sb.append(fieldName.substring(0, 1).toUpperCase()); sb.append(fieldName.substring(1)); Method method =
		 * objectClass.getMethod(sb.toString(), parameterTypes);
		 * 
		 * return method;
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } return null;
		 */
	}

	/**
	 * 判断是否是基础类型
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isWrapClass(Class clz) {
		if (clz.getClassLoader() == null) {
			return true;
		} else {
			return false;
		}
		/*
		 * try {
		 * 
		 * clz.getClassLoader() return ((Class) clz.getField("TYPE").get(null)).isPrimitive(); } catch (Exception e) { return false; }
		 */
	}

	public static String getFuzzyField(Class<T> type) {
		StringBuffer conditions = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		Method[] method = type.getDeclaredMethods();
		return null;
	}

	/**
	 * 循环获得字段的值，如果字段是个一个在com.tenwa.business.entity包内的内容,则表示返回的是个实体 如果连续两层实体都为
	 * 
	 * @param entity
	 * @return
	 */
	public static String getMethodValues(Class<T> entity) {
		StringBuffer conditions = new StringBuffer();
		Method[] method = entity.getDeclaredMethods();

		for (Method m : method) {
			if (m.getName().indexOf("get") != -1) {
				Object value;
				String stringValue = null;
				// 返回类型是com.tenwa.business.entity包内的内容,则表示返回的是个实体
				Boolean entityType = m.getReturnType().toString().contains("entity.");
				Boolean setType = m.getReturnType().equals(Set.class);
				if (setType) {
					continue;
				}
				try {
					value = m.invoke(entity);
					if (value != null && !value.equals("")) {
						// 获得关联类的内容
						if (entityType) {
							getMethodValues((Class<T>) value);
							/*
							 * Method getId = null; getId = value.getClass().getMethod("getId"); Object o = getId.invoke(value); if(o != null){ stringValue = o.toString(); } else {
							 * continue; }
							 */
						} else {
							stringValue = value.toString();
						}
						StringBuffer sb = new StringBuffer();
						sb.append(m.getName().substring(3, 4).toLowerCase());
						sb.append(m.getName().substring(4));
						conditions.append("c." + sb + " like '%" + stringValue + "%' and ");
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public static Field findField(Class<?> clazz, String column) {
		Field field = null;
		int pointIndex = column.indexOf(".");
		if (pointIndex == -1) {
			field = ReflectionUtils.findField(clazz, column);
		} else {
			String columnHead = column.substring(0, pointIndex);
			String columnBody = column.substring(pointIndex + 1);

			field = ReflectionUtils.findField(clazz, columnHead);
			if (null != field) {
				Class<?> clazz2 = field.getType();
				return BeanFieldUtil.findField(clazz2, columnBody);
			}
		}
		return field;
	}

	@SuppressWarnings("hiding")
	public static <T> T copyProperties(T source, T destination, Map<Class<?>, String[]> equalsProps, Map<Class<?>, String[]> excludeProps,
			boolean removeNoExist) {
		if (source == null || destination == null) {
			return null;
		}

		if (source instanceof Collection) {
			for (Object s : (Collection<?>) source) {
				for (Object d : (Collection<?>) destination) {
					if (isEqual(s, d, equalsProps)) {
						d = copyProperties(s,d,equalsProps,excludeProps,removeNoExist);
					}
				}
			}
		}

		return source;

	}

	private static boolean isEqual(Object s, Object d, Map<Class<?>, String[]> equalsProps) {
		if(s == null && d == null){
			return true;
		}else if(s== null || d==null){
			return false;
		}
		
		if (!s.getClass().getName().equals(d.getClass().getName())) {
			return false;
		}
		
		for (Class<?> clazz : equalsProps.keySet()) {
			if (s.getClass().isAssignableFrom(clazz)) {
				String[] props = equalsProps.get(clazz);
				if (props != null) {
					Boolean compareResult = Boolean.TRUE;
					for (String prop : props) {
						try {
							Object src_o = PropertyUtils.getProperty(s, prop);
							Object des_o = PropertyUtils.getProperty(d, prop);
							if(src_o == null && des_o == null){
								compareResult &= Boolean.TRUE;
							}else if(src_o == null || des_o == null){
								return false;
							}else if(src_o.equals(des_o)){
								compareResult &= Boolean.TRUE;
							}else{
								return false;
							}
						} catch (Exception e) {
							log.error("",e);
							return false;
							
						} 
					}
					return compareResult;
				}else{
					return false;
				}				
			}
		}
		return s.equals(d);
	}
	/**
	 * @param source
	 * @return
	 * @throws JSONException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONObject deepJonsobject(JSONObject source) throws JSONException, InstantiationException, IllegalAccessException {
		JSONObject dest = source.getClass().newInstance();
		Iterator it = source.keys(); 
		while(it.hasNext()) {
			String key=(String) it.next();
			dest.put(key,source.getString(key));
		}
		return dest;
	}

}
