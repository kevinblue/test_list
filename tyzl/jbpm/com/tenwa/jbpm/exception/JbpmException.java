
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.exception
 * 文件名：         JbpmException.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-8-21-下午04:06:56
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.jbpm.exception;


 /**
 * 类名称：     JbpmException
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-8-21 下午04:06:56
 * 修改备注：
 * @version 1.0.0
 **/

public class JbpmException extends Exception
{
	public JbpmException(String exceptionMsg) {
		super(exceptionMsg);
	}

	private static final long serialVersionUID = 1L;
        
}
