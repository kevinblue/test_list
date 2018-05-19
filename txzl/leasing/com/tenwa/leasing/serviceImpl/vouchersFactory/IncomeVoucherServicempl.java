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
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.FundPutVoucherService;
import com.tenwa.leasing.service.vouchersFactory.IncomeVoucherService;
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
@Service(value = "incomeVoucherService")
public class IncomeVoucherServicempl implements IncomeVoucherService {

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="咨询费收入";

	@Override
	public void createVoucherReceiveIncome(Map<String, String> variablesMap)
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
		
		
		//获得网银数据信息
		String ebankid=variablesMap.get("fund_ebank_data.id");
		FundEbankData fundebankdata=this.baseService.findEntityByID(FundEbankData.class,ebankid);
		String factdate=fundebankdata.getFactDate();	//网银到账时间

		//获得投放明细全部信息
		BigDecimal recesunmma= BigDecimal.ZERO; //应付账款
		String sunmma="";
		String accountdate="";
		String jsoncollectioncurrent=variablesMap.get("json_collection_current_str");
		JSONArray collectioncurrent=new JSONArray(jsoncollectioncurrent);
		for(int i=0;i<collectioncurrent.length();i++){
			JSONObject obj = collectioncurrent.getJSONObject(i);
			if(obj.getString("feetypename").equals("咨询费")){
				String feetype=obj.getString("feetypename");	//费用类型
				BigDecimal factmoney=new BigDecimal(obj.getString("factmoney"));//金额				
				accountdate=obj.getString("accountingdate");  //会计处理日
				recesunmma=recesunmma.add(factmoney);	
			}
		}
		//摘要
		String summay="合同号:"+contractnum+"-承租人:"+custname+"咨询服务费确认收入";
		
		//借 
		Map<String, String> mapj = new HashMap<String, String>();	
		mapj.put("voucherSummary", summay); //摘要
		mapj.put("subjectsId", "102");	//科目代码			
		mapj.put("debitMoney", Tools.formatNumberDoubleTwo(recesunmma.toString()));	//科目
		mapj.put("lenderMoney", "0");  //贷
		mapj.put("ebankFactDate",factdate);//网银日期
		mapj.put("accountDate",accountdate);	//会计到账日
		list.add(mapj);
	
		String taxrate="6";	//项目对应税率
		BigDecimal one=new BigDecimal("0.01").multiply(new BigDecimal(taxrate));
		BigDecimal two=BigDecimal.ONE.add(one);
		BigDecimal three=recesunmma.divide(two,2, BigDecimal.ROUND_HALF_UP); 
		BigDecimal four=one.multiply(three);	//销项税金	
		BigDecimal five=recesunmma.subtract(four); //一次确认不含税
				
		//贷
		Map<String, String> mape = new HashMap<String, String>();
		mape.put("voucherSummary", sunmma); //摘要		
		mape.put("subjectsId", "121");
		mape.put("debitMoney", "0");
		mape.put("lenderMoney",Tools.formatNumberDoubleTwo(five.toString())); 
		mape.put("ebankFactDate",factdate);
		mape.put("accountDate", accountdate);
		list.add(mape);
		
		//贷
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("voucherSummary", sunmma); //摘要		
		maps.put("subjectsId", "125");
		maps.put("debitMoney", "0");
		maps.put("lenderMoney",Tools.formatNumberDoubleTwo(four.toString())); 
		maps.put("ebankFactDate",factdate);
		maps.put("accountDate", accountdate);
		list.add(maps);		
			
		if(list!=null&&list.size()>0){
			voucherToV8Service.saveV8Message(headMap, list, currentUser);
		}
	}
	

	
	
	
}
