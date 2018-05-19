package com.tenwa.leasing.entity.cust;

import java.io.Serializable;

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
@FieldName(name = "合同档案查询")
@Table(name = "CONTENT_MATERIAL")
public class ContentMaterial implements Serializable {
	
	private static final long serialVersionUID = 3133647570511366377L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 90)
	@FieldName(name = "标识符")
	private String id;
	
	@FieldName(name = "合同编号")
	@Column(name = "CONTRACT_NUMBER",length=255)
	private String contractnumber;	

	@FieldName(name = "合同名称")
	@Column(name = "CONTRACT_NAME",length=255)
	private String contractname;	
	
	@FieldName(name = "对方名称")
	@Column(name = "OTHER_NAME",length=255)
	private String othername;
	
	@FieldName(name = "合同金额")
	@Column(name = "CONTRACT_MONEY",length=255)
	private String contractmoney;
	
	@FieldName(name = "对方编号")
	@Column(name = "OTHER_NUMBER",length=255)
	private String othernumber;
	
	@FieldName(name = "合同说明")
	@Column(name = "CONTRACT_EXPLAIN",length=255)
	private String contractexplain;
	
	@FieldName(name = "签订日期")
	@Column(name = "SIGN_DATE",length=255)
	private String   signdate;
	
	@FieldName(name = "负责人")
	@Column(name = "CONTRACT_LEADER",length=255)
	private String  contractleader;
	
	@FieldName(name = "档案柜号")
	@Column(name = "ARCHIVES_SARK",length=255)
	private String archivesark;

	@FieldName(name = "归档时间")
	@Column(name = "SAVE_DATE",length=255)
	private String   savedate;
	
	@FieldName(name = "档案编号")
	@Column(name = "ARCHIVES_NUMBER",length = 255)
	private String archivesnumber;

	@FieldName(name = "档案说明")
	@Column(name = "ARCHIVES_EXPLAIN",length=255)
	private String archivesexplain;

	@FieldName(name = "状态")
	@Column(name = "STATUS", length = 20)
	private String status;

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

	public String getContractnumber() {
		return contractnumber;
	}

	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}

	public String getContractname() {
		return contractname;
	}

	public void setContractname(String contractname) {
		this.contractname = contractname;
	}

	public String getOthername() {
		return othername;
	}

	public void setOthername(String othername) {
		this.othername = othername;
	}

	public String getContractmoney() {
		return contractmoney;
	}

	public void setContractmoney(String contractmoney) {
		this.contractmoney = contractmoney;
	}

	public String getOthernumber() {
		return othernumber;
	}

	public void setOthernumber(String othernumber) {
		this.othernumber = othernumber;
	}

	public String getContractexplain() {
		return contractexplain;
	}

	public void setContractexplain(String contractexplain) {
		this.contractexplain = contractexplain;
	}

	public String getSigndate() {
		return signdate;
	}

	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}

	public String getContractleader() {
		return contractleader;
	}

	public void setContractleader(String contractleader) {
		this.contractleader = contractleader;
	}

	public String getArchivesark() {
		return archivesark;
	}

	public void setArchivesark(String archivesark) {
		this.archivesark = archivesark;
	}

	public String getSavedate() {
		return savedate;
	}

	public void setSavedate(String savedate) {
		this.savedate = savedate;
	}

	public String getArchivesnumber() {
		return archivesnumber;
	}

	public void setArchivesnumber(String archivesnumber) {
		this.archivesnumber = archivesnumber;
	}

	public String getArchivesexplaint() {
		return archivesexplain;
	}

	public void setArchivesexplaint(String archivesexplaint) {
		this.archivesexplain = archivesexplaint;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getArchivesexplain() {
		return archivesexplain;
	}
	
	public void setArchivesexplain(String archivesexplain) {
		this.archivesexplain = archivesexplain;
	}
	
}
