package com.tenwa.kernal.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogUtil {
	public static void trace(Object logTrace){
		Class<?> currentLogClass = Thread.currentThread().getClass();
    	Log log = LogFactory.getLog(currentLogClass);
    	if(log.isTraceEnabled()){
    		log.trace(logTrace);
    	}
    	log = null;
	}
    public static void debug(Object logDebug){
    	Class<?> currentLogClass = Thread.currentThread().getClass();
    	Log log = LogFactory.getLog(currentLogClass);
    	if(log.isDebugEnabled()){
    		log.debug(logDebug);
    	}
    	log = null;
    }
    public static void info(Object logInfo){
    	Class<?> currentLogClass = Thread.currentThread().getClass();
    	Log log = LogFactory.getLog(currentLogClass);
    	if(log.isInfoEnabled()){
    		log.info(logInfo);
    	}
    	log = null;
    }
    public static void warn(Object logWarn){
    	Class<?> currentLogClass = Thread.currentThread().getClass();
    	Log log = LogFactory.getLog(currentLogClass);
    	if(log.isWarnEnabled()){
    		log.warn(logWarn);
    	}
    	log = null;
    }
    public static void error(Object logError){
    	Class<?> currentLogClass = Thread.currentThread().getClass();
    	Log log = LogFactory.getLog(currentLogClass);
    	if(log.isErrorEnabled()){
    		log.error(logError);
    	}
    	log = null;
    }
    public static void fatal(Object logFatal){
    	Class<?> currentLogClass = Thread.currentThread().getClass();
    	Log log = LogFactory.getLog(currentLogClass);
    	if(log.isFatalEnabled()){
    		log.fatal(logFatal);
    	}
    	log = null;
    }
    public static Log getCurrentLog(){
    	Class<?> currentLogClass = Thread.currentThread().getClass();
    	Log log = LogFactory.getLog(currentLogClass);
    	return log;
    }
}
