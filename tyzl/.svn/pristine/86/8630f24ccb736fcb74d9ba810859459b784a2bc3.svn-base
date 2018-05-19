
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.dao
 * 文件名：         AttachmentFileDao.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-9-上午11:35:14
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.business.dao;

import java.util.List;

import com.tenwa.business.entity.Dictionary;
import com.tenwa.business.entity.DictionaryData;





 /**
 * 类名称：     AttachmentFileDao
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-9 上午11:35:14
 * 修改备注：
 * @version 1.0.0
 **/
public interface AttachmentFileDao  extends BaseDao
{
	public Object getEntityObjectById(Class<? extends Object> entityClass,String id) throws Exception;
	public String saveEntityObject(Object  entityObject) throws Exception;
	public void removeEntityObject(Object  entityObject) throws Exception;
	//public List<Object[]> getAttachmentFileInfosListByAttachmentType(String attachmentType, Map<String,String> identifiersMap) throws Exception ;
	//public List<AttachmentFileUploadInfo> getAttachmentFileInfosListByIdentifierOne(Map<String,String> identifiersMap) throws Exception ;
	public List<DictionaryData> getAttachmentFileDictDatasListByAttachmentType(String attachmentType) throws Exception;
	public List<Dictionary> getAttachmentFileDictsListByAttachmentType(String attachmentType) throws Exception;
}
