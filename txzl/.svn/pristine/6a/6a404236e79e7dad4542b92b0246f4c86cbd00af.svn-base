package com.tenwa.leasing.serviceImpl.vouchersFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.PranayamaChangeVoucherService;
import com.tenwa.leasing.utils.Tools;

/**   
*    
* 项目名称：tls5.1   
* 类名称：ReceivablesVoucherServicempl   
* 类描述：   
* 创建人：sunz   
* 创建时间：2016年6月12日 上午10:32:15   
* @version        
*/
@Service(value = "pranayamaChangeVoucherService")
public class PranayamaChangeVoucherServicempl implements PranayamaChangeVoucherService {

	private Logger logger=Logger.getLogger(PranayamaChangeVoucherServicempl.class);
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="调息流程";

	@Override
	public void createVoucherReceivePranayama(Map<String, String> variablesMap)
			throws Exception {
		logger.info("调息凭证开始！");
		
		User currentUser = SecurityUtil.getPrincipal();
		Map<String, String> headMap = new HashMap<String, String>();
		headMap.put("moduleName", modlename);
		
		//集合传入的数据是一个list集合，里面是一个个的map集合，一个map封装一个凭证分录
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		String adjustcontractids=variablesMap.get("adjust_contractids");
		String[] contractid=adjustcontractids.split(",");
		String docid=variablesMap.get("docid");
		BigDecimal recesunmma= BigDecimal.ZERO; //租赁款
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String factdate=format.format(date); //网银到账日给当前系统时间
		for(int i=0;i<contractid.length;i++){
			String ciid=contractid[i];
			headMap.put("contract_id", ciid); 
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("docid", docid);
			queryMainObjectMap.put("ciid", ciid);
			String interest=this.tableService.getJsonArrayData("eleasing/workflow/rent/regulating_breathing/Interestdifference.xml", queryMainObjectMap).toString();
			JSONArray jsonarray=new JSONArray(interest);
			for(int j=0;j<jsonarray.length();j++){
				JSONObject jsonobject=jsonarray.getJSONObject(j);
				recesunmma=new BigDecimal(jsonobject.getString("interest")); //利益差
			}
			ContractInfo contractinfo=new ContractInfo();
			contractinfo=this.tableService.findEntityByID(ContractInfo.class, ciid);
			String contractnum=contractinfo.getContractNumber(); 	//摘要里面的合同号
			String custname=contractinfo.getCustId().getCustName();	//承租人姓名
			String interestInvoice=contractinfo.getContractInvoiceType().getInterestInvoiceRatio(); // 利息开票税率
			
			String summay="合同号-"+contractnum+"-承租人-"+custname+"-调息流程";
			//借 
			Map<String, String> mapj = new HashMap<String, String>();		
			mapj.put("voucherSummary", summay); //摘要
			mapj.put("subjectsId", "101");	//科目代码
			mapj.put("debitMoney", Tools.formatNumberDoubleTwo(recesunmma.toString()));	//借
			mapj.put("lenderMoney", "0");  //贷
			mapj.put("ebankFactDate",factdate);//网银日期
			mapj.put("accountDate",factdate);	//会计到账日
			list.add(mapj);
			
			
			//贷  ：租赁收益
			BigDecimal profit= BigDecimal.ZERO; 
			BigDecimal one=new BigDecimal("0.17");
			if(interestInvoice!=null){								
				BigDecimal inte=new BigDecimal(interestInvoice.toString()); //开票税率转换成BigDecimal类型
				profit=recesunmma.divide(inte.add(BigDecimal.ONE),2,BigDecimal.ROUND_HALF_UP); //租赁收益
			}else{
				profit=recesunmma.divide(BigDecimal.ONE.add(one),2,BigDecimal.ROUND_HALF_UP); //租赁收益
			}			
			Map<String, String> mapd = new HashMap<String, String>();
			mapd.put("voucherSummary", summay); //摘要
			mapd.put("subjectsId", "102");
			mapd.put("debitMoney", "0");
			mapd.put("lenderMoney",Tools.formatNumberDoubleTwo(profit.toString())); 
			mapd.put("ebankFactDate",factdate);//网银日期
			mapd.put("accountDate", factdate);
			list.add(mapd);
			
			//贷  ：租赁税金
			BigDecimal tax= BigDecimal.ZERO; 
			tax=recesunmma.subtract(profit);
			Map<String, String> mapf = new HashMap<String, String>();
			mapf.put("voucherSummary", summay); //摘要
			mapf.put("subjectsId", "102");
			mapf.put("debitMoney", "0");
			mapf.put("lenderMoney",Tools.formatNumberDoubleTwo(tax.toString())); 
			mapf.put("ebankFactDate",factdate);//网银日期
			mapf.put("accountDate", factdate);
			list.add(mapf);
			
			if(list!=null&&list.size()>0){
				voucherToV8Service.saveV8Message(headMap, list, currentUser);
			}
			logger.info(list);
			logger.info("调息结束");
		}
		
		
	}
	

	
	
	
}
