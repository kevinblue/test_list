package com.reckon.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reckon.service.CustInfoDataTohisService;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.contract.ContractCustHis;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;


@Service(value = "custInfoDataTohisService")
public class CustInfoDataTohisServiceImpl implements CustInfoDataTohisService{
	@Resource(name = "baseService")
	private BaseService baseService;
	@Override
	public void saveCustInfoDataToHis(ContractInfo contractInfo, String docId,
			String hisType, CustInfo newCustInfo) throws Exception {
		//先设定入历史表的类型
		Map<String, Object> whereMap=new HashMap<String, Object>();
		DictionaryData hisBefore=new DictionaryData();
		DictionaryData hisAfter=new DictionaryData();
		DictionaryData hisTypeDict=new DictionaryData();
		whereMap.put("code", "his_status_before");
		hisBefore=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		whereMap.put("code", "his_status_after");
		hisAfter=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		whereMap.put("code", hisType);
		hisTypeDict=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		// 1.承租人入历史表(前)
		ContractCustHis contractCustHis = new ContractCustHis();
		contractCustHis.setContractId(contractInfo);
		contractCustHis.setCustID(contractInfo.getCustId());
		contractCustHis.setFlowUnid(docId);
		contractCustHis.setModStatus(hisBefore);
		contractCustHis.setModReason(hisTypeDict);
		contractCustHis.setCreateDate(DateUtil.getSystemDateTime());
		this.baseService.saveEntity(contractCustHis);
		// 2.更新合同信息
		contractInfo.setCustId(newCustInfo);
		this.baseService.updateEntity(contractInfo);
		// 3.承租人入历史表(后)
		contractCustHis = new ContractCustHis();
		contractCustHis.setContractId(contractInfo);
		contractCustHis.setCustID(newCustInfo);
		contractCustHis.setFlowUnid(docId);
		contractCustHis.setModStatus(hisAfter);
		contractCustHis.setModReason(hisTypeDict);
		contractCustHis.setCreateDate(DateUtil.getSystemDateTime());
		this.baseService.saveEntity(contractCustHis);
	}

}
