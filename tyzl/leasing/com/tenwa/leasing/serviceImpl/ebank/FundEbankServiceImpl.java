package com.tenwa.leasing.serviceImpl.ebank;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.service.ebank.FundEbankService;

@Service(value="fundEbankService")
public class FundEbankServiceImpl implements FundEbankService{
	
	private Logger logger=Logger.getLogger(FundEbankServiceImpl.class);

	@Resource(name="tableService")
	private TableService tableService;

	//网银废弃数据方法
	@Override
	public String delEbankInfoImpl(Map<String, String> model) throws Exception 
	{
		logger.info("delEbankInfoImpl()重要参数的值："+"参数1："+model.get("id")
								 +"   参数2："+model.get("isdiuse"));
		String ebankPkId = model.get("id");
		//减少jar依赖   String 判断为空   采取调用原生jdK  length()方法
		// 修改字段  Isdiuse  是否废弃  0为 废弃
		if ( ebankPkId.length() != 0 && null != ebankPkId )
		{
			Map<String, Object> propertiesMap =new HashMap<String, Object>();
			propertiesMap.put("ebdataId.id",ebankPkId);
			List<FundEbankProcess> proList = this.tableService.findEntityByProperties(FundEbankProcess.class, propertiesMap);
			if ( proList.size() > 0 )
			{
				return "process";
			}
			else
			{
				String sql ="update fund_ebank_data set INVALID_ ='是' where id= '"+ebankPkId +"'";
				this.tableService.updateBySql(sql);
				return "yes";
			}
		}
		//exec 
		else{
			return "no";
		}
	}
	  

	 
	
}
