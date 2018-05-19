package com.tenwa.freemaker.serviceImpl;

import static com.tenwa.kernal.utils.StringUtil.nullToString;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.freemaker.entity.FreeMakerEntity;
import com.tenwa.freemaker.entity.FreeMakerEntityColumn;
import com.tenwa.freemaker.enums.EntityColumTypeEnum;
import com.tenwa.freemaker.service.FreemakerService;
import com.tenwa.freemaker.tools.FreemakerTool;
import com.tenwa.freemaker.tools.UpperFirstCharacter;
import com.tenwa.kernal.utils.FreeMarkerUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service("freemakerService")
public class FreemakerServiceImpl extends AbstractBaseServiceImpl implements FreemakerService {
	
	@Resource(name = "baseDao")
	private BaseDao baseDao;
	
	@Override
	public String saveOrQueryFreemakerEntities(Map<String, String> paramMap)
			throws Exception {
		String parent = paramMap.get("parent");
		FreeMakerEntity entity = this.findEntityByID(FreeMakerEntity.class, parent);
		if(parent.equals("0") && null  == entity){
			this.getBussinessDao().getJdbcTemplate().execute("INSERT INTO T_FREEMAKER_ENTITY(ID, ENTITY_NAME, POSITION_, ENTITY_TYPE,SOURCEFOLDER_NAME) "
									+ "VALUES ('0', 'com' ,'0', 'PACKAGE','business')");//默认是business下面的
			entity = this.findEntityByID(FreeMakerEntity.class, "0");
		}
		JSONObject reportTreeJson = this.getFreemakerEntityTreeAsJson(entity);
		JSONArray treeJson = new JSONArray();
		treeJson.put(reportTreeJson);
		return treeJson.toString();
	}
	
	@Override
	public JSONObject getFreemakerEntityTreeAsJson(FreeMakerEntity rootEntity) throws Exception {
		if (rootEntity == null)
			throw new Exception("Not Found Report Entity is NULL");

		JSONObject nodeJson = this.getFreemakerEntityTreeNodeAsJson(rootEntity);
		if (rootEntity.getChildren() != null && rootEntity.getChildren().size() > 0) {
			JSONArray childJsons = new JSONArray();
			for (FreeMakerEntity child : rootEntity.getChildren()) {
				JSONObject childNodeJson = getFreemakerEntityTreeAsJson(child);
				childJsons.put(childNodeJson);
			}
			nodeJson.put("children", childJsons);
		}

		return nodeJson;

	}
	
	private JSONObject getFreemakerEntityTreeNodeAsJson(FreeMakerEntity entity) throws Exception {
		String currentState = "close";
		JSONObject nodeJson = new JSONObject();
		nodeJson.put("id", entity.getId());
		nodeJson.put("text", entity.getEntityName());
		//判断有没子节点，如果没有或者子节点的类型全为class，则关闭
		Set<FreeMakerEntity> entitys =  entity.getChildren();
		if(null != entitys && entitys.size() > 0 ){
			for(FreeMakerEntity e : entitys){
				if(e.getEntityType().toString().equals("PACKAGE")){
					currentState = "open";
					break;
				}
			}
		}else{
			currentState = "open";
		}
		String icon;
		switch (entity.getEntityType()) {
		case PACKAGE:
			icon = "icon-package";
			break;
		case ENTITY:
			icon = "icon-bean";
			break;

		default:
			icon = "icon-bean";
		}
		nodeJson.put("iconCls", icon);
		nodeJson.put("state", currentState);
		JSONObject attributesJson = new JSONObject();
		attributesJson.put("id", entity.getId());
		attributesJson.put("position", entity.getPosition());
		attributesJson.put("entityName", entity.getEntityName());
		attributesJson.put("tableName", entity.getTableName());
		attributesJson.put("entityType",entity.getEntityType());
		attributesJson.put("tableDesc",entity.getTableDesc());
		attributesJson.put("sourcefoldername", entity.getSourcefolderName());
		
		attributesJson.put("entityType", nullToString(entity.getEntityType()));
		attributesJson.put("parent", (entity.getParent() == null) ? "-1" : entity.getParent().getId());
		nodeJson.put("attributes", attributesJson);
		return nodeJson;
	}
	
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return this.baseDao;
	}
	
	@Override
	public String updateFreemakerEntityPosition(Map<String, String> paramMap)
			throws Exception {
		String id = paramMap.get("id");
		FreeMakerEntity entity = this.findEntityByID(FreeMakerEntity.class, id);
		int oldPosion = entity.getPosition();
		int nowPosion = Integer.parseInt(paramMap.get("position"));
		FreeMakerEntity oldParent = entity.getParent();
		Map<String, String> classFieldMapping = new HashMap<String, String>();
		classFieldMapping.put("FreeMakerEntity", "id");
		this.copyAndOverrideExistedValueFromStringMap(paramMap, entity, classFieldMapping, true);
		this.saveOrUpdateEntity(entity);
		//同时还要更新其他节点的位置
		String action = paramMap.get("action");
		if(action.equals("add")){
			//比拖拽位置大的统一减1
			String hql = "update  FreeMakerEntity entity set position = entity.position - 1 where entity.parent = ? and entity.position > ?";
			this.updateByHSQL(hql, oldParent,oldPosion);
		}else if(action.equals("after")){
			//如果是after的话，需要将大于等于拖拽节点且小于现节点的所有的同级节点的位置 -1 ，将大于等于拖拽节点，且不等于当前节点的所有节点位置+1
			String hql = "update  FreeMakerEntity entity set position = entity.position - 1 where entity.parent = ? and entity.position > ? and entity.position < ?";
			this.updateByHSQL(hql, oldParent,oldPosion,nowPosion);
			String hql2 = "update  FreeMakerEntity entity set position = entity.position +1  where entity.parent = ? and entity.position >= ? and entity.id <> ?";
			this.updateByHSQL(hql2, oldParent,nowPosion,entity.getId());
		}else{
			//比拖拽位置大的统一减1
			String hql2 = "update  FreeMakerEntity entity set position = entity.position +1  where entity.parent = ? and entity.position >= ? and entity.id <> ?";
			this.updateByHSQL(hql2, oldParent,nowPosion,entity.getId());
		}
		return null;
	}
	
	@Override
	public String saveEntityColumn(Map<String, String> paramMap)
			throws Exception {
		String id = paramMap.get("id");
		FreeMakerEntityColumn entityColumn = new FreeMakerEntityColumn();
		if(id != null &&  id.length() > 0 ){
			entityColumn = this.findEntityByID(FreeMakerEntityColumn.class,id );
		}
		Map<String, String> classFieldMapping = new HashMap<String, String>();
		classFieldMapping.put("FreeMakerEntity", "id");
		this.copyAndOverrideExistedValueFromStringMap(paramMap, entityColumn, classFieldMapping, true);
		//tableIsNotnull tableIsUnique tableIsIndex
		entityColumn.setTableIsNotnull(paramMap.get("tableisnotnull").equals("1") ? true :false);
		entityColumn.setTableIsUnique(paramMap.get("tableisunique").equals("1") ? true :false);
		entityColumn.setTableIsIndex(paramMap.get("tableisindex").equals("1") ? true :false);
		this.saveOrUpdateEntity(entityColumn);
		this.updateFlush();
		this.updateOrderPosition("t_freemaker_entity_column", entityColumn.getId(), paramMap.get("entity"), paramMap.get("position"));
		return entityColumn.getId();
	}
	
	@Override
	public String createEntity(Map<String, String> paramMap) throws Exception {
		String pid = paramMap.get("pid");
		FreeMakerEntity entity = this.findEntityByID(FreeMakerEntity.class, pid);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		Set<FreeMakerEntityColumn>  columns =  entity.getFields();
		//开始生成实体
		String freemakerBasePath = ResourceUtil.getConfigValue("freemaker.entitybasepath")+entity.getSourcefolderName()+"\\";
		Configuration cfg = FreeMarkerUtil.getConfiguration(WebUtil.getWebContextRealPath()+"FreemakerTemplate");
		cfg.setSharedVariable("upperFC", new UpperFirstCharacter());//添加一个"宏"共享变量用来将属性名首字母大写  
		Template template = cfg.getTemplate("/JavaBeanTemplate.ftl");//指定模板
		//将对应的java类名转化为实际的存放路径
		String entityName = entity.getEntityName();
		entityName = entityName.replaceAll("\\.java", "");
		String entityPath = freemakerBasePath +"\\"+ entityName;
		entityPath = entityPath.replaceAll("\\.", "\\\\");
		entityPath += ".java";
		//判断父目录是否存在，不存在，则进行创建
		String parentPackage =   freemakerBasePath +"\\" + entity.getParent().getEntityName();
		parentPackage = parentPackage.replaceAll("\\.", "\\\\");
		File parentPackageFile = new File(parentPackage);
		if(!parentPackageFile.exists() && !parentPackageFile.isDirectory()){
			parentPackageFile.mkdirs();
		}
		File entityFile =  new File(entityPath);
		if(!entityFile.exists()){
			entityFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(entityFile);
		//创建数据模型
		Map<String, Object>data = new HashMap<String, Object>();
		//得到包名
		data.put("package", entity.getParent().getEntityName());
		//从全类名中得到，simpleClassName
		String simpleClassName = entityName.substring(entityName.lastIndexOf(".")+1);
		data.put("className", simpleClassName);
		data.put("entityDesc", entity.getTableDesc());
		data.put("entityName", entity.getTableName());
		List<Map<String, String>> properties = new ArrayList<Map<String,String>>();
		Set<String>importClasses = new HashSet<String>();
		for(FreeMakerEntityColumn column : columns){
			Map<String, String> property = new HashMap<String, String>();
			property.put("proType", column.getFieldType());
			property.put("proName", column.getFieldName());
			property.put("columnType", column.getTableColumnType().toString());
			property.put("proDesc", column.getTableColumnDesc());
			property.put("columnName", column.getTableColumnName());
			property.put("columnLength", column.getTableColumnLength() != null &&column.getTableColumnLength() <= 0 ? null : column.getTableColumnLength()+"");
			property.put("precision", column.getTableColumnPrecision() != null && column.getTableColumnPrecision() <= 0 ? null : column.getTableColumnPrecision()+"");
			property.put("scale", column.getTableColumnScale() != null && column.getTableColumnScale() <= 0 ? null : column.getTableColumnScale()+"");
			property.put("notnull", column.getTableIsNotnull()+"");
			property.put("unique", column.getTableIsUnique()+"");
			property.put("isindex", column.getTableIsIndex()+"");
			property.put("fetchType", column.getFetchType() == null  ? "" : column.getFetchType().toString());
			property.put("orderBy", column.getColumnOrderBy());
			//索引名称随机 
			if(column.getTableIsIndex() == null ? false : column.getTableIsIndex() ){
				property.put("indexname",column.getTableIndexName() == null || column.getTableIndexName().length() <= 0 ?  "IND_"+(new Date()).getTime() : column.getTableIndexName());
				importClasses.add("org.hibernate.annotations.Index");
			}
			if(column.getTableColumnType().equals(EntityColumTypeEnum.FOREIGNKEY)){
				importClasses.add(column.getFieldTypeFullName());
			}else if(column.getTableColumnType().equals(EntityColumTypeEnum.ONETOMANY)){
				importClasses.add("java.util.Set");
				property.put("proType", "Set<"+column.getFieldType()+">");
				importClasses.add(column.getFieldTypeFullName());
			}else if(column.getTableColumnType().equals(EntityColumTypeEnum.COMMON)){
				if(column.getFieldType().equalsIgnoreCase("Bigdecimal") || column.getFieldType().equalsIgnoreCase("Date")){
					importClasses.add(column.getFieldTypeFullName());
				}
			}else if(column.getTableColumnType().equals(EntityColumTypeEnum.PRIMARY)){
				importClasses.add("org.hibernate.annotations.GenericGenerator");
			}else if(column.getTableColumnType().equals(EntityColumTypeEnum.BLOB) || column.getTableColumnType().equals(EntityColumTypeEnum.CLOB)){
				importClasses.add("org.hibernate.annotations.Type");
			}else if(column.getTableColumnType().equals(EntityColumTypeEnum.ENUM)){
				importClasses.add(column.getFieldTypeFullName());
			}
			properties.add(property);
		}
		data.put("importclasses", importClasses);
		data.put("properties", properties);
		template.process(data,new BufferedWriter(new OutputStreamWriter(fos)));
		fos.flush();
		fos.close();
		return "success";
	}
	
	/**
	 * 将实体中的属性动态映射到数据库中
	 */
	@Override
	public String createDataFromEntity(Map<String, String> paramMap)
			throws Exception {
		String pid = paramMap.get("pid");
		FreeMakerEntity entity = this.findEntityByID(FreeMakerEntity.class, pid);
		this.removeAllEntites(entity.getFields());
		this.updateFlush();
		FreemakerTool.createDbDataByEntity(entity, SecurityUtil.getPrincipal(), this);
		return null;
	}
	
	/**
	 * 将所有的实体，动态更新到数据库中
	 */
	@Override
	public String createDataByAllEntity(Map<String, String> paramMap)
			throws Exception {
		//先将t_freemaker_entity_column清空，然后将t_freemaker_entity除了根目录其他的全部清空
		this.getBussinessDao().removeAllEntites(this.findEntities(FreeMakerEntityColumn.class));
		String hql = "from FreeMakerEntity where id <> '0'";
		this.getBussinessDao().removeAllEntites(this.findResultsByHSQL(hql));
		this.updateFlush();
		FreemakerTool.createAllDbData( SecurityUtil.getPrincipal(), this); 
		return null;
	}
}
