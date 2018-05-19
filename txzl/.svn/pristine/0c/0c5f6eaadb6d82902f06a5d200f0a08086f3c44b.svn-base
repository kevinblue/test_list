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

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.RentIncomeVoucherService;
import com.tenwa.leasing.utils.Tools;

/**   
*    
* 项目名称：tls5.1   
* 类名称：FundCollectionVoucherServiceImpl   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月9日 下午3:38:15   
* @version        
*/
@Service(value = "rentIncomeVoucherService")
public class RentIncomeVoucherServicempl implements RentIncomeVoucherService {

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="租金回笼流程";

	@Override
	public void createVoucherReceiveRent(Map<String, String> variablesMap)
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
		//获得租金明细全部信息
		String rentincomes=variablesMap.get("json_rent_income_detail_str");
		JSONArray rentincome=new JSONArray(rentincomes);
		
		BigDecimal rentsunmma= BigDecimal.ZERO;
		String sunmma="";
		String accountdate="";
		for(int i=0;i<rentincome.length();i++){
			JSONObject obj = rentincome.getJSONObject(i);
			BigDecimal rent=new BigDecimal(obj.getString("rent"));//每期回笼租金
			String planlist=obj.getString("planlist"); //期项
			sunmma+="第"+planlist+"期租金"+rent.toString()+"元";
			accountdate=obj.getString("accountingdate"); //会计到账日
			rentsunmma=rentsunmma.add(rent);	//银行存款
		}
		//摘要
		String summay="合同号："+contractnum+",承租人:"+custname+",收取："+rentsunmma+"元,"+sunmma;
		//借 
		Map<String, String> mapj = new HashMap<String, String>();	
		
		mapj.put("voucherSummary", summay); //摘要
		mapj.put("subjectsId", "101");	//科目代码
		mapj.put("debitMoney", Tools.formatNumberDoubleTwo(rentsunmma.toString()));	//科目
		mapj.put("lenderMoney", "0");  //贷
		mapj.put("ebankFactDate",factdate);//网银日期
		mapj.put("accountDate",accountdate);	//会计到账日
		list.add(mapj);
	
		
		for(int i=0;i<rentincome.length();i++){
			JSONObject obj = rentincome.getJSONObject(i);
			BigDecimal rent=new BigDecimal(obj.getString("rent"));//每期回笼租金
			String planlist=obj.getString("planlist");
			accountdate=obj.getString("accountingdate"); //会计到账日
			
			sunmma="合同号："+contractnum+",承租人:"+custname+"第"+planlist+"期租金"+rent.toString()+"元";
			
			//贷
			Map<String, String> mapd = new HashMap<String, String>();
			mapd.put("voucherSummary", sunmma); //摘要
			mapd.put("subjectsId", "102");
			mapd.put("debitMoney", "0");
			mapd.put("lenderMoney",Tools.formatNumberDoubleTwo(rent.toString())); 
			mapd.put("ebankFactDate",factdate);
			mapd.put("accountDate", accountdate);
			list.add(mapd);
		}
		
		if(list!=null&&list.size()>0){
			voucherToV8Service.saveV8Message(headMap, list, currentUser);
		}
	}
}
