<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
	<input  style="display:none;"	class="mini-textarea" id="id_json_proj_equip_str" name="json_proj_equip_str" value='<mini:param  name="json_proj_equip_str" defaultValue="[]"/>'></input>
	<input  style="display:none;"	class="mini-textarea" id="id_json_proj_guarantee_detail_str" 	name="json_proj_guarantee_detail_str" value='<mini:param  name="json_proj_guarantee_detail_str" defaultValue="[]"/>'></input>
	<input  style="display:none;"	class="mini-textarea" id="id_json_proj_guaranty_detail_str" 	name="json_proj_guaranty_detail_str" value='<mini:param  name="json_proj_guaranty_detail_str" defaultValue="[]"/>'></input>
	<input  style="display:none;"	class="mini-textarea" id="id_json_proj_person_detail_str" 	name="id_json_proj_person_detail_str" value='<mini:param  name="json_proj_guaranty_detail_str" defaultValue="[]"/>'></input>
	<input  style="display:none;"	class="mini-textarea" id="id_json_proj_file_detail_str" 	name="id_json_proj_file_detail_str" value='<mini:param  name="id_json_proj_file_detail_str" defaultValue="[]"/>'></input>
	<input  style="display:none;"	class="mini-textarea" id="id_json_proj_letter_detail_str" 	name="id_json_proj_letter_detail_str" value='<mini:param  name="id_json_proj_letter_detail_str" defaultValue="[]"/>'></input>
	<input style="display:none;"	class="mini-textarea" id="id_json_proj_conclusion_detail_str" 	name="id_json_proj_conclusion_detail_str" value='<mini:param  name="id_json_proj_conclusion_detail_str" defaultValue="[]"/>'></input>
	<input style="display:none;"	class="mini-textarea" id="id_json_proj_director_detail_str" 	name="id_json_proj_director_detail_str" value='<mini:param  name="id_json_proj_director_detail_str" defaultValue="[]"/>'></input>
	<input style="display:none;"	class="mini-textarea" id="id_json_credit_report_str" 	name="id_json_credit_report_str" value='<mini:param  name="id_json_credit_report_str" defaultValue="[]"/>'></input>
    <!-- 个人的从业经验-->
	<input  style="display:none;"	class="mini-textarea" id="id_json_proj_report_person_experience" 	name="proj_report_person_experience_str"   value='<mini:param  name="proj_report_person_experience_str" defaultValue="[]"/>'></input>
	<!-- 资产信息 -->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_assetinfo_str" 	name="proj_report_assetinfo_str" value='<mini:param  name="proj_report_assetinfo_str" defaultValue="[]"/>'></input>
	<!-- 收入来源 -->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_person_incomesource_str" 	name="proj_report_person_incomesource_str" value='<mini:param  name="proj_report_person_incomesource_str" defaultValue="[]"/>'></input>
	
	<!-- 债务调查-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_person_debtanalysis_str" 	name="proj_report_person_debtanalysis_str" value='<mini:param  name="proj_report_person_debtanalysis_str" defaultValue="[]"/>'></input>
	
	<!-- 债券调查 -->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_debt_survey_str" 	name="proj_report_debt_survey_str" value='<mini:param  name="proj_report_debt_survey_str" defaultValue="[]"/>'></input>
	
	<!-- 信审评分-->
	<input  style="display:none;"	class="mini-textarea" id="id_credit_grade_result" name="id_credit_grade_result" ></input>
	
	<!-- 文件清单-->
	<input  style="display:none;"	class="mini-textarea" id="id_document_list_result" name="id_document_list_result" ></input>
	
	<!-- 银行资信情况-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_bank_credit_info_str" name="proj_report_bank_credit_info_str"  value='<mini:param  name="proj_report_debt_survey_str" defaultValue="[]"/>''${empty proj_report_bank_credit_info_str ? "[]" : proj_report_bank_credit_info_str }'></input>
	
	<!-- 主要产品及产销量表-->
	<input style="display:none;"    class="mini-textarea" id="proj_report_main_product_str" name="proj_report_main_product_str"  value='<mini:param  name="proj_report_debt_survey_str" defaultValue="[]"/>''${empty proj_report_main_product_str ? "[]" : proj_report_main_product_str }'></input>
	
	<!--承租企业重要个人信息-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_vip_person" name="proj_report_vip_person"  value='<mini:param  name="proj_report_vip_person" defaultValue="[]"/>'></input>
	<!-- 关联企业及分支机构情况-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_share_company" name="proj_report_share_company"  value='<mini:param  name="proj_report_share_company" defaultValue="[]"/>'></input>
	<!-- 股东及经营团队-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_stock_holder" name="proj_report_stock_holder"  value='<mini:param  name="proj_report_stock_holder" defaultValue="[]"/>'></input>
	<!-- 视图列表>财务经营状况-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_operationinfo_str" name="proj_report_operationinfo_str"  value='<mini:param  name="proj_report_operationinfo_str" defaultValue="[]"/>'></input>
	<!-- 进货厂商-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_manufacturers_info" name="proj_report_manufacturers_info"  value='<mini:param  name="proj_report_manufacturers_info" defaultValue="[]"/>'></input>
	<!-- 销货厂商-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_sale_info" name="proj_report_sale_info"  value='<mini:param  name="proj_report_sale_info" defaultValue="[]"/>'></input>
	<!-- 能源消耗-->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_report_energy" name="proj_report_report_energy"  value='<mini:param  name="proj_report_report_energy" defaultValue="[]"/>'></input>
	<!-- 金融机构往来—银行借款 -->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_bank_debt" name="proj_report_bank_debt"  value='<mini:param  name="proj_report_bank_debt" defaultValue="[]"/>'></input>
	<!-- 金融机构往来—租赁公司 -->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_eleasing_company" name="proj_report_eleasing_company"  value='<mini:param  name="proj_report_eleasing_company" defaultValue="[]"/>'></input>
	<!-- 照会单内容(交易确认/品质/营运/外部信息) -->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_meeting_info" name="proj_report_meeting_info"  value='<mini:param  name="proj_report_meeting_info" defaultValue="[]"/>'></input>
	<!-- 保证人资力背景 -->
	<input  style="display:none;"	class="mini-textarea" id="proj_report_guarantor_backinfo" name="proj_report_guarantor_backinfo"  value='<mini:param  name="proj_report_guarantor_backinfo" defaultValue="[]"/>'></input>
	
	

