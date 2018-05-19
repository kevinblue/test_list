package com.tenwa.business.controller.image;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.AppImageService;
import com.tenwa.kernal.utils.QueryUtil;

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
