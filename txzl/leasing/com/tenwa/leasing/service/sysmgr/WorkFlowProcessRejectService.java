
 /**
 * 项目名称：    系统名称
 * 包名：              com.business.service.cust
 * 文件名：         CustService.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-18-下午12:28:40
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.leasing.service.sysmgr;

import java.util.Map;



 /**
 * 类名称：     CustService
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-5-18 下午12:28:40
 * 修改备注：
 * @version 1.0.0
 **/

public interface WorkFlowProcessRejectService {
	public void addOrRemoveReject(Map<String,String> model) throws Exception;
}
