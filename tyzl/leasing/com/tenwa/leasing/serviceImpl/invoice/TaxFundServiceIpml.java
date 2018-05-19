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

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.FundInvoiceInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceInfo;
import com.tenwa.leasing.service.invoice.TaxFundService;
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
			BigDecimal planmoney=new BigDecimal(jobj.getString("planmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
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
		}
	}
	
}
