package com.reckon.rentcharge.service;

import java.util.Hashtable;

import com.reckon.bean.AdjustBean;


public interface RentChargeService {
	/**
	 * 
	 *  (租金变更方法入口,传入变更bean和类型,返回变更结果)
	 * 
	 * @param adb
	 *            变更bean
	 * @param calType
	 *            项目or合同
	 * @param ht
	 *            Hashtable用来存储变更信息
	 * @return 返回Hashtable
	 */
	public Hashtable<String, Object> rentChargeCal(AdjustBean adb, String calType, Hashtable<String, Object> ht, String[] rentAdjustList) throws Exception;
}
