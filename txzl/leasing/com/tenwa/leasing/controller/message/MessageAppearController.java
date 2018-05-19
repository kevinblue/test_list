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
* 类名称：MessageAppearController   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月7日 下午1:41:25   
* @version        
*/
@Controller(value = "messageAppearController")
@RequestMapping(value = "/**/acl")
public class MessageAppearController {
	private Logger logger=Logger.getLogger(MessageAppearController.class);
	@Resource(name="messageService")
	private MessageService messageService;
	// #################
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMessageAppear.acl")
	@ResponseBody
	public JsonReturnResult addMessageAppear(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			messageService.addMyMessaage(request, response);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
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
	@RequestMapping(value = "/updateMessageAppear.acl")
	@ResponseBody
	public JsonReturnResult updateMessageAppear(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		try {
			messageService.updateMyMessage(request, response);
			if(logger.isInfoEnabled()){
				 logger.info("提交成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
			returnResult.setContent(e.getMessage());
		}
		return returnResult;
	}
}
