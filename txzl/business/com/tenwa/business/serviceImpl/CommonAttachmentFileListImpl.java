
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.serviceImpl
 * 文件名：         CommonAttachmentFileDictListImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-11-5-下午05:01:57
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.AttachmentFileDao;
import com.tenwa.business.entity.DictionaryData;


 /**
 * 类名称：     CommonAttachmentFileDictListImpl
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-11-5 下午05:01:57
 * 修改备注：
 * @version 1.0.0
 **/
@Service(value="commonAttachmentFileDictList")
public class CommonAttachmentFileListImpl extends AbstractAttachmentFileList{
    @Resource(name="attachmentFileDao")
	private AttachmentFileDao attachmentFileDao;
    
	@Override
	public List<DictionaryData> getAttachmentFileDictList(
			String attachmentType, Map<String, String> identifiersMap,
			Map<String, String> modelData) throws Exception {
		return this.attachmentFileDao.getAttachmentFileDictDatasListByAttachmentType(attachmentType);
	}

}
