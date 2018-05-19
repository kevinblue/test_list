package com.reckon.controller.adjustInterest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.reckon.renttranrate.service.AjustInterestService;
import com.tenwa.business.controller.BaseController;

/**
 * @author Chennes
 * @version 1.0
 */
@Controller(value = "rollBackAdjustInterestController")
@RequestMapping(value = "/**/acl")
public class RollBackAdjustInterestController extends BaseController  {
	private Logger logger=Logger.getLogger(RollBackAdjustInterestController.class);
	@Resource(name="ajustInterestService")
	private AjustInterestService ajustInterestService;
	
	/**
	 * 保存回滚信息到临时表数据<br>
	 * @param request<br>
	 * @param response<br>
	 * @return string<br>
	 * @throws Exception
	 */
	@RequestMapping(value = "/rollBackAdjustinterest.acl")
	@ResponseBody
	public String rollBackAdjustInterest(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		//参数初始化
		Map<String,String> model = new HashMap<String,String>();
		String adjustId   = request.getParameter("adjustid");
		String docid      = request.getParameter("docid");
		String already_json_val = request.getParameter("already_json_val");
		already_json_val = URLDecoder.decode(already_json_val,"UTF-8");
		model.put("adjustId", adjustId);
		model.put("docid", docid);
		model.put("already_json_val", already_json_val);
		logger.info("回滚信息插入临时表数据:adjustId="+model.get("adjustId")+"docid:"+docid);
		logger.info("回滚信息插入临时表数据:already_json_val="+model.get("already_json_val"));
		 //* #.回滚开始处理
		 //* 1.央行调息记录表（调息前）-->到央行调息临时表FUND_ADJUST_INTEREST_CONTRACT to FUND_ADJUST_INTEREST_C_TEMP*/
		this.ajustInterestService.updateCopyFundAdjustInterestContractFromHisToTemp(model);
		 /* 2.租金计划his表（调息前）-->租金计划temp表 */
		this.ajustInterestService.updateCopyFundRentPlanFromHisToTemp(model);
		/* X.资金计划his表（调息前）-->资金计划temp表 */
		this.ajustInterestService.updateCopyFundFundPlanFromHisToTemp(model);
		// * 3.现金流his表（调息前）-->现金流temp表	调息\回滚暂不处理现金流表
		//this.ajustInterestService.updateCopyFundRentCashFromHisToTemp(model);
		// * 4.交易结构his表（调息前）-->交易结构temp表	调息\回滚暂不处理交易结构表
		//this.ajustInterestService.updateCopyConditionFromHisToTemp(model);
		
		return "回滚成功";
	}

	/**
	 * 撤销回滚信息临时表数据<br>
	 * @param request<br>
	 * @param response<br>
	 * @return		<br>
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeRollBackAdjustinterest.acl")
	@ResponseBody
	public String removeRollBackAdjustInterest(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		 // * #.撤销回滚
		 // * 1.删除 央行调息临时表FUND_ADJUST_INTEREST_C_TEMP
		 // * 2.删除租金计划temp表 
		 // * 3.删除现金流temp表
		 // * 4.删除交易结构temp表
		Map<String,String> model = new HashMap<String,String>();
		String adjustId   = request.getParameter("adjustid");
		String docid      = request.getParameter("docid");
		String already_json_val = request.getParameter("already_json_val");
		already_json_val = URLDecoder.decode(already_json_val,"UTF-8");
		model.put("adjustId", adjustId);
		model.put("docid", docid);
		model.put("already_json_val", already_json_val);
		logger.info("撤销回滚信息临时表数据:adjustId="+model.get("adjustId")+"docid:"+docid);
		logger.info("撤销回滚信息临时表数据:already_json_val="+model.get("already_json_val"));
		// * 1.删除 央行调息临时表FUND_ADJUST_INTEREST_C_TEMP
		this.ajustInterestService.removeFundAdjustInterestCTemp(model);
		// * 2.删除租金计划temp表 
		this.ajustInterestService.removeFundRentPlanFromHisToTemp(model);
		// *X.删除资金计划temp表 
		this.ajustInterestService.removeFundFundPlanFromHisToTemp(model);
		// * 3.删除现金流temp表
		//this.ajustInterestService.removeFundRentCashFromHisToTemp(model);
		// * 4.删除交易结构temp表
		//this.ajustInterestService.removeConditionFromHisToTemp(model);
		return "撤销回滚成功";
	}
	
}
