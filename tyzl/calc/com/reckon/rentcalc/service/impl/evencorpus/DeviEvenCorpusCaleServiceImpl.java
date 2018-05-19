package com.reckon.rentcalc.service.impl.evencorpus;

import java.math.BigDecimal;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.FundRentPlanIrr;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.RentAdjustmentUtil;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.calculation.vo.RentPlanInfo;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.irr.service.IrrDetailsService;
import com.reckon.irr.service.impl.IrrCalServiceImpl;
import com.reckon.irr.service.impl.IrrDetailsServiceImpl;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.evenrent.EvenRentCalcServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanFinacCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentalServiceImpl;
import com.reckon.util.DictTools;
import com.reckon.util.IrrTools;
import com.reckon.util.TbBeanTools;

import edu.emory.mathcs.backport.java.util.Arrays;

public class DeviEvenCorpusCaleServiceImpl implements RentCalcService {

	static Logger logger = Logger.getLogger(DeviEvenCorpusCaleServiceImpl.class);

	/**
	 * 
	 *  (  不规则租金测算，最主要的是构建合同租金计划实体bean其它的可如等额租金处理)
	 * 
	 * @param cb
	 * @param calType
	 * @param userName
	 * @param rentAdjustList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,Boolean isCharge) throws Exception {
		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		// 对交易结构信息处理
		cb = DictTools.getReversDict(cb);

		FundRentPlanBean frpb = new FundRentPlanBean();
		// 因为在不规则中算回去年利率时是以全部租金计划为基础的所以从1开始
		frpb = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb, 1);// 初始化租金计划bean并获得年利率集合
		// 等额租金下要清除该值
		frpb.setColumn_1(null);
		frpb.setColumn_2(null);
		// 设置他的合同号
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		// 2012-1-11 太原特殊算法 特殊处理年利率 所以保存的时候要调用另一个方法获得原始的年利率值
		frpb.setYearRate(cb.getYearRate());

		frpb.setRentAdjustList(Arrays.asList(rentAdjustList));// 租金调整列值

		// 1,表从第一期开始;2,表租金格式化为几位小数,合同租金计划测算

		// 不规则修改处//////////////////////////
		frpb = rentPlanCalByDevi(cb, tcb, startList, frpb, rentAdjustList);
		// ////////////////

		// 1,默认按期来计算现金流irr,其它的如按月，按天，在此改参数
		// 2011-06-14修改为安月计算
		// 2011-11-14 改为按期
		cb = calCashIrrFinacAndCont(cb, tcb, frpb, "1");

		//
		// 财务租金计划处理
		RentPlanFinacCalServiceImpl rpfcs = new RentPlanFinacCalServiceImpl();
		// 1,表从第一期开始;2,表租金格式化为几位小数
		rpfcs.rentPlanCalByConditionAndTab(cb, tcb, startList, frpb);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht.put("isSucess", "true");
		return re_ht;

	}

	/**
	 * 
	 *  (  合同不规则租金测算)
	 * 
	 * @param cb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean rentPlanCalByDevi(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb, String[] rentAdjustList) throws Exception {
		// 根据表信息进行对表数据的清除操作
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);

		getPmtRentPlan(cb, frpb, rentAdjustList);

		// 调用保存的操作
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList);

		// 返回租金计划实体 bean
		return frpb;

	}

	private void getPmtRentPlan(ConditionBean cb, FundRentPlanBean frpb, String[] adjust) {
		logger.info("正常 租金测算开始..(getPmtRentPlan)" + "t");

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			CalculationCondition condition = new CalculationConditionImpl();
			condition.copyConditionBeanValues(cb);
			List<RentPlanInfo> rentPlanList = RentCalculateUtil.calculateForSameCorpus(condition,null);
			for (int i = 0; i < adjust.length; i++) {
				if (adjust[i] != null && adjust[i].length() > 0) {
					rentPlanList.get(i).setCorpusAdjust(new BigDecimal(adjust[i]));
				}
			}
			RentAdjustmentUtil.updateForSameCorpus(condition, rentPlanList);
			RentCalculateUtil.calculateFinacesPlan(condition, rentPlanList);

			List<String> reteList = new ArrayList<String>();// 利息列表
			List<String> rentList = new ArrayList<String>();// 租金列表
			List<String> planDateList = new ArrayList<String>();// 日期列表

			List<String> businessInterestList = new ArrayList<String>();// 业务利息列表
			List<String> businessCorpusList = new ArrayList<String>();// 业务本金列表
			List<String> businessRemainList = new ArrayList<String>();// 业务本金剩余列表

			List<String> financeInterestList = new ArrayList<String>();// 财务利息列表
			List<String> financeCorpusList = new ArrayList<String>();// 财务本金列表
			List<String> financeRemainList = new ArrayList<String>();// 财务本金剩余列表

			for (RentPlanInfo rpi : rentPlanList) {
				reteList.add(cb.getYearRate());// 年利率列表
				planDateList.add(dateFormat.format(rpi.getEndDate()));// 租金日起列表
				rentList.add(rpi.getRent().toString());// 租金列表

				businessInterestList.add(rpi.getBusinessInterest().toString());// 业务利息列表
				businessCorpusList.add(rpi.getBusinessCorpus().toString());// 业务本金列表
				businessRemainList.add(rpi.getBusinessRemain().toString());// 业务本金剩余

				financeInterestList.add(rpi.getFinanceInterest().toString());// 财务利息列表
				financeCorpusList.add(rpi.getFinanceCorpus().toString());// 财务本金列表
				financeRemainList.add(rpi.getFinanceRemain().toString());// 财务剩余本金列表

				System.out.println(rpi.getId() + "\t" + dateFormat.format(rpi.getEndDate()) + "\t" + rpi.getFinanceCorpus() + "\t" + rpi.getFinanceInterest() + "\t" + rpi.getBusinessCorpus() + "\t" + rpi.getBusinessInterest());
			}
			frpb.setYearRateList(reteList);
			frpb.setPlanDateList(planDateList);
			frpb.setRentList(rentList);

			frpb.setColumn_1(businessCorpusList);
			frpb.setColumn_2(businessInterestList);

			frpb.setCorpusFinacList(financeCorpusList);
			frpb.setInterestFinacList(financeInterestList);
			frpb.setCorpusOverageFinacList(financeRemainList);

			frpb.setCorpusBusinessList(financeCorpusList);
			frpb.setInterestBusinessList(financeInterestList);
			frpb.setCorpusOverageBusinessList(businessRemainList);

			List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);
			frpb.setAllRemainRentList(rentRemainList);
			
			logger.info("正常 租金测算结束..(getPmtRentPlan)");
		} catch (Exception e) {
			logger.info("租金测算错误..(getPmtRentPlan)" + e.getMessage());
			e.printStackTrace();
		}
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
		calContrCashDetails(cb, tcb, frpb, calType);
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
			String wheresql = tcb_cont.getCondition_tb() + " where " + tcb_cont.getContOrProjCName() + "=? and doc_id=?";
			List paramValue=new ArrayList();
			List paramType=new ArrayList();
			paramValue.add(tcb_cont.getContOrProjCValue());
			paramType.add(Types.VARCHAR);
			paramValue.add(tcb_cont.getDocId());
			paramType.add(Types.VARCHAR);
            Map whereMap=new HashMap();
			whereMap.put("where",wheresql);
			whereMap.put("value", paramValue);
			whereMap.put("type", paramType);
			
			cdb_t = new ConditionDAOImpl().getConditionBeanByContract(whereMap, tcb_cont);
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
		//  Auto-generated method stub
		// 由于他的处理与等额租金的是一样的，故调用同一个方法
		return new EvenRentCalcServiceImpl().rentCalOnHire(cb, calType, userName, rentAdjustList,null);
	}

	@Override
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType,
			String userName, String[] rentAdjustList, int startList,
			List<FundPlanBean> fundPlanList, List<SpecialRulesBean> srb,
			List<FundRentPlanIrr> irrPlans, List<String>... planList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb,
			String calType, String userName, String[] rentAdjustList,
			List<FundPlanBean> fundPlanList, List<SpecialRulesBean> srb,
			List<FundRentPlanIrr> irrPlans) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
