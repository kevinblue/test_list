package com.tenwa.leasing.controller.own;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.base.OwnInfo;



/**
 * 
 * @author 蔡雅超
 * @date 2013-6-5上午10:55:13
 * @info 本方账号的Action
 * @Copyright 
 * Tenwa
 */

@Controller(value = "OwnAccountController")
@RequestMapping(value = "/**/acl")  
public class OwnAccountController {
	private Logger logger=Logger.getLogger(OwnAccountController.class);
	@Resource(name = "baseService")
	private BaseService baseService;
	OwnAccount ownAccount=new OwnAccount();
	public OwnAccount getOwnAccount() {
		return ownAccount;
	}

	public void setOwnAccount(OwnAccount ownAccount) {
		this.ownAccount = ownAccount;
	}
	OwnInfo ownInfo=new OwnInfo();
	// #################
/*	@RequestMapping(value = "/addOwnAccount.acl")
	public String addOwnAccount(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 OwnAccount ownAccount=new OwnAccount();
		 Map<String,String>classFieldMapping=new HashMap<String,String>();
		 classFieldMapping.put("OwnInfo", "id");
		 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ownAccount, classFieldMapping);
		 OwnInfo ownId=new OwnInfo();
		// ownId.setId(model.get("oid"));
		// ownAccount.setOwnInfo(ownId);
		 this.baseService.saveEntity(ownAccount);
		 if(logger.isInfoEnabled()){
			 logger.info("新增成功!");
		 }
		return null;
	}*/
	@RequestMapping(value = "/addOwnAccount.acl")
	@ResponseBody
	public JsonReturnResult addRemoteOper(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 OwnAccount ownAccount=new OwnAccount();
			 Map<String,String>classFieldMapping=new HashMap<String,String>();
			 classFieldMapping.put("OwnInfo", "id");
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ownAccount, classFieldMapping);
			 OwnInfo ownId=new OwnInfo();
			// ownId.setId(model.get("oid"));
			// ownAccount.setOwnInfo(ownId);
			 this.baseService.saveEntity(ownAccount);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	/*@RequestMapping(value = "/updateOwnAccount.acl")
	public String updateOwnAccount(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 System.out.println(model.toString());
		 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ownAccount, null);
		// ownInfo=(OwnInfo)this.baseService.findEntityByID(OwnInfo.class,model.get("oid"));
		// ownAccount.setOwnInfo(ownInfo);
		 ownAccount.setId(model.get("id"));
		 this.baseService.updateEntity(ownAccount);
		 if(logger.isInfoEnabled()){
			 logger.info("修改成功!");
		 }
		return null;
	}*/
	@RequestMapping(value = "/updateOwnAccount.acl")
	@ResponseBody
	public JsonReturnResult updateRemoteOper(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 System.out.println(model.toString());
			 Map<String, String>classFieldMapping = new HashMap<String, String>();
			 classFieldMapping.put("OwnInfo", "id");
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ownAccount, classFieldMapping);
			// ownInfo=(OwnInfo)this.baseService.findEntityByID(OwnInfo.class,model.get("oid"));
			// ownAccount.setOwnInfo(ownInfo);
			 ownAccount.setId(model.get("id"));
			 //
			 this.baseService.updateEntity(ownAccount);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}


	/*@RequestMapping(value = "/removeOwnAccount.acl")
	@ResponseBody
	public String removeOwnAccount(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ownAccount, null);
		 ownAccount.setId(model.get("cid"));
		 this.baseService.removeEntity(ownAccount);
		 if(logger.isInfoEnabled()){
			 logger.info("删除成功!");
		 }
		return null;
	}*/
	@RequestMapping(value = "/removeOwnAccount.acl")
	@ResponseBody
	public JsonReturnResult removeRemoteOper(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ownAccount, null);
			 ownAccount.setId(model.get("id"));
			 this.baseService.removeEntity(ownAccount);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
}
