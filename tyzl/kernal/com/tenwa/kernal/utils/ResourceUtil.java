package com.tenwa.kernal.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tenwa.business.entity.I18nDictionary;
import com.tenwa.business.model.file.BaseFileOperation;
import com.tenwa.business.model.file.FileStoreType;
import com.tenwa.business.model.file.FtpFileOperationImpl;
import com.tenwa.business.model.file.LocalFileOperationImpl;
import com.tenwa.business.model.file.SmbFileOperationImpl;


public class ResourceUtil 
{
   public static void init(){
	   //获得tablexml文件路径
	   tablesDataSourceDirectoryPath =getWebConfigValue("tables_dataDirectory");
	   //获得chartxml文件路径
	   chartsDataSourceDirectoryPath =getWebConfigValue("charts_dataDirectory");
	   //获得chartflt文件路径
	   chartsFltSourceDirectoryPath =getWebConfigValue("charts_fltDirectory");
   };
   //private static  String  CONFIG_FILE_NAME = "framework-config.properties";
   private static final  String  CONFIG_FILE_NAME = "quartz.properties";
   private static final String  EN_FILE_NAME = "messages_en_US.properties";
   private static final  String  ZH_FILE_NAME = "messages_zh_CN.properties";
   private static final  String  TW_FILE_NAME = "messages_zh_TW.properties";
   private static  Properties proEn  = new Properties();
   private static  Properties proZH = new Properties();
   private static  Properties proTW = new Properties();
   private static  Log log = LogFactory.getLog(ResourceUtil.class);
   private static  final Map<String,String> configMap = new HashMap<String,String>();
   private static  Map<String,String> enMap = new HashMap<String,String>();
   private static  Map<String,String> zhMap = new HashMap<String,String>();
   private static  Map<String,String> twMap = new HashMap<String,String>();
   private static String freemakerEntityStr ;
   
   public static String getFreemakerEntityStr() {
	   return freemakerEntityStr;
   }

	public static void setFreemakerEntityStr(String freemakerEntityStr) {
		ResourceUtil.freemakerEntityStr = freemakerEntityStr;
	}

public static void cacheI18N(){
	   cacheI18N(EN_FILE_NAME);
	   cacheI18N(ZH_FILE_NAME);
	   cacheI18N(TW_FILE_NAME);
   }
   
   public static void cacheI18N(String propertiesAddr){
	   FileInputStream fis = null;
	   Properties pro = null;
	   try 
		 {
		   Map<String, String> map = new HashMap<String, String>();
		   if(propertiesAddr.equals(EN_FILE_NAME)){
			   map = enMap;
			   pro = proEn;
		   }else if(propertiesAddr.equals(ZH_FILE_NAME)){
			   map = zhMap;
			   pro = proZH;
		   }else if(propertiesAddr.equals(TW_FILE_NAME)){
			   map = twMap;
			   pro = proTW;
		   }
		   fis = new FileInputStream(URLDecoder.decode(Thread.currentThread()
					.getContextClassLoader().getResource(propertiesAddr)
					.getFile(), "UTF-8"));
			 pro.load(fis);
			
			for(Object key : pro.keySet()){
				String valString = StringUtil.nullToString(pro.get(key)).trim();
				String keyString = StringUtil.nullToString(key).trim();
				map.put(keyString, valString);
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} finally{
			try {
				if(null != fis){
					fis.close();
					fis = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
   }
   
   public static Boolean synI18nProperties(List<I18nDictionary> dicts){
	  if( synI18nProperties(dicts,EN_FILE_NAME) && synI18nProperties(dicts,ZH_FILE_NAME) &&  synI18nProperties(dicts,TW_FILE_NAME)){
		 return true; 
	  }else{
		 return false;
	  }
   }
   
   private synchronized static boolean synI18nProperties(List<I18nDictionary> dicts,String propertiesAddrName){
	   FileOutputStream fos = null;
	   Properties pro = null;
	   boolean flag = true;
	   try 
		 {
		   Map<String, String> map = new HashMap<String, String>();
		   String methodName = "";
		   if(propertiesAddrName.equals(EN_FILE_NAME)){
			   map = enMap;
			   methodName = "getEnName";
			   pro = proEn;
		   }else if(propertiesAddrName.equals(ZH_FILE_NAME)){
			   map = zhMap;
			   methodName = "getZhName";
			   pro = proZH;
		   }else if(propertiesAddrName.equals(TW_FILE_NAME)){
			   map = twMap;
			   methodName = "getTwName";
			   pro = proTW;
		   }
		   fos = new FileOutputStream(URLDecoder.decode(Thread.currentThread()
					 .getContextClassLoader().getResource(propertiesAddrName)
					 .getFile(), "UTF-8"));
			for(I18nDictionary dic : dicts){
				if(null != dic.getEnName()){
					String valueStr = (String)I18nDictionary.class.getMethod(methodName).invoke(dic);
					if(!map.containsKey(dic.getCode()) || !map.get(dic.getCode()).equals(valueStr)){
						if(null != valueStr){
							map.put(dic.getCode(), valueStr);
							pro.setProperty(dic.getCode(), valueStr);
						}
					}
				}
			}
			pro.store(fos, "add properties");
		}
		catch (FileNotFoundException e) 
		{
			flag = false;
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			flag = false;
			e.printStackTrace();
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		} finally{
			try {
				if(null != fos){
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	   return flag;
   }
   
   public  synchronized static List<I18nDictionary> getI18nBeans( Map<String, I18nDictionary> oldDics){
	   List<I18nDictionary> insertDics= new ArrayList<I18nDictionary>();
	   for(Entry<String, String>entry : enMap.entrySet()){
		   I18nDictionary dict = new I18nDictionary();
		   String key = entry.getKey();
		   if(oldDics.containsKey(key)){
			   dict = oldDics.get(key);
		   }
		   dict.setCode(key);
		   dict.setEnName(enMap.get(key));
		   if(zhMap.containsKey(key)){
			   dict.setName(zhMap.get(key));
			   dict.setZhName(zhMap.get(key))  ;
		   }  
		   if(twMap.containsKey(key)){
			   dict.setTwName(twMap.get(key))  ;
		   }  
		   dict.setEnabled(true);
		   insertDics.add(dict);
	   }
	   return insertDics;
   }
   
   public static String getConfigValue(String propertyName)
   {
		   if(0 == configMap.size())
		   {
				 FileInputStream fis = null;
				 try 
				 {
					fis = new FileInputStream(URLDecoder.decode(Thread.currentThread()
							.getContextClassLoader().getResource(CONFIG_FILE_NAME)
							.getFile(), "UTF-8"));
					Properties pro = new Properties();
					pro.load(fis);
					for(Object key : pro.keySet()){
						String valString = StringUtil.nullToString(pro.get(key)).trim();
						String keyString = StringUtil.nullToString(key).trim();
						configMap.put(keyString, valString);
						
				        if(log.isInfoEnabled())
				        {
				           log.info("###########加载配置文件属性：【"+keyString+" = "+valString+"】");
				        }
					}
				}
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} finally{
					try {
						if(null != fis){
							fis.close();
							fis = null;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		   }

	   return configMap.get(propertyName);
   }
   //获得项目部署相对路径名称
   public static String getWebConfigValue(String propertyName)
   {
	   String value = WebUtil.getWebContextRealPath()+StringUtil.nullToString(getConfigValue(propertyName)).trim();
	   return value;
   }
   //获得是否处于系统开发阶段
   private static boolean isDebug = "TRUE".equals(StringUtil.nullToString(getConfigValue("isDebug")).toUpperCase());
   public  static void setDebug(boolean isDebugModel){
	   isDebug = isDebugModel;
   }
   public static boolean isDebug()
   {
	   return isDebug;
   }
   //远程RMI主机地址
   private static String RMILocalIp = getConfigValue("RMILocalIp");
   public  static String getRMILocalIp(){
	   return RMILocalIp;
   }
   //远程RMI端口
   private static int RMILocalPort = Integer.parseInt(getConfigValue("RMILocalPort"));
   public  static int getRMILocalPort(){
	   return RMILocalPort;
   }
   //远程RMI服务名
   private static String RMILocalServiceName = getConfigValue("RMILocalServiceName");
   public  static String getRMILocalServiceName(){
	   return RMILocalServiceName;
   }
   //远程RMI集群
   private static String RMICluster = getConfigValue("RMICluster");
   public  static String getRMICluster(){
	   return RMICluster;
   }
   private static String rmiServiceName = getConfigValue("rmiServiceName");
   public  static String getRmiServiceName(){
	   return rmiServiceName;
   }
   //密码复杂度
   private static int minPasswordLength = Integer.parseInt(StringUtil.empty2Other(getConfigValue("minkeyLength"), "6"));
   public  static int getMinPasswordLength(){
	   return minPasswordLength;
   }
   private static String passwordComplexityJSRegex = getConfigValue("keyComplexityJSRegex");
   public  static String getMinPasswordComplexityJSRegex(){
	   return passwordComplexityJSRegex;
   }
   //获得数据库类型
   private static String DBTYPE = getConfigValue("org.quartz.dataSource.myDS.URL").toUpperCase();
   public static String getDBType()
   {
	   if(DBTYPE.indexOf("ORACLE")>-1){
		   DBTYPE = "ORACLE";
	   }
	   else if(DBTYPE.indexOf("SQLSERVER")>-1){
		   DBTYPE = "SQLSERVER";
	   }
	   else if(DBTYPE.indexOf("MYSQL")>-1){
		   DBTYPE = "MYSQL";
	   }
	   return DBTYPE;
   }
   //获得tablexml文件路径
   private static String tablesDataSourceDirectoryPath = null;
   public static String getTablesDataSourceDirectoryPath()
   {
	   return tablesDataSourceDirectoryPath;
   }
   //获得chartxml文件路径
   private static String chartsDataSourceDirectoryPath = null;
   public static String getChartsDataSourceDirectoryPath()
   {
	   return chartsDataSourceDirectoryPath;
   }
   //获得chartflt文件路径
   private static String chartsFltSourceDirectoryPath = null;
   public static String getFltSourceDirectoryPath()
   {
	   return chartsFltSourceDirectoryPath;
   }
   
   //获取菜单的装载方式
   private static String MENUTYPE = getConfigValue("MENUTYPE").toUpperCase();
   public static String getMenuType() 
   {
	  return MENUTYPE;
   }
   ////获取upload文件上传路径
   private static String fileUploadDataPath = getConfigValue("uploads_store_path");
   public static String getFileUploadDataPath()
   {
	   return fileUploadDataPath;
   }
   
   ////获取word模板相对文件上传路径
   private static String wordTemplatesRelativeStorePath = getConfigValue("word_templates_relative_store_path");
   public static String getWordTemplatesRelativeStorePath()
   {
	   return wordTemplatesRelativeStorePath;
   }
   ////获取word模板相对文件上传路径
   private static String wordSourceTemplatesRelativeStorePath = getConfigValue("word_source_templates_relative_store_path");
   public static String getWordSourceTemplatesRelativeStorePath()
   {
	   return wordSourceTemplatesRelativeStorePath;
   }
   ////获取word生成文件相对文件上传路径
   private static String wordFilesRelativeStorePath = getConfigValue("word_file_relative_store_path");
   public static String getWordFilesRelativeStorePath()
   {
	   return wordFilesRelativeStorePath;
   }
   //网银相对上传路径
   private static String ebankExcelRelativeStorePath=getConfigValue("ebank_excel_relative_store_path");
   public static String getEbankExcelRelativeStorePath()
   {
	   return ebankExcelRelativeStorePath;
   }
 //网银模板的相对上传路径
   private static String ebankExcelTemplatesRelativeStorePath=getConfigValue("ebank_excel_templates_relative_store_path");
   public static String getEbankExcelTemplatesRelativeStorePath()
   {
	   return ebankExcelTemplatesRelativeStorePath;
   }
   //（集群或者本地模式）
   private static BaseFileOperation<? extends Object> baseFileOperation = null;
   private static final String  type = getConfigValue("uploads_store_type").toUpperCase();
   private static final String  charset = getConfigValue("uploads_store_charset");
   private static final String  host = getConfigValue("uploads_store_host");
   private static final String  port_ = getConfigValue("uploads_store_port");
   private static final String  username = getConfigValue("uploads_store_username");
   private static final String  password = getConfigValue("uploads_store_key");
   private static int   port = port_.isEmpty() ?  21 : Integer.parseInt(port_);
   
   public  static BaseFileOperation<? extends Object> getFileUploadOperation()
   {
	   if(null == baseFileOperation)
	   {

		   switch(FileStoreType.valueOf(type)){
		   		case LOCAL :{
		   			baseFileOperation = new LocalFileOperationImpl();
		   			break;
		   		}
		   		case FTP :{
		   			baseFileOperation = new FtpFileOperationImpl(host,port,username,password,charset);
		   			break;
		   		}
		   		case SMB :{
		   			baseFileOperation = new SmbFileOperationImpl(host,username,password);
		   			break;
		   		}
		   }
	   }
	   return baseFileOperation;
   }
   //获取不检测的硬盘号
   private static String noCheckDiskNumbers = getConfigValue("noCheckDiskNumbers");
   public static String getNoCheckDiskNumbers()
   {
	   return noCheckDiskNumbers;
   }
   //获取公司名称
   private static String companyName = getConfigValue("companyName");
   public static String getCompanyName()
   {
	   return companyName;
   }
   //设置更新密码的时间
   private static int updatePasswordDays = Integer.parseInt(getConfigValue("updatekeyDays"));
   public static int getUpdatePasswordDays()
   {
	   return updatePasswordDays;
   }
   private static boolean isNeedUpdatePermissionCache = false;
   public static void setNeedUpdatePermissionCache(boolean isNeed)
   {
	   isNeedUpdatePermissionCache = isNeed;
   }
   public static boolean isNeedUpdatePermissionCache()
   {
	   return isNeedUpdatePermissionCache ;
   }
   //sso
   private static String casServer = getConfigValue("sso.casServer");
   public  static String getCasServer(){
	   return casServer;
   }
   private static String localClient = getConfigValue("sso.localClient");
   public  static String getLocalClient(){
	   return localClient;
   }
}
