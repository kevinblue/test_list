<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="gridperson" title="尽职调查报告（自然人）">
	<jsp:include page="../credit_report/person/proj_person_report_info.jsp" /> <!-- 申请人自然情况 -->
	<jsp:include page="../credit_report/person/proj_person_report_mateinfo.jsp" /><!-- 配偶情况 -->
	<jsp:include page="../credit_report/person/proj_person_report_experience.jsp" /><!-- 从业经验 -->	
	<jsp:include page="../credit_report/company/cust_manufacturers_info.jsp"/><!-- 进货厂商 -->
	<jsp:include page="../credit_report/company/cust_sales_info.jsp"/><!-- 销货厂商-->
	<jsp:include page="../credit_report/common/proj_report_assetinfo.jsp" /><!-- 资产信息-->
	<jsp:include page="../credit_report/person/proj_person_report_revenue.jsp" /> <!-- 收入来源-->
	<jsp:include page="../credit_report/person/proj_person_report_debtanalysis.jsp" /><!-- 债务调查-->
	<jsp:include page="../credit_report/person/proj_person_report_survey.jsp" /> <!-- 债权调查-->
	<jsp:include page="../credit_report/company/report_bank_debt.jsp"/><!-- 金融机构往来—银行借款 -->
	<jsp:include page="../credit_report/company/report_elease_company.jsp"/><!-- 金融机构往来—租赁公司-->
	<jsp:include page="../credit_report/person/proj_main_product_sale.jsp"/><!-- 主要产品及产销量表 -->
	
	
	<%--<jsp:include page="../credit_report/common/proj_other_eleaing_corporation.jsp"/><!-- 与其他融资企业的相关交易情况 -->
	<jsp:include page="../credit_report/common/proj_local_eleaing_corporation.jsp"/><!-- 与本融资企业的相关交易情况 -->
	<jsp:include page="../credit_report/common/proj_ebank_credit_info.jsp"/><!-- 银行资信情况 -->
	 <jsp:include page="../credit_report/person/proj_voteoption.jsp"/><!-- 表决意见 --> 
	<jsp:include page="../credit_report/person/credit_approval_process.jsp"/><!-- 评审进程--> 
	<jsp:include page="../credit_report/person/credit_option_summary.jsp"/><!-- 评审意见汇总--> --%>
</div>
<script type="text/javascript">
    var reportShowFlag=true;
	jQuery(function(){
		if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){reportShowFlag=false;miniui_ext.disableFormFields("gridperson");};
	});
</script>