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
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.invoice.VatInvoiceContract;
import com.tenwa.leasing.entity.invoice.VatInvoiceInfo;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.InvoiceVoucherService;
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
@Service(value = "invoiceVoucherService")
public class InvoiceVoucherServicempl implements InvoiceVoucherService {
	
	private Logger logger=Logger.getLogger(InvoiceVoucherServicempl.class);

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="发票认证";

	@Override
	public void createVoucherReceiveInvoice(VatInvoiceInfo vatInvoiceInfo)
			throws Exception {
		//logger.info("发票认证凭证开始！");
		
		User currentUser = SecurityUtil.getPrincipal();
		Map<String, String> headMap = new HashMap<String, String>();
		headMap.put("moduleName", modlename);
		
		//集合传入的数据是一个list集合，里面是一个个的map集合，一个map封装一个凭证分录
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		String invoiceno=vatInvoiceInfo.getInvoiceNo(); //发票号码
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("invoiceNo", invoiceno);
		VatInvoiceInfo vatInvoice=this.tableService.findEntityByProperties(VatInvoiceInfo.class, propertiesMap).get(0);
		String vatinvoiceid=vatInvoice.getId();
		BigDecimal taxmoney=vatInvoice.getTaxMoney(); //税额
		Map<String, Object> propertiesMap1 = new HashMap<String, Object>();
		propertiesMap1.put("invoiceId", vatInvoice);
		VatInvoiceContract vatInvoiceContract=this.tableService.findEntityByProperties(VatInvoiceContract.class, propertiesMap1).get(0);
		String contractid=vatInvoiceContract.getContractId().getId();
		String contractnum=vatInvoiceContract.getContractId().getContractNumber(); //合同编号
		String custname=vatInvoiceContract.getContractId().getCustId().getCustName(); //承租人
		headMap.put("contract_id", contractid); 
		String summay="合同号-"+contractnum+"-承租人-"+custname+"-进项税认证";
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String factdate=format.format(date); //网银到账日给当前系统时间
		
		//借  107
		Map<String, String> mapj = new HashMap<String, String>();		
		mapj.put("voucherSummary", summay); //摘要
		mapj.put("subjectsId", "107");	//科目代码
		mapj.put("debitMoney", Tools.formatNumberDoubleTwo(taxmoney.toString()));	//借
		mapj.put("lenderMoney", "0");  //贷
		mapj.put("ebankFactDate",factdate);//网银日期
		mapj.put("accountDate",factdate);	//会计到账日
		list.add(mapj);
		
		//贷  ：租赁税金
		Map<String, String> mapf = new HashMap<String, String>();
		mapf.put("voucherSummary", summay); //摘要
		mapf.put("subjectsId", "104");
		mapf.put("debitMoney", "0");
		mapf.put("lenderMoney",Tools.formatNumberDoubleTwo(taxmoney.toString())); 
		mapf.put("ebankFactDate",factdate);//网银日期
		mapf.put("accountDate", factdate);
		list.add(mapf);
		
		if(list!=null&&list.size()>0){
			voucherToV8Service.saveV8Message(headMap, list, currentUser);
		}
	//	logger.info(list);
	//	logger.info("发票认证凭证结束！");
	
	
	
	
	
	
	
	}
	

	
	
	
}
