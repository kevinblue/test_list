package com.reckon.rentcalc.service.impl.pub;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 本金余额列表算法实现，其它算法可在此类中重构方法)
 */
public class CorpusOvergeServiceImpl {

	/**
	 * 
	 *  (  不规则时 本金余额计算方法)
	 * 
	 * @param calTotalCorpus
	 * @param corpusList
	 * @return
	 */
	public List<String> getCorpusOvergeList(String calTotalCorpus, List<String> corpusList, List<String> corpusOver_list, String index) {

		String total = "0";// 累积每一期的本金

		for (int i = Integer.parseInt(index); i < corpusList.size(); i++) {

			total = new BigDecimal(total).add(new BigDecimal(corpusList.get(i).toString())).toString();

			corpusOver_list.set(i, new BigDecimal(calTotalCorpus).subtract(new BigDecimal(total)).toString());

		}
		return corpusOver_list;
	}
}
