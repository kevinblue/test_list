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
import com.tenwa.leasing.service.invoice.TaxFundService;

/**
 * 
 * 项目名称：tls5.1 类名称：TaxFundInvoiceController 类描述： 创建人：rovine 创建时间：2014年11月24日
 * 下午5:39:28
 * 
 * @version
 */
@Controller(value = "taxFundInvoiceController")
@RequestMapping(value = "/**/acl")
public class TaxFundInvoiceController {
	private Logger logger = Logger.getLogger(TaxFundInvoiceController.class);
	@Resource(name = "taxFundService")
	private TaxFundService taxFundService;

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitFundPlanInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitFundPlanInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			this.taxFundService.submitFundPlanInvoice(model);
			if (logger.isInfoEnabled()) {
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
	@RequestMapping(value = "/backFundPlanInvoice.acl")
	@ResponseBody
	public JsonReturnResult backFundPlanInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			Map<String, String> model = QueryUtil
					.getRequestParameterMapByAjax(request);
			this.taxFundService.backFundPlanInvoice(model);
			if (logger.isInfoEnabled()) {
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
	@RequestMapping(value = "/submitFundChargeInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitFundChargeInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			Map<String, String> model = QueryUtil
					.getRequestParameterMapByAjax(request);
			this.taxFundService.submitFundChargeInvoice(model);
			if (logger.isInfoEnabled()) {
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
	@RequestMapping(value = "/backFundChargeInvoice.acl")
	@ResponseBody
	public JsonReturnResult backFundChargeInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			Map<String, String> model = QueryUtil
					.getRequestParameterMapByAjax(request);
			this.taxFundService.backFundChargeInvoice(model);
			if (logger.isInfoEnabled()) {
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
	 * @function 资金实收不开票 提交
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitFundChargeNoInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitFundChargeNoInvoice(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			Map<String, String> model = QueryUtil
					.getRequestParameterMapByAjax(request);
			this.taxFundService.submitFundChargeNoInvoice(model);
			if (logger.isInfoEnabled()) {
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
	@RequestMapping(value = "/submitFundChargeReceipt.acl")
	@ResponseBody
	public JsonReturnResult submitFundChargeReceipt(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			Map<String, String> model = QueryUtil
					.getRequestParameterMapByAjax(request);
			this.taxFundService.submitFundChargeReceipt(model);
			if (logger.isInfoEnabled()) {
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
	@RequestMapping(value = "/backFundChargeReceipt.acl")
	@ResponseBody
	public JsonReturnResult backFundChargeReceipt(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			Map<String, String> model = QueryUtil
					.getRequestParameterMapByAjax(request);
			this.taxFundService.backFundChargeReceipt(model);
			if (logger.isInfoEnabled()) {
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
	@RequestMapping(value = "/deleteFundInvoiceOrReceipt.acl")
	@ResponseBody
	public JsonReturnResult deleteFundInvoiceOrReceipt(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			Map<String, String> model = QueryUtil
					.getRequestParameterMapByAjax(request);
			this.taxFundService.deleteFundInvoiceOrReceipt(model);
			if (logger.isInfoEnabled()) {
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
	@RequestMapping(value = "/addTaxFundHcInfo.acl")
	@ResponseBody
	public JsonReturnResult addTaxFundHcInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(
				JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			Map<String, String> model = QueryUtil
					.getRequestParameterMapByAjax(request);
			this.taxFundService.addTaxFundHcInfoInvoice(model);
			if (logger.isInfoEnabled()) {
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
