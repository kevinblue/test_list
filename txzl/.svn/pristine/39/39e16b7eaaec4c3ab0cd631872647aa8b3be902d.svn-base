package com.tenwa.leasing.serviceImpl.ebank;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.service.ebank.NetSilverService;
@Service(value="NetSilverService")
public class NetSilverServiceImpl implements NetSilverService{
	@Resource(name="tableService")
	private TableService tableService;
	@Override
	public void saveNetSilverRefund(HttpServletRequest request,
			Map<String, String> model) throws Exception {
		String sum = model.get("sum");
		String sumsummary = model.get("sumsummary");
		BigDecimal bdsum = new BigDecimal(sum);
		   FundEbankData info = this.tableService.findEntityByID(FundEbankData.class, model.get("ebank_id"));
		   info.setHisRefundSummary(sumsummary);
		   info.setAlreadyRefundAmount(bdsum);
		   this.tableService.updateEntity(info);
	}
   
}
