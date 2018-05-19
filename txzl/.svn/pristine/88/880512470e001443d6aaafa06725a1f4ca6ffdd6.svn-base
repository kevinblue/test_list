package com.tenwa.leasing.serviceImpl.insuremanage;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.insurance.InsuranceCover;
import com.tenwa.leasing.service.insuremanage.InsuranceCoverService;

@Service(value="insuranceCoverService")
public class InsuranceCoverServiceImpl extends  AbstractBaseServiceImpl 
implements InsuranceCoverService {
	@Resource
    private BaseDao baseDao;
	
	@Override
	public BaseDao getBussinessDao() throws Exception {
		// TODO Auto-generated method stub
		return baseDao;
	}
	@Override
	public void saveInsuranceCover(Map<String, String> model)
			throws Exception {
		InsuranceCover insuranceCover = new InsuranceCover();
		baseDao.copyAndOverrideExistedValueFromStringMap(model, insuranceCover, null);
		this.baseDao.saveOrUpdateEntity(insuranceCover);
	}

}
