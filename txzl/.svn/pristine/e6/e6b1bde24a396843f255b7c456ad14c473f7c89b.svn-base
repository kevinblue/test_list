/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.web.session;


import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.concurrent.SessionRegistryImpl;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tenwa.business.entity.LoginInfo;
import com.tenwa.business.service.AclService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;


/**
 * Declared in web.xml as
 * <pre>
 * &lt;listener&gt;
 *     &lt;listener-class&gt;org.springframework.security.ui.session.HttpSessionEventPublisher&lt;/listener-class&gt;
 * &lt;/listener&gt;
 * </pre>
 *
 * Publishes <code>HttpSessionApplicationEvent</code>s to the Spring Root WebApplicationContext. Maps
 * javax.servlet.http.HttpSessionListener.sessionCreated() to {@link HttpSessionCreatedEvent}. Maps
 * javax.servlet.http.HttpSessionListener.sessionDestroyed() to {@link HttpSessionDestroyedEvent}.
 *
 * @author Ray Krueger
 */
public class HttpSessionEventPublisher implements HttpSessionListener {
    //~ Static fields/initializers =====================================================================================

    private static final Log log = LogFactory.getLog(HttpSessionEventPublisher.class);

    //~ Instance fields ================================================================================================

    //~ Methods ========================================================================================================

    ApplicationContext getContext(ServletContext servletContext) {
        return WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    /**
     * Handles the HttpSessionEvent by publishing a {@link HttpSessionCreatedEvent} to the application
     * appContext.
     *
     * @param event HttpSessionEvent passed in by the container
     */
    public void sessionCreated(HttpSessionEvent event) {
        HttpSessionCreatedEvent e = new HttpSessionCreatedEvent(event.getSession());

        if (log.isDebugEnabled()) {
            log.debug("Publishing event: " + e);
        }

        getContext(event.getSession().getServletContext()).publishEvent(e);
    }

    /**
     * Handles the HttpSessionEvent by publishing a {@link HttpSessionDestroyedEvent} to the application
     * appContext.
     *
     * @param event The HttpSessionEvent pass in by the container
     */
    @SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent event) {
    	//添加用户注销处理
    	HttpSession session = event.getSession();
    	if(null !=  session)
    	{
    		String login_userid = StringUtil.nullToString(session.getAttribute("login_userid"));
    		if(!login_userid.isEmpty()){
        		SessionRegistryImpl sessionRegistry = (SessionRegistryImpl)WebUtil.getApplicationContext().getBean("sessionRegistry");
        		try {
					sessionRegistry.removeSessionByUserId(login_userid);
				} catch (Exception e1) {
				}
        		//将用户对应的session删除掉
        		//WebUtil.getUserSessions().remove(login_userid);
		        //更新登录状态
		        AclService aclService = (AclService)WebUtil.getApplicationContext().getBean("aclService");
		        List<LoginInfo> li_list = null;
				try {
					li_list = aclService.findResultsByHSQL("select li from LoginInfo li where li.loginUser.id = ? and li.sessionId=?",login_userid,session.getId());
				    LoginInfo logInfo = null;
			        if(0 < li_list.size()){
			        	logInfo = li_list.get(0);
			        	aclService.removeEntity(logInfo);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}
        HttpSessionDestroyedEvent e = new HttpSessionDestroyedEvent(event.getSession());

        if (log.isDebugEnabled()) {
            log.debug("Publishing event: " + e);
        }

        getContext(event.getSession().getServletContext()).publishEvent(e);
    }
}
