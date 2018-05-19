/**
 * 
 */
package com.b510.base.bean.install;

import com.b510.base.bean.Admin;
import com.b510.base.bean.People;
import com.b510.base.bean.User;

/**
 * @author hongten(hongtenzone@foxmail.com)
 * @date 2013-2-24
 */
public class BeanUtilTest {

	public static void main(String[] args) throws Exception{
		BeanUtilTest beanUtilTest = new BeanUtilTest();
		BeanUtils beanUtils = new BeanUtils();
		beanUtilTest.beanTool(beanUtils, User.class);
		beanUtilTest.beanTool(beanUtils, People.class);
		beanUtilTest.beanTool(beanUtils, Admin.class);
	}
	
	/**
	 * 根据bean生成相应的文件
	 * @param beanUtils
	 * @param c
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void beanTool(BeanUtils beanUtils ,Class c)throws Exception{
		beanUtils.createBeanDao(c);
		beanUtils.createBeanDaoImpl(c);
		beanUtils.createBeanService(c);
		beanUtils.createBeanServiceImpl(c);
	}
}
