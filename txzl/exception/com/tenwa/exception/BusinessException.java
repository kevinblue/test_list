
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.exception
 * 文件名：         BusinessException.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-8-21-下午04:43:32
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.exception;


 /**
 * 类名称：     BusinessException
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-8-21 下午04:43:32
 * 修改备注：
 * @version 1.0.0
 **/

public class BusinessException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(Exception e) {
		super(e);
	}

}
