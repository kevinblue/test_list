package com.tenwa.kernal.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.tags.MessageTag;


public class WebUtil 
{
    private static ApplicationContext applicationContext = null;
    private static StdSchedulerFactory schedulerFactory = null;
    private static Map<String,Map<String,String>> allXMLTable = null;
    private static Map<String,Map<String,String>> allXMLChart = null;
    private static Map<Locale,Map<String, String>>      userOwnedMenusJson = null;
    private static Map<String,List<Map<String,String>>> userOwnedMenus             = null;
    private static Map<String,List<Map<String,String>>> userOwnedLeafMenus         = null;
    private static Map<String,List<Map<String,String>>> userOwnedResources         = null;
    private static Map<String,List<Map<String,String>>> userOwnedActions           = null;
    private static Map<String,List<Map<String,String>>> userOwnedWorkflowStarts    = null;
    private static Map<String,List<Map<String,String>>> userOwnedWorkflowViews     = null;
    private static Map<String,List<String>>             resourceOwnedUsers         = null;
    private static ServletContext                       servletContext             = null;  
    private static Locale defaultLocal = null;
    private static HttpSession localSession = null;
    private static Map<String, Locale> userSessions = new HashMap<String, Locale>();
    private static String serverMac = null;
    
    static{
    	WebUtil.setServerMac(WebUtil.getServerMacStr());
    }
    
    
	public static Map<String, Locale> getUserSessions() {
		return userSessions;
	}
	public static void setUserSessions(Map<String, Locale> userSessions) {
		WebUtil.userSessions = userSessions;
	}
	public static HttpSession getLocalSession() {
		return localSession;
	}
	public static void setLocalSession(HttpSession localSession) {
		WebUtil.localSession = localSession;
	}
	public static Locale getDefaultLocal() {
		return defaultLocal;
	}
	public static void setDefaultLocal(Locale defaultLocal) {
		WebUtil.defaultLocal = defaultLocal;
	}
	
	public static ServletContext getServletContext() {
		return servletContext;
	}
	public static void setServletContext(ServletContext servletContext) {
		WebUtil.servletContext = servletContext;
	}
	
	public static Map<String, List<String>> getResourceOwnedUsers() {
		return resourceOwnedUsers;
	}
	/*
	public static void setUserOwnedMenus(Map<String, List<Map<String,String>>> userOwnedMenus) {
		WebUtil.userOwnedMenus = userOwnedMenus;
	}
	public static void setUserOwnedResources(
			Map<String, List<Map<String,String>>> userOwnedResources) {
		WebUtil.userOwnedResources = userOwnedResources;
	}
	public static void setUserOwnedActions(Map<String, List<Map<String,String>>> userOwnedActions) {
		WebUtil.userOwnedActions = userOwnedActions;
	}
	public static void setUserOwnedWorkflowStarts(
	Map<String, List<Map<String, String>>> userOwnedWorkflowStarts) {
	WebUtil.userOwnedWorkflowStarts = userOwnedWorkflowStarts;
	}
	public static void setUserOwnedWorkflowViews(
		Map<String, List<Map<String, String>>> userOwnedWorkflowViews) {
	WebUtil.userOwnedWorkflowViews = userOwnedWorkflowViews;
	}
	public static void setResourceOwnedUsers(
			Map<String, List<String>> resourceOwnedUsers) {
		WebUtil.resourceOwnedUsers = resourceOwnedUsers;
	}
		public static void setUserOwnedMenusJson(
			Map<Locale, Map<String, String>> userOwnedMenusJson) {
		WebUtil.userOwnedMenusJson = userOwnedMenusJson;
	}*/
	public static Map<String, List<Map<String,String>>> getUserOwnedMenus() {
		return userOwnedMenus;
	}
	public static Map<String, List<Map<String,String>>> getUserOwnedResources() {
		return userOwnedResources;
	}
	public static Map<String, List<Map<String,String>>> getUserOwnedActions() {
		return userOwnedActions;
	}
	public static Map<String, List<Map<String, String>>> getUserOwnedWorkflowStarts() {
		return userOwnedWorkflowStarts;
	}
	public static Map<String, List<Map<String, String>>> getUserOwnedWorkflowViews() {
		return userOwnedWorkflowViews;
	}


	private static String webContextPath = "";
	private static String webContextRealPath = "";
	private static MessageSource messageSource = null;
	private static LocaleResolver localeResolver = null;
	private static Locale sessionLocale  = null;
	public  static final String EXCEPTION_FOR_MAX_FILESIZE_KEY = "EXCEPTION_FOR_MAX_FILESIZE";
	
	public static void setApplicationContext(ApplicationContext applicationContext) 
	{
		WebUtil.applicationContext = applicationContext;
		messageSource  = (MessageSource)applicationContext.getBean("messageSource");
		localeResolver = (LocaleResolver)applicationContext.getBean("localeResolver");
	}
	public static ApplicationContext getApplicationContext()
	{
	   return applicationContext;
	}
	public static void setWebContextPath(String webContextPath) {
		WebUtil.webContextPath = webContextPath;
	}
	public static String getWebContextPath() {
		return webContextPath;
	}
	public static void setWebContextRealPath(String webContextRealPath) {
		WebUtil.webContextRealPath = webContextRealPath;
	}
	public static String getWebContextRealPath() {
		return webContextRealPath;
	}
	/**
	 * @param allXMLTable the allXMLTable to set
	 */
	public static void setAllXMLTable(Map<String,Map<String,String>> allXMLTable) {
		WebUtil.allXMLTable = allXMLTable;
	}
	/**
	 * @return the allXMLTable
	 */
	public static Map<String,Map<String,String>> getAllXMLTable() {
		return allXMLTable;
	}
	
	 /**
	 * @param schedulerFactory the schedulerFactory to set
	 **/
	
	public static void setSchedulerFactory(StdSchedulerFactory schedulerFactory) {
		WebUtil.schedulerFactory = schedulerFactory;
	}
	public static String getMessageWithDefault(String resolvedCode,String defaultText){
		return getMessageByLocale(resolvedCode,null,defaultText,(Locale)WebUtil.getUserSessions().get(SecurityUtil.getPrincipal().getId()));
	}
	 /**
	 * schedulerFactoryh
	 * @return the schedulerFactory
	 * @since 1.0.0
	 **/
	
	public static StdSchedulerFactory getSchedulerFactory() {
		return schedulerFactory;
	}
	
	 /**
	 * @param allXMLChart the allXMLChart to set
	 **/
	
	public static void setAllXMLChart(Map<String,Map<String,String>> allXMLChart) {
		WebUtil.allXMLChart = allXMLChart;
	}
	
	 /**
	 * allXMLChart
	 * @return the allXMLChart
	 * @since 1.0.0
	 **/
	
	public static Map<String,Map<String,String>> getAllXMLChart() {
		return allXMLChart;
	}
	
	
	
	public static Map<Locale, Map<String, String>> getUserOwnedMenusJson() {
		return userOwnedMenusJson;
	}

	/**
	 * @method getMessage(根据提供的code获取国际化信息)
	 * @param resolvedCode 解析国际化的code代码
	 * @param arguments    参数用于填充{0}{1}{2}....
	 * @param resolvedText 如果国际化文件中不存在相应code则用text的值代替
	 * @param request      请求的request对象，如果为空则使用系统默认的locale
	 * @return
	 * @returnType String
	 * @exception  
	 * @since      1.0.0
	 **/
	public static String getMessage(String resolvedCode,Object arguments,String resolvedText,HttpServletRequest request)
	{
		//modify by tracywindy updated 2013-03-28
		Locale locale =  null;
		if(null == request){
			locale = Locale.getDefault();
		}
		else{
			locale = localeResolver.resolveLocale(request);
		}
		return getMessageByLocale(resolvedCode,arguments,resolvedText,locale );
	}
	public static LocaleResolver getLocaleResolver() {
		return localeResolver;
	}
	public static String getMessageByLocale(String resolvedCode,Object arguments,String resolvedText,Locale locale ){
		if (resolvedCode != null || resolvedText != null) {
			// We have a code or default text that we need to resolve.
			Object[] argumentsArray = resolveArguments(arguments);
			if (resolvedText != null) {
				// We have a fallback text to consider.
				return messageSource.getMessage(
						resolvedCode, argumentsArray, resolvedText, locale);
			}
			else {
				// We have no fallback text to consider.
				return messageSource.getMessage(
						resolvedCode, argumentsArray, locale);
			}
		}
		// All we have is a specified literal text.
		return resolvedText;
	}
	
	public static String getMessage(String resolvedCode,String... arguments){
		return getMessageByLocale(resolvedCode,arguments,null,WebUtil.getSessionLocale());
	}
		
	/**
	 * Resolve the given arguments Object into an arguments array.
	 * @param arguments the specified arguments Object
	 * @return the resolved arguments as array
	 * @throws JspException if argument conversion failed
	 * @see #setArguments
	 */
	@SuppressWarnings("unchecked")
	private static Object[] resolveArguments(Object arguments)
	{
		if (arguments instanceof String) {
			String[] stringArray = StringUtils.delimitedListToStringArray((String) arguments, MessageTag.DEFAULT_ARGUMENT_SEPARATOR);
		    return stringArray;
		}
		else if (arguments instanceof Object[]) {
			return (Object[]) arguments;
		}
		else if (arguments instanceof Collection) {
			return ((Collection) arguments).toArray();
		}
		else if (arguments != null) {
			// Assume a single argument object.
			return new Object[] {arguments};
		}
		else {
			return null;
		}
	}
	public static void setSessionLocale(Locale sessionLocale) {
		WebUtil.sessionLocale = sessionLocale;
	}
	public static Locale getSessionLocale() {
		return sessionLocale;
	}
	public static void setPermissionCaches(		        
			Map<Locale,Map<String, String>>      userOwnedMenusJson 	 ,
	        Map<String,List<Map<String,String>>> userOwnedMenus      	 ,
	        Map<String,List<Map<String,String>>> userOwnedLeafMenus      ,
	        Map<String,List<Map<String,String>>> userOwnedResources  	 ,
	        Map<String,List<Map<String,String>>> userOwnedActions        ,
	        Map<String,List<Map<String,String>>> userOwnedWorkflowStarts ,
	        Map<String,List<Map<String,String>>> userOwnedWorkflowViews  ,
	        Map<String,List<String>>             resourceOwnedUsers      ,
	        Map<String, String> 				 userOwnedWorkflowStartSqlIdsStr, 
	        Map<String, String> 				 userOwnedWorkflowViewSqlIdsStr
	){
		WebUtil.userOwnedMenusJson      = userOwnedMenusJson;
		WebUtil.userOwnedMenus          = userOwnedMenus;
		WebUtil.userOwnedLeafMenus      = userOwnedLeafMenus;
		WebUtil.userOwnedResources      = userOwnedResources;
		WebUtil.userOwnedActions        = userOwnedActions;
		WebUtil.userOwnedWorkflowStarts = userOwnedWorkflowStarts;
		WebUtil.userOwnedWorkflowViews  = userOwnedWorkflowViews;
		WebUtil.resourceOwnedUsers      = resourceOwnedUsers;
		servletContext.setAttribute("userOwnedWorkflowStartSqlIdsStr", userOwnedWorkflowStartSqlIdsStr);
		servletContext.setAttribute("userOwnedWorkflowViewSqlIds", userOwnedWorkflowViewSqlIdsStr);
	}
	public static Map<String,List<Map<String,String>>> getUserOwnedLeafMenus() {
		return userOwnedLeafMenus;
	}
	public static Boolean isEnglish(){
		if(((Locale)WebUtil.getUserSessions().get(SecurityUtil.getPrincipal().getId())).getLanguage().equals("en")){
			return true;
		}
		return false;
	}
	
	public static String getMac(String ip){
		String mac = "";
		try{
			Process pro = Runtime.getRuntime().exec("nbtstat -A "+ip);
			InputStream is = pro.getInputStream();
			
			BufferedReader rd = new  BufferedReader(new InputStreamReader(is, "GBK"));
			String message = "";
			while((message = rd.readLine()) != null ){
				if( message.indexOf("MAC") >= 0){
					//mac = message.substring(message.length()-17); 这是其中的一种方式
					StringTokenizer token = new StringTokenizer(message, "=");
					for( ; token.hasMoreElements();){
						String info = token.nextToken().trim();
						if(info.length() > 0 ){
							mac = info;
						}
					}
				}
			}
			rd.close();
			is.close();
			pro.destroy();
		}catch(Exception e){
			//System.out.println("获取不到mac地址。。。");
			return null;
		}
		return mac;
	}
	public static String getServerMacStr(){
		String mac = null;
		try{
			Process pro = Runtime.getRuntime().exec("cmd /c ipconfig/all");
			InputStream is =  pro.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String message = br.readLine();
			int index = -1;
			while(null != message){
				if((index = message.indexOf("Physical Address")) > 0 || (index = message.indexOf("物理地址")) > 0){
					mac += message.substring(index +36).trim()+",";
				}
				message = br.readLine();
			}
			br.close();
			is.close();
			pro.destroy();
		}catch(Exception e){
			System.out.println("获取不到mac地址。。。");
			return null;
		}
		if(null!=mac&&mac.trim().length()>0)
		mac = mac.substring(0, mac.length()-1);
		return mac;
	}
	public static String getServerMac() {
		return serverMac;
	}
	public static void setServerMac(String serverMac) {
		WebUtil.serverMac = serverMac;
	}
	
	
}
