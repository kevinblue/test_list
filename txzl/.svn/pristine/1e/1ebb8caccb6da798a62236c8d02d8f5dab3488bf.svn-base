package com.tenwa.leasing.serviceImpl.cust;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.User;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.cust.CustRelatedPerson;
import com.tenwa.leasing.entity.cust.CustRelatedPersonHis;
import com.tenwa.leasing.service.cust.CustRelatedPersonHisService;
import com.tenwa.leasing.service.cust.CustRelatedPersonService;




/**
 * 重要个人
 * @Title: CustRelatedPersonServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午5:59:50 
 * @version V5.1
 */
@Service(value="custRelatedPersonHisService")
public class CustRelatedPersonHisServiceImpl extends AbstractBaseServiceImpl implements
		CustRelatedPersonHisService {

	@Resource
    private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 修改重要个人
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public void updateCustRelatedPersonHis(Map<String, String> model) throws Exception {
		CustRelatedPerson custrelatedperson = (CustRelatedPerson)this.baseDao.findEntityByID(CustRelatedPerson.class, model.get("id"));//法人客户信息
		CustRelatedPersonHis custrelatedpersonhis=new CustRelatedPersonHis();
		//把前面对象里的信息copy到后面的对象里
		BeanUtils.copyProperties(custrelatedperson,custrelatedpersonhis);
		this.baseDao.saveEntity(custrelatedpersonhis);
		
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custrelatedperson,null);
		
		
		
		
		
	/*	
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		this.baseDao.copyAndOverrideExistedValueFromStringMap(model, custManufacturersInfo, dictDataClassMapping);
		this.baseDao.updateEntity(custManufacturersInfo);*/
	}
}
