<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<title>待补文件管理</title>
<%@include file="/base/mini.jsp"%>
<%
String id=request.getParameter("id");
String custname=request.getParameter("custname");
String contractnumber=request.getParameter("contractnumber");
String signdate=request.getParameter("signdate");
%>
<script type="text/javascript">
	var custname = '${param.custname}';
</script>
<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/js/my97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>

<script type="text/javascript">
var docjson='[]';
var params = {};
params['contract_id'] ="${param.id}"; 

jQuery(function(){
	//初始化数据
	var data = miniui_ext.initPageData("/docadd/limitmagere_list.xml","form1","${param.id}");
	ajaxRequest({
		url:'${pageContext.request.contextPath}/table/getTree2TableJsonInfoContract.action',
		async:false,
		params:params,
		timeout:60*60*1000,
		success:function(res){
			docjson=res.responseText;
		}
	 }); 
});
</script>
</head>
<body>
	<div id="form1" class="mini-panel" style="width: 100%;">
		<table class="fillTable">
			<tr>
				<td colspan="6" style="border-bottom: 1px solid #99CCFF;">
					<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
					<a class="mini-button" iconCls="icon-save" onclick="saveDateDoc">保存</a>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="15%">客户名称:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="cust_name" value="${param.custname}" allowInput="false"></input></td>
				<td class="td-content-title" width="15%">合同编号:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="contract_id" value="${param.contractnumber}" allowInput="false"></input></td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="15%">项目经理:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="projmanage" value="${param.projmanage}" allowInput="false"></input></td>
				<td class="td-content-title" width="15%">部门:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="department" value="${param.department}" allowInput="false"></input></td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="15%">起租日:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="start_date_" value="${param.start_date_}"allowInput="false"></input></td>
				<td class="td-content-title" width="15%">拨款时间:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="firstdate" value="${param.firstdate}"allowInput="false"></input></td>
			
			</tr>
		</table>
	</div>
	<div id="table_id1"></div>
	<div class="mini-tabs" activeIndex="0" style="width: 100%" id="tabDetailsBase">
		<div title="放款资料清单">
			<div id="id_table_fundput_file_container" style="overflow: hidden;">
				<jsp:include page="fundput_file_list.jsp"></jsp:include></div>
		</div>
<%-- 		<div title="合同文本清单">
			<div id="id_table_conntract_file_container" style="overflow: hidden;">
				<jsp:include page="contract_file_list.jsp"></jsp:include></div>
		</div> --%>
	</div>
</body>
<script type="text/javascript">
function onCancel(){
	window.opener.location.href=window.opener.location.href;
	window.close();
}


function saveDateDoc(){
	var docdata          = JsonUtil.encode(mini.get("table_id1").getChanges());//放款资料清单
	var url=getRootPath()+"/acl/savecontractdocjson.acl";
	//遮罩
	mini.mask({
		el : document.body,
		cls : 'mini-mask-loading',
		html : '数据操作中 请稍等...'
	});
	jQuery.ajax({
		url : url,
		type : 'POST',
		timeout : 30 * 1000,
		data : {
			json_document_list_str : docdata,
			cid : "${param.id}"
		},
		dataType : 'json',
		error : function(res, errInfo, e) {
			mini.unmask(document.body);
		},
		success : function(resultJson) {
			var returnState = resultJson.returnType;
			switch (returnState) {
			case "SUCCESS": {
				mini.unmask(document.body);
				mini.alert("保存成功！");
				mini.get("table_id1").reload();
				break;
			}
			case "FAILURE": {
				mini.unmask(document.body);
				mini.alert("保存失败！"+resultJson.content);
				break;
			}
			}
		}
	});
}
</script>
</html>