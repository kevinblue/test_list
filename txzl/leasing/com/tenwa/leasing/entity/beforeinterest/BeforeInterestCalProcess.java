package com.tenwa.leasing.entity.beforeinterest;



import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.reckon.commons.helper.Scale;
import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author liao bo 
 * @date 2016-3-7下午03:26:04
 * @info 租前息计算过程存储表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "租前息计算过程存储表")
@Table(name="BEFORE_INTEREST_CALPROCESS")
public class BeforeInterestCalProcess  implements Comparable<BeforeInterestCalProcess>{

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@FieldName(name="项目号")
	@Column(name="PROJ_ID")
	private String projId;
	
	@FieldName(name="合同编号")
	@Column(name="CONTRACT_ID")
	private String contractId ;
	
	@FieldName(name="报价编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name="客户初始报价测算编号")
	@Column(name="QUOT_ID")
	@Deprecated
	private String quotId;
	
	
	@FieldName(name="投放期此")
	@Column(name="LIST")
	private Integer list;
	
	@FieldName(name="投放日")
	@Column(name="FUND_PUT_DATE",length=20)
	private String  fundPutDate;
	
	@FieldName(name="投放金额")
	@Column(name="MONEY",precision=22,scale=2)
	private BigDecimal money;
	
	@FieldName(name="起息日")
	@Column(name="START_DATE",length=20)
	private String  startDate;
	
	@FieldName(name="结息日")
	@Column(name="END_DATE",length=20)
	private String  endDate;
	
	@FieldName(name="计息天数")
	@Column(name="DAYS")
	private Integer days;
	
	@FieldName(name="计息年利率")
	@Column(name="YEAR_RATE", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal yearRate;
	
	@FieldName(name="租前息")
	@Column(name="INTEREST",precision=22,scale=2)
	private BigDecimal Interest;

	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getQuotId() {
		return quotId;
	}

	public void setQuotId(String quotId) {
		this.quotId = quotId;
	}

	public Integer getList() {
		return list;
	}

	public void setList(Integer list) {
		this.list = list;
	}

	public String getFundPutDate() {
		return fundPutDate;
	}

	public void setFundPutDate(String fundPutDate) {
		this.fundPutDate = fundPutDate;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public BigDecimal getInterest() {
		return Interest;
	}

	public void setInterest(BigDecimal interest) {
		Interest = interest;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public int compareTo(BeforeInterestCalProcess o) {try {
		long dateDiff = DateTools.getDateDiff(o.getEndDate(),this.getEndDate());
		if(dateDiff > 0){
			return 1;
		} else if(dateDiff < 0){
			return -1;
		} else {
			return 0;
		}
	} catch (Exception e) {
		return 0;
	}}

	
}
