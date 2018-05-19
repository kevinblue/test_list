package com.tenwa.leasing.entity.global;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "风场和风机采购合同对应表")
@Table(name="T_GLOBAL_CONTRACT_MESSAGE")
public class GlobalContractMessage {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;
	
	@FieldName(name="风场id")
	@Column(name="WF_ID",length=200)
	private String   wfid;
	
	@FieldName(name="scadawfid")
	@Column(name="SCADAWF_ID",length=200)
	private String   scadawfid;
	
	@FieldName(name="风机采购合同号")
	@Column(name="ORAPRO_ID",length=200)
	private String   oraproid;
	
	@FieldName(name="风电场经度")
	@Column(name="WFX",length=200)
	private String   wfX;
	
	@FieldName(name="风电场纬度")
	@Column(name="WFY",length=200)
	private String   wfY;
	
	@FieldName(name="风电场名称")
	@Column(name="wfname",length=200)
	private String   wfName;
	//必填字段
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

	public String getWfid() {
		return wfid;
	}

	public void setWfid(String wfid) {
		this.wfid = wfid;
	}

	public String getScadawfid() {
		return scadawfid;
	}

	public void setScadawfid(String scadawfid) {
		this.scadawfid = scadawfid;
	}

	public String getOraproid() {
		return oraproid;
	}

	public void setOraproid(String oraproid) {
		this.oraproid = oraproid;
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

	public String getWfX() {
		return wfX;
	}

	public void setWfX(String wfX) {
		this.wfX = wfX;
	}

	public String getWfY() {
		return wfY;
	}

	public void setWfY(String wfY) {
		this.wfY = wfY;
	}

	public String getWfName() {
		return wfName;
	}

	public void setWfName(String wfName) {
		this.wfName = wfName;
	}
	
	
	
}
