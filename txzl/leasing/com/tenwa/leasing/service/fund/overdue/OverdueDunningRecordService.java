package com.tenwa.leasing.service.fund.overdue;

import java.util.Map;

public interface OverdueDunningRecordService {
	public void saveOverdueDunningRecord(Map<String,String> model) throws Exception;
}
