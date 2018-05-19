package com.tenwa.leasing.action.contract.borrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractBorrowHis;
import com.tenwa.leasing.entity.contract.ContractFiling;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractBorrowEndAction", description = "流程结束动作", workflowName = "合同借阅")
@Component(value = "contractBorrowEndAction")
public class ContractBorrowEndAction implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractChangeService")
	private ContractChangeService contractChangeService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		/*
		String borrow_id=variablesMap.get("proj_info.borrower");
		User borrow=this.baseService.findEntityByID(User.class, borrow_id);
		
		String borrowdate=variablesMap.get("proj_info.borrowdate");
		String borrowtype=variablesMap.get("rawValue_proj_info.borrowtype");*/
		/*//保存信息
		String json_contract_borrow_str=variablesMap.get("json_contract_borrow_str");
		saveDocList(borrow, borrowdate, json_contract_borrow_str,borrowtype);*/
		
		/*//保存放款清单借阅信息
		String json_fundput_file_borrow_str=variablesMap.get("json_fundput_file_borrow_str");
		saveDocList(borrow, borrowdate, json_fundput_file_borrow_str,borrowtype);*/
		//保存图书的借阅状态
		String isagree = variablesMap.get("processedResult");
		if("JbpmCommonAdvise_agree".equals(isagree)){//同意借阅
	    	variablesMap.put("borrowingStatus", "1");
	    	String json_contract_borrow_str=variablesMap.get("json_contract_borrow_str");
			saveContractBorrowHis(json_contract_borrow_str,variablesMap);
		}else{
			variablesMap.put("borrowingStatus", "2");//不同意借阅
		}
		
		
	}
	private void saveContractBorrowHis(String jsonstr,Map<String, String> variablesMap) throws JSONException, Exception {
		JSONArray jsonArray =new JSONArray(jsonstr);
		List<ContractBorrowHis> list=new ArrayList<ContractBorrowHis>();
		User user  =  SecurityUtil.getPrincipal();//当前人
		for (int i = 0; i < jsonArray.length(); i++) {
			ContractBorrowHis cb=new ContractBorrowHis();
			JSONObject jsonObject =(JSONObject) jsonArray.get(i);
			cb.setContractFilingId(this.baseService.findEntityByID(ContractFiling.class,jsonObject.getString("contractfilingid")));
		    cb.setDocSubName(jsonObject.getString("docsubname")); 
		    cb.setBorrow(jsonObject.getString("borrow"));
		    cb.setBorrowdate(jsonObject.getString("borrowdate"));
		    cb.setMemoborrow(jsonObject.getString("memoborrow"));
			cb.setBorrowingType(variablesMap.get("conotract_info.borrowingtype"));//保存内部或外部借阅
			cb.setBorrowingObject(variablesMap.get("conotract_info.borrowingobject"));//保存借阅对象档案室或保险柜
			cb.setFileStatus("1");//保存流程结束状态
			cb.setCreator(user);
			cb.setCreateDate(DateUtil.getSystemDateTime());
			cb.setBorrowingStatus(variablesMap.get("borrowingStatus"));//保存借阅或者归还
			list.add(cb);
		}
		if(list.size()>0){
		this.baseService.saveAllEntities(list);}
	}
	/*private void saveDocList(User borrow, String borrowdate, String jsonstr,String borrowtype) throws JSONException, Exception {
		JSONArray jsonArray = new JSONArray(jsonstr);
		List<ContractDocListBorrow> list=new ArrayList<ContractDocListBorrow>();
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject =(JSONObject) jsonArray.get(i);
			//保存借出文件信息
			if("1".equals(jsonObject.getString("ischeck"))){
				ContractDocList cl=this.baseService.findEntityByID(ContractDocList.class, jsonObject.getString("id"));
				
				propertiesMap.put("ContractDocListId", cl);
				List<ContractDocListBorrow> listcb=this.baseService.findEntityByProperties(ContractDocListBorrow.class, propertiesMap);
				if(listcb.size()>0){
					ContractDocListBorrow cb=listcb.get(0);
					cb.setBorrow(borrow);
					cb.setBorrowdate(borrowdate);
					cb.setBorrowtype(borrowtype);
					cb.setMemoborrow(jsonObject.getString("memoborrow"));
					cb.setPrebackdate(jsonObject.getString("prebackdate"));
					cb.setIsback("已借出");
					list.add(cb);
				}else{
					ContractDocListBorrow cb=new ContractDocListBorrow();
					cb.setContractDocListId(cl);
					cb.setBorrow(borrow);
					cb.setBorrowdate(borrowdate);
					cb.setBorrowtype(borrowtype);
					cb.setMemoborrow(jsonObject.getString("memoborrow"));
					cb.setPrebackdate(jsonObject.getString("prebackdate"));
					cb.setIsback("已借出");
					list.add(cb);
				}
				
			}
		}
		this.baseService.saveOrUpdateAllEntities(list);
	}*/

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
