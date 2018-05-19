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
						<div title="资金收付计划" name="fund_fund_charge" iconCls="icon-cut">
							<jsp:include page="fund_fund_plan.jsp" ></jsp:include>
						</div> 
						 <div title="现金流" name="fund_cash_flow" iconCls="icon-cut">
							<jsp:include page="fund_cash_plan_frame.jsp" ></jsp:include>
						</div>
					</div>
				</div>
			</td> 
		</tr> 
	</table>
</div>

<script type="text/javascript">
var form = new mini.Form("businessconditionForm");
//获取对象的数值
function getNumber(id){
    var value = mini.get(id).getValue();
    try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}
function decimalReal(num,v)
{
   var dight  =(num*Math.pow(10,v)/Math.pow(10,v)).toFixed(v);  
   return parseFloat(dight);
} 
//四舍五入
function decimal(num,v)
{	//num表示要四舍五入的数,v表示要保留的小数位数。
	if(0 == v)
	{
		return decimalReal(decimalReal(num,2),0);
	}
    return decimalReal(num,v);		
}
function $minigetvalue(id){
	return mini.get(id).getValue();
}
function $mini(id){
	return mini.get(id);
}
function $minigetinputtext(id){
	var value = mini.get(id).getInputText();
	try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}

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

function $miniEnable(id){
	var miniObj = mini.get(id);
	miniObj.enable();
	var uiCl = miniObj.uiCls;
	var jQueryObj = $('#'+id);
	if(uiCl == 'mini-textbox'){
		jQueryObj.find('.mini-textbox-border').attr("style", "");
		jQueryObj.find('.mini-textbox-border').prop("readonly","");
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
</body>
</html>

