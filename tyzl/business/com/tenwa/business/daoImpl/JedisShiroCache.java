
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.daoImpl
 * 文件名：         JedisShiroCache.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-8-下午03:48:56
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.daoImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.tenwa.business.dao.JedisManager;
import com.tenwa.kernal.utils.SerializeUtil;

public class JedisShiroCache<K, V> implements Cache<K, V> {

	private final String REDIS_SHIRO_CACHE = "shiro-cache:";

	private JedisManager jedisManager;

	private String name;

	public JedisShiroCache(String name, JedisManager jedisManager) {
		this.name = name;
		this.jedisManager = jedisManager;
	}
	
	/**
	 * 自定义relm中的授权/认证的类名加上授权/认证英文名字
	 * @return
	 */
	public String getName() {
		if (name == null)
			return "";
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public V get(K key) throws CacheException {
		byte[] byteKey = SerializeUtil.serialize(getCacheKey(key));
		byte[] byteValue = jedisManager.getValueByKey(byteKey);
		return (V) SerializeUtil.deserialize(byteValue);
	}

	@Override
	public V put(K key, V value) throws CacheException {
		V previos = get(key);
		jedisManager.saveValueByKey(SerializeUtil.serialize(getCacheKey(key)),
				SerializeUtil.serialize(value));
		return previos;
	}

	@Override
	public V remove(K key) throws CacheException {
		V previos = get(key);
		jedisManager.deleteByKey(SerializeUtil.serialize(getCacheKey(key)));
		return previos;
	}

	@Override
	public void clear() throws CacheException {
		byte[] keysPattern = SerializeUtil.serialize(this.REDIS_SHIRO_CACHE
				+ "*");
		jedisManager.deleteByKeysPattern(keysPattern);
	}

	@Override
	public int size() {
		if (keys() == null)
			return 0;
		return keys().size();
	}

	@Override
	public Set<K> keys() {
		Set<byte[]> byteSet = jedisManager.getKeysByKeysPattern(SerializeUtil
				.serialize(this.REDIS_SHIRO_CACHE + "*"));
		Set<K> keys = new HashSet<K>();
		for (byte[] bs : byteSet) {
			keys.add((K) SerializeUtil.deserialize(bs));
		}
		return keys;
	}

	@Override
	public Collection<V> values() {
		Set<byte[]> byteSet = jedisManager.getKeysByKeysPattern(SerializeUtil
				.serialize(this.REDIS_SHIRO_CACHE + "*"));
		List<V> result = new LinkedList<V>();
		for (byte[] bs : byteSet) {
			result.add((V) SerializeUtil.deserialize(jedisManager
					.getValueByKey(bs)));
		}
		return result;
	}

	private String getCacheKey(Object key) {
		return this.REDIS_SHIRO_CACHE + getName() + ":" + key;
	}
}
