package com.tenwa.business.service;   

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-6-25 下午5:08:55
 * 类说明
 */
public interface ResourceService {
	/**
	 * 获取系统中所有添加了@WorkflowAction注解的类（暂未使用）
	 * @param request
	 * @return
	 */
	@Deprecated
	public List<Map<String,String>> getProcessAction(HttpServletRequest request);

	/**
	 * 获取所有可用流程
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getAllWorkFlowName(String key) throws Exception;

	/**
	 * 获取流程节点信息
	 * @param id 流程定义ID JbpmWorkflowDesigner.id
	 * @return List<Map<id, 显示名称>> id=ActivityDetail.id
	 * @throws Exception
	 */
	public List<Map<String, String>> getWorkFlowPointByID(String id) throws Exception;

	/**
	 * 获取实体所有字段名称
	 * @param entityName 实体名称（包括包名 com.**.entity.User）
	 * @return 字段名称
	 * @throws Exception
	 */
	public List<Map<String, String>> getEntityFieldName(String entityName) throws Exception;

	/**
	 * 获取ProcessStatus，表里的流程配置状态
	 * @param page 分页第几页
	 * @param pagesize 分页大小
	 * @return 带分页的流程配置列表
	 * @throws Exception
	 */
	public Map<String, Object> getProcessStatus(int page, int pagesize) throws Exception;
}
  
