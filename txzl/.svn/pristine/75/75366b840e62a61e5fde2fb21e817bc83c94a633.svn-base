package com.reckon.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.dao.DataAccessException;

import com.reckon.bean.KnowingRentBean;
import com.tenwa.business.entity.DictionaryData;

/**
 * 
 * @author 孙传良
 * @date 2013-3-19上午11:00:32
 * @info 租金测算服务类的主接口
 * @Copyright 
 * Tenwa
 */
public interface RentCalculateService {
	
	/**
	 * 为了和老的租金测算做对接写的测算入口
	 * @param modelData
	 * @param kniwnRentBeans 已知租金法测算传入的分段租金计划值，为空则直接传入null
	 * <pre>中文注解：
	 * 第1期到第6期租金值是：1W
	 * 第7期到第10期租金值是：2W
	 * 第11期到第12期租金值是：3W
	 * 底层方法生成完整的租金列集合是：{1W,1W,1W,1W,1W,2W,2W,2W,2W,2W,3W,3W}
	 * kniwnRentBeans伪码参数格式例子如下：
	 * List<KnowingRentBean> bean_l = new ArrayList<KnowingRentBean>();
	 *	KnowingRentBean obj0 = new KnowingRentBean();
	 *	obj0.setStartlist(1);
	 *	obj0.setEndlist(6);
	 *	obj0.setRent("10000.00");
	 *	bean_l.add(obj0);
	 *	KnowingRentBean obj1 = new KnowingRentBean();
	 *	obj1.setStartlist(7);
	 *	obj1.setEndlist(10);
	 *	obj1.setRent("20000.00");
	 *	bean_l.add(obj1);
	 *  ...
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> doCalculateRentPlanOld(Map<String,String> modelData,List<KnowingRentBean> kniwnRentBeans)  throws Exception ;
	public Map<String, Object> calculateRentByIrr(Map<String,String> modelData,InputStream file)  throws Exception ;
	/**
	 * 描述：资金计划的更新以及现金流的重新生成
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateFundFundPlan(Map<String,String> modelData)  throws Exception ;
	/**
	 * 资金计划变更 变更租前息
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateFundPutPlan(Map<String,String> modelData)  throws Exception ;
	
	
	/**
	 * 租金计划变更实现
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> adjustCalculate(Map<String,String> modelData)  throws Exception ;

	/**
	 * 租金计划修改实现
	 * @param modelData
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateCalculateOld(Map<String,String> modelData)  throws Exception ;
	/**
	 * 重新把数据从正式表拉到临时表  仅限合同层
	 * @param contractId 合同表的主键ID
	 * @param docId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> resetConditionData(String contractId,String docId)  throws Exception ;
	
	/**
	  * <p>将字符串转成固定的KnowingRentBean对象格式。</p>
	  * @author sea
	  * @param jsonStr
	  * @return
	  * @throws JSONException
	 */
	public List<KnowingRentBean> getList(String jsonStr) throws JSONException;
	
	/**
	  * <p>查询设备分类(保险)的数据字典对象。</p>
	  * @author sea
	  * @param id
	  * @return
	  * @throws DataAccessException
	  * @throws Exception
	 */
	public DictionaryData getDictionaryData(String id) throws DataAccessException, Exception;
	
	public Map<String, Object> getCustConditionInfo(Map<String, String> paramMap) throws Exception ;
	
	public Map<String, Object> doFacCalculateRentPlan(Map<String,String> modelData)  throws Exception ;
	public Map<String, Object> updateFundFundPlanForFinance(Map<String, String> modelData) throws Exception;
}
