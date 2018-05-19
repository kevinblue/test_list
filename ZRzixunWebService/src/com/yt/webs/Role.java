package com.yt.webs;


public class Role {
	private String complaintid;//工单ID
	private String dealContent;//处理内容
	private String dealState;//处理状态
	private String dealUser;//处理人
	private String dealTime;//处理时间
	public String getComplaintid() {
		return complaintid;
	}
	public void setComplaintid(String complaintid) {
		this.complaintid = complaintid;
	}
	public String getDealContent() {
		return dealContent;
	}
	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}
	public String getDealState() {
		return dealState;
	}
	public void setDealState(String dealState) {
		this.dealState = dealState;
	}
	public String getDealUser() {
		return dealUser;
	}
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

}