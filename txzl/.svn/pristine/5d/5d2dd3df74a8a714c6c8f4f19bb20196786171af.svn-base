package com.tenwa.leasing.controller.ebank;

 

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.ebank.FundEbankImportService;
import com.tenwa.leasing.service.ebank.FundEbankService;
 
@Controller(value = "fundEbankImportControllers")
@RequestMapping(value = "/**/acl")
public class FundEbankImportControllers extends BaseController {

	@Resource(name="fundEbankService")
	private FundEbankService fundEbankService;
	
	@Resource(name="fundEbankImportService")
	FundEbankImportService fundEbankImportService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
 	/**
	 * 工商银行网银导入
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importExcelIDBC.action")
	public String importExcelIDBC(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String currentTableId = modelData.get("currentTableId");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("tableImportExcel");
		
		String returnInfo="";
		try {
			returnInfo=fundEbankImportService.importIDBCEbank(multipartFile, modelData);
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
	/**
	 * 南京银行网银导入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importExcelNJ.action")
	public String importExcelNJ(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String currentTableId = modelData.get("currentTableId");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("tableImportExcel");
		
		String returnInfo="";
		try {
			//returnInfo = this.baseService.importExcel(multipartFile, modelData);
			returnInfo=fundEbankImportService.importNJEbank(multipartFile, modelData);
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
	/**
	 * 民生银行网银导入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importExcelCMBC.action")
	public String importExcelCMBC(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String currentTableId = modelData.get("currentTableId");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("tableImportExcel");
		
		String returnInfo="";
		try {
			returnInfo=fundEbankImportService.importCMBCEbank(multipartFile, modelData);
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
	/**
	 * 交通银行网银导入
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/importExcelCOMM.action")
	public String importExcelCOMM(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String currentTableId = modelData.get("currentTableId");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("tableImportExcel");
		
		String returnInfo="";
		try {
			returnInfo=fundEbankImportService.importCOMMEbank(multipartFile, modelData);
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
}

