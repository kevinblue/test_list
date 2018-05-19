package com.reckon.util;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.TabCalBean;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.utils.WorkflowUtil;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 此类用于根据流程类型得出，交易结构，租金计划所用的表信息)
 */
public class TbBeanTools {
	static Logger logger = Logger.getLogger(TbBeanTools.class);

	/**
	 * 
	 *  (  根据交易结构信息，测算类型得到测算所涉及的表信息)
	 * 
	 * @param calType
	 * @param cb
	 * @return
	 * @throws Exception 
	 */
	public static TabCalBean getTabInfo(String calType, ConditionBean cb) throws Exception {

		logger.info("进入方法:getTabInfo");
		TabCalBean tcb = new TabCalBean();
		tcb = getTabInfo(calType);
		if(tcb.getCalType().equals("quoted_price")){
			tcb.setContOrProjCValue(cb.getCustId());
		}else{
			tcb.setContOrProjCValue(cb.getProjId());
		}
		tcb.setDocId(cb.getDocId());
		// 在修改统一调用getTabInfo(calType) 方法是 在这个方法里客户报价和其他地方一点不同
		// 如果在后续测测试中没用到可以删除
		if ("quoted_price".equals(calType)) {
			tcb.setContOrProjCName("cust_id");
		}
		// 如果是多次起租而且是合并计算,则要把是否合并计算值设置为是
		// && !cb.getLastCorpus().equals("")
		if ("onHire_more_process".equals(calType)) {
			tcb.setIs_merger("是");
		} else {
			tcb.setIs_merger("否");
		}
		tcb.setUserId(cb.getCreator());// 登陆人
		logger.info("方法getTabInfo处理结束");
		logger.debug(tcb.toString());
		return tcb;
	}
	
	/**
	 * 
	 *  (  根据变更bean信息，测算类型得到测算所涉及的表信息)
	 * @param calType
	 * @param ajb
	 * @return
	 * @throws Exception
	 */
	public static TabCalBean getTabInfo(String calType,AdjustBean ajb) throws Exception {

		// logger.info("进入方法:getTabInfo");
		TabCalBean tcb = new TabCalBean();
		tcb = getTabInfo(calType);
		tcb.setContOrProjCValue(ajb.getContractId());
		tcb.setDocId(ajb.getDocId());
		if (ajb.getOnHireId() != null && !ajb.getOnHireId().equals("")) {
			tcb.setOnHire_id(ajb.getOnHireId());
		}
		tcb.setUserId(ajb.getCreator());// 登陆人
		// logger.info("方法getTabInfo处理结束");
		return tcb;
	}
	
	/**
	 * 
	 * 根据calType 类型返回在该类型下查询的表和字段名 该方法流程类型返回要和该类中其他重载方法一致
	 * <BR>在这里返回的都是表名,可以通过tabcalbean中的方法转换为视图或者正式表
	 * <BR>该方法中会吧userid存为查询客户名的表,实际上应该为当前登录人所以需要在其他方法中覆盖
	 * @author 孙传良
	 * @param calType(流程类型:项目：proj_process;合同：cont_process;
	 *<BR>起租：onHire_process;多次起租只读onHire_ready_process
	 *<BR>项目只读:proj_ready_process;合同只读:cont_ready_process;客户:quoted_price)
	 *<BR>proj_process				--vi_proj_condition_temp      --vi_fund_rent_plan_proj_temp
	 *<BR>proj_ready_process		--vi_proj_condition_temp      --vi_fund_rent_plan_proj_temp
	 *<BR>cont_process				--vi_contract_condition_temp  --vi_fund_rent_plan_temp
	 *<BR>cont_ready_process		--vi_contract_condition_temp  --vi_fund_rent_plan_temp
	 *<BR>onHire_process			--vi_contract_condition_temp  --vi_fund_rent_plan_before->vi_fund_rent_plan_temp
	 *<BR>onHire_more_process		--vi_onHire_condition_temp    --vi_fund_rent_plan_onHire_temp
	 *<BR>onHire_more_ready_process	--vi_onHire_condition_temp    --vi_fund_rent_plan_onHire_temp
	 *<BR>quoted_price				--vi_quot_price_condition     --vi_quot_price_rent_plan
	 *<BR>quoted_ready_price		--vi_quot_price_condition     --vi_quot_price_rent_plan
	 * @return TabCalBean 返回操作表的bean类
	 */
	public static TabCalBean getTabInfo(String calType) {
		TabCalBean tcb = new TabCalBean();
		tcb.setCalType(calType);
		tcb.setFundFundPlan_tb("FUND_FUND_PLAN_TEMP");
		if ("proj_process".equals(calType) || "proj_ready_process".equals(calType)) {// 项目和项目只读都是来自同一个视图
			tcb.setContOrProjCName("proj_id");
			
			tcb.setCondition_tb("proj_condition_temp");
			tcb.setFinacCashTb("proj_fina_cash_detail_temp");
			tcb.setContractCashTb("proj_cash_detail_temp");
			tcb.setRentPlan_tb("proj_fund_rent_plan_temp");
			tcb.setRentFinaPlan_tb("proj_fina_rent_plan_temp");// 财务租金计划
			tcb.setUserId("vi_proj_info");// 用来查询客户名的视图
		} else if ("cont_process".equals(calType) || "cont_ready_process".equals(calType)) {// 合同
			tcb.setContOrProjCName("contract_id");
			
			tcb.setCondition_tb("contract_condition_temp");
			tcb.setContractCashTb("contract_cash_detail_temp");
			tcb.setFinacCashTb("contract_fina_cash_detail_temp");
			tcb.setRentPlan_tb("contract_fund_rent_plan_temp");
			tcb.setRentFinaPlan_tb("contract_fina_rent_plan_temp");// 财务租金计划
			tcb.setUserId("vi_contract_info");
		} else if ("quoted_price".equals(calType) || "quoted_ready_price".equals(calType)) {// 客户报价
			tcb.setContOrProjCName("cust_id");
			
			tcb.setCondition_tb("cust_condition");
			tcb.setContractCashTb("cust_cash_detail");
			tcb.setFinacCashTb("cust_fina_cash_detail");
			tcb.setRentPlan_tb("cust_fund_rent_plan");
			tcb.setRentFinaPlan_tb("cust_fina_rent_plan");// 财务租金计划
			tcb.setUserId("vi_cust_all_info_complete");
			tcb.setFundFundPlan_tb("CUST_FUND_FUND_PLAN");
		} else if ("onHire_process".equals(calType)) {// 起租时
			tcb.setContOrProjCName("contract_id");
			
			tcb.setCondition_tb("contract_condition_temp");
			tcb.setContractCashTb("contract_cash_detail_temp");
			tcb.setFinacCashTb("contract_fina_cash_detail_temp");
			tcb.setRentPlan_tb("contract_fund_rent_plan_temp");
			tcb.setRentFinaPlan_tb("contract_fina_rent_plan_temp");// 财务租金计划
			tcb.setUserId("vi_contract_info");
		} else if ("onHire_more_process".equals(calType) || "onHire_more_ready_process".equals(calType)) {// 多次起租时
			tcb.setContOrProjCName("contract_id");
			
			tcb.setCondition_tb("contract_condition_more_temp");
			tcb.setContractCashTb("contract_cash_detail_more_temp");
			tcb.setFinacCashTb("contract_fina_cash_detail_more_temp");
			tcb.setRentPlan_tb("contract_fund_rent_plan_more_temp");
			tcb.setRentFinaPlan_tb("contract_fina_rent_plan_more_temp");// 财务租金计划
			tcb.setUserId("vi_contract_info");
		} else {// 默认为合同
			tcb.setContOrProjCName("contract_id");
			
			tcb.setCondition_tb("contract_condition_temp");
			tcb.setContractCashTb("contract_cash_detail_temp");
			tcb.setFinacCashTb("contract_fina_cash_detail_temp");
			tcb.setRentPlan_tb("contract_fund_rent_plan_temp");
			tcb.setRentFinaPlan_tb("contract_fina_rent_plan_temp");// 财务租金计划
			tcb.setUserId("vi_contract_info");
		}
		return tcb;
	}
}
