package com.tenwa.leasing.controller.cust;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.service.cust.CustService;


/**
 * 集团客户
 * @Title: CustSalesInfoController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:22:43 
 * @version V5.1
 */
@Controller(value = "CustSubsidiaryController")
@RequestMapping(value = "/**/acl")
public class CustSubsidiaryController {
	@Resource(name = "tableService")
	private TableService tableService;
	/**
	  * 添加子公司
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/addSubsidiary.acl")
	@ResponseBody
	public String addSubsidiary(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String custid=model.get("custid");
		String companycustid=model.get("companyid");
		CustInfo custcompany=this.tableService.findEntityByID(CustInfo.class, companycustid);
		CustInfoCompany company=custcompany.getCustInfoCompany();
		CustInfo cust=this.tableService.findEntityByID(CustInfo.class, custid);
		company.setCustinfoClique(cust);
		this.tableService.updateEntity(company);
		return null;
	}
	
	@RequestMapping(value = "/deleteSubsidiary.acl")
	@ResponseBody
	public String deleteSubsidiary(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String companycustid=model.get("companyid");
		CustInfo custcompany=this.tableService.findEntityByID(CustInfo.class, companycustid);
		CustInfoCompany company=custcompany.getCustInfoCompany();
		company.setCustinfoClique(null);
		this.tableService.updateEntity(company);
		return null;
	}
	

}
