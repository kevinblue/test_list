package com.tenwa.leasing.entity.sapmaindatainfo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
@Entity
@FieldName(name = "SAP主数据流程")
@Table(name = "SAP_MAIN_DATA_INFO")
public class SapMainDataInfo {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@Column(name = "SAP_NUMBER", length=32)
	@FieldName(name="流程编号")
	private String sapNumber;

	@JoinColumn(name = "APPLICANT_DEPT")
	@FieldName(name="申请人部门")
	private String applicantDept;
	
	@JoinColumn(name = "APPLICANT_RENSON")
	@FieldName(name="申请原因")
	private String applicantReason;

	@JoinColumn(name = "ECR_NUMBER")
	@FieldName(name="ECR号")
	private String ecrNumber;
	
	@JoinColumn(name = "IS_RELEASEBMC")
	@FieldName(name="是否申请Release BMC")
	private String isReleaseBmc;

	@JoinColumn(name = "PROJ_NUMBER")
	@FieldName(name="项目号")
	private String projNumber;

	@JoinColumn(name = "OTHERS")
	@FieldName(name="其它")
	private String others;
	
	@JoinColumn(name = "EDIT_TEXT")
	@FieldName(name="修改内容")
	private String editText;

	@JoinColumn(name = "DESIGN_MASTER_DATA")
	@FieldName(name="设计主数据")
	private String designMasterData;

	@JoinColumn(name = "NEED_BMC")
	@FieldName(name="是否需要Release BMC")
	private String needBmc;

	@JoinColumn(name = "NEXT_STEP")
	@FieldName(name="下一步处理")
	private String nextStep;

	@JoinColumn(name = "ALREADY_BMC")
	@FieldName(name="是否已经Release BMC")
	private String alreadyBmc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="申请人")
	private User creator;
	
	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="申请时间")
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name = "MODIFY_DATE", length = 40)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSapNumber() {
		return sapNumber;
	}

	public void setSapNumber(String sapNumber) {
		this.sapNumber = sapNumber;
	}

	public String getApplicantDept() {
		return applicantDept;
	}

	public void setApplicantDept(String applicantDept) {
		this.applicantDept = applicantDept;
	}

	public String getApplicantReason() {
		return applicantReason;
	}

	public void setApplicantReason(String applicantReason) {
		this.applicantReason = applicantReason;
	}

	public String getEcrNumber() {
		return ecrNumber;
	}

	public void setEcrNumber(String ecrNumber) {
		this.ecrNumber = ecrNumber;
	}

	public String getIsReleaseBmc() {
		return isReleaseBmc;
	}

	public void setIsReleaseBmc(String isReleaseBmc) {
		this.isReleaseBmc = isReleaseBmc;
	}

	public String getProjNumber() {
		return projNumber;
	}

	public void setProjNumber(String projNumber) {
		this.projNumber = projNumber;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getEditText() {
		return editText;
	}

	public void setEditText(String editText) {
		this.editText = editText;
	}

	public String getDesignMasterData() {
		return designMasterData;
	}

	public void setDesignMasterData(String designMasterData) {
		this.designMasterData = designMasterData;
	}

	public String getNeedBmc() {
		return needBmc;
	}

	public void setNeedBmc(String needBmc) {
		this.needBmc = needBmc;
	}

	public String getNextStep() {
		return nextStep;
	}

	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}

	public String getAlreadyBmc() {
		return alreadyBmc;
	}

	public void setAlreadyBmc(String alreadyBmc) {
		this.alreadyBmc = alreadyBmc;
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
