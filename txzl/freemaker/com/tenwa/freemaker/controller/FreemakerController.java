package com.tenwa.freemaker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.controller.BaseController;
import com.tenwa.freemaker.entity.FreeMakerEntity;
import com.tenwa.freemaker.entity.FreeMakerEntityColumn;
import com.tenwa.freemaker.service.FreemakerService;
import com.tenwa.kernal.utils.QueryUtil;

@Controller(value = "freemakerController")
@RequestMapping(value = "/**/freemaker")
public class FreemakerController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(FreemakerController.class);

	@Resource(name = "freemakerService")
	private FreemakerService freemakerService;

	@RequestMapping(value = "/getEntityJsonInfo.action")
	public String getFreeMakerEntities(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String  returnJsonInfo = this.freemakerService.saveOrQueryFreemakerEntities(model);
		this.output(response, returnJsonInfo);
		return null;
	}
	
	@RequestMapping(value = "/saveEntity.action")
	public String saveEntity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnInfo = "";
		try{
			Map<String, String> classFieldMapping = new HashMap<String, String>();
			String id = model.get("id");
			FreeMakerEntity entity = new FreeMakerEntity();
			if(id != null &&  id.length() > 0 ){
				entity = this.freemakerService.findEntityByID(FreeMakerEntity.class,id );
			}
			classFieldMapping.put("FreeMakerEntity", "id");
			this.freemakerService.copyAndOverrideExistedValueFromStringMap(model, entity, classFieldMapping, true);
			this.freemakerService.saveOrUpdateEntity(entity);
			returnInfo = entity.getId();
		}catch(Exception e){
			e.printStackTrace();
		}
		this.output(response, returnInfo);
		return null ;
	}
	@RequestMapping(value = "/removeEntity.action")
	public String removeEntity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String id = model.get("id");
		this.freemakerService.removeEntityById(FreeMakerEntity.class, id);;
		return null ;
	}
	@RequestMapping(value = "/saveEntityColumn.action")
	public String saveEntityColumn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnInfo = "";
		try{
			returnInfo = this.freemakerService.saveEntityColumn(model);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.output(response, returnInfo);
		return null ;
	}
	@RequestMapping(value = "/removeEntityField.action")
	public String removeEntityField(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String id = model.get("id");
		this.freemakerService.removeEntityById(FreeMakerEntityColumn.class, id);;
		return null ;
	}

	
	@RequestMapping(value = "/updatePosition.action")
	public String updatePosition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		this.freemakerService.updateFreemakerEntityPosition(model);
		return null ;
	}
	
	@RequestMapping(value = "/createEntity.action")
	public String createEntity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnInfo = "";
		try{
			returnInfo = this.freemakerService.createEntity(model);
			returnInfo = "success";
		}catch(Exception e){
			returnInfo = "操作失败";
			e.printStackTrace();
		}
		this.output(response, returnInfo);
		return null ;
	}
	
	@RequestMapping(value = "/createDataFromEntity.action")
	public String createDataFromEntity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnInfo = "";
		try{
			returnInfo = this.freemakerService.createDataFromEntity(model);
			returnInfo = "success";
		}catch(Exception e){
			returnInfo = "请先生成实体，然后再去映射到数据库！！！";
			e.printStackTrace();
		}
		this.output(response, returnInfo);
		return null ;
	}
	
	@RequestMapping(value = "/createDataByAllEntity.action")
	public String createDataByAllEntity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnInfo = "";
		try{
			this.freemakerService.createDataByAllEntity(model);
			returnInfo = "success";
		}catch(Exception e){
			returnInfo = "操作失败";
			e.printStackTrace();
		}
		this.output(response, returnInfo);
		return null ;
	}
}
