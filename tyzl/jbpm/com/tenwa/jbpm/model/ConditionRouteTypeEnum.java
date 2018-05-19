package com.tenwa.jbpm.model;

public enum ConditionRouteTypeEnum {
	   PAGECALLBACK("pageCallBack","页面回调函数"),//默认 workflowNextRouteCallBack
	   FIELD("field","表单域"),
	   EXPRESSION("expression","表达式"),
	   SQL("sql","自定义")
	   ;
	
	   private final String value ;
	   private final String chineseName ;
	   
	   private ConditionRouteTypeEnum(String value,String chineseName){
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
