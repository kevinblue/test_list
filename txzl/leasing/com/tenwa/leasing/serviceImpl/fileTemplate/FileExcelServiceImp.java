package com.tenwa.leasing.serviceImpl.fileTemplate;
 
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.BeanFieldUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.MoneyUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustTypeInfo;
import com.tenwa.leasing.entity.file.BaseFileTemplate;
import com.tenwa.leasing.entity.finacial.DepositInterest;
import com.tenwa.leasing.entity.finacial.DepositInterestInfo;
import com.tenwa.leasing.entity.finacial.InterestProvision;
import com.tenwa.leasing.entity.finacial.InterestProvisionInfo;
import com.tenwa.leasing.entity.finacial.LoanAccount;
import com.tenwa.leasing.entity.finacial.LoanFeeIncome;
import com.tenwa.leasing.entity.finacial.LoanFeePlan;
import com.tenwa.leasing.entity.finacial.LoanRentIncome;
import com.tenwa.leasing.entity.finacial.LoanRentIncomeToInterest;
import com.tenwa.leasing.entity.finacial.LoanRentPlan;
import com.tenwa.leasing.entity.finacial.LoanRentPlanToInterest;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.FundInvoiceInfo;
import com.tenwa.leasing.entity.invoice.InvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceInfo;
import com.tenwa.leasing.entity.invoice.RentPayNoticeRecord;
import com.tenwa.leasing.entity.invoice.VatInvoiceInfo;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.proj.ProjProgress;
import com.tenwa.leasing.entity.proj.ProjWindResource;
import com.tenwa.leasing.entity.proj.ProjectProgress;
import com.tenwa.leasing.entity.proj.WindPowerInvestmentDetails;
import com.tenwa.leasing.entity.proj.WpBuildCost;
import com.tenwa.leasing.entity.proj.WpEquipCost;
import com.tenwa.leasing.entity.proj.WpOtherCost;
import com.tenwa.leasing.entity.proj.WpSetupCost;
import com.tenwa.leasing.entity.voucher.InterV8Vouchers;
import com.tenwa.leasing.service.fileTemplate.FileExcelService;
import com.tenwa.leasing.service.fileTemplate.FileTemplateService;
import com.tenwa.leasing.service.vouchersFactory.EbankImportService;
import com.tenwa.leasing.service.vouchersFactory.InvoiceVoucherService;
import com.tenwa.leasing.utils.Tools;
import com.tenwa.leasing.utils.WorkflowUtil;

import edu.emory.mathcs.backport.java.util.Arrays;


/**   
*    
* 项目名称：tls5.1   
* 类名称：FileExcelServiceImp   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年11月25日 下午3:48:53   
* @version        
*/
@Service(value="fileExcelService")
public class FileExcelServiceImp implements FileExcelService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	
	@Resource(name="ebankImportService")
	private EbankImportService ebankImportService;
	
	@Resource(name="invoiceVoucherService")
	private InvoiceVoucherService invoiceVoucherService;
	
	private Logger logger = Logger.getLogger(FileExcelServiceImp.class);
	public final static List<String> EbankDataList=new ArrayList<String>();
	 
	//回调网银上传数据
	@Override
	public Object checkFundEbankInfoImport(FundEbankData fundEbankData,Map<String, String> model,Integer rowIndex) throws Exception 
    {
			if(null==fundEbankData.getSn()||fundEbankData.getSn().length() == 0) 
			{
				EbankDataList.clear();
				throw new BusinessException("第"+rowIndex+"行流水号不能为空");
			}
			 else if(null==fundEbankData.getOwnBank() || fundEbankData.getOwnBank().length() == 0  ) 
			 {
					EbankDataList.clear();
				throw new BusinessException("第"+rowIndex+"行本方银行不能为空");
			 }
			 else if(null== fundEbankData.getOwnAccount()|| fundEbankData.getOwnAccount().length() == 0  ) 
			 {
					EbankDataList.clear();
				 throw new BusinessException("第"+rowIndex+"行本方账户不能为空");
			 }
			 else if(null== fundEbankData.getOwnAccNumber() ||fundEbankData.getOwnAccNumber().length() == 0 ) 
			 {
					EbankDataList.clear();
				 throw new BusinessException("第"+rowIndex+"行本方账号不能为空");
			 }
//			 else if(null==fundEbankData.getClientBank()||fundEbankData.getClientBank().length() == 0  ) 
//			 {
//					EbankDataList.clear();
//				 throw new BusinessException("第"+rowIndex+"行对方银行不能为空");
//			 }
//			 else if(null==fundEbankData.getClientAccount()||fundEbankData.getClientAccount().length() == 0 ) 
//			 {
//					EbankDataList.clear();
//				 throw new BusinessException("第"+rowIndex+"行对方账户不能为空");
//			 }
//			 else if(null==fundEbankData.getClientAccNumber()||fundEbankData.getClientAccNumber().length() == 0  )
//			 {
//					EbankDataList.clear();
//				 throw new BusinessException("第"+rowIndex+"行对方账号不能为空");
//			 }
			 else if(null==fundEbankData.getClientName()||fundEbankData.getClientName().length() == 0  ) 
			 {
					EbankDataList.clear();
				 throw new BusinessException("第"+rowIndex+"行付款人不能为空");
			 }
			 else if(null==fundEbankData.getFactDate() ||fundEbankData.getFactDate().length() == 0 ) 
			 {
					EbankDataList.clear();
				 throw new BusinessException("第"+rowIndex+"行到账时间不能为空");
			 }
			 else if(null==fundEbankData.getFactMoney() ) 
			 {
					EbankDataList.clear();
				 throw new BusinessException("第"+rowIndex+"行到账金额不能为空");
			 }
			Map<String,Object> propertiesMap = new HashMap<String,Object>();
			List<FundEbankData> EbankSnList = null;
			propertiesMap.put("sn", fundEbankData.getSn());
			propertiesMap.put("invalid", "否");
			//流水号判断
			EbankSnList = this.tableService.findEntityByProperties(FundEbankData.class, propertiesMap);
			if (EbankSnList.size() > 0)
			{
				EbankDataList.clear();
				throw new BusinessException("第"+rowIndex+"行流水号("+fundEbankData.getSn()+")不能与系统中流水号重复!");
			}
			//增加本次流水号重复判断
			if( EbankDataList.contains(fundEbankData.getSn()) ){
				EbankDataList.clear();
				throw new BusinessException("第"+rowIndex+"行流水号("+fundEbankData.getSn()+")在excel重复了!");
			}
			logger.info("本次上传的流水号:"+fundEbankData.getSn());
			EbankDataList.add(fundEbankData.getSn());
			//导入本方账号与系统中维护的本方账号对比判断
			propertiesMap.clear();
			propertiesMap.put("accNumber", fundEbankData.getOwnAccNumber());
			List<OwnAccount> ownaccnumberList = this.tableService.findEntityByProperties(OwnAccount.class, propertiesMap);
			if ( ownaccnumberList.size() > 0)
			{
			}else{
				EbankDataList.clear();
				throw new BusinessException("第"+rowIndex+"行本方账号("+fundEbankData.getOwnAccNumber()+")在系统中未维护,请在本方信息中维护该账号信息!");
			}
		  
			fundEbankData.setInvalid("否");
		   fundEbankData.setCoin("RMB");
		   fundEbankData.setMoneyType("人民币");
		   fundEbankData.setNoWithMoney(BigDecimal.ZERO);
		   fundEbankData.setUploaddate(DateUtil.getSystemDateTime());
		   String ebankNumber = WorkflowUtil.getEbankNoInfoSerialNumber(null,tableService.getBussinessDao().getHibernateTemplate(), tableService.getBussinessDao().getJdbcTemplate());
		   fundEbankData.setEbdataId(ebankNumber);
		   fundEbankData.setFundMoney(BigDecimal.ZERO);
		   fundEbankData.setHadMoney(BigDecimal.ZERO);
		   fundEbankData.setMayOpeMoney(fundEbankData.getFactMoney());//姚建议加   (初始化) 可核销余额 =到账金额
		   return fundEbankData;
	}
	/*
	 * 资金发票回导数据检查
	 * @see com.tenwa.leasing.service.fileTemplate.FileExcelService#checkFundInvoiceDownloadInfoImport(com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo, java.util.Map, java.lang.Integer)
	 */
	@Override
	public Object checkFundInvoiceDownloadInfoImport(FundInvoiceDownloadInfo taxFui,
			Map<String, String> model,Integer rowIndex) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String nowdate = DateUtil.getSystemDateTime();
		if(taxFui.getOutNo() == null || "".equals(taxFui.getOutNo())){
			throw new BusinessException("第"+rowIndex+"行发票编号不能为空");
		}else if(taxFui.getInvoiceNo() == null || "".equals(taxFui.getInvoiceNo())){
			throw new BusinessException("第"+rowIndex+"行发票号码不能为空");
		}else if(taxFui.getInvoiceMoney() == null || "".equals(taxFui.getInvoiceMoney())){
			throw new BusinessException("第"+rowIndex+"行含税总金额不能为空");
		}else if(taxFui.getInvoiceDate() == null || "".equals(taxFui.getInvoiceDate())){
			throw new BusinessException("第"+rowIndex+"行开票日期不能为空");
		}else if(taxFui.getTaxMoney() == null || "".equals(taxFui.getTaxMoney())){
			throw new BusinessException("第"+rowIndex+"行合计税额不能为空");
		}else if(taxFui.getTaxRate() == null || "".equals(taxFui.getTaxRate())){
			throw new BusinessException("第"+rowIndex+"行税率不能为空");
		}
		
		Map<String,Object> propertiesNO = new HashMap<String,Object>();
		propertiesNO.put("invoiceNo", taxFui.getInvoiceNo());
		List<FundInvoiceDownloadInfo> downinfosNO = this.tableService.findEntityByProperties(FundInvoiceDownloadInfo.class, propertiesNO);
		if(downinfosNO.size()>0){
			throw new BusinessException("第"+rowIndex+"行发票号已经回导，不能重复回导!!!");
		}
		
		Map<String,Object> propertiesMap = new HashMap<String,Object>();
		List<FundInvoiceDownloadInfo> downinfos = new ArrayList<FundInvoiceDownloadInfo>();
		propertiesMap.put("outNo", taxFui.getOutNo());
		
		downinfos = this.tableService.findEntityByProperties(FundInvoiceDownloadInfo.class, propertiesMap);
		FundInvoiceDownloadInfo info;
		if(downinfos.size()>0){
			info = downinfos.get(0);
			if(taxFui.getInvoiceMoney().compareTo(info.getInvoiceMoney())!=0){
				throw new BusinessException("第"+rowIndex+"行发票金额与导出不相等");
			}
			info.setInvoiceNo(taxFui.getInvoiceNo());//保存发票号
			info.setInvoiceMoney(taxFui.getInvoiceMoney());//保存开票金额
			info.setInvoiceDate(taxFui.getInvoiceDate());//保存开票时间
			info.setTaxMoney(taxFui.getTaxMoney());//保存销项税金
			info.setTaxRate(taxFui.getTaxRate());//保存税率
			info.setIsBackImport("1");//1代表已回导
			//修该信息
			info.setUpLoadId(taxFui.getUpLoadId());
			info.setModificator(user);
			info.setModifyDate(nowdate);
		}else{
			throw new BusinessException("该导出流水号不存在记录！！！");
		}
		return info;
	}
	
	/*
	 * 租金发票回导数据检查
	 * @see com.tenwa.leasing.service.fileTemplate.FileExcelService#checkFundInvoiceDownloadInfoImport(com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo, java.util.Map, java.lang.Integer)
	 */
	@Override
	public Object checkRentInvoiceDownloadInfoImport(RentInvoiceDownloadInfo taxFui,
			Map<String, String> model,Integer rowIndex) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String nowdate = DateUtil.getSystemDateTime();
		if(taxFui.getOutNo() == null || "".equals(taxFui.getOutNo())){
			throw new BusinessException("第"+rowIndex+"行发票编号不能为空");
		}else if(taxFui.getInvoiceNo() == null || "".equals(taxFui.getInvoiceNo())){
			throw new BusinessException("第"+rowIndex+"行发票号码不能为空");
		}else if(taxFui.getInvoiceMoney() == null || "".equals(taxFui.getInvoiceMoney())){
			throw new BusinessException("第"+rowIndex+"行含税总金额不能为空");
		}else if(taxFui.getInvoiceDate() == null || "".equals(taxFui.getInvoiceDate())){
			throw new BusinessException("第"+rowIndex+"行开票日期不能为空");
		}else if(taxFui.getTaxMoney() == null || "".equals(taxFui.getTaxMoney())){
			throw new BusinessException("第"+rowIndex+"行合计税额不能为空");
		}else if(taxFui.getTaxRate() == null || "".equals(taxFui.getTaxRate())){
			throw new BusinessException("第"+rowIndex+"行税率不能为空");
		}
		
		Map<String,Object> propertiesNO = new HashMap<String,Object>();
		propertiesNO.put("invoiceNo", taxFui.getInvoiceNo());
		List<RentInvoiceDownloadInfo> downinfosNO = this.tableService.findEntityByProperties(RentInvoiceDownloadInfo.class, propertiesNO);
		if(downinfosNO.size()>0){
			throw new BusinessException("第"+rowIndex+"行发票号已经回导，不能重复回导!!!");
		}
		
		Map<String,Object> propertiesMap = new HashMap<String,Object>();
		List<RentInvoiceDownloadInfo> downinfos = new ArrayList<RentInvoiceDownloadInfo>();
		propertiesMap.put("outNo", taxFui.getOutNo());
		
		downinfos = this.tableService.findEntityByProperties(RentInvoiceDownloadInfo.class, propertiesMap);
		RentInvoiceDownloadInfo info;
		if(downinfos.size()>0){
			info = downinfos.get(0);
		    if(taxFui.getInvoiceMoney().compareTo(info.getInvoiceMoney())!=0){
			   throw new BusinessException("第"+rowIndex+"行发票金额与导出不相等");
		    }
			info.setInvoiceNo(taxFui.getInvoiceNo());//保存发票号
			info.setInvoiceMoney(taxFui.getInvoiceMoney());//保存开票金额
			info.setInvoiceDate(taxFui.getInvoiceDate());//保存开票时间
			info.setTaxMoney(taxFui.getTaxMoney());//保存销项税金
			info.setTaxRate(taxFui.getTaxRate());//保存税率
			info.setIsBackImport("1");//1代表已回导
			//修该信息
			info.setUpLoadId(taxFui.getUpLoadId());
			info.setModificator(user);
			info.setModifyDate(nowdate);
		}else{
			throw new BusinessException(taxFui.getOutNo()+"该导出流水号不存在记录！！！");
		}
		
		return info;
	}
	
	/**
	 * @param model 
	 * @throws BusinessException 
	 * 导出计划开票前插入数据库
	 */
	public  void exportFundInvoiceBefore(Map<String,Object> model) throws BusinessException{
		try {
			//导出数据参数
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("ids",(String) model.get("ids"));
			JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/fund_invoice/invoicePlan/fund_invoice_plan_confirm_exp.xml", queryMainObjectMap);
			//拆分后记录
			JSONArray newexportdata = new JSONArray();
			//获取拆分金额
			DictionaryData dictmoney=this.baseService.findEntityByID(DictionaryData.class,"invoicemoney.01");
			BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); //BigDecimal.valueOf(100);
			
			for(int i=0;i<exportdata.length();i++){
				JSONObject jsonObj = exportdata.getJSONObject(i);
				//剩余金额
				BigDecimal balanceexportmoney=new BigDecimal(jsonObj.getDouble("balanceexportmoney"));
				if (balanceexportmoney.compareTo(leavelRent) == 1) {
					int floor = balanceexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
					String serialNumber="";
					for (int j = 0; j < floor; j++) {
						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",leavelRent);//导出金额
						newexportdata.put(jsonObj1);
					}
					
					BigDecimal residueRent = balanceexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
					if(residueRent.compareTo(BigDecimal.ZERO)==1){
						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",residueRent);//导出金额
						newexportdata.put(jsonObj1);
					}
					
				} else {
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",balanceexportmoney);//导出金额
					newexportdata.put(jsonObj1);
				}
				
			}
			//资金计划导出数据入库
			//数据字典通过name属性匹配
			Map<String,String> dictDataClassMapping = new HashMap<String,String>();
			//dictDataClassMapping.put("DictionaryData", "code");
			dictDataClassMapping.put("FundInvoiceInfo", "id");
			List<FundInvoiceDownloadInfo> infos=(List<FundInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(FundInvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
			//this.tableService.saveOrUpdateEntitiesByJSONArrayString(FundInvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
			this.tableService.saveOrUpdateAllEntities(infos);
			model.put("donwinfos", infos);
			this.tableService.updateFlush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BusinessException("初始化数据失败！！");
			
		}
		
	}
	
	/**
	 * @param model 
	 * @throws BusinessException 
	 * 导出实收开票前插入数据库
	 */
	public  void exportFundChargeInvoiceBefore(Map<String,Object> model) throws BusinessException{
		try {
			//导出数据参数
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("ids",(String) model.get("ids"));
			JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/fund_invoice/invoiceCharge/fund_invoice_charge_confirm_exp.xml", queryMainObjectMap);
			//拆分后记录
			JSONArray newexportdata = new JSONArray();
			//获取拆分金额
			DictionaryData dictmoney=this.baseService.findEntityByID(DictionaryData.class,"invoicemoney.01");
			BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); //BigDecimal.valueOf(100);
			
			//判断导出金额是否大于该笔资金的计划金额
			Map<String,BigDecimal> mapplan=new HashMap<String, BigDecimal>();
			Map<String,BigDecimal> mapfact=new HashMap<String, BigDecimal>();
			Map<String,BigDecimal> mapinmoney=new HashMap<String, BigDecimal>();
			for (int i=0;i<exportdata.length();i++) {
				JSONObject jobj=exportdata.getJSONObject(i);
				String pid=jobj.getString("pid");
				//计算该笔实收对应的计划金额
				BigDecimal planmoney=new BigDecimal(jobj.getString("planmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
				mapplan.put(pid, planmoney);
				
				//计算该笔实收对应的本次实收开票金额
				BigDecimal factmoney=new BigDecimal(jobj.getString("balanceexportmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
				if(mapfact.containsKey(pid)){
					mapfact.put(pid, mapfact.get(pid).add(factmoney).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}else{
					mapfact.put(pid, factmoney);
				}
				
				//计算该笔实收对应的计划已经申请的开票金额
				//查询出计划对应的实收信息
				BigDecimal inmoney=new BigDecimal(jobj.getString("planexportmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
				mapinmoney.put(pid, inmoney);
				
			}
			
			///判断当前是否存在实收提交金额大于计划金额
			Iterator<?> iter = mapplan.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				BigDecimal val = (BigDecimal) entry.getValue();
				BigDecimal factval =mapfact.get(key).add(mapinmoney.get(key));
				//计划金额与数据库已提交金额+当前提交金额比较
				if(val.compareTo(factval)==-1){
					ContractFundFundPlan contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, key);
					throw new BusinessException("合同号："+contractFundFundPlan.getContractId().getContractId()+"的"+contractFundFundPlan.getFeeType().getName()+"提交导出金额超过计划金额，计划金额："+val+",本次提交导出金额："+mapfact.get(key)+"，已导出金额："+mapinmoney.get(key));
				}
			}
			
			for(int i=0;i<exportdata.length();i++){
				JSONObject jsonObj = exportdata.getJSONObject(i);
				//按剩余开票金额开票
				BigDecimal balanceexportmoney=new BigDecimal(jsonObj.getDouble("balanceexportmoney"));
				if (balanceexportmoney.compareTo(leavelRent) == 1) {
					int floor = balanceexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
					String serialNumber="";
					for (int j = 0; j < floor; j++) {
						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",leavelRent);//导出金额
						newexportdata.put(jsonObj1);
					}
					
					BigDecimal residueRent = balanceexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
					if(residueRent.compareTo(BigDecimal.ZERO)==1){
						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",residueRent);//导出金额
						newexportdata.put(jsonObj1);
					}
					
				} else {
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",balanceexportmoney);//导出金额
					newexportdata.put(jsonObj1);
				}
				
			}
			//资金计划导出数据入库
			//数据字典通过name属性匹配
			Map<String,String> dictDataClassMapping = new HashMap<String,String>();
			//dictDataClassMapping.put("DictionaryData", "code");
			dictDataClassMapping.put("FundInvoiceInfo", "id");
			List<FundInvoiceDownloadInfo> infos=(List<FundInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(FundInvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
			//this.tableService.saveOrUpdateEntitiesByJSONArrayString(FundInvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
			this.tableService.saveOrUpdateAllEntities(infos);
			model.put("donwinfos", infos);
			
			this.tableService.updateFlush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		
	}
	
	/*
	 * 资金开票数据导出后修改状态为已导出
	 *  */
	@SuppressWarnings("unchecked")
	public  void exportInvoiceAfter(Map<String,Object> model) throws Exception{
		String[] idsArray = ((String) model.get("ids")).split(",");
		List<InvoiceDownloadInfo>  taxlist=new ArrayList<InvoiceDownloadInfo>();
		for (String id : idsArray) {
			InvoiceDownloadInfo tax=this.tableService.findEntityByID(InvoiceDownloadInfo.class,id.replaceAll("'", ""));
			tax.setIsExport("1");
			taxlist.add(tax);
		}
		this.baseService.saveOrUpdateAllEntities(taxlist);
	}
	/*
	 * 资金收据数据导出前准备插入word的数据
	 *  */
	public String exportFundReceiptBefore(FileTemplateService fi,Map<String, String> pageMap,BaseFileTemplate ft,String modename) throws Exception{
		Map<String,Object> dataMap=new HashMap<String, Object>();
		String fileName = "";		
		if(pageMap.containsKey("fund_receipt_list")){
			String fundReceiptListStr = pageMap.get("fund_receipt_list");	
			if(null==fundReceiptListStr || "".equals(fundReceiptListStr) || "null".equalsIgnoreCase(fundReceiptListStr)){
				return "";
			}
			List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
			User user = (User) SecurityUtil.getPrincipal();
			String createDate = DateUtil.getSystemDateTime();
			JSONArray fundReceiptJsonArray = new JSONArray(fundReceiptListStr);
			List<List> llist=new ArrayList<List>();
			for(int j = 0; j < fundReceiptJsonArray.length(); j++) {
				JSONObject fundReceiptJsonObj = fundReceiptJsonArray.getJSONObject(j);
				//String cashname=fundReceiptJsonObj.getString("tableid");
				BigDecimal planmoney = new BigDecimal(fundReceiptJsonObj.getString("factmoney"));
				String planmoneyThousands = MoneyUtil.amountToChinese(planmoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				String custname = fundReceiptJsonObj.getString("custname");
				String chargelist=fundReceiptJsonObj.getString("chargelist");//期次
				String settlemethodname=fundReceiptJsonObj.getString("settlemethodname");//结算方式
				String feetype=fundReceiptJsonObj.getString("feetype");//款项名称
				String contractnumber=fundReceiptJsonObj.getString("contractnumber");				
				List alist=new ArrayList<String>();
				String memo=contractnumber+feetype;
				String number="NO." + WorkflowUtil.getRentReceiptSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				alist.add(memo+"收据"+Math.round(Math.random()*10000));
				for(int i=0;i<3;i++){					
					alist.add(fundReceiptJsonObj.getString("factdate"));
			     	alist.add(custname);
			     	alist.add(settlemethodname);		
					alist.add(planmoneyThousands);
					alist.add("￥"+MoneyUtil.parseMoney(planmoney));
					alist.add(contractnumber+"合同项第"+chargelist+"期"+feetype);
					alist.add(user.getRealname());
					alist.add(number);
					
				}											
				llist.add(alist);
				
				FundInvoiceInfo ri=this.baseService.findEntityByID(FundInvoiceInfo.class, fundReceiptJsonObj.getString("id"));
				ri.setInvoiceStatus(3);//类型为收据时，3代表已导出
				ri.setModificator(user);
				ri.setModifyDate(createDate);
				list.add(ri);
			}
			//MS_receipt_8
			dataMap.put("MS_receipt", llist);
			System.out.println(dataMap);
			if(fileName.length()>0){fileName=fileName+",";}
			fileName = fileName+ fi.createRealFile(ft, dataMap, "收据");
		
			this.baseService.saveOrUpdateAllEntities(list);
		}
		return fileName;
	}
	
	/*
	 * 资金收据数据导出后修改状态为已导出
	 *  */
	
	public  void exportFundReceiptAfter(Map<String,Object> model) throws Exception{
		String[] idsArray = ((String) model.get("ids")).split(",");
		
		List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		for (String id : idsArray) {
			FundInvoiceInfo fi=this.baseService.findEntityByID(FundInvoiceInfo.class, id.replaceAll("\'", ""));
			fi.setInvoiceStatus(3);//类型为收据时，3代表已导出
			fi.setModificator(user);
			fi.setModifyDate(createDate);
			list.add(fi);
		}
		this.baseService.saveOrUpdateAllEntities(list);
	}
	
	/*
	 * 租金收据数据导出前准备插入word的数据
	 *  */
	public String exportRentReceiptBefore(FileTemplateService fi,Map<String, String> pageMap,BaseFileTemplate ft,String modename) throws Exception{
		Map<String,Object> dataMap=new HashMap<String, Object>();
		String fileName = "";
		if(pageMap.containsKey("rent_receipt_list")){
			String rentReceiptListStr = pageMap.get("rent_receipt_list");
			if(null==rentReceiptListStr || "".equals(rentReceiptListStr) || "null".equalsIgnoreCase(rentReceiptListStr)){
				return "";
			}
			//修改状态
			List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
			User user = (User) SecurityUtil.getPrincipal();
			String createDate = DateUtil.getSystemDateTime();
			
			JSONArray rentReceiptJsonArray = new JSONArray(rentReceiptListStr);
			List<List> llist=new ArrayList<List>();
			for(int j = 0; j < rentReceiptJsonArray.length(); j++) {
				JSONObject rentReceiptJsonObj = rentReceiptJsonArray.getJSONObject(j);
				BigDecimal planmoney = new BigDecimal(rentReceiptJsonObj.getString("planmoney"));
				String planmoneyThousands = MoneyUtil.amountToChinese(planmoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				String custname = rentReceiptJsonObj.getString("custname");
				List alist=new ArrayList<String>();
				String plantype=rentReceiptJsonObj.getString("plantype");
				if("利息".equals(plantype)){
					plantype="违约金";
				}else{
					plantype="租金";
				}
				String contractnumber=rentReceiptJsonObj.getString("contractnumber");
				String rentlist=rentReceiptJsonObj.getString("rentlist");
				String memo=contractnumber+"第"+rentlist+"期";
				String number="NO." + WorkflowUtil.getRentReceiptSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				alist.add(memo+"收据"+Math.round(Math.random()*10000));
				for(int i=0;i<3;i++){
					alist.add(rentReceiptJsonObj.getString("cdate"));
					alist.add(custname);
					alist.add("电汇");					
					alist.add(planmoneyThousands);
					alist.add("￥"+MoneyUtil.parseMoney(planmoney));
					alist.add(contractnumber+"合同项第"+rentlist+"期"+plantype);
					alist.add(user.getRealname());
					alist.add(number);
				}
				
				
				llist.add(alist);
				//本金一次开票,id 为合同id
				if("corpusone".equals(rentReceiptJsonObj.getString("plantypeid"))){
					Map<String, Object> propertiesMap=new HashMap<String, Object>();
					propertiesMap.put("contractId", this.tableService.findEntityByID(ContractInfo.class, rentReceiptJsonObj.getString("id")));
					List<RentInvoiceInfo> rentinvoices=this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
					for(RentInvoiceInfo ri:rentinvoices){
						ri.setInvoiceStatus(3);//类型为收据时，3代表已导出
						ri.setModificator(user);
						ri.setModifyDate(createDate);
						list.add(ri);
					}
				}else{
					RentInvoiceInfo ri=this.baseService.findEntityByID(RentInvoiceInfo.class, rentReceiptJsonObj.getString("id"));
					ri.setInvoiceStatus(3);//类型为收据时，3代表已导出
					ri.setModificator(user);
					ri.setModifyDate(createDate);
					list.add(ri);
				}
			}
			//MS_receipt_8
			dataMap.put("MS_receipt", llist);
			if(fileName.length()>0){fileName=fileName+",";}
			fileName = fileName+ fi.createRealFile(ft, dataMap, "收据");
			this.baseService.saveOrUpdateAllEntities(list);
		}
		return fileName;
	}
	
	/*
	 * 租金收据数据导出后修改状态为已导出
	 *  */
	
	public  void exportRentReceiptAfter(Map<String,Object> model) throws Exception{
		 
			
			String[] idsArray = ((String) model.get("ids")).split(",");
			List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
			User user = (User) SecurityUtil.getPrincipal();
			String createDate = DateUtil.getSystemDateTime();
			for (String id : idsArray) {
			   System.out.println(id.replaceAll("\'", "")+":===xu");
				RentInvoiceInfo fi=this.baseService.findEntityByID(RentInvoiceInfo.class, id.replaceAll("\'", ""));
				fi.setInvoiceStatus(3);//类型为收据时，3代表已导出
				fi.setModificator(user);
				fi.setModifyDate(createDate);
				list.add(fi);
			}
			this.baseService.saveOrUpdateAllEntities(list);
	}
	
	/*
	 * 租金开票数据导出后修改状态为已导出
	 *  */
	@SuppressWarnings("unchecked")
	public  void exportRentInvoiceAfter(Map<String,Object> model) throws Exception{
		List<RentInvoiceDownloadInfo> infos=(List<RentInvoiceDownloadInfo>) model.get("donwinfos");
		System.out.println("ee");
		List<RentInvoiceDownloadInfo> newinfos=new ArrayList<RentInvoiceDownloadInfo>();
		for(RentInvoiceDownloadInfo info:infos){
			info.setIsExport("1");
			newinfos.add(info);
		}
		this.baseService.saveOrUpdateAllEntities(newinfos);
	}
	
	/**
	 * @param model 
	 * @throws BusinessException 
	 * 导出租金计划开票前插入数据库
	 */
	public  void exportRentInvoiceBefore(Map<String,Object> model) throws BusinessException{
		try {
			//导出数据参数
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("ids",(String) model.get("ids"));
			JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/rent_invoice/invoicePlan/rent_invoice_plan_confirm_exp.xml", queryMainObjectMap);
			//拆分后记录
			JSONArray newexportdata = new JSONArray();
			//获取拆分金额
			DictionaryData dictmoney=this.baseService.findEntityByID(DictionaryData.class,"invoicemoney.01");
			BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); //BigDecimal.valueOf(100);
			
			for(int i=0;i<exportdata.length();i++){
				JSONObject jsonObj = exportdata.getJSONObject(i);
				//剩余金额
				BigDecimal balanceexportmoney=new BigDecimal(jsonObj.getDouble("balanceexportmoney"));
				if (balanceexportmoney.compareTo(leavelRent) == 1) {
					int floor = balanceexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
					String serialNumber="";
					for (int j = 0; j < floor; j++) {
						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",leavelRent);//导出金额
						newexportdata.put(jsonObj1);
					}
					
					BigDecimal residueRent = balanceexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
					if(residueRent.compareTo(BigDecimal.ZERO)==1){
						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",residueRent);//导出金额
						newexportdata.put(jsonObj1);
					}
					
				} else {
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",balanceexportmoney);//导出金额
					newexportdata.put(jsonObj1);
				}
				
			}
			//资金计划导出数据入库
			//数据字典通过name属性匹配
			Map<String,String> dictDataClassMapping = new HashMap<String,String>();
			//dictDataClassMapping.put("DictionaryData", "code");
			dictDataClassMapping.put("RentInvoiceInfo", "id");
			List<RentInvoiceDownloadInfo> infos=(List<RentInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(RentInvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
			this.tableService.saveOrUpdateAllEntities(infos);
			model.put("donwinfos", infos);
			this.tableService.updateFlush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		
	}

	/**
	 * @param model 
	 * @throws BusinessException 
	 * 导出租金实收开票前插入数据库
	 */
	public  void exportRentIncomeInvoiceBefore(Map<String,Object> model) throws BusinessException{
		try {
			//导出数据参数
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("ids",(String) model.get("ids"));
			JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/rent_invoice/invoiceIncome/rent_invoice_income_confirm_exp.xml", queryMainObjectMap);
			
			//判断导出金额是否大于该笔资金的计划金额
			Map<String,BigDecimal> mapplan=new HashMap<String, BigDecimal>();
			Map<String,BigDecimal> mapfact=new HashMap<String, BigDecimal>();
			Map<String,BigDecimal> mapinmoney=new HashMap<String, BigDecimal>();
			for (int i=0;i<exportdata.length();i++) {
				JSONObject jobj=exportdata.getJSONObject(i);
				String pid=jobj.getString("pid");
				String plantypeid=jobj.getString("plantypeid");
				String spid=pid+"_"+plantypeid;
				//计算该笔实收对应的计划金额
				BigDecimal planmoney=new BigDecimal(jobj.getString("planmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
				mapplan.put(spid, planmoney);
				
				//计算该笔实收对应的本次实收开票金额
				BigDecimal factmoney=new BigDecimal(jobj.getString("balanceexportmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
				if(mapfact.containsKey(spid)){
					mapfact.put(spid, mapfact.get(spid).add(factmoney).setScale(2,BigDecimal.ROUND_HALF_EVEN));
				}else{
					mapfact.put(spid, factmoney);
				}
				
				//计算该笔实收对应的计划已经申请的开票金额
				//查询出计划对应的实收信息
				BigDecimal inmoney=new BigDecimal(jobj.getString("planexportmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
				mapinmoney.put(spid, inmoney);
				
			}
			
			///判断当前是否存在实收提交金额大于计划金额
			Iterator<?> iter = mapplan.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				BigDecimal val = (BigDecimal) entry.getValue();
				BigDecimal factval =mapfact.get(key).add(mapinmoney.get(key));
				//计划金额与数据库已提交金额+当前提交金额比较
				if(val.compareTo(factval)==-1){
					String skey=key.split("_")[0];
					ContractFundRentPlan crp = this.tableService.findEntityByID(ContractFundRentPlan.class, skey);
					throw new BusinessException("合同号："+crp.getContractId().getContractId()+"的第"+crp.getRentList()+"期租金提交导出金额超过计划金额,计划金额："+val+",本次提交导出金额："+mapfact.get(key)+"，已导出金额："+mapinmoney.get(key));
				}
			}
			
			//拆分后记录
			JSONArray newexportdata = new JSONArray();
			//获取拆分金额
			DictionaryData dictmoney=this.baseService.findEntityByID(DictionaryData.class,"invoicemoney.01");
			BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); //BigDecimal.valueOf(100);
			
			for(int i=0;i<exportdata.length();i++){
				JSONObject jsonObj = exportdata.getJSONObject(i);
				//剩余金额
				BigDecimal balanceexportmoney=new BigDecimal(jsonObj.getDouble("balanceexportmoney"));
				if (balanceexportmoney.compareTo(leavelRent) == 1) {
					int floor = balanceexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
					String serialNumber="";
					for (int j = 0; j < floor; j++) {
						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",leavelRent);//导出金额
						newexportdata.put(jsonObj1);
					}
					
					BigDecimal residueRent = balanceexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
					if(residueRent.compareTo(BigDecimal.ZERO)==1){
						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",residueRent);//导出金额
						newexportdata.put(jsonObj1);
					}
					
				} else {
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",balanceexportmoney);//导出金额
					newexportdata.put(jsonObj1);
				}
				
			}
			//资金计划导出数据入库
			//数据字典通过name属性匹配
			Map<String,String> dictDataClassMapping = new HashMap<String,String>();
			//dictDataClassMapping.put("DictionaryData", "code");
			dictDataClassMapping.put("RentInvoiceInfo", "id");
			List<RentInvoiceDownloadInfo> infos=(List<RentInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(RentInvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
			this.tableService.saveOrUpdateAllEntities(infos);
			model.put("donwinfos", infos);
			this.tableService.updateFlush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		
	}
	
	//检查网银数据是否符合
	//保存本次网银流水号数据
	public static List<String> data=new ArrayList<String>();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Deprecated
	public Object checkEbankInfoDataImport(FundEbankData fed,
			Map<String,String> model,Integer rowIndex)throws Exception{
		if(null==fed.getSn()|| "".equals(fed.getSn())){
			throw new BusinessException("第"+rowIndex+"行流水号不能为空");
		}else if(fed.getFactMoney() == null || "".equals(fed.getFactMoney())){
			throw new BusinessException("第"+rowIndex+"行到账金额不能为空");
		}else if(null==fed.getOwnAccNumber()||"".equals(fed.getOwnAccNumber())){
			throw new BusinessException("第"+rowIndex+"行本方账号不能为空");	
		}
		Map<String, Object>	propertiesMap=new HashMap<String, Object>();
		System.out.println("-----:"+fed.getOwnAccNumber());
		propertiesMap.put("accNumber",fed.getOwnAccNumber());
	     List<OwnAccount> owaccount=(List<OwnAccount>)this.tableService.findEntityByProperties(OwnAccount.class, propertiesMap);
	     if(owaccount.size()>0){
	     }else{
	    	 throw new BusinessException("第"+rowIndex+"行本方账号"+fed.getOwnAccNumber()+"在系统中不存在");	
	     }
	     propertiesMap.clear();
		List<ContractInfo> contractInfos = new ArrayList<ContractInfo>();
		List<FundEbankData> feds = new ArrayList<FundEbankData>();
		if(fed.getCustId() != null){
			propertiesMap.put("custId", fed.getCustId());
			contractInfos = (List) this.tableService.findEntityByProperties(ContractInfo.class, propertiesMap);
			if(contractInfos == null || contractInfos.size() == 0){
				throw new BusinessException("第"+rowIndex+"行客户不存在");
			}
		}
		propertiesMap.clear();
		propertiesMap.put("sn", fed.getSn());
		feds = (List<FundEbankData>)this.tableService.findEntityByProperties(FundEbankData.class, propertiesMap);
		if(feds != null && feds.size() > 0){
			throw new BusinessException("第"+rowIndex+"行流水号不能重复上传");
		}
		//增加本次流水号重复判断
		if(data.contains(fed.getSn())){
			data.clear();
			throw new BusinessException("第"+rowIndex+"行流水号("+fed.getSn()+")重复");
		}
		data.add(fed.getSn());
		String EbankNo = WorkflowUtil.getEbankNoInfoSerialNumber(null,tableService.getBussinessDao().getHibernateTemplate(), tableService.getBussinessDao().getJdbcTemplate());
		//EbankNo=EbankNo.replace("accountcode",owaccount.get(0).getAccCode() );
		fed.setEbdataId(EbankNo);
		fed.setInvalid("是");
		//虚拟网银不产生凭证
		if(!"888888".equals(fed.getOwnAccNumber())){
			//凭证生成
			ebankImportService.createVoucherForFund(fed);
		}
		//凭证结束
		return fed;
	}
	@Override
	public Object checkEquipInfoImport(ProjEquip equip,
			Map<String, String> model, Integer rowIndex) throws Exception {
		// TODO Auto-generated method stub
		return equip;
	}
	@Override
	public Object checkRegVatInvoice(VatInvoiceInfo vatInvoiceInfo,
			Map<String, String> model, Integer rowIndex) throws Exception {
		/* 空值判断、合法导入判断 */
		
		if (vatInvoiceInfo.getInvoiceNo() == null) {
			throw new BusinessException("第" + rowIndex + "行发票号不能为空");
		} else if (vatInvoiceInfo.getInvoiceMoney() == null) {
			throw new BusinessException("第" + rowIndex + "行发票金额不能为空");
		} else if (vatInvoiceInfo.getContractnumber() == null) {
			throw new BusinessException("第" + rowIndex + "行合同号不能为空");
		}
		
		
		
		/* 默认值处理 */
		vatInvoiceInfo.setRecordDate(DateUtil.getSystemDate());
		return vatInvoiceInfo;
	}
	@Override
	public Object checkVatInvoiceDownloadInfoImport(VatInvoiceInfo vatInvoiceInfo,
			Map<String, String> model, Integer rowIndex) throws Exception {
		if(vatInvoiceInfo.getInvoiceNo() == null || "".equals(vatInvoiceInfo.getInvoiceNo())){
			throw new BusinessException("第"+rowIndex+"行发票号不能为空");
		}else if(vatInvoiceInfo.getInvoiceMoney() == null || "".equals(vatInvoiceInfo.getInvoiceMoney())){
			throw new BusinessException("第"+rowIndex+"行发票金额不能为空");
		}else if(vatInvoiceInfo.getInvoiceDate() == null || "".equals(vatInvoiceInfo.getInvoiceDate())){
			throw new BusinessException("第"+rowIndex+"行开票时间不能为空");
		}else if(vatInvoiceInfo.getTaxMoney() == null || "".equals(vatInvoiceInfo.getTaxMoney())){
			throw new BusinessException("第"+rowIndex+"行税额不能为空");
		}else if(vatInvoiceInfo.getTaxRate() == null || "".equals(vatInvoiceInfo.getTaxRate())){
			throw new BusinessException("第"+rowIndex+"行税率不能为空");
		}else if(vatInvoiceInfo.getCertificationResults() == null || "".equals(vatInvoiceInfo.getCertificationResults())){
			throw new BusinessException("第"+rowIndex+"行认证结果不能为空");
		}else if(vatInvoiceInfo.getCertificationDate() == null || "".equals(vatInvoiceInfo.getCertificationDate())){
			throw new BusinessException("第"+rowIndex+"行认证时间不能为空");
		}

		
		Map<String,Object> propertiesMap = new HashMap<String,Object>();
		List<VatInvoiceInfo> vatinfos = new ArrayList<VatInvoiceInfo>();
		propertiesMap.put("invoiceNo", vatInvoiceInfo.getInvoiceNo());
		vatinfos = this.tableService.findEntityByProperties(VatInvoiceInfo.class, propertiesMap);
		
		VatInvoiceInfo vii = new VatInvoiceInfo();
		if (null == vatinfos || vatinfos.size() == 0) {
			throw new BusinessException("第"+rowIndex+"行发票未登记");
		} else {
			vii = vatinfos.get(0);
			Integer invoiceStatus = vii.getInvoiceStatus();
			if(invoiceStatus != 2){
				throw new BusinessException("第"+rowIndex+"行发票未确认");
			}
			if(!"认证相符".equals(vatInvoiceInfo.getCertificationResults()) && !"认证不符".equals(vatInvoiceInfo.getCertificationResults())){
				throw new BusinessException("第"+rowIndex+"行认证结果不符合要求");
			} else if("认证相符".equals(vatInvoiceInfo.getCertificationResults())){
				vii.setInvoiceStatus(4);
			} else {
				vii.setInvoiceStatus(3);
			}
			vii.setCertificationDate(vatInvoiceInfo.getCertificationDate());
			vii.setCertificationResults(vatInvoiceInfo.getCertificationResults());
			vii.setInvoiceDate(vatInvoiceInfo.getInvoiceDate());
			vii.setTaxRate(vatInvoiceInfo.getTaxRate());
			vii.setTaxMoney(vatInvoiceInfo.getTaxMoney());
		}
		
		//发票认证凭证生成
	//	invoiceVoucherService.createVoucherReceiveInvoice(vatInvoiceInfo);
		
		return vii;
	}
	
	/**
	 * @param model 
	 * @throws BusinessException 
	 * 导出凭证后修改导出状态
	 */
	public  void exportVoucherAfter(Map<String,Object> model) throws BusinessException{
		try {
			List<String> idArray=  Arrays.asList(model.get("ids").toString().replace("'", "").split(","));
			String hsql="from InterV8Vouchers v8 WHERE v8.f3 in(:listid)";
			List<InterV8Vouchers> list=this.tableService.getBussinessDao().getHibernateTemplate().findByNamedParam(hsql, "listid", idArray);
			for(InterV8Vouchers v8:list){
				v8.setV8Flag("1");//{title:'未导出',name:'0'},{title:'已导出',name:'1'}
				v8.setV8Memo("已导出");
				v8.setExp_date(DateUtil.getSystemDateTime());
			}
			this.baseService.saveOrUpdateAllEntities(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String createvndr(String custname) throws Exception {
		
		User user = null;
		String id="";
		try {
			user = (User) SecurityUtil.getPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CustInfo custInfo;
		try {
			String systemDate = DateUtil.getSystemDateTime();
			SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
			String custNumber=sdf.format(new Date())+(int)(Math.random()*9000+1000);
			custInfo = new CustInfo();
			custInfo.setCustNumber(custNumber);
			custInfo.setCustName(custname);
			custInfo.setDraft("否");
			custInfo.setInvalid("否");
			DictionaryData custClass=new DictionaryData();
			custClass.setId("CUST_INFO_COMPANY");
			custInfo.setCustClass(custClass);
			custInfo.setCreator(user);
			custInfo.setCreateDate(systemDate);
			this.baseService.saveEntity(custInfo);
			CustInfoCompany custInfoCompany = new CustInfoCompany();//法人客户信	
			custInfoCompany.setCustId(custInfo);
			custInfoCompany.setCreator(user);
			custInfoCompany.setCreateDate(systemDate);
			this.baseService.saveEntity(custInfoCompany);
			CustTypeInfo custype=new CustTypeInfo();
			custype.setCustId(custInfo);
			DictionaryData dd=new DictionaryData();
			dd.setId("cust_type.vndr");
			custype.setCustType(dd);
			custype.setCreator(user);
			custype.setCreateDate(systemDate);
			this.baseService.saveEntity(custype);
			id=custInfo.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return id;
	}
	@Override
	public String createmanufacturer(String custname) throws Exception {
		User user = null;
		String id="";
		try {
			user = (User) SecurityUtil.getPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		CustInfo custInfo;
		try {
			String systemDate = DateUtil.getSystemDateTime();
			SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
			String custNumber=sdf.format(new Date())+(int)(Math.random()*9000+1000);
			custInfo = new CustInfo();
			custInfo.setCustNumber(custNumber);
			custInfo.setCustName(custname);
			custInfo.setDraft("否");
			custInfo.setInvalid("否");
			DictionaryData custClass=new DictionaryData();
			custClass.setId("CUST_INFO_COMPANY");
			custInfo.setCustClass(custClass);
			custInfo.setCreator(user);
			custInfo.setCreateDate(systemDate);
			this.baseService.saveEntity(custInfo);
			CustInfoCompany custInfoCompany = new CustInfoCompany();//法人客户信	
			custInfoCompany.setCustId(custInfo);
			custInfoCompany.setCreator(user);
			custInfoCompany.setCreateDate(systemDate);
			this.baseService.saveEntity(custInfoCompany);
			CustTypeInfo custype=new CustTypeInfo();
			custype.setCustId(custInfo);
			DictionaryData dd=new DictionaryData();
			dd.setId("cust_type.manufacturer");
			custype.setCustType(dd);
			custype.setCreator(user);
			custype.setCreateDate(systemDate);
			this.baseService.saveEntity(custype);
			id=custInfo.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return id;
	}
	//网银匹配导入
	@Override
	public Object checkVerificationImport(FundEbankData fundebankdate,
			Map<String, String> model, Integer rowIndex) throws Exception {
		Map<String,Object> propertiesMap = new HashMap<String,Object>();
		String id=fundebankdate.getEbdataId();
		System.out.println(id);
		propertiesMap.put("ebdataId",id);
		
		String contractid=fundebankdate.getContracIdIncome();
		String RentListIncome=fundebankdate.getRentListIncome();
		List<FundEbankData>  list = this.tableService.findEntityByProperties(FundEbankData.class, propertiesMap);
		
		//网银在进行流程信息 处理
		User user = (User) SecurityUtil.getPrincipal();
		String contractUUID = "";
		String ebankDataUUID = "";//网银表UUID
		BigDecimal fact_money = new BigDecimal("0.00");
		String sql = "";
		String contractIdIncome = "";
		for (FundEbankData obj : list) {
			ebankDataUUID = obj.getId();
			fact_money =  Tools.getDBDecStr( obj.getMayOpeMoney() );	//可核销金额
			contractIdIncome = obj.getContracIdIncome();	//网银匹配的合同号UUID
			
			propertiesMap = new HashMap<String,Object>();
			propertiesMap.put("contractNumber",contractid);
			List<ContractInfo> contract_l =  this.tableService.findEntityByProperties(ContractInfo.class, propertiesMap);
			if(contract_l.size() > 0){
				contractUUID = contract_l.get(0).getId();
			}
			if(!Tools.isNullOrEmpty(ebankDataUUID)){//传文件中对应的网银id不为空
				//上传文件中对应合同id不为空，网银数据中匹配的合同号UUID为空时；添加网银在进行流程信息及互斥信息
				if(!Tools.isNullOrEmpty(contractUUID) && Tools.isNullOrEmpty(contractIdIncome)){
					//表一操作:网银在进行流程信息
					//字段：网银编号 ebdata_id ，合同编号 contract_id，占用流程 process_name，流程开始日期 start_date，涉及网银金额 process_amount ，流程实例ID FLOW_UNID
					sql = "";
					sql = " insert into fund_ebank_process (id,ebdata_id,contract_id,process_name,start_date_,process_amount,FLOW_UNID,work_flag) ";
					sql = sql + " values ( sys_guid(),'"+ebankDataUUID+"','"+contractUUID+"','网银批量核销',to_char(sysdate,'yyyy-mm-dd'),"+fact_money+", '"+UUID.randomUUID()+"','0' ) ";
					//
					this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
					//表二操作: 批量核销时：开始时要把 contract_info 中work_flag，  改为 1 完了再改为0
					//sql = " update contract_info set work_flag = 1 where id = '"+contractUUID+"' ";
					
					//表三操作：流程互斥需加入一行关于批量核销的流程互斥数据信息 ,T_WORK_FLOW_FLAG 要加入一条数据，结束时候将数据删除掉
					sql = "   INSERT INTO T_WORK_FLOW_FLAG (ID,WORK_FLOW_INSTANCE,WORK_FLOW_NAME, WORK_NUMBER, KEY_TWO,begin_user,Status) ";
					sql = sql + "   VALUES ( sys_guid(), '"+obj.getEbdataId()+"', '网银批量核销', '"+contractUUID+"','网银批量核销','"+user.getId()+"','1' ) ";
					this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
				}
				
				//上传文件中对应合同id不为空,且文件中对应合同id与网银数据中匹配的合同号UUID不相等时；更新网银在进行流程信息及互斥信息
				if(!Tools.isNullOrEmpty(contractUUID) && !Tools.isNullOrEmpty(contractIdIncome) && !contractUUID.equals(contractIdIncome)){
					sql = "";
					//删除原来网银匹配的合同的网银在进行流程信息的状态
					sql = "delete fund_ebank_process where ebdata_id='"+ebankDataUUID+"' and contract_id='"+contractIdIncome+"' and process_name='网银批量核销' ";
					this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
					//修改原来网银匹配的合同信息中的work_flag
					//sql = " update contract_info set work_flag = 0 where id = '"+contractIdIncome+"' ";
					//this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
					//删除原来网银匹配的合同流程互斥信息
					sql = "delete T_WORK_FLOW_FLAG where WORK_FLOW_INSTANCE='"+obj.getEbdataId()+"' and WORK_NUMBER='"+contractIdIncome+"' and KEY_TWO='网银批量核销' and Status='1'";
					this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
					
					//表一操作:网银在进行流程信息
					//字段：网银编号 ebdata_id ，合同编号 contract_id，占用流程 process_name，流程开始日期 start_date，涉及网银金额 process_amount ，流程实例ID FLOW_UNID
					sql = " insert into fund_ebank_process (id,ebdata_id,contract_id,process_name,start_date_,process_amount,FLOW_UNID,work_flag) ";
					sql = sql + " values ( sys_guid(),'"+ebankDataUUID+"','"+contractUUID+"','网银批量核销',to_char(sysdate,'yyyy-mm-dd'),"+fact_money+", '"+UUID.randomUUID()+"','0' ) ";
					this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
					
					//表二操作: 批量核销时：开始时要把 contract_info 中work_flag，  改为 1 完了再改为0
					//sql = " update contract_info set work_flag = 1 where id = '"+contractUUID+"' ";
					//this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
					
					//表三操作：流程互斥需加入一行关于批量核销的流程互斥数据信息 ,T_WORK_FLOW_FLAG 要加入一条数据，结束时候将数据删除掉
					sql = "   INSERT INTO T_WORK_FLOW_FLAG (ID,WORK_FLOW_INSTANCE,WORK_FLOW_NAME, WORK_NUMBER, KEY_TWO,begin_user,Status) ";
					sql = sql + "   VALUES ( sys_guid(), '"+obj.getEbdataId()+"', '网银批量核销', '"+contractUUID+"','网银批量核销','"+user.getId()+"','1' ) ";
					this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
				}
				
				//上传文件中对应合同id为空,且网银数据中匹配的合同号UUID不为空时；更新原来网银匹配的合同网银在进行流程信息及互斥信息
				if(Tools.isNullOrEmpty(contractUUID) && !Tools.isNullOrEmpty(contractIdIncome)){
					sql = "";
					//删除原来网银匹配的合同的网银在进行流程信息的状态
					sql = "delete fund_ebank_process where ebdata_id='"+ebankDataUUID+"' and  contract_id='"+contractIdIncome+"' and process_name='网银批量核销' ";
					this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
					//修改原来网银匹配的合同信息中的work_flag
					//sql = " update contract_info set work_flag = 0 where id = '"+contractIdIncome+"' ";
					//this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
					//删除原来网银匹配的合同流程互斥信息
					sql = "delete T_WORK_FLOW_FLAG where WORK_FLOW_INSTANCE='"+obj.getEbdataId()+"' and WORK_NUMBER='"+contractIdIncome+"' and KEY_TWO='网银批量核销' and Status='1'";
					this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
				}
			}
			
		}
		
		
		fundebankdate=list.get(0);
		fundebankdate.setContracIdIncome(contractUUID);
		fundebankdate.setRentListIncome(RentListIncome);
		return fundebankdate;
	}
	
	/**
	 * 导出租金支付通知书
	 * @param model
	 * @throws BusinessException
	 */
	public String exportRentPaymentNoticeBefore(FileTemplateService fi,Map<String, String> pageMap,BaseFileTemplate ft,String modename) throws Exception{
		User user = (User) SecurityUtil.getPrincipal();
		String nowdate = DateUtil.getSystemDateTime();
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		String fileName = "";
		if(pageMap.containsKey("rent_payment_notice_list")){
			String rentPaymentNoticeList = pageMap.get("rent_payment_notice_list");
			if(null==rentPaymentNoticeList || "".equals(rentPaymentNoticeList) || "null".equalsIgnoreCase(rentPaymentNoticeList)){
				return "";
			}
			List<RentPayNoticeRecord> recordList = new ArrayList<RentPayNoticeRecord>();
			String currDate = DateUtil.getSystemTimeByFormat("yyyy年MM月dd日");
			JSONArray rentPaymentNoticeJsonArray = new JSONArray(rentPaymentNoticeList);
			for(int i = 0;i < rentPaymentNoticeJsonArray.length(); i++){
				Map<String,Object> dataMap=new HashMap<String, Object>();
				JSONObject obj = rentPaymentNoticeJsonArray.getJSONObject(i);
				String contractid = obj.getString("id");
				String offsetflag = obj.getString("offsetflag");//是否抵扣 
				String leaseAccNumber = obj.getString("leaseaccnumber");
				String leaseAccName = obj.getString("leaseaccname");
				String leaseAccBank = obj.getString("leaseaccbank");
				String custName = obj.getString("cust_name");
				String contractNumber = obj.getString("contract_number");
				String plan_date=obj.getString("plandate").replace("-", "年").replace("-", "月") + "日";
				String planDate = obj.getString("plan_date").replace("-", "年").replace("-", "月") + "日";
				String contractName = obj.getString("contractname");
				BigDecimal rent = (new BigDecimal(obj.getString("rent"))).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal penalty = (new BigDecimal(obj.getString("penalty"))).setScale(2, BigDecimal.ROUND_HALF_UP);
				String rentCapital = MoneyUtil.amountToChinese(rent.doubleValue());
				String firstRentDate= obj.getString("firstrentdate").replace("-", "年").replace("-", "月") + "日";
				String lastRentDate = obj.getString("lastrentdate").replace("-", "年").replace("-", "月") + "日"; 
				String rentList = obj.getString("rent_list");
				BigDecimal sumrent= BigDecimal.ZERO;
				String recommendedDate = planDate;
				String summary = "";
				ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
				//记录导出次数
				RentPayNoticeRecord record = new RentPayNoticeRecord();
				Map<String, Object> propertiesMap = new HashMap<String, Object>();
				propertiesMap.put("contractId", contract);
				List<RentPayNoticeRecord> resultList = this.tableService.findEntityByProperties(RentPayNoticeRecord.class, propertiesMap);
				if(null!=resultList && resultList.size()>0){
					record = resultList.get(0);
					record.setExportTimes(record.getExportTimes()+1);
				} else{
					record.setContractId(contract);
					record.setExportTimes(1);
				}
				recordList.add(record);
				List<List<String>> tableList = new ArrayList<List<String>>();
				
				
				if(!"".equals(offsetflag) && "offset".equals(offsetflag)){
					//抵扣版查询可抵扣租金
					String rentlistArray = "";
					String rentdateArray = "";
					Set<ContractFundRentPlan> plans = contract.getContractFundRentPlans();
					for(ContractFundRentPlan plan : plans){
						if(plan.getRentList() >= Integer.valueOf(rentList)){
							List<String> listtemp =  new ArrayList<String>();
							listtemp.add("第"+plan.getRentList().toString()+"期租金金额");
							listtemp.add(MoneyUtil.parseMoney(plan.getRent()));
							sumrent.add(plan.getRent());
							rentlistArray = rentlistArray + plan.getRentList().toString()+"、";
							rentdateArray = rentdateArray + plan.getPlanDate().replace("-", "年").replace("-", "月") + "日、";
							tableList.add(listtemp);
						}
					}
					
					List<String> priceList = new ArrayList<String>();
					priceList.add("留购价款");
					priceList.add(MoneyUtil.parseMoney(contract.getContractCondition().getNominalPrice()));
					
					List<String> cautionList = new ArrayList<String>();
					cautionList.add("保证金金额");
					cautionList.add(MoneyUtil.parseMoney(contract.getContractCondition().getCautionDeductionMoney()));
					
					BigDecimal allPay = sumrent.add(contract.getContractCondition().getNominalPrice()).subtract(contract.getContractCondition().getCautionDeductionMoney());
					List<String> allPayList = new ArrayList<String>();
					allPayList.add("本期实际支付金额");
					allPayList.add(MoneyUtil.parseMoney(allPay));
					
					List<String> recommendedDateList = new ArrayList<String>();
					recommendedDateList.add("建议付款日");
					recommendedDateList.add(recommendedDate);
					
					tableList.add(priceList);
					tableList.add(cautionList);
					tableList.add(allPayList);
					tableList.add(recommendedDateList);
					
					rentlistArray = rentlistArray.substring(0,rentlistArray.length()-1);
					rentdateArray = rentdateArray.substring(0,rentlistArray.length()-1);
					summary = "依据贵我双方签订的编号为"+contractNumber+"的"+contractName
							+ "，以及我公司出具的《起租通知书》之约定，贵方的第"+rentlistArray+"期租金将于"+rentdateArray+"到期，"
							+ "请按下表列述之时间、金额、汇款途径将款项及时汇入我公司账户。";
				}else{
					List<String> amountList = new ArrayList<String>();
					amountList.add("本期租金金额");
					amountList.add("人民币 "+MoneyUtil.parseMoney(rent)+"元");
					List<String> recommendedDateList = new ArrayList<String>();
					recommendedDateList.add("建议付款日");
					recommendedDateList.add(recommendedDate);
					tableList.add(amountList);
					tableList.add(recommendedDateList);
					summary = "依据贵我双方签订的编号为"+contractNumber+"的"+contractName
							+ "，以及我公司出具的《起租通知书》之约定，贵方的第"+rentList+"期租金将于"+plan_date+"到期，"
							+ "请按下表列述之时间、金额、汇款途径将款项及时汇入我公司账户。";
				}
				dataMap.put("notice_tabs", tableList);
				dataMap.put("sheetName", contractNumber+" "+custName+"-"+rentList);
				List<String> aList = new ArrayList<String>();
				aList.add(" ");
				aList.add("致"+custName+"：");
				aList.add(summary);
				aList.add(firstRentDate+"起至"+lastRentDate);
				aList.add("收款人："+leaseAccName);
				aList.add("开户行："+leaseAccBank);
				aList.add("账  号："+leaseAccNumber);
				aList.add(currDate);
				dataMap.put("MS_array", aList);
				dataList.add(dataMap);
			}
			if(fileName.length()>0){fileName=fileName+",";}
			fileName = fileName +fi.createRealFile2(ft, dataList, "租金通知书");
			this.baseService.saveOrUpdateAllEntities(recordList);
		}
		return fileName;
	}
	/**
	 * 导出租金支付通知书罚息版本
	 * @param model
	 * @throws BusinessException
	 */
	public String exportRentPaymentNoticePenaltyBefore(FileTemplateService fi,Map<String, String> pageMap,BaseFileTemplate ft,String modename) throws Exception{
		User user = (User) SecurityUtil.getPrincipal();
		String nowdate = DateUtil.getSystemDateTime();
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		String fileName = "";
		if(pageMap.containsKey("rent_payment_notice_list")){
			String rentPaymentNoticeList = pageMap.get("rent_payment_notice_list");
			if(null==rentPaymentNoticeList || "".equals(rentPaymentNoticeList) || "null".equalsIgnoreCase(rentPaymentNoticeList)){
				return "";
			}
			List<RentPayNoticeRecord> recordList = new ArrayList<RentPayNoticeRecord>();
			String currDate = DateUtil.getSystemTimeByFormat("yyyy年MM月dd日");
			JSONArray rentPaymentNoticeJsonArray = new JSONArray(rentPaymentNoticeList);
			for(int i = 0;i < rentPaymentNoticeJsonArray.length(); i++){
				Map<String,Object> dataMap=new HashMap<String, Object>();
				JSONObject obj = rentPaymentNoticeJsonArray.getJSONObject(i);
				String contractid = obj.getString("id");
				String offsetflag = obj.getString("offsetflag");//是否抵扣 
				String leaseAccNumber = obj.getString("leaseaccnumber");
				String leaseAccName = obj.getString("leaseaccname");
				String leaseAccBank = obj.getString("leaseaccbank");
				String custName = obj.getString("cust_name");
				String contractNumber = obj.getString("contract_number");
				String plan_date=obj.getString("plandate").replace("-", "年").replace("-", "月") + "日";
				String planDate = obj.getString("plan_date").replace("-", "年").replace("-", "月") + "日";
				String contractName = obj.getString("contractname");
				BigDecimal rent = (new BigDecimal(obj.getString("rent"))).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal penalty = (new BigDecimal(obj.getString("penalty"))).setScale(2, BigDecimal.ROUND_HALF_UP);
				String rentCapital = MoneyUtil.amountToChinese(rent.doubleValue());
				String firstRentDate= obj.getString("firstrentdate").replace("-", "年").replace("-", "月") + "日";
				String lastRentDate = obj.getString("lastrentdate").replace("-", "年").replace("-", "月") + "日"; 
				String rentList = obj.getString("rent_list");
				BigDecimal sumrent= BigDecimal.ZERO;
				String recommendedDate = planDate;
				String summary = "";
				
				ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
				//记录导出次数
				RentPayNoticeRecord record = new RentPayNoticeRecord();
				Map<String, Object> propertiesMap = new HashMap<String, Object>();
				propertiesMap.put("contractId", contract);
				List<RentPayNoticeRecord> resultList = this.tableService.findEntityByProperties(RentPayNoticeRecord.class, propertiesMap);
				if(null!=resultList && resultList.size()>0){
					record = resultList.get(0);
					record.setExportTimes(record.getExportTimes()+1);
				} else{
					record.setContractId(contract);
					record.setExportTimes(1);
				}
				recordList.add(record);
				List<List<String>> tableList = new ArrayList<List<String>>();
				if(!"".equals(offsetflag) && "offset".equals(offsetflag)){
					//抵扣版本
					String rentlistArray = "";
					String rentdateArray = "";
					Set<ContractFundRentPlan> plans = contract.getContractFundRentPlans();
					for(ContractFundRentPlan plan : plans){
						if(plan.getRentList() >= Integer.valueOf(rentList)){
							List<String> listtemp =  new ArrayList<String>();
							listtemp.add("第"+plan.getRentList().toString()+"期租金金额");
							listtemp.add(MoneyUtil.parseMoney(plan.getRent()));
							sumrent.add(plan.getRent());
							rentlistArray = rentlistArray + plan.getRentList().toString()+"、";
							rentdateArray = rentdateArray + plan.getPlanDate().replace("-", "年").replace("-", "月") + "日、";
							tableList.add(listtemp);
						}
					}
					
					List<String> priceList = new ArrayList<String>();
					priceList.add("留购价款");
					priceList.add(MoneyUtil.parseMoney(contract.getContractCondition().getNominalPrice()));
					
					List<String> cautionList = new ArrayList<String>();
					cautionList.add("保证金金额");
					cautionList.add(MoneyUtil.parseMoney(contract.getContractCondition().getCautionDeductionMoney()));
					
					List<String> penaltyList = new ArrayList<String>();
					penaltyList.add("罚息金额(明细见附件)");
					penaltyList.add(MoneyUtil.parseMoney(penalty));
					
					BigDecimal allPay = sumrent.add(contract.getContractCondition().getNominalPrice()).subtract(contract.getContractCondition().getCautionDeductionMoney());
					List<String> allPayList = new ArrayList<String>();
					allPayList.add("本期实际支付金额");
					allPayList.add(MoneyUtil.parseMoney(penalty.add(allPay)));
					
					List<String> recommendedDateList = new ArrayList<String>();
					recommendedDateList.add("建议付款日");
					recommendedDateList.add(recommendedDate);
					
					tableList.add(priceList);
					tableList.add(cautionList);
					tableList.add(penaltyList);
					tableList.add(allPayList);
					tableList.add(recommendedDateList);
					
					rentlistArray = rentlistArray.substring(0,rentlistArray.length()-1);
					rentdateArray = rentdateArray.substring(0,rentlistArray.length()-1);
					summary = "依据贵我双方签订的编号为"+contractNumber+"的"+contractName
							+ "，以及我公司出具的《起租通知书》之约定，贵方的第"+rentlistArray+"期租金将于"+rentdateArray+"到期，"
							+ "请按下表列述之时间、金额、汇款途径将款项及时汇入我公司账户。";
				}else{
					//常规版本
					List<String> amountList = new ArrayList<String>();
					amountList.add("第"+rentList+"期租金金额");
					amountList.add("人民币 "+MoneyUtil.parseMoney(rent)+"元");
					
					List<String> penaltyList = new ArrayList<String>();
					penaltyList.add("罚息金额(明细见附件)");
					penaltyList.add("人民币 "+MoneyUtil.parseMoney(penalty)+"元");
					
					List<String> allPayList = new ArrayList<String>();
					allPayList.add("本期实际支付金额");
					allPayList.add("人民币 "+MoneyUtil.parseMoney(penalty.add(rent))+"元");
					
					List<String> recommendedDateList = new ArrayList<String>();
					recommendedDateList.add("建议付款日");
					recommendedDateList.add(recommendedDate);
					tableList.add(amountList);
					tableList.add(penaltyList);
					tableList.add(allPayList);
					tableList.add(recommendedDateList);
					
					summary = "依据贵我双方签订的编号为"+contractNumber+"的"+contractName
							+ "，以及我公司出具的《起租通知书》之约定，贵方的第"+rentList+"期租金将于"+plan_date+"到期，"
							+ "请按下表列述之时间、金额、汇款途径将款项及时汇入我公司账户。";
				}
				
				List<String> aList = new ArrayList<String>();
				aList.add(" ");
				aList.add("致"+custName+"：");
				aList.add(summary);
				aList.add(firstRentDate+"起至"+lastRentDate);
				aList.add("收款人："+leaseAccName);
				aList.add("开户行："+leaseAccBank);
				aList.add("账  号："+leaseAccNumber);
				aList.add(currDate);
				dataMap.put("MS_array", aList);
				Map<String, String> model = new HashMap<String, String>();
				List<Map<String, Object>> onhireRemindList = null;
				
				List<List<String>> penaltyList = new ArrayList<List<String>>();
				String sql = this.tableService.getTableXMLJsonData("/eleasing/jsp/invoice_manage/rent_invoice/payment_notice_penalty_detail.xml", model);
				onhireRemindList = this.tableService.queryListBySql(sql,contractid);
				
				for(int j=0;j<onhireRemindList.size();j++){
					Map<String,Object> innerMap = onhireRemindList.get(j);
					List<String> innerList = new ArrayList<String>();
					innerList.add(String.valueOf(innerMap.get("rent_list")));
					innerList.add(String.valueOf(innerMap.get("rent")));
					innerList.add(String.valueOf(innerMap.get("plan_date")));
					innerList.add(String.valueOf(innerMap.get("hiredate")));
					innerList.add(String.valueOf(innerMap.get("penaday")));
					innerList.add(String.valueOf(innerMap.get("pena_rate")));
					innerList.add(String.valueOf(innerMap.get("penalty")));
					penaltyList.add(innerList);
				}
				dataMap.put("sheetName", contractNumber+" "+custName+"-"+rentList);
				dataMap.put("notice_tabs", tableList);
				dataMap.put("MC_penalty_detail", penaltyList);
				dataList.add(dataMap);
			}
			if(fileName.length()>0){fileName=fileName+",";}
			fileName = fileName +fi.createRealFile2(ft, dataList, "租金通知书","罚息版");
			this.baseService.saveOrUpdateAllEntities(recordList);
		}
		return fileName;
	}
	public String getDateIntervalStr(String planDate, String periodType, String incomeNumberYear){
		int payPeriodMonth = Integer.valueOf(incomeNumberYear.substring(7));
		if("period_type_1".equals(periodType)){
			return DateUtil.addDate(DateUtil.getTimeByFormat(planDate, "yyyy-MM-dd"), "DAY", 1).replaceAll("-", "/")  + "-" + DateUtil.addDate(DateUtil.getTimeByFormat(planDate, "yyyy-MM-dd"), "MONTH", payPeriodMonth).replaceAll("-", "/");
		}else{
			return DateUtil.addDate(DateUtil.getTimeByFormat(planDate, "yyyy-MM-dd"), "MONTH", -payPeriodMonth).replaceAll("-", "/") + "-" + DateUtil.addDate(DateUtil.getTimeByFormat(planDate, "yyyy-MM-dd"), "DAY", -1).replaceAll("-", "/");
		}
	}
	@Override
	public boolean checkEquipInfoImport(List<String> list,
			Map<String, String> model, JSONObject json) throws Exception {
		boolean flag=true;
		for(int i = 0;i < json.length(); i++){
			String equipprice=json.getString("equipprice");//总价
			String equipnum=json.getString("equipnum");//数量
			String price=json.getString("price");//单价
			  if(Double.parseDouble(equipprice)!=Double.parseDouble(equipnum)*Double.parseDouble(price)){
				  
				  flag=false;
			  }
		}
		return flag;
	}
	public  void exportRentIncomeInvoiceBeforeTwo(Map<String,Object> model) throws BusinessException{
		try {
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("ids",(String) model.get("ids"));
			queryMainObjectMap.put("status","已申请");
			JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/rent_invoice/invoice_confirm.xml", queryMainObjectMap);
			JSONArray newexportdata = new JSONArray();
			DictionaryData dictmoney=this.baseService.findEntityByID(DictionaryData.class,"invoicemoney.01");
			BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); //BigDecimal.valueOf(100);
			//同一个合同的罚息合并到rent_invoice_download_info
			Map<String,BigDecimal> cids=new HashMap<String, BigDecimal>();
			//记录合并前的 rent_invoice_info 
			Map<String,String> riis=new HashMap<String, String>();
			for(int i=0;i<exportdata.length();i++){
				JSONObject jsonObj = exportdata.getJSONObject(i);
				if("罚息".equals(jsonObj.optString("plantype"))){
					BigDecimal currentmoney=new BigDecimal(jsonObj.optString("currentmoney"));
					if(cids.containsKey(jsonObj.optString("cid"))){
						currentmoney=new BigDecimal(String.valueOf(cids.get(jsonObj.optString("contractid")))).add(currentmoney);
					}
					cids.put(jsonObj.optString("cid"), currentmoney);
					riis.put(jsonObj.optString("id"), jsonObj.optString("cid"));
				}
			}
			for(String key:cids.keySet()){
				JSONArray penaltyexportdata = new JSONArray();
				if(cids.get(key).compareTo(leavelRent)>0){
					throw new BusinessException("罚息合并金额【"+cids.get(key)+"】超出了单张发票限额【"+leavelRent+"】 -> 不能合并！");
				}
				String serialNumber = "PR"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				JSONObject jsonObj1=new JSONObject();
				jsonObj1.put("outno", serialNumber);//导出流水号
				jsonObj1.put("invoicemoney",cids.get(key));//导出金额
				jsonObj1.put("contractId", key);
				jsonObj1.remove("id");
				penaltyexportdata.put(jsonObj1);
				List<RentInvoiceDownloadInfo> penaltyinfos=(List<RentInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(RentInvoiceDownloadInfo.class, penaltyexportdata.toString(), null, "");
				this.tableService.saveOrUpdateAllEntities(penaltyinfos);
				for(Map.Entry<String, String> entry: riis.entrySet()){
					if(entry.getValue().equals(key)){
						RentInvoiceInfo rii=this.baseService.findEntityByID(RentInvoiceInfo.class, entry.getKey());
						rii.setRidiId(penaltyinfos.get(0));
						this.baseService.updateEntity(rii);
					}
				}
			}
			for(int i=0;i<exportdata.length();i++){
				JSONObject jsonObj = exportdata.getJSONObject(i);
				if("罚息".equals(jsonObj.optString("plantype")))continue;
				RentInvoiceInfo ri=this.baseService.findEntityByID(RentInvoiceInfo.class, jsonObj.optString("id"));
				BigDecimal currentexportmoney=new BigDecimal(jsonObj.optString("currentmoney"));
				BigDecimal taxrate=new BigDecimal(jsonObj.optString("taxrate"));
				BigDecimal reone = BigDecimal.ONE;
				if (currentexportmoney.compareTo(leavelRent) == 1) {
					int floor = currentexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
					String serialNumber="";
					for (int j = 0; j < floor; j++) {
						BigDecimal invoicemoney=leavelRent.multiply(taxrate).divide(reone.add(taxrate), 20, BigDecimal.ROUND_HALF_UP);
						invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
						serialNumber = "PR"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("taxmoney",invoicemoney);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",leavelRent);//导出金额
						jsonObj1.put("rentInvoiceId",jsonObj.optString("id"));
						jsonObj1.remove("id");
						newexportdata.put(jsonObj1);
					}
					
					BigDecimal residueRent = currentexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
					if(residueRent.compareTo(BigDecimal.ZERO)==1){
						BigDecimal invoicemoney=residueRent.multiply(taxrate).divide(reone.add(taxrate), 20, BigDecimal.ROUND_HALF_UP);
						invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
						serialNumber = "PR"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("taxmoney",invoicemoney);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",residueRent);//导出金额
						jsonObj1.put("rentInvoiceId",jsonObj.optString("id"));
						jsonObj1.remove("id");
						newexportdata.put(jsonObj1);
					}
					
				} else {
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PR"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",currentexportmoney);//导出金额
					jsonObj1.put("rentInvoiceId",jsonObj.optString("id"));
					jsonObj1.remove("id");
					newexportdata.put(jsonObj1);
				}
				
			}
			List<RentInvoiceDownloadInfo> infos=(List<RentInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(RentInvoiceDownloadInfo.class, newexportdata.toString(), null, "");
			this.tableService.saveOrUpdateAllEntities(infos);
			this.tableService.updateFlush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		
	}
	@Override
	public Object checkFundEbankDataTX(FundEbankData fd,
			Map<String, String> model, Integer rowIndex) throws Exception {
		BigDecimal borrow = fd.getBorrow();// 借方金额
		    BigDecimal credit = fd.getCredit();// 贷方金额
		    
		    String hsql="FROM OwnAccount o";
		    List<OwnAccount> oacc=this.tableService.findResultsByHSQL(hsql, null);
		    List<String> accNumberList=new ArrayList<String>();
		    for (OwnAccount ownAccount : oacc) {
		    	
		    	accNumberList.add(ownAccount.getAccNumber());
			}
		    if(!accNumberList.contains(fd.getOwnAccNumber())){
		    	throw new BusinessException("第"+rowIndex+"行数据没有找到匹配的帐号");
		    
		    }
		    //String sql="select OWN_ACCOUNT.ACC_NUMBER from OWN_ACCOUNT";
		   
		    String account=fd.getAccount();//帐号信息
		    // int borrowNumber=borrow.compareTo(BigDecimal.ZERO);
		    int creditNumber = credit.compareTo(BigDecimal.ZERO);          
		    if (creditNumber == -1 || creditNumber == 0) {	    
		      this.tableService.removeEntity(fd);
		     
		      return null;
		    }
		    //自动获取上传时间
		    String date=DateUtil.getSystemDateTime();
		    fd.setUploaddate(date);
		    //自动导入网银编号
		    String ebankNumber = WorkflowUtil.getEbankNoInfoSerialNumber(null,tableService.getBussinessDao().getHibernateTemplate(), tableService.getBussinessDao().getJdbcTemplate());
			fd.setEbdataId(ebankNumber);
			
		    return fd;
	}
	@Override
	public Object SetprojDevelop(ProjWindResource projresource, Map<String, String> model, Integer rowIndex)
			throws Exception {
			String  proj_id=model.get("importprojDevelop");
			ProjDevelopInfo developInfo=null;
			if(!"".equals(proj_id)&&null!=proj_id){
					 developInfo=this.tableService.findEntityByID(ProjDevelopInfo.class, proj_id);
	              
			}
			projresource.setProjId(developInfo);
		return projresource;
	}
	@Override
	public Object SetDevelopProgress(ProjectProgress projectprogress, Map<String, String> model, Integer rowIndex)
			throws Exception {
		String  proj_id=model.get("importprojDevelop");
		ProjDevelopInfo developInfo=null;
		if(!"".equals(proj_id)&&null!=proj_id){
			developInfo=this.tableService.findEntityByID(ProjDevelopInfo.class, proj_id);
			
		}
		projectprogress.setProjId(developInfo);
		return projectprogress;
	}
	@Override
	public Object SetequipCost(WpEquipCost projresource,
			Map<String, String> model,Integer rowIndex)
			throws Exception {
			String  proj_id=model.get("importprojDevelop");
			ProjDevelopInfo developInfo=null;
			if(!"".equals(proj_id)&&null!=proj_id){
					 developInfo=this.tableService.findEntityByID(ProjDevelopInfo.class, proj_id);
	              
			}
			projresource.setProjId(developInfo);
		return projresource;
	}
	@Override
	public Object SetbuildCost(WpBuildCost projresource,
			Map<String, String> model,Integer rowIndex) 
			throws Exception {
			String  proj_id=model.get("importprojDevelop");
			ProjDevelopInfo developInfo=null;
			if(!"".equals(proj_id)&&null!=proj_id){
					 developInfo=this.tableService.findEntityByID(ProjDevelopInfo.class, proj_id);
	              
			}
			projresource.setProjId(developInfo);
		return projresource;
	}
	@Override
	public Object SetsetupCost(WpSetupCost projresource,
			Map<String, String> model,Integer rowIndex) 
			throws Exception {
			String  proj_id=model.get("importprojDevelop");
			ProjDevelopInfo developInfo=null;
			if(!"".equals(proj_id)&&null!=proj_id){
					 developInfo=this.tableService.findEntityByID(ProjDevelopInfo.class, proj_id);
	              
			}
			projresource.setProjId(developInfo);
		return projresource;
	}
	@Override
	public Object SetotherCost(WpOtherCost projresource,
			Map<String, String> model,Integer rowIndex) 
			throws Exception {
			String  proj_id=model.get("importprojDevelop");
			ProjDevelopInfo developInfo=null;
			if(!"".equals(proj_id)&&null!=proj_id){
					 developInfo=this.tableService.findEntityByID(ProjDevelopInfo.class, proj_id);
	              
			}
			projresource.setProjId(developInfo);
		return projresource;
	}
	@Override
	public Object SetwindInvestment(WindPowerInvestmentDetails projresource,
			Map<String, String> model,Integer rowIndex)
			throws Exception {
			String  proj_id=model.get("importprojDevelop");
			ProjDevelopInfo developInfo=null;
			if(!"".equals(proj_id)&&null!=proj_id){
					 developInfo=this.tableService.findEntityByID(ProjDevelopInfo.class, proj_id);
	              
			}
			projresource.setProjId(developInfo);
		return projresource;
	}
	@Override
	public Object SetprojDevelopProgress(ProjProgress projresource, Map<String, String> model, Integer rowIndex)
			throws Exception {
		String  proj_id=model.get("importprojDevelop");
		ProjDevelopInfo developInfo=null;
		if(!"".equals(proj_id)&&null!=proj_id){
				 developInfo=this.tableService.findEntityByID(ProjDevelopInfo.class, proj_id);
              
		}
		projresource.setProjId(developInfo);
		return projresource;
	}
	@Override
	public Object SetDateid(DepositInterestInfo depositinterestinfo, Map<String, String> model, Integer rowIndex)
			throws Exception {
		String dateid=model.get("importDateid");
		DepositInterest depositInterest=null;
		if(!"".equals(dateid)&&null!=dateid){
			depositInterest=this.tableService.findEntityByID(DepositInterest.class, dateid);
		}
		depositinterestinfo.setDateId(depositInterest);
		return depositinterestinfo;
	}
	@Override
	public Object SetDateidInterest(InterestProvisionInfo interestProvisionInfo,Map<String, String> model, Integer rowIndex)
			throws Exception {
		String dateid=model.get("importDateid");
		InterestProvision interestProvision=null;
		if(!"".equals(dateid)&&null!=dateid){
			interestProvision=this.tableService.findEntityByID(InterestProvision.class, dateid);
		}
		interestProvisionInfo.setDateId(interestProvision);
		return interestProvisionInfo;
	}
	@Override
	public Object SetloanId(LoanFeePlan loanFeePlan, Map<String, String> model, Integer rowIndex) throws Exception {
		
		String loanid=model.get("importLoanid");
		LoanAccount loanAccount=null;
		if(!"".equals(loanid)&&null!=loanid){
			loanAccount=this.tableService.findEntityByID(LoanAccount.class, loanid);
		}
		loanFeePlan.setLoanid(loanAccount);
		return loanFeePlan;
	}
	@Override
	public Object SetloanIdIncome(LoanFeeIncome loanFeeIncome, Map<String, String> model, Integer rowIndex)
			throws Exception {
		String loanid=model.get("importLoanaccountid");
		LoanAccount loanAccount=null;
		if(!"".equals(loanid)&&null!=loanid){
			loanAccount=this.tableService.findEntityByID(LoanAccount.class, loanid);
		}
		loanFeeIncome.setLoanid(loanAccount);
		return loanFeeIncome;
	}
	@Override
	public Object SetRentloanId(LoanRentPlan loanRentPlan, Map<String, String> model, Integer rowIndex)
			throws Exception {
		String loanid=model.get("importLoanid");
		LoanAccount loanAccount=null;
		if(!"".equals(loanid)&&null!=loanid){
			loanAccount=this.tableService.findEntityByID(LoanAccount.class, loanid);
		}
		loanRentPlan.setLoanid(loanAccount);
		return loanRentPlan;
	}
	@Override
	public Object SetIncomeloanId(LoanRentIncome loanRentIncome, Map<String, String> model, Integer rowIndex)
			throws Exception {
		String loanid=model.get("importLoanid");
		LoanAccount loanAccount=null;
		if(!"".equals(loanid)&&null!=loanid){
			loanAccount=this.tableService.findEntityByID(LoanAccount.class, loanid);
		}
		loanRentIncome.setLoanid(loanAccount);
		return loanRentIncome;
	}
	@Override
	public Object SetPIloanId(LoanRentPlanToInterest loanRentPlanToInterest, Map<String, String> model,
			Integer rowIndex) throws Exception {
		String loanid=model.get("importLoanid");
		LoanAccount loanAccount=null;
		if(!"".equals(loanid)&&null!=loanid){
			loanAccount=this.tableService.findEntityByID(LoanAccount.class, loanid);
		}
		loanRentPlanToInterest.setLoanid(loanAccount);
		
		return loanRentPlanToInterest;
	}
	@Override
	public Object SetIIloanId(LoanRentIncomeToInterest loanRentIncomeToInterest, Map<String, String> model,
			Integer rowIndex) throws Exception {
		
		String loanid=model.get("importLoanid");
		LoanAccount loanAccount=null;
		if(!"".equals(loanid)&&null!=loanid){
			loanAccount=this.tableService.findEntityByID(LoanAccount.class, loanid);
		}
		loanRentIncomeToInterest.setLoanid(loanAccount);
		return loanRentIncomeToInterest;
	}
	
	/*
	 * 资金开票数据导出前准备插入word的数据--未修改
	 *  */
	public String exportFundInvoiceBefore(FileTemplateService fi,Map<String, String> pageMap,BaseFileTemplate ft,String modename) throws Exception{
		Map<String,Object> dataMap=new HashMap<String, Object>();
		String fileName = "";		
		if(pageMap.containsKey("fund_rent_invoice_list")){
			String fundReceiptListStr = pageMap.get("fund_rent_invoice_list");	
			if(null==fundReceiptListStr || "".equals(fundReceiptListStr) || "null".equalsIgnoreCase(fundReceiptListStr)){
				return "";
			}
			List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
			User user = (User) SecurityUtil.getPrincipal();
			String createDate = DateUtil.getSystemDateTime();
			JSONArray fundReceiptJsonArray = new JSONArray(fundReceiptListStr);
			List<List> llist=new ArrayList<List>();
			for(int j = 0; j < fundReceiptJsonArray.length(); j++) {
				JSONObject fundReceiptJsonObj = fundReceiptJsonArray.getJSONObject(j);
				//String cashname=fundReceiptJsonObj.getString("tableid");
				BigDecimal planmoney = new BigDecimal(fundReceiptJsonObj.getString("factmoney"));
				String planmoneyThousands = MoneyUtil.amountToChinese(planmoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				String custname = fundReceiptJsonObj.getString("custname");
				String chargelist=fundReceiptJsonObj.getString("chargelist");//期次
				String settlemethodname=fundReceiptJsonObj.getString("settlemethodname");//结算方式
				String feetype=fundReceiptJsonObj.getString("feetype");//款项名称
				String contractnumber=fundReceiptJsonObj.getString("contractnumber");				
				List alist=new ArrayList<String>();
				String memo=contractnumber+feetype;
				String number="NO." + WorkflowUtil.getRentReceiptSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				alist.add(memo+"收据"+Math.round(Math.random()*10000));
				for(int i=0;i<3;i++){					
					alist.add(fundReceiptJsonObj.getString("factdate"));
			     	alist.add(custname);
			     	alist.add(settlemethodname);		
					alist.add(planmoneyThousands);
					alist.add("￥"+MoneyUtil.parseMoney(planmoney));
					alist.add(contractnumber+"合同项第"+chargelist+"期"+feetype);
					alist.add(user.getRealname());
					alist.add(number);
					
				}											
				llist.add(alist);
				
				FundInvoiceInfo ri=this.baseService.findEntityByID(FundInvoiceInfo.class, fundReceiptJsonObj.getString("id"));
				ri.setInvoiceStatus(3);//类型为收据时，3代表已导出
				ri.setModificator(user);
				ri.setModifyDate(createDate);
				list.add(ri);
			}
			//MS_receipt_8
			dataMap.put("MS_receipt", llist);
			System.out.println(dataMap);
			if(fileName.length()>0){fileName=fileName+",";}
			fileName = fileName+ fi.createRealFile(ft, dataMap, "收据");
		
			this.baseService.saveOrUpdateAllEntities(list);
		}
		return fileName;
	}
	/*
	 * 租金开票数据导出前准备插入word的数据--未修改
	 *  */
	public String exportRentInvoiceBefore(FileTemplateService fi,Map<String, String> pageMap,BaseFileTemplate ft,String modename) throws Exception{
		Map<String,Object> dataMap=new HashMap<String, Object>();
		String fileName = "";
		if(pageMap.containsKey("rent_receipt_list")){
			String rentReceiptListStr = pageMap.get("rent_receipt_list");
			if(null==rentReceiptListStr || "".equals(rentReceiptListStr) || "null".equalsIgnoreCase(rentReceiptListStr)){
				return "";
			}
			//修改状态
			List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
			User user = (User) SecurityUtil.getPrincipal();
			String createDate = DateUtil.getSystemDateTime();
			
			JSONArray rentReceiptJsonArray = new JSONArray(rentReceiptListStr);
			List<List> llist=new ArrayList<List>();
			for(int j = 0; j < rentReceiptJsonArray.length(); j++) {
				JSONObject rentReceiptJsonObj = rentReceiptJsonArray.getJSONObject(j);
				BigDecimal planmoney = new BigDecimal(rentReceiptJsonObj.getString("planmoney"));
				String planmoneyThousands = MoneyUtil.amountToChinese(planmoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				String custname = rentReceiptJsonObj.getString("custname");
				List alist=new ArrayList<String>();
				String plantype=rentReceiptJsonObj.getString("plantype");
				if("利息".equals(plantype)){
					plantype="违约金";
				}else{
					plantype="租金";
				}
				String contractnumber=rentReceiptJsonObj.getString("contractnumber");
				String rentlist=rentReceiptJsonObj.getString("rentlist");
				String memo=contractnumber+"第"+rentlist+"期";
				String number="NO." + WorkflowUtil.getRentReceiptSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				alist.add(memo+"收据"+Math.round(Math.random()*10000));
				for(int i=0;i<3;i++){
					alist.add(rentReceiptJsonObj.getString("cdate"));
					alist.add(custname);
					alist.add("电汇");					
					alist.add(planmoneyThousands);
					alist.add("￥"+MoneyUtil.parseMoney(planmoney));
					alist.add(contractnumber+"合同项第"+rentlist+"期"+plantype);
					alist.add(user.getRealname());
					alist.add(number);
				}
				
				
				llist.add(alist);
				//本金一次开票,id 为合同id
				if("corpusone".equals(rentReceiptJsonObj.getString("plantypeid"))){
					Map<String, Object> propertiesMap=new HashMap<String, Object>();
					propertiesMap.put("contractId", this.tableService.findEntityByID(ContractInfo.class, rentReceiptJsonObj.getString("id")));
					List<RentInvoiceInfo> rentinvoices=this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
					for(RentInvoiceInfo ri:rentinvoices){
						ri.setInvoiceStatus(3);//类型为收据时，3代表已导出
						ri.setModificator(user);
						ri.setModifyDate(createDate);
						list.add(ri);
					}
				}else{
					RentInvoiceInfo ri=this.baseService.findEntityByID(RentInvoiceInfo.class, rentReceiptJsonObj.getString("id"));
					ri.setInvoiceStatus(3);//类型为收据时，3代表已导出
					ri.setModificator(user);
					ri.setModifyDate(createDate);
					list.add(ri);
				}
			}
			//MS_receipt_8
			dataMap.put("MS_receipt", llist);
			if(fileName.length()>0){fileName=fileName+",";}
			fileName = fileName+ fi.createRealFile(ft, dataMap, "收据");
			this.baseService.saveOrUpdateAllEntities(list);
		}
		return fileName;
	}
	@Override
	public String exportFundRentInvoiceBefore(FileTemplateService fi,
			Map<String, String> pageMap, BaseFileTemplate ft, String modename)
			throws Exception {
		Map<String,Object> dataMap=new HashMap<String, Object>();
		String fileName = "";		
		if(pageMap.containsKey("fund_rent_invoice_list")){
			String fundRentInvoiceListStr = pageMap.get("fund_rent_invoice_list");	
			if(null==fundRentInvoiceListStr || "".equals(fundRentInvoiceListStr) || "null".equalsIgnoreCase(fundRentInvoiceListStr)){
				return "";
			}
			List<FundInvoiceDownloadInfo> listFund = new ArrayList<FundInvoiceDownloadInfo>();
			List<RentInvoiceDownloadInfo> listRent = new ArrayList<RentInvoiceDownloadInfo>();
			//User user = (User) SecurityUtil.getPrincipal();
			
			String createDate = DateUtil.getSystemDateTime();
			createDate=createDate.substring(0,10);
			JSONArray fundReceiptJsonArray = new JSONArray(fundRentInvoiceListStr);
			List<List> llist=new ArrayList<List>();
			for(int j = 0; j < fundReceiptJsonArray.length(); j++) {
				JSONObject fundReceiptJsonObj = fundReceiptJsonArray.getJSONObject(j);
				//String cashname=fundReceiptJsonObj.getString("tableid");
				FundInvoiceDownloadInfo  fid=null;
				RentInvoiceDownloadInfo rid=null;
				RentInvoiceInfo ri=null;
				FundInvoiceInfo fii=null;
				User user=null;
				String eqOutno=fundReceiptJsonObj.getString("outno");
				if(eqOutno.startsWith("PR")){//修改租金发票状态					
					String id=fundReceiptJsonObj.getString("id");
					Map<String,Object> queryMap=new HashMap<String, Object>();
					rid=(RentInvoiceDownloadInfo)this.tableService.findEntityByID(RentInvoiceDownloadInfo.class, id);
					ri=(RentInvoiceInfo)this.tableService.findEntityByID(RentInvoiceInfo.class,rid.getRentInvoiceId().getId());
					user=(User)this.tableService.findEntityByID(User.class, ri.getCreator().getId());
					rid.setIsExport("3");
				//	this.tableService.saveEntity(rid);
					listRent.add(rid);					
				}else{//修改资金发票状态					
					String id=fundReceiptJsonObj.getString("id");
					Map<String,Object> queryMap=new HashMap<String, Object>();
					fid=(FundInvoiceDownloadInfo)this.tableService.findEntityByID(FundInvoiceDownloadInfo.class, id);
					fii=(FundInvoiceInfo)this.tableService.findEntityByID(FundInvoiceInfo.class, fid.getFundInvoiceId().getId());
					user=(User)this.tableService.findEntityByID(User.class, fii.getCreator().getId());
					fid.setIsExport("3");
				//	this.tableService.saveEntity(fid);
					listFund.add(fid);
				}
				String userName=user.getRealname();//资金开票或者租金开票流程发起人名称
				
				String hql=" FROM UserDepartment  ut where  ut.user.id=?";
				Map<String,Object> queryUserDeptMap=new HashMap<String, Object>();
				List<UserDepartment> userDeptList=this.tableService.findResultsByHSQL(hql, user.getId());
				Department  dept=this.baseService.findEntityByID(Department.class,userDeptList.get(0).getDept().getId());
				String deptName=dept==null ?  "" :userDeptList.get(0).getDept().getName();//部门名称
/*				BigDecimal planmoney = new BigDecimal(fundReceiptJsonObj.getString("factmoney"));
				String planmoneyThousands = MoneyUtil.amountToChinese(planmoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
*/				String feetype=fundReceiptJsonObj.getString("feetype");//款项名称
				feetype=feetype.equals("利息")? "租息":feetype;
				String contractnumber=fundReceiptJsonObj.getString("contractnumber");					
				String invoiceno=fundReceiptJsonObj.getString("invoiceno");//发票号码
				String custname=fundReceiptJsonObj.getString("custname");//客户名称
				String taxregcode=fundReceiptJsonObj.getString("taxregcode");//税务登记-社会编码				
				String regaddress=fundReceiptJsonObj.getString("regaddress");//地址
				String phone=fundReceiptJsonObj.getString("phone");//电话
				String taxbank=fundReceiptJsonObj.getString("taxbank");//银行
				String taxacc=fundReceiptJsonObj.getString("taxacc");//银行帐号
			//	String invoicemoney=fundReceiptJsonObj.getString("invoicemoney");//本次开票金额
				BigDecimal invoicemoney = new BigDecimal(fundReceiptJsonObj.getString("invoicemoney"));
			//	String invoicemoneyThousands = MoneyUtil.amountToChinese(invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				String taxrate=fundReceiptJsonObj.getString("taxrate");//税率
				String taxratename="";
				if(taxrate.length()>0){
					taxratename=(int)(Double.parseDouble(taxrate)*100)+"%";
				}				
				//String taxmoney=fundReceiptJsonObj.getString("taxmoney");//税额
				BigDecimal taxmoney=new BigDecimal("0");
				if("".equals(fundReceiptJsonObj.getString("taxmoney"))){
				}else{
					taxmoney=new BigDecimal(fundReceiptJsonObj.getString("taxmoney"));
				}			
			//	String taxmoneyThousands = MoneyUtil.amountToChinese(taxmoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				//合计
				//BigDecimal summoney=invoicemoney.add(taxmoney);
				BigDecimal summoney=invoicemoney;
				String summoneyThousands = MoneyUtil.amountToChinese(summoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				String rentlist=fundReceiptJsonObj.getString("rentlist");//期次
				
				//预计开票时间  申请日期加上5天
				String  endDate=DateUtil.addDate(DateUtil.getSystemDate(), "DAY", 5);	
				//标题
				String rentinvoicetype=fundReceiptJsonObj.getString("rentinvoicetype");//发票类型
				
				
				List alist=new ArrayList<String>();
				String memo=contractnumber+feetype;
				String number="NO." + WorkflowUtil.getRentReceiptSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				alist.add(memo+"发票"+Math.round(Math.random()*10000));
				for(int i=0;i<1;i++){	
					alist.add(invoiceno);//发票号码
					alist.add(createDate);//创建日期
					alist.add(custname);//客户名称
					alist.add(taxregcode);//纳税人识别号
					alist.add(regaddress+"  "+phone);//地址电话
					alist.add(taxbank+"  "+taxacc);//银行+银行帐号
					alist.add(feetype);//费用类型--货物或应税劳务名称
					alist.add(MoneyUtil.parseMoney(invoicemoney.subtract(taxmoney)));//金额
					alist.add(taxratename);//税率
					alist.add(MoneyUtil.parseMoney(taxmoney));//税额
					alist.add(MoneyUtil.parseMoney(invoicemoney.subtract(taxmoney)));//合计
					alist.add(MoneyUtil.parseMoney(taxmoney));//合计
					alist.add(summoneyThousands);//合计--大写
					alist.add(MoneyUtil.parseMoney(summoney));//合计--小写
					alist.add("《融资租赁合同》编号:"+contractnumber+",第"+rentlist+"期"+feetype+"。");//合计--小写
					
					alist.add(endDate);//预计开票时间
					alist.add(rentinvoicetype+"信息录入委托单");//标题
					
			     	alist.add(deptName);
					alist.add(userName);
				}											
				llist.add(alist);
				
			}
			//MS_receipt_8
			dataMap.put("MS_invoice", llist);
			System.out.println(dataMap);
			if(fileName.length()>0){fileName=fileName+",";}
			fileName = fileName+ fi.createRealFile(ft, dataMap, "发票");
		if(listFund.size()>0){
			this.baseService.saveOrUpdateAllEntities(listFund);
		}
		if(listRent.size()>0){
			this.baseService.saveOrUpdateAllEntities(listRent);
		}
		}
		return fileName;
	}
}
