package com.tenwa.leasing.controller.invoice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.fund.ContractFundPaymentInterfaceLog;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.service.invoice.VatInvoiceService;

@Controller(value = "fundRentInvoiceController")
@RequestMapping(value = "/**/eleasing")
public class FundRentInvoiceController {

	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "vatInvoiceService")
	private VatInvoiceService vatInvoiceService;
	/**
	 * 修改发票回导页面信息---单条数据修改
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateFundRentInvoice.acl")
	@ResponseBody
	public JsonReturnResult updateFundRentInvoice(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			System.out.println(model);
			//String rowdate=model.get("rowdate").toString();
			JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
			try{
				this.vatInvoiceService.updateFundRentInvoice(model);
			} catch (Exception e) {
				e.printStackTrace();
				returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
				returnResult.setContent(e.getMessage());
			}
			
			return returnResult;
	}

	/**
	 * 红冲作废发票（资金租金一起判断）
	 * @param request
	 * @param response        
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hcFundRentInvoice.acl")
	@ResponseBody
	public JsonReturnResult hcFundRentInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS, "");
		try {
			this.vatInvoiceService.saveOrUpdateFundRentInvoice(model);
			returnResult.setContent("红冲发票成功!");
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	//导入发票信息时更新发票（租金和资金的字段信息）
	@RequestMapping(value = "/updateInvoiceImportExcel.action")
	public String updateInvoiceImportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String currentTableId = modelData.get("currentTableId");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("tableImportExcel");
		
		String returnInfo="";
		try {
			returnInfo = this.vatInvoiceService.updateInvoiceImportExcel(multipartFile, modelData);
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo="{message:\"导入失败"+e.getMessage()+"\",tabledate:\"\"}";
		}
		String jscallback="importMiniTableCallBack";
		if(modelData.containsKey("jscallback")){
			jscallback=modelData.get("jscallback");
		}
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent."+jscallback+"('" + returnInfo + "','" + currentTableId
				+ "');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}
	
	protected void ajaxReturn(HttpServletResponse response, String returnInfo) throws IOException {
		response.setContentType("textml;charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(returnInfo);
		out.flush();
		out.close();
	}

}
