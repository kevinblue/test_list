
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model.file
 * 文件名：         FtpListener.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-29-下午04:49:45
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.model.file;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;

import com.tenwa.business.model.FileProcess;


/** 
* FTP监听器,做了简单实现，可以使用commons logger替换System.out.println 
* 
* 
*/ 
public class FtpListener implements FTPDataTransferListener { 
        private FTPOptType optType; 
        private FileProcess fileProcess;

        public static FtpListener instance(FTPOptType optType,FileProcess fileProcess) { 
           return new FtpListener(optType,fileProcess); 
        } 

        private FtpListener(FTPOptType optType,FileProcess fileProcess) { 
           this.optType = optType; 
           this.fileProcess = fileProcess;
        } 

        public void started() { 
           //System.out.println(optType.getOptname() + "：FTP启动。。。。。。"); 
        } 

        public void transferred(int length) { 
          // System.out.println(optType.getOptname() + "：FTP传输。。。。。。"); 
        	if(null != fileProcess){
        		fileProcess.setUploadedSize(fileProcess.getUploadedSize()+length);
        		System.out.println(fileProcess.getUploadedSize()); 
        	}
        	
        } 

        public void completed() { 
           //System.out.println(optType.getOptname() + "：FTP完成。。。。。。"); 
        } 

        public void aborted() { 
           //System.out.println(optType.getOptname() + "：FTP中止。。。。。。"); 
        } 

        public void failed() { 
           //System.out.println(optType.getOptname() + "：FTP挂掉。。。。。。"); 
        } 
}
