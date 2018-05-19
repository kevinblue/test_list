<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/DataUtil.js"></script>
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" >
function onbeforeshowpopup(e) {
    miniui_ext.onbeforeshowpopup(e);
}

function comboboxChanged(e){
	 var cbb=e.sender;
	 mini.get("rawValue_"+cbb.name).setValue(cbb.text);
}
</script>