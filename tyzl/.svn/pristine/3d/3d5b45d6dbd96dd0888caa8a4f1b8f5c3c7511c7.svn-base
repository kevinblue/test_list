
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.cache
 * 文件名：         ParameterCacheKeyGenerator.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-4-23-下午11:33:19
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.cache;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.key.CacheKeyGenerator;


 /**
 * 类名称：     ParameterCacheKeyGenerator
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-4-23 下午11:33:19
 * 修改备注：
 * @version 1.0.0
 **/
@Component(value="ArgumentsCacheKeyGenerator")
public class ArgumentsCacheKeyGenerator implements CacheKeyGenerator<Serializable> {   
	    private static final String PREFIX = "";
	    private static final String SUFFIX = "";
	    @Override  
	    public Serializable generateKey(MethodInvocation methodInvocation) {
	    	methodInvocation.getArguments();
	        String cacheKeyName = String.format("%s", methodInvocation.getArguments()[0]);   
	        return cacheKeyName;   
	    }   
	  
	    @Override  
	    public Serializable generateKey(Object... data) 
	    {   
	        return null;   
	    }   
	   
	} 
