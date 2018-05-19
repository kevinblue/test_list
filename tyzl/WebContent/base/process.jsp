<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!-- css sheet -->
<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- javascript libray -->

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>  -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyMultiTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/ajaxfileupload.js"></script>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>

<script type="text/javascript" >
var isCompletedTask=("true"=="<mini:param name='isCompletedTask'/>");   //当前结点是否结束
var flowName="<mini:param name='currentWorkFlowName'/>";//流程名称
var stepName="<mini:param name='currentTaskActivityName'/>";   //当前节点的名称
var flowUnid="<mini:param name='currentProcessInstanceDBID'/>";   //当前流程的ID
var stepUnid="<mini:param name='currentTaskId'/>";   //当前节点的id
function comboboxChanged(e){
	 var cbb=e.sender;
	 mini.get("rawValue_"+cbb.name).setValue(cbb.text);
}


</script>