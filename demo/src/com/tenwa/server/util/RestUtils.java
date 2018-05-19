package com.tenwa.server.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;

public class RestUtils {

	/**
	 * Get 请求
	 * @param urlStr
	 * @param authToken
	 * @param responseType
	 * @return
	 * @throws Exception
	 */
	public static <T> T doGet(String urlStr,  String authToken, TypeReference<T> responseType) 
			throws Exception{				
		T doGetHandler = null;
		int num = 0;
		while(doGetHandler==null && num<3){
			num++;
			doGetHandler = doGetHandler(urlStr, authToken, responseType);
		}
		
		if(doGetHandler==null){
			throw new Exception(new Exception("do get handler error"));
		}
		return doGetHandler;
	}

	/**
	 * @param urlStr
	 * @param authToken
	 * @param responseType
	 * @return
	 * @throws Exception
	 */
	private static <T> T doGetHandler(String urlStr, String authToken,
			TypeReference<T> responseType) throws Exception {
		URL url = null;		
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e1) {
			throw new Exception(e1);
		}
		
		OkHttpClient client = new OkHttpClient();
		InputStream in = null;
		HttpURLConnection connection = client.open(url);
		try {
			/*if(authToken!=null && authToken.length()>0){
				connection.setRequestProperty(Dict_Common.REQUEST_HEADER_TOKEN, authToken);	
			}*/
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.connect();
			
			if(connection.getResponseCode()==200){				
				in = connection.getInputStream();
			}
			
			else if(connection.getResponseCode()==400){
				in = connection.getErrorStream();
			}else{
				throw new Exception(connection.getResponseCode()+":"+connection.getResponseMessage());
			}
			
			ObjectMapper mapper = new ObjectMapper();
		//	Phone phone = mapper.readValue(phoneObject, Phone[].class)[0];
			byte[] response = readFully(in);
			return mapper.readValue(response, 0, response.length, responseType);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e2) {
				}
			connection.disconnect();
		}
	}
	
	/**
	 * POST请求
	 * @param urlStr
	 * @param requestStr
	 * @param authToken
	 * @param responseType
	 * @return
	 * @throws Exception
	 */
	public static <T> T doPost(String urlStr,  String requestStr, String authToken, TypeReference<T> responseType) 
			throws Exception{		
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e1) {
			throw new Exception(e1);
		}
		
		OkHttpClient client = new OkHttpClient();
		InputStream in = null;
		byte[] response = null;
		
		HttpURLConnection connection = client.open(url);
		try {
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");		
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			
			/*if(authToken!=null && authToken.length()>0){
				connection.setRequestProperty(Dict_Common.REQUEST_HEADER_TOKEN, authToken);	
			}*/
			connection.setDoOutput(true);
			if(requestStr!=null)
			connection.getOutputStream().write(requestStr.getBytes());
				
			ObjectMapper mapper = new ObjectMapper();
			if(connection.getResponseCode()==200){				
				response = readFully(connection.getInputStream());
			}
			
			else if(connection.getResponseCode()==400){
				response = readFully(connection.getErrorStream());
			}
			
			else{
				throw new Exception(connection.getResponseCode()+":"+connection.getResponseMessage());
			}
			
			return mapper.readValue(response, 0, response.length, responseType);
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			connection.disconnect();
			if (in != null)
				try {
					in.close();
				} catch (Exception e2) {
				}
		}
	}
	
	
	/**
	 * Put请求
	 * @param urlStr
	 * @param requestStr
	 * @param authToken
	 * @param responseType
	 * @return
	 * @throws Exception
	 */
	public static <T> T doPut(String urlStr,  String requestStr, String authToken, TypeReference<T> responseType) 
			throws Exception{		
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e1) {
			throw new Exception(e1);
		}
		
		OkHttpClient client = new OkHttpClient();
		InputStream in = null;
		byte[] response = null;
		
		HttpURLConnection connection = client.open(url);
		try {
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");		
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			
			/*if(authToken!=null && authToken.length()>0){
				connection.setRequestProperty(Dict_Common.REQUEST_HEADER_TOKEN, authToken);	
			}*/
			
			connection.setDoOutput(true);
			if(requestStr!=null)
			connection.getOutputStream().write(requestStr.getBytes());
			
			
			ObjectMapper mapper = new ObjectMapper();
			if(connection.getResponseCode()==200){				
				response = readFully(connection.getInputStream());
			}
			
			else if(connection.getResponseCode()==400){
				response = readFully(connection.getErrorStream());
			}
			
			else{
				throw new Exception(connection.getResponseCode()+":"+connection.getResponseMessage());
			}
						
			
			return mapper.readValue(response, 0, response.length, responseType);
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			connection.disconnect();
			if (in != null)
				try {
					in.close();
				} catch (Exception e2) {
				}
		}
	}
	
	
	/**
	 * doDelete
	 * @param urlStr
	 * @param authToken
	 * @param responseType
	 * @return
	 * @throws Exception
	 */
	public static <T> T doDelete(String urlStr, String authToken,
			TypeReference<T> responseType) throws Exception {
		URL url = null;		
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e1) {
			//log.error("invalid URL", e1);
			throw new Exception(e1);
		}
		
		OkHttpClient client = new OkHttpClient();
		InputStream in = null;
		HttpURLConnection connection = client.open(url);
		try {
			/*if(authToken!=null && authToken.length()>0){
				connection.setRequestProperty(Dict_Common.REQUEST_HEADER_TOKEN, authToken);	
			}*/
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.connect();
			
			if(connection.getResponseCode()==200){				
				in = connection.getInputStream();
			}
			
			else if(connection.getResponseCode()==400){
				in = connection.getErrorStream();
			}else{
				throw new Exception(connection.getResponseCode()+":"+connection.getResponseMessage());
			}
			
			
			
			in = connection.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			byte[] response = readFully(in);
			return mapper.readValue(response, 0, response.length, responseType);
		} catch (IOException e) {
			e.printStackTrace();
			//log.error("doGetHandler error :" + e);
			return null;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e2) {
					//log.error("failed to close connection", e2);
				}
			connection.disconnect();
		}
	}
	

	private static byte[] readFully(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[in.available()];
		for (int count; (count = in.read(buffer)) != -1;) {
			out.write(buffer, 0, count);
		}
		//System.out.println(out.toString());
		return out.toByteArray();
	}
}
