package com.runto.dao;

public class MsgBean {

	private String message_id=""; ////报文ID,我方维护
	private String message_time=""; //报文请求时间
	private String name="";	//注册用户名
	private String tel="";//注册用户联系电话
	private String backup_name=""; ////紧急联系人名
	private String backup_tel="";//紧急联系人电话
	private String plate_num="";//车牌号
	private String model_name="";//车辆型号
	private String color="";//车辆颜色（暂时不提供）
	private String vin="";//车架号
	private String latitude=""; //纬度
	private String longitude="";//经度
	private String direction="";//航向，单位度
	private String description="";	//位置地理信息
	
	//事故发生前10个位置及速度信息,将10个位置信息保存到一个字段中
	private String fasq_msg="";
	
	private String time="";//事件发生时间（unix时间戳）
	private String source="";//触发源manual(手动)或auto(自动)
	private String intensity="";//碰撞各个方向的强度，单位 G（9.8米/秒平方）
	
	public String getFasq_msg() {
		return fasq_msg;
	}
	public void setFasq_msg(String fasqMsg) {
		fasq_msg = fasqMsg;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String messageId) {
		message_id = messageId;
	}
	public String getMessage_time() {
		return message_time;
	}
	public void setMessage_time(String messageTime) {
		message_time = messageTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBackup_name() {
		return backup_name;
	}
	public void setBackup_name(String backupName) {
		backup_name = backupName;
	}
	public String getBackup_tel() {
		return backup_tel;
	}
	public void setBackup_tel(String backupTel) {
		backup_tel = backupTel;
	}
	public String getPlate_num() {
		return plate_num;
	}
	public void setPlate_num(String plateNum) {
		plate_num = plateNum;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String modelName) {
		model_name = modelName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIntensity() {
		return intensity;
	}
	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
	
}
