package com.reckon.dao.impl;

import org.apache.log4j.Logger;

import com.reckon.bean.AdjustBean;
import com.reckon.dao.Conn;
import com.reckon.util.tools.DateTools;
import com.reckon.util.tools.NumTools;
import com.tenwa.kernal.utils.UUIDUtil;

public class RentChargeDAOImpl {

	static Logger logger = Logger.getLogger(RentChargeDAOImpl.class);

	public int addAjust(AdjustBean adb) throws Exception {
		Conn conn = new Conn();
		String sql = " INSERT INTO fund_rent_adjust_temp (";
		sql += "id";//0
		sql += ",contract_id ";//1
		sql += ",doc_id";//2
		sql += ",onhire_id";//3
		sql += ",adjust_type";//4
		sql += ",start_list";//5
		sql += ",payday_adjust";
		sql += ",handling_charge";
		sql += ",income_number_year";
		sql += ",adjust_list";
		sql += ",year_rate";
		sql += ",interest_handling_charge";
		sql += ",corpus_overage";
		sql += ",dun_rent";
		sql += ",dun_penalty";
		sql += ",agreed_penalty";
		sql += ",other_out";
		sql += ",other_in";
		sql += ",asset_ownership";
		sql += ",contract_total";
		sql += ",creator_";
		sql += ",agreed_interest";
		sql += ",create_date";
		sql += ",modificator_";
		sql += ",modify_date";
		sql += ") VALUES ('" + UUIDUtil.getUUID();//0
		sql += "','" + adb.getContractInfoId();//1
		sql += "','" + adb.getDocId();//2
		sql += "','" + adb.getOnHireId();//3
		sql += "','" + adb.getAdjustType();//4
		sql += "','" + adb.getStartList();
		sql += "','" + adb.getPaydayAdjust();
		sql += "','" + adb.getHandlingCharge();
		sql += "','" + adb.getIncomeNumberYear();
		sql += "','" + adb.getAdjustList();
		sql += "','" + adb.getYearRate()+"',";
		
		if(adb.getInterestHandlingCharge()==null){
			sql +="NULL,";
		}else{
			sql += "'" + NumTools.formatNumberDoubleScale(adb.getInterestHandlingCharge().toString(),2) + "',";
		}
		if(adb.getCorpusOverage()==null){
			sql +="NULL,";
		}else{
			sql += "'" + NumTools.formatNumberDoubleScale(adb.getCorpusOverage().toString(),2) + "',";
		}
		if(adb.getDunRent()==null){
			sql +="NULL,";
		}else{
			sql += "'" + NumTools.formatNumberDoubleScale(adb.getDunRent().toString() ,2)+ "',";
		}
		if(adb.getDunPenalty()==null){
			sql +="NULL,";
		}else{
			sql += "'" + NumTools.formatNumberDoubleScale(adb.getDunPenalty().toString(),2) + "',";
		}
		if(adb.getAgreedPenalty()==null){
			sql +="NULL,";
		}else{
			sql += "'" + NumTools.formatNumberDoubleScale(adb.getAgreedPenalty().toString(),2)+ "',";
		}
		if(adb.getOtherOut()==null){
			sql +="NULL,";
		}else{
			sql += "'" +NumTools.formatNumberDoubleScale( adb.getOtherOut().toString() ,2)+ "',";
		}
		if(adb.getOtherIn()==null){
			sql +="NULL,";
		}else{
			sql += "'" + NumTools.formatNumberDoubleScale(adb.getOtherIn().toString() ,2)+ "',";
		}
		if(adb.getAssetOwnership()==null){
			sql +="NULL,";
		}else{
			sql += "'" + adb.getAssetOwnership() + "',";
		}
		if(adb.getContractTotal()==null){
			sql +="NULL,";
		}else{
			sql += "'" + NumTools.formatNumberDoubleScale(adb.getContractTotal().toString(),2) + "',";
		}		
		if(adb.getCreator()==null){
			sql +="NULL,";
		}else{
			sql += "'" + adb.getCreator() + "',";
		}
		if(adb.getAgreedInterest()==null){
			sql +="NULL,";
		}else{
			sql += "'" + NumTools.formatNumberDoubleScale(adb.getAgreedInterest().toString(),2)+ "',";
		}
		
		sql += DateTools.getSystemDate(1)+",";
		if(adb.getModificator()==null){
			sql +="NULL,";
		}else{
			sql += "'" + adb.getModificator() + "',";
		}
		if(adb.getModificator()==null){
			sql +="NULL";
		}else{
			sql += DateTools.getSystemDate(1);
		}
		sql += ")";
		sql = sql.replace("'null'", "null");
		int ini = 0;
		logger.info("变更表insert语句:"+sql);
		ini = conn.executeUpdate(sql, "变更表insert语句:");
		return ini;
	}

	public void deleteAjust(AdjustBean adb) throws Exception {
		Conn conn = new Conn();
		/**
		 *sea edit 2014-04-05
		 *这里临时表中contract_id存入的是contract_info中的UUID，
		 *而不是这样‘C2014000094’或者这样‘L201401000095’的合同号，
		 *因此根据合同号是无法删除的,导致表中存在多条数据，
		 *进而导致后方的计算中取值错误，最终造成测算数据不对！！！ 
		 *所以这里需要将合同号转换下，转成对应的uuid
		 *
		 */
		//String sql = " delete fund_rent_adjust_temp where contract_id = '" + adb.getContractId() + "' and doc_id='" + adb.getDocId() + "'";
		String sql = " delete fund_rent_adjust_temp  where  doc_id='" + adb.getDocId() + "'";
		if (adb.getOnHireId() != null && !adb.getOnHireId().equals("")) {
			sql += " and onHire_id='" + adb.getOnHireId() + "'";
		}
		// logger.info( + sql);
		conn.executeUpdate(sql, "删除fund_rent_adjust_temp：");
	}
	
	public void deleteAjustByConAndDocId(String contractId,String docId) throws Exception {
		Conn conn = new Conn();
		/**
		 *sea edit 2014-04-05
		 *这里临时表中contract_id存入的是contract_info中的UUID，
		 *而不是这样‘C2014000094’或者这样‘L201401000095’的合同号，
		 *因此根据合同号是无法删除的,导致表中存在多条数据，
		 *进而导致后方的计算中取值错误，最终造成测算数据不对！！！ 
		 *所以这里需要将合同号转换下，转成对应的uuid
		 *
		 */
		//String sql = " delete fund_rent_adjust_temp where contract_id = '" + adb.getContractId() + "' and doc_id='" + adb.getDocId() + "'";
		String sql = " delete fund_rent_adjust_temp  where  doc_id='" + docId + "'";
		sql += " and contract_id = '"+contractId+"' ";
		conn.executeUpdate(sql, "删除fund_rent_adjust_temp：");
	}
}
