package com.tenwa.leasing.service.fund.overdue;

import java.util.Map;

public interface OverdueDunningInfoService {
	public void saveOverdueDunningInfo(Map<String,String> model) throws Exception;
	public void saveMultiOverdueDunningInfo(Map<String,String> model) throws Exception;
	public void saveOverdueDunningRecord(Map<String,String> model) throws Exception;
}
