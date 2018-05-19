package com.reckon.commons.helper;

import java.util.List;

import com.reckon.commons.base.CashDetail;
import com.reckon.commons.base.Condition;
import com.reckon.commons.base.FundPlan;
import com.reckon.commons.base.RentPlan;

public class ObjectAssociationUtil {

	public static <T extends RentPlan, K extends Condition> void associationConditionForRentPlan(List<T> rentPlan, K condition) {
		for (T bean : rentPlan) {
			bean.setDocId(condition.getDocId());
			bean.setCustId(condition.getCustId());
			bean.setProjId(condition.getProjId());
			bean.setContractId(condition.getContractId());
			bean.setEquipId(condition.getEquipId());
			bean.setConditionId(condition.getId());
		}
	}

	public static <T extends FundPlan, K extends Condition> void associationConditionForFundPlan(List<T> fundPlan, K condition) {
		for (T bean : fundPlan) {
			bean.setDocId(condition.getDocId());
			bean.setCustId(condition.getCustId());
			bean.setProjId(condition.getProjId());
			bean.setContractId(condition.getContractId());
			bean.setEquipId(condition.getEquipId());
			bean.setConditionId(condition.getId());
		}
	}

	public static <T extends CashDetail, K extends Condition> void associationConditionForCashFlow(List<T> cashFlow, K condition) {
		for (T bean : cashFlow) {
			bean.setDocId(condition.getDocId());
			bean.setCustId(condition.getCustId());
			bean.setProjId(condition.getProjId());
			bean.setContractId(condition.getContractId());
			bean.setEquipId(condition.getEquipId());
			bean.setConditionId(condition.getId());
		}
	}
}
