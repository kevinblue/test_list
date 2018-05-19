<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div class="mini-panel" title="财务经营状况" showCollapseButton="true" style="width: 100%;"> 
	<jsp:include page="../../../../../../leasing/cust_info/cust_finance/cust_finance_report.jsp" >
		<jsp:param value="${custid}" name="custid"/>
	</jsp:include>
</div>
<script>
var custid = "<mini:param name='proj_info.custid' />";
</script>
