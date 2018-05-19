package com.tenwa.leasing.entity.voucher;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;

@Entity
@FieldName(name = "腾华财务凭证表")
@Table(name="INTER_EVIDENCE_INFO")
public class InterEvidenceInfo {

		@Id
	    @GeneratedValue(generator = "paymentableGenerator")     
	    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	    @Column(length=32)
		private String id ;
		
		//2
		@FieldName(name="凭证号")
		@Column(name="EVIDENCE_NUMBER", length=20)	
		private String evidenceNumber;
		
		
		//3
		@FieldName(name="摘要")
		@Column(name="EVIDENCE_SUMMARY", length=900)	
		private String evidenceSummary;
		
		
		//4
		@ManyToOne(fetch=FetchType.LAZY)
		@FieldName(name="科目编号")
		@JoinColumn(name="SUBJECT_NUMBER")
		private VoucherassStactsConfig subjectNumber;
		
		
		//5
		@FieldName(name="借方金额")
		@Column(name="DEBIT",precision=22,scale=2)
		private BigDecimal debit ;//借方金额 分录行号为1就=原币金额 分录行号为2就为0
		
		
		//6
		@FieldName(name="贷方金额")
		@Column(name="CREDIT",precision=22,scale=2)
		private BigDecimal credit ;//贷方金额  分录行号为2就=原币金额 分录行号为1就为0
		
		
		//7
		@FieldName(name="会计处理日")
		@Column(name="HAPPEN_DATE", length=20)
		private String happenDate ;
		
		
		//8
		@FieldName(name = "客户编码(合同)")
		@OneToOne(targetEntity = CustInfo.class, fetch = FetchType.LAZY)
		@JoinColumn(name = "CLIENT_ID")
		private CustInfo clientId ;//欧力士客户就是合同，这里存入合同号,其余项目请具体处理
		
		
		//9
		@ManyToOne(fetch=FetchType.LAZY)
		@FieldName(name="供应商编码")
		@JoinColumn(name="VNDR_ID")
		private CustInfo vndrId ;//按供应商辅助核算时带入供应商编码
					
		
		@FieldName(name="公司编码")
		@Column(name="ACC_SET_NUMBER",length=500)
		private String  accSetNumber ;
		
		
		@FieldName(name="公司所属名称")
		@Column(name="ACCOUTING_UNIT",length=500)
		private String accoutingUnit ;
		
		//12年
		@FieldName(name="会计期间-年")
		@Column(name="ACC_YEAR", length=10)
		private String accYear ; 
		//13月
		@FieldName(name="会计期间-月")
		@Column(name="ACC_MONTH", length=10)
		private String accMonth ; 
       
		//14行号	
		@FieldName(name="序列号")
		@Column(name="LINE_NUMBER", length=200)
		private String lineNumber ;

		//15科目名称
						
		
		@FieldName(name="科目名称")
		@Column(name="SUBJECT_NAME",length=500)
		private String subjectName;
				

		//16模块名称
		@FieldName(name="业务模块")
		@Column(name="MODULE_NAME", length=500)
		private String moduleName ;
		
		//17导出状态
		@FieldName(name="导入财务系统状态")
		@Column(name="EXP_FLAG", length=20)
		private String expFlag  ;//title:'未导出',name:'0'},{title:'已导出',name:'1'
		
		//18导出日期
		@FieldName(name="导入财务系统日期")
		@Column(name="EXP_DATE", length=20)
		private String exp_date  ;
		
		//19导出人	--user表中没有写			
		@ManyToOne(fetch=FetchType.LAZY)
		@FieldName(name="修改人")
		@JoinColumn(name="EXP_USER")
		private User expUser;
		
	
		
		//20凭证(业务)发生日期
		@FieldName(name="凭证发生日期")
		@Column(name="GENERATE_DATE", length=20)
		private String generateDate  ;//系统当前日期带时分秒
		
		
		//21合同编号
		@FieldName(name = "合同编号")
		@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
		@JoinColumn(name = "INTER_CONTRACT_ID")
		private ContractInfo interContractId;
		
		/*//22凭证编号
		@FieldName(name="凭证编号")
		@Column(name="VOUCHER_ID", length=50)
		private String voucherId;*/
		
		//23凭证状态
		@FieldName(name="凭证状态")
		@Column(name="VOUCHER_STATUS", length=20)
		private String voucherStatus ;
		//24凭证文件地址
		@FieldName(name="凭证文件地址")
		@Column(name="VOUCHER_FILE", length=20)
		private String voucherFile ;
	
		//25网银到账到账日期（发生日期）
		@FieldName(name="到账日期")
		@Column(name="EBANK_FACT_DATE", length=20)
		private String ebankFactDate ;
		
		//26记账凭证
		@FieldName(name="凭证字")
		@Column(name="RECORDING_VOUCHER", length=10)
		private String recordingVoucher ;
		
		//27借贷方向
		@FieldName(name="借贷方向")
		@Column(name="ENTRYDC", length=10)
		private Integer entryDC;//1 借方-1 贷方
		
		//28项目编号
		@FieldName(name = "项目编码")
		@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
		@JoinColumn(name="PROJ_ID")
		private ContractInfo projId ;
		
		//29红冲记录 
		@FieldName(name="红冲记录 0或null：正常凭证,1:被红冲,-1:红冲凭证,2:冲抵凭证")
		@Column(name="ROLL_BACK", length=20)
		private String rollBack ;
		
		//必填字段
		@ManyToOne(fetch=FetchType.LAZY)
		@FieldName(name="创建人")
		@JoinColumn(name="CREATOR_")
		private User creator;
		
		@FieldName(name="创建时间")
		@Column(name="CREATE_DATE", length=20)	
		private String createDate;
		
		@ManyToOne(fetch=FetchType.LAZY)
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

		public String getEvidenceNumber() {
			return evidenceNumber;
		}

		public void setEvidenceNumber(String evidenceNumber) {
			this.evidenceNumber = evidenceNumber;
		}

		public String getEvidenceSummary() {
			return evidenceSummary;
		}

		public void setEvidenceSummary(String evidenceSummary) {
			this.evidenceSummary = evidenceSummary;
		}

		public VoucherassStactsConfig getSubjectNumber() {
			return subjectNumber;
		}

		public void setSubjectNumber(VoucherassStactsConfig subjectNumber) {
			this.subjectNumber = subjectNumber;
		}

		public BigDecimal getDebit() {
			return debit;
		}

		public void setDebit(BigDecimal debit) {
			this.debit = debit;
		}

		public BigDecimal getCredit() {
			return credit;
		}

		public void setCredit(BigDecimal credit) {
			this.credit = credit;
		}

		public String getHappenDate() {
			return happenDate;
		}

		public void setHappenDate(String happenDate) {
			this.happenDate = happenDate;
		}

		public CustInfo getClientId() {
			return clientId;
		}

		public void setClientId(CustInfo clientId) {
			this.clientId = clientId;
		}

		public CustInfo getVndrId() {
			return vndrId;
		}

		public void setVndrId(CustInfo vndrId) {
			this.vndrId = vndrId;
		}

		public String getAccSetNumber() {
			return accSetNumber;
		}

		public void setAccSetNumber(String accSetNumber) {
			this.accSetNumber = accSetNumber;
		}

		public String getAccoutingUnit() {
			return accoutingUnit;
		}

		public void setAccoutingUnit(String accoutingUnit) {
			this.accoutingUnit = accoutingUnit;
		}

		public String getAccYear() {
			return accYear;
		}

		public void setAccYear(String accYear) {
			this.accYear = accYear;
		}

		public String getAccMonth() {
			return accMonth;
		}

		public void setAccMonth(String accMonth) {
			this.accMonth = accMonth;
		}

		public String getLineNumber() {
			return lineNumber;
		}

		public void setLineNumber(String lineNumber) {
			this.lineNumber = lineNumber;
		}

		

		public String getModuleName() {
			return moduleName;
		}

		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}

		public String getExpFlag() {
			return expFlag;
		}

		public void setExpFlag(String expFlag) {
			this.expFlag = expFlag;
		}

		public String getExp_date() {
			return exp_date;
		}

		public void setExp_date(String exp_date) {
			this.exp_date = exp_date;
		}

		public User getExpUser() {
			return expUser;
		}

		public void setExpUser(User expUser) {
			this.expUser = expUser;
		}

		public String getGenerateDate() {
			return generateDate;
		}

		public void setGenerateDate(String generateDate) {
			this.generateDate = generateDate;
		}

		public ContractInfo getInterContractId() {
			return interContractId;
		}

		public void setInterContractId(ContractInfo interContractId) {
			this.interContractId = interContractId;
		}

		
		public String getVoucherStatus() {
			return voucherStatus;
		}

		public void setVoucherStatus(String voucherStatus) {
			this.voucherStatus = voucherStatus;
		}

		public String getVoucherFile() {
			return voucherFile;
		}

		public void setVoucherFile(String voucherFile) {
			this.voucherFile = voucherFile;
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

		public Integer getEntryDC() {
			return entryDC;
		}

		public void setEntryDC(Integer entryDC) {
			this.entryDC = entryDC;
		}

		public String getEbankFactDate() {
			return ebankFactDate;
		}

		public void setEbankFactDate(String ebankFactDate) {
			this.ebankFactDate = ebankFactDate;
		}

		public String getRecordingVoucher() {
			return recordingVoucher;
		}

		public void setRecordingVoucher(String recordingVoucher) {
			this.recordingVoucher = recordingVoucher;
		}

		
		
		public String  getSubjectName() {
			return subjectName;
		}

		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}

		
		   
		public ContractInfo getProjId() {
			return projId;
		}

		public void setProjId(ContractInfo projId) {
			this.projId = projId;
		}

		
		public String getRollBack() {
			return rollBack;
		}

		public void setRollBack(String rollBack) {
			this.rollBack = rollBack;
		}

		@Override
		public String toString() {
			return "InterEvidenceInfo [id=" + id + ", evidenceNumber=" + evidenceNumber + ", evidenceSummary="
					+ evidenceSummary + ", subjectNumber=" + subjectNumber + ", debit=" + debit + ", credit=" + credit
					+ ", happenDate=" + happenDate + ", clientId=" + clientId + ", vndrId=" + vndrId + ", accSetNumber="
					+ accSetNumber + ", accoutingUnit=" + accoutingUnit + ", accYear=" + accYear + ", accMonth="
					+ accMonth + ", lineNumber=" + lineNumber + ", subjectName=" + subjectName + ", moduleName="
					+ moduleName + ", expFlag=" + expFlag + ", exp_date=" + exp_date + ", expUser=" + expUser
					+ ", generateDate=" + generateDate + ", interContractId=" + interContractId + ", voucherStatus="
					+ voucherStatus + ", voucherFile=" + voucherFile + ", ebankFactDate=" + ebankFactDate
					+ ", recordingVoucher=" + recordingVoucher + ", entryDC=" + entryDC + ", projId=" + projId
					+ ", rollBack=" + rollBack + ", creator=" + creator + ", createDate=" + createDate
					+ ", modificator=" + modificator + ", modifyDate=" + modifyDate + "]";
		}

	

		
		

		
		

		
		
		
		
	

		

		

	

		

		
		}
		

