<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %>
<%@ taglib uri="/minidict" prefix="mini"%>
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">  
<%@include file="/base/mini.jsp"%>

<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">

<%-- <link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css"> --%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/apcomponent/apattachmentfileupload/apAttachmentFileUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyCommonUserSelection.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>

<%--dragonmagic --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dragonmagic/tree2TableUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dragonmagic/workflow_${currentLocale}.js"></script>

<script type="text/javascript">
  var currentJbpmWorkflowHistoryInfoId = "<mini:param  name='currentJbpmWorkflowHistoryInfoId'/>";
  var currentTaskActivityDetailId      = "<mini:param  name='currentTaskActivityDetailId'/>";
  var isViewHistoryTask  = ( "true" == "<mini:param  name='isViewHistoryTask'/>" ) || ("<mini:param  name='currentRequestTaskType'/>" == "read")|| ("<mini:param  name='currentRequestTaskType'/>" == "signature") ;
  var isCompletedTask    = ( "true" == "<mini:param  name='isCompletedTask' />" );
  //i18n
  var submitButtonDisplayText ="Submit"; //("提交"),
  var resetRouteButtonDisplayText="ResetRoute";//("任意路由"),
  var saveButtonDisplayText = "Save";//("保存"),
  var backButtonDisplayText ="Back";//("退回"),
  var deleteButtonDisplayText="Delete";//("删除"),
  var deleteButtonNoSavedDisplayText="DeleteNoSaved";//("删除"),
  var abondonButtonDisplayText="Abondon";//("废弃流程"),
  var finishButtonDisplayText="Finish";//("已结束"),
  var draftButtonDisplayText="Draft";//("草稿"),
  var pendingButtonDisplayText="Pending";//("待处理"),
  var signatureButtonDisplayText="Signature";//("会签"),
  var readButtonDisplayText="WorkflowRead";//("传阅"),
  var assignmentButtonDisplayText="Assignment";//("工作指派"),
  
  var isCompletedTask=("true"=="${isCompletedTask}");   //当前结点是否结束
  var flowName="<mini:param name='currentWorkFlowName'/>";//流程名称
  var stepName="${currentTaskActivityName}";   //当前节点的名称
  var flowUnid="${currentProcessInstanceDBID}";   //当前流程的ID
  var stepUnid="<mini:param name='currentTaskId'/>";   //当前节点的id
</script>
<script type="text/javascript">
	var all_width  =  document.documentElement.clientWidth;
	var all_height =  document.documentElement.clientHeight;
	var oldCurrentPageClientWidth = all_width;
	var oldCurrentPageClientHeight = all_height;
	
	var formHeight = 0;
	var formWidth = 0;
	var containerWidthAdd = 12;
	var containerHeightAdd = 115;
	formHeight = all_height-containerHeightAdd-10;
	formWidth = all_width-containerWidthAdd-28;
	var nextChosePersonArr = new Array();
	var backChosePersonArr = new Array();
	var allChosePersonArr = new Array();
	var isShowSubmitChosePersonWindow = true;
	var isShowBackChosePersonWindow = true;
	var globalCurrentPressButtonText = "";
	var loadMask  = null;
	var lazyLoadedObj = {};
	function comboboxChanged(e){
		 var cbb=e.sender;
		 mini.get("rawValue_"+cbb.name).setValue(cbb.text);
	}
	
	function  saveConditonInfo(){
		$mini('json_fund_rent_plan_str').setValue(mini.encode($mini('fund_rent_plan_frame').getData()));
		$mini('json_fund_cash_flow_str').setValue(mini.encode($mini('fund_cash_plan_frame').getData()));
		$mini('json_fund_fund_charge_str').setValue(mini.encode($mini('fund_fund_plan').getData()));
	} 
</script>
<style type="text/css">
.route-choose {
	width: 100%;
	position: relative;
}

.route-choose table{
	width: 98%;
	position: relative;
	border-collapse: collapse;
}

.route-choose td{
	border: 1px dotted #aaa;
	border-collapse: collapse;
}

</style>