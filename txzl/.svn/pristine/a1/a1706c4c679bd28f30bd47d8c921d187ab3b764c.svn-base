package com.tenwa.business.controller;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.RelationMap;
import com.tenwa.business.entity.Relationship;
import com.tenwa.business.service.RelationService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;


@Controller(value = "relationController")
@RequestMapping(value = "/**/acl/relation")
public class RelationController {
	private static final Logger log = LoggerFactory.getLogger(RelationController.class);

	@Resource(name = "relationService")
	private RelationService relationService;

	@RequestMapping(value = "/queryAllRelation.action")
	@ResponseBody
	public String listAllRelations() throws Exception {
		JSONArray treeJson = new JSONArray();
		JSONObject rootJson = new JSONObject();
		rootJson.put("id", "-1");
		rootJson.put("text", WebUtil.getMessageWithDefault("com.business.relationship.tree.root", "关系"));
		rootJson.put("state", "open");
		JSONObject attributesJson = new JSONObject();
		attributesJson.put("type", "ROOT");
		System.out.println(attributesJson.toString());
		rootJson.put("attributes", attributesJson);
		rootJson.put("children", relationService.listAllRelations());
		treeJson.put(rootJson);
		return treeJson.toString();
	}
	
	@RequestMapping(value = "/getDeptTree.action")
	@ResponseBody
	public String getDetpTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String queryText = model.get("queryText");
		String treeDataJson = this.relationService.getRelationMapTree();

		return treeDataJson;
	}
	@RequestMapping(value = "/saveMapItem.action")
	@ResponseBody
	public String saveMapItem(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> model;
		JSONObject retData = new JSONObject();
		try {
			model = QueryUtil.getRequestParameterMapByAjax(request);
			String relationId = StringUtil.nullToString(model.get("map_relation_id"));
			String mapId = StringUtil.nullToString(model.get("map_id"));

			String leftItemIds = StringUtil.nullToString(model.get("leftSelected"));
			String rightItemIds = StringUtil.nullToString(model.get("rightSelected"));
			if ("".equals(relationId)) {
				retData.put("error", "请先选择关系");
				return retData.toString();
			}

			return this.relationService.saveMapItem(relationId, mapId, model.get("map_name"), leftItemIds, rightItemIds)
					.toString();

		} catch (Exception e) {
			log.error("", e);
			try {
				retData.put("error", e.getMessage());
				retData.put("mapId", "");
			} catch (JSONException e1) {
				log.error("", e1);
			}

		}
		return retData.toString();
	}
	
	@RequestMapping(value="/getMapItems.action")
	@ResponseBody
	public String getMapItems(@RequestParam String mapId){
		return this.relationService.getItems(mapId);
	}
	
	@RequestMapping(value = "/saveMap.action")
	@ResponseBody
	public String saveMap(HttpServletRequest request, HttpServletResponse response) {
		try{
			
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.relationService.saveMap(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	@RequestMapping(value = "/saveRelation.action")
	@ResponseBody
	public String saveRelation(HttpServletRequest request, HttpServletResponse response) {
		try{
			
			Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
			this.relationService.saveRelation(model);
		}catch (Exception e) {
			return "添加失败!["+e.getMessage()+"]";
		}
		return null;
	}
	@RequestMapping(value = "/removeRelation.action")
	@ResponseBody
	public String removeRelation(@RequestParam String id, @RequestParam String type) {
		try {
			this.relationService.removeRelation(id,type);
		} catch (Exception e) {
			log.error("对应关系删除失败：----------", e);
			return "删除失败!["+e.getMessage()+"]";
		}
		return "";
	}
}
