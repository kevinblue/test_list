package com.reckon.util;

import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.calc.SetLawRentCaleUtil;
import com.tenwa.business.model.RentCashBean;
import com.tenwa.business.model.RentInfoBox;


public class SettleLawUtil {

	/**
	 * @author toybaby 2011-07-20
	 * @param conditionBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static RentInfoBox getRentInfoBox(ConditionBean conditionBean) {

		RentInfoBox rentInfoBox = new RentInfoBox();
		String contract_id = conditionBean.getContractId();
		String leaseMoney = String.valueOf(conditionBean.getCleanLeaseMoney());// 租赁本金
		String income_number = String.valueOf(conditionBean.getIncomeNumber());// 还租次数
		String yearRate = String.valueOf(conditionBean.getYearRate());// 年利率
		String lease_interval = conditionBean.getIncomeNumberYear();// 租金间隔
		String caution_money = conditionBean.getCautionMoney();// 保证金
		String Other_expenditure = conditionBean.getOtherExpenditure();// 其它支出
//		String Other_expenditure = conditionBean.getNominalprice();// 残值收入
		String assets_value = conditionBean.getEquipEndValue();// 资产余值
		String rentScale = "0";// 租金精确度
		String newScale = "2";
		String grace = "0";
		String delay = "0";
		String type = "0";// 期末
		conditionBean.setPeriodType("0");
		String plan_date = conditionBean.getStartDate();// 起租日期
		String firstMoney = String
				.valueOf("-" + conditionBean.getCleanLeaseMoney());// 现金流第一期流量
		//String caution_oper_way = conditionBean.getCautionOperway();//保证金返回方式
		// 现金流部分调用
		// 装租金测算条件 9个
		RentCalc rent = new RentCalc();
		rent.setYear_rate(conditionBean.getYearRate()); // 年利率
		rent.setIncome_number(conditionBean.getIncomeNumber()+"");// 期数
		rent.setLease_money(conditionBean.getCleanLeaseMoney());// 租赁本金 （租赁本金 = 设备金额
		// - 首付款）
		rent.setFuture_money(conditionBean.getNominalPrice());// 留购价
		rent.setPeriod_type(conditionBean.getPeriodType());// 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
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
		rent.setRentScale("4");// 圆整到

		SetLawRentCaleUtil slrcu = new SetLawRentCaleUtil();
		// 2010-12-08 rentScale-->newScale 除了租金之外的
		// 租金列表
		List rent_list = slrcu.getRentList(leaseMoney, assets_value,
				income_number, yearRate, lease_interval, rentScale, grace,
				delay);
		//System.out.println("租金计算完成");
		// 利息列表
		List inter_list = slrcu.getInterMarket(rent_list, leaseMoney, yearRate,
				lease_interval, rentScale);
		//System.out.println("利息计算完成");
		// 得到本金list
		List corpus_market = slrcu.getCorpusList(rent_list, inter_list,
				leaseMoney, assets_value, newScale);
		// 重新计算利息 目的为了处理最后一期剩余本金不为0的情况
		inter_list = slrcu.getNewInterList(rent_list, corpus_market,
				inter_list, rentScale);
		// 得到本金余额
		List corpusOverge_market = slrcu.getCorpusOvergeList(String
				.valueOf(conditionBean.getCleanLeaseMoney()), corpus_market,
				newScale);
		// 计划日期
		List date_list = slrcu.getPlanDateList(rent_list, type, lease_interval,
				plan_date, grace, delay);

		/*
		 * ///////////////// 现金流及IRR测算
		 */// //////////////
		RentCaleCommonTools calcTools = new RentCaleCommonTools();
		// irr
		String caution_sfjx = "";//conditionBean.getCaution_sfjx();//保证金是否计息
		String irr = calcTools.getIrr(firstMoney, rent_list, caution_money,
				assets_value, Other_expenditure, lease_interval, type,caution_sfjx);
		// 得到保证金抵扣租金List rent_list 租金List,caut_money 保证金
		List l_RentDetails = calcTools.getRentDetails(firstMoney, rent_list,
				date_list, conditionBean);
//		 RentInshareCal rentInshareCal = new RentInshareCal();
////		 List l_cash_rent  = calcTools.getRentCautNew(firstMoney, rent_list, caution_money,
////				 assets_value, Other_expenditure, lease_interval, type, caution_oper_way, caution_sfjx);
//		 //2012-08-27 新增内容，融资收益分摊表
//		 List l_cash_rent = rentInshareCal.getInshareCashRent(lease_money,handling_charge, rent_list, caution_money,
//				 assets_value, Other_expenditure, lease_interval, type, caution_oper_way, caution_sfjx,
//				 lease_term);
//		 String inshareIrr = rentInshareCal.getInshareIrr(lease_interval, l_cash_rent);
//		 List<RentInshareBean> l_rentInshareBean = new ArrayList<RentInshareBean>();
//		 l_rentInshareBean = rentInshareCal.RentInshare(inshareIrr, conditionBean, l_cash_rent, date_list);
		/*
		 * 
		 * RentInfoBox 封装测算List
		 * 
		 */
		rentInfoBox.setPlanDate(date_list);
		rentInfoBox.setRent(rent_list);
		rentInfoBox.setCorpus(corpus_market);
		rentInfoBox.setInterest(inter_list);
		rentInfoBox.setCorpusOverge(corpusOverge_market);
		rentInfoBox.setRentDetails(l_RentDetails);
		rentInfoBox.setIrr(irr);// irr未乘以100
//		rentInfoBox.setL_RentInshare(l_rentInshareBean);

		//System.out.println("irr==" + irr);
		for (int i = 0; i < l_RentDetails.size(); i++) {
			RentCashBean a = (RentCashBean) l_RentDetails.get(i);
			/*System.out.println(
			// date_list.get(i)+" rent="+rent_list.get(i)+"
					// cor="+corpus_market.get(i)
					// +" inte="+inter_list.get(i)+"
					// cor_ovg="+corpusOverge_market.get(i));
					// " Details="+l_RentDetails.get(i)
					a.getPlan_date() + "  " + a.getFollow_in() + "  "
							+ a.getFollow_in_detail() + "  "
							+ a.getFollow_out() + "  "
							+ a.getFollow_out_detail() + "  "
							+ a.getNet_follow()

					);*/

		}

		/**
		 * =======================================================
		 * 开始更新数据库表数据（交易结构临时表、项目租金计划临时表、项目租金现金流临时表）
		 * =======================================================
		 */
		//System.out.println("数据库操作开始(均息法测算)================================");
		// RentDBOperation.execDBOperation(contract_id, conditionBean, irr,
		// date_list, rent_list, corpus_market, inter_list, corpusOverge_market,
		// l_RentDetails, rent, new_rent);

		return rentInfoBox;

	}

}
