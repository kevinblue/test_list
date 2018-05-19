package com.tenwa.business.serviceImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tenwa.business.dao.CommDao;
import com.tenwa.business.dao.ResourceDao;
import com.tenwa.business.service.ResourceService;
import com.tenwa.kernal.annotation.WorkflowAction;

@Service(value = "resourceService")
public class ResourceServiceImpl implements ResourceService {
	@Resource
	private ResourceDao resourceDao;
	@Resource
	private CommDao commDao;

	@Override
	public List<Map<String,String>> getProcessAction(HttpServletRequest request) {
		 List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		
		ServletContext sc = request.getSession().getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		Map<String, Object> workFlowActions = ac.getBeansWithAnnotation(WorkflowAction.class);
		
		//排序
		List<String> temList = new ArrayList<String>();
		temList.addAll(workFlowActions.keySet());
		Collections.sort(temList);
		
		Iterator<String> it = temList.iterator();
		while (it.hasNext()) {
			Map<String,String> actionMap = new HashMap<String, String>();
			String key = (String) it.next();
			//Map的key和value都设置成action的名称，如果需要，可以解析出action的workflow中文名称。设置成value；
			actionMap.put("name", key);
			actionMap.put("value", key.toLowerCase());
			list.add(actionMap);
		}
		return list;
	}

	@Override
	public List<Map<String, String>> getAllWorkFlowName(String key) throws Exception {
		return this.resourceDao.getAllWorkFlowName(key);
	}

	@Override
	public List<Map<String, String>> getWorkFlowPointByID(String id) throws Exception {
		//workFlowDispaly
		//pointDsiplay
		List<Map<String, String>> list = this.resourceDao.getWorkFlowPointByID(id);
		
		
		
		return list;
	}

	@Override
	public List<Map<String, String>> getEntityFieldName(String entityName) throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		try{
			Class<?> entity = Class.forName(entityName);
			Field[] field = entity.getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				Map<String,String> map = new HashMap<String, String>();
				String fieldName = field[i].getName();
				map.put("name", fieldName);
				map.put("value", fieldName);
				list.add(map);
			}
		} catch(Exception e){
			list = null;
		}
		return list;
	}

	@Override
	public Map<String, Object> getProcessStatus(int page, int pagesize) throws Exception {
		String columns = "T1.id as id,T1.workFlowName as workFlowName,T1.pointName as pointName,T1.actionMethod as actionMethod,T1.entityName as entityName,T1.typeName as typeName,T1.processFieldName as processFieldName,T1.workFlowFieldName as workFlowFieldName,T1.processStatus as processStatus,T1.workFlow as workFlow,T1.additionalField as additionalField,T1.flag as flag";
		String queryString = "SELECT new Map("+columns+", CONCAT(T2.activityName,'(', T2.activityAction, ',', T2.activityType ,')') as pointDsiplay, T3.workflowName as workFlowDispaly) FROM  com.tenwa.business.entity.ProcessStatus T1, ActivityDetail T2, com.tenwa.business.entity.JbpmWorkflowDesigner T3 WHERE T1.pointName = T2.id AND T1.workFlowName = T3.id ORDER BY T1.workFlowName, T1.pointName, T1.actionMethod";
		Map<String, Object> map = commDao.findPageList(page, pagesize, queryString);
		return map;
	}
}