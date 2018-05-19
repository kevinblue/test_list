package com.reckon.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.Conn;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.util.TbBeanTools;
import com.reckon.util.tools.NumTools;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.UUIDUtil;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc  ( Irr现金流明细DAO实现类)
 */
public class IrrDetailsDAOImpl {
	static Logger logger = Logger.getLogger(TbBeanTools.class);

	/**
	 * 
	 *    添加现金流明细记录)
	 * 
	 * @param tb
	 * @param cdsList
	 * @param tabName
	 *            所操作的表
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public boolean addCashDetails(TabCalBean tcb, List<CashDetailsBean> cdsList, String tabName) throws Exception {
		//  Auto-generated method stub
		Conn conn = new Conn();
		String sql = "";

		for (CashDetailsBean cdb : cdsList) {
			sql += "INSERT INTO " + tabName + " (id," + tcb.getContOrProjCName() + "";
			sql += ",onhire_id";
			sql += ",plan_date";
			sql += ",fund_in";
			sql += ",fund_in_details";
			sql += ",fund_out";
			sql += ",fund_out_details";
			sql += ",net_flow";
			// sql += ",creator";
			// sql += ",create_date";
			sql += ",doc_id,quot_id";
			sql += " )VALUES ('" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase() + "',";
			sql += "'" + tcb.getContOrProjCValue() + "'";
			sql += ",'" + cdb.getOnhire_id() + "'";
			sql += ",'" + cdb.getPlanDate() + "'";
			sql += "," + NumTools.formatNumberDoubleScale(cdb.getFundIn(), 2) + "";
			sql += ",'" + (cdb.getFundInDetails().equals("") ? "-" : cdb.getFundInDetails()) + "'";
			sql += "," + NumTools.formatNumberDoubleScale(cdb.getFundOut(), 2) + "";

			sql += ",'" + (cdb.getFundOutDetails().equals("") ? "-" : cdb.getFundOutDetails()) + "'";
			sql += "," + NumTools.formatNumberDoubleScale(cdb.getNetFlow(), 2) + "";

			// sql += ",'" + tcb.getUserId() + "'";
			// sql += ",getdate()";
			sql += ",'" + tcb.getDocId() + "',";
			sql += "'" + tcb.getQuot_id() + "'";
			sql += ") ";
			conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "添加现金流语句...");
			sql = "";
		}
		// 如果是多次起租,那么现金流要做汇总
		if ("onHire_more_process".equals(tcb.getCalType())) {
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process");
			tcb_cont.setOnHire_id(tcb.getOnHire_id());
			tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
			tcb_cont.setDocId(tcb.getDocId());
			tcb_cont.setIs_merger(tcb.getIs_merger());// 传递是否合并计算
			tcb_cont.setUserId(tcb.getUserId());

			// 判断要不要合并 第一次 发起多次起租流程的时候不需要合并 判断标准为 多次起租交易结构正式表中有没有这个合同的数据
			TabCalBean tempTcb = null;
			String wheresql = "";
			ConditionBean cb = new ConditionBean();
			tempTcb = TbBeanTools.getTabInfo("onHire_more_process");
			tempTcb.setTableToFormal();// 转为正式表
			wheresql = tempTcb.getCondition_tb() + " where  " + tempTcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "'";
			cb = new ConditionDAOImpl().getConditionBeanByContract(wheresql, tempTcb);
			if (cb == null || cb.getEquipAmt().equals("")) {// 从多次起租正式交易结构表中没有查到数据
				logger.info("-----------------------------第一次起租,现金流不需要合并!");
				if (tabName.equals(tcb.getContractCashTb())) {
					tabName = tcb_cont.getContractCashTb();
				} else if (tabName.equals(tcb.getFinacCashTb())) {
					tabName = tcb_cont.getFinacCashTb();
				}
				deleteCashDetails(tcb_cont, tabName);// 先删除
				return addCashDetails(tcb_cont, cdsList, tabName);
			} else {
				logger.info("进入现金流的合并!");
				return insertCashDetailsOnHire(tcb_cont, cdsList.get(0).getPlanDate().toString(), tabName);
			}
		}
		return true;
	}

	/**
	 * 
	 * @Title: addCashDetails
	 * @author zhangc	change by ykl
	 * @Description: 根据资金计划和租金计划，生成现金流,自定义isdate触发器，生成月现金流时防止出现2-31这种日期错误
	 * @param tcb
	 * @param cb
	 * @return
	 * @throws Exception
	 * @return boolean
	 * @throws
	 */
	public boolean addCashDetails (TabCalBean tcb,ConditionBean cb) throws Exception {
		Conn conn = new Conn();
		String sql = "";
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			if(tcb.getCondition_tb().equalsIgnoreCase("contract_condition_temp")){
				sql = "exec [dbo].[proc_cal_cash_contract] '" + tcb.getDocId() + "','"+tcb.getContOrProjCValue()+"'"+ ",'"+cb.getStartDate()+"'"+ ",'"+cb.getEndDate()+"'";
			}else{
				sql = "exec [dbo].[proc_cal_cash_proj] '" + tcb.getDocId() + "','"+tcb.getContOrProjCValue()+"'"+ ",'"+cb.getStartDate()+"'"+ ",'"+cb.getEndDate()+"'";
			}
			
		}else if(ResourceUtil.getDBType().indexOf("ORACLE")>-1){
			sql += "INSERT INTO " + tcb.getContractCashTb() + " (id," + tcb.getContOrProjCName() + "";
			sql += ",onhire_id";
			sql += ",plan_date";
			sql += ",fund_in";
			sql += ",fund_in_details";
			sql += ",fund_out";
			sql += ",fund_out_details";
			sql += ",net_flow";
			// sql += ",creator";
			// sql += ",create_date";
			sql += ",doc_id,quot_id";//create_date,creator_ "+DateUtil.getSystemDate()+"' create_date ,'"+SecurityUtil.getPrincipal().getId()+"' creator_"
			sql += " )";
			
			sql +=" select sys_guid() id,'"+tcb.getContOrProjCValue()+"' "+tcb.getContOrProjCName()+",'' onhire_id,"+(cb.getIsFactory() ? "t.plan_date" : " t.plan_date ")+" plan_date,sum(t.flowin) fund_in,nvl(to_char(wm_concat(t.flowindetail)),'-') fund_in_details, ";
			sql +="   sum(t.flowout) fund_out ,nvl(to_char(wm_concat(t.flowoutdetail)),'-')fund_out_details,sum(t.cleanfow)net_flow ,'"+tcb.getDocId()+"' doc_id,'' quot_id " ;
			sql +="  from  (" ;
			sql +="  select cfrp.plan_date,cfrp.rent flowin,";
			if(cb.getIsFactory()){
				sql +="(case when nvl(cfrp.CORPUS_BUSINESS,0) > 0 then '第'||cfrp.rent_list||'期收回应收账款:'||cfrp.CORPUS_BUSINESS||';' else '' end)||(case when nvl(cfrp.interest_BUSINESS,0) > 0 then '第'||cfrp.rent_list||'期保理费收入:'||cfrp.interest_BUSINESS||';' else '' end) flowindetail";
			}else{
				sql +=" '第'||cfrp.rent_list||'期租金:'||cfrp.rent flowindetail";
			}
			sql +=",0 flowout,'' flowoutdetail,cfrp.rent-0  cleanfow " ;
			sql +="    from  "+tcb.getRentPlan_tb() +" cfrp" ;
			sql +="    where cfrp.doc_id = '"+tcb.getDocId()+"'" ;
			sql +="    and cfrp."+tcb.getContOrProjCName()+" = '"+tcb.getContOrProjCValue()+"'" ;
			sql +="    union all " ;
			sql +="    select fundplan.plan_date,decode(fundplan.pay_type,'pay_type_in',fundplan.plan_money,0)flowin, ";
			sql +="    decode(fundplan.pay_type,'pay_type_in',tdd.name_||':'||fundplan.plan_money,''), " ;
			sql +="    decode(fundplan.pay_type,'pay_type_out',fundplan.plan_money,0)flowout, " ;
			sql +="    decode(fundplan.pay_type,'pay_type_out',tdd.name_||':'||fundplan.plan_money,'')flowoutdetail, " ;
			sql +="    decode(fundplan.pay_type,'pay_type_in',fundplan.plan_money,-fundplan.plan_money) cleanfow " ;
			sql +="     from  "+tcb.getFundFundPlan_tb()+" fundplan " ;
			sql +="    left join t_dicts_datas tdd on  fundplan.fee_type = tdd.id_ " ;
			sql +="    where fundplan.doc_id =  '"+ tcb.getDocId()+"'";
			sql +="    and fundplan."+tcb.getContOrProjCName()+" = '"+tcb.getContOrProjCValue()+"' " ;//and fundplan.fee_type not in ('feetype18','feetype16') 国药特殊需求。保证金抵扣和厂商保证金不在现金流体现。
//			sql +="    and fee_type<>'feetype4' ";2017-12-12客户需求，现金流包含留购价
			sql +="    union all " ;
			sql +="		 select  case isdate(tt.plandate,'yyyy-MM-dd') when 0 then to_char(last_day(to_date(substr(tt.plandate,0,7),'yyyy-MM')),'yyyy-MM-dd') when 1 then  tt.plandate end, 0 flowin, '', 0, '', 0 from (";
			sql +="    select cash_date||'"+cb.getStartDate().substring(7, 10)+"' plandate,0 flowin,'',0,'',0 from cash_help where cash_date >= substr('"+cb.getLeaseAmtDate()+"',0,7) and cash_date<=substr('"+cb.getEndDate()+"',0,7))tt";
			sql +="    )t group by "+(cb.getIsFactory() ? "t.plan_date" : " t.plan_date ");
		}
		logger.info(sql);
		conn.executeUpdate(sql);
		return true;
	}
	
	

	/**
	 * 
	 * @Title: addCashDetails
	 * @author zhangc
	 * @Description: 根据资金计划和租金计划，生成现金流
	 * @param tcb
	 * @param cb
	 * @return
	 * @throws Exception
	 * @return boolean
	 * @throws
	 */
	public boolean addFctiorngCashDetails (TabCalBean tcb,ConditionBean cb) throws Exception {
		Conn conn = new Conn();
		String sql = "";
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			if(tcb.getCondition_tb().equalsIgnoreCase("contract_condition_temp")){
				sql = "exec [dbo].[proc_cal_cash_contract] '" + tcb.getDocId() + "','"+tcb.getContOrProjCValue()+"'"+ ",'"+cb.getStartDate()+"'"+ ",'"+cb.getEndDate()+"'";
			}else{
				sql = "exec [dbo].[proc_cal_cash_proj] '" + tcb.getDocId() + "','"+tcb.getContOrProjCValue()+"'"+ ",'"+cb.getStartDate()+"'"+ ",'"+cb.getEndDate()+"'";
			}
			
		}else if(ResourceUtil.getDBType().indexOf("ORACLE")>-1){
			sql += "INSERT INTO " + tcb.getContractCashTb() + " (id," + tcb.getContOrProjCName() + "";
			sql += ",onhire_id";
			sql += ",plan_date";
			sql += ",fund_in";
			sql += ",fund_in_details";
			sql += ",fund_out";
			sql += ",fund_out_details";
			sql += ",net_flow";
			// sql += ",creator";
			// sql += ",create_date";
			sql += ",doc_id,quot_id";//create_date,creator_ "+DateUtil.getSystemDate()+"' create_date ,'"+SecurityUtil.getPrincipal().getId()+"' creator_"
			sql += " )";
			
			sql +=" select sys_guid() id,'"+tcb.getContOrProjCValue()+"' "+tcb.getContOrProjCName()+",'' onhire_id,"+(cb.getIsFactory() ? "t.plan_date" : " substr(t.plan_date,0,7) ")+" plan_date,sum(t.flowin) fund_in,nvl(to_char(wm_concat(t.flowindetail)),'-') fund_in_details, ";
			sql +="   sum(t.flowout) fund_out ,nvl(to_char(wm_concat(t.flowoutdetail)),'-')fund_out_details,sum(t.cleanfow)net_flow ,'"+tcb.getDocId()+"' doc_id,'' quot_id " ;
			sql +="  from  (" ;
			sql +="  select cfrp.plan_date,cfrp.rent flowin,";
			if(cb.getIsFactory()){
				sql +="(case when nvl(cfrp.CORPUS_BUSINESS,0) > 0 then '第'||cfrp.rent_list||'期收回应收账款:'||cfrp.CORPUS_BUSINESS||';' else '' end)||(case when nvl(cfrp.interest_BUSINESS,0) > 0 then '第'||cfrp.rent_list||'期保理费收入:'||cfrp.interest_BUSINESS||';' else '' end) flowindetail";
			}else{
				sql +=" '第'||cfrp.rent_list||'期租金:'||cfrp.rent flowindetail";
			}
			sql +=",0 flowout,'' flowoutdetail,cfrp.rent-0  cleanfow " ;
			sql +="    from  "+tcb.getRentPlan_tb() +" cfrp" ;
			sql +="    where cfrp.doc_id = '"+tcb.getDocId()+"'" ;
			sql +="    and cfrp."+tcb.getContOrProjCName()+" = '"+tcb.getContOrProjCValue()+"'" ;
			sql +="    union all " ;
			sql +="    select fundplan.plan_date,decode(fundplan.pay_type,'pay_type_in',fundplan.plan_money,0)flowin, ";
			sql +="    decode(fundplan.pay_type,'pay_type_in',tdd.name_||':'||fundplan.plan_money,''), " ;
			sql +="    decode(fundplan.pay_type,'pay_type_out',fundplan.plan_money,0)flowout, " ;
			sql +="    decode(fundplan.pay_type,'pay_type_out',tdd.name_||':'||fundplan.plan_money,'')flowoutdetail, " ;
			sql +="    decode(fundplan.pay_type,'pay_type_in',fundplan.plan_money,-fundplan.plan_money) cleanfow " ;
			sql +="     from  "+tcb.getFundFundPlan_tb()+" fundplan " ;
			sql +="    left join t_dicts_datas tdd on  fundplan.fee_type = tdd.id_ " ;
			sql +="    where fundplan.doc_id =  '"+ tcb.getDocId()+"'";
			sql +="    and fundplan."+tcb.getContOrProjCName()+" = '"+tcb.getContOrProjCValue()+"' " ;//and fundplan.fee_type not in ('feetype18','feetype16') 国药特殊需求。保证金抵扣和厂商保证金不在现金流体现。
			if(!cb.getIsFactory()){
				sql +="	union all  ";
				sql +="  select ch.cash_date plan_date,ch.cash_value flowin,'' flowindetail,0 flowout,'' flowoutdetail,0 cleanfow from  cash_help ch ";
				sql +="   where ch.cash_date >=  '"+cb.getStartDate()+"' and ch.cash_date <='"+cb.getEndDate().replaceAll(" ", "")+"'";
			}
			sql +="    )t group by "+(cb.getIsFactory() ? "t.plan_date" : " substr(t.plan_date,0,7) ");
		}
		logger.info(sql);
		conn.executeUpdate(sql);
		return true;
	}
	/**
	 * 
	 *    删除现金流明细记录)
	 * 
	 * @param tb
	 * @param tabName
	 *            所操作的表
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCashDetails(TabCalBean tcb, String tabName) throws Exception {
		Conn conn = new Conn();
		String sql = " delete " + tabName + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		conn.executeUpdate(sql, "删除现金流sql..:");
		return true;
	}

	/**
	 * 
	 *  (  根据现金流配置信息构建明细)
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<CashDetailsBean> getcDetailsBySql(String sql, String quot_id) throws Exception {
		Conn conn = new Conn();
		List<Map<String, String>> rs_list = conn.executeQuery(sql, "配置现金流构建..");

		List<CashDetailsBean> cdbList = new ArrayList<CashDetailsBean>();
		for (Map<String, String> rs : rs_list) {

			CashDetailsBean cdb = new CashDetailsBean();
			cdb.setContractId(rs.get("corp"));
			cdb.setPlanDate(rs.get("sdate"));
			cdb.setQuotId(quot_id);

			if ("in".equals(rs.get("inorout"))) {// 流入时

				cdb.setFundIn(rs.get("feename"));

				if (null != rs.get("feename") && !"".equals(rs.get("feename")) && Double.parseDouble(rs.get("feename")) > 0) {// 当费用大于0时
					cdb.setFundInDetails(rs.get("feenamedes") + "：" + NumTools.formatNumberDouble(Double.parseDouble(rs.get("feename"))) + "；");
				}

				cdb.setFundOut("0");
				cdb.setFundOutDetails("");

			} else {

				cdb.setFundOut(rs.get("feename"));

				if (null != rs.get("feename") && !"".equals(rs.get("feename")) && Double.parseDouble(rs.get("feename")) > 0) {// //当费用大于0时
					cdb.setFundOutDetails(rs.get("feenamedes") + "：" + NumTools.formatNumberDouble(Double.parseDouble(rs.get("feename"))) + "；");
				}

				cdb.setFundIn("0");
				cdb.setFundInDetails("");
			}

			cdb.setNetFlow(new BigDecimal(cdb.getFundIn()).subtract(new BigDecimal(cdb.getFundOut())).toString());
			cdbList.add(cdb);

		}

		logger.info("配置现金流构建成功..");
		// DaoUtil.closeRSOrConn(rs, conn);
		return cdbList;
	}

	/**
	 * 
	 * 多次起租时合并插入 在这里做的是字段相加,如果字段类型改变会导致相加之和数据有误 多次起租合并测算时
	 * 
	 * @param tcb
	 * @param star_date
	 *            第一期租金计划日期
	 * @return
	 * @throws Exception
	 */
	public boolean insertCashDetailsOnHire(TabCalBean tcb, String star_date, String tabName) throws Exception {

		String addString = "||";
		// 数据来源 合同正式表
		TabCalBean src_tcb_cont = TbBeanTools.getTabInfo("cont_process");
		src_tcb_cont.setTableToFormal();// 转换为正式表
		src_tcb_cont.setTableToView();// 转换为视图
		// 多次起租表
		TabCalBean onHire_tcb = TbBeanTools.getTabInfo("onHire_more_process");
		String src_tableName = "";// 用来查询的正式表
		String insert_table = "";// 用来插入的表
		if (tabName.equals(onHire_tcb.getContractCashTb())) {
			src_tableName = src_tcb_cont.getContractCashTb();
			insert_table = tcb.getContractCashTb();
		} else if (tabName.equals(onHire_tcb.getFinacCashTb())) {
			src_tableName = src_tcb_cont.getFinacCashTb();
			insert_table = tcb.getFinacCashTb();
		}
		String where1 = " where " + src_tcb_cont.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "'";
		String where2 = " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		if (!"".equals(tcb.getOnHire_id())) {
			where2 += " and onhire_id='" + tcb.getOnHire_id() + "'";
		}
		if (tcb.getIs_merger().equals("是")) {
			where1 += " and plan_date<='" + star_date + "'";
		}
		// 删除
		deleteCashDetails(tcb, insert_table);
		// 插入
		String sql = "";
		sql += "INSERT INTO " + insert_table + " (id," + tcb.getContOrProjCName() + "";
		sql += ",onhire_id";
		sql += ",plan_date";
		sql += ",fund_in";
		sql += ",fund_in_details";
		sql += ",fund_out";
		sql += ",fund_out_details";
		sql += ",net_flow";
		// sql += ",creator";
		// sql += ",create_date";
		sql += ",doc_id,quot_id";
		sql += " )";
		sql += "select '" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase() + "'," + "case when con." + src_tcb_cont.getContOrProjCName() + " is null then onhire." + onHire_tcb.getContOrProjCName() + "else con." + src_tcb_cont.getContOrProjCName() + " end ) as contract_id,'" + tcb.getOnHire_id() + "'," + "cas when con.plan_date is null then  onhire.plan_date else con.plan_date end as plan_date" + ",cas when con.fund_in is null then 0 else con.fund_in end +" + " case when onhire.fund_in is null then 0 else onhire.fund_in end as fund_in" + ",case when con.fund_in_details is null then ' ' else con.fund_in_details  end+" + addString + "'　'" + addString + "case when onhire.fund_in_details is null then '' else onhire.fund_in_details end as fund_in_details"
				+ ",case when con.fund_out is null  then 0 else con.fund_out end +" + "case when onhire.fund_out is null then 0 else onhire.fund_out end  as fund_out" + ",case when con.fund_out_details is null then '' else con.fund_out_details end " + addString + "'　'" + addString + "case when onhire.fund_out_details is null then '' else onhire.fund_out_details end as fund_out_details" + ",case when con.net_flow is null then 0 else con.net_flow+case when onhire.net_flow is null 0 then onhire.net_flow end  as net_flow"
				// + ",'" + tcb.getUserId() + "'"
				// + ",getdate()"
				+ ",'" + tcb.getDocId() + "'," + "'" + tcb.getQuot_id() + "'" + " from (" + " select " + onHire_tcb.getContOrProjCName() + ",onhire_id,plan_date,fund_in,fund_in_details,fund_out,fund_out_details,net_flow" + " from " + tabName + where2 + ") as onhire " + "FULL OUTER join(" + "select " + src_tcb_cont.getContOrProjCName() + ",onhire_id,plan_date,fund_in,fund_in_details,fund_out,fund_out_details,net_flow" + " from " + src_tableName + where1 + ") as con on(onhire." + src_tcb_cont.getContOrProjCName() + "=con." + onHire_tcb.getContOrProjCName() + " and onhire.plan_date=con.plan_date)";
		Conn db = new Conn();
		db.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "添加现金流语句...");
		return true;
	}

	public boolean addCashDetailsForFinance(TabCalBean tcb, ConditionBean cb) throws Exception {
		Conn conn = new Conn();
		String sql = "";
		
		sql += "INSERT INTO " + tcb.getContractCashTb() + " (id," + tcb.getContOrProjCName() + "";
		sql += ",onhire_id";
		sql += ",plan_date";
		sql += ",fund_in";
		sql += ",fund_in_details";
		sql += ",fund_out";
		sql += ",fund_out_details";
		sql += ",net_flow";
		sql += ",doc_id,quot_id";//create_date,creator_ "+DateUtil.getSystemDate()+"' create_date ,'"+SecurityUtil.getPrincipal().getId()+"' creator_"
		sql += " )";
		sql +="select sys_guid() id,'"+tcb.getContOrProjCValue()+"' "+tcb.getContOrProjCName()+",'' onhire_id,"+(cb.getIsFactory() ? "t.plan_date" : " t.plan_date ")+" plan_date,sum(t.flowin) fund_in,nvl(to_char(wm_concat(t.flowindetail)),'-') fund_in_details, ";
		sql +="   sum(t.flowout) fund_out ,nvl(to_char(wm_concat(t.flowoutdetail)),'-')fund_out_details,sum(t.cleanfow)net_flow ,'"+tcb.getDocId()+"' doc_id,'' quot_id " ;
		sql +="  from  (" ;
		sql +="  select cfrp.plan_date,cfrp.rent flowin,";
		if(cb.getIsFactory()){
			sql +="(case when nvl(cfrp.CORPUS_BUSINESS,0) > 0 then '第'||cfrp.rent_list||'期收回应收账款:'||cfrp.CORPUS_BUSINESS||';' else '' end)||(case when nvl(cfrp.interest_BUSINESS,0) > 0 then '第'||cfrp.rent_list||'期保理费收入:'||cfrp.interest_BUSINESS||';' else '' end) flowindetail";
		}else{
			sql +=" '第'||cfrp.rent_list||'期租金:'||cfrp.rent flowindetail";
		}
		sql +=",0 flowout,'' flowoutdetail,cfrp.rent-0  cleanfow " ;
		sql +="    from  "+tcb.getRentPlan_tb() +" cfrp" ;
		sql +="    where cfrp.doc_id = '"+tcb.getDocId()+"'" ;
		sql +="    and cfrp."+tcb.getContOrProjCName()+" = '"+tcb.getContOrProjCValue()+"'" ;
		sql +="    union all " ;
		sql +="    select fundplan.plan_date,decode(fundplan.pay_type,'pay_type_in',fundplan.plan_money,0)flowin, ";
		sql +="    decode(fundplan.pay_type,'pay_type_in',tdd.name_||':'||fundplan.plan_money,''), " ;
		sql +="    decode(fundplan.pay_type,'pay_type_out',fundplan.plan_money,0)flowout, " ;
		sql +="    decode(fundplan.pay_type,'pay_type_out',tdd.name_||':'||fundplan.plan_money,'')flowoutdetail, " ;
		sql +="    decode(fundplan.pay_type,'pay_type_in',fundplan.plan_money,-fundplan.plan_money) cleanfow " ;
		sql +="     from  "+tcb.getFundFundPlan_tb()+" fundplan " ;
		sql +="    left join t_dicts_datas tdd on  fundplan.fee_type = tdd.id_ " ;
		sql +="    where fundplan.doc_id =  '"+ tcb.getDocId()+"'";
		sql +="		and plan_date>='"+cb.getLeaseAmtDate()+"'";
		sql +="    and fundplan."+tcb.getContOrProjCName()+" = '"+tcb.getContOrProjCValue()+"' " ;//and fundplan.fee_type not in ('feetype18','feetype16') 国药特殊需求。保证金抵扣和厂商保证金不在现金流体现。
		sql +="    union all " ;
		sql +="		 select  case isdate(tt.plandate,'yyyy-MM-dd') when 0 then to_char(last_day(to_date(substr(tt.plandate,0,7),'yyyy-MM')),'yyyy-MM-dd') when 1 then  tt.plandate end, 0 flowin, '', 0, '', 0 from (";
		sql +="    select cash_date||'"+cb.getStartDate().substring(7, 10)+"' plandate,0 flowin,'',0,'',0 from cash_help where cash_date >= substr('"+cb.getLeaseAmtDate()+"',0,7) and cash_date<=substr('"+cb.getEndDate()+"',0,7))tt";
		sql +="    )t group by "+(cb.getIsFactory() ? "t.plan_date" : " t.plan_date ");
		logger.info(sql);
		conn.executeUpdate(sql);
		return true;
	}
	public List<BigDecimal> getCurrentCashList(String contractid , ContractCondition condition) throws Exception{
		Conn conn = new Conn();
		String sql = "";
		sql += 	  "select substr(dates,0,7)datas,sum(money) sum from ("  ;
//			      --实收租金从租金实收表查询
		sql +=	  " select HIREDATE dates,RENT money,'rent'FEETYPE,'租金'FEETYPENAME from vi_contract_fund_rent_income where PLANID in( select id from vi_contract_fund_rent_plan ";
		sql +=	  " where contractid ='"+contractid+"'";
		sql += 	  " and rent=CURRENTINCOME)";
		sql +=    " union all";
//			      --未回收/部分回收从计划表里面查询
		sql +=	  " select PLANDATE dates,RENT money,'rent'FEETYPE,'租金'FEETYPENAME from vi_contract_fund_rent_plan ";
		sql +=	  " where contractid ='"+contractid+"'";
		sql +=	  " and rent<>CURRENTINCOME";
		sql +=	  " union all";
//			      --实收资金从资金实收表查询
		sql +=	  " select FACTDATE dates,case PAYTYPE when 'pay_type_in' then FACTMONEY";
		sql +=	  " else -FACTMONEY end money ,FEETYPE,FEETYPENAME from vi_contract_fund_fund_charge where FUNDFUNDCHARGEPLAN in( select id from vi_contract_fund_fund_plan"; 
		sql +=	  " where contractid ='"+contractid+"'";
		sql +=	  " and PLANMONEY=FACTMONEY)";
		sql +=	  " union all"; 
//			      --未回收/部分回收从计划表里面查询
		sql +=	  " select PLANDATE dates,case PAYTYPE when 'pay_type_in' then PLANMONEY else -PLANMONEY end money  ,FEETYPE,FEETYPENAME from vi_contract_fund_fund_plan"; 
		sql +=	  " where contractid ='"+contractid+"'";
		sql +=	  " and PLANMONEY<>FACTMONEY";
		sql +=	  " union all";
		sql +=	  "	select  case isdate(tt.plandate,'yyyy-MM-dd') when 0 then to_char(last_day(to_date(substr(tt.plandate,0,7),'yyyy-MM')),'yyyy-MM-dd') when 1 then  tt.plandate end datas, 0 money, 'addmonth' FEETYPE,'补齐月份' FEETYPENAME from (";
		sql +=	  " select cash_date||'"+condition.getStartDate().substring(7, 10)+"' plandate,0 flowin,'',0,'',0 from cash_help where cash_date >= substr('"+condition.getLeaseAmtDate()+"',0,7) and cash_date<=substr('"+condition.getEndDate()+"',0,7))tt";
		sql +=	  ")  group by substr(dates,0,7) order by substr(dates,0,7)";
		logger.info(sql);
		List<Map<String, String>> cashlist = conn.executeQuery(sql, "获取最新现金流···");
		List<BigDecimal> returnList = new ArrayList<BigDecimal>();
		for (Map<String, String> rs : cashlist){
			String money = rs.get("sum");
			money = money==null||money==""?"0":money;
			returnList.add(new BigDecimal(money));
		}
		return returnList;
	}
}
