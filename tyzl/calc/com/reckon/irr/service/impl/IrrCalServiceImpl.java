package com.reckon.irr.service.impl;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.ConditionBean;
import com.reckon.util.IrrTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc  ( 有关irr算法处理类)
 */
public class IrrCalServiceImpl {
	private static Logger logger = Logger.getLogger(IrrCalServiceImpl.class);

	
	/**
	 * 
	 * <p>根据现金流明细（租金与配置信息的），现金流计算方法（按月，按天，按期等）。</p>
	 * @author sea
	 * @param calType 测算类型,1:按期 ，2：按月补0，3：按固定间隔补0
	 * @param ccfbList 现金流集合
	 * @param cb 交易结构对象
	 * @param size 第0期现金流集合的长度
	 * @return
	 */
	public List<CashDetailsBean> getNewCashDetailsByCalType(String calType, List<CashDetailsBean> ccfbList,ConditionBean cb,int size) {
		//欧力士默认现金流按月计算，间隔做补0操作
		calType = "3";
		
		// 得到重复时间的现金流
		if ("1".equals(calType)) {// 按期时,默认
			Hashtable<String, String> ht_date = IrrTools.remoRepDate(ccfbList, 7);// 由于他是显示年月字段
			// 调用现金流明细构建
			logger.debug("现金流明细按期构建..");
			return IrrTools.getNetCashByDate(ht_date, ccfbList);

		} else if ("2".equals(calType)) {// 按月等处理(季付半年付等做补0操作)
			// 重新构建ccfbList
			return new IrrCalByMonthServiceImpl().getNewCashDetailsByCalType(ccfbList);
		}
		//固定补0方式
		else if("3".equals(calType)){
			return IrrTools.getNetCashByFixedZero(ccfbList, cb, size);
		}
		return null;
	}
}
