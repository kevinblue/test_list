package com.tenwa.business.log;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;

/**   
*    
* 项目名称：tls5.1   
* 类名称：ActionTimeLogInterceptor   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月29日 下午12:14:34   
* @version        
*/
@Aspect
@Component
public class ActionTimeLogInterceptor {
	@Resource(name = "tableService")
	private TableService tableService;
	
	/**
	 * @param pjp
	 * @throws Throwable
	 */
	@Around("execution(* com.tenwa.leasing.action..*.*(..))")
	public void aroundActionMethod(ProceedingJoinPoint pjp) throws Throwable {
		Log logger = LogFactory.getLog(pjp.getTarget().getClass());
		Object[] args=pjp.getArgs();
		//JBPMWorkflowHistoryInfo  jinfo=(JBPMWorkflowHistoryInfo)args[2];
		HashMap<String,Object> map=(HashMap<String, Object>) args[1];
		String method=pjp.getSignature().getName();
		logger.info("["+map.get("proj_info.custInfo")+"]["+map.get("processDefinitionId")+"]["+map.get("login_username")+"]"+method+"方法开始");
		
 		pjp.proceed();
		
        logger.info("["+map.get("proj_info.custInfo")+"]["+map.get("processDefinitionId")+"]["+map.get("login_username")+"]"+method+"方法结束");
       
	}
	
	/**
	 * @param pjp
	 * @throws Throwable
	 */
	@Around("execution(* com.tenwa.leasing.serviceImpl..*.*(..))")
	public Object aroundServiceMethod(ProceedingJoinPoint pjp) throws Throwable {
		Log logger = LogFactory.getLog(pjp.getTarget().getClass());
		//Object[] args=pjp.getArgs();
		String method=pjp.getSignature().getName();
		logger.info("调用["+method+"]方法开始");
		Object returnObj= pjp.proceed();
		logger.info("调用["+method+"]方法结束");
		return returnObj;
	}
}
