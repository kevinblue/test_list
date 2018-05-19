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
import com.tenwa.leasing.service.invoice.InvoiceToSajtService;
import com.tenwa.leasing.service.invoice.TaxRentService;

/**
 * 
 * 项目名称：tls5.1 类名称：TaxRentInvoiceController 类描述： 创建人：rovine 创建时间：2014年11月24日
 * 下午5:39:28
 * 
 * @version
 */
@Controller(value = "invoiceToSajtController")
@RequestMapping(value = "/**/acl")
public class InvoiceToSajtController {
	private Logger logger = Logger.getLogger(InvoiceToSajtController.class);
	@Resource(name = "invoiceToSajtService")
	private InvoiceToSajtService invoiceToSajtService;

	/**
	 * @param request
	 * @param response
	 *            生成发票
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/generateInvoiceToSajt.acl")
	@ResponseBody
	public JsonReturnResult generateInvoiceToSajt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			this.invoiceToSajtService.generateInoviceToSajt(model);
			returnResult.setContent("生成发票成功!");
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
	 *            打印发票
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/printInvoiceToSajt.acl")
	@ResponseBody
	public JsonReturnResult printInvoiceToSajt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			this.invoiceToSajtService.printInvoiceToSajt(model);
			returnResult.setContent("打印发票成功!");
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
	 *            作废发票
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/voidedInvoiceToSajt.acl")
	@ResponseBody
	public JsonReturnResult voidedInvoiceToSajt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			this.invoiceToSajtService.voidedInvoiceToSajt(model);
			returnResult.setContent("作废发票成功!");
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}


}
