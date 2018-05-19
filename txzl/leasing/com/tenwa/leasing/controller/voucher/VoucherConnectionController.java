package com.tenwa.leasing.controller.voucher;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.voucher.VoucherConnection;


/**
 * 
 * @author lyh
 * @date 2014-3-24上午10:55:13
 * @info 凭证的Action
 * @Copyright 
 * Tenwa
 */
@Controller(value = "voucherConnectionController")
@RequestMapping(value = "/**/acl")
public class VoucherConnectionController {
	private Logger logger=Logger.getLogger(VoucherConnectionController.class);
	@Resource(name = "baseService")
	private BaseService baseService;

	// #################
	@RequestMapping(value = "/addVoucherConnection.acl")
	public String addVoucherConnection(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 VoucherConnection vcon=new VoucherConnection();
		 this.baseService.copyAndOverrideExistedValueFromStringMap(model,vcon, null);
		 this.baseService.saveEntity(vcon);
		 if(logger.isInfoEnabled()){
			 logger.info("新增成功!");
		 }
		return null;
	}

	@RequestMapping(value = "/updateVoucherConnection.acl")
	public String updateVoucherConnection(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 VoucherConnection vcon=this.baseService.findEntityByID(VoucherConnection.class,model.get("id"));
		 this.baseService.copyAndOverrideExistedValueFromStringMap(model,vcon, null);
		 this.baseService.updateEntity(vcon);
		 if(logger.isInfoEnabled()){
			 logger.info("修改成功!");
		 }
		return null;
	}

	@RequestMapping(value = "/removeVoucherConnection.acl")
	public String removeVoucherConnection(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 VoucherConnection vcon=this.baseService.findEntityByID(VoucherConnection.class, model.get("id"));
		 this.baseService.removeEntity(vcon);
		 if(logger.isInfoEnabled()){
			 logger.info("删除成功!");
		 }
		return null;
	}
}

