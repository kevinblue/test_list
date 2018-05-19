
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model.file
 * 文件名：         BaseFileOperation.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-29-下午03:53:05
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.model.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.tenwa.business.model.FileProcess;


 /**
 * 类名称：     BaseFileOperation
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-29 下午03:53:05
 * 修改备注：
 * @version 1.0.0
 **/

public abstract class AbstractBaseFileOperation<T> implements BaseFileOperation<T> {
   public void uploadOrDownload(InputStream in ,OutputStream out,FileProcess fileProcess) throws Exception{
	   /* InputStreamReader reader  = null;
       OutputStreamWriter writer = null; 
	   if(null != fileProcess){
		   fileProcess.setUploadedFinish(true);
		   fileProcess.setTotalSize(in.available());
		   fileProcess.setUploadedSize(0);  
	   }
        try{
        	reader = new InputStreamReader(in,"UTF-8");
        	writer = new OutputStreamWriter(out);
			int c;
			char[] by = new char[BUFFEREDSIZE];
			while ((c = reader.read(by)) != -1) {
				writer.write(by, 0, c);
				if(null != fileProcess){
					fileProcess.setUploadedSize(fileProcess.getUploadedSize()+c*8);
				}
			}
        }
        catch (IOException e) {
			throw e;
		} finally {
			if(null != writer){
				writer.flush();
				writer.close();
			}
			if(null != out){
				out.flush();
				out.close();
			}
			if(null != reader){
				reader.close();
			}
			if(null != in){
				in.close();
			}
		}*/
	   try {
			int c;
			byte[] by = new byte[BUFFEREDSIZE];
			while ((c = in.read(by)) != -1) {
				out.write(by, 0, c);
				if(null != fileProcess){
					fileProcess.setUploadedSize(fileProcess.getUploadedSize()+c);
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(null != out){
				out.flush();
				out.close();
			}
			if(null != in){
				in.close();
			}
		}
   }
}
