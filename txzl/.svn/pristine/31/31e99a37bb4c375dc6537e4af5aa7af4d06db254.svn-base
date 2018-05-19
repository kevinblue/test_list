package com.reckon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.tenwa.business.model.RentInfoBox;


/**
 * 
 * 
 * @author toybaby
 * Date: Jul 13, 2011 
 */
public class EqualCorpusUtil {

	/**
	 * 测算
	 * 
	 * @param conditionBean
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public static RentInfoBox  getRentInfoBox(ConditionBean conditionBean) {
		/**
		 * =======================================================
		 * 
		 * <pre>
		 * 先测算出相关的List数据
		 * </pre>
		 * 
		 * =======================================================
		 */
		RentInfoBox rentInfoBox = new RentInfoBox();
		String contract_id = conditionBean.getContractId();
		List l_plan_date = new ArrayList();
		//Irr计算条件
		String firstMoney= String.valueOf("-"+conditionBean.getCleanLeaseMoney());//现金流第一期流量
		String lease_interval = conditionBean.getIncomeNumberYear();//租金间隔
		String caution_money=conditionBean.getCautionMoney();//保证金
		String Other_expenditure = conditionBean.getOtherExpenditure();//其它支出
//		String Other_expenditure = conditionBean.getNominalprice();//残值收入
		String assets_value = conditionBean.getEquipEndValue();//资产余值
		String type = conditionBean.getPeriodType();
		//注释conditionBean.getSupplierCautionMoney()
		String manu_caution_money = "";
		// 租金测算程序
		List l_rent = new ArrayList();
		List l_corpus = new ArrayList();
		List l_interest = new ArrayList();
		List l_corpus_overage = new ArrayList();

		// *****************************************************************************************************
		// *** 等额本金 租金测算 ****
		// *****************************************************************************************************
		// 情况一 正常情况下
		// 租金测算之封装租金测算条件 9个
		RentCalc rent = new RentCalc();
		rent.setYear_rate(conditionBean.getYearRate()); // 年利率
		
		rent.setIncome_number(conditionBean.getIncomeNumber()+"");// 期数
		rent.setLease_money(conditionBean.getCleanLeaseMoney());// 租赁本金  
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
		rent.setRentScale("4");// 圆整到

		// 新增四个字段 【**************** 测算市场IRR所需的附加条件
		// *****************】【修改时间2011-07-13】
		Double mon = Double.parseDouble(conditionBean.getCleanLeaseMoney());
				//注释没有的字段- Double.parseDouble(conditionBean.getPmtEndValue());
		//System.out.println("实际本金Cle_cau_Money="+mon);
		rent.setCle_cau_Money(mon.toString());// 租凭本金 lease_money- 资产余值Nominalprice
		// caution_money
		rent.setCauti_Money(conditionBean.getCautionMoney());// 保证金
		rent.setFuture_money(conditionBean.getNominalPrice());// 期末残值
		rent.setStart_date(conditionBean.getStartDate());// 保证金的流入时间

		// 租金测算的现金流封装 主要是 租赁本金，手续费，咨询费 等
		List<String> llist_case = new ArrayList<String>();//
		List<String> list_date = new ArrayList<String>();//
		// 修改：-净融资额 net_lease_money-保证金 caution_money 算财务IRR
		Double list_mon = ( Double
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

		// 封装HashMap 获得所有租金计划
		HashMap hm = null;
		try {
			hm = rent.getPlanByEqCorpus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取值
		l_plan_date = (List) hm.get("plan_date");// 租金偿还日期
		l_rent = (List) hm.get("rent");// 租金
		l_corpus = (List) hm.get("corpus");// 本金
		l_interest = (List) hm.get("interest");// 利息
		l_corpus_overage = (List) hm.get("corpus_overage");// 剩余本金
		/*for(int i=0;i<l_rent.size();i++){
			System.out.println("rent="+l_rent.get(i)+"  corpus= "+l_corpus.get(i)+"  " +
					"inter="+l_interest.get(i)+"   cor_overge="+l_corpus_overage.get(i));

		}*/

		
		/*/////////////////
		 * 现金流及IRR测算
		 */////////////////
		RentCaleCommonTools  calcTools = new RentCaleCommonTools();
		List l_RentDetails = new ArrayList();
		String caution_oper_way = conditionBean.getCautionMoney();//保证金返回方式
		String caution_sfjx = "";//conditionBean.getCaution_sfjx();//保证金是否计息
		String irr = calcTools.getIrr(firstMoney, l_rent, caution_money, assets_value, Other_expenditure, lease_interval, type, manu_caution_money);
		//String irr = calcTools.getIrr(firstMoney, l_rent, caution_money, assets_value, 
				//Other_expenditure, lease_interval, type,caution_oper_way,caution_sfjx,manu_caution_money);
		// 得到保证金抵扣租金List rent_list 租金List,caut_money 保证金
		 l_RentDetails = calcTools.getRentDetails(firstMoney,l_rent,l_plan_date,conditionBean);
		 
//		 RentInshareCal rentInshareCal = new RentInshareCal();
////		 List l_cash_rent  = calcTools.getRentCautNew(firstMoney, rent_list, caution_money,
////				 assets_value, Other_expenditure, lease_interval, type, caution_oper_way, caution_sfjx);
//		 //2012-08-27 新增内容，融资收益分摊表
//		 List l_cash_rent = rentInshareCal.getInshareCashRent(lease_money,handling_charge, l_rent, caution_money,
//				 assets_value, Other_expenditure, lease_interval, type, caution_oper_way, caution_sfjx,
//				 lease_term);
//		 String inshareIrr = rentInshareCal.getInshareIrr(lease_interval, l_cash_rent);
//		 List<RentInshareBean> l_rentInshareBean = new ArrayList<RentInshareBean>();
//		 l_rentInshareBean = rentInshareCal.RentInshare(inshareIrr, conditionBean, l_cash_rent, l_plan_date);
//		
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
//		rentInfoBox.setRentInshare(l_rentInshareBean);


	
		/**
		 * =======================================================
		 * 开始更新数据库表数据（交易结构临时表、项目租金计划临时表、项目租金现金流临时表）
		 * =======================================================
		 */
		System.out.println("数据库操作开始(等额本金测算)================================");
//		RentDBOperation.execDBOperation(contract_id, conditionBean, irr,
//				l_plan_date, l_rent, l_corpus, l_interest, l_corpus_overage,
//				l_RentDetails, rent, new_rent);
		return rentInfoBox;

		
	}		
	
}
