package com.tenwa.leasing.serviceImpl.fileTemplate;
 
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
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
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.FundInvoiceInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceInfo;
import com.tenwa.leasing.entity.invoice.VatInvoiceInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.voucher.InterV8Vouchers;
import com.tenwa.leasing.service.fileTemplate.FileExcelService;
import com.tenwa.leasing.service.fileTemplate.FileTemplateService;
import com.tenwa.leasing.service.vouchersFactory.EbankImportService;
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
			 else if(null==fundEbankData.getClientBank()||fundEbankData.getClientBank().length() == 0  ) 
			 {
					EbankDataList.clear();
				 throw new BusinessException("第"+rowIndex+"行对方银行不能为空");
			 }
			 else if(null==fundEbankData.getClientAccount()||fundEbankData.getClientAccount().length() == 0 ) 
			 {
					EbankDataList.clear();
				 throw new BusinessException("第"+rowIndex+"行对方账户不能为空");
			 }
			 else if(null==fundEbankData.getClientAccNumber()||fundEbankData.getClientAccNumber().length() == 0  )
			 {
					EbankDataList.clear();
				 throw new BusinessException("第"+rowIndex+"行对方账号不能为空");
			 }
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
//				if (balanceexportmoney.compareTo(leavelRent) == 1) {
//					int floor = balanceexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
//					String serialNumber="";
//					for (int j = 0; j < floor; j++) {
//						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
//						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
//						jsonObj1.put("outno", serialNumber);//导出流水号
//						jsonObj1.put("invoicemoney",leavelRent);//导出金额
//						newexportdata.put(jsonObj1);
//					}
//					
//					BigDecimal residueRent = balanceexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
//					if(residueRent.compareTo(BigDecimal.ZERO)==1){
//						serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
//						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
//						jsonObj1.put("outno", serialNumber);//导出流水号
//						jsonObj1.put("invoicemoney",residueRent);//导出金额
//						newexportdata.put(jsonObj1);
//					}
//					
//				} else {
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",balanceexportmoney);//导出金额
					newexportdata.put(jsonObj1);
//				}
				
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
		/*		if (balanceexportmoney.compareTo(leavelRent) == 1) {
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
					
				} else {*/
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",balanceexportmoney);//导出金额
					newexportdata.put(jsonObj1);
				}
				
	//		}
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
	public  void exportFundInvoiceAfter(Map<String,Object> model) throws Exception{
		List<FundInvoiceDownloadInfo> infos=(List<FundInvoiceDownloadInfo>) model.get("donwinfos");
		System.out.println("ee");
		List<FundInvoiceDownloadInfo> newinfos=new ArrayList<FundInvoiceDownloadInfo>();
		for(FundInvoiceDownloadInfo info:infos){
			info.setIsExport("1");
			newinfos.add(info);
		}
		this.baseService.saveOrUpdateAllEntities(newinfos);
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
				BigDecimal planmoney = new BigDecimal(fundReceiptJsonObj.getString("factmoney"));
				String planmoneyThousands = MoneyUtil.amountToChinese(planmoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				String custname = fundReceiptJsonObj.getString("custname");
				List alist=new ArrayList<String>();
				String memo=fundReceiptJsonObj.getString("contractnumber")+fundReceiptJsonObj.getString("feetype");
				alist.add(memo+"收据"+Math.round(Math.random()*10000));
				alist.add(fundReceiptJsonObj.getString("factdate"));
				alist.add(custname);
				alist.add("设备租赁款("+memo+")");
				alist.add(MoneyUtil.parseMoney(planmoney));
				alist.add(planmoneyThousands);
				alist.add("");
				alist.add("NO." + WorkflowUtil.getRentReceiptSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate()));
				alist.add("融资租赁有限公司");
				alist.add(user.getRealname());
				
				llist.add(alist);
				
				FundInvoiceInfo ri=this.baseService.findEntityByID(FundInvoiceInfo.class, fundReceiptJsonObj.getString("id"));
				ri.setInvoiceStatus(3);//类型为收据时，3代表已导出
				ri.setModificator(user);
				ri.setModifyDate(createDate);
				list.add(ri);
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
				String memo=rentReceiptJsonObj.getString("contractnumber")+"第"+rentReceiptJsonObj.getString("rentlist")+"期";
				alist.add(memo+"收据"+Math.round(Math.random()*10000));
				alist.add(rentReceiptJsonObj.getString("cdate"));
				alist.add(custname);
				alist.add("设备租赁款("+memo+")");
				alist.add(MoneyUtil.parseMoney(planmoney));
				alist.add(planmoneyThousands);
				alist.add("");
				alist.add("NO." + WorkflowUtil.getRentReceiptSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate()));
				alist.add("融资租赁有限公司");
				alist.add(user.getRealname());
				
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
			/*	if (balanceexportmoney.compareTo(leavelRent) == 1) {
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
					
				} else {*/
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",balanceexportmoney);//导出金额
					newexportdata.put(jsonObj1);
				}
				
//			}
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
		/*		if (balanceexportmoney.compareTo(leavelRent) == 1) {
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
					
				} else {*/
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",balanceexportmoney);//导出金额
					newexportdata.put(jsonObj1);
				}
				
//			}
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
}
