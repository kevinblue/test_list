package com.runto.servlet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
	private String server_url="";
	public String getServerMsg(String username){
		String strResponse ="";
		server_url="Http://UCS_API_HOST/api/1.0/ucs/geely/car_info/+";
        URL url;
		try {
			url = new URL(server_url);
	        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
	        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	        out.write("中国");
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
	public static void main(String[] args) {
		Test t=new Test();
		String ss=t.getServerMsg("");
		System.out.println(ss);
	}
}
