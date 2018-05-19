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
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
/**   
*    
* 项目名称guowant  
* 类名称：FundCollectionVoucherServiceImpl   
* 类描述：   
* 创建人：zhongqm   
* 创建时间：2016年06月2日 下午2:50:15   
* @version        
*/
import com.tenwa.leasing.service.vouchersFactory.InputTaxVoucherService;
import com.tenwa.leasing.utils.Tools;
@Service(value = "inputTaxVoucher")
public class InputTaxVoucherServiceImpl implements InputTaxVoucherService{

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="进项税认证凭证";
	@Override
	public void createInputTaxVoucher(Map<String, String> model)
			throws Exception {
		String json = model.get("rowdata");
		
		JSONArray jsonarr = new JSONArray(json);
		JSONObject jsonobj = new JSONObject();
		//当前时间
		String currentDate = DateUtil.getSystemDate();
		//当前用户
		User currentUser = SecurityUtil.getPrincipal();
		Map<String, String> headMap = new HashMap<String, String>();
		//合同ID
		String contractid ="";	
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		//发票金额
		BigDecimal invoicemoneyb   = BigDecimal.ZERO;
		//税额
		BigDecimal taxmoneyb = BigDecimal.ZERO;
		//合同号
		String contractnum = "";
		//发票号
		String invoicenum = "";
		//供应商ID
		String supplier = "";
		List<String> invoicelist = new ArrayList<String>();
		for(int i = 0;i<jsonarr.length();i++){	
				jsonobj = jsonarr.getJSONObject(i);	
				//合同号
			    contractnum = jsonobj.getString("contractinfo").substring(0,jsonobj.getString("contractinfo").indexOf(":"));
                //发票金额合计
			    String invoice = jsonobj.getString("invoicemoney");
			    if(invoice!=""){
			    	 BigDecimal invoicemoney = new BigDecimal(invoice);
					 invoicemoneyb = invoicemoneyb.add(invoicemoney);
			    }
			    //税额合计
			    String tax = jsonobj.getString("taxmoney");
                	BigDecimal taxmoney = new BigDecimal(tax);
				    taxmoneyb = taxmoneyb.add(taxmoney);
			    //发票号
			    String invoiceno = jsonobj.getString("invoiceno");
			    invoicelist.add(invoiceno);	   
			    //供应商ID
			    supplier = jsonobj.getString("supplier");
		}
		Map<String,Object> propertiesMap = new HashMap<String,Object>();
		propertiesMap.put("contractNumber", contractnum);
		ContractInfo contractinfo = this.baseService.findEntityByProperties(ContractInfo.class, propertiesMap).get(0);
		if(contractinfo.getId()!=null){
    	   headMap.put("contract_id", contractinfo.getId());
        }
		//发票号拼接
        invoicenum = invoicelist.get(0)+"-"+invoicelist.get(invoicelist.size()-1);
        //进项税认证凭证
      	headMap.put("moduleName", modlename);   
        //借 1461
        Map<String, String> map1 = new HashMap<String, String>();
        String summary1 ="购供应商设备(合同号:"+contractnum+")发票号"+invoicenum+"设备价格"+invoicemoneyb.subtract(taxmoneyb)+"元";
		map1.put("voucherSummary", summary1);
		map1.put("subjectsId","100");
	    map1.put("debitMoney", Tools.formatNumberDoubleTwo(invoicemoneyb.subtract(taxmoneyb).toString()));
		map1.put("lenderMoney", "0"); 	
	    map1.put("ebankFactDate", currentDate);	
		map1.put("accountDate", currentDate);
		map1.put("F19", supplier);
		list.add(map1);
        //贷  2221.01.01
		Map<String, String> map2 = new HashMap<String, String>();
        String summary2 ="购供应商设备(合同号:"+contractnum+")发票号"+invoicenum+"进项税"+taxmoneyb+"元";
		map2.put("voucherSummary", summary2);
		map2.put("subjectsId","101");
	    map2.put("debitMoney", Tools.formatNumberDoubleTwo(taxmoneyb.toString()));
		map2.put("lenderMoney", "0"); 	
	    map2.put("ebankFactDate", currentDate);	
		map2.put("accountDate", currentDate);
		map2.put("F19", supplier);
		list.add(map2);
        //贷 
		Map<String, String> map3 = new HashMap<String, String>();
        String summary3 ="购供应商设备(合同号:"+contractnum+")发票号"+invoicenum+"应付"+invoicemoneyb+"元";
		map3.put("voucherSummary", summary3);
		map3.put("subjectsId","102");
	    map3.put("debitMoney", "0");
		map3.put("lenderMoney",Tools.formatNumberDoubleTwo(invoicemoneyb.toString()) ); 	
	    map3.put("ebankFactDate", currentDate);	
		map3.put("accountDate", currentDate);
		map3.put("F19", supplier);
		list.add(map3);
		if (list != null && list.size() > 0) {
			voucherToV8Service.saveV8Message(headMap, list, currentUser);	
		}
		
	}

}
