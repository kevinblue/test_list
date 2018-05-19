package com.tenwa.leasing.serviceImpl.vouchersFactory;

import java.math.BigDecimal;
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

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.ContractOnhireVoucherService;
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
@Service(value = "ContractOnhireVoucherService")
public class ContractOnhireVoucherServicempl implements ContractOnhireVoucherService {

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;	
	private static String modlename="合同起租流程";
	private static String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	@Override
	public void createVoucherContractOnhire(Map<String, String> variablesMap)
			throws Exception {
		User currentUser = SecurityUtil.getPrincipal();
		String contractid = variablesMap.get("contractid"); 
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
		//获得投放明细全部信息
		BigDecimal sumrent          = BigDecimal.ZERO; //租金总额
		BigDecimal suminterest      = BigDecimal.ZERO; //租金总额
		BigDecimal firstpayment     = BigDecimal.ZERO; //首付款
		BigDecimal nominalprice     = BigDecimal.ZERO; //期末购买权
		//起租凭证1借3贷
		BigDecimal leaseAssets      = BigDecimal.ZERO; //融资租赁资产
		BigDecimal otherReceive     = BigDecimal.ZERO; //其他应收款
		BigDecimal equip            = BigDecimal.ZERO; //设备款
		BigDecimal longtermreceive  = BigDecimal.ZERO; //长期应收款
		BigDecimal unrealizedIncome = BigDecimal.ZERO; //未实现融资租赁收益
		BigDecimal unrealizedtax    = BigDecimal.ZERO; //未确认融资租赁税金
		
		equip = new BigDecimal(variablesMap.get("equipamt"));
		String jsonrenplan=variablesMap.get("json_fund_rent_plan_str");
		JSONArray renplan=new JSONArray(jsonrenplan);
		for(int i=0;i<renplan.length();i++){
			JSONObject obj = renplan.getJSONObject(i);
			sumrent = sumrent.add(new BigDecimal(obj.getString("rent")));
			suminterest = suminterest.add(new BigDecimal(obj.getString("interest")));
		}
		firstpayment = new BigDecimal(variablesMap.get("firstpayment"));
		nominalprice = new BigDecimal(variablesMap.get("nominalprice"));
		
		
		longtermreceive = sumrent.add(firstpayment).add(nominalprice);
		//回租直租 税率不同
		if("lease_form1".equals(variablesMap.get("contract_info.leasform"))
				||"lease_form3".equals(variablesMap.get("contract_info.leasform"))){
			//直租
			/*unrealizedtax = (suminterest.add(nominalprice)).divide(BigDecimal.valueOf(1.17)).multiply(BigDecimal.valueOf(0.17))
					.setScale(2, BigDecimal.ROUND_HALF_UP);
			leaseAssets = equip.divide(BigDecimal.valueOf(1.17)).setScale(2, BigDecimal.ROUND_HALF_UP);*/
			
			unrealizedtax = (suminterest.add(nominalprice)).divide(BigDecimal.valueOf(1.17),2, BigDecimal.ROUND_HALF_UP)
					.multiply(BigDecimal.valueOf(0.17));
			leaseAssets = equip.divide(BigDecimal.valueOf(1.17),2, BigDecimal.ROUND_HALF_UP);
			
			otherReceive = equip.subtract(leaseAssets);
		}else{
//			unrealizedtax = suminterest.add(nominalprice).divide(BigDecimal.valueOf(1.06)).multiply(BigDecimal.valueOf(0.06))
//					.setScale(2, BigDecimal.ROUND_UP); 
			
			unrealizedtax = suminterest.add(nominalprice).divide(BigDecimal.valueOf(1.06),2, BigDecimal.ROUND_UP)
					.multiply(BigDecimal.valueOf(0.06));
			
			leaseAssets = equip;
		}
		
		unrealizedIncome = longtermreceive.subtract(unrealizedtax).subtract(leaseAssets);
		
		//proj_type2 集团外 proj_type1 集团内
		String projtype = (null == contractInfo.getProjId().getProjType())?
				"proj_type2":contractInfo.getProjId().getProjType().getId();
		
		//摘要
		String summay1 = "合同号:"+contractnum+"-承租人:"+custname+"-设备价格:"+equip;
		String summay2 = "合同号:"+contractnum+"-承租人:"+custname+"-设备价格:"+equip+"-进项税:"+otherReceive;
//		String summay="合同号:"+contractnum+"-承租人:"+custname +"租金总额:"+ String.valueOf(sumrent)
//				+"首付款:"+String.valueOf(firstpayment)+"留购价:"+String.valueOf(nominalprice);
		//租赁资产入账
		/*租赁资产入账		
		 *借		融资租赁资产 
		 *	 	其他应收款-其他-其他应收款-其他-其他 
		 *	贷	应付账款 
		 * 		应付账款 
		 *起租
		 *借		融资租赁资产
		 *		其他应收款-其他-其他应收款-其他-其他
		 *	贷	应付账款	
		 *		应付账款
		 */
		
		Map<String, String> map1 = new HashMap<String, String>();	
		map1.put("voucherSummary", summay1); //摘要
		map1.put("subjectsId", "103");
		map1.put("debitMoney", Tools.formatNumberDoubleTwo(leaseAssets.toString()));	//科目
		map1.put("lenderMoney", "0");  //贷
		map1.put("ebankFactDate",currentDate);//网银日期
		map1.put("accountDate",currentDate);	//会计到账日
		list.add(map1);
		if(otherReceive.compareTo(BigDecimal.ZERO)>0){
			Map<String, String> map2 = new HashMap<String, String>();	
			map2.put("voucherSummary", summay2);
			map2.put("subjectsId", "104");
			map2.put("debitMoney", Tools.formatNumberDoubleTwo(otherReceive.toString()));	
			map2.put("lenderMoney", "0");  
			map2.put("ebankFactDate",currentDate);
			map2.put("accountDate",currentDate);	
			list.add(map2);
		}
		
		Map<String, String> map3 = new HashMap<String, String>();	
		map3.put("voucherSummary", summay1);
		//区分集团内集团外对应科目不同
		if("proj_type2".equals(projtype)){
			map3.put("subjectsId", "106");
		}else{
			map3.put("subjectsId", "105");
		}
		map3.put("debitMoney", "0");	
		map3.put("lenderMoney", Tools.formatNumberDoubleTwo(equip.toString()));  
		map3.put("ebankFactDate",currentDate);
		map3.put("accountDate",currentDate);	
		list.add(map3);
		
		
		//1借3贷
		String summayallrent = "合同号:"+contractnum+"-承租人:"+custname +"租金总额:"+ String.valueOf(sumrent)
				+"首付款:"+String.valueOf(firstpayment)+"留购价:"+String.valueOf(nominalprice);
		Map<String, String> map4 = new HashMap<String, String>();	
		map4.put("voucherSummary", summayallrent);
		map4.put("subjectsId", "109");
		map4.put("debitMoney", Tools.formatNumberDoubleTwo(longtermreceive.toString()));	
		map4.put("lenderMoney", "0");  
		map4.put("ebankFactDate",currentDate);
		map4.put("accountDate",currentDate);	
		list.add(map4);
		
		String summay3 = "合同号:"+contractnum+"-承租人:"+custname+"-流程名:"+modlename;
		Map<String, String> map5 = new HashMap<String, String>();	
		map5.put("voucherSummary", summay1);
		map5.put("subjectsId", "103");
		map5.put("debitMoney", "0");	
		map5.put("lenderMoney", Tools.formatNumberDoubleTwo(leaseAssets.toString()));  
		map5.put("ebankFactDate",currentDate);
		map5.put("accountDate",currentDate);	
		list.add(map5);
		
		Map<String, String> map6 = new HashMap<String, String>();	
		map6.put("voucherSummary", summay3);
		map6.put("subjectsId", "111");
		map6.put("debitMoney", "0");	
		map6.put("lenderMoney", Tools.formatNumberDoubleTwo(unrealizedIncome.toString()));  
		map6.put("ebankFactDate",currentDate);
		map6.put("accountDate",currentDate);	
		list.add(map6);
		
		Map<String, String> map7 = new HashMap<String, String>();	
		map7.put("voucherSummary", summay3);
		map7.put("subjectsId", "112");
		map7.put("debitMoney", "0");	
		map7.put("lenderMoney", Tools.formatNumberDoubleTwo(unrealizedtax.toString()));  
		map7.put("ebankFactDate",currentDate);
		map7.put("accountDate",currentDate);	
		list.add(map7);
		
		if (list != null && list.size() > 0) {
			voucherToV8Service.saveEvidenceInfo(headMap, list, currentUser);
		}
		
		
	}
	

	
	
	
}
