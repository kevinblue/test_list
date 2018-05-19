package com.tenwa.leasing.controller.litigationConclusion;

import java.util.HashMap;
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
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.lawmng.LawApproval;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.litigationConclusion.LitigationConclusionService;
/**
 * 诉讼结论登记
 * 
 * @Title: AssetsNetMonitorController.java
 * @package: com.tenwa.leasing.controller.AssetsNetMonitorController
 * @author: ganwei
 * @date 2014年11月20日 上午9:27:24
 * @version V5.1
 */
@Controller(value = "litigationConclusionRegistrationController")
@RequestMapping(value = "/**/acl")
public class LitigationConclusionController {
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "litigationConclusionRegistrationService")
	private LitigationConclusionService litigationConclusionRegistrationService;

	/**
	 * 诉讼基本信息页面展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showLitigationConclusionRegistrationApplication.acl")
	public String showLitigationConclusionRegistrationApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		User user = SecurityUtil.getPrincipal();
		String opertype = model.get("opertype");
		Map<String, String> variablesMap = new HashMap<String, String>();
		if ("litigation".equals(opertype)) {
			variablesMap.put("lawnum",model.get("lawnum"));
			Map<String, Object> propertiesMap = new HashMap<String,Object>();
			propertiesMap.put("lawnum",model.get("lawnum"));
			LawApproval lawApproval = this.tableService
					.findEntityByProperties(LawApproval.class, propertiesMap).get(0);
			ContractInfo contractInfo = lawApproval.getContractInfo();  
			CustInfo custInfo =contractInfo.getCustId();
			CustInfoCompany custInfoCompany =custInfo.getCustInfoCompany();
			variablesMap.put("lawApproval", lawApproval.getId());
			variablesMap.put("contract_id", contractInfo.getContractId());
			variablesMap.put("contract_number",contractInfo.getContractNumber());
			variablesMap.put("cust_name", custInfo.getCustName());
			variablesMap.put("card_no", custInfoCompany.getOrgCode());
			variablesMap.put("projmanage",contractInfo.getProjManage().getRealname());	//项目经理
		}
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		if ("litigation".equals(opertype)) {
            return "solutions/workflow/forms/law_mng/litigation_conclusion/litigation_conclusion_registration_view.jsp";
		} else {
			return "solutions/workflow/forms/law_mng/litigation_conclusion/litigation_conclusion_registration.jsp";
		}
	}

	/**
	 * 新增诉讼结论
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/addLitigationConclusionRegistrationApplication.acl")
	@ResponseBody
	public String addLitigationConclusionRegistrationApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			this.litigationConclusionRegistrationService.addlitigationConclusionRegistrationApplication(model);
			returnstr = "{flag:\"true\",msg:\"新增完毕！\",opertype:\"add\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage()
					+ "\",opertype:\"add\"}";
		}

		return returnstr;
	}*/

	/**
	 * 修改申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/editLitigationConclusionRegistrationApplication.acl")
	@ResponseBody
	public String updatelitigationConclusionRegistrationApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		String returnstr = "";
		try {
			this.litigationConclusionRegistrationService
					.updatelitigationConclusionRegistrationApplication(model);
			returnstr = "{flag:\"true\",msg:\"修改成功！\"}";
		} catch (Exception e) {
			e.printStackTrace();
			returnstr = "{flag:\"false\",msg:\"" + e.getMessage() + "\"}";
		}
		return returnstr;
	}*/

	/**
	 * 删除申请
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/deleteLitigationConclusionRegistrationApplication.acl")
	@ResponseBody
	public String deletelitigationConclusionRegistrationApplication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		this.litigationConclusionRegistrationService.deletelitigationConclusionRegistrationApplication(model);
		return null;
	}*/
}
