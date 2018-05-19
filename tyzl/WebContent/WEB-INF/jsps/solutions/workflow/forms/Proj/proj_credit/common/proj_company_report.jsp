<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
  <div id="gridcompany" title="尽职调查报告（法人）">
	<jsp:include page="../credit_report/company/proj_cust_info.jsp"/><!-- 承租企业基本信息 -->
	<jsp:include page="../credit_report/company/proj_vip_person.jsp"/><!-- 承租企业重要个人信息 -->
	<jsp:include page="../credit_report/company/cust_share_company.jsp"/><!-- 关联企业及分支机构情况 -->
	<jsp:include page="../credit_report/company/cust_stock_holder.jsp"/><!-- 股东及经营团队 -->
	<jsp:include page="../credit_report/company/report_operationinfo.jsp"/><!-- 视图列表>财务经营状况 -->
	<jsp:include page="../credit_report/company/report_energy.jsp"/><!-- 能源消耗 -->
	<jsp:include page="../credit_report/company/report_bank_debt.jsp"/><!-- 金融机构往来—银行借款 -->
	<jsp:include page="../credit_report/company/report_elease_company.jsp"/><!-- 金融机构往来—租赁公司-->
	<jsp:include page="../credit_report/person/proj_main_product_sale.jsp"/><!-- 主要产品及产销量表 -->
	<jsp:include page="../credit_report/company/cust_manufacturers_info.jsp"/><!-- 进货厂商 -->
 	<jsp:include page="../credit_report/company/cust_sales_info.jsp"/><!-- 销货厂商--> 
	<jsp:include page="../credit_report/company/report_meetinginfo.jsp"/><!-- 照会单内容(交易确认/品质/营运/外部信息) -->
	<jsp:include page="../credit_report/company/report_guarantor_backinfo.jsp"/><!-- 保证人资力背景 --> 
	<jsp:include page="../credit_report/common/proj_ebank_credit_info.jsp"/><!--银行资信情况  -->
	<%-- <jsp:include page="../credit_report/common/proj_rent_invoice_type.jsp" ></jsp:include> --%>
	<%-- <jsp:include page="../credit_report/company/proj_market_competition.jsp"/><!-- 承租企业现有主要产品市场竞争能力 --> 
	<jsp:include page="../credit_report/company/proj_equip_explanation.jsp"/><!-- 租赁物基本情况说明 -->
	<jsp:include page="../credit_report/common/proj_report_assetinfo.jsp" /> <!-- 资产信息 --> --%>
</div>
<script type="text/javascript">
var reportShowFlag=true;
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){
		reportShowFlag=false;
		miniui_ext.disableFormFields("gridcompany");};
		mini.get("id_finance_tool").hide();
});
</script>
     
 