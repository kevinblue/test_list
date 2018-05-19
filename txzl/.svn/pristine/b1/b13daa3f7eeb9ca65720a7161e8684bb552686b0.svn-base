package com.tenwa.business.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.service.ChartService;
import com.tenwa.kernal.utils.QueryUtil;

@Controller(value = "chartController")
@RequestMapping(value = "/**/chart")
public class ChartController extends BaseController
{
	
	 /**
	 * chartService:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 **/
	@Resource(name = "chartService")
	private ChartService chartService;

	
	 /**
	 * @method getChartData(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @returnType String
	 * @exception  
	 * @since      1.0.0
	 **/
	@RequestMapping(value = "/getChartData.action")
	public String getChartData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String xmlFileNameOrPath = model.get("xmlFileName");
		this.chartService.getChartXmlData(xmlFileNameOrPath, model, response.getOutputStream());
		return null;
	}

	
	 /**
	 * @method returnChart(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param request
	 * @param response
	 * @throws Exception
	 * @returnType void
	 * @exception  
	 * @since      1.0.0
	 **/
	@RequestMapping(value = "/returnChart.action")
	public void returnChart (HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String xmlFileNameOrPath = model.get("xmlFileName");
		this.chartService.getChartXmlData(xmlFileNameOrPath, model, response.getOutputStream());
	}
}