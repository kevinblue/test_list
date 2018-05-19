package com.reckon.bean;

import java.math.BigDecimal;


import com.reckon.util.DateUtils;
import com.tenwa.exception.BusinessException;

public class FundRentPlanIrr implements Comparable<FundRentPlanIrr> {
	private String rentList;
	private String rent;
	private String businesscorpus;
	private String businessinterest;
	private String plandate;
	private String yearrate;
	public String getRentList() {
		return rentList;
	}
	public void setRentList(String rentList) {
		this.rentList = rentList;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getBusinesscorpus() {
		return businesscorpus;
	}
	public void setBusinesscorpus(String businesscorpus) {
		this.businesscorpus = businesscorpus;
	}
	public String getBusinessinterest() {
		return businessinterest;
	}
	public void setBusinessinterest(String businessinterest) {
		this.businessinterest = businessinterest;
	}
	public String getPlandate() {
		return plandate;
	}
	public void setPlandate(String plandate) {
		this.plandate = plandate;
	}
	public String getYearrate() {
		return yearrate;
	}
	public void setYearrate(String yearrate) {
		this.yearrate = yearrate;
	}
	public static void checkIrrFundRentPlan(FundRentPlanIrr irrBean,int index) throws Exception{
		index++;
		String intRegex = "[\\+]?\\d+";
		String pmoneyRegex = "[-\\+]?\\d+(\\.[0-9]{1,2})?";
		String rateRegex = "[-\\+]?\\d+(\\.[0-9]{1,6})?";
		if(irrBean.getRentList() == null ){
			throw new BusinessException("第"+index+"行的期项不能为空！");
		}else if(!irrBean.getRentList().matches(intRegex)){
			throw new BusinessException("第"+index+"行的期项格式不正确！");
		}else if (irrBean.getRent() == null){
			throw new BusinessException("第"+index+"行的租金不能为空！");
		}else if(!irrBean.getRent().matches(pmoneyRegex)){
			throw new BusinessException("第"+index+"行的租金格式格式不正确！正确格式为小数点后保留俩位！");
		}else if(irrBean.getBusinesscorpus() == null){
			throw new BusinessException("第"+index+"行的本金不能为空！");
		}else if(!irrBean.getBusinesscorpus().matches(pmoneyRegex)){
			throw new BusinessException("第"+index+"行的本金格式格式不正确！正确格式为小数点后保留俩位！");
		}else if(irrBean.getBusinessinterest() == null){
			throw new BusinessException("第"+index+"行的利息不能为空！");
		}else if(!irrBean.getBusinessinterest().matches(pmoneyRegex)){
			throw new BusinessException("第"+index+"行的利息格式格式不正确！正确格式为小数点后保留俩位！");
		}else if(irrBean.getPlandate() == null){
			throw new BusinessException("第"+index+"行的计划日期不能为空！");
		}else if(!DateUtils.isValidDate(irrBean.getPlandate())){
			throw new BusinessException("第"+index+"行的计划日期格式不正确！正确格式为yyyy-MM-dd！");
		}else if(irrBean.getYearrate() != null && !irrBean.getYearrate().matches(rateRegex)){
			throw new BusinessException("第"+index+"行的年利率格式不正确！正确格式为小数点后最多保留六位！");
		}else if(new BigDecimal(irrBean.getRent()).compareTo(new BigDecimal(irrBean.getBusinesscorpus()).add(new BigDecimal(irrBean.getBusinessinterest()))) != 0 ){
			throw new BusinessException("第"+index+"行的租金不等于本金+利息，请核对后在进行上传！");
		}
	}
	
	@Override
	public int compareTo(FundRentPlanIrr o) {
		if(Integer.parseInt(this.getRentList()) > Integer.parseInt(o.getRentList())){
			return 1;
		}else{
			return -1;
		}
	}
}
