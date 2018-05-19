
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.controller
 * 文件名：         InterfaceController.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-4-下午01:41:04
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.ad.AdUtil;
import com.tenwa.business.controller.BaseController;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.ad.AdConfig;
import com.tenwa.business.entity.email.EmailConfig;
import com.tenwa.business.entity.message.MessageConfig;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.StringUtil;


 /**
 * 类名称：     InterfaceController
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-12-4 下午01:41:04
 * 修改备注：
 * @version 1.0.0
 **/
@Controller(value="interfaceController")
@RequestMapping(value="/**/acl")
public class InterfaceController  extends BaseController{
	@Resource(name="tableService")
	private TableService tableService;
	
	@RequestMapping(value="/getAdUserInfoByOU.acl")
    public String getAdUserInfoByOU(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	   Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
       String ou                 = model.get("ou");
       int totalCount = 0;
       String norecord = "true";
       StringBuffer datas = new StringBuffer("[");
       //if(null != ou)
       {
    	  List<Map<String,String>> adInfoList = AdUtil.getADInfoList(ou);
    	  if(0 < adInfoList.size()){
    		    List<User> userList = this.tableService.findEntities(User.class);
    			boolean tbExistAccount ;
    			for(Map<String,String> adInfo : adInfoList){
    				String adAccountName = adInfo.get("sAMAccountName");
    				tbExistAccount = false;
    				for(User tbUser : userList){
    					if(adAccountName.equalsIgnoreCase(tbUser.getUsername())){
    						tbExistAccount = true;
    						break;
    					}
    				}
    				if(!tbExistAccount){
    					norecord="false";
    					if(0 < totalCount++){
    						datas.append(",");
    					}
    					datas.append("{");
    					int j = 0;
    					for(String nameMapping : adInfo.keySet()){
	    						String jsonKey = StringUtil.getJsonString(nameMapping).toLowerCase();
	    						String jsonValue = StringUtil.getJsonString(adInfo.get(nameMapping));
	    						if("iconcls".equalsIgnoreCase(jsonKey)){
	    								jsonKey = "iconCls";
	    						}
	    						else if(jsonKey.startsWith("rawvalue_")){
	    								jsonKey = jsonKey.replace("rawvalue_","rawValue_");
	    						}
	    						if(0 < j++){
	    								datas.append(",");
	    						}
	    						datas.append("\""+jsonKey+"\":\""+jsonValue+"\"");
    					}
    					datas.append("}");
    				}
    			}
    	  } 
       }
       datas.append("]");
	   StringBuffer sb = new StringBuffer();
	   sb.append("{totalCount:"+totalCount+",norecord:\""+norecord+"\",datas:");
	   sb.append(datas.toString());
	   sb.append("}"); 
	   this.output(response,sb.toString());
	   return null;
     }
	@RequestMapping(value="/addAdUserToSystem.acl")
	public String addAdUserToSystem(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
	    String userInfo                 = model.get("userInfo");
		JSONObject ADInfo = new JSONObject(userInfo);
		User currentFindUser = new User(); 
		if(ADInfo.has("department"))currentFindUser.setDeptName(ADInfo.getString("department"));
		if(ADInfo.has("name"))currentFindUser.setRealname(ADInfo.getString("name"));
		if(ADInfo.has("samaccountname"))currentFindUser.setUsername(ADInfo.getString("samaccountname"));
		if(ADInfo.has("samaccountname"))currentFindUser.setCode(ADInfo.getString("samaccountname"));
		currentFindUser.setPassword("111111");
		if(ADInfo.has("mail"))currentFindUser.setEmail(ADInfo.getString("mail"));
		if(ADInfo.has("mobile"))currentFindUser.setTelephone(ADInfo.getString("mobile"));
		currentFindUser.setEnabled(true);
		currentFindUser.setIsExcepted(true);
		this.tableService.saveEntity(currentFindUser);
		return null;
	}
	@RequestMapping(value="/updateAdConfig.acl")
	public String updateAdConfig(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		   Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		   String adConfigId = model.get("id");
		   AdConfig adConfig = this.tableService.findEntityByID(AdConfig.class, adConfigId);
		   this.tableService.copyAndOverrideExistedValueFromStringMap(model, adConfig, null,false);
		   this.tableService.updateEntity(adConfig);
		   return null;
	}
	@RequestMapping(value="/updateEmailConfig.acl")
	public String updateEmailConfig(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String emailConfigId = model.get("id");
		EmailConfig emailConfig = this.tableService.findEntityByID(EmailConfig.class, emailConfigId);
		this.tableService.copyAndOverrideExistedValueFromStringMap(model, emailConfig, null,false);
		this.tableService.updateEntity(emailConfig);
		return null;
	}
	@RequestMapping(value="/updateMessageConfig.acl")
	public String updateMessageConfig(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String messageConfigId = model.get("id");
		MessageConfig messageConfig = this.tableService.findEntityByID(MessageConfig.class, messageConfigId);
		this.tableService.copyAndOverrideExistedValueFromStringMap(model, messageConfig, null,false);
		this.tableService.updateEntity(messageConfig);
		return null;
	}
	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}

	public TableService getTableService() {
		return tableService;
	}
}
