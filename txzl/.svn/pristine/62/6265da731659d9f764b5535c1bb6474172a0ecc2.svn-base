package com.tenwa.kernal.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.User;
import com.tenwa.freemaker.tools.FreemakerTool;

public class VoUtil {
	public static<T> List<Map<String, String>> convertListToVoMap(List<T>pos,Boolean isThousand)  throws Exception{
		List<Map<String, String>> returnList = new ArrayList<Map<String,String>>();
		for(T po : pos){
			Map<String, String> returnMap = new HashMap<String, String>();
			convertObjectToMap(returnMap,po,null,isThousand);
			returnList.add(returnMap);
		}
		return returnList;
	}
	public static<T> Map<String, String> convertObjectToMap(Map<String, String> resultMap,Object obj,String pre,Boolean isThousand)throws Exception{
		Class<?>clazz = obj.getClass();
		/*for(Field field : clazz.getDeclaredFields()){
			System.out.println(field.getName());
		}*/
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor descriptor : properties){
			String propertyName =  descriptor.getName().toLowerCase();
			if(!propertyName.equals("class")){
				Method readMethod = descriptor.getReadMethod();
				if (readMethod != null) {
					Object result = readMethod.invoke(obj, new Object[0]);
					if(null != pre){
						propertyName = pre+"_"+propertyName;
					}
					if (result != null) {
						if(result instanceof Date) {
							String values = DateTools.formatToDate((Date) result);
							resultMap.put(propertyName, values);
						}else if(result instanceof BigDecimal){
							if(isThousand){
								String throundValue = covertNumberToThousand((BigDecimal)result);
								resultMap.put(propertyName, throundValue);
							}else{
								resultMap.put(propertyName, result.toString());
							}
						}else if(EntityUtil.isTenwaEntity(result.getClass())){
							Field field = result.getClass().getDeclaredField("id");
							if(null != field){
								resultMap.put(propertyName, (String)result.getClass().getMethod("getId").invoke(result));
							}
						}else if(FreemakerTool.checkIsBaseClass(result.getClass().getSimpleName()) || result.getClass().isEnum()){
							resultMap.put(propertyName, result.toString());
						}
					}
				}
			}
		}
		return resultMap;
	}
	
	
	public static String covertNumberToThousand(BigDecimal numberBig){
		numberBig = numberBig == null ? BigDecimal.ZERO : numberBig;
		String number = numberBig.toString();
		String symbol = "";
		if(number.startsWith("+") || number.startsWith("-")){
			symbol = number.substring(0,1);
			number = number.substring(1);
		}
		DecimalFormat df = null;
		if(number.indexOf(".") > 0) { 
           if(number.length() - number.indexOf(".")-1 == 0) 
           { 
                df = new DecimalFormat("###,##0."); 
           }else if(number.length() - number.indexOf(".")-1 == 1) 
           { 
                df = new DecimalFormat("###,##0.0"); 
           }else 
           { 
                df = new DecimalFormat("###,##0.00"); 
            } 
	    }else{ 
	           df = new DecimalFormat("###,##0"); 
	    } 
		return symbol + df.format(new BigDecimal(number).doubleValue());
	}
		
}
