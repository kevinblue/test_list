package com.reckon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.tenwa.business.model.RentInfoBox;


/**
 * @author Toybaby
 *
 * 2012-12-28下午05:53:29
 * email toybaby@mail2.tenwa.com.cn
 * function:
 *
 */
public class EqualPaymentUtil {

	/**
	 * 测算
	 * 
	 * @param conditionBean
	 * 
	 * @return
	 */
//	public static List<RentPlanBean> calc(ConditionBean conditionBean) {
//		return null;
//	}

	/**
	 * 老系统的测算方法
	 * 
	 * @param conditionBean
	 */
	@SuppressWarnings("unchecked")
	public static RentInfoBox getRentInfoBox(ConditionBean conditionBean) {
		RentInfoBox rentInfoBox = new RentInfoBox();
		String contract_id = conditionBean.getContractId();
		//System.out.println("UUUUU");
		/**
		 * =======================================================
		 * 
		 * <pre>
		 * 先测算出相关的List数据
		 * </pre>
		 * 
		 * =======================================================
		 */
		List l_RentDetails = new ArrayList();
		List l_plan_date = new ArrayList();
		//Irr计算条件
		String firstMoney= String.valueOf("-"+conditionBean.getCleanLeaseMoney());//现金流第一期流量
		String lease_interval = conditionBean.getIncomeNumberYear();//租金间隔
		String caution_money=conditionBean.getCautionMoney();//保证金
		String Other_expenditure = conditionBean.getOtherExpenditure();//其它支出
//		String Other_expenditure = conditionBean.getNominalprice();//残值收入
		
		String assets_value = conditionBean.getEquipEndValue();//资产余值
		String type = conditionBean.getPeriodType();
		//String caution_oper_way = conditionBean.getCautionOperway();//保证金返回方式
		String caution_sfjx = "";//conditionBean.getCautionSfjx();//保证金是否计息
		// 租金测算程序
		List l_rent = new ArrayList();
		List l_corpus = new ArrayList();
		List l_interest = new ArrayList();
		List l_corpus_overage = new ArrayList();

		// *****************************************************************************************************
		// *** 等额租金 租金测算 ****
		// *****************************************************************************************************
		// 情况一 正常情况下
		// 租金测算之封装租金测算条件 9个
		RentCalc rent = new RentCalc();
		rent.setYear_rate(conditionBean.getYearRate()); // 年利率
		rent.setIncome_number(String.valueOf( conditionBean.getIncomeNumber() ));// 期数
		rent.setLease_money(conditionBean.getCleanLeaseMoney());// 租赁本金 （租赁本金 = 设备金额
		// - 首付款）
		rent.setFuture_money(conditionBean.getNominalPrice());// 留购价
		rent.setPeriod_type(conditionBean.getPeriodType());// 1,期初 0,期未的值
		rent.setIrr_type("2");// 1,为按月份的处; 2,为按租金间隔的处理 暂时是2
		rent.setScale("8");// irr的小数位数 暂时就是8位
		rent.setLease_interval(conditionBean.getIncomeNumberYear());// 租金间隔
		// (付租方式)
		rent.setPlan_date(conditionBean.getStartDate());// 每月偿付日 替换 起租日的具体日期
		if ("".equals(contract_id) || contract_id == null) {
			rent.setContract_id(conditionBean.getProjId());// 计算具体项目现金流的KEY
		} else {
			rent.setContract_id(conditionBean.getContractId());// 计算具体项目现金流的KEY
		}
		rent.setRentScale("2");// 圆整到
		// 新增四个字段 【**************** 测算市场IRR所需的附加条件
		// *****************】【修改时间2010-06-29】
		Double mon = Double.parseDouble(conditionBean.getCleanLeaseMoney())
				+ Double.parseDouble(conditionBean.getCautionMoney());
		rent.setCle_cau_Money(mon.toString());// 净融资额 net_lease_money-资产余值
		// caution_money
		rent.setCauti_Money(conditionBean.getCautionMoney());// 保证金
		rent.setFuture_money(conditionBean.getEquipEndValue());// 资产余值
		// 【注：加负号变为负数】【2010-08-04
		// 修改去掉负号】
		rent.setStart_date(conditionBean.getStartDate());// 保证金的流入时间
		// 租金测算的现金流封装 主要是 租赁本金，手续费，咨询费 等
		List<String> llist_case = new ArrayList<String>();//
		List<String> list_date = new ArrayList<String>();//
		// 修改：-净融资额 net_lease_money-保证金 caution_money 算财务IRR
		Double list_mon = (Double.parseDouble(conditionBean.getCleanLeaseMoney()) + Double
				.parseDouble(conditionBean.getCautionMoney()))
				* -1;
		llist_case.add(list_mon.toString());
		list_date.add(conditionBean.getStartDate());

		//rent.setPreCash(llist_case);//
		//rent.setPreDate(list_date);//

		/*System.out.println("净融资额为==>" + conditionBean.getActual_fund());
		System.out.println("保证金==>" + conditionBean.getCaution_money());
		System.out.println("设备款==>" + conditionBean.getEquip_amt());
		System.out.println("值为==>" + list_mon);
		System.out.println("日期==>" + conditionBean.getStart_date());*/

		// ==============具体测算租金===============
		// ‘规则’情况下，租金具体测算如下： 租金Rent的List
		List rent_list = rent.eqRentList(rent.getYear_rate());// 得到租金list,注意不规则时的租金list
		// (只是新的租金的LIST，不包含手续费那些现金)
		// getPlanDateList(参数一,参数二) 参数一 上方数组，租金list 参数二 期初(1)or期末支付(0)
		List l_plan_date_ = rent.getPlanDateList(rent_list, rent
				.getPeriod_type());// 计划时间Plan_date的List
		// 参数说明 getHashRentPlan(参数一,参数二,参数三)
		// 参数一代表：measure_type租金计算方法，参数二代表：上方的list 封装的所有租金列表 参数三 l_plan_date_
		// 上方数组 封装时间的结果集

		// 封装HashMap
		HashMap hm = null;
		try {
			hm = rent.getHashRentPlan("1", rent_list, l_plan_date_);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取值
		l_plan_date = (List) hm.get("plan_date");// 租金偿还日期
		l_rent = (List) hm.get("rent");// 租金
		l_corpus = (List) hm.get("corpus");// 本金
		l_interest = (List) hm.get("interest");// 利息
		l_corpus_overage = (List) hm.get("corpus_overage");// 剩余本金
//		String Markirr = rent.getMarket_irr();
		// 打印输入租金计划（本地调试用）
//		for (int i = 0; i < l_plan_date.size(); i++) {
//			System.out.println("期次 " + (i + 1) + "  " + l_plan_date.get(i)
//					+ " " + l_rent.get(i) + " " + l_corpus_market.get(i) + " "
//					+ l_interest_market.get(i));
//		}
//		for(int i=0;i<l_rent.size();i++){
//			System.out.println("期次："+(i)+"rent="+l_rent.get(i)+"  corpus= "+l_corpus.get(i)+"  " +
//					"inter="+l_interest.get(i)+"   cor_overge="+l_corpus_overage.get(i));
//
//		}
		/*/////////////////
		 * 现金流及IRR测算
		 */////////////////
		RentCaleCommonTools  calcTools = new RentCaleCommonTools();
		// irr
		String irr = calcTools.getIrr(firstMoney, l_rent, caution_money, assets_value, 
				Other_expenditure, lease_interval, type,caution_sfjx);
		// 得到保证金抵扣租金List rent_list 租金List,caut_money 保证金
		 l_RentDetails = calcTools.getRentDetails(firstMoney,l_rent,l_plan_date,conditionBean);
		 //融资收益分摊表 
//		 RentInshareCal rentInshareCal = new RentInshareCal();
//		 List l_cash_rent  = calcTools.getRentCautNew(firstMoney, rent_list, caution_money,
//				 assets_value, Other_expenditure, lease_interval, type, caution_oper_way, caution_sfjx);
		 //2012-08-27 新增内容，融资收益分摊表
//		 List l_cash_rent = rentInshareCal.getInshareCashRent(lease_money,handling_charge, l_rent, caution_money,
//				 assets_value, Other_expenditure, lease_interval, type, caution_oper_way, caution_sfjx,
//				 lease_term);
//		 String inshareIrr = rentInshareCal.getInshareIrr(lease_interval, l_cash_rent);
//		 List<RentInshareBean> l_rentInshareBean = new ArrayList<RentInshareBean>();
//		 l_rentInshareBean = rentInshareCal.RentInshare(inshareIrr, conditionBean, l_cash_rent, l_plan_date);
		/*
		 * 
		 * RentInfoBox 封装测算List
		 * 
		 */
		rentInfoBox.setPlanDate(l_plan_date);
		rentInfoBox.setRent(l_rent);
		rentInfoBox.setCorpus(l_corpus);
		rentInfoBox.setInterest(l_interest);
		rentInfoBox.setCorpusOverge(l_corpus_overage);
		rentInfoBox.setRentDetails(l_RentDetails);
		rentInfoBox.setIrr(irr);//irr未乘以100
//		rentInfoBox.setL_RentInshare(l_rentInshareBean);
		
		return rentInfoBox;
	}

}
