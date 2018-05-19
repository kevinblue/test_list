package com.yt.webs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class TestServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TestServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	  System.out.println("开始执行");
		try {
			String endpoint = "http://localhost:8080/ZhongrongGetCtiDateWebService/services/GetCtiDateServer";
			// 直接引用远程的wsdl文件
			// 以下都是套路

			Service service = new Service();
			
			Call	call = (Call) service.createCall();
	
		call.setTargetEndpointAddress(endpoint);
		call.setOperationName("getCtiDate");// WSDL里面描述的接口名称
		call.addParameter("in0",
				org.apache.axis.encoding.XMLType.XSD_STRING,
				javax.xml.rpc.ParameterMode.IN);// 接口的参数
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
		String temp1 = "<Body><StartDate>2015-08-28 18:00:00</StartDate><EndDate>2015-08-30 23:00:00</EndDate></Body>";

		String result = (String) call.invoke(new Object[] { temp1 });
		// 给方法传递参数，并且调用方法
	
    	 System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
