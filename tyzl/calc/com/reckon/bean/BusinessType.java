package com.reckon.bean;

public enum BusinessType {
	
	/**
	 * 初始测算流程
	 */
	INIT,
	
	/**
	 * 客户报价流程
	 */
	CUST,
	
	/**
	 * 客户报价转立项流程
	 */
	CUST_TO_PROJECT,
	
	/**
	 * 项目阶段流程
	 */
	PROJECT,
	
	/**
	 * 项目转合同流程
	 */
	PROJECT_TO_CONTRACT,
	
	/**
	 * 合同阶段流程
	 */
	CONTRACT,
	
	/**
	 * 合同起租流程
	 */
	CONTRACT_ONHIRE,
	
	/**
	 * 合同结束流程
	 */
	STOP
}
