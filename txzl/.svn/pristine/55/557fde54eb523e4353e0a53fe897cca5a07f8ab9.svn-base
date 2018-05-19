package com.tenwa.business.serviceImpl;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.reckon.util.ScaleImageUtils;
import com.tenwa.business.entity.AppImage;
import com.tenwa.business.model.FileProcess;
import com.tenwa.business.service.AppImageService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.WebUtil;

@Service(value = "appImageService")
public class AppImageServiceImpl implements AppImageService {

	private Log logger = LogFactory.getLog(this.getClass());
	
	@Resource(name = "tableService")
	private TableService tableService;

	@Override
	public String addAppImage(HttpServletRequest request,String filename) throws Exception {
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		AppImage appImage = new AppImage();
		tableService.saveOrUpdateEntity(appImage);
		String uuid = appImage.getId();
		logger.info("上传文件之主键ID：" + uuid);
		
		// 获取流信息
		//filename = "name_upload_file_name";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile(filename);
		InputStream source = multipartFile.getInputStream();
		String contextRealPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath());// 项目绝对路径
		//String contextPath = WebUtil.getWebContextPath();// 项目绝对路径
		String fileName = multipartFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		String title = model.get("title");
		if(title == null || title.isEmpty()){
			model.put("title", fileName);
		}
		if(model.get("remark") != null && !model.get("remark").isEmpty()){
			model.put("title", model.get("remark"));
		}
		
		logger.info("上传文件之项目文件名称：" + fileName);
		logger.info("上传文件之项目绝对路径：" + contextRealPath);
		// 图片引用路径
		
		String uploadDirPath = "/images/" + new Random().nextInt(50);// 图片相对文件夹，分50个
		String uploadFileName = uploadDirPath + "/" + uuid + "_original" + suffix;// 图片相对路径，存数据库
		uploadFileName = FileUtil.getFilePathString(uploadFileName);// 图片相对路径
		String thumbFileName = uploadDirPath + "/" + uuid + "_thumb" + suffix;// 缩略图相对路径，存数据库
		thumbFileName = FileUtil.getFilePathString(thumbFileName);// 图片相对路径
		
		logger.info("上传文件之文件夹相对目录：" + uploadDirPath);
		logger.info("上传文件之原始图相对路径：" + uploadFileName);
		logger.info("上传文件之缩略图相对路径：" + thumbFileName);
		
		// 2个文件的绝对路径
		String imageRealPath = contextRealPath + uploadFileName;
		imageRealPath = FileUtil.getFilePathString(imageRealPath);// 图片相对路径
		String thumbRealPath = contextRealPath + thumbFileName;
		thumbRealPath = FileUtil.getFilePathString(thumbRealPath);// 缩略图相对路径
		logger.info("上传文件之原始图绝对路径：" + imageRealPath);
		logger.info("上传文件之缩略图绝对路径：" + thumbRealPath);
		
		// 获取上传文件夹
		String uploadDirFullPath = FileUtil.getFilePathString(contextRealPath + uploadDirPath);
		File uploadDir = new File(uploadDirFullPath);
		if(uploadDir.exists() == false){
			uploadDir.mkdirs();
			logger.info("上传文件之文件夹创建成功：" + uploadDir.getAbsolutePath());
		}
		
		// 上传文件
		HttpSession session = request.getSession();
		FileProcess fileProcess = (FileProcess)session.getAttribute("attachmentFileProcessKey");
		ResourceUtil.getFileUploadOperation().uploadFile(imageRealPath, source, fileProcess);
		try { source.close(); } catch (Exception e) { }
		logger.info("上传文件之文件上传成功：" + uploadDir.getAbsolutePath());
		
		
		// 创建缩略图，存储数据库
		File uploadFile = new File(imageRealPath);
		if(uploadFile.exists()){
			Map<String, String> fieldMap = new HashMap<String, String>();
			fieldMap.put("User", "id");
			tableService.copyAndOverrideExistedValueFromStringMap(model, appImage, fieldMap);
			if(appImage.getThumbImageWidth() == null){
				appImage.setThumbImageWidth(200);
			}
			if(appImage.getThumbImageHeight() == null){
				appImage.setThumbImageHeight(200);
			}
			appImage.setImagePath(imageRealPath);
			appImage.setThumbImagePath(thumbRealPath);
			ScaleImageUtils.createThumbAppImage(appImage);
			logger.info("上传文件之缩略图创建成功：" + appImage.getImageSize());
			appImage.setImagePath(uploadFileName);
			appImage.setThumbImagePath(thumbFileName);
			tableService.saveOrUpdateEntity(appImage);
			return convertAppImageToJsonString(appImage);
		}
		return null;
	}
	
	@Override
	public String removeAppImage(String id) throws Exception{
		AppImage appImage = tableService.findEntityByID(AppImage.class, id);
		String retult = convertAppImageToJsonString(appImage);
		tableService.removeEntity(appImage);
		return retult;
	}
	
	@Override
	public String updateAppImage(Map<String, String> model) throws Exception {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("User", "id");
		String id = model.get("id");
		AppImage appImage = tableService.findEntityByID(AppImage.class, id);
		tableService.copyAndOverrideExistedValueFromStringMap(model, appImage, fieldMap);
		tableService.saveEntity(appImage);
		return convertAppImageToJsonString(appImage);
	}

	@Override
	public String findAppImage(String id) throws Exception {
		AppImage appImage = tableService.findEntityByID(AppImage.class, id);
		return convertAppImageToJsonString(appImage);
	}
	
	private String convertAppImageToJsonString(AppImage appImage) throws Exception{
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("User", "id");
		return tableService.getEntityPropertiesToJsonEntityString(appImage, fieldMap);
	}
}
