package com.tenwa.leasing.controller.factoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.factoring.FactoringControversy;
import com.tenwa.leasing.service.asset.AssetsMngService;

@Controller(value = "controversyRelieve")
@RequestMapping(value = "/**/acl")
public class ControversyRelieve {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "assetsMngService")
	private AssetsMngService assetsMngService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/showControversyRelieve.acl")
	public String showControversyRelieve(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			Map<String, String> variablesMap = new HashMap<String, String>();
			Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
			String opertype = model.get("opertype");
			String id = model.get("id");
			FactoringControversy factoringControversy = this.tableService.findEntityByID(FactoringControversy.class, id);
			
			variablesMap = this.tableService.getEntityPropertiesToStringMap(factoringControversy, null, "fc_info");
			variablesMap.put("fc.cid", factoringControversy.getContractId().getId());//合同ID
			variablesMap.put("fc.contractid", factoringControversy.getContractId().getContractId());//合同编号
			
			variablesMap.put("fc.contractnumber", factoringControversy.getContractId().getContractNumber());//业务合同编号
			//System.out.println(factoringControversy.getContractId().getCustId().getCustName());
			variablesMap.put("fc.custname", factoringControversy.getContractId().getCustId().getCustName());//客户名称
			
			/*ContractInfo contractId=factoringControversy.getContractId();
			CustInfo custId=contractId.getCustId();
			String custname=custId.getCustName();*/
		   variablesMap.put("fc.projname", factoringControversy.getContractId().getProjectName());//项目名称
			variablesMap.put("fc.applicationnumber", factoringControversy.getApplicationNumber());//争议申请编号
			variablesMap.put("fc.createdate", factoringControversy.getCreateDate());//争议申请日期
			variablesMap.put("fc.explaination", factoringControversy.getExplaination());//争议申请说明
			/*variablesMap.put("fc.relievedate", factoringControversy.getRelieveDate());//争议解除日期
			variablesMap.put("fc.status", factoringControversy.getStatus()+"");//争议状态
			variablesMap.put("fc.relieveexplaination", factoringControversy.getRelieveExplaination());//争议解除说明
*/			for (Entry entry : variablesMap.entrySet()) {
				request.setAttribute(entry.getKey().toString(), entry.getValue());
			}
			
		if ("view".equals(opertype)) {
			return "solutions/workflow/forms/factoring/factoring_relieve/factoring_relieve_edit.jsp";
		}else{
			return null;
		}
	}
}
