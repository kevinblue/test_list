package com.reckon.service.impl;

import java.io.IOException;
import java.text.DateFormat;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONUtil {
	
	private static ObjectMapper objectMapper = null;
	private static JsonGenerator jsonGenerator = null; 
	static{
		objectMapper =  new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}
	
	/**
	 * 对象 转换成 JSON
	 * @param bean
	 * @return
	 */
	public static String writeEntityJSON(Object bean){
		String result = "";
		try{
			jsonGenerator.writeObject(bean); 
			result = objectMapper.writeValueAsString(bean);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
