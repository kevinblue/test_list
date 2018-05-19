
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.exception
 * 文件名：         UserNotSynchronizedException.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-11-29-下午04:30:53
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.exception;

import org.springframework.security.core.AuthenticationException;


 /**
 * 类名称：     UserNotSynchronizedException
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-11-29 下午04:30:53
 * 修改备注：
 * @version 1.0.0
 **/

public class UserNotSynchronizedException extends AuthenticationException{
	
	private static final long serialVersionUID = 1L;

	public UserNotSynchronizedException(String msg) {
		super(msg);
	}
}
