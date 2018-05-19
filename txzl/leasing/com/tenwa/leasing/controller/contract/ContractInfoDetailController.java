package com.tenwa.leasing.controller.contract;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.SM.MachineMainData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.BSDataBaseManager;



/**
 * 银行信息
 * @Title: ContractInfoDetailController.java
 * @package: com.tenwa.leasing.controller.contract
 * @author: lichaojie
 * @date 2014年11月20日 上午9:27:24
 * @version V5.1
 */
@Controller(value = "contractInfoDetailController")
@RequestMapping(value = "/**/acl")
public class ContractInfoDetailController {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@RequestMapping(value = "/queryContractInfoDetail.acl")
	public String queryContractInfoDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String contractid = model.get("contractid");
		ContractInfo contractInfo =(ContractInfo) this.tableService.findEntityByID(ContractInfo.class, contractid);
		CustInfo customerInfo = contractInfo.getCustId();
		
		Map<String,String> variablesMap = new HashMap<String, String>();
		
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractInfo, null, "contract_info"));
		String cust_class = customerInfo.getCustClass().getCode();
		variablesMap.put("rawValue_contract_info.custname", customerInfo.getCustName());
		variablesMap.put("contract_info.custname", customerInfo.getCustName());
		variablesMap.put("contract_info.custclass", cust_class);
		variablesMap.put("contract_info.oldcustname", customerInfo.getCustName());//旧承租人name(起租后合同变更使用)
		variablesMap.put("contract_info.oldcustid", customerInfo.getId());//旧承租人id(起租后合同变更使用)
		variablesMap.put("contract_info.custid", customerInfo.getId());//新承租人id
		variablesMap.put("rawValue_contract_info.custid", customerInfo.getCustName());//新承租人名字
		variablesMap.put("contract_info.custnumber", customerInfo.getCustNumber());
		variablesMap.put("contract_info.proj_id", contractInfo.getProjId() == null ? "" : contractInfo.getProjId().getProjId());
		variablesMap.put("contract_info.projdate", contractInfo.getProjId() == null ? "" : contractInfo.getProjId().getProjDate());
		variablesMap.put("contract_info.othercondition", contractInfo.getOtherCondition());
		
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		
		//租金计划
		queryMainObjectMap.put("contract_id", contractid);
		queryMainObjectMap.put("plan_date",variablesMap.get("fund_ebank_data.factdate"));
		String json_rent_income_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/rent/rent_comm/rent_plan.xml", queryMainObjectMap).toString();
		variablesMap.put("json_rent_income_plan_str", json_rent_income_plan_str);
		
		//租金回笼历史
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contract_id", contractInfo.getId());
		queryMainObjectMap.put("orderbyclause", "order by income.planlist asc");
		String json_rent_income_his_str = this.tableService.getJsonArrayData("eleasing/workflow/rent/rent_comm/rent_income.xml", queryMainObjectMap).toString();
		variablesMap.put("json_rent_income_his_str", json_rent_income_his_str);
		
		//资金收付款计划
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractInfo.getId());
		//queryMainObjectMap.put("paytype", "pay_type_in");
		String json_collection_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_plan_list.xml", queryMainObjectMap).toString();
		variablesMap.put("json_collection_plan_str", json_collection_plan_str);
		
		//资金收付款历史
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contractid", contractInfo.getId());
	    //queryMainObjectMap.put("paytype", "pay_type_in");
		String json_collection_his_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_charge_list.xml", queryMainObjectMap).toString();
		variablesMap.put("json_collection_his_str", json_collection_his_str);
		
		//租赁物件明细
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contract_id", contractInfo.getId());
		String json_contract_equip_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_change/contract_equip.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_equip_str",json_contract_equip_str);

		//担保单位信息
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contract_id", contractInfo.getId());
		String json_contract_guarantee_method_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_change/contract_guarantee_method.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_guarantee_method_str", json_contract_guarantee_method_str);
		
		//抵押物信息
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contract_id", contractInfo.getId());
		String json_contract_guarantee_equip_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_change/contract_guarantee_equip.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_guarantee_equip_str", json_contract_guarantee_equip_str);

		//租金开票类型
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contractid", contractInfo.getId());
		ContractInvoiceType invoiceType = contractInfo.getContractInvoiceType();
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(invoiceType, null, "contract_invoice_type"));
		
		//投放历史明细
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contract_id", contractid);
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("feetype", "feetype10");
		queryMainObjectMap.put("paytype", "pay_type_out");
		String json_put_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_charge_list.xml", queryMainObjectMap).toString();
		variablesMap.put("json_put_his_str",json_put_plan_str);
		
		
		 //合同各方
		this.contractCommService.loadContractSignatoryInfo(contractInfo, variablesMap);
		//供应商信息
		this.contractCommService.loadContractSupplierInfo(contractInfo, variablesMap);
		
		for(Entry entry : variablesMap.entrySet()){
			request.setAttribute(entry.getKey().toString(), entry.getValue());
		}
		return "solutions/workflow/forms/contract/contract_search/contract_info_detail.jsp";
	}
	
	@RequestMapping(value = "/saveSpecialrequirement.acl")
	@ResponseBody
	public String specialrequirementforcontract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String contractid = model.get("contractid");
		String specialrequirement = model.get("specialrequirement");
		ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
		contract.setSpecialRequirement(specialrequirement);
		this.tableService.saveEntity(contract);
		try {
			
			this.tableService.updateEntity(contract);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "项目维护中登网登记更新失败！";
		}
	}
	
	@RequestMapping(value = "/saveZdnet.acl")
	@ResponseBody
	public String zdnetforcontract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String contractid = model.get("contractid");
		String zdnet = model.get("zdnet");
		String registerdate = model.get("registerdate");
		ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
		contract.setZdnetRegisterDate(registerdate);
		contract.setRegisterZdnet(zdnet);
		this.tableService.saveEntity(contract);
		try {
			
			this.tableService.updateEntity(contract);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "项目维护中登网登记更新失败！";
		}
	}
	
	@RequestMapping(value = "/saveSignDate.acl")
	@ResponseBody
	public String signdateforcontract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String contractid = model.get("contractid");
		String signdate = model.get("signdate");
		ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
		contract.setSignDate(signdate);
		this.tableService.saveEntity(contract);
		try {
			
			this.tableService.updateEntity(contract);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "项目维护签约时间更新失败！";
		}
	}
	@RequestMapping(value = "/saveAdvanceBilling.acl")
	@ResponseBody
	public String advancebillingforcontract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String contractid = model.get("contractid");
		String advancebilling = model.get("advancebilling");
		ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
		contract.setAdvanceBilling(advancebilling);
		this.tableService.saveEntity(contract);
		try {
			
			this.tableService.updateEntity(contract);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "项目维护是否提前开票更新失败！";
		}
	}
	
	
	@RequestMapping(value = "/saveWindMachine.acl")
	@ResponseBody
	public String saveMainBrandMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);	
		String contractid = model.get("cid");
		String  windmachine = model.get("windmachine");
		ContractInfo ci = this.tableService.findEntityByID(ContractInfo.class, contractid);
		  ci.setWindmachine(windmachine);
		  this.tableService.saveEntity(ci);
		try {
			this.tableService.updateEntity(ci);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "风机合同号更新失败！";
		}
	}
	
	
	@RequestMapping(value = "/getGuarantor.acl")
	@ResponseBody
	public String getGuarantor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);	
		
		String value = null;
		String name = null;
		String cid = model.get("id");
		String sqlstr = "  select t.name,t.value from(select ROWNUM rn ,cgm.assuror name,ci.cust_name value, cgm.create_date from contract_guarantee_method cgm  left join contract_info c on cgm.contract_id = c.id left join cust_info ci on ci.id = cgm.assuror where c.id =? and cgm.main_guarantee_flag = '是' order by cgm.create_date) t where rn = 1";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>() ;
		list = this.tableService.queryListBySql(sqlstr,cid);
		if(list.size()>0){
		 Map<String,Object>map = list.get(0);
		 Object get1 =  map.get("value");
		 Object get2 =  map.get("name");
		 value = get1.toString();
		 name = get2.toString();
		 String x = value +"+"+name;
			try {
				return x;
			} catch (Exception e) {
				 e.printStackTrace();
				return  null;
			}
		}else{
			return null;
		}
			

	}

	
	
	
	@RequestMapping(value = "/saveaccbank.acl")
	@ResponseBody
	public String saveAccBank(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);	
		try{
            String number=createNum();
			return number;
		}catch (Exception e) {
			return "调用失败!["+e.getMessage()+"]";
		}
	}
	private synchronized String createNum() throws Exception {
		try{
			
			String year=DateUtil.getSystemDate().substring(0, 4);
			String month=DateUtil.getSystemDate().substring(5, 7);
			String day=DateUtil.getSystemDate().substring(8, 10);
			List<Map<String,Object>> numberlist=this.tableService.queryListBySql("select * from T_SERIAL_NUMBER where type_='付款通知书编号' ");
			if(numberlist!=null&&numberlist.size()>0){
				if(new BigDecimal(year).compareTo(new BigDecimal(numberlist.get(0).get("year_").toString()))!=0||new BigDecimal(month).compareTo(new BigDecimal(numberlist.get(0).get("month_").toString()))!=0){
					this.tableService.updateBySql("update T_SERIAL_NUMBER set year_=?, month_=?, order_number_=? where  type_='付款通知书编号' ", Integer.parseInt(year),Integer.parseInt(month),001);
					return "FKTZS-"+year+month+"001";
				}else{
					BigDecimal number=new BigDecimal(numberlist.get(0).get("order_number_").toString()).add(BigDecimal.ONE) ;
					String format=new DecimalFormat("000").format(number);
					this.tableService.updateBySql("update T_SERIAL_NUMBER set  order_number_=? where  type_='付款通知书编号' ", Integer.parseInt(format));
					return "FKTZS-"+year+month+format;
				}
			}else{
				this.tableService.updateBySql("insert into T_SERIAL_NUMBER ( id_,type_,year_,month_,order_number_) values(sys_guid(),'付款通知书编号',?,?,?)", Integer.parseInt(year),Integer.parseInt(month),001);
				return "FKTZS-"+year+month+"001";
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
