
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model.file
 * 文件名：         LocalFileOperationImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-29-下午03:57:52
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.model.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.tenwa.business.model.FileProcess;


 /**
 * 类名称：     LocalFileOperationImpl
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-29 下午03:57:52
 * 修改备注：
 * @version 1.0.0
 **/

public class LocalFileOperationImpl  extends AbstractBaseFileOperation<File>  
{

	@Override
	public void uploadFile(String  uploadedFileFullPath,InputStream in,FileProcess fileProcess)
			throws Exception {
		createNotExistDirs(uploadedFileFullPath);  
		uploadOrDownload(in,new FileOutputStream(uploadedFileFullPath), fileProcess);
	}
	@Override
	public void downloadFile(String downloadedFileFullPath,OutputStream out,FileProcess fileProcess)
	throws Exception {
		createNotExistDirs(downloadedFileFullPath);
		uploadOrDownload(new FileInputStream(downloadedFileFullPath),out, fileProcess);
	}
	@Override
	public File createNotExistDirs(String writedFileFullPath) throws Exception {
		   File file = new File(writedFileFullPath);
		   File parentFile = file.getParentFile();
		   if(null != parentFile){
			   if(!parentFile.exists()){
				   parentFile.mkdirs();
			   }
		   }
		   return file;
	}
	
	@Override
	public void removeFile(String uploadedFileFullPath) throws Exception {
         new File(uploadedFileFullPath).delete();
	}
	
	 /**
	 * (non-Javadoc)
	 * @see com.tenwa.business.model.file.BaseFileOperation#readInputStreamToOutputStream(java.lang.String, java.io.OutputStream)
	 **/
	
	@Override
	public void readInputStreamToOutputStream(String inputFileFullPath,
			OutputStream out) throws Exception {
		
		FileInputStream in = new FileInputStream(inputFileFullPath);
		try {
			int c;
			byte[] by = new byte[BUFFEREDSIZE];
			while ((c = in.read(by)) != -1) {
				out.write(by, 0, c);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			in.close();
		}
	}

}
