package com.reckon.rentcalc.service.impl.knowingrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.KnowingRentBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.irr.service.IrrService;
import com.reckon.irr.service.impl.IrrServiceImpl;
import com.reckon.rentcalc.service.RentCalcKonwingRentService;
import com.reckon.rentcalc.service.impl.pub.ConditionServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanFinacCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentalServiceImpl;
import com.reckon.util.DictTools;
import com.reckon.util.InsureTypeTools;
import com.reckon.util.IrrTools;
import com.reckon.util.MoneyUtils;
import com.reckon.util.NumberUtils;
import com.reckon.util.TbBeanTools;
import com.reckon.util.Tools;
import com.reckon.util.tools.NumTools;

/**
 * 
 * <p>已知租金法。</p>
 * <p>2014-3-10</p>
 * @author sea
 * @version 4.5
 */
@Service(value = "rentCalcKonwingRentService")
public class KnowingRentImpl implements RentCalcKonwingRentService {
	/**
	 * 日志
	 */
	static Logger log = Logger.getLogger(KnowingRentImpl.class);
	
	/*
	 * @author sea
	 */
	@Override
public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,List<KnowingRentBean> kniwnRentBeans,List<FundPlanBean> fundPlanList,List<String>...RentListStr) throws Exception {
		
		log.info("已知租金法测算租金开始:");
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		// 保存商务条件
		cb = DictTools.getReversDict(cb);
		
		// 租金计划对象创建
		FundRentPlanBean frpb = new FundRentPlanBean();
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		frpb.setYearRate(cb.getYearRate());
		List<String> rentAdjustLists =null;
		if(null != rentAdjustList && rentAdjustList.length > 0 ){
			rentAdjustLists = new ArrayList<String>();
			for(String rentAdjust : rentAdjustList){
				rentAdjustLists.add(rentAdjust);
			}
			frpb.setRentList(RentListStr[0]);
		}
		frpb.setRentAdjustList(rentAdjustLists);// 租金调整值

		// 合同租金计划测算
		RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
		rpcs.getKnowingRentPlan(cb, frpb, kniwnRentBeans);
		
		// 租金计划刷新数据库
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList,cb);
		
		// 结果返回
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		//计算现金流和irr 以及NPV
		re_ht = RentCalculateHelper.createFundPlanCashIrr(cb, tcb, fundPlanList, frpb);
		re_ht.put("isSucess", "true");

		log.info("已知租金法租金测算方法:rentCal ..结束");
		return re_ht;
	}

	@Override
	public Hashtable<String, String> rentCalOnHire(ConditionBean cb,String calType, String userName, String[] rentAdjustList,List<KnowingRentBean> kniwnRentBeans)throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * @author sea
	 */
	@Override
	public String getYearRate(ConditionBean cb,List<KnowingRentBean> kniwnRentBeans) throws Exception {
		log.info("已知租金法测算年利率开始:");
		
		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		//leaseAmt 起租本金/融资额
		//incomeNumberYear 年还租次数
		String periodtype = cb.getPeriodType();//付租方式    起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		String leaseAmt = MoneyUtils.getZeroStr( cb.getCleanLeaseMoney() );//起租本金/融资额
		if(!Tools.isNullOrEmpty(leaseAmt)){
			leaseAmt = NumberUtils.doubleToString( NumberUtils.parseDouble(leaseAmt) * -1 );
		}
		//拼装前台多行设置后的完整租金集合
		// 加载租金列表
		RentalServiceImpl rsi = new RentalServiceImpl();
		List<String> rent_l = rsi.getRentValue(kniwnRentBeans);
		if(rent_l.size() <= 0){
			log.error("已知租金法对应的租金值不存在，无法进行租金测算操作!");
			throw new Exception("已知租金法对应的租金值不存在，无法进行租金测算操作!!!");
		}
		
		int i = 0;
		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		if("period_type_1".equals(periodtype)){//期初模式 + 第一期租金值
			leaseAmt = NumberUtils.doubleToString( NumberUtils.parseDouble(leaseAmt) + NumberUtils.parseDouble(rent_l.get(0)) );
			i = 1;//期初情况下第一期租金已经加入第0期净流量中，下方无需重复封装
		}
		
		//构建现金流 net_flow集合
		List<Map<String, String>> alCash = this.getAlCash(leaseAmt, rent_l, i);
		
		//计算年利率
		String incomeNumberYear  = cb.getIncomeNumberYear(); // 年还租次数
		String yearRate = IrrTools.getRateByFlow(alCash, incomeNumberYear);//租赁年利率
		yearRate = NumTools.formatNumberDoubleScale(Double.parseDouble(yearRate) / 100.0+"",6);
		log.info("已知租金法年利率值："+yearRate);
		log.info("已知租金法测算年利率结束!");
		return yearRate;
	}
	
	/**
	  * <p>构建完整的现金流。</p>
	  * @author sea
	  * @param net_flow 第0期净流出
	  * @param rent_l 完整租金列集合
	  * @param i 循环变量
	  * @return List<Map<String, String>> 例如：map0.put("net_flow", net_flow); list.add(map0);
	 */
	private List<Map<String, String>> getAlCash(String net_flow,List<String> rent_l,int i){
		//构建现金流 net_flow集合
		List<Map<String, String>> alCash = new ArrayList<Map<String, String>>();
		//添加现金流第0期
		Map<String, String> map0 = new HashMap<String, String>();
		map0.put("net_flow", net_flow);
		alCash.add(map0);
		//现金流其余期项
		for (;i < rent_l.size(); i++) {
			Map<String, String> mapTemp = new HashMap<String, String>();
			mapTemp.put("net_flow", rent_l.get(i));
			alCash.add(mapTemp);
		}
		
		return alCash;
	}
}
