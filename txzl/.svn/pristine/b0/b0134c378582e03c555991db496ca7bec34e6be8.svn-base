package com.tenwa.leasing.controller.loaninterestpayment;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.loaninterestpayment.LoanInterestPaymentService;

@Controller(value="loanInterestPaymentController")
@RequestMapping(value = "/**/acl")
public class LoanInterestPaymentController {

	@Resource(name = "loanInterestPaymentService")
	private LoanInterestPaymentService loanInterestPaymentService;
	
	@RequestMapping(value = "/addInterestRepaymentIncome.acl")
	@ResponseBody
	public String interestRepaymentIncome(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);			
					
			return loanInterestPaymentService.addInteretPaymentToIncome(request, model);
		}catch (Exception e) {
			return "核销失败!["+e.getMessage()+"]";
		}
	}
	
	
	
}
