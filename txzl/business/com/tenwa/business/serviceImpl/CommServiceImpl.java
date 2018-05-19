package com.tenwa.business.serviceImpl;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.tenwa.business.dao.CommDao;
import com.tenwa.business.entity.Dictionary;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.model.UIColumns;
import com.tenwa.business.service.CommService;
import com.tenwa.kernal.utils.BeanFieldUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SortUtil;
import com.tenwa.kernal.utils.xml.ConvertUtil;

import edu.emory.mathcs.backport.java.util.Arrays;

@Service(value = "commService")
public class CommServiceImpl implements CommService {
	private CommDao commDao;

	@Override
	public Serializable save(String objectType, HttpServletRequest request) throws Exception {
		Map<String, String> map = QueryUtil.getRequestParameterMapNoAjax(request);
		Object targetObject = Class.forName(objectType).newInstance();
		String jsonString = map.get(this.getType(objectType).getSimpleName());
		System.out.println(jsonString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		Class<?> clazz = this.getType(objectType);
		targetObject = mapper.readValue(jsonString, clazz);

		return this.commDao.save(objectType, targetObject);
	}

	@Override
	public void remove(String objectType, String id) throws Exception {
		this.commDao.remove(objectType, id);
	}

	@Override
	public void removeAll(String objectType, Collection<Object> objects) throws ClassNotFoundException {
		this.commDao.removeAll(objectType, objects);
	}

	@Override
	public void modify(String objectType, Object object, Serializable id) throws Exception {
		this.commDao.modify(objectType, object, id);
	}

	@Override
	public Object findById(String objectType, String id) throws DataAccessException, SecurityException, ClassNotFoundException, NoSuchFieldException {
		return this.commDao.findById(objectType, id);
	}

	@Override
	public List<Object> findAll(String objectType) {
		return this.commDao.findAll(objectType);
	}

	@Override
	public Map<String, Object> findPageList(int page, int size, String queryString) {
		return this.commDao.findPageList(page, size, queryString);
	}

	@Override
	public Map<String, Object> findPageList(String objectType, int page, int size) {
		return this.commDao.findPageList(objectType, page, size);
	}

	@Override
	public int getPageSize(int size, String queryString) {
		return this.commDao.getPageSize(size, queryString);
	}

	@Override
	public List<Object> findByList(String objectType, Object[] roleIdList) throws ClassNotFoundException {
		return this.commDao.findPageList(objectType, roleIdList);
	}

/*	@Override
	public Map<String, Object> findFuzzy(String objectType, String conditionString, int page, int rows) {
		return this.commDao.findFuzzy(objectType, conditionString, page, rows);
	}*/

	@Override
	public int getTotalRows(String queryStrintg) {
		return this.commDao.getTotalRows(queryStrintg);
	}

	public CommDao getCommDao() {
		return commDao;
	}

	@Resource(name = "commDao")
	public void setCommDao(CommDao commDao) {
		this.commDao = commDao;
	}

	@Override
	public int getPageSize(String objectType, int size) {
		return this.getPageSize(objectType, size);
	}

	@Override
	public Class<?> getType(String objectType) throws ClassNotFoundException {
		return this.commDao.getType(objectType);
	}
	

	@Override
	public List<UIColumns> findFieldNames(String objectType, String xml, String modelType) throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, InstantiationException {
		Class<?> type = Class.forName(objectType);
		//List<Map<String, String>> list = BeanFieldUtil.parseFieldNames(type);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		System.out.println("list =  " + list);
		List<UIColumns> columns = new ArrayList<UIColumns>();
		if (xml.endsWith(".xml")) {
			ConvertUtil convertUtil = new ConvertUtil();
			columns = convertUtil.convertToUIData(list, xml, modelType);
			System.out.println("字段内容#################################################1111111111111");
			System.out.println("findFields = " + xml + " - " + modelType + ",  id = ,   " + Thread.currentThread().getId() + ", name = " + Thread.currentThread().getName());
			System.out.println("colums = " + columns);
			System.out.println("字段内容#################################################22222222222222");
		} else {
			for (Map<String, String> map : list) {
				UIColumns uiColumns = new UIColumns();
				uiColumns.setTitle(map.get("title"));
				uiColumns.setField(map.get("field"));
				columns.add(uiColumns);
			}
		}

		return columns;
	}

	@Override
	public Serializable getSerializableId(String objectType, String id) throws SecurityException, ClassNotFoundException, NoSuchFieldException {
		return this.commDao.getSerializableId(objectType, id);
	}

	@Override
	public Map<String, String> saveOrUpdataReturnCasecadeID(String objectType, HttpServletRequest request) throws Exception {
		
		Map<String, String> map = QueryUtil.getRequestParameterMapNoAjax(request);
		Object targetObject = Class.forName(objectType).newInstance();
		String jsonString = map.get(this.getType(objectType).getSimpleName());
		String cascadeTypes = map.get("cascadeType");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Class<?> clazz = this.getType(objectType);
		targetObject = mapper.readValue(jsonString, clazz);
		Serializable id = this.commDao.saveOrUpdata(objectType, targetObject);

		Object target = this.findById(objectType, id.toString());

		Map<String, String> returenIDs = new HashMap<String, String>();
		returenIDs.put("id", id.toString());

		if (cascadeTypes != null) {
			String[] s = cascadeTypes.split(",");
			for (int i = 0; i < s.length; i++) {
				PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(clazz, s[i]);
				Method method = pd.getReadMethod();
				Object childTarget = method.invoke(target);
				PropertyDescriptor pdID = BeanUtils.getPropertyDescriptor(pd.getPropertyType(), "id");
				String childTargetID = pdID.getReadMethod().invoke(childTarget).toString();
				returenIDs.put(s[i], childTargetID);
			}
		}

		return returenIDs;
	}

	@Override
	public List<Object> findByColumn(String objectType, String columnName, String columnValue) throws Exception {
		return this.commDao.findByColumn(objectType, columnName, columnValue);

	}

	@Override
	public List<DictionaryData> findDictValue(String columnValue) throws Exception {

		Dictionary dictionary = (Dictionary) this.findByColumn(Dictionary.class.getName(), "code", columnValue).get(0);
		Set<DictionaryData> dictionaryDatas = dictionary.getDatas();
		List<DictionaryData> list = new ArrayList<DictionaryData>(Arrays.asList(dictionaryDatas.toArray()));
		// 对list排序，
		PropertyComparator.sort(list, new MutableSortDefinition("id", false, true));
		return list;
	}

	@Override
	public List<Map<String, String>> getAllEntityProperty(String key) throws Exception{
		return this.commDao.getAllEntityProperty(key);
	}

	@Override
	public List<Map<String, String>> getColumnsProperty(HttpServletRequest request) throws Exception {
		//返回数组
		List<Map<String, String>> returnList = new ArrayList<Map<String,String>>();
		
		//判断传递的是类名称还是json字符串名称
		String objectType = request.getParameter("objectType");
		String fileName = request.getParameter("fileName");
		
	
		
		if(objectType != null && objectType != ""){//类名
			returnList = this.commDao.getColumnsProperty(this.commDao.getType(objectType));
		} else if(fileName !=null){//json字符串
			String path = ResourceUtil.getTablesDataSourceDirectoryPath();
			String fileString = this.readFile(path+fileName);
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Map<String, String>> temMap = new LinkedHashMap<String,Map<String, String>>();
			Map<String,Map<String, String>> _temMap = new LinkedHashMap<String,Map<String, String>>();
			Map<String,Map<String, String>> treeMap = new java.util.TreeMap<String, Map<String,String>>();
			
			Map<String, Object> map =  mapper.readValue(fileString, Map.class);
			System.out.println(map);
			Map<String,String> attrMap = (Map<String, String>) map.get("attribute");
			//通过json字符串中的attribute属性获取保存的类名，取得基础类
			List<String> sortFieldList = new ArrayList<String>();
			
			if(attrMap != null && attrMap.get("objectType")!=null){
				returnList = this.commDao.getColumnsProperty(this.commDao.getType(attrMap.get("objectType")));
				//将
				for (Map<String, String>  column: returnList) {
					//temMap.put(column.get("fieldName"), column);
					sortFieldList.add(column.get("fieldName"));
				}
			}
			
			//json字符串中保存的grid
			List gridList = (ArrayList) map.get("grid");
			//搜素字段
			List searchList = (ArrayList) map.get("search");
			//表单字段
			List formList = (ArrayList) map.get("form");
			//Map attrMap =  map.get("attribute");
			
			//将表单字段排序
			//归并将form和grid合并name字段

			List<String> sortGridList = new ArrayList<String>();
			Set<String> linkSet = new LinkedHashSet<String>();
			for (int i = 0; i < gridList.size(); i++) {
				Map<String,Object> gridField =  (Map<String, Object>) gridList.get(i);
				String name = gridField.get("name").toString();
				if(name.contains(".")){
					name = name.substring(0, name.indexOf("."));
				}
				linkSet.add(name);
			}
			sortGridList.addAll(linkSet);
			
			List<String> sortFormList = new ArrayList<String>();
			for (int i = 0; i < formList.size(); i++) {
				Map<String,Object> fromField =  (Map<String, Object>) formList.get(i);
				sortFormList.add(fromField.get("name").toString());
			}
			
			
			List<String> sortGridAndForm = SortUtil.mergeArray(sortGridList, sortFormList);
			List<String> sortList = SortUtil.mergeArray(sortFieldList, sortGridAndForm);

			for (int i = 0; i < sortList.size(); i++) {
				temMap.put(sortList.get(i), null);
			}
			
			
			
			if(attrMap != null && attrMap.get("objectType")!=null){
				returnList = this.commDao.getColumnsProperty(this.commDao.getType(attrMap.get("objectType")));
				//将
				for (Map<String, String>  column: returnList) {
					temMap.put(column.get("fieldName"), column);
					//sortFieldList.add(column.get("fieldName"));
				}
			}
			
			
			if(!gridList.isEmpty()){
				for (int i = 0; i < gridList.size(); i++) {
					Map<String,Object> gridField =  (Map<String, Object>) gridList.get(i);
					
					Field f = BeanFieldUtil.findField(this.commDao.getType(attrMap.get("objectType")), gridField.get("name").toString());
					
					if(f!=null){
						
					}
					
					Map<String,String> columnMap = temMap.get(gridField.get("name"));
					if(null == columnMap){
						columnMap = new HashMap<String, String>();
					}
					Set<String > keys =  gridField.keySet();
					for (String keyString : keys) {
						String value = gridField.get(keyString).toString();
						columnMap.put("inlist", "true");
						//将保存的jsonString转成页面的字段
						if(keyString.equals("name")){
							columnMap.put("fieldName", value);
						} else if(keyString.equals("display")){
							columnMap.put("showName", value);
						} else if(keyString.equals("width")){
							columnMap.put("listwidth", value);
						}else if(keyString.equals("hide")){
							if(value == "true"){
								columnMap.put("render", "hide");
							}
						}  else{
							columnMap.put(keyString, value);
						}
					}
					temMap.put((String) gridField.get("name"), columnMap);
					_temMap.put((String) gridField.get("name"), columnMap);
				};
			}
			if(!searchList.isEmpty()){
				for (int i = 0; i < searchList.size(); i++) {
					Map<String,Object> gridField = (Map<String, Object>) searchList.get(i);
					Map<String,String> columnMap = temMap.get(gridField.get("name"));
					if(null == columnMap){
						columnMap = new HashMap<String, String>();
					}
					Set<String > keys =  gridField.keySet();
					for (String keyString : keys) {
						String value = gridField.get(keyString).toString();
						columnMap.put("insearch", "true");
						if(keyString.equals("name")){
							columnMap.put("fieldName", value);
						}else if(keyString.equals("display")){
							columnMap.put("showName", value);
						} else{
							columnMap.put(keyString, value);
						}
					}
					temMap.put((String) gridField.get("name"), columnMap);
					_temMap.put((String) gridField.get("name"), columnMap);
				};
			}
			if(!formList.isEmpty()){
				for (int i = 0; i < formList.size(); i++) {
					Map<String,Object> gridField = (Map<String, Object>) formList.get(i);
					Map<String,String> columnMap = temMap.get(gridField.get("name"));
					if(null == columnMap){
						columnMap = new HashMap<String, String>();
					}
					Set<String > keys =  gridField.keySet();
					for (String keyString : keys) {
						String value = gridField.get(keyString).toString();
						columnMap.put("inform", "true");
						
						if(keyString.equals("name")){
							columnMap.put("fieldName", value);
						}else if(keyString.equals("display")){
							columnMap.put("showName", value);
						} else{
							columnMap.put(keyString, value);
						}
					}
					temMap.put((String) gridField.get("name"), columnMap);
					_temMap.put((String) gridField.get("name"), columnMap);
				};
			}
			returnList.clear();
			for (String k : temMap.keySet()) {
				returnList.add(temMap.get(k));
			}
			returnList.add(attrMap);
		}
		return returnList;
	}

	@Override
	public void saveCommGrid(String param) throws Exception {
		String path = ResourceUtil.getTablesDataSourceDirectoryPath();
		JSONObject paramObj = new JSONObject(param);
		path = path + paramObj.getJSONObject("attribute").get("fileName") + ".json";
		FileUtil.writeFile(param.toString(), path, "UTF-8");
	}
	
	@Override
	public String readFile(String fileName) throws Exception {
		String fileString = FileUtil.readFileByLines(fileName, "UTF-8");
		return fileString;
	}

	@Override
	public String getJsonString(String jsonStringName) throws Exception {
		if(jsonStringName == null || jsonStringName.equals("")){
			return null;
		}
		String path = ResourceUtil.getTablesDataSourceDirectoryPath();
		if(!jsonStringName.endsWith(".json")){
			jsonStringName = jsonStringName + ".json";
		}
		path = path + jsonStringName;
		return this.readFile(path);
	}

	@Override
	public Map<String, Object> findFuzzy(String column, String objectType, String condition, int page, int pageSize) throws ClassNotFoundException, JSONException {
		String[] columnArray = column.split(",");
		StringBuffer sbColumn = new StringBuffer();
		StringBuffer sbEntity = new StringBuffer();
		Map<String, String> aliasMap = new HashMap<String, String>(); 
		for (int i = 0; i < columnArray.length; i++) {
			if(columnArray[i].indexOf(".") != -1){
				String columnHead = columnArray[i].substring(0,columnArray[i].lastIndexOf("."));
				String columnBody = columnArray[i].substring(columnArray[i].lastIndexOf(".") + 1);
				String columnAlias = "";
				//Field f = ReflectionUtils.findField(this.getType(objectType), columnHead);
				Field f = BeanFieldUtil.findField(this.getType(objectType), columnHead);
				
				if(null == f){
					continue;
				}
				if(!aliasMap.containsKey(columnHead)){
					columnAlias = "t_" + i;
					aliasMap.put(columnHead, "t_" + i);
					sbEntity.append(" left join " + "t_." + columnHead + " as " + "t_" + i);
				} else{
					columnAlias = aliasMap.get(columnHead);
				}
				sbColumn.append(columnAlias + "." + columnBody + " as " + columnArray[i].replace(".", "_alias_point_") +",");
			}else{
				Field f = ReflectionUtils.findField(this.getType(objectType), columnArray[i]);
				if(null == f){
					continue;
				}
				
				sbColumn.append("t_." + columnArray[i] + " as " + columnArray[i] + "," );
			}
		}
		String sbColumnString = sbColumn.toString();
		String hql = "select new map(" + sbColumnString.substring(0, sbColumnString.lastIndexOf(",")) +") from "+ objectType +" as t_ " + sbEntity.toString() +"";
		if(condition != null && !condition.equals("")){
			String conditionString = "";
			JSONObject conditionObject = new JSONObject(condition);
			System.out.println(conditionObject.keys());
			Iterator<String> it = conditionObject.keys();
	          
			while (it.hasNext()) {// 遍历
				String key = it.next();
				String value = conditionObject.getString(key);
				if (value != null && !value.equals("")) {
					conditionString += " AND t_." + key + " like '%" + value + "%'";
				}
			}
			/*String[] conditionArray = condition.split(";");
			for (int i = 0; i < conditionArray.length; i++) {
				String[] cds = conditionArray[i].split("=");
				if (cds.length > 1 && cds[1] != null && !cds[1].equals("")) {
					conditionString += "t_."+cds[0] + " like '%" + cds[1] +"%'";
				}
			}*/
			if(!conditionString.equals("")){
				hql += " where " + conditionString.substring(4);
			}
		}
		System.out.println(hql);
		return this.commDao.findPageList(page, pageSize, hql);
		//return this.commDao.findFuzzy(hql, conditionString, page, pageSize);
	}

	@Override
	public List<Map<String,String>> getExistedFile(String fileName) throws Exception {
		String path = ResourceUtil.getTablesDataSourceDirectoryPath();
		System.out.println(fileName);
		if(fileName != null && fileName.length() > 0){
			this.getJsonString(fileName);
		}
		Set<File> set= FileUtil.getAllFiles(path);
		 List<Map<String,String>> fileNames = new ArrayList<Map<String,String>>();
		for (File file : set) {
			if (file.getName().endsWith(".json")){
				Map<String,String> map = new HashMap<String, String>();
				String fName = file.getName();
				map.put("id", fName);
				map.put("text",fName.substring(0, fName.lastIndexOf(".json")));
				String fileString = "";
				ObjectMapper mapper = new ObjectMapper();
				Map<String,String> attrMap;
				try{
					fileString = this.readFile(file.getAbsolutePath());
					Map<String, Object> filemap =  mapper.readValue(fileString, Map.class);
					attrMap = (Map<String, String>) filemap.get("attribute");
				}catch (Exception e){
					e.printStackTrace();
					continue;
				} 
				map.put("attribute", mapper.writeValueAsString(attrMap));
				fileNames.add(map);
			}
			
			
		}
		return fileNames;
	}

	@Override
	public void removeByIDs(String objectType, String ids) throws Exception {
		if(ids.indexOf(";") != -1 ){
			String[] idArray = ids.split(";");
			for (int i = 0; i < idArray.length; i++) {
				this.remove(objectType, idArray[i]);
			}
		} else{
			this.remove(objectType, ids);
		}
	}
}