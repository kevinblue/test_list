
 /**
 * 项目名称：    系统名称
 * 包名：              com.ad
 * 文件名：         AdUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-3-下午01:29:50
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.ad;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.tenwa.business.entity.ad.AdConfig;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     AdUtil
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-12-3 下午01:29:50
 * 修改备注：
 * @version 1.0.0
 **/

public class AdUtil {
	
	
	 /**
	 * @method getADInfoList 获取所有的ad域用户信息
	 * @param ou ldap的AD域服务器OU
	 * @return
	 * @throws Exception
	 * @returnType List<Map<String,String>>
	 * @exception  
	 * @since      1.0.0
	 * 10.1.128.3'：AD域服务器id地址。
	 * 389：AD域服务器端口。
	 * fin.sso：AD域服务器用户名。
	 * YXfin!#%&'：AD域服务器用户密码。
	 * yx.yuexiu.com：AD域服务器domain。
	 * 越秀集团,下属集团,金融集团,下属公司,小额贷款公司：AD域服务器OU。
	 **/
	public static List<Map<String, String>> getADInfoList(String ou) throws Exception {
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
		List<AdConfig> adConfigList = baseService.findEntities(AdConfig.class);
		AdConfig adConfig = null;
		if(0 == adConfigList.size()){
		   adConfig = new AdConfig();
		   adConfig.setHost("10.1.128.3");
		   adConfig.setPort(389);
		   adConfig.setAdminName("fin.sso");
		   adConfig.setAdminKey("YXfin!#%&");
		   adConfig.setDomainName("yx.yuexiu.com");
		   adConfig.setOu("下属集团,金融集团,下属公司,融资租赁公司");
		   baseService.saveEntity(adConfig);
		}else{
			adConfig = adConfigList.get(0);
		}
		if(null == ou){
			ou = adConfig.getOu();
		}
		String host = adConfig.getHost();
		String port = String.valueOf(adConfig.getPort());
		String adminName = adConfig.getAdminName();
		String adminKey = adConfig.getAdminKey();
		String domainName = adConfig.getDomainName();
		
		String url = "ldap://" + host + ":" + port;
		String domain   = getDoamin(domainName);
		String formatOU = getFormatOU(ou);
		String formatDC = getFormatDoamin(domainName);
		
		Hashtable<String,String> HashEnv = new Hashtable<String,String>();
		HashEnv.put(Context.PROVIDER_URL, url);
		HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别
		HashEnv.put(Context.SECURITY_PRINCIPAL, domain + "\\" + adminName); // domain\User
		HashEnv.put(Context.SECURITY_CREDENTIALS, adminKey); // AD Password
		HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
		
		// 域节点
		String searchBase = formatOU + "," + formatDC;
		// LDAP搜索过滤器类
		String searchFilter = "objectClass=User";
		// 搜索控制器
		SearchControls searchCtls = new SearchControls();
		// 创建搜索控制器
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Specify
		// 定制返回属性：姓名，移动电话，部门，登录账户，email
		String returnedAtts[] = { "name", "mobile", "department", "sAMAccountName", "mail" }; 
		
		searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
		List<Map<String,String>> ADInfoList = new ArrayList<Map<String,String>>();
		try {
			LdapContext ctx = new InitialLdapContext(HashEnv, null);
			// 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
			NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);
			// 遍历结果集
			while ( answer.hasMoreElements() ) {
				// 得到符合搜索条件的DN
				SearchResult sr = (SearchResult) answer.next();
		        Attributes attrs = sr.getAttributes();
		        if ( attrs != null ) {
		        	try {
		        		Map<String,String> m = new HashMap<String,String>();
		            	for ( NamingEnumeration<? extends Attribute> ne = attrs.getAll(); ne.hasMore(); ) {
		            		Attribute attr = ne.next();
		            		Enumeration<?> values = attr.getAll();
		            		if (values != null) {
		            			while (values.hasMoreElements()) {
		            				Object value = values.nextElement();
		            				m.put( attr.getID(),  (null == value) ? "" : String.valueOf(value) );
		            				//System.out.println( attr.getID()+":"+value);
		            				break;
		            			}
		            		}
		            	}
		            	ADInfoList.add(m);
		            } catch (NamingException e) {
		              System.err.println("Throw Exception : " + e);
		            }
		        }
			}
			ctx.close();
		} catch (NamingException e) {
            System.err.println("Throw Exception : " + e);
        }
		return  ADInfoList;
	}
	private  static String getFormatDoamin(String domainName) {
		String[] splt = domainName.split("\\.");
		String realFormat = "";
		for (int i = 0; i < splt.length; i++) {
			if (!"".equals(splt[i].trim()))
				realFormat += "DC=" + splt[i].trim() + ",";
		}
		if (',' == realFormat.charAt(realFormat.length() - 1)) {
			realFormat = realFormat.substring(0, realFormat.length() - 1);
		}
		return realFormat;
	}
	private static String getFormatOU(String ou) {
		String[] splt = ou.split(",");
		String realFormat = "";
		for (int i = splt.length - 1; i >= 0; i--) {
			realFormat = realFormat + "OU=" + splt[i].trim() + ",";
		}
		if (',' == realFormat.charAt(realFormat.length() - 1)) {
			realFormat = realFormat.substring(0, realFormat.length() - 1);
		}
		return realFormat;
	}
	private static String getDoamin(String domainName) {
		String[] splt = domainName.split("\\.");
		for (int i = 0; i < splt.length; i++) {
			if (!"".equals(splt[i].trim())){
				return splt[i].trim();
			}
		}
		return "";
	}
}
