
 /**
 * 项目名称：    系统名称
 * 包名：              com.jbpm.utils
 * 文件名：         JBPMDesignerUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-5-下午05:11:33
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;
import com.tenwa.jbpm.model.DesignerActivity;
import com.tenwa.jbpm.model.DesignerDot;
import com.tenwa.jbpm.model.DesignerTransition;
import com.tenwa.jbpm.model.DesignerWorkflow;
import com.tenwa.jbpm.model.DesingerActivityHtmlProperty;
import com.tenwa.jbpm.service.JbpmService;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.kernal.utils.XmlUtil;


 /**
 * 类名称：     JBPMDesignerUtil
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-5 下午05:11:33
 * 修改备注：
 * @version 1.0.0
 **/
public class DesignerTransferUtil 
{
    public static Map<String,String> getTransferedJpdlXml(String designerWorkflowJsonString) throws Exception
    {
    	Map<String,String> map = new HashMap<String,String>();
    	DesignerWorkflow designerWorkflow = getTransferedDesignerWorkflow(designerWorkflowJsonString);
    	Document document = XmlUtil.getDocumentInstance();
    	Element process = XmlUtil.getElement("process");
    	process.setNamespace(Namespace.getNamespace("http://jbpm.org/4.4/jpdl"));
    	document.setRootElement(process);
    	
    	String displayName = StringUtil.nullToString(designerWorkflow.getDisplayName());
    	
    	String name = StringUtil.nullToString(designerWorkflow.getName());
    	if(!name.isEmpty()){process.setAttribute("name", name);process.setAttribute("key", name);}
    	int version = designerWorkflow.getVersion();
    	if(version>0){process.setAttribute("version", version+"");}
    	String desc  = StringUtil.nullToString(designerWorkflow.getDescription());
    	if(!desc.isEmpty()){
    		process.addContent(XmlUtil.getElement("description").setText(desc));
    	}
    	String type   = designerWorkflow.getType();
    	String code   = designerWorkflow.getCode();
    	String position   = String.valueOf(designerWorkflow.getPosition());
    	
    	List<DesignerActivity> activities = designerWorkflow.getActivities();
    	for(int i=0;i<activities.size();i++)
    	{
    		DesignerActivity activity = activities.get(i);
    		String activity_type = activity.getType();
    		String activity_name = activity.getName();
    		String activity_key = activity.getKey();
    		String activity_position = String.valueOf(activity.getPosition());
    		DesingerActivityHtmlProperty htmlProperty = activity.getHtmlProperty();
    		String gAtributeValue = htmlProperty.getX()+","+htmlProperty.getY()+","+htmlProperty.getWidth()+","+htmlProperty.getHeight();
    		Element activityElement = XmlUtil.getElement(activity_type).setAttribute("name",activity_key).setAttribute("g",gAtributeValue);
    		activityElement.setAttribute("activityName",activity_name);
    		activityElement.setAttribute("key",activity_key);
    		activityElement.setAttribute("position",activity_position);
    		process.addContent(activityElement);
    		activityElement.setNamespace(process.getNamespace());
    		List<DesignerTransition> transitions = activity.getTransitions();
    		for(int j=0;j<transitions.size();j++)
    		{
    			DesignerTransition transition = transitions.get(j);
    			StringBuffer transitionGAttribute_sb = new StringBuffer("");
    			int index = 0;
    			for(DesignerDot dd:transition.getDots())
    			{
    				if(0 < index++)
    				{
    					transitionGAttribute_sb.append(";");
    				}
    				transitionGAttribute_sb.append(dd.getX()+","+dd.getY());
    			}
    			if(transition.getDots().size()>0)
    			{
    				transitionGAttribute_sb.append(":");
    			}
    			transitionGAttribute_sb.append(transition.getTextPosition().getX()+","+transition.getTextPosition().getY());
    			String to = transition.getTo();
    			Element transitionElement = XmlUtil.getElement("transition");
    			transitionElement.setNamespace(process.getNamespace());
    			activityElement.addContent(
    					transitionElement.setAttribute("g", transitionGAttribute_sb.toString())
    					.setAttribute("to",to)
    					);
    			String transition_name = StringUtil.nullToString(transition.getName());
    			if(!transition_name.isEmpty())
    			{
    				transitionElement.setAttribute("name",transition_name);
    			}
    		}
    	}
    	/*
    	 * FileOutputStream fos = new FileOutputStream(fileFullName);
    	   XmlUtil.getXMLOutputterInstance().output(document, fos);
    	   fos.flush();
    	   fos.close();
    	*/
    	map.put("name", name);
    	map.put("displayName", displayName);
    	map.put("type", type);
    	map.put("code", code);
    	map.put("position", position);
    	map.put("key", name);
    	map.put("version", String.valueOf(version));
    	map.put("jpdlVersion", designerWorkflow.getJpdlVersion());
    	map.put("description", desc);
    	map.put("maxDotX", String.valueOf(designerWorkflow.getMaxDotX()));
    	map.put("maxDotY", String.valueOf(designerWorkflow.getMaxDotY()));
    	Format f = Format.getPrettyFormat(); 
    	f.setEncoding("UTF-8");
    	String transferedJpdlXml = XmlUtil.getXMLOutputterInstance(f).outputString(document);
    	map.put("transferedJpdlXml", transferedJpdlXml);
    	XmlUtil.closeLocalResources();
    	document.removeContent();
    	document = null;
    	return map;
    }
    @SuppressWarnings({"rawtypes"})
	private static DesignerWorkflow getTransferedDesignerWorkflow(String designerWorkflowJsonString) throws Exception
    {
    	int maxDotX = 0;
    	int maxDotY = 0;
    	JSONObject designerWorkflowJson = new JSONObject(designerWorkflowJsonString);
    	Map<String,JSONObject> jsonObjectNameMap = getJsonObjectNameMap(designerWorkflowJson);
    	
    	JSONObject designerWorkflowPropsJson = designerWorkflowJson.getJSONObject("props").getJSONObject("props");
    	String displayName = designerWorkflowPropsJson.getJSONObject("displayName").getString("value");
    	String name = designerWorkflowPropsJson.getJSONObject("name").getString("value");
    	String type = designerWorkflowPropsJson.getJSONObject("type").getString("value");
    	String code = designerWorkflowPropsJson.getJSONObject("code").getString("value");
    	
    	String positionWorkflowDefinition = designerWorkflowPropsJson.getJSONObject("position").getString("value");
    	int position = -1;
    	if("第一位".equals(positionWorkflowDefinition)){
    		position = -1;
    	}else if("最后".equals(positionWorkflowDefinition)){
    		position = 999999;
    	}else{
    		JbpmService jbpmService = (JbpmService)(WebUtil.getApplicationContext().getBean("jbpmService"));
        	Map<String,Object> propertiesMap = new HashMap<String,Object>();
        	propertiesMap.put("workflowDefinition", positionWorkflowDefinition);
        	position = ((JbpmWorkflowDesigner)jbpmService.findEntityByProperties(JbpmWorkflowDesigner.class, propertiesMap).get(0)).getPosition();
    	}
    	//int position = Integer.parseInt(designerWorkflowPropsJson.getJSONObject("position").getString("value"));
    	String key = name;//designerWorkflowPropsJson.getJSONObject("key").getString("value");
    	String jpdlVersion = designerWorkflowPropsJson.getJSONObject("jpdlVersion").getString("value");
    	int    version = Integer.parseInt(StringUtil.empty2Other(designerWorkflowPropsJson.getJSONObject("version").getString("value"),"1"));
    	String description = designerWorkflowPropsJson.getJSONObject("desc").getString("value");	
    	DesignerWorkflow designerWorkflow = new DesignerWorkflow(displayName,name,key,version,jpdlVersion,description,type,code,position) ;
    	List<DesignerActivity> activities = designerWorkflow.getActivities();
        //props:{text:{value:'任务'},key:{value:'task01'},position:{value:'1'}}
    	Iterator<String> designerWorkflowActivityKeys = jsonObjectNameMap.keySet().iterator();
    	while(designerWorkflowActivityKeys.hasNext())
    	{
    		String jsonObjIdentifier = designerWorkflowActivityKeys.next();
    		JSONObject activityJson = jsonObjectNameMap.get(jsonObjIdentifier);
    		String activityType = activityJson.getString("type");
    		if("exclusive".equalsIgnoreCase(activityType)){
    			continue;
    		}
    		String activityName = activityJson.getJSONObject("text").getString("text");
    		JSONObject activityProps = activityJson.getJSONObject("props");
    		//System.out.println("####:"+activityProps.getJSONObject("key").getString("value"));
    		String activityKey     =   activityProps.getJSONObject("key").getString("value");
    		String activityPostion =   StringUtil.empty2Other(activityProps.getJSONObject("position").getString("value"), "1");
    		JSONObject activityJsonHtmlPropertyJson =  activityJson.getJSONObject("attr");
    		int x  = activityJsonHtmlPropertyJson.getInt("x");
    		int y  = activityJsonHtmlPropertyJson.getInt("y");
    		int width  = activityJsonHtmlPropertyJson.getInt("width");
    		int height  = activityJsonHtmlPropertyJson.getInt("height");
    		maxDotX    = Math.max(maxDotX, x+width);
    		maxDotY    = Math.max(maxDotY, y+height);
    		
    		DesignerActivity designerActivity = new DesignerActivity(jsonObjIdentifier,activityType,activityName,activityKey,Integer.valueOf(activityPostion),new DesingerActivityHtmlProperty(x,y,width,height)); 
    		activities.add(designerActivity);
    		List<DesignerTransition> transitions = designerActivity.getTransitions();
    		JSONObject designerWorkflowTransitionJson =  designerWorkflowJson.getJSONObject("paths");
    	   	Iterator designerWorkflowTransitionKeys = designerWorkflowTransitionJson.keys();
        	while(designerWorkflowTransitionKeys.hasNext())
        	{
        		String jsonKey= designerWorkflowTransitionKeys.next().toString();
        		JSONObject transitionJson = designerWorkflowTransitionJson.getJSONObject(jsonKey);
        		String transitionName = transitionJson.getJSONObject("text").getString("text");
        		
        		String transitionJsonObjFrom = transitionJson.getString("from");
        		String transitionJsonObjTo = transitionJson.getString("to");
        		if(jsonObjIdentifier.equals(transitionJsonObjFrom))
        		{
        			JSONObject transitionToJsonObj = jsonObjectNameMap.get(transitionJsonObjTo);
            		String transitionTo = transitionToJsonObj.getJSONObject("props").getJSONObject("key").getString("value");//.getJSONObject("text").getString("text");
            		JSONArray dotJSONArray = transitionJson.getJSONArray("dots");
        			for(int i=0;i<dotJSONArray.length();i++)
        			{
        				JSONObject jsonDot = dotJSONArray.getJSONObject(i);
        				int dot_x  = jsonDot.getInt("x");
        	    		int dot_y  = jsonDot.getInt("y");
        	    		maxDotX    = Math.max(maxDotX, dot_x);
        	    		maxDotY    = Math.max(maxDotY, dot_y);
        			}
        			boolean isExclusive = "exclusive".equalsIgnoreCase(transitionToJsonObj.getString("type"));
            		if(!isExclusive){
	            		JSONObject textPostionJsonDot =  transitionJson.getJSONObject("textPos");
	            		int textPostionJsonDot_x  = textPostionJsonDot.getInt("x");
	            		int textPostionJsonDot_y  = textPostionJsonDot.getInt("y");
	            		DesignerDot   textPosition = new DesignerDot(textPostionJsonDot_x,textPostionJsonDot_y);
	            		DesignerTransition designerTransition = new DesignerTransition(transitionName,transitionJsonObjFrom,transitionTo,textPosition);
	            		transitions.add(designerTransition);
	            		List<DesignerDot> dots = designerTransition.getDots();
	            		for(int i=0;i<dotJSONArray.length();i++)
	        			{
	        				JSONObject jsonDot = dotJSONArray.getJSONObject(i);
	        				int dot_x  = jsonDot.getInt("x");
	        	    		int dot_y  = jsonDot.getInt("y");
	        	    		dots.add(new DesignerDot(dot_x,dot_y));
	        			}
            		}else{
            			recursionExclusiveUtilIsTask(jsonObjectNameMap, designerWorkflowTransitionJson, dotJSONArray, transitions, transitionJsonObjTo);
            		}
        		}
        	}
    	}
    	designerWorkflow.setMaxDotX(maxDotX);
    	designerWorkflow.setMaxDotY(maxDotY);
    	//System.out.println("$$$$最大的X轴坐标："+maxDotX);
    	//System.out.println("$$$$最大的Y轴坐标："+maxDotY);
    	return designerWorkflow;
    }
    @SuppressWarnings("rawtypes")
    public static void recursionExclusiveUtilIsTask(Map<String,JSONObject> jsonObjectNameMap ,JSONObject designerWorkflowTransitionJson,JSONArray dotJSONArray,List<DesignerTransition> transitions,String jsonObjIdentifier) throws Exception{
    	Iterator designerWorkflowTransitionKeys = designerWorkflowTransitionJson.keys();
    	while(designerWorkflowTransitionKeys.hasNext())
    	{
    		String jsonKey= designerWorkflowTransitionKeys.next().toString();
    		JSONObject transitionJson = designerWorkflowTransitionJson.getJSONObject(jsonKey);
    		String transitionName = transitionJson.getJSONObject("text").getString("text");
    		
    		String transitionJsonObjFrom = transitionJson.getString("from");
    		String transitionJsonObjTo = transitionJson.getString("to");
    		if(jsonObjIdentifier.equals(transitionJsonObjFrom))
    		{
    			JSONObject transitionToJsonObj = jsonObjectNameMap.get(transitionJsonObjTo);
        		String transitionTo = transitionToJsonObj.getJSONObject("props").getJSONObject("key").getString("value");//.getJSONObject("text").getString("text");
    	    	boolean isExclusive = "exclusive".equalsIgnoreCase(transitionToJsonObj.getString("type"));
    			if(!isExclusive){
    	    		JSONObject textPostionJsonDot =  transitionJson.getJSONObject("textPos");
    	    		int textPostionJsonDot_x  = textPostionJsonDot.getInt("x");
    	    		int textPostionJsonDot_y  = textPostionJsonDot.getInt("y");
    	    		DesignerDot   textPosition = new DesignerDot(textPostionJsonDot_x,textPostionJsonDot_y);
    	    		DesignerTransition designerTransition = new DesignerTransition(transitionName,transitionJsonObjFrom,transitionTo,textPosition);
    	    		transitions.add(designerTransition);
    	    		List<DesignerDot> dots = designerTransition.getDots();
    	    		for(int i=0;i<dotJSONArray.length();i++)
    				{
    					JSONObject jsonDot = dotJSONArray.getJSONObject(i);
    					int dot_x  = jsonDot.getInt("x");
    		    		int dot_y  = jsonDot.getInt("y");
    		    		dots.add(new DesignerDot(dot_x,dot_y));
    				}
    			}else{
    				recursionExclusiveUtilIsTask(jsonObjectNameMap, designerWorkflowTransitionJson, dotJSONArray, transitions, transitionJsonObjTo);
    			}
    		}
    	}
    }
    @SuppressWarnings({"rawtypes"})
	public static Map<String,JSONObject> getJsonObjectNameMap(JSONObject designerWorkflowJson) throws Exception
    {
    	Map<String,JSONObject> jsonObjectNameMap = new HashMap<String,JSONObject>();
    	JSONObject designerWorkflowActivityJson =  designerWorkflowJson.getJSONObject("states");
    	Iterator designerWorkflowActivityKeys = designerWorkflowActivityJson.keys();
    	while(designerWorkflowActivityKeys.hasNext())
    	{
    		String jsonObjIdentifier = designerWorkflowActivityKeys.next().toString();
    		JSONObject activityJson = designerWorkflowActivityJson.getJSONObject(jsonObjIdentifier);
    		jsonObjectNameMap.put(jsonObjIdentifier, activityJson);
    	}
    	return jsonObjectNameMap;
    }
    @SuppressWarnings({"rawtypes"})
	public static List<String> getDesignerNameTypeTaskMap(String designerWorkflowJsonString) throws Exception
    {
    	JSONObject designerWorkflowJson = new JSONObject(designerWorkflowJsonString);
    	List<String> designerNameTypeTaskList = new ArrayList<String>();
    	JSONObject designerWorkflowActivityJson =  designerWorkflowJson.getJSONObject("states");
    	Iterator designerWorkflowActivityKeys = designerWorkflowActivityJson.keys();
    	while(designerWorkflowActivityKeys.hasNext())
    	{
    		String activityNameKey = designerWorkflowActivityKeys.next().toString();
    		JSONObject obj = designerWorkflowActivityJson.getJSONObject(activityNameKey);
    		String activityName = obj.getJSONObject("text").getString("text");
    		String activityType = obj.getString("type");
    		if("task".equalsIgnoreCase(activityType))
    		{
    			designerNameTypeTaskList.add(activityName);
    		}
    		if("start".equalsIgnoreCase(activityType))
    		{
    			designerNameTypeTaskList.add(activityName);
    		}
    		if(activityType.startsWith("end"))
    		{
    			designerNameTypeTaskList.add(activityName);
    		}
    	}
    	return designerNameTypeTaskList;
    }
    @SuppressWarnings({"rawtypes"})
    public static List<DesignerActivity> getDesignerActivities(String designerWorkflowJsonString) throws Exception
    {
    	List<DesignerActivity> activities  = new ArrayList<DesignerActivity> ();
    	JSONObject designerWorkflowJson = new JSONObject(designerWorkflowJsonString);
    	JSONObject designerWorkflowActivityJson =  designerWorkflowJson.getJSONObject("states");
    	Iterator designerWorkflowActivityKeys = designerWorkflowActivityJson.keys();
    	while(designerWorkflowActivityKeys.hasNext())
    	{
    		String activityNameKey = designerWorkflowActivityKeys.next().toString();
    		JSONObject obj = designerWorkflowActivityJson.getJSONObject(activityNameKey);
    		String activityName = obj.getJSONObject("text").getString("text");
    		String activityType = obj.getString("type");
    		JSONObject activityProps = obj.getJSONObject("props");
    		String activityKey     =   activityProps.getJSONObject("key").getString("value");
    		String activityPostion =   StringUtil.empty2Other(activityProps.getJSONObject("position").getString("value"),"1");
    		if("task".equalsIgnoreCase(activityType)||"start".equalsIgnoreCase(activityType)||activityType.startsWith("end"))
    		{
    			activities.add(new DesignerActivity(activityType,activityName,activityKey,Integer.valueOf(activityPostion)));
    		}
    	}
    	return activities;
    }
}
