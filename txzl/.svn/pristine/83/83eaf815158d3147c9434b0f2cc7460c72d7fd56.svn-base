package com.tenwa.leasing.entity.assetnetworkmonitor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;

/**
 * FundEbankProcess entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "资产网络监控申请详细")
@Table(name="ASSET_NET_MONITOR_APPLY_DETAIL")
public class AssetNetMonitorApplyDetail {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="申请ID")
	@ManyToOne(targetEntity=AssetNetMonitorApply.class,fetch=FetchType.LAZY)
	@JoinColumn(name="APPLY_ID")
	private AssetNetMonitorApply applyid ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_ID")
	@FieldName(name="客户ID")
	private CustInfo custInfo;
	
	@FieldName(name="客户名称")
	@Column(name="CUST_NAME")
	private String custname ;
	
	@FieldName(name = "组织机构代码/身份证号码")
	@Column(name="CODE")
	private String code;
	
	@FieldName(name = "百度")
	@Column(name="BAIDU",length =4000)
	private String baidu;

	@FieldName(name="被执行人网")
	@Column(name="EXECUTION_NET",length =4000)
	private String executionnet;
	
	@FieldName(name = "裁判文书网")
	@Column(name="JUDGMENTNET",length =4000)
	private String judgmentnet;

	@FieldName(name="负面事件管控措施")
	@Column(name="NEGATIVE_CONTROL",length =4000)
	private String negativecontrol ;

	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AssetNetMonitorApply getApplyid() {
		return applyid;
	}

	public void setApplyid(AssetNetMonitorApply applyid) {
		this.applyid = applyid;
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

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBaidu() {
		return baidu;
	}

	public void setBaidu(String baidu) {
		this.baidu = baidu;
	}

	public String getExecutionnet() {
		return executionnet;
	}

	public void setExecutionnet(String executionnet) {
		this.executionnet = executionnet;
	}

	public String getJudgmentnet() {
		return judgmentnet;
	}

	public void setJudgmentnet(String judgmentnet) {
		this.judgmentnet = judgmentnet;
	}

	public String getNegativecontrol() {
		return negativecontrol;
	}

	public void setNegativecontrol(String negativecontrol) {
		this.negativecontrol = negativecontrol;
	}

	public CustInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfo custInfo) {
		this.custInfo = custInfo;
	}
	
}