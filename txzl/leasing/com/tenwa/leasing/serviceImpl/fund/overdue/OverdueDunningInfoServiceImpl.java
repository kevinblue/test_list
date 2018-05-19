package com.tenwa.leasing.serviceImpl.fund.overdue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.base.District;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.dun.DunningDistrict;
import com.tenwa.leasing.entity.fund.overdue.OverdueDunningInfo;
import com.tenwa.leasing.entity.fund.overdue.OverdueDunningRecord;
import com.tenwa.leasing.entity.other.BusinessCreditConfig;
import com.tenwa.leasing.service.fund.overdue.OverdueDunningInfoService;

@Service(value = "overdueDunningInfoService")
public class OverdueDunningInfoServiceImpl extends AbstractBaseServiceImpl
		implements OverdueDunningInfoService {

	@Override
	public void saveOverdueDunningInfo(Map<String, String> model)
			throws Exception {
		  OverdueDunningInfo overdueDunningInfo = new OverdueDunningInfo();
		   baseDao.copyAndOverrideExistedValueFromStringMap(model, overdueDunningInfo, null);
		   overdueDunningInfo.setId(model.get("id"));
		   this.baseDao.saveOrUpdateEntity(overdueDunningInfo);
		// OverdueDunningInfo overdueDunningInfo = new OverdueDunningInfo();
		// baseDao.copyAndOverrideExistedValueFromStringMap(model,
		// overdueDunningInfo, null);
		// overdueDunningInfo.setId(model.get("id"));
		/*DunningDistrict qy = new DunningDistrict();
		String id = model.get("id");
		if (id == null) {
			Department department = this.baseDao.findEntityByID(
					Department.class, model.get("deptid"));
			District district = this.baseDao.findEntityByID(District.class,
					model.get("proviceid"));
			qy.setDepartment(department);
			qy.setDistrict(district);
		} else {
			qy = this.baseDao.findEntityByID(DunningDistrict.class,
					model.get("id"));
		}
		User du = this.baseDao
				.findEntityByID(User.class, model.get("partdept"));
		qy.setDunning(du);
		this.baseDao.saveOrUpdateEntity(qy);*/
	}

	@Override
	public void saveMultiOverdueDunningInfo(Map<String, String> model)
			throws Exception {
		/*List<DunningDistrict> ddlist = new ArrayList();
		String partdept = model.get("partdept");
		User du = this.baseDao.findEntityByID(User.class, partdept);
		String arr = model.get("arr");
		String[] row = arr.split(",");
		for (int i = 0; i < row.length; i++) {
			String[] rows = row[i].split("@");
			for (int j = 0; j < rows.length;) {
				if (rows[0].equals("null")) {
					DunningDistrict ddt = new DunningDistrict();
					Department dt = this.baseDao.findEntityByID(
							Department.class, rows[1]);
					ddt.setDepartment(dt);
					District dist = this.baseDao.findEntityByID(District.class,
							rows[2]);
					ddt.setDistrict(dist);
					ddt.setDunning(du);
					ddlist.add(ddt);
				} else {
					DunningDistrict ddit = this.baseDao.findEntityByID(
							DunningDistrict.class, rows[0]);
					ddit.setDunning(du);
					ddlist.add(ddit);
				}
				break;
			}
		}
		this.baseDao.saveOrUpdateAllEntities(ddlist);*/
		// =this.baseDao.findEntityByIDArray(ids)
		// String contractids = model.get("contractids");
		/*
		 * String[] dunning=dunningids.split(","); for(int
		 * i=0;i<dunning.length;i++){ String id=dunning[i]; String
		 * deptid=dunning[i+1]; String proviceid=dunning[i+2];
		 * System.out.println
		 * ("========="+id+"======="+deptid+"======="+proviceid); } String[]
		 * dunningidArrays = new String[0]; if(null !=
		 * dunningids){dunningidArrays = dunningids.split(",");}
		 * List<DunningDistrict>
		 * ddlist=this.baseDao.findEntityByIDArray(DunningDistrict.class,
		 * dunningidArrays); for(DunningDistrict dd:ddlist){ dd.setDunning(du);
		 * }
		 */
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
	public void saveOverdueDunningRecord(Map<String, String> model)
			throws Exception {
		String contractids = model.get("contractid");
		String[] contractArrays = contractids.split(",");
		for (int i = 0; i < contractArrays.length; i++) {
			ContractInfo contractInfo = new ContractInfo();
			contractInfo.setId(contractArrays[i]);
			OverdueDunningRecord overdueDunningRecord = new OverdueDunningRecord();
			baseDao.copyAndOverrideExistedValueFromStringMap(model,
					overdueDunningRecord, null);
			overdueDunningRecord.setContractId(contractInfo);
			this.baseDao.saveOrUpdateEntity(overdueDunningRecord);

		}
	}

	@Override
	public void saveMultiXinshenDunningInfo(Map<String, String> model)
			throws Exception {
		String currentDate = DateUtil.getSystemDateTime(); // 当前时间
		User currentUser = SecurityUtil.getPrincipal();//当前登录人ID
		String partdept = model.get("partdept");// 信审ID
		String arr = model.get("arr");// 数组（主键表ID，业务ID,行业ID）
		String[] row = arr.split(",");
		List<BusinessCreditConfig> list = new ArrayList();
		for (int i = 0; i < row.length; i++) {
			String[] rows_two = row[i].split("@");
			for (int j = 0; j < rows_two.length; j++) {
				if ("null".equals(rows_two[0])) {// 为0就插入					
			     	BusinessCreditConfig bsc = new BusinessCreditConfig();
					Department dt=this.baseDao.findEntityByID(Department.class, rows_two[1]);
					bsc.setBusinessDept(dt);// 业务部门ID
					DictionaryData dd=this.baseDao.findEntityByID(DictionaryData.class, rows_two[2]);
					bsc.setIndustryId(dd);// 行业ID
					bsc.setCreditDept(this.baseDao.findEntityByID(Department.class, partdept));// 信审ID
					bsc.setCreateDate(currentDate);
					bsc.setCreator(currentUser);
					list.add(bsc);			
				} else {// 反之更新	
					BusinessCreditConfig bsc= this.baseDao.findEntityByID(BusinessCreditConfig.class, rows_two[0]);//
					bsc.setCreditDept(this.baseDao.findEntityByID(Department.class, partdept));// 信审ID
					bsc.setModifyDate(currentDate);
					bsc.setModificator(currentUser);
					list.add(bsc);
				}
                    break;
			}

		}

		this.baseDao.saveOrUpdateAllEntities(list);
		

	}

}
