<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>

<!--表单页签1开始-->
<!--表单处理-->
<div id="id_workflowFormContainer" style="width:100%;overflow:auto;position:relative;">
	<iframe name="real_submit_frame" style="display:none;" id="id_real_submit_frame"></iframe>
	<form id="id_submitProcessedForm" enctype="multipart/form-data" target="real_submit_frame" action="{pageContext.request.contextPath}/submitProcessedForm/jbpm/submitProcessedForm.action" method="post">
		<!-- 流程提交隐藏域 -->
		<jsp:include page="getWorkflowFormHiddenField.jsp"></jsp:include>
		<!-- 当前任务节点表单路径相对于jbpm-core/forms下的jsp页面 -->
		<jsp:include page="/${empty requestFormPath ? 'errorPages/error404.bi' : requestFormPath }" flush="true"></jsp:include>
		<script type="text/javascript">
			mini.unmask(document.body);
		</script>
	</form>
</div>
<script>
jQuery(function(){
	$(".mini-textbox-readOnly .mini-textbox-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-button").css("display", "none");
	$("<font class='required-tag'>*</font>").insertAfter(".mini-required");
});
function $miniEnable(id){
	var miniObj = mini.get(id);
	miniObj.enable();
	var uiCl = miniObj.uiCls;
	var jQueryObj = $('#'+id);
	if(uiCl == 'mini-textbox'){
		jQueryObj.find('.mini-textbox-border').attr("style", "");
	}else if(uiCl == 'mini-combobox'){
		jQueryObj.find('.mini-textbox-border').attr("style", "");
		jQueryObj.find('.mini-buttonedit-border').attr("style", "");
		jQueryObj.find(".mini-buttonedit-button").css("display", "block");
	}
}
function $miniDisable(id){
	var miniObj = mini.get(id);
	miniObj.disable();
	var uiCl = miniObj.uiCls;
	var jQueryObj = $('#'+id);
	if(uiCl == 'mini-combobox'){
		jQueryObj.find(".mini-buttonedit-button").css("display", "none");
	}
}
function $miniSetCombValue(id,value,text){
	var miniObj = mini.get(id);
	miniObj.setValue(value);
	miniObj.setText(text);
	var miniHiddenObj = mini.get("rawValue_"+id).setValue(text);
}
</script>