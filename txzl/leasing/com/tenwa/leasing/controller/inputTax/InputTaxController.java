package com.tenwa.leasing.controller.inputTax;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.inputTax.InputTaxService;

@Controller(value = "inputController")
@RequestMapping(value = "/**/acl")
public class InputTaxController {
	
	@Resource(name = "inputTaxService")
	private InputTaxService inputtaxservice;
	
	@RequestMapping(value = "/generateinput.acl")
	@ResponseBody
    public void generateInputTax(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		inputtaxservice.createInputTax(model);
    }
}
