package com.tenwa.leasing.entity.voucher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;


/**
 * 
 * @author 孙传良
 * @date 2013-9-13下午02:11:11
 * @info 凭证头实体
 * @Copyright 
 * TENWA
 */
@Entity
@FieldName(name = "凭证头实体")
@Table(name="INTEREAS_VOUCHERHEAD")
public class IntereasVoucherHead {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id ;
	
	@FieldName(name="凭证号")
	@Column(name="VOUCHERNUMBER", length=20)	
	private String voucherNumber;
	
	@ManyToOne
	@FieldName(name="租赁合同号")
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contract_id  ;
	
	@ManyToOne
	@FieldName(name="公司编码")
	@JoinColumn(name="COMPANYNUMBER")
	private InterOrgCode companyNumber ;
	
	@FieldName(name="记帐日期")
	@Column(name="BOOKEDDATE", length=20)
	private String bookedDate ;
	
	@FieldName(name="业务日期")
	@Column(name="BIZDATE", length=20)
	private String bizDate ;
	
	@FieldName(name="会计期间-年")
	@Column(name="PERIODYEAR", length=10)
	private String periodYear ; 
	
	@FieldName(name="会计期间-月")
	@Column(name="PERIODNUMBER", length=10)
	private String periodNumber ;
	
//	@FieldName(name="凭证字（凭证类型）")
//	@Column(name="VOUCHERTYPE", length=10)
//	private String voucherType ;
	@ManyToOne
	@FieldName(name="凭证字（凭证类型）")
	@JoinColumn(name="VOUCHERTYPE")
	private DictionaryData voucherType ;
	
	
	
	@FieldName(name="凭证状态")
	@Column(name="STATUS_", length=20)
	private String status ;
	
	@FieldName(name="凭证发生日期")
	@Column(name="GENERATE_DATE", length=20)
	private String generate_date  ;
	
	@FieldName(name="导入财务系统状态")
	@Column(name="EAS_FLAG", length=20)
	private String eas_flag  ;
	
	@FieldName(name="业务模块")
	@Column(name="MODLENAME", length=50)
	private String modleName ;
	
	@FieldName(name="导入财务系统备注")
	@Column(name="EAS_MEMO", length=500)
	private String eas_memo ;
	
	@FieldName(name="导入财务系统日期")
	@Column(name="EXP_DATE", length=20)
	private String exp_date  ;
	
	@FieldName(name="凭证备注")
	@Column(name="MEMO1", length=500)
	private String memo1  ;
	
	@FieldName(name="公司所属")
	@Column(name="BELONG_UNIT", length=50)
	private String belong_unit ;
	
	@FieldName(name="凭证体实体")
	@OneToMany(mappedBy="voucherNumber", fetch=FetchType.LAZY)
	@OrderBy(value="entrySeq ASC,createDate ASC")
	private Set<IntereasVoucherEntries> intereasVoucherEntries=new HashSet<IntereasVoucherEntries>();
	
	@ManyToOne
	@FieldName(name="创建人/制单人")
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
	
	@Transient
	private List<IntereasVoucherEntries> intereasVoucherEntriesInAction = new ArrayList<IntereasVoucherEntries>();
	
	/**
	 * 
	 * <p>GET公司所属编号。</p>
	 * <p>01：所属公司1，02：所属公司2，03：所属公司3。例如：01：欧力士，02：欧力士子公司1，03：欧力士子公司2。</p>
	 * <p>取在表inter_orgcode中的配置数据中的id，字段三个：id/org_code/org_name,例如恒信：01	01-C0001	欧力士融资租赁有限公司</p>
	 * @author sea
	 * @return
	 */
	public String getBelong_unit() {
		return belong_unit;
	}
	/**
	 * 
	 * <p>SET公司所属编号。</p>
	 * <p>01：所属公司1，02：所属公司2，03：所属公司3。例如：01：欧力士，02：欧力士子公司1，03：欧力士子公司2。</p>
	 * <p>取在表inter_orgcode中的配置数据中的id，字段三个：id/org_code/org_name,例如恒信：01	01-C0001	欧力士融资租赁有限公司</p>
	 * @author sea
	 * @return
	 */
	public void setBelong_unit(String belongUnit) {
		belong_unit = belongUnit;
	}
	/**
	 * 
	 * <p>凭证头自增长主键。</p>
	 * @author sea
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * <p>凭证头自增长主键。</p>
	 * @author sea
	 * @return
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * <p>GET凭证号。</p>
	 * <p>函数：select evidenceNocreateEAS() as voucherNumber from dual;生产。</p>
	 * @author sea
	 * @return
	 */
	public String getVoucherNumber() {
		return voucherNumber;
	}
	/**
	 * 
	 * <p>SET凭证号。</p>
	 * <p>函数：select evidenceNocreateEAS() as voucherNumber from dual;生产。</p>
	 * @author sea
	 * @return
	 */
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	/**
	 * 
	 * <p>GET公司编码。</p>
	 * <p>公司所属表inter_orgcode对应主键 </p>
	 * @author sea
	 * @return
	 */
	public InterOrgCode getCompanyNumber() {
		return companyNumber;
	}
	/**
	 * 
	 * <p>SET公司编码。</p>
	 * <p>公司所属表inter_orgcode对应主键</p>
	 * @author sea
	 * @return
	 */
	public void setCompanyNumber(InterOrgCode companyNumber) {
		this.companyNumber = companyNumber;
	}
	/**
	 * 
	 * <p>GET财务记帐日期。</p>
	 * <p>财务记账日期为系统传入值或根据业务日期根据getAccountDay函数计算得出。</p>
	 * @author sea
	 * @return
	 */
	public String getBookedDate() {
		return bookedDate;
	}
	/**
	 * 
	 * <p>SET财务记帐日期。</p>
	 * <p>财务记账日期为系统传入值或根据业务日期根据getAccountDay函数计算得出。</p>
	 * @author sea
	 * @return
	 */
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	/**
	 * 
	 * <p>GET业务日期。</p>
	 *  <p>业务发生日期为系统传入值。</p>
	 * @author sea
	 * @return
	 */
	public String getBizDate() {
		return bizDate;
	}
	/**
	 * 
	 * <p>SET业务日期。</p>
	 * <p>业务发生日期为系统传入值。</p>
	 * @author sea
	 * @return
	 */
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}
	/**
	 * 
	 * <p>GET会计期间-年 。</p>
	 * <p>财务记账日期.substring(0,4)。</p>
	 * @author sea
	 * @return
	 */
	public String getPeriodYear() {
		return periodYear;
	}
	/**
	 * 
	 * <p>SET会计期间-年 。</p>
	 * <p>财务记账日期.substring(0,4)。</p>
	 * @author sea
	 * @return
	 */
	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}
	/**
	 * 
	 * <p>GET会计期间-月。</p>
	 * <p>财务记账日期.substring(5,7)。</p>
	 * @author sea
	 * @return
	 */
	public String getPeriodNumber() {
		return periodNumber;
	}
	/**
	 * 
	 * <p>SET会计期间-月。</p>
	 * <p>财务记账日期.substring(5,7)。</p>
	 * @author sea
	 * @return
	 */
	public void setPeriodNumber(String periodNumber) {
		this.periodNumber = periodNumber;
	}
	/**
	 * 
	 * <p>GET凭证字（凭证类型）。</p>
	 * <p>系统传入参数，例如：01/02...</p>
	 * @author sea
	 * @return
	 */
	public DictionaryData getVoucherType() {
		return voucherType;
	}
	/**
	 * 
	 * <p>SET凭证字（凭证类型）。</p>
	 * <p>系统传入参数，例如：  01、02等</p>
	 * @author sea
	 * @return
	 */
	public void setVoucherType(DictionaryData voucherType) {
		this.voucherType = voucherType;
	}
	/**
	 * 
	 * <p>GET制单人。</p>
	 * <p>预设用户/当前系统登录人。如果需要可以单独指定人</p>
	 * @author sea
	 * @return
	 */
	public User getCreator() {
		return creator;
	}
	/**
	 * 
	 * <p>SET制单人。</p>
	 * <p>预设用户/当前系统登录人。</p>
	 * @author sea
	 * @return
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}
	/**
	 * 
	 * <p>GET凭证状态。</p>
	 * <p>默认：已完整，凭证头凭证体及辅助账信息完整状态为已完整，否则该凭证状态为不完整。</p>
	 * @author sea
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 
	 * <p>SET凭证状态。</p>
	 * <p>默认：已完整，凭证头凭证体及辅助账信息完整状态为已完整，否则该凭证状态为不完整。</p>
	 * @author sea
	 * @return
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 
	 * <p>GET凭证发生日期（当前日期或者指定日期）。</p>
	 * @author sea
	 * @return
	 */
	public String getGenerate_date() {
		return generate_date;
	}
	/**
	 * 
	 * <p>SET凭证发生日期（当前日期或者指定日期）。</p>
	 * @author sea
	 * @return
	 */
	public void setGenerate_date(String generateDate) {
		generate_date = generateDate;
	}
	/**
	 * 
	 * <p>GET导入财务系统状态。</p>
	 * <p>导入前值默认为：0，导入成功则会改为：1。</p>
	 * @author sea
	 * @return
	 */
	public String getEas_flag() {
		return eas_flag;
	}
	/**
	 * 
	 * <p>SET导入财务系统状态。</p>
	 * <p>导入前值默认为：0，导入成功则会改为：1。</p>
	 * @author sea
	 * @return
	 */
	public void setEas_flag(String easFlag) {
		eas_flag = easFlag;
	}
	/**
	 * 
	 * <p>GET业务模块。</p>
	 * <p>系统传入参数，生产该凭证的操作模块，例如：已开票增值税发票回导/网银。</p>
	 * @author sea
	 * @return
	 */
	public String getModleName() {
		return modleName;
	}
	/**
	 * 
	 * <p>SET业务模块。</p>
	 * <p>系统传入参数，生产该凭证的操作模块，例如：已开票增值税发票回导/网银。</p>
	 * @author sea
	 * @return
	 */
	public void setModleName(String modleName) {
		this.modleName = modleName;
	}
	/**
	 * 
	 * <p>GET导入财务系统备注。</p>
	 * <p>导入前值默认为空。</p>
	 * @author sea
	 * @return
	 */
	public String getEas_memo() {
		return eas_memo;
	}
	/**
	 * 
	 * <p>SET导入财务系统备注。</p>
	 * <p>导入前值默认为空。</p>
	 * @author sea
	 * @return
	 */
	public void setEas_memo(String easMemo) {
		eas_memo = easMemo;
	}
	/**
	 * 
	 * <p>GET导入财务系统日期。</p>
	 * <p>导入前值默认为空。</p>
	 * @author sea
	 * @return
	 */
	public String getExp_date() {
		return exp_date;
	}
	/**
	 * 
	 * <p>SET导入财务系统日期。</p>
	 * <p>导入前值默认为空。</p>
	 * @author sea
	 * @return
	 */
	public void setExp_date(String expDate) {
		exp_date = expDate;
	}
	/**
	 * 
	 * <p>GET租赁合同号。</p>
	 * <p>系统传入参数。</p>
	 * @author sea
	 * @return
	 */
	public ContractInfo getContract_id() {
		return contract_id;
	}
	/**
	 * 
	 * <p>SET租赁合同号。</p>
	 * <p>系统传入参数。</p>
	 * @author sea
	 * @return
	 */
	public void setContract_id(ContractInfo contractId) {
		contract_id = contractId;
	}
	/**
	 * 
	 * <p>GET修改日期。</p>
	 * @author sea
	 * @return
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * 
	 * <p>SET修改日期。</p>
	 * @author sea
	 * @return
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * 
	 * <p>GET修改人。</p>
	 * @author sea
	 * @return
	 */
	public User getModificator() {
		return modificator;
	}
	/**
	 * 
	 * <p>SET修改人。</p>
	 * @author sea
	 * @return
	 */
	public void setModificator(User modificator) {
		this.modificator = modificator;
	}
	/**
	 * 
	 * <p>GET凭证备注/摘要。</p>
	 * @author sea
	 * @return
	 */
	public String getMemo1() {
		return memo1;
	}
	/**
	 * 
	 * <p>SET凭证备注/摘要。</p>
	 * @author sea
	 * @return
	 */
	public void setMemo1(String memo1) {
		this.memo1 = memo1;
	}
	
	/**
	 * <p>GET凭证创建日期。</p>
	 * @author sea
	 * @return
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * <p>SET凭证创建日期。</p>
	 * @author sea
	 * @return
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	/**
	  * <p>SET根据凭证头相关信息去获取凭证体ENTITY。</p>
	  * @author sea
	  * @return
	 */
	public Set<IntereasVoucherEntries> getIntereasVoucherEntries() {
		return intereasVoucherEntries;
	}
	
	/**
	 * <p>GET根据凭证头相关信息去获取凭证提ENTITY。</p>
	 * @author sea
	 * @return
	 */
	public void setIntereasVoucherEntries(
			Set<IntereasVoucherEntries> intereasVoucherEntries) {
		this.intereasVoucherEntries = intereasVoucherEntries;
	}
	@Transient
	public List<IntereasVoucherEntries> getIntereasVoucherEntriesInAction() {
		return intereasVoucherEntriesInAction;
	}
	@Transient
	public void setIntereasVoucherEntriesInAction(List<IntereasVoucherEntries> intereasVoucherEntriesInAction) {
		this.intereasVoucherEntriesInAction = intereasVoucherEntriesInAction;
	}
	
	
	
	
}
