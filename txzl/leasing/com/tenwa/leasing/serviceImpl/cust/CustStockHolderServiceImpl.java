package com.tenwa.leasing.serviceImpl.cust;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.cust.CustStockHolder;
import com.tenwa.leasing.service.cust.CustStockHolderService;




/**
 * 企业股本结构
 * @Title: CustStockHolderServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午5:59:50 
 * @version V5.1
 */
@Service(value="custStockHolderService")
public class CustStockHolderServiceImpl extends AbstractBaseServiceImpl implements
		CustStockHolderService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 添加企业股本结构
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void addCustStockHolder(Map<String, String> model) throws Exception {
		CustStockHolder custManufacturersInfo = new CustStockHolder();//客户基本信息
		//设置客户编号
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		//保存
		this.baseDao.saveEntity(custManufacturersInfo);
	}
	/**
	  * 修改企业股本结构
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustStockHolder(Map<String, String> model) throws Exception {
		CustStockHolder custManufacturersInfo = (CustStockHolder)this.baseDao.findEntityByID(CustStockHolder.class, model.get("id"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		this.baseDao.updateEntity(custManufacturersInfo);
	}
	/**
	  * 删除企业股本结构
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void removeCustStockHolder(Map<String, String> model) throws Exception {
		System.out.println("id---"+model.get("id"));
		CustStockHolder info = (CustStockHolder)this.baseDao.findEntityByID(CustStockHolder.class, model.get("id"));
		this.baseDao.removeEntity(info);
	}
}
