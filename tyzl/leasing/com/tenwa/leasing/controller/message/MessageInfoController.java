package com.tenwa.leasing.controller.message;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.leasing.service.message.MessageService;
/**   
*    
* 项目名称：tls5.1   
* 类名称：MessageInfoController   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月7日 下午1:42:08   
* @version        
*/
@Controller(value = "messageInfoController")
@RequestMapping(value = "/**/acl")
public class MessageInfoController {
	private Logger logger=Logger.getLogger(MessageInfoController.class);
	@Resource(name="messageService")
	private MessageService messageService;
	// #################
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMessageInfo.acl")
	public String addMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			messageService.addMessaage(request, response);
			if(logger.isInfoEnabled()){
				 logger.info("新增成功!");
			 }
		}catch (Exception e) {
			e.printStackTrace();
			return "新增失败!["+e.getMessage()+"]";
		}
		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMessageInfo.acl")
	public String updateMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			messageService.updateMessage(request, response);
			if(logger.isInfoEnabled()){
				 logger.info("修改成功!");
			 }
		}catch (Exception e) {
			return "修改失败!["+e.getMessage()+"]";
		}
		return null;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMessageInfo.acl")
	public String removeMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
			messageService.removeMessage(request, response);
			if(logger.isInfoEnabled()){
				logger.info("删除成功!");
			}
		}catch (Exception e) {
			return "删除失败!["+e.getMessage()+"]";
		}
		return null;
	}
}
