package com.reckon.bean;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 用于租金测算时保存交易结构表信息，租金计划表信息)
 */
public class TabCalBean {

	private String condition_tb = ""; // 交易结构表信息
	private String rentPlan_tb = ""; // 租金计划表信息
	private String rentFinaPlan_tb = ""; // 租金会计计划表信息
	private String contractCashTb = ""; // 合同现金流表名;
	private String finacCashTb = ""; // 会计现金流表名;

	private String contOrProjCName = ""; // 项目号或合同号列名;
	private String contOrProjCValue = ""; // 项目号或合同号列值;
	
	private String userId = "";// 操作人员
	private String quot_id = "";// 报价编号
	private String onHire_id = "";// 起租编号
	private String calType = "";// 流程类型 和TbBeanTools绑定
	private String is_merger = "否";// 在多次起租时是否合并测算
	private String table_type = "temp";// temp为从表都是temp出,formal都从正式表出,默认从temp
	private String view_type = "table";// view为从表都是视图出,table都从表出,默认从table
	private String docId;

	public String getIs_merger() {
		return is_merger;
	}

	public void setIs_merger(String is_merger) {
		this.is_merger = is_merger;
	}

	public String getView_type() {
		return view_type;
	}

	public String getTable_type() {
		return table_type;
	}

	public String getCalType() {
		return calType;
	}

	public void setCalType(String calType) {
		this.calType = calType;
	}

	public String getOnHire_id() {
		return onHire_id;
	}

	public void setOnHire_id(String onHire_id) {
		this.onHire_id = onHire_id;
	}

	public String getQuot_id() {
		return quot_id;
	}

	public void setQuot_id(String quot_id) {
		this.quot_id = quot_id;
	}

	public String getCondition_tb() {
		return condition_tb;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public TabCalBean() {
		super();
	}

	public void setCondition_tb(String condition_tb) {
		this.condition_tb = condition_tb;
	}

	public String getContOrProjCName() {
		return contOrProjCName;
	}

	public void setContOrProjCName(String contOrProjCName) {
		this.contOrProjCName = contOrProjCName;
	}

	public String getContOrProjCValue() {
		return contOrProjCValue;
	}

	public void setContOrProjCValue(String contOrProjCValue) {
		this.contOrProjCValue = contOrProjCValue;
	}

	public String getContractCashTb() {
		return contractCashTb;
	}

	public void setContractCashTb(String contractCashTb) {
		this.contractCashTb = contractCashTb;
	}

	public String getFinacCashTb() {
		return finacCashTb;
	}

	public void setFinacCashTb(String finacCashTb) {
		this.finacCashTb = finacCashTb;
	}

	public String getRentPlan_tb() {
		return rentPlan_tb;
	}

	public void setRentPlan_tb(String rentPlan_tb) {
		this.rentPlan_tb = rentPlan_tb;
	}

	public String getRentFinaPlan_tb() {
		return rentFinaPlan_tb;
	}

	public void setRentFinaPlan_tb(String rentFinaPlan_tb) {
		this.rentFinaPlan_tb = rentFinaPlan_tb;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	@Override
	public String toString() {
		String message = "\n测算中操作的表信息如下:";
		message += "\n 流程类型" + getCalType();
		message += "\n 交易结构表信息" + getCondition_tb();
		message += "\n 租金计划表信息" + getRentPlan_tb();
		message += "\n 合同现金流表名" + getContractCashTb();
		message += "\n 会计现金流表名" + getFinacCashTb();
		message += "\n 租金会计计划表信息" + getRentFinaPlan_tb();
		message += "\n 项目号或合同号列名" + getContOrProjCName();
		message += "\n 项目号或合同号列值" + getContOrProjCValue();
		message += "\n 流程编号列名doc_id";
		message += "\n 流程编号值" + getDocId();
		message += "\n 报价编号" + getQuot_id();
		message += "\n 起租编号" + getOnHire_id();
		message += "\n 操作人员" + getUserId();
		return message;
	}

	/**
	 * 
	 * 将所有的表都改为从temp表取数
	 */
	public void setTableToTemp() {
		if (!table_type.equals("temp")) {// 已经是temp表了就不用改
			table_type = "temp";
			condition_tb = condition_tb + "_temp";
			rentPlan_tb = rentPlan_tb + "_temp";
			rentFinaPlan_tb = rentFinaPlan_tb + "_temp";
			contractCashTb = contractCashTb + "_temp";
			finacCashTb = finacCashTb + "_temp";
		}
	}

	/**
	 * 
	 *  将所有表都改为充正式表取数
	 */
	public void setTableToFormal() {
		if (table_type.equals("temp")) {// 不是正式表 就要改
			table_type = "formal";
			condition_tb = condition_tb.replace("_temp", "");
			rentPlan_tb = rentPlan_tb.replace("_temp", "");
			rentFinaPlan_tb = rentFinaPlan_tb.replace("_temp", "");
			contractCashTb = contractCashTb.replace("_temp", "");
			finacCashTb = finacCashTb.replace("_temp", "");
		}
	}

	/**
	 * 
	 * 将所有的表都改为从temp表取数
	 */
	public void setTableToView() {
		// if(view_type.equals("table")){//已经是view表了就不用改
		// view_type="view";
		// condition_tb="vi_"+condition_tb;
		// rentPlan_tb="vi_"+rentPlan_tb;
		// rentFinaPlan_tb="vi_"+rentFinaPlan_tb;
		// contractCashTb="vi_"+contractCashTb;
		// finacCashTb="vi_"+finacCashTb;
		// }
	}

	/**
	 * 
	 *  将所有表都改为充正式表取数
	 */
	public void setTableToTable() {
		// if(view_type.equals("view")){//不是正式表 就要改
		// view_type="table";
		// condition_tb=condition_tb.replace("vi_", "");
		// rentPlan_tb=rentPlan_tb.replace("vi_", "");
		// rentFinaPlan_tb=rentFinaPlan_tb.replace("vi_", "");
		// contractCashTb=contractCashTb.replace("vi_", "");
		// finacCashTb=finacCashTb.replace("vi_", "");
		// }
	}

	
}
