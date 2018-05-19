package com.tenwa.leasing.service.invoice;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.kernal.utils.QueryUtil;

public interface InvoiceToSajtService {
	public void sendInvoiceToSajt(String command,String xmlType) throws Exception ;
	public void generateInoviceToSajt(Map<String,String> variablesMap) throws Exception;
	public void printInvoiceToSajt(Map<String,String> variablesMap) throws Exception;
	public void voidedInvoiceToSajt(Map<String,String> variablesMap) throws Exception;
	//public void createSajtXmlFile(String responseXmlStr) throws Exception;
}
