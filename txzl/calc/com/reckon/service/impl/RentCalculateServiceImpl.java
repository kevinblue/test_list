package com.reckon.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.FundRentPlanIrr;
import com.reckon.bean.IncomeDiscount;
import com.reckon.bean.IncomeDiscountBean;
import com.reckon.bean.KnowingRentBean;
import com.reckon.bean.RentPlan;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.utils.ExcelReader;
import com.reckon.calculation.utils.PMTCalculateUtil;
import com.reckon.calculation.utils.Scale;
import com.reckon.commons.helper.ObjectConvertUtils;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.controller.RentCalculateControllerHelper;
import com.reckon.convert.ConvertMapToBeanService;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.FundFundChargeDAOImpl;
import com.reckon.dao.impl.FundPlanDAOImpl;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.dao.impl.RentChargeDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.entity.contract.reckon.condition.SpecialRulesBean;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractSpecialRulesBean;
import com.reckon.entity.interest.FundStandardInterest;
import com.reckon.entity.proj.ProjFundRentPlanTemp;
import com.reckon.rentcalc.service.impl.pub.ConditionServiceImpl;
import com.reckon.rentcalc.service.impl.pub.FundFundChargeServiceImpl;
import com.reckon.rentcalc.service.impl.pub.PlanDateServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentalServiceImpl;
import com.reckon.rentcalc.web.RentCalcDo;
import com.reckon.rentcharge.web.RentChargeDo;
import com.reckon.rentcharge.web.RentPlanModDo;
import com.reckon.service.FundFundChargePlanService;
import com.reckon.service.IncomeDiscountService;
import com.reckon.service.RentCalculateService;
import com.reckon.util.DictTools;
import com.reckon.util.IrrTools;
import com.reckon.util.NumberUtils;
import com.reckon.util.TbBeanTools;
import com.reckon.util.Tools;
import com.reckon.util.tools.DateTools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.action.contract.approval.ContractApprovalStartAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.quot.CustCashDetail;
import com.tenwa.leasing.entity.cust.quot.CustCondition;
import com.tenwa.leasing.entity.cust.quot.CustFundRentPlan;
import com.tenwa.leasing.entity.fund.FinanceCashDetailTemp;
import com.tenwa.leasing.entity.fund.FinanceFundFundPlanTemp;
import com.tenwa.leasing.entity.fund.FinanceIncomeDiscountTemp;
import com.tenwa.leasing.utils.LeasingException;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 
 * @author 孙传良
 * @date 2013-3-19上午11:03:49
 * @info 租金测算的接口的实现类
 * @Copyright Tenwa
 */
@Service(value = "rentCalculateService")
public class RentCalculateServiceImpl implements RentCalculateService {
	
	private static Logger logger = Logger.getLogger(RentCalculateServiceImpl.class);
//	ConditionBean conditionBean = new ConditionBean();
	
	@Resource(name = "convertMapToBeanService")
	private ConvertMapToBeanService convertMapToBeanService;

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "fundFundChargePlanService")
	private FundFundChargePlanService fundFundChargePlanService;
	
	@Resource(name = "contractApprovalStartAction")
	private ContractApprovalStartAction contractApprovalStartAction;


	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public Map<String, Object> doCalculateRentPlanOld(Map<String, String> modelData,List<KnowingRentBean> kniwnRentBeans) throws Exception {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.putAll(modelData);
		ConditionBean cb = ObjectConvertUtils.convertMapToBean(ConditionBean.class, paramsMap);
		List<FundPutPlan> fpps=ObjectConvertUtils.convertObjectByJson(FundPutPlan.class, modelData.get("json_fund_put_config_str"));
		List<SpecialRulesBean> srbs=ObjectConvertUtils.convertObjectByJson(SpecialRulesBean.class, modelData.get("json_special_regular_str"));
		cb.setFpps(fpps);
		
		//合同号
		String contractId = modelData.get("contractid");
		String reckonType = modelData.get("reckontype");
		
		//立项时值是：cust_process, proj_process, contract_process
		String process = modelData.get("process");
		if(process.equals("quoted_price") && (cb.getDocId() == null || cb.getDocId().equals("null"))){
			//生成一个新的客户报价编号
			HibernateTemplate hibernateTemplate =  (HibernateTemplate)WebUtil.getApplicationContext().getBean("hibernateTemplate");
			JdbcTemplate jdbcTemplate =  (JdbcTemplate)WebUtil.getApplicationContext().getBean("jdbcTemplate");
			String docId = WorkflowUtil.getCustConditionSerialNumber(hibernateTemplate, jdbcTemplate);
			cb.setDocId(docId);
		}
		//项目号
		String projId = modelData.get("projid");
		//客户编号
		String custId = modelData.get("custid");
		//文档号
		String docId = modelData.get("docid");
		if(Tools.isNullOrEmpty(docId)){
			docId = "Test001";
			modelData.put("docid",docId);
		}
		
		/**测算前置条件3：
		 * 合同号为空则租金测算载体默认挂靠的是项目号
		 */
		if (contractId == null || contractId.equals("")) {
			cb.setProjId(projId);
		} else {//挂靠合同号
			cb.setProjId(contractId);
		}
		
		/**测算前置条件4：
		 * 获取任意现金流对应的按序租金JSON字符串
		 * 计算方式 为:已知租金法情况下 【注意这里取的是‘租金推算方法’而不是‘租金计算方式’】
		 */
		if("knowing_rent".equals(cb.getRentOrRate())){
			String tempAttr = modelData.get("json_knowing_rent_plan_str");
			kniwnRentBeans = this.getList(tempAttr);
		}else if("knowing_corpus".equals(cb.getRentOrRate())){
			String tempAttr = modelData.get("json_knowing_corpus_plan_str");
			kniwnRentBeans = this.getList(tempAttr);
		}
		
		/**测算前置条件5：
		 * 设置表名，下方构建返回值时会用到，用于判断表名及查询
		 */
		TabCalBean tcb = new TbBeanTools().getTabInfo(process, cb);
		//tcb.setCalType(process);//流程类型 和TbBeanTools绑定
		
		//租金调整的按钮
		List<String> adjustList = new ArrayList<String>();
		List<String> rentList = new ArrayList<String>();
		BigDecimal rentTotal = BigDecimal.ZERO;
		if (reckonType.equals("plan") || reckonType.equals("adjust")) {
			ObjectMapper jsonMapper = new ObjectMapper();
			//租金计划
			String fundRentPlan = modelData.get("json_fund_rent_plan_str");
			Map[] newSetMaps = jsonMapper.readValue(fundRentPlan, Map[].class);
			for (int j = 0; j < newSetMaps.length; j++) {
				Map<String, Object> map = newSetMaps[j];
				logger.info(map.get("rentadjust"));
				String rentAdjust = map.get("rentadjust")+"";
				adjustList.add(rentAdjust.equals("null") ? "" : rentAdjust);
			}
			Map<String, Object> returnInfo = RentCalculateControllerHelper.calculateYearRateFromKnowing(newSetMaps,cb);
			cb.setYearRate((String)returnInfo.get("yearRate"));
			rentList = (List<String>)returnInfo.get("rentList");
			if(reckonType.equals("plan")){
				RentPlanContrCalDAOImpl rpd = new RentPlanContrCalDAOImpl();
				rpd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);
				rpd.addRentPlanByJsonStr(tcb, fundRentPlan);
			}
		}else if("knowing_rent".equals(cb.getRentOrRate())){
			RentalServiceImpl rsi = new RentalServiceImpl();
			rentList  = rsi.getRentValue(kniwnRentBeans);
			for(String rent : rentList){
				rentTotal = rentTotal.add(new BigDecimal(rent));
			}
		}else if("rent".equals(cb.getRentOrRate())){
			rentTotal =  cb.getRentOrRateValue().multiply(new BigDecimal(cb.getIncomeNumber())).setScale(BigDecimal.ROUND_HALF_UP, Scale.RENT_SCALE);
		}else if("knowing_corpus".equals(cb.getRentOrRate())){
			RentalServiceImpl rsi = new RentalServiceImpl();
			rentList  = rsi.getRentValue(kniwnRentBeans);
		}
		//如果是按照年利率推算租金
		Boolean isCalculate = true;
		Map<String, Object> result = new HashMap<String, Object>();// 构建返回数据
		
		if(isCalculate){

			List<FundPlanBean> fundPlanList = this.getFundPlanList(modelData);
			
			//***********************************************************************************************************************************START!
			//测算主入口
			Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
			logger.info(reckonType);
			if (reckonType.equals("plan")) {//租金计划修改
				FundRentPlanBean fundplan = getFundRentPlanBeanByJsonStr(modelData.get("json_fund_rent_plan_str"), contractId, docId, process,cb.getYearRate());
				RentPlanModDo rpmd = new RentPlanModDo();
				re_ht = rpmd.updatePlan(projId, cb.getDocId(), fundplan, process,modelData.get("custid"),cb);
			} else {//租金测算调用入口点
				RentCalcDo rcd = new RentCalcDo();
				re_ht = rcd.rentCal(cb, process, "", adjustList.size() == 0 ? null : adjustList.toArray(new String[0]), kniwnRentBeans,fundPlanList,null,fpps,srbs,rentList);
			}
			fundPlanList = (List<FundPlanBean>)re_ht.get("fundchargeplan");
			List<FundPlanBean> fundputplanList = (List<FundPlanBean>)re_ht.get("fundputplan");
			List<FundPlanBean> graceplanList = (List<FundPlanBean>)re_ht.get("graceplan");
			if(null!=fundPlanList)Collections.sort(fundPlanList);
			if(null!=fundputplanList)Collections.sort(fundputplanList);
			if(null!=graceplanList)Collections.sort(graceplanList);
			//***********************************************************************************************************************************END!
			
			result.putAll(modelData);
			result.putAll(re_ht);
			logger.info("isSucess:"+re_ht.get("isSucess"));
			if ("true".equals(re_ht.get("isSucess"))) {
				List rentPlanList = (List)result.get("rentPlanList");
				List cashDetailList = (List)result.get("cashDetailList");
				//计算粗利
				BigDecimal grossProfit =  this.getGrossProfit(rentPlanList,cb,process);
				logger.info("项目粗利>>>>>>>>>>>>>>>>>>>>"+grossProfit);
				//净融资额 公式：净融资额==购买金额－首付款－保证金-其他收入+其他支出-前收第1期租金+保险费-厂商返利-手续费
				BigDecimal cleancreditmoney = this.getCleancreditmoney(rentPlanList, result,process,modelData);
				logger.info("净融资额>>>>>>>>>>>>>>>>>>>>"+cleancreditmoney);
				BigDecimal cleanCreditRatio = cleancreditmoney.divide(new BigDecimal(cb.getEquipAmt()),20, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP);
				//计算期初付款总计
				BigDecimal firstpaymenttotal = this.getFirstpaymenttotal(rentPlanList, result,process);
				logger.info("期初付款总计>>>>>>>>>>>>>>>>>>>>"+firstpaymenttotal);
				cb.setCleanCreditMoney(cleancreditmoney);
				cb.setFirstPaymentTotal(firstpaymenttotal.toString());
				cb.setGrossProfit(grossProfit);
				ConditionServiceImpl csi = new ConditionServiceImpl();
				csi.addConditionByCal(tcb, cb);
				if (rentPlanList.size() > 0 && cashDetailList.size() > 0 ) {
					logger.info("process:"+process);
					result.put("irr", cb.getIrr());
					result.put("yearrate", cb.getYearRate());
					result.put("enddate", cb.getEndDate());
					result.put("grace", cb.getGrace());
					result.put("cleancreditmoney", cleancreditmoney);
					result.put("firstpaymenttotal", firstpaymenttotal);
					result.put("grossprofit", grossProfit);
					result.put("cleancreditratio", cleanCreditRatio);
				}
				result.put("fundchargeplan", changeListToLower(fundPlanList));
				result.put("fundputplan", changeListToLower(fundputplanList));
				result.put("graceplan", changeListToLower(graceplanList));
				result.put("rentPlanList", changeListToLower(rentPlanList));
				result.put("cashDetailList", changeListToLower(cashDetailList));
			} else {
				throw new LeasingException((String)re_ht.get("message"));
			}
		}
		
		return result;
	}
	
	private static List<Map<String, Object>> changeListToLower(List resource) throws Exception{
		List<Map<String, Object>> resourceObjList = new ArrayList<Map<String,Object>>();
		for(Object obj : resource){
			Map<String, Object> resourceObj =  ObjectConvertUtils.convertBeanToMap(obj);
			Map<String, Object> resourceObjNew = new HashMap<String, Object>();
			for(String key : resourceObj.keySet()){
				resourceObjNew.put(key.toLowerCase(),resourceObj.get(key) );
			}
			resourceObjList.add(resourceObjNew);
		}
		return resourceObjList;
	}
	
	/**
	 * 
	 * @Title: getGrossProfit
	 * @author zhangc
	 * @Description: 项目粗利：财务利息总和+手续费+管理费+返点收入+利息补贴-保险费（我司）+租前息+名义货价+其他收入-其他支出
	 * @return
	 * @return BigDecimal
	 * @throws
	 */
	public BigDecimal getGrossProfit(List rentPlanList,ConditionBean cb,String process){
		BigDecimal interestTotal = BigDecimal.ZERO;
		for(Object obj : rentPlanList){
			RentPlan plan = null;
			if(process.equals("proj_process") ){
				plan = (ProjFundRentPlanTemp) obj ;
			}else if(process.equals("quoted_price") ){
				plan= (CustFundRentPlan) obj ;
			}else{
				plan = (ContractFundRentPlanTemp) obj ;
			}
			interestTotal = interestTotal.add(plan.getInterest());
		}
		BigDecimal  handleChargeMoney =new BigDecimal(null == cb.getHandlingChargeMoney() || cb.getHandlingChargeMoney().length() <= 0 ? "0" : cb.getHandlingChargeMoney()) ;
		BigDecimal insureMoney = new BigDecimal( null == cb.getInsureMoney()  || cb.getInsureMoney().length() <= 0 ? "0" : cb.getInsureMoney());
		BigDecimal managementMoney = new BigDecimal(null ==  cb.getManagementMoney()  || cb.getManagementMoney().length() <=0  ? "0" : cb.getManagementMoney());
		BigDecimal beforeInterest = new BigDecimal(null == cb.getBeforeInterest() || cb.getBeforeInterest().length() <= 0 ? "0" : cb.getBeforeInterest());
		BigDecimal nominalPrice = new BigDecimal(null == cb.getNominalPrice() || cb.getNominalPrice().length() <= 0 ? "0" : cb.getNominalPrice());
		BigDecimal otherIncome = new BigDecimal(null == cb.getOtherIncome() || cb.getOtherIncome().length() <= 0 ? "0" : cb.getOtherIncome());
		BigDecimal otherExpenditure = new BigDecimal(null == cb.getOtherExpenditure() || cb.getOtherExpenditure().length() <= 0 ? "0" : cb.getOtherExpenditure());
		BigDecimal returnPointIncome = new BigDecimal(null == cb.getReturnPointIncome() || cb.getReturnPointIncome().length() <= 0 ? "0" : cb.getReturnPointIncome());
		BigDecimal interestSupport = new BigDecimal(null == cb.getInterestSupport() || cb.getInterestSupport() .length() <= 0 ? "0" : cb.getInterestSupport() );
		//BigDecimal faccautionmoney = new BigDecimal(null == cb.getFacCautionMoney() || cb.getFacCautionMoney() .length() <= 0 ? "0" : cb.getFacCautionMoney() );
		return interestTotal.add(handleChargeMoney).add(returnPointIncome).add(interestSupport).add(managementMoney).subtract(insureMoney).add(beforeInterest).add(nominalPrice).add(otherIncome).subtract(otherExpenditure);
	}
	
	/**
	 * 
	 * <p>计算精授信额。</p>
	 * <p>公式：净授信额 = 设备款－首付款－ 咨询服务费-手续费-其他收入-厂商返利-承租人保证金-供应商保证金-期初第1期租金。</p>
	 * <p>其实前台存在这个JS的计算方法的cleancreditmoney(e)，因为未完成，我暂时在后台使用java的方式处理，如果前端JS完善了，这个方法可以寿终正寝，但是请注意前端JS计算注意精度问题。</p>
	 * @author 谢永伦
	 * @param rentPlanList  租金计划集合
	 * @param result 返回前台的MAP集合
	 * @return
	 */
	public BigDecimal getCleancreditmoney(List rentPlanList,Map<String, Object> result,String process,Map<String, String> map){
		BigDecimal cleancreditmoney = BigDecimal.ZERO;
		String periodtype  = (String) map.get("periodtype");// 期初（期末）支付 // 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		
		//设备款
		BigDecimal equipamt = new BigDecimal( NumberUtils.nullToZero( (String) map.get("equipamt") ) );//设备款
		//-
		//首付款
		BigDecimal firstpayment = new BigDecimal( NumberUtils.nullToZero( (String) map.get("firstpayment") ) );//首付款
		//保证金
		BigDecimal cautionmoney = new BigDecimal( NumberUtils.nullToZero( (String) map.get("cautionmoney") ) );//保证金 
		//其他收入
		BigDecimal otherincome = new BigDecimal( NumberUtils.nullToZero( (String) map.get("otherincome") ) );//其他收入 
		//期初第1期租金
		BigDecimal firstRent = BigDecimal.ZERO;
		if("1".equals(periodtype) || "period_type_1".equals(periodtype)){
			if(process.equals("proj_process") ){
				firstRent = ((ProjFundRentPlanTemp) rentPlanList.get(0)).getRent() ;
			}else if(process.equals("quoted_price") ){
				firstRent = ((CustFundRentPlan) rentPlanList.get(0)).getRent() ;
			}else{
				firstRent = ((ContractFundRentPlanTemp) rentPlanList.get(0)).getRent() ;
			}
		}
		
		//厂商返利
		BigDecimal returnamt = new BigDecimal( NumberUtils.nullToZero( (String) map.get("returnamt") ) );//厂商返利
		//手续费
		BigDecimal handlingchargemoney =  new BigDecimal( NumberUtils.nullToZero( (String) map.get("handlingchargemoney") ) );//手续费
		//管理费
		BigDecimal managementmoney = new BigDecimal( NumberUtils.nullToZero( (String) map.get("managementmoney") ) );//管理费
		//返点收入
		BigDecimal returnPointIncome = new BigDecimal( NumberUtils.nullToZero( (String) map.get("returnpointincome") ) );
		//利息补贴
		BigDecimal interestSupport = new BigDecimal( NumberUtils.nullToZero( (String) map.get("interestsupport") ) );
		//厂商保证金
		BigDecimal faccautionmoney = new BigDecimal( NumberUtils.nullToZero( (String) map.get("faccautionmoney") ) );
		//其他支出
		BigDecimal otherexpenditure = new BigDecimal( NumberUtils.nullToZero( (String) map.get("otherexpenditure") ) );//其他支出
		//保险费
		BigDecimal insuremoney = BigDecimal.ZERO;//new BigDecimal( NumberUtils.nullToZero( (String) map.get("insuremoney") ) );//保险费
		//保险费收入
		BigDecimal insurancelessee = new BigDecimal( NumberUtils.nullToZero( (String) map.get("insurancelessee_show") ) );
		//保险费支出
		BigDecimal insurancelessor = new BigDecimal( NumberUtils.nullToZero( (String) map.get("insurancelessor_show") ) );
		
		//计算
		BigDecimal subtracts = equipamt.subtract(firstpayment).subtract(returnPointIncome).subtract(interestSupport).subtract(cautionmoney).subtract(faccautionmoney).subtract(otherincome).subtract(firstRent).subtract(returnamt).subtract(handlingchargemoney).subtract(managementmoney);
		BigDecimal adds = otherexpenditure.add(insuremoney);
		//净授信额 = 设备款－首付款－保证金-厂商保证金-利息补贴-返点收入-其他收入-期初第1期租金-厂商返利-手续费 - 管理费+其他支出+保险费+保险费收入-保险费支出
		cleancreditmoney = subtracts.add(adds).add(insurancelessor).subtract(insurancelessee);
		//返回
		return cleancreditmoney;
	}
	public BigDecimal getCleancreditmoneyTwo(List rentPlanList,Map<String, Object> result,String process,Map<String, String> map,ConditionBean cb){
		BigDecimal cleancreditmoney = BigDecimal.ZERO;
		String periodtype  = cb.getPeriodType();// 期初（期末）支付 // 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		
		//设备款
		BigDecimal equipamt = new BigDecimal( NumberUtils.nullToZero(cb.getEquipAmt() ) );//设备款
		//-
		//首付款
		BigDecimal firstpayment = new BigDecimal( NumberUtils.nullToZero(cb.getFirstPayment() ) );//首付款
		//保证金
		BigDecimal cautionmoney = new BigDecimal( NumberUtils.nullToZero( cb.getCautionMoney()) );//保证金 
		//其他收入
		BigDecimal otherincome = new BigDecimal( NumberUtils.nullToZero( cb.getOtherIncome() ) );//其他收入 
		//期初第1期租金
		BigDecimal firstRent = BigDecimal.ZERO;
		if("1".equals(periodtype) || "period_type_1".equals(periodtype)){
			if(process.equals("proj_process") ){
				firstRent = ((ProjFundRentPlanTemp) rentPlanList.get(0)).getRent() ;
			}else if(process.equals("quoted_price") ){
				firstRent = ((CustFundRentPlan) rentPlanList.get(0)).getRent() ;
			}else{
				firstRent = ((ContractFundRentPlanTemp) rentPlanList.get(0)).getRent() ;
			}
		}
		
		//厂商返利
		BigDecimal returnamt = new BigDecimal( NumberUtils.nullToZero(cb.getReturnAmt()) );//厂商返利
		//手续费
		BigDecimal handlingchargemoney =  new BigDecimal( NumberUtils.nullToZero(cb.getHandlingChargeMoney()));//手续费
		//管理费
		BigDecimal managementmoney = new BigDecimal( NumberUtils.nullToZero( cb.getManagementMoney()) );//管理费
		//返点收入
		BigDecimal returnPointIncome = new BigDecimal( NumberUtils.nullToZero(cb.getReturnPointIncome()) );
		//利息补贴
		BigDecimal interestSupport = new BigDecimal( NumberUtils.nullToZero(cb.getInterestSupport()) );
		//厂商保证金
		BigDecimal faccautionmoney = new BigDecimal( NumberUtils.nullToZero(cb.getFacCautionMoney()) );
		//其他支出
		BigDecimal otherexpenditure = new BigDecimal( NumberUtils.nullToZero(cb.getOtherExpenditure()) );//其他支出
		//保险费
		BigDecimal insuremoney = BigDecimal.ZERO;//new BigDecimal( NumberUtils.nullToZero( (String) map.get("insuremoney") ) );//保险费
		//保险费收入
		BigDecimal insurancelessee = new BigDecimal( NumberUtils.nullToZero(String.valueOf(cb.getInsuranceLessee())) );
		//保险费支出
		BigDecimal insurancelessor = new BigDecimal( NumberUtils.nullToZero(String.valueOf(cb.getInsuranceLessor())) );
		
		//计算
		BigDecimal subtracts = equipamt.subtract(firstpayment).subtract(returnPointIncome).subtract(interestSupport).subtract(cautionmoney).subtract(faccautionmoney).subtract(otherincome).subtract(firstRent).subtract(returnamt).subtract(handlingchargemoney).subtract(managementmoney);
		BigDecimal adds = otherexpenditure.add(insuremoney);
		//净授信额 = 设备款－首付款－保证金-厂商保证金-利息补贴-返点收入-其他收入-期初第1期租金-厂商返利-手续费 - 管理费+其他支出+保险费+保险费收入-保险费支出
		cleancreditmoney = subtracts.add(adds).add(insurancelessor).subtract(insurancelessee);
		//返回
		return cleancreditmoney;
	}
	/**
	 * 
	 * <p>计算期初付款总计 。</p>
	 * <p>公式：期初付款总计   = 首付 款 +保证金 +手续费 +管理费 +其他收入 + 期初第1期租金 + 厂商返利   。</p>
	 * <p>其实前台存在这个JS的计算方法的firstpaymenttotal(e)，因为未完成，我暂时在后台使用java的方式处理，如果前端JS完善了，这个方法可以寿终正寝，但是请注意前端JS计算注意精度问题。</p>
	 * @author 谢永伦
	 * @param rentPlanList  租金计划集合
	 * @param result 返回前台的MAP集合
	 * @return
	 */
	public BigDecimal getFirstpaymenttotal(List rentPlanList,Map<String, Object> result,String process){
		BigDecimal firstpaymenttotal = BigDecimal.ZERO;
		String periodtype  = (String) result.get("periodtype");// 期初（期末）支付 // 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		
		//首付款
		BigDecimal firstpayment = new BigDecimal( NumberUtils.nullToZero( (String) result.get("firstpayment") ) );//首付款
		//保证金
		BigDecimal cautionmoney = new BigDecimal( NumberUtils.nullToZero( (String) result.get("cautionmoney") ) );//保证金 
		//手续费
		BigDecimal handlingchargemoney =  new BigDecimal( NumberUtils.nullToZero( (String) result.get("handlingchargemoney") ) );//手续费
		//管理费
		BigDecimal managementmoney = new BigDecimal( NumberUtils.nullToZero( (String) result.get("managementmoney") ) );//管理费
		//其他收入
		BigDecimal otherincome = new BigDecimal( NumberUtils.nullToZero( (String) result.get("otherincome") ) );//其他收入 
		//期初第1期租金
		BigDecimal firstRent = BigDecimal.ZERO;
		if("1".equals(periodtype) || "period_type_1".equals(periodtype)){
			if(process.equals("proj_process") ){
				firstRent = ((ProjFundRentPlanTemp) rentPlanList.get(0)).getRent() ;
			}else if(process.equals("quoted_price") ){
				firstRent = ((CustFundRentPlan) rentPlanList.get(0)).getRent() ;
			}else{
				firstRent = ((ContractFundRentPlanTemp) rentPlanList.get(0)).getRent() ;
			}
		}
		//厂商返利
		BigDecimal returnamt = new BigDecimal( NumberUtils.nullToZero( (String) result.get("returnamt") ) );//厂商返利
		
		//期初付款总计   = 首付 款 +保证金 +手续费 +管理费 +其他收入 + 期初第1期租金 + 厂商返利
		firstpaymenttotal = firstpayment.add(cautionmoney).add(handlingchargemoney).add(managementmoney).add(otherincome).add(firstRent).add(returnamt);
		//返回
		return firstpaymenttotal;
	}
	
	
	/*
	 * sea 
	 */
	public DictionaryData getDictionaryData(String id) throws DataAccessException, Exception{
		DictionaryData dict = this.tableService.findEntityByID(DictionaryData.class, id);
		return dict;
	}
	
	
	@Override
	public Map<String, Object> adjustCalculate(Map<String, String> modelData) throws Exception {
		//构建了租金变更的工具Bean AdjustBean
		AdjustBean adb = this.getAdjustBean(modelData);
		Map<String, Object> return_model = new HashMap<String, Object>();
		RentChargeDo rcd = new RentChargeDo();
		Hashtable ht = new Hashtable();
		String saveType = modelData.get("fund_rent_adjust.adjustsavetype");
		if (saveType.equals("reckon")) {// 测算
			ht = rcd.rentChargeCal(adb, "cont_process", null);
		} else if (saveType.equals("terminate")) {// 中途终止
			String fundputstr = modelData.get("fund_rent_adjust.fundputstr");
			adb.setFundputstr(fundputstr);
			ht = rcd.rentChargeCal(adb, "cont_process", null);
		} else if (saveType.equals("adjust")) {// 调整
			// 从JSON中提取租金计划
			ObjectMapper jsonMapper = new ObjectMapper();
			String fundRentPlan = modelData.get("fund_rent_adjust.jsonfundrentplanstr");
			Map[] newSetMaps = jsonMapper.readValue(fundRentPlan, Map[].class);
			String obj[] = new String[(newSetMaps.length - adb.getStartList() + 1)];
			for (int j = 0; j < obj.length; j++) {
				obj[j] = newSetMaps[j + adb.getStartList() - 1].get("rentadjust") == null ? "" : newSetMaps[j + adb.getStartList() - 1].get("rentadjust").toString();
			}
			ht = rcd.rentChargeCal(adb, "cont_process", obj);
		}
		if (ht.get("isSucess").equals("false")) {
			throw new LeasingException(ht.get("error").toString());
		} /*else {
			return_model = selectConditionDataFromTemp(adb.getContractId(), adb.getDocId());
			return_model.put("adjust", "adjust");// 标记这个是变更
		}*/
		return ht;
	}

	@Override
	public Map<String, Object> updateCalculateOld(Map<String, String> modelData) throws Exception {
		String fundRentPlan = modelData.get("json_fund_rent_plan_str");
		String contractId = modelData.get("contractid");
		String yearRate = modelData.get("yearrate");
		String docId = modelData.get("docid");
		String projid = modelData.get("projid");
		String process = modelData.get("process");
		
		if(null == contractId){
			contractId= projid;
		}
		//得到修改后的租金计划测算Bean
		FundRentPlanBean frpf =  getFundRentPlanBeanByJsonStr(fundRentPlan,contractId,docId,process,yearRate);
		
		RentPlanModDo rpmd = new RentPlanModDo();
		Hashtable ht = rpmd.updatePlan(contractId, docId, frpf, process,modelData.get("custid"),null);
		if (ht.get("isSucess").equals("false")) {
			throw new LeasingException(ht.get("error").toString());
		} else {
			ht.put("adjust", "update");// 标记这个是修改
		}
		return ht;
	}

	public Map<String, Object> resetConditionData(String contractId, String docId) throws Exception {
		RentChargeDAOImpl rcd = new RentChargeDAOImpl();
		//删除租金变更临时表数据
		rcd.deleteAjustByConAndDocId(contractId, docId);
		ContractInfo contractInfo = (ContractInfo) this.tableService.findEntityByID(ContractInfo.class, contractId);
		RentCalculateHelper.saveConditionDataToTemp(contractInfo, docId);
		Map<String, Object> return_model = selectConditionDataFromTemp(contractInfo.getId(), docId);// 构建返回数据
		return_model.put("adjust", "adjust");// 标记这个是变更
		return return_model;
	}
	
	private  FundRentPlanBean  getFundRentPlanBeanByJsonStr(String fundRentPlan,String contractId, String docId,String process,String yearRate) throws Exception{
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("contractId", contractId);
			propertiesMap.put("docId", docId);
			List fundRentPlanTemps = new ArrayList();
			// 从JSON中提取租金计划
			ObjectMapper jsonMapper = new ObjectMapper();
			Map[] newSetMaps = jsonMapper.readValue(fundRentPlan, Map[].class);
			for (int j = 0; j < newSetMaps.length; j++) {
				Map map = newSetMaps[j];
				if("proj_process".equals(process)){
					ProjFundRentPlanTemp temp = new ProjFundRentPlanTemp();
					this.tableService.copyAndOverrideExistedValueFromStringMap(map, temp, null);
					fundRentPlanTemps.add(temp);
				}else{
					ContractFundRentPlanTemp temp = new ContractFundRentPlanTemp();
					this.tableService.copyAndOverrideExistedValueFromStringMap(map, temp, null);
					fundRentPlanTemps.add(temp);
				}
			}
			Collections.sort(fundRentPlanTemps);// 排序
			FundRentPlanBean frpf = new FundRentPlanBean();
			for (int i = 0; i < fundRentPlanTemps.size(); i++) {
				RentPlan rentPlanTemp = (RentPlan)fundRentPlanTemps.get(i);
				if(rentPlanTemp.getRent().compareTo(BigDecimal.ZERO) <= 0 ){
					continue;
				}
				frpf.getCautionmoneyRemainList().add(rentPlanTemp.getCautionmoneyRemain() == null ? "0" : rentPlanTemp.getCautionmoneyRemain().toString());
				frpf.getRentList().add(rentPlanTemp.getRent() == null ? "0" : rentPlanTemp.getRent().toString());
				frpf.getAllRemainRentList().add(rentPlanTemp.getAllRemainRent() == null ? "0" : rentPlanTemp.getAllRemainRent().toString());
				frpf.getPlanDateList().add(rentPlanTemp.getPlanDate());
				frpf.getRentAdjustList().add(rentPlanTemp.getRentAdjust() == null ? "" : rentPlanTemp.getRentAdjust().toString());
				frpf.getCorpusFinacList().add(rentPlanTemp.getCorpus() == null ? "0" : rentPlanTemp.getCorpus().toString());
				frpf.getInterestFinacList().add(rentPlanTemp.getInterest() == null ? "0" : rentPlanTemp.getInterest().toString());
				frpf.getCorpusBusinessList().add(rentPlanTemp.getCorpusBusiness() == null ? "0" : rentPlanTemp.getCorpusBusiness().toString());
				frpf.getInterestBusinessList().add(rentPlanTemp.getInterestBusiness() == null ? "0" : rentPlanTemp.getInterestBusiness().toString());
				frpf.getColumn_1().add(rentPlanTemp.getCorpusBusiness() == null ? "0" : rentPlanTemp.getCorpusBusiness().toString());
				frpf.getColumn_2().add(rentPlanTemp.getInterestBusiness() == null ? "0" : rentPlanTemp.getInterestBusiness().toString());
				frpf.getYearRateList().add((rentPlanTemp.getYearRate() == null ? yearRate : rentPlanTemp.getYearRate().toString()) );
				frpf.getCorpusOverageBusinessList().add(rentPlanTemp.getCorpusOverage() == null ? "0" : rentPlanTemp.getCorpusOverage().toString());
			}
			return 	frpf;
	}
	/**
	 * 返回临时表中的数据
	 * 
	 * @param contractId
	 *            合同号(不是合同表中的主键id 而是 contract_id字段的值)
	 * @param docId
	 *            测算的doc_id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectConditionDataFromTemp(String contractId, String docId) throws Exception {
		Map<String, Object> return_model = new HashMap<String, Object>();// 构建返回数据
		List ccts;
		List cfdrpts;
		List cfarpts;
		List ccdt;
		List cfcdt;
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("contractId", contractId);
		propertiesMap.put("docId", docId);
		logger.info("[sea]contractId:"+contractId);
		logger.info("[sea]docId:"+docId);
		ccts = this.tableService.findEntityByProperties(ContractConditionTemp.class, propertiesMap);
		cfdrpts = this.tableService.findEntityByProperties(ContractFundRentPlanTemp.class, propertiesMap);
		cfarpts = this.tableService.findEntityByProperties(ContractFinaRentPlanTemp.class, propertiesMap);
		ccdt = this.tableService.findEntityByProperties(ContractCashDetailTemp.class, propertiesMap);
		cfcdt = this.tableService.findEntityByProperties(ContractFinaCashDetailTemp.class, propertiesMap);
		if (cfdrpts.size() > 0 ) {//&& ccdt.size() > 0
			//修改租金计划状态
			Collections.sort(cfdrpts);
			//cfdrpts = this.changeRentStatus(cfdrpts, propertiesMap);
			String jsonStr = this.changeRentStatus(cfdrpts, propertiesMap);
			
			Collections.sort(cfarpts);
			Collections.sort(ccdt);
			Collections.sort(cfcdt);
			//TODO sea 这里的租金计划如果是临时表需要处理‘状态’，不然前台无法显示,暂时不会处理
			//return_model.put("fundrentplan", cfdrpts);
			return_model.put("fundrentplan", jsonStr);
			logger.info("[sea]cfdrpts.size():"+cfdrpts.size());
			return_model.put("finarentplan", cfarpts);
			return_model.put("fundcashdetail", ccdt);
			return_model.put("finacashdetail", cfcdt);
			return_model.put("rentcalculaters", "yes");
			return_model.put("irr", ((ContractConditionTemp) ccts.get(0)).getIrr());
			return_model.put("planIrr", ((ContractConditionTemp) ccts.get(0)).getPlanIrr());
			return_model.put("enddate", ((ContractFundRentPlanTemp) cfdrpts.get(cfdrpts.size() - 1)).getPlanDate());
			return_model.put("yearrate", ((ContractConditionTemp) ccts.get(0)).getYearRate());
			return_model.put("incomenumber", ((ContractConditionTemp) ccts.get(0)).getIncomeNumber());
			return_model.put("leaseterm", ((ContractConditionTemp) ccts.get(0)).getLeaseTerm());
		}
		return return_model;
	}
	
	/**
	  * <p>遍历实收与临时计划信息，构建临时租金计划对应的状态。</p>
	  * @author sea
	  * @param cfdrpts 临时偿还计划信息
	  * @param map contract_id 和 doc_id
	  * 	   propertiesMap.put("contractId", contractId);
	  * 	   propertiesMap.put("docId", docId);	
	  * @return
	  * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private String changeRentStatus(List cfdrpts,Map<String, Object> map) throws Exception{
		List<ContractFundRentPlanTemp> list = new ArrayList<ContractFundRentPlanTemp>();
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		String jsonStr = "\"fundrentplan\":"; 
		if(cfdrpts.size() > 0){
			Collections.sort(cfdrpts);
			//合同信息表中的contract_info
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("contractId", map.get("contractId"));
			//查询合同信息
			List<ContractInfo> info_list = this.tableService.findEntityByProperties(ContractInfo.class, propertiesMap);
			String contract_uuid = (String) map.get("contractId");
			ContractInfo contractInfo = null;
			if(info_list.size() > 0){
				contractInfo = info_list.get(0);
				contract_uuid = contractInfo.getId();
			}
			//
			if(!Tools.isNullOrEmpty(contract_uuid)){
				String sql = this.getSQLForRentTemp(contract_uuid, map);
			    logger.info("查询租金计划状态sql:"+sql);   
			    List<Map<String, Object>> mapList = this.tableService.getBussinessDao().getJdbcTemplate().queryForList(sql);
			    
			    logger.info("cfdrpts:"+cfdrpts.size());
			    for (int i = 0; i < mapList.size(); i++) {
			    	Map<String, Object> tempMap = mapList.get(i);
			    	DictionaryData status = this.tableService.findEntityByID( DictionaryData.class,String.valueOf( tempMap.get("status") ) );
			    	//方式1 ：可返回List集合中存的是每个租金临时对象
			    	ContractFundRentPlanTemp planTemp = new  ContractFundRentPlanTemp();
			    	planTemp = (ContractFundRentPlanTemp) cfdrpts.get(i);
			    	planTemp.setStatus(status);
			    	list.add(planTemp);
			    	
			    	//方式2: 可返回List集合中存的是每个租金临时MAP格式对象
			    	Map<String, Object> rentMap = new HashMap<String, Object>();
			    	rentMap.put("id", tempMap.get("id"));
			    	rentMap.put("rentlist", tempMap.get("rentlist"));
			    	rentMap.put("plandate", tempMap.get("plandate"));//计划日期
			    	rentMap.put("planstatusid", status.getId());//状态ID
			    	rentMap.put("planstatusname", status.getName());//状态
			    	rentMap.put("rent", tempMap.get("rent"));//租金
			    	rentMap.put("corpus", tempMap.get("corpus"));//财务本金
			    	rentMap.put("interest", tempMap.get("interest"));//财务利息
			    	rentMap.put("corpusoverage", tempMap.get("corpusoverage"));//本金余额
			    	rentMap.put("corpusbusiness", tempMap.get("corpusbusiness"));//业务本金
			    	rentMap.put("interestbusiness", tempMap.get("interestbusiness"));//业务利息
			    	rentMap.put("yearrate", tempMap.get("yearrate"));//年利率
			    	rentMap.put("rentadjust", tempMap.get("rentadjust"));//租金调整
			    	returnList.add(rentMap);
			    	
			    	// 将LSIMap转换为JSONArray数据
					JSONArray ja = new JSONArray();
					ja.put(rentMap);
					//System.out.println("租金计划对象数据格式：");
					jsonStr = jsonStr + ja.toString();
				}
			}else{
				return null;
			}
		}
		//方式3：可返回JSON字符串，其中存的是整个租金临时对象构建的JSON格式字符串
		jsonStr = jsonStr.replace("][", ",");
		//jsonStr = "{"+jsonStr.replace("][", ",")+"}";
        logger.info("租金计划临时JSON:"+jsonStr);
		return jsonStr;
	}
	
	/**
	  * <p>根据合同UUID/合同号/文档号构建查询租金计划临时表数据的信息。</p>
	  * @author sea
	  * @param contract_uuid 合同UUID 
	  * @param map 合同号/文档号
	  * @return
	 */
	private String getSQLForRentTemp(String contract_uuid,Map<String, Object> map){
		StringBuffer sql =  new StringBuffer();
	     sql.append(" select sql1.id, ")
	     	.append(" sql1.CONTRACT_ID as contractId ,") //FieldName(name="合同编号") 
			.append(" sql1.DOC_ID as docId	 ,")//FieldName(name="报价编号")
			.append(" sql1.QUOT_ID as quotId  ,")//FieldName(name="客户初始报价测算编号")
			.append(" sql1.ONHIRE_ID as onhireId ,")//FieldName(name="多次起租编号")
			.append(" sql1.RENT_LIST as rentList ,") //FieldName(name="期项")
			.append(" sql1.PLAN_DATE as planDate ,")//FieldName(name="承付日期")
			.append(" nvl(sql1.RENT,0) as rent ,")//FieldName(name="租金")
			.append(" nvl(sql1.RENT_ADJUST,0) as rentAdjust ,")//FieldName(name="租金调整值")
			.append(" nvl(sql1.CORPUS,0) as corpus ,")//FieldName(name="财务本金")
			.append(" nvl(sql1.CORPUS_BUSINESS,0) as corpusBusiness ,")//FieldName(name="业务本金")
			.append(" nvl(sql1.YEAR_RATE,0) as yearRate ,") //FieldName(name="年利率")
			.append(" nvl(sql1.INTEREST,0) as interest ,") //FieldName(name="租息")
			.append(" nvl(sql1.INTEREST_BUSINESS,0) as interestBusiness ,") //FieldName(name="业务租息")
			.append(" nvl(sql1.RENT_OVERAGE,0) as rentOverage ,")//FieldName(name="财务租金余额")
			.append(" nvl(sql1.CORPUS_OVERAGE,0) as corpusOverage ,") //FieldName(name="财务本金余额")
			.append(" nvl(sql1.INTEREST_OVERAGE,0) as interestOverage ,")//FieldName(name="财务租息余额")
			.append(" nvl(sql1.PENALTY_OVERAGE,0) as penaltyOverage ,")//FieldName(name="罚息余额")
			.append(" nvl(sql1.PENALTY,0) as penalty	 ,")//FieldName(name="罚息")
			//.append(" STATUS_ as status ,")//FieldName(name="状态")
			.append(" sql1.CREATOR_ as creator ,") //FieldName(name="创建人")
			.append(" sql1.CREATE_DATE as createDate ,") //FieldName(name="创建时间")
			.append(" sql1.MODIFICATOR_ as modificator ,")//FieldName(name="修改人")
			.append(" sql1.MODIFY_DATE as modifyDate ,")//FieldName(name="修改时间")
			.append(" nvl(sql1.All_REMAIN_RENT,0) as allRemainRent ,") //FieldName(name="本期以后所有的租金剩余")
			.append(" nvl(sql1.ALL_REMAIN_CORPUS,0) as  allRemainCorpus	 ,")//FieldName(name="本期以后所有的本金剩余")
			.append(" nvl(sql1.ALL_REMAIN_INTEREST,0) as  allRemainInterest ,")//FieldName(name="本期以后所有的利息剩余")
		    .append(" case  ")
		    //状态处理
			//PLANMANYSTATUSOVER	超额回笼  ,PLANMANYSTATUSNO 未收款,PLANMANYSTATUSPART	部份收款, PLANMANYSTATUSALL	已结清
			//计划 - 实收 = 0 表示：已回笼  
		    .append("      when nvl(sql1.rent,0) - nvl(sql2.income_rent,0) = 0 ")
		    .append("      THEN  'PLANMANYSTATUSALL' ")
			 //计划 - 实收 = 租金  表示：未回笼
		    .append("     when nvl(sql1.rent,0) - nvl(sql2.income_rent,0) = nvl(sql1.rent,0) ")
		    .append("     THEN  'PLANMANYSTATUSNO' ")
			//计划 - 实收 < 租金  表示：部分回笼
		    .append("     when nvl(sql1.rent,0) - nvl(sql2.income_rent,0) < nvl(sql1.rent,0) ")
		    .append("     THEN  'PLANMANYSTATUSPART' ")
		    //其它情况  表示：超额回笼
		    .append("       else ")
		    .append("         'PLANMANYSTATUSOVER' ")
		    .append("       end    as   status    ")
		    .append(" from ( ")
		    .append("       select  * from contract_fund_rent_plan_temp   ")
		    .append("       where contract_id = '"+map.get("contractId")+"' and  doc_id = '"+map.get("docId")+"'  ")
		    .append(") sql1 ")
		    .append("left join  contract_info ci on ci.contract_id = sql1.contract_id ")
		    .append("left join ( ")
		    .append("    select contract_id,plan_list ,nvl(sum(nvl(rent,0)),0) as income_rent  ")
		    .append("    from  contract_fund_rent_income  ")
		    .append("    where contract_id = '"+contract_uuid+"' ")
		    .append("    group by  contract_id,plan_list  ")
		    .append(") sql2 on sql2.plan_list = sql1.rent_list ")
		    .append("order by sql1.plan_date ");
		
	     return sql.toString();
	}
	
	
	/**
	  * <p>将字符串转成固定的KnowingRentBean对象格式。</p>
	  * @author sea
	  * @param jsonStr
	  * @return
	  * @throws JSONException
	 */
	public List<KnowingRentBean> getList(String jsonStr) throws JSONException{
		List<KnowingRentBean> kniwnRentBeans = new ArrayList<KnowingRentBean>();
		JSONArray jsonArray = new JSONArray(jsonStr);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			//直接取值方式，空不报错,jsonObject.opt("rent");
			
			String rent = null ;
			String startrentlist = null;
			String endrentlist = null;
			try{
				rent = (String) jsonObject.get("rent");
				startrentlist = (String) jsonObject.get("startrentlist");
				endrentlist = (String) jsonObject.get("endrentlist");
			}catch(Exception e){
				rent = (String) jsonObject.get("corpus");
				startrentlist = (String) jsonObject.get("startcorpuslist");
				endrentlist = (String) jsonObject.get("endcorpuslist");
			}
			
			KnowingRentBean obj = new KnowingRentBean();
			obj.setStartlist( Integer.parseInt(startrentlist) );
			obj.setEndlist( Integer.parseInt(endrentlist) );
			obj.setRent(rent);
			kniwnRentBeans.add(obj);
		}
		return kniwnRentBeans;
	}
	
	/**
	 * <p>计息JSON中的资金计划转换为实体对象集合。</p>
	 * @author sea
	 * @param modelData
	 * @return
	 * @throws JSONException
	 */
	private List<FundPlanBean> getFundPlanList(Map<String, String> modelData) throws Exception{
		String json_fund_fund_charge_str = modelData.get("json_fund_fund_charge_str");//获取资金计划JSON字符串
		List<FundPlanBean> fundPlanList = new ArrayList<FundPlanBean>();
		if(!"null".equals(json_fund_fund_charge_str)){
			JSONArray jsonArray = new JSONArray(json_fund_fund_charge_str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				//jsonObject.put("settlemethod",((String)jsonObject.get("settlemethod")).replace("payfund", "PayFund"));
				//将JSON转换为map
				Map map = tableService.getStringMapByJsonObject(jsonObject);
				//将map再转换为FundPlanBean的对象
				FundPlanBean obj = ObjectConvertUtils.convertMapToBean(FundPlanBean.class, map);
				
				//二次封装
				fundPlanList.add(obj);
			}
		}
		String json_fund_put_config_str = modelData.get("json_fund_put_config_str");//获取资金计划JSON字符串
		if(!"null".equals(json_fund_put_config_str)){
			JSONArray jsonArray = new JSONArray(json_fund_put_config_str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				//jsonObject.put("settlemethod",((String)jsonObject.get("settlemethod")).replace("payfund", "PayFund"));
				//将JSON转换为map
				Map map = tableService.getStringMapByJsonObject(jsonObject);
				//将map再转换为FundPlanBean的对象
				FundPlanBean obj = ObjectConvertUtils.convertMapToBean(FundPlanBean.class, map);
				
				//二次封装
				fundPlanList.add(obj);
			}
		}
		return fundPlanList;
	}
	private List<FundPlanBean> getFundPlanListForFinance(Map<String, String> modelData) throws Exception{
		String json_fund_fund_charge_str = modelData.get("json_fund_fund_charge_str");//获取资金计划JSON字符串
		List<FundPlanBean> fundPlanList = new ArrayList<FundPlanBean>();
		if(!"null".equals(json_fund_fund_charge_str)){
			JSONArray jsonArray = new JSONArray(json_fund_fund_charge_str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				//jsonObject.put("settlemethod",((String)jsonObject.get("settlemethod")).replace("payfund", "PayFund"));
				//将JSON转换为map
				Map map = tableService.getStringMapByJsonObject(jsonObject);
				//将map再转换为FundPlanBean的对象
				FundPlanBean obj = ObjectConvertUtils.convertMapToBean(FundPlanBean.class, map);
				
				//二次封装
				fundPlanList.add(obj);
			}
		}
		return fundPlanList;
	}
	private List<FundPutPlan> getFundPutList(Map<String, String> modelData) throws Exception{
		List<FundPutPlan> fundPlanList = new ArrayList<FundPutPlan>();
		//获取投放计划
		String json_fund_put_config_str = modelData.get("json_fund_put_config_str");//获取资金计划JSON字符串
		if(!"null".equals(json_fund_put_config_str)){
			JSONArray jsonArray = new JSONArray(json_fund_put_config_str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				Map map = tableService.getStringMapByJsonObject(jsonObject);
				FundPutPlan obj = ObjectConvertUtils.convertMapToBean(FundPutPlan.class, map);
				//二次封装
				fundPlanList.add(obj);
			}
		}
		return fundPlanList;
	}
	
	private AdjustBean getAdjustBean (Map<String, String> modelData) throws Exception{
		AdjustBean adb = new AdjustBean();
		this.tableService.copyAndOverrideExistedValueFromStringMap(modelData, adb, null, "fund_rent_adjust");
		String saveType = modelData.get("fund_rent_adjust.adjustsavetype");
		logger.debug("saveType:"+saveType);
		String contractId = modelData.get("fund_rent_adjust.contract");
		String contractInfoId = modelData.get("fund_rent_adjust.contractid");
		adb.setContractId(contractId);
		adb.setContractInfoId(contractInfoId);
		adb.setDocId(modelData.get("docid"));
		
		String rateTemp = "0.00";
		if (saveType.equals("reckon")) {// 测算
			//变更年利率
			rateTemp = NumberUtils.nullToZero( modelData.get("fund_rent_adjust.yearrate") );
		}
		else if (saveType.equals("terminate")) {// 中途终止情况下，改值封装折现率
			rateTemp = NumberUtils.nullToZero( modelData.get("fund_rent_adjust.salerate") );
		}
		/**
		 *因为暂时不明这里的利率有多少种取值方式，因此这里全部用else代替。
		 *因为之前的代码默认直接取下方的 fund_rent_adjust.salerate值，
		 *目前已知变更年利率、中途终止情况下的取值方式
		 */
		else{
			rateTemp = NumberUtils.nullToZero( modelData.get("fund_rent_adjust.salerate") );
		}
		adb.setYearRate( new BigDecimal( rateTemp ) );
		logger.debug("变更年利率:"+rateTemp);
		return adb;
	}
	
	@Override
	public Map<String, Object> updateFundFundPlan(Map<String, String> modelData)
			throws Exception {
		List<FundPlanBean> fundPlanList = this.getFundPlanList(modelData);
		FundFundChargeServiceImpl  ffc = new FundFundChargeServiceImpl();
		String process = modelData.get("process");
		String markId = "";
		String markValue = "";
		if("proj_process".equals(process)){
			markId = "proj_id";
			markValue = modelData.get("projId");
		}else{
			markId = "contract_id";
			markValue = modelData.get("contractId");
		}
		//修改现金流的生成规则，按照租金计划和资金计划生成
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		ConditionBean cb = new ConditionBean();
		cb.setEndDate(modelData.get("enddate"));
		cb.setStartDate(modelData.get("startdate"));
		cb.setLeaseAmtDate(modelData.get("leaseamtdate"));
		TabCalBean tcb = new TabCalBean();
		tcb.setFundFundPlan_tb("fund_fund_plan_temp");
		if("proj_process".equals(process)){
			tcb.setCondition_tb("proj_condition_temp");
			tcb.setFinacCashTb("proj_fina_cash_detail_temp");
			tcb.setContractCashTb("proj_cash_detail_temp");
			tcb.setRentPlan_tb("proj_fund_rent_plan_temp");
			tcb.setContOrProjCName("proj_id");
			tcb.setContOrProjCValue(modelData.get("projId"));
		}else{
			tcb.setContOrProjCName("contract_id");
			tcb.setCondition_tb("contract_condition_temp");
			tcb.setContractCashTb("contract_cash_detail_temp");
			tcb.setFinacCashTb("contract_fina_cash_detail_temp");
			tcb.setRentPlan_tb("contract_fund_rent_plan_temp");
			tcb.setRentFinaPlan_tb("contract_fina_rent_plan_temp");// 财务租金计划
			tcb.setContOrProjCValue(modelData.get("contractId"));
		}
		tcb.setCalType(process);
		tcb.setDocId(modelData.get("docId"));
		cb.setDocId(modelData.get("docId"));
		ffc.addFundFundTemp(fundPlanList, cb,tcb);
		String  fundRentPlan = modelData.get("json_fund_rent_plan_str");
		//在生成现金流之前，需要将重新插入临时的租金计划，防止流程开始，无临时数据导致现金流生成出现问题
		RentPlanContrCalDAOImpl rpd = new RentPlanContrCalDAOImpl();
		rpd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);
		rpd.addRentPlanByJsonStr(tcb, fundRentPlan);
		
		idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
		idd.addCashDetails(tcb, cb);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		String hsql = "";
		List cashDetailList = null;
		if ("proj_process".equals(process)) {
			hsql = " FROM   com.reckon.entity.proj.ProjCashDetailTemp where projId = '"+modelData.get("projId")+"' AND docId = '"+ modelData.get("docId")+"' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
		}else{
			hsql = " FROM  ContractCashDetailTemp where contractId = '"+modelData.get("contractId")+"' AND docId = '"+ modelData.get("docId")+"' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
		}
		Collections.sort(cashDetailList);
		String irr = IrrTools.getIrr(cashDetailList, process);
		String xirr = IrrTools.getIrr(cashDetailList, process,true);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("irr", irr);
		result.put("xirr", xirr);
		result.put("fundcashdetail", cashDetailList);
		return result;
	}
	@Override
	public Map<String, Object> updateFundFundPlanForFinance(Map<String, String> modelData)
			throws Exception {
		List<FundPlanBean> fundPlanList = this.getFundPlanListForFinance(modelData);
		IncomeDiscount id=new IncomeDiscount();
		//根据财务起租日，改变总本金的时间
		for(FundPlanBean fpb:fundPlanList){
			if("feetype23".equals(fpb.getFeeType())){
				fpb.setPlanDate(modelData.get("finaleaseamtdate"));
				id.setAcqCost(fpb.getPlanMoney());//购置成本
			}
		}
		FundFundChargeServiceImpl  ffc = new FundFundChargeServiceImpl();
		String process = modelData.get("process");
		//修改现金流的生成规则，按照租金计划和资金计划生成
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		ConditionBean cb = new ConditionBean();
		cb.setEndDate(modelData.get("enddate"));
		cb.setStartDate(modelData.get("startdate"));
		cb.setLeaseAmtDate(modelData.get("finaleaseamtdate"));
		TabCalBean tcb = new TabCalBean();
		tcb.setFundFundPlan_tb("finance_fund_fund_plan_temp");// 财务资金收付计划(收入折现流程)
		tcb.setContOrProjCName("contract_id");
		tcb.setCondition_tb("contract_condition_temp");
		tcb.setContractCashTb("finance_cash_detail_temp");// 财务现金流(收入折现流程)
		tcb.setFinacCashTb("contract_fina_cash_detail_temp");
		tcb.setRentPlan_tb("finance_fund_rent_plan_temp");// 财务租金计划(收入折现流程)
		tcb.setRentFinaPlan_tb("contract_fina_rent_plan_temp");// 财务租金计划
		tcb.setFinanceIncomeDiscount("finance_income_discount_temp");//收入折现表
		tcb.setContOrProjCValue(modelData.get("contractId"));
		tcb.setCalType(process);
		tcb.setDocId(modelData.get("docId"));
		cb.setDocId(modelData.get("docId"));
		ffc.addFundFundTempForFinance(fundPlanList, cb,tcb);
		String  fundRentPlan = modelData.get("json_fund_rent_plan_str");
		//在生成现金流之前，需要将重新插入临时的租金计划，防止流程开始，无临时数据导致现金流生成出现问题
		RentPlanContrCalDAOImpl rpd = new RentPlanContrCalDAOImpl();
		rpd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);
		rpd.addRentPlanByJsonStr(tcb, fundRentPlan);
		
		idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
		idd.addCashDetailsForFinance(tcb, cb);//***************现金流生成计划要更改
		String hsql = "";
		String hql = "";
		List<FinanceCashDetailTemp> cashDetailList = null;
		//List<FinanceFundFundPlanTemp> fundfundplanlist = null;
		List<FinanceIncomeDiscountTemp> incomediscountList = null;
		
		hsql = " FROM  FinanceCashDetailTemp where contractId = '"+modelData.get("contractId")+"' AND docId = '"+ modelData.get("docId")+"' order by onhireId ";
		cashDetailList = tableService.findResultsByHSQL(hsql);
//		hql = " FROM   FinanceFundFundPlanTemp where contractId = '"+modelData.get("contractId")+"' AND docId = '"+ modelData.get("docId")+"' order by feeType,paymentId ";
//		fundfundplanlist = tableService.findResultsByHSQL(hql);
		Collections.sort(cashDetailList);
		List<String> cashdetail = new ArrayList<String>();
		for(FinanceCashDetailTemp cash:cashDetailList){
			cashdetail.add(cash.getNetFlow().toString());
		}
		String finairr 	= IrrTools.getIRRByNewton(cashdetail, "12");
		IncomeDiscountService ids=new IncomeDiscountServiceImpl();
		
		id.setFinaIrr(finairr);
		id.setTaxratio(modelData.get("taxratio"));
		id.setDocId(modelData.get("docId"));
		id.setContractId(modelData.get("contractId"));
		ids.getIncomeDiscount(id, cashDetailList);//生成收入折现数据
		List<IncomeDiscountBean> incomeDiscountlist = this.getIncomeDiscountListForFinance(cashdetail,id);
		ids.deleteIncomeDiscount(tcb);				//从临时表删除
		ids.addIncomeDiscount(incomeDiscountlist,id,tcb);//插入临时表
		
		hql = " FROM   FinanceIncomeDiscountTemp where contractId = '"+modelData.get("contractId")+"' AND docId = '"+ modelData.get("docId")+"' order by acountDate ";
		incomediscountList = tableService.findResultsByHSQL(hql);
		
		//String xirr = IrrTools.getIrr(cashDetailList, process,true);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("finairr", new BigDecimal(finairr).setScale(6,BigDecimal.ROUND_HALF_UP).toString());//更新finairr
//		result.put("xirr", xirr);
		result.put("fundcashdetail", cashDetailList);
		//result.put("fundfundcharge", fundfundplanlist);
		result.put("incomediscount", incomediscountList);
		return result;
	}
	
	private List<IncomeDiscountBean> getIncomeDiscountListForFinance(List<String> cashDetailList,IncomeDiscount id) {
		List<IncomeDiscountBean> list=new ArrayList<IncomeDiscountBean>();
		
		List<String> financeIncomeWithTax 	= id.getFinanceIncomeWithTax();//融资收入（含税）
		List<String> financeincomeWithoutTax = id.getFinanceincomeWithoutTax();//融资收入（不含税）
		List<String> investmentDecrease = id.getInvestmentDecrease();//租赁投资净额减少额
		List<String> overInvestmentDecrease = id.getOverInvestmentDecrease();//租赁投资净额余额
		List<String> overDueAccounts = id.getOverDueAccounts();//长期应收款-原值(剩余应收款)
		List<String> unconfirmedProfit = id.getUnconfirmedProfit();//未确认融资收益=overDueAccounts-overInvestmentDecrease
		List<String> acountDate =	id.getAcountDate();
		String contractId=id.getContractId();
		String docId=id.getDocId();
		for(int i=0;i<cashDetailList.size();i++){
			
			IncomeDiscountBean fid=new IncomeDiscountBean();
			fid.setCashDetail(cashDetailList.get(i));
			fid.setFinanceIncomeWithTax(financeIncomeWithTax.get(i));
			fid.setFinanceincomeWithoutTax(financeincomeWithoutTax.get(i));
			fid.setInvestmentDecrease(investmentDecrease.get(i));
			fid.setOverInvestmentDecrease(overInvestmentDecrease.get(i));
			fid.setOverDueAccounts(overDueAccounts.get(i));
			fid.setUnconfirmedProfit(unconfirmedProfit.get(i));
			fid.setAcountDate(acountDate.get(i));
			fid.setContractId(contractId);
			fid.setDocId(docId);
			list.add(fid);
		}
		return list;
	}

	@Override
	public Map<String, Object> getCustConditionInfo(Map<String, String> paramMap)
			throws Exception {
		String docId = paramMap.get("docid");
		String custId = paramMap.get("custid");
		Map<String, Object> returnMapInfo = new HashMap<String, Object>();
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("custId", custId);
		queryParams.put("docId", docId);
		List<CustFundRentPlan> rentPlanList = tableService.findEntityByProperties(CustFundRentPlan.class, queryParams);
		List<CustCashDetail> cashDetailList = tableService.findEntityByProperties(CustCashDetail.class, queryParams);
		Collections.sort(rentPlanList);
		Collections.sort(cashDetailList);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("custid", custId);
		paramsMap.put("docid", docId);
		String fundFundPlanList =   tableService.getJsonArrayData("eleasing/workflow/rent/rent_cal/cust_fund_fund_plan.xml", paramsMap).toString();
		queryParams.remove("custId");
		CustInfo cust = new CustInfo();
		cust.setId(custId);
		queryParams.put("custId", cust);
		CustCondition condition =  tableService.findEntityByProperties(CustCondition.class, queryParams).get(0);
		returnMapInfo.put("rentPlanList", rentPlanList);
		returnMapInfo.put("cashDetailList", cashDetailList);
		returnMapInfo.put("fundFundPlanList", fundFundPlanList);
		returnMapInfo.putAll(this.tableService.getEntityPropertiesToStringMap(condition, null,""));
		returnMapInfo.put("flag", "ok");
		return returnMapInfo;
	}
	
	
	@Override
	public Map<String, Object> doFacCalculateRentPlan(
			Map<String, String> modelData) throws Exception {
		//合同号
		String contractId = modelData.get("contractid");
		String projId = modelData.get("projid");
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.putAll(modelData);
		ConditionBean cb = ObjectConvertUtils.convertMapToBean(ConditionBean.class, paramsMap);
		cb.setIsFactory(true);
		if (contractId == null || contractId.equals("")) {
			cb.setProjId(projId);
		} else {//挂靠合同号
			cb.setProjId(contractId);
		}
		String fundRentPlan = modelData.get("json_fund_rent_plan_str");
		String docId = modelData.get("docid");
		String process = modelData.get("process");
		FundRentPlanBean frpf =  getFundRentPlanBeanByJsonStr(fundRentPlan,cb.getProjId(),docId,process,"0");
		// 保存商务条件
		TabCalBean tcb = TbBeanTools.getTabInfo(process, cb);
		ConditionServiceImpl csi = new ConditionServiceImpl();
		//生成租金计划
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpf, tcb, 1,cb);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht = RentCalculateHelper.createFactoringFundPlanCashIrr(cb, tcb, null, frpf);
		calFacConditionInfo(re_ht, cb, process, frpf);
		csi.addConditionByCal(tcb, cb);
		return re_ht;
	}
	
	private void  calFacConditionInfo(Map<String, Object> result,ConditionBean cb,String process,FundRentPlanBean frpf) throws Exception{
		//先算出月Irr
		BigDecimal baseRate = getBaseRate(cb.getLeaseAmtDate(),cb.getLeaseTermDay());
		String floatingRadio = this.tableService.findEntityByID(DictionaryData.class, "factorFundRadio").getEnName();
		//计算资金成本(月)
		BigDecimal factoringFundCostMonth = baseRate.multiply(new BigDecimal(floatingRadio).divide(new BigDecimal(100)).add(new BigDecimal(1))).divide(new BigDecimal(12),Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP);
		
		String  irr = (String)result.get("irr");
		//计算用月irr
		BigDecimal monthIrr = new BigDecimal(irr).divide(new BigDecimal(12),Scale.GENERAL_RATE);
		cb.setFactoringIrrYear(new BigDecimal(irr));
		//月Irr
		String factoringirrmonth = new BigDecimal(irr).divide(new BigDecimal(12), Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP).toString();
		cb.setFactoringirrmonth(new BigDecimal(factoringirrmonth));
		result.put("factoringirrmonth", factoringirrmonth);
		List cashDetailList = (List)result.get("cashDetailList");
		List rentPlanList = (List)result.get("rentPlanList");
		//名义利率
		String  factoringyearrateyear = IrrTools.getIrrFactoringFlat(cashDetailList, process, cb, frpf);
		String factoringyearratemonth = new BigDecimal(factoringyearrateyear).divide(new BigDecimal(12), Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP).toString();
		cb.setFactoringYearRateYear(new BigDecimal(factoringyearrateyear));
		cb.setFactoringFlatRateMonth(new BigDecimal(factoringyearratemonth));
		result.put("factoringyearrateyear", factoringyearrateyear);
		result.put("factoringyearratemonth", factoringyearratemonth);
		//计算Spread
		//Spread(月) = IRR(月) - 资金成本（月）
		//Spread(年) = Spread(月) * 12;
		
		String factoringspreadmonth = monthIrr.subtract(factoringFundCostMonth).toString(); 
		String factoringspreadyear = new BigDecimal(factoringspreadmonth).multiply(new BigDecimal(12)).toString();
		
		//平面利率
		String factoringflatrateyear = baseRate.multiply(cb.getRateFloat().divide(new BigDecimal(100)).add(new BigDecimal(1))).toString();
		String factoringflatratemonth = new BigDecimal(factoringflatrateyear).divide(new BigDecimal(12)).toString();
		
		cb.setFactoringSpreadMonth(new BigDecimal(factoringspreadmonth));
		cb.setFactoringSpreadYear(new BigDecimal(factoringspreadyear));
		result.put("factoringspreadmonth", factoringspreadmonth);
		result.put("factoringspreadyear", factoringspreadyear);
		result.put("factoringflatrateyear", factoringflatrateyear);
		result.put("factoringflatratemonth", factoringflatratemonth);
		//GP
		String gp = IrrTools.calGP(cashDetailList, process, irr, factoringspreadyear);
		result.put("gp", gp);
		cb.setGp(new BigDecimal(gp));
	}
	private BigDecimal getBaseRate(String leaseamtdate , Integer leasetermday) throws Exception {
		int range = leasetermday/(30*6+1);
		String hql = "from FundStandardInterest i where i.startDate<? order by i.startDate desc";
		List<FundStandardInterest> interests = this.tableService.findResultsByHSQL(hql, leaseamtdate);
		BigDecimal baseRate = BigDecimal.ZERO;
		if(interests.size() > 0){
			FundStandardInterest stand = interests.get(0);
			switch (range) {
			case 0://半年
				baseRate = stand.getBaseRateHalf();
				break;
			case 1://半年-1年
				baseRate = stand.getBaseRateOne();
				break;
			case 2://1年-1年半
			case 3://1年半-2年
			case 4://2年-2年半
			case 5://2年半-3年	
				baseRate = stand.getBaseRateThree();	
				break;
			case 6://3年-3年半	
			case 7://3年半-4年
			case 8://4年半-5年
				baseRate = stand.getBaseRateFive();	
				break;
			default://5年以上
				baseRate = stand.getBaseRateAbovefive();
				break;
			}
		}
		return baseRate ;
	}

	@Override
	public Map<String, Object> calculateRentByIrr(
			Map<String, String> modelData, InputStream file) throws Exception {
		
		ConditionBean cb = ObjectConvertUtils.convertMapToBeanEBFIL(ConditionBean.class, modelData);
		List<FundPutPlan> fpps = this.getFundPutList(modelData);
		cb.setFpps(fpps);
		if(modelData.containsKey("calType")&&"onhire_change".equals(modelData.get("calType"))){
			//资产变更
			FundPlanDAOImpl fundPlanDAO = new FundPlanDAOImpl();
			cb = fundPlanDAO.findContractCondition(modelData.get("contractId"));
		}
		if(null==cb.getProjId()){
			cb.setProjId(cb.getContractId());
		}
		List<Map<String, String>> result =  ExcelReader.readExcelDatas(file,ExcelVersionEnum.valueOf(modelData.get("excelType")));
		List<FundRentPlanIrr> irrPlans = new ArrayList<FundRentPlanIrr>();
		for(int i = 0 ; i <  result.size() ; i++  ){
			Map<String, String>irrPlan  =  result.get(i);
			for(String key:irrPlan.keySet()){
				if(key.equals("rentlist")){
					irrPlan.put(key, irrPlan.get(key).replaceAll("第", "").replaceAll("期", ""));
				}else if(key.equals("plandate")){
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
					Date date=sdf.parse(irrPlan.get(key));
					irrPlan.put(key, DateUtil.getSystemTimeByFormat(date, "yyyy-MM-dd"));
				}
			}
			FundRentPlanIrr irr = ObjectConvertUtils.convertMapToBeanEBFIL(FundRentPlanIrr.class, irrPlan);
			if(logger.isInfoEnabled()){
				logger.info("租金计划，第"+(i+1)+"数据检查开始============================");
			}
			FundRentPlanIrr.checkIrrFundRentPlan(irr, i);
			irrPlans.add(irr);
		}
		cb.setIncomeNumber(irrPlans.size());
		//先校验本金之和是否等于融资额
		BigDecimal corpusTotal = BigDecimal.ZERO;
		for(FundRentPlanIrr irrRentPlan : irrPlans){
			corpusTotal = corpusTotal.add(new BigDecimal(irrRentPlan.getBusinesscorpus()));
		}
		logger.info("本金之和为：====================================="+corpusTotal);
		if(new BigDecimal(cb.getCleanLeaseMoney()).subtract(corpusTotal).doubleValue()<=-0.1|| new BigDecimal(cb.getCleanLeaseMoney()).subtract(corpusTotal).doubleValue()>=0.1){
			throw new BusinessException("上传Excel中的本金之和需要等于融资额，请核实后再进行上传！");
		}
		String process = modelData.get("process");
		TabCalBean tcb = new TbBeanTools().getTabInfo(process, cb);
		RentCalcDo rcd = new RentCalcDo();
		Map<String, Object> resultMap = new HashMap<String, Object>();// 构建返回数据
		//List<FundPlanBean> fundPlanList = this.getFundPlanList(modelData);
		cb.setSettleMethod("excel_import");
		Hashtable<String, Object> re_ht = rcd.rentCal(cb, process, "", null, null,null ,irrPlans,null,null);
		//resultMap.putAll(modelData);
		resultMap.putAll(re_ht);
		if ("true".equals(re_ht.get("isSucess"))) {
			bindConditionOtherResult(cb, resultMap,modelData ,process, tcb);
			resultMap.put("leaseterm", cb.getLeaseTerm());
			resultMap.put("incomenumber", cb.getIncomeNumber());
		} else {
			throw new LeasingException((String)re_ht.get("message"));
		}
		return resultMap;
	}
	private void bindConditionOtherResult(ConditionBean cb,Map<String, Object> result,Map<String, String> map,String process,TabCalBean tcb ) throws Exception{
		List rentPlanList = (List)result.get("rentPlanList");
		List cashDetailList = (List)result.get("cashDetailList");
		//计算粗利
		BigDecimal grossProfit =  this.getGrossProfit(rentPlanList,cb,process);
		logger.info("项目粗利>>>>>>>>>>>>>>>>>>>>"+grossProfit);
		//净融资额 公式：净融资额==购买金额－首付款－保证金-其他收入+其他支出-前收第1期租金+保险费-厂商返利-手续费
		BigDecimal cleancreditmoney = this.getCleancreditmoney(rentPlanList, result,process,map);
		logger.info("净融资额>>>>>>>>>>>>>>>>>>>>"+cleancreditmoney);
		BigDecimal cleanCreditRatio = cleancreditmoney.divide(new BigDecimal(cb.getEquipAmt()),20, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP);
		//计算期初付款总计
		BigDecimal firstpaymenttotal = this.getFirstpaymenttotal(result,process);
		logger.info("期初付款总计>>>>>>>>>>>>>>>>>>>>"+firstpaymenttotal);
		
		if(map.containsKey("calType")&&"onhire_change".equals(map.get("calType"))){
			//资产变更情况 
			cleancreditmoney=this.getCleancreditmoneyTwo(rentPlanList, result, process, map, cb);
			firstpaymenttotal=this.getFirstpaymenttotalTwo(result, process, cb);
		}
		
		cb.setCleanCreditMoney(cleancreditmoney);
		cb.setFirstPaymentTotal(firstpaymenttotal.toString());
		cb.setGrossProfit(grossProfit);
		ConditionServiceImpl csi = new ConditionServiceImpl();
		csi.addConditionByCal(tcb, cb);
		if (rentPlanList.size() > 0 && cashDetailList.size() > 0 ) {
			logger.info("process:"+process);
			result.put("irr", cb.getIrr());
			String yearRate = new BigDecimal(cb.getYearRate()).setScale(Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP).toString();
			result.put("yearrate", yearRate);
			result.put("dayrate", new BigDecimal(cb.getYearRate()).divide(new BigDecimal(360),Scale.GENERAL_RATE,BigDecimal.ROUND_HALF_UP).toString());
			
			result.put("enddate", cb.getEndDate());
			result.put("cleancreditmoney", cleancreditmoney);
			result.put("firstpaymenttotal", firstpaymenttotal);
			result.put("grossprofit", grossProfit);
			result.put("cleancreditratio", cleanCreditRatio);
		}
	}
	public BigDecimal getFirstpaymenttotal(Map<String, Object> result,String process){
		BigDecimal firstpaymenttotal = BigDecimal.ZERO;
		//String periodtype  = (String) result.get("periodtype");// 期初（期末）支付 // 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		
		BigDecimal cleanleasemoney = new BigDecimal( NumberUtils.nullToZero( (String) result.get("cleanleasemoney") ) );//融资额
		
		//BigDecimal firstpayment = new BigDecimal( NumberUtils.nullToZero( (String) result.get("firstpayment") ) );//首付款
		//保证金
		BigDecimal cautionmoney = new BigDecimal( NumberUtils.nullToZero( (String) result.get("cautionmoney") ) );//保证金 
		//手续费
		BigDecimal handlingchargemoney =  new BigDecimal( NumberUtils.nullToZero( (String) result.get("handlingchargemoney") ) );//手续费
		//管理费
		BigDecimal managementmoney = new BigDecimal( NumberUtils.nullToZero( (String) result.get("managementmoney") ) );//管理费
		//其他收入
		BigDecimal otherincome = new BigDecimal( NumberUtils.nullToZero( (String) result.get("otherincome") ) );//其他收入
		//其他支出
		BigDecimal otherexpenditure = new BigDecimal( NumberUtils.nullToZero( (String) result.get("otherexpenditure") ) );
		//保险费
		BigDecimal insuremoney = new BigDecimal( NumberUtils.nullToZero( (String) result.get("insuremoney") ) );
		
		//期初付款总计   = 融资额  - 手续费 - 管理费 - 保证金 - 保险费  + 其他支出  - 其他收入
		firstpaymenttotal =cleanleasemoney.subtract(handlingchargemoney).subtract(managementmoney).subtract(cautionmoney).subtract(insuremoney).add(otherexpenditure).subtract(otherincome) ;
		//返回
		return firstpaymenttotal;
	}
	public BigDecimal getFirstpaymenttotalTwo(Map<String, Object> result,String process,ConditionBean cb){
		BigDecimal firstpaymenttotal = BigDecimal.ZERO;
		//String periodtype  = (String) result.get("periodtype");// 期初（期末）支付 // 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		
		BigDecimal cleanleasemoney = new BigDecimal( NumberUtils.nullToZero(cb.getCleanLeaseMoney()) );//融资额
		
		//BigDecimal firstpayment = new BigDecimal( NumberUtils.nullToZero( (String) result.get("firstpayment") ) );//首付款
		//保证金
		BigDecimal cautionmoney = new BigDecimal( NumberUtils.nullToZero(cb.getCautionMoney()) );//保证金 
		//手续费
		BigDecimal handlingchargemoney =  new BigDecimal( NumberUtils.nullToZero(cb.getHandlingChargeMoney() ) );//手续费
		//管理费
		BigDecimal managementmoney = new BigDecimal( NumberUtils.nullToZero(cb.getManagementMoney()) );//管理费
		//其他收入
		BigDecimal otherincome = new BigDecimal( NumberUtils.nullToZero(cb.getOtherIncome() ) );//其他收入
		//其他支出
		BigDecimal otherexpenditure = new BigDecimal( NumberUtils.nullToZero(cb.getOtherExpenditure()) );
		//保险费
		BigDecimal insuremoney = new BigDecimal( NumberUtils.nullToZero(cb.getInsureMoney() ) );
		
		//期初付款总计   = 融资额  - 手续费 - 管理费 - 保证金 - 保险费  + 其他支出  - 其他收入
		firstpaymenttotal =cleanleasemoney.subtract(handlingchargemoney).subtract(managementmoney).subtract(cautionmoney).subtract(insuremoney).add(otherexpenditure).subtract(otherincome) ;
		//返回
		return firstpaymenttotal;
	}

	@Override
	public Map<String, Object> updateFundPutPlan(Map<String, String> modelData) throws Exception {
		List<FundPutPlan> fpps = this.getFundPutList(modelData);
		ContractInfo contractInfo=this.tableService.findEntityByID(ContractInfo.class, modelData.get("contract_id"));
		ContractCondition ContractCondition=contractInfo.getContractCondition();
		ConditionBean cb=new ConditionBean();
		cb=this.tableService.copyAndOverrideExistedValueFromObject(ContractCondition, cb);
		cb.setDocId(modelData.get("docId"));
		cb.setFpps(fpps);
		cb.setIncomeNumberYear(ContractCondition.getIncomeNumberYear().getId().replace("income_", ""));
		List<FundPlanBean> ffcpList=new ArrayList<FundPlanBean>();
		RentCalculateHelper.graceOperate(cb, ffcpList);
		TabCalBean tcb = TbBeanTools.getTabInfo("");
		tcb.setDocId(cb.getDocId());
		tcb.setContOrProjCValue(contractInfo.getContractId());
		FundFundChargeServiceImpl  ffc = new FundFundChargeServiceImpl();
		ffc.addFundFundTemp(ffcpList, cb,tcb);
		Map<String, Object> result =new HashMap<String, Object>();
		result.put("rentPlanList", ffcpList);
		return result;
	}
}