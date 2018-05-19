package com.tenwa.leasing.action.windfarmreport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.proj.WindFarmReportData;
import com.tenwa.leasing.service.contract.onhireContractChange.OnhireContractChangeService;
import com.tenwa.leasing.service.vouchersFactory.ContractAssetsChangeVoucherService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * @author wwc
 * @date 2017-3-6
 * @info
 */
@WorkflowAction(name = "windFarmReportEndAction", description = "流程结束动作", workflowName = "风电场报告")
@Component(value = "windFarmReportEndAction")
public class WindFarmReportEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "contractAssetsChangeVoucherService")
	private ContractAssetsChangeVoucherService contractAssetsChangeVoucherService;
	
	@Resource(name = "onhireContractChangeService")
	private OnhireContractChangeService onhireContractChangeService;

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// 获取参数
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "";
		variablesMap.put("docId", doc_id);		
		User user  =  SecurityUtil.getPrincipal();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		WindFarmReportData windfarmreportdata = new WindFarmReportData();
		windfarmreportdata.setDocid(doc_id);
		windfarmreportdata.setProjid(variablesMap.get("selectedprojid"));
		windfarmreportdata.setReportdate(variablesMap.get("reportDate"));
		//运行状况分析
		windfarmreportdata.setStatusanalysis(variablesMap.get("statusanalysis"));
		//故障数据分析
		windfarmreportdata.setDataanalysis(variablesMap.get("dataanalysis"));
		//损失电量分析
		windfarmreportdata.setLossanalysis(variablesMap.get("lossanalysis"));
	    //功率曲线分析
		windfarmreportdata.setCurveanalysis(variablesMap.get("curveanalysis"));
		//结论/关注点
		windfarmreportdata.setConclusion(variablesMap.get("conclusion"));
		//改进措施与建议
		windfarmreportdata.setSuggestions(variablesMap.get("suggestions"));
		windfarmreportdata.setCreator(user);
		windfarmreportdata.setCreateDate(df.format(new Date()));
		this.baseService.saveEntity(windfarmreportdata);
	
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
