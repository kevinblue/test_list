package com.tenwa.leasing.controller.insuremanage;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.insuremanage.InsuranceCoverService;


@Controller(value = "InsuranceCoverController")
@RequestMapping(value = "/**/acl")
/**
 * 保险覆盖融资期限
 * @Title: InsuranceCoverController.java
 * @package: com.tenwa.leasing.controller.insuremanage
 * @author: tpf
 * @date 2014年12月10日 下午5:30:43 
 * @version V5.1
 */
public class InsuranceCoverController {
	@Resource(name = "insuranceCoverService")
	private InsuranceCoverService baseService;
	/**
	  * 保险覆盖融资期限
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/saveInsuranceCover.acl")
	@ResponseBody
	public String saveOverdueDunningInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.baseService.saveInsuranceCover(model);
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
