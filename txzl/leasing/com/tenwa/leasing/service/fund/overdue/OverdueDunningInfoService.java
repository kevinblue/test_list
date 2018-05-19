package com.tenwa.leasing.service.fund.overdue;

import java.util.Map;

public interface OverdueDunningInfoService {
	public void saveOverdueDunningInfo(Map<String,String> model) throws Exception;
	public void saveMultiOverdueDunningInfo(Map<String,String> model) throws Exception;
	public void saveOverdueDunningRecord(Map<String,String> model) throws Exception;
	//信审部门、业务部门、行业部门关联方法
	public void saveMultiXinshenDunningInfo(Map<String,String> model) throws Exception;
}
