package com.reckon.entity.proj;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.proj.ProjInfo;

@Entity
@FieldName(name = "付款前提表")
@Table(name = "PROJ_PAYMENT_PREMISE_CONDITION")
public class ProjPaymentPremiseCondition {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "资金收付计划编号")
	@Column(name = "PAYMENT_ID", length = 32)
	private String paymentid;
	
	@FieldName(name = "项目编号")
	@Column(name = "PROJ_NUMBER", length = 32)
	private String projnumber;

	@FieldName(name = "付款节点")
	@Column(name = "PAYMENT_NODE", length = 64)
	private String paymentnode;
	
	@FieldName(name = "项目编号")
	@ManyToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;
	
	@ManyToOne
	@FieldName(name = "费用类型")
	@JoinColumn(name = "FEE_TYPE")
	private DictionaryData feetype;

	@FieldName(name = "前提条件")
	@Column(name = "CONDITION_NAME", length = 200)
	private String conditionname;
	
	@ManyToOne
	@FieldName(name = "设备名称")
	@JoinColumn(name = "DEVICE_NAME")
	private DictionaryData devicename;
	
	
	@FieldName(name="备注")
	@Column(name="REMARK")
	private String   remark;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
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


	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}


	public String getProjnumber() {
		return projnumber;
	}

	public void setProjnumber(String projnumber) {
		this.projnumber = projnumber;
	}

	public String getPaymentnode() {
		return paymentnode;
	}

	public void setPaymentnode(String paymentnode) {
		this.paymentnode = paymentnode;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}



	public DictionaryData getFeetype() {
		return feetype;
	}

	public void setFeetype(DictionaryData feetype) {
		this.feetype = feetype;
	}

	public String getConditionname() {
		return conditionname;
	}

	public void setConditionname(String conditionname) {
		this.conditionname = conditionname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public DictionaryData getDevicename() {
		return devicename;
	}

	public void setDevicename(DictionaryData devicename) {
		this.devicename = devicename;
	}
	
	
}
