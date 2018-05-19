package com.tenwa.leasing.controller.voucher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.tenwa.business.entity.User;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustTypeInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.voucher.InterV8Vouchers;
import com.tenwa.leasing.entity.voucher.VoucherConnection;
import com.tenwa.leasing.entity.voucher.VoucherassCustConfig;
import com.tenwa.leasing.entity.voucher.VoucherassStactsConfig;
import com.tenwa.leasing.entity.voucher.VoucherassStactsInfo;
import com.tenwa.leasing.service.vouchersFactory.RentIncomeVoucherService;


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

	@Resource(name="rentIncomeVoucherService")
	private RentIncomeVoucherService rentIncomeVoucherService;
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
			 vouConfig.setSubjectsName(model.get("subjectsname"));//科目名称
			 vouConfig.setSubjectsCode(model.get("subjectscode"));//科目段
			 vouConfig.setSubjectsOneName(model.get("subjects_onename"));//科目段名称
			 vouConfig.setSubjectsId(model.get("subjectsid"));//科目编码ID
			 vouConfig.setCompanyCode(model.get("company_code"));//公司段
			 vouConfig.setCompanyName(model.get("company_name"));//公司段名称
			 vouConfig.setSonSubjectCode(model.get("sonsubject_code"));//子科目段
			 vouConfig.setSonSubjectName(model.get("sonsubject_name"));//子科目段名称
			 vouConfig.setResponCenter(model.get("respon_center"));//责任中心段
			 vouConfig.setResponCenterName(model.get("respon_center_name"));//责任中心段名称
			 vouConfig.setProduct(model.get("product"));//产品段
			 vouConfig.setProductName(model.get("product_name"));//产品段名称
			 vouConfig.setAttribute(model.get("attribute"));//属性段
			 vouConfig.setAttributeName(model.get("attribute_name"));//属性段名称
			 vouConfig.setIntrabranch(model.get("intrabranch"));//内部往来段
			 vouConfig.setIntrabranchName(model.get("intrabranch_name"));//内部往来段名称
			 vouConfig.setStandby(model.get("stanby"));//备用段
			 vouConfig.setStandbyName(model.get("stanby_name"));//备用段名称
			 
			 
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
			 vouConfig.setSubjectsOneName(model.get("subjects_onename"));//科目段名称
			 vouConfig.setSubjectsId(model.get("subjectsid"));
			 vouConfig.setCompanyCode(model.get("company_code"));//公司段
			 vouConfig.setCompanyName(model.get("company_name"));//公司段名称
			 vouConfig.setSonSubjectCode(model.get("sonsubject_code"));//子科目段
			 vouConfig.setSonSubjectName(model.get("sonsubject_name"));//子科目段名称
			 vouConfig.setResponCenter(model.get("respon_center"));//责任中心段
			 vouConfig.setResponCenterName(model.get("respon_center_name"));//责任中心段名称
			 vouConfig.setProduct(model.get("product"));//产品段
			 vouConfig.setProductName(model.get("product_name"));//产品段名称
			 vouConfig.setAttribute(model.get("attribute"));//属性段
			 vouConfig.setAttributeName(model.get("attribute_name"));//属性段名称
			 vouConfig.setIntrabranch(model.get("intrabranch"));//内部往来段
			 vouConfig.setIntrabranchName(model.get("intrabranch_name"));//内部往来段名称
			 vouConfig.setStandby(model.get("stanby"));//备用段
			 vouConfig.setStandbyName(model.get("stanby_name"));//备用段名称
			 
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
	@RequestMapping(value = "/editVoucherCust.acl")
	@ResponseBody
	public JsonReturnResult editVoucherCust(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			VoucherassCustConfig vouCustConfig = new VoucherassCustConfig();
			String custNumber = model.get("custid");
			CustInfo custInfo = this.baseService.findEntityByID(CustInfo.class, custNumber);
			this.baseService.updateEntity(custInfo);
			String financialCode = model.get("financialcode");
			Map<String,Object> propertiesMap = new HashMap<String,Object>();
			propertiesMap.put("custNumber", custInfo);
			List<VoucherassCustConfig> vouCustConfigs = this.baseService.findEntityByProperties(VoucherassCustConfig.class, propertiesMap);
			if(vouCustConfigs.size()>0){
				vouCustConfig = vouCustConfigs.get(0);
			}
			vouCustConfig.setCustNumber(custInfo);
			vouCustConfig.setFinancialCode(financialCode);
			vouCustConfig.setFinancialCode_vendor(model.get("financialcode_vendor"));
			this.baseService.saveOrUpdateEntity(vouCustConfig);
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
	@RequestMapping(value = "/redVoucher.acl")
	@ResponseBody
	public String redVoucher(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String jsonostr = model.get("rowdatastring");
		JSONArray jsonarr = new JSONArray(jsonostr);
		for(int i =0;i<jsonarr.length();i++){
			Map<String,Object> propertiesMap = new HashMap<String, Object>();
			JSONObject jsonobj = jsonarr.getJSONObject(i);
			String vouchernum = jsonobj.getString("vouchernumber");
			propertiesMap.put("f3", vouchernum);
			String userid = model.get("loginuserid");
			User user = this.baseService.findEntityByID(User.class, userid);
			List<InterV8Vouchers> list = new ArrayList<InterV8Vouchers>();
			list = this.baseService.findEntityByProperties(InterV8Vouchers.class, propertiesMap);
            for(InterV8Vouchers v8 :list){
			        	  v8.setRollBack("1");
			        	  this.baseService.updateEntity(v8);
			        	  DictionaryData dd = v8.getDeptId();//deptid deptname f49 f50
			        	  VoucherassStactsConfig vsf = v8.getF6();//f6 f62
			        	  ContractInfo info = v8.getF18();//f18 f65
			        	  InterV8Vouchers inter1 = new InterV8Vouchers();
			        	  inter1 = this.baseService.copyAndOverrideExistedValueFromObject(v8, inter1);
   		        	      BigDecimal bd7 = inter1.getF7();
   		        	      BigDecimal bd8 = inter1.getF8();
   		        	      bd7 = bd7.multiply(new BigDecimal(-1));
   		        	      bd8 = bd8.multiply(new BigDecimal(-1));
   		        	      inter1.setF7(bd7);
   		        	      inter1.setF8(bd8);
			        	  inter1.setRollBack("-1");
   		        	      inter1.setDeptId(dd);
   		        	      inter1.setDeptName(dd);
   		        	      inter1.setF49(dd);
   		        	      inter1.setF50(dd);
   		        	      inter1.setF6(vsf);
   		        	      inter1.setF62(vsf);
   		        	      inter1.setF18(info);
   		        	      inter1.setF65(info);
   		        	      inter1.setCreator(user);
			        	  this.baseService.saveEntity(inter1);
			        	  InterV8Vouchers inter2 = new InterV8Vouchers();
			        	  inter2 = this.baseService.copyAndOverrideExistedValueFromObject(v8, inter2);
			        	  inter2.setRollBack("2");
   		        	      inter2.setDeptId(dd);
   		        	      inter2.setDeptName(dd);
   		        	      inter2.setF49(dd);
   		        	      inter2.setF50(dd);
   		        	      inter2.setF6(vsf);
   		        	      inter2.setF62(vsf);
   		        	      inter2.setF18(info);
   		        	      inter2.setF65(info);
   		        	      inter2.setCreator(user);
			        	  this.baseService.saveEntity(inter2);         
			}
		}
		return null;
	}
}

