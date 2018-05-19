package com.reckon.rentcalc.service.impl.pub;

import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.FundFundChargeDAOImpl;
import com.reckon.util.DictTools;


/**
 * 
 * @author zhangc
 * @ClassName: FundFundChargeServiceImpl 
 * @date 2014年12月16日 下午7:35:16 
 * @Description: 资金计划的生成
 */
public class FundFundChargeServiceImpl {

	/**
	 * 
	 *  (  //租金测算时先删除，后新增交易结构信息)
	 * 
	 * @param tcb
	 * @param cb
	 * @return
	 * @throws Exception
	 */
	public boolean addFundFundTemp(List<FundPlanBean> fp, ConditionBean cb,TabCalBean tcb) throws Exception {
		// 删除
		FundFundChargeDAOImpl ffd = new FundFundChargeDAOImpl();
		ffd.deleteFundFundCharge( cb,tcb);
		//新增
		ffd.addFundFundCharge(cb,fp,tcb);
		return true;
	}
	/**
	 * 起租前调息特有，分段插入不包括租前息的和只有租前息的资金收付计划
	 * @param fp
	 * @param cb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public boolean addFundFundForAdjustInterestTemp(List<FundPlanBean> fpWithoutInterest,List<FundPlanBean> fpOnlyInterest, ConditionBean cb,TabCalBean tcb) throws Exception {
		// 删除
		FundFundChargeDAOImpl ffd = new FundFundChargeDAOImpl();
		ffd.deleteFundFundCharge( cb,tcb);
		//新增
		ffd.addFundFundCharge(cb,fpWithoutInterest,tcb);
		ffd.addFundFundCharge(cb,fpOnlyInterest,tcb);
		return true;
	}
	public boolean addFundFundTempForFinance(List<FundPlanBean> fp, ConditionBean cb,TabCalBean tcb) throws Exception {
		// 删除
		FundFundChargeDAOImpl ffd = new FundFundChargeDAOImpl();
		ffd.deleteFundFundCharge( cb,tcb);
		//新增
		ffd.addFundFundChargeForFinance(cb,fp,tcb);
		return true;
	}
	
	public boolean addFundFundTemp(List<FundPlanBean> fp, String  docId,String markName,String markValue) throws Exception {
		// 删除
		FundFundChargeDAOImpl ffd = new FundFundChargeDAOImpl();
		ffd.deleteFundFundCharge( docId);
		//新增
		ffd.addFundFundCharge(docId,fp,markName,markValue);
		return true;
	}
}