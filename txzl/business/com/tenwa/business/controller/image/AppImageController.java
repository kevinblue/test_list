package com.tenwa.business.controller.image;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.AppImageService;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;

@Controller(value = "appImageController")
@RequestMapping(value = "/**/acl")
public class AppImageController extends BaseController {
	
	@Resource(name = "appImageService")
	private AppImageService appImageService;
	
	@ResponseBody
	@RequestMapping(value = "/addAppImage.acl")
	public String addAppImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			response.setContentType("text/html;charset=UTF-8"); 
			response.setCharacterEncoding("UTF-8");
			String appImage = appImageService.addAppImage(request,"");
			String returnInfo = "<script type=\"text/javascript\">window.parent.uploadSuccess(";
			if(appImage != null){
				returnInfo += appImage;
			} else {
				returnInfo +=  "{error:true}";
			}
			returnInfo +=  ");</script>";
			return returnInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return "<script type=\"text/javascript\">window.parent.uploadSuccess({error:true});</script>";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAppImage.acl")
	public void getAppImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServletOutputStream sos =  null;
		InputStream is  = null;
		try {
			String url = request.getParameter("url");
			String contextRealPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath());
			String filePath =  contextRealPath + url;
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			sos = response.getOutputStream();
		    is = new BufferedInputStream(new FileInputStream(filePath)) ;
			byte[] data = new byte[1024];
			int c;
			while ((c = is.read(data, 0, 1024)) != -1) {
				try {
					sos.write(data, 0, c);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(sos != null){
				try{
					sos.close();
				}catch(Exception e){
					
				}
			}
			if(is != null){
				try{
					is.close();
				}catch(Exception e){
					
				}
			}
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateAppImage.acl")
	public String updateAppImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
			return appImageService.updateAppImage(model);
		} catch (Exception e) {
			return "{error:true}";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeAppImage.acl")
	public String removeAppImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
			String id = model.get("id");
			return appImageService.removeAppImage(id);
		} catch (Exception e) {
			return "{error:true}";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/findAppImageById.acl")
	public String findAppImageById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
			String id = model.get("id");
			return appImageService.findAppImage(id);
		} catch (Exception e) {
			return "{error:true}";
		}
	}
}
