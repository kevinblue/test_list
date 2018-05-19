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



/**
 * 
 * <p>用友V8凭证实体，目前仅用于欧力士项目，如需改动请联系欧力士项目负责人，防止冲突情况出现。</p>
 * <p>2014-2-12</p>
 * @author sea
 * @version 4.5
 */
@Entity
@FieldName(name = "用友V8凭证表")
@Table(name="VOUCHER_V8")
public class InterV8Vouchers {
/*
"F1日期F2类别","F3凭证号F4附单据数","F5摘要F6科目编码","F7借方F8贷方","F9数量F10外币",
"F11汇率","F12制单人","F13结算方式","F14票号","F15发生日期","F16部门编码","F17个人编码","F18客户编码","F19供应商编码","F20业务员",
"F21项目编码","F22自定义项1","F23自定义项2","F24自由项1","F25自由项2","F26自由项3","F27自由项4","F28自由项5","F29自由项6","F30自由项7",
"F31自由项8","F32自由项9","F33自由项10","F34外部系统标识","F35业务类型","F36单据类型","F37单据日期","F38单据号","F39凭证是否可改","F40分录是否可增删",
"F41合计金额是否保值","F42数值是否可改","F43科目是否可改","F44受控科目","F45往来是否可改","F46部门是否可改","F47项目是否可改","F48往来项是否必输","F49账套号","F50核算单位",
"F51会计年度","F52会计期间","F53类别顺序号","F54凭证号","F55审核人","F56记账人","F57是否记账","F58出纳人","F59行号",
"F60外币名称","数量单位","F61单价","F62科目名称","F63部门名称","F64个人名称","F65客户简称","F66供应商简称","F67项目名称","F68项目大类编码","F69项目大类名称","F70对方科目",
"F71银行两清标志","F72往来两清标志","F73银行核销标志","F74外部系统名称","F75外部账套号","F76外部会计年度","F77外部会计期间","F78外部制单日期","F79外部系统版本","F80凭证标识",
"F81分录自动编号","F82唯一标识","F83主管签字","F84自由项11","F85自由项12","F86自由项13","F87自由项14","F88自由项15","F89自由项16","F90审核日期："
*/	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id ;
	
	@FieldName(name="凭证输出起始标志位")
	@Column(name="EVIDENCE_MESSAGE", length=50)	
	private String evidenceMessage;
	
	@FieldName(name="基本类别")
	@Column(name="EVIDENCE_TYPE", length=50)	
	private String evidenceType;
	
	@ManyToOne
	@FieldName(name="公司所属编号")
	@JoinColumn(name="DEPT_ID")
	private DictionaryData deptId ;
	
	@ManyToOne
	@FieldName(name="公司所属名称")
	@JoinColumn(name="DEPT_NAME")
	private DictionaryData deptName ;
	
	@FieldName(name="会计期间-年")
	@Column(name="PERIODYEAR", length=10)
	private String periodYear ; 
	
	@FieldName(name="会计处理日")
	@Column(name="F1", length=20)
	private String f1 ;
	
	@FieldName(name="凭证字")
	@Column(name="F2", length=10)
	private String f2 ;
	
	@FieldName(name="凭证号")
	@Column(name="F3", length=20)	
	private String f3;
	
	@FieldName(name="附单据号")
	@Column(name="F4", length=20)	
	private String f4;
	
	@FieldName(name="摘要")
	@Column(name="F5", length=900)	
	private String f5;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="科目编号")
	@JoinColumn(name="F6")
	private VoucherassStactsConfig f6;
	
	@FieldName(name="借方金额")
	@Column(name="F7",precision=22,scale=2)
	private BigDecimal f7 ;//借方金额 分录行号为1就=原币金额 分录行号为2就为0
	
	@FieldName(name="贷方金额")
	@Column(name="F8",precision=22,scale=2)
	private BigDecimal f8 ;//贷方金额  分录行号为2就=原币金额 分录行号为1就为0
	
	@FieldName(name="数量")
	@Column(name="F9", length=10)
	private Integer f9 ;
	
	@FieldName(name="外币")
	@Column(name="F10", length=10)	
	private String f10;
	
	@FieldName(name="汇率")
	@Column(name="F11",precision=22,scale=2)
	private BigDecimal f11 ;
	
	@FieldName(name="制单人")
	@Column(name="F12", length=200)	
	private String f12;
	
	@FieldName(name="结算方式")
	@Column(name="F13", length=100)	
	private String f13;
	
	@FieldName(name="票号")
	@Column(name="F14", length=100)	
	private String f14;
	
	@FieldName(name="到账日期")
	@Column(name="F15", length=20)
	private String f15 ;
	
	@ManyToOne
	@FieldName(name="部门编码")
	@JoinColumn(name="F16")
	private Department f16 ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="个人编码")
	@JoinColumn(name="F17")
	private User f17 ;
	
	@FieldName(name = "客户编码(合同)")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "F18")
	private ContractInfo f18 ;//欧力士客户就是合同，这里存入合同号,其余项目请具体处理
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="供应商编码")
	@JoinColumn(name="F19")
	private CustInfo f19 ;//按供应商辅助核算时带入供应商编码
	
	@FieldName(name="业务员编码")
	@Column(name="F20", length=20)
	private String f20 ;
	
	@FieldName(name = "项目编码")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name="F21")
	private ContractInfo f21 ;
	
	@FieldName(name = "自定义项1")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name="F22")
	private ContractInfo f22 ;
	
	@FieldName(name="自定义项")
	@Column(name="F23", length=200)
	private String f23 ;
	
	@FieldName(name="自定义项")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name="F24")
	private ContractInfo f24 ;
	
	@FieldName(name="自定义项")
	@Column(name="F25", length=200)
	private String f25 ;
	
	@FieldName(name="自定义项")
	@Column(name="F26", length=200)
	private String f26 ;
	
	@FieldName(name="自定义项")
	@Column(name="F27", length=200)
	private String f27 ;
	
	@FieldName(name="自定义项")
	@Column(name="F28", length=200)
	private String f28 ;
	
	@FieldName(name="自定义项")
	@Column(name="F29", length=200)
	private String f29 ;
	
	@FieldName(name="自定义项")
	@Column(name="F30", length=200)
	private String f30 ;
	
	@FieldName(name="自定义项")
	@Column(name="F31", length=200)
	private String f31 ;
	
	@FieldName(name="自定义项")
	@Column(name="F32", length=200)
	private String f32 ;
	
	@FieldName(name="自定义项")
	@Column(name="F33", length=200)
	private String f33 ;
	
	@FieldName(name="自定义项")
	@Column(name="F34", length=200)
	private String f34 ;
	
	@FieldName(name="业务类型")
	@Column(name="F35", length=200)
	private String f35 ;
	
	@FieldName(name="自定义项")
	@Column(name="F36", length=200)
	private String f36 ;
	
	@FieldName(name="自定义项")
	@Column(name="F37", length=200)
	private String f37 ;
	
	@FieldName(name="自定义项")
	@Column(name="F38", length=200)
	private String f38 ;
	
	@FieldName(name="自定义项")
	@Column(name="F39", length=200)
	private String f39 ;
	
	@FieldName(name="自定义项")
	@Column(name="F40", length=200)
	private String f40 ;
	
	@FieldName(name="自定义项")
	@Column(name="F41", length=200)
	private String f41 ;
	
	@FieldName(name="自定义项")
	@Column(name="F42", length=200)
	private String f42 ;
	
	@FieldName(name="自定义项")
	@Column(name="F43", length=200)
	private String f43 ;
	
	@FieldName(name="自定义项")
	@Column(name="F44", length=200)
	private String f44 ;
	
	@FieldName(name="自定义项")
	@Column(name="F45", length=200)
	private String f45 ;
	
	@FieldName(name="自定义项")
	@Column(name="F46", length=200)
	private String f46 ;
	
	@FieldName(name="自定义项")
	@Column(name="F47", length=200)
	private String f47 ;
	
	@FieldName(name="自定义项")
	@Column(name="F48", length=200)
	private String f48 ;
	
	@ManyToOne
	@FieldName(name="帐套")
	@JoinColumn(name="F49")
	private DictionaryData f49 ;
	
	@ManyToOne
	@FieldName(name="公司所属名称")
	@JoinColumn(name="F50")
	private DictionaryData f50 ;
	
	@FieldName(name="会计期间-年")
	@Column(name="F51", length=10)
	private String f51 ; 
	
	@FieldName(name="会计期间-月")
	@Column(name="F52", length=10)
	private String f52 ; 

	@FieldName(name="自定义项")
	@Column(name="F53", length=200)
	private String f53 ;
	
	@FieldName(name="自定义项")
	@Column(name="F54", length=200)
	private String f54 ;
	
	@FieldName(name="自定义项")
	@Column(name="F55", length=200)
	private String f55 ;
	
	@FieldName(name="自定义项")
	@Column(name="F56", length=200)
	private String f56 ;
	
	@FieldName(name="自定义项")
	@Column(name="F57", length=200)
	private String f57 ;
	
	@FieldName(name="自定义项")
	@Column(name="F58", length=200)
	private String f58 ;
	
	@FieldName(name="序列号")
	@Column(name="F59", length=200)
	private String f59 ;

	@FieldName(name="自定义项")
	@Column(name="F60", length=200)
	private String f60 ;

	@FieldName(name="自定义项")
	@Column(name="F61", length=200)
	private String f61 ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="科目名称")
	@JoinColumn(name="F62")
	private VoucherassStactsConfig f62;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="部门名称")
	@JoinColumn(name="F63")
	private Department f63 ;//按部门辅助核算时带入部门名称

	@FieldName(name="自定义项")
	@Column(name="F64", length=200)
	private String f64 ;
	
	@FieldName(name = "客户名称")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "F65")
	private ContractInfo f65 ;//欧力士客户就是合同，这里存入合同号,其余项目请具体处理
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="供应商名称")
	@JoinColumn(name="F66")
	private CustInfo f66 ;//按供应商辅助核算时带入供应商名称
	
	@FieldName(name = "合同编号")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "F67")
	private ContractInfo f67;
	
	/**
	 *大类编码，暂定为：00
	 *用友U8系统中分为：01、02、QT 
	 */
	@FieldName(name="自定义项68")
	@Column(name="F68", length=200)
	private String f68 ;

	/**
	 *大类编码，暂定为：组别
	 *用友U8系统中分为：VP合同、D合同、其它 与编码对应 
	 */
	@FieldName(name="自定义项69")
	@Column(name="F69", length=200)
	private String f69 ;
	
	@FieldName(name="对方科目")
	@Column(name="F70", length=1000)
	private String f70 ;//对方科目，多个以“，”隔开 ,借就全取贷方的所有，贷就取所有借的
						
	@FieldName(name="银行两清标志")
	@Column(name="F71", length=50)
	private String f71 ;
	
	@FieldName(name="往来两清标志")
	@Column(name="F72", length=50)
	private String f72 ;
	
	@FieldName(name="银行核销标志")
	@Column(name="F73", length=50)
	private String f73 ;
	
	@FieldName(name="外部系统名称")
	@Column(name="F74", length=200)
	private String f74 ;
	
	@FieldName(name="外部账套号")
	@Column(name="F75", length=100)
	private String f75 ;
	
	@FieldName(name="外部会计年度")
	@Column(name="F76", length=10)
	private String f76 ;
	
	@FieldName(name="外部会计期间")
	@Column(name="F77", length=10)
	private String f77 ;
	
	@FieldName(name="外部制单日期")
	@Column(name="F78", length=20)
	private String f78 ;
	
	@FieldName(name="外部系统版本")
	@Column(name="F79", length=100)
	private String f79 ;
	
	@FieldName(name="凭证标识")
	@Column(name="F80", length=10)
	private String f80 ;
	
	@FieldName(name="分录自动编号")
	@Column(name="F81", length=10)
	private String f81 ;
	
	@FieldName(name="唯一标识")
	@Column(name="F82", length=10)
	private String f82 ;
	
	@FieldName(name="主管签字")
	@Column(name="F83", length=50)
	private String f83 ;
	
	@FieldName(name="自由项11")
	@Column(name="F84", length=100)
	private String f84 ;
	
	@FieldName(name="自由项12")
	@Column(name="F85", length=100)
	private String f85 ;
	
	@FieldName(name="自由项13")
	@Column(name="F86", length=100)
	private String f86 ;
	
	@FieldName(name="自由项14")
	@Column(name="F87", length=100)
	private String f87 ;
	
	@FieldName(name="自由项15")
	@Column(name="F88", length=100)
	private String f88 ;
	
	@FieldName(name="自由项16")
	@Column(name="F89", length=100)
	private String f89 ;
	
	@FieldName(name="审核日期")
	@Column(name="F90", length=100)
	private String f90 ;
	
	@FieldName(name="凭证状态")
	@Column(name="STATUS_", length=20)
	private String status ;
	
	@FieldName(name="凭证发生日期")
	@Column(name="GENERATE_DATE", length=20)
	private String generateDate  ;//系统当前日期带时分秒
	
	@FieldName(name="导入财务系统状态")
	@Column(name="V8_FLAG", length=20)
	private String v8Flag  ;//title:'未导出',name:'0'},{title:'已导出',name:'1'
	
	@FieldName(name="业务模块")
	@Column(name="MODULE_NAME", length=500)
	private String moduleName ;
	
	@FieldName(name="导入财务系统备注")
	@Column(name="V8_MEMO", length=500)
	private String v8Memo ;
	
	@FieldName(name="导入财务系统日期")
	@Column(name="EXP_DATE", length=20)
	private String exp_date  ;
	
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
	
	@FieldName(name="借贷方向")
	@Column(name="ENTRYDC", length=10)
	private Integer entryDC;//1 借方-1 贷方
	
	@FieldName(name="红冲记录 0或null：正常凭证,1:被红冲,-1:红冲凭证,2:冲抵凭证")
	@Column(name="ROLL_BACK", length=20)
	private String rollBack ;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvidenceMessage() {
		return evidenceMessage;
	}

	public void setEvidenceMessage(String evidenceMessage) {
		this.evidenceMessage = evidenceMessage;
	}

	public String getEvidenceType() {
		return evidenceType;
	}

	public void setEvidenceType(String evidenceType) {
		this.evidenceType = evidenceType;
	}

	public DictionaryData getDeptId() {
		return deptId;
	}

	public void setDeptId(DictionaryData deptId) {
		this.deptId = deptId;
	}

	public DictionaryData getDeptName() {
		return deptName;
	}

	public void setDeptName(DictionaryData deptName) {
		this.deptName = deptName;
	}

	public String getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public String getF3() {
		return f3;
	}

	public void setF3(String f3) {
		this.f3 = f3;
	}

	public String getF4() {
		return f4;
	}

	public void setF4(String f4) {
		this.f4 = f4;
	}

	public String getF5() {
		return f5;
	}

	public void setF5(String f5) {
		this.f5 = f5;
	}

	public VoucherassStactsConfig getF6() {
		return f6;
	}

	public void setF6(VoucherassStactsConfig f6) {
		this.f6 = f6;
	}

	public BigDecimal getF7() {
		return f7;
	}

	public void setF7(BigDecimal f7) {
		this.f7 = f7;
	}

	public BigDecimal getF8() {
		return f8;
	}

	public void setF8(BigDecimal f8) {
		this.f8 = f8;
	}

	public Integer getF9() {
		return f9;
	}

	public void setF9(Integer f9) {
		this.f9 = f9;
	}

	public String getF10() {
		return f10;
	}

	public void setF10(String f10) {
		this.f10 = f10;
	}

	public BigDecimal getF11() {
		return f11;
	}

	public void setF11(BigDecimal f11) {
		this.f11 = f11;
	}

	public String getF12() {
		return f12;
	}

	public void setF12(String f12) {
		this.f12 = f12;
	}

	public String getF13() {
		return f13;
	}

	public void setF13(String f13) {
		this.f13 = f13;
	}

	public String getF14() {
		return f14;
	}

	public void setF14(String f14) {
		this.f14 = f14;
	}

	public String getF15() {
		return f15;
	}

	public void setF15(String f15) {
		this.f15 = f15;
	}

	public Department getF16() {
		return f16;
	}

	public void setF16(Department f16) {
		this.f16 = f16;
	}

	public User getF17() {
		return f17;
	}

	public void setF17(User f17) {
		this.f17 = f17;
	}

	public ContractInfo getF18() {
		return f18;
	}

	public void setF18(ContractInfo f18) {
		this.f18 = f18;
	}

	public CustInfo getF19() {
		return f19;
	}

	public void setF19(CustInfo f19) {
		this.f19 = f19;
	}

	public String getF20() {
		return f20;
	}

	public void setF20(String f20) {
		this.f20 = f20;
	}

	public ContractInfo getF21() {
		return f21;
	}

	public void setF21(ContractInfo f21) {
		this.f21 = f21;
	}

	public ContractInfo getF22() {
		return f22;
	}

	public void setF22(ContractInfo f22) {
		this.f22 = f22;
	}

	public String getF23() {
		return f23;
	}

	public void setF23(String f23) {
		this.f23 = f23;
	}

	

	public ContractInfo getF24() {
		return f24;
	}

	public void setF24(ContractInfo f24) {
		this.f24 = f24;
	}

	public String getF25() {
		return f25;
	}

	public void setF25(String f25) {
		this.f25 = f25;
	}

	public String getF26() {
		return f26;
	}

	public void setF26(String f26) {
		this.f26 = f26;
	}

	public String getF27() {
		return f27;
	}

	public void setF27(String f27) {
		this.f27 = f27;
	}

	public String getF28() {
		return f28;
	}

	public void setF28(String f28) {
		this.f28 = f28;
	}

	public String getF29() {
		return f29;
	}

	public void setF29(String f29) {
		this.f29 = f29;
	}

	public String getF30() {
		return f30;
	}

	public void setF30(String f30) {
		this.f30 = f30;
	}

	public String getF31() {
		return f31;
	}

	public void setF31(String f31) {
		this.f31 = f31;
	}

	public String getF32() {
		return f32;
	}

	public void setF32(String f32) {
		this.f32 = f32;
	}

	public String getF33() {
		return f33;
	}

	public void setF33(String f33) {
		this.f33 = f33;
	}

	public String getF34() {
		return f34;
	}

	public void setF34(String f34) {
		this.f34 = f34;
	}

	public String getF35() {
		return f35;
	}

	public void setF35(String f35) {
		this.f35 = f35;
	}

	public String getF36() {
		return f36;
	}

	public void setF36(String f36) {
		this.f36 = f36;
	}

	public String getF37() {
		return f37;
	}

	public void setF37(String f37) {
		this.f37 = f37;
	}

	public String getF38() {
		return f38;
	}

	public void setF38(String f38) {
		this.f38 = f38;
	}

	public String getF39() {
		return f39;
	}

	public void setF39(String f39) {
		this.f39 = f39;
	}

	public String getF40() {
		return f40;
	}

	public void setF40(String f40) {
		this.f40 = f40;
	}

	public String getF41() {
		return f41;
	}

	public void setF41(String f41) {
		this.f41 = f41;
	}

	public String getF42() {
		return f42;
	}

	public void setF42(String f42) {
		this.f42 = f42;
	}

	public String getF43() {
		return f43;
	}

	public void setF43(String f43) {
		this.f43 = f43;
	}

	public String getF44() {
		return f44;
	}

	public void setF44(String f44) {
		this.f44 = f44;
	}

	public String getF45() {
		return f45;
	}

	public void setF45(String f45) {
		this.f45 = f45;
	}

	public String getF46() {
		return f46;
	}

	public void setF46(String f46) {
		this.f46 = f46;
	}

	public String getF47() {
		return f47;
	}

	public void setF47(String f47) {
		this.f47 = f47;
	}

	public String getF48() {
		return f48;
	}

	public void setF48(String f48) {
		this.f48 = f48;
	}

	public DictionaryData getF49() {
		return f49;
	}

	public void setF49(DictionaryData f49) {
		this.f49 = f49;
	}

	public DictionaryData getF50() {
		return f50;
	}

	public void setF50(DictionaryData f50) {
		this.f50 = f50;
	}

	public String getF51() {
		return f51;
	}

	public void setF51(String f51) {
		this.f51 = f51;
	}

	public String getF52() {
		return f52;
	}

	public void setF52(String f52) {
		this.f52 = f52;
	}

	public String getF53() {
		return f53;
	}

	public void setF53(String f53) {
		this.f53 = f53;
	}

	public String getF54() {
		return f54;
	}

	public void setF54(String f54) {
		this.f54 = f54;
	}

	public String getF55() {
		return f55;
	}

	public void setF55(String f55) {
		this.f55 = f55;
	}

	public String getF56() {
		return f56;
	}

	public void setF56(String f56) {
		this.f56 = f56;
	}

	public String getF57() {
		return f57;
	}

	public void setF57(String f57) {
		this.f57 = f57;
	}

	public String getF58() {
		return f58;
	}

	public void setF58(String f58) {
		this.f58 = f58;
	}

	public String getF59() {
		return f59;
	}

	public void setF59(String f59) {
		this.f59 = f59;
	}

	public String getF60() {
		return f60;
	}

	public void setF60(String f60) {
		this.f60 = f60;
	}

	public String getF61() {
		return f61;
	}

	public void setF61(String f61) {
		this.f61 = f61;
	}

	public VoucherassStactsConfig getF62() {
		return f62;
	}

	public void setF62(VoucherassStactsConfig f62) {
		this.f62 = f62;
	}

	public Department getF63() {
		return f63;
	}

	public void setF63(Department f63) {
		this.f63 = f63;
	}

	public String getF64() {
		return f64;
	}

	public void setF64(String f64) {
		this.f64 = f64;
	}

	public ContractInfo getF65() {
		return f65;
	}

	public void setF65(ContractInfo f65) {
		this.f65 = f65;
	}

	public CustInfo getF66() {
		return f66;
	}

	public void setF66(CustInfo f66) {
		this.f66 = f66;
	}

	public ContractInfo getF67() {
		return f67;
	}

	public void setF67(ContractInfo f67) {
		this.f67 = f67;
	}

	public String getF68() {
		return f68;
	}

	public void setF68(String f68) {
		this.f68 = f68;
	}

	public String getF69() {
		return f69;
	}

	public void setF69(String f69) {
		this.f69 = f69;
	}

	public String getF70() {
		return f70;
	}

	public void setF70(String f70) {
		this.f70 = f70;
	}

	public String getF71() {
		return f71;
	}

	public void setF71(String f71) {
		this.f71 = f71;
	}

	public String getF72() {
		return f72;
	}

	public void setF72(String f72) {
		this.f72 = f72;
	}

	public String getF73() {
		return f73;
	}

	public void setF73(String f73) {
		this.f73 = f73;
	}

	public String getF74() {
		return f74;
	}

	public void setF74(String f74) {
		this.f74 = f74;
	}

	public String getF75() {
		return f75;
	}

	public void setF75(String f75) {
		this.f75 = f75;
	}

	public String getF76() {
		return f76;
	}

	public void setF76(String f76) {
		this.f76 = f76;
	}

	public String getF77() {
		return f77;
	}

	public void setF77(String f77) {
		this.f77 = f77;
	}

	public String getF78() {
		return f78;
	}

	public void setF78(String f78) {
		this.f78 = f78;
	}

	public String getF79() {
		return f79;
	}

	public void setF79(String f79) {
		this.f79 = f79;
	}

	public String getF80() {
		return f80;
	}

	public void setF80(String f80) {
		this.f80 = f80;
	}

	public String getF81() {
		return f81;
	}

	public void setF81(String f81) {
		this.f81 = f81;
	}

	public String getF82() {
		return f82;
	}

	public void setF82(String f82) {
		this.f82 = f82;
	}

	public String getF83() {
		return f83;
	}

	public void setF83(String f83) {
		this.f83 = f83;
	}

	public String getF84() {
		return f84;
	}

	public void setF84(String f84) {
		this.f84 = f84;
	}

	public String getF85() {
		return f85;
	}

	public void setF85(String f85) {
		this.f85 = f85;
	}

	public String getF86() {
		return f86;
	}

	public void setF86(String f86) {
		this.f86 = f86;
	}

	public String getF87() {
		return f87;
	}

	public void setF87(String f87) {
		this.f87 = f87;
	}

	public String getF88() {
		return f88;
	}

	public void setF88(String f88) {
		this.f88 = f88;
	}

	public String getF89() {
		return f89;
	}

	public void setF89(String f89) {
		this.f89 = f89;
	}

	public String getF90() {
		return f90;
	}

	public void setF90(String f90) {
		this.f90 = f90;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(String generateDate) {
		this.generateDate = generateDate;
	}

	public String getV8Flag() {
		return v8Flag;
	}

	public void setV8Flag(String v8Flag) {
		this.v8Flag = v8Flag;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getV8Memo() {
		return v8Memo;
	}

	public void setV8Memo(String v8Memo) {
		this.v8Memo = v8Memo;
	}

	public String getExp_date() {
		return exp_date;
	}

	public void setExp_date(String expDate) {
		exp_date = expDate;
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
	/**
	  * <p>借贷方法1 借方-1 贷方。</p>
	  * @author sea
	  * @return
	 */
	public Integer getEntryDC() {
		return entryDC;
	}

	/**
	 * <p>借贷方法1 借方-1 贷方。</p>
	 * @author sea
	 * @return
	 */
	public void setEntryDC(Integer entryDC) {
		this.entryDC = entryDC;
	}

	public String getRollBack() {
		return rollBack;
	}

	public void setRollBack(String rollBack) {
		this.rollBack = rollBack;
	}

	@Override
	public String toString() {
		return "InterV8Vouchers [id=" + id + ", evidenceMessage="
				+ evidenceMessage + ", evidenceType=" + evidenceType
				+ ", deptId=" + deptId + ", deptName=" + deptName
				+ ", periodYear=" + periodYear + ", f1=" + f1 + ", f2=" + f2
				+ ", f3=" + f3 + ", f4=" + f4 + ", f5=" + f5 + ", f6=" + f6
				+ ", f7=" + f7 + ", f8=" + f8 + ", f9=" + f9 + ", f10=" + f10
				+ ", f11=" + f11 + ", f12=" + f12 + ", f13=" + f13 + ", f14="
				+ f14 + ", f15=" + f15 + ", f16=" + f16 + ", f17=" + f17
				+ ", f18=" + f18 + ", f19=" + f19 + ", f20=" + f20 + ", f21="
				+ f21 + ", f22=" + f22 + ", f23=" + f23 + ", f24=" + f24
				+ ", f25=" + f25 + ", f26=" + f26 + ", f27=" + f27 + ", f28="
				+ f28 + ", f29=" + f29 + ", f30=" + f30 + ", f31=" + f31
				+ ", f32=" + f32 + ", f33=" + f33 + ", f34=" + f34 + ", f35="
				+ f35 + ", f36=" + f36 + ", f37=" + f37 + ", f38=" + f38
				+ ", f39=" + f39 + ", f40=" + f40 + ", f41=" + f41 + ", f42="
				+ f42 + ", f43=" + f43 + ", f44=" + f44 + ", f45=" + f45
				+ ", f46=" + f46 + ", f47=" + f47 + ", f48=" + f48 + ", f49="
				+ f49 + ", f50=" + f50 + ", f51=" + f51 + ", f52=" + f52
				+ ", f53=" + f53 + ", f54=" + f54 + ", f55=" + f55 + ", f56="
				+ f56 + ", f57=" + f57 + ", f58=" + f58 + ", f59=" + f59
				+ ", f60=" + f60 + ", f61=" + f61 + ", f62=" + f62 + ", f63="
				+ f63 + ", f64=" + f64 + ", f65=" + f65 + ", f66=" + f66
				+ ", f67=" + f67 + ", f68=" + f68 + ", f69=" + f69 + ", f70="
				+ f70 + ", f71=" + f71 + ", f72=" + f72 + ", f73=" + f73
				+ ", f74=" + f74 + ", f75=" + f75 + ", f76=" + f76 + ", f77="
				+ f77 + ", f78=" + f78 + ", f79=" + f79 + ", f80=" + f80
				+ ", f81=" + f81 + ", f82=" + f82 + ", f83=" + f83 + ", f84="
				+ f84 + ", f85=" + f85 + ", f86=" + f86 + ", f87=" + f87
				+ ", f88=" + f88 + ", f89=" + f89 + ", f90=" + f90
				+ ", status=" + status + ", generateDate=" + generateDate
				+ ", v8Flag=" + v8Flag + ", moduleName=" + moduleName
				+ ", v8Memo=" + v8Memo + ", exp_date=" + exp_date
				+ ", creator=" + creator + ", createDate=" + createDate
				+ ", modificator=" + modificator + ", modifyDate=" + modifyDate
				+ ", entryDC=" + entryDC + ", rollBack=" + rollBack + "]";
	}
	
	
}
