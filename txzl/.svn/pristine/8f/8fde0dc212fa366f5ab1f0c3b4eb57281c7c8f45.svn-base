package com.tenwa.kernal.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.ProcessStatus;
import com.tenwa.business.service.BaseService;
import com.google.inject.internal.Maps;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.utils.BeanFieldUtil;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-6-25 下午12:16ƒ:03 类说明 Action的拦截器，用于拦截Action的状态，和流程
 */
@Aspect
@Component("actionInterceptor")
public class ActionInterceptor {
	
	@Resource(name = "baseService")
	private BaseService baseService;

	@Pointcut("execution (* com.tenwa.business.action.leasing..*.*(..))")
	public void ActionPointcut() {
	}

	@Around("ActionPointcut()")
	public Object testHandleBody(ProceedingJoinPoint pjp) throws Throwable {
	
		Object retVal = null; // 连接点方法返回值

		Object[] args = pjp.getArgs();
		Map<String, String> variablesMap = new HashMap<String, String>();
		JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo = null;

		// 获取将要执行的方法名称
		String actionMethod = pjp.getSignature().getName();
		// String actionName = pjp.getTarget().getClass().getSimpleName().toLowerCase();

		String workflowDesignerID = null;
		String activityDetailID = null;

		// 获取参数
		for (int i = 0; i < args.length; i++) {
		    //System.out.println(args[i].getClass().getName());
			if (Map.class.isAssignableFrom(args[i].getClass())) {
				variablesMap = (Map<String, String>) args[i];
				break;
			}
			/*if (args[i].getClass().getName().equals("com.tenwa.business.entity.JBPMWorkflowHistoryInfo")) {
				System.out.println("12354");
				jbpmWorkflowHistoryInfo = (JBPMWorkflowHistoryInfo) args[i];
			}*/

		}
	/*	if (jbpmWorkflowHistoryInfo != null) {
			workflowDesignerID = jbpmWorkflowHistoryInfo.getDeploymentImpl().getJbpmWorkflowDesigner().getId();
			activityDetailID = jbpmWorkflowHistoryInfo.getActivityDetail().getId();
			System.out.println(jbpmWorkflowHistoryInfo.getDeploymentImpl().getJbpmWorkflowDesigner().getId());
			System.out.println(jbpmWorkflowHistoryInfo.getActivityDetail().getId());
		}*/
		if(variablesMap != null){
			workflowDesignerID = variablesMap.get("currentJbpmWorkflowDesignerId");
			activityDetailID = variablesMap.get("currentActivityDetailId");
			//System.out.println(workflowDesignerID);
			//System.out.println(activityDetailID);
		}

		// 从数据库中获取配置列表
		// 判断配置

		retVal = pjp.proceed();

		if (workflowDesignerID != null && activityDetailID != null) {
			// 获取processStatus
			Map<String, Object> properMap = Maps.newHashMap();
			properMap.put("workFlowName", workflowDesignerID);
			properMap.put("pointName", activityDetailID);
			properMap.put("actionMethod", actionMethod);
			List<ProcessStatus> processStatusList = this.baseService.findEntityByProperties(ProcessStatus.class, properMap);

			if (processStatusList.size() > 0) { 
				for (ProcessStatus processStatus : processStatusList) {
					if(processStatus.getFlag() != 1){
						break;
					}
					String entityName = processStatus.getEntityName();
					Class<?> entityClass = Class.forName(entityName);
					Logger logger=Logger.getLogger(entityClass);

					// ID值
					String idType = processStatus.getTypeName();
					String id = variablesMap.get(idType);
					// System.out.println(id);

					if (id != null) {
						Object entity = this.baseService.findEntityByID(entityClass, id);

						// 设置流程状态
						String processFieldName = processStatus.getProcessFieldName();
						String processFieldValue = processStatus.getProcessStatus();
						String additionalField = processStatus.getAdditionalField();
						if (processFieldName != null ) {
							Object val = null;
							String _val = "";
							Field processField = entityClass.getDeclaredField(processFieldName);
							String valueType = processField.getType().getName();

							if(additionalField != null){//如果填写了附加字段，则获取附加字段的值
								_val =  variablesMap.get(additionalField);
							}else if(processFieldValue != null){
								_val = processFieldValue;
							}
							
							if(_val != null && _val != ""){
								if ("java.lang.Integer" == valueType) {
									val = Integer.valueOf(_val);
								} else {
									val = _val;
								}
								//ReflectionUtils.setField(processField, entity, val);
							
								Method processMethod = BeanFieldUtil.getSetMethod(entityClass, processFieldName);
								processMethod.invoke(entity, val);
								
								logger.info("设置【" + processFieldName + "】为" + val);
							}
							
						} 

						// 设置是否流程中
						String workFlowFieldName = processStatus.getWorkFlowFieldName();
						String workFlowValue = processStatus.getWorkFlow();

						if (workFlowFieldName != null && workFlowValue != null) {
							Object val = null;
							Field workFlowField = entityClass.getDeclaredField(workFlowFieldName);
							String valueType = workFlowField.getType().getName();

							if ("java.lang.Integer" == valueType) {
								val = Integer.valueOf(workFlowValue);
							} else {
								val = workFlowValue;
							}
							//ReflectionUtils.setField(workFlowField, entity, val);
							
							Method processMethod = BeanFieldUtil.getSetMethod(entityClass, workFlowFieldName);
							processMethod.invoke(entity, val);
							
							logger.info("设置【" + workFlowFieldName + "】为" + val);
						}

						if ((processFieldName != null && processFieldValue != null) || (workFlowFieldName != null && workFlowValue != null)) {
							if (id != null && !id.equals("")) {
								this.baseService.updateEntity(entity);
							}
						}
					} else {
						throw new java.lang.Exception("ActionInterceptor拦截器中未能获取项目ID，请指定ID值");
					}

				}
			}

		}
		return retVal;
	}
}
