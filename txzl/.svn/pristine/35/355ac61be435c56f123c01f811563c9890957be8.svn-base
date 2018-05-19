/**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.controller.notice
 * 文件名：         NoticeController.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-7-30-上午10:06:54
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.controller.email;
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
import com.tenwa.business.entity.notice.EmailNotice;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.email.SendEmailUtil;
import com.tenwa.kernal.utils.QueryUtil;

/**
 * 类名称： EmailController 类描述： 创建人： Administrator 修改人： Administrator
 * 修改时间：2013-7-30 上午10:06:54 修改备注：
 * 
 * @version 1.0.0
 **/
@Controller(value = "emailController")
@RequestMapping(value = "/**/acl")
public class EmailController{
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
	@RequestMapping(value = "/addEmailTask.acl")
	@ResponseBody
	public JsonReturnResult addEmailTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			EmailNotice notice = new EmailNotice();
			this.baseService.copyAndOverrideExistedValueFromStringMap(model, notice, null);
			String result = null;
			String emailtype = model.get("emailtype");
			
			if("IMMEDIATELY".equals(emailtype)){
				try {
					result = SendEmailUtil.sendSimpleMessage(notice.getEmailAddress(), notice.getEmailTitle(), notice.getEmailContent(), notice.getFileAddress());
				} catch (Exception e) {
					logger.error("邮件发送任务执行失败。", e);
					result = "发送失败，发送遇到异常：" + e.getMessage();
				}
				notice.setEmailType("IMMEDIATELY");
				notice.setSendResult(result);
				notice.setSendTime(sdf.format(new Date()));
				notice.setSendFlag("1");
			}
			this.baseService.saveEntity(notice);
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
	@RequestMapping(value = "/sendEmailNow.acl")
	@ResponseBody
	public JsonReturnResult sendEmailNow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			String [] ids = model.get("ids").split(",");
			for(String id : ids){
				EmailNotice notice = (EmailNotice) this.tableService.findEntityByID(EmailNotice.class, id);
				if(notice != null){
					if("0".equals(notice.getSendFlag())){
						String result = null;
						try {
							result = SendEmailUtil.sendSimpleMessage(notice.getEmailAddress(), notice.getEmailTitle(), notice.getEmailContent(), notice.getFileAddress());
						} catch (Exception e) {
							logger.error("邮件发送任务执行失败。", e);
							result = "发送失败，发送遇到异常：" + e.getMessage();
						}
						notice.setEmailType("IMMEDIATELY");
						notice.setSendResult(result);
						notice.setSendTime(sdf.format(new Date()));
						notice.setSendFlag("1");
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
	@RequestMapping(value = "/sendEmailAgain.acl")
	@ResponseBody
	public JsonReturnResult sendEmailAgain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			String [] ids = model.get("ids").split(",");
			for(String id : ids){
				EmailNotice notice = (EmailNotice) this.tableService.findEntityByID(EmailNotice.class, id);
				if(notice != null){
					String result = null;
					try {
						result = SendEmailUtil.sendSimpleMessage(notice.getEmailAddress(), notice.getEmailTitle(), notice.getEmailContent(), notice.getFileAddress());
					} catch (Exception e) {
						logger.error("邮件发送任务执行失败。", e);
						result = "发送失败，发送遇到异常：" + e.getMessage();
					}
					if("否".equals(result)){
						notice.setSendFlag("0");
					}else{
						notice.setSendResult(result);
						notice.setSendTime(sdf.format(new Date()));
						notice.setSendFlag("1");
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
	@RequestMapping(value = "/updateEmailTask.acl")
	@ResponseBody
	public JsonReturnResult updateEmailTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			EmailNotice notice = (EmailNotice) this.tableService.findEntityByID(EmailNotice.class, model.get("id"));
			String result = null;
			if(notice != null){
				String noticeTime = model.get("noticetime");
				String emailContent = model.get("emailcontent");
				String emailAddress = model.get("emailaddress");
				String emailTitle = model.get("emailtitle");
				notice.setNoticeTime(noticeTime);
				notice.setEmailContent(emailContent);
				notice.setEmailAddress(emailAddress);
				notice.setEmailTitle(emailTitle);
				String emailtype = model.get("emailtype");
				if("IMMEDIATELY".equals(emailtype) && "0".equals(notice.getSendFlag())){
					try {
						result = SendEmailUtil.sendSimpleMessage(notice.getEmailAddress(), notice.getEmailTitle(), notice.getEmailContent(), notice.getFileAddress());
					} catch (Exception e) {
						logger.error("邮件发送任务执行失败。", e);
						result = "发送失败，发送遇到异常：" + e.getMessage();
					}
					notice.setEmailType("IMMEDIATELY");
					notice.setSendResult(result);
					notice.setSendTime(sdf.format(new Date()));
					notice.setSendFlag("1");
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
	@RequestMapping(value = "/removeEmailTask.acl")
	@ResponseBody
	public JsonReturnResult removeEmailTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			String id = model.get("id");
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("id", id);
			EmailNotice notice = (EmailNotice) this.baseService.getNewOrUpdateObject(EmailNotice.class, propertiesMap);
			if(notice != null){
				notice.setSendFlag("2");
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
}
