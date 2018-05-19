package com.tenwa.leasing.controller.fivecategoryapply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApply;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApplyDetail;
import com.tenwa.leasing.service.fivecategory.FiveCategoryService;
import com.tenwa.leasing.utils.WorkflowUtil;
import com.tenwa.leasing.serviceImpl.fivecategory.FiveCategoryServiceImpl;

/**
 * 五级分类
 * 
 * @Title: AssetsNetMonitorController.java
 * @package: com.tenwa.leasing.controller.AssetsNetMonitorController
 * @author: ganwei
 * @date 2014年11月20日 上午9:27:24
 * @version V5.1
 */
@Controller(value = "fiveCatgoryController")
@RequestMapping(value = "/**/acl")
public class FiveCatgoryController {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "fiveCategoryService")
	private FiveCategoryService fiveCategoryService;

	@RequestMapping(value = "/updFiveCategory.acl")
	@ResponseBody
	public JsonReturnResult updFiveCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.fiveCategoryService.updateFiveCategory(model);
		}catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;	
	}
	/**
	 * 申请页面展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showFiveCategoryApplication.acl")
	public String showFiveCategoryApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);//获取请求数据
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");

		Map<String, String> variablesMap = new HashMap<String, String>();

		if ("add".equals(opertype)) {
			//String applynumber=WorkflowUtil.getFiveCategoryApplySerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
			
			variablesMap.put("applyusername", user.getRealname());
			variablesMap.put("createdate", DateUtil.getSystemDateTime());
			
			FiveCategoryApply fiveCateGoryApply = new FiveCategoryApply();
			fiveCateGoryApply.setApplystatus("未审核");
			fiveCateGoryApply.setApplyuser(user);
			fiveCateGoryApply.setCreator(user);//给创建人字段插入信息，为了下面能够查出creator字段的信息
			//fiveCateGoryApply.setCreateDate(DateUtil.getSystemDateTime());
			//fiveCateGoryApply.setCreator(user.getRealname());
			//fiveCateGoryApply.setApplynumber(applynumber);
			//fiveCateGoryApply.setCreateDate(DateUtil.getSystemDate());
			String applyid = model.get("applyid");
			Map<String, Object> queryMap =new HashMap<String, Object>();
			
			queryMap.put("applystatus","未审核");
			queryMap.put("creator",user);
			//queryMap.put("CREATE_DATE",model.get("createdate"));
			
			
			
			List<FiveCategoryApply> listfiveCateGoryApply1=this.tableService.
					findEntityByProperties(FiveCategoryApply.class, queryMap);
			
			if(listfiveCateGoryApply1.size()==0){
				
//				String applyNumber = WorkflowUtil.getFiveCategoryApplySerialNumber(null,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
//				fiveCateGoryApply.setApplynumber(applyNumber);
				this.fiveCategoryService.addFiveCategoryApplication(fiveCateGoryApply);
				variablesMap.put("pid", fiveCateGoryApply.getId());
				variablesMap.put("applynumber", fiveCateGoryApply.getApplynumber());
				variablesMap.put("creatornamex",fiveCateGoryApply.getCreator().getRealname());
			
				
			}else{
				
				FiveCategoryApply fiveCateGoryApply2=new FiveCategoryApply();
				fiveCateGoryApply2=listfiveCateGoryApply1.get(0);
				variablesMap.put("applymemo",fiveCateGoryApply2.getApplymemo());
				variablesMap.put("pid",fiveCateGoryApply2.getId());
				variablesMap.put("applynumber", fiveCateGoryApply2.getApplynumber());
				variablesMap.put("creatornamex",user.getRealname());
				
			}
			
			/*this.tableService.saveEntity(fiveCateGoryApply);
			variablesMap.put("pid",fiveCateGoryApply.getId());*/
			
		} else if("edit".equals(opertype)){
			String applyid = model.get("applyid");
			FiveCategoryApply fiveCateGoryApply = this.tableService
					.findEntityByID(FiveCategoryApply.class, applyid);
			
			variablesMap = this.tableService.getEntityPropertiesToStringMap(
					fiveCateGoryApply, null, "");
			variablesMap.put("pid",applyid);
			variablesMap.put("applyusername",fiveCateGoryApply.getCreator().getRealname());
			variablesMap.put("createdate",fiveCateGoryApply.getCreateDate());
			
			variablesMap.put("applynumber", fiveCateGoryApply.getApplynumber());
			variablesMap.put("creatornamex",fiveCateGoryApply.getCreator().getRealname());
			variablesMap.put("modificator",user.getRealname());
			variablesMap.put("modifydate", DateUtil.getSystemDateTime());
			
//			variablesMap.put(key, value)
			
//			variablesMap.put("")
			
			
			
		} else if("view".equals(opertype)){
			String applyid = model.get("applyid");
			FiveCategoryApply fiveCateGoryApply = this.tableService
					.findEntityByID(FiveCategoryApply.class, applyid);
			
			variablesMap = this.tableService.getEntityPropertiesToStringMap(
					fiveCateGoryApply, null, "");
			variablesMap.put("applyusername", fiveCateGoryApply
					.getApplyuser().getRealname());
			variablesMap.put("applyid",fiveCateGoryApply.getApplynumber());
			variablesMap.put("creatornamex",fiveCateGoryApply.getCreator().getRealname());
			variablesMap.put("modificator", fiveCateGoryApply.getModificator()== null ? ""
					:fiveCateGoryApply.getModificator().getRealname());
			
		}

		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}

		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/contract/five_category_apply/five_category_apply_view.jsp";
		} else {
			return "solutions/workflow/forms/contract/five_category_apply/five_category_apply.jsp";
		}
	}
	
	
	

	/**
	 * 新增申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addFiveCategoryApplication.acl")
	@ResponseBody
	public String addFiveCategoryApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			//Map<String, String> variablesMap = new HashMap<String, String>();
			//String applynumber=WorkflowUtil.getFiveCategoryApplySerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
			this.fiveCategoryService.addFiveCategoryApplication(model);
			
			
			returnstr = "{flag:\"true\",msg:\"申请成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}

		return returnstr;
	}
	@RequestMapping(value = "/showFiveCategoryApplicationOk.acl")
	@ResponseBody
	public String showFiveCategoryApplicationOk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);

		String applynumbers = model.get("applynumbers");	
		String[] applymum = applynumbers.split(",");
		
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		for(String key : applymum){
			
			propertiesMap.put("applynumber", key);
			FiveCategoryApply fivecategoryapply= this.tableService
					.findEntityByProperties(FiveCategoryApply.class, propertiesMap).get(0);
			
			fivecategoryapply.setApplystatus("审核中");
			propertiesMap.clear();
			this.tableService.updateEntity(fivecategoryapply);
		}
		return "yes";
	}

	/**
	 * 修改申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editFiveCategoryApplication.acl")
	@ResponseBody
	public String updateFiveCategoryApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			this.fiveCategoryService
					.updateFiveCategoryApplication(model);
			returnstr = "{flag:\"true\",msg:\"修改成功！\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage() + "\"}";
		}
		return returnstr;
	}

	/**
	 * 删除申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteFiveCategoryApplication.acl")
	@ResponseBody
	public String deleteFiveCategoryApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		this.fiveCategoryService.deleteFiveCategoryApplication(model);
		return null;
	}
	
	
}