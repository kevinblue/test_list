package com.tenwa.leasing.serviceImpl.fund.fundcomm;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.reckon.entity.contract.reckon.fund.ContractPaymentPremiseCondition;
import com.tenwa.business.entity.BaseMessage;
import com.tenwa.business.entity.BaseMessageToUser;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.contract.ContractDocList;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundPaymentInterfaceLog;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.paymentinterface.PaymentLog;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.DataBaseManager;

@Service(value="fundCommMethod")
public class FundCommMethodImpl implements FundCommMethod {

	@Resource(name = "tableService")
	private TableService tableService; 
	
	Logger logger =Logger.getLogger(FundCommMethodImpl.class);

	@Override
	public void initFundEquipDetail(String fieldIput,
			Map<String, String> variablesMap,
			Map<String, String> queryMainObjectMap) throws Exception {
		String json_contract_equip_detail_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_equip_detail_list.xml", queryMainObjectMap).toString();
		System.out.println("json_contract_equip_detail_str:"+json_contract_equip_detail_str);
		if ( json_contract_equip_detail_str.length() > 0 && null != json_contract_equip_detail_str)
		{
			variablesMap.put(fieldIput,json_contract_equip_detail_str);
		}
		
	}
	
	@Override
	public void initFundFundCharge(String fieldIput,Map<String, String> variablesMap,Map<String, String> queryMainObjectMap) throws Exception {
		String json_put_plan_str;
		if(fieldIput=="json_put_his_str"){//已付款明细
			json_put_plan_str= this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_charge_plan_list.xml", queryMainObjectMap).toString();
		}else{
			json_put_plan_str= this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_charge_his_list.xml", queryMainObjectMap).toString();
		}
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
		 String json_current_str_tamp1 = variablesMap.get(fieldIput);
		 String json_current_str_tamp2 = json_current_str_tamp1.replaceAll("payobj", "factobject");//付款对象
		 json_current_str_tamp1=null;
		 String json_current_str_tamp3 = json_current_str_tamp2.replaceAll("otherbank", "clientBank");//对方银行
		 json_current_str_tamp2=null;
		 String json_current_str = json_current_str_tamp3.replaceAll("clientname", "clientAccount");//对方账户
		 json_current_str_tamp3=null;
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
			fundEbankData.setFactDate(fundEbankData.getFactDate()==""?"":fundEbankData.getFactDate().substring(0, 10));
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
		 if(null!=fundEbankProcess){
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
	public void initFundRentFundPlan(String fieldIput,Map<String, String> vriablesMap,Map<String,String>  queryMainObjectMap) throws Exception {
		System.out.println("1111");
		String json__plan_str = this.tableService.getJsonArrayData("eleasing/workflow/rent/rent_comm/rentfund_plan.xml", queryMainObjectMap).toString();
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

	@Override
	public void saveFundPutFileList(Map<String, String> variablesMap) throws Exception {
		String  contractid=variablesMap.get("contract_id");
		ContractInfo contractInfo=this.tableService.findEntityByID(ContractInfo.class, contractid);
		String fileJson = variablesMap.get("json_fund_put_file_str");
		List<ContractDocList> list = new ArrayList<ContractDocList>();
		if (list.size()>0){
			JSONObject json = new JSONObject(fileJson);
			JSONArray tableArray = json.getJSONArray("sheets_data").getJSONObject(0).getJSONArray("table_data");
			for(int i=0;i<tableArray.length();i++){
				JSONObject tabledata = tableArray.getJSONObject(i);
				String docType = tabledata.getString("name");
				JSONArray dataArray = tabledata.getJSONArray("datas");
				for(int j=0;j<dataArray.length();j++){
					ContractDocList doc = new ContractDocList();
					JSONObject datajson = dataArray.getJSONObject(j);
					doc.setDocNo(datajson.get("docno").toString());
					doc.setDocNum(datajson.get("docnum").toString());
					doc.setDocMemo(datajson.get("memo").toString());
					doc.setIsSubmit(datajson.get("issubmit").toString());
					doc.setPageNum(datajson.get("pagenum").toString());
					doc.setDocname(datajson.get("docname").toString());
					doc.setDocType(docType);
					doc.setContractId(contractInfo);
					doc.setWorkflowType("2");//放款资料清单
					list.add(doc);
				}
			}
			this.tableService.saveAllEntities(list);
		}
	}

	@Override
	public void updateCustInfoFromDelivery(Map<String, String> variablesMap)
			throws Exception {
		 //保存客户类型到客户表中
		String arraydel = variablesMap.get("json_put_current_str");
		JSONArray json = new JSONArray(arraydel);
		for(int i=0;i<json.length();i++){
			JSONObject jsonobj = json.getJSONObject(i);
			String iddel = (String) jsonobj.get("factobjectid"); 
			String customerdel = (String) jsonobj.get("customertype");
			if(!customerdel.equals("1")){
			   	DictionaryData dictionarydel = new DictionaryData(customerdel);
		    	CustInfo custinfodel = tableService.findEntityByID(CustInfo.class, iddel);
		    	custinfodel.setCustOmerType(dictionarydel);
		    	this.tableService.updateEntity(custinfodel);
			}
	    
		}
	}

	@Override
	public void updateCustInfoFromPayMent(Map<String, String> variablesMap)
			throws Exception {
		//保存客户类型到客户表中
		String array = variablesMap.get("json_payment_current_str");
		JSONArray json = new JSONArray(array);
		for(int i=0;i<json.length();i++){
			JSONObject jsonobj = json.getJSONObject(i);
			String id = (String) jsonobj.get("factobjectid"); 
			String customer = (String) jsonobj.get("customertype");
			if(!customer.equals("1")){
				DictionaryData dictionary = new DictionaryData(customer);
		    	CustInfo custinfo = tableService.findEntityByID(CustInfo.class, id);
		    	custinfo.setCustOmerType(dictionary);
		    	this.tableService.updateEntity(custinfo);
			}
	       	
		}    
	}

	@Override
	public void savePremiseCondition(Map<String, String> variablesMap)
			throws Exception {
		String array = variablesMap.get("json_premise_condition_str");
		JSONArray json = new JSONArray(array);
		List<ContractPaymentPremiseCondition> list=new ArrayList<ContractPaymentPremiseCondition>();
		ContractPaymentPremiseCondition contractPaymentPremiseCondition = null;
		for(int i=0;i<json.length();i++){
			JSONObject jsonobj = json.getJSONObject(i);
			String id = (String) jsonobj.get("id"); 
			String ismeet = (String) jsonobj.get("ismeet");
			String conditionstub = (String) jsonobj.get("conditionstub");
			if(ismeet!=null){
				contractPaymentPremiseCondition = 
						this.tableService.findEntityByID(ContractPaymentPremiseCondition.class, id);
				contractPaymentPremiseCondition.setIsMeet(ismeet);
				contractPaymentPremiseCondition.setConditionStub(conditionstub);
				list.add(contractPaymentPremiseCondition);
			}
		}   
    	this.tableService.updateAllEntities(list);
	}

	@Override
	public void savePaymentLog(Map<String, String> map,
			Map<String, String> variablesMap) throws Exception {
		String status=map.get("status");//导入第三方数据库的状态1：成功，0：失败
	    String filepath=map.get("filepath");//日志文件路径
	    String contractid=variablesMap.get("contract_id");
	    String login_userid=variablesMap.get("login_userid");
	    ContractInfo ContractInfo=this.tableService.findEntityByID(ContractInfo.class,contractid);
	    String json_payment_current_str=variablesMap.get("json_payment_current_str");
	    JSONArray jsonAry = new JSONArray(json_payment_current_str);
	    
		for(int i=0;i<jsonAry.length();i++){
			JSONObject obj=jsonAry.getJSONObject(i);
			String payment_id=obj.optString("fundfundchargeplan");//获取当前支付的payment_id 
//			List<PaymentLog> logs=this.tableService.findResultsByHSQL("from PaymentLog where paymentid.fundFundChargePlan=?", payment_id);
//			Map<String, Object> propertiesMap=new HashMap<String, Object>();
//			propertiesMap.put("paymentid",payment_id);
//			List<PaymentLog> logs=this.tableService.findEntityByProperties(PaymentLog.class, propertiesMap);
//			if(logs.size()>0){
//				PaymentLog log=logs.get(0);
//				String sql="update payment_log set (PAYMENT_STATUS,FILE_PATH"
////						+ ",MODIFICATOR_,MODIFY_DATE"
//						+ ") values (?,?) where PAYMENT_ID=?";
//				this.tableService.updateBySql(sql, status,filepath,payment_id);
//			}else{
				PaymentLog log=new PaymentLog();
//				String sql="insert into payment_log(ID,PAYMENT_STATUS,CONTRACT_ID,PAYMENT_ID,FILE_PATH) values (SYS_GUID(),?,?,?,?)";
				log.setPaymentstatus(status);
				log.setCreator(this.tableService.findEntityByID(User.class, login_userid));
				log.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				log.setContractid(this.tableService.findEntityByID(ContractInfo.class, contractid));
				//没数据是因为要流程走完之后才会有对应的ContractFundFundCharge
				ContractFundFundPlan s=this.tableService.findEntityByID(ContractFundFundPlan.class, payment_id);
				log.setPaymentid(this.tableService.findEntityByID(ContractFundFundPlan.class, payment_id));
				log.setFilepath(filepath);
			    this.tableService.saveEntity(log);
			    
		}	
	}

	@Override
	public void initFundEquip(String fieldIput, Map<String, String> variablesMap, Map<String, String> queryMainObjectMap) throws Exception {
		String json_equip_amt_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fun_equip_amt.xml", queryMainObjectMap).toString();
		if ( json_equip_amt_str.length() > 0 && null != json_equip_amt_str)
		{
			variablesMap.put(fieldIput,json_equip_amt_str);
		} 		
	}
	
	@Override
	public void initFundPutPlan(String fieldIput, Map<String, String> variablesMap, Map<String, String> queryMainObjectMap) throws Exception {
		String json_put_plan_str ="";
		if("json_fund_put_config_str".endsWith(fieldIput)){
			json_put_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_plan_configlist.xml", queryMainObjectMap).toString();
		 }else{
			json_put_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_fund_plan_list.xml", queryMainObjectMap).toString();
		 }
		if ( json_put_plan_str.length() > 0 && null != json_put_plan_str){
			variablesMap.put(fieldIput,json_put_plan_str);
		} 		
	}

	@Override
	public void initBeforeInterestPlan(String fieldIput, Map<String, String> variablesMap, Map<String, String> queryMainObjectMap) throws Exception {
		String json_put_plan_str = this.tableService.getJsonArrayData("eleasing/workflow/fund/fundcomm/fund_rent_plan_list.xml", queryMainObjectMap).toString();
		if ( json_put_plan_str.length() > 0 && null != json_put_plan_str)
		{
			variablesMap.put(fieldIput,json_put_plan_str);
		} 		
	}

	
	//流程结束时保存数据---付款日志信息和中间表信息
	@Override
	public void savePaymentEndDate(String fieldInput,Map<String,String> variablesMap) throws Exception {
		//获取流程发起人 
		String login_userid=variablesMap.get("payment_userid");
		User user=this.tableService.findEntityByID(User.class, login_userid);
		String sysDate=DateUtil.getSystemDateTime();//获取当前系统时间
		String json_put_current_str=variablesMap.get(fieldInput);
		System.out.println(json_put_current_str+"========");
		String docId=variablesMap.get("docId");//获取流程ID
		JSONArray json = new JSONArray(json_put_current_str);
		JSONArray jsonArray=new JSONArray();
		//判断是付款执行还是 付款结束后手动传输数据
		JSONObject jsonobj=null;
			for(int i=0;i<json.length();i++){	
				jsonobj= json.getJSONObject(i);
				jsonobj.put("contract_info.contractid",variablesMap.get("contract_info.contractid"));//合同编号
				jsonobj.put("docId",docId);//流程编号
				jsonobj.put("contract_info.proj_id", variablesMap.get("contract_info.proj_id"));//项目编号
				jsonobj.put("paymenttypeid", variablesMap.get("paymenttypeid"));//付款租赁形式
				jsonobj.put("ERP_PAYMENT_ID", getJsonObjValue(jsonobj,"contract_info.proj_id")+docId+i);//中间表ID
				jsonobj.put("paymentstatus", " ");//付款状态,这里直接根据insertStrSql方法返回值设置付款状态
				jsonobj.put("errorreason", " ");//错误原因
				jsonobj.put("payment_userid", user.getId());//流程发起人  User表ID
				jsonobj.put("createdate", sysDate);//付款时间
				//jsonobj.put("priorityflag", "N");//加急标识---原来json数组中就有
				if("电汇".equals(getJsonObjValue(jsonobj,"settlemethodname"))){
					jsonArray.put(jsonobj);
				}
				
			}
	    variablesMap.put("retjsonArray",jsonArray.toString());
		int ret=insertStrSql(jsonArray);
		savePaymentChargeLog(variablesMap,ret);
	}
	//执行批量SQL语句  0代表成功，1,代表失败
	@Override
	public int  insertStrSql(JSONArray jsonArray) throws Exception  {		
		int ret=0;
		DataBaseManager  dbm=DataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement inspstmt = null;
        try {
        	conn = dbm.getConnection();
        	if(conn==null){//当获取连接失败的时候ret为失败
        		remindPayment(jsonArray);
        		return 1;
        	}
			conn.setAutoCommit(false);
			insertSqlBatch(jsonArray, conn, inspstmt);//批量执行SQL语句
			conn.commit();
		} catch (SQLException e) {//回滚事物
			e.printStackTrace();
			try {
				conn.rollback();
				remindPayment(jsonArray);
				return 1;
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}			
			remindPayment(jsonArray);
			return 1;//执行保存sql错误
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}

		}	 	
		return 0;				
	}

	//批量执行SQL语句
	
	private void insertSqlBatch(JSONArray jsonArray,Connection conn,PreparedStatement inspstmt) throws SQLException, JSONException {
		//String insertsql="insert into contract_payment_interface"
		String insertsql="insert into CUX.CBS_AUTHCRIZATICH_TO_FAYHENT"
				+" (ERP_PAYMENT_ID,"
				//+" DOC_ID,"				
				+" CURRENCY_TYPE,"
				+" PAYMENT_TYPE_ID,"
				+" PAYMENT_METHOD_TYPE_ID,"
				+" OPERATION_TYPE,"
				+" PAYMENT_ACCOUNTS,"
				+" DEPOSIT_BANK_NAME,"
				+" DEPOSIT_ACCOUNTS_NAME,"
				+" DEPOSIT_ACCOUNTS,"
				+" DEPOSIT_BANK_TYPE,"
				+" DEPOSIT_PROVINCE,"
				+" DEPOSIT_CITY,"
				+" AMOUNT,"
				+" BANK_NUMBER,"
				+" UNION_BANK_NUMBER,"
				+" PRIORITY_FLAG,"
				+" RECORD_STATUS,"
				+" version,"			
				+" PURPOSE"
				+" )"
				+" values"+"(?,'10','202','3','3',?,?,?,?,?,?,?,?,?,?,?,'A','0',?)";	
		inspstmt=conn.prepareStatement(insertsql);
		JSONObject jsonobj=null;
		for(int  i=0;i<jsonArray.length();i++){	
			jsonobj=jsonArray.getJSONObject(i);
			/*Public String jsonoo(String obj){
				if(obj==null)
				return "";
			}*/
			inspstmt.setString(1, jsonobj.getString("ERP_PAYMENT_ID"));//主键
			//inspstmt.setString(2, jsonobj.getString("docId"));//结算方式
			//inspstmt.setString(2, jsonobj.getString("settlemethodname"));//结算方式
			inspstmt.setString(2,getJsonObjValue(jsonobj, "accnumber"));//付款方银行账号
			inspstmt.setString(3,getJsonObjValue(jsonobj, "clientbankname"));//收款方开户行
			inspstmt.setString(4,getJsonObjValue(jsonobj, "clientaccount"));//收款方银行账户名称
			inspstmt.setString(5,getJsonObjValue(jsonobj, "clientaccnumber"));//收款方银行账号
			inspstmt.setString(6,getJsonObjValue(jsonobj, "depositbanktype"));//收款方银行简称
			inspstmt.setString(7,getJsonObjValue(jsonobj, "depositprovince"));//收款方银行省
			inspstmt.setString(8,getJsonObjValue(jsonobj, "depositcity"));//收款方银行市				
			inspstmt.setString(9,getJsonObjValue(jsonobj, "factmoney"));//付款金额
			inspstmt.setString(10,getJsonObjValue(jsonobj, "clientbankno"));//银行号
			inspstmt.setString(11,getJsonObjValue(jsonobj, "unionclientbankno"));//联行号
			inspstmt.setString(12,getJsonObjValue(jsonobj, "priorityflag"));//加急标识
			inspstmt.setString(13,getJsonObjValue(jsonobj, "purpose"));//用途
			inspstmt.addBatch();
	   }
		inspstmt.executeBatch();
		
	}
	
	@Override
	public int  updateStrSql(JSONArray jsonArray) throws Exception  {		
		int ret=0;
		DataBaseManager  dbm=DataBaseManager.getInstance();
		Connection conn = null;
		PreparedStatement uppstmt = null;
        try {
        	conn = dbm.getConnection();
        	if(conn==null){//当获取连接失败的时候ret为失败
        		//remindPayment(jsonArray);
        		return 2;
        	}
			conn.setAutoCommit(false);
			updateSqlBatch(jsonArray, conn, uppstmt);//批量执行SQL语句
			conn.commit();
		} catch (SQLException e) {//回滚事物
			try {
				e.printStackTrace();
				conn.rollback();
				//remindPayment(jsonArray);
				return 2;
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
			e.printStackTrace();
			remindPayment(jsonArray);
			return 2;//执行保存sql错误
		} finally {
			if (conn != null) {
				try {
					conn.close();
					dbm.CloseStatement(uppstmt);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}

		}	 	
		return 0;				
	}

	
	private void updateSqlBatch(JSONArray jsonArray,Connection conn,PreparedStatement upspstmt) throws SQLException, JSONException {
		//String upsql="update contract_payment_interface "
				String upsql="update CUX.CBS_AUTHCRIZATICH_TO_FAYHENT "
				+" set priority_flag="+"?"+","
				+" PAYMENT_ACCOUNTS="+"?"+","
				+" DEPOSIT_BANK_NAME="+"?"+","
				+" DEPOSIT_ACCOUNTS_NAME="+"?"+","
				+" DEPOSIT_ACCOUNTS="+"?"+","				
				+" DEPOSIT_BANK_TYPE="+"?"+","
				+" DEPOSIT_PROVINCE="+"?"+","
				+" DEPOSIT_CITY="+"?"+","
				+" BANK_NUMBER="+"?"+","
				+" UNION_BANK_NUMBER="+"?"+","
				+" RECORD_STATUS="+"?"
				+" where ERP_PAYMENT_ID="+"?"				
				;	
		upspstmt=conn.prepareStatement(upsql);
		JSONObject jsonobj=null;
		for(int  i=0;i<jsonArray.length();i++){	
			jsonobj=jsonArray.getJSONObject(i);
			upspstmt.setString(1,getJsonObjValue(jsonobj, "priorityflag"));//加急标识
			upspstmt.setString(2,getJsonObjValue(jsonobj, "accnumber"));//付款方银行账号
			upspstmt.setString(3,getJsonObjValue(jsonobj, "clientbankname"));//收款方开户行
			upspstmt.setString(4,getJsonObjValue(jsonobj, "clientaccount"));//收款方银行账户名称
			upspstmt.setString(5,getJsonObjValue(jsonobj, "clientaccnumber"));//收款方银行账号
			upspstmt.setString(6,getJsonObjValue(jsonobj, "depositbanktype"));//收款方银行简称
			upspstmt.setString(7,getJsonObjValue(jsonobj, "depositprovince"));//收款方银行省
			upspstmt.setString(8,getJsonObjValue(jsonobj, "depositcity"));//收款方银行市	
			upspstmt.setString(9,getJsonObjValue(jsonobj, "clientbankno"));//银行号
			upspstmt.setString(10,getJsonObjValue(jsonobj, "unionclientbankno"));//联行号
			upspstmt.setString(11,"A");//更新付款状态,当付款失败后再次传输数据时，设置RECORD_STATUS为处理中状态
			upspstmt.setString(12,getJsonObjValue(jsonobj, "ERP_PAYMENT_ID"));//更新条件 -主键ID
			//upspstmt.setString(2, jsonobj.getString("docId"));//结算方式
			//upspstmt.setString(3, jsonobj.getString("settlemethodname"));//结算方式
			upspstmt.addBatch();
	   }
		upspstmt.executeBatch();	
	}
	/**
	 * JSON数组对象获取值
	 * @param jsonobj
	 * @param key
	 * @return
	 */
	private String getJsonObjValue(JSONObject jsonobj,String key){
		String value="";
		try {
			value=jsonobj.getString(key);
			if("".equals(value)||"null".equals(value)){
				return " ";
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return " "; 
		}
		
		return value;
		
	}
	//保存付款日志信息
	private void  savePaymentChargeLog(Map<String,String> variablesMap,int status) throws Exception{

		// 当前用户信息
		User loguser = (User) SecurityUtil.getPrincipal();
		
		List<ContractFundPaymentInterfaceLog>  loglist=new ArrayList<ContractFundPaymentInterfaceLog>();
		//获取流程发起人 
		String login_userid=variablesMap.get("payment_userid");
		User user=this.tableService.findEntityByID(User.class, login_userid);
		String sysDate=DateUtil.getSystemDateTime();//获取当前系统时间
		String docId=variablesMap.get("docId");//获取流程ID		
		String jsonstr=variablesMap.get("retjsonArray");
		JSONArray jsonAry=new JSONArray(jsonstr);
		for(int i=0;i<jsonAry.length();i++){
			JSONObject  obj=jsonAry.getJSONObject(i);
			ContractFundPaymentInterfaceLog cfpf=new ContractFundPaymentInterfaceLog();
			cfpf.setPaymentContractId(getJsonObjValue(obj, "contract_info.contractid"));
			cfpf.setErpPaymentId(getJsonObjValue(obj,"ERP_PAYMENT_ID"));//对应中间表ID
			cfpf.setNumUuid(getJsonObjValue(obj,"numuuid"));
			cfpf.setFeetypename(getJsonObjValue(obj,"feetypename"));//费用类型
			cfpf.setPaymentMethodTypeId(getJsonObjValue(obj,"settlemethodname"));//结算方式
			cfpf.setPaymentAccounts(getJsonObjValue(obj,"accnumber"));//付款银行账号
			cfpf.setDepositBankName(getJsonObjValue(obj,"clientbankname"));//收款方开户行
			cfpf.setDepositAccountsName(getJsonObjValue(obj,"clientaccount"));//收款方银行账户名称
			cfpf.setDepositAccounts(getJsonObjValue(obj,"clientaccnumber"));//收款方银行账号
			cfpf.setDepositBankType(getJsonObjValue(obj,"depositbanktype"));//收款方银行简称
			cfpf.setDepositProvince(getJsonObjValue(obj,"depositprovince"));//收款方开户行省
			cfpf.setDepositCity(getJsonObjValue(obj,"depositcity"));//收款方开户行市
			cfpf.setAmount(Double.valueOf(getJsonObjValue(obj,"factmoney")));//交易金额
			cfpf.setBankNumber(getJsonObjValue(obj,"clientbankno"));//收款方银行号
			cfpf.setUnionBankNumber(getJsonObjValue(obj,"unionclientbankno"));//收款方联行号
			//cfpf.setModificator(loguser);
			cfpf.setPriorityFlag(getJsonObjValue(obj,"priorityflag"));//加急标识
			cfpf.setPaymentTypeId(getJsonObjValue(obj,"paymenttypeid"));//保存付款业务形式
			cfpf.setCreator(user);
			cfpf.setCreateDate(sysDate);
			cfpf.setPaymentProcessId(docId);
			cfpf.setPaymentLog(jsonstr);
			if(status==0){
				cfpf.setPaymentStatus("A");
			}else{
				cfpf.setPaymentStatus(status+"");
			}
			loglist.add(cfpf);
		}
		
	
		this.tableService.saveAllEntities(loglist);
		
	}
	//付款传输失败提醒语句
	private  void  remindPayment(JSONArray jsonary) throws Exception{
		
		JSONObject  obj=null;
		for(int i=0;i<jsonary.length();i++){
			obj=jsonary.getJSONObject(i);
			String message="";
			try {
				message="合同号为："+obj.get("contract_info.contractid")+"，流程号为："+obj.get("docId")+"付款失败";
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//获取当前系统时间
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
			String sysDate = dateFormat.format( now ); 
			User user=new User();
			user=this.tableService.findEntityByID(User.class, "Administrator");
			BaseMessage bm=new BaseMessage();
			bm.setMsgTitle("数据传输失败提醒");  //提醒标题
			bm.setMsgText(message);		 //提醒消息正文
			bm.setSendDate(sysDate);     //发布时间
			bm.setCreateDate(sysDate);   //创建时间
			DictionaryData dda=this.tableService.findEntityByID(DictionaryData.class, "msgtype.1");			
			bm.setMsgType(dda);
			bm.setFromUser(user);
			bm.setCreator(user);
			this.tableService.saveEntity(bm);
			String id=bm.getId();
			BaseMessageToUser bmu=new BaseMessageToUser();
//			BaseMessage  bm0=this.tableService.findEntityByID(BaseMessage.class, bm.getMsgText());
			bmu.setMsgID(bm);
			User  user0=tableService.findEntityByID(User.class, obj.getString("payment_userid"));
			bmu.setReadUser(user0);//消息接收人--发起人
			bmu.setReadStatus("1");
			bmu.setCreateDate(sysDate);
			bmu.setCreator(user0);
			this.tableService.saveEntity(bmu);
		}
		
	
		
	}


	
}
