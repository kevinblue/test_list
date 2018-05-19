package com.tenwa.business.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.dao.RelationDao;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DepartmentRole;
import com.tenwa.business.entity.Group;
import com.tenwa.business.entity.JsonUtil;
import com.tenwa.business.entity.RelationItem;
import com.tenwa.business.entity.RelationMap;
import com.tenwa.business.entity.Relationship;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.relationship.parse.RelationParserEnum;
import com.tenwa.business.service.RelationService;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.UUIDUtil;

@Service("relationService")
public class RelationServiceImpl extends AbstractBaseServiceImpl implements RelationService {
	private static final Logger log = LoggerFactory.getLogger(RelationServiceImpl.class);
	@Resource
    private BaseDao baseDao;

	@Override
	public BaseDao getBussinessDao() throws Exception {
		return this.relationDao;
	}
	
	@Resource(name = "relationDao")
	private RelationDao relationDao;
	
	@Override
	public JSONArray listAllRelations() throws Exception {
		List<Relationship> relations = this.baseDao.findEntities(Relationship.class);
		JSONArray js = new JSONArray();
		for (Relationship rs : relations) {
			JSONObject nodeJson = new JSONObject();
			nodeJson.put("text", rs.getRelationName());
			nodeJson.put("id", rs.getId());
			nodeJson.put("relationname", rs.getRelationName());
			nodeJson.put("description", rs.getDescription());
			nodeJson.put("relationid", rs.getId());
			JSONObject attributes = new JSONObject();
			attributes.put("type", "RELATION");
			attributes.put("relation_name", rs.getRelationName());
			attributes.put("relation_description", rs.getDescription());
			nodeJson.put("attributes", attributes);
			System.out.println(rs.getRelationMaps());
			if (rs.getRelationMaps() != null && rs.getRelationMaps().size() > 0) {
				JSONArray mapJson = new JSONArray();
				for (RelationMap map : rs.getRelationMaps()) {
					JSONObject mapNodeJson = new JSONObject();
					mapNodeJson.put("id", map.getId());
					mapNodeJson.put("text", map.getMapName());
					JSONObject mapAttributes = new JSONObject();
					mapAttributes.put("type", "MAPPING");
					mapNodeJson.put("attributes", mapAttributes);
					mapJson.put(mapNodeJson);
				}
				nodeJson.put("children", mapJson);
			}
			js.put(nodeJson);
		}

		return js;
	}

	@Override
	public String getRelationMapTree() throws Exception {
		Department rootDept = this.baseDao.findEntityByID(Department.class, "0");

		
		JSONArray treeJson = new JSONArray();
		treeJson.put(getChildrenDeptTree(rootDept, true));
		return treeJson.toString();
	}
	private JSONObject getChildrenDeptTree(Department dept, boolean isRoot) throws Exception {
		JSONObject deptJson = dept.getJsonObjectDept(dept, isRoot, null);
		JSONArray childrenJson = new JSONArray();
		JSONArray combinedJsonArray = null;

		// combinedJsonArray = WebUtil.getRelationDeptTree(dept.getId());
		// if (combinedJsonArray == null) {
		for (Department subDept : dept.getChildrenDepts()) {
			JSONObject subDeptJson = getChildrenDeptTree(subDept, false);
			childrenJson.put(subDeptJson);
		}

		// Add User
		for (UserDepartment userDept : dept.getUserDepts()) {
			JSONObject userJsonObj = dept.getJsonObjectUser(userDept);
			childrenJson.put(userJsonObj);
		}
		// Add Role
		for (DepartmentRole deptRole : dept.getDeptRoles()) {
			JSONObject userJsonObj = dept.getJsonObjectRole(deptRole);
			childrenJson.put(userJsonObj);
		}
		// if is Root Department, then add Group
		JSONArray groupJson = null;
		if (isRoot) {
			groupJson = new JSONArray();
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("enabled", Boolean.TRUE);
			List<Group> groups = (List<Group>) this.baseDao.findEntityByProperties(Group.class, propertiesMap);

			for (Group group : groups) {
				JSONObject groupJsonObj = group.getJsonObjectGroup();
				groupJson.put(groupJsonObj);
			}
		}
		//注：这里注释掉这两行
		combinedJsonArray = JsonUtil.combine(childrenJson, groupJson);
		//WebUtil.setRelationDeptTree(dept.getId(), combinedJsonArray);
		deptJson.put("children", combinedJsonArray);
		return deptJson;
	}
	@Override
	public JSONObject saveMapItem(String relationId, String mapId,
			String newMapName, String leftItemIds, String rightItemIds)
			throws Exception {
		
		Relationship relation = this.baseDao.findEntityByID(Relationship.class, relationId);
		RelationMap map = null;
		if (!"".equals(mapId)) {
			map = this.baseDao.findEntityByID(RelationMap.class, mapId);
			map.setMapName(StringUtil.nullToString(newMapName, map.getMapName()));
			map.setParserClass(RelationParserEnum.ADDRESS_PARSER);
		} else {
			map = new RelationMap();
			map.setMapName(StringUtil.nullToString(newMapName, UUIDUtil.getUUID()));
			map.setParserClass(RelationParserEnum.ADDRESS_PARSER);
			relation.getRelationMaps().add(map);
			this.baseDao.saveOrUpdateEntity(relation);
		}

		// 添加映射左侧
		Set<RelationItem> leftItems = map.getLeftItems();
		List<String> removedIds = new ArrayList<String>();
		Set<RelationItem> newLeftItems = new HashSet<RelationItem>();
		if (leftItems == null) {
			leftItems = new HashSet<RelationItem>();
		}
		String[] leftIds = leftItemIds.toLowerCase().split(",");
		for (RelationItem item : leftItems) {
			String itemKey = item.getItemType().name().toLowerCase() + "#" + item.getAddressId().toLowerCase();
			int pos = Arrays.binarySearch(leftIds, itemKey);
			if (pos > -1) {
				newLeftItems.add(item);
				leftIds[pos] = ""; // 去除已存在的，保留不存在于数据库的，用于新生成
			} else {
				removedIds.add(item.getId()); // 去除此次未选择但已存在于数据库的。
			}

		}
		for (String id : leftIds) {
			if (!"".equals(id)) {
				RelationItem newItem = new RelationItem();
				String[] addressInfo = id.split("#");
				newItem.setAddressId(addressInfo[1]);
				newItem.setItemType(Enum.valueOf(RelationItem.ITEMTYPE.class, addressInfo[0].toUpperCase()));
				newItem.setDirection(RelationItem.DIRECTION.LEFT);
				newLeftItems.add(newItem);
			}
		}

		map.setLeftItems(newLeftItems);

		// 添加映射右侧
		Set<RelationItem> rightItems = map.getRightItems();

		Set<RelationItem> newRightItems = new HashSet<RelationItem>();
		if (rightItems == null) {
			rightItems = new HashSet<RelationItem>();
		}
		String[] rightIds = rightItemIds.toLowerCase().split(",");
		// List<String> rightIds = Arrays.asList(rightItemIds.toLowerCase().split(","));
		for (RelationItem item : rightItems) {
			String itemKey = item.getItemType().name().toLowerCase() + "#" + item.getAddressId().toLowerCase();
			int pos = Arrays.binarySearch(rightIds, itemKey);
			if (pos > -1) {
				newRightItems.add(item);
				rightIds[pos] = ""; // 去除已存在的，保留不存在于数据库的，用于新生成
			} else {
				removedIds.add(item.getId()); // 去除此次未选择但已存在于数据库的。
			}
		}
		for (String id : rightIds) {
			if (!"".equals(id)) {
				RelationItem newItem = new RelationItem();
				String[] addressInfo = id.split("#");
				newItem.setAddressId(addressInfo[1]);
				newItem.setItemType(Enum.valueOf(RelationItem.ITEMTYPE.class, addressInfo[0].toUpperCase()));
				newItem.setDirection(RelationItem.DIRECTION.RIGHT);

				newRightItems.add(newItem);
			}
		}

		map.setRightItems(newRightItems);
		this.baseDao.saveOrUpdateEntity(map);
		this.baseDao.updateFlush();
		// 删除不需要数据
		for (String id : removedIds) {
			this.baseDao.removeEntityById(RelationItem.class, id);
		}
		JSONObject retData = new JSONObject();

		retData.put("error", "");
		retData.put("mapId", map.getId());
		retData.put("relationId", relationId);
		return retData;
	}

	@Override
	public String getItems(String mapId) {
		JSONObject retData = new JSONObject();
		if ("".equals(StringUtil.nullToString(mapId))) {
			return retData.toString();
		}
		try {
			RelationMap map = this.baseDao.findEntityByID(RelationMap.class, mapId);
			JSONArray leftJson = new JSONArray();
			if (map.getLeftItems() != null) {
				for (RelationItem left : map.getLeftItems()) {
					JSONObject itemData = new JSONObject();
					itemData.put("id", left.getId());
					itemData.put("type", left.getItemType().name());
					itemData.put("addressId", left.getAddressId());
					leftJson.put(itemData);
				}
			}
			retData.put("left", leftJson);
			JSONArray rightJson = new JSONArray();
			if (map.getRightItems() != null) {
				for (RelationItem right : map.getRightItems()) {
					JSONObject itemData = new JSONObject();
					itemData.put("id", right.getId());
					itemData.put("type", right.getItemType().name());
					itemData.put("addressId", right.getAddressId());
					rightJson.put(itemData);
				}
			}
			retData.put("right", rightJson);

		} catch (Exception e) {
			log.error("", e);
		}
		return retData.toString();
	}

	@Override
	public void saveRelation(Map<String, String> model) throws Exception {
		Relationship relationship    = new Relationship();
		String relationid = model.get("relationid");
		if(null !=relationid && !"".equals(relationid)){
			relationship = this.baseDao.findEntityByID(Relationship.class, relationid);
		}
		baseDao.copyAndOverrideExistedValueFromStringMap(model, relationship, null,false);
		
		this.baseDao.saveOrUpdateEntity(relationship);
	}

	@Override
	public void removeRelation(String id, String type) throws Exception {
		if ("RELATION".equalsIgnoreCase(type) && id != null && !"".equals(id)) {
			Relationship r = this.baseDao.findEntityByID(Relationship.class, id);
			
			this.baseDao.removeEntity(r);
		} else if ("MAPPING".equalsIgnoreCase(type) && id != null && !"".equals(id)) {
			this.baseDao.removeEntityById(RelationMap.class, id);
		}
	}

	@Override
	public void saveMap(Map<String, String> model) throws Exception {
		Relationship relation = this.baseDao.findEntityByID(Relationship.class, model.get("map_relation_id"));
		
		RelationMap relationMap    = new RelationMap();
		baseDao.copyAndOverrideExistedValueFromStringMap(model, relationMap, null,false);
		relationMap.setMapName(model.get("map_name"));
		relationMap.setId(model.get("map_id"));
		relationMap.setParserClass(RelationParserEnum.ADDRESS_PARSER);
		relation.getRelationMaps().add(relationMap);
		this.baseDao.saveOrUpdateEntity(relationMap);
	}
}
