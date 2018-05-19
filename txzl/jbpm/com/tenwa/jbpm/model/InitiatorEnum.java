
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         InitiatorEnum.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-6-11-上午09:17:17
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.jbpm.model;


 /**
 * 类名称：     InitiatorEnum
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-6-11 上午09:17:17
 * 修改备注：
 * @version 1.0.0
 **/

public enum InitiatorEnum 
{
	REQUESTINITIATOR("requestInitiator","流程发起人"),
	USER("user","指定人员"), 
	REQUESTINITIATORRELATION("requestInitiatorRelation","发起人关系"), 
	SUBMITRELATION("submitRelation","提交人关系"), 
	REQUESTINITIATORDEFINEDRELATION("requestInitiatorDefinedRelation","预定义的发起人关系"),
	SUBMITDEFINEDRELATION("submitDefinedRelation","预定义的提交人关系"), 
	DEPT("dept","部门"), 
	DEPTROLE("deptRole","角色"), 
	GROUP("group","群组"), 
	STEP("step","历史步骤"), 
	FORMFIELD("formField","表单域"), 
	SQL("sql","自定义");  
   private final String value ;
   private final String chineseName ;
   private InitiatorEnum(String value,String chineseName){
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
