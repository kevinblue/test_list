package com.tenwa.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class Testwebservice {
public static void main(String[] args) {
	// 获取Servlet连接并设置请求的方法
	String url = "http://10.122.1.251:80/service/XChangeServlet?account=001&groupcode=0001";
	URL realURL;
	try {
		realURL = new URL(url);
	
	HttpURLConnection	connection = (HttpURLConnection)realURL.openConnection();
	
	connection.setDoOutput(true);
	connection.setRequestProperty("Content-type", "text/xml");
	connection.setRequestMethod("POST");
	// 将Document对象写入连接的输出流中
	File file = new File("e:/samples/aaa.xml");
	BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
	BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
	int length;
	byte[] buffer = new byte[1000];
	while ((length = input.read(buffer, 0, 1000)) != -1) {
	       out.write(buffer, 0, length);
	}
	input.close();
	out.close();
	// 从连接的输入流中取得回执信息
	InputStream inputStream = connection.getInputStream();
    StringBuffer   sb   =   new   StringBuffer(); 
    byte[]   b   =   new   byte[1000]; 
    for   (int   n;   (n   =   inputStream.read(b))   !=   -1;)   { 
    	sb.append(new   String(b,   0,   n)); 
    } 

	File file1 = new File("e:/samples/bb.xml");
	OutputStream  outputStream = new FileOutputStream(file1);
	int bytesWritten = 0;
	int byteCount = 0;

	byte[] bytes = new byte[1024];

	while ((byteCount = inputStream.read(bytes)) != -1)
	{
	          outputStream.write(bytes, bytesWritten, byteCount);
	           bytesWritten += byteCount;
	}
	inputStream.close();
	outputStream.close();

	/*HeloWordClient hc=new HeloWordClient();
	Document resDoc = hc.getDocumentBuilder().parse(inputStream); // 解析为Doc对象
*///对回执结果的后续处理…
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
