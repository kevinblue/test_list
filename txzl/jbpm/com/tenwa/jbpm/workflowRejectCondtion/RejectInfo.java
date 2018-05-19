
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         RejectInfo.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-10-21-下午02:32:37
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.jbpm.workflowRejectCondtion;


 /**
 * 类名称：     RejectInfo
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-10-21 下午02:32:37
 * 修改备注：
 * @version 1.0.0
 **/

public interface  RejectInfo {
   public  boolean isRejected() throws Exception;
   public  String  returnedRejectInfo() throws Exception;
}
