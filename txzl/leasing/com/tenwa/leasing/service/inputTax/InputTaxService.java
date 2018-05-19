package com.tenwa.leasing.service.inputTax;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InputTaxService {
   public void createInputTax(Map<String,String> model) throws Exception;
}
