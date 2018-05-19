package com.tenwa.leasing.serviceImpl.vouchersFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
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
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.ContractAssetsChangeVoucherService;
import com.tenwa.leasing.utils.Tools;

/**   
*    
* 项目名称：tls5.1   
* 类名称：ContractAssetsChangeVoucherServicempl   
* 类描述：   
* 创建人：sunz   
* 创建时间：2016年6月12日 上午10:32:15   
* @version        
*/
@Service(value = "contractAssetsChangeVoucherService")
public class ContractAssetsChangeVoucherServicempl implements ContractAssetsChangeVoucherService {

	private Logger logger=Logger.getLogger(ContractAssetsChangeVoucherServicempl.class);
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="资产变更流程";

	@Override
	public void createVoucherReceiveContract(Map<String, String> variablesMap)
			throws Exception {
		logger.info("调息凭证开始！");
		
			
		User currentUser = SecurityUtil.getPrincipal();
		String contractid = variablesMap.get("contract_info.id");
		String datas = variablesMap.get("rawValue_contract_info.leasform");//租赁形式
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
		
		
		//变更前财务利息
		BigDecimal beforeInterest  = BigDecimal.ZERO;
		//变更前财务利息总和
		BigDecimal beforeInterests  = BigDecimal.ZERO;
		//变更后财务利息
		BigDecimal afterInterest  = BigDecimal.ZERO;
		//变更后财务利息总和
		BigDecimal afterInterests  = BigDecimal.ZERO;
		//网银到账日给当前系统时间
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String factdate=format.format(date); 
		
		//变更前		
		String fundrentplan = variablesMap.get("json_fund_rent_plan_before_str");
		JSONArray fundrentplans = new JSONArray(fundrentplan);
		
		for(int i = 0; i < fundrentplans.length(); i++){
			JSONObject obj = fundrentplans.getJSONObject(i);
			beforeInterest = new BigDecimal(obj.getString("interest"));
			//变更前财务利息总和
			beforeInterests=beforeInterests.add(beforeInterest);
		}
		
		//变更后
		String fundrent = variablesMap.get("json_fund_rent_plan_str");
		JSONArray fundrents = new JSONArray(fundrent);
		
		for(int i = 0; i < fundrents.length(); i++){
			JSONObject obj = fundrents.getJSONObject(i);
			afterInterest = new BigDecimal(obj.getString("interest"));
			//变更后财务利息总和
			afterInterests=afterInterests.add(afterInterest);
		}
		
		
		//利息差(借)	
		BigDecimal interest = afterInterests.subtract(beforeInterests);	
		String summay="合同号-"+contractnum+"-承租人-"+custname+"-资产变更流程";		
		//借 
		Map<String, String> mapj = new HashMap<String, String>();		
		mapj.put("voucherSummary", summay); //摘要
		mapj.put("subjectsId", "109");	//科目代码
		mapj.put("debitMoney", Tools.formatNumberDoubleTwo(interest.toString()));	//借
		mapj.put("lenderMoney", "0");  //贷
		mapj.put("ebankFactDate",factdate);//网银日期
		mapj.put("accountDate",factdate);	//会计到账日
		list.add(mapj);				
		
		String summay1="合同号-"+contractnum+"-承租人-"+custname+"-资产变更流程";	
		//利息差(贷:直租)
		BigDecimal interest1 = interest.multiply(new BigDecimal("1.17"));
		BigDecimal interest5 = interest1.divide(new BigDecimal("0.17"),2,BigDecimal.ROUND_HALF_UP);
		//利息差(贷:回租)	
		BigDecimal interest2 = interest.multiply(new BigDecimal("1.06"));
		BigDecimal interest6 = interest2.divide(new BigDecimal("0.17"),2,BigDecimal.ROUND_HALF_UP);
		
		//贷  
		Map<String, String> mapf = new HashMap<String, String>();
		mapf.put("voucherSummary", summay1); //摘要
		  if(("直租").equals(datas)){						  	
			  mapf.put("subjectsId", "111");	//科目代码
			  mapf.put("debitMoney", "0");	//借
			  mapf.put("lenderMoney",Tools.formatNumberDoubleTwo(interest5.toString()));//贷
			  mapf.put("ebankFactDate",factdate);//网银日期
			  mapf.put("accountDate",factdate);	//会计到账日
			  list.add(mapf);
		}else {						
			  mapf.put("subjectsId", "111");	//科目代码
			  mapf.put("debitMoney", "0");	//借
			  mapf.put("lenderMoney",Tools.formatNumberDoubleTwo(interest6.toString()));//贷
			  mapf.put("ebankFactDate",factdate);//网银日期
			  mapf.put("accountDate",factdate);	//会计到账日
			  list.add(mapf);
		}
		
		  
		String summay2="合同号-"+contractnum+"-承租人-"+custname+"-资产变更流程";
		//利息差- 未实现融资租赁收益(贷)(直租)	
		BigDecimal interest3 = interest.subtract(interest5);				
		//利息差- 未实现融资租赁收益(贷)(回租)	
		BigDecimal interest4 = interest.subtract(interest6);
		
		//贷  
		Map<String, String> mapg = new HashMap<String, String>();
		mapg.put("voucherSummary", summay2); //摘要
		if(("直租").equals(datas)){			
			mapg.put("subjectsId", "112");
			mapg.put("debitMoney", "0");
			mapg.put("lenderMoney",Tools.formatNumberDoubleTwo(interest3.toString())); //贷
			mapg.put("ebankFactDate",factdate);//网银日期
			mapg.put("accountDate", factdate);
			list.add(mapg);
		}else {
			mapg.put("subjectsId", "112");
			mapg.put("debitMoney", "0");
			mapg.put("lenderMoney",Tools.formatNumberDoubleTwo(interest4.toString())); 
			mapg.put("ebankFactDate",factdate);//网银日期
			mapg.put("accountDate", factdate);
			list.add(mapg);
		}
				
		if(list!=null&&list.size()>0){
			voucherToV8Service.saveV8Message(headMap, list, currentUser);
		}
		logger.info(list);
		logger.info("调息结束");
	}
}
		
		
	

	
	

