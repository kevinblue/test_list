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

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.FundPutVoucherService;
import com.tenwa.leasing.utils.Tools;

/**   
*    
* 项目名称：tls5.1   
* 类名称：ReceivablesVoucherServicempl   
* 类描述：   
* 创建人：sunz   
* 创建时间：2016年6月12日 上午10:32:15   
* @version        
*/
@Service(value = "fundPutVoucherService")
public class FundPutVoucherServicempl implements FundPutVoucherService {

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="投放流程";

	@Override
	public void createVoucherReceiveFundPut(Map<String, String> variablesMap)
			throws Exception {
		User currentUser = SecurityUtil.getPrincipal();
		String contractid = variablesMap.get("contract_info.id"); 
		ContractInfo contractInfo=this.baseService.findEntityByID(ContractInfo.class, contractid);
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
		
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		//获得网银数据信息

		//获得投放明细全部信息
		BigDecimal recesunmma= BigDecimal.ZERO; //应付账款
		String sunmma="";
		String feetype="";//费用类型
		String customertype="";//客户类型
		String accountdate=""; //会计处理日
		String jsonfundput=variablesMap.get("json_put_current_str");
		JSONArray fundput=new JSONArray(jsonfundput);
		for(int i=0;i<fundput.length();i++){
			JSONObject obj = fundput.getJSONObject(i);
			if(obj.getString("settlemethodname").equals("电汇")){
				customertype=obj.getString("customertypename");
				accountdate=obj.getString("accountingdate");
				feetype=obj.getString("feetypename");	//费用类型
				BigDecimal factmoney=new BigDecimal(obj.getString("factmoney"));//收款金额				
				accountdate=obj.getString("accountingdate"); 
				sunmma+="-"+feetype+":"+factmoney;
				recesunmma=recesunmma.add(factmoney);	
			}
		}
		String jsoncautionmoney=variablesMap.get("json_caution_money_refund_detail_str");
		JSONArray cautionmoney=new JSONArray(jsoncautionmoney);
		for(int i=0;i<cautionmoney.length();i++){
			JSONObject cat = cautionmoney.getJSONObject(i);
			feetype=cat.getString("feetypename");	//费用类型
			BigDecimal factmoney=new BigDecimal(cat.getString("factmoney"));//收款金额	
			accountdate=cat.getString("accountingdate"); 			
			sunmma+="-"+feetype+":"+factmoney;
			recesunmma=recesunmma.add(factmoney);	
		}
		//摘要
		String summay="合同号:"+contractnum+"-承租人:"+custname+sunmma;
		
		//借 
		Map<String, String> mapj = new HashMap<String, String>();	
		mapj.put("voucherSummary", summay); //摘要
		if(customertype.equals("集团内")){
			mapj.put("subjectsId", "105");	//科目代码			
		}else{
			mapj.put("subjectsId", "106");
		}
		mapj.put("debitMoney", Tools.formatNumberDoubleTwo(recesunmma.toString()));	//科目
		mapj.put("lenderMoney", "0");  //贷
		if(accountdate.equals("")){
			accountdate=format.format(date); //当前系统时间
		}
		mapj.put("ebankFactDate",accountdate);//网银日期
		mapj.put("accountDate",accountdate);	//会计到账日
		list.add(mapj);
	
		for(int i=0;i<fundput.length();i++){
			JSONObject obj = fundput.getJSONObject(i);
			if(obj.getString("settlemethodname").equals("电汇")){
				feetype=obj.getString("feetypename");	//费用类型
				BigDecimal factmoney=new BigDecimal(obj.getString("factmoney"));//收款金额				
				accountdate=obj.getString("accountingdate"); 
				sunmma="合同号:"+contractnum+"-承租人:"+custname+"-"+feetype+":"+factmoney;
				//贷
				Map<String, String> mape = new HashMap<String, String>();
				mape.put("voucherSummary", sunmma); //摘要		
				mape.put("subjectsId", "122");
				mape.put("debitMoney", "0");
				mape.put("lenderMoney",Tools.formatNumberDoubleTwo(factmoney.toString())); 
				if(accountdate.equals("")){
					accountdate=format.format(date); //当前系统时间
				}
				mape.put("ebankFactDate",accountdate);
				mape.put("accountDate", accountdate);
				list.add(mape);
			}
		}
		
		for(int i=0;i<cautionmoney.length();i++){
			JSONObject obj = cautionmoney.getJSONObject(i);
			String feetypename=obj.getString("feetypename");	//费用类型name
			String feetype1=obj.getString("feetype");	//费用类型
			BigDecimal factmoney=new BigDecimal(obj.getString("factmoney"));//收款金额
			accountdate=obj.getString("accountingdate"); //会计到账日
			sunmma="合同号:"+contractnum+"-承租人:"+custname+"-"+feetypename+":"+factmoney;
			
			//贷
			Map<String, String> mapd = new HashMap<String, String>();
			mapd.put("voucherSummary", sunmma); //摘要			
			if(feetype1.equals("feetype5")){
				mapd.put("subjectsId", "109");	//科目代码				
			}else if(feetype1.equals("feetype1")||feetype1.equals("feetype3")){
				mapd.put("subjectsId", "102");
			}else if(feetype1.equals("feetype2")){
				mapd.put("subjectsId", "101");
			}else if(feetype1.equals("feetype6")){
				mapd.put("subjectsId", "120");
			}else{
				mapd.put("subjectsId", "122");
			}
			mapd.put("debitMoney", "0");
			mapd.put("lenderMoney",Tools.formatNumberDoubleTwo(factmoney.toString())); 
			if(accountdate.equals("")){
				accountdate=format.format(date); //当前系统时间
			}
			mapd.put("ebankFactDate",accountdate);
			mapd.put("accountDate", accountdate);
			list.add(mapd);
		}
		
		if(list!=null&&list.size()>0){
			voucherToV8Service.saveV8Message(headMap, list, currentUser);
		}
	}
	

	
	
	
}
