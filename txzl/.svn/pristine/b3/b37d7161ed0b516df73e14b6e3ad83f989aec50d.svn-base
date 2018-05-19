package com.tenwa.test.test;   

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tenwa.business.dao.CommDao;
import com.tenwa.business.entity.SystemLog;
import com.tenwa.business.entity.SystemLog.Level;
import com.tenwa.business.entity.SystemLog.Type;
import com.tenwa.business.entity.User;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-6-25 下午4:04:11
 * 类说明
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:applicationContext.xml"})  
public class test {
	
	@Autowired
	private CommDao commDao;

	@Test
	public void test() throws DataAccessException, SecurityException, ClassNotFoundException, NoSuchFieldException {
		User user = (User) commDao.findById(User.class.getName(), "402881ee3e492270013e496a80c90003");
		SystemLog log = new SystemLog();
		log.setLevel(Level.DEBUG);
		//log.setOpernateName("测试日志");
		//log.setOpernateUser(user);
		log.setTime("2013-7-5 12:14:32");
		log.setType(Type.BUSINESS);
		log.setContent("测试内容");
		log.setUrl("WWW.BAIDU.COM");
		commDao.save(SystemLog.class.getName(), log);
/*		//ApplicationContext ac = new  ClassPathXmlApplicationContext("applicationContext.xml");  
		//Map<String, Object> map = ac.getBeansWithAnnotation(WorkflowAction.class);
		System.out.println(map);*/
	}

}
  
