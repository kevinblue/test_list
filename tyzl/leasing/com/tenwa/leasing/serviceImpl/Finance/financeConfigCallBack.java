package com.tenwa.leasing.serviceImpl.Finance;

import java.util.List;
import java.util.Map;

import com.tenwa.business.callback.EntityBeanCallBack;
import com.tenwa.business.constant.OperTypeEnum;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.finance.FinancialSubjects;

public class financeConfigCallBack implements EntityBeanCallBack {

	@Override
	public void afterPersistent(BaseService txService, List entityBeanList,List entityIdList, Map<String, String> paramMap,OperTypeEnum operType) throws Exception {

	}

	@Override
	public void beforePersistent(BaseService txService, List entityBeanList,List entityIdList, Map<String, String> paramMap,OperTypeEnum operType) throws Exception {
		List<FinancialSubjects> userBeanList = (List<FinancialSubjects>)entityBeanList;
		switch(operType){
	          case ADD:{
	        	  FinancialSubjects fs = userBeanList.get(0);
	        	  String sheetName=fs.getSubjectName();
	        	  String strIndex=fs.getSubjectIndex();
			      String SQL = "UPDATE Financial_SUBJECTS SET SUBJECT_INDEX=SUBJECT_INDEX+1 where financial_table in( ";
			      SQL += " select id from Financial_Table where sheet_name =? ";
			      SQL += " ) and SUBJECT_INDEX>=?";
			      txService.getBussinessDao().getJdbcTemplate().update(SQL,sheetName,strIndex);
	        	  break;
	        	  
	          }
	          case COPY:{
	        	 break;
	          }
	          case EDIT: {
	        	  break;
	          }
	          case REMOVE: {
	        	  FinancialSubjects fs = userBeanList.get(0);
	        	  String sheetName=fs.getSubjectName();
	        	  String strIndex=fs.getSubjectIndex();
			      String SQL = "UPDATE Financial_SUBJECTS SET SUBJECT_INDEX=SUBJECT_INDEX-1 where financial_table in( ";
			      SQL += " select id from Financial_Table where sheet_name =? ";
			      SQL += " ) and SUBJECT_INDEX>=?";
			      txService.getBussinessDao().getJdbcTemplate().update(SQL,sheetName,strIndex);
	        	  break;
	          }
          }

	}

}
