package com.reckon.bean;

import java.math.BigDecimal;
/**
 * <p>已知租金构建现金流算IRR做本利金拆分的ENTITY。</p>
 * <p>2014-3-10</p>
 * @author sea
 * @version 4.5
 */
public class KnowingRentBean {

	private Integer startlist = 0;//开始期项
	private Integer endlist = 0;//截止期项
	private String rent;//对应租金值
	private BigDecimal countrent;//总租金值
	
	/**
	  * <p>GET开始期项。</p>
	  * @author sea
	  * @return
	 */
	public Integer getStartlist() {
		return startlist;
	}
	
	/**
	 * <p>SET开始期项。</p>
	 * @author sea
	 * @return
	 */
	public void setStartlist(Integer startlist) {
		this.startlist = startlist;
	}
	
	/**
	 * <p>GET截止期项。</p>
	 * @author sea
	 * @return
	 */
	public Integer getEndlist() {
		return endlist;
	}
	
	/**
	 * <p>SET截止期项。</p>
	 * @author sea
	 * @return
	 */
	public void setEndlist(Integer endlist) {
		this.endlist = endlist;
	}
	
	/**
	 * <p>GE对应租金值T。</p>
	 * @author sea
	 * @return
	 */
	public String getRent() {
		return rent;
	}
	
	/**
	 * <p>SET对应租金值。</p>
	 * @author sea
	 * @return
	 */
	public void setRent(String rent) {
		this.rent = rent;
	}
	
	/**
	 * <p>GET总租金值。</p>
	 * @author sea
	 * @return
	 */
	public BigDecimal getCountrent() {
		return countrent;
	}
	
	/**
	 * <p>SET总租金值。</p>
	 * @author sea
	 * @return
	 */
	public void setCountrent(BigDecimal countrent) {
		this.countrent = countrent;
	}
	
}
