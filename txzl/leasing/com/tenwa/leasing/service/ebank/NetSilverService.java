package com.tenwa.leasing.service.ebank;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface NetSilverService {
   public void saveNetSilverRefund(HttpServletRequest request,Map<String,String> model) throws Exception;
}