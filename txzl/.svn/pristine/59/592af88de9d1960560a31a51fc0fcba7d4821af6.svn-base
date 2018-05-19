package com.tenwa.leasing.serviceImpl.cust;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.cust.CustInfoAccount;
import com.tenwa.leasing.service.cust.CustAccountService;




/**
 * 银行信息
 * @Title: CustAccountServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月20日 上午9:29:28 
 * @version V5.1
 */
@Service(value="custAccountService")
public class CustAccountServiceImpl extends AbstractBaseServiceImpl implements
		CustAccountService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 添加银行信息
	  * @param 数据Map
	  * @throws Exception
	  */ 
	@Override
	public void addCustAccount(Map<String, String> model) throws Exception {
		CustInfoAccount custManufacturersInfo = new CustInfoAccount();
		//设置客户编号
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		//保存
		this.baseDao.saveEntity(custManufacturersInfo);
	}
	/**
	  * 修改银行信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustAccount(Map<String, String> model) throws Exception {
		CustInfoAccount custManufacturersInfo = (CustInfoAccount)this.baseDao.findEntityByID(CustInfoAccount.class, model.get("id"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		this.baseDao.updateEntity(custManufacturersInfo);
	}
	/**
	  * 删除银行信息
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void removeCustAccount(Map<String, String> model) throws Exception {
		CustInfoAccount info = (CustInfoAccount)this.baseDao.findEntityByID(CustInfoAccount.class, model.get("id"));
		this.baseDao.removeEntity(info);
	}
}
