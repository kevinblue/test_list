package com.tenwa.test.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tenwa.business.dao.BaseDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext.xml","classpath:applicationContext-jedis.xml"})
public class RedisTest {
	@Autowired
	 private  BaseDao baseDao;
	
	@org.junit.Test
	public void Test() throws Exception{
		String key = "testList";
		List<String> valueList = new ArrayList<String>();
		valueList.add("zhangsan");
		valueList.add("lisi");
		valueList.add("wangwu");
		baseDao.inByRedis(key, valueList);
	}
}
