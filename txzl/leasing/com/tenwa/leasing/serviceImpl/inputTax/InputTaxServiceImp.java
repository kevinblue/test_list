package com.tenwa.leasing.serviceImpl.inputTax;

import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.tenwa.leasing.service.inputTax.InputTaxService;
import com.tenwa.leasing.service.vouchersFactory.InputTaxVoucherService;

@Service(value ="inputTaxService")
public class InputTaxServiceImp implements InputTaxService{
    
	@Resource(name="inputTaxVoucher")
	private InputTaxVoucherService inputtaxvoucher;
	
	@Override
	public void createInputTax(Map<String, String> model) throws Exception {
              this.inputtaxvoucher.createInputTaxVoucher(model);
	}


}
