package com.tenwa.leasing.entity.lawmng;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.filingmng.LawFilingNotice;

@Entity
@Table(name = "LAW_APPROVAL")
@FieldName(name="法务申请流程信息 【url: workflow/forms/law_mng/law_approval/law_approval_open_list.bi】")
public class LawApproval implements java.io.Serializable {

	private static final long serialVersionUID = 3007301093288854797L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACTINFO_ID")
	@FieldName(name="合同信息主键  id")
	private ContractInfo contractInfo;
	
	@Column(name = "LAWNUM", length =100)
	@FieldName(name="法务编号")
	private String lawnum;
	

	@Column(name = "LAWMEMO",length = 4000)
	@FieldName(name="法务处理说明")
	private String lawmemo;
	
	@Column(name = "JALAWTYPE", length = 50)
	@FieldName(name="结案类型")
	private String jalawtype;
	
	@Column(name = "CLOSEINFO",length = 100)
	@FieldName(name="结案信息")
	private String closeinfo;

	@Column(name = "CLOSEMONEY",length = 100)
	@FieldName(name="回收金额")
	private String closemoney;
	
	@Column(name = "CLOSEDATE",length = 100)
	@FieldName(name="结案时间")
	private String closedate;
	
	@Column(name = "CLOSEMEMO",length = 4000)
	@FieldName(name="结案法务处理说明")
	private String closememo;
	
	@Column(name = "detailmemo",length = 4000)
	@FieldName(name="情况说明")
	private String detailmemo;
	
	@Column(name = "IS_PRESERVE",length = 100)
	@FieldName(name="是否保全")
	private String ispreserve;
	
	@Column(name = "IS_PRINTFILE",length = 100)
	@FieldName(name="是否上传打印文件")
	private String isprintfile;
	
	@Column(name = "PRESERVE_DATE",length = 100)
	@FieldName(name="保全日期")
	private String preservedate;
	
	@Column(name = "MATURITY_DATE",length = 100)
	@FieldName(name="保全到期日")
	private String maturitydate;
	
	@Column(name = "RELIEF_PRESERVATION",length = 100)
	@FieldName(name="解除保全日")
	private String reliefpreservation;
	
	@Column(name = "PROXY_TYPE",length = 100)
	@JoinColumn(name = "PROXY_TYPE")
	@FieldName(name="代理情况")
	private String proxytype;
	
	@Column(name = "PROXY_TYPE_NAME",length = 100)
	@FieldName(name="代理情况")
	private String proxytypename;
	
	@Column(name = "INDICT_DATE",length = 100)
	@FieldName(name="起诉日期")
	private String indictdate;
	
	@Column(name = "LAW_POSITION",length = 200)
	@FieldName(name="职务")
	private String lawposition;
	
	@Column(name = "CONTRACTOR_NAME",length = 200)
	@FieldName(name="我方承办人姓名")
	private String contractorname;
	
	@Column(name = "LAW_POST",length = 200)
	@FieldName(name="职位")
	private String lawpost;
	
	@Column(name = "COMP_PHONE",length =200)
	@FieldName(name="公司电话")
	private String compphone;

	@Column(name = "LINK_TYPE",length = 200)
	@FieldName(name="联系方式")
	private String linktype;
	
	@Column(name = "PROXY_MEMO",length = 4000)
	@FieldName(name="备注")
	private String proxymemo;
	
	@Column(name = "RISK_EXPOSURE",length = 400)
	@FieldName(name="风险敞口")
	private String riskexposure;
	
	@Column(name = "TRANSFER",length = 100)
	@FieldName(name="移交时间")
	private String transfer;
	
	@Column(name = "STAFFSUGS",length = 4000)
	@FieldName(name="办理意见")
	private String staffsugs;
	
	@Column(name = "TOTAL_DEBT",length = 100)
	@FieldName(name="应收债权总计")
	private String totaldebt;
	
	@Column(name = "KNOWN_MONEY",length = 100)
	@FieldName(name="预知费用金额")
	private String knownmoney;
	
	@Column(name = "IS_KNOWNMONEY",length = 100)
	@FieldName(name="是否有预知费用")
	private String isknownmoney;
	
	@FieldName(name = "法务申请立案通知")
	@OneToMany(mappedBy = "lawapprovalId", fetch = FetchType.LAZY)
	private Set<LawFilingNotice> filingInfos = new HashSet<LawFilingNotice>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;
	
	@Column(name = "LAWMONEY", length = 100)
	@FieldName(name="诉讼费用总计")
	private String lawmoney;
	
	@Column(name = "NETRECMONEY", length = 100)
	@FieldName(name="净回收额")
	private String netrecmoney;

	
	public String getLawmoney() {
		return lawmoney;
	}

	public void setLawmoney(String lawmoney) {
		this.lawmoney = lawmoney;
	}

	public String getNetrecmoney() {
		return netrecmoney;
	}

	public void setNetrecmoney(String netrecmoney) {
		this.netrecmoney = netrecmoney;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}

	public String getLawnum() {
		return lawnum;
	}

	public void setLawnum(String lawnum) {
		this.lawnum = lawnum;
	}


	public String getLawmemo() {
		return lawmemo;
	}

	public void setLawmemo(String lawmemo) {
		this.lawmemo = lawmemo;
	}

	public String getJalawtype() {
		return jalawtype;
	}

	public void setJalawtype(String jalawtype) {
		this.jalawtype = jalawtype;
	}

	public String getCloseinfo() {
		return closeinfo;
	}

	public void setCloseinfo(String closeinfo) {
		this.closeinfo = closeinfo;
	}

	public String getClosemoney() {
		return closemoney;
	}

	public void setClosemoney(String closemoney) {
		this.closemoney = closemoney;
	}

	public String getClosedate() {
		return closedate;
	}

	public void setClosedate(String closedate) {
		this.closedate = closedate;
	}

	public String getClosememo() {
		return closememo;
	}

	public void setClosememo(String closememo) {
		this.closememo = closememo;
	}

	public String getDetailmemo() {
		return detailmemo;
	}

	public void setDetailmemo(String detailmemo) {
		this.detailmemo = detailmemo;
	}

	public String getIspreserve() {
		return ispreserve;
	}

	public void setIspreserve(String ispreserve) {
		this.ispreserve = ispreserve;
	}

	public String getPreservedate() {
		return preservedate;
	}

	public void setPreservedate(String preservedate) {
		this.preservedate = preservedate;
	}

	public String getIndictdate() {
		return indictdate;
	}

	public void setIndictdate(String indictdate) {
		this.indictdate = indictdate;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
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

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getKnownmoney() {
		return knownmoney;
	}

	public void setKnownmoney(String knownmoney) {
		this.knownmoney = knownmoney;
	}

	public String getIsknownmoney() {
		return isknownmoney;
	}

	public void setIsknownmoney(String isknownmoney) {
		this.isknownmoney = isknownmoney;
	}

	public String getMaturitydate() {
		return maturitydate;
	}

	public void setMaturitydate(String maturitydate) {
		this.maturitydate = maturitydate;
	}

	public String getReliefpreservation() {
		return reliefpreservation;
	}

	public void setReliefpreservation(String reliefpreservation) {
		this.reliefpreservation = reliefpreservation;
	}

	public String getProxytype() {
		return proxytype;
	}

	public void setProxytype(String proxytype) {
		this.proxytype = proxytype;
	}

	public String getProxytypename() {
		return proxytypename;
	}

	public void setProxytypename(String proxytypename) {
		this.proxytypename = proxytypename;
	}

	public String getLawposition() {
		return lawposition;
	}

	public void setLawposition(String lawposition) {
		this.lawposition = lawposition;
	}

	public String getCompphone() {
		return compphone;
	}

	public void setCompphone(String compphone) {
		this.compphone = compphone;
	}

	public String getLinktype() {
		return linktype;
	}

	public void setLinktype(String linktype) {
		this.linktype = linktype;
	}

	public String getProxymemo() {
		return proxymemo;
	}

	public void setProxymemo(String proxymemo) {
		this.proxymemo = proxymemo;
	}

	public String getRiskexposure() {
		return riskexposure;
	}

	public void setRiskexposure(String riskexposure) {
		this.riskexposure = riskexposure;
	}

	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	public String getStaffsugs() {
		return staffsugs;
	}

	public void setStaffsugs(String staffsugs) {
		this.staffsugs = staffsugs;
	}

	public String getTotaldebt() {
		return totaldebt;
	}

	public void setTotaldebt(String totaldebt) {
		this.totaldebt = totaldebt;
	}

	public Set<LawFilingNotice> getFilingInfos() {
		return filingInfos;
	}

	public void setFilingInfos(Set<LawFilingNotice> filingInfos) {
		this.filingInfos = filingInfos;
	}

	public String getIsprintfile() {
		return isprintfile;
	}

	public void setIsprintfile(String isprintfile) {
		this.isprintfile = isprintfile;
	}

	public String getContractorname() {
		return contractorname;
	}

	public void setContractorname(String contractorname) {
		this.contractorname = contractorname;
	}

	public String getLawpost() {
		return lawpost;
	}

	public void setLawpost(String lawpost) {
		this.lawpost = lawpost;
	}
	
							
}