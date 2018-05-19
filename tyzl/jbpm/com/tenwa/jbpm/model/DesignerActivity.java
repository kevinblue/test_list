
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         DesignerActivityInfo.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-5-下午05:26:37
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.model;

import java.util.ArrayList;
import java.util.List;


 /**
 * 类名称：     DesignerActivityInfo
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-5 下午05:26:37
 * 修改备注：
 * @version 1.0.0
 **/

public class DesignerActivity 
{
   private String jsonObjIdentifier ;
   private String type;
   private String name;
   private String key;
   private Integer position;

   private DesingerActivityHtmlProperty htmlProperty ;
   
   private List<DesignerTransition> transitions = new ArrayList<DesignerTransition>();
   
   public DesignerActivity(String jsonObjIdentifier,String type, String name,String key ,Integer position,DesingerActivityHtmlProperty htmlProperty) 
   {
	   this.setJsonObjIdentifier(jsonObjIdentifier);
		this.type = type;
		this.name = name;
		this.key  =key;
		this.position = position;
		this.htmlProperty = htmlProperty;
   }
   public DesignerActivity(String type, String name,String key ,Integer position){
		this.type = type;
		this.name = name;
		this.key  =key;
		this.position = position;
   }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DesingerActivityHtmlProperty getHtmlProperty() {
		return htmlProperty;
	}
	public void setHtmlProperty(DesingerActivityHtmlProperty htmlProperty) {
		this.htmlProperty = htmlProperty;
	}
	public String getKey() {
		return key;
	}
	public Integer getPosition() {
		return position;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	
	public void setTransitions(List<DesignerTransition> transitions) {
		this.transitions = transitions;
	}
	
	public List<DesignerTransition> getTransitions() {
		return transitions;
	}
	public void setJsonObjIdentifier(String jsonObjIdentifier) {
		this.jsonObjIdentifier = jsonObjIdentifier;
	}
	public String getJsonObjIdentifier() {
		return jsonObjIdentifier;
	}
}
