package com.tenwa.leasing.controller.invoice;

import java.util.HashMap;
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
import com.tenwa.leasing.service.asset.AssetsMngService;

@Controller(value = "vatInvoiceInfoContro")
@RequestMapping(value = "/**/acl")
public class VatInvoiceInfoContro {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "assetsMngService")
	private AssetsMngService assetsMngService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/showVatInvoiceInfoContro.acl")
	public String showAccountReceivable(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String opertype = model.get("opertype");
		String contractid = model.get("contractid");
		String id = model.get("id");
		ContractInfo contractinfo = this.tableService.findEntityByID(ContractInfo.class, id);
		Map<String, String> variablesMap = new HashMap<String, String>();
		variablesMap = this.tableService.getEntityPropertiesToStringMap(contractinfo, null, "contract_info");
		variablesMap.put("contract_info.proj_id", contractinfo.getProjId().getProjId());
		variablesMap.put("contract_info.projdate", contractinfo.getProjId().getProjDate());
		variablesMap.put("rawValue_contract_info.custname", contractinfo.getCustId().getCustName());
		variablesMap.put("contract_info.custnumber", contractinfo.getCustId().getCustNumber());
		variablesMap.put("contract_info.projname", contractinfo.getProjectName());
		for (Entry entry : variablesMap.entrySet()) {
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		if ("view".equals(opertype)) {                                             
			return "solutions/workflow/forms/factoring/vat_invoice/vat_invoice_record_new.jsp?contractid="+contractid+"&id="+id;
		}else{
			return null;
		}
	}
}
