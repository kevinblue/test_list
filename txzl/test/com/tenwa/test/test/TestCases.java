
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.test
 * 文件名：         TestCases.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-19-下午01:44:10
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.test.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.utils.MD5Util;



 /**
 * 类名称：     TestCases
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-2-19 下午01:44:10
 * 修改备注：
 * @version 1.0.0
 **/

public class TestCases extends TestCase{
	@Override 
	public void finalize(){
		System.out.println("释放资源："+ (null == this.ac));
	}
	ApplicationContext ac = null;
	@Override
	protected void setUp() throws Exception {
		ac = new ClassPathXmlApplicationContext("classpath:applicationContext*.xml");
	}

	@Override
	protected void tearDown() throws Exception {
		ac = null;
		System.gc();
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testHibernateEntityFlush() throws Exception{
	   HibernateTemplate hibernateTemplate = (HibernateTemplate)this.ac.getBean("hibernateTemplate");
	   List<User> users = (List<User>)hibernateTemplate.findByCriteria(DetachedCriteria.forClass(User.class));
	   for(User user : users){
		   String password = MD5Util.getMD5EncodedPasswordWithSalt("111111", user.getId());
		   user.setPassword(password);
		   hibernateTemplate.update(user);
	   }
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	
	public static void listtest() throws Exception{
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println(list.size());
		//assertEquals(list.size(), 4);
		TestCases.list(list);
		System.out.println(list.size());
		//assertEquals(list.size(), 3);
	}
	
	public static void list(final ArrayList<String> s){
		//ArrayList<String> s = s;
		s.remove(0);
	}
	
	public static void main(String[] args) {
		try {
			listtest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
