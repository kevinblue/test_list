
 /**
 * 项目名称：    系统名称
 * 包名：              org.springframework.web.multipart.commons
 * 文件名：         PJCommonsMultipartResolver.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-13-下午04:57:14
 * Copyright：2013XX公司-版权所有
 **/

package org.springframework.web.multipart.commons;


 /**
 * 类名称：     PJCommonsMultipartResolver
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-13 下午04:57:14
 * 修改备注：
 * @version 1.0.0
 **/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
  
/** 
 * 重写CommonsMultipartResolver以监听文件上传进度 
 * @author Van 
 * @date 2012-12-12 
 */  
public class PJCommonsMultipartResolver extends CommonsMultipartResolver {  
      
    private HttpServletRequest request;  
    protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {  
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);  
        upload.setSizeMax(-1);  
        if (request != null) {  
            PJProgressListener uploadProgressListener = new PJProgressListener(request);  
            upload.setProgressListener(uploadProgressListener);  
        }  
        return upload;  
    }  
    public MultipartHttpServletRequest resolveMultipart(  
            HttpServletRequest request) throws MultipartException {  
        this.request = request;// 获取到request,要用到session  
        return super.resolveMultipart(request);  
    }  
      
      
    @SuppressWarnings("unchecked")  
    @Override  
    public MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {  
          
          
        String encoding = "utf-8";  
        FileUpload fileUpload = prepareFileUpload(encoding);  
         
        PJProgressListener uploadProgressListener = new PJProgressListener(request);  
        fileUpload.setProgressListener(uploadProgressListener);  
        try {  
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);  
            return parseFileItems(fileItems, encoding);  
        }  
        catch (FileUploadBase.SizeLimitExceededException ex) {  
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);  
        }  
        catch (FileUploadException ex) {  
            throw new MultipartException("Could not parse multipart servlet request", ex);  
        }  
    }  
  
}  

