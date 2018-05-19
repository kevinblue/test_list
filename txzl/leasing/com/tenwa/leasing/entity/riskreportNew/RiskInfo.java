package com.tenwa.leasing.entity.riskreportNew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
@Entity
@FieldName(name = "风险报告")
@Table(name = "RISK_INFO")

public class RiskInfo {


		@Id
		@GeneratedValue(generator = "paymentableGenerator")
		@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
		@Column(length = 32)
		private String id;

		@FieldName(name = "合同编号")
		@JoinColumn(name="CONTRACT_ID")
		@OneToOne
		private ContractInfo contractId;

		@FieldName(name = "风险名称")
		@Column(name = "RISK_NAME", length = 300)
		private String riskName;
		
		@FieldName(name = "风险类型")
		@Column(name = "RISK_TYPE", length = 300)
		private String riskType;
		
		@ManyToOne
		@FieldName(name = "风险可能性")
		@JoinColumn(name = "RISK_POSS")
		private DictionaryData riskPoss;
		
		@FieldName(name = "可能性评分")
		@Column(name = "POSS_SCORE", length = 300)
		private String possScore;
		
		@FieldName(name = "风险概率")
		@Column(name = "RISK_CHANCE", length = 300)
		private String riskChance;
		
		@FieldName(name = "风险频率")
		@Column(name = "RISK_QUENCY", length = 300)
		private String riskQuency;
		
		@ManyToOne
		@FieldName(name = "风险影响程度")
		@JoinColumn(name = "RISK_DEGREE")
		private DictionaryData riskDegree;
		
		@FieldName(name = "影响程度评分")
		@Column(name = "DEGREE_SCORE", length = 300)
		private String degreeScore;
		
		@FieldName(name = "风险定性影响")
		@Column(name = "RISK_QUALITATIVE", length = 800)
		private String riskQualitative;
		
		@FieldName(name = "风险定量影响")
		@Column(name = "RISK_RATION", length = 800)
		private String riskRation;
		
		@FieldName(name = "风险等级")
		@Column(name = "RISK_LEVEL", length = 300)
		private String riskLevel;

		@FieldName(name = "风险类别")
		@Column(name = "RISK_CATEGORIES", length = 300)
		private String riskCategories;

		@FieldName(name = "风险描述")
		@Column(name = "RISK_DESCRIPTION", length = 3000)
		private String riskDescription;
		
		@FieldName(name = "风险控制策略")
		@Column(name = "RISK_CONTROL_STRATEGY", length = 3000)
		private String riskControlStrategy;
		
		@FieldName(name = "风险控制措施")
		@Column(name = "RISK_CONTROL_MEASURES", length = 3000)
		private String riskControlMeasures;
//必填字段
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

		public ContractInfo getContractId() {
			return contractId;
		}

		public void setContractId(ContractInfo contractId) {
			this.contractId = contractId;
		}

		public String getRiskName() {
			return riskName;
		}

		public void setRiskName(String riskName) {
			this.riskName = riskName;
		}

		public String getRiskType() {
			return riskType;
		}

		public void setRiskType(String riskType) {
			this.riskType = riskType;
		}

		public String getRiskLevel() {
			return riskLevel;
		}

		public void setRiskLevel(String riskLevel) {
			this.riskLevel = riskLevel;
		}

		public String getRiskCategories() {
			return riskCategories;
		}

		public void setRiskCategories(String riskCategories) {
			this.riskCategories = riskCategories;
		}

		public String getRiskDescription() {
			return riskDescription;
		}

		public void setRiskDescription(String riskDescription) {
			this.riskDescription = riskDescription;
		}

		public String getRiskControlStrategy() {
			return riskControlStrategy;
		}

		public void setRiskControlStrategy(String riskControlStrategy) {
			this.riskControlStrategy = riskControlStrategy;
		}

		public String getRiskControlMeasures() {
			return riskControlMeasures;
		}

		public void setRiskControlMeasures(String riskControlMeasures) {
			this.riskControlMeasures = riskControlMeasures;
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

		public DictionaryData getRiskPoss() {
			return riskPoss;
		}

		public void setRiskPoss(DictionaryData riskPoss) {
			this.riskPoss = riskPoss;
		}

		public String getPossScore() {
			return possScore;
		}

		public void setPossScore(String possScore) {
			this.possScore = possScore;
		}

		public String getRiskChance() {
			return riskChance;
		}

		public void setRiskChance(String riskChance) {
			this.riskChance = riskChance;
		}

		public String getRiskQuency() {
			return riskQuency;
		}

		public void setRiskQuency(String riskQuency) {
			this.riskQuency = riskQuency;
		}

		public DictionaryData getRiskDegree() {
			return riskDegree;
		}

		public void setRiskDegree(DictionaryData riskDegree) {
			this.riskDegree = riskDegree;
		}

		public String getDegreeScore() {
			return degreeScore;
		}

		public void setDegreeScore(String degreeScore) {
			this.degreeScore = degreeScore;
		}

		public String getRiskQualitative() {
			return riskQualitative;
		}

		public void setRiskQualitative(String riskQualitative) {
			this.riskQualitative = riskQualitative;
		}

		public String getRiskRation() {
			return riskRation;
		}

		public void setRiskRation(String riskRation) {
			this.riskRation = riskRation;
		}
		
		
}
