package com.tenwa.leasing.controller.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.entity.BaseMessage;
import com.tenwa.business.entity.BaseMessageToUser;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;

/**   
*    
* 项目名称：tls5.1   
* 类名称：MyNoticeInfoController   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月7日 下午4:23:36   
* @version        
*/
@Controller(value="myNoticeInfoController")
@RequestMapping(value="/**/leasing")
public class MyNoticeInfoController{
	   @Resource(name = "tableService")
	   private TableService tableService;
	   
	   /**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/myNoticeInfo.action")
	   @ResponseBody
	   public String myNoticeInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);//把请求数据转成map
		   //获取ajax参数
		   String message_id = modelData.get("message_id");
		   String jsonResult = "";
		   Map<String,Object> returnMap=new HashMap<String, Object>();
		   ObjectMapper mapper = new ObjectMapper();
		   BaseMessage baseMessage = (BaseMessage) this.tableService.findEntityByID(BaseMessage.class, message_id);
		   returnMap.put("msgtitle", baseMessage.getMsgTitle());
		   returnMap.put("senddate", baseMessage.getSendDate());
		   returnMap.put("fromuser", baseMessage.getFromUser().getRealname());
		   returnMap.put("msgtext", baseMessage.getMsgText());
		   jsonResult = mapper.writeValueAsString(returnMap);
		   return jsonResult;
	   }
	
	   /**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/markAsRead.action")
	   @ResponseBody
	   public String markAsRead(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);//把请求数据转成map
		   //获取ajax参数
		   String message_bid = modelData.get("message_bid");
		   String user_id = modelData.get("user_id");
		   String read_status = modelData.get("read_status");
		   String jsonResult = "";
		   Map<String,Object> returnMap=new HashMap<String, Object>();
		   ObjectMapper mapper = new ObjectMapper();
		   BaseMessageToUser baseMessageToUser = (BaseMessageToUser) this.tableService.findEntityByID(BaseMessageToUser.class, message_bid);
		   baseMessageToUser.setReadStatus(read_status);
		   this.tableService.updateEntity(baseMessageToUser);
		   List list = this.tableService.findResultsByHSQL("from BaseMessageToUser bmtu where bmtu.readUser.id=? and readStatus=?", new String[]{user_id,"1"});
		   returnMap.put("readnum", list != null ? list.size() : 0);
		   returnMap.put("readstatus", baseMessageToUser.getReadStatus());
		   jsonResult = mapper.writeValueAsString(returnMap);
		   return jsonResult.toLowerCase();
	   }
	   /**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/noMarkEmailNum.action")
	   @ResponseBody
	   public String noMarkEmailNum(HttpServletRequest request,HttpServletResponse response) throws Exception{
		   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);//把请求数据转成map
		   //获取ajax参数
		   String user_id = modelData.get("user_id");
		   String jsonResult = "";
		   Map<String,Object> returnMap=new HashMap<String, Object>();
		   ObjectMapper mapper = new ObjectMapper();
		   List list = this.tableService.findResultsByHSQL("from BaseMessageToUser bmtu where bmtu.readUser.id=? and readStatus=?", new String[]{user_id,"1"});
		   returnMap.put("emailnum", list != null ? list.size() : 0);
		   jsonResult = mapper.writeValueAsString(returnMap);
		   return jsonResult.toLowerCase();
	   }
}
