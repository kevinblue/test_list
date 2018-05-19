package com.reckon.rentcalc.service.impl.specialCal;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.FundRentPlanIrr;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.pub.PlanDateServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.util.DictTools;
import com.reckon.util.TbBeanTools;
import com.tenwa.leasing.utils.LeasingException;

public class SpecialRegularCalServiceImpl  implements RentCalcService{


	@Override
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType,
			String userName, String[] rentAdjustList, int startList,
			List<FundPlanBean> fundPlanList, List<SpecialRulesBean> srb,
			List<FundRentPlanIrr> irrPlans, List<String>... planList)
			throws Exception {
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		try{
			if(null!=srb&&srb.size()>0){
				// 结果返回
				TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
				// 保存商务条件
				cb = DictTools.getReversDict(cb);
				cb.setRemainLeaseTerm(cb.getLeaseTerm());//初始化剩余租赁期限
				cb.setRemainCorpus(new BigDecimal(cb.getCleanLeaseMoney()).subtract(new BigDecimal(cb.getEquipEndValue())));//初始化剩余本金
				// 租金计划对象创建
				FundRentPlanBean frpb = new FundRentPlanBean();
				frpb.setProjOrCont(cb.getProjId());
				frpb.setCreator(cb.getCreator());
				frpb.setYearRate(cb.getYearRate());
				cb.setIncomeNumberYear("1");//随便给个值  分段测算里面  这个字段时空的
				
				ConditionBean newcb=cb;//备份
				RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
				//找出最后一期的租金测算方式
				String method="";
				for(int i=0;i<srb.size();i++){
					if(srb.get(i).getStartList()<startList){
						continue;
					}
					//已知本金比例
					if("regular_settlemethod.knowcorpusrate".equals(srb.get(i).getRegular_settlemethod())){
						RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
						rpcs.getKnowingCorpusPlanForSpecial(cb, frpb, srb.get(i));
					}
					//等额本息
					else if("regular_settlemethod.evenrent".equals(srb.get(i).getRegular_settlemethod())){
						RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
						rpcs.getEqualRentPlanForSpecial(cb, frpb, srb.get(i));
					}
					if(i==srb.size()-1){
						method=srb.get(i).getRegular_settlemethod();
					}
				}
				//等额本息调整  最后一期 租金不变  
				if("regular_settlemethod.evenrent".equals(method)){
					RentCalculateHelper.adjustLastListInterest(frpb, cb);
				}else{
					RentCalculateHelper.adjustLastListCorpus(frpb, cb);
				}
				// 加载日期列表
				PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
				List<String> dateList = pdsi.getPlanDateListForSpecial(cb, String.valueOf(frpb.getRentList().size()),srb);
				frpb.setPlanDateList(dateList);   
				rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);
				rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList);
				cb=newcb;
				//计算现金流和irr 以及NPV
				re_ht = RentCalculateHelper.createFundPlanCashIrrForSpecial(cb, tcb, fundPlanList, frpb,srb);
				re_ht.put("firstplandate", dateList.size()>0?dateList.get(0):"");
				re_ht.put("secondplandate", dateList.size()>1?dateList.get(1):"");
				re_ht.put("isSucess", "true");
			}
		}catch(LeasingException e){
			re_ht.put("isSucess", "false");
			re_ht.put("message", e.getMessage());
		}
		return re_ht;
	}

	@Override
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb,
			String calType, String userName, String[] rentAdjustList,
			List<FundPlanBean> fundPlanList, List<SpecialRulesBean> srb,
			List<FundRentPlanIrr> irrPlans) throws Exception {
		return null;
	}




}
