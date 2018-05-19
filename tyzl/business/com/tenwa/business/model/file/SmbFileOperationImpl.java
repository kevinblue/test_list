
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model.file
 * 文件名：         LocalFileOperationImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-29-下午03:57:52
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.model.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

import com.tenwa.business.model.FileProcess;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.FileUtil;


 /**
 * 类名称：     LocalFileOperationImpl
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-29 下午03:57:52
 * 修改备注：
 * @version 1.0.0
 **/

public class SmbFileOperationImpl  extends AbstractBaseFileOperation<SmbFile>  {
    //smb://administrator:123456@172.16.10.136/smb
	private final String host;
	private final String username;
	private final String password ;
	
	public SmbFileOperationImpl(String host, String username,String password)
	{
		super();
		this.host = host;
		this.username = username;
		this.password = password;
	}
	private String getSmbConnectionStr(){
		return "smb://"+this.username+":"+this.password+"@"+this.host;
	}
	@Override
	public void uploadFile(String  uploadedFileFullPath,InputStream in,FileProcess fileProcess)
			throws Exception {
		uploadedFileFullPath = this.getSmbConnectionStr() + uploadedFileFullPath;
		createNotExistDirs(uploadedFileFullPath);
		SmbFileOutputStream fi=null;
		try {
			fi=new SmbFileOutputStream(uploadedFileFullPath);
			uploadOrDownload(in,fi, fileProcess);
		} catch (Exception e) {
		   throw new BusinessException(e.getMessage());
		}finally{
			if(null!=fi){FileUtil.safeCloseOutStream(fi);}
		}
	}

	@Override
	public void downloadFile(String downloadedFileFullPath,OutputStream out,FileProcess fileProcess)
			throws Exception {
		downloadedFileFullPath = this.getSmbConnectionStr()+downloadedFileFullPath;
		createNotExistDirs(downloadedFileFullPath); 
		SmbFileInputStream si=null;
		try {
			si=new SmbFileInputStream(downloadedFileFullPath);
			uploadOrDownload(si,out, fileProcess);
		} catch (Exception e) {
			  throw new BusinessException(e.getMessage());
		}finally{
			if(null!=si){FileUtil.safeCloseInputStream(si);}
		}
	}

	@Override
	public SmbFile createNotExistDirs(String writedFileFullPath)
			throws Exception {
		SmbFile file = new SmbFile(writedFileFullPath);
		String parentFilePath = file.getParent();
		if(null != parentFilePath){
			SmbFile parentFile = new SmbFile(parentFilePath);
			if(null != parentFile){
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
			}
		}
		return file;
	}
	@Override
	public void removeFile(String uploadedFileFullPath) throws Exception {
		uploadedFileFullPath = this.getSmbConnectionStr()+uploadedFileFullPath;
		new SmbFile(uploadedFileFullPath).delete();
	}
	@Override
	public void readInputStreamToOutputStream(String inputFileFullPath,
			OutputStream out) throws Exception {
		inputFileFullPath = this.getSmbConnectionStr()+inputFileFullPath;
		InputStream in = new SmbFileInputStream(inputFileFullPath);
		try {
			int c;
			byte[] by = new byte[BUFFEREDSIZE];
			while ((c = in.read(by)) != -1) {
				out.write(by, 0, c);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(null!=in){FileUtil.safeCloseInputStream(in);};
		}
	}
}
