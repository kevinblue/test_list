package com.tenwa.leasing.controller.cust;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.service.cust.CustRelatedPersonHisService;
import com.tenwa.leasing.service.cust.CustRelatedPersonService;
import com.tenwa.leasing.service.cust.CustService;

@Controller(value = "custRelatedPersonHisController")
/**
 * 重要个人信息历史记录
 * @Title: CustSalesInfoController.java
 * @package: com.tenwa.leasing.controller.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:22:43 
 * @version V5.1
 */
@RequestMapping(value = "/**/acl")
public class CustRelatedPersonHisController {
	@Resource(name = "custRelatedPersonHisService")
	private CustRelatedPersonHisService baseService;

	/**
	  * 修改重要个人信息
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/updateCustRelatedPersonHis.acl")
	@ResponseBody
	public JsonReturnResult updateCustRelatedPersonHis(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.updateCustRelatedPersonHis(model);
		}catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;		
	}

}
