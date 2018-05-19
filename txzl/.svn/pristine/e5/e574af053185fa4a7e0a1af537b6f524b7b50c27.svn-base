package com.tenwa.leasing.controller.fund.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.fund.ContractFundPaymentInterfaceLog;
import com.tenwa.leasing.entity.fund.PurchaseContractFundFundPlan;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;

@Controller(value = "fundCommonController")
@RequestMapping(value = "/**/eleasing")
public class FundCommonController {

	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@RequestMapping(value = "/getFundCheckInfo.acl")
	@ResponseBody
	public String getFundCheckInfo(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{	
		/**
                     资金检验
        */
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String json_fund="[]";
		String json_ebank="[]";
		String message="成功";
		String result="";
		try {
			if(model.containsKey("fundplandids")){
				json_fund=fundCommMethod.getFundPlanInfoToCheck(model);
			}
			if(model.containsKey("ebankid") && model.containsKey("flowunid")){
				json_ebank=fundCommMethod.getEbankInfoToCheck(model);
			}
			result="{result:1,fund:"+json_fund+",ebank:"+json_ebank+",message:'"+message+"'}";
			System.out.println(result);
			//result="{result:1,fund:[{'id':'2c9ba3814a37fdc0014a38168e7f0040','factmoney':'0','overmoney':'2000','feeadjust':'0','planmoney':'3000'},{'id':'2c9ba3814a37fdc0014a38168e7f003f','factmoney':'0','overmoney':'500','feeadjust':'0','planmoney':'500'}],ebank:[],message:'成功'}";
		} catch (Exception e) {
			message=e.getMessage();
			result="{result:0,message:'"+message+"'}";
		}
		return result;
	}
	
	
	@RequestMapping(value = "/getRentCheckInfo.acl")
	@ResponseBody
	public String getRentCheckInfo(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{	
		/**
	         租金检验
	    */
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String json_fund="[]";
		String json_ebank="[]";
		String message="成功";
		String result="";
		try {
			if(model.containsKey("rentplandids")){
				json_fund=fundCommMethod.getRentPlanInfoToCheck(model);
			}
			if(model.containsKey("ebankid") && model.containsKey("flowunid")){
				json_ebank=fundCommMethod.getEbankInfoToCheck(model);
			}
			result="{result:1,rent:"+json_fund+",ebank:"+json_ebank+",message:'"+message+"'}";
			System.out.println(result);
			//result="{result:1,rent:[{'currentoverage':'22317.83','curinterestincome':'0','curpenaltyadjustincome':'0','curinterestadjustincome':'0','yearrate':'7.65','contractid':'402882a64a332c2e014a337a876a000f','curpenaltyincome':'0','penaltyoverage':'636.06','plandate':'2014-10-26','id':'402882a64a332c2e014a337caa180099','rentlist':'1','corpus':'22317.83','interest':'0','currentincome':'0','curcorpusincome':'0','curcorpusoverage':'22317.83','rent':'22317.83','penalty':'636.06','curinterestoverage':'0'},{'currentoverage':'22317.83','curinterestincome':'0','curpenaltyadjustincome':'0','curinterestadjustincome':'0','yearrate':'7.65','contractid':'402882a64a332c2e014a337a876a000f','curpenaltyincome':'0','penaltyoverage':'100.09','plandate':'2014-11-20','id':'402882a64a332c2e014a337caa2d00a8','rentlist':'2','corpus':'17360.11','interest':'4957.72','currentincome':'0','curcorpusincome':'0','curcorpusoverage':'17360.11','rent':'22317.83','penalty':'357.09','curinterestoverage':'4957.72'}],ebank:[],message:'成功'}";
		} catch (Exception e) {
			message=e.getMessage();
			result="{result:0,message:'"+message+"'}";
		}
		return result;
	}
	
	@RequestMapping(value = "/removePurchaseFundPlan.acl")
	@ResponseBody
	public JsonReturnResult saveRemovePurchaseFundPlan(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{	
		/**
	       采购合同 付款计划信息  删除
	    */
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		String  purfundplanid=model.get("purfundplanid");
	//	String str[]=purfundplanid.split(",");
	//	for(String id:str){
			try {
				this.tableService.removeEntityById(PurchaseContractFundFundPlan.class, purfundplanid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
	//	}
		return returnResult;
	}
}
