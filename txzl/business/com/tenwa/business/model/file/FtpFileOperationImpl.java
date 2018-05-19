
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model.file
 * 文件名：         LocalFileOperationImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-29-下午03:57:52
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.model.file;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;

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

public class FtpFileOperationImpl extends AbstractBaseFileOperation<FTPFile> {
    
	private final String host;
	private final int port;
	private final String username;
	private final String password ;
	private final String charset ;
	private FTPClient client ;
	
	public FtpFileOperationImpl(String host, int port, String username,String password,String charset)
	{
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.charset  = charset;
	}

	@Override
	public void downloadFile(String downloadedFileFullPath,OutputStream out,FileProcess fileProcess)
			throws Exception {
		try{
			this.client = makeFtpConnection();
			FtpListener listener = FtpListener.instance(FTPOptType.DOWN,fileProcess);
			downloadedFileFullPath = PathToolkit.formatPath4FTP(downloadedFileFullPath);
			int lastSeparatorIndex = downloadedFileFullPath.lastIndexOf("/");
			String parentDir =downloadedFileFullPath.substring(0,lastSeparatorIndex);
			String fileName  =  downloadedFileFullPath.substring(lastSeparatorIndex+1,downloadedFileFullPath.length());
			this.client.changeDirectory(parentDir);
			this.client.download(fileName, out, -1, listener);
		}catch(Exception e){
			throw e;
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
			this.closeConnection();
		}
	}

	@Override
	public void uploadFile(String uploadedFileFullPath ,InputStream in,FileProcess fileProcess)
			throws Exception {
		try{
			fileProcess.setUploadedFinish(true);
			fileProcess.setTotalSize(in.available());
			fileProcess.setUploadedSize(0);
			
			this.client = makeFtpConnection();
			createNotExistDirs(uploadedFileFullPath);
			uploadedFileFullPath = PathToolkit.formatPath4FTP(uploadedFileFullPath);
			int lastSeparatorIndex = uploadedFileFullPath.lastIndexOf("/");
	        String fileName  =  uploadedFileFullPath.substring(lastSeparatorIndex+1,uploadedFileFullPath.length());
	        FtpListener listener = FtpListener.instance(FTPOptType.UP,fileProcess);
	        this.client.upload(fileName, in, -1,0, listener);
		}catch(Exception e){
			throw e;
		}finally{
			if(null != in){
				in.close();
			}
			this.closeConnection();
		}

	}
	@Override
    public  FTPFile createNotExistDirs(String writedFileFullPath) throws Exception
    { 
		writedFileFullPath = PathToolkit.formatPath4FTP(writedFileFullPath); 
		int lastSeparatorIndex = writedFileFullPath.lastIndexOf("/");
		String parentDir =writedFileFullPath.substring(0,lastSeparatorIndex);
        FTPFile ftpFile = null;
        try{ 
        	this.client.changeDirectory(parentDir);
        } catch (Exception e) { 
        	recursionCreateFtpDirs(parentDir);
        }
        return ftpFile;
    } 
	public void recursionCreateFtpDirs(String parentDir) throws Exception{
		this.client.changeDirectory("/");
		String currentDir = "";
		String[] dirs = parentDir.split("/");
		for(int i=0;i < dirs.length;i++){
			String dir = dirs[i];
			if(StringUtils.isBlank(dir))continue;
			try{
				this.client.changeDirectory(currentDir+"/"+dir);
			}catch(Exception e){
				this.client.createDirectory(dir);
				this.client.changeDirectory(currentDir+"/"+dir);
				currentDir+=("/"+dir);
			}
		}
		this.client.changeDirectory(currentDir);
	}
    public  FTPClient makeFtpConnection() { 
            FTPClient client = new FTPClient();
            try { 
                    client.connect(host, port); 
                    client.login(username, password); 
                    client.setCharset(charset);
                    client.setType(FTPClient.TYPE_BINARY);
            } catch (Exception e) { 
                    throw new FTPRuntimeException(e); 
            } 
            return client; 
    } 
    public  boolean closeConnection() throws Exception
    { 
        if (client == null) return true; 
        if (client.isConnected()) { 
                try { 
                        client.disconnect(true); 
                        return true; 
                } catch (Exception e) { 
                        try { 
                                client.disconnect(false); 
                        } catch (Exception e1) { 
                                e1.printStackTrace(); 
                                return false; 
                        } 
                } 
        } 
        this.client = null;
        return true; 
    }
	
	@Override
	public void removeFile(String uploadedFileFullPath) throws Exception {
		try{
			this.client = makeFtpConnection();
			this.client.deleteFile(uploadedFileFullPath);
		}catch (Exception e) {
			throw e;
		}finally{
			this.closeConnection();
		}
	}

	
	 /**
	 * (non-Javadoc)
	 * @see com.tenwa.business.model.file.BaseFileOperation#readInputStreamToOutputStream(java.lang.String, java.io.OutputStream)
	 **/
	
	@Override
	public void readInputStreamToOutputStream(String inputFileFullPath,
			OutputStream out) throws Exception {
		try {
			this.client = makeFtpConnection();
			FtpListener listener = FtpListener.instance(FTPOptType.DOWN,null);
			inputFileFullPath = PathToolkit.formatPath4FTP(inputFileFullPath); 
			int lastSeparatorIndex = inputFileFullPath.lastIndexOf("/");
			String parentDir =inputFileFullPath.substring(0,lastSeparatorIndex);
			String fileName  =  inputFileFullPath.substring(lastSeparatorIndex+1,inputFileFullPath.length());
			this.client.changeDirectory(parentDir);
			this.client.download(fileName, out, -1, listener);
		} catch (Exception e) {
			throw e;
		}finally{
			this.closeConnection();
		}
	}
}
