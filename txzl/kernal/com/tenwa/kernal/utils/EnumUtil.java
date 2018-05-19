package com.tenwa.kernal.utils;

import java.util.Locale;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

public class EnumUtil {
	private static Logger log = LoggerFactory.getLogger(EnumUtil.class);
	private static MessageSource messageSource = WebUtil.getApplicationContext().getBean(MessageSource.class);

	public static <T extends Enum<?>> String[] getEnumConstants(Class<T> clazz) {
		Object[] objects = clazz.getEnumConstants();
		String[] constants = new String[objects.length];
		for (int i = 0; i < objects.length; i++) {
			constants[i] = objects[i].toString();
		}
		return constants;
	}

	public static String getEnumMessage(Enum<?> e) {
		Locale locale = WebUtil.getSessionLocale();
		return getEnumMessage(e, locale);
	}

	public static String getEnumMessage(Enum<?> e, String defaultText) {
		Locale l = ((Locale)WebUtil.getUserSessions().get(SecurityUtil.getPrincipal().getId()));
		return getEnumMessage(e, l, defaultText);
	}
	public static String getEnumMessage(Enum<?> e, Locale l) {
		return getEnumMessage(e, l, e.name());
	}

	public static String getEnumMessage(Enum<?> e, Locale l, String defaultText) {
		if (messageSource != null) {
			System.out.println(e.getClass().getName() + "." + e.name());
			return messageSource.getMessage(e.getClass().getName() + "." + e.name(), null, defaultText, l);
		}
		return defaultText;
	}

	public static <T extends Enum<?>> String getEnumConstantsAsJson(Class<T> clazz) {
		String[] cc = getEnumConstants(clazz);
		JSONArray jsArray = new JSONArray();
		Locale locale = WebUtil.getSessionLocale();
		for (String c : cc) {
			JSONObject jsObj = new JSONObject();
			try {
				String text = WordUtils.capitalizeFully(c.toLowerCase().replace("_", " "));
				if (locale != null && messageSource != null) {
					text = messageSource.getMessage(clazz.getName() + "." + c, null, text, locale);
				}
				jsObj.put("text", text);
				jsObj.put("value", c);
				jsArray.put(jsObj);
			} catch (JSONException e) {
				log.error("", e);
			}

		}
		return jsArray.toString();
	}
	
	public static <T extends Enum<?>> String getEnumConstantsAsJson(T...ts){
		JSONArray jsArray = new JSONArray();
		for(T t : ts){
			try {
				jsArray.put(getEnumConstantAsJson(t));
			} catch (Exception e) {
				log.error("",e);
			}
		}
		return jsArray.toString();
	}

	public static <T extends Enum<?>> JSONObject getEnumConstantAsJson(T t) throws Exception {
		String text = WordUtils.capitalizeFully(t.name().toLowerCase().replace("_", " "));
		Locale locale = WebUtil.getSessionLocale();
		if (locale != null && messageSource != null) {
			text = messageSource.getMessage(t.getClass().getName() + "." + t.name(), null, text, locale);
		}
		JSONObject comboxJson = new JSONObject();

		comboxJson.put("text", text);
		comboxJson.put("value", t.name());
		return comboxJson;
	}

}
