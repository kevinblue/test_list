package com.reckon.controller.adjustInterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.inject.internal.Maps;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContract;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.reckon.renttranrate.service.TransferInterestService;
import com.tenwa.business.controller.BaseController;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;

/**
 * @author Chennes
 * @version 1.0
 */
@Controller(value = "adjustInterestController")
@RequestMapping(value = "/**/acl")
public class AdjustInterestController extends BaseController  {
	
	private Logger logger=Logger.getLogger(AdjustInterestController.class);
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name="transferInterestService")
	private TransferInterestService transferInterest;
	

	
	/**
	 * 保存调息信息到临时表数据<br>
	 * @param request<br>
	 * @param response<br>
	 * @return string<br>
	 * @throws Exception
	 */
	@RequestMapping(value = "/addAdjustinterest.acl")
	@ResponseBody
	public String addAdjustInterest(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		logger.debug("央行调息表UUID:"+request.getParameter("adjustid"));
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		
		String adjustId = model.get("adjustid");
		String docId= model.get("docid");
		String contractId= model.get("contractid");
		//----Start加入调息算法
		String msg="无反馈";
		try{
			msg= transferInterest.updateConditionByTransferInterest(adjustId, docId, contractId);
		}catch (Exception e) {
			removeAdjustInterest(request,response);
			logger.error(e.getMessage(), e);
			msg="调息中出错:"+e.getMessage();
		}
		//----End加入调息算法
		msg=msg.replaceAll("\\\\n", "<br>");
		return msg;
	}
	/**
	 * 撤销调息信息临时表数据<br>
	 * @param request<br>
	 * @param response<br>
	 * @return		<br>
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeAdjustinterest.acl")
	@ResponseBody
	public String removeAdjustInterest(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String adjustId = model.get("adjustid");
		String docId= model.get("docid");
		String contractId= model.get("contractid");
		//----Start加入调息算法
		String msg="无反馈";
		try{
			msg= transferInterest.removeConditionByTransferInterest(adjustId, docId, contractId);
		}catch (Exception e) {
			logger.error(e.getMessage());
			msg="撤销调息中出错:"+e.getMessage();
		}
		//----End加入调息算法
		msg=msg.replaceAll("\\\\n", "<br>");
		return msg;
	}
	
	/**
	 * 获取调息临时信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInterestTempBaseInfo.acl")
	@ResponseBody
	public String getTempBaseInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String contractPutNumber = model.get("contractputnumber");
		String docid = model.get("docid");

		// 查合同表
		HashMap<String, Object> Map = new HashMap<String, Object>();
		Map.put("contractPutNumber", contractPutNumber);
		List<ContractInfo> list_cinfo = new ArrayList<ContractInfo>();
		list_cinfo = this.tableService.findEntityByProperties(
				ContractInfo.class, Map);
		ContractInfo cinfo = list_cinfo.get(0);

		// 调息信息
		HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("contractId", cinfo);
		propertiesMap.put("docId", docid);
		List<FundAdjustInterestContractTemp> list_Interest = new ArrayList<FundAdjustInterestContractTemp>();
		list_Interest = this.tableService.findEntityByProperties(
				FundAdjustInterestContractTemp.class, propertiesMap);
		FundAdjustInterestContractTemp fundInterest = list_Interest.get(0);

		Map<String, Object> map = Maps.newHashMap();
		map.put("id", fundInterest.getId());
		map.put("contractputnum", cinfo.getContractPutNumber());
		map.put("custname", cinfo.getCustId().getCustName().toString());
		map.put("rateoriginal", fundInterest.getRateOriginal());
		map.put("rateadjust", fundInterest.getRateAdjust());
		map.put("oldirr", fundInterest.getOldIrr());
		map.put("newirr", fundInterest.getNewIrr());

		ObjectMapper mapper = new ObjectMapper();
		String InterestString = mapper.writeValueAsString(map);
		return InterestString;
	}

	/**
	 * 获取调息基本信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInterestBaseInfo.acl")
	@ResponseBody
	public String getBaseInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String contractputnumber = model.get("contractputnumber");
		String docid =  model.get("docid");

		// 查合同表
		HashMap<String, Object> Map = new HashMap<String, Object>();
		Map.put("contractPutNumber", contractputnumber);
		List<ContractInfo> list_cinfo = new ArrayList<ContractInfo>();
		list_cinfo = this.tableService.findEntityByProperties(
				ContractInfo.class, Map);
		ContractInfo cinfo = list_cinfo.get(0);

		// 调息信息
		HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("contractId", cinfo);
		propertiesMap.put("docId", docid);
		List<FundAdjustInterestContract> list_Interest = new ArrayList<FundAdjustInterestContract>();
		list_Interest = this.tableService.findEntityByProperties(
				FundAdjustInterestContract.class, propertiesMap);
		Map<String, Object> map = Maps.newHashMap();
		if (list_Interest.size() > 0) {
			FundAdjustInterestContract fundInteresthis = list_Interest.get(0);

			map.put("id", fundInteresthis.getId());
			map.put("Contractid", fundInteresthis.getContractId().toString());
			map.put("contractputnum", cinfo.getContractPutNumber());
			map.put("custname", cinfo.getCustId().getCustName().toString());
//			map.put("dealername", cinfo.getCustDealer().getCustName()
//					.toString());
			 map.put("rateoriginal",fundInteresthis.getRateOriginal());
			 map.put("rateadjust",fundInteresthis.getRateAdjust());
			 map.put("oldirr",fundInteresthis.getOldIrr());
			 map.put("newirr",fundInteresthis.getNewIrr());
		}
		ObjectMapper mapper = new ObjectMapper();
		String InterestString = mapper.writeValueAsString(map);
		return InterestString;
	}
}
