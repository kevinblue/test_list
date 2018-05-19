package com.tenwa.leasing.serviceImpl.fileTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.PoiExcelUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.kernal.utils.XMLDataUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.file.BaseFileRecorder;
import com.tenwa.leasing.entity.file.BaseFileTemplate;
import com.tenwa.leasing.entity.file.BaseFileTemplateData;
import com.tenwa.leasing.service.fileTemplate.FileExcelService;
import com.tenwa.leasing.service.fileTemplate.FileTemplateService;
import com.tenwa.leasing.utils.MoneyUtils;
import com.tenwa.leasing.utils.StrTools;
import com.tenwa.leasing.utils.WordBookMarkXmlUtil;
import com.tenwa.leasing.utils.WorkflowUtil;
import com.tenwa.leasing.utils.excel.ReadExecl;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

@Service(value = "fileTemplateService")
public class FileTemplateServiceImp extends AbstractBaseServiceImpl implements FileTemplateService {

	@Resource
	private BaseDao baseDao;

	@Resource(name = "tableService")
	private TableService tableService;
    
	@Resource(name = "fileExcelService")
	private FileExcelService fileExcelService;
	
//	@Resource(name = "ebankImportService")
//	private EbankImportService ebankImportService;

	private Logger logger = Logger.getLogger(FileTemplateServiceImp.class);
	private String wordTemplateDir = ResourceUtil.getWordTemplatesRelativeStorePath();
	private String wordSourceTemplateDir = ResourceUtil.getWordSourceTemplatesRelativeStorePath();
	private String contractWordDir = ResourceUtil.getWordFilesRelativeStorePath();
	private static ObjectMapper jsonMapper = new ObjectMapper();
    
	@Override
	public BaseDao getBussinessDao() throws Exception {
		// TODO Auto-generated method stub
		return baseDao;
	}

	@Override
	public String addFileTemplate(Map<String, String> model) throws Exception {
		// TODO Auto-generated method stub
		BaseFileTemplate ft = new BaseFileTemplate();
		Map<String, String> classFieldMapping = new HashMap<String, String>();
		classFieldMapping.put("DictionaryData", "code");
		this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(model, ft, null);
		ft.setTemplateNo(WorkflowUtil.getFileTemplateNoInfoSerialNumber(null, this.getBussinessDao().getHibernateTemplate(), this.getBussinessDao().getJdbcTemplate()));
		this.getBussinessDao().saveOrUpdateEntity(ft);
		return null;
	}

	@Override
	public String updateFileTemplate(Map<String, String> model) throws Exception {
		String id = model.get("id");
		BaseFileTemplate fileTemplate = (BaseFileTemplate) this.getBussinessDao().findEntityByID(BaseFileTemplate.class, id);
		this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(model, fileTemplate, null);
		this.getBussinessDao().saveOrUpdateEntity(fileTemplate);
		return null;
	}

	@Override
	public String removeFileTemplate(Map<String, String> model) throws Exception {
		String id = model.get("id");
		BaseFileTemplate baseWord;
		baseWord = (BaseFileTemplate) this.getBussinessDao().findEntityByID(BaseFileTemplate.class, id);
		/*
		 * String uploadDirPath =
		 * FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath
		 * ())+this.wordTemplateDir; String
		 * uploadedFileFullPath=uploadDirPath+"/"+baseWord.getTemplatePath();
		 * try {
		 * ResourceUtil.getFileUploadOperation().removeFile(uploadedFileFullPath
		 * ); }catch(Exception e) { String info =
		 * "强制删除文件："+uploadedFileFullPath; if(logger.isInfoEnabled()) {
		 * logger.info(info); } }
		 */
		this.getBussinessDao().removeEntity(baseWord);
		return null;
	}

	@Override
	public BaseFile saveUpFiletoService(MultipartFile multipartFile, Map<String, String> modelData) throws Exception {
		// TODO Auto-generated method stub
		BaseFile bf = new BaseFile();
		String SystemDate = DateUtil.getSystemDate();
		Map<String, String> classFieldMapping = new HashMap<String, String>();
		String modelname = modelData.get("modelname");
		classFieldMapping.put("DictionaryData", "code");
		this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(modelData, bf, classFieldMapping);
		bf.setFileName(multipartFile.getOriginalFilename());
		InputStream source = null;
		try {
			source=multipartFile.getInputStream();
			String uploadDirPath = getuploadFileAllDir(modelname);
			String dirpath = getuploadFileDir(modelname);
			String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			String fileName = UUID.randomUUID() + suffix;
			String uploadedFileFullPath = uploadDirPath + fileName;
			bf.setFileAddress(dirpath + fileName);
			ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, source, null);
			this.getBussinessDao().saveOrUpdateEntity(bf);
			this.addlogFileOper(bf, "上传");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null!=source){FileUtil.safeCloseInputStream(source);}
		}
		return bf;
	}

	@Override
	/**
	 * 将Excl中数据导入实体
	 */
	public <T> Collection<T> getEntitysFromExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List newC = new ArrayList();
		InputStream is = null;
		JSONArray resultJsonArray;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("tableImportTemplate");
			if (null == multipartFile) {
				throw new BusinessException("没有上传附件");
			}
			Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
			String targetClass = model.get("targetClass");
			if (null == targetClass) {
				throw new BusinessException("没有要插入的实体");
			}
			String checkColumnEmpty="0";
			if(model.containsKey("checkColumnEmpty")){
				checkColumnEmpty=model.get("checkColumnEmpty").toString();
			}
			Class<?> targetObject = Class.forName(targetClass);
			String custMethod = "";
			Method operatormethod = null;
			if (model.containsKey("custoperatorMethod")) {
				custMethod = model.get("custoperatorMethod");
				if (null != custMethod && (!"".equals(custMethod.toString().trim()))) {
					try {
						operatormethod = fileExcelService.getClass().getMethod(custMethod, targetObject, Map.class, Integer.class);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new BusinessException("getEntitysFromExcel中没有自定义方法" + custMethod + ":" + e.getMessage());
					}
				}
			}
			BaseFile bf = saveUpFiletoService(multipartFile, model);// 记录上传操作
			// 列名对象解析
			String importExcelColumnStr = model.get("parames");
			JSONArray importExcelColumnJsonArray = new JSONArray(importExcelColumnStr);
			String header = "";
			String mapping = "";
			String fieldclass = "";
			String field = "";
			Map<String, String> classFieldMap = new HashMap<String, String>();
			Map<String, JSONObject> excelFieldMap = new HashMap<String, JSONObject>();
			List excelObject = new ArrayList();
			try {
				for (int i = 0; i < importExcelColumnJsonArray.length(); i++) {
					header = "";
					mapping = "";
					fieldclass = "";
					field = "";
					JSONObject jsonColumn = importExcelColumnJsonArray.getJSONObject(i);
					// {header:'付款账号',mapping:'clientaccount',fieldclass:'',field:''},
					header = jsonColumn.getString("header");
					mapping = jsonColumn.getString("mapping");
					if (jsonColumn.has("fieldclass")) {
						fieldclass = StringUtil.empty2Other(jsonColumn.getString("fieldclass"), "");
					}
					if (jsonColumn.has("field")) {
						field = StringUtil.empty2Other(jsonColumn.getString("field"), "");
					}
					if (fieldclass != "" && field != "") {
						classFieldMap.put(fieldclass, field);
					}
					excelFieldMap.put(header, jsonColumn);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				throw new BusinessException("解析配置的参数出错" + e1.getMessage());
			}
			// 读EXCEL
			is = multipartFile.getInputStream();
			resultJsonArray = new JSONArray();
			Workbook wb = null;
			String importFileName = multipartFile.getOriginalFilename().toLowerCase();
			Sheet sheet;
			try {
				if (importFileName.endsWith("xlsx")) {
					wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2007);
				} else {
					wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2003);
				}
				sheet = wb.getSheetAt(0);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				throw new BusinessException("解析excel现错" + e1.getMessage());
			}
			/*
			 * String skipRowCountStr = StringUtil.nullToString(
			 * model.get("skipRowCount"),"0"); int skipRowCount =
			 * Integer.parseInt(skipRowCountStr);
			 */
			String headerIndexStr = StringUtil.nullToString(model.get("headerIndex"), "1");
			String dataIndexStr = StringUtil.nullToString(model.get("dataIndex"), "2");
			int headerIndex = Integer.parseInt(headerIndexStr);
			int dataIndex = Integer.parseInt(dataIndexStr);
			String[] headerIndexs = headerIndexStr.split(",");
			List<Row> headerRows = new ArrayList<Row>();
			Boolean empty = true;
			// 标题行
			for (int i = 0; i < headerIndexs.length; i++) {
				headerRows.add(sheet.getRow(Integer.parseInt(headerIndexs[i]) - 1));
			}
			// 遍历列和单元格
			Iterator rit = sheet.rowIterator();
			int rowCount = sheet.getPhysicalNumberOfRows();
			boolean emptyRow=false;
			for (int rowIndex = dataIndex - 1; rowIndex < rowCount; rowIndex++) {
				JSONObject jsonObj = new JSONObject();
				Row row = (Row) sheet.getRow(rowIndex);// rit.next();
				empty = true;
				if(null!=row){
					 emptyRow=true;
					for( int j=0;j<row.getPhysicalNumberOfCells();j++){
						Cell cell = PoiExcelUtil.getCell(row,j);
						String value = "";
						value = PoiExcelUtil.getCellValue(cell);
						value=value.replace("\"","\\\"") ;
						if(null!=value){
							 if(!"".equals(value)){
								 emptyRow=false;
							 }
						}
					}
					if(!emptyRow){
					for( int j=0;j<row.getPhysicalNumberOfCells();j++){
						Cell cell = PoiExcelUtil.getCell(row,j);
						String value = "";
						value = PoiExcelUtil.getCellValue(cell);
						value=value.replace("\"","\\\"") ;
						if(null!=value){
						int colIndex = cell.getColumnIndex();
						String headerName = "";
						String name = "";
						String tname = "";
						String type="";
						for (Row r : headerRows) {
							if(null!=r.getCell(colIndex)){
							String headername = r.getCell(colIndex).getStringCellValue().trim();
							String tempheadername="";
							 if(headername.startsWith("*")){
							    	tempheadername=headername.substring(1);
								}else{
									tempheadername=headername;	
								}
							    if(excelFieldMap.containsKey(tempheadername)){
							    	JSONObject jsonColumn= excelFieldMap.get(tempheadername);
									name =jsonColumn.getString("mapping"); 
									if(jsonColumn.has("type")){
										type=jsonColumn.getString("type");
										if(!DataStringValidator.checkStringData(value, type)){
											  throw new BusinessException("Excel中的第"+rowIndex+"行"+headername+"和系统中设定数据格式不对");	
										}
									}
							    }
							if (null != name && (!"".equals(name))) {
							    //检查是否必填
								if(checkColumnEmpty.equals("1")){	 
								   if(headername.startsWith("*")){
									   if("".equals(value)){
										   throw new BusinessException("Excel中的第"+rowIndex+"行"+headername+"不能为空");
									   }
								   }
								}
								tname = name;
							}
						}}
						if ((!"".equals(tname)) && (!"".equals(value))) {
							 empty = false;
							jsonObj.put(tname, value);
						}}
			     }}
				}
				if (empty == false) {
					Object sourectObject = targetObject.newInstance();
					this.tableService.copyAndOverrideExistedValueFromJSONObject(jsonObj, sourectObject, classFieldMap);
					try {
						BeanUtils.getPropertyDescriptor(sourectObject.getClass(), "upLoadId").getWriteMethod().invoke(sourectObject, bf);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						throw new BusinessException("没有文件的ID:upLoadId"+e1.getMessage());
					}
					if (!"".equals(operatormethod) && null != operatormethod) {
						try {
							sourectObject = operatormethod.invoke(fileExcelService, sourectObject, model, (Integer) (rowIndex+1));
							} catch (Exception e) {
							InvocationTargetException targetEx = (InvocationTargetException) e;
							Throwable t = targetEx.getTargetException();
							throw new BusinessException(t.getMessage());
						}
					}
					if(null!=sourectObject){//返回空不插数据库
					   for(int i=0;i<newC.size();i++){
						  if(newC.get(i).equals(sourectObject)){
							throw new BusinessException("Excel中的第"+(i+1)+"行与前面重复");
						  }
					   }
					newC.add(sourectObject);
					}
				}

			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}finally{
			if(null!=is){FileUtil.safeCloseInputStream(is);}
		}
		return newC;
	}

	@Override
	public String getuploadFileAllDir(String modelname) throws Exception {
		// TODO Auto-generated method stub
		String SystemDate = DateUtil.getSystemDate();
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + this.contractWordDir + "/";
		String dirpath = modelname + "/" + SystemDate.replaceAll("-", "/") + "/";
		return uploadDirPath + dirpath;
	}

	@Override
	public String getuploadFileDir(String modelname) throws Exception {
		// TODO Auto-generated method stub
		String SystemDate = DateUtil.getSystemDate();
		String dirpath = modelname + "/" + SystemDate.replaceAll("-", "/") + "/";
		return dirpath;
	}

	@Override
	public String addFileTempalteDateConfig(Map<String, String> model) throws Exception {
		String jsonData = model.get("jsonData");
		String wordid = model.get("id");
		BaseFileTemplate bw = (BaseFileTemplate) this.getBussinessDao().findEntityByID(BaseFileTemplate.class, wordid);
		Map<String, String> propertiesMap = new HashMap<String, String>();
		propertiesMap.put("DictionaryData", "code");
		this.getBussinessDao().updateOneToManyCollections(bw, "fileTemplateDataConfigs", BaseFileTemplateData.class, "fileTemplate", jsonData, propertiesMap, true);
		return null;
	}

	@Override
	public String loadFileTemplateDateConfig(Map<String, String> model) throws Exception {
		String wordid = model.get("id");
		List<BaseFileTemplateData> fileTemplatelist = new ArrayList<BaseFileTemplateData>();
		BaseFileTemplate ft = new BaseFileTemplate();
		ft = (BaseFileTemplate) this.getBussinessDao().findEntityByID(BaseFileTemplate.class, wordid);
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("fileTemplate", ft);
		Map<String, String> fieldClassMapping = new HashMap<String, String>();
		fieldClassMapping.put("baseFileTemplate", "id");
		fileTemplatelist = (List<BaseFileTemplateData>) this.getBussinessDao().findEntityByProperties(BaseFileTemplateData.class, propertiesMap);
		
		if (fileTemplatelist != null && fileTemplatelist.size() > 0) {
			return this.getBussinessDao().getCollectionEntitiesPropertiesToJsonArrayString(fileTemplatelist, fieldClassMapping);
		}
		return "";
	}

	@Override
	public String uploadTemplateFtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		BaseFileTemplate fileTemplate;
		fileTemplate = (BaseFileTemplate) this.getBussinessDao().findEntityByID(BaseFileTemplate.class, modelData.get("id"));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("tableImportExcel");
		InputStream source = null;
		try {
			source=multipartFile.getInputStream();
			String uploadDirPath =WebUtil.getWebContextRealPath()+"template/"+ this.wordTemplateDir;
			String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			String uuid="";
			if(null!=fileTemplate.getTemplatePath()){
				uuid=fileTemplate.getTemplatePath();
				uuid=uuid.substring(0, uuid.indexOf("."));
			}else{
				uuid=UUID.randomUUID().toString();
			}
			String fileName =uuid+ suffix;
			String uploadedFileFullPath = FileUtil.getFilePathString(uploadDirPath + "/" + fileName);
			fileTemplate.setTemplatePath(fileName);
			this.getBussinessDao().saveOrUpdateEntity(fileTemplate);
			ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, source, null);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}finally{
			if(null!=source){FileUtil.safeCloseInputStream(source);}
		}
		return null;
	}

	@Override
	public String downloadTemplateFtl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
			String id = modelData.get("id");
			BaseFileTemplate fileTemplate=null;
			if(modelData.containsKey("id")){
			   fileTemplate = (BaseFileTemplate) this.getBussinessDao().findEntityByID(BaseFileTemplate.class, id);
			}else if(modelData.containsKey("templateNo")){
			   Map<String,Object>propertiesMap=new HashMap<String,Object>();
			   propertiesMap.put("templateNo", modelData.get("templateNo").toString());
			   List<BaseFileTemplate> bfs= this.getBussinessDao().findEntityByProperties(BaseFileTemplate.class, propertiesMap);
			   fileTemplate=bfs.get(0);
			}
			String paddress = fileTemplate.getTemplatePath();
			String suffix = paddress.substring(paddress.lastIndexOf("."));
			String fileTitleName = fileTemplate.getTemplateName() + suffix;
			fileTitleName = this.getdownloadfileName(modelData, fileTitleName);
			String uploadDirPath = WebUtil.getWebContextRealPath()+"template/" + this.wordTemplateDir;
			String downloadedFileFullPath = uploadDirPath + "/" + fileTemplate.getTemplatePath();
			downloadedFileFullPath = FileUtil.getFilePathString(downloadedFileFullPath);
			File file=new File(StrTools.fileNameCheck(downloadedFileFullPath));
			if(!file.isFile()){
				throw new BusinessException("没有下载文件：");
			}
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + fileTitleName);
			response.setContentType("text/html; charset=UTF-8");
			OutputStream out = response.getOutputStream();
			ResourceUtil.getFileUploadOperation().downloadFile(downloadedFileFullPath, out, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e.getMessage());
		}
		return null;
	}

	@Override
	public String uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);

		modelData.remove("id");
		String SystemDate = DateUtil.getSystemDate();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("tableImportTemplate");
		BaseFile bf=null;
		InputStream source = null;
		try {
			source=multipartFile.getInputStream();
			String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + this.contractWordDir + "/" + SystemDate;
			String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			String fileName = UUID.randomUUID() + suffix;
			String uploadedFileFullPath = uploadDirPath + "/" + fileName;
			bf = new BaseFile();
			ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, source, null);
			this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(modelData, bf, null);
			bf.setFileName(multipartFile.getOriginalFilename());
			bf.setFileAddress(SystemDate + "/" + fileName);
			this.getBussinessDao().saveEntity(bf);
			this.addlogFileOper(bf, "上传");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}finally{
           if(null!=source){FileUtil.safeCloseInputStream(source);}
		}
		return bf.getId();
	}

	public String saveuploadSoureFile(HttpServletRequest request,Map<String,String> model) throws Exception{
		MultipartFile multipartFile = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			//加载附件
			multipartFile = multipartRequest.getFile("tableImportExcel");
			//登记上传的附件
			BaseFile bf=this.tableService.saveUpFiletoService(multipartFile, model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return null;
		
	}
	@Override
	public void addlogFileOper(BaseFile bf, String operType) throws Exception {
		// TODO Auto-generated method stub
		BaseFileRecorder bfr = new BaseFileRecorder();
		bfr.setBaseFile(bf);
		bfr.setOperatorType(operType);
		boolean isWithUser = true;
		User user = null;
		try {
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			isWithUser = false;
		}
		if (isWithUser) {
			String systemDate = DateUtil.getSystemDateTime();
			bfr.setCreator(user);
			bfr.setCreateDate(systemDate);
		}
		this.getBussinessDao().saveEntity(bfr);

	}
	
	
	public List<BaseFileTemplate> LoadTemplateByClassify(Map<String, String> modelData) throws Exception {
		List<BaseFileTemplate> listTemplate;
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		System.out.println(modelData.get("sixClassify"));
		if (modelData.containsKey("oneClassify") && modelData.get("oneClassify") != null && !"".equals(modelData.get("oneClassify")) && !"undefined".equals(modelData.get("oneClassify"))) {
			propertiesMap.put("oneClassify",modelData.get("oneClassify"));
			}
		if (modelData.containsKey("twoClassify") && modelData.get("twoClassify") != null && !"".equals(modelData.get("twoClassify")) && !"undefined".equals(modelData.get("twoClassify"))) {
			propertiesMap.put("twoClassify", modelData.get("twoClassify"));
		}
		if (modelData.containsKey("threeClassify") && modelData.get("threeClassify") != null && !"".equals(modelData.get("threeClassify")) && !"undefined".equals(modelData.get("threeClassify"))) {
			propertiesMap.put("threeClassify",modelData.get("threeClassify"));
		}
		if (modelData.containsKey("fourClassify") && modelData.get("fourClassify") != null && !"".equals(modelData.get("fourClassify")) && !"undefined".equals(modelData.get("fourClassify"))) {
			propertiesMap.put("fourClassify",modelData.get("fourClassify"));
		}
		if (modelData.containsKey("fiveClassify") && modelData.get("fiveClassify") != null && !"".equals(modelData.get("fiveClassify")) && !"undefined".equals(modelData.get("fiveClassify"))) {
			propertiesMap.put("fiveClassify",modelData.get("fiveClassify"));
		}
		if (modelData.containsKey("sixClassify") && modelData.get("sixClassify") != null && !"".equals(modelData.get("sixClassify")) && !"undefined".equals(modelData.get("sixClassify"))) {
			propertiesMap.put("sixClassify",modelData.get("sixClassify"));
		}
		Set<String> propertiesSet = propertiesMap.keySet();
		String swhere="";
		for (String propertyName : propertiesSet)
		{
			Object value = propertiesMap.get(propertyName);
			swhere=swhere+" and t."+propertyName+".code='"+value+"'";
		}
		
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			swhere=swhere+" and isnull(t.status, '0')='0'";
		}else if(ResourceUtil.getDBType().indexOf("ORACLE")>-1){
			swhere=swhere+" and nvl(t.status, '0')='0'";
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			swhere=swhere+" and IFNULL(t.status, '0')='0'";
		}
		String HQL="";
		HQL="FROM BaseFileTemplate t where 1=1 "+swhere +"  order by t.templateIndex";
		listTemplate = this.getBussinessDao().findResultsByHSQL(HQL);
		return listTemplate;
	}

	@Override
	public  BaseFileTemplate LoadTemplateByTemplateNo(Map<String,String>modelData)
			throws Exception {
		   Map<String,Object>propertiesMap=new HashMap<String,Object>();
		   propertiesMap.put("templateNo", modelData.get("templateno").toString());
		   List<BaseFileTemplate> bfs= this.getBussinessDao().findEntityByProperties(BaseFileTemplate.class, propertiesMap);
		   if(bfs.size()>0){
			   return bfs.get(0);
		   }else{
			  
			   throw new BusinessException("模板编号为"+modelData.get("templateno")+"没有找到模板");
		   }
	}

	public String createCheckBoxByTemplate(List<BaseFileTemplate> wcList, int changeRowNum) throws Exception {

		String box = "";
		int i = 0;
		box = "<table><tr>";
		for (BaseFileTemplate wc : wcList) {
			box += "<td><input type='checkbox' name='contract_word_list_check_box' value='" + wc.getId() + "'>" + wc.getTemplateName() + "</input></td>";
			i++;
			if (changeRowNum > 0) {
				if (i == changeRowNum) {
					i = 0;
					box = box + "</tr><tr>";
				}
			}
		}
		for (; i < changeRowNum; i++) {
			box = box + "<td></td>";
		}
		box = box + "</tr></table>";
		return box;
	}

	public String createFileByTemplate(Map<String, String> model) throws Exception {
		String templateids = model.get("templateid");
		String modename = model.get("tableid");
		
		
		Map<String,Method>custCreatTemplateMethodMap=new HashMap<String,Method>();
		custCreatTemplateMethodMap=this.getCustCreatTempalteMethod(model);
		List<BaseFileTemplate> fileTemplates = new ArrayList<BaseFileTemplate>();
		String[] ids;
		try {
			ids = templateids.split(",");
			Set<BaseFileTemplateData> templatedataConfigs;
			Map<String, Object> DataMap = new HashMap<String, Object>();
			fileTemplates = this.getBussinessDao().findEntityByIDArray(BaseFileTemplate.class, ids);
			boolean isWithUser = true;
			User user = null;
			try {
				user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			} catch (Exception e) {
				isWithUser = false;
			}
			// 得到当前页面的数据
			Map<String, String> pageMap = new HashMap<String, String>();
			String currentProcessInstanceDBID = "";
			if (model.containsKey("flowunid")) {
				currentProcessInstanceDBID = model.get("flowunid");
				if((!"".equals(currentProcessInstanceDBID)) && null!=currentProcessInstanceDBID){
				pageMap = JBPMUtil.getProcessInstanceHistoryData(this.getBussinessDao().getHibernateTemplate(), currentProcessInstanceDBID);
			}}
			pageMap.putAll(model);
			List<BaseFile> cws = new ArrayList<BaseFile>();
			if (fileTemplates.size() > 0) {
				for (int i = 0; i < fileTemplates.size(); i++) {
					BaseFileTemplate ft = (BaseFileTemplate) fileTemplates.get(i);
					System.out.println("开始操作" + ft.getTemplateName());
					// 查找配置
					templatedataConfigs = ft.getFileTemplateDataConfigs();
					// 获得数据
					String fileName="";
					if(custCreatTemplateMethodMap.containsKey(ft.getTemplateNo())){
						
						try{
							  System.out.println(custCreatTemplateMethodMap.get(ft.getTemplateNo()).getName());	
							  fileName=(String)custCreatTemplateMethodMap.get(ft.getTemplateNo()).invoke(fileExcelService,this,pageMap,ft,modename);
						    
							} catch (Exception e) {
								e.printStackTrace();
							    InvocationTargetException targetEx = (InvocationTargetException) e;
							    Throwable t = targetEx.getTargetException();
							    e.printStackTrace();
							    throw new BusinessException(t.getMessage()+"自定义生成合同出错");
						    }
					}else{
						 if (templatedataConfigs.size() > 0) {
							    DataMap = getTemplateData(templatedataConfigs, pageMap);
						    }else{
						        DataMap= new HashMap<String, Object>();	
						    }				
						    fileName = createRealFile(ft, DataMap, modename);
					}
					
					if (!fileName.equalsIgnoreCase("")) {
					    String[] fileNames=fileName.split(",");
					    for(String tempfileName : fileNames){
					      String fileprx="";
					      String fileaddress="";
					      if(tempfileName.indexOf(";")>0){
					    	  fileprx=tempfileName.split(";")[0];
					    	  fileaddress=tempfileName.split(";")[1];
					       }else{
					    	   fileaddress=tempfileName;
					       }
						   BaseFile cw = new BaseFile();
						   this.getBussinessDao().copyAndOverrideExistedValueFromMap(model, cw);
						 
						   cw.setFlowUnid(currentProcessInstanceDBID);
						   cw.setFileAddress(fileaddress);
						   cw.setBaseFileTemplate(ft);
						   cw.setFileName(ft.getTemplateName());
						   //cw.setTemplateShowName(ft.getTemplateShowName());
						   cw.setFileName(fileprx+ft.getTemplateShowName());
						   cw.setInvalid("0");
						   if (isWithUser) {
							   String systemDate = DateUtil.getSystemDateTime();
							   cw.setCreator(user);
							   cw.setCreateDate(systemDate);
						   }
						 cws.add(cw);
					   }	
				}
			 }
			}
			if (cws.size() > 0) {
				this.getBussinessDao().saveAllEntities(cws);
				Map<String, String> fieldClassMapping = new HashMap<String, String>();
				fieldClassMapping.put("DictionaryData", "code");
				fieldClassMapping.put("FileTemplate", "id");
				return this.getBussinessDao().getCollectionEntitiesPropertiesToJsonArrayString(cws, fieldClassMapping);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		return "";
	}

	public String getfile(Map<String, String> model, BaseFile bf) throws Exception {
		List<BaseFile> cws = new ArrayList<BaseFile>();
		bf.setFlowUnid(bf.getId());
		cws.add(bf);
		Map<String, String> fieldClassMapping = new HashMap<String, String>();
		fieldClassMapping.put("DictionaryData", "code");
		fieldClassMapping.put("FileTemplate", "id");
		return this.getBussinessDao().getCollectionEntitiesPropertiesToJsonArrayString(cws, fieldClassMapping);
	}
	public String getColumnKey(String oneKey,String twoKey,Map<String, String> pageMap){
		String isJoinKeyColumn="1";
		if(pageMap.containsKey("isJoinKeyColumn")){
			isJoinKeyColumn=pageMap.get("isJoinKeyColumn");
		}
		if(isJoinKeyColumn.equals("1")){
			return oneKey+"."+twoKey;
		}else{
			return twoKey;
		}
	}
	public Map getTemplateData(Set<BaseFileTemplateData> templateDataConfigs, Map<String, String> pageMap) throws Exception {
		Map<String, Object> worddata = new HashMap<String, Object>();
		String functionField="";
		String[]vfunctionField=null;
		String function="";
		String[] vfunction=null;
		Map<String,Method>custmethod= new HashMap<String, Method>();
		//自定义取数
		if(pageMap.containsKey("functionField")){
			functionField=pageMap.get("functionField");
			if(null==functionField||"".equals(functionField)||"null".equalsIgnoreCase(functionField)){
				functionField="";
			}
		}
		if(pageMap.containsKey("function")){
			function=pageMap.get("function");
			if(null==function||"".equals(function)||"null".equalsIgnoreCase(function)){
				function="";
			}
		}
		if("".equals(functionField)||"".equals(function)){}
		else{
			vfunctionField=functionField.split(",");
			vfunction=function.split(",");
		}
		
		
		if(null!=vfunctionField&&null!=vfunction){
			if(vfunctionField.length!=vfunction.length){
				throw new BusinessException("配置的自己数据转换域的和方法的数量不相等"+functionField+":"+function);
			}else{
				Method operatormethod=null;
				for(int i=0;i<vfunctionField.length;i++){
					try {
						operatormethod = fileExcelService.getClass().getMethod(vfunction[i], Map.class,Map.class);
					} catch (Exception e1) {
						throw new BusinessException("自定义方法没有找到" + vfunction[i]);
					}
					if(null!=operatormethod){
						custmethod.put(vfunctionField[i], operatormethod);
					}
				}
				
			}
		}
		try {
			//获得报告中的数据(信审报告)
			for (BaseFileTemplateData bw : templateDataConfigs) {
				String formatFields=StringUtil.nullToString(bw.getFormatfield(),"");
				if(formatFields.length()>0){
					formatFields=","+formatFields+",";
				}
				if (bw.getDataSource().equalsIgnoreCase("page")) {
					// 从前台的数据中抓取数据
					if (bw.getKeyType().equalsIgnoreCase("STRING")) {
						// 字段
						String columnKey=getColumnKey(bw.getWordKey(), bw.getWordField(),pageMap);
						if (pageMap.containsKey(columnKey)) {
							worddata.put(bw.getWordKey() + bw.getWordField(),MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(pageMap.get(columnKey),""), bw.getWordField(), formatFields));
							
						} else {
							logger.info(bw.getWordField() + "没有数据");
							worddata.put(bw.getWordKey() + bw.getWordField(), "");
						}
					} else if (bw.getKeyType().equalsIgnoreCase("LIST")) {
						// 列表
						String[] fields = bw.getWordField().split(",");
						List<String> fielddata = new ArrayList<String>();
						for (String field : fields) {
							String columnKey=getColumnKey(bw.getWordKey(),field,pageMap);
							if (pageMap.containsKey(columnKey)) {
								fielddata.add(MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(pageMap.get(columnKey),""),field, formatFields));
							} else {
								fielddata.add("");
							}
						}
						worddata.put(bw.getWordKey(), fielddata);
					} else {
						// 表格
						String[] fields = bw.getWordField().split(",");
						if(pageMap.containsKey(bw.getMultiWordField())){
						String jsonData = pageMap.get(bw.getMultiWordField());
						
						if ("[]".equals(jsonData)||jsonData.length()<10) {
							List<List> tabledata = new ArrayList<List>();
							List<String> fielddata = new ArrayList<String>();
							for (String field : fields) {
								fielddata.add("");
							}
							tabledata.add(fielddata);
							worddata.put(bw.getWordKey(), tabledata);
						}else{
						jsonData=jsonData.replace("'","\""); 
						Map[] newSetMaps = jsonMapper.readValue(jsonData, Map[].class);
						List<List> tabledata = new ArrayList<List>();
						for (int i = 0; i < newSetMaps.length; i++) {
							Map<String, Object> dataMap = new HashMap<String, Object>();
							dataMap = newSetMaps[i];
							List<String> fielddata = new ArrayList<String>();
							for (String field : fields) {
								if("index".equals(field)){
									fielddata.add(String.valueOf(i+1));
								}else{
								  if(field.indexOf("/")>0){
									  //处理多行中两列连接
									  String[] tempfiled=field.split("/");
									  String values="";
									  for(String tem:tempfiled){
										  if (dataMap.containsKey(tem)) {
											  values=values+MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(dataMap.get(tem).toString(),""), tem, formatFields);
										   } else {
											  logger.info(bw.getWordKey() + "." + field + "没有数据");
											  values=values+"";
										   } 
									  }
									  fielddata.add(values);
								  }else{
                                      if(custmethod.containsKey(field)){
                                    	  String value="";
  										if (null != custmethod.get(field)) {
  											try {
  												value=(String)custmethod.get(field).invoke(fileExcelService,pageMap,dataMap);
  											} catch (Exception e) {
  												InvocationTargetException targetEx = (InvocationTargetException) e;
  												Throwable t = targetEx.getTargetException();
  												throw new BusinessException(t.getMessage());
  											}
  										}
  										 fielddata.add(value);
										   
									   }else{	  
								   if (dataMap.containsKey(field)) {
									
									   fielddata.add(MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(dataMap.get(field).toString(),""), field, formatFields));
								   } else {
									  logger.info(bw.getWordKey() + "." + field + "没有数据");
									  fielddata.add("");
								   }}
								  }
								 }
							}
							tabledata.add(fielddata);
						}
						worddata.put(bw.getWordKey(), tabledata);
						}}else{
							List<List> tabledata = new ArrayList<List>();
							List<String> fielddata = new ArrayList<String>();
							for (String field : fields) {
								fielddata.add("");
							}
							tabledata.add(fielddata);
							worddata.put(bw.getWordKey(), tabledata);
						}
					}
				} else {
					// 从数据库中抓取数据
					String sql = bw.getDataSql();
				    Map sqlmap=new HashMap();
					if (sql.toLowerCase().indexOf(".xml") > 0) {
						String realPath = ResourceUtil.getTablesDataSourceDirectoryPath() + "/" + sql;
						realPath = FileUtil.getFilePathString(realPath);
						String tempSql = XMLDataUtil.readTableInfoFromXmlFile(realPath).get("table_sql");
						String realSql = QueryUtil.getQueryString(pageMap, tempSql);
						sqlmap.put("sql", realSql);
						sqlmap.put("value", new Object[]{});
						sqlmap.put("valueType",new int[]{});
					} else {
						sqlmap = StrTools.setSqlCondtion(sql, pageMap);
					}
					System.out.println(sqlmap.get("sql"));
					List rows = this.getBussinessDao().getJdbcTemplate().queryForList(sqlmap.get("sql").toString(),(Object [])sqlmap.get("value"),(int [])sqlmap.get("valueType"));
					Iterator it = rows.iterator();
					if (bw.getKeyType().equalsIgnoreCase("STRING")) {
						// 字段
						if (it.hasNext()) {
							Map dataMap = (Map) it.next();
							if (dataMap.containsKey(bw.getWordField())) {
								worddata.put(bw.getWordKey() + bw.getWordField(), MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(dataMap.get(bw.getWordField())),bw.getWordField(),formatFields));
							} else {
								logger.info(bw.getWordField() + "没有数据");
								worddata.put(bw.getWordKey() + bw.getWordField(), "");
							}
						}
					} else if (bw.getKeyType().equalsIgnoreCase("LIST")) {
						// 列表
						if (it.hasNext()) {
							Map dataMap = (Map) it.next();
							String[] fields = bw.getWordField().split(",");
							List<String> fielddata = new ArrayList<String>();
							for (String field : fields) {
								if (dataMap.containsKey(field)) {
									// fielddata.add((String)dataMap.get(field));
									fielddata.add(MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(dataMap.get(field)).toString(),field,formatFields));
								} else {
									logger.info(bw.getWordKey() + "." + field + "没有数据");
									fielddata.add("  ");
								}
							}
							worddata.put(bw.getWordKey(), fielddata);
						}else{
							//加空字符
							String[] fields = bw.getWordField().split(",");
							List<String> fielddata = new ArrayList<String>();
							for (String field : fields){
									fielddata.add("  ");
							}
							worddata.put(bw.getWordKey(), fielddata);
						}
					} else {
						// 表格
						String[] fields = bw.getWordField().split(",");
						List<List> tabledata = new ArrayList<List>();
						String splitField = "";
						Double rangeValue = 0.00;
						int index = -1;
						try {
							if (pageMap.containsKey("splitfield")) {
								splitField = pageMap.get("splitfield");
							}
							if (pageMap.containsKey("rangevalue")) {
								rangeValue = Double.valueOf(pageMap.get("rangevalue"));
							}
						} catch (Exception e) {
							logger.info("取拆分字段时出错:");
							splitField = "";
							rangeValue = 0.00;
						}
						if(it.hasNext()){
						while (it.hasNext()) {
							Map dataMap = (Map) it.next();
							List<String> fielddata = new ArrayList<String>();
							int i = -1;
							for (String field : fields) {
								i = i + 1;
								if (null != splitField && (!"".equals(splitField))) {
									if (splitField.equalsIgnoreCase(field)) {
										index = i;
									}
								}
								if (dataMap.containsKey(field)) {
									   fielddata.add(MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(dataMap.get(field)).toString(),field,formatFields));
									
								} else {
									logger.info(bw.getWordKey() + "." + field + "没有数据");
									fielddata.add("");
								}
							}
							if (index > 0 && rangeValue > 0) {
								tabledata.addAll(this.SplitListByConfig(fielddata, rangeValue, index));
							} else {
								tabledata.add(fielddata);
							}
						}}else{
						   //补空数据
							List<String> fielddata = new ArrayList<String>();
							for (String field : fields) {
								fielddata.add(" ");
							}
							tabledata.add(fielddata);
						}
						worddata.put(bw.getWordKey(), tabledata);
					}
				}
			}
		}catch(BusinessException b){
			b.printStackTrace();
			throw new BusinessException("加载数据时出错,"+b.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
			throw new BusinessException("加载数据时出错,"+e.getMessage());
		}
		return worddata;
	}
	public String createRealFile(BaseFileTemplate ft, Map<String, Object> dataMap, String modelname) throws Exception {
		String SystemDate = DateUtil.getSystemDate();

		String wordfile;
		InputStream source=null;
		try {
			String uploadDirPath = WebUtil.getWebContextRealPath()+"template/" + this.wordTemplateDir;
		
			String uploadedFileFullPath = uploadDirPath + "/" + ft.getTemplatePath();
			 
			String fileName = FileUtil.getFilePathString(uploadedFileFullPath);
			String paddress = ft.getTemplateShowName();
			String suffix = paddress.substring(paddress.lastIndexOf("."));
		    String templateSuffix=fileName.substring(fileName.lastIndexOf("."));
			File file = new File(StrTools.fileNameCheck(fileName));
			wordfile = "";
			if (file.isFile()) {
				String wordDir = getuploadFileAllDir(modelname);
				wordfile = UUID.randomUUID() + suffix;
				String uploadFiel = FileUtil.getFilePathString(wordDir + "/" + wordfile);
				String tempFile = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath() + "/" + wordfile);
			
				if (suffix.indexOf("doc") > 0) {
					Writer out = null;
					try {
						if(templateSuffix.indexOf("zip")>0){
						    //写入WORD XML中
						    //写到EXCEL中
							this.writeDatatoTemplateWordbyBookMark(fileName, dataMap, tempFile);
						}else{
						Configuration cfg = new Configuration();
						cfg.setDefaultEncoding("utf-8");
						cfg.setDirectoryForTemplateLoading(new File(StrTools.fileNameCheck(uploadDirPath)));
						cfg.setObjectWrapper(new DefaultObjectWrapper());
						Template temp = cfg.getTemplate(ft.getTemplatePath().replaceAll(this.wordTemplateDir + "/", ""));
						File outFile = new File(StrTools.fileNameCheck(tempFile));
						
						out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
					    
						temp.process(dataMap, out);
						out.flush();
						out.close();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new BusinessException("写入word模板时出错"+e.getMessage());
					}finally{
						if(null!=out){FileUtil.safeCloseInputStream(source);}
					}
				}else{
					//写到EXCEL中
					try {
						this.writeDatatoTemplateExcel(fileName, dataMap, tempFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new BusinessException("写入excel模板时出错"+e.getMessage());
					}
				}
				File inFile = new File(StrTools.fileNameCheck(tempFile));
				 source = new FileInputStream(inFile);
				ResourceUtil.getFileUploadOperation().uploadFile(uploadFiel, source, null);
				inFile.delete();
				logger.info("生功生成合同");
			} else {
				throw new BusinessException(ft.getTemplateShowName() + "没有模板");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("createRealFile中的"+e.getMessage());
		}finally{
			if(null!=source){FileUtil.safeCloseInputStream(source);}
		}
		return getuploadFileDir(modelname) + wordfile;
	}
   

	public String downloadAttachById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String id = modelData.get("id");
		BaseFile bw = (BaseFile) this.getBussinessDao().findEntityByID(BaseFile.class, id);
		// 记录下载的信息
		String fileAddress = bw.getFileAddress();
		// String fileTitleName =bw.getFileTemplate().getTemplateShowName();
		String fileTitleName = bw.getFileName();
		this.addlogFileOper(bw, "下载");
		fileTitleName = this.getdownloadfileName(modelData, fileTitleName);
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + this.contractWordDir;
		String downloadedFileFullPath = uploadDirPath + "/" + fileAddress;
		downloadedFileFullPath = FileUtil.getFilePathString(downloadedFileFullPath);
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + fileTitleName);
		response.setContentType("text/html; charset=UTF-8");
		OutputStream out = response.getOutputStream();
		ResourceUtil.getFileUploadOperation().downloadFile(downloadedFileFullPath, out, null);
		return null;
	}

	public String downloadAttachByIdToEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String id = modelData.get("id");
		BaseFile bw = (BaseFile) this.getBussinessDao().findEntityByID(BaseFile.class, id);
		// 记录下载的信息
		String fileAddress = bw.getFileAddress();
		// String fileTitleName =bw.getFileTemplate().getTemplateShowName();

		String fileTitleName =bw.getFileName();
		fileTitleName = fileTitleName.substring(fileTitleName.lastIndexOf("/") + 1).replaceAll("-", "");
		this.addlogFileOper(bw, "下载");
		fileTitleName = this.getdownloadfileName(modelData, fileTitleName);
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + this.contractWordDir;
		String downloadedFileFullPath = uploadDirPath + "/" + fileAddress;
		downloadedFileFullPath = FileUtil.getFilePathString(downloadedFileFullPath);
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + fileTitleName);
		response.setContentType("text/html; charset=UTF-8");
		OutputStream out = response.getOutputStream();
		ResourceUtil.getFileUploadOperation().downloadFile(downloadedFileFullPath, out, null);
		return null;
	}

	public String downloadAttachByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String fileName = modelData.get("filename");
		String fileTitleName = fileName.substring(fileName.indexOf("/") + 1);
		fileTitleName = this.getdownloadfileName(modelData, fileTitleName);
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + this.contractWordDir;
		String downloadedFileFullPath = uploadDirPath + "/" + fileName;
		downloadedFileFullPath = FileUtil.getFilePathString(downloadedFileFullPath);
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=" + fileTitleName);
		response.setContentType("text/html; charset=UTF-8");
		OutputStream out = response.getOutputStream();
		ResourceUtil.getFileUploadOperation().downloadFile(downloadedFileFullPath, out, null);
		return null;
	}

	public String uploadEditTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("fileid");
		BaseFile bf = this.getBussinessDao().findEntityByID(BaseFile.class, id);
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + this.contractWordDir;
		String downloadedFileFullPath = uploadDirPath + "/" + bf.getFileAddress();
		try {
			ResourceUtil.getFileUploadOperation().removeFile(downloadedFileFullPath);
		} catch (Exception e) {
			String info = "强制删除文件：" + downloadedFileFullPath;
			if (logger.isInfoEnabled()) {
				logger.info(info);
			}
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> multipartFiles = multipartRequest.getFileMap();
		MultipartFile multipartFile = multipartFiles.get("upLoadFile");
		InputStream source =null;
		try {
			source=multipartFile.getInputStream();
			ResourceUtil.getFileUploadOperation().uploadFile(downloadedFileFullPath, source, null);
			this.addlogFileOper(bf, "编辑");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}finally{
			if(null!=source){FileUtil.safeCloseInputStream(source);}
		}
		return "编辑成功";
	}

	

	@Override
	public User getCurUser() throws Exception {
		User user = null;
		try {
			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public BaseFile saveCreateBaseFile(String fileRealName, Map<String,String>dataModel,BaseFileTemplate ft) throws Exception {
		// TODO Auto-generated method stub
		BaseFile cw = new BaseFile();
		if (dataModel.containsKey("filename")) {
			cw.setFileName(dataModel.get("filename").toString());
		} else {
			cw.setFileName(ft.getTemplateShowName());
		}
        cw.setFileAddress(fileRealName);
        cw.setBaseFileTemplate(ft);
        cw.setCreator(this.getCurUser());
        if(dataModel.containsKey("flowunid")){
        	cw.setFlowUnid(dataModel.get("flowunid").toString());
        }
        cw.setModelName(dataModel.get("modelname").toString());
        cw.setCreateDate( DateUtil.getSystemDateTime());
        cw.setInvalid("0");
        this.getBussinessDao().saveOrUpdateEntity(cw);
		return cw;
	}

	public List<BaseFile> createFileByTemplateClass(Map<String, String> model) throws Exception {
		//生成文件之前回调
		String custMethod = "";
		Method operatormethod = null;
		
		if (model.containsKey("importorexportcallback")) {
			custMethod = model.get("importorexportcallback").toString();
			if (null != custMethod && (!"".equals(custMethod.toString().trim()))) {
				try {
					operatormethod = fileExcelService.getClass().getMethod(custMethod,Map.class);
				} catch (Exception e1) {
					throw new BusinessException("自定义方法没有找到" + custMethod);
				}
				try {
					if (null != operatormethod) {
						operatormethod.invoke(fileExcelService,model);
					}
				} catch (Exception e) {
					InvocationTargetException targetEx = (InvocationTargetException) e;
					Throwable t = targetEx.getTargetException();
					throw new BusinessException(t.getMessage());
				}
			}
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();//保存要导出的数据
		Map<String, String> pageMap = new HashMap<String, String>();//当前页面上的数据
		BaseFileTemplate ft =null;
		
		Set<BaseFileTemplateData> templatedataConfigs=null;
		List<BaseFile> bfs = new ArrayList<BaseFile>();
	    User user=this.getCurUser();
		//加载页面数据
		String flowunid = "";
		if (model.containsKey("flowunid")) {
			flowunid = model.get("flowunid");
			if(!"".equals(flowunid)){
			pageMap = JBPMUtil.getProcessInstanceHistoryData(this.getBussinessDao().getHibernateTemplate(), flowunid);
		}}
		String fileParames="";
		if(model.containsKey("filesconfig")){
	     	fileParames = model.get("filesconfig");
		}
		BaseFile lastBaseFile = null;
		if ((!"".equals(fileParames))&&(null!=fileParames)) {
			
			ObjectMapper jsonMapper = new ObjectMapper();
			JSONArray jsonArray = new JSONArray(fileParames);
			JSONArray newjsonArray = new JSONArray();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				Map[] newSetMaps = jsonMapper.readValue("[" + jsonObj.toString() + "]", Map[].class);
				Map<String, String> dataModel = newSetMaps[0];
				dataModel.putAll(pageMap);
				dataModel.putAll(model);
				ft=this.LoadTemplateByTemplateNo(dataModel);
				templatedataConfigs=ft.getFileTemplateDataConfigs();
				if (templatedataConfigs.size() > 0) {
					dataMap = getTemplateData(templatedataConfigs, dataModel);
				}
				if (model.containsKey("SystemSN")) {
					dataMap.put("SystemSN", model.get("SystemSN"));
				}
				String fileName = createRealFile(ft, dataMap, model.get("tableid").toString());
				BaseFile cw=this.saveCreateBaseFile(fileName, dataModel, ft);
				this.addlogFileOper(cw, "生成");
				bfs.add(cw);
			}
		} else {
			pageMap.putAll(model);
			ft=this.LoadTemplateByTemplateNo(model);
			templatedataConfigs=ft.getFileTemplateDataConfigs();
			if (templatedataConfigs.size() > 0) {
				dataMap = getTemplateData(templatedataConfigs, pageMap);
			}
			if (model.containsKey("SystemSN")) {
				dataMap.put("SystemSN", model.get("SystemSN"));
			}
			
			String fileName = createRealFile(ft, dataMap, model.get("tableid").toString());
			BaseFile cw=this.saveCreateBaseFile(fileName, model, ft);
			this.addlogFileOper(cw, "生成");
			bfs.add(cw);

		}
//		if (bfs.size() > 1) {
//			String modelname = model.get("modelname");
//			String baseDir = new File(StrTools.fileNameCheck(ResourceUtil.getFileUploadDataPath())).getAbsolutePath();
//			String zipfile = getuploadFileDir(modelname) + UUID.randomUUID() + ".zip";
//			String filename = FileUtil.getFilePathString(baseDir + "/" + this.contractWordDir + "/" + zipfile);
//			String tempFileName = WebUtil.getWebContextRealPath() + "/tempFilesDir/" + UUIDUtil.getUUID() + ".zip";
//			uploadBatchAttachmentFilesByBaseFiles(filename, bfs, tempFileName);
//			BaseFile cw = new BaseFile();
//			this.getBussinessDao().copyAndOverrideExistedValueFromObject(bfs.get(0), cw);
//			cw.setMemo("");
//			for (BaseFile bf : bfs) {
//				cw.setMemo(cw.getMemo() + bf.getMemo() + ",");
//			}
//			if (null != modelDict) {
//				cw.setModelName(modelDict);
//			}
//			cw.setFileRealAddress(zipfile);
//			cw.setFileTemplate(ft);
//			cw.setTemplateName(ft.getTemplateName());
//			cw.setTemplateShowName(ft.getTemplateShowName());
//			cw.setFileName(ft.getTemplateName() + ".zip");
//			this.getBussinessDao().saveOrUpdateEntity(cw);
//			this.addlogFileOper(cw, "生成");
//			lastBaseFile = cw;
//		} else {
//			if (isWithUser) {
//				String systemDate = DateUtil.getSystemDateTime();
//				bf.setCreator(user);
//				bf.setCreateDate(systemDate);
//			}
//			this.getBussinessDao().saveOrUpdateEntity(bf);
//
//			this.addlogFileOper(bfs.get(0), "生成");
//			lastBaseFile = bfs.get(0);
//		}
		if (model.containsKey("importorexportcallbackafter")) {
			custMethod = model.get("importorexportcallbackafter").toString();
			if (null != custMethod && (!"".equals(custMethod.toString().trim()))) {
				try {
					System.out.println(custMethod+":xxxx");
					operatormethod = fileExcelService.getClass().getMethod(custMethod,Map.class);
				} catch (Exception e1) {
					throw new BusinessException("自定义方法没有找到" + custMethod);
				}
				try {
					if (null != operatormethod) {
						operatormethod.invoke(fileExcelService,model);
					}
				} catch (Exception e) {
					InvocationTargetException targetEx = (InvocationTargetException) e;
					Throwable t = targetEx.getTargetException();
					throw new BusinessException(t.getMessage());
				}
			}
		}
		this.tableService.saveAllEntities(bfs);
		return bfs;
	}

	public void uploadBatchAttachmentFilesByBaseFiles(String uploadRemoteFullPath, List<BaseFile> bfs, String tempFileFullPath) throws Exception {
		File tempFileOut = new File(StrTools.fileNameCheck(tempFileFullPath));
		File parentFile = tempFileOut.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		OutputStream os = null;
		ZipOutputStream out = null;
		String charSetName = "GB2312";
		try {
		String baseDir = new File(StrTools.fileNameCheck(ResourceUtil.getFileUploadDataPath())).getAbsolutePath();
		os = new FileOutputStream(StrTools.fileNameCheck(tempFileFullPath));
	
			out = new ZipOutputStream(os);
			         
   		//out.setEncoding(charSetName);
			
			for (BaseFile bf : bfs) {
				// 下载文件
				String inputFileFullPath = FileUtil.getFilePathString(baseDir + "/" + this.contractWordDir + "/" + bf.getFileAddress());
				String chineseName = bf.getFileName();
				out.putNextEntry(new ZipEntry(chineseName));
				ResourceUtil.getFileUploadOperation().readInputStreamToOutputStream(inputFileFullPath, out);
			}
		} finally {
			if (null != out) {
				out.closeEntry();
				out.flush();
				FileUtil.safeCloseOutStream(out);
			}
			if (null != os) {
				os.flush();
				FileUtil.safeCloseOutStream(out);
			}
		}
		File tempFile = new File(StrTools.fileNameCheck(tempFileFullPath));
		FileInputStream fi=null;
		try {
			 fi=new FileInputStream(tempFile);
			ResourceUtil.getFileUploadOperation().uploadFile(uploadRemoteFullPath,fi , null);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(fi!=null){
				FileUtil.safeCloseInputStream(fi);
			}
		}
		tempFile.delete();
	}
	
	//新的插入word
	public void writeDatatoTemplateWordbyBookMark(String tempfile,Map<String, Object> model, String targetFile) throws Exception {
		 Document document = null; 
		 File file = new File(StrTools.fileNameCheck(tempfile));
		 ZipFile docxFile = new ZipFile(file);
		 ZipEntry documentXML = docxFile.getEntry("word/document.xml");
		 InputStream documentXMLIS = docxFile.getInputStream(documentXML); 
    	 SAXReader saxReader = new SAXReader();  
	      document  = saxReader.read(documentXMLIS);
		 if(null!=model){  
		 for (Iterator it = model.entrySet().iterator(); it.hasNext();) {
			Map.Entry element = (Map.Entry) it.next();
			String book_name = element.getKey().toString().trim();// 获得列名
			Object t_value = element.getValue();
			System.out.println(book_name);
			if (null != t_value){
				if (t_value instanceof String) {
					WordBookMarkXmlUtil.replaceBookMark(document,book_name,StringUtil.nullToString( t_value));
			    }else{
			    	List templistvalue = (List) t_value;
				    if (templistvalue.size() > 0 && templistvalue.get(0) instanceof List) {
				    	//写入表格
				    	if(book_name.startsWith("MT_")){//限制长度的表格
				    		WordBookMarkXmlUtil.replaceMTTableBookMark(document, book_name, templistvalue,true,5);
				    	}else if(book_name.startsWith("MS_")){
				    		WordBookMarkXmlUtil.replaceMSTableBookMark(document, book_name, templistvalue);
				    	}else if(book_name.startsWith("MD_")){
							//书签插入文件
							for(int i=0;i<templistvalue.size();i++){
						    	  List temp=(List)templistvalue.get(i);
						    	  Map<String,String> oneData=new HashMap<String,String>();
						    	  oneData.put("templateno", temp.get(0).toString());
						    	  oneData.put("id",temp.get(1).toString());
						    	  BaseFileTemplate ft = null;
					      		  Set<BaseFileTemplateData> templatedataConfigs = null;
					      		  ft = this.LoadTemplateByTemplateNo(oneData);
					  			  templatedataConfigs = ft.getFileTemplateDataConfigs();
					  			  Map<String,Object>cdataMap=new HashMap<String,Object>();
								  if (templatedataConfigs.size()>0) {
									cdataMap = getTemplateData(templatedataConfigs, oneData);
									}
								  String fileName = createRealFile(ft, cdataMap,temp.get(1).toString());
								 
								  fileName=FileUtil.getFilePathString(ResourceUtil
											.getFileUploadDataPath() + "/"+this.contractWordDir+"/" + fileName);
								  WordBookMarkXmlUtil.replaceMDBookMarkByFile(document, book_name, fileName);
								  
						      } 
							
						}
				    	else {
				    		WordBookMarkXmlUtil.replaceTableBookMark(document, book_name, templistvalue);
				    	}
				    	//表格拷贝套打
				    	
				    }else{
				    	if(book_name.startsWith("MC_")){
				    		//表格方式只插数，不插表格，把多余的表格删除
				    		WordBookMarkXmlUtil.replaceMCListBookMark(document, book_name, templistvalue, true, 5);
				    	}else{
				    	//写入列表
				    	for(int j=0;j<templistvalue.size();j++){
				    		WordBookMarkXmlUtil.replaceBookMark(document,book_name+"_"+j+"",StringUtil.nullToString(templistvalue.get(j)));
				    	}}
				    }
				}
		 }
	  }}
	  //保存文档
		 WordBookMarkXmlUtil.saveWordXML(docxFile,document, targetFile); 	 
	}
	//写excel 模板
	@Override
	public void writeDatatoTemplateExcel(String tempfile, Map<String, Object> model, String targetFile) throws Exception {
			Row templateRow;
			Cell cell;
			Workbook wb=null;
			String custMethod = "";
			Method operatormethod = null;
			Boolean flag;
			OutputStream osServer=null;
			Class WorkflowUtilClass = Class.forName("com.tenwa.leasing.utils.WorkflowUtil");
			// 插入之前调自定义方法
			try {
				if (tempfile.endsWith("xlsx")) {
					wb = PoiExcelUtil.readWorkbook(tempfile, ExcelVersionEnum.VERSION2007);
				} else {
					wb = PoiExcelUtil.readWorkbook(tempfile, ExcelVersionEnum.VERSION2003);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new BusinessException("加载excel时出错"+e.getMessage());
			}
			try {
			ReadExecl readExecl=null;
			Sheet templateSheet=null;
			try {
				readExecl = new ReadExecl(tempfile);
				templateSheet = wb.getSheetAt(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new BusinessException("加载readExecl时出");
			}
			for (Iterator it = model.entrySet().iterator(); it.hasNext();) {
				Map.Entry element = (Map.Entry) it.next();
				String title_name = element.getKey().toString().trim();// 获得列名
				if (logger.isInfoEnabled()) {
					logger.info("Excel开始生成写入" + title_name + "块");
				}
				Object t_value = element.getValue();
				if (null != t_value && title_name != "SystemSN") {
					if (t_value instanceof String) {
						templateSheet = wb.getSheetAt(readExecl.getCell(readExecl.getCellReference(title_name)).getSheet_index());
						int col_index = readExecl.getCellColNum(title_name);
						int row_index = readExecl.getCellRowNum(title_name);
						if (col_index >= 0 && row_index >= 0) {
							templateRow = templateSheet.getRow(row_index);
							cell = templateRow.getCell(col_index);
							cell.setCellValue(t_value.toString());
							if (logger.isInfoEnabled()) {
								logger.info("Excel开始生成写入" + title_name + ":" + t_value.toString());
							}
						}
					} else {
						List templistvalue = (List) t_value;
						if (templistvalue.size() > 0 && templistvalue.get(0) instanceof List) {
							// 表格
							// 查找sheet
							int col_index = 0;
							int row_index = 0;
							if (title_name.startsWith("MS_")) {

							} else {
								if (title_name.startsWith("listtable")) {
									col_index = readExecl.getCellColNum(title_name + "_start");
									row_index = readExecl.getCellRowNum(title_name + "_start");
									templateSheet = wb.getSheetAt(readExecl.getCell(readExecl.getCellReference(title_name + "_start")).getSheet_index());
								} else if(title_name.startsWith("ML_")){
									col_index = readExecl.getCellColNum(title_name + "0_0");
									row_index = readExecl.getCellRowNum(title_name + "0_0");
									templateSheet = wb.getSheetAt(readExecl.getCell(readExecl.getCellReference(title_name+ "0_0")).getSheet_index());		
								}else
								{
									
									col_index = readExecl.getCellColNum(title_name);
									row_index = readExecl.getCellRowNum(title_name);
									templateSheet = wb.getSheetAt(readExecl.getCell(readExecl.getCellReference(title_name)).getSheet_index());

								}
							}
							if (col_index >= 0 && row_index >= 0) {
								if (title_name.startsWith("listtable")) {
									// 条件表格
									int col_startindex = readExecl.getCellColNum(title_name + "_start");
									int row_startindex = readExecl.getCellRowNum(title_name + "_start");
									int col_endindex = readExecl.getCellColNum(title_name + "_end");
									int row_endindex = readExecl.getCellRowNum(title_name + "_end");
									int region = row_endindex - row_startindex + 1;
									if (templistvalue.size() > 0) {
										PoiExcelUtil.insertRowArea(templateSheet, row_startindex + 1, row_endindex + 1, templistvalue.size() - 1);
										readExecl.bookNamesMove(row_startindex + 1, row_endindex + 1, templistvalue.size() - 1);
										for (int i = 0; i < templistvalue.size(); i++) {
											List tempvalue = (List) templistvalue.get(i);
											for (int j = 0; j < tempvalue.size(); j++) {
												row_index = readExecl.getCellRowNum(title_name + "_" + j);
												templateRow = templateSheet.getRow(row_index + i * region);
												col_index = readExecl.getCellColNum(title_name + "_" + j);
												if (col_index >= 0) {
													cell = templateRow.getCell(col_index);
													cell.setCellValue(tempvalue.get(j).toString());
													if (logger.isInfoEnabled()) {
														logger.info("Excel开始生成写入" + title_name + "[" + row_index + i * region + 1 + "][" + col_index + "]:" + tempvalue.get(j).toString());
													}
												}
											}
										}
									} else {
									}
								} else if (title_name.startsWith("MS_")) {
									// 多个sheet表，复制第一个sheet
									if (templistvalue.size() > 0) {
										//重命名模板sheet名称
										List tempvalue0 = (List) templistvalue.get(0);
							        	wb.setSheetName(0,tempvalue0.get(0).toString());
										for (int i = 1; i < templistvalue.size(); i++) {
											    List tempvalue = (List) templistvalue.get(i);
									        	Sheet targetSheet = wb.createSheet(tempvalue.get(0).toString());
												PoiExcelUtil.CopySheet(templateSheet, targetSheet);	
										}
										for (int i = 0; i < templistvalue.size(); i++) {
											templateSheet = wb.getSheetAt(i);
											List tempvalue = (List) templistvalue.get(i);
											for (int j = 1; j < tempvalue.size(); j++) {
												row_index = readExecl.getCellRowNum(title_name + "_" + (j-1));
												col_index = readExecl.getCellColNum(title_name + "_" + (j-1));
												templateRow = templateSheet.getRow(row_index);
												if(null!=templateRow){
												cell = templateRow.getCell(col_index);
												String value = tempvalue.get(j).toString();
												if (tempvalue.get(j).toString().equalsIgnoreCase("SystemSN")) {
													String sn = "";
													if (model.containsKey("SystemSN")) {
														String SystemSN = model.get("SystemSN").toString();
														if (null == SystemSN || "null".equalsIgnoreCase(SystemSN) || "".equals(SystemSN)) {

														} else {
															Method operatorS = WorkflowUtilClass.getMethod(SystemSN, Map.class, HibernateTemplate.class, JdbcTemplate.class);
															if (null != operatorS) {
																sn = (String) operatorS.invoke(WorkflowUtilClass, null, this.baseDao.getHibernateTemplate(), this.baseDao.getJdbcTemplate());
															}
														}
													}
													value = sn;
												}
												cell.setCellValue(value);
												}else{
													//System.out.println(title_name + "_" + j);
												}
												// if(logger.isInfoEnabled())
												// {
												// logger.info("Excel开始生成写入"+title_name+"["+row_index+"]["+col_index+"]:"+tempvalue.get(j).toString());
												// }
											}
										}
									}

								}else if(title_name.startsWith("ML_")){
								 //System.out.println("========"+templistvalue);
								 //System.out.println("========"+title_name);
                                  // 表格列表用于表格中有合并单元格
									if (templistvalue.size() > 0) {
										for (int i = 0; i < templistvalue.size(); i++) {
											List tempvalue = (List) templistvalue.get(i);
										 	for (int j = 0; j < tempvalue.size(); j++) {
												col_index = readExecl.getCellColNum(title_name +i +"_" + j);
												row_index = readExecl.getCellRowNum(title_name +i +"_" + j);
												System.out.println(title_name + "_" + i+":"+col_index+":"+row_index);
												if (col_index >= 0 && row_index >= 0) {
													templateRow = templateSheet.getRow(row_index);
													//cell = templateRow.getCell();
													if(null!=templateRow){
													cell=PoiExcelUtil.getCell(templateRow,col_index);
													cell.setCellValue(tempvalue.get(j).toString());
													if (logger.isInfoEnabled()) {
														logger.info("Excel开始生成写入" + title_name + "_" + i + ":" + tempvalue.get(j).toString());
													}}
												}
											}
										}
									}

								
								}	
								else {
									List columnIndex=new ArrayList();
									columnIndex=PoiExcelUtil.getMergedRegionIndex(templateSheet, row_index-1);
									if( templistvalue.size()>1){
										//MC_开头说明表格的单元格不用新增
										if (!title_name.startsWith("MC_")){
									        PoiExcelUtil.insertRow(templateSheet, row_index, templistvalue.size() - 1);
									        readExecl.bookNamesMove(row_index, row_index + templistvalue.size() - 1, 1);
										}
									}
							     	//MR_开头(合并表中第一列之前的一列的单元格)
									if (title_name.startsWith("MR_")){
										//合并左边的单元格
									   if(Integer.valueOf(columnIndex.get(col_index).toString())-1>=0){
									     CellRangeAddress cellRangeAddress = new CellRangeAddress(row_index-1, row_index+templistvalue.size()-1, Integer.valueOf(columnIndex.get(col_index).toString())-1,Integer.valueOf(columnIndex.get(col_index).toString())-1);
									     templateSheet.addMergedRegion(cellRangeAddress);
									    }
									}
								System.out.println(columnIndex+"=========xu");
								  for (int i = 0; i < templistvalue.size(); i++) {
										List tempvalue = (List) templistvalue.get(i);
										templateRow = PoiExcelUtil.getRow(templateSheet, row_index + i);
										System.out.println(tempvalue.size());
										for (int j = 0; j < tempvalue.size(); j++) {
											cell = PoiExcelUtil.getCell(templateRow, Integer.valueOf(columnIndex.get(col_index + j).toString()));
											//cell = PoiExcelUtil.getCell(templateRow,col_index + j);
											cell.setCellValue(tempvalue.get(j).toString());
											if (logger.isInfoEnabled()) {
												logger.info("Excel开始生成写入" + title_name + "[" + row_index + i + "][" + col_index + j + "]:" + tempvalue.get(j).toString());
											}
										}
									}
								}
							}

						} else {
							int col_index = 0;
							int row_index = 0;
							// 删除没有数据的列表
							if (title_name.startsWith("listtable")) {
								col_index = readExecl.getCellColNum(title_name + "_start");
								row_index = readExecl.getCellRowNum(title_name + "_start");
								templateSheet = wb.getSheetAt(readExecl.getCell(readExecl.getCellReference(title_name + "_start")).getSheet_index());
								int col_startindex = readExecl.getCellColNum(title_name + "_start");
								int row_startindex = readExecl.getCellRowNum(title_name + "_start");
								int col_endindex = readExecl.getCellColNum(title_name + "_end");
								int row_endindex = readExecl.getCellRowNum(title_name + "_end");
								int region = row_endindex - row_startindex + 1;
								PoiExcelUtil.deleteRowByArea(templateSheet, row_startindex, row_endindex);
								readExecl.bookNamesMove(row_startindex, row_endindex, -1);
							}
							// 列表
							// 查找sheet
							if (templistvalue.size() > 0) {
								templateSheet = wb.getSheetAt(readExecl.getCell(readExecl.getCellReference(title_name + "_0")).getSheet_index());
								for (int i = 0; i < templistvalue.size(); i++) {
									col_index = readExecl.getCellColNum(title_name + "_" + i);
									row_index = readExecl.getCellRowNum(title_name + "_" + i);
									System.out.println(title_name + "_" + i+":"+col_index+":"+row_index);
									if (col_index >= 0 && row_index >= 0) {
										templateRow = templateSheet.getRow(row_index);
										//cell = templateRow.getCell();
										if(null!=templateRow){
										cell=PoiExcelUtil.getCell(templateRow,col_index);
										cell.setCellValue(templistvalue.get(i).toString());
										if (logger.isInfoEnabled()) {
											logger.info("Excel开始生成写入" + title_name + "_" + i + ":" + templistvalue.get(i).toString());
										}}
									}
								}
							}
						}
					}
				}
			}
			osServer = new FileOutputStream(StrTools.fileNameCheck(targetFile));
			wb.write(osServer);
			osServer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("写excel时出错"+e.getMessage());
		}finally{
			if(null!=osServer){FileUtil.safeCloseOutStream(osServer);}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String uploadExcelFileToObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		try {
			String fileupdatedate="";
			Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
			String isSaveTo="";
			if(model.containsKey("isSaveTo")){
				isSaveTo=model.get("isSaveTo").toString();
			}else{
				isSaveTo="savedate";
			}
			Map<String,String>prototypesClassFieldMap=new HashMap<String,String>();
			prototypesClassFieldMap.put("CustInfo", "id");
			List ccri = new ArrayList();
			ccri = (List) getEntitysFromExcel(request, response);
			String message = "";
			if (null != ccri && ccri.size() > 0) {
				logger.debug("上传输出:");
				logger.debug(ccri.get(0));
				
				if(isSaveTo=="savedate"){
					try {
				   this.tableService.saveOrUpdateAllEntities(ccri);
				   
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				    //清空网银流水号临时数据
				 
//				   //如果是网银导入则要生成凭证
//				   if("com.business.entity.asset.FundEbankData".equals(model.get("targetClass"))){
//					   for (int i=0;i<ccri.size();i++){
//						   //传入网银信息
//					      ebankImportService.createVoucherForFund((FundEbankData)ccri.get(i));
//					   }
//				   }
				   message = "\"message\":\"成功导入导入" + ccri.size() + "条\"";
				}else{
					fileupdatedate=this.tableService.getCollectionEntitiesPropertiesToJsonArray(ccri,prototypesClassFieldMap).toString();
					message = "\"message\":\"成功导入导入" + ccri.size() + "条\",\"fileupdatedate\":"+fileupdatedate+"";
						
				}
			} else {
				message = "\"message\":\"\"";
			}
			  
			  Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
		         Matcher m = p.matcher(message);  
		         message = m.replaceAll("");  
			return message;
		} catch (BusinessException b) {
			//清空网银流水号临时数据
		    
			throw new BusinessException("上传出错:" + b.getMessage());
		} catch (Exception e) {
			//清空网银流水号临时数据
			e.printStackTrace();
			throw new BusinessException("上传出错:" + e.getMessage());

		}
	}
	@Override
	public String getDefaultAjaxCallBack(String message) throws Exception {
		// TODO Auto-generated method stub
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent.DefaultAjaxCallBack('" + message + "');</script>";
		return ajaxCallBackScript;
	}

	public List<List> SplitListByConfig(List<String> ExcelData, double dual, int index) throws Exception {
		String sourceDouble = ExcelData.get(index).toString();
		sourceDouble=sourceDouble.replaceAll(",", "");
		Double cmoney = 0.00;
		cmoney = Double.valueOf(sourceDouble);
		List<String> temp = new ArrayList<String>();
		List<List> tabledata = new ArrayList<List>();
		if (cmoney > 0 && cmoney > dual) {
			while (cmoney > 0) {
				temp = new ArrayList<String>();
				for (int i = 0; i < ExcelData.size(); i++) {
					if (i != index) {
						temp.add(ExcelData.get(i));
					} else {
						temp.add(String.valueOf(dual));
					}
				}
				tabledata.add(temp);
				if(cmoney<=dual){
					temp = new ArrayList<String>();
					for (int i = 0; i < ExcelData.size(); i++) {
						if (i != index) {
							temp.add(ExcelData.get(i));
						} else {
							temp.add(String.valueOf(cmoney));
						}
					}
					tabledata.add(temp);	
				}
				
				cmoney = cmoney - dual;
			}
		} else {
			tabledata.add(ExcelData);
		}
		return tabledata;
	}

	public String getdownloadfileName(Map<String, String> model, String fileName) throws Exception {

		String browserType = model.get("browserType");
		String fileTitleName = fileName;
		if ("firefox".equals(browserType)) {
			fileTitleName = new String(fileTitleName.getBytes("GB2312"), "ISO-8859-1");
		} else if ("chrome".equals(browserType)) {
			//System.out.println(fileTitleName);
			//fileTitleName = URLEncoder.encode(fileTitleName, "UTF-8");
			fileTitleName=new String(fileTitleName.getBytes("UTF-8"), "ISO-8859-1");
		} else if ("safari".equals(browserType)) {
			fileTitleName = new String(fileTitleName.getBytes("UTF-8"), "ISO-8859-1");
		} else {
			fileTitleName = URLEncoder.encode(fileTitleName, "UTF-8");
		}
		return fileTitleName;
	}
	 public String removeTemplate()throws Exception{
		 List<BaseFileTemplate> templates=new ArrayList<BaseFileTemplate>();
		 templates=this.tableService.findEntities(BaseFileTemplate.class);
		 String sourcepath= ResourceUtil.getFileUploadDataPath();
		 String targetPath=WebUtil.getWebContextRealPath()+"template/";
		 InputStream source = null;
		 try {
			for(BaseFileTemplate t : templates){
			      if(null!=t.getTemplatePath()){
			    	  String file=sourcepath+this.wordTemplateDir+"/" + t.getTemplatePath();;
			    	  String targerfile=targetPath+this.wordTemplateDir +"/" + t.getTemplatePath();
			    	  File f=new File(StrTools.fileNameCheck(file));
			    	  if(null!=f && f.isFile()){
			    		   source = new FileInputStream(f);
						ResourceUtil.getFileUploadOperation().uploadFile(targerfile, source, null); 
			    	  }
			      }
			 }
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}finally{
			if(null!=source){FileUtil.safeCloseInputStream(source);}
		}
		 return "";
	 }
	public Map<String,Method>getCustCreatTempalteMethod(Map<String,String> model)   throws Exception {
		String custcreateTemplateNo="";
		String custcreateTemplateMethod="";
		String[]vcustcreateTemplateNo=null;
		String[]vcustcreateTemplateMethod=null;
		Map<String,Method>custCreatTemplateMethodMap=new HashMap<String,Method>();
		try {
			if (model.containsKey("custcreatetemplateno")) {
				custcreateTemplateNo = model.get("custcreatetemplateno");
				if (null == custcreateTemplateNo
						|| "".equals(custcreateTemplateNo)
						|| "null".equalsIgnoreCase(custcreateTemplateNo)) {
					custcreateTemplateNo = "";
				}
			}
			if (model.containsKey("custcreatetemplatemethod")) {
				custcreateTemplateMethod = model
						.get("custcreatetemplatemethod");
				if (null == custcreateTemplateMethod
						|| "".equals(custcreateTemplateMethod)
						|| "null".equalsIgnoreCase(custcreateTemplateMethod)) {
					custcreateTemplateMethod = "";
				}
			}
			if ("".equals(custcreateTemplateNo)
					|| "".equals(custcreateTemplateMethod)) {
			} else {
				//custmethod = new HashMap<String, Method>();
				vcustcreateTemplateNo = custcreateTemplateNo.split(",");
				vcustcreateTemplateMethod = custcreateTemplateMethod.split(",");
			}
			if (null != vcustcreateTemplateNo
					&& null != vcustcreateTemplateMethod) {
				if (vcustcreateTemplateNo.length != vcustcreateTemplateMethod.length) {
					throw new BusinessException("配置的自己数据转换域的和方法的数量不相等"
							+ custcreateTemplateNo + ":"
							+ custcreateTemplateMethod);
				} else {
					Method operatormethod = null;
					for (int i = 0; i < vcustcreateTemplateMethod.length; i++) {
						try {
							operatormethod = fileExcelService.getClass()
									.getMethod(vcustcreateTemplateMethod[i],
											FileTemplateService.class,
											Map.class, BaseFileTemplate.class,
											String.class);
						} catch (Exception e1) {
							throw new BusinessException("自定义方法没有找到"
									+ vcustcreateTemplateMethod[i]);
						}
						if (null != operatormethod) {
							custCreatTemplateMethodMap.put(
									vcustcreateTemplateNo[i], operatormethod);
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custCreatTemplateMethodMap;
	}

	@Override
	public String saveFileState(Map<String, String> model) throws Exception {
		String id=model.get("id");
		BaseFile bf=this.baseDao.findEntityByID(BaseFile.class, id);
		bf.setInvalid(model.get("state"));
		return null;
	}
	
}
