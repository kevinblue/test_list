package com.tenwa.leasing.serviceImpl.invoice;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
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
	public void updateFundRentInvoice(Map<String, String> model) throws Exception {
		String id=model.get("id");
		FundInvoiceDownloadInfo  fd=new FundInvoiceDownloadInfo();
		RentInvoiceDownloadInfo  rd=new RentInvoiceDownloadInfo();
		String outno=model.get("outno");
		if(outno.startsWith("PR")){
			rd=(RentInvoiceDownloadInfo)this.tableService.findEntityByID(RentInvoiceDownloadInfo.class, id);
			rd.setInvoiceNo(model.get("invoiceno"));//发票号码
			rd.setInvoiceCode(model.get("invoicecode"));//发票代码
			String taxratename=model.get("taxratename").replace("%","");
			rd.setTaxRate(new BigDecimal(taxratename).divide(new BigDecimal("100")));//发票税率
			rd.setTaxMoney(new BigDecimal(model.get("taxmoney")));//发票税额
			rd.setInvoiceDate(model.get("invoicedate"));//开票日期
			rd.setIsBackImport("1");//回导状态
		/*	if("已回导".equals(rowdateObj.getString("backstatus"))){
				rd.setIsExport("1");//回导状态
			}else{
				rd.setIsExport("0");//回导状态
			}*/
			this.tableService.updateEntity(rd);
		}else{
			fd=(FundInvoiceDownloadInfo)this.tableService.findEntityByID(FundInvoiceDownloadInfo.class, id);
			fd.setInvoiceNo(model.get("invoiceno"));//发票号码
			fd.setInvoiceCode(model.get("invoicecode"));//发票代码
			String taxratename=model.get("taxratename").replace("%","");
			//taxratename=taxratename.replace("%","");
			fd.setTaxRate(new BigDecimal(taxratename).divide(new BigDecimal("100")));//发票税率
			fd.setTaxMoney(new BigDecimal(model.get("taxmoney")));//发票税额
			fd.setInvoiceDate(model.get("invoicedate"));//开票日期
			fd.setIsBackImport("1");//回导状态
		/*	if("已回导".equals(rowdateObj.getString("backstatus"))){
				fd.setIsExport("1");//回导状态
			}else{
				fd.setIsExport("0");//回导状态
			}*/
			this.tableService.updateEntity(fd);
			
		}
		
		
	}
	@Override
	public void saveOrUpdateFundRentInvoice(Map<String, String> model) throws Exception {
		String rowdate=model.get("rowdate").toString();	
		JSONArray rowdateArray=new JSONArray(rowdate);
		JSONObject  rowdateObj=(JSONObject) rowdateArray.get(0);
		String id=rowdateObj.getString("id");
		String outno=rowdateObj.getString("outno");
		//String hcinvoicedate=DateUtil.getSystemDate();
		FundInvoiceDownloadInfo  fd=null;
		RentInvoiceDownloadInfo  rd=null;
		List<FundInvoiceDownloadInfo> listFund=new  ArrayList<FundInvoiceDownloadInfo>();
		List<FundInvoiceDownloadInfo> listFundNew=new  ArrayList<FundInvoiceDownloadInfo>();
		List<RentInvoiceDownloadInfo> listRent=new  ArrayList<RentInvoiceDownloadInfo>();
		List<RentInvoiceDownloadInfo> listRentNew=new  ArrayList<RentInvoiceDownloadInfo>();
		if("租金".equals(rowdateObj.getString("feetype"))||"PR".equals(outno)){
			rd=(RentInvoiceDownloadInfo)this.tableService.findEntityByID(RentInvoiceDownloadInfo.class, id);
			//如果找到对象，将状态改变
			rd.setHcStatus("1");//已红冲
			rd.setIsCancel("1");//已作废
			listRent.add(rd);
			//this.tableService.updateAllEntities(listRent);
			this.tableService.updateEntity(rd);			
			RentInvoiceDownloadInfo downhc = new RentInvoiceDownloadInfo();
			downhc = this.tableService.copyAndOverrideExistedValueFromObject(rd, downhc);
			//租金发票信息 加入一条一模一样的数据 发票状态改为 已红冲  金额变成负数
			downhc.setId("");
			downhc.setHcStatus("-1");//红冲发票
			downhc.setRentInvoiceId(rd.getRentInvoiceId());//与租金发票表关联
			if(downhc.getInvoiceMoney()!=null){
		         BigDecimal b1 = new BigDecimal(-1);
		         downhc.setInvoiceMoney(b1.multiply(downhc.getInvoiceMoney()));
			}
			listRentNew.add(downhc);
			//this.tableService.saveAllEntities(listRentNew);
			this.tableService.saveEntity(downhc);
		}else{
			List<FundInvoiceDownloadInfo>  list =new ArrayList<FundInvoiceDownloadInfo>();
			Map<String,Object>  queryMap=new HashMap<String,Object>();
			queryMap.put("outNo", outno);
			list=this.tableService.findEntityByProperties(FundInvoiceDownloadInfo.class, queryMap);
			fd=list.get(0);
			fd=(FundInvoiceDownloadInfo)this.tableService.findEntityByID(FundInvoiceDownloadInfo.class, id);
			//如果找到对象，将状态改变
			fd.setHcStatus("1");//已红冲
			fd.setIsCancel("1");//已作废
			listFund.add(fd);
			//this.tableService.updateAllEntities(listFund);
			this.tableService.updateEntity(fd);
			FundInvoiceDownloadInfo downhc = new FundInvoiceDownloadInfo();
			downhc = this.tableService.copyAndOverrideExistedValueFromObject(fd, downhc);
			//资金发票信息 加入一条一模一样的数据 发票状态改为 已红冲  金额变成负数
			downhc.setId("");
			downhc.setHcStatus("-1");//红冲发票	
			downhc.setFundInvoiceId(fd.getFundInvoiceId());//与资金发票表关联
			if(downhc.getInvoiceMoney()!=null){
		         BigDecimal b1 = new BigDecimal(-1);
		         downhc.setInvoiceMoney(b1.multiply(downhc.getInvoiceMoney()));
			}
			listFundNew.add(downhc);
			//this.tableService.saveAllEntities(listFundNew);
			this.tableService.saveEntity(downhc);
			
		}
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
	/**
	 * 读取Excle 表格的数据，并且改变数据库对象的属性
	 * 导入的数据分为  资金发票和租金发票，因此需要判断对象类别
	 */
	@Override
	public String updateInvoiceImportExcel(MultipartFile multipartFile,
			Map<String, String> modelData) throws Exception {
		InputStream is =multipartFile.getInputStream();
		//获取路径并把后缀转换成小写
		String path=multipartFile.getOriginalFilename().toLowerCase();
		Workbook wb = null;//操作excel对象
		try {
			 if(path.endsWith("xlsx")){//2007
				 wb=new XSSFWorkbook(is);
			 }else{//2003
				 wb=new HSSFWorkbook(is);
			 }
			Object objvalue=null;
			Sheet sheet=wb.getSheetAt(0);
			int allrow=sheet.getLastRowNum();//总行数120
			List<FundInvoiceDownloadInfo> fidis=new ArrayList<FundInvoiceDownloadInfo>();
			List<RentInvoiceDownloadInfo> ridis=new ArrayList<RentInvoiceDownloadInfo>();
			
			for(int i=0;i<allrow+1;i++){
				Row rows=sheet.getRow(i);//行
				if(i==0){
					continue;//i等于0 的时候，跳出循环。读取下一行数据
				}
					//Cell cell=rows.getCell(0);//列
					//objvalue=this.getStringCellValue(cell);
					FundInvoiceDownloadInfo  fidi=null;
					RentInvoiceDownloadInfo  ridi=null;
					String num=this.getStringCellValue(rows.getCell(0));//序号
					if(num==null){
						break;
					}
					String outno=this.getStringCellValue(rows.getCell(1));
					String invoiceNo=this.getStringCellValue(rows.getCell(4));
					String invoiceCode=this.getStringCellValue(rows.getCell(5));
					BigDecimal invoiceMoney=new BigDecimal(this.getStringCellValue(rows.getCell(7)));
					String invoiceDate=this.getStringCellValue(rows.getCell(3));
					BigDecimal taxMoney=new BigDecimal(this.getStringCellValue(rows.getCell(11)));
					BigDecimal taxRate=new BigDecimal(this.getStringCellValue(rows.getCell(10)));					
					if(outno.startsWith("PF")){
						List<FundInvoiceDownloadInfo>  list =new ArrayList<FundInvoiceDownloadInfo>();
						Map<String,Object>  queryMap=new HashMap<String,Object>();
						queryMap.put("outNo", outno);
						list=this.tableService.findEntityByProperties(FundInvoiceDownloadInfo.class, queryMap);
						fidi=list.get(0);
						fidi.setOutNo(outno);
						fidi.setInvoiceNo(invoiceNo);
						fidi.setInvoiceCode(invoiceCode);
						fidi.setInvoiceMoney(invoiceMoney);
						fidi.setInvoiceDate(invoiceDate);
						fidi.setTaxMoney(taxMoney);
						fidi.setTaxRate(taxRate);
						fidi.setIsBackImport("1");
						fidis.add(fidi);
					}
					if(outno.startsWith("PR")){
						List<RentInvoiceDownloadInfo>  list =new ArrayList<RentInvoiceDownloadInfo>();
						Map<String,Object>  queryMap=new HashMap<String,Object>();
						queryMap.put("outNo", outno);
						list=this.tableService.findEntityByProperties(RentInvoiceDownloadInfo.class, queryMap);
						ridi=list.get(0);
						ridi.setOutNo(outno);
						ridi.setInvoiceNo(invoiceNo);
						ridi.setInvoiceCode(invoiceCode);
						ridi.setInvoiceMoney(invoiceMoney);
						ridi.setInvoiceDate(invoiceDate);
						ridi.setTaxMoney(taxMoney);
						ridi.setTaxRate(taxRate);
						ridi.setIsBackImport("1");
						ridis.add(ridi);
					}
			}
			if(fidis.size()>0||ridis.size()>0){
				if(fidis.size()>0){
					this.tableService.updateAllEntities(fidis);
				}
				if(ridis.size()>0){
					this.tableService.updateAllEntities(ridis);
				}
				String num=String.valueOf(fidis.size()+ridis.size());
				return "{message:\"成功导入"+num+"条\",tabledate:\"\"}";
			}else{
				return "导入失败";
				//return "{message:\"成功导入"+resultJsonArray.length()+"条\",tabledate:"+resultJsonArray.toString()+"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "导入失败";
		}
	}
	/**
	 * 获取每个单元格的数据
	 * @param cell
	 * @return
	 */
	private static String getStringCellValue(Cell cell) {
		if(cell==null){//如果读到空行就退出，不进行下面操作
			return null;
		}
		String strCell = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:			
            	  try {
      				strCell = String.valueOf(cell.getNumericCellValue());
      			} catch (Exception e) {
      				strCell = cell.getStringCellValue().replaceAll(",", "");
      			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}
}
