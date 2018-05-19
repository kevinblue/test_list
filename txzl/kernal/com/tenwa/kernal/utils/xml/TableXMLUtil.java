package com.tenwa.kernal.utils.xml;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.tenwa.business.model.TableColum;
import com.tenwa.business.model.TableEntity;

public class TableXMLUtil {

	private static TableXMLUtil instance = null;

	public static synchronized TableXMLUtil getInstance() {
		if (instance == null) {
			instance = new TableXMLUtil();
		}
		return instance;
	}

	public Document getXML(String path) throws Exception {
		Document doc = null;
		FileInputStream fireInputStream = null;
		try {
			fireInputStream = new FileInputStream(path);
			SAXBuilder sb = new SAXBuilder();
			doc = sb.build(fireInputStream);

		} catch (Exception e) {
			System.err.println(e + "error");
		} finally {
			try {
				fireInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return doc;
	}

	/**
	 * 读取XML文件所有信息
	 */
	public List<TableEntity> parseXMLtoModule(String path) throws Exception {
		List<TableEntity> tableEntityList = new ArrayList<TableEntity>();
		Document doc = getXML(path);
		Element root = doc.getRootElement(); // 得到根元素
		tableEntityList = this.getModule(root, "entity");
		return tableEntityList;
	}

	//字段元素
	public List<TableColum> getColumns(Element element, String elementName) {
		List<TableColum> columnsList = new ArrayList<TableColum>();

		List<Element> columnsElements = element.getChildren(elementName);
		Element cloumn = null;

		for (int j = 0; j < columnsElements.size(); j++) {
			TableColum tableColum = new TableColum();
			List<TableColum> tableTableColumChildren = new ArrayList<TableColum>();
			cloumn = (Element) columnsElements.get(j);

			if (null != cloumn.getAttributeValue("id") && !cloumn.getAttributeValue("id").trim().equals("")) {
				tableColum.setId(cloumn.getAttributeValue("id"));
			} else if (null == cloumn.getAttributeValue("id") && cloumn.getAttributeValue("id").trim().equals("")) {
				break;
			}
			if (null != cloumn.getAttributeValue("name") && !cloumn.getAttributeValue("name").trim().equals("")) {
				tableColum.setName(cloumn.getAttributeValue("name"));
			}
			if (null != cloumn.getAttributeValue("attribute") && !cloumn.getAttributeValue("attribute").trim().equals("")) {
				tableColum.setAttribute(cloumn.getAttributeValue("attribute"));
			}
			if (null != cloumn.getAttributeValue("sort") && !cloumn.getAttributeValue("sort").trim().equals("")) {
				tableColum.setSort(Integer.parseInt(cloumn.getAttributeValue("sort")));
			}
			if (null != cloumn.getAttributeValue("columnsStyle") && !cloumn.getAttributeValue("columnsStyle").trim().equals("")) {
				tableColum.setColumnsStyle(cloumn.getAttributeValue("columnsStyle"));
			}
			if (null != cloumn.getAttributeValue("columnValidation") && !cloumn.getAttributeValue("columnValidation").trim().equals("")) {
				tableColum.setColumnValidation(cloumn.getAttributeValue("columnValidation"));
			}
			if (null != cloumn.getAttributeValue("type") && !cloumn.getAttributeValue("type").trim().equals("")) {
				tableColum.setType(cloumn.getAttributeValue("type"));
			}

			tableTableColumChildren = this.getColumns(cloumn, "columns");

			if (null != tableTableColumChildren && !tableTableColumChildren.isEmpty()) {
				tableColum.setColums(tableTableColumChildren);
			}
			if (null != tableColum) {
				columnsList.add(tableColum);
			}
		}

		return columnsList;
	}
	//实体元素
	public List<TableEntity> getModule(Element element, String elementName) {
		List<TableEntity> entityList = new ArrayList<TableEntity>();

		List<Element> columnsElements = element.getChildren(elementName);
		Element entity = null;
		for (int i = 0; i < columnsElements.size(); i++) {
			TableEntity tableEntity = new TableEntity();
			List<TableColum> columsList = new ArrayList<TableColum>();
			entity = (Element) columnsElements.get(i); 
			if (null != entity.getAttributeValue("id") && !entity.getAttributeValue("id").trim().equals("")) {
				tableEntity.setId(entity.getAttributeValue("id"));
			} else if (null == entity.getAttributeValue("id") && entity.getAttributeValue("id").trim().equals("")) {
				break;
			}
			if (null != entity.getAttributeValue("name") && !entity.getAttributeValue("name").trim().equals("")) {
				tableEntity.setName(entity.getAttributeValue("name"));
			}
			if (null != entity.getAttributeValue("dataUrl") && !entity.getAttributeValue("dataUrl").trim().equals("")) {
				tableEntity.setDataUrl(entity.getAttributeValue("dataUrl"));
			}
			if (null != entity.getAttributeValue("type") && !entity.getAttributeValue("type").trim().equals("")) {
				tableEntity.setType(entity.getAttributeValue("type"));
			}
			if (null != entity.getAttributeValue("typeModel") && !entity.getAttributeValue("typeModel").trim().equals("")) {
				tableEntity.setTypeModel(entity.getAttributeValue("typeModel"));
			}
			if (null != entity.getAttributeValue("groupStyle") && !entity.getAttributeValue("groupStyle").trim().equals("")) {
				tableEntity.setGroupStyle(entity.getAttributeValue("groupStyle"));
			}

			columsList = this.getColumns(entity, "columns");

			if (null != columsList && !columsList.isEmpty())
				tableEntity.setColums(columsList);

			if (null != tableEntity)
				entityList.add(tableEntity);
		}
		return entityList;
	}

	public Map<String, Map<String, String>> getEntityHead(String path, String type) throws Exception {
		Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
		String[] types = { "findPageList" };
		if (null != type && !type.trim().equals("")) {
			types = type.split(",");
		}

		List<TableEntity> tableEntityList = new ArrayList<TableEntity>();
		Document doc = getXML(path);
		Element root = doc.getRootElement(); // 得到根元素
		List<Element> columnsElements = root.getChildren("entity");

		for (int i = 0; i < types.length; i++) {

			for (Iterator iterator = columnsElements.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				String entityType = element.getAttributeValue("type");
				if (null == entityType || "".equals(entityType.trim())) {
					entityType = "findPageList";
				}
				if (entityType.toLowerCase().trim().equals(types[i].toLowerCase().trim())) {
					Map<String, String> attrMap = new HashMap<String, String>();
					attrMap.put("id", element.getAttributeValue("id"));
					attrMap.put("name", element.getAttributeValue("name"));
					attrMap.put("dataUrl", element.getAttributeValue("dataUrl"));
					attrMap.put("modelType", entityType);
					attrMap.put("typeModel", element.getAttributeValue("typeModel"));
					attrMap.put("groupStyle", element.getAttributeValue("groupStyle"));
					map.put(getType(entityType), attrMap);
					break;
				}

			}
		}

		return map;
	}

	public String getType(String typeWord) {
		if (null == typeWord || "".equals(typeWord)) {
			return "findPageList";
		}else if(typeWord.toLowerCase().startsWith("findpagelist")){
			return "findPageList";
		} else if (typeWord.toLowerCase().startsWith("modify")) {
			return "modify";
		} else if (typeWord.toLowerCase().startsWith("add")) {
			return "add";
		} else if (typeWord.toLowerCase().startsWith("fuzzy")) {
			return "fuzzy";
		} else if(typeWord.toLowerCase().startsWith("findbyid")){
			return "findById";
		} else {
			return "findPageList";
		}
	} 
}
