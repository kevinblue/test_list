package com.tenwa.leasing.entity.proj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;
//W_E 是wind electricity 的简写 W_T是wind_tower的简写 
//W_D 是wind direction 的简写 W_V是wind velocity的简写
@Entity
@FieldName(name = "风资源分析")
@Table(name="PROJ_WIND_RESOURCE")
public class ProjWindResource {
		@Id
		@OrderBy
	    @GeneratedValue(generator = "paymentableGenerator")     
	    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	    @Column(length=32)
	    @FieldName(name="标识符")
		private String id;
		
		
		@ManyToOne(fetch = FetchType.LAZY)
		@FieldName(name="项目id号")
		@JoinColumn(name = "PROJ_id")
		private ProjDevelopInfo projId;
		
		

		public ProjDevelopInfo getProjId() {
			return projId;
		}

		public void setProjId(ProjDevelopInfo projId) {
			this.projId = projId;
		}

		@FieldName(name = "风电场地地形特征")
		@Column(name="W_E_TERRAIN ")
		private String w_e_terrain;
		
		@FieldName(name = "风电场地地貌特征")
		@Column(name="W_E_LANDFORM ")
		private String w_e_landform;
		
		@FieldName(name = "测风塔编号")
		@Column(name="W_T_NUM")
		private String w_t_num;
		
		@FieldName(name = "测风塔高度")
		@Column(name="W_T_HEIGHT")
		private String w_t_height;
		
		

		@FieldName(name = "测风塔位置")
		@Column(name="W_T_LOCATION")
		private String w_t_location;
		
		@FieldName(name = "测风塔代表性")
		@Column(name="W_T_REPRESENTATIVE ")
		private String w_t_representative;
		
		@FieldName(name = "测风时段")
		@Column(name="WIND_PERIOD ")
		private String windPeriod;
		
		@FieldName(name = "有效数据完整率")
		@Column(name="EFFECTIVE_DATA_INTEGRITY ")
		private String effectiveDataIntegrity ;
		
		@FieldName(name = "测风数据是否符合规范")
		@Column(name="WIND_SPECIFICATION")
		private String windSpecification ;
		
		@FieldName(name = "代表年平均风速")
		@Column(name="ANNUAL_M_V")
		private String annual_m_v;
		
		@FieldName(name = "空气密度")
		@Column(name="AIR_DENSITY")
		private String airDensity;

		@FieldName(name = "主导风向")
		@Column(name="DOMINANT_W_D")
		private String dominant_w_d;
		
		@FieldName(name = "风切变指数")
		@Column(name="WIND_SHEAR")
		private String windShear ;
		
		@FieldName(name = "特征湍流强度")
		@Column(name="CHARACTERISTIC")
		private String characteristic ;
		
		@FieldName(name = "极端气候条件")
		@Column(name="EXTREME_CLIMATIC_EVENT")
		private String extremeclimaticevent ;
		
		@FieldName(name = "50年一遇最大风速")
		@Column(name="FIFTY_YEAR_MAX_WIND")
		private String fiftyYearMaxWind;

		@ManyToOne
		@FieldName(name = "登记人")
		@JoinColumn(name="CREATOR_")
		private User creator;
		
		@FieldName(name = "登记时间")
		@Column(name="CREATE_DATE")
		private String createDate;
		
		@ManyToOne
		@FieldName(name = "更新人")
		@JoinColumn(name="MODIFICATOR_")
		private User modificator;
		
		@FieldName(name = "更新日期")
		@Column(name="MODIFY_DATE", length=20)
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

		public String getW_e_terrain() {
			return w_e_terrain;
		}

		public void setW_e_terrain(String w_e_terrain) {
			this.w_e_terrain = w_e_terrain;
		}

		public String getW_e_landform() {
			return w_e_landform;
		}

		public void setW_e_landform(String w_e_landform) {
			this.w_e_landform = w_e_landform;
		}

		public String getW_t_num() {
			return w_t_num;
		}

		public void setW_t_num(String w_t_num) {
			this.w_t_num = w_t_num;
		}

		public String getW_t_height() {
			return w_t_height;
		}

		public void setW_t_height(String w_t_height) {
			this.w_t_height = w_t_height;
		}

		public String getW_t_location() {
			return w_t_location;
		}

		public void setW_t_location(String w_t_location) {
			this.w_t_location = w_t_location;
		}

		public String getW_t_representative() {
			return w_t_representative;
		}

		public void setW_t_representative(String w_t_representative) {
			this.w_t_representative = w_t_representative;
		}

		public String getWindPeriod() {
			return windPeriod;
		}

		public void setWindPeriod(String windPeriod) {
			this.windPeriod = windPeriod;
		}

		public String getEffectiveDataIntegrity() {
			return effectiveDataIntegrity;
		}

		public void setEffectiveDataIntegrity(String effectiveDataIntegrity) {
			this.effectiveDataIntegrity = effectiveDataIntegrity;
		}

		public String getWindSpecification() {
			return windSpecification;
		}

		public void setWindSpecification(String windSpecification) {
			this.windSpecification = windSpecification;
		}

		public String getAnnual_m_v() {
			return annual_m_v;
		}

		public void setAnnual_m_v(String annual_m_v) {
			this.annual_m_v = annual_m_v;
		}

		public String getAirDensity() {
			return airDensity;
		}

		public void setAirDensity(String airDensity) {
			this.airDensity = airDensity;
		}

		public String getDominant_w_d() {
			return dominant_w_d;
		}

		public void setDominant_w_d(String dominant_w_d) {
			this.dominant_w_d = dominant_w_d;
		}

		public String getWindShear() {
			return windShear;
		}

		public void setWindShear(String windShear) {
			this.windShear = windShear;
		}

		public String getCharacteristic() {
			return characteristic;
		}

		public void setCharacteristic(String characteristic) {
			this.characteristic = characteristic;
		}

		public String getFiftyYearMaxWind() {
			return fiftyYearMaxWind;
		}

		public void setFiftyYearMaxWind(String fiftyYearMaxWind) {
			this.fiftyYearMaxWind = fiftyYearMaxWind;
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

		public String getExtremeclimaticevent() {
			return extremeclimaticevent;
		}

		public void setExtremeclimaticevent(String extremeclimaticevent) {
			this.extremeclimaticevent = extremeclimaticevent;
		}
		

}