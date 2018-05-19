package com.tenwa.kernal.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.tags.MessageTag;

import com.tenwa.business.entity.PermissionCache;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.enums.PermissionEnum;


public class new_WebUtil 
{
    private static ApplicationContext applicationContext = null;
    private static StdSchedulerFactory schedulerFactory = null;
    private static Map<String,Map<String,String>> allXMLTable = new HashMap<String,Map<String,String>>();
    private static Map<String,Map<String,String>> allXMLChart = new HashMap<String,Map<String,String>>();
    private static Map<String,Map<String, String>>      userOwnedMenusJson = new HashMap<String,Map<String,String>>();
    private static Map<String,List<Map<String,String>>> userOwnedResources         = new HashMap<String,List<Map<String,String>>>();
    private static Map<String,List<Map<String,String>>> userOwnedActions           = new HashMap<String,List<Map<String,String>>>();
    private static Map<String,List<Map<String,String>>> userOwnedWorkflowStarts    = new HashMap<String,List<Map<String,String>>>();
    private static Map<String,List<Map<String,String>>> userOwnedWorkflowViews     = new HashMap<String,List<Map<String,String>>>();
    private static Map<String,List<String>>             resourceOwnedUsers         = new HashMap<String,List<String>>();
    private static Map<String, String> userOwnedWorkflowStartSqlIdsStr             = new HashMap<String, String>();
    private static Map<String, String> userOwnedWorkflowViewSqlIdsStr			   = new HashMap<String, String>();
    private static BaseService         baseService			                       = null;
    private static ObjectMapper mapper = new ObjectMapper();
    
    public static void initBaseSerivice(){
    	if(null == baseService){
    		baseService = (BaseService)applicationContext.getBean("baseService");
    	}
    }
    public static String getPermissionCachedJsonByKey(PermissionEnum permissionEnum){
    	initBaseSerivice();
    	String hsql = "from PermissionCache where key = ?";
    	String permissionCachedJson = null;
    	try {
    		permissionCachedJson =  ((PermissionCache)baseService.findResultsByHSQL(hsql, permissionEnum.getCode()).get(0)).getPermissionCachedJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return permissionCachedJson;
    }
	@SuppressWarnings("unchecked")
	public static Map<String,Map<String,String>> getAllXMLTable() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.AllXMLTable);
		try {
			return (Map<String,Map<String,String>>)mapper.readValue(permissionCachedJson, new HashMap<String,Map<String,String>>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allXMLTable;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Map<String,String>> getAllXMLChart() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.AllXMLChart);
		try {
			return (Map<String,Map<String,String>>)mapper.readValue(permissionCachedJson, new HashMap<String,Map<String,String>>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allXMLChart;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>> getUserOwnedMenusJson() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.UserOwnedMenusJson);
		try {
			return (Map<String,Map<String,String>>)mapper.readValue(permissionCachedJson, new HashMap<String,Map<String,String>>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userOwnedMenusJson;
	}
	 
	@SuppressWarnings("unchecked")
	public static Map<String, List<String>> getResourceOwnedUsers() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.ResourceOwnedUsers);
		try {
			return (Map<String,List<String>>)mapper.readValue(permissionCachedJson, new HashMap<String,List<String>>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resourceOwnedUsers;
	}
	@SuppressWarnings("unchecked")
	public static Map<String, List<Map<String,String>>> getUserOwnedResources() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.UserOwnedResources);
		try {
			return (Map<String, List<Map<String,String>>>)mapper.readValue(permissionCachedJson, new HashMap<String, List<Map<String,String>>>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userOwnedResources;
	}
	@SuppressWarnings("unchecked")
	public static Map<String, List<Map<String,String>>> getUserOwnedActions() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.UserOwnedActions);
		try {
			return (Map<String, List<Map<String,String>>>)mapper.readValue(permissionCachedJson, new HashMap<String, List<Map<String,String>>>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userOwnedActions;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, List<Map<String, String>>> getUserOwnedWorkflowStarts() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.UserOwnedWorkflowStarts);
		try {
			return (Map<String, List<Map<String,String>>>)mapper.readValue(permissionCachedJson, new HashMap<String, List<Map<String,String>>>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userOwnedWorkflowStarts;
	}
	@SuppressWarnings("unchecked")
	public static Map<String, String> getUserOwnedWorkflowStartSqlIdsStr() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.UserOwnedWorkflowStartSqlIdsStr);
		try {
			return (Map<String,String>)mapper.readValue(permissionCachedJson, new HashMap<String,String>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userOwnedWorkflowStartSqlIdsStr;
	}
	@SuppressWarnings("unchecked")
	public static Map<String, List<Map<String, String>>> getUserOwnedWorkflowViews() {
		String permissionCachedJson = getPermissionCachedJsonByKey(PermissionEnum.UserOwnedWorkflowViews);
		try {
			return (Map<String, List<Map<String,String>>>)mapper.readValue(permissionCachedJson, new HashMap<String, List<Map<String,String>>>().getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userOwnedWorkflowViews;
	}
	public static Map<String, String> getUserOwnedWorkflowViewSqlIdsStr() {
		return userOwnedWorkflowViewSqlIdsStr;
	}

	private static String webContextPath = "";
	private static String webContextRealPath = "";
	private static MessageSource messageSource = null;
	private static LocaleResolver localeResolver = null;
	private static Locale sessionLocale  = null;
	public  static final String EXCEPTION_FOR_MAX_FILESIZE_KEY = "EXCEPTION_FOR_MAX_FILESIZE";
	
	public static void setApplicationContext(ApplicationContext applicationContext) 
	{
		new_WebUtil.applicationContext = applicationContext;
		messageSource  = (MessageSource)applicationContext.getBean("messageSource");
		localeResolver = (LocaleResolver)applicationContext.getBean("localeResolver");
		 
	}
	public static ApplicationContext getApplicationContext()
	{
	   return applicationContext;
	}
	public static void setWebContextPath(String webContextPath) {
		new_WebUtil.webContextPath = webContextPath;
	}
	public static String getWebContextPath() {
		return webContextPath;
	}
	public static void setWebContextRealPath(String webContextRealPath) {
		new_WebUtil.webContextRealPath = webContextRealPath;
	}
	public static String getWebContextRealPath() {
		return webContextRealPath;
	}
	public static void setSchedulerFactory(StdSchedulerFactory schedulerFactory) {
		new_WebUtil.schedulerFactory = schedulerFactory;
	}
	public static StdSchedulerFactory getSchedulerFactory() {
		return schedulerFactory;
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
		new_WebUtil.sessionLocale = sessionLocale;
	}
	public static Locale getSessionLocale() {
		return sessionLocale;
	}
}
