package com.tenwa.kernal.utils.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tenwa.business.model.TableColum;
import com.tenwa.business.model.TableEntity;
import com.tenwa.business.model.UIColumns;
import com.tenwa.kernal.utils.ResourceUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class ConvertUtil {
	/**
	 * @param entityField
	 *            数据库中读取的表头字段
	 * @param xmlModel
	 *            前台数据的XML配置文件
	 * @param type
	 *            类型，find, updata, 等，默认find
	 * @return
	 */
	private int num = 0;
	private List<UIColumns> tempColumns = new ArrayList<UIColumns>();

	public static List<TableEntity> getTableEntities(String xmlModel) {
		List<TableEntity> tableEntities = new ArrayList<TableEntity>();
		try {
			String path = ResourceUtil.getTablesDataSourceDirectoryPath() + xmlModel;
			// 解析xml文件
			tableEntities = TableXMLUtil.getInstance().parseXMLtoModule(path);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tableEntities;

	}

	public List<UIColumns> convertToUIData(List<Map<String, String>> entityField, String xmlModel, String type) {
		List<UIColumns> list = new ArrayList<UIColumns>();
		List<TableEntity> tableEntities = getTableEntities(xmlModel);

		for (TableEntity tableEntity : tableEntities) {
			String columnType = tableEntity.getType().toLowerCase();

			if (columnType.equals(type.toLowerCase())) {
				list = convertEntity(tableEntity, entityField);
			} 
		}

		return list;
	}
	/**
	 * @param tableEntity 解析的xml文件
	 * @param entityField 数据库中读取的表头字段
	 * @return
	 */
	public List<UIColumns> convertEntity(TableEntity tableEntity, List<Map<String, String>> entityField) {
		List<UIColumns> columnsList = new ArrayList<UIColumns>();

		for (int i = 0; i < entityField.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map = entityField.get(i);
			UIColumns uiColumns = new UIColumns();
			Boolean hasColumns = false;
			String id = map.get("field");
			String name =map.get("title");
			uiColumns.setField(id);

			if (id.equals("id")) {// id字段设置为隐藏
				uiColumns.setType("hidden");
			}
			uiColumns.setTitle(map.get("title"));
			if(tableEntity.getColums()!=null){
				for (TableColum tableColum : tableEntity.getColums()) {
					String columnid = tableColum.getId();

					if (null != columnid && columnid.equals(id)) {
						if(tableColum.getName() == null){
							tableColum.setName(name);
						}
						getModual(columnsList, tableColum, "");
						hasColumns = true;
					}
				}
			}
			if (!hasColumns) {
				uiColumns.setSort(num);
				num++;
				columnsList.add(uiColumns);
			}
		}


		if (!tempColumns.isEmpty()) {// 将含有排序字段的临时列表按排序序号插入到表
			if (tempColumns.size() > 1) {
				Collections.sort(tempColumns);
			}
			for (UIColumns uiColumns : tempColumns) {
				columnsList.add(uiColumns.getSort(), uiColumns);
			}
		}
		
	/*	if (tableEntity.getColums() != null) {
			for (TableColum tableColum : tableEntity.getColums()) {
				UIColumns uiColumn = new UIColumns();
				if (null != tableColum.getType() && tableColum.getType().toLowerCase().trim().equals("set")) {
					System.out.println("set +++++++");
					if (null != tableColum.getId())
						uiColumn.setField(tableColum.getId());
					if (null != tableColum.getName())
						uiColumn.setTitle(tableColum.getName());
					if (null != tableColum.getAttribute())
						uiColumn.setAttribute(tableColum.getAttribute());
					if (null != tableColum.getType())
						uiColumn.setType(tableColum.getType());
					if (null != tableColum.getColumnsStyle())
						uiColumn.setColumnsStyle(tableColum.getColumnsStyle());
					if (null != tableColum.getColumnValidation())
						uiColumn.setColumnValidation(tableColum.getColumnValidation());
					if (-1 != tableColum.getSort())
						uiColumn.setSort(tableColum.getSort());
					columnsList.add(uiColumn);
				}
			}
		}*/
		return columnsList;
	}

	/**
	 * @param columnsList
	 *            返回字段添加到list
	 * @param tableColum
	 *            遍历的table
	 * @param id
	 *            父id
	 */
	public void getModual(List<UIColumns> columnsList, TableColum tableColum, String id) {
		UIColumns uiColumns = new UIColumns();
		String columnId = id.equals("") ? tableColum.getId() : id + "." + tableColum.getId();

		if (null == tableColum.getColums()) {
			System.out.println(tableColum.getType());
			
			if (tableColum.getId().equals("id")) {
				uiColumns.setType("hidden");
			}
			if (null != tableColum.getType()) {
				uiColumns.setType(tableColum.getType());
				if (uiColumns.getType().toLowerCase().equals("none")) {// 如果设置了none属性，则不加载该字段
					return;
				}
			}
			
			if (null != tableColum.getAttribute()) {
				uiColumns.setAttribute(tableColum.getAttribute());
			}
			uiColumns.setField(columnId);
			if (null != tableColum.getName()) {
				uiColumns.setTitle(tableColum.getName());
			}
			
			if (null != tableColum.getColumnsStyle())
				uiColumns.setColumnsStyle(tableColum.getColumnsStyle());
			if (null != tableColum.getColumnValidation())
				uiColumns.setColumnValidation(tableColum.getColumnValidation());

			if (-1 != tableColum.getSort()) {// 如果有排序，将有排序的添加到中间list中，排序之后在添加到表中
				uiColumns.setSort(tableColum.getSort());
				tempColumns.add(uiColumns);
			} else {
				uiColumns.setSort(num);
				num++;
				columnsList.add(uiColumns);
			}

		} else if (null != tableColum.getColums()) {
			List<TableColum> colums = tableColum.getColums();
			for (TableColum tableColum2 : colums) {
				getModual(columnsList, tableColum2, columnId);
			}
		}
	}

	public Map<String, String> getTableInfo(String xmlModel, String type) {
		List<TableEntity> tableEntities;
		Map<String, String> map = new HashMap<String, String>();
		tableEntities = getTableEntities(xmlModel);
		for (TableEntity tableEntity : tableEntities) {
			String columnType = tableEntity.getType().toLowerCase();
			if (columnType.equals(type.toLowerCase())) {
				// map.put("entity", value)
			}
		}
		return map;
	}
}
