package com.tenwa.leasing.controller.sysmgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.entity.JbpmFormValues;
import com.tenwa.jbpm.entity.JbpmListValues;
import com.tenwa.jbpm.entity.JbpmPhoneGroup;
import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;
import com.tenwa.jbpm.entity.PhoneGroupDesigner;
import com.tenwa.kernal.utils.QueryUtil;


/**
 * 
 * @author tpf
 * @date 2014-8-11上午10:30:01
 * @info 省市信息维护
 * @Copyright Tenwa
 */
@Controller(value = "WorkFlowAPPController")
@RequestMapping(value = "/**/acl")
public class WorkFlowAPPController {
	@Resource(name = "baseService")
	private BaseService baseService;

	@RequestMapping(value="/addOrRemovePhoneGroup.acl")
	@ResponseBody
	public JsonReturnResult addOrRemoveRejectRecord(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try{
			String phonegroupId=model.get("phonegroupId");
			String designerId=model.get("designerId");
			JbpmPhoneGroup phonegroup=this.baseService.findEntityByID(JbpmPhoneGroup.class, phonegroupId);
			JbpmWorkflowDesigner designer=this.baseService.findEntityByID(JbpmWorkflowDesigner.class, designerId);
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("phonegroupId", phonegroup);
			params.put("designerId", designer);
			List<PhoneGroupDesigner> list=this.baseService.findEntityByProperties(PhoneGroupDesigner.class, params);
			if(list.size()>0){
				this.baseService.removeEntity(list.get(0));
			}else{
				PhoneGroupDesigner pd=new PhoneGroupDesigner();
				pd.setPhonegroupId(phonegroup);
				pd.setPhoneGroupName(phonegroup.getGroupName());
				pd.setDesignerId(designer);
				pd.setDesignerName(designer.getDisplayName());
				this.baseService.saveEntity(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	@RequestMapping(value = "/addPhoneGroup.acl")
	@ResponseBody
	public JsonReturnResult addPhoneGroup(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 Map<String,String>classFieldMapping=new HashMap<String,String>();
			 int position=Integer.parseInt(model.get("position_"));
			 JbpmPhoneGroup phoneGroup=new JbpmPhoneGroup();
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, phoneGroup, classFieldMapping);
			 if(position==999999){
				 String sql="select nvl(Max(position_),0) position from  t_jbpm_phonegroup where 1=?";
				 List<Map<String,Object>> list=this.baseService.queryListBySql(sql, 1);
				 if(list.size()>0){
					position=Integer.parseInt(list.get(0).get("POSITION").toString())+1; 
				 }else{
					 position=1;
				 }
			 }else{
				 String sql="update t_jbpm_phonegroup set position_=position_+1 where position_>?";
				 this.baseService.updateBySql(sql, position);
				 position=position+1;
			 }
			 System.out.println(position);
			 phoneGroup.setPosition(position);
			 if("1".equals(model.get("islist"))){
				 phoneGroup.setIsList(true); 
			 }
			 this.baseService.saveEntity(phoneGroup);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	@RequestMapping(value = "/updatePhoneGroup.acl")
	@ResponseBody
	public JsonReturnResult updatePhoneGroup(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 Map<String,String>classFieldMapping=new HashMap<String,String>();
			 int position=Integer.parseInt(model.get("position_"));
			 JbpmPhoneGroup phoneGroup=new JbpmPhoneGroup();
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, phoneGroup, classFieldMapping);
			 if(position==999999){
				 String sql="select nvl(Max(position_),0) position from  t_jbpm_phonegroup where 1=?";
				 List<Map<String,Object>> list=this.baseService.queryListBySql(sql, 1);
				 if(list.size()>0){
					position=Integer.parseInt(list.get(0).get("POSITION").toString())+1; 
				 }else{
					 position=1;
				 }
			 }else{
				 String sql="update t_jbpm_phonegroup set position_=position_+1 where position_>?";
				 this.baseService.updateBySql(sql, position);
				 position=position+1;
			 }
			 System.out.println(position);
			 phoneGroup.setPosition(position);
			 phoneGroup.setId(model.get("id"));
			 if("1".equals(model.get("islist"))){
				 phoneGroup.setIsList(true); 
			 }
			 this.baseService.updateEntity(phoneGroup);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	@RequestMapping(value = "/addFormValues.acl")
	@ResponseBody
	public JsonReturnResult addFormValues(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 Map<String,String>classFieldMapping=new HashMap<String,String>();
			 int position=Integer.parseInt(model.get("position_"));
			 JbpmFormValues formValues=new JbpmFormValues();
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, formValues, classFieldMapping);
			 if(position==999999){
				 String sql="select nvl(Max(position_),0) position from  t_jbpm_formvalues where phonegroupid=?";
				 List<Map<String,Object>> list=this.baseService.queryListBySql(sql, model.get("phonegroupid"));
				 System.out.println(sql);
				 System.out.println(model.get("phonegroupid"));
				 if(list.size()>0){
					position=Integer.parseInt(list.get(0).get("POSITION").toString())+1; 
				 }else{
					 position=1;
				 }
			 }else{
				 String sql="update t_jbpm_formvalues set position_=position_+1 where position_>? and phonegroupid=?";
				 this.baseService.updateBySql(sql, position,model.get("phonegroupid"));
				 position=position+1;
			 }
			 System.out.println(position);
			 formValues.setPosition(position);
			 this.baseService.saveEntity(formValues);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	@RequestMapping(value = "/updateFormValues.acl")
	@ResponseBody
	public JsonReturnResult updateFormValues(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 Map<String,String>classFieldMapping=new HashMap<String,String>();
			 int position=Integer.parseInt(model.get("position_"));
			 JbpmFormValues formValues=new JbpmFormValues();
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, formValues, classFieldMapping);
			 if(position==999999){
				 String sql="select nvl(Max(position_),0) position from  t_jbpm_formvalues where phonegroupid=?";
				 List<Map<String,Object>> list=this.baseService.queryListBySql(sql, model.get("phonegroupid"));
				 if(list.size()>0){
					position=Integer.parseInt(list.get(0).get("POSITION").toString())+1; 
				 }else{
					 position=1;
				 }
			 }else{
				 String sql="update t_jbpm_formvalues set position_=position_+1 where position_>? and phonegroupid=?";
				 this.baseService.updateBySql(sql, position,model.get("phonegroupid"));
				 position=position+1;
			 }
			 System.out.println(position);
			 formValues.setPosition(position);
			 formValues.setId(model.get("id"));
			 this.baseService.updateEntity(formValues);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	@RequestMapping(value = "/addListValues.acl")
	@ResponseBody
	public JsonReturnResult addListValues(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 Map<String,String>classFieldMapping=new HashMap<String,String>();
			 int position=Integer.parseInt(model.get("position_"));
			 JbpmListValues ListValues=new JbpmListValues();
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, ListValues, classFieldMapping);
			 if(position==999999){
				 String sql="select nvl(Max(position_),0) position from  t_jbpm_listvalues where phonegroupid=?";
				 List<Map<String,Object>> list=this.baseService.queryListBySql(sql, model.get("phonegroupid"));
				 System.out.println(sql);
				 System.out.println(model.get("phonegroupid"));
				 if(list.size()>0){
					position=Integer.parseInt(list.get(0).get("POSITION").toString())+1; 
				 }else{
					 position=1;
				 }
			 }else{
				 String sql="update t_jbpm_listvalues set position_=position_+1 where position_>? and phonegroupid=?";
				 this.baseService.updateBySql(sql, position,model.get("phonegroupid"));
				 position=position+1;
			 }
			 System.out.println(position);
			 ListValues.setPosition(position);
			 if("1".equals(model.get("listshow"))){
				 ListValues.setListShow(true); 
			 }else{
				 ListValues.setListShow(false); 
			 }
			 if("1".equals(model.get("detailshow"))){
				 ListValues.setDetailShow(true); 
			 }else{
				 ListValues.setDetailShow(false); 
			 }
			 this.baseService.saveEntity(ListValues);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	@RequestMapping(value = "/updateListValues.acl")
	@ResponseBody
	public JsonReturnResult updateListValues(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			 Map<String,String>classFieldMapping=new HashMap<String,String>();
			 int position=Integer.parseInt(model.get("position_"));
			 JbpmListValues listValues=new JbpmListValues();
			 this.baseService.copyAndOverrideExistedValueFromStringMap(model, listValues, classFieldMapping);
			 if(position==999999){
				 String sql="select nvl(Max(position_),0) position from  t_jbpm_listvalues where phonegroupid=?";
				 List<Map<String,Object>> list=this.baseService.queryListBySql(sql, model.get("phonegroupid"));
				 if(list.size()>0){
					position=Integer.parseInt(list.get(0).get("POSITION").toString())+1; 
				 }else{
					 position=1;
				 }
			 }else{
				 String sql="update t_jbpm_listvalues set position_=position_+1 where position_>? and phonegroupid=?";
				 this.baseService.updateBySql(sql, position,model.get("phonegroupid"));
				 position=position+1;
			 }
			 System.out.println(position);
			 listValues.setPosition(position);
			 listValues.setId(model.get("id"));
			 if("1".equals(model.get("listshow"))){
				 listValues.setListShow(true); 
			 }else{
				 listValues.setListShow(false); 
			 }
			 if("1".equals(model.get("detailshow"))){
				 listValues.setDetailShow(true); 
			 }else{
				 listValues.setDetailShow(false); 
			 }
			 this.baseService.updateEntity(listValues);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
}
