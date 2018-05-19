package com.tenwa.leasing.controller.invoice;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.invoice.TaxRentService;


/**   
*    
* 项目名称：tls5.1   
* 类名称：TaxRentInvoiceController   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年11月24日 下午5:39:28   
* @version        
*/
@Controller(value = "taxRentInvoiceController")
@RequestMapping(value = "/**/acl")
public class TaxRentInvoiceController {
	private Logger logger=Logger.getLogger(TaxRentInvoiceController.class);
	@Resource(name = "taxRentService")
	private TaxRentService taxRentService;
	
	/**
	 * @param request
	 * @param response
	 * 本金一次性开票
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitRentPlanCorpusOneInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitRentPlanCorpusOneInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				this.taxRentService.submitRentPlanCorpusOneInvoice(model);
				if(logger.isInfoEnabled()){
					 logger.info("提交成功!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
			return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitRentPlanInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitRentPlanInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				this.taxRentService.submitRentPlanInvoice(model);
				if(logger.isInfoEnabled()){
					 logger.info("提交成功!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
			return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @function 资金计划开票 退回
	 * @throws Exception
	 */
	@RequestMapping(value = "/backRentPlanInvoice.acl")
	@ResponseBody
	public JsonReturnResult backRentPlanInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.backRentPlanInvoice(model);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	/**
	 * @param request
	 * @param response
	 * @return
	 * @function 资金实收开票 提交
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitRentIncomeInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitRentIncomeInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.submitRentIncomeInvoice(model);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @function 资金实收开票   
	 * @throws Exception
	 */
	@RequestMapping(value = "/backRentIncomeInvoice.acl")
	@ResponseBody
	public JsonReturnResult backRentIncomeInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.backRentIncomeInvoice(model);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @function 资金实收开票 提交
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitRentIncomeNoInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitRentIncomeNoInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.submitRentIncomeNoInvoice(model);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitRentIncomeReceipt.acl")
	@ResponseBody
	public JsonReturnResult submitRentIncomeReceipt(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.submitRentIncomeReceipt(model);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @function 资金实收收据 
	 * @throws Exception
	 */
	@RequestMapping(value = "/backRentIncomeReceipt.acl")
	@ResponseBody
	public JsonReturnResult backRentIncomeReceipt(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.backRentIncomeReceipt(model);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @function 删除发票or收据 
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteRentInvoiceOrReceipt.acl")
	@ResponseBody
	public JsonReturnResult deleteRentInvoiceOrReceipt(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.deleteRentInvoiceOrReceipt(model);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @function 删除本金一次性开票发票
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCorpusOneInvoice.acl")
	@ResponseBody
	public JsonReturnResult deleteCorpusOneInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.deleteCorpusOneInvoice(model);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return 
	 * @function 资金开票红冲增加
	 * @throws Exception
	 */
	@RequestMapping(value = "/addTaxRentHcInfo.acl")
	@ResponseBody
	public JsonReturnResult addTaxRentHcInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxRentService.addTaxRentHcInfoInvoice(model);
			if(logger.isInfoEnabled()){
				logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
}
