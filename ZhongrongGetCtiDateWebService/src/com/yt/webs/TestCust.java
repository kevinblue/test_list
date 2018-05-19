package com.yt.webs;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class TestCust extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response); 
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/*
		String endpoint = "http://localhost:8080/ZhongrongGetCtiDateWebService/services/GetCtiDateServer";
		// 直接引用远程的wsdl文件
		// 以下都是套路

		Service service = new Service();
		
		try {
			Call	call = (Call) service.createCall();
	
		call.setTargetEndpointAddress(endpoint);
		call.setOperationName("getCtiDate");// WSDL里面描述的接口名称
		call.addParameter("in0",
				org.apache.axis.encoding.XMLType.XSD_STRING,
				javax.xml.rpc.ParameterMode.IN);// 接口的参数
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
		String temp1 = "<Body><StartDate>2013-08-30 18:00:00</StartDate><EndDate>2013-08-30 23:00:00</EndDate></Body>";

		String result = (String) call.invoke(new Object[] { temp1 });
		// 给方法传递参数，并且调用方法
	
    	 System.out.println(result);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		PrintWriter out = response.getWriter();
		response.getWriter().write("111111111111111");
		System.out.println("111111111111");
		
		out.write("11111111111111111111111111111111");
		
	}


}
