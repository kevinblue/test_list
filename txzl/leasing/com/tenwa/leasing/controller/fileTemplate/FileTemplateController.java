package com.tenwa.leasing.controller.fileTemplate;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.entity.AppVersion;
import com.tenwa.business.entity.AttachmentFileUploadInfo;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.service.AttachmentFileService;
import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.PoiExcelUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.file.BaseFileTemplate;
import com.tenwa.leasing.entity.file.BaseFile_AttachmentInfo;
import com.tenwa.leasing.entity.proj.EvaluationModelData;
import com.tenwa.leasing.entity.proj.ReserveRatioData;
import com.tenwa.leasing.service.fileTemplate.FileTemplateService;



@Controller(value = "fileTemplateController")
@RequestMapping(value = "/**/leasing")
public class FileTemplateController   extends BaseController{
	
	@Resource(name="fileTemplateService")
	private FileTemplateService  templateService;
	@Resource(name = "baseService")
	private BaseService baseService;
	private String wordTemplateDir=ResourceUtil.getWordTemplatesRelativeStorePath();
	private String wordSourceTemplateDir=ResourceUtil.getWordSourceTemplatesRelativeStorePath();
	private String contractWordDir=ResourceUtil.getWordFilesRelativeStorePath();
	
	@Resource(name="attachmentFileService")
	private AttachmentFileService attachmentFileService ;
	/**   
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *新增模板
	 */
	@RequestMapping(value = "/template/addFileTemplate.action")
	@ResponseBody
	public String addFileTempalte(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		try {
			templateService.addFileTemplate(model);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		return null;
	
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *更新模板
	 */
	@RequestMapping(value = "/template/updateFileTemplate.action")
	@ResponseBody
	public String updateFileTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		templateService.updateFileTemplate(model);
			return null;
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *删除模板
	 */
	@RequestMapping(value = "/template/removeFileTemplate.action")
    @ResponseBody
	public String removeFileTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		try {
			templateService.removeFileTemplate(model);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @date 2014-9-3
	 * rovine
	 *上传模板ftl
	 */
	@RequestMapping(value = "/template/uploadingFileTemplateFtl.action")
    @ResponseBody
	public String uploadingFileTemplateFtl(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String message="上传模块成功";
		try {
			templateService.uploadTemplateFtl(request, response);
		} catch (Exception e) {
			message="上传模块失败";
			//throw new BusinessException(e.getMessage());
			String ajaxCallBackScript = "<script type='text/javascript'>window.parent.importExcelFormCallBack('"+message+e.getMessage()+"');</script>";
			this.ajaxReturn(response, ajaxCallBackScript);
		}
		String ajaxCallBackScript = "<script type='text/javascript'>window.alert('"+message+"');window.parent.location.reload();</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		//return "leasing/common/templateManager/FileTemplate.bi";
		return null;
	}
	
	/**
	 * 
	 * @date 2014-9-3
	 * rovine
	 *加载模板配置数据
	 */
	@RequestMapping(value = "/template/loadFileTemplateDataConfig.action")
    @ResponseBody
	public String loadFileTemplateDataConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		return templateService.loadFileTemplateDateConfig(model);
	}
	
	/**
	 * 
	 * @date 2014-9-3
	 * rovine
	 *新增模板配置数据
	 */
	@RequestMapping(value = "/template/addFileTemplateDataConfig.action")
    @ResponseBody
	public String addFileTemplateDataConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message="配置数据成功";
		System.out.println("22");
		try {
			templateService.addFileTempalteDateConfig(model);
		}catch(BusinessException b){
			message="保存配置数据出错:"+b.getMessage();
		} catch (Exception e) {
			message="保存配置数据出错:"+e.getMessage();
		}
		return message; 
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 下载模板和模板原文件
	 */
	@RequestMapping(value = "/template/downLoadTemplateAttach.action")
    @ResponseBody
	public String downloadingFileTemplateFtlSource(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapNoAjax(request);
	 
		 try{	 
			 this.templateService.downloadTemplateFtl(request, response);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.ajaxReturn(response, this.templateService.getDefaultAjaxCallBack("{result:'error',message:'"+e.getMessage()+"'}"));
		}
		return null;
	}
	
	@RequestMapping(value = "/template/createFileByTemplateClass.action")
	@ResponseBody
	public String createFileByTemplateClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String returnType=model.get("returntype");
		List<BaseFile> bfs=new ArrayList();
		BaseFile bf=null;
		String message="";
		try {
			bfs = this.templateService.createFileByTemplateClass(model);
			//生成合同附带上传附件一览的上传的文件
			if(Boolean.parseBoolean(model.get("isAttachment"))){
				//根据关键字1找到对应AttachmentFileUploadInfo
				if(null != bfs && bfs.size() > 0){
					Map<String, Object> propertiesMap = new HashMap<String, Object>();
					propertiesMap.put("identifierOne", model.get("identifierOne"));
					DictionaryData attachmentFileDict = new DictionaryData();
					attachmentFileDict.setId( model.get("attachmentFileDictId"));
					propertiesMap.put("attachmentFileDict", attachmentFileDict);
					List<AttachmentFileUploadInfo> uploadInfos = this.baseService.findEntityByProperties(AttachmentFileUploadInfo.class, propertiesMap);
					if(null != uploadInfos && uploadInfos.size() > 0){
						model.put("attachmentFileUploadInfoId", uploadInfos.get(0).getId());
					}
					this.attachmentFileService.uploadAttachmentFiles(request, response,model,bfs);
				}
			}
		}catch(Exception b){
			b.printStackTrace();
			message="{result:'error',message:'"+"出错:"+b.getMessage()+"'}";
			if(returnType.equalsIgnoreCase("file")){
			 this.ajaxReturn(response, this.templateService.getDefaultAjaxCallBack(message));
			}else{
				return message;
			}
		}
		
		if(returnType.equalsIgnoreCase("file")){
			//返回文件
			bf=bfs.get(0);
			this.templateService.addlogFileOper(bf, "下载");
			String fileTitleName=this.templateService.getdownloadfileName(model, bf.getFileName());
			String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath())+this.contractWordDir;
			String downloadedFileFullPath=uploadDirPath+"/"+bf.getFileAddress();
			downloadedFileFullPath=FileUtil.getFilePathString(downloadedFileFullPath);
			response.reset();
			response.setHeader("Content-disposition","attachment; filename="+fileTitleName);
			response.setContentType("text/html; charset=UTF-8");
			OutputStream out  = response.getOutputStream();
			ResourceUtil.getFileUploadOperation().downloadFile(downloadedFileFullPath, out, null);
		} else if(returnType.equalsIgnoreCase("jscallback")){
			//调用回调函数
			String jscallback=model.get("jscallback");
			Map<String,String> fieldClassMapping=new HashMap<String,String>();
			fieldClassMapping.put("DictionaryData", "code");
			fieldClassMapping.put("FileTemplate", "id");
			message="{result:'success',message:"+this.baseService.getCollectionEntitiesPropertiesToJsonArrayString(bfs, fieldClassMapping)+"}";
			String ajaxCallBackScript = "<script type='text/javascript'>window.parent."+jscallback+"(\""+message+"\");</script>";
			this.ajaxReturn(response, ajaxCallBackScript);
		}else{
			//ajax返回
			Map<String,String> fieldClassMapping=new HashMap<String,String>();
			fieldClassMapping.put("DictionaryData", "code");
			fieldClassMapping.put("FileTemplate", "id");
			message="{result:'success',message:"+this.baseService.getCollectionEntitiesPropertiesToJsonArrayString(bfs, fieldClassMapping)+"}";
			System.out.println(message);
			return message;
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value = "/template/uploadExcelToDatabase.action")
	public String addUploadCardHire(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		String message="";
		Boolean flag=false;
		String jscallback="importEbankCallBack";
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		
		if(model.containsKey("jscallback")){
			jscallback=model.get("jscallback");
		}
		try{
            message=this.templateService.uploadExcelFileToObject(request, response);
            flag=true;
		}
		 catch (BusinessException e) {
			 e.printStackTrace();
			 flag=false;
			 message=e.getMessage();
			message="\"message\":\""+message.toString()+"\"";
			
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
			message=e.getMessage();
			message="\"message\":\""+message.toString()+"\"";
		}
		 String returnStr="{\"result\":\""+flag+"\","+message+"}";
		 Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
         Matcher m = p.matcher(returnStr);  
         returnStr = m.replaceAll("");  
         System.out.println(returnStr);
	    String ajaxCallBackScript = "<script type='text/javascript'>window.parent."+jscallback+"('"+returnStr+"');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return  null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/template/removeTempalate.action")
	public String removeTemplate(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		this.templateService.removeTemplate();
		return "成功";
	}
	@ResponseBody
	@RequestMapping(value = "/template/dropCreateFile.action")
	public String dropCreateFile(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		if(model.containsKey("ids")){
		   String ids=model.get("ids");
		   String[] arryids=ids.split(",");
		   List<BaseFile> basefiles= this.baseService.findEntityByIDArray(BaseFile.class, arryids);
		   HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		   for(BaseFile bf:basefiles){
			     if(model.containsKey("isAttachment")){
			    	  String isAttachment=model.get("isAttachment");
			    	  if("true".equals(isAttachment)){	
			    		  propertiesMap.clear();
			    		  propertiesMap.put("uploadid", bf.getId());
			           List<BaseFile_AttachmentInfo> baList = this.baseService.findEntityByProperties(BaseFile_AttachmentInfo.class, propertiesMap); 		  
			    		  if(baList.size()>0){
			    			  BaseFile_AttachmentInfo bainfo=baList.get(0);
			    		      String returnInfo = this.attachmentFileService.removeAttachmentFile(bainfo.getAttachmentid());
			    		    if("删除成功！".equals(returnInfo)){
			    		    	 this.baseService.removeEntity(bainfo);
			    		    }
			    		    
			    		  }
			    	  }
			     }
			   bf.setInvalid("否");
			   this.baseService.saveEntity(bf);
		   }
		}
		return "删除成功";
	}
	
	@ResponseBody
	@RequestMapping(value = "/template/dropCreateFileAndValue.action")
	public String dropCreateFileAndValue(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		if(model.containsKey("ids")){
		   String ids=model.get("ids");
		   String[] arryids=ids.split(",");
		   List<BaseFile> basefiles= this.baseService.findEntityByIDArray(BaseFile.class, arryids);
		   HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		   for(BaseFile bf:basefiles){
			     if(model.containsKey("isAttachment")){
			    	  String isAttachment=model.get("isAttachment");
			    	  if("true".equals(isAttachment)){	
			    		  propertiesMap.clear();
			    		  propertiesMap.put("uploadid", bf.getId());
			           List<BaseFile_AttachmentInfo> baList = this.baseService.findEntityByProperties(BaseFile_AttachmentInfo.class, propertiesMap); 		  
			    		  if(baList.size()>0){
			    			  BaseFile_AttachmentInfo bainfo=baList.get(0);
			    		      String returnInfo = this.attachmentFileService.removeAttachmentFile(bainfo.getAttachmentid());
			    		    if("删除成功！".equals(returnInfo)){
			    		    	 this.baseService.removeEntity(bainfo);
			    		    }
			    		    
			    		  }
			    	  }
			     }
			   bf.setInvalid("否");
			   this.baseService.saveEntity(bf);
		   }
		   List<EvaluationModelData> emdListNew = new ArrayList<EvaluationModelData>();
		   List<ReserveRatioData> rrdListNew = new ArrayList<ReserveRatioData>();
		      propertiesMap.clear();
		      propertiesMap.put("projId", basefiles.get(0).getFileNumber());
		      propertiesMap.put("status", "1");
		      List<EvaluationModelData> emdList = this.baseService.findEntityByProperties(EvaluationModelData.class,propertiesMap);
		      List<ReserveRatioData> rrdList = this.baseService.findEntityByProperties(ReserveRatioData.class,propertiesMap);
	           for(EvaluationModelData emd:emdList){
	         	  emd.setStatus("2");
	         	 emdListNew.add(emd);
	           };
	           for(ReserveRatioData rrd:rrdList){
	        	      rrd.setStatus("2");
	        	      rrdListNew.add(rrd);
		       };
		      this.baseService.updateAllEntities(emdListNew);
		      this.baseService.updateAllEntities(rrdListNew);
		}
		return "删除成功";
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 通过ID下载文档
	 */
	@RequestMapping(value = "/template/downLoadAttachById.action")
	@ResponseBody
	public String downLoadAttachById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.templateService.downloadAttachById(request, response);
	}
	@RequestMapping(value = "/template/downLoadRentNoticeAttachById.action")
	@ResponseBody
	public String downLoadRentNoticeAttachById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.templateService.downLoadRentNoticeAttachById(request, response);
	}
	
	/**
	 * 
	 * @date 2013-7-31 xuyunlong 加载模板分类进行生成文件
	 */
	@RequestMapping(value = "/template/loadTemplateByClassify.action")
	@ResponseBody
	public String LoadTemplateByClassify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapNoAjax(request);
		List<BaseFileTemplate> listTemplate = this.templateService.LoadTemplateByClassify(model);
		int changeRowNum = 0;
		if (model.containsKey("changeRowNum")) {
			changeRowNum = Integer.valueOf(model.get("changeRowNum")).intValue();
		}
		if (listTemplate != null && listTemplate.size() > 0) {
			return this.templateService.createCheckBoxByTemplate(listTemplate, changeRowNum);
		} else {
			return "没有合同模板.";
		}
	}
	
	/**
	 * 
	 * @date 2013-7-31 xuyunlong 加载模板分类进行生成文件
	 */
	@RequestMapping(value = "/template/CreateFileByTemplate.action")
	@ResponseBody
	public String CreateFileByTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message = "";
		String contractlist = "";
		try {
			contractlist = this.templateService.createFileByTemplate(model,request,response);
		} catch (Exception e) {
			String tempmessage = e.getMessage();
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(tempmessage);
			message = m.replaceAll("");
			return "{result:'error',message:'" + message + "'}";

		}
		return "{result:'success',message:" + contractlist + "}";
	}
	
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 通过ID下载文档
	 */
	@RequestMapping(value = "/template/downLoadAttachByIdtoEdit.action")
	@ResponseBody
	public String downLoadAttachByIdtoEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.templateService.downloadAttachByIdToEdit(request, response);
	}
	
	/**
	 * 
	 * @date 2013-7-31 xuyunlong 文档编辑之后上传
	 */
	@RequestMapping(value = "/template/uploadEidtFile.action")
	@ResponseBody
	public String uploadEidtFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return this.templateService.uploadEditTemplate(request, response);
	}
	
	/**
	 * 
	 * @date 2013-7-31 xuyunlong 文件上传
	 */
	@RequestMapping(value = "/file/uploadFile.action")
	@ResponseBody
	public String uploadSaveFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message="";
		String jscallback=model.get("jscallback");
		try {
			this.templateService.saveuploadSoureFile(request, model);
			 String uploadid=model.get("uploadid");
			if(Boolean.parseBoolean(model.get("isAttachment"))){
				//根据关键字1找到对应AttachmentFileUploadInfo
				Map<String, Object> propertiesMap = new HashMap<String, Object>();
				propertiesMap.put("identifierOne", model.get("identifierOne"));
				DictionaryData attachmentFileDict = new DictionaryData();
				attachmentFileDict.setId( model.get("attachmentFileDictId"));
				propertiesMap.put("attachmentFileDict", attachmentFileDict);
				List<AttachmentFileUploadInfo> uploadInfos = this.baseService.findEntityByProperties(AttachmentFileUploadInfo.class, propertiesMap);
				if(null != uploadInfos && uploadInfos.size() > 0){
					model.put("attachmentFileUploadInfoId", uploadInfos.get(0).getId());
				}
				this.attachmentFileService.uploadAttachmentFiles(request, response,model);
				String attachmentid=model.get("currentCttachmentFileUploadInfoDetailId");
				BaseFile_AttachmentInfo bainfo=new BaseFile_AttachmentInfo();
				User user =  SecurityUtil.getPrincipal();
				String nowdate = DateUtil.getSystemDateTime();
				bainfo.setAttachmentid(attachmentid);
				bainfo.setUploadid(uploadid);
				bainfo.setCreator(user);
				bainfo.setCreateDate(nowdate);
				this.baseService.saveEntity(bainfo);
			}
			//APP二维码自定义方法
			if(StringUtil.nullToString(model.get("modelname")).equals("app_version")){
				String appid=model.get("appId");
				AppVersion app=this.baseService.findEntityByID(AppVersion.class, appid);
				BaseFile bs=this.baseService.findEntityByID(BaseFile.class, uploadid);
				app.setBaseFileId(bs);
				app.setImagePath(bs.getFileAddress());
				this.baseService.updateEntity(app);
				jscallback="alert";
			}
			message="成功上传";
		} catch (Exception e) {
			e.printStackTrace();
			message="上传失败 内容："+e.getMessage();
		}
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent."+jscallback+"('"+message+"');</script>";
		if(StringUtil.nullToString(model.get("modelname")).equals("app_version")){
			ajaxCallBackScript+="<script type='text/javascript'>window.parent.location.reload();</script>";
		}
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}
	
	/**
	 * 
	 * @date 2013-7-31 xuyunlong 文件上传
	 */
	@RequestMapping(value = "/file/setFileState.action")
	@ResponseBody
	public String setFileState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message="";
		String jscallback=model.get("jscallback");
		try {
			this.templateService.saveFileState(model);
			message="设置状态成功";
		} catch (Exception e) {
			e.printStackTrace();
			message="设置状态失败 内容："+e.getMessage();
		}
		return message;
	}
	
	@RequestMapping(value = "/getUploadExcelData.action")
	@ResponseBody
	public String queryContractInfoDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject dataJsonObj = new JSONObject();

		User user = (User) SecurityUtil.getPrincipal();
		String nowdate = DateUtil.getSystemDateTime();

		Map<String, String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("uplaodfile");
		String realFileName = multipartFile.getOriginalFilename();
		String fileSuffix = realFileName.substring(realFileName.lastIndexOf('.'));

		// 保存文件
		String encodeFileName = UUIDUtil.getUUID() + fileSuffix;
		String uploadFilePath = ResourceUtil.getFileUploadDataPath() + ResourceUtil.getWordFilesRelativeStorePath();
		String fileAddress = "/WorkflowAttchmentFiles/"
				+ FileUtil.getYearMonthDayPathByString(DateUtil.getSystemDate(), "yyyy-MM-dd") + "/" + encodeFileName;
		String fileFullPath = uploadFilePath + fileAddress;
		ResourceUtil.getFileUploadOperation().createNotExistDirs(fileFullPath);
		
		InputStream is = multipartFile.getInputStream();
		FileUtils.copyInputStreamToFile(is, new File(fileFullPath));
		
		String upload_module = modelData.get("import_type");
		String file_key = modelData.get("file_key");
		String call_back_func = modelData.get("call_back_func");
		
		BaseFile bf = new BaseFile();
		bf.setFileAddress(fileAddress);
		bf.setFilekey(file_key);
		bf.setFileName(realFileName);
		bf.setInvalid("是");
		bf.setModelName(upload_module);
		bf.setCreateDate(nowdate);
		bf.setCreator(user);
		this.baseService.saveEntity(bf);
		try {
			is.reset();
			Workbook wb = null;
			String importFileName = multipartFile.getOriginalFilename().toLowerCase();
			try {
				if (importFileName.endsWith("xlsx")) {
					wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2007);
				} else {
					wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2003);
				}
			} catch (Exception e) {
				throw new Exception("解析excel出错" + e.getMessage());
				// returnMsg = "{result:'failure',message:'解析excel出错" +
				// e.getMessage() + "'}";
			}
			JSONObject jsonObj = this.templateService.getExcelDataByConfig(wb, upload_module);
			//导入评估报告附带上传到附件一览中
			if(Boolean.parseBoolean(modelData.get("isAttachment"))){
			//根据关键字1找到对应AttachmentFileUploadInfo
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("identifierOne", modelData.get("identifierOne"));
			DictionaryData attachmentFileDict = new DictionaryData();
			attachmentFileDict.setId( modelData.get("attachmentFileDictId"));
			propertiesMap.put("attachmentFileDict", attachmentFileDict);
			List<AttachmentFileUploadInfo> uploadInfos = this.baseService.findEntityByProperties(AttachmentFileUploadInfo.class, propertiesMap);
			if(null != uploadInfos && uploadInfos.size() > 0){
				modelData.put("attachmentFileUploadInfoId", uploadInfos.get(0).getId());
			}
			this.attachmentFileService.uploadAttachmentFiles(request, response,modelData);
		}
			dataJsonObj.put("result", "success");
			dataJsonObj.put("message", "上传成功！");
			dataJsonObj.put("basefileid", bf.getId());
			dataJsonObj.put("basefilename", bf.getFileName());
			dataJsonObj.put("exceldata", jsonObj);
		} catch (Exception e) {
			dataJsonObj.put("result", "failure");
			dataJsonObj.put("message", e.getMessage());
			dataJsonObj.put("basefileid", bf.getId());
			dataJsonObj.put("basefilename", bf.getFileName());
			e.printStackTrace();
		} finally {
			is.close();
		}

		String ajaxCallBackScript = "<script type='text/javascript'>window.parent." + call_back_func + "('" + dataJsonObj.toString() + "');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);

		return null;
	}
}
