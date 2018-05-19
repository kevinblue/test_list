package com.tenwa.leasing.serviceImpl.cust;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.cust.other.CustSalesInfo;
import com.tenwa.leasing.service.cust.CustSalesInfoService;




/**
 * 销货厂商资料
 * @Title: CustSalesInfoServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午5:59:50 
 * @version V5.1
 */
@Service(value="custSalesInfoService")
public class CustSalesInfoServiceImpl extends AbstractBaseServiceImpl implements
		CustSalesInfoService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 添加销货厂商资料
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void addCustSalesInfo(Map<String, String> model) throws Exception {
		CustSalesInfo custManufacturersInfo = new CustSalesInfo();//客户基本信息
		//设置客户编号
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		//保存
		this.baseDao.saveEntity(custManufacturersInfo);
	}
	/**
	  * 修改销货厂商资料
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustSalesInfo(Map<String, String> model) throws Exception {
		CustSalesInfo custManufacturersInfo = (CustSalesInfo)this.baseDao.findEntityByID(CustSalesInfo.class, model.get("id"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		this.baseDao.updateEntity(custManufacturersInfo);
	}
	/**
	  * 删除销货厂商资料
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void removeCustSalesInfo(Map<String, String> model) throws Exception {
		System.out.println("id---"+model.get("id"));
		CustSalesInfo info = (CustSalesInfo)this.baseDao.findEntityByID(CustSalesInfo.class, model.get("id"));
		System.out.println(info.getCompanyName());
		this.baseDao.removeEntity(info);
	}
}
