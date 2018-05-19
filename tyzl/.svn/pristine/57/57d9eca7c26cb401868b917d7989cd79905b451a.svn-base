package com.tenwa.leasing.serviceImpl.fund.fundcomm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;

@Service(value="fundCommMethod")
public class FundCommMethodImpl implements FundCommMethod {

	@Resource(name = "tableService")
	private TableService tableService; 
	
	Logger logger =Logger.getLogger(FundCommMethodImpl.class);

	
	@Override
	public void initFundFundCharge(String fieldIput,Map<String, String> variablesMap,Map<String, String> queryMainObjectMap) throws Exception {
		String json_put_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_charge_list.xml", queryMainObjectMap).toString();
		System.out.println("json_put_plan_str:"+json_put_plan_str);
		if ( json_put_plan_str.length() > 0 && null != json_put_plan_str)
		{
			variablesMap.put(fieldIput,json_put_plan_str);
		}		
	}

	@Override
	public void initFundFundPlan(String fieldIput,Map<String, String> variablesMap,	Map<String, String> queryMainObjectMap) throws Exception {
		String json_put_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_plan_list.xml", queryMainObjectMap).toString();
		System.out.println("json_put_plan_str:"+json_put_plan_str);
		if ( json_put_plan_str.length() > 0 && null != json_put_plan_str)
		{
			variablesMap.put(fieldIput,json_put_plan_str);
		} 		
	}

	@Override
	public void saveFundFundCharge(String fieldIput,Map<String, String> variablesMap) throws Exception {
		 String  contractid=variablesMap.get("contract_id");
		 ContractInfo contractInfo=this.tableService.findEntityByID(ContractInfo.class, contractid);
		 String json_current_str = variablesMap.get(fieldIput);
		 Map<String,String> filedMap=new HashMap<String,String>();
		 filedMap.put("DictionaryData", "id");
		 filedMap.put("ContractFundFundPlan", "id");
		 filedMap.put("FundEbankData", "ebdataId");
		  if(null!=json_current_str&&json_current_str.length()>0 )
		  {
		 this.tableService.updateOneToManyCollectionsNoRemoved(contractInfo,"fundFundCharges", ContractFundFundCharge.class, "contractId",json_current_str, filedMap);
		  }	
	}

	@Override
	public FundEbankData initFundEbankInfo(Map<String, String> variablesMap)		throws Exception {
		String  ebankId = variablesMap.get("ebank_id");
		FundEbankData fundEbankData = this.tableService.findEntityByID(FundEbankData.class,ebankId);
		if ( null != fundEbankData )
		{
			fundEbankData.initFundEbank();
			variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(fundEbankData,null, "fund_ebank_data"));
			return fundEbankData;
		}
		return null;
	}

	@Override
	public <T> void saveFundRebackFlag(String fieldIput,Map<String, String> variablesMap) throws Exception {
		String jsonFundString = variablesMap.get(fieldIput);
		JSONArray jsonArray = new JSONArray(jsonFundString);
		String pid="";
		List<ContractFundFundCharge>fundcharges=new ArrayList<ContractFundFundCharge>();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			if(jsonObj.has("pid")){
				pid=jsonObj.getString("pid");
				if(null!=pid &&(! "".equals(pid))){
					ContractFundFundCharge cf=this.tableService.findEntityByID(ContractFundFundCharge.class, pid);
				    cf.setRoll_Back("1");
				    fundcharges.add(cf);
					
				}
			}
		}	
		if(fundcharges.size()>0){
			this.tableService.updateAllEntities(fundcharges);
		}
		
	}
	@Override
	public <T> void saveRentRebackFlag(String fieldIput,Map<String, String> variablesMap) throws Exception {
		String jsonFundString = variablesMap.get(fieldIput);
		JSONArray jsonArray = new JSONArray(jsonFundString);
		String pid="";
		List<ContractFundRentInCome>fundRents=new ArrayList<ContractFundRentInCome>();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			if(jsonObj.has("pid")){
				pid=jsonObj.getString("pid");
				if(null!=pid &&(! "".equals(pid))){
					ContractFundRentInCome cf=this.tableService.findEntityByID(ContractFundRentInCome.class, pid);
				    cf.setRollBack("1");
				    fundRents.add(cf);
				}
			}
		}	
		if(fundRents.size()>0){
			this.tableService.updateAllEntities(fundRents);
		}
		
	}

	@Override
	public <T> void saveEbankProcessFlow(String contractid,FundEbankData fundBank,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
          FundEbankProcess fundEbankProcess = new FundEbankProcess();
	      fundEbankProcess.setContractId(contractid);
	      fundEbankProcess.setEbdataId(fundBank);
	      fundEbankProcess.setFlowUnid(jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"");
	      fundEbankProcess.setProcessName(jbpmWorkflowHistoryInfo.getWorkflowDisplayName());
	      fundEbankProcess.setStartDate(DateUtil.getSystemDateTime());
	      fundEbankProcess.setWork_flag("0");
	      fundEbankProcess.setProcessAmount(BigDecimal.ZERO);
	      this.tableService.saveEntity(fundEbankProcess);
	      variablesMap.put("fund_ebank_process", fundEbankProcess.getId());
	}

	@Override
	public <T> void updateEbankProcessFlow(String tablename,List<String> sumFields,Map<String, String> variablesMap) throws Exception {
	  //取出本次核销的金额并保存到网银过程表中	
	  BigDecimal sum = BigDecimal.ZERO;
	  JSONArray jsonArr = new JSONArray(variablesMap.get(tablename));
	  for(int i=0;i<jsonArr.length();i++){
		  JSONObject jsonObj = jsonArr.getJSONObject(i);
		  for(int j=0;j<sumFields.size();j++){
		    sum=sum.add(new BigDecimal(jsonObj.getDouble(sumFields.get(j))));
		  }
		}
	   FundEbankProcess fundEbankProcess = (FundEbankProcess) this.tableService.findEntityByID(FundEbankProcess.class, variablesMap.get("fund_ebank_process"));
	 
	   fundEbankProcess.setProcessAmount(sum);
	   this.tableService.updateEntity(fundEbankProcess);		
	}

	@Override
	public <T> void deleteEbankProcessFlow(Map<String, String> variablesMap) throws Exception {
		  FundEbankProcess fundEbankProcess = (FundEbankProcess) this.tableService.findEntityByID(FundEbankProcess.class, variablesMap.get("fund_ebank_process"));
          if(fundEbankProcess!=null){
		  this.tableService.removeEntity(fundEbankProcess);	
          }
	}
	@Override
	public String getEbankInfoToCheck(Map<String, String> variablesMap)	throws Exception {
		Map<String, String> queryMainObjectMap=new HashMap<String,String>();
		queryMainObjectMap.put("ebankid", variablesMap.get("ebankid").toString());
		queryMainObjectMap.put("flowunid", variablesMap.get("flowunid").toString());
		String json_fund_info = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_ebank_check.xml", queryMainObjectMap).toString();
		return json_fund_info;
	}

	@Override
	public String getFundPlanInfoToCheck(Map<String, String> variablesMap)	throws Exception {
		Map<String, String> queryMainObjectMap=new HashMap<String,String>();
		queryMainObjectMap.put("ids", variablesMap.get("fundplandids").toString());
		String json_fund_info = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_plan_check.xml", queryMainObjectMap).toString();
		return json_fund_info;
	}


	@Override
	public void initFundRentIncome(String fieldIput,Map<String, String> vriablesMap,Map<String,String>  queryMainObjectMap) throws Exception {
		String json__plan_str = this.tableService.getJsonArrayData("eleasing/workflow/rent/rent_comm/rent_income.xml", queryMainObjectMap).toString();
		System.out.println("json__plan_str:"+json__plan_str);
		if ( json__plan_str.length() > 0 && null != json__plan_str)
		{
			vriablesMap.put(fieldIput,json__plan_str);
		} 		
		
	}


	@Override
	public void initFundRentPlan(String fieldIput,Map<String, String> vriablesMap,Map<String,String>  queryMainObjectMapp) throws Exception {
		String json__plan_str = this.tableService.getJsonArrayData("eleasing/workflow/rent/rent_comm/rent_plan.xml", queryMainObjectMapp).toString();
		System.out.println("json__plan_str:"+json__plan_str);
		if ( json__plan_str.length() > 0 && null != json__plan_str)
		{
			vriablesMap.put(fieldIput,json__plan_str);
		} 	
		
	}


	@Override
	public void saveFundRentIncome(String fieldIput,Map<String, String> vriablesMap) throws Exception {
		 String  contractid=vriablesMap.get("contract_id");
		 ContractInfo contractInfo=this.tableService.findEntityByID(ContractInfo.class, contractid);
		 String json_current_str = vriablesMap.get(fieldIput);
		 Map<String,String> filedMap=new HashMap<String,String>();
		  if(null!=json_current_str&&json_current_str.length()>0 )
		  {
		 this.tableService.updateOneToManyCollectionsNoRemoved(contractInfo,"contractFundRentIncomes", ContractFundRentInCome.class, "contractId",json_current_str, filedMap);
		  }	
		
	}


	@Override
	public String getRentPlanInfoToCheck(Map<String, String> variablesMap)throws Exception {
		Map<String, String> queryMainObjectMap=new HashMap<String,String>();
		queryMainObjectMap.put("ids", variablesMap.get("rentplandids").toString());
		if(variablesMap.containsKey("plan_date")){
		  queryMainObjectMap.put("plan_date", variablesMap.get("plan_date").toString());
		}else{
		  queryMainObjectMap.put("plan_date",DateUtil.getSystemDate());
		}
		System.out.println(queryMainObjectMap);
		String json_fund_info = this.tableService.getJsonArrayData("eleasing/workflow/rent/rent_comm/rent_plan.xml", queryMainObjectMap).toString();
		return json_fund_info;
		 
	} 
	
}
