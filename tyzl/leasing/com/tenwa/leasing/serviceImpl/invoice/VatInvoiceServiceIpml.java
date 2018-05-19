package com.tenwa.leasing.serviceImpl.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.invoice.VatInvoiceContract;
import com.tenwa.leasing.entity.invoice.VatInvoiceInfo;
import com.tenwa.leasing.service.invoice.VatInvoiceService;

/**   
*    
* 项目名称：tls5.1   
* 类名称：VatInvoiceServiceIpml   
* 类描述：   
* 创建人：luoshuai   
* 创建时间：2014年11月24日 下午5:15:33   
* @version        
*/
@Service(value="vatInvoiceService")
public class VatInvoiceServiceIpml implements VatInvoiceService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void submitVatInvoice(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		List<VatInvoiceInfo> list = new ArrayList<VatInvoiceInfo>();
		for (String id : idsArray) {
			VatInvoiceInfo info = new VatInvoiceInfo();
			info = this.tableService.findEntityByID(VatInvoiceInfo.class, id);
			info.setInvoiceStatus(1);
			list.add(info);
		}
		this.tableService.updateAllEntities(list);
		
	}
	
	
	@Override
	public void saveVatInvoice(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		List<VatInvoiceInfo> list = new ArrayList<VatInvoiceInfo>();
		for (String id : idsArray) {
			VatInvoiceInfo info = new VatInvoiceInfo();
			info = this.tableService.findEntityByID(VatInvoiceInfo.class, id);
			info.setInvoiceStatus(2);
			list.add(info);
		}
		this.tableService.updateAllEntities(list);
	}

	@Override
	public void backVatInvoice(Map<String, String> model) throws Exception {
		String[] idsArray = model.get("ids").split(",");
		List<VatInvoiceInfo> list = new ArrayList<VatInvoiceInfo>();
		for (String id : idsArray) {
			VatInvoiceInfo info = new VatInvoiceInfo();
			info = this.tableService.findEntityByID(VatInvoiceInfo.class, id);
			info.setInvoiceStatus(3);
			list.add(info);
		}
		this.tableService.updateAllEntities(list);

	}


	@Override
	public void saveVatInvoiceContract(Map<String, String> model) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		//保存发票信息
		String supplierId = model.get("supplier");
		CustInfo supplier = this.tableService.findEntityByID(CustInfo.class, supplierId);
		VatInvoiceInfo info = new VatInvoiceInfo();
		info.setSupplier(supplier);
		info.setGoodsName(model.get("goodsname"));
		info.setInvoiceMoney(new BigDecimal(model.get("invoicemoney")));
		info.setInvoiceNo(model.get("invoiceno"));
		info.setPurchasenits(model.get("purchasenits"));
		info.setRecordDate(model.get("recorddate"));
		info.setMemo(model.get("memo"));
		info.setCreator(user);
		info.setCreateDate(date);
		this.tableService.saveOrUpdateEntity(info);
		
		//保存关联合同信息
		List<VatInvoiceContract> list = new ArrayList<VatInvoiceContract>(); 
		JSONArray idsArray=new JSONArray(model.get("contractData"));
		for(int i=0;i<idsArray.length();i++){
			JSONObject jobj=idsArray.getJSONObject(i);
			VatInvoiceContract vatic = new VatInvoiceContract();
			String contractId = jobj.getString("contractid");
			BigDecimal registeredAmt = new BigDecimal(jobj.getString("registeredamt"));
			ContractInfo contractInfo = new ContractInfo();
			contractInfo = this.tableService.findEntityByID(ContractInfo.class, contractId);
			vatic.setContractId(contractInfo);
			vatic.setInvoiceId(info);
			vatic.setRegisteredAmt(registeredAmt);
			vatic.setCreator(user);
			vatic.setCreateDate(date);
			list.add(vatic);
		}
		this.tableService.saveOrUpdateAllEntities(list);
	}


	@Override
	public void updateVatInvoiceContract(Map<String, String> model)
			throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		//修改发票信息
		String supplierId = model.get("supplier");
		CustInfo supplier = this.tableService.findEntityByID(CustInfo.class, supplierId);
		String invoiceid = model.get("invoiceid");
		VatInvoiceInfo info = this.tableService.findEntityByID(VatInvoiceInfo.class, invoiceid);
		info.setSupplier(supplier);
		info.setGoodsName(model.get("goodsname"));
		info.setInvoiceMoney(new BigDecimal(model.get("invoicemoney")));
		info.setInvoiceNo(model.get("invoiceno"));
		info.setPurchasenits(model.get("purchasenits"));
		info.setRecordDate(model.get("recorddate"));
		info.setMemo(model.get("memo"));
		info.setModificator(user);
		info.setModifyDate(date);
		this.tableService.updateEntity(info);
	}
	
}
