
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.serviceImpl
 * 文件名：         BaseServiceImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-1-15-下午03:47:16
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.serviceImpl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;


 /**
 * 类名称：     BaseServiceImpl
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-1-15 下午03:47:16
 * 修改备注：
 * @version 1.0.0
 **/
@Service(value="baseService")
public class BaseServiceImpl extends AbstractBaseServiceImpl {
	@Resource(name="baseDao")
    private BaseDao baseDao;

	@Override
	public BaseDao getBussinessDao() throws Exception {
		return this.baseDao;
	}
}
