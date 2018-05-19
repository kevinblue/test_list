
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.daoImpl
 * 文件名：         CustomShiroCacheManager.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-8-下午03:51:35
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.daoImpl;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.ShiroCacheManager;

//@Service(value="customShiroCacheManager")
public class CustomShiroCacheManager implements CacheManager, Destroyable {
    @Resource(name="shiroCacheManager")
	private ShiroCacheManager shiroCacheManager;

	public ShiroCacheManager getShiroCacheManager() {
		return shiroCacheManager;
	}

	public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
		this.shiroCacheManager = shiroCacheManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return getShiroCacheManager().getCache(name);
	}

	@Override
	public void destroy() throws Exception {
		shiroCacheManager.destroy();
	}

}
