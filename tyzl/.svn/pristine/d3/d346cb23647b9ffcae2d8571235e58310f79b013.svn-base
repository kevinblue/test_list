package com.tenwa.leasing.serviceImpl.cust;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.cust.CustRelatedPerson;
import com.tenwa.leasing.service.cust.CustRelatedPersonService;




/**
 * 重要个人
 * @Title: CustRelatedPersonServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午5:59:50 
 * @version V5.1
 */
@Service(value="custRelatedPersonService")
public class CustRelatedPersonServiceImpl extends AbstractBaseServiceImpl implements
		CustRelatedPersonService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 添加重要个人
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void addCustRelatedPerson(Map<String, String> model) throws Exception {
		CustRelatedPerson custManufacturersInfo = new CustRelatedPerson();//客户基本信息
		//设置客户编号
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		//保存
		this.baseDao.saveEntity(custManufacturersInfo);
	}
	/**
	  * 修改重要个人
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustRelatedPerson(Map<String, String> model) throws Exception {
		CustRelatedPerson custManufacturersInfo = (CustRelatedPerson)this.baseDao.findEntityByID(CustRelatedPerson.class, model.get("id"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		this.baseDao.updateEntity(custManufacturersInfo);
	}
	/**
	  * 删除重要个人
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void removeCustRelatedPerson(Map<String, String> model) throws Exception {
		CustRelatedPerson info = (CustRelatedPerson)this.baseDao.findEntityByID(CustRelatedPerson.class, model.get("id"));
		this.baseDao.removeEntity(info);
	}
}
