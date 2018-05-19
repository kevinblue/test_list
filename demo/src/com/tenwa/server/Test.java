package com.tenwa.server;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.squareup.okhttp.OkHttpClient;
import com.tenwa.server.util.RestUtils;


public class Test {
	private String server_url="";
	public String getServerMsg(String username){
		String strResponse ="";
		server_url="http://54.223.190.36:8081/api/WeChat/WfDataReport/WF/67170f00-ffc4-11e5-8a0e-02d9101dfa4b/2/2016-10";
        URL url;
		try {
			url = new URL(server_url);
	        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setRequestMethod("GET");
	        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	        out.write("�� ��");
	        out.flush();
	        out.close();
	        String strLine="";
	        InputStream in =connection.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        while((strLine =reader.readLine()) != null)
	        {
	     	   strResponse +=strLine;
	        }
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		strResponse=strResponse=="null"?"":strResponse;
		return strResponse;
	}
	public static int dataSyncRequest()throws Exception  {
		int res = 0;//0�������ɹ�,1����ʧ��
		String urlStr = "http://54.223.190.36:8081/api/WeChat/WfDataReport/WF/67170f00-ffc4-11e5-8a0e-02d9101dfa4b/2/2016-10";
		 
		URL url = null;		
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e1) {
			throw new Exception(e1);
		}
		OkHttpClient client = new OkHttpClient();
		InputStream in = null;
		HttpURLConnection connection = client.open(url);
		
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		connection.connect();
		if(connection.getResponseCode()==200){				
			in = connection.getInputStream();
		}else if(connection.getResponseCode()==400){
			in = connection.getErrorStream();
		}else{
			throw new Exception(connection.getResponseCode()+":"+connection.getResponseMessage());
		}
		
		
		
		/*ObjectMapper mapper = new ObjectMapper();
		byte[] response = readFully(in);
		return mapper.readValue(response, 0, response.length, responseType);
		return res;*/
		
	   return 0;
	}
	private static byte[] readFully(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		for (int count; (count = in.read(buffer)) != -1;) {
			out.write(buffer, 0, count);
		}
		return out.toByteArray();
	}

/*	public static void main(String[] args) {
		Map<String, Object> jsonMapList = null;
		try {
			jsonMapList = RestUtils.doGet("http://54.223.190.36:8081/api/WeChat/WfDataReport/WF/WfList/TianXin/KpiSystem", "", new TypeReference<Map<String,Object>>() {});
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		System.out.print(jsonMapList);
	}*/
	
		
	public static void main(String[] args) {
		//Test t=new Test();
		//String ss=t.getServerMsg("");
		//System.out.println(ss);
		
		List<Map<String, Object>> jsonMapList = null;
	
		try {
			jsonMapList = RestUtils.doGet("http://54.223.190.36:8081/api/WeChat/WfDataReport/WF/WfList/TianXin/KpiSystem", "", new TypeReference<List<Map<String,Object>>>() {});
		} catch (Exception e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		System.out.print(jsonMapList);
	}
}
