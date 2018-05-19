package com.tenwa.leasing.serviceImpl.cust;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.cust.other.CustManufacturersInfo;
import com.tenwa.leasing.service.cust.CustManufacturersInfoService;




/**
 * 进货厂商资料
 * @Title: CustManufacturersInfoServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午6:02:05 
 * @version V5.1
 */
@Service(value="custManufacturersInfoService")
public class CustManufacturersInfoServiceImpl extends AbstractBaseServiceImpl implements
		CustManufacturersInfoService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 添加进货厂商资料
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void addCustManufacturersInfo(Map<String, String> model)
			throws Exception {
		CustManufacturersInfo custManufacturersInfo = new CustManufacturersInfo();//客户基本信息
		//设置客户编号
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		//保存
		this.baseDao.saveEntity(custManufacturersInfo);
	}
	/**
	  * 修改进货厂商资料
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustManufacturersInfo(Map<String, String> model)
			throws Exception {
		CustManufacturersInfo custManufacturersInfo = (CustManufacturersInfo)this.baseDao.findEntityByID(CustManufacturersInfo.class, model.get("id"));//法人客户信息
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		this.baseDao.updateEntity(custManufacturersInfo);
	}
	/**
	  * 删除进货厂商资料
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void removeCustManufacturersInfo(Map<String, String> model)
			throws Exception {
		System.out.println("id---"+model.get("id"));
		CustManufacturersInfo info = (CustManufacturersInfo)this.baseDao.findEntityByID(CustManufacturersInfo.class, model.get("id"));
		System.out.println(info.getCompanyName());
		this.baseDao.removeEntity(info);
	}
}
