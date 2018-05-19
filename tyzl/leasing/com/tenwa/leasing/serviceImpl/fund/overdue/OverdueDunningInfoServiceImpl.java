package com.tenwa.leasing.serviceImpl.fund.overdue;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.User;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.overdue.OverdueDunningInfo;
import com.tenwa.leasing.entity.fund.overdue.OverdueDunningRecord;
import com.tenwa.leasing.service.fund.overdue.OverdueDunningInfoService;

@Service(value="overdueDunningInfoService")
public class OverdueDunningInfoServiceImpl extends  AbstractBaseServiceImpl 
implements OverdueDunningInfoService {

	@Override
	public void saveOverdueDunningInfo(Map<String, String> model)throws Exception {
		   OverdueDunningInfo overdueDunningInfo = new OverdueDunningInfo();
		   baseDao.copyAndOverrideExistedValueFromStringMap(model, overdueDunningInfo, null);
		   overdueDunningInfo.setId(model.get("id"));
		   this.baseDao.saveOrUpdateEntity(overdueDunningInfo);
	}

	@Override
	public void saveMultiOverdueDunningInfo(Map<String, String> model)throws Exception {
		String dunningids = model.get("dunningids");
		String partdept = model.get("partdept");
		String contractids = model.get("contractids");
		String[]  contractArrays = contractids.split(",");
		String[]  dunningidArrays = new String[0];
		if(null != dunningids){dunningidArrays = dunningids.split(",");}
		for (int i = 0; i < contractArrays.length; i++) {
			OverdueDunningInfo overdueDunningInfo = new OverdueDunningInfo();
			for (int j = 0; j < dunningidArrays.length; j++) {
				if(i==j){
					overdueDunningInfo.setId(dunningidArrays[j]);
				}
			}
			
			ContractInfo contractInfo = new ContractInfo();
			contractInfo.setId(contractArrays[i]);
			overdueDunningInfo.setContractId(contractInfo);
			
			User user = new User();
			user.setId(partdept);
			overdueDunningInfo.setPartDept(user);
			
			this.baseDao.saveOrUpdateEntity(overdueDunningInfo);
		}
	}
	@Resource
    private BaseDao baseDao;
	
	@Override
	public BaseDao getBussinessDao() throws Exception {
		 
		return baseDao;
	}

	@Override
	public void saveOverdueDunningRecord(Map<String, String> model)throws Exception {
		String contractids = model.get("contractid");
		String[]  contractArrays = contractids.split(",");
		for (int i = 0; i < contractArrays.length; i++) {
			ContractInfo contractInfo = new ContractInfo();
			contractInfo.setId(contractArrays[i]);
			OverdueDunningRecord overdueDunningRecord = new OverdueDunningRecord();
			baseDao.copyAndOverrideExistedValueFromStringMap(model, overdueDunningRecord, null);
			overdueDunningRecord.setContractId(contractInfo);
			this.baseDao.saveOrUpdateEntity(overdueDunningRecord);
		}
	}

}
