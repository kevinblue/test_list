package com.tenwa.leasing.serviceImpl.Proj.projCredit;




import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projCredit.ProjCreditService;


@Service(value = "projCreditService")
public class ProjCreditServiceImpl   implements ProjCreditService {

	@Resource(name = "tableService")
	private TableService tableService;
	
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Override
	public void updateProjCreditInfo(Map<String, String> variablesMap) throws Exception {
		variablesMap.remove("proj_info.projcondition");
		ProjInfo projinfo=new ProjInfo();
		/*String proj_id = variablesMap.get("proj_info.projid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("projId", proj_id);
		String sql = "select "
				+" sum(case when is_view ='同意' then 1 else 0 end) agree "
				+" ,sum(case when is_view='不同意' then 1 else 0 end) disagree "
				+" ,count(vote.id) allvote "
				+" ,case when count(vote.id)=0 then 0 else sum(case when is_view ='同意' then 1 else 0 end)/count(vote.id) end agreeradio"
				+" from PROJ_CREDIT_VOTE vote left join proj_info pi on pi.id=vote.proj_id"
				+" where pi.proj_id = '"+proj_id+"'";
		List<Map<String, Object>> radiolist= this.tableService.queryListBySql(sql, null);
		//一定会有一条记录查询记录 少于半数同意信审否决
		BigDecimal radio = new BigDecimal(String.valueOf(radiolist.get(0).get("agreeradio")));
		if((variablesMap.containsKey("proj_info.preisagree")&&variablesMap.get("proj_info.preisagree").toString().equals("否"))
				||radio.compareTo(new BigDecimal("0.5"))<=0){
			ProjInfo proj=this.tableService.findEntityByID(ProjInfo.class, variablesMap.get("proj_info.id"));
			proj.setProjStatus(104);
			this.tableService.updateEntity(proj);*/
			String isagree = variablesMap.get("processedResult");
			String proj_id = variablesMap.get("proj_info.projid");
			Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
			queryMainObjectMap.put("projId", proj_id);
		    if(!"JbpmCommonAdvise_agree".equals(isagree)){//同意立项
		    	projinfo.setProjStatus(104);
			ProjInfo projInfo = (ProjInfo)this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
			this.tableService.updateEntity(projInfo);
		}else{
			variablesMap.put("proj_info.projstatus", AppStaticUtil.PROJ_CREDIT+"");
			projinfo = (ProjInfo)this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
		    proCommService.saveProjMeeting(projinfo, variablesMap);  
		    proCommService.saveProjEquipment(projinfo, variablesMap);
		    proCommService.saveProjPaymentPremiseCondition(projinfo, variablesMap);
		    proCommService.saveProjRentCalculation(projinfo, variablesMap);
		    proCommService.saveProjGuaranteeMethod(projinfo, variablesMap);
		    proCommService.saveProjGuaranteeEquipment(projinfo, variablesMap);
		    proCommService.savProjInvoice(projinfo, variablesMap);
		    proCommService.saveCreditReport(projinfo, variablesMap);
		    proCommService.saveProjLimitInfo(projinfo, variablesMap);
		}
	    proCommService.saveProjPremiseInfo(projinfo, variablesMap);
	}
	@Override
	public void updateProjCreditInfo01(Map<String, String> variablesMap) throws Exception {
		
		
		variablesMap.remove("proj_info.projcondition");
		ProjInfo projinfo=new ProjInfo();
		String proj_id = variablesMap.get("proj_info.projid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("projId", proj_id);
		String sql = "select "
				+" sum(case when is_view ='同意' then 1 else 0 end) agree "
				+" ,sum(case when is_view='不同意' then 1 else 0 end) disagree "
				+" ,count(vote.id) allvote "
				+" ,case when count(vote.id)=0 then 0 else sum(case when is_view ='同意' then 1 else 0 end)/count(vote.id) end agreeradio"
				+" from PROJ_CREDIT_VOTE vote left join proj_info pi on pi.id=vote.proj_id"
				+" where pi.proj_id = '"+proj_id+"'";
		List<Map<String, Object>> radiolist= this.tableService.queryListBySql(sql, null);
		//一定会有一条记录查询记录 少于半数同意信审否决
		BigDecimal radio = new BigDecimal(String.valueOf(radiolist.get(0).get("agreeradio")));
		if((variablesMap.containsKey("proj_info.preisagree")&&variablesMap.get("proj_info.preisagree").toString().equals("否"))
				||radio.compareTo(new BigDecimal("0.5"))<=0){
//			ProjInfo proj=this.tableService.findEntityByID(ProjInfo.class, variablesMap.get("proj_info.id"));
//			proj.setProjStatus(104);
//			this.tableService.updateEntity(proj);
		}else{
			/*			String projlevl=variablesMap.get("proj_info.creditisapproval");
			if("".equals(projlevl)||null==projlevl) projlevl="credit_type_@11";//防止拿不到信审结论时报错。
			projlevl=projlevl.substring(projlevl.indexOf("@")+1);*/
			variablesMap.put("proj_info.projstatus", AppStaticUtil.PROJ_CREDIT+"");
			projinfo = (ProjInfo)this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
			//proCommService.saveProjReport(projinfo, variablesMap);
			proCommService.saveProjMeeting(projinfo, variablesMap);
			proCommService.saveProjEquipment(projinfo, variablesMap);
			proCommService.saveProjRentCalculation(projinfo, variablesMap);
			proCommService.saveProjGuaranteeMethod(projinfo, variablesMap);
			proCommService.saveProjGuaranteeEquipment(projinfo, variablesMap);
			proCommService.savProjInvoice(projinfo, variablesMap);
			proCommService.saveCreditReport(projinfo, variablesMap);
			proCommService.saveProjLimitInfo(projinfo, variablesMap);
		}
//	    proCommService.saveProjPremiseInfo(projinfo, variablesMap);
	}
	@Override
	public void updateProjCreditInfo03(Map<String, String> variablesMap) throws Exception {

		variablesMap.remove("proj_info.projcondition");
		ProjInfo projinfo=new ProjInfo();
		String proj_id = variablesMap.get("proj_info.projid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("projId", proj_id);
		String processedResultShow=variablesMap.get("processedResultShow");
		if(!processedResultShow.equals("同意")){
//			ProjInfo proj=this.tableService.findEntityByID(ProjInfo.class, variablesMap.get("proj_info.id"));
//			proj.setProjStatus(104);
//			this.tableService.updateEntity(proj);
		}else{
			variablesMap.put("proj_info.projstatus", AppStaticUtil.PROJ_CREDIT+"");
			projinfo = (ProjInfo)this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
		    proCommService.saveProjMeeting(projinfo, variablesMap);
		    proCommService.saveProjEquipment(projinfo, variablesMap);
		    proCommService.saveProjRentCalculation(projinfo, variablesMap);
		    proCommService.saveProjGuaranteeMethod(projinfo, variablesMap);
		    proCommService.saveProjGuaranteeEquipment(projinfo, variablesMap);
		    proCommService.savProjInvoice(projinfo, variablesMap);
		    proCommService.saveCreditReport(projinfo, variablesMap);
		    proCommService.saveProjLimitInfo(projinfo, variablesMap);
		}
//	    proCommService.saveProjPremiseInfo(projinfo, variablesMap);
	}
	@Override
	public void updateProjCreditInfo02(Map<String, String> variablesMap) throws Exception {

		variablesMap.remove("proj_info.projcondition");
		ProjInfo projinfo=new ProjInfo();
		String proj_id = variablesMap.get("proj_info.projid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("projId", proj_id);
		String sql = "select "
				+" sum(case when is_view ='同意' then 1 else 0 end) agree "
				+" ,sum(case when is_view='不同意' then 1 else 0 end) disagree "
				+" ,count(vote.id) allvote "
				+" ,case when count(vote.id)=0 then 0 else sum(case when is_view ='同意' then 1 else 0 end)/count(vote.id) end agreeradio"
				+" from PROJ_CREDIT_VOTE vote left join proj_info pi on pi.id=vote.proj_id"
				+" where pi.proj_id = '"+proj_id+"'";
		List<Map<String, Object>> radiolist= this.tableService.queryListBySql(sql, null);
		//一定会有一条记录查询记录 任何一人不同意信审否决
		BigDecimal radio = new BigDecimal(String.valueOf(radiolist.get(0).get("agreeradio")));
		if((variablesMap.containsKey("proj_info.preisagree")&&variablesMap.get("proj_info.preisagree").toString().equals("否"))
				||radio.compareTo(new BigDecimal("1"))<0){
//			ProjInfo proj=this.tableService.findEntityByID(ProjInfo.class, variablesMap.get("proj_info.id"));
//			proj.setProjStatus(104);
//			this.tableService.updateEntity(proj);
		}else{
/*			String projlevl=variablesMap.get("proj_info.creditisapproval");
			if("".equals(projlevl)||null==projlevl) projlevl="credit_type_@11";//防止拿不到信审结论时报错。
			projlevl=projlevl.substring(projlevl.indexOf("@")+1);*/
			variablesMap.put("proj_info.projstatus", AppStaticUtil.PROJ_CREDIT+"");
			projinfo = (ProjInfo)this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
		    //proCommService.saveProjReport(projinfo, variablesMap);
		    proCommService.saveProjMeeting(projinfo, variablesMap);
		    proCommService.saveProjEquipment(projinfo, variablesMap);
		    proCommService.saveProjRentCalculation(projinfo, variablesMap);
		    proCommService.saveProjGuaranteeMethod(projinfo, variablesMap);
		    proCommService.saveProjGuaranteeEquipment(projinfo, variablesMap);
		    proCommService.savProjInvoice(projinfo, variablesMap);
		    proCommService.saveCreditReport(projinfo, variablesMap);
		    proCommService.saveProjLimitInfo(projinfo, variablesMap);
		}
//	    proCommService.saveProjPremiseInfo(projinfo, variablesMap);
	}
	@Override
	public void  getProjCreditInfo(Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo)
			throws Exception {
		
		String proj_id = variablesMap.get("proj_id");
		ProjInfo projInfo =proCommService.loadProjInfo(variablesMap);
		proCommService.loadProjEquipment(projInfo, variablesMap);
		proCommService.loadProjPaymentPremiseCondition(projInfo, variablesMap);
		proCommService.loadProjRentCalculation(projInfo, variablesMap);
		proCommService.loadProjGuaranteeMethod(projInfo, variablesMap);
		proCommService.loadProjGuaranteeEquipment(projInfo, variablesMap);
		proCommService.loadProjInvoice(projInfo, variablesMap);
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		proCommService.loadRentCalculationParam(proj_id, projInfo.getCustInfo().getCustName(), projInfo.getCustInfo().getId(), flowunid, variablesMap);
		getProjCreditReportInfo(variablesMap,projInfo);
	}
	
	//得到尽职调查报告，信息
	private void getProjCreditReportInfo(Map<String, String> variablesMap,ProjInfo proj) throws Exception{
		CustInfo cust = proj.getCustInfo();
		if(cust.getCustClass().getCode().equalsIgnoreCase("CUST_INFO_PERSON")){
			CustInfoPerson person = cust.getCustInfoPerson();
			variablesMap.put("proj_report.person.custname", person.getCustId().getCustName());
			variablesMap.put("proj_report.person.sex", person.getSex());
			variablesMap.put("proj_report.person.idcardno", person.getIdCardNo());
			variablesMap.put("proj_report.person.brithdate", person.getBrithDate());
			variablesMap.put("proj_report.person.maritalstatus",  null == person.getMaritalStatus() ?  "" : person.getMaritalStatus().getCode());
			variablesMap.put("rawValue_proj_info.person.maritalstatus",  null == person.getMaritalStatus() ?  "" : person.getMaritalStatus().getName());
			variablesMap.put("proj_report.person.school", null == person.getSchool() ? "" : person.getSchool().getCode());
			variablesMap.put("rawValue_proj_info.person.school", null == person.getSchool() ? "" : person.getSchool().getName());
			variablesMap.put("proj_report.person.domicileplace", null == person.getDomicilePlace() ? "" : person.getDomicilePlace());
			variablesMap.put("proj_report.person.mailadd", person.getMailAdd());
			variablesMap.put("proj_report.person.phone", person.getPhone());
			variablesMap.put("proj_report.person.mobilenumber", person.getMobileNumber());
			Map<String, String> queryMainObjectMap = new HashMap<String, String>();
			queryMainObjectMap.put("custid", cust.getId());
			String proj_report_manufacturers_info = this.tableService.getJsonArrayData("eleasing/jsp/cust_info/cust_manufacturersInfo/cust_manufacturersInfo_list.xml", queryMainObjectMap).toString();
			String proj_report_sale_info = this.tableService.getJsonArrayData("eleasing/jsp/cust_info/cust_salesinfo/cust_salesinfo_list.xml", queryMainObjectMap).toString();
			variablesMap.put("proj_report_manufacturers_info", proj_report_manufacturers_info);
			variablesMap.put("proj_report_sale_info", proj_report_sale_info);
		}else{
			
			CustInfoCompany company = cust.getCustInfoCompany();
			variablesMap.put("proj_report.company.regcapital", null == company.getRegCapital() ? "" : company.getRegCapital().toString());
			variablesMap.put("proj_report.company.empnumber", company.getEmpNumber());
			variablesMap.put("proj_report.company.custname", company.getCustId().getCustName());
			variablesMap.put("proj_report.company.operate", company.getOperate());
			variablesMap.put("proj_report.company.personidcard", company.getPersonIdcard());
			variablesMap.put("proj_report.company.ownership", null==company.getOwnerShip()?"":company.getOwnerShip().getId());
			variablesMap.put("rawValue_proj_report.company.ownership",null==company.getOwnerShip()?"":company.getOwnerShip().getName());
			variablesMap.put("proj_report.company.operatemaster", company.getOperateMaster());
			variablesMap.put("proj_report.company.operateminor", company.getOperateMinor());
			variablesMap.put("proj_report.company.custprobably", company.getCustProbably());
			variablesMap.put("proj_report.company.orgcode",  company.getOrgCode());
			variablesMap.put("proj_report.company.buslicense", company.getBusLicense());
			variablesMap.put("proj_report.company.taxbank", company.getTaxBank());
			variablesMap.put("proj_report.company.taxacc", company.getTaxAcc());
			variablesMap.put("proj_report.company.personrep", company.getPersonRep());
			variablesMap.put("proj_report.company.postcode", company.getPostCode());
			variablesMap.put("proj_report.company.mobilenumber", company.getMoblie());
			variablesMap.put("proj_report.company.phone", company.getPhone());
			variablesMap.put("proj_report.company.mailaddress", company.getMailAddress());
			//得到法人客户相关信息
			Map<String, String> queryMainObjectMap = new HashMap<String, String>();
			queryMainObjectMap.put("custid", cust.getId());
			String proj_report_vip_person = this.tableService.getJsonArrayData("eleasing/jsp/cust_info/cust_relatedperson/cust_relatedperson_list.xml", queryMainObjectMap).toString();
			String proj_report_share_company = this.tableService.getJsonArrayData("eleasing/jsp/cust_info/cust_share_company/cust_share_company.xml", queryMainObjectMap).toString();
			String proj_report_stock_holder = this.tableService.getJsonArrayData("eleasing/jsp/cust_info/cust_stockholder/cust_stockholder.xml", queryMainObjectMap).toString();
			String proj_report_manufacturers_info = this.tableService.getJsonArrayData("eleasing/jsp/cust_info/cust_manufacturersInfo/cust_manufacturersInfo_list.xml", queryMainObjectMap).toString();
			String proj_report_sale_info = this.tableService.getJsonArrayData("eleasing/jsp/cust_info/cust_salesinfo/cust_salesinfo_list.xml", queryMainObjectMap).toString();
			variablesMap.put("proj_report_vip_person", proj_report_vip_person);
			variablesMap.put("proj_report_share_company", proj_report_share_company);
			variablesMap.put("proj_report_stock_holder", proj_report_stock_holder);
			variablesMap.put("proj_report_manufacturers_info", proj_report_manufacturers_info);
			variablesMap.put("proj_report_sale_info", proj_report_sale_info);
		}
	}
	
	@Override
	public void  getFactoringProjCreditInfo(Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo)
			throws Exception {
		String proj_id = variablesMap.get("proj_id");
		ProjInfo projInfo =proCommService.loadProjInfo(variablesMap);
		proCommService.loadProjInvoicement(projInfo, variablesMap);
		proCommService.loadProjRentCalculation(projInfo, variablesMap);
		proCommService.loadTradeBasedTransactions(projInfo, variablesMap);
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		proCommService.loadRentCalculationParam(proj_id, projInfo.getCustInfo().getCustName(), projInfo.getCustInfo().getId(), flowunid, variablesMap);
	}
	//加载保理复议信息
	@Override
	public void  getFactoringProjReconsiderInfo(Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo)
			throws Exception {
		String proj_id = variablesMap.get("proj_id");
		ProjInfo projInfo =proCommService.loadProjInfo(variablesMap);
		proCommService.loadProjInvoicement(projInfo, variablesMap);
		proCommService.loadProjChangeExplain(projInfo, variablesMap);//加载项目变更说明页签内容
		proCommService.loadProjRentCalculation(projInfo, variablesMap);
		proCommService.loadTradeBasedTransactions(projInfo, variablesMap);
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		proCommService.loadRentCalculationParam(proj_id, projInfo.getCustInfo().getCustName(), projInfo.getCustInfo().getId(), flowunid, variablesMap);
	}
	
	@Override
	public void updateFactoringProjCreditInfo(Map<String, String> variablesMap,Integer projStatusCode) throws Exception {
		String proj_id = variablesMap.get("proj_info.id");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("id", proj_id);  
		variablesMap.put("proj_info.projstatus", projStatusCode+"");
		ProjInfo projinfo = this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
		proCommService.saveProjInvoice(projinfo, variablesMap);
		proCommService.saveProjRentCalculation(projinfo, variablesMap);
		proCommService.saveTradeBasedTransactions(projinfo, variablesMap);
		
		
		
	}
	
	@Override
	public void updateFactoringReconsiderInfo(Map<String, String> variablesMap,Integer projStatusCode) throws Exception {
		String proj_id = variablesMap.get("proj_info.id");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("id", proj_id);  
		variablesMap.put("proj_info.projstatus", projStatusCode+"");
		ProjInfo projinfo = this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
		proCommService.saveProjInvoice(projinfo, variablesMap);
		proCommService.saveProjRentCalculation(projinfo, variablesMap);
		//保存复议信息，新增保存项目变更说明
		proCommService.saveProjChangeExplain(projinfo, variablesMap);
		//保存复议信息，新增变更原因类型
		proCommService.saveChangeReasonType(projinfo, variablesMap);
		//保存复议信息，贸易基础交易情况改变
		proCommService.saveTradeBasedTransactions(projinfo, variablesMap);
	}
	
}
