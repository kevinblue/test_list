package com.tenwa.leasing.serviceImpl.Finance;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.entity.User;
import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.PoiExcelUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.finance.FinancialData;
import com.tenwa.leasing.entity.finance.FinancialSubjects;
import com.tenwa.leasing.entity.finance.FinancialTable;
import com.tenwa.leasing.service.finance.FinanceService;
@Service(value = "financeServiceImpl")
public class FinanceServiceImpl implements FinanceService {
	@Resource(name = "tableService")
	private TableService tableService;
	@Override
	public String importFinance(HttpServletRequest request,Map<String, String> model) throws Exception {
		MultipartFile multipartFile = null;
		InputStream is = null;
		String custid="";
		CustInfo custInfo=null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			//加载附件
			multipartFile = multipartRequest.getFile("tableImportExcel");
			if (null == multipartFile) {
				throw new BusinessException("没有上传财务报表的EXCEL");
			}
			//根据前台传过来的客户ID获得客户
			if(model.containsKey("custid")){
				custid=model.get("custid");
				if(custid==null||"".equals(custid)){
					
				}else{
				   custInfo=this.tableService.findEntityByID(CustInfo.class, custid);
				}
			}
			if(null==custInfo){
				throw new BusinessException("导入财务报表时没有提供客户ID");
			}
			//获得当前登陆的用户
			User user = null;
			boolean isWithUser = true;
			try {
				user = (User) SecurityUtil.getPrincipal();
			} catch (Exception e) {
				isWithUser = false;
			}
			is = multipartFile.getInputStream();
			Workbook wb = null;
			//获得上传文件的名称
			String importFileName = multipartFile.getOriginalFilename().toLowerCase();
			//登记上传的附件
			BaseFile bf=this.tableService.saveUpFiletoService(multipartFile, model);
			this.tableService.addlogFileOper(bf, "上传");
			//判断文件类型创建wb
			try {
				if (importFileName.endsWith("xlsx")) {
					wb = PoiExcelUtil.readWorkbook(is,ExcelVersionEnum.VERSION2007);
				} else {
					wb = PoiExcelUtil.readWorkbook(is,ExcelVersionEnum.VERSION2003);
				}
			} catch (Exception e1) {
				throw new BusinessException("财务报表解析excel现错" + e1.getMessage());
			}
		    Map<String,List>financeTableConfig=new  HashMap<String,List>();
		    //根据上传的文件的sheet名获得上传的配置
		    financeTableConfig=this.getSheetRowConfig(wb);
		    if(financeTableConfig.keySet().size()==0){
		    	throw new BusinessException("财务报表出错没有配置科目表");
		    }
		    //根据文件和配置导入财务报表
		    this.importFinanceDateByConfig(wb, financeTableConfig,custInfo,user);
		} catch (Exception e) {
			e.printStackTrace();
			is.close();
			throw new BusinessException(e.getMessage());
		}finally{
			if(null!=is){FileUtil.safeCloseInputStream(is);}
		}
		return null;
		
	}
	@Override
	public String addFinance(HttpServletRequest request,Map<String, String> model) throws Exception {
		MultipartFile multipartFile = null;
		InputStream is = null;
		String custid="";
		CustInfo custInfo=null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			//加载附件
			multipartFile = multipartRequest.getFile("tableImportExcel");
			if (null == multipartFile) {
				throw new BusinessException("没有上传财务报表的EXCEL");
			}
			//根据前台传过来的客户ID获得客户
			if(model.containsKey("custid")){
				custid=model.get("custid");
				if(custid==null||"".equals(custid)){
					
				}else{
				   custInfo=this.tableService.findEntityByID(CustInfo.class, custid);
				}
			}
			if(null==custInfo){
				throw new BusinessException("导入财务报表时没有提供客户ID");
			}
			//获得当前登陆的用户
			User user = null;
			boolean isWithUser = true;
			try {
				user = (User) SecurityUtil.getPrincipal();
			} catch (Exception e) {
				isWithUser = false;
			}
			is = multipartFile.getInputStream();
			//登记上传的附件
			BaseFile bf=this.tableService.saveUpFiletoService(multipartFile, model);
			Long size=multipartFile.getSize();
			bf.setFileSize(size);
			bf.setFlowUnid(custid);
			this.tableService.updateEntity(bf);
			this.tableService.addlogFileOper(bf, "上传");
		}catch (Exception e) {
			e.printStackTrace();
			is.close();
			throw new BusinessException(e.getMessage());
		}finally{
			if(null!=is){FileUtil.safeCloseInputStream(is);}
		}
		return null;
	}
	
	
	@Override
	public Map<String, List> getSheetRowConfig(Workbook wb )throws Exception {
		//根据Sheet名称获得配置放到MAP中
		int sheetCount = wb.getNumberOfSheets();
		List<String> sheetNames = new ArrayList<String>();
		for(int i=0;i<sheetCount;i++){
			sheetNames.add(wb.getSheetName(i));
		}
		String hsql = "from FinancialTable where sheetname=?";
		Map<String, List> maptable = new HashMap<String, List>();
		List<FinancialTable> tables = null;
		for (String sheetName : sheetNames) {
			tables = null;
			tables = this.tableService.findResultsByHSQL(hsql, sheetName);
			if (tables.size() > 0) {
				maptable.put(sheetName, tables);
			}
		}
		return maptable;
	}

	@Override
	public String importFinanceDateByConfig(Workbook wb,Map<String, List> tableconfig,CustInfo cust,User user) throws Exception {
		List<FinancialTable> ftables=null;
		Sheet sheet=null;
		int startrowindex=0;
		int startcellindex=0;
		int keyRowIndex=0;
		int keyCellIndex=0;
		int skipcell=0;
		String id="";
		String otherData="";
		//获取当前时间
		String systemDate = DateUtil.getSystemDateTime();
		Row DataRow=null;
		String keyDate="";
	    Set<FinancialSubjects> finSubjects=new HashSet<FinancialSubjects>();
		Cell cell=null;
		String value = "";
		List<FinancialData> datas=new ArrayList<FinancialData>();
		//根据每个shett配置遍历配置列
		for(String sheetname:tableconfig.keySet()){
			ftables=tableconfig.get(sheetname);
			sheet=wb.getSheet(sheetname);
		    for(FinancialTable ft:ftables){
		    	keyRowIndex=ft.getKeyRowIndex()-1;//年份所在行
		    	keyCellIndex=ft.getKeyCellIndex()-1;//年份开如列
		    	skipcell=ft.getSkipcell();//调过列数
		    	finSubjects=ft.getFinancialSubjects();
		    	if(finSubjects.size()<=0){
		    		throw new BusinessException("财务报表中"+sheetname+"导入"+ft.getTitlename()+"没有配置科目");
		    	}
		    	Row keyrow = (Row)sheet.getRow(keyRowIndex);//获得年所在行
		    	for(int i=keyCellIndex;i<keyrow.getPhysicalNumberOfCells();i++){
		    		 cell = PoiExcelUtil.getCell(keyrow,i);
		    		 keyDate = "";
		    		 keyDate = PoiExcelUtil.getCellValue(cell);
		    		 keyDate=keyDate.replace("\"","\\\"") ;
					if(null!=keyDate&&(!"".equals(keyDate))){
						keyDate=this.getKeyDate(keyDate);//年转换
					     for(FinancialSubjects fs:finSubjects){
					    	 DataRow=(Row)sheet.getRow(Integer.valueOf(fs.getSubjectIndex())-1);
					    	 cell = PoiExcelUtil.getCell(DataRow,i);
					    	 value=PoiExcelUtil.getCellValue(cell);
					    	 FinancialData fd=null;
					    	 fd=this.getFinanceData(cust,fs,keyDate);//根据客户，科日和时间到数据库中查是否导过
					    	 if(null==fd){
					    	 fd=new FinancialData();
					    	 }
					 		 fd.setSubjectData(value);
					 		 fd.setFinancialSubjectsid(fs);
					 		 fd.setCreateDate(systemDate);
					 		 fd.setCustInfo(cust);
					 		 fd.setCreator(user);
					 		 fd.setFinancialDate(keyDate);
					 		 otherData="";
					 		 //如果保存跳过的列，则调过的列，的值以豆号分隔
					 		 if("是".equals(ft.getIsSaveSkipData())){
						    	for(int j=1;j<=skipcell;j++){
						    		cell = PoiExcelUtil.getCell(DataRow,i+j);
							    	value=PoiExcelUtil.getCellValue(cell);
							    	if(!"".equals(otherData)){otherData+=",";}
							    	otherData+=value;
						    	} 
						     }
					 		 fd.setSubjectOtherData(otherData);
					 		 datas.add(fd);
					     }
					    
					}
					i=i+skipcell;
		    	}
		    }
		}
		this.tableService.saveOrUpdateAllEntities(datas);
		return null;
	}

	@Override
	public String getKeyDate(String key) throws Exception {
	   String strData="";
	   if(key.indexOf("年")>0){
		   strData=key.substring(0, 4)+"-12-31"; 
	   }else{
		   strData=key.replaceAll("//", "-");
	   }
	   return strData;
	}

	@Override
	public FinancialData getFinanceData(CustInfo custinfo,FinancialSubjects fs,String financialDate ) throws Exception {
		String HSQL="From FinancialData FD where FD.custInfo=? and FD.FinancialSubjectsid=? and FD.FinancialDate=?";
		List<FinancialData> fds=new ArrayList<FinancialData>();
		fds=this.tableService.findResultsByHSQL(HSQL, custinfo,fs,financialDate);
	    if(fds.size()>0){
	    	return fds.get(0);
	    }else{
	       return null;	
	    }
	}

	@Override
	public List<Map<String, Object>> getProjectYear(Map<String, String> model) throws Exception {
		String custid = model.get("custid");
		String sql="select fd.subject_data,fd.financial_date from financial_data fd left join financial_subjects fs on fs.id=fd.fina_sub_id where fs.subject_code='item1'and fd.cust_id='"+custid+"'";
		List<Map<String, Object>> results = null;
		results = this.tableService.queryListBySql(sql);
		return results;
	}

	@Override
	public void modifyFinanceData(Map<String, String> model) throws Exception {
		List<FinancialData> dataList = new ArrayList<FinancialData>();
		String ids = model.get("data");
		System.out.println(ids);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, String>> list = objectMapper.readValue(ids, List.class);
		for(Map<String,String> map : list){
			String id = map.get("id");
			FinancialData data = new FinancialData();
			data = this.tableService.findEntityByID(FinancialData.class, id);
			data.setSubjectData(map.get("subjectdata"));
			dataList.add(data);
		}
	}
	@Override
	public void removeFinanceData(Map<String, String> model) throws Exception {
		String custid = model.get("custid");
		if(null==custid || custid.length()<0){
			return;
		}
		CustInfo custInfo = this.tableService.findEntityByID(CustInfo.class, custid);
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("custInfo",custInfo);
		List<FinancialData> list = this.tableService.findEntityByProperties(FinancialData.class, propertiesMap);
		this.tableService.removeAllEntites(list);
	}
}
