package com.tenwa.leasing.controller.assetsnetmonitorapply;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;
import com.tenwa.leasing.service.assetsnetmonitor.AssetsNetMonitorService;

/**
 * 资产网络监控
 * 
 * @Title: AssetsNetMonitorController.java
 * @package: com.tenwa.leasing.controller.AssetsNetMonitorController
 * @author: ganwei
 * @date 2014年11月20日 上午9:27:24
 * @version V5.1
 */
@Controller(value = "assetsNetMonitorController")
@RequestMapping(value = "/**/acl")
public class AssetsNetMonitorController {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "assetsNetMonitorService")
	private AssetsNetMonitorService assetsNetMonitorService;

	/**
	 * 申请页面展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showAssetsNetMonitorApplication.acl")
	public String showAssetsNetMonitorApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");
		Map<String, String> variablesMap = new HashMap<String, String>();
		
		
		if ("add".equals(opertype)) {
			
			variablesMap.put("applyusername", user.getRealname());
			variablesMap.put("createdate", DateUtil.getSystemDateTime());
			
			AssetNetMonitorApply anma=new AssetNetMonitorApply();
			anma.setApplyuser(user);
			anma.setCreator(user);
			
			
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("creator", user);
			propertiesMap.put("applystatus", "未审核");
			
			List<AssetNetMonitorApply> anmalist =  this.tableService.findEntityByProperties(AssetNetMonitorApply.class, propertiesMap);
			
			if(anmalist.size()>0){
				variablesMap.put("applyno", anmalist.get(0).getApplynumber());
				variablesMap.put("applyusername", anmalist.get(0).getApplyuser().getRealname());
				variablesMap.put("createdate", anmalist.get(0).getCreateDate());
				variablesMap.put("applymemo", anmalist.get(0).getApplymemo());
				variablesMap.put("applyid", anmalist.get(0).getId());
				variablesMap.put("creator", anmalist.get(0).getCreator().getRealname());
			}else{
				this.assetsNetMonitorService.addAssetsNetMonitor(anma);
				variablesMap.put("applyno", anma.getApplynumber());
				variablesMap.put("applyid", anma.getId());
				variablesMap.put("creator", anma.getCreator().getRealname());
			}
			
			
		} else {
			String applyid = model.get("applyid");
			AssetNetMonitorApply assetNetMonitorApply = this.tableService
					.findEntityByID(AssetNetMonitorApply.class, applyid);
			variablesMap = this.tableService.getEntityPropertiesToStringMap(
					assetNetMonitorApply, null, "");
			
			variablesMap.put("applyid", assetNetMonitorApply.getId());
			variablesMap.put("applyusername", assetNetMonitorApply.getApplyuser().getRealname());
			variablesMap.put("applyno", assetNetMonitorApply.getApplynumber());
			variablesMap.put("createdate", assetNetMonitorApply.getCreateDate());
			variablesMap.put("creator", assetNetMonitorApply.getCreator().getRealname());
			if("view".equals(opertype)){
				variablesMap.put("modificatorname", assetNetMonitorApply.getModificator()==null?"":assetNetMonitorApply.getModificator().getRealname());
			}else{
				variablesMap.put("modificatorname", user.getRealname()==null?"":user.getRealname());								
			}
			if("edit".equals(opertype)){
				variablesMap.put("modifydate", DateUtil.getSystemDateTime().substring(0,19));
			}else{
				variablesMap.put("modifydate", assetNetMonitorApply.getModifyDate()==null?"":assetNetMonitorApply.getModifyDate());
			}
			

			
			
		/*	Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("applyid", assetNetMonitorApply);

			variablesMap.put("applyusername", assetNetMonitorApply
					.getApplyuser().getRealname());
			List<AssetNetMonitorApplyDetail> list = new ArrayList<AssetNetMonitorApplyDetail>();
			list = this.tableService.findEntityByProperties(
					AssetNetMonitorApplyDetail.class, propertiesMap);
			
			String json_csut_apply_list_str = this.tableService
					.getCollectionEntitiesPropertiesToJsonArray(list, null, "")
					.toString();
			variablesMap.put("json_csut_apply_list_str",
					json_csut_apply_list_str);*/
		}

		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}

		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/other/assets_network_monitor/assets_network_moniter_application_view.jsp";
		} else{			
			return "solutions/workflow/forms/other/assets_network_monitor/assets_network_moniter_application.jsp";
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
	@RequestMapping(value = "/addAssetsNetMonitorApplication.acl")
	@ResponseBody
	public String addAssetsNetMonitorApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {		
			this.assetsNetMonitorService.addAssetsNetMonitorApplication(model);
			returnstr = "{flag:\"true\",msg:\"申请成功！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}

		return returnstr;
	}

	/**
	 * 修改申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editAssetsNetMonitorApplication.acl")
	@ResponseBody
	public String updateAssetsNetMonitorApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			
			this.assetsNetMonitorService.updateAssetsNetMonitorApplication(model);
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
	@RequestMapping(value = "/deleteAssetsNetMonitorApplication.acl")
	@ResponseBody
	public String deleteAssetsNetMonitorApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		this.assetsNetMonitorService.deleteAssetsNetMonitorApplication(model);
		return null;
	}
}
