package com.tenwa.leasing.service.Proj.proComm;

import java.util.Map;

import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * 项目名称：tls5.1 类名称：ProCommService 类描述： 创建人：rovine 创建时间：2014年11月19日 下午6:33:09
 * 
 * @version
 */
public interface ProCommService {
  /**
     * 
    * 方法名: initPorjCustInfo
    * 方法描述: TODO(加载项目的客户信息)
    * 参数:  @param pinfo
    * 参数:  @param variablesMap
    * 参数:  @throws Exception    设定文件
    * 返回类型 void    返回类型
    * @throws
     */
	public void loadPorjCustInfo(ProjInfo pinfo,Map<String, String> variablesMap) throws Exception;

	/**
	 * 加载项目联合承租人
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void loadProjUnionCust(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	
	/**
	 * 保存项目联合承租人
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void saveProjUnionCust(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 加载项目的基本信息
	 * @param variablesMap
	 * @throws Exception
	 */
	public ProjInfo loadProjInfo(Map<String,String>variablesMap)throws Exception;
	/**
	 *保存项目信息
	 * @param variablesMap
	 * @throws Exception
	 */
	  
	public ProjInfo saveProjInfo(Map<String,String>variablesMap,Integer ProStatus)throws Exception;
	
	/**
	 * 加载测算的的基本参数
	 * @param variablesMap
	 * @throws Exception
	 */
	public void loadRentCalculationParam(String projid,String custname,String custid,String flowunid,Map<String,String>variablesMap)throws Exception;
	
	/**
	 * 加载项目的交易，资金计划，租金计划和现金流
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void loadProjRentCalculation(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 保存项目的交易结构，资金计划，租金计划，现金流
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void saveProjRentCalculation(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 保存项目的租赁物件
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void saveProjEquipment(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	
	/**
	 * 保存项目的担保人
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void saveProjGuaranteeMethod(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 保存项目的抵押物 
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void saveProjGuaranteeEquipment(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	
	/**
	 * 加载项目的租赁物件到前台的MAP中
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void loadProjEquipment(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 加载项目的担保人到前台的Map中
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void loadProjGuaranteeMethod(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 加载项目的抵押物到前台的MAP中
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void loadProjGuaranteeEquipment(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 加载项目的发票信息
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void loadProjInvoice(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 保存项目的发票信息
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void savProjInvoice(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 保存信审报告
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void saveProjReport(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	/**
	 * 保存上会记录
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void saveProjMeeting(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;
	
	public void saveProjBoardMeeting(ProjInfo projinfo,Map<String,String>variablesMap)throws Exception;

	
}
