package com.tenwa.leasing.controller.assetsnetmonitorapply;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.service.assetsnetmonitor.AssetNetMonitorApplyDetailService;


@Controller(value = "assetNetMonitorApplyDetailController")
/**
 * 资产网络监控申请详细
 * @Title: AssetNetMonitorDetailController.java
 * @package: com.tenwa.leasing.controller.assetsnetmonitor
 * @author: tpf
 * @date 2016年04月07日 上午11:19:21 
 * @version V5.1
 */
@RequestMapping(value = "/**/acl")
public class AssetNetMonitorApplyDetailController {

	@Resource(name = "assetNetMonitorApplyDetailService")
	private AssetNetMonitorApplyDetailService baseService;
	
	@RequestMapping(value="/updateAssetNetMonitorApplyDetail.acl")
	@ResponseBody
	public JsonReturnResult updateAssetNetMonitorApplyDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		Map<String, String> model = QueryUtil
				.getRequestParameterMapByAjax(request);
		try{
			this.baseService.updateAssetsNetMonitorApplyDetail(model);
			
		}catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;		
	}
}
