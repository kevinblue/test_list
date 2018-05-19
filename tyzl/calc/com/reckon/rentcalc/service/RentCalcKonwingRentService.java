package com.reckon.rentcalc.service;

import java.util.Hashtable;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.KnowingRentBean;

/**
 * <p>已知租金法租金测算模型入口。</p>
 * <p>2014-3-10</p>
 * @author sea
 * @version 4.5
 */
public interface RentCalcKonwingRentService {
 
	/**
	  * <p>已知租金法测算租金。</p>
	  * @author sea
	  * @param cb
	  * @param calType
	  * <pre>流程类型 如果在后续测测试中没用到可以删除  quoted_price  
	  * 	如果是多次起租而且是合并计算,则要把是否合并计算值设置为是 onHire_more_process</pre>
	  * @param userName
	  * @param rentAdjustList
	  * @param startList
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
	  * @return
	  * @throws Exception
	 */
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,List<KnowingRentBean> kniwnRentBeans,List<FundPlanBean> fundPlanList,List<String>...RentListStr) throws Exception;

	/**
	 * 
	 *  (  起租时要实现的方法)
	 * 
	 * @param cb
	 * @param calType
	 * <pre>流程类型 如果在后续测测试中没用到可以删除  quoted_price  
	 * 	如果是多次起租而且是合并计算,则要把是否合并计算值设置为是 onHire_more_process</pre>
	 * @param userName
	 * @param obj
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
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, String> rentCalOnHire(ConditionBean cb, String calType, String userName, String[] rentAdjustList,List<KnowingRentBean> kniwnRentBeans) throws Exception;

	
	/**
	  * <p>已知租金法根据分段租金计划及商务条件测算构建现金流测算年利率返回前台显示。</p>
	  * @author sea
	  * @param cb 合
	  * <pre>同交易结构,需要封装以下三个参数
	  * 参数1：periodType 付租方式  期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
	  *	参数2：leaseAmt 起租本金/融资额
	  *	参数3：incomeNumberYear 年还租次数</pre>
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
	  * @return String 返回测算后的年利率，保留6位小数
	  * @throws Exception
	 */
	public String getYearRate(ConditionBean cb,List<KnowingRentBean> kniwnRentBeans) throws Exception;
	
}
