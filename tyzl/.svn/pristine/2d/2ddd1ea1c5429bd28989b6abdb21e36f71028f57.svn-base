package com.tenwa.leasing.controller.own;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.base.OwnInfo;

/**
 * 
 * @author 蔡雅超
 * @date 2013-6-5上午10:55:13
 * @info 本方信息的Action
 * @Copyright 
 * Tenwa
 */
@Controller(value = "ownInfoController")
@RequestMapping(value = "/**/acl")
public class OwnInfoController {
	private Logger logger=Logger.getLogger(OwnInfoController.class);
	@Resource(name = "baseService")
	private BaseService baseService;

	// #################
	@RequestMapping(value = "/addOwnInfo.acl")
	public String addOwnInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 OwnInfo ouInfo=new OwnInfo();
		 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ouInfo, null);
		 this.baseService.saveEntity(ouInfo);
		 if(logger.isInfoEnabled()){
			 logger.info("新增成功!");
		 }
		return null;
	}

	@RequestMapping(value = "/updateOwnInfo.acl")
	public String updateOwnInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 OwnInfo ownInfo=new OwnInfo();
		 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ownInfo, null);
		 ownInfo.setId(model.get("id"));
		 this.baseService.updateEntity(ownInfo);
		 if(logger.isInfoEnabled()){
			 logger.info("修改成功!");
		 }
		return null;
	}

	@RequestMapping(value = "/removeOwnInfo.acl")
	public String removeOwnInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 OwnInfo ownInfo=new OwnInfo();
		 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ownInfo, null);
		 //ownInfo.setId(model.get("id"));
		 ownInfo=(OwnInfo)this.baseService.findEntityByID(OwnInfo.class, model.get("id"));
	//	 Iterator<OwnAccount> set=ownInfo.getOwnAccounts().iterator();
//		 while(set.hasNext()){
//			 OwnAccount s =set.next();
//			 this.baseService.removeEntity(s);
//		 }
		 this.baseService.removeAllEntites(ownInfo.getOwnAccounts());
		 this.baseService.removeEntity(ownInfo);
		 if(logger.isInfoEnabled()){
			 logger.info("删除成功!");
		 }
		return null;
	}
}
