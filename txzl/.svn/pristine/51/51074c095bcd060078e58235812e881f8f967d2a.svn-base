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
import com.tenwa.leasing.service.invoice.TaxInvoiceService;


/**   
*    
* 项目名称：tls5.1   
* 类名称：TaxFundInvoiceController   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年11月24日 下午5:39:28   
* @version        
*/
@Controller(value = "taxInvoiceController")
@RequestMapping(value = "/**/acl")
public class TaxInvoiceController {
	private Logger logger=Logger.getLogger(TaxInvoiceController.class);
	@Resource(name = "taxInvoiceService")
	private TaxInvoiceService taxInvoiceService;
	
	/**
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitConfirmInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitConfirmInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				this.taxInvoiceService.submitConfirmInvoice(model);
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
	@RequestMapping(value = "/submitRentConfirmInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitRentConfirmInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try {
				this.taxInvoiceService.submitRentConfirmInvoice(model);
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
	@RequestMapping(value = "/deleteInvoiceInfo.acl")
	@ResponseBody
	public JsonReturnResult deleteInvoiceInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.taxInvoiceService.deleteInvoiceInfo(model);
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
