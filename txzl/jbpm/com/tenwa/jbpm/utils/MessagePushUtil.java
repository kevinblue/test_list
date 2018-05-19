package com.tenwa.jbpm.utils;

import java.util.List;

import com.tenwa.business.entity.User;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.utils.ResourceUtil;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.TargetedMessage;


public class MessagePushUtil {
	
	public static void  sendMessageToApp(JBPMWorkflowHistoryInfo jwi,User u) {
		try {
		String isPush = ResourceUtil.getConfigValue("IS_PUSH");
		//是否推送
		if("true".equals(isPush)){
			if(u.getLastLoginRegId()!=null&&!u.getLastLoginRegId().equals("")){
				if("android".equals(u.getDevType())){
					Constants.useOfficial();
					String appSecret=ResourceUtil.getConfigValue("APP_SECRET_KEY");
					Sender sender = new Sender(appSecret);
					String MY_PACKAGE_NAME = ResourceUtil.getConfigValue("MY_PACKAGE_NAME");
					String messagePayload = "This is a message";
					String title = jwi.getWorkflowDisplayName();
					String description =jwi.getKeyTwo();
					if(title!=null&&description!=null){
						Message message = new Message.Builder()
						.title(title)
						.description(description).payload(messagePayload)
						.restrictedPackageName(MY_PACKAGE_NAME)
						.notifyType(1)     // 使用默认提示音提示
						.extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_LAUNCHER_ACTIVITY)
						.build();
						Result send = sender.send(message, u.getLastLoginRegId(), 10); //根据regID，发送消息到指定设备上，不重试。
						System.out.println(send.getData().toString());  
					}
				}else{
					Constants.useOfficial();
					String appSecret=ResourceUtil.getConfigValue("APP_SECRET_KEY_IOS");
					Sender sender = new Sender(appSecret);
					String title = jwi.getWorkflowDisplayName();
					String description =jwi.getKeyTwo();
					if(title!=null&&description!=null){
						  Message message = new Message.IOSBuilder()
				             .description(description)
				             .soundURL("default")    // 消息铃声
				             .badge(1)               // 数字角标
				             .category("action")     // 快速回复类别
				             .extra(Constants.EXTRA_PARAM_NOTIFY_EFFECT, Constants.NOTIFY_LAUNCHER_ACTIVITY)  // 自定义键值对
				             .extra("flow_control", "4000")   // 设置平滑推送, 推送速度4000每秒(qps=4000)
				             .build();
						Result send = sender.send(message, u.getLastLoginRegId(), 10); //根据regID，发送消息到指定设备上，不重试。
						System.out.println(send.getData().toString());  
				}
			}
		  }
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMessagesToApp(List<TargetedMessage> messages){
		try {
			Constants.useOfficial();
			String appSecret=ResourceUtil.getConfigValue("APP_SECRET_KEY");
			Sender sender = new Sender(appSecret);
			Result send=sender.send(messages, 10);
			System.out.println(send.getData().toString());  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			JBPMWorkflowHistoryInfo jwi =new JBPMWorkflowHistoryInfo();
			jwi.setKeyTwo("项目审批流程");
			jwi.setWorkflowDisplayName("项目审批");
			User u=new User();
			u.setLastLoginRegId("KGeqEFV2qYcCpVrWMBR92uZXAsVE1l/tJCPpW5os5Xo=");
			MessagePushUtil.sendMessageToApp(jwi, u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}