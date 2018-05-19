package com.reckon.convert.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import com.reckon.bean.ConditionBean;
import com.reckon.convert.ConvertMapToBeanService;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.util.MoneyUtils;
import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.MoneyUtil;
import com.tenwa.leasing.utils.Tools;
@Service(value="convertMapToBeanService")
public class ConvertMapToBeanServiceImpl extends AbstractBaseServiceImpl implements ConvertMapToBeanService {
	
	static Logger logger = Logger.getLogger(ConvertMapToBeanServiceImpl.class);
	
    @Resource(name="baseDao")
	private BaseDao baseDao ;
    
    /**
     * 将ContractConditionTemp参数转换为com.tenwa.leasing.bean.ConditionBean
     * @param condition
     * @return
     * @throws Exception
     */
    public ConditionBean convertContionBean(ContractConditionTemp condition)  throws Exception {
    	ConditionBean conditionBean = new ConditionBean();
    	conditionBean.setId(condition.getId());
    	conditionBean.setDocId(condition.getDocId());
    	conditionBean.setEquipAmt(condition.getEquipAmt()==null?"0":condition.getEquipAmt().toString());
    	///conditionBean.setLeaseAmt(condition.getLeaseAmt()==null?"0":condition.getLeaseAmt().toString());
    	conditionBean.setLeaseAmtDate(condition.getLeaseAmtDate());
    	conditionBean.setFirstPaymentRatio(condition.getFirstPaymentRatio()==null?"0":condition.getFirstPaymentRatio().toString());
    	conditionBean.setFirstPayment(condition.getFirstPayment()==null?"0":condition.getFirstPayment().toString());
    	conditionBean.setCleanLeaseMoney(condition.getCleanLeaseMoney()==null?"0":condition.getCleanLeaseMoney().toString());
    	//conditionBean.setLeaseMoney(condition.getLeaseMoney()==null?"0":condition.getLeaseMoney().toString());
    	conditionBean.setYearRate(condition.getYearRate()==null?"0":condition.getYearRate().toString());
    	conditionBean.setPeriodType(condition.getPeriodType()==null?"0":condition.getPeriodType().getCode());
    	conditionBean.setIncomeNumberYear(condition.getIncomeNumberYear()==null?"0":condition.getIncomeNumberYear().getCode());
    	conditionBean.setIncomeNumber(condition.getIncomeNumber()==null?0:condition.getIncomeNumber());
    	conditionBean.setLeaseTerm(condition.getLeaseTerm()==null?0:condition.getLeaseTerm().intValue());
    	conditionBean.setSettleMethod(condition.getSettleMethod()==null?"0":condition.getSettleMethod().getCode());
    	conditionBean.setStartDate(condition.getStartDate());
    	conditionBean.setNominalPrice(condition.getNominalPrice()==null?"0":condition.getNominalPrice().toString());
    	conditionBean.setPenaRate(condition.getPenaRate()==null?"0":condition.getPenaRate().toString());
    	conditionBean.setRateFloatType(condition.getRateFloatType()==null?"0":condition.getRateFloatType().getCode());
    	conditionBean.setRateFloatAmt(condition.getRateFloatAmt()==null?"0":condition.getRateFloatAmt().toString());
    	conditionBean.setAdjustStyle(condition.getAdjustStyle()==null?"0":condition.getAdjustStyle().getCode());
    	conditionBean.setCautionMoneyRatio(condition.getCautionDeductionRatio()==null?"0":condition.getCautionDeductionRatio().toString());
    	conditionBean.setCautionMoney(condition.getCautionMoney()==null?"0":condition.getCautionMoney().toString());
    	conditionBean.setCautionDeductionRatio(condition.getCautionDeductionRatio()==null?"0":condition.getCautionDeductionRatio().toString());
    	conditionBean.setCautionDeductionMoney(condition.getCautionDeductionMoney()==null?"0":condition.getCautionDeductionMoney().toString());
    	conditionBean.sethandlingChargeMoneyRatio(condition.getHandlingChargeMoneyRatio()==null?"0":condition.getHandlingChargeMoneyRatio().toString());
    	conditionBean.setHandlingChargeMoney(condition.getHandlingChargeMoney()==null?"0":condition.getHandlingChargeMoney().toString());
    	//conditionBean.setInsurer(condition.getInsurer());
    	conditionBean.setManagementMoneyRatio(condition.getManagementMoneyRatio()==null?"0":condition.getManagementMoneyRatio().toString());
    	conditionBean.setManagementMoney(condition.getManagementMoney()==null?"0":condition.getManagementMoney().toString());
    	conditionBean.setIrr(condition.getIrr()==null?"0":condition.getIrr().toString());
    	conditionBean.setPlanIrr(condition.getPlanIrr()==null?"0":condition.getPlanIrr().toString());
    	//conditionBean.setReturnAmt(condition.getReturnAmt()==null?"0":condition.getReturnAmt().toString());
    	conditionBean.setFirstPaymentTotal(condition.getFirstPaymentTotal()==null?"0":condition.getFirstPaymentTotal().toString());
    	//conditionBean.setIncomeDay(condition.getIncomeDay()==null?"0":condition.getIncomeDay().toString());
    	conditionBean.setBeforeInterest(condition.getBeforeInterest()==null?"0":condition.getBeforeInterest().toString());
    	//conditionBean.setRateAdjustmentModulus(condition.getRateAdjustmentModulus()==null?"0":condition.getRateAdjustmentModulus().toString());
    	conditionBean.setGrace(condition.getGrace()==null?0:condition.getGrace().intValue());
    	conditionBean.setOtherIncome(condition.getOtherIncome()==null?"0":condition.getOtherIncome().toString());
    	conditionBean.setOtherExpenditure(condition.getOtherExpenditure()==null?"0":condition.getOtherExpenditure().toString());
    	
    	conditionBean.setCreator(condition.getCreator());
    	conditionBean.setCreateDate(condition.getCreateDate());
    	conditionBean.setModifyDate(condition.getModifyDate());
    	conditionBean.setModificator(condition.getModificator());
    	
    	conditionBean.setFreeDefaInterDay(condition.getFreeDefaInterDay()==null?"0":condition.getFreeDefaInterDay().toString());
    	conditionBean.setEquipEndValue(condition.getEquipEndValue()==null?"0":condition.getEquipEndValue().toString());
    	
    	//conditionBean.setQuotId(condition.getQuotId());
    	//conditionBean.setEndDate(condition.getEndDate());
    	//conditionBean.setActualStartDate(condition.getActualStartDate());
    	//conditionBean.setAccountingStartDate(condition.getAccountingStartDate());
//    	conditionBean.setOnhireId(condition.getOnhireId()==null?"0":condition.getOnhireId().toString());
//    	conditionBean.setGpsMoney(condition.getGpsMoney()==null?"0":condition.getGpsMoney().toString());
//    	conditionBean.setIsMerger(condition.getIsMerger()==null?"1":condition.getIsMerger().toString());
//    	conditionBean.setIsGuaranty(condition.getIsGuaranty());
    	
    	return conditionBean;
    }
    
    
    
    
	@Override
	public ConditionBean convertContionBean(ConditionBean conditionBean,
			Map<String, String> modelData)  throws Exception {
//		System.out.println(modelData.get("framework_condition.equipamt"));
//		modelData.put("equipamt", modelData.get("framework_condition.equipamt"));
		
		//注释掉赋值部分
		this.baseDao.copyAndOverrideExistedValueFromStringMap(modelData, conditionBean,null,"framework_condition");
		//this.baseDao.copyAndSetValueFromStringMap(modelData, conditionBean,null,"proj_condition");
		String incomenumberyear = modelData.get("framework_condition.incomenumberyear");
		
		//String incomenumberyear = modelData.get("framework_condition.incomenumberyear");
		logger.debug("截取前你俺还租次数值:"+incomenumberyear);
		/**
		 * income_1,income_2,income_3,income_6,income_12(直接截取)
		 * income_1	月  付    年还租次数 12
		 * income_3	季  付      年还租次数 4
		 * income_6	半年付   年还租次数 2
		 * income_12 年  付    年还租次数 1
		 * income_2	双月付    年还租次数 6
		 */
		conditionBean.setIncomeNumberYear(incomenumberyear.substring(7,incomenumberyear.length()));
		//起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		conditionBean.setPeriodType("period_type_1".equals(modelData.get("proj_condition.period_type")) ? "1" : "0");
		//新系统中暂时没有字段，暂时不改动算法的情况下先强行赋值
//		conditionBean.setRateSubsidy("0");
//		conditionBean.setDiscountRate("0");
//		conditionBean.setConsultingFeeIn("0");
//		conditionBean.setConsultingFeeOut("0");
//		//conditionBean.setInsure_type(modelData.get("insure_type"));
//		conditionBean.setCautionSfjx("否");
//		conditionBean.setIfLeas("否");
		//conditionBean.setCautionOperWay("抵扣");
		return conditionBean;
	}

	
	 /**
	 * (non-Javadoc)
	 * @see com.business.serviceImpl.AbstractBaseServiceImpl#getBussinessDao(com.business.dao.BaseDao)
	 **/

	@Override
	public BaseDao getBussinessDao() throws Exception {
		return this.baseDao;
	}


//	@Override
//	public ConditionBean convertContionBean(
//			com.tenwa.leasing.bean.ConditionBean cb,
//			Map<String, String> modelData) throws Exception {
//		//通用方式转化bean
//		this.baseDao.copyAndOverrideExistedValueFromStringMap(modelData, cb,null,"framework_condition");
//		//处理其他的数据
//		String is_merger=cb.getIsMerger();
//		BigDecimal handling_charge_money_onHire=new BigDecimal("0");
//		BigDecimal lastCorpus=new BigDecimal("0");
//		//根据是否合并起租设置剩余本金
//		String processType=modelData.get("framework_condition.process");
//		if("onHire_more_process".equals(processType)&&"0".equals(is_merger)){
//			Map<String, Object> propertiesMap=new HashMap<String, Object>();
//			propertiesMap.put("contract_id", cb.getProjId());
//			List<ContractConditionMore> ccml=this.baseDao.findEntityByProperties(ContractConditionMore.class, propertiesMap);                            
//			boolean cal=true;
//			//如果是合并计算的话 只能是同一种租金测算方式
//			if(ccml!=null&&ccml.size()>0){
//				if(!(ccml.get(0).getSettleMethod().equals(cb.getSettleMethod())
//						&&ccml.get(0).getYearRate().equals(cb.getYearRate())
//						&&ccml.get(0).getIncomeNumberYear().equals(cb.getIncomeNumberYear()))){
//					throw new BusinessException("如果要合并起租必须是相同的 租金测算方式,租赁年利率,付租类型!");
//				}
//			}else{
//				throw new BusinessException("此为第一次起租,不能选择合并起租!");
//			}
//			
//			//判断期初期末
//			String add="0";
//			String income_number_year=cb.getIncomeNumberYear();
//			if(income_number_year.equals("income_1")) {add="1";}
//			if(income_number_year.equals("income_2")) {add="2";}
//			if(income_number_year.equals("income_3")) {add="3";}
//			if(income_number_year.equals("income_6")) {add="6";}
//			if(income_number_year.equals("income_12")){add="12";}
//			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
//			add=cb.getPeriodType().equals("period_type_0")?"0":add;
//			String dateNow=Tools.getSystemDate(0);
//			BigDecimal planCorpus=new BigDecimal("0");
//			BigDecimal incomeCorpus=new BigDecimal("0");
//			BigDecimal fundInterest=new BigDecimal("0");
//			BigDecimal finaInterest=new BigDecimal("0");
//			BigDecimal fundInterestOld=new BigDecimal("0");
//			BigDecimal finaInterestOld=new BigDecimal("0");
//			List<ContractFundRentPlan> contractFundRentPlans=this.baseDao.findEntityByProperties(ContractFundRentPlan.class, propertiesMap);
//			List<ContractFinaRentPlan> contractFinaRentPlans=this.baseDao.findEntityByProperties(ContractFinaRentPlan.class, propertiesMap);
//			int i=0;
//			for (ContractFundRentPlan contractFundRentPlan : contractFundRentPlans) {
//				ContractFinaRentPlan contractFinaRentPlan=contractFinaRentPlans.get(i);
//				//计算剩余本金
//				if(Tools.dateDiff("day", dateNow, contractFundRentPlan.getPlanDate())>=0){
//					planCorpus.add(contractFundRentPlan.getCorpusBusiness());
//					for(ContractFundRentInCome cfric:contractFundRentPlan.getContractFundRentInComes()){
//						incomeCorpus.add(cfric.getCorpus());
//						if(BigDecimal.ZERO.compareTo(cfric.getCorpusAdjust())!=0){
//							incomeCorpus.add(cfric.getCorpusAdjust());
//						}
//					}
//				}else{
//					fundInterestOld.add(contractFundRentPlan.getInterest());
//					finaInterestOld.add(contractFinaRentPlan.getInterest());
//				}
//				//计算剩余手续费
//				fundInterest.add(contractFundRentPlan.getInterest());
//				finaInterest.add(contractFinaRentPlan.getInterest());
//				i++;
//			}
//			lastCorpus=planCorpus.subtract(incomeCorpus);//剩余本金
//			handling_charge_money_onHire=finaInterest.subtract(fundInterest).subtract(finaInterestOld.subtract(fundInterestOld));//剩余手续费
//		}
//		//必须设置项
//		cb.setReckonType("reckon");
//		//多次起租时 要判断是否合并起租
//		cb.setCalTotalByCont(String.valueOf(Double.parseDouble(cb.getEquipAmt())-Double.parseDouble(cb.getFirstPayment())+Double.parseDouble(lastCorpus.toString())));// 租金测总本金合同==设备款－首付款
//		//2012-4-18 多次起租是如果是合并计算财务总本金要减去上次起租剩余手续费
//		cb.setCalTotalByFinac(String.valueOf(Double.parseDouble(cb.getCleanLeaseMoney())+Double.parseDouble(cb.getCautionMoney())+Double.parseDouble(lastCorpus.toString())-Double.parseDouble(handling_charge_money_onHire.toString())));// 租金测总本金会计==净融资额 +保证金-多次起租剩余手续费
//		return cb;
//	}
	
	@Override
	public ContractCondition convertContionBean(ContractCondition cb,Map<String, String> modelData) throws Exception {
		//通用方式转化bean
		this.baseDao.copyAndOverrideExistedValueFromStringMap(modelData, cb,null,"framework_condition");
		return cb;
	}
	
	/*
	 * @author sea
	 */
	public ConditionBean convertContionBeanByOriginal(ConditionBean conditionBean,Map<String, String> modelData)  throws Exception {
		
		/**
		 * 测算前置载体对象
		 */
		String id = modelData.get("");//ID
		String docId = Tools.isNullOrEmpty(modelData.get("framework_condition.docid")) ? modelData.get("docid") : modelData.get("framework_condition.docid") ;//docId
		if(Tools.isNullOrEmpty(id)){
			id = docId;
		}
		String contractId = Tools.isNullOrEmpty(modelData.get("framework_condition.contractid")) ?  modelData.get("contractid") : modelData.get("framework_condition.contractid");//合同信息编号
		String projId = Tools.isNullOrEmpty(modelData.get("framework_condition.projid")) ? modelData.get("projid") : modelData.get("framework_condition.projid") ;//项目编号
		String custId = Tools.isNullOrEmpty(modelData.get("framework_condition.custid")) ? modelData.get("custid") : modelData.get("framework_condition.custid") ;//客户编号
		 
		//*********************************************************************************【授信条件开始：】
		String equipAmt = modelData.get("equipamt");//设备款
		String firstPaymentRatio = modelData.get("firstpaymentratio");//首付款比率
		String firstPayment = modelData.get("firstpayment");//首付款
		String equipEndValue = modelData.get("equipendvalue");//期末残值
		String cleanLeaseMoney = modelData.get("cleanleasemoney");//净融资额 
		String firstPaymentTotal = modelData.get("firstpaymenttotal");//期初付款总计
		BigDecimal cleanCreditMoney = new BigDecimal(MoneyUtils.getZeroStr( modelData.get("cleancreditmoney") ));//净授信额
		BigDecimal cleanCreditRatio = new BigDecimal(MoneyUtils.getZeroStr( modelData.get("cleancreditratio") ));//净授信比例
		//*********************************************************************************【授信条件结束！】
		
		
		//*********************************************************************************【租金推算方式开始：】
		String settleMethod = modelData.get("settlemethod");//租金计算方式(测算方式)
		String rentOrRate = modelData.get("rentorrate");//租金推算方法(已知利率算租金、已知租金算利率、任意现金流)
		BigDecimal rentOrRateValue = new BigDecimal(MoneyUtils.getZeroStr( modelData.get("rentorratevalue") ));//测算租金/利率(已知利率算租金、已知租金算利率、任意现金流的值)
		String periodType = modelData.get("periodtype");// 期初（期末）支付 // 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		String incomeNumberYear = modelData.get("incomenumberyear");//租金支付类型
		int incomeNumber = Integer.parseInt(MoneyUtils.getZeroStr( modelData.get("incomenumber") ));//还租次数(年)
		int grace = Integer.parseInt(MoneyUtils.getZeroStr( modelData.get("grace") ));//宽限期数
		int leaseTerm = Integer.parseInt(MoneyUtils.getZeroStr( modelData.get("leaseterm") ));// 租赁期限（月）
		String rateFloatType = modelData.get("ratefloattype");//利率计算方式(利率浮动类型)
		String rateFloatAmt = modelData.get("ratefloatamt");//利率调整值
		BigDecimal baseRate = new BigDecimal(MoneyUtils.getZeroStr( modelData.get("baserate") ));//基准利率
		String yearRate = Tools.isNullOrEmpty(modelData.get("framework_condition.yearrate")) ? modelData.get("yearrate") : modelData.get("framework_condition.yearrate") ;//租赁年利率 
		//*********************************************************************************【租金推算方式结束！】
		
		//*********************************************************************************【测算条件开始：】
		String startDate = Tools.getDBDateStr( modelData.get("startdate") );//起租日
		String leaseAmtDate = Tools.getDBDateStr( modelData.get("leaseamtdate") );//付款日(融资款/设备款支付日期)
		String firstPlanDate = Tools.getDBDateStr( modelData.get("firstplandate") );//第一期租金支付日
		String secondPlanDate = Tools.getDBDateStr( modelData.get("secondplandate") );//第二期租金支付日
		//*********************************************************************************【测算条件结束！】
		
		//*********************************************************************************【资金信息开始：】
		String handlingChargeRatio = modelData.get("handlingchargeratio");//手续费比例
		String handlingChargeMoney = modelData.get("handlingchargemoney");//手续费
		String managementMoneyRatio = modelData.get("managementmoneyratio");//管理费比例
		String managementMoney = modelData.get("managementmoney");//管理费
		String cautionMoneyRatio = modelData.get("cautionmoneyratio");//保证金比例
		
		String cautionMoney = modelData.get("cautionmoney");//保证金
		String cautionDeductionRatio = modelData.get("cautiondeductionratio");//保证金抵扣比例【5.1中不使用该字段】
		String cautionDeductionMoney = modelData.get("cautiondeductionmoney");//保证金抵扣金额
		String cautionMoneyRemain = modelData.get("cautionmoneyremain");//保证金退还金额
		String insureMoneyType = modelData.get("insuremoneytype");//保险计算方式
		
		String insureMoney = modelData.get("insuremoney");// 保险费
		BigDecimal insuranceLessee = new BigDecimal(MoneyUtils.getZeroStr( modelData.get("insurancelessee") ));//保险费(承租人)
		BigDecimal insuranceLessor = new BigDecimal(MoneyUtils.getZeroStr( modelData.get("insurancelessor") ));//保险费(我司)
		String nominalPrice = modelData.get("nominalprice");//名义货价
		String beforeInterest = modelData.get("beforeinterest");//租前息金额
		
		String otherIncome = modelData.get("otherincome");//其它收入
		String otherExpenditure = modelData.get("otherexpenditure");//其它支出
		String returnAmt = modelData.get("returnamt");//厂商返利
		
		//*********************************************************************************【资金信息结束！】
		
		
		//*********************************************************************************【调息/罚息开始：】
		String adjustStyle = modelData.get("adjuststyle");//调息生效节点(调息方式)
		String penaRate = modelData.get("penarate");//罚息利率(万分之)
		String freeDefaInterDay = modelData.get("freedefainterday");//免罚息天数
		//*********************************************************************************【调息/罚息结束！】
		
		
		//*********************************************************************************【收益开始：】
		String irr = modelData.get("irr");//内部收益率(IRR)
		String planIrr = modelData.get("planirr");//财务收益率
		BigDecimal grossProfit = new BigDecimal(MoneyUtils.getZeroStr( modelData.get("grossprofit") ));//项目粗利
		//*********************************************************************************【收益结束！】
		
		
		//*********************************************************************************【隐藏字段开始：】
		int dcNum = Integer.parseInt(MoneyUtils.getZeroStr( modelData.get("dcnum") ));//保证金抵扣期数
		String creditMonths = modelData.get("creditmonths");//实际授信月数
		//*********************************************************************************【隐藏字段结束！】
		
		conditionBean.setId(id);
		conditionBean.setDocId(docId);
		conditionBean.setContractId(contractId);
		conditionBean.setProjId(projId);
		conditionBean.setCustId(custId);
		
		conditionBean.setEquipAmt(equipAmt);
		conditionBean.setFirstPaymentRatio(firstPaymentRatio);
		conditionBean.setFirstPayment(firstPayment);
		conditionBean.setEquipEndValue(equipEndValue);
		conditionBean.setCleanLeaseMoney(cleanLeaseMoney);
		conditionBean.setFirstPaymentTotal(firstPaymentTotal);
		conditionBean.setCleanCreditMoney(cleanCreditMoney);
		conditionBean.setCleanCreditRatio(cleanCreditRatio);
		
		conditionBean.setSettleMethod(settleMethod);
		conditionBean.setRentOrRate(rentOrRate);
		conditionBean.setRentOrRateValue(rentOrRateValue);
		conditionBean.setPeriodType(periodType);
		conditionBean.setIncomeNumberYear(incomeNumberYear);
		conditionBean.setIncomeNumber(incomeNumber);
		conditionBean.setGrace(grace);
		conditionBean.setLeaseTerm(leaseTerm);
		conditionBean.setRateFloatType(rateFloatType);
		conditionBean.setRateFloatAmt(rateFloatAmt);
		conditionBean.setBaseRate(baseRate);
		conditionBean.setYearRate(yearRate);
		
		conditionBean.setStartDate(startDate);
		conditionBean.setLeaseAmtDate(leaseAmtDate);
		conditionBean.setFirstPlanDate(firstPlanDate);
		conditionBean.setSecondPlanDate(secondPlanDate);
		
		conditionBean.sethandlingChargeMoneyRatio(handlingChargeRatio);
		conditionBean.setHandlingChargeMoney(handlingChargeMoney);
		conditionBean.setManagementMoneyRatio(managementMoneyRatio);
		conditionBean.setManagementMoney(managementMoney);
		conditionBean.setCautionMoneyRatio(cautionMoneyRatio);
		//
		conditionBean.setCautionMoney(cautionMoney);
		conditionBean.setCautionDeductionRatio(cautionDeductionRatio);
		conditionBean.setCautionDeductionMoney(cautionDeductionMoney);
		conditionBean.setCautionMoneyRemain(cautionMoneyRemain);
		conditionBean.setInsureMoneyType(insureMoneyType);
		//
		conditionBean.setInsureMoney(insureMoney);
		conditionBean.setInsuranceLessee(insuranceLessee);
		conditionBean.setInsuranceLessor(insuranceLessor);
		conditionBean.setNominalPrice(nominalPrice);
		conditionBean.setBeforeInterest(beforeInterest);
		//
		conditionBean.setOtherIncome(otherIncome);
		conditionBean.setOtherExpenditure(otherExpenditure);
		conditionBean.setReturnAmt(returnAmt);
		
		conditionBean.setAdjustStyle(adjustStyle);
		conditionBean.setPenaRate(penaRate);
		conditionBean.setFreeDefaInterDay(freeDefaInterDay);
		
		conditionBean.setIrr(irr);
		conditionBean.setPlanIrr(planIrr);
		conditionBean.setGrossProfit(grossProfit);
		
		conditionBean.setDcNum(dcNum);
		conditionBean.setCreditMonths(creditMonths);
		
		return conditionBean;
	}
}
