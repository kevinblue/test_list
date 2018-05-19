
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.utils
 * 文件名：         LoggerUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-8-上午10:07:23
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


 /**
 * 类名称：     LoggerUtil
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-9-8 上午10:07:23
 * 修改备注：
 * @version 1.0.0
 **/ 

public class LoggerUtil {
   private static Log log = null;
   public static void error(Class<?> clazz,String info){
	   log = LogFactory.getLog(clazz);
	   log.error(info);
   }
   public static void error(Class<?> clazz,String info,Exception e){
	   log = LogFactory.getLog(clazz);
	   log.error(info+e.getMessage());
   }
}
