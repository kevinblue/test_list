package com.reckon.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.Condition;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.FundRentPlanIrr;
import com.reckon.bean.KnowingRentBean;
import com.reckon.bean.RentPlan;
import com.reckon.bean.RentPlanBean;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.utils.ExcelReader;
import com.reckon.calculation.utils.PMTCalculateUtil;
import com.reckon.calculation.utils.Scale;
import com.reckon.commons.helper.ObjectConvertUtils;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.controller.RentCalculateControllerHelper;
import com.reckon.convert.ConvertMapToBeanService;
import com.reckon.dao.impl.FundPlanDAOImpl;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.dao.impl.RentChargeDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.entity.proj.ProjConditionTemp;
import com.reckon.entity.proj.ProjFinaCashDetailTemp;
import com.reckon.entity.proj.ProjFinaRentPlanTemp;
import com.reckon.entity.proj.ProjFundRentPlanTemp;
import com.reckon.rentcalc.service.impl.pub.ConditionServiceImpl;
import com.reckon.rentcalc.service.impl.pub.FundFundChargeServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentalServiceImpl;
import com.reckon.rentcalc.web.RentCalcDo;
import com.reckon.rentcharge.web.RentChargeDo;
import com.reckon.rentcharge.web.RentPlanModDo;
import com.reckon.service.FundFundChargePlanService;
import com.reckon.service.RentCalculateService;
import com.reckon.util.IrrTools;
import com.reckon.util.MoneyUtils;
import com.reckon.util.NumberUtils;
import com.reckon.util.TbBeanTools;
import com.reckon.util.Tools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.action.contract.approval.ContractApprovalStartAction;
import com.tenwa.leasing.entity.base.OwnInfo;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.quot.CustCashDetail;
import com.tenwa.leasing.entity.cust.quot.CustCondition;
import com.tenwa.leasing.entity.cust.quot.CustFinaCashDetail;
import com.tenwa.leasing.entity.cust.quot.CustFinaRentPlan;
import com.tenwa.leasing.entity.cust.quot.CustFundRentPlan;
import com.tenwa.leasing.utils.LeasingException;

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

	public Map<String, Object> calculateRentPlan(Map<String, String> modelData) throws Exception {
//		Map<String, Object> return_model = new HashMap<String, Object>();
//		List<FundRentPlanBean> rentPlanDetails = new ArrayList<FundRentPlanBean>();
//		conditionBean = this.convertMapToBeanService.convertContionBean(conditionBean, modelData);
//		RentInfoBox rentInfoBox = new RentInfoBox();
//		// 1参数的提取
//		// 1.1租金测算方法
//		String settle_method = conditionBean.getSettle_method();
//		if ("RentCalcType1".equals(settle_method)) {// 等额租金
//			rentInfoBox = EqualPaymentUtil.getRentInfoBox(conditionBean);
//		} else if ("RentCalcType2".equals(settle_method)) {// 等差租金
//			rentInfoBox = EqualDiffRentUtil.getRentInfoBox(conditionBean);
//		} else if ("RentCalcType3".equals(settle_method)) {// 等比租金
//			rentInfoBox = EqualRatioRentUtil.getRentInfoBox(conditionBean);
//		} else if ("RentCalcType4".equals(settle_method)) {// 等额本金
//			rentInfoBox = EqualCorpusUtil.getRentInfoBox(conditionBean);
//		} else if ("RentCalcType5".equals(settle_method)) {// 等差本金
//			rentInfoBox = EqualDiffCorUtil.getRentInfoBox(conditionBean);
//		} else if ("RentCalcType6".equals(settle_method)) {// 等比本金
//			rentInfoBox = EqualRatioCorpusUtil.getRentInfoBox(conditionBean);
//		} else if ("RentCalcType7".equals(settle_method)) {// 均息法
//			rentInfoBox = SettleLawUtil.getRentInfoBox(conditionBean);
//		}
//		for (int i = 0; i < rentInfoBox.getPlanDate().size(); i++) {
//			FundRentPlanBean fundRentPlanBean = new FundRentPlanBean();
//			fundRentPlanBean.setRent_list(i + 1);
//			fundRentPlanBean.setPlan_date(rentInfoBox.getPlanDate().get(i));
//			fundRentPlanBean.setRent(rentInfoBox.getRent().get(i));
//			fundRentPlanBean.setCorpus(rentInfoBox.getCorpus().get(i));
//			fundRentPlanBean.setInterest(rentInfoBox.getInterest().get(i));
//			fundRentPlanBean.setCorpus_overage(rentInfoBox.getCorpusOverge().get(i));
//			rentPlanDetails.add(fundRentPlanBean);
//		}
//		return_model.put("rentPlanDetails", rentPlanDetails);
//		return_model.put("rentCashDetails", rentInfoBox.getRentDetails());
//		return_model.put("irr", rentInfoBox.getIrr());
//		return return_model;
		return null;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public Map<String, Object> calculateRentPlanOld(Map<String, String> modelData,List<KnowingRentBean> kniwnRentBeans) throws Exception {
		
		ConditionBean cb = new ConditionBean();
		//FIXME:ALL改这段代码的人！这里强制改成使用原始的取值注入值方式，方便后方代码！详细原因请看该方法注释说明!!!
		this.convertMapToBeanService.convertContionBeanByOriginal(cb, modelData);
		/**
		 * 读取前台特殊规则配置
		 */
		List<SpecialRulesBean> srb= RentCalculateHelper.getSpecialRulesList(modelData);
		//合同号
		String contractId = modelData.get("contractid");
		//动作类型： condition,onhire,onhire_more,paln,adjust
		String reckonType = modelData.get("reckontype");
		//立项时值是：cust_process, proj_process, contract_process
		String process = modelData.get("process");
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
		Boolean isCalculate = false;
		Map<String, Object> result = new HashMap<String, Object>();// 构建返回数据
		if("rate".equals(cb.getRentOrRate())){
			isCalculate = true;
		}else{
			//通过PMT公式推导出来一个0利率下的租金
			BigDecimal rentZero =  PMTCalculateUtil.getPMT(BigDecimal.ZERO, cb.getIncomeNumber(), new BigDecimal(cb.getCleanLeaseMoney()), new BigDecimal(cb.getEquipEndValue()), "period_type_0".equals(cb.getPeriodType()) ? 0 : 1);
			logger.info("0利率下的测算租金为>>>>>>>>>>>>>>>>>>>>"+rentZero);
			BigDecimal totalRentZero = rentZero.multiply(new BigDecimal(cb.getIncomeNumber())).setScale(BigDecimal.ROUND_HALF_UP, Scale.RENT_SCALE);
			if(new BigDecimal(cb.getYearRate()).compareTo(BigDecimal.ZERO) <=0 && rentTotal.compareTo(totalRentZero) < 0 ){
				result.put("rentcalculaters", "rentSmall");
			}else{
				isCalculate = true;
			}	
		}
		
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
				re_ht = rcd.rentCal(cb, process, "", adjustList.size() == 0 ? null : adjustList.toArray(new String[0]), kniwnRentBeans,fundPlanList,srb,null,rentList );
			}
			//***********************************************************************************************************************************END!
			
			result.putAll(modelData);
			result.putAll(re_ht);
			logger.info("isSucess:"+re_ht.get("isSucess"));
			if ("true".equals(re_ht.get("isSucess"))) {
				List rentPlanList = (List)result.get("rentPlanList");
				//计算粗利
				//BigDecimal grossProfit =  this.getGrossProfit(rentPlanList,cb,process);
				//净融资额 公式：净融资额==购买金额－首付款－保证金-其他收入+其他支出-前收第1期租金+保险费-厂商返利-手续费
				BigDecimal cleancreditmoney = this.getCleancreditmoney(rentPlanList, result,process,modelData);
				logger.info("净融资额>>>>>>>>>>>>>>>>>>>>"+cleancreditmoney);
				BigDecimal cleanCreditRatio = cleancreditmoney.divide(new BigDecimal(cb.getEquipAmt()),20, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP);
				//计算期初付款总计
				BigDecimal firstpaymenttotal = this.getFirstpaymenttotal(rentPlanList, result,process);
				logger.info("期初付款总计>>>>>>>>>>>>>>>>>>>>"+firstpaymenttotal);
				cb.setCleanCreditMoney(cleancreditmoney);
				cb.setFirstPaymentTotal(firstpaymenttotal.toString());
				//cb.setGrossProfit(grossProfit);
				ConditionServiceImpl csi = new ConditionServiceImpl();
				csi.addConditionByCal(tcb, cb);
				if (rentPlanList.size() > 0  ) {
					logger.info("process:"+process);
					result.put("irr", cb.getIrr());
					result.put("yearrate", cb.getYearRate());
					result.put("enddate", cb.getEndDate());
					result.put("cleancreditmoney", cleancreditmoney);
					result.put("firstpaymenttotal", firstpaymenttotal);
					//result.put("grossprofit", grossProfit);
					result.put("cleancreditratio", cleanCreditRatio);
				}
			} else {
				throw new LeasingException((String)re_ht.get("message"));
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @Title: getGrossProfit
	 * @author zhangc
	 * @Description: 项目粗利：财务利息总和+手续费+管理费-保险费（我司）+租前息+名义货价+其他收入-其他支出
	 * @return
	 * @return BigDecimal
	 * @throws
	 */
	public BigDecimal getGrossProfit(List<RentPlanBean> rentPlanList,ConditionBean cb,String process){
		BigDecimal interestTotal = BigDecimal.ZERO;
		for(RentPlanBean obj : rentPlanList){
			interestTotal = interestTotal.add(new BigDecimal(obj.getInterest()));
		}
		BigDecimal  handleChargeMoney =new BigDecimal(null == cb.getHandlingChargeMoney() || cb.getHandlingChargeMoney().length() <= 0 ? "0" : cb.getHandlingChargeMoney()) ;
		BigDecimal insureMoney = new BigDecimal( null == cb.getInsureMoney()  || cb.getInsureMoney().length() <= 0 ? "0" : cb.getInsureMoney());
		BigDecimal managementMoney = new BigDecimal(null ==  cb.getManagementMoney()  || cb.getManagementMoney().length() <=0  ? "0" : cb.getManagementMoney());
		BigDecimal beforeInterest = new BigDecimal(null == cb.getBeforeInterest() || cb.getBeforeInterest().length() <= 0 ? "0" : cb.getBeforeInterest());
		BigDecimal nominalPrice = new BigDecimal(null == cb.getNominalPrice() || cb.getNominalPrice().length() <= 0 ? "0" : cb.getNominalPrice());
		BigDecimal otherIncome = new BigDecimal(null == cb.getOtherIncome() || cb.getOtherIncome().length() <= 0 ? "0" : cb.getOtherIncome());
		BigDecimal otherExpenditure = new BigDecimal(null == cb.getOtherExpenditure() || cb.getOtherExpenditure().length() <= 0 ? "0" : cb.getOtherExpenditure());
		return interestTotal.add(handleChargeMoney).add(managementMoney).subtract(insureMoney).add(beforeInterest).add(nominalPrice).add(otherIncome).subtract(otherExpenditure);
	}
	/**
	 * 调息重新计算
	 * @param rentPlanList
	 * @param cb
	 * @param process
	 * @return
	 */
	public BigDecimal getGrossProfitR(List<String> corpusList,ConditionBean cb,String process){
		BigDecimal interestTotal = BigDecimal.ZERO;
		for(String str:corpusList){
			interestTotal = interestTotal.add(new BigDecimal(str));
		}
		BigDecimal  handleChargeMoney =new BigDecimal(null == cb.getHandlingChargeMoney() || cb.getHandlingChargeMoney().length() <= 0 ? "0" : cb.getHandlingChargeMoney()) ;
		BigDecimal insureMoney = new BigDecimal( null == cb.getInsureMoney()  || cb.getInsureMoney().length() <= 0 ? "0" : cb.getInsureMoney());
		BigDecimal managementMoney = new BigDecimal(null ==  cb.getManagementMoney()  || cb.getManagementMoney().length() <=0  ? "0" : cb.getManagementMoney());
		BigDecimal beforeInterest = new BigDecimal(null == cb.getBeforeInterest() || cb.getBeforeInterest().length() <= 0 ? "0" : cb.getBeforeInterest());
		BigDecimal nominalPrice = new BigDecimal(null == cb.getNominalPrice() || cb.getNominalPrice().length() <= 0 ? "0" : cb.getNominalPrice());
		BigDecimal otherIncome = new BigDecimal(null == cb.getOtherIncome() || cb.getOtherIncome().length() <= 0 ? "0" : cb.getOtherIncome());
		BigDecimal otherExpenditure = new BigDecimal(null == cb.getOtherExpenditure() || cb.getOtherExpenditure().length() <= 0 ? "0" : cb.getOtherExpenditure());
		return interestTotal.add(handleChargeMoney).add(managementMoney).subtract(insureMoney).add(beforeInterest).add(nominalPrice).add(otherIncome).subtract(otherExpenditure);
	}
	
	/**
	 * 
	 * <p>计算精授信额。</p>
	 * <p>公式：净授信额 = 设备款－首付款－保证金-其他收入+其他支出-期初第1期租金+保险费-厂商返利-手续费 - 管理费。</p>
	 * <p>其实前台存在这个JS的计算方法的cleancreditmoney(e)，因为未完成，我暂时在后台使用java的方式处理，如果前端JS完善了，这个方法可以寿终正寝，但是请注意前端JS计算注意精度问题。</p>
	 * @author 谢永伦
	 * @param rentPlanList  租金计划集合
	 * @param result 返回前台的MAP集合
	 * @return
	 */
	public BigDecimal getCleancreditmoney(List rentPlanList,Map<String, Object> result,String process,Map<String, String> map){
		BigDecimal cleancreditmoney = BigDecimal.ZERO;
		//modify by liaobo 2016-10-09 风险敞口=融资额-风险金
		/*String periodtype  = (String) map.get("periodtype");// 期初（期末）支付 // 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		
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
		if(null!=rentPlanList&&rentPlanList.size()>0){
			if("1".equals(periodtype) || "period_type_1".equals(periodtype)){
				if(process.equals("proj_process") ){
					firstRent = ((ProjFundRentPlanTemp) rentPlanList.get(0)).getRent() ;
				}else if(process.equals("quoted_price") ){
					firstRent = ((CustFundRentPlan) rentPlanList.get(0)).getRent() ;
				}else{
					firstRent = ((ContractFundRentPlanTemp) rentPlanList.get(0)).getRent() ;
				}
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
		BigDecimal insuremoney = new BigDecimal( NumberUtils.nullToZero( (String) map.get("insuremoney") ) );//保险费
		
		//计算
		BigDecimal subtracts = equipamt.subtract(firstpayment).subtract(returnPointIncome).subtract(interestSupport).subtract(cautionmoney).subtract(faccautionmoney).subtract(otherincome).subtract(firstRent).subtract(returnamt).subtract(handlingchargemoney).subtract(managementmoney);
		BigDecimal adds = otherexpenditure.add(insuremoney);
		//净授信额 = 设备款－首付款－保证金-厂商保证金-利息补贴-返点收入-其他收入-期初第1期租金-厂商返利-手续费 - 管理费+其他支出+保险费
		cleancreditmoney = subtracts.add(adds);*/
		BigDecimal cleanMoney=new BigDecimal( NumberUtils.nullToZero( (String) map.get("cleanleasemoney") ) );//融资额
		BigDecimal cautionMoney=new BigDecimal( NumberUtils.nullToZero( (String) map.get("cautionmoney") ) );//风险金
		//返回
		return cleanMoney.subtract(cautionMoney);
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
				if(process.equals("proj_process")){
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
			    List<Map<String, Object>> mapList = this.tableService.getBussinessDao().getJdbcTemplate().queryForList(sql,map.get("contractId"),map.get("docId"),contract_uuid);		    
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
		    .append("       where contract_id = ? and  doc_id =?")
		    .append(") sql1 ")
		    .append("left join  contract_info ci on ci.contract_id = sql1.contract_id ")
		    .append("left join ( ")
		    .append("    select contract_id,plan_list ,nvl(sum(nvl(rent,0)),0) as income_rent  ")
		    .append("    from  contract_fund_rent_income  ")
		    .append("    where contract_id = ? ")
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
		if(!"null".equals(json_fund_fund_charge_str)&&null!=json_fund_fund_charge_str){
			JSONArray jsonArray = new JSONArray(json_fund_fund_charge_str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				//jsonObject.put("settlemethod",((String)jsonObject.get("settlemethod")).replace("payfund", "PayFund"));
				//将JSON转换为map
				Map map = tableService.getStringMapByJsonObject(jsonObject);
				//将map再转换为FundPlanBean的对象
				FundPlanBean obj = ObjectConvertUtils.convertMapToBean(FundPlanBean.class, map);
				if(obj.getPayCustName() == null || obj.getPayCustName().equals("null")){
					obj.setPayCustName("");
				}
				//二次封装
				fundPlanList.add(obj);
			}
		}
		return fundPlanList;
	}
	/**
	 * <p>JSON中的租金计划转换为实体对象集合。</p>
	 * @author sea
	 * @param modelData
	 * @return
	 * @throws JSONException
	 */
	private List<RentPlanBean> getRentPlanList(Map<String, String> modelData) throws Exception{
		String json_rent_str = modelData.get("json_fund_rent_plan_str");//获取租金计划JSON字符串
		List<RentPlanBean> rentPlanList = new ArrayList<RentPlanBean>();
		if(!"null".equals(json_rent_str)&&null!=json_rent_str){
			JSONArray jsonArray = new JSONArray(json_rent_str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				//将JSON转换为map
				Map map = tableService.getStringMapByJsonObject(jsonObject);
				//将map再转换为FundPlanBean的对象
				RentPlanBean obj = ObjectConvertUtils.convertMapToBean(RentPlanBean.class, map);
				
				//二次封装
				rentPlanList.add(obj);
			}
		}
		return rentPlanList;
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
		ConditionBean cb = new ConditionBean();
		this.convertMapToBeanService.convertContionBeanByOriginal(cb, modelData);
		//合同号
		String contractId = modelData.get("contractid");
		String process = modelData.get("process");
		String projId = modelData.get("projid");
		if (contractId == null || contractId.equals("")) {
			cb.setProjId(projId);
		} else {//挂靠合同号
			cb.setProjId(contractId);
		}
		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(process, cb);
		tcb.setCalType(process);
		tcb.setDocId(modelData.get("docId"));
		List<FundPlanBean> fundPlanList = this.getFundPlanList(modelData);
		FundFundChargeServiceImpl  ffc = new FundFundChargeServiceImpl();
		if(null==fundPlanList||fundPlanList.size()==0){//插入操作
			FundFundChargePlanService chargeService = (FundFundChargePlanService)WebUtil.getApplicationContext().getBean("fundFundChargePlanService");
			Map<String, Object> result= chargeService.createFundPlan(cb,modelData.get("lastRent"));
			fundPlanList = (List<FundPlanBean>)result.get("fundchargeplan");
		}
		//向临时表当中插入数据
		ffc.addFundFundTemp(fundPlanList, cb,tcb);
		Collections.sort(fundPlanList);
		//风险敞口计算
		BigDecimal cleancreditmoney = this.getCleancreditmoney(null, null,process,modelData);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("fundchargeplan", fundPlanList);
		result.put("cautionmoneyremain", cb.getCautionMoneyRemain());//保证金退还
		result.put("cautiondeductionmoney", cb.getCautionDeductionMoney());//保证金抵扣
		result.put("cleancreditmoney", cleancreditmoney);//风险敞口
		return result;
	}

	@Override
	public Map<String, Object> updateCashFlow(Map<String, String> modelData)
			throws Exception {
		ConditionBean cb = new ConditionBean();
		this.convertMapToBeanService.convertContionBeanByOriginal(cb, modelData);
		List<SpecialRulesBean> srb= RentCalculateHelper.getSpecialRulesList(modelData);
		if(srb.size()>0){
			cb.setIncomeNumberYear(srb.get(0).getRegular_months());
		}
		String contractId = modelData.get("contractid");
		String process = modelData.get("process");
		String projId = modelData.get("projid");
		if (contractId == null || contractId.equals("")) {
			cb.setProjId(projId);
		} else {//挂靠合同号
			cb.setProjId(contractId);
		}
		TabCalBean tcb = TbBeanTools.getTabInfo(process, cb);
		tcb.setCalType(process);
		tcb.setDocId(modelData.get("docId"));
		//前台获取 租金计划
		List<RentPlanBean> rentPlanList=this.getRentPlanList(modelData);
		List<String> planDateList = new ArrayList<String>(); // 租金实际收付日期
		List<String> calPlanDate = new ArrayList<String>(); // 租金测算收付日期
		List<String> yearRateList = new ArrayList<String>();// 年利率
		List<String> rentList = new ArrayList<String>(); // 租金列表
		List<String> actualRentList = new ArrayList<String>(); // 实际租金列表
		List<String> corpusBusinessList = new ArrayList<String>(); // 本金列表
		List<String> interestBusinessList = new ArrayList<String>(); // 利息列表
		List<String> rent_remain = new ArrayList<String>();// 租金剩余列
		List<String> corpusOverageBusinessList = new ArrayList<String>(); // 本金余额列表
		FundRentPlanBean frpb=new FundRentPlanBean();
		for(RentPlanBean rpb:rentPlanList){
			planDateList.add(rpb.getPlanDate());
			calPlanDate.add(rpb.getCalPlanDate());
			yearRateList.add(rpb.getYearRate());
			rentList.add(rpb.getRent());
			actualRentList.add(rpb.getActualRent());
			corpusBusinessList.add(rpb.getBusinessCorpus());
			interestBusinessList.add(rpb.getBusinessInterest());
			rent_remain.add(rpb.getAllRemainRent());
			corpusOverageBusinessList.add(rpb.getAllRemainCorpus());
		}
		frpb.setPlanDateList(planDateList);
		frpb.setCalPlanDateList(calPlanDate);
		frpb.setAclRentList(actualRentList);
		frpb.setYearRateList(yearRateList);
		frpb.setRentList(rentList);
		frpb.setCorpusBusinessList(corpusBusinessList);
		frpb.setInterestBusinessList(interestBusinessList);
		frpb.setAllRemainRentList(rent_remain);
		frpb.setCorpusOverageBusinessList(corpusOverageBusinessList);
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, 1);
		//前台获取  资金计划
		List<FundPlanBean> fundPlanList=this.getFundPlanList(modelData);
		FundFundChargeServiceImpl  ffc = new FundFundChargeServiceImpl();
		ffc.addFundFundTemp(fundPlanList, cb,tcb);
		//插入现金流
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		idd.deleteCashDetails(tcb, tcb.getContractCashTb());
		idd.addCashDetails(tcb, cb);
		List cashDetailList = null;
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("docId", modelData.get("docId"));
		String hsql = "";
		if ("proj_process".equals(process)) {
			queryParams.put("projId", cb.getProjId());
			hsql = " FROM   com.reckon.entity.proj.ProjCashDetailTemp where projId = '"+cb.getProjId()+"' AND docId = '"+cb.getDocId()+"' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
		}else if ("quoted_price".equals(process)) {
			queryParams.put("custId", cb.getCustId());
			cashDetailList = tableService.findEntityByProperties(CustCashDetail.class, queryParams);
		}else{
			queryParams.put("contractId", cb.getProjId());
			hsql = " FROM  ContractCashDetailTemp where contractId = '"+cb.getProjId()+"' AND docId = '"+cb.getDocId()+"' order by onhireId ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
		}
		Collections.sort(cashDetailList);
		String irr = IrrTools.getIrr(cashDetailList,process);
		
		//项目粗利 计算
		BigDecimal grossProfit =  this.getGrossProfit(rentPlanList,cb,process);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht.put("irr", irr);
		re_ht.put("cashdetaillist", cashDetailList);
		re_ht.put("grossprofit", grossProfit);
		return re_ht;
	}

	@Override
	public Map<String, Object> calculateRentByIrr(
			Map<String, String> modelData, InputStream file) throws Exception {
		
		ConditionBean cb = ObjectConvertUtils.convertMapToBeanEBFIL(ConditionBean.class, modelData);
		if(null==cb.getProjId()){
			cb.setProjId(cb.getContractId());
		}
		List<Map<String, String>> result =  ExcelReader.readExcelDatas(file,ExcelVersionEnum.valueOf(modelData.get("excelType")));
		List<FundRentPlanIrr> irrPlans = new ArrayList<FundRentPlanIrr>();
		for(int i = 0 ; i <  result.size() ; i++  ){
			Map<String, String>irrPlan  =  result.get(i);
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
		if(new BigDecimal(cb.getCleanLeaseMoney()).compareTo(corpusTotal) != 0 ){
			throw new BusinessException("上传Excel中的本金之和需要等于融资额，请核实后再进行上传！");
		}
		String process = modelData.get("process");
		TabCalBean tcb = new TbBeanTools().getTabInfo(process, cb);
		RentCalcDo rcd = new RentCalcDo();
		Map<String, Object> resultMap = new HashMap<String, Object>();// 构建返回数据
		//List<FundPlanBean> fundPlanList = this.getFundPlanList(modelData);
		Hashtable<String, Object> re_ht = rcd.rentCal(cb, process, "", null, null,null, null,irrPlans);
		//resultMap.putAll(modelData);
		resultMap.putAll(re_ht);
		if ("true".equals(re_ht.get("isSucess"))) {
			bindConditionOtherResult(cb, resultMap, modelData,process, tcb);
			resultMap.put("leaseterm", cb.getLeaseTerm());
			resultMap.put("incomenumber", cb.getIncomeNumber());
		} else {
			throw new LeasingException((String)re_ht.get("message"));
		}
		return resultMap;
	}
	private void bindConditionOtherResult(ConditionBean cb,Map<String, Object> result,Map<String, String> map,String process,TabCalBean tcb ) throws Exception{
		String yearRate = new BigDecimal(cb.getYearRate()).setScale(Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP).toString();
		result.put("yearrate", yearRate);
		result.put("enddate", cb.getEndDate());
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
}
