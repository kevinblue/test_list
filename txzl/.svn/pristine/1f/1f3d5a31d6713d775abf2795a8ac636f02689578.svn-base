package com.tenwa.leasing.entity.base;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name = "OWN_INFO")
public class OwnInfo implements java.io.Serializable {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="出租人")
	@Column(name="OWN_NAME",length=100)
	private String ownName;

	@FieldName(name="出租人编号")
	@Column(name="OWN_NUMBER",length=10)
	private String ownNumber;
	
	@FieldName(name="委托代理人")
	@Column(name="LEASECONSIGNER",length=50)
	private String leaseconsigner;

	@FieldName(name="注册地址")
	@Column(name="LEASE_REGISTER_ADDR",length=200)
	private String leaseRegisterAddr;

	@FieldName(name="通讯地址")
	@Column(name="LEASE_ADDR",length=200)
	private String leaseAddr;

	@FieldName(name="法定代表人")
	@Column(name="LEASE_PERSON",length=100)
	private String leasePerson;
	
	@Column(name="ORG_CODE", length=50)
	@FieldName(name="组织结构代码")
	private String orgCode;	

	@FieldName(name="邮  编")
	@Column(name="LEASE_POSTCODE",length=10)
	private String leasePostcode;

	@FieldName(name="联系人")
	@Column(name="LEASE_LINKMAN",length=50)
	private String leaseLinkman;

	@FieldName(name="电话")
	@Column(name="LEASE_TEL",length=100)
	private String leaseTel;

	@FieldName(name="传真")
	@Column(name="LEASE_FAX",length=20)
	private String leaseFax;

	@FieldName(name="电子邮件")
	@Column(name="LEASE_EMAIL",length=100)
	private String leaseEmail;
	
	@FieldName(name="本方账户信息")
	@OneToMany(mappedBy="ownId", fetch=FetchType.LAZY)
	private Set<OwnAccount>  ownAccounts=new HashSet<OwnAccount>();

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

	public String getOwnName() {
		return ownName;
	}

	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}

	public String getOwnNumber() {
		return ownNumber;
	}

	public void setOwnNumber(String ownNumber) {
		this.ownNumber = ownNumber;
	}

	public String getLeaseconsigner() {
		return leaseconsigner;
	}

	public void setLeaseconsigner(String leaseconsigner) {
		this.leaseconsigner = leaseconsigner;
	}

	public String getLeaseRegisterAddr() {
		return leaseRegisterAddr;
	}

	public void setLeaseRegisterAddr(String leaseRegisterAddr) {
		this.leaseRegisterAddr = leaseRegisterAddr;
	}

	public String getLeaseAddr() {
		return leaseAddr;
	}

	public void setLeaseAddr(String leaseAddr) {
		this.leaseAddr = leaseAddr;
	}

	public String getLeasePerson() {
		return leasePerson;
	}

	public void setLeasePerson(String leasePerson) {
		this.leasePerson = leasePerson;
	}

	public String getLeasePostcode() {
		return leasePostcode;
	}

	public void setLeasePostcode(String leasePostcode) {
		this.leasePostcode = leasePostcode;
	}

	public String getLeaseLinkman() {
		return leaseLinkman;
	}

	public void setLeaseLinkman(String leaseLinkman) {
		this.leaseLinkman = leaseLinkman;
	}

	public String getLeaseTel() {
		return leaseTel;
	}

	public void setLeaseTel(String leaseTel) {
		this.leaseTel = leaseTel;
	}

	public String getLeaseFax() {
		return leaseFax;
	}

	public void setLeaseFax(String leaseFax) {
		this.leaseFax = leaseFax;
	}

	public String getLeaseEmail() {
		return leaseEmail;
	}

	public void setLeaseEmail(String leaseEmail) {
		this.leaseEmail = leaseEmail;
	}

	public Set<OwnAccount> getOwnAccounts() {
		return ownAccounts;
	}

	public void setOwnAccounts(Set<OwnAccount> ownAccounts) {
		this.ownAccounts = ownAccounts;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
    
}