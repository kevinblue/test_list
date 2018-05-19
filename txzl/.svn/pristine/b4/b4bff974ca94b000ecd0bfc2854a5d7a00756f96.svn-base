package org.springframework.security.web.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.SSODecoder;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.AuthenticatedSessionStrategy;
import org.springframework.security.web.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.LoginInfo;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.service.AclService;
import com.tenwa.kernal.i18n.LocaleEnum;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.PermissionUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;


public abstract class AbstractAuthenticationProcessingFilter extends GenericFilterBean
    implements ApplicationEventPublisherAware, MessageSourceAware
{

    protected AbstractAuthenticationProcessingFilter(String defaultFilterProcessesUrl)
    {
        authenticationDetailsSource = new WebAuthenticationDetailsSource();
        messages = SpringSecurityMessageSource.getAccessor();
        rememberMeServices = null;
        continueChainBeforeSuccessfulAuthentication = false;
        sessionStrategy = new NullAuthenticatedSessionStrategy();
        allowSessionCreation = true;
        successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        failureHandler = new SimpleUrlAuthenticationFailureHandler();
        filterProcessesUrl = defaultFilterProcessesUrl;
    }

    public void afterPropertiesSet()
    {
        Assert.hasLength(filterProcessesUrl, "filterProcessesUrl must be specified");
        Assert.isTrue(UrlUtils.isValidRedirectUrl(filterProcessesUrl), (new StringBuilder()).append(filterProcessesUrl).append(" isn't a valid redirect URL").toString());
        Assert.notNull(authenticationManager, "authenticationManager must be specified");
        if(rememberMeServices == null)
            rememberMeServices = new NullRememberMeServices();
    }
    public boolean checkUserInfo(String username,HttpServletRequest request, HttpServletResponse response,boolean isSSO){
    	String requestURI = request.getRequestURI();
    	HttpSession session = request.getSession(false);
    	boolean dealCondition = isSSO ? (( requestURI.equals(session.getAttribute("currentRequestFirstPageURI")))) : (requiresAuthentication(request, response));
    	String checkUserName = StringUtil.nullToString(username);
    	AclService aclService = (AclService)WebUtil.getApplicationContext().getBean("aclService");
    	if( (!checkUserName.isEmpty() ) && dealCondition )
        {
        	User user = null;
			try {
				user = aclService.findUserByUserName(checkUserName);
			} catch (Exception e) {
				e.printStackTrace();
			}
        	if(null == user)
        	{
        		try {
					request.getRequestDispatcher("/login_old.jsp?login_username_error=true").forward(request,response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
        		return false;
        	}else{
        		try {
        			// 校验验证码
        			String isVerifyCheck = ResourceUtil.getConfigValue("login_verification_code_check");
        			if("true".equals(isVerifyCheck)){
        				String sv = (String)session.getAttribute("VALIDATE_NUMBER");
        				String cv = request.getParameter("validatenum");
        				if(cv == null || sv == null || !sv.toLowerCase().equals(cv.toLowerCase())){
        					request.setAttribute("login_verification_error", "true");
        					request.getRequestDispatcher("/login_old.jsp?login_verification_error=true").forward(request,response);
        					return false;
        				}
        			}
        			
					if(null == PermissionUtil.getUserMenusJsonString(LocaleEnum.valueOf("zh_CN").getLocale(),user.getUsername())){
						session.setAttribute("isNoPermission", "true");
					}else{
						session.setAttribute("isNoPermission", "false");
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	//检查用户的mac地址
        	//校验MAC地址
    		String isMacCheck = ResourceUtil.getConfigValue("login_mac_address_check");
    		if("true".equals(isMacCheck)){
    			String ip =  request.getRemoteAddr();
    			String mac = WebUtil.getServerMac();
    			if(!ip.equals("127.0.0.1")){
    				mac = WebUtil.getMac(ip);
    				logger.info("远程登录用户的mac地址为："+mac);
    			}
    			/*if(!"admin".equalsIgnoreCase(username) && !"administrator".equalsIgnoreCase(username)){
    				try {
    					if(user.getUsermac() == null || "".equals(user.getUsermac().trim())){
    						// 初次登陆，不管是不是管理员还是其他人员
    						user.setUsermac(mac);
    						aclService.saveOrUpdateEntity(user);
    					} else if(mac.indexOf(user.getUsermac()) < 0 ){
    						request.setAttribute("login_usermac_error", "true");
    						request.getRequestDispatcher("/login.jsp?login_usermac_error=true").forward(request,response);
    						return false;
    					}
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}*/
    		}
    		
        }
    	
    	
    	
    	if("true".equals(session.getAttribute("isNoPermission"))){
			String returnInfo = ("该账号为新增用户/登录账号发生变更，暂不具有系统分配的任何权限，请联系管理员分配/同步权限！");
			try {
				this.ajaxReturn(response, returnInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
    	}
    	User currentUser = SecurityUtil.getPrincipal();
    	/*//修改单点登录，客户认证平台不支持注销问题导致上一次session未失效。
    	boolean isSameUser = false;
    	if((null != currentUser) && requiresAuthentication(request, response) ) {
    		isSameUser = currentUser.getUsername().equalsIgnoreCase(checkUserName.trim());
    		if(!isSameUser){
    			//删除
    			String returnInfo = ("<script type='text/javascript'>window.alert('该浏览器已被用户"+currentUser.getRealname()+"（"+currentUser.getUsername()+"）"+"登录！');try{window.top.opener=null;window.top.open('','_self');window.top.close();}catch(e){window.top.close();}</script><body>该窗口登录用户已被强制退出,该浏览器不支持自动关闭窗口,请手动关闭窗口。</body>");
    			try {
    				this.ajaxReturn(response, returnInfo);
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			return false;
    		}
    	}*/
    	
    	
    	String isUserCountCheck = ResourceUtil.getConfigValue("login_user_count_check");
    	if("true".equals(isUserCountCheck)){
    		if(null != currentUser && !requiresAuthentication(request, response) && !QueryUtil.isAjaxRequest(request) ){
        		List<LoginInfo> li_list = null;
    			try {
    				li_list = aclService.findResultsByHSQL("select li from LoginInfo li where li.loginUser.id = ?", currentUser.getId());
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
        	    if(0 < li_list.size() && session.getId().equals(li_list.get(0).getRemovedSessionId())){
        	    	//if(!session.getId().equals(li_list.get(0).getSessionId())){
    	    	    	String returnInfo = ("<script type='text/javascript'>alert('该窗口登录用户已被强制退出！');try{window.top.opener=null;window.top.open('','_self');window.top.close();}catch(e){window.top.close();}</script><body>该窗口登录用户已被强制退出,该浏览器不支持自动关闭窗口,请手动关闭窗口。</body>");
    	    			try {
    	    				this.ajaxReturn(response, returnInfo);
    	    			} catch (IOException e) {
    	    				e.printStackTrace();
    	    			}
    	    			return false;
        	    	//}
        	    }
        	}
    	}
    	return true;
    }
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)req;
    	String username = request.getParameter("j_username");
        HttpServletResponse response = (HttpServletResponse)res;
        //if(!ResourceUtil.isDebug())
        {
        	HttpSession session = request.getSession(false);
        	session.removeAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
        }
        //iframe引起的内部cookie丢失
        response.setHeader("P3P","CP=CAO PSA OUR");
        ///String requestURI = request.getRequestURI();
        //检查用户是否具有权限开始
		if(!this.checkUserInfo(username, request,response,false))return;
		//检查用户是否具有权限结束
		HttpSession session = request.getSession(false);
        if(null == session.getAttribute("currentRequestFirstPageURI")){
        	session.setAttribute("currentRequestFirstPageURI", request.getRequestURI());
        }
        if(!requiresAuthentication(request, response))
        {
        	Object SSOUserNameObj =  session.getAttribute("SSOUserName");//new Base64().getDecodeSSOUserName(request);
        	
        	if(null == SSOUserNameObj){
        		SSOUserNameObj= SSODecoder.getInstance().getDecodeSSOUserName(request);
        		session.setAttribute("SSOUserName", SSOUserNameObj);
        	}
        	String SSOUserName    =  (null == SSOUserNameObj) ? null : SSOUserNameObj.toString().trim();
        	//检查SSO用户是否具有权限开始
    		if(!this.checkUserInfo(SSOUserName, request,response,true))return;
    		//检查SSO用户是否具有权限结束
        	Object isHasUserLoginedUserNameObj = session.getAttribute("isHasUserLoginedUserName");
        	String isHasUserLoginedUserName = (null == isHasUserLoginedUserNameObj) ? null : isHasUserLoginedUserNameObj.toString().trim();
        	if(
        		  (null == SSOUserName)
        		||((null != isHasUserLoginedUserName)&&(isHasUserLoginedUserName.toUpperCase().equals(SSOUserName.toUpperCase())))
        		||((null == isHasUserLoginedUserName)&&(!( request.getRequestURI().equals(session.getAttribute("currentRequestFirstPageURI")))))
            ){
//                //检查用户是否具有权限
//        		if(!this.checkUserInfo(SSOUserName, request,response))return;
        		chain.doFilter(request, response);
                return;
        	}
        }
        if(logger.isDebugEnabled())
            logger.debug("Request is to process authentication");
        Authentication authResult;
        try
        {
            authResult = attemptAuthentication(request, response);
            if(authResult == null)
                return;
        }/*catch(UserNotSynchronizedException userNotSynchronizedException){
        	SecurityContextHolder.clearContext();
        	  String returnInfo = (("<script type='text/javascript'>alert('该用户没有权限登陆租赁业务系统！');window.top.location.href='"+WebUtil.getWebContextPath()+"/j_spring_cas_security_logout';</script>"));
	  			try {
	  				this.ajaxReturn(response, returnInfo);
	  			} catch (IOException e) {
	  				e.printStackTrace();
	  			}
              return;
        }*/
        catch(AuthenticationException failed)
        {
             unsuccessfulAuthentication(request, response, failed);
             return;
            /*SecurityContextHolder.clearContext();
      	    String returnInfo = (("<script type='text/javascript'>alert('该用户没有权限登陆租赁业务系统！');window.top.location.href='"+WebUtil.getWebContextPath()+"/j_spring_cas_security_logout';</script>"));
	  			try {
	  				this.ajaxReturn(response, returnInfo);
	  			} catch (IOException e) {
	  				e.printStackTrace();
	  			} return;
	  		*/
        }
        if(continueChainBeforeSuccessfulAuthentication)
            chain.doFilter(request, response);
        successfulAuthentication(request, response, authResult);
        User user = (User)SecurityUtil.getPrincipal();
        username = user.getUsername();
        session = request.getSession(false);
        session.setAttribute("isHasUserLoginedUserName",user.getUsername());
        session.setAttribute("login_userid", user.getId());
        session.setAttribute("login_userpassword", user.getId());
        session.setAttribute("login_username", user.getUsername());
        session.setAttribute("login_realname", user.getRealname());
        session.setAttribute("login_usertelephone", user.getTelephone());
        session.setAttribute("login_useremail", user.getEmail());
        session.setAttribute("currentAllAuthoritiesString",user.getAllAuthoritiesString());
        
        session.setAttribute("loginUser",user);
        List<String> userDeptList = new ArrayList<String>();
        String dealerCustInfoId     = "";
        String dealerCustInfoName   = "";
        for(UserDepartment userDept : user.getUserDepts()){
        	Department dept = userDept.getDept();
        	dept.toString();
        	userDeptList.add(dept.getId());
        	/*if("".equals(dealerCustInfoId)){
            	for(DealerDeptInfo ddi : dept.getDealerDeptInfos()){
            		DealerInfo dealerInfo = ddi.getDealerId();
            		if(null != dealerInfo){
            			dealerCustInfoId = dealerInfo.getId();
            			dealerCustInfoName = dealerInfo.getDealerName();
            		}
            	}
        	}*/
        	session.setAttribute("loginUserDeptObj", dept);
        }
        session.setAttribute("userDeptListStr", StringUtil.join(userDeptList, ","));
        session.setAttribute("userDeptListSqlStr", StringUtil.join(userDeptList, ",", "'", "'"));
        //代理商信息
		session.setAttribute("currentDealerCustInfoId", dealerCustInfoId);
		session.setAttribute("currentDealerCustInfoName",  dealerCustInfoName);
        
        Date lastUpdatePasswordDate = DateUtil.getTimeByFormat(StringUtil.empty2Other(user.getLastUpdatePasswordDate(),"1900-01-01"),"yyyy-MM-dd");
        int intervalDays = DateUtil.getIntervalDays(lastUpdatePasswordDate, new Date());
        String isForceUpdatePassword = (intervalDays >= ResourceUtil.getUpdatePasswordDays()) ? "true":"false";
        session.setAttribute("isForceUpdatePassword",isForceUpdatePassword);
        session.setAttribute("isOccuredUserLoginBySystem", "true");
        //设置session中的locale 
        //设置session中的locale 
        WebUtil.setSessionLocale(WebUtil.getLocaleResolver().resolveLocale(request));
        Locale sessionLocale = WebUtil.getDefaultLocal();
        if(null != user.getUserLocal()){
        	sessionLocale =  user.getUserLocal().getLocale();
        }
        session.setAttribute("login_userlanguage", sessionLocale);
        WebUtil.getUserSessions().put(user.getId(), sessionLocale);
        //WebUtil.setLocalSession(session);
       
        try{
        	PermissionUtil.saveSystemLang(sessionLocale);
        }catch(Exception e){
        	e.printStackTrace();
        }
        //更新登录状态
        AclService aclService = (AclService)WebUtil.getApplicationContext().getBean("aclService");
        
        String isHasExistUser = "false";
        List<LoginInfo> li_list = null;
        
		try {
			String sessionId = session.getId();
			li_list = aclService.findResultsByHSQL("select li from LoginInfo li where li.loginUser.id = ?", user.getId());
		    LoginInfo logInfo = null;
	        String currentTime = DateUtil.getSystemDateTime();
	        Calendar now = Calendar.getInstance();
	        now.add(Calendar.SECOND, session.getMaxInactiveInterval());
	        String expireTime  = DateUtil.getDateTime(now.getTime());
	        if(0 < li_list.size()){
	        	logInfo = li_list.get(0);
//	        	logInfo.setLoginTime(currentTime);
//	        	logInfo.setSessionId(sessionId);
//	        	logInfo.setExpireTime(expireTime);
	        	logInfo.setRemovedSessionId(null);
	        	aclService.updateEntity(logInfo);
	        	isHasExistUser = "true";
	        }else{
	        	logInfo = new LoginInfo();
	        	logInfo.setLoginUser(user);
	        	logInfo.setLoginTime(currentTime);
	        	logInfo.setSessionId(sessionId);
	        	logInfo.setExpireTime(expireTime);
	        	aclService.saveEntity(logInfo);
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(requiresAuthentication(request, response)){
	        session.setAttribute("isHasExistUser", isHasExistUser);
	        session.setAttribute("isHasExistUserId", user.getId());
	        session.setAttribute("isHasExistUserName", user.getUsername());
	        session.setAttribute("isHasExistUserRealName", user.getRealname());
		}
    }
	protected void ajaxReturn(HttpServletResponse response, String returnInfo) throws IOException {
		response.setContentType("text/html;charset=UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(returnInfo);
		out.flush();
		out.close();
	}
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response)
    {
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');
        if(pathParamIndex > 0)
            uri = uri.substring(0, pathParamIndex);
        if("".equals(request.getContextPath()))
            return uri.endsWith(filterProcessesUrl);
        else
            return uri.endsWith((new StringBuilder()).append(request.getContextPath()).append(filterProcessesUrl).toString());
    }

    public abstract Authentication attemptAuthentication(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws AuthenticationException, IOException, ServletException;

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
        throws IOException, ServletException
    {
        if(logger.isDebugEnabled())
            logger.debug((new StringBuilder()).append("Authentication success. Updating SecurityContextHolder to contain: ").append(authResult).toString());
        SecurityContextHolder.getContext().setAuthentication(authResult);
        sessionStrategy.onAuthenticationSuccess(authResult, request, response);
        rememberMeServices.loginSuccess(request, response, authResult);
        if(eventPublisher != null)
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, getClass()));
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
        throws IOException, ServletException
    {
        SecurityContextHolder.clearContext();
        if(logger.isDebugEnabled())
        {
            logger.debug((new StringBuilder()).append("Authentication request failed: ").append(failed.toString()).toString());
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug((new StringBuilder()).append("Delegating to authentication failure handler").append(failureHandler).toString());
        }
        HttpSession session = request.getSession(false);
        if(session != null || allowSessionCreation)
            request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", failed);
        rememberMeServices.loginFail(request, response);
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    protected AuthenticationManager getAuthenticationManager()
    {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }

    public String getFilterProcessesUrl()
    {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl)
    {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public RememberMeServices getRememberMeServices()
    {
        return rememberMeServices;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices)
    {
        this.rememberMeServices = rememberMeServices;
    }

    public void setContinueChainBeforeSuccessfulAuthentication(boolean continueChainBeforeSuccessfulAuthentication)
    {
        this.continueChainBeforeSuccessfulAuthentication = continueChainBeforeSuccessfulAuthentication;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher)
    {
        this.eventPublisher = eventPublisher;
    }

    public void setAuthenticationDetailsSource(AuthenticationDetailsSource authenticationDetailsSource)
    {
        Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    public void setMessageSource(MessageSource messageSource)
    {
        messages = new MessageSourceAccessor(messageSource);
    }

    public AuthenticationDetailsSource getAuthenticationDetailsSource()
    {
        return authenticationDetailsSource;
    }

    protected boolean getAllowSessionCreation()
    {
        return allowSessionCreation;
    }

    public void setAllowSessionCreation(boolean allowSessionCreation)
    {
        this.allowSessionCreation = allowSessionCreation;
    }

    public void setAuthenticatedSessionStrategy(AuthenticatedSessionStrategy sessionStrategy)
    {
        this.sessionStrategy = sessionStrategy;
    }

    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler)
    {
        Assert.notNull(successHandler, "successHandler cannot be null");
        this.successHandler = successHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler)
    {
        Assert.notNull(failureHandler, "failureHandler cannot be null");
        this.failureHandler = failureHandler;
    }

    public static final String SPRING_SECURITY_LAST_EXCEPTION_KEY = "SPRING_SECURITY_LAST_EXCEPTION";
    protected ApplicationEventPublisher eventPublisher;
    protected AuthenticationDetailsSource authenticationDetailsSource;
    private AuthenticationManager authenticationManager;
    protected MessageSourceAccessor messages;
    private RememberMeServices rememberMeServices;
    private String filterProcessesUrl;
    private boolean continueChainBeforeSuccessfulAuthentication;
    private AuthenticatedSessionStrategy sessionStrategy;
    private boolean allowSessionCreation;
    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;
}
