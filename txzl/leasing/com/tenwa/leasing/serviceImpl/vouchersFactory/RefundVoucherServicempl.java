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
import com.tenwa.leasing.service.vouchersFactory.RefundVoucherService;
import com.tenwa.leasing.utils.Tools;

/**   
*    
* 项目名称：tls5.1   
* 类名称：RefundVoucherServicempl   
* 类描述：   
* 创建人：sunz   
* 创建时间：2016年6月12日 上午10:32:15   
* @version        
*/
@Service(value = "refundVoucherService")
public class RefundVoucherServicempl implements RefundVoucherService {

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="合同结束保证金处理";

	@Override
	public void createVoucherReceiveRefund(Map<String, String> variablesMap)
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
		String factdate=format.format(date); //网银到账日给当前系统时间
		
		//获得抵扣金明细全部信息
		String rentincomes=variablesMap.get("json_caution_money_refund_detail_str");
		JSONArray rentincome=new JSONArray(rentincomes);
		
		BigDecimal resunmma= BigDecimal.ZERO; //抵扣就金额
		String accountdate=""; //会计处理日
		for(int i=0;i<rentincome.length();i++){
			JSONObject obj = rentincome.getJSONObject(i);
			BigDecimal factmoney=new BigDecimal(obj.getString("factmoney"));//收款金额
			accountdate=obj.getString("accountingdate"); 
			resunmma=resunmma.add(factmoney);	
		}
		//摘要
		String summay="合同号-"+contractnum+"-承租人-"+custname+"-保证金抵扣(合同结束)-保证金抵扣";
		//借 
		Map<String, String> mapj = new HashMap<String, String>();		
		mapj.put("voucherSummary", summay); //摘要
		mapj.put("subjectsId", "101");	//科目代码
		mapj.put("debitMoney", Tools.formatNumberDoubleTwo(resunmma.toString()));	//科目
		mapj.put("lenderMoney", "0");  
		mapj.put("ebankFactDate",factdate);//网银日期
		mapj.put("accountDate",accountdate);	//会计到账日
		list.add(mapj);
	
		
		//贷
		Map<String, String> mapd = new HashMap<String, String>();
		mapd.put("voucherSummary", summay); //摘要
		mapd.put("subjectsId", "102");
		mapd.put("debitMoney", "0");
		mapd.put("lenderMoney",Tools.formatNumberDoubleTwo(resunmma.toString())); 
		mapd.put("ebankFactDate",factdate);//网银日期
		mapd.put("accountDate", accountdate);
		list.add(mapd);
	
		
		if(list!=null&&list.size()>0){
			voucherToV8Service.saveV8Message(headMap, list, currentUser);
		}
	}
	

	
	
	
}
