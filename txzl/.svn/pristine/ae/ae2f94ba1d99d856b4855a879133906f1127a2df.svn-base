package com.tenwa.leasing.entity.contract;


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

@Entity
@FieldName(name = "整体经营情况")
@Table(name = "CONTRACT_INVEST_INFO")
public class ContractInvestInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name = "标识符")
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractPatrolInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PATROLINFO_ID")
	private ContractPatrolInfo contractPatrolInfo;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_ID")
	@FieldName(name="承租人")
	private CustInfo custInfo;
	
	@FieldName(name="评估时/前次巡视[医疗药品年收入（万元）]")
	@Column(name="assetmng", length=200)	
	private String yxmoney;
	
	@FieldName(name="本次巡视[医疗药品年收入（万元）]")
	@Column(name="ycurmoney", length=200)	
	private String ycurmoney;
	
	@FieldName(name="情况说明/备注[医疗药品年收入（万元）]")
	@Column(name="ymemo", length=4000)	
	private String ymemo;
	
	
	@FieldName(name="评估时/前次巡视[年住院量（人次）]")
	@Column(name="zynum", length=200)	
	private String zynum;
	
	@FieldName(name="本次巡视[年住院量（人次）]")
	@Column(name="zycurnum", length=200)	
	private String zycurnum;
	
	@FieldName(name="情况说明/备注[年住院量（人次）]")
	@Column(name="zymemo", length=4000)	
	private String zymemo;
	
	
	
	@FieldName(name="评估时/前次巡视[年门诊量（人次）]")
	@Column(name="mznum", length=200)	
	private String mznum;
	
	@FieldName(name="本次巡视[年门诊量（人次）]")
	@Column(name="mzcurnum", length=200)	
	private String mzcurnum;
	
	@FieldName(name="情况说明/备注[年门诊量（人次））]")
	@Column(name="mzmemo", length=4000)	
	private String mzmemo;
	
	@FieldName(name="评估时/前次巡视[应收医疗款（万元）]")
	@Column(name="ylsnum", length=200)	
	private String ylsnum;
	
	@FieldName(name="本次巡视[年门诊量（人次）]")
	@Column(name="ylscurnum", length=200)	
	private String ylscurnum;
	
	@FieldName(name="情况说明/备注[年门诊量（人次））]")
	@Column(name="ylsmemo", length=4000)	
	private String ylsmemo;
	
	//*******************************非医疗的部分字段**************************************
	
	@FieldName(name="评估时/前次巡视[年销售收入（万元）]")
	@Column(name="fsrmoney", length=200)	
	private String fsrmoney;
	
	@FieldName(name="本次巡视[年销售收入（万元）]")
	@Column(name="fsrcurmoney", length=200)	
	private String fsrcurmoney;
	
	@FieldName(name="情况说明/备注[年销售收入（万元）]")
	@Column(name="fsrmemo", length=4000)	
	private String fsrmemo;
	
	@FieldName(name="评估时/前次巡视[库存（千元）]")
	@Column(name="kcmoney", length=200)	
	private String kcmoney;
	
	@FieldName(name="本次巡视[库存（千元）]")
	@Column(name="kccurmoney", length=200)	
	private String kccurmoney;
	
	@FieldName(name="情况说明/备注[库存（千元）]")
	@Column(name="kcmemo", length=4000)	
	private String kcmemo;
	
	
	@FieldName(name="评估时/前次巡视[应收账款（千元）]")
	@Column(name="ysmoney", length=200)	
	private String ysmoney;
	
	@FieldName(name="本次巡视[应收账款（千元）]")
	@Column(name="yscurmoney", length=200)	
	private String yscurmoney;
	
	@FieldName(name="情况说明/备注[应收账款（千元）]")
	@Column(name="ysmemo", length=4000)	
	private String ysmemo;
	
	
	@FieldName(name="评估时/前次巡视[应付账款（千元）]")
	@Column(name="yfmoney", length=200)	
	private String yfmoney;
	
	@FieldName(name="本次巡视[应付账款（千元）]")
	@Column(name="yfcurmoney", length=200)	
	private String yfcurmoney;
	
	@FieldName(name="情况说明/备注[应付账款（千元）]")
	@Column(name="yfmemo", length=4000)	
	private String yfmemo;
	
	@FieldName(name="评估时/前次巡视[其他应收账款（千元）]")
	@Column(name="othermoney", length=200)	
	private String othermoney;
	
	@FieldName(name="本次巡视[其他应收账款（千元）]")
	@Column(name="othercurmoney", length=200)	
	private String othercurmoney;
	
	@FieldName(name="情况说明/备注[其他应收账款（千元）]")
	@Column(name="othermemo", length=4000)	
	private String othermemo;
	
	//**************************公用字段******************************************
	@FieldName(name="资产负债率")
	@Column(name="debtlv", length=400)	
	private String debtlv;
	
	@FieldName(name="流动比率")
	@Column(name="cashlv", length=400)	
	private String cashlv;
	
	@FieldName(name="小结分析")
	@Column(name="investmemo", length=400)	
	private String investmemo;
	
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

	public ContractPatrolInfo getContractPatrolInfo() {
		return contractPatrolInfo;
	}

	public void setContractPatrolInfo(ContractPatrolInfo contractPatrolInfo) {
		this.contractPatrolInfo = contractPatrolInfo;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getYxmoney() {
		return yxmoney;
	}

	public void setYxmoney(String yxmoney) {
		this.yxmoney = yxmoney;
	}

	public String getYcurmoney() {
		return ycurmoney;
	}

	public void setYcurmoney(String ycurmoney) {
		this.ycurmoney = ycurmoney;
	}

	public String getYmemo() {
		return ymemo;
	}

	public void setYmemo(String ymemo) {
		this.ymemo = ymemo;
	}

	public String getZynum() {
		return zynum;
	}

	public void setZynum(String zynum) {
		this.zynum = zynum;
	}

	public String getZycurnum() {
		return zycurnum;
	}

	public void setZycurnum(String zycurnum) {
		this.zycurnum = zycurnum;
	}

	public String getZymemo() {
		return zymemo;
	}

	public void setZymemo(String zymemo) {
		this.zymemo = zymemo;
	}

	public String getMznum() {
		return mznum;
	}

	public void setMznum(String mznum) {
		this.mznum = mznum;
	}

	public String getMzcurnum() {
		return mzcurnum;
	}

	public void setMzcurnum(String mzcurnum) {
		this.mzcurnum = mzcurnum;
	}

	public String getMzmemo() {
		return mzmemo;
	}

	public void setMzmemo(String mzmemo) {
		this.mzmemo = mzmemo;
	}

	public String getYlsnum() {
		return ylsnum;
	}

	public void setYlsnum(String ylsnum) {
		this.ylsnum = ylsnum;
	}

	public String getYlscurnum() {
		return ylscurnum;
	}

	public void setYlscurnum(String ylscurnum) {
		this.ylscurnum = ylscurnum;
	}

	public String getYlsmemo() {
		return ylsmemo;
	}

	public void setYlsmemo(String ylsmemo) {
		this.ylsmemo = ylsmemo;
	}

	public String getFsrmoney() {
		return fsrmoney;
	}

	public void setFsrmoney(String fsrmoney) {
		this.fsrmoney = fsrmoney;
	}

	public String getFsrcurmoney() {
		return fsrcurmoney;
	}

	public void setFsrcurmoney(String fsrcurmoney) {
		this.fsrcurmoney = fsrcurmoney;
	}

	public String getFsrmemo() {
		return fsrmemo;
	}

	public void setFsrmemo(String fsrmemo) {
		this.fsrmemo = fsrmemo;
	}

	public String getKcmoney() {
		return kcmoney;
	}

	public void setKcmoney(String kcmoney) {
		this.kcmoney = kcmoney;
	}

	public String getKccurmoney() {
		return kccurmoney;
	}

	public void setKccurmoney(String kccurmoney) {
		this.kccurmoney = kccurmoney;
	}

	public String getKcmemo() {
		return kcmemo;
	}

	public void setKcmemo(String kcmemo) {
		this.kcmemo = kcmemo;
	}

	public String getYsmoney() {
		return ysmoney;
	}

	public void setYsmoney(String ysmoney) {
		this.ysmoney = ysmoney;
	}

	public String getYscurmoney() {
		return yscurmoney;
	}

	public void setYscurmoney(String yscurmoney) {
		this.yscurmoney = yscurmoney;
	}

	public String getYsmemo() {
		return ysmemo;
	}

	public void setYsmemo(String ysmemo) {
		this.ysmemo = ysmemo;
	}

	public String getYfmoney() {
		return yfmoney;
	}

	public void setYfmoney(String yfmoney) {
		this.yfmoney = yfmoney;
	}

	public String getYfcurmoney() {
		return yfcurmoney;
	}

	public void setYfcurmoney(String yfcurmoney) {
		this.yfcurmoney = yfcurmoney;
	}

	public String getYfmemo() {
		return yfmemo;
	}

	public void setYfmemo(String yfmemo) {
		this.yfmemo = yfmemo;
	}

	public String getOthermoney() {
		return othermoney;
	}

	public void setOthermoney(String othermoney) {
		this.othermoney = othermoney;
	}

	public String getOthercurmoney() {
		return othercurmoney;
	}

	public void setOthercurmoney(String othercurmoney) {
		this.othercurmoney = othercurmoney;
	}

	public String getOthermemo() {
		return othermemo;
	}

	public void setOthermemo(String othermemo) {
		this.othermemo = othermemo;
	}

	public String getDebtlv() {
		return debtlv;
	}

	public void setDebtlv(String debtlv) {
		this.debtlv = debtlv;
	}

	public String getCashlv() {
		return cashlv;
	}

	public void setCashlv(String cashlv) {
		this.cashlv = cashlv;
	}

	public String getInvestmemo() {
		return investmemo;
	}

	public void setInvestmemo(String investmemo) {
		this.investmemo = investmemo;
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

	public CustInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfo custInfo) {
		this.custInfo = custInfo;
	}
	
}
