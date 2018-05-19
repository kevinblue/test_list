
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.service
 * 文件名：         AttachmentFileService.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-9-上午11:36:07
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.business.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;




 /**
 * 类名称：     AttachmentFileService
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-9 上午11:36:07
 * 修改备注：
 * @version 1.0.0
 **/

public interface AttachmentFileService 
{
	public String getAttachmentUploadFileSize(MultipartFile multipartFile,Map<String, String> modelData) throws Exception ;
    public String getAttachmentFileDetailsJsonString(Map<String,String> modelData) throws Exception;
    public String uploadAttachmentFile(HttpServletRequest request,MultipartFile multipartFile,Map<String,String> modelData) throws Exception;
    public String uploadCustFile(HttpServletRequest request,MultipartFile multipartFile,Map<String,String> modelData) throws Exception;
    public String uploadAttachmentFiles(HttpServletRequest request, HttpServletResponse response) throws Exception;
    public String removeAttachmentFile(String uploadAttachmentFileDetailId) throws Exception;
    public void downloadAttachmentFile(HttpServletResponse response,String uploadAttachmentFileDetailId,String browserType) throws Exception;
    public void downloadBatchAttachmentFiles(HttpServletResponse response,String fileTitleName,String attchmentDetailBatchDownloadIdStr,String browserType)  throws Exception;
    //在线编辑文件
    public String updateOnlineAttachmentFile(MultipartFile multipartFile,Map<String,String> modelData) throws Exception;
}
