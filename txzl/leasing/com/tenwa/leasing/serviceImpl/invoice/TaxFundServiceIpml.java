package com.tenwa.leasing.serviceImpl.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.cxf.BusException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.BeanFieldUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.FundInvoiceInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceInfo;
import com.tenwa.leasing.entity.voucher.VoucherassCustConfig;
import com.tenwa.leasing.service.fileTemplate.FileExcelService;
import com.tenwa.leasing.service.invoice.TaxFundService;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.utils.Tools;
import com.tenwa.leasing.utils.WorkflowUtil;

/**   
*    
* 项目名称：tls5.1   
* 类名称：TaxFundServiceIpml   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年11月24日 下午5:15:33   
* @version        
*/
@Service(value="taxFundService")
public class TaxFundServiceIpml implements TaxFundService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "fileExcelService")
	private FileExcelService fileExcelService;
	

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	//资金计划发票提交
	@Override
	public void submitFundPlanInvoice(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		Map<String,Object> propertiesMapin = new HashMap<String, Object>();
		for (String id : idsArray) {
			ContractFundFundPlan contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, id);
			//增加后台判断是否有实收开票
			//1、查询出该计划对应的实收记录
			Set<ContractFundFundCharge> cincomes=contractFundFundPlan.getFundFundCharges();
			//2、查询实收是否存在开票记录
			for(ContractFundFundCharge ci:cincomes){
				//查询实收开发票
				propertiesMapin.put("cffcId", ci);
				propertiesMapin.put("billType","invoice");
				List<FundInvoiceInfo> infoListin = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMapin);
				if (infoListin.size()>0) {
					throw new BusinessException("合同号："+ci.getContractId().getContractId()+"的"+ci.getFeeType().getName()+"有实收开票记录，不能计划开票!");
				}
			}
			FundInvoiceInfo info = new FundInvoiceInfo();
			//查询计划开发票
			propertiesMap.put("cffpId", contractFundFundPlan);
			propertiesMap.put("billType","invoice");
			List<FundInvoiceInfo> infoList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCffpId(contractFundFundPlan);
				info.setInvoiceStatus(2);//已申请
				info.setBillType("invoice");//发票
				info.setMoney(contractFundFundPlan.getPlanMoney());//提交开票金额
				info.setCreator(user);
				info.setCreateDate(createDate);
			} else {
				info = infoList.get(0);
				if(info.getInvoiceStatus()==2){//已申请开票不能再申请
					throw new BusinessException("已申请开票不能再申请！！");
				}else{
					info.setInvoiceStatus(2);
					info.setModificator(user);
					info.setModifyDate(createDate);
				}
			}
			list.add(info);
		}
		this.tableService.saveOrUpdateAllEntities(list);
	}
	
	//资金计划发票退回
	@Override
	public void backFundPlanInvoice(Map<String, String> model) throws Exception {
        String[] idsArray = model.get("ids").split(",");
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		List<FundInvoiceInfo>  taxlist=new ArrayList<FundInvoiceInfo>();
		for (String id : idsArray) {
			FundInvoiceInfo tax=this.tableService.findEntityByID(FundInvoiceInfo.class,id);
			//判断资金发票是否已导出(导出总金额为0)
			Set<FundInvoiceDownloadInfo> funddowns=tax.getFundInvoiceDownloadInfos();
			BigDecimal totaldownmoney=BigDecimal.ZERO;
			for (FundInvoiceDownloadInfo ri:funddowns){
				totaldownmoney=totaldownmoney.add(ri.getInvoiceMoney()).setScale(2);
			}
			if(totaldownmoney.compareTo(BigDecimal.ZERO)!=0){
				throw new BusinessException("已导出，不能退回！！！");
			}
			if(tax.getInvoiceStatus()==1){//已退回开票不能再退回
				throw new BusinessException("已退回开票不能再退回！！");
			}else{
				tax.setInvoiceStatus(1);//已退回
				tax.setModificator(user);
				tax.setModifyDate(date);
				taxlist.add(tax);
			}
		}
		this.tableService.saveAllEntities(taxlist);
	}
	
	// 资金实收发票提交
	@Override
	public void submitFundChargeInvoice(Map<String, String> model) throws Exception {
		Map<String,BigDecimal> mapplan=new HashMap<String, BigDecimal>();
		Map<String,BigDecimal> mapfact=new HashMap<String, BigDecimal>();
		Map<String,BigDecimal> mapinmoney=new HashMap<String, BigDecimal>();
		String datas = model.get("datas");
		JSONArray curArray=new JSONArray(datas);
		for (int i=0;i<curArray.length();i++) {
			JSONObject jobj=curArray.getJSONObject(i);
			String pid=jobj.getString("pid");
			//计算该笔实收对应的计划金额
			String dd = "".equals(jobj.getString("planmoney"))?"0":jobj.getString("planmoney");
			BigDecimal planmoney=new BigDecimal(dd).setScale(2,BigDecimal.ROUND_HALF_EVEN);
			mapplan.put(pid, planmoney);
			
			//计算该笔实收对应的本次实收金额
			BigDecimal factmoney=new BigDecimal(jobj.getString("factmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
			if(mapfact.containsKey(pid)){
				mapfact.put(pid, mapfact.get(pid).add(factmoney).setScale(2,BigDecimal.ROUND_HALF_EVEN));
			}else{
				mapfact.put(pid, factmoney);
			}
			
			//计算该笔实收对应的计划已经申请的开票金额
			ContractFundFundPlan contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, pid);
			//查询出计划对应的实收信息
			BigDecimal inmoney=BigDecimal.ZERO;
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
			propertiesMap.put("fundFundChargePlan", contractFundFundPlan);
			List<ContractFundFundCharge> cchargelist = this.tableService.findEntityByProperties(ContractFundFundCharge.class, propertiesMap);
			for(ContractFundFundCharge cc:cchargelist){
				Map<String, Object> propertiesMap1=new HashMap<String, Object>();
				propertiesMap1.put("cffcId", cc);
				propertiesMap1.put("billType","invoice");
				propertiesMap1.put("invoiceStatus",2);//状态为已申请的金额
				List<FundInvoiceInfo> filist = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap1);
				for(FundInvoiceInfo fi:filist){
					inmoney=inmoney.add(fi.getMoney()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				}
				
			}
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
				throw new BusinessException("合同号："+contractFundFundPlan.getContractId().getContractId()+"的"+contractFundFundPlan.getFeeType().getName()+"提交金额超过计划金额，计划金额："+val+",本次提交申请金额："+mapfact.get(key)+"，已提交金额："+mapinmoney.get(key));
			}
		}
		
		List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		Map<String, Object> propertiesMapin=new HashMap<String, Object>();
		for (int i=0;i<curArray.length();i++) {
			JSONObject jobj=curArray.getJSONObject(i);
			String id=jobj.getString("id");
			FundInvoiceInfo info = new FundInvoiceInfo();
			//contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, paymentidsArray[i]);
			ContractFundFundCharge contractFundFundCharge = this.tableService.findEntityByID(ContractFundFundCharge.class, id);
			
			//增加后台判断是否有实收开票
			//1、查询出该实收对应的计划记录
			ContractFundFundPlan cp=contractFundFundCharge.getFundFundChargePlan();
			//2、查询计划是否存在开票记录
			//查询实收开发票
			propertiesMapin.put("cffpId", cp);
			propertiesMapin.put("billType","invoice");
			List<FundInvoiceInfo> infoListin = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMapin);
			if (infoListin.size()>0) {
				throw new BusinessException("合同号："+cp.getContractId().getContractId()+"的"+cp.getFeeType().getName()+"有计划开票记录，不能实收开票!");
			}
			//查询实收开发票
			propertiesMap.put("cffcId", contractFundFundCharge);
			propertiesMap.put("billType","invoice");
			
			List<FundInvoiceInfo> infoList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCffcId(contractFundFundCharge);
				info.setInvoiceStatus(2);//已申请
				info.setBillType("invoice");//发票
				info.setMoney(contractFundFundCharge.getFactMoney());//提交开票金额
				info.setCreator(user);
				info.setCreateDate(createDate);
			} else {
				info = infoList.get(0);
				if(info.getInvoiceStatus()==2){//已申请开票不能再申请
					throw new BusinessException("已申请开票不能再申请！！");
				}else{
					info.setInvoiceStatus(2);
					info.setModificator(user);
					info.setModifyDate(createDate);
				}
			}
			list.add(info);
		}
		this.tableService.saveOrUpdateAllEntities(list);
	}
	
	//资金实收发票退回
	@Override
	public void backFundChargeInvoice(Map<String, String> model) throws Exception {
        String[] idsArray = model.get("ids").split(",");
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		List<FundInvoiceInfo>  taxlist=new ArrayList<FundInvoiceInfo>();
		for (String id : idsArray) {
			FundInvoiceInfo tax=this.tableService.findEntityByID(FundInvoiceInfo.class,id);
			//判断资金发票是否已导出(导出总金额为0)
			Set<FundInvoiceDownloadInfo> funddowns=tax.getFundInvoiceDownloadInfos();
			BigDecimal totaldownmoney=BigDecimal.ZERO;
			for (FundInvoiceDownloadInfo ri:funddowns){
				totaldownmoney=totaldownmoney.add(ri.getInvoiceMoney()).setScale(2);
			}
			if(totaldownmoney.compareTo(BigDecimal.ZERO)!=0){
				throw new BusinessException("已导出，不能退回！！！");
			}
			if(tax.getInvoiceStatus()==1){//已退回开票不能再退回
				throw new BusinessException("已退回开票不能再退回！！");
			}else{
				tax.setInvoiceStatus(1);//已退回
				tax.setModificator(user);
				tax.setModifyDate(date);
				taxlist.add(tax);
			}
		}
		this.tableService.saveAllEntities(taxlist);
	}
	
	// 资金实收发票提交
	@Override
	public void submitFundChargeNoInvoice(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		for (String id : idsArray) {
			FundInvoiceInfo info = new FundInvoiceInfo();
			//contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, paymentidsArray[i]);
			ContractFundFundCharge contractFundFundCharge = this.tableService.findEntityByID(ContractFundFundCharge.class, id);
			//查询实收开发票
			propertiesMap.put("cffcId", contractFundFundCharge);
			propertiesMap.put("billType","invoice");
			
			List<FundInvoiceInfo> infoList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCffcId(contractFundFundCharge);
				info.setMoney(contractFundFundCharge.getFactMoney());//提交开票金额
				info.setInvoiceStatus(-1);//不开票状态
				info.setBillType("invoice");//发票
				info.setCreator(user);
				info.setCreateDate(createDate);
			} else {
				info = infoList.get(0);
				if(info.getInvoiceStatus()==2){//已申请开票不能再申请
					throw new BusinessException("已申请开票不能申请不开票！！");
				}else if(info.getInvoiceStatus()==-1){
				    throw new BusinessException("已经是不开票不能再申请！！");
				}else{
					info.setInvoiceStatus(-1);
					info.setModificator(user);
					info.setModifyDate(createDate);
				}
			}
			list.add(info);
		}
		this.tableService.saveOrUpdateAllEntities(list);
	}
	
	// 资金实收发票提交
	@Override
	public void submitFundChargeReceipt(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		for (String id : idsArray) {
			FundInvoiceInfo info = new FundInvoiceInfo();
			//contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, paymentidsArray[i]);
			ContractFundFundCharge contractFundFundCharge = this.tableService.findEntityByID(ContractFundFundCharge.class, id);
			//查询实收开发票
			propertiesMap.put("cffcId", contractFundFundCharge);
			propertiesMap.put("billType","receipt");
			
			List<FundInvoiceInfo> infoList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCffcId(contractFundFundCharge);
				info.setMoney(contractFundFundCharge.getFactMoney());//提交开票金额
				info.setInvoiceStatus(2);//已申请
				info.setBillType("receipt");//发票
				info.setCreator(user);
				info.setCreateDate(createDate);
			} else {
				info = infoList.get(0);
				if(info.getInvoiceStatus()==2){//已申请开票不能再申请
					throw new BusinessException("已申请开票不能再申请！！");
				}else{
				    info.setInvoiceStatus(2);
				    info.setModificator(user);
					info.setModifyDate(createDate);
				}
			}
			list.add(info);
		}
		this.tableService.saveOrUpdateAllEntities(list);
	}
	
	//资金实收发票退回
	@Override
	public void backFundChargeReceipt(Map<String, String> model) throws Exception {
        String[] idsArray = model.get("ids").split(",");
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		List<FundInvoiceInfo>  taxlist=new ArrayList<FundInvoiceInfo>();
		for (String id : idsArray) {
			FundInvoiceInfo tax=this.tableService.findEntityByID(FundInvoiceInfo.class,id);
			if(tax.getInvoiceStatus()==1){//已申请开票不能删除
				throw new BusinessException("已退回不能退回！！");
			}else if(tax.getInvoiceStatus()==3){
				throw new BusinessException("已导出不能退回！！");
			}else{
				tax.setInvoiceStatus(1);//已退回
				tax.setModificator(user);
				tax.setModifyDate(date);
				taxlist.add(tax);
			}
		}
		this.tableService.saveAllEntities(taxlist);
	}
	
	//资金实收发票退回
	@Override
	public void deleteFundInvoiceOrReceipt(Map<String, String> model) throws Exception {
        String[] idsArray = model.get("ids").split(",");
		List<FundInvoiceInfo>  taxlist=new ArrayList<FundInvoiceInfo>();
		for (String id : idsArray) {
			FundInvoiceInfo tax=this.tableService.findEntityByID(FundInvoiceInfo.class,id);
			taxlist.add(tax);
		}
		this.tableService.removeAllEntites(taxlist);
	}
		
	
	
	/* (non-Javadoc)资金开票状态更新
	 * @see com.business.service.Invoice.TaxFundService#backFundPlanInvoice(java.util.Map)
	 */
	@Override
	public void updateFundstatus(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		//String  hsql = "update TaxFundInfo set invoiceStatus = '2',modificator=?,modifyDate='"+ DateUtil.getSystemDateTime()+"'  where id =?"; 
		List<FundInvoiceInfo>  taxlist=new ArrayList<FundInvoiceInfo>();
		for (String id : idsArray) {
			String uid=id.replaceAll("\'", "").replaceAll("\"", "");
			FundInvoiceInfo tax=this.tableService.findEntityByID(FundInvoiceInfo.class,uid);
			tax.setInvoiceStatus(2);
			tax.setModificator(user);
			tax.setModifyDate(date);
			taxlist.add(tax);
		}
		this.tableService.saveAllEntities(taxlist);
	}

	@Override
	public void removeFundChargeInvoice(Map<String, String> model)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	/* (non-Javadoc)不生成资金计划开票清单
	 * @see com.business.service.Invoice.TaxFundService#backFundPlanInvoice(java.util.Map)
	 */
	/*@Override
	public void removeFundChargeInvoice(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		String[] paymentidsArray = model.get("paymentids").split(",");
		System.out.println("taxidArray="+model.get("taxid").split(","));
		String[] taxidArray = model.get("taxid").split(",");
		List<TaxFundInfo> list = new ArrayList<TaxFundInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		for (int i=0;i<idsArray.length;i++) {
			ContractFundFundPlan contractFundFundPlan = new ContractFundFundPlan();
			ContractFundFundCharge contractFundFundCharge = new ContractFundFundCharge();
			TaxFundInfo info = new TaxFundInfo();
			contractFundFundCharge.setId(idsArray[i]);
			contractFundFundPlan.setId(paymentidsArray[i]);
			info.setCffpId(contractFundFundPlan);
			info.setCffcId(contractFundFundCharge);
			info.setInvoiceStatus(0);
			info.setCreator(user);
			if (!"null".equals(taxidArray[i]))//SQL中对没有记录的赋了个'null'字符串值,由此判断是否插入还是更新
			{
			info.setId(taxidArray[i]);
			}
			info.setCreateDate(createDate);
			list.add(info);
		}
		this.baseDao.saveOrUpdateAllEntities(list);
	}*/
	
	@Override
	public void addTaxFundHcInfoInvoice(Map<String, String> model) throws Exception{
		//上传编号
		String invoiceno=model.get("invoiceno");
		//红冲通知单号
		String hcnumber=  model.get("hcnumber");
		//紅沖日期
		String hcinvoicedate =  model.get("hcinvoicedate");
		
		FundInvoiceDownloadInfo downinfo=this.tableService.findEntityByID(FundInvoiceDownloadInfo.class, invoiceno);
		if(downinfo!=null){
			downinfo.setHcStatus("1"); //红冲状态1 代表 被红冲 -1代表红冲发票
			this.tableService.saveOrUpdateEntity(downinfo);
	
			FundInvoiceDownloadInfo downhc = new FundInvoiceDownloadInfo();
			downhc = this.tableService.copyAndOverrideExistedValueFromObject(downinfo, downhc);
			//资金发票信息 加入一条一模一样的数据 发票状态改为 已红冲  金额变成负数
			downhc.setId("");
			downhc.setHcStatus("-1");
			downhc.setInvoiceDate(hcinvoicedate);//红冲日期
			downhc.setInvoiceNo(hcnumber);
			downhc.setFundInvoiceId(downinfo.getFundInvoiceId());
			if(downhc.getInvoiceMoney()!=null){
		         BigDecimal b1 = new BigDecimal(-1);
		         downhc.setInvoiceMoney(b1.multiply(downhc.getInvoiceMoney()));
			}
			this.tableService.saveEntity(downhc);

			/*List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
			List<Map<String,String>> list11 = new ArrayList<Map<String,String>>();
			List<Map<String,String>> list12 = new ArrayList<Map<String,String>>();
			List<Map<String,String>> list2 = new ArrayList<Map<String,String>>();
			List<Map<String,String>> list3 = new ArrayList<Map<String,String>>();
			List<Map<String,String>> list4 = new ArrayList<Map<String,String>>();
			String currentDate = DateUtil.getSystemDate();                                                                                                  // 当前时间
			User currentUser = SecurityUtil.getPrincipal();  
			FundInvoiceInfo fidi=downinfo.getFundInvoiceId();
		    ContractInfo contractInfo = null;
		    String feetype="";
			if(null!=fidi){
				ContractFundFundCharge cfc=fidi.getCffcId();
				if(null!=cfc){
					contractInfo=cfc.getContractId();
					feetype=cfc.getFeeType().getId();
				}
			}
		    if(null==contractInfo){
		    	ContractFundFundPlan cff=fidi.getCffpId();
		    	if(null!=cff){
		    		contractInfo=cff.getContractId();
		    		feetype=cff.getFeeType().getId();
		    	}
		    }
			Map<String,String> headMap1 = new HashMap<String, String>();
			Map<String,String> headMap2 = new HashMap<String, String>();
			Map<String,String> headMap3 = new HashMap<String, String>();
			Map<String,String> headMap4 = new HashMap<String, String>();
			if(contractInfo.getId()!=null){
				headMap1.put("contract_id", contractInfo.getId());
				headMap2.put("contract_id", contractInfo.getId());
				headMap3.put("contract_id", contractInfo.getId());
				headMap4.put("contract_id", contractInfo.getId());
			}else{
				throw new BusinessException("合同号为空，不能生成凭证！");
			}
			headMap1.put("moduleName","资金开票红冲-首付款");
			headMap2.put("moduleName","资金开票红冲-手续费");
			headMap3.put("moduleName","资金开票红冲-名义货价");
			headMap4.put("moduleName","资金开票红冲-期末余值");
			*//**
			 * 资金开票（首付款）
			 *//*
			Map<String,Object> proper = new HashMap<String,Object>();
			Map<String,Object> proper_costomer = new HashMap<String,Object>();
			if(contractInfo.getIsInternalSupplier().equals("否")){
				proper.put("custNumber", contractInfo.getSalesCompany());
			}else{
				proper.put("custNumber", contractInfo.getProjCc());
			}
			proper_costomer.put("custNumber", contractInfo.getCustId());
			List<VoucherassCustConfig> vcc_vender = this.tableService.findEntityByProperties(VoucherassCustConfig.class, proper);
			List<VoucherassCustConfig> vcc_customer = this.tableService.findEntityByProperties(VoucherassCustConfig.class, proper_costomer);
			//借1 预收账款（外部客户）--资金开票（首付款）
			if("feetype5".equals(feetype)&&downinfo.getInvoiceMoney().compareTo(BigDecimal.ZERO)!=0){
				Map<String, String> map1 = new HashMap<String, String>(); //借1
				map1.put("voucherSummary", "应付账款（集团内部公司）-Down payment"); 
				// 获取供应商科目编码
				if(null!=vcc_vender&&vcc_vender.size()>0){
					map1.put("F6", vcc_vender.get(0).getFinancialCode_vendor());
				}else{
					throw new BusinessException("该供应商："+contractInfo.getCustId().getCustName()+" 没有配置科目编码！");
				}
				map1.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().toString()));//首付款
				map1.put("borrowMoney", "0");
				map1.put("lendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().toString()));
				map1.put("ebankFactDate", currentDate);// 网银到账日期
				map1.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map1.put("documentType", "KA");//业务类别
				map1.put("postkey", "24");//Post Key
				map1.put("docHeaderText", contractInfo.getContractId());//凭证头
				map1.put("specialGLIndicator", "V");
				list1.add(map1);
				//贷1
				Map<String, String> map11 = new HashMap<String, String>(); 
				map11.put("voucherSummary", "中转科目-Down payment"); 
				map11.put("subjectsId", "122");
				map11.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().toString()));//首付款
				map11.put("borrowMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().toString()));
				map11.put("lendMoney", "0");
				map11.put("ebankFactDate", currentDate);// 网银到账日期
				map11.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map11.put("documentType", "KA");//业务类别
				map11.put("postkey", "50");//Post Key
				map11.put("docHeaderText", contractInfo.getContractId());//凭证头
				map11.put("profitCenter", "5410");//profitCenter
				list1.add(map11);
				//借2
				Map<String, String> map12 = new HashMap<String, String>(); 
				map12.put("voucherSummary", "中转科目-Down payment"); 
				map12.put("subjectsId", "122");
				map12.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().toString()));//首付款
				map12.put("borrowMoney", "0");
				map12.put("lendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().toString()));
				map12.put("ebankFactDate", currentDate);// 网银到账日期
				map12.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map12.put("documentType", "DA");//业务类别
				map12.put("postkey", "40");//Post Key
				map12.put("docHeaderText", contractInfo.getContractId());//凭证头
				map12.put("profitCenter", "5410");//profitCenter
				list11.add(map12);
				//贷2 应收账款--资金开票（首付款）
				Map<String, String> map2 = new HashMap<String, String>();
				map2.put("voucherSummary", "应收账款-Down payment");
				// 获取供应商科目编码
				if(null!=vcc_customer&&vcc_customer.size()>0){
					map2.put("F6", vcc_customer.get(0).getFinancialCode());
				}else{
					throw new BusinessException("该客户："+contractInfo.getCustId().getCustName()+" 没有配置科目编码！");
				}
				map2.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().toString()));//首付款
				map2.put("borrowMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().toString()));
				map2.put("lendMoney", "0");
				map2.put("ebankFactDate", currentDate);// 网银到账日期
				map2.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map2.put("documentType", "DA");//业务类别
				map2.put("postkey", "17");//Post Key
				map2.put("docHeaderText", contractInfo.getContractId());//凭证头
				map2.put("specialGLIndicator", "A");
				list11.add(map2);
				//借2 其他应付款-待缴税金--资金开票（首付款）
				Map<String, String> map3 = new HashMap<String, String>();
				map3.put("voucherSummary", "其他应付款-待缴税金 ");
				map3.put("subjectsId", "106");
				map3.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).toString()));// 首付款/1.17*0.17
				map3.put("borrowMoney", "0");
				map3.put("lendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).toString()));
				map3.put("ebankFactDate", currentDate);// 网银到账日期
				map3.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map3.put("documentType", "SA");//业务类别
				map3.put("postkey", "40");//Post Key
				map3.put("docHeaderText", contractInfo.getContractId());//凭证头
				list12.add(map3);
				//贷2  应交税费-应交增值税（销项税额）--资金开票（首付款）
				Map<String, String> map4 = new HashMap<String, String>();
				map4.put("voucherSummary", "应交税费-应交增值税（销项税额） ");
				map4.put("subjectsId", "110");
				map4.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).toString()));// 首付款/1.17*0.17
				map4.put("borrowMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().abs().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).toString()));
				map4.put("lendMoney", "0");
				map4.put("ebankFactDate", currentDate);// 网银到账日期
				map4.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map4.put("documentType", "SA");//业务类别
				map4.put("postkey", "50");//Post Key
				map4.put("docHeaderText", contractInfo.getContractId());//凭证头
				list12.add(map4);
			}
			*//**
			 * 资金开票（手续费）
			 *//*
			//借3 应收账款--资金开票（手续费）
			if("feetype1".equals(feetype)&&downinfo.getInvoiceMoney().compareTo(BigDecimal.ZERO)!=0){
				Map<String, String> map5 = new HashMap<String, String>();
				map5.put("voucherSummary", "应收账款-Set up fee");
				if(null!=vcc_customer&&vcc_customer.size()>0){
					map5.put("F6", vcc_customer.get(0).getFinancialCode());
				}else{
					throw new BusinessException("该客户："+contractInfo.getCustId().getCustName()+" 没有配置科目编码！");
				}
				BigDecimal handMoney=downinfo.getInvoiceMoney().abs();
				map5.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(handMoney.toString()));// 手续费
				map5.put("borrowMoney", "0");
				map5.put("lendMoney", Tools.formatNumberDoubleTwo(handMoney.toString()));
				map5.put("ebankFactDate", currentDate);// 网银到账日期
				map5.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map5.put("documentType", "DR");//业务类别
				map5.put("postkey", "01");//Post Key
				map5.put("docHeaderText", contractInfo.getContractId());//凭证头
				list2.add(map5);
				//贷3 应交税费-应交增值税（销项税额）
				Map<String, String> map6 = new HashMap<String, String>();
				map6.put("voucherSummary", "应交税费-应交增值税（销项税额）");
				map6.put("subjectsId", "110");
				BigDecimal hand1=handMoney.divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				map6.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(hand1.toString()));// 手续费/1.17*0.17
				map6.put("borrowMoney", Tools.formatNumberDoubleTwo(hand1.toString()));
				map6.put("lendMoney", "0");
				map6.put("ebankFactDate", currentDate);// 网银到账日期
				map6.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map6.put("documentType", "DR");//业务类别
				map6.put("postkey", "50");//Post Key
				map6.put("docHeaderText", contractInfo.getContractId());//凭证头
				map6.put("profitCenter", "5410");//profitCenter
				list2.add(map6);
				//贷4 主营业务收入
				Map<String, String> map7 = new HashMap<String, String>();
				map7.put("voucherSummary", "主营业务收入-Interest");
				map7.put("subjectsId", "111");
				BigDecimal hand3=handMoney.subtract(hand1).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				map7.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(hand3.toString()));// 手续费/1.17*0.17
				map7.put("borrowMoney", Tools.formatNumberDoubleTwo(hand3.toString()));
				map7.put("lendMoney", "0");
				map7.put("ebankFactDate", currentDate);// 网银到账日期
				map7.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map7.put("documentType", "DR");//业务类别
				map7.put("postkey", "50");//Post Key
				map7.put("docHeaderText", contractInfo.getContractId());//凭证头
				list2.add(map7);
			}
			*//**
			 * 资金开票（名义货价）
			 *//*
			if("feetype4".equals(feetype)&&downinfo.getInvoiceMoney().compareTo(BigDecimal.ZERO)>0){
				Map<String, String> map8 = new HashMap<String, String>();
				map8.put("voucherSummary", "应交税费-应交增值税（销项税额）");
				map8.put("subjectsId", "110");
				BigDecimal nominalPrice=downinfo.getInvoiceMoney().abs().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17));
				map8.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(nominalPrice.toString()));// 名义货价或期末余值/1.17*0.17
				map8.put("borrowMoney", Tools.formatNumberDoubleTwo(nominalPrice.toString()));
				map8.put("lendMoney", "0");
				map8.put("ebankFactDate", currentDate);// 网银到账日期
				map8.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map8.put("documentType", "SA");//业务类别
				map8.put("postkey", "50");//Post Key
				map8.put("docHeaderText", contractInfo.getContractId());//凭证头
				map8.put("profitCenter", "5410");//profitCenter
				list3.add(map8);
				Map<String, String> map9 = new HashMap<String, String>();
				map9.put("voucherSummary", "其他应付款-待缴税金");
				map9.put("subjectsId", "106");
				map9.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(nominalPrice.toString()));// 名义货价或期末余值/1.17*0.17
				if(downinfo.getInvoiceMoney().compareTo(BigDecimal.ZERO)>0){
					map9.put("borrowMoney", Tools.formatNumberDoubleTwo(nominalPrice.toString()));
					map9.put("lendMoney", "0");
				}else{
					map9.put("borrowMoney", "0");
					map9.put("lendMoney", Tools.formatNumberDoubleTwo(nominalPrice.toString()));
				}
				map9.put("ebankFactDate", currentDate);// 网银到账日期
				map9.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map9.put("documentType", "SA");//业务类别
				map9.put("postkey", "40");//Post Key
				map9.put("docHeaderText", contractInfo.getContractId());//凭证头
				map9.put("profitCenter", "5410");//profitCenter
				list3.add(map9);
			}
			*//**
			 * 资金开票（期末余值）
			 *//*
			if("feetype12".equals(feetype)&&downinfo.getInvoiceMoney().compareTo(BigDecimal.ZERO)>0){
				Map<String, String> map10 = new HashMap<String, String>();
				map10.put("voucherSummary", "应交税费-应交增值税（销项税额）");
				map10.put("subjectsId", "110");
				BigDecimal equipEndValue=downinfo.getInvoiceMoney().abs().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17));
				map10.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(equipEndValue.toString()));// 名义货价或期末余值/1.17*0.17
				map10.put("borrowMoney", Tools.formatNumberDoubleTwo(equipEndValue.toString()));
				map10.put("lendMoney", "0");
				map10.put("ebankFactDate", currentDate);// 网银到账日期
				map10.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map10.put("documentType", "SA");//业务类别
				map10.put("postkey", "50");//Post Key
				map10.put("docHeaderText", contractInfo.getContractId());//凭证头
				map10.put("profitCenter", "5410");//profitCenter
				list4.add(map10);
				Map<String, String> map11 = new HashMap<String, String>();
				map11.put("voucherSummary", "其他应付款-待缴税金");
				map11.put("subjectsId", "106");
				map11.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(equipEndValue.toString()));// 名义货价或期末余值/1.17*0.17
				map11.put("borrowMoney", "0");
				map11.put("lendMoney", Tools.formatNumberDoubleTwo(equipEndValue.toString()));
				map11.put("ebankFactDate", currentDate);// 网银到账日期
				map11.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
				map11.put("documentType", "SA");//业务类别
				map11.put("postkey", "40");//Post Key
				map11.put("docHeaderText", contractInfo.getContractId());//凭证头
				map11.put("profitCenter", "5410");//profitCenter
				list4.add(map11);
			}
			if(list1.size()>0){
				voucherToV8Service.saveV8Message(headMap1, list1, currentUser);
			}
			if(list11.size()>0){
				voucherToV8Service.saveV8Message(headMap1, list11, currentUser);
			}
			if(list12.size()>0){
				voucherToV8Service.saveV8Message(headMap1, list12, currentUser);
			}
			if(list2.size()>0){
				voucherToV8Service.saveV8Message(headMap2, list2, currentUser);
			}
			if(list3.size()>0){
				voucherToV8Service.saveV8Message(headMap3, list3, currentUser);
			}
			if(list4.size()>0){
				voucherToV8Service.saveV8Message(headMap4, list4, currentUser);
			}*/		
		}
	}

	@Override
	public void updateReceipt(Map<String, String> model) throws Exception {
		 String[] idsArray = model.get("cids").split(",");
		 String[] feetypeArr = model.get("feetypeArr").split(",");
		 String[] factmoneyArr = model.get("factmoneyArr").split(",");
		 String currentDate = DateUtil.getSystemDate();
		 User currentUser = (User) SecurityUtil.getPrincipal();
		 Map<String,String> headMap = new HashMap<String, String>();
	     headMap.put("moduleName", "资金收据（保证金）");
		 for(int i=0;i<idsArray.length;i++){
			 List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			 String cid=idsArray[i];
			 ContractInfo ci=this.tableService.findEntityByID(ContractInfo.class, idsArray[i]);
			 headMap.put("contract_id", ci.getId());
			 Map<String,Object> propertiesMap = new HashMap<String,Object>();
			 propertiesMap.put("custNumber", ci.getCustId());
			 List<VoucherassCustConfig> vcc = this.tableService.findEntityByProperties(VoucherassCustConfig.class, propertiesMap);
			 BigDecimal cautionMoney=new BigDecimal(factmoneyArr[i]);
			 if(cautionMoney.compareTo(BigDecimal.ZERO)!=0&&feetypeArr[i].equals("保证金")){
				 Map<String, String> map1 = new HashMap<String, String>();
				 map1.put("voucherSummary", "应收账款");
				 map1.put("F6", vcc.get(0).getFinancialCode());// VOUCHERASS_STACTS_CONFIG表中SUBJECTS_CODE对应的SUBJECTS_ID
				 map1.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(factmoneyArr[i]));
				 map1.put("borrowMoney",Tools.formatNumberDoubleTwo(factmoneyArr[i]));
				 map1.put("lendMoney","0");
				 map1.put("ebankFactDate", currentDate);// 网银到账日期
				 map1.put("accountDate", currentDate);
				 map1.put("documentType", "DA");//业务类别
				 map1.put("postkey", "04");//Post Key
				 map1.put("docHeaderText", ci.getContractId());//凭证头
				 list.add(map1);
				 //应收帐款 贷方
				 Map<String, String> map2 = new HashMap<String, String>();
				 map2.put("voucherSummary", "其他应付款-保证金");
				 map2.put("subjectsId", "123");
				 map2.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(factmoneyArr[i]));
				 map2.put("borrowMoney","0");
				 map2.put("lendMoney",Tools.formatNumberDoubleTwo(factmoneyArr[i]));
				 map2.put("documentType", "DA");//业务类别
				 map2.put("ebankFactDate", currentDate);// 网银到账日期
				 map2.put("accountDate", currentDate);
				 map2.put("postkey", "50");//Post Key
				 map2.put("docHeaderText", ci.getContractId());//凭证头
				 map2.put("profitCenter", "5410");//profitCenter
				 list.add(map2);
			 }
			 if(list.size()>0){  
			 		voucherToV8Service.saveV8Message(headMap, list, currentUser);
			 }
		 }
	}

	@Override
	public void exportFundChargeInvoiceBefore(Map<String, String> model)
			throws Exception {
		Map<String, Object> objMap=new HashMap<String, Object>();
		objMap.put("ids", model.get("ids"));
		fileExcelService.exportFundChargeInvoiceBefore(objMap);
	}

	@Override
	public void exportFundInvoiceBefore(Map<String, String> model)
			throws Exception {
		Map<String, Object> objMap=new HashMap<String, Object>();
		objMap.put("ids", model.get("ids"));
		fileExcelService.exportFundInvoiceBefore(objMap);
	}

	@Override
	public void exportRentInvoiceBefore(Map<String, String> model)
			throws Exception {
		Map<String, Object> objMap=new HashMap<String, Object>();
		objMap.put("ids", model.get("ids"));
		fileExcelService.exportRentInvoiceBefore(objMap);
	}

	@Override
	public void exportRentIncomeInvoiceBefore(Map<String, String> model)
			throws Exception {
		Map<String, Object> objMap=new HashMap<String, Object>();
		objMap.put("ids", model.get("ids"));
		fileExcelService.exportRentIncomeInvoiceBefore(objMap);
	}

	//租金发票开票确认
		@Override
		public void exportRentIncomeInvoiceBeforeTwo(Map<String, String> model)
				throws Exception {
			Map<String, Object> objMap=new HashMap<String, Object>();
			objMap.put("ids", model.get("rentids"));
			fileExcelService.exportRentIncomeInvoiceBeforeTwo(objMap);
			
		}
		//租金和资金发票开票确认
		@Override
		public void exportRentAndIncomeInvoice(Map<String, String> model) throws Exception {
			String rent=model.get("rentids");
			String invoice=model.get("invoiceids");
			if(rent!=null){
				exportRentIncomeInvoiceBeforeTwo(model);//租金确认开票			
			}
			if(invoice!=null){
				submitConfirmInvoice(model);			//资金确认开票
			}
		}
		//资金发票开票确认
		@Override
		public void submitConfirmInvoice(Map<String, String> model) throws Exception {
			//导出数据参数
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			String ids=(String) model.get("invoiceids");
				queryMainObjectMap.put("ids",ids);
				//JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/fund_invoice/invoiceConfirm/fund_invoice_confirm.xml", queryMainObjectMap);
				JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/rent_invoice/invoice_confirm.xml", queryMainObjectMap);
				JSONArray newexportdata = new JSONArray();
				DictionaryData dictmoney=this.tableService.findEntityByID(DictionaryData.class,"invoicemoney.01");
				BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); 
				for(int i=0;i<exportdata.length();i++){
					JSONObject jsonObj = exportdata.getJSONObject(i);
					BigDecimal currentexportmoney=new BigDecimal(jsonObj.optString("currentmoney"));
					BigDecimal taxrate=new BigDecimal(jsonObj.optString("taxrate"));
					BigDecimal reone = BigDecimal.ONE;
					if (currentexportmoney.compareTo(leavelRent) == 1) {
						int floor = currentexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
						String serialNumber="";
						for (int j = 0; j < floor; j++) {
							BigDecimal invoicemoney=leavelRent.multiply(taxrate).divide(reone.add(taxrate), 20, BigDecimal.ROUND_HALF_UP);
							invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
							serialNumber = "PF"+WorkflowUtil.getFundInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
							JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
							jsonObj1.put("taxmoney",invoicemoney);
							jsonObj1.put("outno", serialNumber);//导出流水号
							jsonObj1.put("invoicemoney",leavelRent);//导出金额
							jsonObj1.put("fundInvoiceId",jsonObj.optString("id"));
							jsonObj1.remove("id");
							newexportdata.put(jsonObj1);
						}
						BigDecimal residueRent = currentexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
						if(residueRent.compareTo(BigDecimal.ZERO)==1){
							BigDecimal invoicemoney=residueRent.multiply(taxrate).divide(reone.add(taxrate), 20, BigDecimal.ROUND_HALF_UP);
							invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
							serialNumber = "PF"+WorkflowUtil.getFundInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
							JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
							jsonObj1.put("taxmoney",invoicemoney);
							jsonObj1.put("outno", serialNumber);//导出流水号
							jsonObj1.put("invoicemoney",residueRent);//导出金额
							jsonObj1.put("fundInvoiceId",jsonObj.optString("id"));
							jsonObj1.remove("id");
							newexportdata.put(jsonObj1);
						}
						
					} else {
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						String serialNumber = "PF"+WorkflowUtil.getFundInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",currentexportmoney);//导出金额
						jsonObj1.put("fundInvoiceId",jsonObj.optString("id"));
						jsonObj1.remove("id");
						newexportdata.put(jsonObj1);
					}
				}
				Map<String,String> dictDataClassMapping = new HashMap<String,String>();
				List<FundInvoiceDownloadInfo> infos=(List<FundInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(FundInvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
				this.tableService.saveOrUpdateAllEntities(infos);
		} 
	
	
}