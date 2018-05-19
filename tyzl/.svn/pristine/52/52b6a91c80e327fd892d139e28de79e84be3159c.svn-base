package com.tenwa.leasing.controller.ebank;

 

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.service.ebank.FundEbankService;
 
@Controller(value = "fundEbankControllers")
@RequestMapping(value = "/**/acl")
public class FundEbankControllers extends BaseController {

	@Resource(name="fundEbankService")
	private FundEbankService fundEbankService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
 
	//处理废弃请求方法
	@RequestMapping(value = "/delEbankInfo.acl")
	@ResponseBody
	public String delEbankInfo(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		 Map<String,String> model =new HashMap<String,String>();
		 String id = request.getParameter("id");
		 String isdiuse = request.getParameter("isdiuse");
		 model.put("id", id);
		 model.put("isdiuse",isdiuse);
		 return this.fundEbankService.delEbankInfoImpl(model);
	}
	
 
}

