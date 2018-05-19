<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%-- <input type="hidden" id="id_json_contract_equip_str"
		name="json_contract_equip_str"
		value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
	<input type="hidden" id="id_json_rent_income_plan_str"
		name="json_rent_income_plan_str"
		value='${empty json_rent_income_plan_str ? "[]" : json_rent_income_plan_str }'></input>
	<input type="hidden" id="id_json_rent_income_his_str"
		name="json_rent_income_his_str"
		value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
	<input type="hidden" id="id_json_collection_plan_str"
		name="json_collection_plan_str"
		value='${empty json_collection_plan_str ? "[]" : json_collection_plan_str }'></input>
	<input type="hidden" id="id_json_collection_his_str"
		name="json_collection_his_str"
		value='${empty json_collection_his_str ? "[]" : json_collection_his_str }'></input>
	<input type="hidden" id="id_json_contract_guarantee_method_str"
		name="json_contract_guarantee_method_str"
		value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
	<input type="hidden" id="id_json_contract_guarantee_equip_str"
		name="json_contract_guarantee_equip_str"
		value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
 --%>


<div class="mini-tabs" activeIndex="0"
		style="width: 100%; height: 500px;">
		<div title="资金计划明细" iconCls="icon-expand">
				<jsp:include
					page="../../fund/fund_comm/fund_collection_plan_info.jsp"></jsp:include>
			</div>
			<div title="资金收款历史明细" iconCls="icon-expand">
				<jsp:include
					page="../../fund/fund_comm/fund_collection_his_info.jsp">
					<jsp:param value="true" name="isView" />
				</jsp:include>
			</div>
			<div title="租金回笼计划" iconCls="icon-node">
				<jsp:include page="../../rent/rent_comm/rent_income_plan_query.jsp"></jsp:include>
			</div>
			<div title="租金回笼历史" iconCls="icon-node">
				<jsp:include page="../../rent/rent_comm/rent_income_his_query.jsp"></jsp:include>
			</div>
			<div title="投放明细" iconCls="icon-node">
				<jsp:include page="../../fund/fund_comm/fund_put_his_info_query.jsp"></jsp:include>
			</div>
		</div>