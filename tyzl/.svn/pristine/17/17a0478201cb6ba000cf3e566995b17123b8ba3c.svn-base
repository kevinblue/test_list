package com.reckon.renttranrate.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.TabCalBean;
import com.reckon.irr.service.IrrDetailsService;
import com.reckon.irr.service.impl.IrrCalServiceImpl;
import com.reckon.irr.service.impl.IrrDetailsServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanFinacCalServiceImpl;
import com.reckon.util.IrrTools;


/**
 * @author MHY QQ:648020894
 */
public class FinaRentPlanServiceImpl {

	static Logger logger = Logger.getLogger(FinaRentPlanServiceImpl.class);

	/**
	 * 
	 *  (  财务调息处理)
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public InterContBean processFinacRentPlan(ConditionBean cb, InterContBean icb, TabCalBean tcb, FundRentPlanBean frpb) throws Exception {

		// 1,默认按期来计算现金流irr,
		getAdjustFinacIrr(cb, tcb, frpb, "1", icb.getStartList());

		// 财务租金计划处理
		RentPlanFinacCalServiceImpl rpfcs = new RentPlanFinacCalServiceImpl();
		// 1,表从第一期开始;2,表租金格式化为几位小数
		rpfcs.rentPlanCalByConditionAndTab(cb, tcb, icb.getStartList(), frpb);

		return icb;
	}
	
	
	/**
	 * 
	 *  (  调息时会计租金计划irr计算)
	 * 
	 * @param cb
	 * @param tcb
	 * @param frpb
	 * @param calType
	 * @param startList
	 * @return
	 * @throws Exception
	 */
	public ConditionBean getAdjustFinacIrr(ConditionBean cb, TabCalBean tcb, FundRentPlanBean frpb, String calType, int startList) throws Exception {

		// 处理会计配置的现金流
		IrrDetailsService ids = new IrrDetailsServiceImpl();
		List<CashDetailsBean> cdbList = new ArrayList<CashDetailsBean>();// 是从正常期开始时
		int size = 0;
		if (startList == 1) {// 查看调息是是从第一期开始时则加添加交易结构的现金流明细
			cdbList = ids.getCashDetailByCfg(tcb, "会计");
			size = cdbList.size();
		} else {
			CashDetailsBean cdb = new CashDetailsBean();
			//cdb.setNetFlow("-" + cb.getCalTotalByFinac());// 由于只是净流量影响irr,时间为上一期的时间值
			cdb.setFundIn("0");
			//cdb.setFundOut(cb.getCalTotalByFinac());
			
//			// 2011-11-13 因为调息合同终止不同. 调息 后面还有租金计划 终止实际上只有一期租金计划了 所以这个日期可能取不到.
//			String plan_temp = "";
//			if (frpb.getPlanDate_list().size() >= Integer.parseInt(startList)) {
//				plan_temp = frpb.getPlanDate_list().get(Integer.parseInt(startList) - 1).toString();
//			} else {
//				plan_temp = frpb.getPlanDate_list().get(frpb.getPlanDate_list().size() - 1).toString();
//			}
//			String plan_date = DateTools.dateAdd("month", -12 / Integer.parseInt(cb.getIncomeNumberYear()), plan_temp);
//			cdb.setPlan_date(plan_date);
			
			
			cdb.setPlanDate(cb.getStartDate());
			cdbList.add(cdb);
		}
		// 租金计划现金流明细的构建
		cdbList = ids.getCashDetailByRentPlan(frpb, cdbList, startList);

		// 以上操作完后，根据日期进行，重组现金流明细
		IrrCalServiceImpl icsi = new IrrCalServiceImpl();
		cdbList = icsi.getNewCashDetailsByCalType(calType, cdbList,cb,size);// 按期得到新的现金流明细,如按其他现金流测算的，可以在此修改，并重写方法

		// 最后一期留购价，期末列值更新
		cdbList = IrrTools.getCashDetailsByEndValue(cdbList, cb.getEquipEndValue(), cb.getNominalPrice(),cb);

		// 测算新的会计 plan_irr
		String finacIrr = IrrTools.getIrrByPreMonth(cdbList, cb);

		// ConditionBean cb 设置 plan_irr属性,暂时用于会计租金计划的测算
		cb.setPlanIrr(new BigDecimal(finacIrr).multiply(new BigDecimal("100")).toString());

		logger.info("调息时会计租金计划测算irr:" + finacIrr);
		return cb;

	}

}
