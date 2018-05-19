package com.tenwa.kernal.utils;   

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.kernal.utils.JacksonFilter.userFilter;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-8-10 下午3:01:24
 * 类说明
 */
public class JacksonUtil {
	private static Logger logger = Logger.getLogger(JacksonUtil.class);
	public static ObjectMapper newObjectMapper() {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
			objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
			objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
			objectMapper.configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false);
			objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_PROPERTIES, false);
		return objectMapper;
	}
}
  
