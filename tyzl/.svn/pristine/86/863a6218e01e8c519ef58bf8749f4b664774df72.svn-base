package com.tenwa.leasing.service.finance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;

import com.tenwa.business.entity.User;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.finance.FinancialData;
import com.tenwa.leasing.entity.finance.FinancialSubjects;

public interface FinanceService {
      public String importFinance(HttpServletRequest request,Map<String,String> model) throws Exception;
      public Map<String,List> getSheetRowConfig(Workbook wb) throws Exception;
      public String importFinanceDateByConfig(Workbook wb,Map<String,List> tableconfig,CustInfo cust,User user) throws Exception;
      public String getKeyDate(String key ) throws Exception;
      public FinancialData getFinanceData(CustInfo custinfo,FinancialSubjects fs,String financialDate ) throws Exception;
      public List<Map<String, Object>> getProjectYear(Map<String, String> model) throws Exception;
      public void modifyFinanceData(Map<String,String> model) throws Exception;
      public void removeFinanceData(Map<String,String> model) throws Exception;
	  public String addFinance(HttpServletRequest request, Map<String, String> model) throws Exception;

}
