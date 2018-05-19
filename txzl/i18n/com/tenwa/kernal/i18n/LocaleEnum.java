
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.i18n
 * 文件名：         LocalEnum.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-7-8-下午02:04:27
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.i18n;

import java.util.Locale;


 /**
 * 类名称：     LocalEnum
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-7-8 下午02:04:27
 * 修改备注：
 * @version 1.0.0
 **/

public enum LocaleEnum {
	zh_CN(new Locale("zh","CN"),"中国"),
	en_US(new Locale("en","US"),"美国");
	
	private final Locale locale;
	private final String description;
	private LocaleEnum(Locale locale, String description) {
		this.locale = locale;
		this.description = description;
	}
	public Locale getLocale() {
		return locale;
	}
	public String getDescription() {
		return description;
	}
	
}
