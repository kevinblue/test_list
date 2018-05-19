<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
	/*
	 *保存信审报告数据
	*/
	function saveReportTable(){
		mini.parse();
		var custClass = "<mini:param  name="proj_info.custclass" />";
		if(custClass == "CUST_INFO_PERSON"){
			mini.get('proj_report_person_incomesource_str').setValue(JsonUtil.encode(mini.get('proj_person_incomesource').getData()));
			mini.get('proj_report_person_debtanalysis_str').setValue(JsonUtil.encode(mini.get('proj_person_debtanalysis').getData()));
			mini.get('proj_report_debt_survey_str').setValue(JsonUtil.encode(mini.get('proj_debt_survey').getData()));
			mini.get('proj_report_assetinfo_str').setValue(JsonUtil.encode(mini.get('report_assetinfo').getData()));
			mini.get('id_json_proj_report_person_experience').setValue(JsonUtil.encode(mini.get('person_experience').getData()));
		}else{
			mini.get('proj_report_vip_person').setValue(JsonUtil.encode(mini.get('vip_person').getData()));
			mini.get('proj_report_share_company').setValue(JsonUtil.encode(mini.get('share_company').getData()));
			mini.get('proj_report_stock_holder').setValue(JsonUtil.encode(mini.get('stock_holder').getData()));
			//$('#proj_report_report_operationinfo').val(JsonUtil.encode(mini.get('report_operationinfo').getData()));
			mini.get('proj_report_report_energy').setValue(JsonUtil.encode(mini.get('report_energy').getData()));
			mini.get('proj_report_meeting_info').setValue(JsonUtil.encode(mini.get('meeting_info').getData()));
			mini.get('proj_report_guarantor_backinfo').setValue(JsonUtil.encode(mini.get('guarantor_backinfo').getData()));
			mini.get('proj_report_bank_credit_info_str').setValue(JsonUtil.encode(mini.get('proj_bank_credit_info').getData()));
		}
		mini.get('proj_report_manufacturers_info').setValue(JsonUtil.encode(mini.get('manufacturers_info').getData()));
		mini.get('proj_report_sale_info').setValue(JsonUtil.encode(mini.get('sale_info').getData()));
		mini.get('proj_report_main_product_str').setValue(JsonUtil.encode(mini.get('proj_report_main_product').getData()));
		mini.get('proj_report_bank_debt').setValue(JsonUtil.encode(mini.get('report_bank_debt').getData()));
		mini.get('proj_report_eleasing_company').setValue(JsonUtil.encode(mini.get('eleasing_company').getData()));
		//mini.get('json_credit_grade_str').setValue(JsonUtil.encode(getTracywindyObject("credit_grade_data").getTableAllSavedConfigJson()));
		mini.get('json_document_list_str').setValue(JsonUtil.encode(getTracywindyObject("document_list_data").getTableAllSavedConfigJson()));
	}
</script>