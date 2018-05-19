<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%
String id=request.getParameter("contractid");//ID
String opertype = request.getParameter("opertype");
%>
<script type="text/javascript">
var contractid='<%=id%>'
var applydate="";
</script>
<title>应收账款信息维护</title>
<%@include file="/base/mini.jsp"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
</head>
<%-- <input style="display:none" class="mini-textarea" id="applynumber" name="applynumber" value="${requestScope['applynumber'] }"></input>
<input style="display:none" class="mini-textarea" id="assetMngApply" name="assetMngApply" value="${requestScope['assetMngApply'] }"></input> --%>
<body style="overflow: auto;">
<!-- 撑满布局 -->
<div class="fillTableContainer">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
			<tr>
		        <td style="padding:5px">
		        <!-- 	<a class="mini-button" iconCls="icon-save" onclick="saveApplication">保存</a> -->
		        	<!-- <span class="separator"></span> -->
		        	<a class="mini-button" iconCls="icon-close" onclick="closeWin">关闭</a>
		        </td>
		    </tr>
		</table>
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入保理基本信息 --> 
			 <jsp:include page="../factoring_comm/contract_factoring_base_info.jsp">
			 	<jsp:param value="true" name="isView"/>
			 </jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
	<div title="应收账款信息" name="proj_invoice" iconCls="icon-cut" >
		<jsp:include page="account_receivable_info_invoice.jsp" ></jsp:include>
	</div>
</div>
<script type="text/javascript">
//初始化
mini.parse("id_table_application_detail");
var form = new mini.Form("id_table_application_detail");

function changeDate(e){
	applydate=mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM');
}
function parentCloseSX(){
	if('${param.opertype}'!="view"){
	 	window.opener.refresh();
	}
}
function saveApplication() {
	if('${param.opertype}'=="view"){
		mini.alert("只读状态不允许修改");
		return ;
	}
	
	var params={};
	params["applydate"]=mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM');
	params["applystatus"]=mini.getbyName("applystatus").getValue();
	params["applymemo"]=mini.getbyName("applymemo").getValue();
	params["applyusername"]=mini.getbyName("applyusername").getValue();
	params["json_csut_apply_list_str"]=mini.encode(grid);
	var URL="";
	URL=getRootPath()+"/acl/updateAssetsMng.acl";
	var applyid = "";
	if('${param.opertype}'=="edit"){
		applyid = '${param.applyid}';
	} else {
		applyid = mini.getbyName("assetMngApply").getValue();
	}
	params["applyid"] = applyid;
    $.ajax({
		url:URL,
		data:params,
		type:'post',
		async:false,
		success:function(e){
			var result=mini.decode(e);
			if(result.flag=='true'){
				 mini.confirm(result.msg,"确认",function(action){
					 if(action=='ok'){
						 onCancel();
					 }else{
						 onCancel();
					 }
				 });
			}else{
				mini.alert('发生异常：'+result.msg);
			}
		}
	}); 
    parentCloseSX();
}


function closeWin(){
	if ('${param.opertype}'=="view") {
		onCancel();
	} else {
		parentCloseSX();
		onCancel();
	}
}
jQuery(function(){
	$(".mini-textbox-readOnly .mini-textbox-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-button").css("display", "none");
	if(mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM')==""){
		mini.getbyName("applydate").setValue(new Date());
	}
	applydate=mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM');
	if('${param.opertype}'=="edit"){
		miniui_ext.disableFormFields("tab_apply","applymemo");
	}
});
</script>
