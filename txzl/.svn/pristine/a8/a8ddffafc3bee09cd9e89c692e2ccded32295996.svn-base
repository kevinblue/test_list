package com.reckon.rentcalc.service.impl.pub;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.KnowingRentBean;
import com.reckon.calculation.utils.Scale;
import com.reckon.entity.contract.reckon.condition.SpecialRulesBean;
import com.reckon.util.MoneyUtils;
import com.reckon.util.RateTools;
import com.reckon.util.RentTools;
import com.reckon.util.tools.NumTools;
import com.tenwa.leasing.utils.Tools;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 不同的租金测算值，pmt算租金，根据期限之类的，算出租金List并返回租金测算层)
 */
public class RentalServiceImpl {

	static Logger logger = Logger.getLogger(RentalServiceImpl.class);

	/**
	 * 
	 *  (  标准根据交易结构算出pmt租金值)
	 * 
	 * @param cb
	 * @return
	 */
	public String getSignRentByCb(ConditionBean cb) {

		// 1.根据年利率等信息，
		// 中的getPreRate得到测算的租金利率
		String preRate = RateTools.getPreRate(cb.getYearRate(), cb.getIncomeNumberYear(),cb.getRateAdjustType());
		logger.info("PMT参数1:"+preRate+"PMT参数2:"+ cb.getIncomeNumber() + "PMT参数4:"+ cb.getEquipEndValue()+"PMT参数5:"+ cb.getPeriodType());
		// 2.调用PMT方法，得每期租金
		String rent = RentTools.getPMT(preRate, cb.getIncomeNumber() + "", "-" + cb.getCleanLeaseMoney(), cb.getEquipEndValue(), cb.getPeriodType());
		
		//sea 2014-03-10 等额租金-按租金计算年利率 情况下：每期租金置为界面输入的租金值,防止不正确的小数出现
		String settleMethod = Tools.getDBStr( cb.getSettleMethod() );//租金计算方法 ,等额租金：even_rent 
		String rentOrRate = Tools.getDBStr( cb.getRentOrRate() );//计算方式   按租金计算年利率 :rent  按年利率计算租金 :rate
		if("even_rent".equals(settleMethod) && "rent".equals(rentOrRate)){
			//TODO
		} 
		//seaEND
		//算出来的租金，进行精度规整，四舍五入，小数点后保留2位
		return new BigDecimal(rent).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 
	 *  (  其它测算方法可以重构此方法，并返回租金list)
	 * 
	 * @param cb
	 * @return
	 */
	public List<String> getRentListByCond(ConditionBean cb) {

		// 1.根据年利率等信息，
		// 中的getPreRate得到测算的租金利率
		String preRate = RateTools.getPreRate(cb.getYearRate(), cb.getIncomeNumberYear(),cb.getRateAdjustType());
		logger.info("PMT期利率:"+preRate);
		String cleanLeaseMoney = cb.getCleanLeaseMoney();
		if(new BigDecimal(cb.getCleanLeaseMoney()).compareTo(BigDecimal.ZERO) > 0 ){
			cleanLeaseMoney = "-"+cb.getCleanLeaseMoney();
		}
		String rent = "";
		if(cb.getRentOrRate() != null && cb.getRentOrRate().equals("rent")){
			rent = cb.getRentOrRateValue().toString();
		}else{
			rent  = RentTools.getPMT(preRate, cb.getIncomeNumber() + "", cleanLeaseMoney, cb.getEquipEndValue(), cb.getPeriodType());
		}
		logger.info("测算的租金>>>>>>>>>>>>>>>>>>>>>>>"+rent);
		// 3.是否有宽限期判断,宽限期租金列表计算
		List<String> rent_list = new ArrayList<String>();
		//rent_list = getGraceRentList(cb, rent_list, preRate);
		// 4.正常租金列表计算
		rent_list = getNormalRentList(cb, rent_list, rent);
		return rent_list;
	}

	/**
	 * 
	 *  (  均息法下获得租金列表,并调整最后一期的均息法下的本息)
	 * 
	 * @param corpus
	 *            总本金
	 * @param endValues
	 *            剩余本金
	 * @param corpus
	 *            本金集合
	 * @param interest
	 *            利息集合
	 * @return 返回租金集合
	 */
	public List<String> getRentListByCond(String leas_money, String endValues, List<String> corpus, List<String> interest) {
		List<String> rent_list = new ArrayList<String>();
		BigDecimal corpus_total = new BigDecimal("0");
		for (int i = 0; i < corpus.size(); i++) {
			BigDecimal rent = new BigDecimal(corpus.get(i).toString()).add(new BigDecimal(interest.get(i).toString())).setScale(RentTools.getRentAccuracy(), BigDecimal.ROUND_HALF_UP);
			rent_list.add(rent.toString());
			// 因为租金元整过,所以这里要重新元整本金
			BigDecimal corpusTemp = rent.subtract(new BigDecimal(interest.get(i).toString()).setScale(RentTools.getInterestAccuracy(), BigDecimal.ROUND_HALF_UP)).setScale(RentTools.getCorpusAccuracy(), BigDecimal.ROUND_HALF_UP);
			corpus.set(i, corpusTemp.toString());
			if (i < corpus.size() - 1) {
				corpus_total = corpus_total.add(corpusTemp);
			}
		}
		// 调整最后一期本息
		String last_corpus = new BigDecimal(leas_money).subtract(new BigDecimal(endValues)).subtract(corpus_total).setScale(RentTools.getAccuracy(), BigDecimal.ROUND_HALF_UP).toString();
		corpus.set(corpus.size() - 1, last_corpus);
		interest.set(interest.size() - 1, new BigDecimal(rent_list.get(rent_list.size() - 1).toString()).subtract(new BigDecimal(corpus.get(corpus.size() - 1).toString())).setScale(RentTools.getAccuracy(), BigDecimal.ROUND_HALF_UP).toString());
		return rent_list;
	}

	/**
	 * 
	 *  (  正常租金列表计算)
	 * 
	 * @param cb
	 * @param rent_list
	 * @param rent
	 * @return
	 */
	public List<String> getNormalRentList(ConditionBean cb, List<String> rent_list, String rent) {
		for (int i = 0; i < cb.getIncomeNumber(); i++) {
			rent_list.add(rent);
		}
		return rent_list;
	}

	/**
	 * 
	 *  (  宽限期租金计算)
	 * 
	 * @param cb
	 * @return
	 */
	public List<String> getGraceRentList(ConditionBean cb, List<String> rent_list, String preRate) {
		if (cb != null && cb.getGrace() > 0) {
			// 算出宽限期的租金
			String newRent = new BigDecimal(cb.getCleanLeaseMoney()).multiply(new BigDecimal(preRate)).toString();
			for (int i = 0; i < cb.getGrace(); i++) {
				rent_list.add(NumTools.formatNumberDoubleScale(newRent, RentTools.getRentAccuracy()));
			}
		}
		return rent_list;
	}

	/**
	 * 
	 *  (  不规则租金测算List)
	 * 
	 * @param cb
	 * @param rent_list
	 * @return
	 */
	public List<String> getDeviRentList(ConditionBean cb, List<String> rent_list, String index) {
		//
		String rent = getSignRentByCb(cb);
		// 4.正常租金列表计算
		// rent_list = getNormalRentList(cb, rent_list, rent);

		// 重新给后面的元素赋值
		for (int i = Integer.parseInt(index); i < rent_list.size(); i++) {
			rent_list.set(i, rent);
			// index = String.valueOf(Integer.parseInt(index) + 1);
		}

		return rent_list;
	}

	/**
	 * 
	 *  (  不规则租金测算，参照合成) 2011-12-6
	 * 增加年利率计划参数.根据租金计划中的年利率算相关值
	 * 
	 * @param cb
	 * @param rentAdjustList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getDeviRentList(ConditionBean cb, String[] rentAdjustList, List<String> year_rate) {

		// 将不规则数组转化为List列表 String []rent_adjust_arr
		List<String> l_rent_adjust = Arrays.asList(rentAdjustList);
		// 1.根据年利率等信息，
		// 中的getPreRate得到测算的租金利率
		// 2011-12-6 修改为从集合中拿年利率
		// String preRate = RateTools.getPreRate(cb.getYear_rate(),
		// cb.getIncome_number_year());
		// String preRate = RateTools.getPreMonthRate(cb.getYear_rate());
		// 3.是否有宽限期判断,宽限期租金列表计算
		List<String> rent_list = new ArrayList<String>();
		// 宽限期的租金调整
		int grace = cb.getGrace();
		if (grace > 0) {
			rent_list = getDevGraceRentList(cb, l_rent_adjust, grace, year_rate);
		}
		
		// 得到调整期的本金和
		String btotal_charge = getCharCorTotal(l_rent_adjust, year_rate, cb);
		logger.info("不规则调整的本金和.." + btotal_charge.toString());
		
		// 得到不调整期的本金系数和
		String btotal_nocharge = getNoCharCorTotal(l_rent_adjust, year_rate, cb);
		logger.info("除了调整期外，计算期它期租金值..");
		
		if (btotal_nocharge.toString().equals("0")) {
			btotal_nocharge = "1";
		}

		// 得到总的测算本金减去期末残值
		logger.info("总的测算值为.." + (Double.parseDouble(cb.getCleanLeaseMoney()) - Double.parseDouble(cb.getEquipEndValue())));

		// 总的
		String preRate = RateTools.getPreRate(year_rate.get(year_rate.size() - 1), cb.getIncomeNumberYear(),cb.getRateAdjustType());
		String endValue = String.valueOf(Double.parseDouble(cb.getEquipEndValue()) / Math.pow(1 + Double.parseDouble(preRate), Double.valueOf(cb.getIncomeNumber())));
		logger.info("endValue:" + endValue);

		String rent = new BigDecimal(String.valueOf(new BigDecimal(cb.getCleanLeaseMoney()).subtract(new BigDecimal(endValue)))).subtract(new BigDecimal(btotal_charge)).divide(new BigDecimal(btotal_nocharge), 20, BigDecimal.ROUND_HALF_UP).toString();

		logger.info("其它期次租金值为" + rent);
		// 构建新的租金列表
		rent_list = getDevNewRentList(l_rent_adjust, rent, rent_list, cb.getGrace());
		return rent_list;
	}

	/**
	 * 
	 *  (  得到宽限期的调整租金)
	 * 
	 * @param cb
	 * @param l_rent_adjust
	 * @param grace
	 * @param year_rate
	 *            年利率集合
	 * @return 返回宽限期内的调整租金后的租金列表
	 */
	private List<String> getDevGraceRentList(ConditionBean cb, List<String> l_rent_adjust, int grace, List<String> year_rate) {
		List<String> devGrace = new ArrayList<String>();
		// String preRate = RateTools.getPreRate(cb.getYear_rate(),
		// cb.getIncome_number_year());
		String preRate = "";
		// 本金总和
		String corpus = cb.getCleanLeaseMoney();
		// 因为宽限期不多,就每一期单独计算
		for (int i = 0; i < grace; i++) {
			preRate = RateTools.getPreRate(year_rate.get(i), cb.getIncomeNumberYear(),cb.getRateAdjustType());
			if (l_rent_adjust.get(i).toString().equals("")) {// 如果不调整就是本金*利率
				devGrace.add(new BigDecimal(corpus).multiply(new BigDecimal(preRate)).toString());
			} else {
				devGrace.add(l_rent_adjust.get(i).toString());
				// 2011-11-14 宽限期调整 不对后续测试有任何影响
				// corpus=new BigDecimal(corpus).subtract(new
				// BigDecimal(l_rent_adjust.get(i).toString())
				// .subtract(new BigDecimal(corpus).multiply(new
				// BigDecimal(preRate)))).toString();
			}
		}
		// 2011-11-14 宽限期调整 不对后续测试有任何影响
		// cb.setCalTotalByCont(corpus);//宽限期之后的本金总值
		return devGrace;
	}

	/**
	 * 
	 *  (  构建新的租金列表)
	 * 
	 * @param l_rent_adjust
	 * @param rent
	 * @return
	 */
	private List<String> getDevNewRentList(List<String> l_rent_adjust, String rent, List<String> rent_list, int grace) {
		// List rent_list = new ArrayList();
		int igrace = grace;

		for (int i = igrace; i < l_rent_adjust.size(); i++) {

			if ("".equals(l_rent_adjust.get(i).toString())) {// 如这期不调整时
				rent_list.add(rent);
			} else {// 有调整时

				rent_list.add(l_rent_adjust.get(i).toString());
			}
		}
		return rent_list;
	}

	/**
	  * <P>根据前台多行传入租金值构建完整的租金列集合。</P>
	  * @author sea
	  * @param kniwnRentBeans 已知租金法测算传入的分段租金计划值，为空则直接传入null
	  * <pre>中文注解：
      * 第1期到第6期租金值是：1W
	  * 第7期到第10期租金值是：2W
	  * 第11期到第12期租金值是：3W
	  * 底层方法生成完整的租金列集合是：{1W,1W,1W,1W,1W,2W,2W,2W,2W,2W,3W,3W}
	  * kniwnRentBeans伪码参数格式例子如下：
	  * List<KnowingRentBean> bean_l = new ArrayList<KnowingRentBean>();
	  *	KnowingRentBean obj0 = new KnowingRentBean();
	  *	obj0.setStartlist(1);
	  *	obj0.setEndlist(6);
	  *	obj0.setRent("10000.00");
	  *	bean_l.add(obj0);
	  *	KnowingRentBean obj1 = new KnowingRentBean();
	  *	obj1.setStartlist(7);
	  *	obj1.setEndlist(10);
	  *	obj1.setRent("20000.00");
	  *	bean_l.add(obj1);
	  *  ...
	  * @return List<String> 返回完整的租金列集合.例如:list {1W,1W,1W,1W,1W,2W,2W,2W,2W,2W,3W,3W}
	 */
	public List<String> getRentValue( List<KnowingRentBean> kniwnRentBeans) {
		List<String> list = new ArrayList<String>();
		for (KnowingRentBean obj : kniwnRentBeans) {
			Integer startlist = obj.getStartlist();//开始期项
			Integer endlist = obj.getEndlist();//截止期项
			String rent = MoneyUtils.getZeroStr( obj.getRent().replaceAll(",",""));//对应租金值  
			for (int j = startlist; j <= endlist; j++) {
				list.add( rent );
			}
		}
		return list;
	}
		
	/**
	 * 
	 *  (  得到变更后的本金和)
	 * 
	 * @param l_rent_adjust
	 * @param preRate
	 * @return
	 */
	private String getCharCorTotal(List<String> l_rent_adjust, List<String> year_rate, ConditionBean cb) {

		String pv_str = "0";// 用于保存变更的本金和
		// 得到变更的租金列表下标值,并以数组返回
		String[] i_array = RentTools.getAdjustIds(l_rent_adjust, cb.getGrace());
		String preRate = "";
		int num = 0;
		if (cb.getPeriodType().toString().equals("0")) {// 如果期末要加1
			num = 1;
		}
		for (int i = 0; i < i_array.length; i++) {
			preRate = RateTools.getPreRate(year_rate.get(i), cb.getIncomeNumberYear(),cb.getRateAdjustType());
			String iValue = new String(i_array[i]);
			// if ("".equals(iValue)){
			// iValue="0";
			// }

			// 2011-04-20宽限期不算进期中，所以
			iValue = String.valueOf(Integer.parseInt(iValue) - cb.getGrace());

			pv_str = String.valueOf(Double.parseDouble(pv_str) + Double.parseDouble(l_rent_adjust.get(Integer.parseInt(i_array[i])).toString()) / Math.pow(1 + Double.parseDouble(preRate), Double.valueOf(Integer.parseInt(iValue) + num)));
			// logger.info("=double算出=" +pv_str);
			// logger.info(btotal_charge.doubleValue());

		}

		return pv_str;
	}

	/**
	 * 
	 *  (  得到不变更的本金系数和)
	 * 
	 * @param l_rent_adjust
	 * @param preRate
	 * @return
	 */
	private String getNoCharCorTotal(List<String> l_rent_adjust, List<String> year_rate, ConditionBean cb) {

		// 得到不调整期的本金系数和
		String dTotal = "0";// 用于保存不变更的本金系数和
		// 得到不变更的租金列表下标值,并以数组返回
		String[] i_no_array = RentTools.getNoAdjustIds(l_rent_adjust, cb.getGrace());
		String preRate = "";
		int num = 0;
		if (cb.getPeriodType().toString().equals("0")) {// 如果期末要加1
			num = 1;
		}
		for (int i = 0; i < i_no_array.length; i++) {
			preRate = RateTools.getPreRate(year_rate.get(i), cb.getIncomeNumberYear(),cb.getRateAdjustType());
			logger.debug("利率" + year_rate.get(i) + "计算利率" + preRate);
			String inoValue = new String(i_no_array[i]);
			// if ("".equals(inoValue)){
			// inoValue="0";
			// }
			// 2011-04-20宽限期不算进期中，所以
			inoValue = String.valueOf(Integer.parseInt(inoValue) - cb.getGrace());

			dTotal = String.valueOf(Double.parseDouble(dTotal) + Double.parseDouble("1") / Math.pow(1 + Double.parseDouble(preRate), Double.valueOf(Integer.parseInt(inoValue) + num)));
		}

		logger.info("不规则不调整的本金系数和.." + dTotal);

		return dTotal;
	}

	/**
	 * scl  (根据FundRentPlanBean和租金调整值测算每期租金)
	 * 
	 * @param frpb
	 *            FundRentPlanBean
	 * @param rentAdjustList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getDeviRentList(ConditionBean cdb, List<String> year_rate_list, String[] rentAdjustList) {

		// 将不规则数组转化为List列表 String []rent_adjust_arr
		List<String> l_rent_adjust = Arrays.asList(rentAdjustList);

		// 得到调整期的本金和
		String btotal_charge = getCharCorTotal(l_rent_adjust, year_rate_list, cdb.getGrace());
		logger.info("不规则调整的本金和.." + btotal_charge.toString());

		// 得到不调整期的本金系数和
		String btotal_nocharge = getNoCharCorTotal(l_rent_adjust, year_rate_list, cdb.getGrace());
		logger.info("除了调整期外，计算期它期租金值..");
		// 得到总的测算本金
		logger.info("总的测算值为.." + cdb.getCleanLeaseMoney());

		if (btotal_nocharge.toString().equals("0")) {
			btotal_nocharge = "1";
		}
		String rent = new BigDecimal(cdb.getCleanLeaseMoney()).subtract(new BigDecimal(btotal_charge)).divide(new BigDecimal(btotal_nocharge), 20, BigDecimal.ROUND_HALF_UP).toString();

		logger.info("其它期次租金值为" + rent);

		// 构建新的租金列表
		List<String> rent_list = new ArrayList<String>();
		rent_list = getDevNewRentList(l_rent_adjust, rent, rent_list, cdb.getGrace());

		return rent_list;
	}

	/**
	 * scl  (  得到变更后的本金和)
	 * 
	 * @param l_rent_adjust
	 *            调整值
	 * @param year_rate_list
	 *            年利率
	 * @return
	 */
	private String getCharCorTotal(List<String> l_rent_adjust, List<String> year_rate_list, int grace) {

		String pv_str = "0";// 用于保存变更的本金和
		// 得到变更的租金列表下标值,并以数组返回
		String[] i_array = RentTools.getAdjustIds(l_rent_adjust, grace);

		for (int i = 0; i < i_array.length; i++) {

			String iValue = new String(i_array[i]);
			// 2011-04-20宽限期不算进期中，所以
			iValue = String.valueOf(Integer.parseInt(iValue) - grace);

			pv_str = String.valueOf(Double.parseDouble(pv_str) + Double.parseDouble(l_rent_adjust.get(Integer.parseInt(i_array[i])).toString()) / Math.pow(1 + Double.parseDouble(year_rate_list.get(i).toString()), Double.valueOf(Integer.parseInt(iValue) + 1)));
		}

		logger.debug("不规则调整的本金和.." + pv_str);
		return pv_str;
	}

	/**
	 * scl   得到不变更的本金系数和)
	 * 
	 * @param l_rent_adjust
	 * @param year_rate_list
	 * @return
	 */
	private String getNoCharCorTotal(List<String> l_rent_adjust, List<String> year_rate_list, int grace) {

		// 得到不调整期的本金系数和
		String dTotal = "0";// 用于保存不变更的本金系数和
		// 得到不变更的租金列表下标值,并以数组返回
		String[] i_no_array = RentTools.getNoAdjustIds(l_rent_adjust, grace);
		for (int i = 0; i < i_no_array.length; i++) {

			String inoValue = new String(i_no_array[i]);
			// 2011-04-20宽限期不算进期中，所以
			inoValue = String.valueOf(Integer.parseInt(inoValue) - grace);

			dTotal = String.valueOf(Double.parseDouble(dTotal) + Double.parseDouble("1") / Math.pow(1 + Double.parseDouble(year_rate_list.get(i).toString()), Double.valueOf(Integer.parseInt(inoValue) + 1)));

		}

		logger.info("不规则不调整的本金系数和.." + dTotal);

		return dTotal;
	}

	public List<String> getRentRemainList(List<String> rentList) {
		List<String> result = new ArrayList<String>();
		String first = "0";
		for (int i = rentList.size() - 1; i >= 0; i--) {
			result.add(0, first);
			first = new BigDecimal(first).add(new BigDecimal(rentList.get(i))).toString();
		}
		return result;
	}
	public List<String> getRentListByCondForSpecial(ConditionBean cb,SpecialRulesBean specialRulesBean) {
		
		// 1.根据年利率等信息，
		// 中的getPreRate得到测算的租金利率
		System.out.println("计算前=="+cb.getYearRate());
		//期利率
		Boolean rateReCal = false;
		if(cb.getRateType() != null && cb.getRateType().equals("365")){
			rateReCal = true;
		}
		String preRate = new BigDecimal(specialRulesBean.getYearRate()).divide(new BigDecimal(100),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(cb.getRateType())).multiply(new BigDecimal(specialRulesBean.getRegularMonths())).divide(new BigDecimal(12),Scale.EXCEL_RATE,BigDecimal.ROUND_HALF_UP).toString();
		logger.debug("PMT期利率:"+preRate);
		//如果等额租金紧跟着宽限期之后 则页面上的还租时点生效  否则都当做期末处理
		String rent = getSignRentByCbForSpecial(cb,rateReCal,preRate,specialRulesBean.getRegularMonths().toString());
		List<String> rent_list = new ArrayList<String>();
		
		for(int i=specialRulesBean.getStartList();i<=specialRulesBean.getEndList();i++){
			rent_list.add(rent);
		}
		return rent_list;
	}
	public String getSignRentByCbForSpecial(ConditionBean cb ,  Boolean rateReCal ,String preRate,String regularMonths) {
		
		// 2.调用PMT方法，得每期租金
		String rent = RentTools.getPMTForSpecial(preRate, new BigDecimal(cb.getRemainLeaseTerm()).divide(new BigDecimal(regularMonths),Scale.GENERAL_RATE,BigDecimal.ROUND_HALF_UP)  + "", "-" + cb.getRemainCorpus(), cb.getEquipEndValue(), cb.getPeriodType());
		//seaEND
		//算出来的租金，进行精度规整，四舍五入，小数点后保留2位
		return new BigDecimal(rent).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP).toString();
	}
}
