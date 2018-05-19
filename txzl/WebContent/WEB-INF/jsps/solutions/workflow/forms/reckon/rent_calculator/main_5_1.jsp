<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%String id=request.getParameter("id");%>
<title>租金计算器</title>
<%@include file="/base/mini.jsp"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<style>
	#reckonContainer .mini-panel-header{
		background :rgb(74,165,219);
	}
	 #reckonContainer .mini-textbox {
		width :105px;
	}
	
	#reckonContainer .mini-buttonedit {
		width :105px;
	} 
	.element-readonly {
		background: #f0f0f0;
	}
</style>
</head>
<body style="overflow: auto;">
<div class="fillTableContainer" id="reckonContainer" >
	<table class="fillTable" cellspacing="0" cellpadding="0" style="width: 100%;">
		<tr>
			<td colspan=4>
				<jsp:include page="condition_frame_5_1.jsp"></jsp:include>
		</td>
		</tr>
		 <tr>
			 <td colspan=4>
				<div class="mini-panel" title="租金计划" showCollapseButton="true" style="width: 100%;">
				  	 <div id="conditionDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab"  style="width: 99%;" bodyStyle="padding:0;border:0;">
					  	 <div title="租金计划" name="fund_rent_plan" iconCls="icon-cut" >
							 <jsp:include page="fund_rent_plan_frame.jsp" ></jsp:include>
						</div> 
						 <div title="现金流" name="fund_cash_flow" iconCls="icon-cut">
							<jsp:include page="fund_cash_plan_frame.jsp" ></jsp:include>
						</div>
						<div title="资金收付计划" name="fund_fund_charge" iconCls="icon-cut">
							<jsp:include page="fund_fund_plan.jsp" ></jsp:include>
						</div> 
					</div>
				</div>
			</td> 
		</tr> 
	</table>
</div>
<script> 
	jQuery(function(){
		//租金计算器的客户承租人默认为，当前用户
		/* var currentUserId = "${sessionScope['login_userid']}";
		mini.get('conditionCustId').setValue(currentUserId); */
		mini.get('reckonProcess').setValue('rent_calculator');
		//虚拟docId  contractId
		mini.get('conditionDocId').setValue('0');
		mini.get('conditionContractId').setValue('CAL_CESHI')
		//同时将所有readOnly 的表单域，背景变成灰色
		var submitForm = document.getElementById("conditionForm");
		var formElements = submitForm.elements;
		
		for (var i = 0; i < formElements.length; i++) {
	         var formElement = formElements[i];
	       	 var flag = formElement.readOnly;
	         if (flag) {
	        	 var currentElementType = formElement.type;
	             if (formElement.className.indexOf("Wdate") > -1) {
	                 formElement.onclick = null;
		             removeClass(formElement, "Wdate");
	             }
	             if(formElement.type != 'button' && formElement.className.indexOf('mini-textbox-input') > -1){
		            addClass(formElement.parentNode, "element-readonly");
	             }
	         }
	     }
		
		/* if('${param.loadConditionFlag}' == 'true'){
			var tab = mini.get('tabDeatils');
			tab.setActiveIndex(8);
		} */
	});
</script>
</body>
</html>

