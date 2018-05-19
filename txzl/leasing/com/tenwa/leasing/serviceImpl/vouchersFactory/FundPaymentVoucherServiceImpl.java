package com.tenwa.leasing.serviceImpl.vouchersFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.FundPaymentVoucherService;
import com.tenwa.leasing.utils.Tools;

@Service(value = "fundPaymentVoucherService")
public class FundPaymentVoucherServiceImpl implements FundPaymentVoucherService{
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="付款流程";
	
	@Override
	public void createVoucherReceiveFund(Map<String, String> variablesMap)
			throws Exception {
		//System.out.println("============="+variablesMap);
		User currentUser = SecurityUtil.getPrincipal();
		String contractid = variablesMap.get("contract_info.id");
		ContractInfo contractInfo=this.baseService.findEntityByID(ContractInfo.class, contractid);
		Map<String, String> headMap = new HashMap<String, String>();
		String custname = "";
		if (contractInfo.getId() != null) {
			headMap.put("contract_id", contractInfo.getId());
			custname = contractInfo.getCustId().getCustName();
		} else {
			throw new BusinessException("合同号为空，不能生成凭证！");
		}
		headMap.put("moduleName", modlename);
		// 获取合同号传入摘要
		String contractnum = variablesMap.get("contract_info.contractnumber");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		//获取当前系统时间
		String currentDate = DateUtil.getSystemDate();
		//计划金额
		BigDecimal planmoney  = BigDecimal.ZERO;
		//计划金额总额
		BigDecimal sumplanmoney  = BigDecimal.ZERO;
		//会计到账日
		String accountDate = "";
		
		String paymentplan = variablesMap.get("json_put_current_str"); 
		JSONArray paymentplans = new JSONArray(paymentplan);
		
		for(int i = 0; i < paymentplans.length(); i++){
			JSONObject obj = paymentplans.getJSONObject(i);
			planmoney = new BigDecimal(obj.getString("factmoney"));
			sumplanmoney=sumplanmoney.add(planmoney);
		}
		//获取客户类型
		String paymentcurrent = variablesMap.get("json_put_current_str");
		JSONArray paymentcurrents = new JSONArray(paymentcurrent);
		String custtype="";
		String feetypename="";
		for(int i =0; i<paymentcurrents.length();i++){
			JSONObject obj = paymentcurrents.getJSONObject(i);
			//custtype=obj.getString("customertypename");
			accountDate=obj.getString("accountingdate");
			feetypename = obj.getString("feetypename");//费用类型	
		}
		
		String summary0 = "合同号："+contractnum+",承租人:"+custname+",收取："+sumplanmoney+"元";
		//借：1002.01.02
				Map<String, String> map1 = new HashMap<String, String>();
				map1.put("voucherSummary", summary0);  // 摘要  显示多起租金				
				/*if(custtype.equals("集团内")){
					map1.put("subjectsId", "105");      //科目id
				}else{
					map1.put("subjectsId", "106"); 
				}*/
				if("首付款".equals(feetypename)){
					map1.put("subjectsId", "103");
				}else if("退回保证金".equals(feetypename)){
					map1.put("subjectsId", "105");
				}else{
					map1.put("subjectsId", "102");
				}
				map1.put("debitMoney", Tools.formatNumberDoubleTwo(sumplanmoney.toString())); //借
				map1.put("lenderMoney", "0");  //贷
				map1.put("ebankFactDate",currentDate);//网银日期
				map1.put("accountDate",accountDate);    //会计记账日
				list.add(map1); 
				
				for(int i = 0; i < paymentcurrents.length(); i++){
					JSONObject obj = paymentcurrents.getJSONObject(i);
					 feetypename = obj.getString("feetypename");
					String accountDate1=obj.getString("accountingdate");
					//结算方式
					String settlemethod=obj.getString("settlemethodname");
					BigDecimal planmoney1 = BigDecimal.ZERO;
					planmoney1 = new BigDecimal(obj.getString("factmoney"));
					Object account= obj.getString("accnumber");
					
					String summary = "合同号："+contractnum+",承租人:"+custname+","+feetypename+"："+planmoney1+"元";
					// 贷
					Map<String, String> map = new HashMap<String, String>();
					map.put("voucherSummary", summary);
					Map<String,Object> maps=new HashMap();
				    maps.put("accNumber", account);//ACC_NUMBER
				    List<OwnAccount> oas=this.baseService.findEntityByProperties(OwnAccount.class, maps);				   				   
				      //2科目
				     if(null !=oas && oas.size()>0){
				    	 map.put("subjectsId", oas.get(0).getAccCode());
				     }
					/*if(settlemethod.equals("电汇")){
						map.put("subjectsId", "106");
					}else if(feetypename.contains("保证金")){
						map.put("subjectsId", "101");
					}else{
						map.put("subjectsId", "102");
					}*/
					map.put("debitMoney", "0");
					map.put("lenderMoney",Tools.formatNumberDoubleTwo(planmoney1.toString())); 
					map.put("ebankFactDate", currentDate);
					map.put("accountDate", accountDate1);
					list.add(map);
				}
				
				if (list != null && list.size() > 0) {
					voucherToV8Service.saveEvidenceInfo(headMap, list, currentUser);
				}
		
	}
}
