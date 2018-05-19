package com.tenwa.leasing.entity.contract;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
/**
 * ContractSignatory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTRACT_DOC_LIST")
@FieldName(name="合同文件表")
public class ContractDocList implements java.io.Serializable {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	
	@FieldName(name="文件名称")
	@Column(name="DOC_NAME",length=1000)
	private String docname;

	@FieldName(name="是否借出")
	@Column(name="IS_CHECK",length=10)
	private String ischeck;
	
	@FieldName(name="借出备注")
	@Column(name="MEMOBORROW",length=2000)
	private String memoborrow;
	
	@FieldName(name="预计归还日期")
	@Column(name="PRE_BACK_DATE",length=100)
	private String prebackdate;
	
	@FieldName(name="是否归还")
	@Column(name="IS_BACK",length=10)
	private String isback;
	
	@FieldName(name="归还备注")
	@Column(name="MEMOBACK",length=2000)
	private String memoback;
	
	@FieldName(name="归还日期")
	@Column(name="BACK_DATE",length=100)
	private String backdate;
	
	@FieldName(name="已办")
	@Column(name="DEAL_ALREADY",length=20)
	private String dealAlready;
	
	@FieldName(name="待办")
	@Column(name="DEAL_WAIT",length=20)
	private String dealWait;
	
	@FieldName(name="待补")
	@Column(name="SUBMIT_WAIT",length=20)
	private String submitWait;

	@FieldName(name="无")
	@Column(name="NOT_HAVE",length=20)
	private String notHave;
	
	@FieldName(name="文件备注")
	@Column(name="DOC_MEMO",length=200)
	private String docMemo;
	
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
	
	@FieldName(name="序号")
	@Column(name="DOC_NO", length=20)
	private String docNo;
	
	@FieldName(name="资料类型")
	@Column(name="DOC_TYPE",length=200)
	private String docType;

	@FieldName(name="提交状态[注：(P -待收集的文件， √应收集且已经收到的文件， N/A 不需要收集该文件）]")
	@Column(name="IS_SUBMIT",length=20)
	private String isSubmit;
	
	@FieldName(name="张数")
	@Column(name="DOC_NUM",length=20)
	private String docNum;
	
	@FieldName(name="张数")
	@Column(name="PAGE_NUM",length=20)
	private String pageNum;
	
	@FieldName(name="流程类型【0:信审资料清单;1:合同文本列表;2:放款资料清单】")
	@Column(name="WORKFLOW_TYPE",length=20)
	private String workflowType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	

	public String getMemoborrow() {
		return memoborrow;
	}

	public void setMemoborrow(String memoborrow) {
		this.memoborrow = memoborrow;
	}

	public String getIsback() {
		return isback;
	}

	public void setIsback(String isback) {
		this.isback = isback;
	}

	public String getMemoback() {
		return memoback;
	}

	public void setMemoback(String memoback) {
		this.memoback = memoback;
	}

	public String getBackdate() {
		return backdate;
	}

	public void setBackdate(String backdate) {
		this.backdate = backdate;
	}

	

	public String getPrebackdate() {
		return prebackdate;
	}

	public void setPrebackdate(String prebackdate) {
		this.prebackdate = prebackdate;
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

	public String getDealAlready() {
		return dealAlready;
	}

	public void setDealAlready(String dealAlready) {
		this.dealAlready = dealAlready;
	}

	public String getDealWait() {
		return dealWait;
	}

	public void setDealWait(String dealWait) {
		this.dealWait = dealWait;
	}

	public String getSubmitWait() {
		return submitWait;
	}

	public void setSubmitWait(String submitWait) {
		this.submitWait = submitWait;
	}

	public String getNotHave() {
		return notHave;
	}

	public void setNotHave(String notHave) {
		this.notHave = notHave;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocMemo() {
		return docMemo;
	}

	public void setDocMemo(String docMemo) {
		this.docMemo = docMemo;
	}

	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

}