package assetnetmonitorapply.copy;

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

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApplyDetail;
import com.tenwa.leasing.service.assetsnetmonitor.AssetsNetMonitorService;
import com.tenwa.leasing.utils.WorkflowUtil;

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
		} else {
			String applyid = model.get("applyid");
			AssetNetMonitorApply assetNetMonitorApply = this.tableService
					.findEntityByID(AssetNetMonitorApply.class, applyid);
			variablesMap = this.tableService.getEntityPropertiesToStringMap(
					assetNetMonitorApply, null, "");

			Map<String, Object> propertiesMap = new HashMap<String, Object>();
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
					json_csut_apply_list_str);
		}

		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}

		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/other/assets_network_monitor/assets_network_moniter_application_view.jsp";
		} else {
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
			this.assetsNetMonitorService
					.updateAssetsNetMonitorApplication(model);
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
