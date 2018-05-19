package com.tenwa.leasing.controller.fund.collection;



import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.fund.fundcollection.FundCollectionService;


@Controller(value = "fundCollectionController")
@RequestMapping(value = "/**/acl")
public class FundCollectionController {
	
	@Resource(name="tableService")
	private TableService  tableService;
	
	@Resource(name="fundCollectionService")
	private FundCollectionService fundCollectionService ;
	
	@RequestMapping(value = "/addEbankInfo.acl")
	@ResponseBody
	public String addEbankInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		try
		{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			return this.fundCollectionService.addEbankInfoImpl(model);
		}
		catch (Exception e) 
		{
			return "处理失败!["+e.getMessage()+"]";
		}
	}
	 
}
