
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.controller.notice
 * 文件名：         NoticeController.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-7-30-上午10:06:54
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.controller.notice;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.entity.NoticeAttachment;
import com.tenwa.business.entity.notice.Notice;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.UUIDUtil;


 /**
 * 类名称：     NoticeController
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-7-30 上午10:06:54
 * 修改备注：
 * @version 1.0.0
 **/
@Controller(value="noticeController")
@RequestMapping(value="/**/acl")
public class NoticeController extends BaseController
{
	  Log logger = LogFactory.getLog(this.getClass());
	  @Resource(name="baseService")
	  private BaseService baseService;
	 
	   @RequestMapping(value="/addNotice.acl")
	   public String addNotice(HttpServletRequest request,HttpServletResponse response) throws Exception
	   {
           this.updateNotice(request, response);
		   return null;
	   }
	   @RequestMapping(value="/updateNotice.acl")
	   public String updateNotice(HttpServletRequest request,HttpServletResponse response) throws Exception
	   {
		   Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		   Map<String,Object> propertiesMap = new HashMap<String,Object>();
		   propertiesMap.put("id", model.get("id"));
		   Notice notice = (Notice)this.baseService.getNewOrUpdateObject(Notice.class, propertiesMap);
		   this.baseService.copyAndOverrideExistedValueFromStringMap(model, notice, null);
		   this.baseService.saveOrUpdateEntity(notice);
		   return null;
	   }
	   @RequestMapping(value="/removeNotice.acl")
	   public String removeNotice(HttpServletRequest request,HttpServletResponse response) throws Exception
	   {
		   Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		   String id = model.get("id");
		   this.baseService.removeEntityById(Notice.class, id);
		   return null;
	   }
	   @RequestMapping(value="/uploadNoticeFile.acl")
	   public String  uploadNoticeFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   if(!ServletFileUpload.isMultipartContent(request))
		   {
			   	this.output(response, getError("请选择文件。"));
			   	return null;
		   }
		   //定义允许上传的文件扩展名
		   HashMap<String, String> extMap = new HashMap<String, String>();
		   extMap.put("image", "gif,jpg,jpeg,png,bmp");
		   extMap.put("flash", "swf,flv");
		   extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		   extMap.put("file", "gif,jpg,jpeg,png,bmp,doc,docx,xls,xlsx,ppt,pptx,htm,html,txt,zip,rar,gz,bz2,pdf");
		   //最大文件大小
		   long maxSize = 100000000;
		   String dirName = request.getParameter("dir");
		   if (dirName == null) {
		   	dirName = "image";
		   }
		   if(!extMap.containsKey(dirName))
		   {
			   this.output(response,getError("目录名不正确。"));
		   	return null;
		   }
		   MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		   Map<String, MultipartFile> uploadedFiles = multipartRequest.getFileMap();
		   for(String fileNameKey : uploadedFiles.keySet()){
			   MultipartFile  uploadedFile =  uploadedFiles.get(fileNameKey);
			   String fileName  = uploadedFile.getOriginalFilename();
			   String chinseFileName = fileName;
			   long fileSize = uploadedFile.getSize();
		   		//检查文件大小
		   		if(fileSize > maxSize){
		   			this.output(response,getError("上传文件大小超过限制。"));
		   			return null;
		   		}
		   		
		   		//检查扩展名
		   		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		   		if(!Arrays.asList(extMap.get(dirName).split(",")).contains(fileExt)){
		   			this.output(response,getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
		   			return null;
		   		}
		   		
				String currentTime = DateUtil.getSystemDateTime();
				String currentDate = currentTime.substring(0,10);
		   		String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath())+"/notices/"+dirName+"/"+currentDate;
                String newFileName = UUIDUtil.getUUID()+ "." + fileExt;
                String uploadedFileFullPath = uploadDirPath+"/"+newFileName;
                InputStream in =null;
		   		try{
					 in = uploadedFile.getInputStream() ;
					ResourceUtil.getFileUploadOperation().uploadFile(uploadedFileFullPath, in, null);
		   		}catch(Exception e){
		   			this.output(response,getError("上传文件失败。"));
		   			return null;
		   		}finally{
		   			if(null!=in){FileUtil.safeCloseInputStream(in);}
		   		}
		   		NoticeAttachment noticeAttachment = new NoticeAttachment();
		   		noticeAttachment.setChineseFileName(chinseFileName);
		   		noticeAttachment.setEncodeFileName(newFileName);
		   		noticeAttachment.setFileSize(fileSize);
		   		noticeAttachment.setFileType("公告上传文件");
		   		noticeAttachment.setUploadTime(currentTime);
		   		noticeAttachment.setUploadUser(SecurityUtil.getPrincipal());
		   		this.baseService.saveEntity(noticeAttachment);
		   		JSONObject obj = new JSONObject();
		   		obj.put("error", 0);
		   		obj.put("title",fileName);
		   		if("file".equalsIgnoreCase(dirName)){
		   			obj.put("url","uuid-"+noticeAttachment.getId());
		   		}else{
		   			obj.put("url","acl/downloadNoticeUploadFile.acl?uploadedFileFullPath=" +uploadedFileFullPath+"&chinseFileName="+URLEncoder.encode(chinseFileName,"UTF-8")+"&type="+dirName);
		   		}
		   		this.output(response,obj.toString());
		   }
		   return null;
	   }
	   @RequestMapping(value="/downloadNoticeUploadFile.acl")
	   public String  downloadNoticeUploadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
	       String type = request.getParameter("type");
	       if("file".equalsIgnoreCase(type)){
	   		   //String fileTitleName = request.getParameter("chinseFileName");
	    	   String uuid = request.getParameter("uuid");
		       NoticeAttachment noticeAttachment  = this.baseService.findEntityByID(NoticeAttachment.class, uuid);
		       String fileTitleName = noticeAttachment.getChineseFileName();
			   String currentTime = noticeAttachment.getUploadTime();
			   String currentDate = currentTime.substring(0,10);
		   	   String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath())+"/notices/"+type+"/"+currentDate;
               String newFileName = noticeAttachment.getEncodeFileName();
               String downloadedFileFullPath = uploadDirPath+"/"+newFileName;
		       String browserType  = "";
		       String browserAgent = request.getHeader("USER-AGENT").toLowerCase();
		       if(browserAgent.indexOf("firefox")>-1){
		    	   browserType = "firefox";
		       }else if(browserAgent.indexOf("chrome")>-1){
		    	   browserType = "chrome";
			   }else if(browserAgent.indexOf("safari")>-1){
				   browserType = "safari";
			   }
		       
			   if("firefox".equals(browserType))
			   {
				   fileTitleName = new String(fileTitleName.getBytes("GB2312"),"ISO-8859-1");
			   }
			   else if("chrome".equals(browserType))
			   {
				   fileTitleName = URLEncoder.encode(fileTitleName, "UTF-8");
			   }
			   else if("safari".equals(browserType))
			   {
				   fileTitleName = new String(fileTitleName.getBytes("UTF-8"),"ISO-8859-1");
			   }
			   else 
			   {
				   fileTitleName = URLEncoder.encode(fileTitleName, "UTF-8");
			   }
			    response.reset();
				response.setHeader("Content-disposition","attachment; filename="+fileTitleName);
				response.setContentType("text/html; charset=UTF-8");
				OutputStream      out  = response.getOutputStream();
				ResourceUtil.getFileUploadOperation().downloadFile(downloadedFileFullPath, out, null);
	       }else{
		       String downloadedFileFullPath = request.getParameter("uploadedFileFullPath");
	    	   OutputStream out  = response.getOutputStream();
	    	   try{
	    		   out = response.getOutputStream();
	    		   ResourceUtil.getFileUploadOperation().readInputStreamToOutputStream(downloadedFileFullPath, out);
	    	   }catch(Exception e){
	    		   System.out.println("文件《"+downloadedFileFullPath+"》不存在！");
	    	   }
			  finally{
					if( null != out){
						out.flush();
						FileUtil.safeCloseOutStream(out);
					}
				}
	       }
		   return null;
	   }
        
	   public String getError(String message) throws Exception 
	   {
			JSONObject obj = new JSONObject();
			obj.put("error", 1);
			obj.put("message", message);
			return obj.toString();
		}
	   
	   public void setBaseService(BaseService baseService) {
			this.baseService = baseService;
	   }
	
	   public BaseService getBaseService() {
			return baseService;
	   }
}
