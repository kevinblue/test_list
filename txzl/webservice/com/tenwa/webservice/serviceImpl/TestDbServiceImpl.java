package com.tenwa.webservice.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.webservice.service.TestDbService;

@Service
@WebService(serviceName = "TestService",endpointInterface="com.tenwa.webservice.service.TestDbService")
public class TestDbServiceImpl implements TestDbService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public String addUser(String value) {
		try{
			JSONObject data = new JSONObject(value);
			User user = new User();
			tableService.copyAndOverrideExistedValueFromJSONObject(data, user, null, true);
			this.tableService.saveEntity(user);
			return "新增用户成功";
		}catch(Exception e){
			return "新增用户失败！！！";
		}
		
	}
	
	
	@Override
	public String deleteUser(String name) {
		try{
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("username", "zhangsan1");
		    tableService.removeAllEntites(this.tableService.findEntityByProperties(User.class, propertiesMap));	
			return "删除用户成功";
		}catch(Exception e){
			return "删除用户失败！！！";
		}
	}

}
