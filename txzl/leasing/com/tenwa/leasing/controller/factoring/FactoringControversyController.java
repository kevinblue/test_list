package com.tenwa.leasing.controller.factoring;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;

@Controller(value = "FactoringControversyController")
@RequestMapping(value = "/**/acl")

public class FactoringControversyController {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@RequestMapping(value = "/getApplicationRunningWater.acl")
	@ResponseBody
	public String getApplicationRunningWater(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
             String number="BL"+createCalNum();
			return number;
		}catch (Exception e) {
			return "调用失败!["+e.getMessage()+"]";
		}
	}
	
	public synchronized String createCalNum() throws Exception {
		try{
			String year=DateUtil.getSystemDate().substring(0, 4);
			String month=DateUtil.getSystemDate().substring(5, 7);
			String day=DateUtil.getSystemDate().substring(8, 10);
			List<Map<String,Object>> numberlist=this.tableService.queryListBySql("select * from T_SERIAL_NUMBER where type_='保理争议申请流水号' ");
			if(numberlist!=null&&numberlist.size()>0){
				if(new BigDecimal(year).compareTo(new BigDecimal(numberlist.get(0).get("year_").toString()))!=0||new BigDecimal(month).compareTo(new BigDecimal(numberlist.get(0).get("month_").toString()))!=0){
					this.tableService.updateBySql("update T_SERIAL_NUMBER set year_=?, month_=?, order_number_=? where type_='保理争议申请流水号' ", Integer.parseInt(year),Integer.parseInt(month),001);
					return "ZY-"+year+month+day+"-"+"001";
				}else{
					BigDecimal number=new BigDecimal(numberlist.get(0).get("order_number_").toString()).add(BigDecimal.ONE) ;
					String format=new DecimalFormat("000").format(number);
					this.tableService.updateBySql("update T_SERIAL_NUMBER set order_number_=? where type_='保理争议申请流水号' ", Integer.parseInt(format));
					return "ZY-"+year+month+day+"-"+format;
				}
			}else{
				this.tableService.updateBySql("insert into T_SERIAL_NUMBER ( id_,type_,year_,month_,order_number_) values(sys_guid(),'保理争议申请流水号',?,?,?)", Integer.parseInt(year),Integer.parseInt(month),001);
				return "ZY-"+year+month+day+"-"+"001";
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	@RequestMapping(value = "/getPenaltyRunningWater.acl")
	@ResponseBody
	public String getPenaltyRunningWater(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
             String number="FXTZS-"+createNum();
			return number;
		}catch (Exception e) {
			return "调用失败!["+e.getMessage()+"]";
		}
	}
	public synchronized String createNum() throws Exception {
		try{
			String year=DateUtil.getSystemDate().substring(0, 4);
			String month=DateUtil.getSystemDate().substring(5, 7);
			String day=DateUtil.getSystemDate().substring(8, 10);
			List<Map<String,Object>> numberlist=this.tableService.queryListBySql("select * from T_SERIAL_NUMBER where type_='罚息通知书编号' ");
			if(numberlist!=null&&numberlist.size()>0){
				if(new BigDecimal(year).compareTo(new BigDecimal(numberlist.get(0).get("year_").toString()))!=0||new BigDecimal(month).compareTo(new BigDecimal(numberlist.get(0).get("month_").toString()))!=0){
					this.tableService.updateBySql("update T_SERIAL_NUMBER set year_=?, month_=?, order_number_=? where type_='罚息通知书编号' ", Integer.parseInt(year),Integer.parseInt(month),001);
					return year+"001";
				}else{
					BigDecimal number=new BigDecimal(numberlist.get(0).get("order_number_").toString()).add(BigDecimal.ONE) ;
					String format=new DecimalFormat("000").format(number);
					this.tableService.updateBySql("update T_SERIAL_NUMBER set order_number_=? where type_='罚息通知书编号' ", Integer.parseInt(format));
					return year+format;
				}
			}else{
				this.tableService.updateBySql("insert into T_SERIAL_NUMBER ( id_,type_,year_,month_,order_number_) values(sys_guid(),'罚息通知书编号',?,?,?)", Integer.parseInt(year),Integer.parseInt(month),001);
				return year+"001";
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
}
