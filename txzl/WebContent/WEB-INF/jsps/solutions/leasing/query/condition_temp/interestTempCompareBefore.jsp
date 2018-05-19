<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调息对比信息</title>
<%@include file="/base/mini.jsp"%>
<!--css sheet-->
<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<!--javascript libray-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyMultiTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/MustFillIn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/custType.js"></script>
<style>
body,html {
	overflow: hidden;
}
</style>

<script language="javascript">
	$(function() {
		var availWidth = parseInt(document.documentElement.clientWidth);
		var availHeight = parseInt(document.documentElement.clientHeight);
		$("#mianbody").css("height", availHeight - 30 + "px");
		$("#mianbody").css("width", availWidth - 20 + "px");
		
		var span = '<span style= "background:#FFFFFF;height:25px;line-height:25px;position:relative;top:-4px;margin-right:30px;width:60px;border-radius:4px;font-weight:bold;text-align:center;color:blue;" onclick="window.close();">关闭</span>';
		jQuery('.mini-tools').html(span);
	});
</script>
</head>


<body>
	<div class="mini-panel" title="调息对比信息"  style="width: 100%;position: relative;height: 100%;" >
		<table style="width:99%" class="fillTable">
	            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
	            <tr  class="tr-odd">
	            	<td class="td-content-title">合同编号:</td>  
	            	<td class="td-content">
	                    <span name="contractid" id="contractid" value="${param.contractid}"></span>
	               <td class="td-content-title">业务合同号:  </td>  
	            	<td class="td-content">
	            		<span name="contractnum" id="contractnum"></span>
	            	</td>
	            <tr  class="tr-even">
	            	<td class="td-content-title">客户名称:  </td>  
	            	<td class="td-content"  >
	            	<span name="custname" id="custname" value="${param.custname}"/>
	            	</td>
	            	<td class="td-content-title"> </td>  
	            	<td class="td-content" >
	            	 
	            	</td>
	            </tr>
	            <tr class="tr-odd">
	                <td class="td-content-title">调息前利率:</td>  
	            	<td class="td-content">
	            		<span name="rateoriginal" id="rateoriginal" value="${param.rateoriginal}"/>
	            	</td>
	            	 <td class="td-content-title">调息后利率:</td>
	            	<td class="td-content">
	            		<span name="rateadjust" id="rateadjust" value="${param.rateadjust}"/>
	            	</td>
	            </tr>
	            <tr class="tr-even">
	            	<td class="td-content-title">调息前IRR:</td>  
	            	<td class="td-content">
	            		<span name="oldirr" id="oldirr" value="${param.oldirr}"/>
	            	</td>
	            	<td class="td-content-title">调息后IRR:</td>  
	            	<td class="td-content">
	            		<span name="newirr" id="newirr" value="${param.newirr}"/>
	            	</td>
	            </tr>
	            
	      </table>
	      <div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0"  style="width: 100%;" bodyStyle="border:0px;">
			<div title="租金计划对比" name="id_table_leasing_current_regulating_of_breathing_container"  >
				 <jsp:include page="compareTempListBefore.jsp" ></jsp:include>
			</div>
			<div title="租前息对比" name="id_table_leasing_interest_current_regulating_of_breathing_container" >
				<jsp:include page="compareTempListBeforeInterest.jsp" ></jsp:include>
			</div>
		 </div>
	</div>
			
</body>
</html>