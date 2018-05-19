package com.tenwa.leasing.utils.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Mar 21, 2011
 * @desc (文件上传类)
 */
public class UploadBean {
	
	private String userid;
	private String sourceFile[];
	private String suffix[];
	private String canSuffix;
	private String objectPath;
	private String objectFileName[];
	private ServletInputStream sis;
	private String description[];
	private long size;
	private int count;
	private byte b[];
	private boolean successful;
	private Hashtable<String, String> fields;
	private Logger log;
	
	public UploadBean() {
		userid = "";
		sourceFile = new String[255];
		suffix = new String[255];
		canSuffix = ".zip.jpg.jpeg.bmp.xls.doc.ppt.mpp.rar";
		objectPath = "c:/temp";
		objectFileName = new String[255];
		sis = null;
		description = new String[255];
		size = 0xa00000L;
		count = 0;
		b = new byte[4096];
		successful = true;
		fields = new Hashtable<String, String>();
		log = Logger.getLogger(this.getClass());
	}

	public void setUserid(String uid) {
		userid = uid;
	}

	/**
	 * 
	 * (设置允许上传文件类型)
	 * 
	 * @param canSuffix
	 *            允许上传文件类型 格式为 .xx 如: .doc.txt.pdf
	 */
	public void setSuffix(String canSuffix) {
		this.canSuffix = canSuffix;
	}

	/**
	 * 
	 * (设置上传文件存储路径)
	 * 
	 * @param objectPath
	 */
	public void setObjectPath(String objectPath) {
		File file = new File(objectPath);
		if (file.exists()) {
			this.objectPath = objectPath;
		} else {
			file.mkdir();
			this.objectPath = objectPath;
		}
	}

	/**
	 * (设置允许上传文件总大小)
	 * 
	 * @param maxSize
	 */
	public void setSize(long maxSize) {
		size = maxSize;
	}

	/**
	 * 
	 * (设置上传输入流调用改方法后将进行文件的上传.)
	 * 
	 * @param request
	 *            页面请求
	 * @throws IOException
	 *             抛出IO读取出错异常
	 */
	public void setSourceFile(HttpServletRequest request) throws IOException {
		sis = request.getInputStream();
		int a = 0;
		int k = 0;
		String s = "";
		while ((a = sis.readLine(b, 0, b.length)) != -1) {
			s = new String(b, 0, a);
			if ((k = s.indexOf("filename=\"")) != -1) {
				s = s.substring(k + 10);
				k = s.indexOf("\"");
				s = s.substring(0, k);
				sourceFile[count] = s;
				k = s.lastIndexOf(".");
				suffix[count] = s.substring(k + 1);
				if (canTransfer(count))
					transferFile(count);
				count++;
			} else if ((k = s.indexOf("name=\"")) != -1) {
				String fieldName = s.substring(k + 6, s.length() - 3);
				sis.readLine(b, 0, b.length);
				StringBuffer fieldValue = new StringBuffer(b.length);
				while ((a = sis.readLine(b, 0, b.length)) != -1) {
					s = new String(b, 0, a - 2);
					// 如果读到连续四个-则为文件末 结束读取
					if (b[0] == 45 && b[1] == 45 && b[2] == 45 && b[3] == 45 && b[4] == 45)
						break;
					fieldValue.append(s);
				}
				fields.put(fieldName, fieldValue.toString());
			}
			if (!successful)
				break;
		}
		if (!successful) {
			for (int d = 0; d < count; d++) {
				File tmp = new File(objectPath + objectFileName[d]);
				tmp.delete();
			}
		}
	}

	/**
	 * 
	 * (返回上传文件的内容)
	 * 
	 * @param fieldName
	 *            上传文件名 改名称为上传到服务器之后的名字
	 * @return 返回String字符为该文件内容
	 */
	public String getFieldValue(String fieldName) {
		if (fields == null || fieldName == null)
			return null;
		else
			return (String) fields.get(fieldName);
	}

	/**
	 * 
	 * (获得上传文件个数)
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * (获得上传文件路径)
	 * 
	 * @return
	 */
	public String getObjectPath() {
		return objectPath;
	}

	/**
	 * 
	 * (获得上传文件上传时的本地地址)
	 * 
	 * @return
	 */
	public String[] getSourceFile() {
		return sourceFile;
	}

	/**
	 * 
	 * (获得上传文件到服务器重命名之后的文件名数据)
	 * 
	 * @return
	 */
	public String[] getObjectFileName() {
		return objectFileName;
	}

	/**
	 * 
	 * (获得上传错误信息)
	 * 
	 * @return
	 */
	public String[] getDescription() {
		String[] description_t;
		List<String> t = new ArrayList<String>();
		int i = 0;
		for (; i < description.length; i++) {
			if (description[i] != null) {
				t.add(description[i]);
			}
		}
		if (t.size() == 0) {
			successful = true;
			description_t = new String[1];
			description_t[0] = "";
		} else {
			description_t = new String[t.size()];
			for (i = 0; i < t.size(); i++) {
				description_t[i] = (String) t.get(i);
			}
			successful = false;
		}
		return description_t;
	}

	/**
	 * 
	 * (获得上传是否成功标记)
	 * 
	 * @return
	 */
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * 
	 * (验证上传文件类型是否符合要求)
	 * 
	 * @param i
	 * @return
	 */
	private boolean canTransfer(int i) {
		suffix[i] = suffix[i].toLowerCase();
		if (sourceFile[i].equals("") || canSuffix.indexOf("." + suffix[i]) < 0) {
			// 出错：该类型文件(xx)不允许上传！整个上传中止
			description[i] = "\u51FA\u9519\uFF1A\u8BE5\u7C7B\u578B" + "(" + suffix[i] + ")\u6587\u4EF6\u4E0D\u5141\u8BB8\u4E0A\u4F20\uFF01\u6574\u4E2A\u4E0A\u4F20\u4E2D\u6B62.";
			successful = false;
			log.debug(description[i]);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * (保存上传文件)
	 * 
	 * @param i
	 */
	private void transferFile(int i) {
		String x = userid + Long.toString((new Date()).getTime());
		try {
			objectFileName[i] = x + "." + suffix[i];

			FileOutputStream out = new FileOutputStream(objectPath + objectFileName[i]);
			int a = 0;
			long hastransfered = 0L;
			String s = "";
			while ((a = sis.readLine(b, 0, b.length)) != -1) {
				s = new String(b, 0, a);
				if (s.indexOf("Content-Type:") != -1)
					break;
			}
			sis.readLine(b, 0, b.length);
			while ((a = sis.readLine(b, 0, b.length)) != -1) {
				s = new String(b, 0, a);
				if (b[0] == 45 && b[1] == 45 && b[2] == 45 && b[3] == 45 && b[4] == 45)
					break;
				out.write(b, 0, a);
				hastransfered += a;
				if (hastransfered >= size) {
					// 出错：文件总量过大，请将文件压缩或联系管理员
					description[count] = "\u51FA\u9519\uFF1A\u6587\u4EF6\u603B\u91CF\u8FC7\u5927\uFF0C\u8BF7\u5C06\u6587\u4EF6\u538B\u7F29\u6216\u8054\u7CFB\u7BA1\u7406\u5458.";
					log.debug(description[i]);
					successful = false;
					break;
				}
			}
			out.close();
			if (!successful) {
				sis.close();
				File tmp = new File(objectPath + objectFileName[count]);
				tmp.delete();
			}
		} catch (IOException ioe) {
			description[i] = ioe.toString();
		}
	}
}
