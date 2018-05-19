<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript">
</script>
<title>诉讼结论登记</title>
<%@include file="/base/mini.jsp"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
</head>
<body style="overflow: auto;">
<!-- 撑满布局 -->
<div id="id_table_application_detail">
	<div id="mini-panel" title="诉讼基本信息" showCollapseButton="true" style="width: 100%;" >
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
			<tr>
		        <td style="padding:5px">
		        	<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
		        </td>
		    </tr>
		</table>
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" >   
		    <tr style="background:#468CC8;height:32px" ><td style="padding-left:10px" colspan='4'><font color="white">诉讼基本信息</font></td></tr>
		     <tr class="tr-even">
		        <td class="td-content-title" width="12%">法务编号：</td>
		        <td class="td-content" width="38%">
		            <input name="lawnum" class="mini-textbox" readOnly="readonly" value='${requestScope["lawnum"]}' />
		        </td>
		        <td class="td-content-title" width="12%">合同编号：</td>
		        <td class="td-content" width="38%">
		            <input width="55%" name="contract_id" class="mini-textbox" required="true" readonly="readonly" value='${requestScope["contract_id"]}'/>
		        </td>
		    </tr>
		    <tr class="tr-even">
		        <td class="td-content-title" width="12%">业务合同编号：</td>
		        <td class="td-content" width="38%">
		            <input width="55%" name="contract_number" class="mini-textbox" required="true" readonly="readonly" value='${requestScope["contract_number"]}'/>
		        </td>
		          <td class="td-content-title" width="12%">客户名称：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="cust_name" class="mini-textbox" readonly value='${requestScope["cust_name"]}'/>
		        </td>
		    </tr>
		    <tr class="tr-even">
		        <td class="td-content-title" width="12%">身份证/组织机构代码：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="card_no" class="mini-textbox" readonly value='${requestScope["card_no"]}'/>
		        </td>
		        <td class="td-content-title" width="12%">项目经理：</td>
		        <td class="td-content" width="38%">
		        	<input width="55%" name="projmanage" class="mini-textbox"  readonly value='${requestScope["projmanage"]}'/>
		        </td>
		    </tr> 
		</table>
	</div>
	</div>
<!-- 页签 -->
<div id="tabDeatils" class="mini-tabs" activeIndex="0" style="width: 100%;" bodyStyle="padding:0;border:0;">
	    <div title="诉讼结论" name="applicationcust" >
	    	<jsp:include page="litigation_conclusion_registration_detail.jsp" ></jsp:include>
	    </div>	 
</div>
<script type="text/javascript">
//初始化
jQuery(function(){
	$(".mini-textbox-readOnly .mini-textbox-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-button").css("display", "none");
});
</script>
</body>
</html>