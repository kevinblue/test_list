package com.tenwa.leasing.entity.litigation;

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
import com.tenwa.leasing.entity.lawmng.LawApproval;
@Entity
@FieldName(name = "诉讼结论登记")
@Table(name="LAW_LITIGATION_CONCLUSION")
public class LitigationDetail {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="结论类型")
	@Column(name="CONCLUSION_TYPE")
	private String conclusiontype ;
	
	@FieldName(name = "判决/调解日期")
	@Column(name="DECISIONDATE")
	private String decisiondate;

	@FieldName(name="是否上诉")
	@Column(name="APPEAL", length=1000)
	private String appeal ;
	
	@ManyToOne(targetEntity=LawApproval.class,fetch=FetchType.LAZY)
	@JoinColumn(name="LAWAPPROVAL_ID")
	@FieldName(name="法务申请流程信息主键  id")
	private LawApproval lawApproval;
	
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

	public String getConclusiontype() {
		return conclusiontype;
	}

	public void setConclusiontype(String conclusiontype) {
		this.conclusiontype = conclusiontype;
	}

	public String getDecisiondate() {
		return decisiondate;
	}

	public void setDecisiondate(String decisiondate) {
		this.decisiondate = decisiondate;
	}

	public String getAppeal() {
		return appeal;
	}

	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}

	public LawApproval getLawApproval() {
		return lawApproval;
	}

	public void setLawApproval(LawApproval lawApproval) {
		this.lawApproval = lawApproval;
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
