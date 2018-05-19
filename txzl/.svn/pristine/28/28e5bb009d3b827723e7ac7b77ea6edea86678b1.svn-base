package com.tenwa.test.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.utils.WebUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext.xml","classpath:applicationContext-jedis.xml"})
public class JBPMTest {
	
	
	@Autowired
	private  BaseService baseService;
	/**
	 * ===========可修改的可以动，其他的代码不用动就行了
	 */
	@Test
	public void moditifyFormValue(){
		try{
			//写的测试方法 默认是修改全部的 你也可以根据实际情况，比如说某个 designer下面的 或者某个流程类型（租前，租中，租后）或者 某一步流程修改 HQL语句
			String hqlEnded = "from JBPMWorkflowHistoryInfo ji where ji.historyProcessInstanceImpl.state = 'active'";//代表当前流程实例没有结束
			List<JBPMWorkflowHistoryInfo>jbpmworkflowhistoryinfos =  baseService.findResultsByHSQL(hqlEnded);//==================可修改添加
			Map<String, String> replaceMap = new HashMap<String, String>();
			//这是流程中的多个或者单个表单域的修改值存放Map，下拉框需要 修改俩个表单域的值
			replaceMap.put("rentorratevalue", "36");//==================可修改添加
			//replaceMap.put("rawValue_proj_info.department", "组织架构"); //==================可修改添加
			for(JBPMWorkflowHistoryInfo jbpmworkflowhistoryinfo : jbpmworkflowhistoryinfos){
				updateWorkFlowInfoFormFieldValue(baseService, jbpmworkflowhistoryinfo, replaceMap);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public  void updateWorkFlowInfoFormFieldValue(BaseService baseService,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo,Map<String, String>replaceFormValues)throws Exception{
		String processedFormVariables = jbpmWorkflowHistoryInfo.getProcessedFormVariables();
		String processedFormValues  = jbpmWorkflowHistoryInfo.getProcessedFormValues();
		Map<String, String>processFormMap =  getVaribalesMapByAllString(processedFormVariables,processedFormValues);
		for(String key : replaceFormValues.keySet()){
			if(processFormMap.containsKey(key)){
				System.out.println(processFormMap.get(key)); 
				processFormMap.put(key, replaceFormValues.get(key));
			}
		}
		StringBuffer sb_sourceKeyStr = new StringBuffer();
		StringBuffer sb_sourceValueStr = new StringBuffer();
		getVaribalesAllStringByMap(processFormMap, sb_sourceKeyStr, sb_sourceValueStr);
		jbpmWorkflowHistoryInfo.setProcessedFormVariables(sb_sourceKeyStr.toString());
		jbpmWorkflowHistoryInfo.setProcessedFormValues(sb_sourceValueStr.toString());
		baseService.updateEntity(jbpmWorkflowHistoryInfo);
	}
	
	private static final String SPLITSTR = "@@_@@";
	
	public static Map<String,String> getVaribalesMapByAllString(String sourceKeyStr,String sourceValueStr)
	{
		   Map<String,String>  map = new LinkedHashMap<String,String>();
		   String[] keyStrs   = sourceKeyStr.split(SPLITSTR);
		   String[] valueStrs = sourceValueStr.split(SPLITSTR);
		   
		   int len = Math.min(keyStrs.length,valueStrs.length);
		   for(int i=0;i<len;i++)
		   {
			   String key = keyStrs[i];
			   String value = valueStrs[i];
			   map.put(key, value);
		   }
		   return map;
	}
	public static void getVaribalesAllStringByMap(Map<String,String> map,StringBuffer sb_sourceKeyStr,StringBuffer sb_sourceValueStr)
	{
		int index = 0;
		Iterator<String> keySetIterator = map.keySet().iterator();
		while(keySetIterator.hasNext())
		{
		   String key = keySetIterator.next();
		   String value = map.get(key);
		   if(null == value)continue;
		   if( (index++) != 0 )
		   {
			   sb_sourceKeyStr.append(SPLITSTR);
			   sb_sourceValueStr.append(SPLITSTR);
		   }
		   sb_sourceKeyStr.append(key);
		   sb_sourceValueStr.append(value);
		}
	}
}
