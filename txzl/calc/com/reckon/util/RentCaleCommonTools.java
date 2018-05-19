package com.reckon.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.tenwa.business.model.RentCashBean;


public class RentCaleCommonTools {

	/**
	 * 得到保证金抵扣租金List 现金 流明细
	 * 
	 * @param rent_list
	 *            租金List
	 * @param caut_money
	 *            保证金
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RentCashBean> getRentDetails(String firstMoney, List rent_list,
			List date_list, ConditionBean conditionBean) {

		List<RentCashBean> l_RentDetails = new ArrayList<RentCashBean>();// 现金流封装
		List new_rent = new ArrayList();// 净流量
		String period_type = conditionBean.getPeriodType();// 付款类型
		String assets_value = conditionBean.getEquipEndValue();// 资产余值
		String lease_interval = conditionBean.getIncomeNumberYear();// 租金间隔
		// 先构造正常情况下的租金 明细
		String first_in = "0";// 第一期流入量
		String first_in_str = "";// 第一期流出明细
		String last_in = rent_list.get(rent_list.size() - 1).toString();// 最后一期流入量
		String last_in_str = "租金：" + last_in;// 最后一期流入明细
		// ------------------------第一期流入量处理-----------------------------------
		// ==========================================================================
		/*
		 * 名称 收支情况 1-设备金额 支 equip_amt 2-首付款 收 first_payment 3-租赁保证金 收
		 * caution_money 4-租赁手续费 收 handling_charge 5-管理费 收 management_fee 6-残值收入
		 * 收 nominalprice 7-厂商返利 收 return_amt 8-租前息 收 before_interest 9-利息补贴 收
		 * rate_subsidy
		 * 
		 * 10-贴现息 支 discount_rate 11-其他收入 收 other_income
		 * 
		 * 12-其他支出 支 other_expenditure 13-咨询费收入 收 consulting_fee_in
		 */
		// --定义资金参数--
		String equip_amt = conditionBean.getEquipAmt();
		String first_payment = conditionBean.getFirstPayment();
		String caution_money = conditionBean.getCautionMoney();
		String handling_charge = conditionBean.getHandlingChargeMoney();
		String management_fee = conditionBean.getManagementMoney();
		String nominalprice = conditionBean.getNominalPrice();
		String return_amt = conditionBean.getReturnAmt();
		String before_interest = conditionBean.getBeforeInterest();
		String rate_subsidy = "0";//conditionBean.getRate_subsidy();//利息补贴
		String discount_rate = "0";//conditionBean.getDiscount_rate();//贴现息
		String other_income = conditionBean.getOtherIncome();
		String other_expenditure = conditionBean.getOtherExpenditure();
		//注释 conditionBean.getConsultingMoney()
		String consulting_fee_in = "";
		// 2012-4-11 jaffe 修改 保险费的加入 判断是否本司付款
		String insure_money = conditionBean.getInsureMoney();
		String insure_type = conditionBean.getInsureMoneyType();
		
		// 2012-08-16 Toybaby 修改，增加保证金返还方式
		//注释 conditionBean.getCautionOperway()
		//String caution_oper_way = "0";
		String caution_sfjx = "";//conditionBean.getCaution_sfjx();
		//注释  conditionBean.getSupplierCautionMoney()
		String manu_caution_money ="0";
		String if_leas = "";//conditionBean.getIf_leas();//// 租赁公司收取 - 选择是|否
		//System.out.println(insure_money + "AAA::" + insure_type+""+caution_oper_way);
		//2012-11-09修改，新增，如果首付非租赁公司收取，则不加首付款
		//System.out.println("if_leas="+if_leas);
		if("是".equals(if_leas)){
			if (Double.parseDouble(first_payment) != 0.00) {
				first_in = MathExtend.parseDoubleStr(first_payment);
				first_in_str = "首付款:" + first_payment;
			}
		}
		//System.out.println("if_leas通过"+2);
		if (Double.parseDouble(caution_money) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in, caution_money));
			first_in_str = first_in_str + "保证金:" + caution_money;
		}
		//System.out.println("manu_caution_money="+manu_caution_money);
		if (Double.parseDouble(manu_caution_money) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in, manu_caution_money));
			first_in_str = first_in_str + "厂商保证金:" + manu_caution_money;
		}
		//System.out.println("manu_caution_money2="+manu_caution_money);
		if (Double.parseDouble(handling_charge) != 0.00) {
			first_in = String
					.valueOf(MathExtend.add(first_in, handling_charge));
			first_in_str = first_in_str + "手续费:" + handling_charge;
		}
		if (Double.parseDouble(management_fee) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in, management_fee));
			first_in_str = first_in_str + "管理费:" + management_fee;
		}
		if (Double.parseDouble(consulting_fee_in) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in,
					consulting_fee_in));
			first_in_str = first_in_str + "咨询费收入:" + consulting_fee_in;
		}
		if (Double.parseDouble(rate_subsidy) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in, rate_subsidy));
			first_in_str = first_in_str + "利息补帖:" + rate_subsidy;
		}
		if (Double.parseDouble(before_interest) != 0.00) {
			first_in = String
					.valueOf(MathExtend.add(first_in, before_interest));
			first_in_str = first_in_str + "租前息:" + before_interest;
		}
		if (Double.parseDouble(other_income) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in, other_income));
			first_in_str = first_in_str + "其它收入:" + other_income;
		}
		if (Double.parseDouble(other_expenditure) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in,
					other_expenditure));
			first_in_str = first_in_str + "其它支出:" + other_expenditure;
		}

		if ("insure_type1".equals(insure_type)) {// 本司付款
			if (Double.parseDouble(insure_money) != 0.00) {
				first_in = String.valueOf(MathExtend
						.add(first_in, insure_money));
				first_in_str = first_in_str + "保险费:" + insure_money;
			}
		}

		// if(Double.parseDouble(nominalprice)!=0.00){
		// first_in =
		// String.valueOf(Double.parseDouble(first_in)+Double.parseDouble(nominalprice));
		// first_in_str =first_in_str +
		// "残值收入:"+String.valueOf(Double.parseDouble(nominalprice));
		// }
		if (Double.parseDouble(return_amt) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in, return_amt));
			first_in_str = first_in_str + "厂商返利:" + return_amt;
		}
		if (Double.parseDouble(discount_rate) != 0.00) {
			first_in = String.valueOf(MathExtend.add(first_in, discount_rate));
			first_in_str = first_in_str + "贴现息:" + discount_rate;
		}

		// ==========================================================================
		// ------------------------最后一期流入量处理---------------------------------
		// ==========================================================================
		if (Double.parseDouble(assets_value) != 0.00) {// 资产余值不为0
			last_in = Tools.formatNumberDoubleTwo(MathExtend.add(last_in,
					assets_value));
			last_in_str = last_in_str + last_in + "资产余值："
					+ Tools.formatNumberDoubleTwo(assets_value);
		}
		if (Double.parseDouble(nominalprice) != 0.00) {// 残值收入不为0
			last_in = Tools.formatNumberDoubleTwo(MathExtend.add(last_in,
					nominalprice));
			last_in_str = last_in_str + "留购价款：" + nominalprice;
		}
		//System.out.println("进入new_rent测算方法----");
		// ==========================================================================
		new_rent = getRentCautNew(firstMoney, rent_list, caution_money,
				assets_value, nominalprice, lease_interval, period_type,caution_sfjx);

		if (Double.parseDouble(caution_money) > 0) {// 保证金不为0

			// /follow_in,follow_in_detail,follow_out,follow_out_detail
			for (int i = 0; i < new_rent.size(); i++) {
				RentCashBean rentCashBean = new RentCashBean();
				if (i == 0) {// 第一期分期末期初进行单独处理
					//System.out.println("保证金不为0时处理");
					if ("1".equals(period_type)) {// 期初
						String temp_in = String.valueOf(MathExtend.add(
								first_in, rent_list.get(i).toString()));
						// BigDecimal
						// .valueOf(Double.parseDouble(first_in)
						// + Double.parseDouble(rent_list.get(i)
						// .toString())));
						String temp_in_str = first_in_str
								+ "第一期租金:"
								+ MathExtend.parseDoubleStr(rent_list.get(i)
										.toString());
						// bean封装
						rentCashBean.setPlan_date(date_list.get(i).toString());
						rentCashBean.setFollow_in(temp_in);
						rentCashBean.setFollow_in_detail(temp_in_str);
						//2012-11-09新增，加入首付款非租赁公司收取选项
						if("否".equals(if_leas)){
							rentCashBean.setFollow_out(String.valueOf(Double.parseDouble(equip_amt)-Double.parseDouble(first_payment)));
							rentCashBean.setFollow_out_detail("设备款:" + String.valueOf(Double.parseDouble(equip_amt)-Double.parseDouble(first_payment)));
						}else{
							rentCashBean.setFollow_out(equip_amt);
							rentCashBean.setFollow_out_detail("设备款:" + equip_amt);
						}
						rentCashBean.setNet_follow(new_rent.get(i).toString());
					} else {// 期末
						// bean封装
						rentCashBean
								.setPlan_date(conditionBean.getStartDate());
						rentCashBean.setFollow_in(first_in);
						rentCashBean.setFollow_in_detail(first_in_str);
						if("否".equals(if_leas)){
							rentCashBean.setFollow_out(String.valueOf(Double.parseDouble(equip_amt)-Double.parseDouble(first_payment)));
							rentCashBean.setFollow_out_detail("设备款:" + String.valueOf(Double.parseDouble(equip_amt)-Double.parseDouble(first_payment)));
						}else{
							rentCashBean.setFollow_out(equip_amt);
							rentCashBean.setFollow_out_detail("设备款:" + equip_amt);
						}
						rentCashBean.setNet_follow(new_rent.get(i).toString());

					}
					// List封装
					// l_RentDetails.add(rentCashBean);

				} else if (i < new_rent.size() - 1 && i > 0) {
					if ("0".equals(period_type)) {
						i = i - 1;// 期末时现金流增加了一期，rent_list比new_rent少一期值
					}
					// bean封装
					rentCashBean.setPlan_date(date_list.get(i).toString());
					rentCashBean.setFollow_in(rent_list.get(i).toString());
					rentCashBean.setFollow_in_detail("租金："
							+ rent_list.get(i).toString());
					// rentCashBean.setFollow_out("0");
					// rentCashBean.setFollow_out_detail("");
					// 2010-12-28 号修改成保证金多期抵扣
					// rentCashBean.setFollow_out(Tools
					// .formatNumberDoubleTwo(String.valueOf(Double
					// .parseDouble(rent_list.get(i).toString())
					// - Double.parseDouble("0"
					// .equals(period_type) ? new_rent
					// .get(i + 1).toString() : new_rent
					// .get(i).toString()))));
					rentCashBean.setFollow_out(Tools
							.formatNumberDoubleTwo(MathExtend.subtract(
									rent_list.get(i).toString(), "0"
											.equals(period_type) ? new_rent
											.get(i + 1).toString() : new_rent
											.get(i).toString())));

					rentCashBean.setFollow_out_detail(Double
							.parseDouble(rent_list.get(i).toString()) != Double
							.parseDouble("0".equals(period_type) ? new_rent
									.get(i + 1).toString() : new_rent.get(i)
									.toString()) ? "保证金抵扣:"
							+ Tools.formatNumberDoubleTwo(rentCashBean
									.getFollow_out()) : "");
					if ("0".equals(period_type)) {
						i = i + 1;// 期末时现金流增加了一期，rent_list比new_rent少一期值
					}
					rentCashBean.setNet_follow(new_rent.get(i).toString());// +1目的为了还原i值
					// List封装
					// l_RentDetails.add(rentCashBean);
				} else {// 最后一期进行保证金抵扣
					if ("0".equals(period_type)) {
						i = i - 1;// 期末时现金流增加了一期，rent_list比new_rent少一期值
					}
					// String temp =
					// Tools.formatNumberDoubleTwo(String.valueOf(Double.parseDouble(rent_list.get(i).toString())-
					// Double.parseDouble(caution_money)+Double.parseDouble(assets_value)+
					// Double.parseDouble(other_expenditure)));
					// bean封装
					rentCashBean.setPlan_date(date_list.get(i).toString());
					rentCashBean.setFollow_in(last_in);
					rentCashBean.setFollow_in_detail(last_in_str);
					// rentCashBean.setFollow_out(caution_money);
					// rentCashBean.setFollow_out_detail("保证金抵扣:"+caution_money);
					// 2011-12-28修改为保证金多期抵扣
					// rentCashBean.setFollow_out(Tools
					// .formatNumberDoubleTwo(String.valueOf(Double
					// .parseDouble(rent_list.get(i).toString())
					// - Double.parseDouble("0"
					// .equals(period_type) ? new_rent
					// .get(i + 1).toString() : new_rent
					// .get(i).toString()))));
					/*System.out.println("最后一期保证金抵扣的操作："+last_in.toString()+"_"+new_rent
							.get(i).toString());*/
//					if("退款".equals(caution_oper_way)){
//						
//						rentCashBean.setFollow_out(Tools
//								.formatNumberDoubleTwo(MathExtend.subtract(
//										last_in.toString(), "0"
//										.equals(period_type) ? new_rent
//												.get(i + 1).toString() : new_rent
//												.get(i).toString())));
//						
//						rentCashBean.setFollow_out_detail(Double
//								.parseDouble(rent_list.get(i).toString()) != Double
//								.parseDouble("0".equals(period_type) ? new_rent
//										.get(i + 1).toString() : new_rent.get(i)
//										.toString()) ? "保证金退款:"
//												+ Tools.formatNumberDoubleTwo(rentCashBean
//														.getFollow_out()) : "");
//					}else{
//						
//						rentCashBean.setFollow_out(Tools
//								.formatNumberDoubleTwo(MathExtend.subtract(
//										last_in.toString(), "0"
//										.equals(period_type) ? new_rent
//												.get(i + 1).toString() : new_rent
//												.get(i).toString())));
//						
//						rentCashBean.setFollow_out_detail(Double
//								.parseDouble(rent_list.get(i).toString()) != Double
//								.parseDouble("0".equals(period_type) ? new_rent
//										.get(i + 1).toString() : new_rent.get(i)
//										.toString()) ? "保证金抵扣:"
//												+ Tools.formatNumberDoubleTwo(rentCashBean
//														.getFollow_out()) : "");
//					}

					if ("0".equals(period_type)) {
						i = i + 1;// 期末时现金流增加了一期，rent_list比new_rent少一期值
					}
					rentCashBean.setNet_follow(new_rent.get(i).toString());
					// List封装
					// l_RentDetails.add(rentCashBean);
				}
				l_RentDetails.add(rentCashBean);
			}

		} else {// 无保证金

			for (int i = 0; i < new_rent.size(); i++) {
				RentCashBean rentCashBean = new RentCashBean();
				if (i == 0) {// 第一期分期末期初进行单独处理
					if ("1".equals(period_type)) {// 期初
						String temp_in = String.valueOf(Double
								.parseDouble(first_in)
								+ Double.parseDouble(rent_list.get(i)
										.toString()));

						String temp_in_str = first_in_str
								+ "第一期租金:"
								+ MathExtend.parseDoubleStr(rent_list.get(i)
										.toString());
						// bean封装
						rentCashBean.setPlan_date(date_list.get(i).toString());
						rentCashBean.setFollow_in(temp_in);
						rentCashBean.setFollow_in_detail(temp_in_str);
						if("否".equals(if_leas)){
							rentCashBean.setFollow_out(String.valueOf(Double.parseDouble(equip_amt)-Double.parseDouble(first_payment)));
							rentCashBean.setFollow_out_detail("设备款:" + String.valueOf(Double.parseDouble(equip_amt)-Double.parseDouble(first_payment)));
						}else{
							rentCashBean.setFollow_out(equip_amt);
							rentCashBean.setFollow_out_detail("设备款:" + equip_amt);
						}
						rentCashBean.setNet_follow(new_rent.get(i).toString());
					} else {// 期末
						// bean封装
						rentCashBean
								.setPlan_date(conditionBean.getStartDate());
						rentCashBean.setFollow_in(first_in);
						rentCashBean.setFollow_in_detail(first_in_str);
						if("否".equals(if_leas)){
							rentCashBean.setFollow_out(String.valueOf(Double.parseDouble(equip_amt)-Double.parseDouble(first_payment)));
							rentCashBean.setFollow_out_detail("设备款:" + String.valueOf(Double.parseDouble(equip_amt)-Double.parseDouble(first_payment)));
						}else{
							rentCashBean.setFollow_out(equip_amt);
							rentCashBean.setFollow_out_detail("设备款:" + equip_amt);
						}
						rentCashBean.setNet_follow(new_rent.get(i).toString());

					}
					// List封装
					l_RentDetails.add(rentCashBean);

				} else if (i < new_rent.size() - 1 && i > 0) {
					if ("0".equals(period_type)) {
						i = i - 1;// 期末时现金流增加了一期，rent_list比new_rent少一期值
					}
					// bean封装
					rentCashBean.setPlan_date(date_list.get(i).toString());
					rentCashBean.setFollow_in(rent_list.get(i).toString());
					rentCashBean.setFollow_in_detail("租金："
							+ rent_list.get(i).toString());
					rentCashBean.setFollow_out("0");
					rentCashBean.setFollow_out_detail("");
					if ("0".equals(period_type)) {
						i = i + 1;// 期末时现金流增加了一期，rent_list比new_rent少一期值
					}
					rentCashBean.setNet_follow(new_rent.get(i).toString());
					// List封装
					l_RentDetails.add(rentCashBean);
				} else {// 最后一期进行保证金抵扣
					// String temp =
					// Tools.formatNumberDoubleTwo(String.valueOf(Double.parseDouble(rent_list.get(i).toString())
					// +Double.parseDouble(assets_value)+Double.parseDouble(other_expenditure)));
					// bean封装
					if ("0".equals(period_type)) {
						i = i - 1;// 期末时现金流增加了一期，rent_list比new_rent少一期值
					}
					rentCashBean.setPlan_date(date_list.get(i).toString());
					rentCashBean.setFollow_in(last_in);
					rentCashBean.setFollow_in_detail(last_in_str);
					rentCashBean.setFollow_out("0.00");
					rentCashBean.setFollow_out_detail("");
					if ("0".equals(period_type)) {
						i = i + 1;// 期末时现金流增加了一期，rent_list比new_rent少一期值
					}
					rentCashBean.setNet_follow(new_rent.get(i).toString());
					// List封装
					l_RentDetails.add(rentCashBean);

				}
			}

		}
		//2012-11-02日修改，新增厂商保证金退款，下面一块为新增，单独针对最扣一期进行处理
		if(Double.parseDouble(manu_caution_money)>0){
			RentCashBean rentCashBean_last = new RentCashBean();
			rentCashBean_last = l_RentDetails.get(l_RentDetails.size()-1);
			rentCashBean_last.setFollow_out(String.valueOf(Double.parseDouble(rentCashBean_last.getFollow_out())+Double.parseDouble(manu_caution_money)));
			rentCashBean_last.setNet_follow(String.valueOf(Double.parseDouble(rentCashBean_last.getNet_follow())-Double.parseDouble(manu_caution_money)));
			rentCashBean_last.setFollow_out_detail(rentCashBean_last.getFollow_out_detail()+"厂商保证金退款："+manu_caution_money);
			l_RentDetails.set(l_RentDetails.size()-1, rentCashBean_last);
		}
		return l_RentDetails;
	}

	/**
	 * 得到净流量租金List
	 * 
	 * @param rent_list
	 *            租金List
	 * @param caut_money
	 *            保证金
	 * @param caution_oper_way 保证金返还方式  退款||抵扣    
	 * @param caution_sfjx 保证金是否计息11      
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getRentCautNew(String firstMoney, List rent_list,
			String caution_money, String assets_value,
			String Other_expenditure, String lease_interval, String type,
			String caution_sfjx) {
		List new_list = new ArrayList();
		int i = 0;
		//manu_caution_money="0";
		if ("1".equals(type)) {// 先付时
			i++;
			new_list.add(Tools.formatNumberDoubleTwo(String.valueOf(Double
					.parseDouble(firstMoney)
					+ Double.parseDouble(rent_list.get(0).toString()))));
		} else {
			new_list.add(Tools.formatNumberDoubleTwo(firstMoney));
		}
		//System.out.println("进入rent_list循环------");
		for (; i < rent_list.size(); i++) {
			if (i < (rent_list.size() - 1)) {
				new_list.add(rent_list.get(i));
			} else {// 最后一期现金流考虑资产余值(租金-保证金+资产余值+残值收入)
					//2012-11-01修改，增加厂商保证金的退款项（租金-保证金-厂商保证金+资产余值+残值收入）
				new_list.add(Tools.formatNumberDoubleTwo(String.valueOf(Double
						.parseDouble(rent_list.get(i).toString())
						- Double.parseDouble(caution_money)
						+ Double.parseDouble(assets_value)
						+ Double.parseDouble(Other_expenditure))));
			}
			// System.out.println("new_list===="+new_list.get(i));
		}

		/*for (int j = 0; j < new_list.size(); j++) {
			System.out.println("NNNN--new_list=" + new_list.get(j)+"caution_oper_way="+caution_oper_way);
		}*/
		//2012-08-16 修改 BY Toybaby 保证金不计息
		//2012-08-16 修改 BY Toybaby  增加 最后一期抵扣，比之前稍做修改  退款|抵扣
		// 最后几期进行保证金抵扣----2011.12.28日修改
//		if("抵扣".equals(caution_oper_way)){
//		for (int j = new_list.size() - 1; j > 0; j--) {
//			// double last_rent =
//			// Double.parseDouble(new_list.get(new_list.size()-1).toString());
//			if (Double.parseDouble(new_list.get(j).toString()) < 0) {
//				double tmp_rent = Double
//						.parseDouble(new_list.get(j).toString());
//				new_list.set((j), "0.00");// 如果该期小于0，把该期置为0，该期产生的负值算到上一期
//				new_list.set(j - 1, Double.parseDouble(new_list.get(j - 1)
//						.toString())
//						+ tmp_rent);
//			} else {
//				break;
//			}
//		}
//		}else{//退款处理，只扣处最后一期，2012-08-16修改By Toybaby 空实现，不做任何处理
//			
//			
//		}
		return new_list;
	}

	public static void main(String[] args) {
		String temp_in = String.valueOf(Double.parseDouble("137415979")
				+ Double.parseDouble("137415979".toString()));

		BigDecimal tempdec = new BigDecimal("0.000000");
		tempdec = BigDecimal.valueOf(Double.parseDouble(temp_in));
		// tempdec = BigDecimal.valueOf(Double.parseDouble("137415979")+
		// Double.parseDouble("137415979".toString()));

		/*System.out.println("11::" + temp_in);
		System.out.println("22::" + tempdec);

		String temp_in2 = String.valueOf(tempdec);
		System.out.println("33::" + temp_in2);*/
	}

	/**
	 * 得到irr
	 * 
	 * @param firstMoney
	 *            净融资额
	 * @param rent_list
	 *            租金计划
	 * @param lease_interval
	 *            租金间隔
	 * @param caution_money
	 *            保证金
	 * @param assets_value
	 *            资产余值
	 * @param Other_expenditure
	 *            其它支出
	 * @param type
	 *            付款类型
	 * @param caution_oper_way 保证金还款类型  
	 * @param caution_sfjx 保证金是否计息         
	 * @return irr
	 */

	@SuppressWarnings("unchecked")
	public String getIrr(String firstMoney, List rent_list,
			String caution_money, String assets_value,
			String Other_expenditure, String lease_interval, String type,
			String caution_sfjx) {
		List new_list = new ArrayList();
		// 获得现金净流量
		new_list = getRentCautNew(firstMoney, rent_list, caution_money,
				assets_value, Other_expenditure, lease_interval, type,
				caution_sfjx);
		//System.out.println("new_list=ssssss"+2);
		IrrCal ic = new IrrCal();
		String irr = Tools.formatNumberDoubleScale(ic.getIRR(new_list,
				lease_interval, lease_interval, String.valueOf(12 / Integer
						.parseInt(lease_interval))), 8);
		return irr;
	}
	


}
