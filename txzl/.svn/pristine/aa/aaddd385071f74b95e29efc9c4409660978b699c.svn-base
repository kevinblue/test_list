package com.reckon.bean;

import java.util.Date;

import com.tenwa.kernal.utils.DateUtil;

/**
 * 用于保证金抵扣逻辑中的倒退抵扣算法
 * @author ykl
 *
 */
public class CashDetailTemp implements Comparable{
	String planDate;
	String planmoney;//租金金额
	String paytye;
	String feetype;
	String feetypename;
	String handchargemoney="0";//包含的手续费的金额

	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	public String getFeetypename() {
		return feetypename;
	}

	public void setFeetypename(String feetypename) {
		this.feetypename = feetypename;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getPlanmoney() {
		return planmoney;
	}

	public void setPlanmoney(String planmoney) {
		this.planmoney = planmoney;
	}

	public String getPaytye() {
		return paytye;
	}

	public void setPaytye(String paytye) {
		this.paytye = paytye;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof CashDetailsBean){
			CashDetailsBean s = (CashDetailsBean) o;
			Date thisDate = DateUtil.getTimeByFormat(this.getPlanDate(), "yyyy-MM");
			Date compareDate = DateUtil.getTimeByFormat(s.getPlanDate(), "yyyy-MM");
			if(thisDate.getTime() - compareDate.getTime() > 0 ){
				return 1 ;
			}else if(thisDate.getTime() - compareDate.getTime() == 0 ){
				return 0;
			}else{
				return -1;
			}
		}else{
			throw new RuntimeException("不同对象之间无法比较");
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj)  
		return true;  
		if((obj == null) || (obj.getClass() != this.getClass()))  
		return false;  
		CashDetailTemp test = (CashDetailTemp)obj;  
		return test.getPlanDate().equals(this.getPlanDate());
	}

	public String getHandchargemoney() {
		return handchargemoney;
	}

	public void setHandchargemoney(String handchargemoney) {
		this.handchargemoney = handchargemoney;
	}

}
