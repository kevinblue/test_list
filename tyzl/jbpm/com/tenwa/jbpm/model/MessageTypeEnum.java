package com.tenwa.jbpm.model;

public enum MessageTypeEnum {
	   SMS("sms","短信"),//默认 workflowNextRouteCallBack
	   MAIL("mail","邮件"),
	   STATIONMESSAGE("stationMessage","站内信")
	   ;
	
	   private final String value ;
	   private final String chineseName ;
	   
	   private MessageTypeEnum(String value,String chineseName){
		   this.value = value;
		   this.chineseName = chineseName;
	   }
		public String getValue() {
		  return value;
		}
		public String getChineseName() {
			return chineseName;
		}
}
