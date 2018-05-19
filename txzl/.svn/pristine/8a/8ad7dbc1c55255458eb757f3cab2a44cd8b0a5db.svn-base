package com.reckon.rentcalc.service.impl.evencorpus;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.calculation.vo.RentPlanInfo;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.entity.contract.reckon.condition.SpecialRulesBean;
import com.reckon.irr.service.IrrDetailsService;
import com.reckon.irr.service.impl.IrrCalServiceImpl;
import com.reckon.irr.service.impl.IrrDetailsServiceImpl;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.evenrent.EvenRentCalcServiceImpl;
import com.reckon.rentcalc.service.impl.pub.ConditionServiceImpl;
import com.reckon.rentcalc.service.impl.pub.PlanDateServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentalServiceImpl;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.util.DictTools;
import com.reckon.util.IrrTools;
import com.reckon.util.TbBeanTools;


public class EvenCorpusCaleServiceImpl implements RentCalcService {

	static Logger logger = Logger.getLogger(EvenRentCalcServiceImpl.class);
	
	public FundRentPlanBean rentPlanCalByConditionAndTabForSpecial(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb,List<FundPlanBean> funds,SpecialRulesBean specialRulesBean) throws Exception {
		if(startList>specialRulesBean.getEndList()){
			return frpb;
		}
		getPmtRentPlanForSpecial(cb, frpb,specialRulesBean,startList);
		return frpb;
	}

	/**
	 * 
	 *  (  合同，会计现金流处理)
	 * 
	 * @param cb
	 * @param tb
	 * @param frpb
	 * @param calType
	 *            1 是按期类型 2按月
	 * @return
	 * @throws Exception
	 */
	public ConditionBean calCashIrrFinacAndCont(ConditionBean cb, TabCalBean tcb, FundRentPlanBean frpb, String calType) throws Exception {
		// 2011-11-11 现金流的处理隔月不补零
		calType = "1";
		// 处理合同现金流明细
		calContrCashDetails(cb, tcb, frpb, calType);// 删除现金流并创建新的现金流
		// 会计现金流明细
		calFinaCashDetails(cb, tcb, frpb, calType);
		// 2012-4-13 修改多次起租的时候会计现金流重算
		if (tcb.getCalType().equals("onHire_more_process")) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process");
			tcb_cont.setOnHire_id(tcb.getOnHire_id());
			tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
			tcb_cont.setDocId(tcb.getDocId());
			tcb_cont.setIs_merger(tcb.getIs_merger());// 传递是否合并计算
			tcb_cont.setUserId(tcb.getUserId());

			ConditionBean cdb_t = new ConditionBean();
			FundRentPlanBean frpb_t = new FundRentPlanBean();
			String wheresql = tcb_cont.getCondition_tb() + " where " + tcb_cont.getContOrProjCName() + "='" + tcb_cont.getContOrProjCValue() + "' and doc_id='" + tcb_cont.getDocId() + "'";
			cdb_t = new ConditionDAOImpl().getConditionBeanByContract(wheresql, tcb_cont);
			cdb_t = DictTools.getReversDict(cdb_t);
			frpb_t = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb_cont, 1);
			// 处理合同现金流明细
			calContrCashDetails(cdb_t, tcb_cont, frpb_t, calType);
			// 会计现金流明细
			calFinaCashDetails(cdb_t, tcb_cont, frpb_t, calType);
		}
		return cb;
	}

	/**
	 * 
	 *  (  处理会计现金流明细)
	 * 
	 * @param cb
	 * @param tcb
	 * @param frpb
	 * @throws Exception
	 */
	private void calFinaCashDetails(ConditionBean cb, TabCalBean tcb, FundRentPlanBean frpb, String calType) throws Exception {

		// 处理会计配置的现金流
		IrrDetailsService ids = new IrrDetailsServiceImpl();
		List<CashDetailsBean> cdbList = ids.getCashDetailByCfg(tcb, "会计");
		int size = cdbList.size();
		// 租金计划现金流明细的构建
		cdbList = ids.getCashDetailByRentPlan(frpb, cdbList, 1);

		// 以上操作完后，根据日期进行，重组现金流明细
		IrrCalServiceImpl icsi = new IrrCalServiceImpl();
		cdbList = icsi.getNewCashDetailsByCalType(calType, cdbList,cb,size);// 按期得到新的现金流明细,如按其他现金流测算的，可以在此修改，并重写方法

		// 最后一期留购价，期末列值更新 2012-5-7 改两项不参与计算
		// cdbList = IrrTools.getCashDetailsByEndValue(cdbList, cb
		// .getEquip_end_value(), cb.getNominal_price());
		// 先删现金流明细,再添加现金流明细
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		idd.deleteCashDetails(tcb, tcb.getFinacCashTb());// 会计现金流明细表
		idd.addCashDetails(tcb, cdbList, tcb.getFinacCashTb());
		// 测算新的会计 plan_irr
		// 2011-11-11 因为现金流是按期构造所以IRR不能用月计算
		// String finacIrr = IrrTools.getIrrByPreMonth(cdbList, cb);
		String finacIrr = IrrTools.getIrr(cdbList, cb);
		// 更新交易结构表irr
		ConditionDAOImpl cd = new ConditionDAOImpl();
		cd.updateConditionFinaIrr(tcb, finacIrr);

		// ConditionBean cb 设置 plan_irr属性
		cb.setPlanIrr(new BigDecimal(finacIrr).multiply(new BigDecimal("100")).toString());

	}

	/**
	 * 
	 *  (  处理合同现金流明细)
	 * 
	 * @param cb
	 * @param tcb
	 * @param frpb
	 * @throws Exception
	 */
	private void calContrCashDetails(ConditionBean cb, TabCalBean tcb, FundRentPlanBean frpb, String calType) throws Exception {
		// 处理合同配置的现金流
		IrrDetailsService ids = new IrrDetailsServiceImpl();

		List<CashDetailsBean> cdbList = ids.getCashDetailByCfg(tcb, "合同");
		int size = cdbList.size();
		// 租金计划现金流明细的构建
		cdbList = ids.getCashDetailByRentPlan(frpb, cdbList, 1);

		// 以上操作完后，根据日期进行，重组现金流明细
		IrrCalServiceImpl icsi = new IrrCalServiceImpl();
		cdbList = icsi.getNewCashDetailsByCalType(calType, cdbList,cb,size);// 按期得到新的现金流明细,如按其他现金流测算的，可以在此修改，并重写方法

		// 合同的保证金抵扣现金流明细构建
		cdbList = IrrTools.getRentDetailsByDeduct(cdbList, cb.getCautionDeductionMoney());

		// 合同的预收租金抵扣现金流明细构建
		//cdbList = IrrTools.getRentDetailsByDeductBeforeRent(cdbList, cb.getExpectRentDeduction());

		// 最后一期留购价，期末列值更新
		cdbList = IrrTools.getCashDetailsByEndValue(cdbList, cb.getEquipEndValue(), cb.getNominalPrice(),cb);

		// 2011-10-26
		// 处理特殊的保证金抵扣 保证金抵扣金额小于保证金金额 最后要做一笔流出 为保证金金额减去保证金抵扣金额
		cdbList = IrrTools.getRentDetailsByDeductOut(cdbList, cb.getCautionMoney(), cb.getCautionDeductionMoney());

		// 处理特殊的预收租金抵扣 预收租金抵扣金额小于预收租金金额 最后要做一笔流出 为预收租金金额减去预收租金抵扣金额
		//cdbList = IrrTools.getRentDetailsByDeductBeforeRentOut(cdbList, cb.getExpectRent(), cb.getExpectRentDeduction());

		// 先删现金流明细,再添加现金流明细
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
		idd.addCashDetails(tcb, cdbList, tcb.getContractCashTb());

		// 测算新的合同 irr
		// 2011-11-11 因为现金流是按期构造所以IRR不能用月计算
		// String irr = IrrTools.getIrrByPreMonth(cdbList, cb);

		for (CashDetailsBean cdb : cdbList) {
			System.out.println(cdb.getPlanDate() + "=========现金流==========：" + cdb.getNetFlow());
		}

		String irr = IrrTools.getIrr(cdbList, cb);
		System.out.println("更新交易结构表irr:" + irr);

		// 更新交易结构表irr
		ConditionDAOImpl cd = new ConditionDAOImpl();
		cd.updateConditionContIrr(tcb, irr);

		// ConditionBean cb 设置 irr属性
		cb.setIrr(new BigDecimal(irr).multiply(new BigDecimal("100")).toString());
	}

	/**
	 * 
	 *  (  起租时实现方法)
	 * 
	 * @param cb
	 * @param calType
	 * @param userName
	 * @param rentAdjustList
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb, String calType, String userName, String[] rentAdjustList) throws Exception {

		logger.info("进入方法:rentCalOnHire 进行起租时的租金的测算..");
		// 起租时只重新保存交易结构信息，更改租金计划支付日期，更改现金流明细

		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		ConditionServiceImpl csi = new ConditionServiceImpl();
		// 对交易结构信息处理
		csi.addConditionByCal(tcb, cb);
		// 合同租金测算
		// RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();

		// 查询他的租金计划
		RentPlanContrCalDAOImpl rpccdi = new RentPlanContrCalDAOImpl();
		FundRentPlanBean frpb = rpccdi.getRentAndDateByTcb(tcb, 1);
		// FundRentPlanBean frpb = new FundRentPlanBean();
		// 设置他的合同号
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		// 2012-1-11 太原特殊算法 特殊处理年利率 所以保存的时候要调用另一个方法获得原始的年利率值
		frpb.setYearRate(cb.getYearRate());
		frpb.setRentAdjustList(new ArrayList<String>());// 租金调整值

		// 根据交易结构重新算出的租金计划日期
		// 日期类返回租金列表
		PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
		frpb.setPlanDateList(pdsi.getPlanDateList(cb, String.valueOf(frpb.getRentList().size())));
		// 更新租金计划 的时间
		rpccdi.updateRentPlanDate(tcb.getRentPlan_tb(), tcb, 1, frpb.getPlanDateList());
		// 更新会计的租金计划时间
		rpccdi.updateRentPlanDate(tcb.getRentFinaPlan_tb(), tcb, 1, frpb.getPlanDateList());

		// 1,默认按期来计算现金流irr,其它的如按月，按天，在此改参数
		cb = calCashIrrFinacAndCont(cb, tcb, frpb, "1");

		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht.put("isSucess", "true");
		logger.info("结束等额租金起租时测算方法:rentCalOnHire ..");
		return re_ht;
	}

	/**
	 * 
	 *  (  合同租金测算)
	 * 
	 * @param cb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean rentPlanCalByConditionAndTab(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb, List<FundPutPlan> fpps) throws Exception {

		logger.info("合同租金计划生成....方法rentPlanCalByConditionAndTab");
		// 根据表信息进行对表数据的清除操作
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);// 删除租金计划
		
		// 得到正常pmt租金测算后的租金计划
		getPmtRentPlan(cb, frpb,fpps);

		//RentCalculateHelper.getRentPlanHandCharge(frpb,cb);
		// 调用保存的操作
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList,cb);// 保存新的租金计划
		logger.info("合同租金计划生成成功....方法rentPlanCalByConditionAndTab");
		// 返回租金计划实体 bean
		return frpb;
	}

	/**
	 * 
	 *  (  正常pmt租金测算后的租金计划)
	 * 
	 * @param cb
	 * @param frpb
	 */
	private void getPmtRentPlan(ConditionBean cb, FundRentPlanBean frpb, List<FundPutPlan> fpps) {
		logger.info("正常 租金测算开始..(getPmtRentPlan)" + "t");

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// 加载利息列表
			CalculationCondition condition = new CalculationConditionImpl();
			condition.copyConditionBeanValues(cb);
			List<RentPlanInfo> rentPlanList = RentCalculateUtil.calculateForSameCorpus(condition,frpb.getCorpusBusinessList(),cb.getEvenCorpusRateType());
			RentCalculateUtil.calculateFinacesPlan(condition, rentPlanList);// 计算财务租金和财务利息，填充到rentPlan里边

			BigDecimal corpusOverage = condition.getLeaseAmt();
			BigDecimal corpusOverage2 = condition.getLeaseAmt();

			List<String> reateList = new ArrayList<String>();// 利息列表
			List<String> rentList = new ArrayList<String>();// 租金列表
			List<String> planDateList = new ArrayList<String>();// 日期列表
			List<String> interestList = new ArrayList<String>();// 业务利息列表
			List<String> corpusList = new ArrayList<String>();// 业务本金列表
			List<String> corpusOverageList = new ArrayList<String>();// 业务本金剩余列表

			List<String> interestFinacList = new ArrayList<String>();// 财务利息列表
			List<String> corpusFinacList = new ArrayList<String>();// 财务本金列表
			List<String> corpusFinacReminList = new ArrayList<String>();// 财务本金剩余列表
			
			for (RentPlanInfo rpi : rentPlanList) {
				planDateList.add(dateFormat.format(rpi.getEndDate()));// 租金日起列表
				//保证最后一期的租金 = 本金 + 利息
				rentList.add(rpi.getRent().toString());// 租金列表

				interestList.add(rpi.getBusinessInterest().toString());// 业务利息列表
				corpusList.add(rpi.getBusinessCorpus().toString());// 业务本金列表

				interestFinacList.add(rpi.getFinanceInterest().toString());// 财务利息列表
				corpusFinacList.add(rpi.getFinanceCorpus().toString());// 财务本金列表

				corpusOverageList.add(corpusOverage.toString());// 业务本金剩余
				corpusFinacReminList.add(corpusOverage2.toString());// 财务剩余本金列表

				reateList.add(cb.getYearRate());// 年利率列表

				corpusOverage = corpusOverage.subtract(rpi.getBusinessCorpus());// 业务本金剩余值
				corpusOverage2 = corpusOverage2.subtract(rpi.getFinanceCorpus());// 财务本金剩余

				System.out.println(rpi.getId() + "\t" + dateFormat.format(rpi.getEndDate()) + "\t" + rpi.getFinanceCorpus() + "\t" + rpi.getFinanceInterest() + "\t" + rpi.getBusinessCorpus() + "\t" + rpi.getBusinessInterest());
			}
			frpb.setPlanDateList(planDateList);
			frpb.setRentList(rentList);
			frpb.setColumn_1(corpusList);
			frpb.setColumn_2(interestList);
			frpb.setInterestFinacList(interestFinacList);
			frpb.setCorpusFinacList(corpusFinacList);
			frpb.setCorpusOverageBusinessList(corpusOverageList);
			frpb.setCorpusOverageFinacList(corpusFinacReminList);
			frpb.setYearRateList(reateList);

			frpb.setCorpusBusinessList(corpusFinacList);
			frpb.setInterestBusinessList(interestFinacList);

			List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);
			frpb.setAllRemainRentList(rentRemainList);
			PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
			List<String> beforeInterestDateList=pdsi.getBeforeInterestDateList(cb);
			RentCalculateHelper.addBeforeInterest(frpb, cb, fpps,beforeInterestDateList);
			logger.info("正常 租金测算结束..(getPmtRentPlan)");
		} catch (Exception e) {
			logger.info("租金测算错误..(getPmtRentPlan)" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb,
			String calType, String userName, String[] rentAdjustList,
			List<FundPlanBean> fundPlanList) throws Exception {
		logger.info("进入方法:rentCalOnHire 进行起租时的租金的测算..");
		// 起租时只重新保存交易结构信息，更改租金计划支付日期，更改现金流明细

		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		ConditionServiceImpl csi = new ConditionServiceImpl();
		// 对交易结构信息处理
		csi.addConditionByCal(tcb, cb);
		// 合同租金测算
		// RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();

		// 查询他的租金计划
		RentPlanContrCalDAOImpl rpccdi = new RentPlanContrCalDAOImpl();
		FundRentPlanBean frpb = rpccdi.getRentAndDateByTcb(tcb, 1);
		
		// FundRentPlanBean frpb = new FundRentPlanBean();
		// 设置他的合同号
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		// 2012-1-11 太原特殊算法 特殊处理年利率 所以保存的时候要调用另一个方法获得原始的年利率值
		frpb.setYearRate(cb.getYearRate());
		frpb.setRentAdjustList(new ArrayList<String>());// 租金调整值

		// 根据交易结构重新算出的租金计划日期
		// 日期类返回租金列表
		PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
		frpb.setPlanDateList(pdsi.getPlanDateList(cb, String.valueOf(frpb.getRentList().size())));
		// 更新租金计划 的时间
		rpccdi.updateRentPlanDate(tcb.getRentPlan_tb(), tcb, 1, frpb.getPlanDateList());
		// 更新会计的租金计划时间
		rpccdi.updateRentPlanDate(tcb.getRentFinaPlan_tb(), tcb, 1, frpb.getPlanDateList());

		

		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		
		re_ht = RentCalculateHelper.createFundPlanCashIrr(cb, tcb, fundPlanList, frpb);
		
		// 保存商务条件
		csi.addConditionByCal(tcb, cb);
		re_ht.put("isSucess", "true");

		logger.info("结束等本起租时测算方法:rentCalOnHire ..");
		return re_ht;
	}
	private void getPmtRentPlanForSpecial(ConditionBean cb, FundRentPlanBean frpb,SpecialRulesBean specialRulesBean,int startList) throws Exception{
		logger.info("正常 租金测算开始..(getPmtRentPlan)" + "t");
		
		BigDecimal remainCorpus=cb.getRemainCorpus();
		// 加载利息列表 返回剩余本金
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		List<RentPlanInfo> rentPlanList = RentCalculateUtil.calculateForSameCorpusForSpecial(condition,specialRulesBean,cb,startList);
		
		
		
		
		List<String> reateList = new ArrayList<String>();// 利息列表
		List<String> rentList = new ArrayList<String>();// 租金列表
		List<String> interestList = new ArrayList<String>();// 业务利息列表
		List<String> corpusList = new ArrayList<String>();// 业务本金列表
		
		List<String> interestFinacList = new ArrayList<String>();// 财务利息列表
		List<String> corpusFinacList = new ArrayList<String>();// 财务本金列表
		List<String> yearRateList = new ArrayList<String>();// 年利率
		for (int i = specialRulesBean.getStartList(); i <= specialRulesBean.getEndList(); i++) {// 循环租金list
			yearRateList.add(specialRulesBean.getYearRate());
		}
		for (RentPlanInfo rpi : rentPlanList) {
			//保证最后一期的租金 = 本金 + 利息
			rentList.add(rpi.getRent().toString());// 租金列表
			
			interestList.add(rpi.getBusinessInterest().toString());// 业务利息列表
			corpusList.add(rpi.getBusinessCorpus().toString());// 业务本金列表
			
			interestFinacList.add(rpi.getFinanceInterest().toString());// 财务利息列表
			corpusFinacList.add(rpi.getFinanceCorpus().toString());// 财务本金列表
			
			
			reateList.add(cb.getYearRate());// 年利率列表
			
			
		}
		List<String> remainCorpusList=new ArrayList<String>();
		remainCorpusList.addAll(TransRateHelper.getCorpusOvergeList(remainCorpus.toString(), corpusList));
		// 加载本金余额列表
		frpb.getCorpusOverageBusinessList().addAll(remainCorpusList);
		frpb.getRentList().addAll(rentList);
		frpb.getCorpusBusinessList().addAll(corpusList);
		frpb.getCorpusFinacList().addAll(corpusFinacList);
		frpb.getInterestBusinessList().addAll(interestList);
		frpb.getInterestFinacList().addAll(interestFinacList);
		frpb.getCorpusOverageFinacList().addAll(remainCorpusList);
		frpb.getYearRateList().addAll(yearRateList);
		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);
		frpb.getAllRemainRentList().addAll(rentRemainList);
		cb.setRemainLeaseTerm(cb.getRemainLeaseTerm()-(specialRulesBean.getEndList()-specialRulesBean.getStartList()+1)*specialRulesBean.getRegularMonths());
		logger.info("正常 租金测算结束..(getPmtRentPlan)");
	}

	@Override
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,
			List<FundPlanBean> fundPlanList, List<FundPutPlan> fpps, List<SpecialRulesBean> srb, List<String>... planList) throws Exception {
		logger.info("进入方法:rentCal 进行等额本金的测算..");
		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		// 保存商务条件
		ConditionServiceImpl csi = new ConditionServiceImpl();
		csi.addConditionByCal(tcb, cb);
				
		// 创建租金计划对象
		FundRentPlanBean frpb = new FundRentPlanBean();
		if(null != planList  && planList.length > 0 && null != planList[0]){
			frpb.setCorpusBusinessList(planList[0]);
		}
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		frpb.setYearRate(cb.getYearRate());
		frpb.setRentAdjustList(new ArrayList<String>());// 租金调整值

		// 1,表从第一期开始;2,表租金格式化为几位小数,合同租金计划测算
		frpb = rentPlanCalByConditionAndTab(cb, tcb, startList, frpb,fpps);
		
		//计算完保险费后需要重新再保存一次商务条件,
		//【原因：保险费是后台计算的，所以这种情况下上方的保存交易机构操作中的保险费是前台的空值，导致后续使用保险费的地方取不到已经测算的保险费】
		csi = new ConditionServiceImpl();
		csi.addConditionByCal(tcb, cb);
		// 保存商务条件
		csi.addConditionByCal(tcb, cb);
		
		//结果返回
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		
		re_ht = RentCalculateHelper.createFundPlanCashIrr(cb, tcb,fundPlanList, frpb);
		
		re_ht.put("isSucess", "true");
		
		logger.info("结束等额租金测算方法:rentCal ..");
		return re_ht;
	}
}
