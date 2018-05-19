package com.tenwa.leasing.controller.voucher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.cust.CustTypeInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.voucher.VoucherConnection;
import com.tenwa.leasing.entity.voucher.VoucherassStactsConfig;
import com.tenwa.leasing.entity.voucher.VoucherassStactsInfo;


/**
 * 
 * @author lyh
 * @date 2014-3-24上午10:55:13
 * @info 凭证的Action
 * @Copyright 
 * Tenwa
 */
@Controller(value = "voucherConfigController")
@RequestMapping(value = "/**/acl")
public class VoucherConfigController {
	private Logger logger=Logger.getLogger(VoucherConfigController.class);
	@Resource(name = "baseService")
	private BaseService baseService;

	// #################
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addVoucherConfig.acl")
	@ResponseBody
	public JsonReturnResult addVoucherConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 VoucherassStactsConfig vouConfig=new VoucherassStactsConfig();
			 DictionaryData owner=this.baseService.findEntityByID(DictionaryData.class,model.get("subjectsowner"));
			 //this.baseService.copyAndOverrideExistedValueFromStringMap(model,vouConfig, null);
			 vouConfig.setSubjectsName(model.get("subjectsname"));
			 vouConfig.setSubjectsCode(model.get("subjectscode"));
			 vouConfig.setSubjectsId(model.get("subjectsid"));
			 vouConfig.setSubjectsOwner(owner);
			 vouConfig.setAsstActTypeBank(1);
			 vouConfig.setStatus(Integer.parseInt((model.get("status"))));
			
			 this.baseService.saveOrUpdateEntity(vouConfig);
			 
			 //保存辅助账类型
			 String asstacttype=model.get("asstacttype");
			 if(asstacttype!=null&&!asstacttype.equals("")){
				 String asstacttypes[]=asstacttype.split(",");
				 List<VoucherConnection> vcInfos=new ArrayList<VoucherConnection>();
				 for (String temp:asstacttypes) {
					 VoucherConnection vc=new VoucherConnection();
					 VoucherassStactsInfo vss=this.baseService.findEntityByID(VoucherassStactsInfo.class,temp);
					 vc.setAsstActType(vss);
					 vc.setConfigid(vouConfig);
					 vcInfos.add(vc);
				}
				this.baseService.saveOrUpdateAllEntities(vcInfos);
			 }
			 if(logger.isInfoEnabled()){
				 logger.info("新增成功!");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateVoucherConfig.acl")
	@ResponseBody
	public JsonReturnResult updateVoucherConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 VoucherassStactsConfig vouConfig=this.baseService.findEntityByID(VoucherassStactsConfig.class,model.get("id"));
			 DictionaryData owner=this.baseService.findEntityByID(DictionaryData.class,model.get("subjectsowner"));
			 vouConfig.setSubjectsName(model.get("subjectsname"));
			 vouConfig.setSubjectsCode(model.get("subjectscode"));
			 vouConfig.setSubjectsId(model.get("subjectsid"));
			 vouConfig.setSubjectsOwner(owner);
			 vouConfig.setStatus(Integer.parseInt((model.get("status"))));
			 this.baseService.saveOrUpdateEntity(vouConfig);
			 
			//保存辅助账类型
			 String asstacttype=model.get("asstacttype");
			 JSONArray jArray = new JSONArray();
			 if(asstacttype!=null&&!asstacttype.equals("")){
				 String asstacttypes[]=asstacttype.split(",");
				 for (String temp:asstacttypes) {
			    	  JSONObject jobj  = new JSONObject();
			    	  jobj.put("asstActType", temp);
			    	  jArray.put(jobj);
			     }
		     }
			 this.baseService.updateOneToManyCollections(vouConfig, "voucherTypes", VoucherConnection.class, "configid", jArray.toString(), null);
				
			 if(logger.isInfoEnabled()){
				 logger.info("修改成功!");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeVoucherConfig.acl")
	@ResponseBody
	public JsonReturnResult removeVoucherConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 VoucherassStactsConfig vouConfig=this.baseService.findEntityByID(VoucherassStactsConfig.class, model.get("id"));
			 this.baseService.removeEntity(vouConfig);
			 if(logger.isInfoEnabled()){
				 logger.info("删除成功!");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
}

