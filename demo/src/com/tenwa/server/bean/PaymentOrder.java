package com.tenwa.server.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.server.util.FieldName;

@Entity
@FieldName(name = "付款单回传表")
@Table(name="PAYMENT_ORDER")
public class PaymentOrder {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id; 
	
	@FieldName(name="主键id")
	@Column(name="PK")
	private String pk;
	
	@FieldName(name="流程")
	@Column(name="FLOW_ID")
	private String flow_id;
	
	@FieldName(name="付款单主键 NC系统付款单主键")
	@Column(name="PK_PAYBILL")
	private String pk_paybill;
	
	
	@FieldName(name="业务单元")
	@Column(name="ORG_NAME")
	private String org_name;
	
	@FieldName(name="单据编号")
	@Column(name="BILL_NO")
	private String bill_no;
	
	@FieldName(name="支付状态")
	@Column(name="PAY_WAY")
	private String pay_way;
	
	@FieldName(name="单据日期")
	@Column(name="BILLMAKER_DATE")
	private String billmaker_date;
	
	@FieldName(name="付款银行账号")
	@Column(name="ACCOUNT_NO")
	private String account_no;
	
	@FieldName(name="付款银行账户")
	@Column(name="ACCOUNT_NAME")
	private String account_name;
	
	@FieldName(name="付款原币金额")
	@Column(name="PAY_AMT")
	private String pay_amt;
	
	@FieldName(name="部门")
	@Column(name="DEPT_NAME")
	private String dept_name;
	
	@FieldName(name="业务员")
	@Column(name="OPERATOR")
	private String operator;
	
	@FieldName(name="资金计划标识")
	@Column(name="SOURCE_BILL")
	private String source_bill;
	
	@FieldName(name="项目编码")
	@Column(name="PRJ_NO")
	private String prj_no;
	
	@FieldName(name="结算日期")
	@Column(name="SETTLE_DATE")
	private String settle_date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlow_id() {
		return flow_id;
	}

	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}

	public String getPk_paybill() {
		return pk_paybill;
	}

	public void setPk_paybill(String pk_paybill) {
		this.pk_paybill = pk_paybill;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getPay_way() {
		return pay_way;
	}

	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}

	public String getBillmaker_date() {
		return billmaker_date;
	}

	public void setBillmaker_date(String billmaker_date) {
		this.billmaker_date = billmaker_date;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getPay_amt() {
		return pay_amt;
	}

	public void setPay_amt(String pay_amt) {
		this.pay_amt = pay_amt;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSource_bill() {
		return source_bill;
	}

	public void setSource_bill(String source_bill) {
		this.source_bill = source_bill;
	}

	public String getPrj_no() {
		return prj_no;
	}

	public void setPrj_no(String prj_no) {
		this.prj_no = prj_no;
	}

	public String getSettle_date() {
		return settle_date;
	}

	public void setSettle_date(String settle_date) {
		this.settle_date = settle_date;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	@Override
	public String toString() {
		return "PaymentOrder [id=" + id + ", pk=" + pk + ", flow_id=" + flow_id
				+ ", pk_paybill=" + pk_paybill + ", org_name=" + org_name
				+ ", bill_no=" + bill_no + ", pay_way=" + pay_way
				+ ", billmaker_date=" + billmaker_date + ", account_no="
				+ account_no + ", account_name=" + account_name + ", pay_amt="
				+ pay_amt + ", dept_name=" + dept_name + ", operator="
				+ operator + ", source_bill=" + source_bill + ", prj_no="
				+ prj_no + ", settle_date=" + settle_date + "]";
	}

	

	
}
