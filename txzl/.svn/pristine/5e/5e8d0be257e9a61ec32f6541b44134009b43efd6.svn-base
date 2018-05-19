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
@Table(name = "CONTRACT_DOC_LIST_BORROW")
@FieldName(name="合同文件借阅归还表")
public class ContractDocListBorrow implements java.io.Serializable {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="文档编号")
	@OneToOne(targetEntity=ContractDocList.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_DOC_LIST_ID")
	private ContractDocList ContractDocListId;
	
	@ManyToOne
	@FieldName(name="借阅人")
	@JoinColumn(name="BORROW")
	private User borrow;
	
	@FieldName(name="借阅时间")
	@Column(name="BORROW_DATE", length=20)	
	private String borrowdate;
	
	@FieldName(name="借出备注")
	@Column(name="MEMOBORROW",length=2000)
	private String memoborrow;
	
	@FieldName(name="预计归还日期")
	@Column(name="PRE_BACK_DATE",length=100)
	private String prebackdate;
	
	@FieldName(name="文档状态--已借出/未借出")
	@Column(name="IS_BACK",length=10)
	private String isback;
	
	@FieldName(name="借阅类型")
	@Column(name="BORROW_TYPE", length=20)	
	private String borrowtype;

	public String getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(String borrowtype) {
		this.borrowtype = borrowtype;
	}

	@FieldName(name="归还备注")
	@Column(name="MEMOBACK",length=2000)
	private String memoback;
	
	@FieldName(name="归还日期")
	@Column(name="BACK_DATE",length=100)
	private String backdate;
	
	@FieldName(name="归还人")
	@Column(name="BACK_MAN",length=100)
	private String back_man;

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

	public ContractDocList getContractDocListId() {
		return ContractDocListId;
	}

	public void setContractDocListId(ContractDocList contractDocListId) {
		ContractDocListId = contractDocListId;
	}

	public User getBorrow() {
		return borrow;
	}

	public void setBorrow(User borrow) {
		this.borrow = borrow;
	}

	public String getBorrowdate() {
		return borrowdate;
	}

	public void setBorrowdate(String borrowdate) {
		this.borrowdate = borrowdate;
	}

	public String getMemoborrow() {
		return memoborrow;
	}

	public void setMemoborrow(String memoborrow) {
		this.memoborrow = memoborrow;
	}

	public String getPrebackdate() {
		return prebackdate;
	}

	public void setPrebackdate(String prebackdate) {
		this.prebackdate = prebackdate;
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

	public String getBack_man() {
		return back_man;
	}

	public void setBack_man(String back_man) {
		this.back_man = back_man;
	}
	
}