package com.reckon.service;

import java.util.List;

import com.reckon.bean.IncomeDiscount;
import com.reckon.bean.IncomeDiscountBean;
import com.reckon.bean.TabCalBean;
import com.tenwa.leasing.entity.fund.FinanceCashDetailTemp;

/**
 * 收入折现
 * Tenwa
 */
public interface IncomeDiscountService {
	/**
	 */
	public void  getIncomeDiscount(IncomeDiscount idb,List<FinanceCashDetailTemp>cashDetailList)throws Exception ;
	public void  addIncomeDiscount(List<IncomeDiscountBean> incomeDiscountBeanList,IncomeDiscount id,TabCalBean tcb)throws Exception ;
	public void deleteIncomeDiscount( TabCalBean tcb)throws Exception;
}
