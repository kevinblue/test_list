
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.daoImpl
 * 文件名：         JedisCacheManager.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-8-下午04:41:54
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.daoImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;

import com.tenwa.business.dao.JedisDataType;
import com.tenwa.business.dao.JedisManager;


 /**
 * 类名称：     JedisCacheManager
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-9-8 下午04:41:54
 * 修改备注：
 * @version 1.0.0
 **/
//@Component(value="jedisCacheManager")
public class JedisCacheManager extends JedisManager {

	@Resource(name="jedisPool")
	private JedisPool jedisPool;

	@Override
	protected JedisPool getJedisPool() {
		return jedisPool;
	}

	@Override
	protected JedisDataType getJedisDataType() {
		return JedisDataType.SESSION_CACHE;
	}
}
