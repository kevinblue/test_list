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
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.FundRedVoucherService;
import com.tenwa.leasing.utils.Tools;


@Service(value = "FundRedVoucherService")
public class FundRedVoucherServiceImpl implements FundRedVoucherService {

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="资金红冲流程";

	@Override
	public void createVoucherFundRed(Map<String, String> variablesMap)
			throws Exception {
		User currentUser = SecurityUtil.getPrincipal();
		String contractid = variablesMap.get("contract_info.id");				
		ContractInfo contractInfo=this.baseService.findEntityByID(ContractInfo.class, contractid);
		Integer contractstatus=  contractInfo.getContractStatus();
		Map<String, String> headMap = new HashMap<String, String>();
		String custname = "";	//承租人
		if (contractInfo.getId() != null) {
			headMap.put("contract_id", contractInfo.getId());	
			custname = contractInfo.getCustId().getCustName();
		} else {
			throw new BusinessException("合同号为空，不能生成凭证！");
		}
		headMap.put("moduleName", modlename);			
		// 获取合同号传入摘要
		String contractnum = variablesMap.get("contract_info.contractnumber");
		//集合传入的数据是一个list集合，里面是一个个的map集合，一个map封装一个凭证分录
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		
		
		//获得网银数据信息
		String ebankid=variablesMap.get("fund_ebank_data.id");
		//FundEbankData fundebankdata=this.baseService.findEntityByID(FundEbankData.class,ebankid);
		//String factdate=fundebankdata.getFactDate();	//网银到账时间
		//获得收款明细全部信息
		
		String rentincomes=variablesMap.get("json_redout_current_str");
		JSONArray rentincome=new JSONArray(rentincomes);
		
		BigDecimal recesunmma= BigDecimal.ZERO; //银行存款
		String sunmma="";
		String accountdate=""; //会计处理日
		
		for(int i=0;i<rentincome.length();i++){
			JSONObject obj = rentincome.getJSONObject(i);
			String	 factdate=obj.getString("factdate");//zmq
			String accnumber=obj.getString("accnumber");
			String feetypename=obj.getString("feetypename");	//费用类型名称
			BigDecimal factmoney=new BigDecimal(obj.getString("factmoney"));//收款金额
			sunmma+="/"+feetypename;
			accountdate=obj.getString("accountingdate"); 
			recesunmma=recesunmma.add(factmoney);	
		
		//摘要
		String summay="合同号-"+contractnum+"-承租人-"+custname+"-收取-"+sunmma;
		//借 
		Map<String, String> mapj = new HashMap<String, String>();	
		Map<String,Object> subMap = new HashMap<String,Object>();
	    subMap.put("accNumber",accnumber);
	    List<OwnAccount> oas= this.baseService.findEntityByProperties(OwnAccount.class, subMap);
	    if(null!=oas&&oas.size()>0){
    		mapj.put("subjectsId", oas.get(0).getAccCode());   
    	}
		mapj.put("voucherSummary", summay); //摘要	
		mapj.put("debitMoney", Tools.formatNumberDoubleTwo(recesunmma.toString()));	//科目
		mapj.put("lenderMoney", "0");  //贷
		mapj.put("ebankFactDate",factdate);//网银日期
		mapj.put("accountDate",accountdate);	//会计到账日
		list.add(mapj);
	
		}
		
		for(int i=0;i<rentincome.length();i++){
			JSONObject obj = rentincome.getJSONObject(i);
			String feetypename=obj.getString("feetypename");	//费用类型name
			String feetype=obj.getString("feetype");	//费用类型
			String	 factdate=obj.getString("factdate");//zmq
			BigDecimal factmoney=new BigDecimal(obj.getString("factmoney"));//收款金额
			accountdate=obj.getString("accountingdate"); //会计到账日
			
			sunmma="合同号-"+contractnum+"-承租人-"+custname+"-"+feetypename;
			
			//贷
			Map<String, String> mapd = new HashMap<String, String>();
			mapd.put("voucherSummary", sunmma); //摘要			
			//DictionaryData dict=this.baseService.findEntityByID(DictionaryData.class, feetype);
			//String prop=dict.getPropThree();
			
			if(feetypename.equals("保证金")){
				mapd.put("subjectsId", "105");	//科目代码
			}else if(feetypename.equals("手续费")){
				 if(contractstatus<31){//起租前手续费
					 mapd.put("subjectsId", "106");//科目代码
				 }else{//起租后手续费
					 mapd.put("subjectsId", "107");//科目代码
				 }
			}else {
				mapd.put("subjectsId", "102");	//科目代码
			};
			
			/*if(feetypename.equals("首付款")){
				mapd.put("subjectsId", "109");	//科目代码
			}else if(feetypename.equals("保证金")){
				mapd.put("subjectsId", "101");	//科目代码
			}else {
				mapd.put("subjectsId", "102");	//科目代码
			};*/
			mapd.put("debitMoney", "0");
			mapd.put("lenderMoney",Tools.formatNumberDoubleTwo(factmoney.toString())); 
			mapd.put("ebankFactDate",factdate);
			mapd.put("accountDate", accountdate);
			list.add(mapd);
		}
		
		if (list != null && list.size() > 0) {
			voucherToV8Service.saveV8Message(headMap, list, currentUser);
		}
		
		
	}

	


	

	
	
	
}