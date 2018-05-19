
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         DesignerTransition.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-5-下午05:33:11
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.model;

import java.util.ArrayList;
import java.util.List;


 /**
 * 类名称：     DesignerTransition
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-5 下午05:33:11
 * 修改备注：
 * @version 1.0.0
 **/

public class DesignerTransition 
{
   private String name;
   private String from;
   private String to;
   private DesignerDot   textPosition; 
   private List<DesignerDot> dots = new ArrayList<DesignerDot>();
   
   public DesignerTransition(String name, String from, String to,DesignerDot   textPosition) {
		this.name = name;
		this.from = from;
		this.to = to;
		this.setTextPosition(textPosition);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}

	public List<DesignerDot> getDots() {
		return dots;
	}

	public void setDots(List<DesignerDot> dots) {
		this.dots = dots;
	}

	
	 /**
	 * @param textPosition the textPosition to set
	 **/
	
	public void setTextPosition(DesignerDot textPosition) {
		this.textPosition = textPosition;
	}

	
	 /**
	 * textPosition
	 * @return the textPosition
	 * @since 1.0.0
	 **/
	
	public DesignerDot getTextPosition() {
		return textPosition;
	}
   
   
}
