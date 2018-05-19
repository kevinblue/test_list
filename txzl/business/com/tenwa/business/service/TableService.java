package com.tenwa.business.service;

import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.model.Table;
import com.tenwa.leasing.entity.file.BaseFile;

public interface TableService extends BaseService
{
	public String getTableJsonData(String xmlFileNameOrPath,Map<String,String> model) throws Exception;
	public String getTableXMLJsonData(String xmlFileNameOrPath,Map<String,String> model) throws Exception;
	public String readRequiredXmlInfo(Table table, boolean isOnlyExportByXmlFile) throws Exception;
	public String getSummaryTableJsonData(String xmlFileNameOrPath,Map<String,String> model) throws Exception;
	public String getTreeJsonData(String xmlFileNameOrPath,Map<String,String> model) throws Exception;
	public void readTabletXmlInfo(Table table,boolean isOnlyExportByXmlFile) throws Exception;
	public void doExportExcel(String xmlFileNameOrPath,Map<String, String> model,boolean isExportTitle,OutputStream os,boolean isOnlyExportByXmlFile) throws Exception;
	public String importExcel(MultipartFile multipartFile,Map<String, String> modelData) throws Exception;
	public void dealExcelDatas(ExcelVersionEnum excelVersionEnum,String complexHeadersStr,String columnsStr,String summaryFields,List<Map<String,String>> datas,Map<String,String> dataTypesMapping,String fileTitleName,boolean isExportTitle,OutputStream os) throws Exception;
	public JSONArray getJsonArrayData(String xmlFileNameOrPath,Map<String, String> model) throws Exception;
	public String createTree2TableJsonString(String savedDataKey,String dictTableName,String dictDataTableName,String rootDictId,boolean isLoadDictData,Map<String,String> savedDataKeyMap) throws Exception;
	public String getRuleScore(String fieldValue,String ruleId) throws Exception;
	public String savedTree2TableData(String savedDataKey,String savedDataDetailJson,Map<String,String> savedDataKeyMap)throws Exception;
	/**
	 * 获取集合内实体的字段值，如果字段是引用类型，获取该类型ID,如果字段是集合类型，则不处理，将结果返回成json字符串
	 * @param collection 需要处理的集合
	 * @param mappingField 匹配字段，且只能是引用类型字段，key格式为a.b，a为字段返回实体名称，b为定义字段，value为字段名称(区分大小写)
	 * 						如果字段为引用类型，但为定义mappingField，则默认取ID
	 * @return json字符串
	 * @throws Exception
	 */
	public String getCollectionJsonString(Collection<?> collection, HashMap<String, String> mappingField) throws Exception;
	/**立项流程第一步添加客户资料上传（车辆资料上传，发票，保单等），<br>然后跟项目有关的所有流程都带出 项目立项上传的附件 ，附件类型分法人客户自然人客户*/
	public void getAttachment(Map<String, String> variablesMap, String project_id,String processDefinitionId)throws Exception;
	public void updateRemoteOper(Map<String,String> model,OperTypeEnum operType) throws Exception;
	public BaseFile saveUpFiletoService(MultipartFile multipartFile, Map<String, String> modelData) throws Exception;
	public String getuploadFileAllDir(String modelname) throws Exception;
	String getuploadFileDir(String modelname) throws Exception;
	public void addlogFileOper(BaseFile bf, String operType) throws Exception;
	/**
	 * 根据当前用户的ID 获得当前用户的数据条件，查看自己，还是全部
	 * @throws Exception
	 */
	public String getDataAuthorityCondtion(String dataAuthority)throws Exception;
}
