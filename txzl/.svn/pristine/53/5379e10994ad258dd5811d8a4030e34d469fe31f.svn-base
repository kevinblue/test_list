package com.tenwa.business.entity;

import org.json.JSONArray;
import org.json.JSONException;

public class JsonUtil {
	public static JSONArray combine(JSONArray...srcArrays) throws JSONException {
		JSONArray combinedArray = new JSONArray();
		if(srcArrays == null || srcArrays.length == 0)
			return combinedArray;
		
		for(JSONArray srcArray: srcArrays){
			for(int i = 0; srcArray != null && i < srcArray.length(); i++){
				combinedArray.put(srcArray.get(i));
			}
		}
		return combinedArray;
	}
}
