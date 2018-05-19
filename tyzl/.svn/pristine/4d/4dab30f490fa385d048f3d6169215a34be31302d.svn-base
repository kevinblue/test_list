/**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.daoImpl
 * 文件名：         JedisShiroSessionRepository.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-8-上午09:08:54
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.daoImpl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.JedisDataType;
import com.tenwa.business.dao.JedisManager;
import com.tenwa.business.dao.ShiroSessionRepository;
import com.tenwa.kernal.utils.LoggerUtil;
import com.tenwa.kernal.utils.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

//@Service(value="shiroSessionRepository")
public class JedisShiroSessionRepositoryImpl extends JedisManager implements
		ShiroSessionRepository {

	/**
	 * redis session key前缀
	 */
	private final String REDIS_SHIRO_SESSION = "shiro-session:";

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

	@Override
	public void saveSession(Session session) {
		if (session == null || session.getId() == null) {
			LoggerUtil.error(JedisShiroSessionRepositoryImpl.class,
					"session或者session id为空");
			return;
		}
		byte[] key = SerializeUtil
				.serialize(getRedisSessionKey(session.getId()));
		byte[] value = SerializeUtil.serialize(session);
		Jedis jedis = this.getJedis();
		try {
			Long timeOut = session.getTimeout() / 1000;
			jedis.set(key, value);
			jedis.expire(key, Integer.parseInt(timeOut.toString()));
		} catch (JedisException e) {
			LoggerUtil.error(JedisShiroSessionRepositoryImpl.class, "保存session失败",
					e);
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public void deleteSession(Serializable id) {
		if (id == null) {
			LoggerUtil.error(JedisShiroSessionRepositoryImpl.class, "id为空");
			return;
		}
		Jedis jedis = this.getJedis();
		try {
			jedis.del(SerializeUtil.serialize(getRedisSessionKey(id)));
		} catch (JedisException e) {
			LoggerUtil.error(JedisShiroSessionRepositoryImpl.class, "删除session失败",
					e);
		} finally {
			this.returnResource(jedis);
		}
	}

	@Override
	public Session getSession(Serializable id) {
		if (id == null) {
			LoggerUtil.error(JedisShiroSessionRepositoryImpl.class, "id为空");
			return null;
		}
		Session session = null;
		Jedis jedis = this.getJedis();
		try {
			byte[] value = jedis.get(SerializeUtil
					.serialize(getRedisSessionKey(id)));
			session = SerializeUtil.deserialize(value, Session.class);
		} catch (JedisException e) {
			LoggerUtil.error(JedisShiroSessionRepositoryImpl.class, "获取id为" + id
					+ "的session失败", e);
		} finally {
			this.returnResource(jedis);
		}
		return session;
	}

	@Override
	public Collection<Session> getAllSessions() {
		Jedis jedis = this.getJedis();
		Set<Session> sessions = new HashSet<Session>();
		try {
			Set<byte[]> byteKeys = jedis.keys(SerializeUtil.serialize(this.REDIS_SHIRO_SESSION + "*"));
			if (byteKeys != null && byteKeys.size() > 0) {
				for (byte[] bs : byteKeys) {
					Session s = SerializeUtil.deserialize(jedis.get(bs),Session.class);
					sessions.add(s);
				}
			}
		} catch (JedisException e) {
			LoggerUtil.error(JedisShiroSessionRepositoryImpl.class,
					"获取所有session失败", e);
		} finally {
			this.returnResource(jedis);
		}
		return sessions;
	}

	/**
	 * 获取redis中的session key
	 * 
	 * @param sessionId
	 * @return
	 */
	private String getRedisSessionKey(Serializable sessionId) {
		return this.REDIS_SHIRO_SESSION + sessionId;
	}
}
