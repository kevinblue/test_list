package com.tenwa.leasing.serviceImpl.cust;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.cust.CustShareCompany;
import com.tenwa.leasing.service.cust.CustShareCompanyService;




/**
 * 关联企业
 * @Title: CustShareCompanyServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午5:59:50 
 * @version V5.1
 */
@Service(value="custShareCompanyService")
public class CustShareCompanyServiceImpl extends AbstractBaseServiceImpl implements
		CustShareCompanyService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 添加关联企业信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void addCustShareCompany(Map<String, String> model) throws Exception {
		CustShareCompany custManufacturersInfo = new CustShareCompany();//客户基本信息
		//设置客户编号
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		//保存
		this.baseDao.saveEntity(custManufacturersInfo);
	}
	/**
	  * 修改关联企业信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustShareCompany(Map<String, String> model) throws Exception {
		CustShareCompany custManufacturersInfo = (CustShareCompany)this.baseDao.findEntityByID(CustShareCompany.class, model.get("id"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		this.baseDao.updateEntity(custManufacturersInfo);
	}
	/**
	  * 删除关联企业信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void removeCustShareCompany(Map<String, String> model) throws Exception {
		System.out.println("id---"+model.get("id"));
		CustShareCompany info = (CustShareCompany)this.baseDao.findEntityByID(CustShareCompany.class, model.get("id"));
		this.baseDao.removeEntity(info);
	}
}
