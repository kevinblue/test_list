<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 通用变量和函数 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><mini:param  name='currentTaskFormTitle'/></title>
<!--样式和引用以及通用函数-->
<jsp:include page="getWorkflowCommonCssAndJs.jsp"></jsp:include>
<script type="text/javascript">
jQuery(function(){
	/* 
	mini.mask({
	    el: document.body,
	    cls: 'mini-mask-loading',
	    html: '数据加载中，请稍后...'
	});
	setTimeout(function(){
		mini.unmask(document.body);
	}, 3000);
	*/
	var windowHeightV = jQuery(window).height() - 100;
	var windowWidthV = jQuery(window).width() - 20;
	jQuery('#id_workflowFormContainer').css({height:windowHeightV});//,width:windowWidthV
});
</script>
</head>
	<body style="overflow:hidden; padding-bottom:2px;">
		<!-- 流程信息容器-->
		<div style="overflow: hidden;margin: 0 auto;width: 99.5%;padding-top: 2px;" id="__processPageAllContainer">
			<!--提交按钮-->
			<jsp:include page="getWorkflowButtons.jsp"></jsp:include>
			<!-- 流程信息明细 -->
			<jsp:include page="getWorkflowMainContainer.jsp"></jsp:include>
			<!-- 弹出意见框开始 -->
			<jsp:include page="getWorkflowChoseAdvise.jsp"></jsp:include>  	 
		</div>
	
		<!-- 流程页面加载完成处理 -->
		<jsp:include page="getWorkflowLoadFinishProcess.jsp"></jsp:include>
		<!-- 流程提交相关函数 -->
		<jsp:include page="getWorkflowSubmitAndCallBack.jsp"></jsp:include>
		<!-- 流程页面通用函数 -->
		<jsp:include page="getWorkflowCommonFunc.jsp"></jsp:include>
		<!-- 流程页面操作函数 -->
		<jsp:include page="getWorkflowButtonsFunc.jsp"></jsp:include>
</body>
</html>