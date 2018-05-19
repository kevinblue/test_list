package com.tenwa.leasing.entity.cust;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author  caiyc
 * @date 
 * @info 重要个人
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "重要个人信息表")
@Table(name="CUST_RELATED_PERSON")
public class CustRelatedPerson {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@ManyToOne
	@FieldName(name="客户ID")
	@JoinColumn(name = "CUST_ID")
	private CustInfo custId;

	@FieldName(name="重要个人名称")
	@Column(name="PERSON_NAME_", length=200)
	private String personname;
	
	@FieldName(name="身份证号码")
	@Column(name="ID_CARD_NO", length=80)
	private String idcardno;
	
	@FieldName(name="出生年月")
	@Column(name="BIRTH_DATE", length=80)
	private String birthdate;
	
	@FieldName(name="性别[S]")
	@Column(name="SEX_", length=80)
	private String sex;
	
	@FieldName(name="关系[D]")
	@Column(name="RELATIONSHIP", length=800)
	private String relationship;
	
	@FieldName(name="是否主联系人(否,是)")
	@Column(name="MAIN_PERSON_FLAG", length=8)
	private String mainpersonflag;
	
	@FieldName(name="手机")
	@Column(name="MOBILE_NUMBER", length=80)
	private String mobilenumber;
	
	@FieldName(name="户口所在地")
	@Column(name="DOMICILE_PLACE", length=80)
	private String domicileplace;
	
	@FieldName(name="邮寄地址")
	@Column(name="MAIL_ADD", length=800)
	private String mailadd;
	
	@FieldName(name="常住地址")
	@Column(name="HOME_ADD", length=800)
	private String homeadd;
	
	@FieldName(name="邮编")
	@Column(name="POST_CODE", length=40)
	private String postcode;
	
	@FieldName(name="电话")
	@Column(name="PHONE_", length=400)
	private String phone;
	
	@ManyToOne
	@FieldName(name = "职务[D]")
	@JoinColumn(name="UNIT_POSITION")
	private DictionaryData unitPosition;
	
	@FieldName(name="任职年限")
	@Column(name="SERVICE_LIFE", length=20)
	private String servicelife;
	
	@FieldName(name="email")
	@Column(name="EMAIL_", length=400)
	private String email;
	
//	@Lob
//	@Basic(fetch = FetchType.LAZY)
	@FieldName(name="备注")
	@Column(name="CPMEMO" ,length=800)//, columnDefinition="BLOB"
	private String cpmemo;
	
	//-----------------------------下面为 通用实体字段---------------------------------------------
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getMainpersonflag() {
		return mainpersonflag;
	}

	public void setMainpersonflag(String mainpersonflag) {
		this.mainpersonflag = mainpersonflag;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getDomicileplace() {
		return domicileplace;
	}

	public void setDomicileplace(String domicileplace) {
		this.domicileplace = domicileplace;
	}

	public String getMailadd() {
		return mailadd;
	}

	public void setMailadd(String mailadd) {
		this.mailadd = mailadd;
	}

	public String getHomeadd() {
		return homeadd;
	}

	public void setHomeadd(String homeadd) {
		this.homeadd = homeadd;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public DictionaryData getUnitPosition() {
		return unitPosition;
	}

	public void setUnitPosition(DictionaryData unitPosition) {
		this.unitPosition = unitPosition;
	}

	public String getServicelife() {
		return servicelife;
	}

	public void setServicelife(String servicelife) {
		this.servicelife = servicelife;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpmemo() {
		return cpmemo;
	}

	public void setCpmemo(String cpmemo) {
		this.cpmemo = cpmemo;
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

}
