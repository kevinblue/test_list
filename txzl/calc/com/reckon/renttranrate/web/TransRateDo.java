package com.reckon.renttranrate.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.SpecialRuleBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.utils.Scale;
import com.reckon.commons.helper.GrossProfitCalculator;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.FundPlanDAOImpl;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.entity.contract.reckon.cash.CashHelp;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractSpecialRulesBean;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContract;
import com.reckon.entity.interest.FundStandardInterest;
import com.reckon.rentcalc.service.impl.pub.FundFundChargeServiceImpl;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.renttranrate.service.TransRateService;
import com.reckon.renttranrate.service.impl.PmtNextDayServiceImpl;
import com.reckon.renttranrate.service.impl.PmtNextListServiceImpl;
import com.reckon.renttranrate.service.impl.PmtNextMonthServiceImpl;
import com.reckon.renttranrate.service.impl.PmtNextYearServiceImpl;
import com.reckon.renttranrate.service.impl.QuotaTransRateServiceImpl;
import com.reckon.service.RentCalculateService;
import com.reckon.service.impl.RentCalculateServiceImpl;
import com.reckon.util.DateUtils;
import com.reckon.util.DictTools;
import com.reckon.util.IrrTools;
import com.reckon.util.TbBeanTools;
import com.reckon.util.tools.DateTools;
import com.reckon.util.tools.NumTools;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;

/**
 * @author MHY QQ:648020894
 */
public class TransRateDo {
	static Logger logger = Logger.getLogger(TransRateDo.class);
	
	
	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		a.add("0");
		a.add("1");
		a.add("2");
		a.add("3");
		a.add("4");
		System.out.println(a.subList(0, 2).get(0));
		System.out.println(a.subList(0, 2).size());
		System.out.println(a.size());
	}

	/**
	 * 
	 * 调息处理入口
	 * 
	 * @param adjustId
	 * @param contractInfoId
	 * @param creator
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, String> processTransRate(String adjustId, String contractInfoId, String contractId, String creator, String docId,String adjusttype) throws Exception {
		logger.info("开始进行调息处理....");
		
		FundPlanDAOImpl fundPlanDAO = new FundPlanDAOImpl();
		// 查询该合同的商务条件信息
		ConditionBean cb = fundPlanDAO.findContractCondition(contractInfoId);
		cb.setContractId(contractId);
		//cb.setTheoryFirstPlanDate("");第一笔租金调整利息？
		cb.setDocId(docId);
		cb.setProjId(contractId);
		cb.setCreator(creator);
		cb.setModificator(creator);
		cb.setCreateDate(DateTools.getSystemDateTime());
		cb.setModifyDate(DateTools.getSystemDateTime());
		DictTools.getReversDict(cb);

		// 查询租金计划*********正式表/before
		String renttabName = null;
		if("onhire".equals(adjusttype)){
			renttabName = "CONTRACT_FUND_RENT_PLAN";
		}else{
			//***租金计划表更换标记***
			//renttabName = "CONTRACT_FUND_RENT_PLAN_BEFORE";
			renttabName = "CONTRACT_FUND_RENT_PLAN";
		}
		FundRentPlanBean oldRentPlanContext = fundPlanDAO.findContractFundRentPlanList(contractInfoId,renttabName);
		oldRentPlanContext.setBusinessId(contractInfoId);// CONTRACT_INFO.ID
		
		oldRentPlanContext.setContractId(contractId);// CONTRACT_INFO.CONTRACT_ID
		oldRentPlanContext.setDocId(docId);
		oldRentPlanContext.setCreator(creator);
		oldRentPlanContext.setModificator(creator);
		oldRentPlanContext.setCreateDate(DateTools.getSystemDateTime());
		oldRentPlanContext.setModifyDate(DateTools.getSystemDateTime());
		
		//分段规则
		TableService tableService = (TableService)WebUtil.getApplicationContext().getBean("tableService");
		//特殊規則-->按照起始期次进行排序（年利率不同）
		List<ContractSpecialRulesBean> srbList=tableService.findResultsByHSQL("from ContractSpecialRulesBean where contractId = '"+ contractInfoId+"' order by startList");
		//宽限期收款计划(為租金首付計劃，不是租金計劃)
		List<ContractFundFundPlan> rbicblist =tableService.findResultsByHSQL("from ContractFundFundPlan where contractId = '"+contractInfoId+"' and feeType ='feetype9'");
		
		//查询原资金收付计划
		String fundsql = " FROM  ContractFundFundPlan where contractId = '"+contractInfoId+"' order by planDate ";
		List<ContractFundFundPlan> fundFundPlanList = tableService.findResultsByHSQL(fundsql);
		// 根据调息id获得选定的央行调息记录
		Hashtable<String, String> PBOCInfo = fundPlanDAO.getStandInfoById(adjustId);
	
		// 根据调息方式得到合同重新计算利息的起始日期*********《添加次日》
		String newDate = TransRateHelper.getNewDateByAdjustType(cb.getAdjustStyle(), PBOCInfo.get("start_date").toString(),cb.getStartDate());
		//计算调息开始期项
		int startList = TransRateHelper.getStartListFromRentPlan(oldRentPlanContext.getPlanDateList(), newDate);
		//注释
//		if(cb.getStartTransRateDate() != null && cb.getStartTransRateDate().length() > 0){
//		//注释 int startListNew = TransRateHelper.getStartListFromRentPlan(oldRentPlanContext.getPlanDateList(), cb.getStartTransRateDate());
//			int startListNew = TransRateHelper.getStartListFromRentPlan(oldRentPlanContext.getPlanDateList(), cb.getStartTransRateDate());
//			if(startList > 0 && startListNew > 0){
//				startList = Math.max(startList, startListNew);
//			}
//		}
		logger.info("调息开始期项...." + startList);
		if("next_list".equals(cb.getAdjustStyle())){
			if(startList != 0){
				startList++;
			}
		}
		//--------------------------------------------------
		//只取最新的央行调息记录
		List<FundStandardInterest> fsiList=tableService.findResultsByHSQL("from FundStandardInterest where startDate= (select max(startDate) from FundStandardInterest )");;
		FundStandardInterest fsi=null;
		if(fsiList.size()>0){
			fsi= fsiList.get(0);
		}
		
		//判断从起始日期开始的利率是否相等 不相等的话不可以调息
		boolean isRateEven = TransRateHelper.isRateEvenFromStartList(oldRentPlanContext.getYearRateList(), startList);
		//查询合同调息开始期项后回笼的租金
		String rentIncome = fundPlanDAO.findRentIncomeByRentList(contractInfoId, startList);
		String msgs = "";
		if (startList == 0) {// 开始日期参数是否正确
			msgs += "\\n合同号:" + contractId + "不满足调息规则!\\n调息日期不在合同租赁期限内.\\n未进行调息.";
		} else if (!isRateEven) {// 起始期数之后的利率是否一致
			msgs += "\\n合同号:" + contractId + "不满足调息规则!\\n调息开始期项之后的期数中利率不相同.\\n未进行调息.";
		} else if ("next_list".equals(cb.getAdjustStyle()) && startList > cb.getIncomeNumber()) {// 次期时
			msgs += "\\n合同号:" + contractId + "不满足调息规则!\\n次期调息合同且调息开始期数为合同最后一期.\\n未进行调息.";
		} else if ("fixed".equals(cb.getAdjustStyle())) {
			msgs += "\\n合同号:" + contractId + "不满足调息规则!\\n合同商务条件为固定不调息合同.\\n未进行调息.";
		} else if(new BigDecimal(rentIncome).compareTo(BigDecimal.ZERO)==1){//合同调息开始期项后回笼的租金大于0不允许调息
			msgs += "\\n合同号:" + contractId + "不满足调息规则!\\n调息开始期项之后回笼过租金.\\n未进行调息.";
		}
		
		logger.info("进行合同是否可以此次调息的判断....." + msgs);

		Hashtable<String, String> resultMap = new Hashtable<String, String>();
		if (msgs.length() > 0) {
			resultMap.put("message", msgs);
			resultMap.put("isSucess", "false");
		} else {
			
			logger.info("起始期数后年利率是否相等...." + isRateEven);
			String oldYearRate = "";
			if(null!=oldRentPlanContext.getYearRateList()&&oldRentPlanContext.getYearRateList().size()>0){
				//*************************************rbicblist可能要更改！
				oldYearRate = oldRentPlanContext.getYearRateList().get((startList- 1)<0?0:(startList - 1));
			}else{
				oldYearRate=cb.getYearRate();
			}
			logger.info("调息旧年利率...." + oldYearRate);
			//String newIRR = new BigDecimal(rateFloat).add(new BigDecimal(cb.getIrr())).toString();
			List<String> newYearRateList = TransRateHelper.getNewRate(cb,fsi,srbList);
			String newYearRate = new BigDecimal(newYearRateList.get(newYearRateList.size()-1)).toString();
			logger.info("调息新年利率...." + newYearRateList);

			// 清空原来的临时流程数据
			fundPlanDAO.cleanContractConditionTemp(docId, contractId);
			fundPlanDAO.cleanContractRentPlanTemp(docId, contractId);
			fundPlanDAO.cleanContractCashDetailTemp(docId, contractId);
			String sql = "";
			List<FundAdjustInterestContract> faicList = new ArrayList<FundAdjustInterestContract>();
			String oldIrr = "";
			sql = " FROM  FundAdjustInterestContract where contractId = '"+ contractInfoId +"' AND modReason = 'his_rate_change' and status='rate_change_in' order by createDate desc ";
			faicList = tableService.findResultsByHSQL(sql);
			if(faicList!=null && faicList.size()>0){
				oldIrr = faicList.get(0).getNewIrr().toString();	//上次IRR
			}
			//logger.info("oldIrr="+oldIrr);
			if("".equals(oldIrr)){
				oldIrr = cb.getIrr();
			}
			// 调息上下文信息
			InterContBean icb = new InterContBean();
			icb.setContractId(contractId);
			icb.setAdjustId(adjustId);
			icb.setStartList(startList);
			icb.setOldYearRate(oldYearRate);
			icb.setNewYearRateList(newYearRateList);
			icb.setCreator(creator);
			//*********************次期是否要改变newdate？？
			icb.setAdjustDate(newDate);
			icb.setDocId(docId);
			icb.setOldIrr(oldIrr);
			//icb.setNewIrr(newIRR);
			//icb.setOldPlanIrr(cb.getPlanIrr());//planIrr还需要
			
			String cashql = " FROM  CashHelp where cashDate >= '"+cb.getStartDate()+"' AND cashDate <= '"+cb.getEndDate().replaceAll(" ", "")+"' order by cashDate ";
			List<CashHelp> cashHelpList = tableService.findResultsByHSQL(cashql);
			
			List<SpecialRuleBean> srb= RentCalculateHelper.getSpecialRulesList(srbList);
			List<FundPlanBean> fundPlanBeans = fundPlanDAO.findFundPlanList(fundFundPlanList);
			// 调息
			processPmtTranRate(cb, oldRentPlanContext, icb,fundPlanBeans,srb);

			// 保存临时租金计划到临时表
			fundPlanDAO.saveRentPlanTempList(oldRentPlanContext);
			
			// 得到操作表信息
			TabCalBean tcb = TbBeanTools.getTabInfo("cont_process", cb);
			
			// 查询资金收付计划
			List<FundPlanBean> fundPlanList = fundPlanDAO.findFundPlanList(contractInfoId);
			// 查询不包括租前息的收付计划
			List<FundPlanBean> fundPlanWithoutBeforeInterestList = fundPlanDAO.findFundPlanExcludeInterestList(contractInfoId);
			// 查询租前息,且按照payment_id进行排序
			List<FundPlanBean> beforeInterestList = fundPlanDAO.findBeforeInterestList(contractInfoId);
			//***********************更改租前息！！！
			if(beforeInterestList!=null&&beforeInterestList.size()>0){
				adjustBeforeInterest(beforeInterestList,cb,fsi,newDate);
			}
			if(fundPlanList.size()>0 ){
				//向资金计划临时表当中插入数据(起租前、起租后都入保存入临时表，调息结束之后都从临时入正式，方便调息回滚。)
				FundFundChargeServiceImpl  ffc = new FundFundChargeServiceImpl();
				ffc.addFundFundForAdjustInterestTemp(fundPlanWithoutBeforeInterestList,beforeInterestList, cb, tcb);
			}
			
			//修改现金流的生成规则，按照租金计划和资金计划生成
			IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
			idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
			idd.addCashDetails(tcb, cb);
			
			
			String hsql = "";
			String process = tcb.getCalType();
			List rentPlanList = null;
			List cashDetailList = null;
			Map<String, Object> queryParams = new HashMap<String, Object>();
			queryParams.put("docId", cb.getDocId());
			queryParams.put("contractId", cb.getProjId());
			rentPlanList = tableService.findEntityByProperties(ContractFundRentPlanTemp.class, queryParams);
			
			hsql = " FROM  ContractCashDetailTemp where contractId = '"+cb.getProjId()+"' AND docId = '"+cb.getDocId()+"' order by planDate ";
			cashDetailList = tableService.findResultsByHSQL(hsql);
			logger.info("现金流大小==》"+ cashDetailList.size());
			Collections.sort(rentPlanList);
			Collections.sort(cashDetailList);
			
			// 计算现金流实际IRR
			List<String> cashDetail =new ArrayList<String>();
			for(int i=0;i<cashDetailList.size();i++){
				ContractCashDetailTemp temp = (ContractCashDetailTemp)cashDetailList.get(i);
				cashDetail.add(temp.getNetFlow().toString());
			}
			String realIrr = IrrTools.getIRRByNewton(cashDetail, cb.getIncomeNumberYear());
			icb.setNewIrr(realIrr);
			
			RentCalculateServiceImpl rcs = new RentCalculateServiceImpl();
			//BigDecimal grossProfit = GrossProfitCalculator.reckonGrossProfit(cb, oldRentPlanContext, fundPlanList, PBOCInfo);
			BigDecimal grossProfit = rcs.getGrossProfit(rentPlanList, cb, process);
			cb.setGrossProfit(grossProfit);
			// 保存临时商务条件
			
			//cb.setPlanIrr(planIrr);//planIrr不需要
			resultMap.put("oldIrr", cb.getIrr());
			cb.setIrr(realIrr);
			cb.setCreator(creator);
			cb.setModificator(creator);
			cb.setCreateDate(DateTools.getSystemDateTime());
			cb.setModifyDate(DateTools.getSystemDateTime());
			DictTools.getPersiDict(cb);
			fundPlanDAO.addContractConditionTemp(cb);
			
			resultMap.put("newIrr", realIrr);
			//resultMap.put("newPlanIrr", planIrr);
			resultMap.put("startList", "" + startList);
			resultMap.put("oldRate", oldYearRate==""?cb.getYearRate():oldYearRate);
			resultMap.put("newRate", newYearRate);
			//resultMap.put("oldPlanIrr", oldPlanIrr);
			resultMap.put("adjustDate", newDate);
			resultMap.put("message", "调息处理成功!");
			resultMap.put("isOver", "true");
			resultMap.put("isSucess", "true");
		}
		return resultMap;
	}

	/**
	 * 起租前调息时，调整租前息！
	 * @param fundPlanList(只有租前息的资金收付计划)
	 * @param fsi 
	 * @param newDate
	 * @throws ParseException 
	 */
	private void adjustBeforeInterest(List<FundPlanBean> beforeInterestList,ConditionBean cb, FundStandardInterest fsi, String newDate) throws ParseException {
		//获取央行调息的基准利率
		BigDecimal baserate = getBaseRate(cb.getLeaseTerm(),fsi);
		
		//新的调息利率与原利率差值
		BigDecimal newrate ;
		if("add".equals(cb.getRateFloatType())){//加点
			newrate = baserate.add(new BigDecimal(cb.getGraceadjust()==null?"0":cb.getGraceadjust()));
		}else{//浮动上调
			newrate = baserate.multiply(new BigDecimal(cb.getGraceadjust()==null?"0":cb.getGraceadjust()).add(new BigDecimal(1)));
		}
		BigDecimal newrateadjust = newrate.subtract(new BigDecimal(cb.getGracerate()==null?"0":cb.getGracerate()));
		//新的日利率调整值360/365????????????????????
		BigDecimal newdayrateadjust = newrateadjust.divide(new BigDecimal(36000),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP);
		BigDecimal cleanLeaseMoney = new BigDecimal(cb.getCleanLeaseMoney());//净融资额
		/**
		 * 1.次期：调息日一定在租前息全部收完之后，租前息不变
		 * 2.次月/次日/次年:调息日则完全可能在租前息收取范围内
		 */
		int startlist;//租前息调息开始期次
		if("next_list".equals(cb.getAdjustStyle())){
			return ;
		}else{
			List<String> dateList = new ArrayList<String>();
			for(FundPlanBean bi:beforeInterestList){
				dateList.add(bi.getPlanDate());
			}
			startlist = TransRateHelper.getStartListFromRentPlan(dateList, newDate);
			if(startlist==0){//调息时间在租前息全部收完之后，则直接返回
				return ;
			}
		}
		for(int i=startlist-1;i<beforeInterestList.size();i++){
			int diffdays;
			if(i==startlist-1){
//				newDate="";
				//调息开始第一期特殊处理，如果newdate在leaseamtdate之前，获取leaseamtdate和第一期调息的plandate之间的天数差,否则获取newdate和第一期调息的plandate之间的天数差,
				if( DateUtils.getDiffDays(newDate,cb.getLeaseAmtDate())>0){
					diffdays =  DateUtils.getDiffDays(cb.getLeaseAmtDate(),beforeInterestList.get(i).getPlanDate());
				}else{
					diffdays =  DateUtils.getDiffDays(newDate,beforeInterestList.get(i).getPlanDate());
				}
			}else{
				//获取相邻两期之间的天数差
				diffdays = DateUtils.getDiffDays(beforeInterestList.get(i-1).getPlanDate(),beforeInterestList.get(i).getPlanDate());
			}
			BigDecimal adjustinterest = cleanLeaseMoney.multiply(new BigDecimal(diffdays)).multiply(newdayrateadjust);
			beforeInterestList.get(i).setPlanMoney(adjustinterest.add(new BigDecimal(beforeInterestList.get(i).getPlanMoney())).toString());
		}
		
	}
	/**
	 * 获取央行调息的基准利率
	 * @param leaseTermValue
	 * @param fsi
	 * @return
	 */
	private BigDecimal getBaseRate(int leaseTermValue, FundStandardInterest fsi){
		BigDecimal rateBase = null;
		if (leaseTermValue <= 6) {// 六个月以内
			rateBase = fsi.getBaseRateHalf();
		} else if (leaseTermValue <= 12) {// 一年以内
			rateBase = fsi.getBaseRateOne();
		} else if (leaseTermValue <= 36) {// 二年到三年之间时
			rateBase = fsi.getBaseRateThree();
		} else if (leaseTermValue <= 60) {// 四，五年之间时
			rateBase = fsi.getBaseRateFive();
		} else {// 五年以上时
			rateBase = fsi.getBaseRateAbovefive();
		}
		return rateBase;
	}

	private static void processPmtTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,List<FundPlanBean> fundPlanBeans,List<SpecialRuleBean> srb) throws Exception {
		TransRateService rtrs = null;
		if("quota".equals(cb.getRateFloatType())){//定额调息
			rtrs = new QuotaTransRateServiceImpl();
		} else if ("next_day".equals(cb.getAdjustStyle())) {// 次日
			rtrs = new PmtNextDayServiceImpl();
		} else if ("next_month".equals(cb.getAdjustStyle())) {// 次月
			rtrs = new PmtNextMonthServiceImpl();
		} else if ("next_year".equals(cb.getAdjustStyle())) {// 次年
			rtrs = new PmtNextYearServiceImpl();
		} else if ("next_list".equals(cb.getAdjustStyle())) {// 次期
			rtrs = new PmtNextListServiceImpl();
		}
		rtrs.processPmtTranRate(cb, oldRentPlanContext, icb,srb);
	}
}
