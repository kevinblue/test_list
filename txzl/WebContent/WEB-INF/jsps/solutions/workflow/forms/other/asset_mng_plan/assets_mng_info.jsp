<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%
String applyid=request.getParameter("applyid");//ID
String opertype = request.getParameter("opertype");
%>
<script type="text/javascript">
var applyid='<%=applyid%>'
var applydate="";
</script>
<title>资产巡视计划申请</title>
<%@include file="/base/mini.jsp"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
</head>
<input style="display:none" class="mini-textarea" id="applynumber" name="applynumber" value="${requestScope['applynumber'] }"></input>
<input style="display:none" class="mini-textarea" id="assetMngApply" name="assetMngApply" value="${requestScope['assetMngApply'] }"></input>
<body style="overflow: auto;">
<!-- 撑满布局 -->
<div id="id_table_application_detail">
	<div id="mini-panel" title="申请基本信息" showCollapseButton="true" style="width: 100%;" >
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
			<tr>
		        <td style="padding:5px">
		        	<a class="mini-button" iconCls="icon-save" onclick="saveApplication">保存</a>
		        	<span class="separator"></span>
		        	<a class="mini-button" iconCls="icon-close" onclick="closeWin">关闭</a>
		        </td>
		    </tr>
		</table>
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="tab_apply">   
		    <tr style="background:#468CC8;height:32px" ><td style="padding-left:10px" colspan='4'><font color="white">申请基本信息</font></td></tr>
		    <tr class="tr-odd">
		        <td class="td-content-title" width="12%">资产主管：</td>
		        <td class="td-content" width="38%">
 		        	 <input width="55%" name="applyusername" class="mini-textbox " type='text' required="true" readonly="readonly" value="${requestScope['applyusername']}"/>
		        </td>
		        <td class="td-content-title" width="12%" >申请年月：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="applydate" class="mini-datepicker" type="date" format="yyyy-MM" required="true" allowinput="false" value="${requestScope['applydate']}" onvaluechanged="changeDate(e)"/>
		        </td>
		    </tr>
		    <tr class="tr-even">
		        <td class="td-content-title" width="12%">申请状态：</td>
		        <td class="td-content" width="38%">
		            <input width="55%" name="applystatus" class="mini-textbox" required="true" readonly="readonly" value="${empty requestScope['applystatus']?'未审核': requestScope['applystatus']}"/>
		        </td>
		        <td class="td-content-title" width="12%">申请编号：</td>
		        <td class="td-content" width="38%">
		            <input width="55%" name="applynumber" class="mini-textbox" required="true" readonly="readonly" value="${requestScope['applynumber']}"/>
		        </td>
		    </tr>
		     <tr class="tr-odd" style="height:40px">
		        <td class="td-content-title" width="12%" height="70px">备注：</td>
		        <td class="td-content" colspan="3">
					<textarea style="width: 81%;height: 90%" name="applymemo"  class="mini-textarea" value="${requestScope['applymemo']}"></textarea>
		        </td>
		    </tr>
		    <tr class="tr-even">
		        <td class="td-content-title" width="12%">创建人：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="creatorname" class="mini-textbox" readonly value="${sessionScope.loginUser.realname}"/>
		        </td>
		        <td class="td-content-title" width="12%">创建时间：</td>
		        <td class="td-content" width="38%">
		        	<input width="55%" name="createdate" class="mini-textbox"  readonly value="${requestScope['createdate']}""/>
		        </td>
		    </tr>
		    <tr class="tr-odd">
		    	<td class="td-content-title" width="12%">修改人：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="modificator" class="mini-textbox" readonly value="${requestScope['modificator']}"/>
		        </td>
		    	<td class="td-content-title" width="12%">修改时间：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="modifydate" class="mini-textbox" readonly value="${requestScope['modifydate']}"/>
		        </td>
		    </tr>
		</table>
	</div>
	</div>
<!-- 页签 -->
<div id="tabDeatils" class="mini-tabs" activeIndex="0" style="width: 100%;" bodyStyle="padding:0;border:0;">
	    <div title="申请详细信息" name="applicationcust" >
	    	<jsp:include page="asset_mng_detail.jsp" ></jsp:include>
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
	 window.opener.refresh();
}
function saveApplication() {
	var grid=mini.get("csut_apply_list").getData();
	
	if(grid.length==0){
		mini.alert('申请详细信息不能为空！');
		return false;
	}
	if(mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM')==""){
		mini.alert('请选择申请年月！');
		return false;
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
</body>
</html>