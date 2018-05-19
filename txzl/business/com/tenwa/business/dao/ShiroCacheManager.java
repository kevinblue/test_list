
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.dao
 * 文件名：         ShiroCacheManager.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-8-下午03:51:15
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.dao;

import org.apache.shiro.cache.Cache;


public interface ShiroCacheManager {

	<K, V> Cache<K, V> getCache(String name);

	void destroy();

}
