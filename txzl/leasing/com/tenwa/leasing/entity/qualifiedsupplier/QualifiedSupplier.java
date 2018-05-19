package com.tenwa.leasing.entity.qualifiedsupplier;

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
import com.tenwa.leasing.entity.file.BaseFile;

/**
 * 
 * @author ZYH
 * @date 2016-10-14上午09:33:10
 * @info 合格供应商名录
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合格供应商名录")
@Table(name = "QUALIFIED_SUPPLIER")
public class QualifiedSupplier {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "排名")
	@Column(name = "RANKING", length = 20)
	private String ranking;

	@FieldName(name = "分级")
	@Column(name = "CLASSIFICATION", length = 200)
	private String classification;

	@FieldName(name = "产品名称")
	@Column(name = "PRO_NAME", length = 500)
	private String proName;

	@FieldName(name = "厂家名称")
	@Column(name = "MAN_NAME", length = 200)
	private String manName;

	@FieldName(name = "所在地区")
	@Column(name = "AREA", length = 200)
	private String area;

	@FieldName(name = "总分")
	@Column(name = "TOTAL_SCORE", length = 200)
	private String totalScore;

	@FieldName(name = "详细信息地址")
	@Column(name = "DETAILED_INFO", length = 200)
	private String detailedInfo;

	@FieldName(name = "评级时间")
	@Column(name = "RATING_TIME", length = 20)
	private String ratingTime;

	@FieldName(name = "编制单位")
	@Column(name = "COM_UNIT", length = 200)
	private String comUnit;

	@FieldName(name = "编制人员")
	@Column(name = "COM_PERSON", length = 20)
	private String comPerson;

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

	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getDetailedInfo() {
		return detailedInfo;
	}

	public void setDetailedInfo(String detailedInfo) {
		this.detailedInfo = detailedInfo;
	}

	public String getRatingTime() {
		return ratingTime;
	}

	public void setRatingTime(String ratingTime) {
		this.ratingTime = ratingTime;
	}

	public String getComUnit() {
		return comUnit;
	}

	public void setComUnit(String comUnit) {
		this.comUnit = comUnit;
	}

	public String getComPerson() {
		return comPerson;
	}

	public void setComPerson(String comPerson) {
		this.comPerson = comPerson;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

}
