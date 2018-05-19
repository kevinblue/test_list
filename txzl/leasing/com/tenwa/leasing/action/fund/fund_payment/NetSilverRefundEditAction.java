package com.tenwa.leasing.action.fund.fund_payment;
import java.math.BigDecimal;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;

@WorkflowAction(name = "netSilverRefundEditAction", description = "第一步保存网银信息", workflowName = "网银退还流程")
@Component(value = "netSilverRefundEditAction")
public class NetSilverRefundEditAction implements JbpmBaseAction{
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void start(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		   //历史退还金额
            String alreadyrefund = variablesMap.get("fund_ebank_data.alreadyrefundamount");
            //历史退还备注
		    String hisrefund = variablesMap.get("fund_ebank_data.hisrefundsummary");
		    //本次退还金额
		    String refundamount =variablesMap.get("fund_ebank_data.refundamount");
		    //本次退还备注
		    String summary = variablesMap.get("fund_ebank_data.refundsummary");
		    if(alreadyrefund==null){
		    	alreadyrefund = "0";
		    }
		    if(hisrefund==null){
		    	hisrefund="";
		    }
		    long alreadyrefundp = Long.parseLong(alreadyrefund);
		     long refundamountp  = Long.parseLong(refundamount);
		    //拿到本次退还金额与历史退还金额的总和
		    long sum = alreadyrefundp+refundamountp;
		    String sumsummary =hisrefund+summary+"【备注信息】"+"\r\n";
		    variablesMap.put("sum", String.valueOf(sum));
		    variablesMap.put("sumsummary", sumsummary);
		    BigDecimal bd = new BigDecimal(refundamountp);
		    FundEbankProcess fundEbankProcess = (FundEbankProcess) this.tableService.findEntityByID(FundEbankProcess.class, variablesMap.get("fund_ebank_process"));
			fundEbankProcess.setProcessAmount(bd);
		    fundEbankProcess.setRefundAmount(bd);
			fundEbankProcess.setRefundSummary(summary);
			this.tableService.updateEntity(fundEbankProcess);
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
