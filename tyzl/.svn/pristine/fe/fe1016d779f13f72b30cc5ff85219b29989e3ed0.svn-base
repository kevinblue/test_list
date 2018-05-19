package com.tenwa.kernal.utils;

import java.util.Map;

import com.tenwa.business.service.BaseService;

public class ApConvertor{
	
	public  static <T> T getBeanByMap(BaseService txService ,Map<String,String> sourceMapModel,Class<T> beanClazz,String... entityIdentifier) throws Exception{
		T beanObject   =  beanClazz.newInstance();
		txService.copyAndOverrideExistedValueFromStringMap(sourceMapModel, beanObject, null, entityIdentifier);
		return beanObject;
	}
	public  static Object copyPropertiesByMap(BaseService txService ,Map<String,String> sourceMapModel,Object beanObject,String... entityIdentifier) throws Exception{
		txService.copyAndOverrideExistedValueFromStringMap(sourceMapModel, beanObject, null, entityIdentifier);
		return beanObject;
	}
	public static void html2Text(Map<String, String> model){
		for(String key : model.keySet()){
			String value = model.get(key);
			if(value !=null){
				value = value.replaceAll("<br[\\s]*/>", "\n");
				model.put(key, value);
			}
		}
	} 
	
	public static void text2Html(Map<String, String> model){
		for(String key : model.keySet()){
			String value = model.get(key);
			if(value !=null){
				value = value.replaceAll("\n|\r", "<br />");
				model.put(key, value);
			}
		}
	} 
}