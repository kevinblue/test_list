package com.tenwa.business.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.ResourceService;

/**
 * @author Jason
 * @date 2013-6-25
 * @info 资源控制类型的controller
 */
@Controller(value = "resourceController")
@RequestMapping(value = "/**/resource")
public class ResourceController extends BaseController {

	@Resource(name = "resourceService")
	private ResourceService resourceService;

	@RequestMapping(value = "/getProcessAction.action")
	@ResponseBody
	public List<Map<String,String>> getTableData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List<Map<String,String>> list = resourceService.getProcessAction(request);
		return list;
	}
	
	@RequestMapping(value = "/getAllWorkFlowName.action")
	@ResponseBody
	public List<Map<String,String>> getAllWorkFlowName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String key = request.getParameter("key");
		List<Map<String,String>> list = resourceService.getAllWorkFlowName(key);
		return list;
	}
	
	@RequestMapping(value = "/getWorkFlowPointByID.action")
	@ResponseBody
	public List<Map<String,String>> getWorkFlowPointByID(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		List<Map<String,String>> list = resourceService.getWorkFlowPointByID(id);
		return list;
	}
	@RequestMapping(value = "/getEntityFieldName.action")
	@ResponseBody
	public List<Map<String,String>> getEntityFieldName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String entityName = request.getParameter("id");
		List<Map<String,String>> list = resourceService.getEntityFieldName(entityName);
		return list;
	}
	
	@RequestMapping(value = "/getProcessStatus.action")
	@ResponseBody
	public Map<String, Object> getProcessStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		int pagesize = Integer.parseInt(request.getParameter("pagesize"));
		Map<String, Object> map = resourceService.getProcessStatus(page, pagesize);
		return map;
	}

}
