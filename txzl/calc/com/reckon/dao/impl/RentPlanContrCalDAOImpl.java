package com.reckon.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.utils.Scale;
import com.reckon.dao.Conn;
import com.reckon.util.DictTools;
import com.reckon.util.NumberUtils;
import com.reckon.util.TbBeanTools;
import com.reckon.util.Tools;
import com.reckon.util.tools.DateTools;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.UUIDUtil;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc  ( 公用的租金计划dao处理类,合同的)
 */

public class RentPlanContrCalDAOImpl {

	public static Logger logger = Logger.getLogger(RentPlanContrCalDAOImpl.class);
	
	/**
	 * 
	 *  (  根据表信息，合同租金计划信息 添加合同租金计划信息)
	 * 
	 * @param fpb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public boolean addRentPlan(String tbName, FundRentPlanBean fpb, TabCalBean tcb, int startList,ConditionBean cb) throws Exception {
		String sql = "";
		Conn conn = new Conn();

		if (fpb != null && fpb.getRentList() != null && fpb.getRentList().size() > 0) {
			List<String> rent_list = fpb.getRentList();// 租金列表

			List<String> planDate_list = fpb.getPlanDateList();// 日期列表
			if(cb!= null && cb.getLastPlanDate() != null){//不规则测算跳过
				/*if(!"irregular_rent".equals(cb.getSettleMethod())&&!"excel_import".equals(cb.getSettleMethod())){
					int index = rent_list.size() -1;
					planDate_list.set(index, cb.getLastPlanDate());
				}*/
			}
			List<String> corpus_list = fpb.getCorpusBusinessList();// 本金列表
			List<String> corpusOverage_list = fpb.getCorpusOverageBusinessList();// 本金余额列表
			List<String> interest_list = fpb.getInterestBusinessList();// 利息列表
			List<String> year_rate_list = new ArrayList<String>();
			List<String> rent_adjust = fpb.getRentAdjustList();// 租金调整列
			List<String> rent_remain = fpb.getAllRemainRentList();// 租金调整列
			List<String> cautionList = fpb.getCautionmoneyRemainList();

			int flag = 1;// 是否添加租金调整列
			if (null == rent_adjust || (null != rent_adjust && rent_adjust.size() == 0) || rent_adjust.get(0) == null) {
				flag = 0;
			}
			if (fpb.getYearRateList() == null || (null != fpb.getYearRateList() && fpb.getYearRateList().size() == 0)) {
				for (int i = 0; i < fpb.getRentList().size(); i++) {
					year_rate_list.add(fpb.getYearRate());
				}
			} else {
				if (fpb.getYearRateList().size() != fpb.getRentList().size()) {
					for (int i = fpb.getYearRateList().size() - 1; i >= startList - 1; i--) {
						year_rate_list.add(0, fpb.getYearRateList().get(i));
					}
				} else {
					year_rate_list = fpb.getYearRateList();
				}

			}
			// Column_1和Column_2 是均息法下的均息法本息,暂时只有在均息法的测算中会处理,
			// 所以其他的地方直接赋值本息
			fpb.setColumn_1(fpb.getCorpusBusinessList());
			fpb.setColumn_2(fpb.getInterestBusinessList());
			for (int i = 0; i < rent_list.size(); i++) {

				sql += " INSERT INTO " + tbName + " (";
				sql += " id,";
				sql += " doc_id";
				sql += "," + tcb.getContOrProjCName() + "";
				sql += ",rent_list";
				sql += ",plan_date";
				sql += ",rent";
				sql += ",all_remain_rent";
				sql += ",corpus";
				sql += ",year_rate";
				sql += ",interest";
				sql += ",corpus_overage";
				if(cb.getIsFactory()){
					sql += ",cautionmoney_remain";
				}
				if (!tbName.contains("fina")) {
					sql += ",CORPUS_BUSINESS";
					sql += ",INTEREST_BUSINESS";
				}
				sql += ",quot_id";
				sql += ",onhire_id";
				if (flag > 0) {
					sql += ",rent_adjust";
				}
				sql += ") VALUES (";
				sql += "'" + UUIDUtil.getUUID() + "',";
				sql += "'" + tcb.getDocId() + "',";
				sql += "'" + tcb.getContOrProjCValue() + "',";
				sql += startList + ",";
				sql += "'" +Tools.getDBDateStr( planDate_list.get(i) ) + "',";
				sql +=NumberUtils.nullToZero( rent_list.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( rent_remain.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( corpus_list.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( year_rate_list.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( interest_list.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( corpusOverage_list.get(i) ) + ",";
				if(cb.getIsFactory()){
					sql +=NumberUtils.nullToZero( cautionList.get(i) ) + ",";
				}
				if (!tbName.contains("fina")) {
					sql +=  fpb.getColumn_1().get(i) + ",";
					sql +=  fpb.getColumn_2().get(i) + ",";
				}
				sql += "'" + fpb.getQuotId() + "', ";
				sql += "'" + tcb.getOnHire_id() + "' ";
				if (flag > 0) {
					sql += "," + (rent_adjust.get(i).equals("") ? "null" : rent_adjust.get(i));
				}
				sql += ") ";

				startList = startList + 1;
				System.out.println(sql);
				conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "|=====|table:" + tbName + "添加租金计划语句...");
				sql = "";
			}
		}

		// 如果是多次起租,那么合同表租金计划要做汇总
		if ("onHire_more_process".equals(tcb.getCalType())) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process");
			tcb_cont.setOnHire_id(tcb.getOnHire_id());
			tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
			tcb_cont.setDocId(tcb.getDocId());
			tcb_cont.setIs_merger(tcb.getIs_merger());// 传递是否合并计算
			// 判断是操作合同租金计划还是会计租金计划
			if (tcb.getRentPlan_tb().equals(tbName)) {
				logger.info("业务租金计划表的合并");
				return insertRentPlanOnHire(tcb_cont, fpb.getPlanDateList().get(0).toString());
			}
			// 如果是合并计算那么会计租金计划会重算不需要合并
			if (tcb.getRentFinaPlan_tb().equals(tbName)) {
				logger.info("会计租金计划表的合并");
				return insertFinaRentPlanOnHire(tcb_cont, fpb.getPlanDateList().get(0).toString());
			}

		}
		return true;
	}

	/**
	 * 
	 *  (  根据表信息，合同租金计划信息 添加合同租金计划信息)
	 * 
	 * @param fpb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public boolean addRentPlan(String tbName, FundRentPlanBean fpb, TabCalBean tcb, int startList) throws Exception {
		String sql = "";
		Conn conn = new Conn();

		if (fpb != null && fpb.getRentList() != null && fpb.getRentList().size() > 0) {
			List<String> rent_list = fpb.getRentList();// 租金列表

			List<String> planDate_list = fpb.getPlanDateList();// 日期列表
			List<String> corpus_list = fpb.getCorpusBusinessList();// 本金列表
			List<String> corpusOverage_list = fpb.getCorpusOverageBusinessList();// 本金余额列表
			List<String> interest_list = fpb.getInterestBusinessList();// 利息列表
			List<String> year_rate_list = new ArrayList<String>();
			List<String> rent_adjust = fpb.getRentAdjustList();// 租金调整列
			List<String> rent_remain = fpb.getAllRemainRentList();// 租金调整列

			int flag = 1;// 是否添加租金调整列
			if (null == rent_adjust || (null != rent_adjust && rent_adjust.size() == 0) || rent_adjust.get(0) == null) {
				flag = 0;
			}
			if (fpb.getYearRateList() == null || (null != fpb.getYearRateList() && fpb.getYearRateList().size() == 0)) {
				for (int i = 0; i < fpb.getRentList().size(); i++) {
					year_rate_list.add(fpb.getYearRate());
				}
			} else {
				if (fpb.getYearRateList().size() != fpb.getRentList().size()) {
					for (int i = fpb.getYearRateList().size() - 1; i >= startList - 1; i--) {
						year_rate_list.add(0, fpb.getYearRateList().get(i));
					}
				} else {
					year_rate_list = fpb.getYearRateList();
				}

			}
			// Column_1和Column_2 是均息法下的均息法本息,暂时只有在均息法的测算中会处理,
			// 所以其他的地方直接赋值本息
			if (fpb.getColumn_1() == null || fpb.getColumn_1().size() == 0) {
				fpb.setColumn_1(fpb.getCorpusBusinessList());
				fpb.setColumn_2(fpb.getInterestBusinessList());
			}
			for (int i = 0; i < rent_list.size(); i++) {

				sql += " INSERT INTO " + tbName + " (";
				sql += " id,";
				sql += " doc_id";
				sql += "," + tcb.getContOrProjCName() + "";
				sql += ",rent_list";
				sql += ",plan_date";
				sql += ",rent";
				sql += ",all_remain_rent";
				sql += ",corpus";
				sql += ",year_rate";
				sql += ",interest";
				sql += ",corpus_overage";
				if (!tbName.contains("fina")) {
					sql += ",CORPUS_BUSINESS";
					sql += ",INTEREST_BUSINESS";
				}
				sql += ",quot_id";
				sql += ",onhire_id";
				if (flag > 0) {
					sql += ",rent_adjust";
				}
				sql += ") VALUES (";
				sql += "'" + UUIDUtil.getUUID() + "',";
				sql += "'" + tcb.getDocId() + "',";
				sql += "'" + tcb.getContOrProjCValue() + "',";
				sql += startList + ",";
				sql += "'" +Tools.getDBDateStr( planDate_list.get(i) ) + "',";
				sql +=NumberUtils.nullToZero( rent_list.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( rent_remain.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( corpus_list.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( year_rate_list.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( interest_list.get(i) ) + ",";
				sql +=NumberUtils.nullToZero( corpusOverage_list.get(i) ) + ",";

				if (!tbName.contains("fina")) {
					sql +=  fpb.getColumn_1().get(i) + ",";
					sql +=  fpb.getColumn_2().get(i) + ",";
				}
				sql += "'" + fpb.getQuotId() + "', ";
				sql += "'" + tcb.getOnHire_id() + "' ";
				if (flag > 0) {
					sql += "," + (rent_adjust.get(i).equals("") ? "null" : rent_adjust.get(i));
				}
				sql += ") ";

				startList = startList + 1;
				System.out.println(sql);
				conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "|=====|table:" + tbName + "添加租金计划语句...");
				sql = "";
			}
		}

		// 如果是多次起租,那么合同表租金计划要做汇总
		if ("onHire_more_process".equals(tcb.getCalType())) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process");
			tcb_cont.setOnHire_id(tcb.getOnHire_id());
			tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
			tcb_cont.setDocId(tcb.getDocId());
			tcb_cont.setIs_merger(tcb.getIs_merger());// 传递是否合并计算
			// 判断是操作合同租金计划还是会计租金计划
			if (tcb.getRentPlan_tb().equals(tbName)) {
				logger.info("业务租金计划表的合并");
				return insertRentPlanOnHire(tcb_cont, fpb.getPlanDateList().get(0).toString());
			}
			// 如果是合并计算那么会计租金计划会重算不需要合并
			if (tcb.getRentFinaPlan_tb().equals(tbName)) {
				logger.info("会计租金计划表的合并");
				return insertFinaRentPlanOnHire(tcb_cont, fpb.getPlanDateList().get(0).toString());
			}

		}
		return true;
	}
	
	public boolean addRentPlanByJsonStr( TabCalBean tcb,String fundRentPlanStr ) throws Exception {
		String sql = "";
		Conn conn = new Conn();
		ObjectMapper mapper = new ObjectMapper();
		Map[] rentPlans = mapper.readValue(fundRentPlanStr,Map[].class);
		String tbName = tcb.getRentPlan_tb();
		for(Map rentPlan : rentPlans){
			sql += " INSERT INTO " + tbName + " (";
			sql += " id,";
			sql += " doc_id";
			sql += "," + tcb.getContOrProjCName() + "";
			sql += ",rent_list";
			sql += ",plan_date";
			sql += ",rent";
			sql += ",corpus";
			sql += ",year_rate";
			sql += ",interest";
			sql += ") VALUES (";
			sql += "'" + UUIDUtil.getUUID() + "',";
			sql += "'" + tcb.getDocId() + "',";
			sql += "'" + tcb.getContOrProjCValue() + "',";
			sql += "'" +rentPlan.get("rentlist")  + "',";
			sql +="'"+rentPlan.get("plandate") + "','";
			sql +=rentPlan.get("rent") + "','";
			sql +=rentPlan.get("corpus")+ "',";
			sql +=rentPlan.get("yearrate")+ ",'";
			sql +=rentPlan.get("interest") + "'";
			sql += ") ";
			logger.info(sql);
			conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "|=====|table:" + tbName + "添加租金计划语句...");
			sql = "";
		}
		return false;
	}
	
	/**
	 * 
	 *  (  根据表表信息对租金计划表进行删除操作)
	 * 
	 * @param tcb
	 * @param startList开始期项
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRentPlan(String tbName, TabCalBean tcb, int startList) throws Exception {
		Conn conn = new Conn();
		String sql = " delete " + tbName + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "' and rent_list>=" + startList;
		// 报价编号
		if (tcb.getOnHire_id() != null && !"".equals(tcb.getOnHire_id())) {
			sql += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		// logger.info("删除租金计划sql：" + sql);
		conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "删除租金计划...");
		logger.info("删除租金计划成功....");
		// 如果是多次起租,那么合同表也要做同样的操作
		/*
		 * if(tcb.getCalType().equals("onHire_more_process")){ TabCalBean
		 * tcb_cont=new TabCalBean();
		 * tcb_cont=TbBeanTools.getTabInfo("cont_process");
		 * tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
		 * tcb_cont.setDocIdCoulValue(tcb.getDocIdCoulValue());
		 * deleteRentPlan(tcb_cont.getRentPlan_tb(), tcb_cont, startList);
		 * return true; }
		 */
		return true;
	}

	/**
	 * 
	 *  (  根据表信息，从数据库中读取租金计划信息，合同的)
	 * 
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean getRentAndDateByTcb(TabCalBean tcb, int startList) throws Exception {
		//  Auto-generated method stub
		Conn conn = new Conn();
		String sql = " select * from " + tcb.getRentPlan_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue();
		if (tcb.getDocId() != null && !tcb.getDocId().equals("")) {
			sql += "' and doc_id='" + tcb.getDocId();
		}
		sql += "' and rent_list>=" + startList;

		// 报价编号
		String where = "";
		if (tcb.getOnHire_id() != null && !"".equals(tcb.getOnHire_id())) {
			where += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		sql += where + " order by plan_date asc";
		List<Map<String, String>> rs_list = conn.executeQuery(sql, "查询租金计划sql：");
		FundRentPlanBean frpb = new FundRentPlanBean();
		for (Map<String, String> rs : rs_list) {
			frpb.getPlanDateList().add(rs.get("plan_date"));
			frpb.getRentList().add( NumberUtils.nullToZero( rs.get("rent") ) );
			frpb.getCorpusBusinessList().add( NumberUtils.nullToZero( rs.get("corpus") ) );
			frpb.getCorpusOverageBusinessList().add( NumberUtils.nullToZero( rs.get("corpus_overage") ) );
			frpb.getInterestBusinessList().add( NumberUtils.nullToZero( rs.get("interest") ) );
			frpb.getRentAdjustList().add( NumberUtils.nullToZero( rs.get("rent_adjust") ) );
			frpb.getCorpusFinacList().add( NumberUtils.nullToZero( rs.get("corpus_finac") ) );
			frpb.getCorpusOverageFinacList().add( NumberUtils.nullToZero( rs.get("corpus_overage_finac") ) );
			frpb.getInterestFinacList().add( NumberUtils.nullToZero( rs.get("interest_finac") ) );
			frpb.getYearRateList().add( NumberUtils.nullToZero( rs.get("year_rate") ) );
			frpb.getColumn_1().add( NumberUtils.nullToZero( rs.get("corpus_business") ) );
			frpb.getColumn_2().add( NumberUtils.nullToZero( rs.get("interest_business") ) );
			frpb.getAllRemainRentList().add( NumberUtils.nullToZero( rs.get("all_remain_rent") ) );//
		}
		return frpb;
	}

	/**
	 * 
	 *  (  添加租金计划到某张表)
	 * 
	 * @param toTb所要添加数据的表
	 * @param frTb从那张表提取数据
	 * @param wherefrsql源数据库的where条件
	 * @return
	 * @throws Exception
	 */
	public boolean addRentInfoBySql(String toTb, String frTb, String wherefrsql, Hashtable<String, String> colHt) throws Exception {
		// 数据库操作对象
		Conn conn = new Conn();
		String sql = "INSERT INTO " + toTb + " (id,doc_id ";
		sql += ",contract_id";
		sql += ",rent_list";
		sql += ",plan_date";
		sql += ",rent";
		sql += ",all_remain_rent";
		sql += ",rent_adjust";
		sql += ",corpus";
		sql += ",year_rate";
		sql += ",interest";
		sql += ",rent_overage";
		sql += ",corpus_overage";
		sql += ",interest_overage";
		sql += ",corpus_business";
		sql += ",interest_business";
		sql += ",quot_id ";
		sql += ",onhire_id ) ";
		sql += "SELECT '"+UUIDUtil.getUUID()+"','" + colHt.get("doc_id").toString() + "' ";
		sql += ", '" + colHt.get("contract_id").toString() + "'";
		sql += ",rent_list";
		sql += ",plan_date";
		sql += ",rent";
		sql += ",all_remain_rent";
		sql += ",rent_adjust";
		sql += ",corpus";
		sql += ",year_rate";
		sql += ",interest";
		sql += ",rent_overage";
		sql += ",corpus_overage";
		sql += ",interest_overage";
		sql += ",corpus_business";
		sql += ",interest_business";
		sql += ",quot_id";
		sql += ",onhire_id  ";
		sql += " FROM " + frTb + wherefrsql;
		conn.executeUpdate(sql, "添加租金计划 从表" + frTb + "到表" + toTb + " ");
		return true;
	}

	/**
	 * 
	 *  (  现金流明细构建时查询租金计划信息)
	 * 
	 * @param tbName
	 * @param wheresql
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean getRentInfoForCash(String tbName, String wheresql) throws Exception {

		FundRentPlanBean frp = new FundRentPlanBean();
		List<String> planDateList = new ArrayList<String>();
		List<String> planRentList = new ArrayList<String>();
		String sql = " select (case when rent is null then 0 else rent end) rent,plan_date from " + tbName + " where " + wheresql;
		Conn conn = new Conn();
		List<Map<String, String>> rs_list = conn.executeQuery(sql, "租金计划现金流所需数据查询....");
		for (Map<String, String> rs : rs_list) {
			planDateList.add(rs.get("plan_date"));
			planRentList.add(rs.get("rent"));
		}

		frp.setPlanDateList(planDateList);
		frp.setRentList(planRentList);
		// DaoUtil.closeRSOrConn(rs, conn);

		return frp;
	}

	/**
	 * 
	 *  ( 起租时的更新租金计划日期信息)
	 * 
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public boolean updateRentPlanDate(String tbName, TabCalBean tcb, int startList, List<String> dateList) throws Exception {
		Conn conn = new Conn();


		for (int i = 0; i < dateList.size(); i++) {
			String sql = "";
			sql += "  update " + tbName + " set plan_date='" + dateList.get(i).toString() + "' where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "' and rent_list=" + startList;
			// 报价编号
			if (!"".equals(tcb.getOnHire_id())) {
				sql += " and onhire_id='" + tcb.getOnHire_id() + "'";
			}
			startList = startList + 1;
			conn.executeUpdate(sql, tcb.getCalType(), "更新租金计划时间sql：");
		}
		// 如果是多次起租,那么合同表也要做同样的操作
		if (tcb.getCalType().equals("onHire_more_process")) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process");
			tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
			tcb_cont.setDocId(tcb.getDocId());
			if (tcb.getRentPlan_tb().equals(tbName)) {
				tbName = tcb_cont.getRentPlan_tb();
			}
			if (tcb.getRentFinaPlan_tb().equals(tbName)) {
				tbName = tcb_cont.getRentFinaPlan_tb();
			}
			updateRentPlanDate(tbName, tcb_cont, startList, dateList);
			return true;
		}
		return true;
	}

	/**
	 * 业务租金计划合并 多次起租时合并插入 在这里做的是字段相加,如果字段类型改变会导致相加之和数据有误 多次起租合并测算时
	 * 
	 * @param tcb
	 * @param star_date
	 *            第一期租金计划日期
	 * @return
	 * @throws Exception
	 */
	public boolean insertRentPlanOnHire(TabCalBean tcb, String star_date) throws Exception {

		// 数据来源 合同正式表
		TabCalBean src_tcb_cont = TbBeanTools.getTabInfo("cont_process");
		src_tcb_cont.setTableToFormal();// 转换为正式表
		src_tcb_cont.setTableToView();// 转换为视图
		// 构建查询汇总的表--正式表
		String table = "(select plan_date,rent_list,rent,all_remain_rent,corpus,interest,corpus_overage,year_rate,rent_adjust,corpus_business,interest_business from " + src_tcb_cont.getRentPlan_tb() + " where " + src_tcb_cont.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "'";
		// 如果是合并计算 那么正式表中起租日之后的 未回笼就不在加入合并
		if (tcb.getIs_merger().equals("是")) {
			table += " and (plan_date<'" + star_date + "' or status!='未回笼')";
			;
		}
		table += ") union all ";
		// 多次起租表
		TabCalBean onHire_tcb = TbBeanTools.getTabInfo("onHire_more_process");
		table += "(select plan_date,rent_list,rent,all_remain_rent,corpus,interest,corpus_overage,year_rate,rent_adjust,corpus_business,interest_business from " + onHire_tcb.getRentPlan_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		// 报价编号
		if (!"".equals(tcb.getOnHire_id())) {
			table += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		table += ")";
		// union 之后总汇总 这里要注意year_rate 为取平均数,因为 利率不同的不允许分次起租
		table = "(select '" + tcb.getContOrProjCValue() + "' as " + tcb.getContOrProjCName() + ",'" + tcb.getDocId() + "' as doc_id,plan_date,sum(rent_list) as rent_list,sum(rent) as rent,sum(all_remain_rent) as all_remain_rent,sum(corpus) as corpus,sum(interest) as interest" + ",sum(corpus_overage) as corpus_overage,avg(year_rate) as year_rate,sum(rent_adjust) as rent_adjust," + "sum(corpus_business) as column_1,sum(interest_business) as column_2 from(" + table + ") as rs group by plan_date) as temp";
		// 构建用来查询的bean
		TabCalBean select_tcb = TbBeanTools.getTabInfo("cont_process");
		select_tcb.setContOrProjCValue(tcb.getContOrProjCValue());
		select_tcb.setDocId(tcb.getDocId());
		select_tcb.setRentPlan_tb(table);
		FundRentPlanBean frpb = getRentAndDateByTcb(select_tcb, 1);
		// 删除
		deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);
		frpb.setRentAdjustList(null);// 汇总不记录调整列
		// 插入
		addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, 1,null);
		// 因为在均息下本金在合并后有差异.所以要做最后一期的调整
		String sql = "";
		String where = "";
		sql = "select sum(corpus_business) as column_1,max(rent_list) as rent_list,max(plan_date) as plan_date,min(plan_date) s_plan_date,max(plan_date) e_plan_date,count(id) as num from " + tcb.getRentPlan_tb();
		where = " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		// 报价编号
		if (!"".equals(tcb.getOnHire_id())) {
			where += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		Conn db = new Conn();

		List<Map<String, String>> rs_list = db.executeQuery(sql + where);
		String column_1 = "";
		String rent_list = "";
		String end_date = "";
		String leas_term = "";
		String num = "";
		for (Map<String, String> rs : rs_list) {
			column_1 = rs.get("column_1");
			rent_list = rs.get("rent_list");
			end_date = rs.get("plan_date");
			leas_term = DateTools.getDateDiffMonth(rs.get("s_plan_date"), rs.get("e_plan_date")) + "";
			num = rs.get("num");
			sql = "select sum(corpus) as corpus from " + tcb.getRentPlan_tb();
			List<Map<String, String>> rs_inner_list = db.executeQuery(sql + where + " and rent_list<" + rent_list);
			for (Map<String, String> s_inner : rs_inner_list) {
				String corpus = s_inner.get("corpus");
				sql = "update " + tcb.getRentPlan_tb() + " set corpus=" + column_1 + "-" + corpus + where + " and rent_list=" + rent_list;
				db.executeUpdate(sql);
				sql = "update " + tcb.getRentPlan_tb() + " set interest=rent-corpus " + where + " and rent_list=" + rent_list;
				db.executeUpdate(sql);
			}
		}
		// 因为多次起租合并了contract_condition中的值 所有有一些字段要在有了合并的租金计划之后才能做更新
		sql = "update " + tcb.getCondition_tb() + " set End_date='" + end_date + "',Lease_term=" + leas_term + ",Income_number=" + num + "-grace" + where;
		db.executeUpdate(sql, "多次起租中在获得了汇总租金计划之后更新交易结构表");
		return true;
	}

	/**
	 * 会计租金计划合并 多次起租时合并插入 在这里做的是字段相加,如果字段类型改变会导致相加之和数据有误 多次起租合并测算时
	 * 
	 * @param tcb
	 * @param star_date
	 *            第一期租金计划日期
	 * @return
	 * @throws Exception
	 */
	public boolean insertFinaRentPlanOnHire(TabCalBean tcb, String star_date) throws Exception {

		// 数据来源 合同正式表
		TabCalBean src_tcb_cont = TbBeanTools.getTabInfo("cont_process");
		src_tcb_cont.setTableToFormal();// 转换为正式表
		src_tcb_cont.setTableToView();// 转换为视图
		// 构建查询汇总的表--正式表
		String table = "(select plan_date,rent_list,rent,corpus,interest,corpus_overage,year_rate,rent_adjust,corpus_business,interest_business from " + src_tcb_cont.getRentFinaPlan_tb() + " where " + src_tcb_cont.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "'";
		// 如果是合并计算 那么正式表中起租日之后的 未回笼就不在加入合并
		if (tcb.getIs_merger().equals("是")) {
			table += " and (plan_date<'" + star_date + "' or status!='未回笼')";
		}
		table += ") union all ";
		// 多次起租表
		TabCalBean onHire_tcb = TbBeanTools.getTabInfo("onHire_more_process");
		table += "(select plan_date,rent_list,rent,corpus,interest,corpus_overage,year_rate,rent_adjust,corpus_business,interest_business from " + onHire_tcb.getRentFinaPlan_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		// 报价编号
		if (!"".equals(tcb.getOnHire_id())) {
			table += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		table += ")";
		// union 之后总汇总 这里要注意year_rate 为取平均数,因为 利率不同的不允许分次起租
		table = "(select '" + tcb.getContOrProjCValue() + "' as " + tcb.getContOrProjCName() + ",'" + tcb.getDocId() + "' as doc_id,plan_date,sum(rent_list) as rent_list,sum(rent) as rent,sum(corpus) as corpus,sum(interest) as interest" + ",sum(corpus_overage) as corpus_overage,avg(year_rate) as year_rate,sum(rent_adjust) as rent_adjust," + "sum(corpus_business) as column_1,sum(interest_business) as column_2 from(" + table + ") as rs group by plan_date) as temp";
		// 构建用来查询的bean
		TabCalBean select_tcb = TbBeanTools.getTabInfo("cont_process");
		select_tcb.setContOrProjCValue(tcb.getContOrProjCValue());
		select_tcb.setDocId(tcb.getDocId());
		select_tcb.setRentPlan_tb(table);
		FundRentPlanBean frpb = getRentAndDateByTcb(select_tcb, 1);
		// 删除
		deleteRentPlan(tcb.getRentFinaPlan_tb(), tcb, 1);
		frpb.setRentAdjustList(null);// 汇总不记录调整列
		// 插入
		addRentPlan(tcb.getRentFinaPlan_tb(), frpb, tcb, 1,null);
		// 因为在均息下本金在合并后有差异.所以要做最后一期的调整
		String sql = "";
		String where = "";
		sql = "select sum(corpus) as column_1,max(rent_list) as rent_list from " + tcb.getRentFinaPlan_tb();
		where = " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		// 报价编号
		if (!"".equals(tcb.getOnHire_id())) {
			where += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		Conn db = new Conn();
		List<Map<String, String>> rs_list = db.executeQuery(sql + where);
		for (Map<String, String> rs : rs_list) {
			String column_1 = rs.get("column_1");
			String rent_list = rs.get("rent_list");
			sql = "select sum(corpus) as corpus from " + tcb.getRentFinaPlan_tb();
			List<Map<String, String>> rs_inner_list = db.executeQuery(sql + where + " and rent_list<" + rent_list);
			for (Map<String, String> rs_inner : rs_inner_list) {
				String corpus = rs_inner.get("corpus");
				sql = "update " + tcb.getRentFinaPlan_tb() + " set corpus=" + column_1 + "-" + corpus + where + " and rent_list=" + rent_list;
				db.executeUpdate(sql);
				sql = "update " + tcb.getRentFinaPlan_tb() + " set interest=rent-corpus " + where + " and rent_list=" + rent_list;
				db.executeUpdate(sql);
			}
		}
		// DaoUtil.closeRSOrConn(null, db);
		return true;
	}

	/**
	 * 
	 *  根据起租日 付租方式 租赁期限 更新 租金计划中的最后一期租金支付日
	 * 
	 * @param tcb
	 */
	public void updateEndPlanDate(TabCalBean tcb) {
		String sql = "select start_date,income_number_year,lease_term from " + tcb.getCondition_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		Conn db = new Conn();
		// ResultSet rs;
		String star_date = "";
		try {
			List<Map<String, String>> rs_list = db.executeQuery(sql);
			ConditionBean cb = new ConditionBean();
			for (Map<String, String> rs : rs_list) {
				cb.setStartDate(rs.get("start_date"));
				cb.setIncomeNumberYear(rs.get("income_number_year"));
				cb.setLeaseTerm(Integer.parseInt(rs.get("lease_term")));
				DictTools.getReversDict(cb);
				if (cb.getPeriodType().equals("1")) {
					star_date = DateTools.dateAdd("month", cb.getLeaseTerm() - 12 / Integer.parseInt(cb.getIncomeNumberYear()), cb.getStartDate());
				} else if (cb.getPeriodType().equals("0")) {
					star_date = DateTools.dateAdd("month", cb.getLeaseTerm(), cb.getStartDate());
				}
				sql = "select max(rent_list)rent_list from " + tcb.getRentPlan_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
				List<Map<String, String>> rs_inner_list = db.executeQuery(sql);
				for (Map<String, String> rs_inner : rs_inner_list) {
					sql = "update " + tcb.getRentPlan_tb() + " set plan_date='" + star_date + "' where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "' and rent_list=" + rs_inner.get("rent_list");
					db.executeUpdate(sql, "变更或者调息 根据起租日更新最后一期租金支付日");
				}
			}
		} catch (Exception e) {
			logger.error("变更或者调息 根据起租日更新最后一期租金支付日失败!");
			e.printStackTrace();
			e.toString();
		}
	}

	/**
	 * 合同中途终止修改最后一期利息
	 * 
	 * @param fpb
	 * @param adb
	 * @param tcb
	 * @throws Exception
	 */
	public void updateDataByRentList(FundRentPlanBean fpb, AdjustBean adb, TabCalBean tcb) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" update " + tcb.getRentPlan_tb() + " set  ")
		   .append(" rent= " + fpb.getRentList().get(adb.getAdjustList() - 1) + " , ")
		   .append(" interest= " + fpb.getInterestBusinessList().get(adb.getAdjustList() - 1) + " , ")
		   .append(" Interest_Business= " + fpb.getInterestBusinessList().get(adb.getAdjustList() - 1) )
		   .append(" where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() )
		   .append("");
		if (tcb.getDocId() != null && !tcb.getDocId().equals("")) {
			sql.append("  and doc_id='" + tcb.getDocId() + "' ");
		}
		sql.append("  and rent_list =" + adb.getStartList());
		Conn db = new Conn();
		db.executeUpdate(sql.toString(), "合同中途终止修改最后一期利息");
		db.close();
	}
	
	/**
	  * <p>合同终止，根据商定租金把大于结算终止日的所有偿还计划合并为一起
	  * <pre>
	  * 1.查询小于等于约定终止日的最大偿还期项加1即为最后的终止期项
	  * 2.删除所有大于结算终止日的偿还计划
	  * 3.偿还日期 = 结算偿还金支付日
	  * 租金 = 商定租金 
	  * 本金 = 商定租金 < 未到期本金:商定租金?未到期本金
	  * 利息 = 商定租金 - 未到期本金 > 0 :商定租金 - 未到期本金 ? 0
	  * 
	  * 4.添加隐藏域字段未到期财务本金 
	  * 财务本金 = 商定租金 < 未到期财务本金:商定租金?未到期财务本金
	  * 利息 = 商定租金 - 未到期财务本金 > 0 :商定租金 - 未到期财务本金 ? 0
	  * 5.本金余额  =  商定租金  < 未到期业务本金:未到期业务本金-商定租金?0
	  * 6.构建终止后最终一期偿还计划SQL
	  * 7.执行合同终止最终一期持久化临时表操作
	  * </pre>
	  *</p>
	  * @author sea
	  * @param adb 租金变更实体
	  * @param tcb 合同终止测算合同号表名等必须信息
	  * @throws Exception
	 */
	public void updateDataByDiscussrent(AdjustBean adb, TabCalBean tcb) throws Exception {
		//结算偿还金支付日
		String payMoneyday = adb.getPaydayAdjust();
		logger.debug("结算偿还金支付日:"+payMoneyday);
		BigDecimal discussrent =  new BigDecimal(adb.getAgreedInterest()).add(new BigDecimal(adb.getCorpusOverage())).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP)   ;
		logger.debug("商定租金:"+discussrent);
		StringBuffer sql = new StringBuffer();
		
		//1. 查询小于等于约定终止日的最大偿还期项加1即为最后的终止期项
		sql.append(" select nvl(max(nvl(rent_list,0)),0) + 1 as rent_list from  " + tcb.getRentPlan_tb())
		   .append(" where  " + tcb.getContOrProjCName() + " = '" + tcb.getContOrProjCValue() + "' ");
		if (!Tools.isNullOrEmpty( tcb.getDocId() )) {
			sql.append("  and doc_id = '" + tcb.getDocId() + "' ");
		}  
		if (!Tools.isNullOrEmpty(adb.getPaydayAdjust())) {
			sql.append("  and plan_date <= '" + adb.getPaydayAdjust() + "' ");
		}
		String finalSql = sql.toString();
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			finalSql = finalSql.replaceAll("nvl", "ISNULL");
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			finalSql = finalSql.replaceAll("nvl", "IFNULL");
		}
		Conn db = new Conn();
		List<Map<String, String>> list = db.executeQuery(finalSql.toString(), "查询小于等于约定终止日的最大偿还期项加1即为最后的终止期项");
		String rent_list = "0";
		if(list.size() > 0){
			rent_list = list.get(0).get("rent_list");
		}
		logger.info("约定终止日期项:"+rent_list);
		
		//2.删除大于约定终止日的所有偿还计划
		sql = new StringBuffer();
		sql.append(" delete " + tcb.getRentPlan_tb() + "  ")
		   .append(" where  " + tcb.getContOrProjCName() + " = '" + tcb.getContOrProjCValue() + "' ");
		if (!Tools.isNullOrEmpty( tcb.getDocId() )) {
			sql.append("  and doc_id = '" + tcb.getDocId() + "' ");
		}   
		if (!Tools.isNullOrEmpty(adb.getPaydayAdjust())) {
			sql.append("  and plan_date > '" + adb.getPaydayAdjust() + "' ");
		}   
		db.executeUpdate(sql.toString(), "合同中途终止删除大于约定终止日的所有偿还计划");
		
		//3.计算终止后最终一期的业务本利
		//业务本金  =  商定租金 < 未到期业务本金:商定租金?未到期本金
		BigDecimal corpusOverage = new BigDecimal(adb.getCorpusOverage()) ;
		BigDecimal  endCorpus = BigDecimal.ZERO;
		if(discussrent.compareTo(corpusOverage) < 0 ){//商定租金 < 未到期本金
			endCorpus = discussrent;
		}else{
			endCorpus = corpusOverage;
		}
		//业务利息 = 商定租金 - 未到期本金 > 0 :商定租金 - 未到期本金 ? 0
		BigDecimal  endInterest =BigDecimal.ZERO;
		if(discussrent.compareTo(corpusOverage)  > 0){//利息 = 商定租金 - 未到期本金 > 0  = 商定租金 - 未到期本金
			endInterest = discussrent .subtract(corpusOverage).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP);  //
		}
		
		//4.计算终止后最终一期的财务本利
		/**
		 * 未到期财务本金 = 
		 * 财务本金 = 商定租金 < 未到期财务本金:商定租金?未到期财务本金
		 * 利息 = 商定租金 - 未到期财务本金 > 0 :商定租金 - 未到期财务本金 ? 0
		 */
		//未到期财务本金 
		BigDecimal finacorpusOverage = new BigDecimal(adb.getFinacorpusOverage()); 
		logger.info("未到期财务本金 :"+finacorpusOverage);
		//最后一期财务本金处理
		BigDecimal endFinaCorpus =BigDecimal.ZERO;
		if(discussrent.compareTo(finacorpusOverage) < 0){
			endFinaCorpus = discussrent;
		}else{
			endFinaCorpus = finacorpusOverage;
		}
		//最后一期财务利息处理  
		BigDecimal endFinaInterest = BigDecimal.ZERO;
		if(discussrent.compareTo(finacorpusOverage)  > 0){//商定租金 - 未到期财务本金 > 0 :商定租金 - 未到期财务本金 ? 0
			endFinaInterest = discussrent.subtract(finacorpusOverage).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP);
		}
		
		//5. 本金余额  =  商定租金 < 未到期业务本金:未到期业务本金-商定租金?0
		BigDecimal corpusMoney = BigDecimal.ZERO;
		if(discussrent .compareTo(corpusOverage) < 0 ){//商定租金  < 未到期业务本金
			corpusMoney = corpusOverage.subtract(discussrent).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP);
		}
		//6.年利率的计算
		BigDecimal yearRate =endInterest.divide(endCorpus,Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(1200)).setScale(Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP) ;
		//7.构建终止后最终一期偿还计划SQL
		sql = new StringBuffer();
		sql.append(" insert into  " + tcb.getRentPlan_tb() + "   ")
		//UUID、合同号、文档号、期项、偿还日期、租金、业务本金、年利率、业务利息、偿还状态  ,status_
		.append(" (ID,contract_id,doc_id,rent_list,plan_date,rent,corpus_business,year_rate,interest_business,interest,corpus,corpus_overage) ")
		.append(" values( ")
		.append(" '" + UUIDUtil.getUUID() + "', ")
		.append(" '" + tcb.getContOrProjCValue( )+ "', ")
		.append(" '" + tcb.getDocId() + "', ")
		.append(" " + rent_list + ",")
		.append(" '" + payMoneyday + "', ")
		.append(" " + discussrent + ", ")
		.append(" " + endCorpus + ", ")
		.append(" " + yearRate + ", ")//折现率
		.append(" " + endInterest + ", ")
		.append(" " + endInterest + ", ")//2014-08-26改为财务利息=业务利息。原本取值：endFinaInterest
		.append(" " + endCorpus + ", ")//2014-08-26改为 财务本金=业务本金。原本取值：endFinaCorpus 
		.append(" " + corpusMoney + " ")
		.append(" ) ");
		
		//7.执行合同终止最终一期持久化临时表操作
		db.executeUpdate(sql.toString(), "合同中途终止插入最终的终止测算利息");
		
		db.close();
	}
}
