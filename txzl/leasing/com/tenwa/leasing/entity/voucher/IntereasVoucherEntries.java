package com.tenwa.leasing.entity.voucher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 凭证体实体(凭证体存在多笔)
 * @date 2013-9-13下午02:11:33
 * @info
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "凭证体实体")
@Table(name="INTEREAS_VOUCHERENTRIES")
public class IntereasVoucherEntries {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String  id;
	
	@ManyToOne
	@FieldName(name="凭证号")
	@JoinColumn(name="VOUCHERNUMBER")
	private IntereasVoucherHead voucherNumber ;
	
	@FieldName(name="分录行号")
	@Column(name="ENTRYSEQ", length=20)
	private String entrySeq ;
	
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@FieldName(name="摘要")
	@Column(name="VOUCHERABSTRACT")
	private String voucherAbstract ;
	
//	@FieldName(name="科目编码")
//	@Column(name="ACCOUNTNUMBER", length=20)
//	private String accountNumber ;
	@ManyToOne
	@FieldName(name="科目编码")
	@JoinColumn(name="ACCOUNTNUMBER")
	private VoucherassStactsConfig accountNumber ;
	
	
	@FieldName(name="方向 （1 借方-1 贷方）")
	@Column(name="ENTRYDC", length=10)
	private Integer entryDC ;
	
	@ManyToOne
	@FieldName(name="币别")
	@JoinColumn(name="CURRENCYNUMBER")
	private DictionaryData currencyNumber ;
	
	@FieldName(name="借贷金额")
	@Column(name="ORIGINALAMOUNT",precision=22,scale=2)
	private BigDecimal originalAmount;
	
	@FieldName(name="借方金额")
	@Column(name="DEBITAMOUNT",precision=22,scale=2)
	private BigDecimal debitAmount ;//借方金额 分录行号为1就=原币金额 分录行号为2就为0
	
	@FieldName(name="贷方金额")
	@Column(name="CREDITAMOUNT",precision=22,scale=2)
	private BigDecimal creditAmount ;//贷方金额  分录行号为2就=原币金额 分录行号为1就为0
	
	/*****************现金流******************/
	
	@FieldName(name="现金流量标记")
	@Column(name="ITEM_FLAG", length = 5)
	private int itemflag;//1情况下方字段请填写，0情况下方这10个值都为空
	
	@FieldName(name="本方分录号")
	@Column(name="LOCAL_ACCOUNT_SEQ", length = 5)
	private int localAccountSeq;//借是1，贷是2 
	
	@FieldName(name="对方分录号")
	@Column(name="OPP_ACCOUNT_SEQ", length = 5)
	private int oppAccountSeq;//借是1，贷是2 
	
	@FieldName(name="主表信息")
	@Column(name="PRIMARY_ITEM")
	private String primaryItem;//对方分录号是1时：01.01.01；对方分录号是2时：01.02.01
	
	@FieldName(name="附表信息")
	@Column(name="SUPPLY_ITEM")
	private String supplyItem;
	
	@FieldName(name="原币")
	@Column(name="CASHFLOW_AMOUNT_ORIGINAL")
	private BigDecimal cashflowAmountOriginal;//取银行数
	
	@FieldName(name="本位币")
	@Column(name="CASHFLOW_AMOUNT_LOCAL")
	private BigDecimal cashflowAmountLocal;//取银行数
	
	@FieldName(name="报告币")
	@Column(name="CASHFLOW_AMOUNT_RPT")
	private BigDecimal cashflowAmountRpt;//取银行数
	
	@FieldName(name="主表金额系数")
	@Column(name="PRIMARY_COEF", length = 5)
	private int primaryCoef;//默认值1
	
	@FieldName(name="附表金额系数")
	@Column(name="SUPPLY_COEF", length = 5)
	private int supplyCoef;//默认值0
	
	@FieldName(name="性质")
	@Column(name="CASHFLOW_PROPERTIES")
	private String cashflowProperties;//取汉字 “外部”

	/*****************现金流******************/
	
	
	@FieldName(name="辅助账实体")
	@OneToMany(mappedBy="entrySeq", fetch=FetchType.LAZY)
	@OrderBy(value="asstSeq ASC,createDate ASC")
	private Set<IntereasVoucherasStacts> intereasVoucherasStacts=new HashSet<IntereasVoucherasStacts>();
	
	/**
	 * 业务用辅助账集合
	 */
	@Transient
	private List<IntereasVoucherasStacts> intereasVoucherasStactsInAction =new ArrayList<IntereasVoucherasStacts>();
	
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
	/**
	 * 
	 * <p>GET凭证体自增长主键。</p>
	 * @author sea
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * <p>SET凭证体自增长主键。</p>
	 * @author sea
	 * @return
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * <p>GET分录行号。</p>
	 * <p>系统传入参数，例如：1</p>
	 * @author sea
	 * @return
	 */
	public String getEntrySeq() {
		return entrySeq;
	}
	/**
	 * 
	 * <p>SET分录行号。</p>
	 * <p>系统传入参数，例如：1</p>
	 * @author sea
	 * @return
	 */
	public void setEntrySeq(String entrySeq) {
		this.entrySeq = entrySeq;
	}
	/**
	 * 
	 * <p>GET摘要。</p>
	 * <p>系统传入参数，例如：01A101105012514-衡阳市蒸湘佳兴彩色印刷厂-L08A3382-未确认收款</p>
	 * @author sea
	 * @return
	 */
	public String getVoucherAbstract() {
		return voucherAbstract;
	}
	/**
	 * 
	 * <p>SET摘要。</p>
	 * <p>系统传入参数，例如:01A101105012514-衡阳市蒸湘佳兴彩色印刷厂-L08A3382-未确认收款</p>
	 * @author sea
	 * @return
	 */
	public void setVoucherAbstract(String voucherAbstract) {
		this.voucherAbstract = voucherAbstract;
	}
	/**
	 * 
	 * <p>GET科目编码。</p>
	 * <p>系统传入参数,例如：1531.02</p>
	 * @author sea
	 * @return
	 */
	public VoucherassStactsConfig getAccountNumber() {
		return accountNumber;
	}
	/**
	 * 
	 * <p>SET科目编码。</p>
	 * <p>系统传入参数,例如：1531.02</p>
	 * @author sea
	 * @return
	 */
	public void setAccountNumber(VoucherassStactsConfig accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * 
	 * <p>GET方向 （1 借方-1 贷方）。</p>
	 * <p>系统传入参数,例如：1</p>
	 * @author sea
	 * @return
	 */
	public Integer getEntryDC() {
		return entryDC;
	}
	/**
	 * 
	 * <p>SET方向 （1 借方-1 贷方）。</p>
	 * <p>系统传入参数,例如：-1</p>
	 * @author sea
	 * @return
	 */
	public void setEntryDC(Integer entryDC) {
		this.entryDC = entryDC;
	}
	/**
	 * 
	 * <p>GET币别,人民币/美元/港币...。</p>
	 * <p>系统传入参数,例如：BB01</p>
	 * @author sea
	 * @return
	 */
	public DictionaryData getCurrencyNumber() {
		return currencyNumber;
	}
	/**
	 * 
	 * <p>SET币别,人民币/美元/港币...。</p>
	 * <p>系统传入参数,例如：BB01</p>
	 * @author sea
	 * @return
	 */
	public void setCurrencyNumber(DictionaryData currencyNumber) {
		this.currencyNumber = currencyNumber;
	}
	/**
	 * 
	 * <p>GET借贷金额。</p>
	 * <p>系统传入参数,例如：34888.00</p>
	 * @author sea
	 * @return
	 */
	public BigDecimal getOriginalAmount() {
		return originalAmount;
	}
	/**
	 * 
	 * <p>SET借贷金额。</p>
	 * <p>系统传入参数,例如：34888.00</p>
	 * @author sea
	 * @return
	 */
	public void setOriginalAmount(BigDecimal originalAmount) {
		this.originalAmount = originalAmount;
	}
	/**
	 * 
	 * <p>GET借方金额。</p>
	 * <p>分录行号(entrySeq)为1就=原币金额(originalAmount) 分录行号为2就为0</p>
	 * @author sea
	 * @return
	 */
	public BigDecimal getDebitAmount() {
		return debitAmount;
	}
	/**
	 * 
	 * <p>SET借方金额  。</p>
	 * <p>分录行号(entrySeq)为1就=原币金额(originalAmount) 分录行号为2就为0</p>
	 * @author sea
	 * @return
	 */
	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}
	/**
	 * 
	 * <p>GET贷方金额 。</p>
	 * <p>分录行号(entrySeq)为2就=原币金额(originalAmount) 分录行号为1就为0</p>
	 * @author sea
	 * @return
	 */
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	/**
	 * 
	 * <p>SET。</p>
	 * <p>分录行号(entrySeq)为2就=原币金额(originalAmount) 分录行号为1就为0</p>
	 * @author sea
	 * @return
	 */
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	/**
	 * 
	 * <p>GET凭证号。</p>
	 * <p>系统传入参数，或者从凭证头获取</p>
	 * @author sea
	 * @return
	 */
	public IntereasVoucherHead getVoucherNumber() {
		return voucherNumber;
	}
	/**
	 * 
	 * <p>SET凭证号。</p>
	 * <p>系统传入参数，或者从凭证头获取</p>
	 * @author sea
	 * @return
	 */
	
	public void setVoucherNumber(IntereasVoucherHead voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	
	/**
	  * <p>GET根据凭证体的信息去查询辅助账ENTITY的信息。</p>
	  * @author sea
	  * @return
	 */
	public Set<IntereasVoucherasStacts> getIntereasVoucherasStacts() {
		return intereasVoucherasStacts;
	}
	
	/**
	 * <p>SET根据凭证体的信息去查询辅助账ENTITY的信息。</p>
	 * @author sea
	 * @return
	 */
	public void setIntereasVoucherasStacts(
			Set<IntereasVoucherasStacts> intereasVoucherasStacts) {
		this.intereasVoucherasStacts = intereasVoucherasStacts;
	}
	/**
	 * <p>GET创建人。</p>
	 * @author sea
	 * @return
	 */
	public User getCreator() {
		return creator;
	}
	/**
	 * <p>SET创建人。</p>
	 * @author sea
	 * @return
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}
	/**
	 * <p>GET创建日期。</p>
	 * @author sea
	 * @return
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * <p>SET创建日期。</p>
	 * @author sea
	 * @return
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * <p>GET修改人。</p>
	 * @author sea
	 * @return
	 */
	public User getModificator() {
		return modificator;
	}
	/**
	 * <p>SET修改人。</p>
	 * @author sea
	 * @return
	 */
	public void setModificator(User modificator) {
		this.modificator = modificator;
	}
	/**
	 * <p>GET修改日期。</p>
	 * @author sea
	 * @return
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * <p>SET修改日期。</p>
	 * @author sea
	 * @return
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Transient
	public List<IntereasVoucherasStacts> getIntereasVoucherasStactsInAction() {
		return intereasVoucherasStactsInAction;
	}
	/**
	 * <p>业务用辅助账集合,不用库</p>
	 * @param intereasVoucherasStactsInAction
	 */
	@Transient
	public void setIntereasVoucherasStactsInAction(List<IntereasVoucherasStacts> intereasVoucherasStactsInAction) {
		this.intereasVoucherasStactsInAction = intereasVoucherasStactsInAction;
	}
	
	/**
	  * <p>GET现金流量标记。</p>
	  * @author sea
	  * @return
	 */
	public int getItemflag() {
		return itemflag;
	}
	/**
	 * <p>GET本方分录号,借是1，贷是2 。</p>
	 * @author sea
	 * @return
	 */
	public int getLocalAccountSeq() {
		return localAccountSeq;
	}
	/**
	 * <p>GET对方分录号,借是1，贷是2。</p>
	 * @author sea
	 * @return
	 */
	public int getOppAccountSeq() {
		return oppAccountSeq;
	}
	/**
	 * <p>GET主表信息,对方分录号是1时：01.01.01；对方分录号是2时：01.02.01。</p>
	 * @author sea
	 * @return
	 */
	public String getPrimaryItem() {
		return primaryItem;
	}
	/**
	 * <p>GET附表信息。</p>
	 * @author sea
	 * @return
	 */
	public String getSupplyItem() {
		return supplyItem;
	}
	/**
	 * <p>GET原币。</p>
	 * @author sea
	 * @return
	 */
	public BigDecimal getCashflowAmountOriginal() {
		return cashflowAmountOriginal;
	}
	/**
	 * <p>GET本位币。</p>
	 * @author sea
	 * @return
	 */
	public BigDecimal getCashflowAmountLocal() {
		return cashflowAmountLocal;
	}
	/**
	 * <p>GET报告币。</p>
	 * @author sea
	 * @return
	 */
	public BigDecimal getCashflowAmountRpt() {
		return cashflowAmountRpt;
	}
	/**
	 * <p>GET主表金额系数,默认值1。</p>
	 * @author sea
	 * @return
	 */
	public int getPrimaryCoef() {
		return primaryCoef;
	}
	/**
	 * <p>GET附表金额系数,默认值0。</p>
	 * @author sea
	 * @return
	 */
	public int getSupplyCoef() {
		return supplyCoef;
	}
	/**
	 * <p>GET性质,默认值中文'外部'。</p>
	 * @author sea
	 * @return
	 */
	public String getCashflowProperties() {
		return cashflowProperties;
	}
	/**
	 * <p>SET现金流量标记。</p>
	 * @author sea
	 * @return
	 */
	public void setItemflag(int itemflag) {
		this.itemflag = itemflag;
	}
	/**
	 * <p>SET本方分录号,借是1，贷是2 。</p>
	 * @author sea
	 * @return
	 */
	public void setLocalAccountSeq(int localAccountSeq) {
		this.localAccountSeq = localAccountSeq;
	}
	/**
	 * <p>SET对方分录号,借是1，贷是2。</p>
	 * @author sea
	 * @return
	 */
	public void setOppAccountSeq(int oppAccountSeq) {
		this.oppAccountSeq = oppAccountSeq;
	}
	/**
	 * <p>SET主表信息,对方分录号是1时：01.01.01；对方分录号是2时：01.02.01。</p>
	 * @author sea
	 * @return
	 */
	public void setPrimaryItem(String primaryItem) {
		this.primaryItem = primaryItem;
	}
	/**
	 * <p>SET附表信息。</p>
	 * @author sea
	 * @return
	 */
	public void setSupplyItem(String supplyItem) {
		this.supplyItem = supplyItem;
	}
	/**
	 * <p>SET原币。</p>
	 * @author sea
	 * @return
	 */
	public void setCashflowAmountOriginal(BigDecimal cashflowAmountOriginal) {
		this.cashflowAmountOriginal = cashflowAmountOriginal;
	}
	/**
	 * <p>SET本位币。</p>
	 * @author sea
	 * @return
	 */
	public void setCashflowAmountLocal(BigDecimal cashflowAmountLocal) {
		this.cashflowAmountLocal = cashflowAmountLocal;
	}
	/**
	 * <p>SET报告币。</p>
	 * @author sea
	 * @return
	 */
	public void setCashflowAmountRpt(BigDecimal cashflowAmountRpt) {
		this.cashflowAmountRpt = cashflowAmountRpt;
	}
	/**
	 * <p>SET主表金额系数,默认值1。</p>
	 * @author sea
	 * @return
	 */
	public void setPrimaryCoef(int primaryCoef) {
		this.primaryCoef = primaryCoef;
	}
	/**
	 * <p>SET附表金额系数,默认值0。</p>
	 * @author sea
	 * @return
	 */
	public void setSupplyCoef(int supplyCoef) {
		this.supplyCoef = supplyCoef;
	}
	/**
	 * <p>SET性质,默认值中文'外部'。</p>
	 * @author sea
	 * @return
	 */
	public void setCashflowProperties(String cashflowProperties) {
		this.cashflowProperties = cashflowProperties;
	}
	
	
	
}
