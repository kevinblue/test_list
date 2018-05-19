package com.tenwa.kernal.web.listener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.tenwa.kernal.utils.XmlUtil;

public class PreInitializeListener implements ServletContextListener {
	Log log = LogFactory.getLog(this.getClass());
	private static final String OUT_PROPERTY_FILE_PATH = "/tomcat/profiles/sczl/properties/quartz.properties";
//	private static final String OUT_PROPERTY_FILE_PATH = "D:\\quartz.properties";
	private static final String INNER_PROPERTY_FILE_TEMP_PATH = "quartz_temp.properties";
	private static final String INNER_PROPERTY_FILE_PATH = "quartz.properties";
	private static final String INNER_LOG4J_FILE = "log4j.xml";
	
	private String traceLog4Debug = "";
	private String debugLog = "";
	private String infoLog = "";
	private String errorLog = "";
	private String warnLog = "";
	private String fatalLog = "";
	

	public static String getOutPropertyFilePath() {
		return OUT_PROPERTY_FILE_PATH;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		File file = new File(getOutPropertyFilePath());
		String path = sc.getRealPath("/") + "/";
		String fileOutPutFilePath = path
				+ "WEB-INF/classes/"+INNER_PROPERTY_FILE_TEMP_PATH;
		InputStream fis = null;
		OutputStream fos = null;
		boolean isFound = true;
		try {
			fis = new BufferedInputStream(new FileInputStream(file));
			fos = new BufferedOutputStream(new FileOutputStream(new File(
					fileOutPutFilePath)));
			byte[] data = new byte[1024];
			int c;
			while ((c = fis.read(data, 0, 1024)) != -1) {
				try {
					fos.write(data, 0, c);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			if (log.isInfoEnabled()) {
				log.info("外部quartz不存在");
			}
			isFound = false;
		} catch (IOException e) {
			e.printStackTrace();
			isFound = false;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {

				}

			}
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (Exception e) {

				}
			}
		}
		// 需要将quartz_temp.properties ---> 覆盖quartz.properties
		if(isFound){
			Properties innerTempPro = new Properties();
			Properties innerPro = new Properties();
			InputStream fisInnerTemp = null;
			InputStream fisInner = null;
			OutputStream fisOut = null;
			try {
				fisInnerTemp = new FileInputStream(URLDecoder.decode(
						Thread.currentThread().getContextClassLoader()
						.getResource(INNER_PROPERTY_FILE_TEMP_PATH)
						.getFile(), "UTF-8"));
				innerTempPro.load(fisInnerTemp);
				fisInner = new FileInputStream(URLDecoder.decode(
						Thread.currentThread().getContextClassLoader()
						.getResource(INNER_PROPERTY_FILE_PATH)
						.getFile(), "UTF-8"));
				innerPro.load(fisInner);
				for(Object key : innerTempPro.keySet()){
					String strKey = (String)key;
					String strValue = innerTempPro.getProperty(strKey);
					if(strKey.equalsIgnoreCase("traceLog4Debug")){
						traceLog4Debug = strValue;
					}
					if(strKey.equalsIgnoreCase("debugLog")){
						debugLog = strValue;
					}
					if(strKey.equalsIgnoreCase("infoLog")){
						infoLog = strValue;
					}
					if(strKey.equalsIgnoreCase("warnLog")){
						warnLog = strValue;
					}
					if(strKey.equalsIgnoreCase("fatalLog")){
						fatalLog = strValue;
					}
					if(strKey.equalsIgnoreCase("errorLog")){
						errorLog = strValue;
					}
					innerPro.setProperty(strKey, strValue);
				}
				fisOut = new FileOutputStream(URLDecoder.decode(
						Thread.currentThread().getContextClassLoader()
						.getResource(INNER_PROPERTY_FILE_PATH)
						.getFile(), "UTF-8"));
				innerPro.store(fisOut, "add properties");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != fisInnerTemp) {
						fisInnerTemp.close();
						fisInnerTemp = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					if (null != fisInner) {
						fisInner.close();
						fisInner = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (null != fisOut) {
						fisOut.close();
						fisOut = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		//读取properties中info、debug、error、warn、fata
		if(isFound){
			Document document = null;
	    	FileOutputStream fosLog4j = null;
			try {
				String xmlFilePath  = URLDecoder.decode(
						Thread.currentThread().getContextClassLoader()
						.getResource(INNER_LOG4J_FILE)
						.getFile(), "UTF-8");
				document = XmlUtil.readXML(xmlFilePath);
				Element  root = document.getRootElement();
				List<Element> eles = root.getChildren("appender");
				for(Element ele : eles){
					String type = ele.getAttributeValue("name");
					List<Element> paramEles = ele.getChildren("param");
					for(Element paramEle : paramEles){
						if(paramEle.getAttributeValue("name") != null && paramEle.getAttributeValue("name").equals("File")){
							if(type.equalsIgnoreCase("traceLog4Debug") && StringUtils.isNotEmpty(this.getTraceLog4Debug())){
								paramEle.setAttribute("value",this.getTraceLog4Debug());
							}
							if(type.equalsIgnoreCase("debugLog") && StringUtils.isNotEmpty(this.getDebugLog())){
								paramEle.setAttribute("value",this.getDebugLog());
							}
							if(type.equalsIgnoreCase("infoLog") && StringUtils.isNotEmpty(this.getInfoLog())){
								paramEle.setAttribute("value",this.getInfoLog());
							}
							if(type.equalsIgnoreCase("warnLog") && StringUtils.isNotEmpty(this.getWarnLog())){
								paramEle.setAttribute("value",this.getWarnLog());
							}
							if(type.equalsIgnoreCase("fatalLog") && StringUtils.isNotEmpty(this.getFatalLog())){
								paramEle.setAttribute("value",this.getFatalLog());
							}
							if(type.equalsIgnoreCase("errorLog") && StringUtils.isNotEmpty(this.getErrorLog())){
								paramEle.setAttribute("value",this.getErrorLog());
							}
							break;
						}
					}
				}
				fosLog4j = new FileOutputStream(xmlFilePath);
				XMLOutputter output = new XMLOutputter();
				output.output(document, fosLog4j);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				document = null;
				if(fosLog4j != null){
					try {
						fosLog4j.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	public String getFatalLog() {
		return fatalLog;
	}

	public void setFatalLog(String fatalLog) {
		this.fatalLog = fatalLog;
	}

	public String getTraceLog4Debug() {
		return traceLog4Debug;
	}

	public String getDebugLog() {
		return debugLog;
	}

	public String getInfoLog() {
		return infoLog;
	}

	public String getWarnLog() {
		return warnLog;
	}

	public String getErrorLog() {
		return errorLog;
	}

	
}
