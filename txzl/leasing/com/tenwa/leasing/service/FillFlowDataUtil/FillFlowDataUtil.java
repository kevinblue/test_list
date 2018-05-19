package com.tenwa.leasing.service.FillFlowDataUtil;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FillFlowDataUtil {
	public void saveFillFlowDataUtil(HttpServletRequest request,String flowunid,String histaskid,String action) throws Exception;
	public void saveFillFlowData(HttpServletRequest request) throws Exception;
}