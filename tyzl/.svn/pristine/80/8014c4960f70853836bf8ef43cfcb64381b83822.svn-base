package com.tenwa.leasing.controller.invoice;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.invoice.VatInvoiceService;


/**   
*    
* 项目名称：tls5.1   
* 类名称：VatInvoiceController   
* 类描述：   
* 创建人：luoshuai   
* 创建时间：2014年11月24日 下午5:39:28   
* @version        
*/
@Controller(value = "vatInvoiceController")
@RequestMapping(value = "/**/acl")
public class VatInvoiceController {
	
	@Resource(name = "vatInvoiceService")
	private VatInvoiceService vatInvoiceService;
	
	/**
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitVatInvoice.acl")
	@ResponseBody
	public JsonReturnResult submitVatInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try{
				this.vatInvoiceService.submitVatInvoice(model);
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
	@RequestMapping(value = "/saveVatInvoice.acl")
	@ResponseBody
	public JsonReturnResult saveVatInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try{
				this.vatInvoiceService.saveVatInvoice(model);
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
	@RequestMapping(value = "/backVatInvoice.acl")
	@ResponseBody
	public JsonReturnResult backVatInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try{
				this.vatInvoiceService.backVatInvoice(model);
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
	@RequestMapping(value = "/saveVatInvoiceContract.acl")
	@ResponseBody
	public JsonReturnResult saveVatInvoiceContract(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try{
				this.vatInvoiceService.saveVatInvoiceContract(model);
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
	@RequestMapping(value = "/updateVatInvoiceContract.acl")
	@ResponseBody
	public JsonReturnResult updateVatInvoiceContract(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try{
			this.vatInvoiceService.updateVatInvoiceContract(model);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
}
