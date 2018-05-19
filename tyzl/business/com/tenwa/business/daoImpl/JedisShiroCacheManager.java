
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.daoImpl
 * 文件名：         JedisShiroCacheManager.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-8-下午04:24:12
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.daoImpl;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.ShiroCacheManager;

//@Service(value="shiroCacheManager")
public class JedisShiroCacheManager implements ShiroCacheManager {

	@Resource(name="jedisCacheManager")
	private JedisCacheManager jedisCacheManager;

	@Override
	public <K, V> Cache<K, V> getCache(String name) {
		return new JedisShiroCache<K, V>(name, jedisCacheManager);
	}

	@Override
	public void destroy() {
		jedisCacheManager.getJedis().shutdown();
	}

}
