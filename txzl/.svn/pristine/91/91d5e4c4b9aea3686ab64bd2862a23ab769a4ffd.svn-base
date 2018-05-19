package com.tenwa.leasing.serviceImpl.invoice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.FundInvoiceInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceInfo;
import com.tenwa.leasing.entity.voucher.VoucherassCustConfig;
import com.tenwa.leasing.service.invoice.TaxRentService;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.utils.Tools;

/**   
*    
* 项目名称：tls5.1   
* 类名称：TaxRentServiceIpml   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月1日 上午11:20:57   
* @version        
*/
@Service(value="taxRentService")
public class TaxRentServiceIpml implements TaxRentService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	//本金一次性租金开票信息提交
	@Override
	public void submitRentPlanCorpusOneInvoice(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		//保存本金一次性租金开票信息
		List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
		//保存本金一次性开票信息首付款部分
		List<FundInvoiceInfo> listFund = new ArrayList<FundInvoiceInfo>();
		//增加判断，本金一次性开票是否包含首付款
		DictionaryData iscorpusone=this.tableService.findEntityByID(DictionaryData.class, "iscorpusone.01");

		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		for (String id : idsArray) {
			ContractInfo cinfo = this.tableService.findEntityByID(ContractInfo.class, id);
			//RentInvoiceInfo ri = new RentInvoiceInfo();
			//查询计划开发票
			propertiesMap.put("contractId", cinfo);
			propertiesMap.put("billType","invoice");
			List<RentInvoiceInfo> infoList = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				for (ContractFundRentPlan cp : cinfo.getContractFundRentPlans()){
					RentInvoiceInfo ri = new RentInvoiceInfo();
					ri.setContractId(cinfo);
					ri.setCfrpId(cp);
					ri.setInvoiceStatus(2);// 已申请
					ri.setBillType("invoice");// 发票
					ri.setTaxType("corpusone");//本金一次性
					ri.setMoney(cp.getCorpus());//保存本金
					ri.setCreator(user);
					ri.setCreateDate(createDate);
					infoList.add(ri);
				}
			} else {
				for(RentInvoiceInfo rii:infoList){
					rii.setInvoiceStatus(2);
					rii.setModificator(user);
					rii.setModifyDate(createDate);
				}
			}
			list.addAll(infoList);
			
			//增加判断，本金一次性开票是否包含首付款
			if(iscorpusone==null||"是".equals(iscorpusone.getDescription())){
	            //2.处理全金一次性开票时的首付款信息，首付款跟本金和进行绑定操作
				List<ContractFundFundPlan> planList = new ArrayList<ContractFundFundPlan>();
				String hsql = "from ContractFundFundPlan where contractId.id=? and feeType.id=? ";
				planList = this.tableService.findResultsByHSQL(hsql, id,"feetype5");
				Map<String,Object> propertiesMapfund = new HashMap<String, Object>();
				for (ContractFundFundPlan contractFundFundPlan : planList) {
					FundInvoiceInfo info = new FundInvoiceInfo();
					//查询计划开发票
					propertiesMapfund.put("cffpId", contractFundFundPlan);
					propertiesMapfund.put("billType","invoice");
					List<FundInvoiceInfo> fundList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMapfund);
					if (fundList.size() == 0) {
						info.setCffpId(contractFundFundPlan);
						info.setInvoiceStatus(2);//已申请
						info.setBillType("invoice");//发票
						info.setMoney(contractFundFundPlan.getPlanMoney());
						info.setCreator(user);
						info.setCreateDate(createDate);
					} else {
						info = fundList.get(0);
						if(info.getInvoiceStatus()==2){//已申请开票不能再申请
							throw new BusinessException("已申请开票不能再申请！！");
						}else{
							info.setInvoiceStatus(2);
							info.setModificator(user);
							info.setModifyDate(createDate);
						}
					}
					listFund.add(info);
				}
			}
		}
		//保存本金
		this.tableService.saveOrUpdateAllEntities(list);
		
		//增加判断，本金一次性开票是否包含首付款
		if(iscorpusone==null||"是".equals(iscorpusone.getDescription())){
			//保存首付款
			this.tableService.saveOrUpdateAllEntities(listFund);
		}
		
			
	}
		
	
	//租金计划发票提交
	@Override
	public void submitRentPlanInvoice(Map<String, String> model) throws Exception {
		String datas = model.get("datas");
		List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		Map<String,Object> propertiesMapin = new HashMap<String, Object>();
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			ContractFundRentPlan contractRentRentPlan = this.tableService.findEntityByID(ContractFundRentPlan.class, jobj.getString("id"));
			//增加后台判断是否有实收开票
			//1、查询出该计划对应的实收记录
			Set<ContractFundRentInCome> cincomes=contractRentRentPlan.getContractFundRentInComes();
			//2、查询实收是否存在开票记录
			for(ContractFundRentInCome ci:cincomes){
				//查询实收开发票
				propertiesMapin.put("cfriId", ci);
				propertiesMapin.put("billType","invoice");
				propertiesMapin.put("taxType",jobj.getString("type"));
				List<RentInvoiceInfo> infoListin = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMapin);
				if (infoListin.size()>0) {
					throw new BusinessException("合同号："+contractRentRentPlan.getContractId().getContractId()+"第"+contractRentRentPlan.getRentList()+"期有实收开票记录，不能计划开票!");
				}
			}
			RentInvoiceInfo info = new RentInvoiceInfo();
			//查询计划开发票
			propertiesMap.put("cfrpId", contractRentRentPlan);
			propertiesMap.put("billType","invoice");
			propertiesMap.put("taxType",jobj.getString("type"));
			List<RentInvoiceInfo> infoList = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCfrpId(contractRentRentPlan);
				info.setInvoiceStatus(2);//已申请
				info.setBillType("invoice");//发票
				info.setTaxType(jobj.getString("type"));
				info.setMoney(new BigDecimal(jobj.getString("planmoney")));
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
	
	//租金计划发票退回
	@Override
	public void backRentPlanInvoice(Map<String, String> model) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		//增加判断，本金一次性开票是否包含首付款
		DictionaryData iscorpusone=this.tableService.findEntityByID(DictionaryData.class, "iscorpusone.01");

		List<RentInvoiceInfo>  taxlist=new ArrayList<RentInvoiceInfo>();
		List<FundInvoiceInfo>  flist=new ArrayList<FundInvoiceInfo>();
		String datas = model.get("datas");
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			String plantype=jobj.getString("type");
			//本金一次开票,id 为合同id
			if("corpusone".equals(plantype)){
				Map<String, Object> propertiesMap=new HashMap<String, Object>();
				propertiesMap.put("contractId", this.tableService.findEntityByID(ContractInfo.class, jobj.getString("id")));
				
				//判断租金发票是否已导出(导出总金额为0)
				List<RentInvoiceDownloadInfo> rentdowns=this.tableService.findEntityByProperties(RentInvoiceDownloadInfo.class, propertiesMap);
				BigDecimal totaldownmoney=BigDecimal.ZERO;
				for (RentInvoiceDownloadInfo ri:rentdowns){
					totaldownmoney=totaldownmoney.add(ri.getInvoiceMoney()).setScale(2);
				}
				if(totaldownmoney.compareTo(BigDecimal.ZERO)!=0){
					throw new BusinessException("已导出，不能退回！！！");
				}
				
				List<RentInvoiceInfo> rentinvoices=this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
				for (RentInvoiceInfo rii:rentinvoices){
					if(rii.getInvoiceStatus()==1){//已退回开票不能再退回
						throw new BusinessException("已退回开票不能再退回！！");
					}else{
						rii.setInvoiceStatus(1);//已退回
						rii.setModificator(user);
						rii.setModifyDate(date);
						taxlist.add(rii);
					}
				}
				//本金一次性开票首付款部分
				//增加判断，本金一次性开票是否包含首付款
				if(iscorpusone==null||"是".equals(iscorpusone.getDescription())){
					List<ContractFundFundPlan> planList = new ArrayList<ContractFundFundPlan>();
					String hsql = "from ContractFundFundPlan where contractId.id=? and feeType.id=? ";
					planList = this.tableService.findResultsByHSQL(hsql, jobj.getString("id"),"feetype5");
					Map<String,Object> propertiesMapfund = new HashMap<String, Object>();
					for (ContractFundFundPlan contractFundFundPlan : planList) {
						//查询计划开发票
						propertiesMapfund.put("cffpId", contractFundFundPlan);
						propertiesMapfund.put("billType","invoice");
						List<FundInvoiceInfo> fundList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMapfund);
						for(FundInvoiceInfo fi:fundList){
							fi.setInvoiceStatus(1);//已退回
							fi.setModificator(user);
							fi.setModifyDate(date);
							flist.add(fi);
						}
					}
				}
			}else{//分期开票，id为发票id
				RentInvoiceInfo tax=this.tableService.findEntityByID(RentInvoiceInfo.class,jobj.getString("id"));
				//判断租金发票是否已导出(导出总金额为0)
				Set<RentInvoiceDownloadInfo> rentdowns=tax.getRentInvoiceDownloadInfos();
				BigDecimal totaldownmoney=BigDecimal.ZERO;
				for (RentInvoiceDownloadInfo ri:rentdowns){
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
		}
		this.tableService.saveOrUpdateAllEntities(taxlist);
		//增加判断，本金一次性开票是否包含首付款
		if(iscorpusone==null||"是".equals(iscorpusone.getDescription())){
			this.tableService.saveOrUpdateAllEntities(flist);
		}
	}
	
	// 租金实收发票提交
	@Override
	public void submitRentIncomeInvoice(Map<String, String> model) throws Exception {
		String datas = model.get("datas");
		Map<String,BigDecimal> mapplan=new HashMap<String, BigDecimal>();
		Map<String,BigDecimal> mapfact=new HashMap<String, BigDecimal>();
		Map<String,BigDecimal> mapinmoney=new HashMap<String, BigDecimal>();
		JSONArray curArray=new JSONArray(datas);
		for (int i=0;i<curArray.length();i++) {
			JSONObject jobj=curArray.getJSONObject(i);
			String pid=jobj.getString("pid");
			String type=jobj.getString("type");
			//计算该笔实收对应的计划金额
			String spid=pid+"_"+type;
			BigDecimal planmoney=new BigDecimal(jobj.getString("planmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
			mapplan.put(spid, planmoney);
			
			//计算该笔实收对应的本次实收金额
			BigDecimal incomemoney=new BigDecimal(jobj.getString("incomemoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
			if(mapfact.containsKey(spid)){
				mapfact.put(spid, mapfact.get(spid).add(incomemoney).setScale(2,BigDecimal.ROUND_HALF_EVEN));
			}else{
				mapfact.put(spid, incomemoney);
			}
			
			//计算该笔实收对应的计划已经申请的开票金额
			ContractFundRentPlan cplan = this.tableService.findEntityByID(ContractFundRentPlan.class, pid);
			//查询出计划对应的实收信息
			BigDecimal inmoney=BigDecimal.ZERO;
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
			propertiesMap.put("planId", cplan);
			List<ContractFundRentInCome> cchargelist = this.tableService.findEntityByProperties(ContractFundRentInCome.class, propertiesMap);
			for(ContractFundRentInCome cc:cchargelist){
				if(null!=cc.getRollBack()&&!"0".equals(cc.getRollBack())){
					continue;
				}
				Map<String, Object> propertiesMap1=new HashMap<String, Object>();
				propertiesMap1.put("cfriId", cc);
				propertiesMap1.put("billType","invoice");
				propertiesMap1.put("taxType",type);
				propertiesMap1.put("invoiceStatus",2);//状态为已申请的金额
				List<RentInvoiceInfo> filist = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap1);
				for(RentInvoiceInfo fi:filist){
					inmoney=inmoney.add(fi.getMoney()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				}
			}
			mapinmoney.put(spid, inmoney);
		}
		
		///判断当前是否存在实收提交金额大于计划金额
		Iterator<?> iter = mapplan.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			if(key.indexOf("penalty") != -1){continue;}
			BigDecimal val = (BigDecimal) entry.getValue();
			BigDecimal factval =mapfact.get(key).add(mapinmoney.get(key));
			//计划金额与数据库已提交金额+当前提交金额比较
			if(val.compareTo(factval)==-1){
				String cpid=key.split("_")[0];
				ContractFundRentPlan cfp = this.tableService.findEntityByID(ContractFundRentPlan.class, cpid);
				throw new BusinessException("合同号："+cfp.getContractId().getContractId()+"的第"+cfp.getRentList()+"期租金提交金额超过计划金额，<br>计划金额："+val+",本次提交申请金额："+mapfact.get(key)+"，已提交金额："+mapinmoney.get(key));
			}
		}
		
		List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		Map<String,Object> propertiesMapin = new HashMap<String, Object>();
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			ContractFundRentInCome contractRentRentIncome = this.tableService.findEntityByID(ContractFundRentInCome.class, jobj.getString("id"));
			//增加后台判断是否有实收开票
			//1、查询出该实收对应的计划记录
			ContractFundRentPlan cp=contractRentRentIncome.getPlanId();
			//2、查询计划是否存在开票记录
			//查询实收开发票
			propertiesMapin.put("cfrpId", cp);
			propertiesMapin.put("billType","invoice");
			propertiesMapin.put("taxType",jobj.getString("type"));
			List<RentInvoiceInfo> infoListin = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMapin);
			if (infoListin.size()>0) {
				throw new BusinessException("合同号："+cp.getContractId().getContractId()+"第"+cp.getRentList()+"期有计划开票记录，不能实收开票!");
			}
			
			RentInvoiceInfo info = new RentInvoiceInfo();
			//查询实收开发票
			propertiesMap.put("cfriId", contractRentRentIncome);
			propertiesMap.put("billType","invoice");
			propertiesMap.put("taxType",jobj.getString("type"));
			List<RentInvoiceInfo> infoList = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCfriId(contractRentRentIncome);
				info.setInvoiceStatus(2);//已申请
				info.setBillType("invoice");//发票
				info.setTaxType(jobj.getString("type"));
				info.setMoney(new BigDecimal(jobj.getString("incomemoney")));
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
	
	//租金实收发票退回
	@Override
	public void backRentIncomeInvoice(Map<String, String> model) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		List<RentInvoiceInfo>  taxlist=new ArrayList<RentInvoiceInfo>();
		String datas = model.get("datas");
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			RentInvoiceInfo tax=this.tableService.findEntityByID(RentInvoiceInfo.class,jobj.getString("id"));
			//判断租金发票是否已导出(导出总金额为0)
			Set<RentInvoiceDownloadInfo> rentdowns=tax.getRentInvoiceDownloadInfos();
			BigDecimal totaldownmoney=BigDecimal.ZERO;
			for (RentInvoiceDownloadInfo ri:rentdowns){
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
	
	// 租金实收发票提交
	@Override
	public void submitRentIncomeNoInvoice(Map<String, String> model) throws Exception {
		String datas = model.get("datas");
		List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			ContractFundRentInCome contractRentRentIncome = this.tableService.findEntityByID(ContractFundRentInCome.class, jobj.getString("id"));
			RentInvoiceInfo info = new RentInvoiceInfo();
			//查询计划开发票
			propertiesMap.put("cfriId", contractRentRentIncome);
			propertiesMap.put("billType","invoice");
			propertiesMap.put("taxType",jobj.getString("type"));
			List<RentInvoiceInfo> infoList = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCfriId(contractRentRentIncome);
				info.setInvoiceStatus(-1);//不开票
				info.setBillType("invoice");//发票
				info.setTaxType(jobj.getString("type"));
				info.setMoney(new BigDecimal(jobj.getString("incomemoney")));
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
	
	// 租金实收收据提交
	@Override
	public void submitRentIncomeReceipt(Map<String, String> model) throws Exception {
		String datas = model.get("datas");
		List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			String type=jobj.getString("type");
			//分次本开票
			if("corpus".equals(type)){
				ContractFundRentInCome contractRentRentincome = this.tableService.findEntityByID(ContractFundRentInCome.class, jobj.getString("id"));
				RentInvoiceInfo info = new RentInvoiceInfo();
				//查询计划开发票
				propertiesMap.clear();
				propertiesMap.put("cfriId", contractRentRentincome);
				propertiesMap.put("billType","receipt");
				propertiesMap.put("taxType",jobj.getString("type"));
				List<RentInvoiceInfo> infoList = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
				if (infoList.size() == 0) {
					info.setCfriId(contractRentRentincome);
					info.setInvoiceStatus(2);//已申请
					info.setBillType("receipt");//收据
					info.setTaxType(jobj.getString("type"));
					info.setMoney(contractRentRentincome.getCorpus());
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
			}else{
				ContractInfo cinfo = this.tableService.findEntityByID(ContractInfo.class,jobj.getString("id"));
				//RentInvoiceInfo ri = new RentInvoiceInfo();
				//查询计划开发票
				propertiesMap.clear();
				propertiesMap.put("contractId", cinfo);
				propertiesMap.put("billType","receipt");
				List<RentInvoiceInfo> infoList = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
				if (infoList.size() == 0) {
					ContractCondition cp=cinfo.getContractCondition();
					//for (ContractFundRentPlan cp : cinfo.getContractFundRentPlans()){
						RentInvoiceInfo ri = new RentInvoiceInfo();
						ri.setContractId(cinfo);
						//ri.setCfrpId(cp);
						ri.setInvoiceStatus(2);// 已申请
						ri.setBillType("receipt");// 收据
						ri.setTaxType("corpusone");//本金一次性
						ri.setMoney(cp.getEquipAmt());
						ri.setCreator(user);
						ri.setCreateDate(createDate);
						infoList.add(ri);
					//}
				} else {
					for(RentInvoiceInfo rii:infoList){
						if(rii.getInvoiceStatus()==2){//已申请开票不能再申请
							throw new BusinessException("已申请开票不能再申请！！");
						}else{
							rii.setInvoiceStatus(2);
							rii.setModificator(user);
							rii.setModifyDate(createDate);
						}
					}
				}
				list.addAll(infoList);
			}
		}
		this.tableService.saveOrUpdateAllEntities(list);
	}
	
	//资金实收发票退回
	@Override
	public void backRentIncomeReceipt(Map<String, String> model) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		List<RentInvoiceInfo>  taxlist=new ArrayList<RentInvoiceInfo>();
		String datas = model.get("datas");
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			String plantype=jobj.getString("type");
			//本金一次开票,id 为合同id
			if("corpusone".equals(plantype)){
				Map<String, Object> propertiesMap=new HashMap<String, Object>();
				propertiesMap.put("contractId", this.tableService.findEntityByID(ContractInfo.class, jobj.getString("id")));
				List<RentInvoiceInfo> rentinvoices=this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
				for (RentInvoiceInfo rii:rentinvoices){
					if(rii.getInvoiceStatus()==1){//已申请开票不能删除
						throw new BusinessException("已退回不能退回！！");
					}else if(rii.getInvoiceStatus()==3){
						throw new BusinessException("已导出不能退回！！");
					}else{
						rii.setInvoiceStatus(1);//已退回
						rii.setModificator(user);
						rii.setModifyDate(date);
						taxlist.add(rii);
					}
				}
			}else{//分期开票，id为发票id
				RentInvoiceInfo tax=this.tableService.findEntityByID(RentInvoiceInfo.class,jobj.getString("id"));
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
		}
		this.tableService.saveAllEntities(taxlist);
	}
	
	//租金删除开票清单
	@Override
	public void deleteRentInvoiceOrReceipt(Map<String, String> model) throws Exception {
        String[] idsArray = model.get("ids").split(",");
		List<RentInvoiceInfo>  taxlist=new ArrayList<RentInvoiceInfo>();
		for (String id : idsArray) {
			RentInvoiceInfo tax=this.tableService.findEntityByID(RentInvoiceInfo.class,id);
			if(tax.getInvoiceStatus()==2){//已申请开票不能删除
				throw new BusinessException("已申请不能删除！！");
			}
			taxlist.add(tax);
		}
		this.tableService.removeAllEntites(taxlist);
	}
	
	//租金删除开票清单
	@Override
	public void deleteRentReceipt(Map<String, String> model) throws Exception {
		List<RentInvoiceInfo>  taxlist=new ArrayList<RentInvoiceInfo>();
		String datas = model.get("datas");
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			String plantype=jobj.getString("type");
			//本金一次开票,id 为合同id
			if("corpusone".equals(plantype)){
				Map<String, Object> propertiesMap=new HashMap<String, Object>();
				propertiesMap.put("contractId", this.tableService.findEntityByID(ContractInfo.class, jobj.getString("id")));
				List<RentInvoiceInfo> rentinvoices=this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
				for (RentInvoiceInfo rii:rentinvoices){
					if(rii.getInvoiceStatus()==2){//已申请开票不能删除
						throw new BusinessException("已申请不能删除！！");
					}
				}
				taxlist.addAll(rentinvoices);
			}else{//分期开票，id为发票id
				RentInvoiceInfo tax=this.tableService.findEntityByID(RentInvoiceInfo.class,jobj.getString("id"));
				if(tax.getInvoiceStatus()==2){//已申请开票不能删除
					throw new BusinessException("已申请不能删除！！");
				}
				taxlist.add(tax);
			}
		}
		this.tableService.removeAllEntites(taxlist);
	}
	
	//本金一次性开票实收发票退回
	@Override
	public void deleteCorpusOneInvoice(Map<String, String> model) throws Exception {
        String[] idsArray = model.get("ids").split(",");
		List<RentInvoiceInfo>  Rlist=new ArrayList<RentInvoiceInfo>();
		List<FundInvoiceInfo> FList=new ArrayList<FundInvoiceInfo>();
		List<RentInvoiceDownloadInfo>  Dlist=new ArrayList<RentInvoiceDownloadInfo>();
		//增加判断，本金一次性开票是否包含首付款
		DictionaryData iscorpusone=this.tableService.findEntityByID(DictionaryData.class, "iscorpusone.01");

		for (String id : idsArray) {
			ContractInfo  tax=this.tableService.findEntityByID(ContractInfo.class,id);
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
			propertiesMap.put("contractId", tax);
			//开票记录
			List<RentInvoiceInfo>  taxlist=this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
			for (RentInvoiceInfo rii:taxlist){
				if(rii.getInvoiceStatus()==2){//已申请开票不能删除
					throw new BusinessException("已申请不能删除！！");
				}
			}
			Rlist.addAll(taxlist);
			
			//删除导出数据
			List<RentInvoiceDownloadInfo>  downlist=this.tableService.findEntityByProperties(RentInvoiceDownloadInfo.class, propertiesMap);
			Dlist.addAll(downlist);
			//增加判断，本金一次性开票是否包含首付款
			if(iscorpusone==null||"是".equals(iscorpusone.getDescription())){
				//删除首付款开票记录
				List<ContractFundFundPlan> planList = new ArrayList<ContractFundFundPlan>();
				String hsql = "from ContractFundFundPlan where contractId.id=? and feeType.id=? ";
				planList = this.tableService.findResultsByHSQL(hsql, id,"feetype5");
				Map<String,Object> propertiesMapfund = new HashMap<String, Object>();
				for (ContractFundFundPlan contractFundFundPlan : planList) {
					//查询计划开发票
					propertiesMapfund.put("cffpId", contractFundFundPlan);
					propertiesMapfund.put("billType","invoice");
					List<FundInvoiceInfo> fundList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMapfund);
					FList.addAll(fundList);
				}
			}
		}
		this.tableService.removeAllEntites(Rlist);
		this.tableService.removeAllEntites(Dlist);
		//增加判断，本金一次性开票是否包含首付款
		if(iscorpusone==null||"是".equals(iscorpusone.getDescription())){
			this.tableService.removeAllEntites(FList);
		}
	}

	
	@Override
	public void addTaxRentHcInfoInvoice(Map<String, String> model) throws Exception{
		//上传编号
		String invoiceno=model.get("invoiceno");
		//红冲通知单号
		String hcnumber=  model.get("hcnumber");
		//紅沖日期
		String hcinvoicedate =  model.get("hcinvoicedate");
		
		RentInvoiceDownloadInfo downinfo=this.tableService.findEntityByID(RentInvoiceDownloadInfo.class, invoiceno);
		if(downinfo!=null){
			downinfo.setHcStatus("1"); //红冲状态1 代表 被红冲 -1代表红冲发票
			this.tableService.saveOrUpdateEntity(downinfo);
	
			RentInvoiceDownloadInfo downhc = new RentInvoiceDownloadInfo();
			downhc = this.tableService.copyAndOverrideExistedValueFromObject(downinfo, downhc);
			//资金发票信息 加入一条一模一样的数据 发票状态改为 已红冲  金额变成负数
			downhc.setId("");
			downhc.setHcStatus("-1");
			downhc.setContractId(downinfo.getContractId());
			downhc.setInvoiceDate(hcinvoicedate);//红冲日期
			downhc.setInvoiceNo(hcnumber);
			downhc.setRentInvoiceId(downinfo.getRentInvoiceId());
			if(downhc.getInvoiceMoney()!=null){
		         BigDecimal b1 = new BigDecimal(-1);
		         downhc.setInvoiceMoney(b1.multiply(downhc.getInvoiceMoney()));
			}
			this.tableService.saveEntity(downhc);
			/**
			 * 生成租金开票红冲凭证
			 *//*
			String currentDate = DateUtil.getSystemDate();                                                                                                  // 当前时间
			User currentUser = SecurityUtil.getPrincipal();  
			List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
		    ContractInfo contractInfo=downinfo.getContractId();
			Map<String,String> headMap = new HashMap<String, String>();
			RentInvoiceInfo rii=downinfo.getRentInvoiceId();
			String taxtype="";
			if(rii!=null){
				taxtype=rii.getTaxType();
			}
			if(contractInfo.getId()!=null){
				headMap.put("contract_id", contractInfo.getId());
			}else{
				throw new BusinessException("合同号为空，不能生成凭证！");
			}
			Integer leaseTerm=contractInfo.getContractCondition().getLeaseTerm()/12;
			headMap.put("moduleName","租金发票红冲凭证");
			if(taxtype.equals("rent")){
				if(downinfo.getInvoiceMoney().compareTo(BigDecimal.ZERO)!=0){
					//借1 应收账款
					Map<String, String> map1 = new HashMap<String, String>();
					map1.put("voucherSummary", "应收账款-Rent");
					// 获取供应商科目编码
					Map<String,Object> proper = new HashMap<String,Object>();
					proper.put("custNumber", contractInfo.getCustId());
					List<VoucherassCustConfig> vcc = this.tableService.findEntityByProperties(VoucherassCustConfig.class, proper);
					if(null!=vcc&&vcc.size()>0){
						map1.put("F6", vcc.get(0).getFinancialCode());
					}else{
						throw new BusinessException("该供应商："+contractInfo.getCustId().getCustName()+" 没有配置科目编码！");
					}
					map1.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().abs())));//一期租金
					map1.put("borrowMoney", "0");
					map1.put("lendMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().abs())));
					map1.put("ebankFactDate", currentDate);// 网银到账日期
					map1.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
					map1.put("documentType", "DR");//业务类别
					map1.put("postkey", "01");//Post Key
					map1.put("docHeaderText", contractInfo.getContractId());//凭证头
					list1.add(map1);
					//贷1 长期应收款－应收融资租赁款 
					Map<String, String> map2 = new HashMap<String, String>();
					map2.put("voucherSummary", "长期应收款－应收融资租赁款-Rent");
					if(leaseTerm<=1){
						map2.put("subjectsId", "103");//长期应收款-应收融资租赁款-----融资期限>1年时为1384001（）--102，=1年时为162001（）--103
					}else{
						map2.put("subjectsId", "102");
					}
					map2.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().abs())));//一期租金
					map2.put("borrowMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().abs())));
					map2.put("lendMoney", "0");
					map2.put("ebankFactDate", currentDate);// 网银到账日期
					map2.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
					map2.put("documentType", "DR");//业务类别
					map2.put("postkey", "50");//Post Key
					map2.put("docHeaderText", contractInfo.getContractId());//凭证头
					map2.put("profitCenter", "5410");//profitCenter
					list1.add(map2);
					//借2 其他应付款-待缴税金
					Map<String, String> map3 = new HashMap<String, String>();
					map3.put("voucherSummary", "长期应收款－应收融资租赁款-Interest");
					map3.put("subjectsId", "106");
					map3.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).abs().toString()));// 一期租金/1.17*17%
					map3.put("borrowMoney", "0");
					map3.put("lendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).abs().toString()));// 一期租金/1.17*17%
					map3.put("ebankFactDate", currentDate);// 网银到账日期
					map3.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
					map3.put("documentType", "DR");//业务类别
					map3.put("postkey", "40");//Post Key
					map3.put("docHeaderText", contractInfo.getContractId());//凭证头
					map3.put("profitCenter", "5410");//profitCenter
					list1.add(map3);
					//贷2  应交税费-应交增值税（销项税额）
					Map<String, String> map4 = new HashMap<String, String>();
					map4.put("voucherSummary", "应交税费-应交增值税（销项税额） ");
					map4.put("subjectsId", "110");
					map4.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).abs().toString()));// 一期租金/1.17*17%
					map4.put("borrowMoney", Tools.formatNumberDoubleTwo(downinfo.getInvoiceMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).abs().toString()));// 一期租金/1.17*17%
					map4.put("lendMoney", "0");
					map4.put("ebankFactDate", currentDate);// 网银到账日期
					map4.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
					map4.put("documentType", "DR");//业务类别
					map4.put("postkey", "50");//Post Key
					map4.put("docHeaderText", contractInfo.getContractId());//凭证头
					map4.put("profitCenter", "5410");//profitCenter
					list1.add(map4);
				}
			}
			if(taxtype.equals("interest")){
				if(downinfo.getTaxMoney().compareTo(BigDecimal.ZERO)!=0){
					//借3 未实现租赁收益
					Map<String, String> map5 = new HashMap<String, String>();
					map5.put("voucherSummary", "未实现租赁收益 ");
					map5.put("subjectsId", "105");
					map5.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getTaxMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).abs().toString()));// 当期利息收入/1.17
					map5.put("borrowMoney", "0");
					map5.put("lendMoney", Tools.formatNumberDoubleTwo(downinfo.getTaxMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).abs().toString()));
					map5.put("ebankFactDate", currentDate);// 网银到账日期
					map5.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
					map5.put("documentType", "DR");//业务类别
					map5.put("postkey", "40");//Post Key
					map5.put("docHeaderText", contractInfo.getContractId());//凭证头
					map5.put("profitCenter", "5410");//profitCenter
					list1.add(map5);
					//贷3 主营业务收入
					Map<String, String> map6 = new HashMap<String, String>();
					map6.put("voucherSummary", "主营业务收入");
					map6.put("subjectsId", "111");
					map6.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(downinfo.getTaxMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).abs().toString()));// 当期利息收入/1.17
					map6.put("borrowMoney", Tools.formatNumberDoubleTwo(downinfo.getTaxMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).abs().toString()));
					map6.put("lendMoney","0" );
					map6.put("ebankFactDate", currentDate);// 网银到账日期
					map6.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
					map6.put("documentType", "DR");//业务类别
					map6.put("postkey", "50");//Post Key
					map6.put("docHeaderText", contractInfo.getContractId());//凭证头
					list1.add(map6);
				}
			}
			//罚息开票
			if(taxtype.equals("penalty")){
				RentInvoiceInfo cit=downinfo.getRentInvoiceId();
				if(null!=cit){
					String code=cit.getTaxType();
					if("penalty".equals(code)&&downinfo.getInvoiceMoney().compareTo(BigDecimal.ZERO)!=0){
						//借1 应收账款
						Map<String, String> map7 = new HashMap<String, String>();
						map7.put("voucherSummary", "红冲应收账款-Penalty");
						// 获取供应商科目编码
						Map<String,Object> proper = new HashMap<String,Object>();
						proper.put("custNumber", contractInfo.getCustId());
						List<VoucherassCustConfig> vcc = this.tableService.findEntityByProperties(VoucherassCustConfig.class, proper);
						if(null!=vcc&&vcc.size()>0){
							map7.put("F6", vcc.get(0).getFinancialCode());
						}else{
							throw new BusinessException("该客户："+contractInfo.getCustId().getCustName()+" 没有配置科目编码！");
						}
						map7.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().abs())));//罚息
						map7.put("borrowMoney", "0");
						map7.put("lendMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().abs())));
						map7.put("ebankFactDate", currentDate);// 网银到账日期
						map7.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
						map7.put("documentType", "DG");//业务类别
						map7.put("postkey", "11");//Post Key
						map7.put("docHeaderText", contractInfo.getContractId());//凭证头
						list1.add(map7);
						//贷1 长期应收款－应收融资租赁款 
						Map<String, String> map8 = new HashMap<String, String>();
						map8.put("voucherSummary", "应交税费-应交增值税（销项税额） ");
						map8.put("subjectsId", "110");
						map8.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).abs().toString())));//
						map8.put("borrowMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17)).abs().toString())));
						map8.put("lendMoney", "0");//
						map8.put("ebankFactDate", currentDate);// 网银到账日期
						map8.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
						map8.put("documentType", "DG");//业务类别
						map8.put("postkey", "40");//Post Key
						map8.put("docHeaderText", contractInfo.getContractId());//凭证头
						map8.put("profitCenter", "5410");//profitCenter
						list1.add(map8);
						//贷2 主营业务收入
						Map<String, String> map9 = new HashMap<String, String>();
						map9.put("voucherSummary", "主营业务收入-Penalty");
						map9.put("subjectsId", "111");
						map9.put("borrowOrLendMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().subtract(downinfo.getInvoiceMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17))).abs().toString())));//
						map9.put("borrowMoney", Tools.formatNumberDoubleTwo(String.valueOf(downinfo.getInvoiceMoney().subtract(downinfo.getInvoiceMoney().divide(new BigDecimal(1.17),2,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(0.17))).abs().toString())));
						map9.put("lendMoney","0");//
						map9.put("ebankFactDate", currentDate);// 网银到账日期
						map9.put("accountDate", downinfo.getInvoiceDate());// 自行指定日期情况下,可不传
						map9.put("documentType", "DG");//业务类别
						map9.put("postkey", "40");//Post Key
						map9.put("docHeaderText", contractInfo.getContractId());//凭证头
						list1.add(map9);
					}
				}
			}
			if(list1.size()>0){
				voucherToV8Service.saveV8Message(headMap, list1, currentUser);
			}*/
		}
	}
	@Override
	public String importRentInvoiceSKBack(Map<String, String> model) throws Exception{
		String[] idsArray = model.get("outnos").split(",");
		String[] idsArray1 = model.get("cids").split(",");
		StringBuffer outs =  new StringBuffer("");
		for(String id :idsArray){
			outs.append("'"+id+"',");
		}
		outs.deleteCharAt(outs.length()-1);
		String currentDate = DateUtil.getSystemDate();                                                                                                  // 当前时间
		User currentUser = SecurityUtil.getPrincipal();
		JdbcTemplate jdbcTemplate = (JdbcTemplate) WebUtil.getApplicationContext().getBean("jdbcTemplateOther");
		String sql="select out_no,invoice_no,invoice_money,tax_money,invoice_date from INVOICE_DOWNLOAD_INFO_SK_BACK where out_no in("+outs+")";
		List<Map<String,Object>> backInfo=jdbcTemplate.queryForList(sql);
		List<RentInvoiceDownloadInfo> updatefi=new ArrayList<RentInvoiceDownloadInfo>();
		for (Map<String,Object> map:backInfo){
			//判断导入下面字段不能为空
			if(map.get("invoice_no") == null || "".equals(map.get("invoice_no"))){
				throw new BusinessException("发票号不能为空");
			}else if(map.get("invoice_money") == null || "".equals(map.get("invoice_money"))){
				throw new BusinessException(map.get("out_no")+"发票金额不能为空");
			}else if(map.get("invoice_date") == null || "".equals(map.get("invoice_date"))){
				throw new BusinessException(map.get("out_no")+"行发票时间不能为空");
			}else if(map.get("tax_money") == null || "".equals(map.get("tax_money"))){
				throw new BusinessException(map.get("out_no")+"行销项税金不能为空");
			}
			//更新回导表
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
			propertiesMap.put("outNo",map.get("out_no"));
			List<RentInvoiceDownloadInfo> fdi=this.tableService.findEntityByProperties(RentInvoiceDownloadInfo.class, propertiesMap);
			if(fdi.size()>0){
				RentInvoiceDownloadInfo fi=fdi.get(0);
				if(fi.getInvoiceNo()!=null && !"".equals(fi.getInvoiceNo())){
					throw new BusinessException(map.get("out_no")+"发票已经回导，不能重复回导!!!");
				}
				//计算含税金额
				BigDecimal includeinvoicemoey=((BigDecimal) map.get("invoice_money")).add((BigDecimal) map.get("tax_money")).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				if(fi.getInvoiceMoney().compareTo(includeinvoicemoey)!=0){
					throw new BusinessException(map.get("out_no")+"发票金额与导出不相等");
				}
				fi.setInvoiceNo(map.get("invoice_no").toString());//保存发票号
				fi.setInvoiceDate(map.get("invoice_date").toString());//保存开票时间
				fi.setTaxMoney((BigDecimal) map.get("tax_money"));//保存销项税金
				//fi.setTaxRate((BigDecimal) map.get("tax_rate"));//保存税率
				fi.setIsBackImport("1");//1代表已回导
				//修该信息
				fi.setModificator(currentUser);
				fi.setModifyDate(currentDate);
				updatefi.add(fi);
			}
		}
		if(updatefi.size()>0){
			this.tableService.updateAllEntities(updatefi);
		}
		Map<String, String> headMap = new HashMap<String, String>();
		String sql1="from RentInvoiceDownloadInfo  where outNo in("+outs+")";
		List<RentInvoiceDownloadInfo> objs=this.tableService.findResultsByHSQL(sql1);
		List<Map<String,String>> listmap=new ArrayList<Map<String,String>>();
		for(RentInvoiceDownloadInfo o:objs){
			Map<String,String> maps=new HashMap<String,String>();
			maps.put("contract_id", o.getContractId().getId());
			maps.put("contract_number", o.getContractId().getContractNumber());
			maps.put("taxmoney", String.valueOf(o.getTaxMoney()));
			maps.put("cno", o.getContractId().getContractId());
			maps.put("custname", o.getContractId().getCustId().getCustName());
			listmap.add(maps);
	    }
		List<Map<String,String>> listmap2=new ArrayList<Map<String,String>>();
		int count=0;
		if(null!=listmap&&listmap.size()>0){
			for(int i=0;i<listmap.size();i++){
				if(null!=listmap2){
					count=listmap2.size();
				}
				if(i==0){
					listmap2.add(listmap.get(i));
				}else{
					for(int j=0;j<count;j++){
						if(listmap.get(i).get("contract_id").equals(listmap2.get(j).get("contract_id"))){
							BigDecimal tax=new BigDecimal(listmap2.get(j).get("taxmoney"));
							BigDecimal tax1=new BigDecimal(listmap.get(i).get("taxmoney"));
							listmap2.get(j).put("taxmoney", tax.add(tax1).toString());
							break;
						}
						if(j==count-1){
							listmap2.add(listmap.get(i));
						}
					}
				}
				
			}
			
		}
		for(int i=0;i<listmap2.size();i++){
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			headMap.put("contract_id", listmap2.get(i).get("contract_id"));
			headMap.put("moduleName", "租金开票数据回导");
			// 借1 2241.02
			Map<String, String> map3 = new HashMap<String, String>();
			map3.put("voucherSummary", "代缴税金(" +listmap2.get(i).get("contract_number")+"-"+listmap2.get(i).get("cno")+"-"+listmap2.get(i).get("custname")+")");
			ContractInfo contract=this.tableService.findEntityByID(ContractInfo.class, listmap2.get(i).get("contract_id"));
			List<ContractFundRentPlan> cffps=this.tableService.findResultsByHSQL("from ContractFundRentPlan where planDate=(select max(planDate) from ContractFundRentPlan where contractId=?)", contract);
			if(null!=cffps&&cffps.size()>0){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date plandate=sdf.parse(cffps.get(0).getPlanDate());//计划日期
				//系统时间往后推一年
				Calendar curr=Calendar.getInstance();
				curr.set(Calendar.YEAR, curr.get(Calendar.YEAR)+1);
				Date sysdate=curr.getTime();
				if(plandate.getTime()>sysdate.getTime()){
					map3.put("subjectsId", "132");//2701.02 对应id
				}else{
					map3.put("subjectsId", "107");
				}
			}
			map3.put("debitMoney", Tools.formatNumberDoubleTwo(listmap2.get(i).get("taxmoney")));// 借方金额
			map3.put("lenderMoney", "0"); // 贷方金额
			map3.put("ebankFactDate", currentDate);// 网银到账日期
			//map3.put("accountDate", accountingdate);// 自行指定日期情况下,可不传
			list.add(map3);
			
			// 贷1
			Map<String, String> map4 = new HashMap<String, String>();
			map4.put("voucherSummary", "确认销项税额(" + listmap2.get(i).get("contract_number")+"-"+listmap2.get(i).get("cno")+"-"+listmap2.get(i).get("custname")+")");
			map4.put("subjectsId", "106");// VOUCHERASS_STACTS_CONFIG表中SUBJECTS_CODE对应的SUBJECTS_ID
			map4.put("debitMoney", "0");// 借方金额
			map4.put("lenderMoney", Tools.formatNumberDoubleTwo(listmap2.get(i).get("taxmoney"))); // 贷方金额
			//租金红冲没有网银信息
			map4.put("ebankFactDate", currentDate);// 网银到账日期
			//map4.put("accountDate", accountingdate);// 自行指定日期情况下,可不传
			list.add(map4);
			if(list.size()>0){
				voucherToV8Service.saveV8Message(headMap,list,currentUser);
			}
		}
		return "成功回导"+backInfo.size()+"条发票数据";
		
	}


	@Override
	public String updateRedInvoiceStatus(Map<String, String> model)
			throws Exception {
		String[] idsArray = model.get("outnos").split(",");
		StringBuffer outs =  new StringBuffer("");
		Map<String,Object> valueMap=new HashMap<String, Object>();
		for(String id :idsArray){
			valueMap.put("outNo",id);
			if(id.startsWith("PF")){
				List<FundInvoiceDownloadInfo> fidis=this.tableService.findEntityByProperties(FundInvoiceDownloadInfo.class, valueMap);
				for(FundInvoiceDownloadInfo fidi:fidis){
					if("-1".equals(fidi.getHcStatus())){
						fidi.setIsCancel("是");
					}else if("1".equals(fidi.getHcStatus())){
						fidi.setHcStatus(null);
					}
					this.tableService.updateEntity(fidi);
				}
			}else{
				List<RentInvoiceDownloadInfo> ridis=this.tableService.findEntityByProperties(RentInvoiceDownloadInfo.class, valueMap);
				for(RentInvoiceDownloadInfo ridi:ridis){
					if("-1".equals(ridi.getHcStatus())){
						ridi.setIsCancel("是");
					}else if("1".equals(ridi.getHcStatus())){
						ridi.setHcStatus(null);
					}
					this.tableService.updateEntity(ridi);
				}
			}
		}
		return "操作成功！";
		
	}
	
}
