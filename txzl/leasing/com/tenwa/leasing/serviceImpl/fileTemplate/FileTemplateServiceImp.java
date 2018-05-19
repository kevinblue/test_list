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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.AttachmentFileUploadInfo;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.service.AppImageService;
import com.tenwa.business.service.AttachmentFileService;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.utils.ArrayUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.PoiExcelUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.kernal.utils.XMLDataUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.excel.ExcelCellConfig;
import com.tenwa.leasing.entity.excel.ExcelSheetConfig;
import com.tenwa.leasing.entity.excel.ExcelTableConfig;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.file.BaseFileRecorder;
import com.tenwa.leasing.entity.file.BaseFileTemplate;
import com.tenwa.leasing.entity.file.BaseFileTemplateData;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.invoice.RentPayNoticeRecord;
import com.tenwa.leasing.entity.proj.EvaluationModelData;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.ReserveRatioData;
import com.tenwa.leasing.entity.proj.SelectionReport;
import com.tenwa.leasing.service.FillFlowDataUtil.FillFlowDataUtil;
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
	
	@Resource(name="FillFlowDataUtilImp")
	private FillFlowDataUtil fillFlowDataUtil;
	
	@Resource(name="attachmentFileService")
	private AttachmentFileService attachmentFileService ;
	
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
		InputStream source = multipartFile.getInputStream();
		String uploadDirPath = getuploadFileAllDir(modelname);
		String dirpath = getuploadFileDir(modelname);
		String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String fileName = UUID.randomUUID() + suffix;
		String uploadedFileFullPath = uploadDirPath + fileName;
		bf.setFileAddress(dirpath + fileName);
		ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, source, null);
		this.getBussinessDao().saveOrUpdateEntity(bf);
		this.addlogFileOper(bf, "上传");
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
			is.close();
			throw new BusinessException(e.getMessage());
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
		InputStream source = multipartFile.getInputStream();
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
			File file=new File(downloadedFileFullPath);
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
		InputStream source = multipartFile.getInputStream();
		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath()) + this.contractWordDir + "/" + SystemDate;
		String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String fileName = UUID.randomUUID() + suffix;
		String uploadedFileFullPath = uploadDirPath + "/" + fileName;
		BaseFile bf = new BaseFile();
		ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, source, null);
		this.getBussinessDao().copyAndOverrideExistedValueFromStringMap(modelData, bf, null);
		bf.setFileName(multipartFile.getOriginalFilename());
		bf.setFileAddress(SystemDate + "/" + fileName);
		this.getBussinessDao().saveEntity(bf);
		this.addlogFileOper(bf, "上传");
		return bf.getId();
	}

	public String saveuploadSoureFile(HttpServletRequest request,Map<String,String> model) throws Exception{
		MultipartFile multipartFile = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			//加载附件
			multipartFile = multipartRequest.getFile("tableImportExcel");
			if(Boolean.parseBoolean(model.get("id_appImage"))){
				// 图片专业处理区，有一个对象AppImage存储流程附件中的图片。
				String fileName = multipartFile.getOriginalFilename();
				String fileType = fileName.substring(fileName.indexOf('.') + 1).toLowerCase();
				String tFileImagesId = "";
				if (fileType.matches("jpg|jpeg|png|bmp|gif")) {
					AppImageService appImage = (AppImageService) WebUtil.getApplicationContext().getBean("appImageService");
					String result = appImage.addAppImage(request,"tableImportExcel");
					JSONObject jo = new JSONObject(result);
					if(jo.has("id")){
						tFileImagesId = jo.getString("id");
						model.put("tFileImagesId", tFileImagesId);// 入库数据同步。
					}
				}
			}
			//登记上传的附件
			BaseFile bf=this.tableService.saveUpFiletoService(multipartFile, model);
			model.put("uploadid", bf.getId());
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
		//排序
		Collections.sort(wcList,new Comparator<BaseFileTemplate>(){
	        public int compare(BaseFileTemplate arg0, BaseFileTemplate arg1) {
                return arg0.getTemplateName().compareTo(arg1.getTemplateName());
            }
		});
		
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
//	
					if(pageMap.containsKey(ft.getTemplateNo())){
						//根据模板的配置生成多个
						String jsonData = pageMap.get(ft.getTemplateNo());
						//jsonData=jsonData.replace("'","\""); 
						Map[] newSetMaps = jsonMapper.readValue(jsonData, Map[].class);
						List<List> tabledata = new ArrayList<List>();
						for (int j = 0; j < newSetMaps.length; j++) {
								Map<String, String> cdataMap = new HashMap<String, String>();
								cdataMap = newSetMaps[j];
								pageMap.putAll(cdataMap);
								if(custCreatTemplateMethodMap.containsKey(ft.getTemplateNo())){
								try{
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
									   BaseFile cw = this.saveCreateBaseFile(fileName, DataMap, ft,pageMap.get("modelname").toString());  
									   cw.setInvalid("是");
									 cws.add(cw);
								  }	
						}										
					}else{
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
						   BaseFile cw = this.saveCreateBaseFile(fileName, DataMap, ft,pageMap.get("modelname").toString());  
						 cws.add(cw);
					   }	
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

	public String createFileByTemplate(Map<String, String> model,HttpServletRequest request,HttpServletResponse response) throws Exception {
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
//	
					if(pageMap.containsKey(ft.getTemplateNo())){
						//根据模板的配置生成多个
						String jsonData = pageMap.get(ft.getTemplateNo());
						//jsonData=jsonData.replace("'","\""); 
						Map[] newSetMaps = jsonMapper.readValue(jsonData, Map[].class);
						List<List> tabledata = new ArrayList<List>();
						for (int j = 0; j < newSetMaps.length; j++) {
								Map<String, String> cdataMap = new HashMap<String, String>();
								cdataMap = newSetMaps[j];
								pageMap.putAll(cdataMap);
								if(custCreatTemplateMethodMap.containsKey(ft.getTemplateNo())){
								try{
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
									   DataMap.put("flowunid", model.get("flowunid"));
									   DataMap.put("filekey",model.get("filekey"));
									   BaseFile cw = this.saveCreateBaseFile(fileName, DataMap, ft,pageMap.get("modelname").toString());  
									   cw.setInvalid("是");
									 cws.add(cw);
								  }	
						}										
					}else{
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
						if(model.containsKey("flowunid")){
							DataMap.put("flowunid", model.get("flowunid"));
						}
						if(model.containsKey("filekey")){
							DataMap.put("filekey",model.get("filekey"));
						}
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
						   BaseFile cw = this.saveCreateBaseFile(fileName, DataMap, ft,pageMap.get("modelname").toString());  
						 cws.add(cw);
					   }	
				}
				}
			 }
			}
			if (cws.size() > 0) {
				this.getBussinessDao().saveAllEntities(cws);
				
				if(Boolean.parseBoolean(model.get("isAttachment"))){
					//根据关键字1找到对应AttachmentFileUploadInfo
					if(null != cws && cws.size() > 0){
						Map<String, Object> propertiesMap = new HashMap<String, Object>();
						propertiesMap.put("identifierOne", model.get("identifierOne"));
						DictionaryData attachmentFileDict = new DictionaryData();
						attachmentFileDict.setId( model.get("attachmentFileDictId"));
						propertiesMap.put("attachmentFileDict", attachmentFileDict);
						List<AttachmentFileUploadInfo> uploadInfos = this.baseDao.findEntityByProperties(AttachmentFileUploadInfo.class, propertiesMap);
						if(null != uploadInfos && uploadInfos.size() > 0){
							//request.setAttribute("attachmentFileUploadInfoId", uploadInfos.get(0).getId());
							model.put("attachmentFileUploadInfoId", uploadInfos.get(0).getId());
						}
						this.attachmentFileService.uploadAttachmentFiles(request, response,model,cws);
					}
				}
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
						if("evaluationModel".equals(fields[0])){
							//经平模型
							String field = fields[0]; 
							String columnKey=bw.getWordKey();
							if (pageMap.containsKey(columnKey)) {
								String valueStr = StringUtil.nullToString(pageMap.get(columnKey),"")  ;
								JSONArray valueArr = new JSONArray("["+valueStr+"]");
								for(int x1 = 0;x1<valueArr.length();x1++){
									fielddata.add(valueArr.get(x1).toString());
								};
							} else {
								fielddata.add("");
								}
						}else{
							for (String field : fields) {
								String columnKey=getColumnKey(bw.getWordKey(),field,pageMap);
								if (pageMap.containsKey(columnKey)) {
									String ii = pageMap.get(columnKey);
									fielddata.add(MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(pageMap.get(columnKey),""),field, formatFields));
								} else {
									fielddata.add("");
								}
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
				
					if (sql.toLowerCase().indexOf(".xml") > 0) {
						String realPath = ResourceUtil.getTablesDataSourceDirectoryPath() + "/" + sql;
						realPath = FileUtil.getFilePathString(realPath);
						String tempSql = XMLDataUtil.readTableInfoFromXmlFile(realPath).get("table_sql");
						String realSql = QueryUtil.getQueryString(pageMap, tempSql);
						sql = realSql;
					} else {
						sql = StrTools.setSqlCondtion(sql, pageMap);
					}
					 System.out.println(sql);
					List rows = this.getBussinessDao().getJdbcTemplate().queryForList(sql);
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
						String[] keyArray = new String[]{"paymentplandate","planmentplanmoney",
								                         "counterfeeplandete","counterfeeplanmoney",
								                         "bondplandate","bondplanmoney","rentmoney",
								                         "rentinterest","rentalinterestmoney",
								                         "rentalinterestdate","rentdate",
								                         "principal","interest","corpusoverage"};
						String open = "no";
						for(String openkey:keyArray){
							if(openkey.equals(bw.getWordKey())){
								open = "yes";
							}
						}
						if("yes".equals(open)){
							List<String> fielddata = new ArrayList<String>();
							String[] fields = bw.getWordField().split(",");
							String field = fields[0].toString();
							for(int i=0;i<rows.size();i++){
								Map rowvalue = (Map)rows.get(i);
								fielddata.add(MoneyUtils.getCheckformatNumberDouble(StringUtil.nullToString(rowvalue.get(field)).toString(),field,formatFields));
							}
							worddata.put(bw.getWordKey(), fielddata);
							open="no";
						}else{
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
									String tt = StringUtil.nullToString(dataMap.get(field)).toString();
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
		try {
			String uploadDirPath = WebUtil.getWebContextRealPath()+"template/" + this.wordTemplateDir;
		
			String uploadedFileFullPath = uploadDirPath + "/" + ft.getTemplatePath();
			 
			String fileName = FileUtil.getFilePathString(uploadedFileFullPath);
			String paddress = ft.getTemplateShowName();
			String suffix = paddress.substring(paddress.lastIndexOf("."));
		    String templateSuffix=fileName.substring(fileName.lastIndexOf("."));
			File file = new File(fileName);
			wordfile = "";
			if (file.isFile()) {
				String wordDir = getuploadFileAllDir(modelname);
				wordfile = UUID.randomUUID() + suffix;
				String uploadFiel = FileUtil.getFilePathString(wordDir + "/" + wordfile);
				String tempFile = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath() + "/" + wordfile);
			
				if (suffix.indexOf("doc") > 0) {
					try {
						if(templateSuffix.indexOf("zip")>0){
						    //写入WORD XML中
						    //写到EXCEL中
							this.writeDatatoTemplateWordbyBookMark(fileName, dataMap, tempFile);
						}else{
						Configuration cfg = new Configuration();
						cfg.setDefaultEncoding("utf-8");
						cfg.setDirectoryForTemplateLoading(new File(uploadDirPath));
						cfg.setObjectWrapper(new DefaultObjectWrapper());
						Template temp = cfg.getTemplate(ft.getTemplatePath().replaceAll(this.wordTemplateDir + "/", ""));
						File outFile = new File(tempFile);
						Writer out = null;
						out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
					    
						temp.process(dataMap, out);
						out.flush();
						out.close();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new BusinessException("写入word模板时出错"+e.getMessage());
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
				File inFile = new File(tempFile);
				InputStream source = new FileInputStream(inFile);
				ResourceUtil.getFileUploadOperation().uploadFile(uploadFiel, source, null);
				inFile.delete();
				logger.info("生功生成合同");
			} else {
				throw new BusinessException(ft.getTemplateShowName() + "没有模板");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("createRealFile中的"+e.getMessage());
		}
		return getuploadFileDir(modelname) + wordfile;
	}
   

	public String downloadAttachById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		String id = modelData.get("id");
		String[] ids=id.split(",");
		List<BaseFile> bws = (List<BaseFile>) this.getBussinessDao().findEntityByIDArray(BaseFile.class, ids);
		if(bws.size()==1){
			BaseFile bw=bws.get(0);
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
		}else{
			String fileTitleName = bws.get(0).getModelName() + DateUtil.getSystemTimeByFormat("yyyy-MM-dd");
			fileTitleName += ".zip";
			fileTitleName = this.getdownloadfileName(modelData, fileTitleName);
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + fileTitleName);
			response.setContentType("textml; charset=UTF-8");
			OutputStream os = null;
			org.apache.tools.zip.ZipOutputStream out = null;
			String charSetName = "GB2312";
			String baseDir = new File(ResourceUtil.getFileUploadDataPath()).getAbsolutePath();
			try {
				os = response.getOutputStream();
				out = new org.apache.tools.zip.ZipOutputStream(os);
				out.setEncoding(charSetName);
				Map<String,Integer> fileNameMap=new HashMap<String,Integer>();
				for (BaseFile bf : bws) {
					// 下载文件
					String inputFileFullPath = FileUtil.getFilePathString(baseDir + "/" + this.contractWordDir + "/" + bf.getFileAddress());
					System.out.println(inputFileFullPath);
					String chineseName = bf.getFileName();
					int cint=0;
					if(fileNameMap.containsKey(chineseName)){
						cint=fileNameMap.get(chineseName);
						cint=cint+1;
						fileNameMap.put(chineseName, cint);
					}else{
						cint=1;
						fileNameMap.put(chineseName, 1);
					}
					if(cint>1){
						chineseName=chineseName.substring(0, chineseName.indexOf("."))+"("+cint+")"+chineseName.substring(chineseName.indexOf("."));
					}
					out.putNextEntry(new org.apache.tools.zip.ZipEntry(chineseName));
					ResourceUtil.getFileUploadOperation().readInputStreamToOutputStream(inputFileFullPath, out);
				}
			}finally {
				if (null != out) {
					out.closeEntry();
					out.flush();
					out.close();
				}
				if (null != os) {
					os.flush();
					os.close();
				}
			}
		}
		return null;
	}
	
	@Override
	public String downLoadRentNoticeAttachById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		//合同id
		String contractid = modelData.get("contractid");
		ContractInfo ContractInfo=this.tableService.findEntityByID(ContractInfo.class, contractid);
		//创建人
		String creator=modelData.get("creator");
		User user=this.tableService.findEntityByID(User.class,creator );
		//日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); ;
		Date date =new Date();
		String datetoDB=sdf.format(date);
		
		//租金计划ContractFundRentPlan
		String cfrpid=modelData.get("cfrpid");
		ContractFundRentPlan ContractFundRentPlan=this.tableService.findEntityByID(ContractFundRentPlan.class, cfrpid);
		String hsql="from RentPayNoticeRecord r where r.contractId='"+contractid+"' and r.planId='"+cfrpid+"'";
		List<RentPayNoticeRecord> rpnrlist =this.tableService.findResultsByHSQL(hsql);
		RentPayNoticeRecord rpnr;
		if(rpnrlist.size()>0){
			rpnr=rpnrlist.get(0);
			rpnr.setModificator(user);
			rpnr.setModifyDate(datetoDB);
			rpnr.setExportTimes(rpnr.getExportTimes()+1);
		}else{
			rpnr=new RentPayNoticeRecord();
			rpnr.setCreator(user);
			rpnr.setCreateDate(datetoDB);
			rpnr.setContractId(ContractInfo);
			rpnr.setPlanId(ContractFundRentPlan);
			rpnr.setExportTimes(1);
		}
		this.tableService.saveOrUpdateEntity(rpnr);
		String id = modelData.get("id");
		String[] ids=id.split(",");
		List<BaseFile> bws = (List<BaseFile>) this.getBussinessDao().findEntityByIDArray(BaseFile.class, ids);
		if(bws.size()==1){
			BaseFile bw=bws.get(0);
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
		}else{
			String fileTitleName = bws.get(0).getModelName() + DateUtil.getSystemTimeByFormat("yyyy-MM-dd");
			fileTitleName += ".zip";
			fileTitleName = this.getdownloadfileName(modelData, fileTitleName);
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=" + fileTitleName);
			response.setContentType("textml; charset=UTF-8");
			OutputStream os = null;
			org.apache.tools.zip.ZipOutputStream out = null;
			String charSetName = "GB2312";
			String baseDir = new File(ResourceUtil.getFileUploadDataPath()).getAbsolutePath();
			try {
				os = response.getOutputStream();
				out = new org.apache.tools.zip.ZipOutputStream(os);
				out.setEncoding(charSetName);
				Map<String,Integer> fileNameMap=new HashMap<String,Integer>();
				for (BaseFile bf : bws) {
					// 下载文件
					String inputFileFullPath = FileUtil.getFilePathString(baseDir + "/" + this.contractWordDir + "/" + bf.getFileAddress());
					System.out.println(inputFileFullPath);
					String chineseName = bf.getFileName();
					int cint=0;
					if(fileNameMap.containsKey(chineseName)){
						cint=fileNameMap.get(chineseName);
						cint=cint+1;
						fileNameMap.put(chineseName, cint);
					}else{
						cint=1;
						fileNameMap.put(chineseName, 1);
					}
					if(cint>1){
						chineseName=chineseName.substring(0, chineseName.indexOf("."))+"("+cint+")"+chineseName.substring(chineseName.indexOf("."));
					}
					out.putNextEntry(new org.apache.tools.zip.ZipEntry(chineseName));
					ResourceUtil.getFileUploadOperation().readInputStreamToOutputStream(inputFileFullPath, out);
				}
			}finally {
				if (null != out) {
					out.closeEntry();
					out.flush();
					out.close();
				}
				if (null != os) {
					os.flush();
					os.close();
				}
			}
		}
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
		InputStream source = multipartFile.getInputStream();
		ResourceUtil.getFileUploadOperation().uploadFile(downloadedFileFullPath, source, null);
		this.addlogFileOper(bf, "编辑");
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
		pageMap.putAll(model);
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
				if (model.containsKey("flowunid")) {
					dataMap.put("flowunid", model.get("flowunid"));
				}
				if (model.containsKey("filekey")) {
					dataMap.put("filekey", model.get("filekey"));
				}
				
				BaseFile cw=this.saveCreateBaseFile(fileName, dataMap,ft, pageMap.get("modelname").toString());
				this.addlogFileOper(cw, "生成");
				bfs.add(cw);
			}
		} else if(model.containsKey("evaluationModel")){
			System.out.println("+++++++++进入自定义方法=========================================");
			String parameterSelection =  model.get("parameterSelection");//参数1
			double windfarmequipmentinput = Double.parseDouble(model.get("windFarmEquipmentInput"));//参数2
			double engineeringinput = Double.parseDouble(model.get("engineeringInput"));//参数3
			String projectid =  model.get("projectid");
			String flowId =  model.get("flowunid");
			double[][] parameters = getParameterArray(projectid,parameterSelection,windfarmequipmentinput,engineeringinput);
			String[] filename = getFileNameArray(projectid,parameterSelection,windfarmequipmentinput,engineeringinput);
			ft=this.LoadTemplateByTemplateNo(model);
			templatedataConfigs=ft.getFileTemplateDataConfigs();
			if (templatedataConfigs.size() > 0) {
				dataMap = getTemplateData(templatedataConfigs, pageMap);
			}
			Map<String, Object> dataMapCopy = dataMap;
			if (model.containsKey("SystemSN")) {
				dataMap.put("SystemSN", model.get("SystemSN"));
			}
			//租赁模式
			ArrayList arraylist2 =(ArrayList)dataMap.get("planmentplanmoney");//付款计划金额
			ArrayList arraylist3 =(ArrayList)dataMap.get("paymentplandate");//付款计划日期
			int x;
			if(arraylist2==null||arraylist2.size()==0){
				throw	new Exception("付款计划为空！");
			}else{
				x = 15-arraylist2.size();
			}
			if(x>0){
				for(int k=0;k<x;k++){
					arraylist2.add(0);
					arraylist3.add(arraylist3.get(arraylist3.size()-1));
				}
			}else if(x<0){
				throw	new Exception("付款计划期数超过模板最大期数15！");
			}
			dataMap.put("planmentplanmoney", arraylist2);
			dataMap.put("paymentplandate", arraylist3);
			arraylist2 = null;
			//租金计划，模板按月展示，空补零
			ArrayList arraylist4 =(ArrayList)dataMap.get("rentmoney");//租金
			ArrayList arraylist5 =(ArrayList)dataMap.get("rentdate");//还款日期
			ArrayList arraylist6 =(ArrayList)dataMap.get("interest");//利息
			ArrayList arraylist7 =(ArrayList)dataMap.get("principal");//本金
			ArrayList arraylist8 =(ArrayList)dataMap.get("corpusoverage");//期末余值
			//银行贷款模式
			ArrayList bankArraylist4 =(ArrayList)dataMap.get("bankrentmoney");//租金
			ArrayList bankArraylist5 =(ArrayList)dataMap.get("bankrentdate");//还款日期
			ArrayList bankArraylist6 =(ArrayList)dataMap.get("bankinterest");//利息
			ArrayList bankArraylist7 =(ArrayList)dataMap.get("bankprincipal");//本金
			ArrayList bankArraylist8 =(ArrayList)dataMap.get("bankcorpusoverage");//期末余值
			
			String firstdate = arraylist5.get(0).toString();
			String lastdate = arraylist5.get(arraylist5.size()-1).toString();
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        Calendar c1 = Calendar.getInstance();
		        Calendar c2 = Calendar.getInstance();
		        List moneyList = new ArrayList();
		        List dateList = new ArrayList();
		        List interestList = new ArrayList();
		        List principalList = new ArrayList();
		        List corpusoverageList = new ArrayList();
		        
		        List bankMoneyList = new ArrayList();
		        List bankDateList = new ArrayList();
		        List bankInterestList = new ArrayList();
		        List bankPrincipalList = new ArrayList();
		        List bankCorpusoverageList = new ArrayList();
		        dateList.add(arraylist5.get(0));
		        moneyList.add(arraylist4.get(0));
		        interestList.add(arraylist6.get(0));
		        principalList.add(arraylist7.get(0));
		        corpusoverageList.add(arraylist8.get(0));
		        
		        bankDateList.add(bankArraylist5.get(0));
		        bankMoneyList.add(bankArraylist4.get(0));
		        bankInterestList.add(bankArraylist6.get(0));
		        bankPrincipalList.add(bankArraylist7.get(0));
		        bankCorpusoverageList.add(bankArraylist8.get(0));
		    for(int y=1;y<arraylist5.size();y++){
		    	Date rr = sdf.parse(arraylist5.get(y-1).toString());
		    	c1.setTime(rr);
		    	Date yy = sdf.parse(arraylist5.get(y).toString());
		        c2.setTime(yy);
		        int monthA = c1.get(Calendar.MONTH);
		        int yearA = c1.get(Calendar.YEAR);
		        int monthB = c2.get(Calendar.MONTH);
		        int yearB = c2.get(Calendar.YEAR);
		        int lastresult = 0;
		        if(yearA==yearB)
		        {
		           lastresult = (monthB-monthA); 
		        }
		        else
		        {
		        	lastresult=12*(yearB-yearA)+monthB-monthA;//两个日期相差几个月
		        }
		        for(int z=0;z<lastresult;z++){
		        	if(z==(lastresult-1)){
		        		moneyList.add(arraylist4.get(y));
		        		interestList.add(arraylist6.get(y));
				        principalList.add(arraylist7.get(y));
				        corpusoverageList.add(arraylist8.get(y));
					    String dateStr = sdf.format(c2.getTime()); 
					    dateList.add(dateStr);
					    bankMoneyList.add(bankArraylist4.get(y));
				        bankInterestList.add(bankArraylist6.get(y));
				        bankPrincipalList.add(bankArraylist7.get(y));
				        bankCorpusoverageList.add(bankArraylist8.get(y));
				        bankDateList.add(dateStr);
		        		break;
		        	}else{
		        		moneyList.add("0");
		        		interestList.add("0");
				        principalList.add("0");
				        corpusoverageList.add("0");
						c1.add(Calendar.MONTH, 1); 
					    String dateStr = sdf.format(c1.getTime()); 
					    dateList.add(dateStr);
					    bankMoneyList.add("0");
				        bankInterestList.add("0");
				        bankPrincipalList.add("0");
				        bankCorpusoverageList.add("0");
				        bankDateList.add(dateStr);
		        	}
		        	
		        }
		    }
		    dataMap.put("rentmoney",moneyList);//租金
			dataMap.put("rentdate",dateList);//还款日期
			dataMap.put("interest",interestList);//利息
			dataMap.put("principal",principalList);//本金
			dataMap.put("corpusoverage",corpusoverageList);//期末余值
			
			dataMap.put("bankrentmoney",bankMoneyList);//租金
			dataMap.put("bankrentdate",bankDateList);//还款日期
			dataMap.put("bankinterest",bankInterestList);//利息
			dataMap.put("bankprincipal",bankPrincipalList);//本金
			dataMap.put("bankcorpusoverage",bankCorpusoverageList);//期末余值
			moneyList = null;
			dateList = null;
			interestList = null;
			principalList = null;
			arraylist4 = null;
			arraylist5 = null;
			arraylist6 = null;
			arraylist7 = null;
			bankArraylist4 = null;
			bankArraylist5 = null;
			bankArraylist6 = null;
			bankArraylist7 = null;
			//租前息最大36，不够补零
			ArrayList rentalinterestdateList =(ArrayList)dataMap.get("rentalinterestdate");//租前息日期
			ArrayList rentalinterestmoneyList =(ArrayList)dataMap.get("rentalinterestmoney");//租前息钱数
			
			ArrayList bankRentAlinterestDateList =(ArrayList)dataMap.get("bankrentalinterestdate");//租前息日期
			ArrayList bankRentAlinterestMoneyList =(ArrayList)dataMap.get("bankrentalinterestmoney");//租前息钱数
			if(rentalinterestdateList.size()<1){
				for(int x2=0;x2<36;x2++){
					rentalinterestdateList.add(arraylist3.get(1));//为空时，补放款日，金额0
					rentalinterestmoneyList.add("0");
					
					bankRentAlinterestDateList.add(arraylist3.get(1));//为空时，补放款日，金额0
					bankRentAlinterestMoneyList.add("0");
				}
			}else if(rentalinterestdateList.size()>=1&&rentalinterestdateList.size()<=36){
				int difference1 = 36-rentalinterestdateList.size();
				for(int x2=0;x2<difference1;x2++){
					rentalinterestdateList.add(rentalinterestdateList.get(rentalinterestdateList.size()-1));
					rentalinterestmoneyList.add("0");
					
					bankRentAlinterestDateList.add(bankRentAlinterestDateList.get(bankRentAlinterestDateList.size()-1));
					bankRentAlinterestMoneyList.add("0");
				}
			}else{
				throw new Exception("租前息期数超过模板最大36期");
			}
			dataMap.put("rentalinterestdate",rentalinterestdateList);
			dataMap.put("rentalinterestmoney",rentalinterestmoneyList);
			
			dataMap.put("bankrentalinterestdate",bankRentAlinterestDateList);
			dataMap.put("bankrentalinterestmoney",bankRentAlinterestMoneyList);
			rentalinterestdateList = null;
			rentalinterestmoneyList = null;
			
			bankRentAlinterestDateList = null;
			bankRentAlinterestMoneyList = null;
			//手续费36期
			ArrayList counterfeeplandeteList =(ArrayList)dataMap.get("counterfeeplandete");//手续费日期
			ArrayList counterfeeplanmoneyList =(ArrayList)dataMap.get("counterfeeplanmoney");//手续费钱数
			if(counterfeeplandeteList.size()<1){
				for(int x3=0;x3<36;x3++){
					counterfeeplandeteList.add(arraylist3.get(1));//为空时，补放款日，金额0
					counterfeeplanmoneyList.add("0");
				}
			}else if(counterfeeplandeteList.size()>=1&&counterfeeplandeteList.size()<=36){
				int difference2 = 36-counterfeeplandeteList.size();
				for(int x3=0;x3<difference2;x3++){
					counterfeeplandeteList.add(counterfeeplandeteList.get(counterfeeplandeteList.size()-1));
					counterfeeplanmoneyList.add("0");
				}
			}else{
				throw new Exception("手续费期数超过模板最大36期");
			};
			dataMap.put("counterfeeplandete",counterfeeplandeteList);
			dataMap.put("counterfeeplanmoney",counterfeeplanmoneyList);
			counterfeeplandeteList = null;
			counterfeeplanmoneyList = null;
			//保证金36期
			ArrayList bondplandateList =(ArrayList)dataMap.get("bondplandate");//保证金日期
			ArrayList bondplanmoneyList =(ArrayList)dataMap.get("bondplanmoney");//保证金钱数
			if(bondplandateList.size()<1){
				for(int x4=0;x4<36;x4++){
					bondplandateList.add(arraylist3.get(1));//为空时，补放款日，金额0
					bondplanmoneyList.add("0");
				}
			}else if(bondplandateList.size()>=1&&bondplandateList.size()<=36){
				int difference3 = 36-bondplandateList.size();
				for(int x4=0;x4<difference3;x4++){
					bondplandateList.add(bondplandateList.get(bondplandateList.size()-1));
					bondplanmoneyList.add("0");
				}
			}else{
				throw new Exception("保证金期数超过模板最大36期");
			};
			dataMap.put("bondplandate",bondplandateList);
			dataMap.put("bondplanmoney",bondplanmoneyList);
			bondplandateList = null;
			bondplanmoneyList = null;
			
			ArrayList arraylist1 =(ArrayList)dataMapCopy.get("parameter");
			for(int i=0;i<8;i++){
				/*if (templatedataConfigs.size() > 0) {
					dataMap = getTemplateData(templatedataConfigs, pageMap);
				}
				*/
				if(dataMap.containsKey("flowunid")) dataMap.remove("flowunid");
				if(dataMap.containsKey("filekey")) dataMap.remove("filekey");
				List list1 = new ArrayList();
				for(int j=0;j<arraylist1.size();j++){
					double[] array2 = parameters[0];
					double[] array3 =parameters[1];
					if(j==0){
						list1.add(array2[i]);
					}else if(j==2){
						list1.add(array3[i]);
					}else{
						list1.add(arraylist1.get(j));
					}
				}
				dataMap.put("parameter",(ArrayList)list1);
				
				String fileName = createRealFile(ft, dataMap, model.get("tableid").toString());
				String wordDir = getuploadFileAllDir(model.get("tableid").toString());//生成文件路径
				//读取生成文件的值
				if (model.containsKey("flowunid")) {
					dataMap.put("flowunid", model.get("flowunid"));
				}
			    dataMap.put("filekey",i);
			    //dataMap.put("projid",projectid);
				String customfilename = "租赁系统-经评模型"+filename[i]+".xls;"+projectid;
				BaseFile cw=this.saveCreateBaseFile(fileName, dataMap,ft, pageMap.get("modelname").toString(),customfilename);
				this.addlogFileOper(cw, "生成");
				bfs.add(cw);
			}
			arraylist3 = null;
		}else {
			ft=this.LoadTemplateByTemplateNo(model);
			templatedataConfigs=ft.getFileTemplateDataConfigs();
			if (templatedataConfigs.size() > 0) {
				dataMap = getTemplateData(templatedataConfigs, pageMap);
			}
			if (model.containsKey("SystemSN")) {
				dataMap.put("SystemSN", model.get("SystemSN"));
			}
			
			String fileName = createRealFile(ft, dataMap, model.get("tableid").toString());
			if (model.containsKey("flowunid")) {
				dataMap.put("flowunid", model.get("flowunid"));
			}
			if (model.containsKey("filekey")) {
				dataMap.put("filekey", model.get("filekey"));
			}
			BaseFile cw=this.saveCreateBaseFile(fileName, dataMap,ft, pageMap.get("modelname").toString());
			this.addlogFileOper(cw, "生成");
			bfs.add(cw);

		}
//		if (bfs.size() > 1) {
//			String modelname = model.get("modelname");
//			String baseDir = new File(ResourceUtil.getFileUploadDataPath()).getAbsolutePath();
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
		File tempFileOut = new File(tempFileFullPath);
		File parentFile = tempFileOut.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		OutputStream os = new FileOutputStream(tempFileFullPath);
		ZipOutputStream out = null;
		String charSetName = "GB2312";
		String baseDir = new File(ResourceUtil.getFileUploadDataPath()).getAbsolutePath();

		try {
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
				out.close();
			}
			if (null != os) {
				os.flush();
				os.close();
			}
		}
		File tempFile = new File(tempFileFullPath);
		ResourceUtil.getFileUploadOperation().uploadFile(uploadRemoteFullPath, new FileInputStream(tempFile), null);
		tempFile.delete();
	}
	
	//新的插入word
	public void writeDatatoTemplateWordbyBookMark(String tempfile,Map<String, Object> model, String targetFile) throws Exception {
		 Document document = null; 
		 File file = new File(tempfile);
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
					WordBookMarkXmlUtil.replaceWindowBookMark(document,book_name,StringUtil.nullToString( t_value));
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
				    		WordBookMarkXmlUtil.replaceWindowBookMark(document,book_name+"_"+j+"",StringUtil.nullToString(templistvalue.get(j)));
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
						templateSheet.setForceFormulaRecalculation(true);
						PoiExcelUtil.forceRefreshFormula(templateSheet);
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
								boolean keyResult = false;
								String[] keyStrArray = {"evaluation","planmentplanmoney","counterfeeplanmoney",
														"bondplanmoney","rentmoney","parameter",
														"rentalinterestmoney","interest","principal",
														"corpusoverage","bankrentmoney","bankinterest",
														"bankprincipal","bankcorpusoverage","bankrentalinterestmoney",
														"baseInformation"};
								 for (String keyStr : keyStrArray) {
									 if(keyStr.equals(title_name)){
										 keyResult = true;
									 }
							     }
								if(keyResult){
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
											String putValue=templistvalue.get(i).toString();
											  String regex="[0-9]+?";
											  Pattern p=Pattern.compile(regex);
											  Matcher m=p.matcher(putValue);
											  if(m.find()==true){
													//判断是否为日期类型
												  if(HSSFDateUtil.isCellDateFormatted(cell)){
												    //设置excel日期类型格式
													  CellStyle dateCellStyle= wb.createCellStyle(); 
													  short df=wb.createDataFormat().getFormat("yyyy-mm-dd"); 
													  dateCellStyle.setDataFormat(df);
													cell.setCellValue(putValue);
													cell.setCellStyle(dateCellStyle);
													}else{
														 cell.setCellValue(Float.parseFloat(templistvalue.get(i).toString()));
															if (logger.isInfoEnabled()) {
																logger.info("Excel开始生成写入" + title_name + "_" + i + ":" + templistvalue.get(i).toString());
															}
													}
											  }else{
												  cell.setCellValue(templistvalue.get(i).toString());
													if (logger.isInfoEnabled()) {
														logger.info("Excel开始生成写入" + title_name + "_" + i + ":" + templistvalue.get(i).toString());
													}
											  }
											
											}
										}
									}
								}else{
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
						templateSheet.setForceFormulaRecalculation(true);
						PoiExcelUtil.forceRefreshFormula(templateSheet);
					}
				}
			}
			OutputStream osServer = new FileOutputStream(targetFile);
			wb.setForceFormulaRecalculation(true);
			wb.write(osServer);
			osServer.close();
		} catch (Exception e) {
			e.printStackTrace();
			OutputStream osServer = new FileOutputStream(targetFile);
			wb.write(osServer);
			osServer.close();
			throw new BusinessException("写excel时出错"+e.getMessage());
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
		 for(BaseFileTemplate t : templates){
	          if(null!=t.getTemplatePath()){
	        	  String file=sourcepath+this.wordTemplateDir+"/" + t.getTemplatePath();;
	        	  String targerfile=targetPath+this.wordTemplateDir +"/" + t.getTemplatePath();
	        	  File f=new File(file);
	        	  if(null!=f && f.isFile()){
	        		  InputStream source = new FileInputStream(f);
	  				ResourceUtil.getFileUploadOperation().uploadFile(targerfile, source, null); 
	        	  }
	          }
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
	
	
	public BaseFile saveCreateBaseFile(String fileRealName, Map<String, Object> dataModel, BaseFileTemplate ft, String modelname,String ... args) throws Exception {
		// TODO Auto-generated method stub
		BaseFile cw = new BaseFile();
		String filename = "";
		if (dataModel.containsKey("filename")) {
			filename = dataModel.get("filename").toString();
		}else if("租赁系统-经评模型模板".equals(modelname)){
			String[] strArray =  args[0].toString().split(";");
			cw.setFileNumber(strArray[1]);
			filename = strArray[0];//args[0].toString();
		}else {
			filename = ft.getTemplateShowName();
		}
		filename = StrTools.getFileNameByFormual(filename, dataModel);
		cw.setFileName(filename);
		cw.setFileAddress(fileRealName);
		cw.setBaseFileTemplate(ft);
		cw.setCreator(this.getCurUser());
		if (dataModel.containsKey("flowunid")) {
			cw.setFlowUnid(dataModel.get("flowunid").toString());
		}
		if (dataModel.containsKey("filekey")) {
			cw.setFilekey(dataModel.get("filekey").toString());
		}
		cw.setModelName(modelname);
		cw.setCreateDate(DateUtil.getSystemDateTime());
		cw.setInvalid("是");
		this.getBussinessDao().saveOrUpdateEntity(cw);
		return cw;
	}

	public JSONObject getExcelDataByConfig(Workbook wb, String importType) throws Exception{
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("importType", importType);
		ExcelSheetConfig excelSheetConfig = null;
		List<ExcelSheetConfig> sheetConfigList = this.tableService.findEntityByProperties(ExcelSheetConfig.class, propertiesMap);
		if(sheetConfigList == null || sheetConfigList.size() == 0){
			throw new Exception("数据库中无此导入类型！");
		}else{
			Sheet sheet = null;
			List<ExcelTableConfig> excelTableConfigList = null;
			List<ExcelCellConfig> excelCellConfigList = null;
			
			Iterator<ExcelTableConfig> excelTableCfgItor = null;
			Iterator<ExcelCellConfig> excelCellCfgItor = null;
			ExcelTableConfig excelTableCfg = null;
			ExcelCellConfig excelCellCfg = null;
			
			JSONObject excelJsonObj = new JSONObject();
			JSONArray sheetsJsonArr = new JSONArray();
			excelJsonObj.put("import_type", importType);
			excelJsonObj.put("sheets_data", sheetsJsonArr);
			JSONObject sheetJsonObj = null;
			JSONArray tableDataJsonArr = null;
			JSONArray cellDataJsonArr = null;
			
			//Map<String, List<List<String>>> tableDataMap = new HashMap<String, List<List<String>>>();
			//Map<String, String> cellDataMap = new HashMap<String, String>();
			for(int i = 0; i < sheetConfigList.size(); i ++){
				excelSheetConfig = sheetConfigList.get(i);
				//excelSheetConfig.getExcelTableConfigs();
				excelTableConfigList = this.tableService.findResultsByHSQL("from ExcelTableConfig where sheetConfigId = ? order by createDate asc", excelSheetConfig);
				//excelSheetConfig.getExcelCellConfigs();
				excelCellConfigList = this.tableService.findResultsByHSQL("from ExcelCellConfig where sheetConfigId = ? order by columnIndex asc", excelSheetConfig);
				sheet = wb.getSheet(excelSheetConfig.getSheetName());
				
				sheetJsonObj = new JSONObject();
				sheetJsonObj.put("sheet_name", excelSheetConfig.getSheetName());
				sheetJsonObj.put("sheet_code", excelSheetConfig.getSheetName());
			
				excelTableCfgItor = excelTableConfigList.iterator();
				tableDataJsonArr = new JSONArray();
				while(excelTableCfgItor.hasNext()){
					excelTableCfg = excelTableCfgItor.next();
					tableDataJsonArr.put(getSheetTableDataByCfg(sheet, excelTableCfg));
				}
				sheetJsonObj.put("table_data", tableDataJsonArr);
				
				excelCellCfgItor = excelCellConfigList.iterator();
				cellDataJsonArr = new JSONArray();
				while(excelCellCfgItor.hasNext()){
					excelCellCfg = excelCellCfgItor.next();
					cellDataJsonArr.put(getSheetCellDataByCfg(sheet, excelCellCfg));
				}
				sheetJsonObj.put("cell_data", cellDataJsonArr);
				sheetsJsonArr.put(sheetJsonObj);
			}
			return excelJsonObj;
		}
	}
	
	public JSONObject getSheetTableDataByCfg(Sheet sheet, ExcelTableConfig tableCfg) throws JSONException{
		JSONObject tableDataJsonObj = new JSONObject();
		
		JSONArray dataJsonArr = new JSONArray();
		JSONObject dataJsonObj = new JSONObject();
		
		String columnsConfig = tableCfg.getColumnsConfig();
		if(columnsConfig != null){
			columnsConfig = columnsConfig.replaceAll("&quot;", "\"").replaceAll("&#39;", "\"");
		}
		
		JSONArray columnsConfigArr = new JSONArray(columnsConfig);
		
		int startRowNum = tableCfg.getStartRowNum();
		int endRowNum = tableCfg.getEndRowNum();
		int startColumnNum = tableCfg.getStartColumnNum();
		int endColumnNum = tableCfg.getEndColumnNum();
		
		String columnsIndex = tableCfg.getColumnsIndex();
		int[] columnsIndexArr = null;
		if(columnsIndex != null && !"".equals(columnsIndex)){
			columnsIndexArr = ArrayUtil.strArray2intArray(columnsIndex.split(","));
		}
		
		Row row = null;
		Cell cell = null;
		String cellValue = "";
		Iterator<Cell> cellItor = null; 
		for(int rowIndex = startRowNum - 1; rowIndex < endRowNum; rowIndex ++){
			row = (Row) sheet.getRow(rowIndex);
			dataJsonObj = new JSONObject();
			cellItor = row.cellIterator();
			if(columnsIndexArr != null && columnsIndexArr.length > 0){
				for(int i = 0; i < columnsIndexArr.length; i ++){
					cell = row.getCell(columnsIndexArr[i]-1);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = cell.getStringCellValue();
					//System.out.println("rowIndex:" + (rowIndex+1) + "\tcolumnIndex:" + (i+1) + "\t" + cellValue);
					dataJsonObj.put(columnsConfigArr.getJSONObject(i).getString("field"), cellValue);
				}
			}else{
				for(int columnIndex = startColumnNum - 1; columnIndex < endColumnNum; columnIndex ++){
					cell = row.getCell(columnIndex);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellValue = cell.getStringCellValue();
					//System.out.println("rowIndex:" + (rowIndex+1) + "\tcolumnIndex:" + (columnIndex+1) + "\t" + cellValue);
					dataJsonObj.put(columnsConfigArr.getJSONObject(columnIndex - startColumnNum + 1).getString("field"), cellValue);
				}
			}
			dataJsonArr.put(dataJsonObj);
		}
		tableDataJsonObj.put("code", tableCfg.getTableCode());
		tableDataJsonObj.put("name", tableCfg.getTableName());
		tableDataJsonObj.put("width", tableCfg.getTableWidth());
		tableDataJsonObj.put("columns", new JSONArray(columnsConfig));
		tableDataJsonObj.put("datas", dataJsonArr);
		return tableDataJsonObj;
	}
	
	public JSONObject getSheetCellDataByCfg(Sheet sheet, ExcelCellConfig cellCfg) throws JSONException{
		JSONObject jsonObj = new JSONObject();
		int rowIndex = cellCfg.getRowIndex();
		int columnIndex = cellCfg.getColumnIndex();
		Row row  = (Row) sheet.getRow(rowIndex-1);
		Cell cell = row.getCell(columnIndex-1);
		String cellValue ="";
		//判断如果是日期类型单元格 转成yyyy-MM-dd 格式存储
		//System.out.println("columnIndex="+columnIndex);
		//System.out.println("Cell.CELL_TYPE_NUMERIC="+Cell.CELL_TYPE_NUMERIC);
		//System.out.println("cell.getCellType()="+cell.getCellType());
		//System.out.println("HSSFDateUtil.isCellDateFormatted(cell)="+HSSFDateUtil.isCellDateFormatted(cell));
		if (null!=cell&&cell.getCellType() == Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(cell)) {    
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
			cellValue =dateformat.format(dt); 
		}else if(null!=cell){
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cellValue = cell.getStringCellValue();
			//2016-07-04 BY toybaby 处理单元格式为百分百又为空值，又是百分比形式的时候
			if("".equals(cellValue)&&"percent".equals(cellCfg.getCellDisplayFormat())){
				cellValue="0";
			}
			//2016-07-04 BY toybaby 处理单元格式为空，又是百分比配置形式的时候
		}else if("percent".equals(cellCfg.getCellDisplayFormat())){
			cellValue="0";
		}else {
			//2016-07-04 BY toybaby 处理单元格式为常规时
			cellValue="";
		}
		jsonObj.put("code", cellCfg.getCellCode());
		jsonObj.put("name", cellCfg.getCellName());
		jsonObj.put("value", cellValue);
		jsonObj.put("displayformat",cellCfg.getCellDisplayFormat());
		return jsonObj;
	}
	
	@Override
	public void saveCreateFilesList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String deleteFileIds = model.get("deleteFileIds");
		//System.out.println("deleteFileIds=====================" + deleteFileIds);
		//System.out.println(model.get("changedate"));
		if(deleteFileIds != null && deleteFileIds.length() > 0){
			String[] deleteFileIdArr = deleteFileIds.split(",");
			String deleteFileSql = "UPDATE BASE_FILE BF SET BF.INVALID_ = '否' WHERE BF.ID = ?";
			for(int i = 0; i < deleteFileIdArr.length; i++){
				this.baseDao.updateBySql(deleteFileSql, deleteFileIdArr[i]);
			}
		}
		fillFlowDataUtil.saveFillFlowData(request);
	}
	 /*
	  *  导出excel 扩展 可导出 包含多个table的多个sheet 
	 */
	@Override
	 public String createRealFile2(BaseFileTemplate ft, List<Map<String, Object>> dataList, String modelname,String... args) throws Exception {
	        String wordfile = "";
	        String uploadDirPath = WebUtil.getWebContextRealPath()+"template/" + this.wordTemplateDir;
	        String uploadedFileFullPath = uploadDirPath + "/" + ft.getTemplatePath();
	        String fileName = FileUtil.getFilePathString(uploadedFileFullPath);
	        String paddress = ft.getTemplateShowName();
	        String suffix = paddress.substring(paddress.lastIndexOf("."));
	        String templateSuffix=fileName.substring(fileName.lastIndexOf("."));
	        File file = new File(fileName);
	        if (file.isFile()) {
	            String wordDir = getuploadFileAllDir(modelname);
	            wordfile = UUID.randomUUID() + suffix;
	            String uploadFiel = FileUtil.getFilePathString(wordDir + "/" + wordfile);
	            String tempFile = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath() + "/" + wordfile);
	            
	            this.writeDatatoTemplateExcel2(fileName, dataList, tempFile,args);
	            
	            File inFile = new File(tempFile);
	            InputStream source = new FileInputStream(inFile);
	            ResourceUtil.getFileUploadOperation().uploadFile(uploadFiel, source, null);
	            inFile.delete();
	            logger.info("生功生成excel");
	        }
	        return getuploadFileDir(modelname) + wordfile;
	        
	    }
	    //写excel 模板
	    @Override
	    public void writeDatatoTemplateExcel2(String tempfile, List<Map<String, Object>> dataList, String targetFile,String... args) throws Exception {
	        Row templateRow;
	        Cell cell;
	        Workbook wb = null;
	        String custMethod = "";
	        Method operatormethod = null;
	        Boolean flag;
	        Class WorkflowUtilClass = Class.forName("com.tenwa.leasing.utils.WorkflowUtil");
	        // 插入之前调自定义方法
	        try {
	            if (tempfile.endsWith("xlsx")) {
	                wb = PoiExcelUtil.readWorkbook(tempfile, ExcelVersionEnum.VERSION2007);
	            } else {
	                wb = PoiExcelUtil.readWorkbook(tempfile, ExcelVersionEnum.VERSION2003);
	            }
	        } catch (Exception e) {
	            throw new BusinessException("加载excel时出错" + e.getMessage());
	        }
	        try {
	            ReadExecl readExecl = null;
	            Sheet templateSheet = null;
	            try {
	                readExecl = new ReadExecl(tempfile);
	                templateSheet = wb.getSheetAt(0);
	            } catch (Exception e) {
	                throw new BusinessException("加载readExecl时出");
	            }
	            int sheetLen = dataList.size();
	            String sheetName = "";
	            Sheet targetSheet;
	            for(int i = 0; i < sheetLen;i++){
	                sheetName = dataList.get(i).get("sheetName").toString();
	                if(i == 0){
	                    wb.setSheetName(0, sheetName);
	                }else{
	                    targetSheet = wb.createSheet(sheetName);
	                    System.out.println(sheetName);
	                    PoiExcelUtil.CopySheet(templateSheet, targetSheet);
	                }
	            }
	            for(Map<String, Object> dataMap : dataList){
	            	//2016/1/20 poi bug sheet名字长度超过一定长度 setSheetName 会有截取现象 sheet名字长度最大31
	            	if(dataMap.get("sheetName").toString().length()>31){
	            		templateSheet = wb.getSheet(dataMap.get("sheetName").toString().substring(0, 31));
	            	}else{
	            		templateSheet = wb.getSheet(dataMap.get("sheetName").toString());
	            	}
	                dataMap.remove("sheetName");
	                for(Entry<String, Object> entry : dataMap.entrySet()){
	                    String title_name = entry.getKey().trim();
	                    Object t_value = entry.getValue();
	                    if (null != t_value && title_name != "SystemSN") {
	                        int col_index,row_index = 0;
	                        if(t_value instanceof String){//单个字符串的写入
	                            col_index = readExecl.getCellColNum(title_name);
	                            row_index = readExecl.getCellRowNum(title_name);
	                            if (col_index >= 0 && row_index >= 0) {
	                                templateRow = templateSheet.getRow(row_index);
	                                cell = templateRow.getCell(col_index);
	                                cell.setCellValue(t_value.toString());
	                                if (logger.isInfoEnabled()) {
	                                    logger.info("Excel开始生成写入" + title_name + ":" + t_value.toString());
	                                }
	                            }
	                        }else{
	                            List tempList = (List) t_value;
	                            if(title_name.startsWith("MS_")){//列表写入
	                                for (int j = 1; j < tempList.size(); j++) {
	                                    row_index = readExecl.getCellRowNum(title_name + "_" + (j - 1));
	                                    col_index = readExecl.getCellColNum(title_name + "_" + (j - 1));
	                                    System.out.println(row_index);
	                                    if(row_index == 30){
	                                    	System.out.println("hhh");
	                                    }
	                                    templateRow = templateSheet.getRow(row_index);
	                                    cell = templateRow.getCell(col_index);
	                                    String value = tempList.get(j).toString();
	                                    cell.setCellValue(value);
	                                }
	                            } else {//表格写入
	                            	row_index = readExecl.getCellRowNum(title_name);
	                            	col_index = readExecl.getCellColNum(title_name);
	                            	if (!title_name.startsWith("MC_")){
	                            		//如果不要根据行数插入新行 titlename用MC_开头
	                            		PoiExcelUtil.insertRow(templateSheet, row_index, tempList.size() - 1);
	                            		//readExecl.bookNamesMove(row_index, row_index + tempList.size() - 1, 1);
	                            		//临时解决方案  如果是罚息版本
	                        	        if(args.length > 0){
	                        	        	readExecl.bookNamesMove(row_index, row_index + tempList.size() - 1, 1);
	                        	        }
	                            	}
	                                List columnIndex=new ArrayList();
	                                columnIndex=PoiExcelUtil.getMergedRegionIndex(templateSheet, row_index-1);
	                                for (int i = 0; i < tempList.size(); i++) {
	                                    List inTempList = (List) tempList.get(i);
	                                    templateRow = PoiExcelUtil.getRow(templateSheet, row_index + i);
	                                    for (int j = 0; j < inTempList.size(); j++) {
	                                        cell = PoiExcelUtil.getCell(templateRow, Integer.valueOf(columnIndex.get(j).toString()));
	                                        String temp = inTempList.get(j).toString();
	                                        cell.setCellValue(temp);
	                                    }
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	            
	            OutputStream osServer = new FileOutputStream(targetFile);
	            wb.write(osServer);
	            osServer.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            OutputStream osServer = new FileOutputStream(targetFile);
	            wb.write(osServer);
	            osServer.close();
	            throw new BusinessException("写excel时出错" + e.getMessage());
	        }
	    }
	    public double[][] getParameterArray(String projid,String parameterSelection,double windFarmEquipmentInput,double engineeringInput) throws Exception {
	    	
	    	ProjInfo projinfo = this.baseDao.findEntityByID(ProjInfo.class,projid);
	    	Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("projId",projinfo.getDevelopid());
	    	List<SelectionReport> selectionreport = this.baseDao.findEntityByProperties(SelectionReport.class, propertiesMap);
	    	double p90 = Double.parseDouble(selectionreport.get(0).getPninetyhours()==null?"0":(selectionreport.get(0).getPninetyhours()));
	    	double p75 = Double.parseDouble(selectionreport.get(0).getPseventyfivehours()==null?"0":(selectionreport.get(0).getPseventyfivehours()));
			double p50 = Double.parseDouble(selectionreport.get(0).getPfiftyhours()==null?"0":selectionreport.get(0).getPfiftyhours());
			double hoursofyear = Double.parseDouble(selectionreport.get(0).getAveStandard()==null?"0":selectionreport.get(0).getAveStandard());//年等效满负荷小时数
			double[] array1 = new double[8];
			double[] array2 = new double[8];
			double[][] array3 = new double[8][2] ;
			switch (parameterSelection.toLowerCase()){
			case "2":
				//p90
				array1[0]=p90;
				array1[1]=p90;
				array1[2]=p90;
				array1[3]=p90;
				array1[4]=p90;
				array1[5]=p75;
				array1[6]=p50;
				array1[7]=hoursofyear;
				//
				array2[0]=engineeringInput;
				array2[1]=engineeringInput*1.1+windFarmEquipmentInput*0.1;
				array2[2]=engineeringInput*1.05+windFarmEquipmentInput*0.05;
				array2[3]=engineeringInput*0.95-windFarmEquipmentInput*0.05;
				array2[4]=engineeringInput*0.9-windFarmEquipmentInput*0.1;
				array2[5]=engineeringInput;
				array2[6]=engineeringInput;
				array2[7]=engineeringInput;
				break;
			case "3":
				//p75
				array1[0]=p75;
				array1[1]=p75;
				array1[2]=p75;
				array1[3]=p75;
				array1[4]=p75;
				array1[5]=p90;
				array1[6]=p50;
				array1[7]=hoursofyear;
				//
				array2[0]=engineeringInput;
				array2[1]=engineeringInput*1.1+windFarmEquipmentInput*0.1;
				array2[2]=engineeringInput*1.05+windFarmEquipmentInput*0.05;
				array2[3]=engineeringInput*0.95-windFarmEquipmentInput*0.05;
				array2[4]=engineeringInput*0.9-windFarmEquipmentInput*0.1;
				array2[5]=engineeringInput;
				array2[6]=engineeringInput;
				array2[7]=engineeringInput;
			   break;
			case "4":
				//p50
				array1[0]=p50;
				array1[1]=p50;
				array1[2]=p50;
				array1[3]=p50;
				array1[4]=p50;
				array1[5]=p90;
				array1[6]=p75;
				array1[7]=hoursofyear;
				//
				array2[0]=engineeringInput;
				array2[1]=engineeringInput*1.1+windFarmEquipmentInput*0.1;
				array2[2]=engineeringInput*1.05+windFarmEquipmentInput*0.05;
				array2[3]=engineeringInput*0.95-windFarmEquipmentInput*0.05;
				array2[4]=engineeringInput*0.9-windFarmEquipmentInput*0.1;
				array2[5]=engineeringInput;
				array2[6]=engineeringInput;
				array2[7]=engineeringInput;
				break;
			case "1":
				//年等效满负荷小时数
				array1[0]=hoursofyear;
				array1[1]=hoursofyear;
				array1[2]=hoursofyear;
				array1[3]=hoursofyear;
				array1[4]=hoursofyear;
				array1[5]=p90;
				array1[6]=p75;
				array1[7]=p50;
				//
				array2[0]=engineeringInput;
				array2[1]=engineeringInput*1.1+windFarmEquipmentInput*0.1;
				array2[2]=engineeringInput*1.05+windFarmEquipmentInput*0.05;
				array2[3]=engineeringInput*0.95-windFarmEquipmentInput*0.05;
				array2[4]=engineeringInput*0.9-windFarmEquipmentInput*0.1;
				array2[5]=engineeringInput;
				array2[6]=engineeringInput;
				array2[7]=engineeringInput;
				break;
			default:
				break;
			}
			array3[0]=array1;
			array3[1]=array2;
			return array3;
	    	
	    };
public String[] getFileNameArray(String projid,String parameterSelection,double windFarmEquipmentInput,double engineeringInput) throws Exception {
	    	String[] filenamearray = new String[8];
	    	switch (parameterSelection.toLowerCase()){
			case "2":
				//自定义文件名称
				filenamearray[0]="(p90-标准)";
				filenamearray[1]="(p90-上浮10%)";
				filenamearray[2]="(p90-上浮5%)";
				filenamearray[3]="(p90-下浮5%)";
				filenamearray[4]="(p90-下浮10%)";
				filenamearray[5]="(p75-标准)";
				filenamearray[6]="(p50-标准)";
				filenamearray[7]="(全年标准-标准)";
				break;
			case "3":
				//自定义文件名称
				filenamearray[0]="(p75-标准)";
				filenamearray[1]="(p75-上浮10%)";
				filenamearray[2]="(p75-上浮5%)";
				filenamearray[3]="(p75-下浮5%)";
				filenamearray[4]="(p75-下浮10%)";
				filenamearray[5]="(p90-标准)";
				filenamearray[6]="(p50-标准)";
				filenamearray[7]="(全年标准-标准)";
			   break;
			case "4":
				//自定义文件名称
				filenamearray[0]="(p50-标准)";
				filenamearray[1]="(p50-上浮10%)";
				filenamearray[2]="(p50-上浮5%)";
				filenamearray[3]="(p50-下浮5%)";
				filenamearray[4]="(p50-下浮10%)";
				filenamearray[5]="(pp90-标准)";
				filenamearray[6]="(p75-标准)";
				filenamearray[7]="(全年标准-标准)";
				break;
			case "1":
				//自定义文件名称
				filenamearray[0]="(全年标准-标准)";
				filenamearray[1]="(全年标准-上浮10%)";
				filenamearray[2]="(全年标准-上浮5%)";
				filenamearray[3]="(全年标准-下浮5%)";
				filenamearray[4]="(全年标准-下浮10%)";
				filenamearray[5]="(p90-标准)";
				filenamearray[6]="(p75-标准)";
				filenamearray[7]="(p50-标准)";
				break;
			default:
				break;
			}
	    	return filenamearray;
	    }
}