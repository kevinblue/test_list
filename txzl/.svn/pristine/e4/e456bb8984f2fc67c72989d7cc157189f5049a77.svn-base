package com.tenwa.leasing.serviceImpl.fund.overdue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlan;
import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.base.District;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustRelatedPerson;
import com.tenwa.leasing.entity.dun.DunningDistrict;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.fund.overdue.OverdueDunningInfo;
import com.tenwa.leasing.entity.fund.overdue.OverdueDunningRecord;
import com.tenwa.leasing.entity.other.BusinessCreditConfig;
import com.tenwa.leasing.service.fund.overdue.OverdueDunningInfoService;
import com.tenwa.leasing.service.fund.overdue.OverdueDunningRecordService;

@Service(value = "overdueDunningRecordService")
public class OverdueDunningRecordServiceImpl extends AbstractBaseServiceImpl
		implements OverdueDunningRecordService {

	@Resource
	private BaseDao baseDao;
	@Resource
	private TableService tableservice;
	
	@Override
	public BaseDao getBussinessDao() throws Exception {

		return baseDao;
	}

	@Override
	public void saveOverdueDunningRecord(Map<String, String> model)
			throws Exception {
		DictionaryData dict=baseDao.findEntityByID(DictionaryData.class, model.get("contactway"));
		ContractFundRentPlan con=baseDao.findEntityByID(ContractFundRentPlan.class, model.get("planid"));
		CustRelatedPerson cust=baseDao.findEntityByID(CustRelatedPerson.class,model.get("custid"));
		OverdueDunningRecord over=new OverdueDunningRecord();
		over.setContactWay(dict);
		over.setPlanid(con);
		over.setPersonid(cust);
		over.setCommitmentInfo(model.get("commitmentinfo"));
		over.setContactDate(DateUtil.getSystemDate());
		this.baseDao.saveEntity(over);
	}


}
