package com.tenwa.leasing.serviceImpl.contractcomm;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contractcomm.AbstactContractMethod;

@Service(value="abstactContractMethod")
public class AbstactContractMethodImpl implements AbstactContractMethod {

	@Resource(name = "tableService")
	private TableService tableService; 
	
	Logger logger =Logger.getLogger(AbstactContractMethodImpl.class);
	
	private  ProjInfo projInfo ;
	private ContractInfo contractInfo ;
	
	@Override
	public void getProjOtherBussieseeMethodsInfo(
			Object o,
			Map<String, String> mainObjectMap, 
			Map<String, String> variablesMap) 
	{
		try{
			if (o instanceof ProjInfo)
			{
				projInfo = (ProjInfo)o;
				if( !projInfo.getProjEquips().isEmpty() ) // 租赁物明细
					this.getOtherBussieseeMethodsInfo(mainObjectMap,variablesMap,"json_proj_equip_str","eleasing/workflow/proj_approval/proj_equip.xml");
				if ( !projInfo.getProjGuaranteeMethods().isEmpty() ) // 担保单位
					this.getOtherBussieseeMethodsInfo(mainObjectMap,variablesMap,"json_proj_guarantee_detail_str","eleasing/workflow/proj_approval/proj_guarantee_method.xml");
				if ( !projInfo.getProjGuaranteeEquips().isEmpty() ) // 抵押物清单
					this.getOtherBussieseeMethodsInfo(mainObjectMap,variablesMap,"json_proj_guaranty_detail_str","eleasing/workflow/proj_approval/proj_guarantee_equip.xml");
			}
			//-------------------------下面是合同层----------------------------
			if ( o instanceof ContractInfo )
			{
				contractInfo= (ContractInfo)o;
				if( !contractInfo.getContractEquips().isEmpty() )
					this.getOtherBussieseeMethodsInfo(mainObjectMap,variablesMap,"","");
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		
	}

	public void getOtherBussieseeMethodsInfo(
			Map<String, String> mainObjectMap,
			Map<String, String> variablesMap, 
			String jsonStr, 
			String xmlStr) 
	{
		try
		{
			variablesMap.put(jsonStr, this.tableService.getJsonArrayData(xmlStr, mainObjectMap).toString());
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	}
	
	/**
	 * 获取项目(合同)基本信息
	 */
	@Override
	public void getProjToMapProjBaseInfo(Object o,
			Map<String, String> variablesMap) 
	{
		try
		{
			this.getEntityStringMap(o, variablesMap);
		}
		catch(Exception e){e.getStackTrace();}
	}
	
	public void getEntityStringMap(Object o,Map<String, String> variablesMap)
	{
		try
		{
			if (o instanceof ProjInfo)
			{
				projInfo = (ProjInfo)o;
				variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(projInfo,null, "proj_info"));
				variablesMap.put("rawValue_proj_info.department", projInfo.getDepartment().getName());
				//variablesMap.put("rawValue_proj_info.userbyprojregistrar", projInfo.getProjRegistrar()==null?"":projInfo.getProjRegistrar().getRealname());
				variablesMap.put("rawValue_proj_info.userbyprojmanage", projInfo.getProjManage()==null?"":projInfo.getProjManage().getRealname());
				variablesMap.put("rawValue_proj_info.userbyprojassist", projInfo.getProjAssist()==null?"":projInfo.getProjAssist().getRealname());
				
				CustInfo cinfo = projInfo.getCustInfo();
				if( null != cinfo ){
					variablesMap.put("proj_info.custname",cinfo.getCustName()==null?"":cinfo.getCustName());
					variablesMap.put("proj_info.custid",cinfo.getId()==null?"":cinfo.getId());
					variablesMap.put("proj_info.custnumber",cinfo.getCustNumber()==null?"":cinfo.getCustNumber());
					variablesMap.put("proj_info.custclass",cinfo.getCustClass()==null?"":cinfo.getCustClass().getCode());
				}
			}
		    else
		    {
		    	contractInfo= (ContractInfo)o;
		    	variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractInfo,null, "contract_info"));
		    	
		    }
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	}
	 
}
