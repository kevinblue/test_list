package com.tenwa.leasing.controller.rent.contractmatching;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.User;
import com.tenwa.business.entity.WorkFlowFlag;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;


@Controller(value = "contractMatchingController")
@RequestMapping(value = "/**/acl")
public class ContractMatchingController {

	private Logger logger=Logger.getLogger(ContractMatchingController.class);
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;
		
	//合同匹配
	@RequestMapping(value = "/checkContractMatching.acl")
	@ResponseBody
	public String checkContractMatching(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 String factdate =  model.get("factdate");
		 
		 User user = new User();
		 user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取登录人的uuid
		 System.out.println("call proc_plhx_match('"+UUID.randomUUID()+"','"+factdate+"','"+user.getId()+"')");
		 this.baseService.getBussinessDao().getJdbcTemplate().execute("call proc_plhx_match('"+UUID.randomUUID()+"','"+factdate+"','"+user.getId()+"')");
	     return null;
	}

	
	
	//合同 批量核销
	@RequestMapping(value = "/deleteCheckedRecors.acl")
	@ResponseBody
	public String deleteCheckedRecors(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		 
		 User user = new User();
		 user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取登录人的uuid
		 String uuid = String.valueOf( UUID.randomUUID() );
		 System.out.println("call proc_plhx_hire('"+uuid+"','"+user.getId()+"')");
		 this.baseService.getBussinessDao().getJdbcTemplate().execute("call proc_plhx_hire('"+uuid+"','"+user.getId()+"')");
		 model.put("docId",uuid);
		 //TODO 调用批量核销
/*		 if(ResourceUtil.getConfigValue("call.voucher.interface.switch").equals("true")){
				HessianProxyFactory factory = new HessianProxyFactory();
				CreateVoucherService service  = (CreateVoucherService)factory.create(CreateVoucherService.class, ResourceUtil.getConfigValue("call.voucher.interface.url"));
				service.rentIncomeBatch(model);
			}*/
	     return null;
	}
	//合同 手工核销
	@RequestMapping(value = "/manualHire.acl")
	@ResponseBody
	public String manualHire(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String ebid = model.get("ebid");
		User user = new User();
		user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取登录人的uuid
		String uuid = String.valueOf( UUID.randomUUID() );
		System.out.println("call proc_manual_hire('"+uuid+"','"+user.getId()+"','"+ebid+"')");
		this.baseService.getBussinessDao().getJdbcTemplate().execute("call proc_manual_hire('"+uuid+"','"+user.getId()+"','"+ebid+"')");
		model.put("docId",uuid);
		return null;
	}
	//合同 手工核销
	@RequestMapping(value = "/manualHirePenalty.acl")
	@ResponseBody
	public String manualHirePenalty(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String ebid = model.get("ebid");
		User user = new User();
		user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取登录人的uuid
		String uuid = String.valueOf( UUID.randomUUID() );
		System.out.println("call proc_manual_hire_penalty('"+uuid+"','"+user.getId()+"','"+ebid+"')");
		this.baseService.getBussinessDao().getJdbcTemplate().execute("call proc_manual_hire_penalty('"+uuid+"','"+user.getId()+"','"+ebid+"')");
		model.put("docId",uuid);
		return null;
	}
	
	/**
	 * <p>根据核销信息去产生核销凭证。</p>
	 * <p>该方法参考租金回笼流程的END方式rentHireEndAction，只是再单个租金回笼核销产生凭证的地方做操作。</p>
	 * @author sea
	 * @param incomeObj_l
	 * @throws Exception 
	 */
	private void saveRentHireVoucherByPl(List<ContractFundRentInCome> list) throws Exception{
		
	}
	//合同匹配
	@RequestMapping(value = "/backMatched.acl")
	@ResponseBody
	public String backMatched(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String procids = model.get("procids");
		String ebid    = model.get("ebid");
		List<FundEbankProcess>     procList = new ArrayList<FundEbankProcess>();
		String[] procAry = procids.split(",");
		procList = this.tableService.findEntityByIDArray(FundEbankProcess.class, procAry);
		if(null!=procList && procList.size()>0){
			this.tableService.removeAllEntites(procList);
		}
		//删除workflag
		FundEbankData data = this.tableService.findEntityByID(FundEbankData.class, ebid);
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("workFlowInstance", data.getEbdataId());
		propertiesMap.put("workFlowName", "手工匹配");
		propertiesMap.put("keyTwo", "手工匹配-租金");
		List<WorkFlowFlag> workflowFlags = this.tableService.findEntityByProperties(WorkFlowFlag.class, propertiesMap);
		if(null != workflowFlags && workflowFlags.size()>0){
			this.tableService.removeAllEntites(workflowFlags);
		}
	     return null;
	}
	//确认匹配
	@RequestMapping(value = "/confirmMatched.acl")
	@ResponseBody
	public String confirmMatched(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model  =QueryUtil.getRequestParameterMapByAjax(request);
		String ebid               = model.get("ebid");
		String planidsStr         = model.get("planids");
		String rentOverage        = model.get("currentoverage");
		BigDecimal mayopemoney    = new BigDecimal(model.get("mayopemoney"));
		BigDecimal processmoney   = new BigDecimal(model.get("processmoney"));
		List<FundEbankProcess> procList = new ArrayList<FundEbankProcess>();
		FundEbankData ebank = this.tableService.findEntityByID(FundEbankData.class, ebid);
		WorkFlowFlag workflowFlag = new WorkFlowFlag();
		workflowFlag.setWorkFlowInstance(ebank.getEbdataId());
		workflowFlag.setWorkFlowName("手工匹配");
		workflowFlag.setKeyTwo("手工匹配-租金");
		workflowFlag.setStatus(1);
		workflowFlag.setBeginUser(model.get("login_userid"));
		if(null!=planidsStr && !"".equals(planidsStr)){
			String[] planAry        = planidsStr.split(",");
			String[] rentOverageAry = rentOverage.split(",");
			//planList = this.tableService.findEntityByIDArray(ContractFundRentPlan.class, planAry);
			//findEntityByIDArray 返回结果没有排序
			for(int i=0;i<planAry.length;i++){
				FundEbankProcess proc = new FundEbankProcess();
				ContractFundRentPlan rentplan = this.tableService.findEntityByID(ContractFundRentPlan.class, planAry[i]);
				proc.setContractId(rentplan.getContractId().getId());
				proc.setEbdataId(ebank);
				proc.setFlowUnid(UUIDUtil.getUUID());
				proc.setProcessName("手工匹配");
				proc.setStartDate(DateUtil.getSystemDateTime());
				proc.setWork_flag("0");
				BigDecimal processAmountTemp = BigDecimal.ZERO;
				if((mayopemoney.subtract(processmoney)).compareTo(new BigDecimal(rentOverageAry[i]))>0){
					//可核销金额-匹配金额>租金余额
					processAmountTemp = new BigDecimal(rentOverageAry[i]);
					proc.setProcessAmount(processAmountTemp);
					proc.setPlanId(rentplan);
					proc.setRentOrPenalty("0");
					processmoney = processmoney.add(processAmountTemp);
					procList.add(proc);
				}else{
					processAmountTemp = mayopemoney.subtract(processmoney);
					proc.setProcessAmount(processAmountTemp);
					proc.setPlanId(rentplan);
					proc.setRentOrPenalty("0");
					procList.add(proc);
					break;
				}
			}
		}
		this.tableService.saveAllEntities(procList);
		this.tableService.saveEntity(workflowFlag);
		return null;
	}
	//罚息合同匹配
	@RequestMapping(value = "/backPenaltyMatched.acl")
	@ResponseBody
	public String backPenaltyMatched(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String procids = model.get("procids");
		String ebid    = model.get("ebid");
		List<FundEbankProcess>     procList = new ArrayList<FundEbankProcess>();
		String[] procAry = procids.split(",");
		procList = this.tableService.findEntityByIDArray(FundEbankProcess.class, procAry);
		if(null!=procList && procList.size()>0){
			this.tableService.removeAllEntites(procList);
		}
		//删除workflag
		FundEbankData data = this.tableService.findEntityByID(FundEbankData.class, ebid);
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("workFlowInstance", data.getEbdataId());
		propertiesMap.put("workFlowName", "手工匹配");
		propertiesMap.put("keyTwo", "手工匹配-罚息");
		List<WorkFlowFlag> workflowFlags = this.tableService.findEntityByProperties(WorkFlowFlag.class, propertiesMap);
		if(null != workflowFlags && workflowFlags.size()>0){
			this.tableService.removeAllEntites(workflowFlags);
		}
		return null;
	}
	//确认匹配
	@RequestMapping(value = "/confirmPenaltyMatched.acl")
	@ResponseBody
	public String confirmPenaltyMatched(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String ebid            = model.get("ebid");
		String planidsStr      = model.get("planids");
		String penaltyStr      = model.get("penalty");
		BigDecimal mayopemoney    = new BigDecimal(model.get("mayopemoney"));
		BigDecimal processmoney   = new BigDecimal(model.get("processmoney"));
		List<ContractFundRentPlan> planList = new ArrayList<ContractFundRentPlan>();
		List<FundEbankProcess>     procList = new ArrayList<FundEbankProcess>();
		FundEbankData ebank = this.tableService.findEntityByID(FundEbankData.class, ebid);
		
		WorkFlowFlag workflowFlag = new WorkFlowFlag();
		workflowFlag.setWorkFlowInstance(ebank.getEbdataId());
		workflowFlag.setWorkFlowName("手工匹配");
		workflowFlag.setKeyTwo("手工匹配-罚息");
		workflowFlag.setStatus(1);
		workflowFlag.setBeginUser(model.get("login_userid"));
		if(null!=planidsStr && !"".equals(planidsStr)){
			String[] planAry    = planidsStr.split(",");
			String[] penaltyAry = penaltyStr.split(",");
			planList = this.tableService.findEntityByIDArray(ContractFundRentPlan.class, planAry);
			for(int i=0;i<planList.size();i++){
				FundEbankProcess proc = new FundEbankProcess();
				ContractFundRentPlan plan = planList.get(i);
				proc.setContractId(plan.getContractId().getId());
				proc.setEbdataId(ebank);
				proc.setFlowUnid(UUIDUtil.getUUID());
				proc.setProcessName("手工匹配");
				proc.setStartDate(DateUtil.getSystemDateTime());
				proc.setWork_flag("0");
				BigDecimal processAmountTemp = BigDecimal.ZERO;
				if((mayopemoney.subtract(processmoney)).compareTo(new BigDecimal(penaltyAry[i]))>0){
					proc.setProcessAmount(new BigDecimal(penaltyAry[i]));
					proc.setPlanId(plan);
					proc.setRentOrPenalty("1");//表示罚息
					procList.add(proc);
					processmoney = processmoney.add(processAmountTemp);
				}else{
					proc.setProcessAmount(mayopemoney.subtract(processmoney));
					proc.setPlanId(plan);
					proc.setRentOrPenalty("1");//表示罚息
					procList.add(proc);
					break;
				}
			}
		}
		this.tableService.saveAllEntities(procList);
		this.tableService.saveEntity(workflowFlag);
		return null;
	}
} 
