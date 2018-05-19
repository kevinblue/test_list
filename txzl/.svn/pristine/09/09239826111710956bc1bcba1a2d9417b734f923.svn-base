package com.tenwa.leasing.controller.rent;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.contract.ContractInfo;



/**
 * @author lichaojie
 * @date 2014-12-01
 * @info 
 */
@Controller(value="rentIncomeController")
@RequestMapping(value = "/**/acl")
public class RentIncomeController {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@RequestMapping(value = "/saveRentIncomeStepOneInfo.acl")
	@ResponseBody
	public String saveRentIncomeStepOneInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String processId = model.get("processId");
		String hireMoney = model.get("hireMoney");
		try{
			FundEbankProcess fep = this.baseService.findEntityByID(FundEbankProcess.class, processId);
			fep.setProcessAmount(new BigDecimal(hireMoney));
			this.baseService.updateEntity(fep);
			return "{result:'success',message:'更新网银进行流程表成功！'}";
		}catch(Exception e){
			e.printStackTrace();
			return "{result:'error',message:'更新网银进行流程表失败！'}";
		}
	}
}
