package com.tenwa.leasing.serviceImpl.cust;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.cust.CustInfoContact;
import com.tenwa.leasing.service.cust.CustContactService;




/**
 * 走访调研信息
 * @Title: CustContactServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:31:30 
 * @version V5.1
 */
@Service(value="custContactService")
public class CustContactServiceImpl extends AbstractBaseServiceImpl implements
		CustContactService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 添加走访调研信息
	  * @param 数据Map
	  * @throws Exception
	  */ 
	@Override
	public void addCustContact(Map<String, String> model) throws Exception {
		CustInfoContact custManufacturersInfo = new CustInfoContact();//客户基本信息
		//设置客户编号
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		//保存
		this.baseDao.saveEntity(custManufacturersInfo);
	}
	/**
	  * 修改走访调研信息
	  * @param 数据Map
	  * @throws Exception
	  */ 
	@Override
	public void updateCustContact(Map<String, String> model) throws Exception {
		CustInfoContact custManufacturersInfo = (CustInfoContact)this.baseDao.findEntityByID(CustInfoContact.class, model.get("id"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		this.baseDao.updateEntity(custManufacturersInfo);
	}
	/**
	  * 删除走访调研信息
	  * @param 数据Map
	  * @throws Exception
	  */ 
	@Override
	public void removeCustContact(Map<String, String> model) throws Exception {
		CustInfoContact info = (CustInfoContact)this.baseDao.findEntityByID(CustInfoContact.class, model.get("id"));
		this.baseDao.removeEntity(info);
	}
}
