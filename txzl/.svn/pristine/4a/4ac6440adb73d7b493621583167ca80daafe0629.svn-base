/**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.controller.notice
 * 文件名：         NoticeController.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-7-30-上午10:06:54
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.controller.sms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.notice.SmsNotice;
import com.tenwa.business.entity.notice.SmsTemplate;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.message.SendMessageUtil;

/**
 * 类名称： SmsController 类描述： 创建人： Administrator 修改人： Administrator
 * 修改时间：2013-7-30 上午10:06:54 修改备注：
 * 
 * @version 1.0.0
 **/
@Controller(value = "smsController")
@RequestMapping(value = "/**/acl")
public class SmsController{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSmsTask.acl")
	@ResponseBody
	public JsonReturnResult addSmsTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			SmsNotice notice = new SmsNotice();
			this.baseService.copyAndOverrideExistedValueFromStringMap(model, notice, null);
			String result = null;
            String smstype = model.get("smstype");
			
			if("IMMEDIATELY".equals(smstype)){
				try {
					result = SendMessageUtil.sendMessage(notice.getPhoneNumber(), notice.getSmsContent());
				} catch (Exception e) {
					logger.error("短信发送任务执行失败。", e);
					result = "发送失败，发送遇到异常：" + e.getMessage();
				}
				notice.setSmsType("IMMEDIATELY");
				notice.setSendResult(result);
				notice.setSendTime(sdf.format(new Date()));
				notice.setSendFlag(1);
			}
			this.baseService.saveOrUpdateEntity(notice);
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendSmsNow.acl")
	@ResponseBody
	public JsonReturnResult sendSmsNow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			String [] ids = model.get("ids").split(",");
			for(String id : ids){
				SmsNotice notice = (SmsNotice) this.tableService.findEntityByID(SmsNotice.class, id);
				if(notice != null){
					if(notice.getSendFlag() == 0){
						String result = null;
						try {
							result = SendMessageUtil.sendMessage(notice.getPhoneNumber(), notice.getSmsContent());
						} catch (Exception e) {
							logger.error("短信发送任务执行失败。", e);
							result = "发送失败，发送遇到异常：" + e.getMessage();
						}
						notice.setSmsType("IMMEDIATELY");
						notice.setSendResult(result);
						notice.setSendTime(sdf.format(new Date()));
						notice.setSendFlag(1);
					}
					this.baseService.updateEntity(notice);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendSmsAgain.acl")
	@ResponseBody
	public JsonReturnResult sendSmsAgain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			String [] ids = model.get("ids").split(",");
			for(String id : ids){
				SmsNotice notice = (SmsNotice) this.tableService.findEntityByID(SmsNotice.class, id);
				if(notice != null){
					String result = null;
					try {
						result = SendMessageUtil.sendMessage(notice.getPhoneNumber(), notice.getSmsContent());
					} catch (Exception e) {
						logger.error("短信发送任务执行失败。", e);
						result = "发送失败，发送遇到异常：" + e.getMessage();
					}
					notice.setSendResult(result);
					notice.setSendTime(sdf.format(new Date()));
					notice.setSendFlag(1);
					this.baseService.updateEntity(notice);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSmsTask.acl")
	@ResponseBody
	public JsonReturnResult updateSmsTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			SmsNotice notice = (SmsNotice) this.tableService.findEntityByID(SmsNotice.class, model.get("id"));
			String result = null;
			if(notice != null){
				String noticeTime = model.get("noticetime");
				String smsContent = model.get("smscontent");
				String phoneNumber = model.get("phonenumber");
				notice.setNoticeTime(noticeTime);
				notice.setSmsContent(smsContent);
				notice.setPhoneNumber(phoneNumber);
				String smstype = model.get("smstype");
				if("IMMEDIATELY".equals(smstype) && notice.getSendFlag() == 0){
					try {
						result = SendMessageUtil.sendMessage(notice.getPhoneNumber(), notice.getSmsContent());
					} catch (Exception e) {
						logger.error("短信发送任务执行失败。", e);
						result = "发送失败，发送遇到异常：" + e.getMessage();
					}
					notice.setSmsType("IMMEDIATELY");
					notice.setSendResult(result);
					notice.setSendTime(sdf.format(new Date()));
					notice.setSendFlag(1);
				}
				this.baseService.updateEntity(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeSmsTask.acl")
	@ResponseBody
	public JsonReturnResult removeSmsTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			String id = model.get("id");
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("id", id);
			SmsNotice notice = (SmsNotice) this.baseService.getNewOrUpdateObject(SmsNotice.class, propertiesMap);
			if(notice != null){
				notice.setSendFlag(2);
				this.baseService.updateEntity(notice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}

	public String getError(String message) throws Exception {
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
	
	/**
	 * 新增短信模板
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSmsTemplate.acl")
	@ResponseBody
	public JsonReturnResult addSmsTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		
		String templateNo = model.get("templateno");
		String templateType = model.get("templatetype");
		String templateContent = model.get("templatecontent");
		try {
			SmsTemplate template = new SmsTemplate();
			template.setTemplateNo(templateNo);
			template.setTemplateType(templateType);
			template.setTemplateContent(templateContent);
			template.setCreator((User) SecurityUtil.getPrincipal());
			template.setCreateDate(DateUtil.getSystemDateTime());
			this.baseService.saveEntity(template);
			
			String updateSsql = "update T_SMS_TEMPLATE set ID_ = TEMPLATE_NO where id_= ?";
			this.baseService.updateBySql(updateSsql, template.getId());
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
}
