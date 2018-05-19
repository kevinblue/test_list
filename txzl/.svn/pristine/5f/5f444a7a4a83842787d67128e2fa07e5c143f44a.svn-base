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
		
	});
</script>
</head>
<body>
	<div style="margin-left: 10px; margin-top: 10px; height: 30px; width: 80%">
		<div id="title" class="x-panel-table-div-title" style="width: 180px; text-align: center">调息对比信息</div>
	</div>
	<div id="mianbody" style="border-style: solid; margin: 0 10px 10px 10px; border-color: #DDDDDD; border-width: 2px; width: 800px; height: 500px; text-align: center">
		<div id="id_custForm" style="overflow:auto; margin: 10px 10px 10px 10px; border-style: solid; border-color: #DDDDDD; border-width: 2px; width: 98.2%; height: 90%">
			<center>
			        <table style="width:95%" class="fillTable">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr  class="tr-odd">
			            	<td class="td-content-title">合同编号:</td>  
			            	<td class="td-content">
			                    <span name="contractid" id="contractid" value="${param.contractid}"/>
			               <td class="td-content-title">合同号:  </td>  
			            	<td class="td-content">
			            		<span name="contractnum" id="contractnum"/>
			            	</td>
			            <tr  class="tr-even">
			            	<td class="td-content-title">客户名称:  </td>  
			            	<td class="td-content">
			            	<span name="custname" id="custname" value="${param.custname}"/>
			            	</td>
			            	<td class="td-content-title"></td>  
			            	<td class="td-content">
			            	
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
		            <table>
		            <tr><td><div id="id_table_leasing_compare_his_list"></div></td></tr>
		            </table>
	        </center>
			
			
		</div>
		<div style="text-align: right; height: 30px; margin: 0px">
			<a style="margin-left: 20px; margin-right: 20px" href="javascript:void(0);" class="btn btn-primary" onclick='window.close()'><span>关闭</span></a>
		</div>
	</div>
</body>
<jsp:include page="compareHisList.jsp"></jsp:include>
</html>