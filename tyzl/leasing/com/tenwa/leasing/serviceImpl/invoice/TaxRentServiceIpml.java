package com.tenwa.leasing.serviceImpl.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.invoice.FundInvoiceInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceInfo;
import com.tenwa.leasing.service.invoice.TaxRentService;

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
		}
	}
	
}
