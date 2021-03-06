package com.tenwa.leasing.action.contract.transfer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractBorrowHis;
import com.tenwa.leasing.entity.contract.ContractFiling;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "fileTransferEndAction", description = "流程结束动作", workflowName = "档案交接流程")
@Component(value = "fileTransferEndAction")
public class FileTransferEndAction implements JbpmBaseAction {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
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
		//拿合同号，妈的实体类千万别建这个外键关联 很烦的
		String cid = variablesMap.get("contract_info.id");
		ContractInfo ci = this.tableService.findEntityByID(ContractInfo.class	, cid);
		String json_handover_str=variablesMap.get("json_this_handover_str");
		JSONArray jsonArray =new JSONArray(json_handover_str);
		List<ContractBorrowHis> list=new ArrayList<ContractBorrowHis>();
		List<ContractFiling> listContractFiling=new ArrayList<ContractFiling>();
		User user  =  SecurityUtil.getPrincipal();//当前人
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String nowdate = df.format(new Date());
		for (int i = 0; i < jsonArray.length(); i++) {
			
			 ContractBorrowHis contractborrowhis=new ContractBorrowHis();
			JSONObject  jsonobject = (JSONObject) jsonArray.get(i);
             contractborrowhis.setDocSubName(jsonobject.get("docsubname").toString());
             contractborrowhis.setDocNumber(jsonobject.get("docnumber").toString());
             contractborrowhis.setDocName(jsonobject.get("docname").toString());
             contractborrowhis.setHandoverDate(jsonobject.get("handoverdate").toString());
             String username = jsonobject.get("handovermanname").toString();
             List<User> userlist = this.tableService.findResultsByHSQL("from User u  where u.realname = ?", username);
             String  userid = null;
             User userin = null;
             if(userlist.size()!=0){
            	 for(User u :userlist){
            		userid = u.getId();
            	 }
            	userin = this.tableService.findEntityByID(User.class, userid);
             }
             contractborrowhis.setHandoverMan(userin);
             contractborrowhis.setDocMemo(jsonobject.get("docmemo").toString());
             contractborrowhis.setId(jsonobject.get("_id").toString());
             contractborrowhis.setDocSubName(jsonobject.get("_uid").toString());
             contractborrowhis.setFileStatus("3");
             contractborrowhis.setContractNumberId(ci);
             contractborrowhis.setCreateDate(nowdate);
             contractborrowhis.setCreator(user);
             list.add(contractborrowhis);
             //将交接的文件连接到档案归档，且归档为保险柜的链接权限只有财务部有权限，其余的为资产部门
            if(jsonobject.get("filing").equals("档案柜")){
             ContractFiling contractFiling=new ContractFiling();
             //档案编号
             contractFiling.setFilingName(jsonobject.get("docname").toString());
             contractFiling.setFillingNumber(jsonobject.get("docnumber").toString());
             contractFiling.setContainerNumber(jsonobject.get("filing")==null?"":jsonobject.get("filing").toString());
             contractFiling.setNote("原件");
             contractFiling.setContractId(ci);
             //contractFiling.setFillingUser(userin);
             contractFiling.setCreator(user);
             contractFiling.setCreateDate(nowdate);
             listContractFiling.add(contractFiling);
            }else if(jsonobject.get("filing").equals("保险柜")){
            	 ContractFiling contractFiling=new ContractFiling();
                 //档案编号
                 contractFiling.setFilingName(jsonobject.get("docname").toString());
                 contractFiling.setFillingNumber(jsonobject.get("docnumber").toString());
                 contractFiling.setContainerNumber(jsonobject.get("filing")==null?"":jsonobject.get("filing").toString());
                 contractFiling.setNote("原件");
                 contractFiling.setContractId(ci);
                 //contractFiling.setFillingUser(userin);
                 contractFiling.setCreator(user);
                 contractFiling.setCreateDate(nowdate);
                 listContractFiling.add(contractFiling);
            }
            
		};
		if(listContractFiling.size()>0){
			this.tableService.saveAllEntities(listContractFiling);
		}
		this.tableService.saveAllEntities(list);
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		}
	
	
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}
}
