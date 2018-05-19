package com.tenwa.leasing.controller.contract;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.PurchaseContractFundFundPlan;
import com.tenwa.leasing.entity.proj.ContractNumberSetting;
import com.tenwa.leasing.entity.proj.ContractNumberSettingTmp;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.entity.proj.ProjDevelopInfohis;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.proj.ProjEquipTmp;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.SelectionReport;
@Controller(value = "ConAppStartActionCheckController")
@RequestMapping(value = "/**/acl")
public class ConAppStartActionCheckController {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@RequestMapping(value = "/loadContractName.acl")
	@ResponseBody
	public String  LoadContractName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			String conid = request.getParameter("conid");
			ContractInfo contractinfo1 = new ContractInfo();
			contractinfo1.setId(conid);
			Map<String,Object> propertiesMap = new HashMap<String,Object>();
			propertiesMap.put("contractId", contractinfo1);
			List<ContractNumberSetting>  cnstlist1=this.tableService.findEntityByProperties(ContractNumberSetting.class, propertiesMap);
			System.err.println(cnstlist1.get(0).getContractName());
			return cnstlist1.get(0).getContractName().toString();
		}catch (Exception e) {
			return null;
		}
	};
	
	
	@RequestMapping(value = "/lvaluationModel.acl")
	@ResponseBody
	public String  LvaluationModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			String projid = request.getParameter("projid");
			String parameterSelection = request.getParameter("parameterSelection");//标准小时数
			ProjInfo projinfo = this.tableService.findEntityByID(ProjInfo.class,projid);
	    	Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("projId",projinfo.getDevelopid());
	    	List<SelectionReport> selectionreport = this.tableService.findEntityByProperties(SelectionReport.class, propertiesMap);
	    	if(selectionreport.size()>=1){
	    		if("2".equals(parameterSelection)) {//2
	    			if(selectionreport.get(0).getPninetyhours()==null){
	    				return "0";
	    			}
	    			 
				}else if("3".equals(parameterSelection)){//3
					if(selectionreport.get(0).getPseventyfivehours()==null){
						return "0";
					}
					
				}else if("4".equals(parameterSelection)){//4
                    if(selectionreport.get(0).getPfiftyhours()==null){
                    	return "0";
					}
					
				}else if("1".equals(parameterSelection)){//1
                    if(selectionreport.get(0).getAveStandard()==null){
                    	return "0";
					}
				}
				return "1";
			}else{
				return "0";
			}
		}catch (Exception e) {
			return null;
		}
	};
	
	
	
	
	
	@RequestMapping(value = "/ContractApprovalStartActionCheck.acl")
	@ResponseBody
	public int ContractApprovalStartActionCheck(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			String num = request.getParameter("nowConNum");
			Map<String,Object>propertiesMap=new HashMap<String,Object>();
			List<ContractNumberSettingTmp>  cnstlist=this.tableService.findEntityByProperties(ContractNumberSettingTmp.class, propertiesMap);
			for(ContractNumberSettingTmp cnst:cnstlist){
				if(num.equals(cnst.getContractNumber())){
					return 0;
				}
			}
			return 1;
		}catch (Exception e) {
			return 2;
		}
		
		
		
	};
	@RequestMapping(value = "/CheckTariffRecord.acl")
	@ResponseBody
	public String CheckTariffRecord(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			String benchmarkpriceestr = request.getParameter("benchmarkprice");
			String internetpricestr = request.getParameter("internetprice");
			BigDecimal benchmarkpricee=new BigDecimal(benchmarkpriceestr); 
			BigDecimal internetprice=new BigDecimal(internetpricestr);
			String id = request.getParameter("id");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String dfStr = df.format(new Date());// new Date()为获取当前系统时间
			ProjDevelopInfo projdevelopinfo = this.tableService.findEntityByID(ProjDevelopInfo.class, id);
			ProjDevelopInfohis projdevelopinfohis = new ProjDevelopInfohis();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(projdevelopinfo, projdevelopinfohis);
			projdevelopinfohis.setBenchmarkPrice(benchmarkpricee);
			projdevelopinfohis.setInternetPrice(internetprice);
			projdevelopinfohis.setCreateDate(dfStr);
			this.tableService.saveEntity(projdevelopinfohis);
			return "记录成功";
		}catch (Exception e) {
			return "记录失败";
		}
	}
	@RequestMapping(value = "/savePurchaseContractFundFundPlan.acl")
	@ResponseBody
	public String savePurchaseContractFundFundPlan(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			User user  =  SecurityUtil.getPrincipal();//当前人
			String edit = request.getParameter("edit");
			String fundid = request.getParameter("rowvalue");
			String purchasecontractid = request.getParameter("purchasecontractid");
			Map<String,Object>propertiesMap=new HashMap<String,Object>();
			propertiesMap.put("fundPlanId", fundid);
			 SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
			List<PurchaseContractFundFundPlan> pcffplist = this.tableService.findEntityByProperties(PurchaseContractFundFundPlan.class, propertiesMap);
            if("yes".equals(edit)){
            	PurchaseContractFundFundPlan pcffpedit = pcffplist.get(0);
            	pcffpedit.setPurchaseContractid(purchasecontractid);
            	pcffpedit.setModifyDate(sdf.format(new Date()));
            	pcffpedit.setModificator(user);
            	this.tableService.updateEntity(pcffpedit);
            	return "修改成功";
			}else{
				if(pcffplist.size()>0){
					return "已经添加";
				};
				PurchaseContractFundFundPlan pcffp = new PurchaseContractFundFundPlan();
				pcffp.setPurchaseContractid(purchasecontractid);
				pcffp.setFundPlanId(fundid);
				pcffp.setCreateDate(sdf.format(new Date()));
				pcffp.setCreator(user);
				this.tableService.saveOrUpdateEntity(pcffp);
				return "记录成功";
			}
					}catch (Exception e) {
			return "记录失败";
		}
	}
	@RequestMapping(value = "/savePurchaseContractFundFundcondition.acl")
	@ResponseBody
	public String savePurchaseContractFundFundcondition(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			User user  =  SecurityUtil.getPrincipal();//当前人
			String edit = request.getParameter("edit");
			String fundid = request.getParameter("contractplanid");//编辑框内的计划
			String purchasecontractid = request.getParameter("purchasecontractid");
			Map<String,Object>propertiesMap=new HashMap<String,Object>();
			propertiesMap.put("fundPlanId", fundid);
			propertiesMap.put("purchaseContractid", purchasecontractid);			
			 SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
			 //无论是修改还是新增 查数据库信息是否已 存在，如果已存在，则返回"已经存在"
			List<PurchaseContractFundFundPlan> pcffplist = this.tableService.findEntityByProperties(PurchaseContractFundFundPlan.class, propertiesMap);
            if("yes".equals(edit)){  
				if(pcffplist.size()>0){
					return "已经添加";
				};
    			String fundplanid=request.getParameter("fundplanid");//展示出来的已添加的计划
    			propertiesMap.clear();
    			propertiesMap.put("fundPlanId", fundplanid);
    			propertiesMap.put("purchaseContractid", purchasecontractid);
    			List<PurchaseContractFundFundPlan> editpcffplist = this.tableService.findEntityByProperties(PurchaseContractFundFundPlan.class, propertiesMap);
            	PurchaseContractFundFundPlan pcffpedit = editpcffplist.get(0);
            	pcffpedit.setPurchaseContractid(purchasecontractid);
            	pcffpedit.setFundPlanId(fundid);
            	pcffpedit.setModifyDate(sdf.format(new Date()));
            	pcffpedit.setModificator(user);
            	this.tableService.updateEntity(pcffpedit);
            	return "修改成功";
			}else{
				if(pcffplist.size()>0){
					return "已经添加";
				};
				PurchaseContractFundFundPlan pcffp = new PurchaseContractFundFundPlan();
				pcffp.setPurchaseContractid(purchasecontractid);
				pcffp.setFundPlanId(fundid);
				pcffp.setCreateDate(sdf.format(new Date()));
				pcffp.setCreator(user);
				this.tableService.saveOrUpdateEntity(pcffp);
				return "记录成功";
			}
					}catch (Exception e) {
			return "记录失败";
		}
	}
}
