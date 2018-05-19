package com.tenwa.leasing.entity.proj;
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

@Entity
@FieldName(name = "机组选型报告")
@Table(name = "SELECTION_REPORT")
public class SelectionReport implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="项目id号")
	@JoinColumn(name = "PROJ_id")
	private ProjDevelopInfo projId;
	
	@FieldName(name="风机型号")
	@Column(name="FAN_MODEL", length=20)	
	private String fanModel;
	
	@FieldName(name="轮毂高度")
	@Column(name="HUB_HEIGHT", length=20)	
	private String hubHeight;
	
	@FieldName(name="风机台数")
	@Column(name="FAN_STATIONS", length=20)	
	private String fanStations;
	
	@FieldName(name="备选点数量")
	@Column(name="POINT_NUMBER", length=20)	
	private String pointNumber;
	
	@FieldName(name="项目理论发电量")
	@Column(name="THEROY_GENERATION", length=20)	
	private String theroyGeneration;
	
	@FieldName(name="尾流损失")
	@Column(name="WAKE_LOSSES", length=20)	
	private String wakeLosses;
	
	@FieldName(name="折减系数")
	@Column(name="REDUCTION_FACTOR", length=20)	
	private String reductionFactor;
	
	@FieldName(name="单机容量")
	@Column(name="UNIT_CAPACITY", length=20)	
	private String unitCapacity;
	
	

	@FieldName(name="总上网电量")
	@Column(name="TOTAL_POWER", length=20)	
	private String totalPower;
	
	@FieldName(name="标准小时数平均值")
	@Column(name="AVE_STANDARD", length=20)	
	private String aveStandard;
	
	@FieldName(name="标准小时数最大值")
	@Column(name="MAX_STANDARD", length=20)	
	private String maxStandard;
	
	@FieldName(name="标准小时数最小值")
	@Column(name="MIN_STANDARD", length=20)	
	private String minStandard;
	
	@FieldName(name="机组最小间距")
	@Column(name="MIN_DISTANCE", length=20)	
	private String minDistance;
    
	

	@FieldName(name="P90小时数")
	@Column(name="P_NINETY_HOURS", length=20)	
	private String pninetyhours;
	

	@FieldName(name="P75小时数")
	@Column(name="P_SEVENTYFIVE_HOURS", length=20)	
	private String pseventyfivehours;
	

	@FieldName(name="P50小时数")
	@Column(name="P_FIFTY_HOURS", length=20)	
	private String pfiftyhours;
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
	private String modifyDate;

	@FieldName(name="风电场涉及的灾害性天气")
	@JoinColumn(name="SEVERE_WEATHER")
	@ManyToOne
	private DictionaryData severeWeather;
	
	@FieldName(name="风电场涉及的敏感性因素")
	@JoinColumn(name="SENSITIVITY_FACTORS")
	@ManyToOne
	private DictionaryData  sensitivityFactors;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjDevelopInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjDevelopInfo projId) {
		this.projId = projId;
	}
	public String getUnitCapacity() {
		return unitCapacity;
	}

	public void setUnitCapacity(String unitCapacity) {
		this.unitCapacity = unitCapacity;
	}
	public String getFanModel() {
		return fanModel;
	}

	public void setFanModel(String fanModel) {
		this.fanModel = fanModel;
	}

	public String getHubHeight() {
		return hubHeight;
	}

	public void setHubHeight(String hubHeight) {
		this.hubHeight = hubHeight;
	}

	public String getFanStations() {
		return fanStations;
	}

	public void setFanStations(String fanStations) {
		this.fanStations = fanStations;
	}

	public String getPointNumber() {
		return pointNumber;
	}

	public void setPointNumber(String pointNumber) {
		this.pointNumber = pointNumber;
	}

	public String getTheroyGeneration() {
		return theroyGeneration;
	}

	public void setTheroyGeneration(String theroyGeneration) {
		this.theroyGeneration = theroyGeneration;
	}

	public String getWakeLosses() {
		return wakeLosses;
	}

	public void setWakeLosses(String wakeLosses) {
		this.wakeLosses = wakeLosses;
	}

	public String getReductionFactor() {
		return reductionFactor;
	}

	public void setReductionFactor(String reductionFactor) {
		this.reductionFactor = reductionFactor;
	}

	public String getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(String totalPower) {
		this.totalPower = totalPower;
	}

	public String getAveStandard() {
		return aveStandard;
	}

	public void setAveStandard(String aveStandard) {
		this.aveStandard = aveStandard;
	}

	public String getMaxStandard() {
		return maxStandard;
	}

	public void setMaxStandard(String maxStandard) {
		this.maxStandard = maxStandard;
	}

	public String getMinStandard() {
		return minStandard;
	}

	public void setMinStandard(String minStandard) {
		this.minStandard = minStandard;
	}

	public String getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(String minDistance) {
		this.minDistance = minDistance;
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

	public DictionaryData getSevereWeather() {
		return severeWeather;
	}

	public void setSevereWeather(DictionaryData severeWeather) {
		this.severeWeather = severeWeather;
	}

	public DictionaryData getSensitivityFactors() {
		return sensitivityFactors;
	}

	public void setSensitivityFactors(DictionaryData sensitivityFactors) {
		this.sensitivityFactors = sensitivityFactors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPninetyhours() {
		return pninetyhours;
	}

	public void setPninetyhours(String pninetyhours) {
		this.pninetyhours = pninetyhours;
	}

	public String getPseventyfivehours() {
		return pseventyfivehours;
	}

	public void setPseventyfivehours(String pseventyfivehours) {
		this.pseventyfivehours = pseventyfivehours;
	}

	public String getPfiftyhours() {
		return pfiftyhours;
	}

	public void setPfiftyhours(String pfiftyhours) {
		this.pfiftyhours = pfiftyhours;
	}
	
	
}
