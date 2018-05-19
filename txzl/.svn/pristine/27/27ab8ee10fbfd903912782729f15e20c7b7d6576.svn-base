package com.reckon.entity.contract.reckon.condition;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.reckon.bean.Condition;
import com.reckon.bean.FundPlanBean;
import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

@Entity
@FieldName(name = "投放计划")
@Table(name = "FUND_PUT_PLAN")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundPutPlan implements Comparable<FundPutPlan>{

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="流程编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name = "合同编号")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "项目编号")
	@OneToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name = "已投放金额")
	@Column(name="fact_money", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal factmoney;
	
	@FieldName(name = "已投放金额/2")
	@Column(name="change_factmoney", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal changefactmoney;
	
	@FieldName(name = "期次")
	@Column(name = "PAYMENT_ID")
	private Integer paymentId;
	
	@FieldName(name="备注")
	@Column(name="FPNOTE")
	private String   fpnote;
		
	@ManyToOne
	@FieldName(name="类型")
	@JoinColumn(name="FUND_TYPE")
	private DictionaryData fundType;
	
	@ManyToOne
	@FieldName(name = "设备名称")
	@JoinColumn(name = "DEVICE_NAME")
	private DictionaryData devicename;
	
	@FieldName(name = "计划投放日期")
	@Column(name="PLAN_DATE")
	private String planDate;
	
	@FieldName(name = "实际计划投放日期")
	@Column(name="change_factdate")
	private String changefactdate;
	
	@FieldName(name = "投放金额")
	@Column(name="PLAN_MONEY", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal planMoney;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;
  
	
	public String getChangefactdate() {
		return changefactdate;
	}

	public void setChangefactdate(String changefactdate) {
		this.changefactdate = changefactdate;
	}

	public BigDecimal getChangefactmoney() {
		return changefactmoney;
	}

	public void setChangefactmoney(BigDecimal changefactmoney) {
		this.changefactmoney = changefactmoney;
	}

	public BigDecimal getFactmoney() {
		return factmoney;
	}

	public void setFactmoney(BigDecimal factmoney) {
		this.factmoney = factmoney;
	}

	public String getFpnote() {
		return fpnote;
	}

	public void setFpnote(String fpnote) {
		this.fpnote = fpnote;
	}

	public DictionaryData getDevicename() {
		return devicename;
	}

	public void setDevicename(DictionaryData devicename) {
		this.devicename = devicename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}


	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public DictionaryData getFundType() {
		return fundType;
	}

	public void setFundType(DictionaryData fundType) {
		this.fundType = fundType;
	}

	public BigDecimal getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
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
	public int compareTo(FundPutPlan o) {
		try {
			if(o.getPaymentId() > this.getPaymentId()){
				return -1;
			}else if(o.getPaymentId() < this.getPaymentId()){
				return 1;
			}else{
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	
}
