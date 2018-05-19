package com.reckon.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc  ( 本金测算)
 */
public class CorpusTools {

	private static Logger	logger	= Logger.getLogger(CorpusTools.class);


	/**
	 * pmt 本金算法
	 * 
	 * @Title: getCorpusList
	 * @Description:
	 * @param
	 * @param rentList租金List
	 * @param
	 * @param inteList利息List
	 * @param
	 * @return
	 * @return List本金List
	 * @throws
	 */
	public static List<String> getCorpusList(List<String> rentList, List<String> inteList) {
		List<String> corpus_list = new ArrayList<String>();
		for (int i = 0; i < rentList.size(); i++) {
			corpus_list.add(new BigDecimal(rentList.get(i).toString())
					.subtract(new BigDecimal(inteList.get(i).toString())).setScale(RentTools.getCorpusAccuracy(), BigDecimal.ROUND_HALF_UP)
					.toString());
		}
		return corpus_list;
	}


	/**
	 * 等额本金,得到本金list
	 * 
	 * @Title: eqCorpusList
	 * @Description:
	 * @param
	 * @param leaseMoney测算本金值
	 * @param
	 * @param incomeNumber总的测算期数
	 * @param
	 * @return
	 * @return List
	 * @throws
	 */
	public List<String> eqCorpusList(String leaseMoney, String incomeNumber) {

		String corpu = "";

		// 得到每期的本金,总的本金/期限
		corpu = new BigDecimal(leaseMoney).divide(new BigDecimal(incomeNumber),
				20, BigDecimal.ROUND_HALF_UP).toString();

		// corpu = NumTools.formatNumberDoubleScale(corpu, 2);

		logger.info("每一期的本金:" + corpu);
		String total = "0";// 用于积累前面的本金和

		// 用于保存本金
		List<String> l_corpus = new ArrayList<String>();
		for (int i = 0; i < Integer.parseInt(incomeNumber); i++) {
			// 最后一期是要作特别的处理

			if (i == Integer.parseInt(incomeNumber) - 1) {

				double d = new BigDecimal(leaseMoney).subtract(
						new BigDecimal(total)).doubleValue();
				l_corpus.add(String.valueOf(d));

			} else {
				l_corpus.add(corpu);
				total = new BigDecimal(total).add(new BigDecimal(corpu))
						.toString();
				// total = NumTools.formatNumberDoubleScale(total, 2);
			}

		}
		return l_corpus;

	}

}
