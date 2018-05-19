
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         DesingerActivityHtmlProperty.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-5-下午05:13:52
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.model;


 /**
 * 类名称：     DesingerActivityHtmlProperty
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-5 下午05:13:52
 * 修改备注：
 * @version 1.0.0
 **/

public class DesingerActivityHtmlProperty 
{
    private int x;
    private int y;
    private int width;
    private int height;
    
	public DesingerActivityHtmlProperty(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
    
}
