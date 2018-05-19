
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
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.leasing.utils.StrTools;


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
	public void uploadFile(String  uploadedFileFullPath,InputStream in,FileProcess fileProcess)throws Exception {
		createNotExistDirs(uploadedFileFullPath);  
		FileOutputStream fi=null;
		try {
			fi=new FileOutputStream(StrTools.fileNameCheck(uploadedFileFullPath));
			uploadOrDownload(in,fi, fileProcess);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}finally{
			if(fi!=null){FileUtil.safeCloseOutStream(fi);}
		}
	}
	@Override
	public void downloadFile(String downloadedFileFullPath,OutputStream out,FileProcess fileProcess)
	throws Exception {
		createNotExistDirs(downloadedFileFullPath);
		FileInputStream fi=null;
		try {
			fi=new FileInputStream(StrTools.fileNameCheck(downloadedFileFullPath));
			uploadOrDownload(fi,out, fileProcess);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}finally{
			if(null!=fi){FileUtil.safeCloseInputStream(fi);}
		}
	}
	@Override
	public File createNotExistDirs(String writedFileFullPath) throws Exception {
		   File file = new File(StrTools.fileNameCheck(writedFileFullPath));
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
         new File(StrTools.fileNameCheck(uploadedFileFullPath)).delete();
	}
	
	 /**
	 * (non-Javadoc)
	 * @see com.tenwa.business.model.file.BaseFileOperation#readInputStreamToOutputStream(java.lang.String, java.io.OutputStream)
	 **/
	
	@Override
	public void readInputStreamToOutputStream(String inputFileFullPath,
			OutputStream out) throws Exception {
		
		FileInputStream in = new FileInputStream(StrTools.fileNameCheck(inputFileFullPath));
		try {
			int c;
			byte[] by = new byte[BUFFEREDSIZE];
			while ((c = in.read(by)) != -1) {
				out.write(by, 0, c);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(null!=in){FileUtil.safeCloseInputStream(in);}
		}
	}

}
