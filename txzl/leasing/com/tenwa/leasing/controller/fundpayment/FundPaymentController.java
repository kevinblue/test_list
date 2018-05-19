package com.tenwa.leasing.controller.fundpayment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundPaymentInterfaceLog;
import com.tenwa.leasing.entity.other.ExpressdeliverInfo;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;

@Controller(value = "fundPaymentController")
@RequestMapping(value = "/**/eleasing")
public class FundPaymentController {

	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "tableService")
	private TableService tableService; 
	
	@RequestMapping(value = "/sendPaymentDate.acl")
	@ResponseBody
	public String sendPaymentDate(HttpServletRequest request,HttpServletResponse response){	
		
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			
			String logid=model.get("logid");
			 return getJsonTrans(logid);
			
		}catch (Exception e) {		
			return "添加失败!["+e.getMessage()+"]";
		}
		
	}
	
	private  String   getJsonTrans(String logid)throws Exception{
		System.out.println("2222");
		String message="";
		ContractFundPaymentInterfaceLog cpf=this.tableService.findEntityByID(ContractFundPaymentInterfaceLog.class, logid);
		int ret=0;
		String sysDate=DateUtil.getSystemDateTime();//获取当前系统时间
		User user = (User) SecurityUtil.getPrincipal();
		if(cpf!=null){
			String alljson=cpf.getPaymentLog();
			JSONArray json = new JSONArray(alljson);
			JSONArray jsontrans=updateJsonAry(json, cpf);
			if("A".equals(cpf.getPaymentStatus())||"F".equals(cpf.getPaymentStatus())){
				//A表示正在处理中，F表示付款失败。此时传输数据就是更新数据
				ret=fundCommMethod.updateStrSql(jsontrans);//返回值是2或者0
			}
			if("1".equals(cpf.getPaymentStatus())){//1表示传输失败，此时为插入语句
				ret=fundCommMethod.insertStrSql(jsontrans);//返回值是1 或者0
			}
			
		}
		cpf.setModificator(user);
		cpf.setModifyDate(sysDate);		
		if(ret==0){
			cpf.setPaymentStatus("A");
			message="传送成功";//付款状态为正在处理中
		}else if(ret==1){
			cpf.setPaymentStatus("1");
			message="传送失败";//
		}else{
			cpf.setPaymentStatus("A");
			message="传送失败";//
		}
		this.tableService.updateEntity(cpf);
		return message;
	}
	private JSONArray updateJsonAry(JSONArray json,ContractFundPaymentInterfaceLog cpf) throws Exception{
		JSONArray array=new JSONArray();
		
		for(int i=0;i<json.length();i++){
			JSONObject  obj=json.getJSONObject(i);
			if(obj.getString("ERP_PAYMENT_ID").equals(cpf.getErpPaymentId())){
				obj.put("accnumber", cpf.getPaymentAccounts());
				obj.put("clientbankname",cpf.getDepositBankName());
				obj.put("clientaccount", cpf.getDepositAccountsName());
				obj.put("clientaccnumber", cpf.getDepositAccounts());
				obj.put("depositbanktype", cpf.getDepositBankType());
				obj.put("depositprovince", cpf.getDepositProvince());
				obj.put("depositcity", cpf.getDepositCity());
				obj.put("factmoney", cpf.getAmount());
				obj.put("clientbankno", cpf.getBankNumber());
				obj.put("unionclientbankno", cpf.getUnionBankNumber());
				obj.put("priorityflag", cpf.getPriorityFlag());
				array.put(obj);
			}
		}
		
		return array;
	}
	
	@RequestMapping(value = "/updateFundCharge.acl")
	@ResponseBody
	public String updateFundCharge(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{	
	
		
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			String  lastDate=model.get("lastDate");
			String  numuuid=model.get("numuuid");
			HashMap<String,Object> propertiesMap = new HashMap<String,Object>();
			propertiesMap.put("numUuid", numuuid);
			List<ContractFundFundCharge> chargelist =this.tableService.findEntityByProperties(ContractFundFundCharge.class, propertiesMap);
			chargelist.get(0).setFactDate(lastDate);
			this.tableService.updateEntity(chargelist.get(0));
			return "成功";
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
	}
}
