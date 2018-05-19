package com.tenwa.leasing.controller.voucher;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.voucher.InterV8Vouchers;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.utils.Tools;


@Controller(value = "intereasVoucherController")
@RequestMapping(value = "/**/leasing")
public class IntereasVoucherController {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	/**
	 * V8凭证服务接口
	 */
	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	/**
	 * log4j日志 
	 */
	private static final Logger log = LoggerFactory.getLogger(IntereasVoucherController.class);

	/**
	  * <p>点击凭证明细列-查看详情。</p>
	  * @author sea
	  * @param request  
	  * @param response
	  * @return
	  * @throws Exception
	 */
	@RequestMapping(value = "/addInsuranceInfo.action")
	public String addInsuranceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取前台传入的参数
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		//凭证号
		String vouchernumber = model.get("vouchernumber");
		log.info("V8凭证号:"+vouchernumber);
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("f3", vouchernumber);
		
		//根据凭证号查询完整的凭证信息
		String HSQL = " FROM InterV8Vouchers V8 WHERE V8.f3 = '"+vouchernumber+"' ORDER BY V8.f59 ";
		List<InterV8Vouchers> interV8Vouchers = this.baseService.findResultsByHSQL(HSQL);
		//List<InterV8Vouchers> interV8Vouchers = this.baseService.getBussinessDao().findEntityByProperties(InterV8Vouchers.class, propertiesMap);
		//返回的是一个对象合计
		request.setAttribute("interV8Vouchers", interV8Vouchers);
		return "solutions/leasing/voucher/voucher_detail.jsp";
	}
	
	
	
	//V8凭证修改
//	@RequestMapping(value = "/updateInsuranceInfo.action")
//	public String updateInsuranceInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		
//		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
//		
//		IntereasVoucherHead intereasVoucherHead  = this.baseService.findEntityByID(IntereasVoucherHead.class, model.get("id"));
//		 
//		 request.setAttribute("intereasVoucherHead", intereasVoucherHead);
//	     System.out.println("#####:"+intereasVoucherHead.getIntereasVoucherEntries());
//		 
//		return "solutions/leasing/voucher/voucher_mod.jsp";
//	}
	
	
	//导出TXT文本格式 
	@RequestMapping(value = "/toV8.action")
	public String toV8(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("toV8.action-V8凭证导出TXT方法进入!");
		User user = (User) SecurityUtil.getPrincipal();
		//接参
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		//解决中文乱麻问题的方法:页面端发出的数据作一次encodeURI，服务器段使用new String(old.getBytes("iso8859-1"),"UTF-8");
		
		String params = model.get("params");
		//params = new String(params.getBytes("iso8859-1"),"UTF-8");
		if (params.equals(new String(params.getBytes("iso8859-1"),"iso8859-1")))
		    { params = new String(params.getBytes("iso8859-1"),"UTF-8");      
		  }
		log.info("params:"+params);

		
		
		if((null != params ) && params.startsWith("{")){
			JSONObject paramsObj = new JSONObject(params);
			model.putAll(this.baseService.getStringMapByJsonObject(paramsObj));
		}
		
		//{rawValue_id_table_queryArea_v8flag=null, f15_end=null, rawValue_id_table_queryArea_status_=null, modlename=null, vouchernumber=null, f15_start=null, url=eleasing/jsp/voucher/voucher_list.xml, rawValue_id_table_queryArea_modlename=null, status_=null, contract_number=null, f1_end=null, v8flag=null, f1_start=null}
		String process_name = "V8Voucher";//V8凭证导出
		//
		String res = voucherToV8Service.updateMessage(model,user);
		log.info("拼接的导出凭证字符串:"+res);
		String filename = "evidence"+Tools.getSystemDate(3)+ new String(process_name.getBytes("GB2312"), "ISO-8859-1")+".txt";
		
		String path = request.getSession().getServletContext().getRealPath("/");
		log.info("path:"+path);
		
		if(res != null){
			BufferedInputStream bis   =   null;   
			BufferedOutputStream bos   =   null;
			File file = null;
			FileWriter fw = null;
			try {
				response.reset();
				response.setContentType("text/plain");   
				response.setHeader("Content-disposition",   "attachment;   filename=\""   +   new String(filename.getBytes("GB2312"), "ISO-8859-1")  +"\"");   
				file = new File(filename);
				if(file.exists()){
					file.delete();
				}
				/*
				//方式1 
				String writeStr = "";   
				writeStr = res;
				fw = new   FileWriter(file,true);
				fw.write(writeStr,0,writeStr.length());   
				fw.flush();
				fw.close();
				*/
				//方式2
				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filename, true), "GB2312");
				osw.write(res);
				osw.flush();
				
				//复制
				String newFile = path+"\\WEB-INF\\jsps\\solutions\\leasing\\pz\\"+filename;
				newFile = FileUtil.getFilePathString(newFile);
				log.info("newFile-->"+newFile);
				/*
				String url = "\\WEB-INF\\jsps\\solutions\\leasing\\pz\\"+filename;
				url = FileUtil.getFilePathString(url);
				log.info("url-->"+url);
				*/
				this.copyFile(file,newFile);
				
			     //记录日志
				voucherToV8Service.saveFileList(newFile, user, res);
				
			   //下载TXT
			   response.setContentType("text/plain;charset=ISO8859_1");
		       response.setHeader("Content-disposition","attachment; filename="+filename);
			   response.setContentType("text/html; charset=GB2312");
			   
			   //工程里公用的下载方法
			   OutputStream out = response.getOutputStream();
			   ResourceUtil.getFileUploadOperation().downloadFile(newFile, out, null);
			
		       //***********************************************************************************
			}catch(IOException   ioexception){
				throw ioexception;
			} finally {  
				try{ 
					if(bis !=   null)   
					        bis.close();   
					if(bos !=   null)   
					        bos.close();
				}catch(IOException e){
					throw e;
				} 
				
			} 
			//response.sendRedirect(request.getContextPath()+"/pzxz/pzxz/pzxz_download.jsp"); 
		}
		
		
		
		return null;
	}
	
	/**
	  * <p>复制单个文件。</p>
	  * @author sea
	  * @param oldPath 原路径
	  * @param newPath 新路径
	 */
	private void copyFile(File oldPath, String newPath) { 
       try { 
           int bytesum = 0; 
           int byteread = 0; 
           if (oldPath.exists()) { //文件存在时 
               InputStream inStream = new FileInputStream(oldPath); //读入原文件 
               FileOutputStream fs = new FileOutputStream(newPath); 
               byte[] buffer = new byte[1444]; 
               int length; 
               while ( (byteread = inStream.read(buffer)) != -1) { 
                   bytesum += byteread; //字节数 文件大小 
                   System.out.println(bytesum); 
                   fs.write(buffer, 0, byteread); 
               } 
               inStream.close(); 
               fs.close();
           } 
       }catch (Exception e) { 
           System.out.println("复制单个文件操作出错"); 
           e.printStackTrace(); 
       } 
   } 

	
	public String cXML(String sPath,String data){
	        BufferedWriter utput;
	        try {
	         File f = new File(sPath);
	         if (f.exists()) {
	          System.out.println("文件存在");
	         } else {
	          System.out.println("文件不存在，正在创建...");
	          if (f.createNewFile()) {
	           System.out.println("文件创建成功！");
	          } else {
	           System.out.println("文件创建失败！");
	          }
	         }
	         FileWriter ff = new FileWriter(f);
	         java.io.FileOutputStream writerStream = new java.io.FileOutputStream(sPath);    
	         utput = new BufferedWriter(new java.io.OutputStreamWriter(writerStream, "GB2312")); 

	         //utput = new BufferedWriter(new FileWriter(f));
	         System.out.println("写入xml的数据："+data);
	         
	         utput.write(data);
	         utput.close();
	         return sPath;
	        } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	        }
	 }
	
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public BaseService getBaseService() {
		return baseService;
	}
}