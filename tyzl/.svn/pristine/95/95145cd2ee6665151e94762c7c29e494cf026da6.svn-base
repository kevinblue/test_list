
 /**
 * 项目名称：    系统名称
 * 包名：              org.springframework.web.multipart.commons
 * 文件名：         PJProgressListener.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-13-下午04:58:51
 * Copyright：2013XX公司-版权所有
 **/

package org.springframework.web.multipart.commons;


 /**
 * 类名称：     PJProgressListener
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-13 下午04:58:51
 * 修改备注：
 * @version 1.0.0
 **/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import com.tenwa.business.model.FileProcess;
  
public class PJProgressListener implements ProgressListener{  
    private HttpServletRequest request;  
    public PJProgressListener() {  
    }  
    public PJProgressListener(HttpServletRequest _request) {  
        this.request = _request;  
        FileProcess fileProcess = new FileProcess();
        HttpSession session = request.getSession();
        String attachmentFileProcessKey = "attachmentFileProcessKey";
        
        session.setAttribute(attachmentFileProcessKey, fileProcess);  
        
    }  
    public void update(long pBytesRead, long pContentLength, int pItems) {  
    	FileProcess fileProcess = (FileProcess) request.getSession().getAttribute("attachmentFileProcessKey");  
    	if(null != fileProcess){
        	fileProcess.setTotalSize(pContentLength);
        	fileProcess.setUploadedSize(pBytesRead);
    	}
    	//System.out.println("############pBytesRead"+pBytesRead+";pContentLength="+pContentLength+";pItems="+pItems);
    }  
  
}  

