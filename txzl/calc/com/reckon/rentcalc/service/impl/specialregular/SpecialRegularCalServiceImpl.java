package com.reckon.rentcalc.service.impl.specialregular;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.entity.contract.reckon.condition.SpecialRulesBean;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.evencorpus.EvenCorpusCaleServiceImpl;
import com.reckon.rentcalc.service.impl.pub.PlanDateServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.util.DictTools;
import com.reckon.util.TbBeanTools;
import com.tenwa.leasing.utils.LeasingException;

public class SpecialRegularCalServiceImpl implements RentCalcService{
	/**
	 * 立项 变更
	 * @param cb
	 * @param calType
	 * @param userName
	 * @param rentAdjustList
	 * @param startList
	 * @param fundPlanList
	 * @param srb
	 * @param rbicblist
	 * @param otherincomepaymentlist
	 * @return
	 * @throws Exception
	 */

	public FundRentPlanBean rentPlanCalByConditionAndTab(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb,List<FundPlanBean> fundPlanList,List<SpecialRulesBean> srb) throws Exception {
		//租赁期限月重新计算
		int leaseTerm=0;
		for(int i=0;i<srb.size();i++){
			leaseTerm=leaseTerm+(srb.get(i).getEndList()-srb.get(i).getStartList()+1)*srb.get(i).getRegularMonths();
		}
		cb.setLeaseTerm(leaseTerm);
		FundRentPlanBean frpbnew=new FundRentPlanBean();
		String dateInterval1="";
		String dateInterval2="";
		List<String> corpustList=frpb.getCorpusBusinessList();
		BigDecimal corpus=BigDecimal.ZERO;
		for(int i=startList-1;i<corpustList.size();i++){
			corpus=corpus.add(new BigDecimal(corpustList.get(i)));
		}
		int term=0;
		for(int i=0;i<srb.size();i++){
			if(startList>=srb.get(i).getStartList()&&startList<=srb.get(i).getEndList()){
				term=term+(srb.get(i).getEndList()-startList+1)*srb.get(i).getRegularMonths();
			}else if(startList<srb.get(i).getStartList()){
				term=term+(srb.get(i).getEndList()-srb.get(i).getStartList()+1)*srb.get(i).getRegularMonths();
			}
		}
		if(null!=srb&&srb.size()>0){
			// 保存商务条件
			cb = DictTools.getReversDict(cb);
			cb.setRemainLeaseTerm(term);//初始化剩余租赁期限
			cb.setRemainCorpus(corpus);//初始化剩余本金
			frpb.setProjOrCont(cb.getProjId());
			frpb.setCreator(cb.getCreator());
			frpb.setYearRate(cb.getYearRate());
			cb.setIncomeNumberYear("1");//随便给个值  分段测算里面  这个字段时空的
			String method="";
			for(int i=0;i<srb.size();i++){
				//已知租金
				if("special_regular.knowingrent".equals(srb.get(i).getRegularSettlemethod().getId())){
					RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
					//rpcs.getKnowingRentPlanForSpecial(cb, frpbnew, srb.get(i),startList);
				}
				//已知本金
				else if("special_regular.knowingcorpus".equals(srb.get(i).getRegularSettlemethod().getId())){
					RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
					//rpcs.getKnowingCorpusPlanForSpecial(cb, frpbnew, srb.get(i),startList);
				}
				//等额本金
				else if("special_regular.evencorpus".equals(srb.get(i).getRegularSettlemethod().getId())){
					EvenCorpusCaleServiceImpl rcs = new EvenCorpusCaleServiceImpl();
					//rcs.rentPlanCalByConditionAndTabForSpecial(cb, tcb, startList, frpbnew, null,srb.get(i));
				}
				//等额租金
				else if("special_regular.evenrent".equals(srb.get(i).getRegularSettlemethod().getId())){
					RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
					//rpcs.getEqualRentPlanForSpecial(cb, frpbnew, srb.get(i),startList);
				}
				if(srb.get(i).getStartList()<=1&&srb.get(i).getEndList()>=1){
					dateInterval1=String.valueOf(srb.get(i).getRegularMonths()) ;
				}
				if(srb.get(i).getStartList()<=2&&srb.get(i).getEndList()>=2){
					dateInterval2=String.valueOf(srb.get(i).getRegularMonths()) ;
				}
				//不计息
				/*else if("special_regular.nointerest".equals(srb.get(i).getRegular_settlemethod())){
					RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
					rpcs.getNoInterestRentPlanForSpecial(cb, frpb, srb.get(i));
				}*/
				if(i==srb.size()-1){
					method=srb.get(i).getRegularSettlemethod().getId();
				}
			}
			// 加载日期列表
			PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
			List<String> dateList = pdsi.getPlanDateListForSpecial(cb, String.valueOf(frpb.getRentList().size()),srb);
			frpbnew.setPlanDateList(dateList);
			//调整本金 保证净融资额等于本金之和  最后一期本金倒减出来 利息不变
			if(method.equals("special_regular.evencorpus")){
				RentCalculateHelper.adjustLastListCorpus(frpbnew, cb);
			}else if(method.equals("special_regular.evenrent")){
				RentCalculateHelper.adjustLastListInterest(frpbnew, cb);
			}
			
			// 租金计划刷新数据库
			RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
			rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb,startList);
			rpcd.addRentPlan(tcb.getRentPlan_tb(), frpbnew, tcb, startList);
			
		}
		return frpbnew;
	}


	@Override
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,
			List<FundPlanBean> fundPlanList, List<FundPutPlan> fpps,List<SpecialRulesBean> srb, List<String>... planList) throws Exception {
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
				
				ConditionBean newcb=cb;//备份
				RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
				//找出最后一期的租金测算方式
				String method="";
				int index=0;
				boolean flag=true;// 期初/期末是否生效 标记位
				for(int i=0;i<srb.size();i++){
					if(srb.get(i).getStartList()<startList){
						continue;
					}
					//指定租金
					if("special_regular.knowingrent".equals(srb.get(i).getRegularSettlemethod().getId())){
						RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
						rpcs.getKnowingRentPlanForSpecial(newcb, frpb, srb.get(i));
						flag=false;
					}
					//指定本金
					else if("special_regular.knowingcorpus".equals(srb.get(i).getRegularSettlemethod().getId())){
						RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
						rpcs.getKnowingCorpusPlanForSpecial(newcb, frpb, srb.get(i));
						flag=false;
					}
					//等额本金
					else if("special_regular.evencorpus".equals(srb.get(i).getRegularSettlemethod().getId())){
						EvenCorpusCaleServiceImpl rcs = new EvenCorpusCaleServiceImpl();
						rcs.rentPlanCalByConditionAndTabForSpecial(newcb, tcb, startList, frpb, null,srb.get(i));
						flag=false;
					}
					//等额租金
					else if("special_regular.evenrent".equals(srb.get(i).getRegularSettlemethod().getId())){
						RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
						if(!flag){
							newcb.setPeriodType("0");
						}
						rpcs.getEqualRentPlanForSpecial(newcb, frpb, srb.get(i));
						flag=false;
					}
					//宽限期处理
					/*else{
						PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
						newcb.setIncomeNumberYear(String.valueOf(12/srb.get(i).getRegularMonths()));//年还租次数
						newcb.setGracerate(srb.get(i).getYearRate());
						List<String> beforeInterestDateList=pdsi.getBeforeInterestDateList(newcb);
						RentCalculateHelper.addBeforeInterest(frpb, newcb, fpps,beforeInterestDateList);
						index=srb.get(i).getEndList();
						newcb=cb;
						newcb.setRemainLeaseTerm(newcb.getRemainLeaseTerm()-srb.get(i).getRegularMonths()*(srb.get(i).getEndList()-srb.get(i).getStartList()+1));//剩余租赁
						flag=true;
					}*/
					if(i==srb.size()-1){
						method=srb.get(i).getRegularSettlemethod().getId();
					}
				}
				// 加载日期列表
				PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
				List<String> dateList = pdsi.getPlanDateListForSpecial(cb, String.valueOf(frpb.getRentList().size()),srb);
				dateList=dateList.subList(0, dateList.size()-index);
				frpb.getPlanDateList().addAll(dateList);
				//添加宽限期（特殊规则不支持）
				//调整本金 保证净融资额等于本金之和  最后一期本金倒减出来 利息不变
				if(method.equals("special_regular.evencorpus")){
					RentCalculateHelper.adjustLastListCorpus(frpb, cb);
				}else if(method.equals("special_regular.evenrent")){
					RentCalculateHelper.adjustLastListInterest(frpb, cb);
				}else if(method.equals("special_regular.knowingrent")){
					RentCalculateHelper.adjustLastListInterest(frpb, cb);
				}
				//调整最后一期剩余本金为0
				List<String> corpusoveragebusiness = frpb.getCorpusOverageBusinessList();
				List<String> corpusoveragefinince = frpb.getCorpusOverageFinacList();
				int size1 = corpusoveragebusiness.size();
				int size2 = corpusoveragefinince.size();
				if(size1>0){
					corpusoveragebusiness.set(size1-1, "0");
				}
				if(size2>0){
					corpusoveragefinince.set(size2-1, "0");
				}
				frpb.setCorpusOverageBusinessList(corpusoveragebusiness);
				frpb.setCorpusOverageFinacList(corpusoveragefinince);
				rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb,startList);
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
			e.printStackTrace();
		}
		return re_ht;
	}


	@Override
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb,
			String calType, String userName, String[] rentAdjustList,
			List<FundPlanBean> fundPlanList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
