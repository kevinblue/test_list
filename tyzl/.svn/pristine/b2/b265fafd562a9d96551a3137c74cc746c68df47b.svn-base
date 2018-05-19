package com.tenwa.leasing.controller.onhire;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.inject.internal.Maps;
import com.tenwa.business.dao.BaseDao;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.contract.contractOnhire.ContractOnhireService;
import com.tenwa.leasing.service.cust.CustAccountService;
import com.tenwa.leasing.service.cust.CustService;
import com.tenwa.leasing.service.cust.CustValidatorService;

@Controller(value = "ContractOnhireController")
@RequestMapping(value = "/**/acl")
/**
 * 客户验证
 * @Title: CustValidatorController.java
 * @package: com.tenwa.leasing.controller.cust.common
 * @author: tpf
 * @date 2014年11月20日 下午6:03:17 
 * @version V5.1
 */
public class ContractOnhireController {
	@Resource(name = "contractOnhireService")
	private ContractOnhireService contractOnhireService;
	/**
	  * 检查设备序列号是否重复
	  * @param request
	  * @param response
	  * @return 返回响应信息
	  * @throws Exception
	  */ 
	@RequestMapping(value = "/checkEquipSerial.acl")
	@ResponseBody
	public String checkEquipSerial(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			String id = model.get("ids");
			String equipid = model.get("equipids");
			return contractOnhireService.checkEquipSerial(id,equipid);
		}catch (Exception e) {
			return "查询失败!["+e.getMessage()+"]";
		}
	}
}
