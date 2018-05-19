package com.reckon.rentcalc.service.impl.evenrent;

import java.util.Hashtable;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.entity.contract.reckon.condition.SpecialRulesBean;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.util.DictTools;
import com.reckon.util.TbBeanTools;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-7
 * @desc  ( 不规则租金测算)
 */
public class DeviRentCalServiceImpl implements RentCalcService {

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
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,List<FundPlanBean> fundPlanList) throws Exception {
		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		// 对交易结构信息处理
		cb = DictTools.getReversDict(cb);
		// 合同租金测算
		RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();

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
		frpb = rpcs.rentPlanCalByDevi(cb, tcb, startList, frpb, rentAdjustList);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht = RentCalculateHelper.createFundPlanCashIrr(cb, tcb, fundPlanList, frpb);
		re_ht.put("isSucess", "true");
		return re_ht;
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
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb, String calType, String userName, String[] rentAdjustList,List<FundPlanBean> fundPlanList) throws Exception {
		//  Auto-generated method stub
		// 由于他的处理与等额租金的是一样的，故调用同一个方法
		return new EvenRentCalcServiceImpl().rentCalOnHire(cb, calType, userName, rentAdjustList,fundPlanList);
	}

	@Override
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType,
			String userName, String[] rentAdjustList, int startList,
			List<FundPlanBean> fundPlanList, List<FundPutPlan> fpps,
			List<SpecialRulesBean> srb, List<String>... planList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
