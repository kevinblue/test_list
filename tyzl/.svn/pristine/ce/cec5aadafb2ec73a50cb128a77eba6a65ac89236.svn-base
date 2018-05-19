package com.reckon.service.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.entity.contract.reckon.fund.FundFundPlanConfig;
import com.reckon.service.FundFundChargePlanService;
import com.reckon.util.MoneyUtils;
import com.reckon.util.Tools;
import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

@Service(value = "fundFundChargePlanService")
public class FundFundChargePlanServiceImpl implements FundFundChargePlanService {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	private static Logger log = Logger.getLogger(FundFundChargePlanServiceImpl.class);
	
	/*
	 * @author sea
	 */
	public Map<String, Object> createFundChargePlan(ConditionBean condition) throws Exception {
		List<FundPlanBean> ffcpList = new ArrayList<FundPlanBean>();
		
		List<FundFundPlanConfig> configs =  this.tableService.findEntities(FundFundPlanConfig.class);
		//合同号
		String contractId = condition.getContractId() == null ? "" :  condition.getContractId();
		//项目号
		String projId = condition.getProjId() == null ? "" :  condition.getProjId() ;
		//客户编号
		String custId =  condition.getCustId() == null ? "" : condition.getCustId();
		//文档号
		String docId = condition.getDocId();
		//客户名称
		String custName = "";
		String businessId = contractId;
		CustInfo cust = null;
		if(contractId != null && !contractId.isEmpty()){
			businessId = contractId;
			ContractInfo ci = tableService.findEntityByID(ContractInfo.class, contractId);
			if(ci != null){
				cust = ci.getCustId(); 
				businessId = ci.getContractId();
			}
		} else if(projId != null && !projId.isEmpty()){
			businessId = projId;
			ProjInfo pi = tableService.findEntityByID(ProjInfo.class, projId);
			if(pi != null){
				cust = pi.getCustInfo(); 
				businessId = pi.getProjId();
			}
		}
		if(cust == null ){
			cust = tableService.findEntityByID(CustInfo.class, custId);
		}
		custName = cust.getCustName();
		
		//循环构建资金计划的对象集合FundPlanBean
		for (int i = 0; i < configs.size(); i++) {
			FundFundPlanConfig config = configs.get(i);
			//取值
			String temp = "";
			Object obj = null;
			obj  = this.getCondtionInfo(condition, config.getFeeTypeFied());
			if(obj instanceof String){
				temp = (String)obj;
			}
			if(obj instanceof BigDecimal){
				temp = ((BigDecimal)obj).toString();
			}
			if (temp != null && !temp.equals("")) {
				if (Double.parseDouble(temp) > 0) {
					//金额要做保留2位四舍五入处理
					temp = MoneyUtils.formatNumberDoubleTwo(temp);
						
					FundPlanBean ffcp = new FundPlanBean();
					ffcp.setContractId(businessId);
					ffcp.setDocId(docId);
					//ffcp.setPlanDate(conditionMap.get("framework_condition." + feeTypeDate[i]));
					//开始日期和结束日期的确定
					if(config.getFeeTypeDate().equalsIgnoreCase("startdate")){
						ffcp.setPlanDate(condition.getStartDate());
					}else if(config.getFeeTypeDate().equalsIgnoreCase("endDate")){
						ffcp.setPlanDate(condition.getEndDate());
					}else if(config.getFeeTypeDate().equalsIgnoreCase("leaseamtdate")){//TODO 其他方式的待定
						ffcp.setPlanDate(condition.getLeaseAmtDate());
					}else{
						ffcp.setPlanDate("");
					}
					ffcp.setFieldName(config.getFeeTypeFied());
					ffcp.setPlanMoney(temp);
					ffcp.setFeeTypeName(config.getFeeTypeName());
					ffcp.setFeeType(config.getFeeType());//款项名称
					ffcp.setPayTypeName(config.getPayTypeName());
					ffcp.setPayType(config.getPayTypeCode());
					ffcp.setPaymentId(config.getPaymentNumb());
					ffcp.setSettleMethod(config.getSettleMethod());
                    ffcp.setSettleMethodName(config.getSettleMethodName());
                    
                    //资金收付款对象
					if (config.getPayTypeCode().equalsIgnoreCase("pay_type_in")) {
						ffcp.setPayObj(custName);//客户名称
						ffcp.setPayCust(cust.getId());
						ffcp.setPayCustName(custName);
					}  
					if (config.getPayTypeCode().equalsIgnoreCase("pay_type_out")) {
						ffcp.setPayObj("");//公司名称 由于无法确定流出的对象，值为空
					}
					ffcp.setIsPrePay("否");//
					ffcpList.add(ffcp);
				}
			}
		}
		Collections.sort(ffcpList);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("cust",cust);
		result.put("fundchargeplan", ffcpList);
		return result;
	}
	
	
	public Map<String, Object> createFundPlan(ConditionBean conditon, String lastRent) throws Exception {
		Map<String, Object> result  = createFundChargePlan(conditon);
		List<FundPlanBean> ffcpList = (List<FundPlanBean>)result.get("fundchargeplan");
		//修改保证金抵扣金额 默认等于最后一期租金
		for(FundPlanBean fb:ffcpList){
			if("feetype16".equals(fb.getFeeType())){//抵扣保证金
				BigDecimal money=new BigDecimal(conditon.getCautionMoney()).subtract(new BigDecimal(lastRent));
				if(money.compareTo(BigDecimal.ZERO)>0){
					fb.setPlanMoney(lastRent);
				}else{
					fb.setPlanMoney(conditon.getCautionMoney());
				}
				conditon.setCautionDeductionMoney(fb.getPlanMoney());
				conditon.setCautionMoneyRemain(new BigDecimal(conditon.getCautionMoney()).subtract(new BigDecimal(conditon.getCautionDeductionMoney())).toString());
			}
		}
		result  = createFundChargePlan(conditon);
		ffcpList = (List<FundPlanBean>)result.get("fundchargeplan");
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("fundchargeplan", ffcpList);
		returnMap.put("cust", result.get("cust"));
		//如果第一次的计算资金计划，插入到临时表当中
		
		return returnMap;
	}
	
	private Object getCondtionInfo(ConditionBean condition , String fieldName) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(condition.getClass());
		PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
		Object returnInfo = null; 
		for(PropertyDescriptor property : properties){
			String tempFildName = property.getName();
			if(tempFildName.equalsIgnoreCase(fieldName)){
				Method method = property.getReadMethod();
				returnInfo = method.invoke(condition); 
			}
		}
		return returnInfo;
	}
	
}
