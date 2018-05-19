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
<title>网络资产监控申请</title>
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
	<div id="mini-panel" title="申请基本信息" showCollapseButton="true" style="width: 100%;" >
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
			<tr>
		        <td style="padding:5px">
		        	<a class="mini-button" iconCls="icon-save" onclick="saveApplication">保存</a>
		        	<span class="separator"></span>
		        	<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
		        </td>
		    </tr>
		</table>
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" >   
		    <tr style="background:#468CC8;height:32px" ><td style="padding-left:10px" colspan='4'><font color="white">申请基本信息</font></td></tr>
		    <tr class="tr-odd">
		       <%--  <td class="td-content-title" width="12%">资产专员：</td>
		        <td class="td-content" width="38%">
 		        	 <input width="55%" name="applyusername" class="mini-textbox " type='text' required="true" readonly="readonly" value="${requestScope['applyusername']}"/>
		        </td> --%>
		        <td class="td-content-title" width="12%">申请编号：</td>
		        <td class="td-content" width="38%">
		        	<input width="55%" name="applyno"  class="mini-textbox" required="true" readonly="readonly" value="${requestScope['applyno']}"/>
		        	<input width="55%" name="applysid" style="display:none"  class="mini-textbox" required="true" readonly="readonly" value="${requestScope['applyid']}"/>
		        </td>
		        <td class="td-content-title" width="12%" >申请年月：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="applydate" class="mini-datepicker" type="date" format="yyyy-MM" readonly="readonly" required="true" allowinput="false" value="${requestScope['applydate']}" onvaluechanged="changeDate(e)"/>
		        </td>
		    </tr>
		   <%--  <tr class="tr-even">
		        <td class="td-content-title" width="12%">申请状态：</td>
		        <td class="td-content" width="38%">
		            <input width="55%" name="applystatus" class="mini-textbox" required="true" readonly="readonly" value="${empty requestScope['applystatus']?'未审核': requestScope['applystatus']}"/>
		        </td>
		    </tr> --%>
		     <tr class="tr-odd" style="height:40px">
		        <td class="td-content-title" width="12%" height="70px">备注：</td>
		        <td class="td-content" colspan="3">
					<textarea style="width: 81%;height: 90%" name="applymemo"  class="mini-textarea" value="${requestScope['applymemo']}"></textarea>
		        </td>
		    </tr>
		    <tr class="tr-even">
		        <td class="td-content-title" width="12%">创建人：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="creator" class="mini-textbox" readonly value="${requestScope['creator']}"/>
		        </td>
		        <td class="td-content-title" width="12%">创建时间：</td>
		        <td class="td-content" width="38%">
		        	<input width="55%" name="createdate" class="mini-textbox"  readonly value="${requestScope['createdate']}""/>
		        </td>
		    </tr>
		 <%--    <tr class="tr-odd">
		    	<td class="td-content-title" width="12%">修改人：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="modificatorname" class="mini-textbox" readonly value="${requestScope['modificatorname']}"/>
		        </td>
		    	<td class="td-content-title" width="12%">修改时间：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="modifydate" class="mini-textbox" readonly value="${requestScope['modifydate']}"/>
		        </td>
		    </tr> --%>
		    <tr class="tr-odd">
				<td class="td-content-title" width="12%">百度：</td>
				<td class="td-content" width="38%"><a href="http://www.baidu.com" target="_blank">www.baidu.com</a></td>
				<td class="td-content-title" width="12%">执行人网：</td>
				<td class="td-content" width="38%"><a href="http://zhixing.court.gov.cn/search/" target="_blank">zhixing.court.gov.cn</a></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" width="12%">裁判文书网：</td>
				<td class="td-content" width="38%"><a href="http://wenshu.court.gov.cn/ " target="_blank">wenshu.court.gov.cn</a></td>
				<td class="td-content-title" width="12%"></td>
				<td class="td-content" width="38%"></td>
			</tr>
		</table>
	</div>
	</div>
<!-- 页签 -->
<div id="tabDeatils" class="mini-tabs" activeIndex="0" style="width: 100%;" bodyStyle="padding:0;border:0;">
	    <div title="客户异常信息" name="applicationcust" >
	    	<jsp:include page="asset_network_moniter_application_detail.jsp" >
	    		<jsp:param name="applyid"  value="${requestScope['applyid']}"/>
	    	</jsp:include>
	    </div>	 
</div>
<script type="text/javascript">
//窗体关闭退出时刷新父窗口
window.onunload = function(){ 
	window.opener.location.reload(); 
} 

//初始化
mini.parse("id_table_application_detail");
var form = new mini.Form("id_table_application_detail");

function changeDate(e){
	applydate=mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM');
}

function saveApplication() {
	
	var applyid=mini.getbyName("applysid").getValue();  
	
	var grid=mini.get("csut_apply_list").getData();
	
	/* if(grid.length==0){
		mini.alert('申请详细信息不能为空！');
		return false;
	} */
	if(mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM')==""){
		mini.alert('请选择申请年月！');
		return false;
	}
	
	var params={};
	params["applydate"]=mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM');
	//params["applystatus"]=mini.getbyName("applystatus").getValue();
	params["applymemo"]=mini.getbyName("applymemo").getValue();
	//params["applyusername"]=mini.getbyName("applyusername").getValue();
	params["json_csut_apply_list_str"]=mini.encode(grid);
	var URL="";
	if('add'=='<%=opertype%>'){
		URL=getRootPath()+"/acl/addAssetsNetMonitorApplication.acl";
		params["applyid"]=applyid;
	}else{
		URL=getRootPath()+"/acl/editAssetsNetMonitorApplication.acl";
		params["applyid"]=applyid;
	}
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
}

jQuery(function(){
	$(".mini-textbox-readOnly .mini-textbox-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-button").css("display", "none");
	if(mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM')==""){
		mini.getbyName("applydate").setValue(new Date());
	}
	applydate=mini.formatDate(mini.getbyName("applydate").getValue(),'yyyy-MM');;
});
</script>
</body>
</html>