
 /**
 * 包名：              com.tenwa.business.dao
 * 文件名：         ShiroSessionRepository.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-8-上午09:04:33
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.dao;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;


public interface ShiroSessionRepository {

	void saveSession(Session session);

	void deleteSession(Serializable sessionId);

	Session getSession(Serializable sessionId);

	Collection<Session> getAllSessions();
}
