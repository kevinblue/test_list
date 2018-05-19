package com.tenwa.leasing.serviceImpl.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.BeanFieldUtil;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.InvoiceDownloadInfo;
import com.tenwa.leasing.service.fileTemplate.FileExcelService;
import com.tenwa.leasing.service.invoice.TaxInvoiceService;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
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
@Service(value="taxInvoiceService")
public class TaxInvoiceServiceIpml implements TaxInvoiceService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "fileExcelService")
	private FileExcelService fileExcelService;
	

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	@Override
	public void submitConfirmInvoice(Map<String, String> model) throws Exception {
		//导出数据参数
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("ids",(String) model.get("ids"));
		
		String invoiceids=queryMainObjectMap.get("ids");
		String [] arrayids=invoiceids.split(",");
		for(int i=0;i<arrayids.length;i++){
			arrayids[i]=arrayids[i].replace("'", "");
		}
		
		JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/fund_invoice/invoiceConfirm/fund_invoice_confirm.xml", queryMainObjectMap);
		JSONArray newexportdata = new JSONArray();
		DictionaryData dictmoney=this.tableService.findEntityByID(DictionaryData.class,"invoicemoney.01");
		BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); 
		for(int i=0;i<exportdata.length();i++){
			JSONObject jsonObj = exportdata.getJSONObject(i);
			Boolean flag=false;
			for(int k=0;k<arrayids.length;k++){
				if(arrayids[k].equals(jsonObj.getString("id"))){
					flag=true;
				}
			}
			if(flag==false){
				break;
			}
			
			BigDecimal currentexportmoney=new BigDecimal(jsonObj.optString("currentmoney"));
			if (currentexportmoney.compareTo(leavelRent) == 1) {
				int floor = currentexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
				String serialNumber="";
				for (int j = 0; j < floor; j++) {
					serialNumber = "PF"+WorkflowUtil.getFundInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",leavelRent);//导出金额
					jsonObj1.put("fundInvoiceId",jsonObj.optString("id"));
					jsonObj1.remove("id");
					newexportdata.put(jsonObj1);
				}
				
				BigDecimal residueRent = currentexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
				if(residueRent.compareTo(BigDecimal.ZERO)==1){
					serialNumber = "PF"+WorkflowUtil.getFundInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
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
	
	//资金计划发票提交
	@Override
	public void submitRentConfirmInvoice(Map<String, String> model) throws Exception {
		//导出数据参数
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("ids",(String) model.get("ids"));
		JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/fund_invoice/invoiceConfirm/rent_invoice_confirm_exp.xml", queryMainObjectMap);
		JSONArray newexportdata = new JSONArray();
		
		for(int i=0;i<exportdata.length();i++){
			JSONObject jsonObj = exportdata.getJSONObject(i);
				JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
				String serialNumber = "PF"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				jsonObj1.put("outno", serialNumber);//导出流水号
				newexportdata.put(jsonObj1);
		}
		//资金计划导出数据入库
		//数据字典通过name属性匹配
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		List<InvoiceDownloadInfo> infos=(List<InvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(InvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
		this.tableService.saveOrUpdateAllEntities(infos);
		
	} 
		
	//删除开票清单
	@Override
	public void deleteInvoiceInfo(Map<String, String> model) throws Exception {
        String[] idsArray = model.get("ids").split(",");
		List<InvoiceDownloadInfo>  taxlist=new ArrayList<InvoiceDownloadInfo>();
		for (String id : idsArray) {
			InvoiceDownloadInfo tax=this.tableService.findEntityByID(InvoiceDownloadInfo.class,id);
			if(tax.getIsExport()=="1"){//已申请开票不能删除
				throw new BusinessException("已导出不能删除！！");
			}
			taxlist.add(tax);
		}
		this.tableService.removeAllEntites(taxlist);
	}
	
}
