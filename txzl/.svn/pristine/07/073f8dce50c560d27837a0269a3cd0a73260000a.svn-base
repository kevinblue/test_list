package com.tenwa.leasing.serviceImpl.SaleWeekReportServiceImpl;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.cust.ChannelAdministrationDetail;
import com.tenwa.leasing.entity.cust.DepartmentBuildDetail;
import com.tenwa.leasing.entity.cust.NextWorkWeekReportDetail;
import com.tenwa.leasing.entity.cust.ProblemAdviceDetail;
import com.tenwa.leasing.entity.cust.SaleWeekReport;
import com.tenwa.leasing.entity.cust.SaleWeekReportDetail;
import com.tenwa.leasing.entity.cust.WorkWeekReport;
import com.tenwa.leasing.entity.cust.WorkWeekReportDetail;
import com.tenwa.leasing.service.SaleWeekReportService.SaleWeekReportService;
import com.tenwa.leasing.service.workweekreport.WorkWeekReportService;


@Service(value = "saleWeekReportService")
public class SaleWeekReportServiceImpl implements SaleWeekReportService {
	@Resource(name = "tableService")
	private TableService tableService;			
	
	public synchronized String createCalNum() throws Exception {
		try{
			
			String year=DateUtil.getSystemDate().substring(0, 4);
			String month=DateUtil.getSystemDate().substring(5, 7);
			String day=DateUtil.getSystemDate().substring(8, 10);
			List<Map<String,Object>> numberlist=this.tableService.queryListBySql("select * from T_SERIAL_NUMBER where type_='工作周报流水号' ");
			if(numberlist!=null&&numberlist.size()>0){
				if(new BigDecimal(year).compareTo(new BigDecimal(numberlist.get(0).get("year_").toString()))!=0||new BigDecimal(month).compareTo(new BigDecimal(numberlist.get(0).get("month_").toString()))!=0){
					this.tableService.updateBySql("update T_SERIAL_NUMBER set year_=?, month_=?, order_number_=? where  type_='工作周报流水号' ", Integer.parseInt(year),Integer.parseInt(month),000001);
					return year+month+day+"000001";
				}else{
					BigDecimal number=new BigDecimal(numberlist.get(0).get("order_number_").toString()).add(BigDecimal.ONE) ;
					String format=new DecimalFormat("000000").format(number);
					this.tableService.updateBySql("update T_SERIAL_NUMBER set  order_number_=? where  type_='工作周报流水号' ", Integer.parseInt(format));
					return year+month+day+format;
				}
			}else{
				this.tableService.updateBySql("insert into T_SERIAL_NUMBER ( id_,type_,year_,month_,order_number_) values(sys_guid(),'工作周报流水号',?,?,?)", Integer.parseInt(year),Integer.parseInt(month),000001);
				return year+month+day+"000001";
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public void addSaleWeekReportApplication(Map<String, String> variablesMap)
			throws Exception {
		SaleWeekReport work=new SaleWeekReport();
		User user = SecurityUtil.getPrincipal();
		work.setRegisterid(user);
		work.setWeekstyle("草稿");
		work.setSerialid(createCalNum());
		work.setStartDate(variablesMap.get("startdate"));
		work.setEndDate(variablesMap.get("enddate"));
		this.tableService.saveEntity(work);

	}

/*	@Override
	public void updateWorkWeekReportApplication(Map<String, String> variablesMap)
			throws Exception {
		String jsonweek=variablesMap.get("workweek");
		WorkWeekReport we=new WorkWeekReport();
		JSONArray week = new JSONArray(jsonweek);
		for(int i=0;i<week.length();i++){
			WorkWeekReportDetail workweek=new WorkWeekReportDetail();
			JSONObject weekobj = week.optJSONObject(i);		
			if(" ".equals(weekobj.getString("weekone"))&&" ".equals(weekobj.getString("weektwo"))&&" ".equals(weekobj.getString("weekthree"))&&" ".equals(weekobj.getString("weekfour"))&&" ".equals(weekobj.getString("weekfive"))){
				break;
			}else if(weekobj.has("twid")&&!" ".equals(weekobj.getString("twid"))){
				workweek=this.tableService.findEntityByID(WorkWeekReportDetail.class,weekobj.getString("twid"));				
			}else{
				we=this.tableService.findEntityByID(WorkWeekReport.class, variablesMap.get("workid"));
				workweek.setWorkweekid(we);
			}
				workweek.setWeekone(weekobj.getString("weekone").replace('\n', ' '));
				workweek.setWeektwo(weekobj.getString("weektwo").replace('\n', ' '));
				workweek.setWeekthree(weekobj.getString("weekthree").replace('\n', ' '));
				workweek.setWeekfour(weekobj.getString("weekfour").replace('\n', ' '));
				workweek.setWeekfive(weekobj.getString("weekfive").replace('\n', ' '));	
				this.tableService.saveOrUpdateEntity(workweek);
		}
		String jsonweekone=variablesMap.get("workweekone");
		JSONArray weekone = new JSONArray(jsonweekone);
		for(int i=0;i<weekone.length();i++){
			NextWorkWeekReportDetail workweekone=new NextWorkWeekReportDetail();
			JSONObject weekoneobj = weekone.optJSONObject(i);
			if(" ".equals(weekoneobj.getString("nextweekone"))&&" ".equals(weekoneobj.getString("nextweektwo"))&&" ".equals(weekoneobj.getString("nextweekthree"))&&" ".equals(weekoneobj.getString("nextweekfour"))&&" ".equals(weekoneobj.getString("nextweekfive"))){
				break;
			}else if(weekoneobj.has("tnid")&&!" ".equals(weekoneobj.getString("tnid"))){
				workweekone=this.tableService.findEntityByID(NextWorkWeekReportDetail.class,weekoneobj.getString("tnid"));								
			}else{
				we=this.tableService.findEntityByID(WorkWeekReport.class, variablesMap.get("workid"));
				workweekone.setWorkweekid(we);
			}
			workweekone.setNextweekone(weekoneobj.getString("nextweekone").replace('\n', ' '));
			workweekone.setNextweektwo(weekoneobj.getString("nextweektwo").replace('\n', ' '));
			workweekone.setNextweekthree(weekoneobj.getString("nextweekthree").replace('\n', ' '));
			workweekone.setNextweekfour(weekoneobj.getString("nextweekfour").replace('\n', ' '));
			workweekone.setNextweekfive(weekoneobj.getString("nextweekfive").replace('\n', ' '));
			this.tableService.saveOrUpdateEntity(workweekone);
		}
	}

	@Override
	public void deleteWorkWeekReportDetailApplication(
			Map<String, String> variablesMap) throws Exception {
		String id=variablesMap.get("twid");
		WorkWeekReportDetail week=this.tableService.findEntityByID(WorkWeekReportDetail.class, id);
		this.tableService.removeEntity(week);
		
	}

	@Override
	public void deleteNextWorkWeekReportDetailApplication(
			Map<String, String> variablesMap) throws Exception {
		String id=variablesMap.get("tnid");
		NextWorkWeekReportDetail nxweek=this.tableService.findEntityByID(NextWorkWeekReportDetail.class, id);
		this.tableService.removeEntity(nxweek);
		
	}
*/

	@Override
	public void deleteSaleWeekReport(Map<String, String> variablesMap)
			throws Exception {
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		String id=variablesMap.get("workid");
		SaleWeekReport week=this.tableService.findEntityByID(SaleWeekReport.class, id);
		queryMainObjectMap.put("saleweekid", week);
		List<SaleWeekReportDetail> worklist=new ArrayList<SaleWeekReportDetail>();
		List<DepartmentBuildDetail> departmentlist=new ArrayList<DepartmentBuildDetail>();
		List<ProblemAdviceDetail> problemtlist=new ArrayList<ProblemAdviceDetail>();
		List<ChannelAdministrationDetail> channellist=new ArrayList<ChannelAdministrationDetail>();
		departmentlist=this.tableService.findEntityByProperties(DepartmentBuildDetail.class, queryMainObjectMap);
		problemtlist=this.tableService.findEntityByProperties(ProblemAdviceDetail.class, queryMainObjectMap);
		channellist=this.tableService.findEntityByProperties(ChannelAdministrationDetail.class, queryMainObjectMap);
		worklist=this.tableService.findEntityByProperties(SaleWeekReportDetail.class, queryMainObjectMap);
		this.tableService.removeAllEntites(worklist);
		this.tableService.removeEntity(week);
		this.tableService.removeAllEntites(departmentlist);
		this.tableService.removeAllEntites(problemtlist);
		this.tableService.removeAllEntites(channellist);
		
	}


	@Override
	public void updateSaleWeekReport(Map<String, String> variablesMap) throws DataAccessException, Exception{
		String id=variablesMap.get("workid");
		SaleWeekReport salework=this.tableService.findEntityByID(SaleWeekReport.class, id);
		salework.setWeekstyle("已完成");
		this.tableService.updateEntity(salework);
	}
	
}
