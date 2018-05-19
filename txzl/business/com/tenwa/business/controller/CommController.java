package com.tenwa.business.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.model.EasyUIRequestParam;
import com.tenwa.business.model.JsonEntyForUIView;
import com.tenwa.business.model.JsonResult;
import com.tenwa.business.model.UIColumns;
import com.tenwa.business.service.CommService;
import com.tenwa.kernal.utils.JacksonFilter.userFilter;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.xml.TableXMLUtil;

/**
 * xml解析，如果传递的是xml，则解析xml，传递的是类名，查询全部数据,type为查询的数据url
 * @author Jason
 *
 */
@Controller("commController")
@RequestMapping(value = "/**/commController")
public class CommController extends BaseController {

	@RequestMapping(value = "/**/comm.action")
	@ResponseBody
	//public ModelAndView comm(@RequestParam("xml") String xml, @RequestParam(value = "type", required=false) String type) throws Exception {
	public Map<String, Map<String, String>> comm(@RequestParam("xml") String xml, @RequestParam(value = "type", required=false) String type) throws Exception {
		System.out.println("通用转发:开始时间 = " + System.currentTimeMillis());
		long start = System.currentTimeMillis();
		
		Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();
		if(xml.endsWith(".xml")){
			String path = ResourceUtil.getTablesDataSourceDirectoryPath() + xml;
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			map = TableXMLUtil.getInstance().getEntityHead(path, type);
			System.out.println(map);
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		} else{
			Map<String,String> mapData = new HashMap<String, String>();
			mapData.put("id", xml);
			mapData.put("dataUrl", type);
			mapData.put("modelType", "find");
			map.put("find", mapData);
			map.put("fuzzy", mapData);
		}
		ObjectMapper mapper = new ObjectMapper();  
        String json = mapper.writeValueAsString(map);
        System.out.println("通用转发:结束时间 = " + System.currentTimeMillis());
        System.out.println("通用转发: 共计 = " + (System.currentTimeMillis() - start)  + " 毫秒");
       return map;
		//return new ModelAndView("commtable.jsp", "data", json);
	}
	
	
@SuppressWarnings("unchecked")
	@RequestMapping(params = "findPageList")
	@ResponseBody
	public JsonEntyForUIView<Object> findPageList(EasyUIRequestParam param, @RequestParam("xml") String xml, @RequestParam("modelType") String modelType, @RequestParam("objectType") String objectType) throws Exception {
		JsonEntyForUIView<? extends Object> json = new JsonEntyForUIView<Object>();
		
		Map<String, Object> findMap = this.commService.findPageList(objectType, param.getPage(), param.getRows());
		json.setTotal((Integer) findMap.get("totalRows"));
		json.setRows((List<?>) findMap.get("dataList"));
		return (JsonEntyForUIView<Object>) json;

}

	@RequestMapping(params = "findAll")
	@ResponseBody
	public List<Object> findAll(@RequestParam("objectType") String objectType,EasyUIRequestParam param) throws Exception {
		List<Object> areaInList = new ArrayList<Object>();
		areaInList = this.commService.findAll(objectType);
		return areaInList;
	}

	@RequestMapping(params = "save")
	@ResponseBody
	public JsonResult add(@RequestParam("objectType") String objectType,HttpServletRequest request) throws Exception {
		JsonResult js = new JsonResult();
		Serializable id = this.commService.save(objectType, request);
		if (id != null) {
			js.setSuccess(true);
			js.setMsg(id.toString());
		} else {
			js.setSuccess(false);
		}
		return js;
	}
	
	@RequestMapping(params = "saveOrUpdata")
	@ResponseBody
	public JsonResult saveOrUpdata(@RequestParam("objectType") String objectType,HttpServletRequest request) throws Exception {
	
		System.out.println("保存:开始时间 = " + System.currentTimeMillis());
		long start = System.currentTimeMillis();
		JsonResult js = new JsonResult();
		Map<String, String> returenIDs = new HashMap<String, String>();
		returenIDs = this.commService.saveOrUpdataReturnCasecadeID(objectType, request);
		//ObjectMapper mapper = new ObjectMapper();  
        //String json = mapper.writeValueAsString(returenIDs);
		if (returenIDs.get("id") != null) {
			js.setSuccess(true);
			js.setMsg("保存成功");
			js.setObj(returenIDs);
		} else {
			js.setMsg("保存失败，请检查");
			js.setSuccess(false);
		}
		
		   System.out.println("保存:结束时间 = " + System.currentTimeMillis());
	       System.out.println("保存: 共计 = " + (System.currentTimeMillis() - start)  + " 毫秒");
		
		return js;
	}

	/*@RequestMapping(params = "modify")
	@ResponseBody
	public JsonResult modify(T entity) throws Exception {
		JsonResult js = new JsonResult();
		this.baseService.modify(entity);
		js.setSuccess(true);
		return js;
	}
*/
	@RequestMapping(params = "remove")
	@ResponseBody
	public JsonResult remove(@RequestParam("objectType") String objectType, @RequestParam("id") String id) {
		JsonResult js = new JsonResult();
		try {
			this.commService.remove(objectType, id);
			js.setSuccess(true);
			js.setMsg("删除成功");
		} catch (Exception e) {
			js.setSuccess(false);
			js.setMsg("删除失败");
			e.printStackTrace();
		}
		return js;
	}
	@RequestMapping(params = "removeByIDs")
	@ResponseBody
	public JsonResult removeByIDs(@RequestParam("objectType") String objectType, @RequestParam("ids") String ids) {
		JsonResult js = new JsonResult();
		try {
			this.commService.removeByIDs(objectType, ids);
			js.setSuccess(true);
			js.setMsg("删除成功");
		} catch (Exception e) {
			js.setSuccess(false);
			js.setMsg("删除失败");
			e.printStackTrace();
		}
		return js;
	}

	@RequestMapping(params = "findById")
	@ResponseBody
	public JsonResult findById(@RequestParam("objectType") String objectType, @RequestParam("id") String id) throws Exception {
		JsonResult js = new JsonResult();
		js.setObj(this.commService.findById(objectType,id));
		js.setSuccess(true);
		return js;
	}

/*	@RequestMapping(params = "findByList")
	@ResponseBody
	public JsonResult findByList(@RequestParam("idList") String idList) throws Exception {
		JsonResult js = new JsonResult();
		String[] ids = idList.split(",");
		this.commService.findByList(ids);
		js.setSuccess(true);

		return js;
	}*/

	@RequestMapping(params = "findFields")
	@ResponseBody
	public List<UIColumns> findFieldNames(@RequestParam("xml") String xml, @RequestParam("modelType") String modelType, @RequestParam("objectType") String objectType) throws Exception {
		return this.commService.findFieldNames(objectType, xml, modelType);
	}
	
	@RequestMapping(params = "getJsonString")
	@ResponseBody
	public String getJsonString(@RequestParam("jsonStringName") String jsonStringName) throws Exception {
		return this.commService.getJsonString(jsonStringName);
	}
	@RequestMapping(params = "getExistedFile")
	@ResponseBody
	public List<Map<String,String>> getExistedFile(HttpServletRequest request) throws Exception {
		String fileName = request.getParameter("request");
		return this.commService.getExistedFile(fileName);
	}


	@RequestMapping(params = "findFuzzy")
	@ResponseBody
	public String findFuzzy(HttpServletRequest request) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("pagesize"));
		String column = request.getParameter("column");
		String objectType = request.getParameter("objectType");
		String condition = request.getParameter("condition");
		Map<String, Object> returnMap =  this.commService.findFuzzy(column, objectType, condition, page,  pageSize);
		ObjectMapper mapper = new ObjectMapper();
		String returnString = mapper.writeValueAsString(returnMap).replace("_alias_point_", ".");
		return returnString;
	}
	
	@RequestMapping(params = "findByColumn")
	@ResponseBody
	public String findByColumn(@RequestParam("objectType") String objectType,@RequestParam("columnName") String columnName, @RequestParam("columnValue") String columnValue) throws Exception {
		List<Object> objects = new ArrayList<Object>();
		objects = this.commService.findByColumn(objectType,columnName, columnValue);
		
		 ObjectMapper mapper = new ObjectMapper();  
	        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性  
		 
	        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);  
	        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false); 
	        
	        //mixin动态过滤字段
	        SerializationConfig serializationConfig = mapper.getSerializationConfig();  
	        //JacksonFilter.setFielter("a","b");
	       	serializationConfig.addMixInAnnotations(DictionaryData.class, userFilter.class);  
	          
	        String ss = mapper.writeValueAsString(objects);
	        System.out.println(ss);
		return ss;
	}
	
	@RequestMapping(params = "findDictValue")
	@ResponseBody
	public List<DictionaryData> findDictValue(String columnName, @RequestParam("columnValue") String columnValue) throws Exception {
		List<DictionaryData> dictionaryData = new ArrayList<DictionaryData>();
		dictionaryData = this.commService.findDictValue(columnValue);
		return dictionaryData;
	}
	
	
	@RequestMapping(params = "getColumnsProperty")
	@ResponseBody
	public List<Map<String, String>> getColumnsProperty(HttpServletRequest request) throws Exception{
		List<Map<String, String>> columnsProperty = this.commService.getColumnsProperty(request);
		return columnsProperty;
	}

	@RequestMapping(params = "getAllEntityProperty")
	@ResponseBody
	public List<Map<String, String>> getAllEntityProperty(HttpServletRequest request) throws Exception{
		String key = request.getParameter("key");
		List<Map<String, String>> entitysProperty = this.getCommService().getAllEntityProperty(key);
		return entitysProperty;
	}
	
	@RequestMapping(params = "saveCommGrid")
	@ResponseBody
	public String saveCommGrid(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String isSaved = "true";
		String param = request.getParameter("param");
		if (param != null) {
			try {
				this.getCommService().saveCommGrid(param);
			} catch (Exception e) {
				isSaved = "false";
				throw e;
			}
		} else{
			isSaved = "false";
		}
		return isSaved;
	}
	
	
	private CommService commService;

	public CommService getCommService() {
		return commService;
	}
	

	@Resource(name="commService")
	public void setCommService(CommService commService) {
		this.commService = commService;
	}
	
}