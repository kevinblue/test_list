package com.reckon.rentcharge.web;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.ObjectConvertUtils;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.Conn;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.RentChargeDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.rentcharge.service.RentChargeService;
import com.reckon.rentcharge.service.impl.DeviRentChargeServiceImpl;
import com.reckon.rentcharge.service.impl.RentChargeServiceImpl;
import com.reckon.util.DictTools;
import com.reckon.util.TbBeanTools;
import com.reckon.util.Tools;
import com.tenwa.business.dao.BaseDao;

/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Mar 23, 2011
 * @desc  (租金计划变更,接受页面参数,并实现变更内容)
 */
public class RentChargeDo {
	Logger logger = Logger.getLogger(RentChargeDo.class);
	
	/**
	 * 持久化服务
	 */
	@Resource
	private BaseDao baseDao;
	
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	
	/**
	 * 
	 *  (租金计划变更方法,接受页面参数)
	 * 
	 * @param aj
	 *            租金计划变更Bean
	 * @param calType
	 *            流程名 项目为proj_process合同为cont_process
	 */
	public Hashtable<String, Object> rentChargeCal(AdjustBean adb, String calType, String[] rentAdjustList) {

		logger.debug("进入变更方法:"+calType);
		Hashtable<String, Object> ht = new Hashtable<String, Object>();
		ht.put("isSucess", "false");
		String error = "";
		try {
			logger.debug("测算类型:"+adb.getAdjustType());
			// 合同中途终止修改最后一起租金计划
			/**
			 * 上方所做的合同终止操作除了记录fund_rent_adjust有效外，其余操作舍弃
			 * OCC合同终止需求
			 * 1.结算偿还金支付日
			 * 1.小于等于 约定终止日 的租金都为已收，大于约定终止日为未收 
			 * 2.结算偿还金支付日 作为最后一期偿还日期
			 * 3.添加：商定租金字段  默认=折现租金值
			 * 4.最后一期租金值=商定租金 
			 * 5.本金  =  商定租金 < 未到期本金:商定租金?未到期本金
			 * 6.利息 = 商定租金 - 未到期本金 > 0 :商定租金 - 未到期本金 ? 0
			 * 7.增加租金恢复按钮，可以撤销已经测算的折现操作
			 */
			//这里应该根据合同号对应contract_info表中的UUID去查询租金计划
			Conn conn = new Conn(); 
			String uuid = adb.getContractId();
		    logger.debug("contractInfo_id:"+uuid);
			if (!Tools.isNullOrEmpty(adb.getContractId()) && adb.getAdjustType().equals("his_contract_end")) {// 合同中途终止
				// 保存AdjustBean
				RentChargeDAOImpl rcd = new RentChargeDAOImpl();
				try {
					rcd.deleteAjust(adb);
				} catch (Exception e) {
					ht.put("isSucess", "false");
					error += "删除fund_rent_adjust出错:" + e.toString();
					return ht;
				}
				try {
					int ini = 0;
					ini = rcd.addAjust(adb);
					if (ini == 0) {
						ht.put("isSucess", "false");
						error += "没有成功添加fund_rent_adjust记录!";
					}
				} catch (Exception e) {
					ht.put("isSucess", "false");
					// 添加捕获到的错误信息
					error += "添加fund_rent_adjust出错:" + e.toString();
					return ht;
				}
				
				
				RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
				// 正式表租金计划
				TabCalBean tcb = TbBeanTools.getTabInfo(calType);
				tcb = TbBeanTools.getTabInfo(calType, adb);
				//欧力士合同终止测算处理
				rpcd.updateDataByDiscussrent(adb, tcb);
				FundRentPlanBean frpTemp = rpcd.getRentAndDateByTcb(tcb, 1);
				// 获得condition表信息
				ConditionBean cdb = new ConditionBean();// 交易结构表
				String wheresql = tcb.getCondition_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
				if (adb.getOnHireId() != null && !adb.getOnHireId().equals("")) {
					wheresql += " and onHire_id='" + adb.getOnHireId() + "'";
				}
				//( 根据查取交易结构信息)
				ConditionDAOImpl cddao = new ConditionDAOImpl();
				//logger.info("( 根据查取交易结构信息):"+wheresql);
				cdb = cddao.getConditionBeanByContract(wheresql, tcb);
				cdb = DictTools.getReversDict(cdb);
				if("his_contract_end".equals(adb.getAdjustType())){//提前终止
					Map<String, String> queryMainObjectMap = new HashMap<String, String>();
					queryMainObjectMap.put("contract_id", adb.getContractInfoId());
					queryMainObjectMap.put("feetypein", "feetype10");
					String putstr = adb.getFundputstr();
					List<FundPutPlan> fpps=ObjectConvertUtils.convertObjectByJson(FundPutPlan.class, putstr);
					cdb.setFpps(fpps);
//					cdb.setFpps(fpps);
				}
				cdb.setContractId(adb.getContractInfoId());
				ht = RentCalculateHelper.createFundPlanCashIrr(cdb, tcb, null, frpTemp,true);
				ht.put("isSucess", "true");
			}else{
				RentChargeService rcs = null;
				if (null == rentAdjustList) {// 正常的租金计划变更,
					rcs = new RentChargeServiceImpl();
				} else {// 不规则租金进化变更,租金调整按钮
					rcs = new DeviRentChargeServiceImpl();
				}
				logger.debug("流程号:"+adb.getDocId());
				ht.put("contractId", uuid);
				ht = rcs.rentChargeCal(adb, calType, ht, rentAdjustList);
			}
		} catch (Exception e) {
			ht.put("isSucess", "false");
			error += e.toString();
			logger.info("变更测算出现异常...");
			e.printStackTrace();
		}
		ht.put("error", error);
		return ht;
	}
}
