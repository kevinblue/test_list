package com.tenwa.leasing.serviceImpl.Proj.proComm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustTypeInfo;
import com.tenwa.leasing.entity.proj.ProjCreditWorkFlowInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.proj.ProjGuaranteeEquip;
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethod;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.ProjMeetingInfo;
import com.tenwa.leasing.entity.proj.ProjUnionCust;
import com.tenwa.leasing.entity.proj.invoice.ProjInvoiceType;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;

/**   
*    
* 项目名称：tls5.1   
* 类名称：ProCommServiceImpl   
* 类描述：   项目层公共数据保存
* 创建人：rovine   
* 创建时间：2014年11月19日 下午6:25:11   
* @version        
*/
@Service(value = "proCommService")
public class ProCommServiceImpl implements ProCommService {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Override
	public void loadPorjCustInfo(ProjInfo pinfo,Map<String, String> variablesMap) throws Exception {
		CustInfo cinfo=pinfo.getCustInfo();
		if(cinfo!=null){
			variablesMap.put("proj_info.custname",cinfo.getCustName());
			variablesMap.put("proj_info.custid",cinfo.getId());
			variablesMap.put("proj_info.custInfo",cinfo.getId());
			variablesMap.put("proj_info.custnumber",cinfo.getCustNumber());
			variablesMap.put("proj_info.custclass",cinfo.getCustClass().getId());	
		}
	}


	
	@Override
	public ProjInfo saveProjInfo(Map<String, String> variablesMap, Integer ProStatus)throws Exception {
		String proj_id = variablesMap.get("proj_info.projid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("projId", proj_id);
		variablesMap.put("proj_info.projstatus", String.valueOf(ProStatus));
		ProjInfo projInfo = (ProjInfo)this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
		this.tableService.saveOrUpdateEntity(projInfo);
		return projInfo;
	}

	@Override
	public ProjInfo loadProjInfo(Map<String, String> variablesMap) throws Exception {
		String proj_id = variablesMap.get("proj_id");
		ProjInfo projInfo = this.tableService.findEntityByID(ProjInfo.class, proj_id);
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(projInfo,null, "proj_info"));
		this.loadPorjCustInfo(projInfo, variablesMap);
		return projInfo;
	}
	
	
	@Override
	public void loadRentCalculationParam(String projid,String custname,String custid,String flowunid,Map<String, String> variablesMap)throws Exception {
		variablesMap.put("framework_condition.projid", projid);
		variablesMap.put("framework_condition.contractid", "");
		variablesMap.put("framework_condition.process", "proj_process");
		variablesMap.put("framework_condition.custname", custname);
		variablesMap.put("framework_condition.custid", custid);
		variablesMap.put("framework_condition.docid", flowunid);
		variablesMap.put("framework_condition.onhireid", "");
		
	}

	@Override
	public void loadProjRentCalculation(ProjInfo projinfo,	Map<String, String> variablesMap) throws Exception {
		this.rentConditionData.LoadProjectContionAndRentAndFundToMap(projinfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str");	
	}
	@Override
	public void saveProjRentCalculation(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		this.rentConditionData.saveProjectContionAndRentAndFundToDatabase(projinfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str");
	}

	@Override
	public void saveProjEquipment(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		// 租赁物明细
		String jsonEquipsString = variablesMap.get("json_proj_equip_str");//proj_info.projEquips
		// 租赁物明细
		this.tableService.updateOneToManyCollections(projinfo, "projEquips", ProjEquip.class, "projInfo", jsonEquipsString,null);
	}

	@Override
	public void saveProjGuaranteeEquipment(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		// 抵押人信息
		String jsonGuarString = variablesMap.get("json_proj_guaranty_detail_str");//proj_info.projEquips
		this.tableService.updateOneToManyCollections(projinfo, "projGuaranteeEquips", ProjGuaranteeEquip.class, "projInfo", jsonGuarString,null);
	}

	@Override
	public void saveProjGuaranteeMethod(ProjInfo projinfo,	Map<String, String> variablesMap) throws Exception {
		//担保人信息
		String jsonAssString = variablesMap.get("json_proj_guarantee_detail_str");//proj_info.projEquips
		//担保人信息
		this.tableService.updateOneToManyCollections(projinfo, "projGuaranteeMethods", ProjGuaranteeMethod.class, "projInfo", jsonAssString,null);
	}

	@Override
	public void loadProjEquipment(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projinfo.getId());
		variablesMap.put("json_proj_equip_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_equip.xml", queryMainObjectMap).toString());
	}

	@Override
	public void loadProjGuaranteeEquipment(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projinfo.getId());
		variablesMap.put("json_proj_guaranty_detail_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_guarantee_equip.xml", queryMainObjectMap).toString());
		
	}

	@Override
	public void loadProjGuaranteeMethod(ProjInfo projinfo,	Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projinfo.getId());
		variablesMap.put("json_proj_guarantee_detail_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_guarantee_method.xml", queryMainObjectMap).toString());
	}

	@Override
	public void loadProjInvoice(ProjInfo projinfo,	Map<String, String> variablesMap) throws Exception {
		ProjInvoiceType	projInvoiceType=projinfo.getProjInvoiceType();
		if(null!=projInvoiceType){
			variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(projInvoiceType,null, "rent_invoice_type"));
		}
		
	}

	@Override
	public void savProjInvoice(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		this.tableService.updateOneToOneEntity(projinfo, "projInvoiceType", "projId", variablesMap,null, "rent_invoice_type");
	}



	@Override
	public void saveProjMeeting(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		ProjMeetingInfo meeting = new ProjMeetingInfo();
		meeting = this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, meeting, null, "proj_meeting");
		meeting.setProjId(projinfo);
		meeting.setDocId(variablesMap.get("docId"));
		this.tableService.saveEntity(meeting);
		
	}

	@Override
	public void saveProjBoardMeeting(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		ProjMeetingInfo meeting = new ProjMeetingInfo();
		meeting = this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, meeting, null, "proj_board_meeting");
		meeting.setProjId(projinfo);
		meeting.setDocId(variablesMap.get("docId"));
		this.tableService.saveEntity(meeting);
		
	}

	@Override
	public void saveProjReport(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
				ProjCreditWorkFlowInfo creditInfo = new ProjCreditWorkFlowInfo();
				JSONObject reportInfo = new JSONObject();
				for(Entry<String, String> entry : variablesMap.entrySet()){
					if(entry.getKey().startsWith("proj_report")){
						reportInfo.put(entry.getKey(), entry.getValue());
					}
					if(entry.getKey().startsWith("rawValue_proj_report")){
						reportInfo.put(entry.getKey(), entry.getValue());
					}
				}
				creditInfo.setCreditReportJson(reportInfo.toString());
				creditInfo.setProjId(projinfo);
				//creditInfo.setCreditGradeJson(variablesMap.get("json_credit_grade_str"));
				creditInfo.setCreditGradeJson("[]");
				creditInfo.setDocListJson(variablesMap.get("json_document_list_str"));
				creditInfo.setDocId(variablesMap.get("docId"));
				this.tableService.saveEntity(creditInfo);
		
	}
	@Override
	public void saveProjUnionCust(ProjInfo projinfo,Map<String,String>variablesMap) throws Exception {
		Map<String, Object> whereMap;
		String custType=variablesMap.get("custid2");
		CustInfo cust=null;
		if(custType!=null&&!custType.equals("")){
			 String custTypes[]=custType.split(",");
			 List<ProjUnionCust> custTypeInfos=new ArrayList<ProjUnionCust>();
			 for (String temp:custTypes) {
				 ProjUnionCust unioncust=new ProjUnionCust();
				 whereMap=new HashMap<String, Object>();
				 whereMap.put("id", temp);
				 cust = this.tableService.findEntityByID(CustInfo.class, temp);
				 unioncust.setProjInfo(projinfo);
				 unioncust.setUnioncust(cust);
				 unioncust.setUnioncustclass(cust.getCustClass().getId());
				 custTypeInfos.add(unioncust);
			}
			 this.tableService.saveAllEntities(custTypeInfos);
		}
		
	}
	/*public void saveProjUnionCust(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		// 联合承租人
		
		String jsonEquipsString = variablesMap.get("json_union_cust_str");
		if(null!=jsonEquipsString && (!"[]".equals(jsonEquipsString))){
		this.tableService.updateOneToManyCollections(projinfo, "projUnionCusts", ProjUnionCust.class, "projInfo", jsonEquipsString,null);
		}
		
	}*/


    //添加联合承租人加载信息
	@Override
	public void loadProjUnionCust(ProjInfo projinfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projinfo.getId());                   
		variablesMap.put("json_union_cust_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_union_cust.xml", queryMainObjectMap).toString());
		
	}

}



