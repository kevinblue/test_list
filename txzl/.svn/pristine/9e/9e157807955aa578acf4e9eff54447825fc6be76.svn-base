package com.tenwa.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

@Entity
@Table(name="T_USER_OHTER_INFO")
public class UserOtherInfo implements Serializable
{
	private static final long serialVersionUID = -7289820236799793248L;
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@FieldName(name="性别 1男 2女")
	@Column(name="GESC")
	private Integer gesc;
	
	@FieldName(name="部门编号")
	@Column(name="ORGEH")
	private String orgeh;
	
	@FieldName(name="部门名称")
	@Column(name="ZHR_OTEXT")
	private String zhrOtext;
	
	@FieldName(name="职务编码（S）")
	@Column(name="PLANS")
	private String plans;
	
	@FieldName(name="职务名称")
	@Column(name="ZHR_PTEXT")
	private String zhrPtext;
	
	@FieldName(name="岗位编码")
	@Column(name="STELL")
	private String stell;
	
	@FieldName(name="岗位名称")
	@Column(name="ZHR_STEXT")
	private String zhrStext;
	
	@FieldName(name="身份证号")
	@Column(name="USERID")
	private String userid;
	
	@FieldName(name="国籍")
	@Column(name="LANDX50")
	private String landx50;
	
	@FieldName(name="民族")
	@Column(name="LTEXT")
	private String ltext;
	
	@FieldName(name="入司时间")
	@Column(name="ZHR_TIME1", length=40)	
	private String zhrTime1;
	
	@FieldName(name="员工状态 0:离职、1:不活动、2:退休、3:激活")
	@Column(name="ZHR_STATUS")
	private Integer zhrStatus;
	
	@FieldName(name="A:职员、B:劳务派遣人员、C:聘用人员D:退休人员、E:不在岗人员、F:实习人员H:外包商、Z:离职人员")
	@Column(name="ZHR_PERSG")
	private String zhrPersg;
	
	@FieldName(name="Z1:正式、Z2:试用")
	@Column(name="ZHR_PTYPE")
	private String zhrPtype;
	
	@FieldName(name="入公司类型")
	@Column(name="ZHR_ETYPE")
	private String zhrEtype;
	
	@FieldName(name="加入本部门时间")
	@Column(name="ZHR_TIME2", length=40)	
	private String zhrTime2;
	
	@FieldName(name="担任本职位时间")
	@Column(name="ZHR_TIME3", length=40)	
	private String zhrTime3;
	
	@FieldName(name="婚姻状况")
	@Column(name="FTEXT")	
	private String ftext;
	
	@FieldName(name="办公号码")
	@Column(name="ZHR_TELL")	
	private String zhrTell;
	
	@FieldName(name="常驻办公地点")
	@Column(name="ZHR_LOCA")	
	private String zhrLoca;
	
	@FieldName(name="省份")
	@Column(name="ZHR_PROV")	
	private String zhrProv;
	
	@FieldName(name="城市")
	@Column(name="ZHR_CITY")	
	private String zhrCity;
	
	@FieldName(name="开户行")
	@Column(name="ZHR_BANK")	
	private String zhrBank;
	
	@FieldName(name="银行卡号")
	@Column(name="ZHR_ACCOUNT")	
	private String zhrAccount;
	
	@FieldName(name="姓名汉语拼音")
	@Column(name="ZHR_HYPY")	
	private String zhrHypy;
	
	@FieldName(name="出生日期")
	@Column(name="GBDAT", length=40)	
	private String gbdat;
	
	@FieldName(name="出生地")
	@Column(name="GBORT")	
	private String gbort;
	
	@FieldName(name="血型")
	@Column(name="ZHR_BTYPE")	
	private String zhrBtype;
	
	@FieldName(name="参加工作时间")
	@Column(name="ZHR_CJGZ", length=40)	
	private String zhrCjgz;
	
	@FieldName(name="转正日期")
	@Column(name="ZHR_ZZRQ", length=40)	
	private String zhrZzrq;
	
	@FieldName(name="入职日期")
	@Column(name="ZHR_RZRQ", length=40)	
	private String zhrRzrq;
	
	@FieldName(name="离职日期")
	@Column(name="ZHR_LZRQ", length=40)	
	private String zhrLzrq;
	
	@FieldName(name="更新标记")
	@Column(name="ZHR_FLAG", length=40)	
	private String zhrFlag;
	
	@FieldName(name="开户行省")
	@Column(name="ZZ_KHHS", length=40)	
	private String zzKhhs;
	
	@FieldName(name="开户行市")
	@Column(name="ZZ_KHHD", length=40)	
	private String zzKhhd;
	
	@FieldName(name="开户行")
	@Column(name="ZZ_KHH", length=40)	
	private String zzKhh;
	
	@FieldName(name="银行号")
	@Column(name="ZZ_YHH", length=40)	
	private String zzYhh;
	
	@FieldName(name="联行号")
	@Column(name="ZZ_LHH", length=40)	
	private String zzLhh;
	
	@FieldName(name="同城")
	@Column(name="ZZ_THXX", length=40)	
	private String zzThxx;
	
	@FieldName(name="岗级")
	@Column(name="TRFGR", length=40)	
	private String trfgr;
	
	@FieldName(name="人事范围")
	@Column(name="WERKS")	
	private String werks;
	
	@FieldName(name="人事子范围")
	@Column(name="BTRTL")	
	private String btrtl;
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getGesc() {
		return gesc;
	}

	public void setGesc(Integer gesc) {
		this.gesc = gesc;
	}

	public String getOrgeh() {
		return orgeh;
	}

	public void setOrgeh(String orgeh) {
		this.orgeh = orgeh;
	}

	public String getZhrOtext() {
		return zhrOtext;
	}

	public void setZhrOtext(String zhrOtext) {
		this.zhrOtext = zhrOtext;
	}

	public String getPlans() {
		return plans;
	}

	public void setPlans(String plans) {
		this.plans = plans;
	}

	public String getZhrPtext() {
		return zhrPtext;
	}

	public void setZhrPtext(String zhrPtext) {
		this.zhrPtext = zhrPtext;
	}

	public String getStell() {
		return stell;
	}

	public void setStell(String stell) {
		this.stell = stell;
	}

	public String getZhrStext() {
		return zhrStext;
	}

	public void setZhrStext(String zhrStext) {
		this.zhrStext = zhrStext;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLandx50() {
		return landx50;
	}

	public void setLandx50(String landx50) {
		this.landx50 = landx50;
	}

	public String getLtext() {
		return ltext;
	}

	public void setLtext(String ltext) {
		this.ltext = ltext;
	}

	public String getZhrTime1() {
		return zhrTime1;
	}

	public void setZhrTime1(String zhrTime1) {
		this.zhrTime1 = zhrTime1;
	}

	public Integer getZhrStatus() {
		return zhrStatus;
	}

	public void setZhrStatus(Integer zhrStatus) {
		this.zhrStatus = zhrStatus;
	}

	public String getZhrPersg() {
		return zhrPersg;
	}

	public void setZhrPersg(String zhrPersg) {
		this.zhrPersg = zhrPersg;
	}

	public String getZhrPtype() {
		return zhrPtype;
	}

	public void setZhrPtype(String zhrPtype) {
		this.zhrPtype = zhrPtype;
	}

	public String getZhrEtype() {
		return zhrEtype;
	}

	public void setZhrEtype(String zhrEtype) {
		this.zhrEtype = zhrEtype;
	}

	public String getZhrTime2() {
		return zhrTime2;
	}

	public void setZhrTime2(String zhrTime2) {
		this.zhrTime2 = zhrTime2;
	}

	public String getZhrTime3() {
		return zhrTime3;
	}

	public void setZhrTime3(String zhrTime3) {
		this.zhrTime3 = zhrTime3;
	}

	public String getFtext() {
		return ftext;
	}

	public void setFtext(String ftext) {
		this.ftext = ftext;
	}

	public String getZhrTell() {
		return zhrTell;
	}

	public void setZhrTell(String zhrTell) {
		this.zhrTell = zhrTell;
	}

	public String getZhrLoca() {
		return zhrLoca;
	}

	public void setZhrLoca(String zhrLoca) {
		this.zhrLoca = zhrLoca;
	}

	public String getZhrProv() {
		return zhrProv;
	}

	public void setZhrProv(String zhrProv) {
		this.zhrProv = zhrProv;
	}

	public String getZhrCity() {
		return zhrCity;
	}

	public void setZhrCity(String zhrCity) {
		this.zhrCity = zhrCity;
	}

	public String getZhrBank() {
		return zhrBank;
	}

	public void setZhrBank(String zhrBank) {
		this.zhrBank = zhrBank;
	}

	public String getZhrAccount() {
		return zhrAccount;
	}

	public void setZhrAccount(String zhrAccount) {
		this.zhrAccount = zhrAccount;
	}

	public String getZhrHypy() {
		return zhrHypy;
	}

	public void setZhrHypy(String zhrHypy) {
		this.zhrHypy = zhrHypy;
	}

	public String getGbdat() {
		return gbdat;
	}

	public void setGbdat(String gbdat) {
		this.gbdat = gbdat;
	}

	public String getGbort() {
		return gbort;
	}

	public void setGbort(String gbort) {
		this.gbort = gbort;
	}

	public String getZhrBtype() {
		return zhrBtype;
	}

	public void setZhrBtype(String zhrBtype) {
		this.zhrBtype = zhrBtype;
	}

	public String getZhrCjgz() {
		return zhrCjgz;
	}

	public void setZhrCjgz(String zhrCjgz) {
		this.zhrCjgz = zhrCjgz;
	}

	public String getZhrZzrq() {
		return zhrZzrq;
	}

	public void setZhrZzrq(String zhrZzrq) {
		this.zhrZzrq = zhrZzrq;
	}

	public String getZhrRzrq() {
		return zhrRzrq;
	}

	public void setZhrRzrq(String zhrRzrq) {
		this.zhrRzrq = zhrRzrq;
	}

	public String getZhrLzrq() {
		return zhrLzrq;
	}

	public void setZhrLzrq(String zhrLzrq) {
		this.zhrLzrq = zhrLzrq;
	}

	public String getZhrFlag() {
		return zhrFlag;
	}

	public void setZhrFlag(String zhrFlag) {
		this.zhrFlag = zhrFlag;
	}

	public String getZzKhhs() {
		return zzKhhs;
	}

	public void setZzKhhs(String zzKhhs) {
		this.zzKhhs = zzKhhs;
	}

	public String getZzKhhd() {
		return zzKhhd;
	}

	public void setZzKhhd(String zzKhhd) {
		this.zzKhhd = zzKhhd;
	}

	public String getZzKhh() {
		return zzKhh;
	}

	public void setZzKhh(String zzKhh) {
		this.zzKhh = zzKhh;
	}

	public String getZzYhh() {
		return zzYhh;
	}

	public void setZzYhh(String zzYhh) {
		this.zzYhh = zzYhh;
	}

	public String getZzLhh() {
		return zzLhh;
	}

	public void setZzLhh(String zzLhh) {
		this.zzLhh = zzLhh;
	}

	public String getZzThxx() {
		return zzThxx;
	}

	public void setZzThxx(String zzThxx) {
		this.zzThxx = zzThxx;
	}

	public String getTrfgr() {
		return trfgr;
	}

	public void setTrfgr(String trfgr) {
		this.trfgr = trfgr;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public String getBtrtl() {
		return btrtl;
	}

	public void setBtrtl(String btrtl) {
		this.btrtl = btrtl;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
	
	
}
